<template>
  <div id="main">
    <nav-bar id="nav"></nav-bar>
    <div id="con">
      <el-container id="type">
        <el-button-group id="itemType">
          <el-button class="item-button" :disabled="itemType === 'type'" type="info"
                     @click="changeSortType('type')">题型
          </el-button>
          <el-button class="item-button" :disabled="itemType === 'bank'" type="info"
                     @click="changeSortType('bank')">题库
          </el-button>
        </el-button-group>
        <el-container id="question-type" v-if="itemType === 'type'">
          <hr>
          <div class="types">
            <el-button class="type-button" type="primary">单选题</el-button>
            <div class="type-format">
              <span>请输入选项数：</span>
              <el-input-number size="small" step-strictly
                               v-model="radioOptionCount" :min="2" :max="6" :step="1" label="选项数">
              </el-input-number>
            </div>
          </div>
          <hr>
          <div class="types">
            <el-button class="type-button" type="primary">多选题</el-button>
            <div class="type-format">
              <span>请输入选项数：</span>
              <el-input-number size="small" step-strictly
                               v-model="choiceOptionCount" :min="2" :max="6" :step="1" label="选项数">
              </el-input-number>
            </div>
          </div>
          <hr>
          <div class="types">
            <el-button class="type-button" type="primary">填空题</el-button>
            <div class="type-format">
              <span>请输入空格数：</span>
              <el-input-number size="small" step-strictly
                               v-model="blankCount" :min="1" :max="4" :step="1" label="空格数">
              </el-input-number>
            </div>
          </div>
          <hr>
          <div class="types">
            <el-button class="type-button" type="primary">排序题</el-button>
            <div class="type-format">
              <span>请选择语句数：</span>
              <el-input-number size="small" step-strictly
                               v-model="sortCount" :min="2" :max="6" :step="1" label="语句数">
              </el-input-number>
            </div>
          </div>
          <hr>
          <div class="types">
            <el-button class="type-button" type="primary">判断题</el-button>
            <!--选择格式：是/否,T/F,yes/no-->
            <div class="type-format">
              <div id="top-span">
                <span>请选择判断格式：</span>
              </div>
              <el-radio-group v-model="judgeFormat" type="">
                <el-radio label="是/否"></el-radio>
                <el-radio label="T/F"></el-radio>
                <el-radio label="yes/no"></el-radio>
              </el-radio-group>
            </div>
          </div>
          <hr>
          <div class="types">
            <el-button class="type-button" type="primary">评分题</el-button>
            <div class="type-format">
              <div id="top-span">
                <span>请选择评分格式：</span>
              </div>
              <el-radio-group v-model="gradeFormat" type="">
                <el-radio label="百分制"></el-radio>
                <el-radio label="十分制"></el-radio>
                <el-radio label="五分制"></el-radio>
              </el-radio-group>
            </div>
          </div>
          <hr>
          <div class="types">
            <el-button class="type-button" type="primary">简答题</el-button>
            <div class="type-format">
              <span>请输入字数限制：</span>
              <el-input-number size="small" step-strictly
                               v-model="related" :min="50" :max="500" :step="50" label="字数限制">
              </el-input-number>
            </div>
          </div>
          <hr>
          <div class="types">
            <el-button class="type-button" type="primary">文件上传</el-button>
            <div class="type-format">
              <div id="top-span">
                <span>请输入大小限制（MB）：</span>
              </div>
              <el-input-number size="small" step-strictly
                               v-model="related" :min="2" :max="5" :step="1" label="大小限制">
              </el-input-number>
            </div>
          </div>
        </el-container>
      </el-container>
      <el-container id="content">
        <div class="questionnaire-name">
          <span class="name">{{ this.questionnaire.name }}</span>
        </div>
        <div class="questionnaire-description">
          <span>问卷说明：{{ this.questionnaire.description }}</span>
        </div>
        <hr>
        <div v-for="(question,index) in this.questionnaire.questions" :key="question">
          <div class="ques-type">
            <span>{{ index + 1 }}.&emsp;{{ question.type }}</span>
          </div>
          <!--根据type生成不同的题型，并生成真实的题目格式-->
          <!--可编辑题干、选项等内容-->
          <!--单选（序号以ABCD形式展现，序号前有单选框）-->
          <div v-if="question.type === '单选题'">
            <div class="ques-stem">
              <el-input class="stem-input" v-model="question.stem" placeholder="请输入题干"></el-input>
            </div>
            <div class="ques-option">
              <div v-for="(option, index) in question.options" :key="option"
                   class="option-input">
                <!--序号abcd形式，序号前有单选框-->
                <div class="option-row">
                  <el-checkbox disabled>{{ String.fromCharCode(index + 65) }}.</el-checkbox>
                  <el-input v-model="question.options[index]" placeholder="请输入选项"></el-input>
                </div>
              </div>
              <div class="ques-addition">
                <el-button class="small_btns" type="success" size="small" @click="plus">加</el-button>
                <el-button class="small_btns" type="warning" size="small" @click="reduce">减</el-button>
              </div>
            </div>
          </div>
          <!--多选（序号以ABCD形式展现，序号前有多选框）-->
          <div v-if="question.type === '多选题'">
            <div class="ques-stem">
              <el-input v-model="question.stem" placeholder="请输入题干"></el-input>
            </div>
            <div class="ques-option">
              <!--el-checkbox和el-input在一行-->
              <div v-for="(option, index) in question.options" :key="option"
                   class="option-input">
                <!--序号abcd形式，序号前有多选框，但disabled-->
                <div class="option-row">
                  <el-checkbox disabled>{{ String.fromCharCode(index + 65) }}.</el-checkbox>
                  <el-input v-model="question.options[index]" placeholder="请输入选项"></el-input>
                </div>
              </div>
              <div class="ques-addition">
                <el-button class="small_btns" type="success" size="small" @click="plus">加</el-button>
                <el-button class="small_btns" type="warning" size="small" @click="reduce">减</el-button>
              </div>
            </div>
          </div>
          <!--排序（无序号，语句后有上下移按钮）-->
          <div v-if="question.type === '排序题'">
            <div class="ques-stem">
              <el-input v-model="question.stem" placeholder="请输入题干"></el-input>
            </div>
            <div class="ques-option">
              <div v-for="(option, index) in question.options" :key="option"
                   class="option-input">
                <!--语句后有上下移按钮，但disabled-->
                <div class="option-row">
                  <el-input v-model="question.options[index]" placeholder="请输入选项"
                            style="margin-right: 40px;"></el-input>
                  <el-button class="small_btns" type="primary" size="small">上移</el-button>
                  <el-button class="small_btns" type="primary" size="small">下移</el-button>
                </div>
              </div>
              <div class="ques-addition">
                <el-button class="small_btns" type="success" size="small" @click="plus">加</el-button>
                <el-button class="small_btns" type="warning" size="small" @click="reduce">减</el-button>
              </div>
            </div>
          </div>
          <!--评分（根据评分格式生成不同的评分形式）-->
          <!--评分格式：五分制(el-rate)、十分制(el-radio)、百分制(el-input-number)-->
          <div v-if="question.type === '评分题'">
            <div class="ques-stem">
              <el-input v-model="question.stem" placeholder="请输入题干"></el-input>
            </div>
            <div class="ques-option">
              <div v-if="question.format === '五分制'">
                <el-rate disabled v-model="question.related" :max="5"></el-rate>
              </div>
              <div v-if="question.format === '十分制'">
                <el-radio-group v-model="question.related" disabled>
                  <el-radio label="1"></el-radio>
                  <el-radio label="2"></el-radio>
                  <el-radio label="3"></el-radio>
                  <el-radio label="4"></el-radio>
                  <el-radio label="5"></el-radio>
                  <el-radio label="6"></el-radio>
                  <el-radio label="7"></el-radio>
                  <el-radio label="8"></el-radio>
                  <el-radio label="9"></el-radio>
                  <el-radio label="10"></el-radio>
                </el-radio-group>
              </div>
              <div v-if="question.format === '百分制'">
                <el-input-number disabled size="small" step-strictly
                                 v-model="question.related" :min="0" :max="100" :step="1" label="分数">
                </el-input-number>
              </div>
            </div>
          </div>
          <!--填空（有多个空）-->
          <div v-if="question.type === '填空题'">
            <div class="ques-stem">
              <el-input v-model="question.stem" placeholder="请输入题干"></el-input>
            </div>
            <div class="ques-option">
              <div v-for="(option, index) in question.options" :key="option"
                   class="option-input">
                <div class="option-row">
                  <el-input v-model="question.options[index]" placeholder="请输入选项"></el-input>
                </div>
              </div>
              <div class="ques-addition">
                <el-button class="small_btns" type="success" size="small" @click="plus">加</el-button>
                <el-button class="small_btns" type="warning" size="small" @click="reduce">减</el-button>
              </div>
            </div>
          </div>
          <!--简答-->
          <div v-if="question.type === '简答题'">
            <div class="ques-stem">
              <el-input v-model="question.stem" placeholder="请输入题干"></el-input>
            </div>
            <div class="ques-answer">
              <div style="margin-bottom: 10px;align-items: center;">
                <span>字数限制：</span>
                <el-input-number size="small" step-strictly
                                 v-model="question.related" :min="50" :max="500" :step="50" label="字数">
                </el-input-number>
              </div>
              <el-input disabled type="textarea" placeholder="请输入答案"
                        :rows="3"></el-input>
            </div>
          </div>
          <!--文件上传-->
          <div v-if="question.type === '文件上传'">
            <div class="ques-stem">
              <el-input v-model="question.stem" placeholder="请输入题干"></el-input>
            </div>
            <div class="ques-answer">
              <div style="margin-bottom: 10px;align-items: center;">
                <span>大小限制：</span>
                <el-input-number size="small" step-strictly
                                 v-model="question.related" :min="2" :max="5" :step="1" label="大小限制">
                </el-input-number>
              </div>
              <el-upload disabled>
                <el-button size="default" type="primary">点击上传</el-button>
              </el-upload>
            </div>
          </div>
          <!--判断题（生成不同的评分形式，yes/no,T/F,是/否）-->
          <div v-if="question.type === '判断题'">
            <div class="ques-stem">
              <el-input v-model="question.stem" placeholder="请输入题干"></el-input>
            </div>
            <div class="ques-option">
              <div v-if="question.format === 'yes/no'">
                <el-radio-group v-model="question.related" type="" disabled>
                  <el-radio label="yes"></el-radio>
                  <el-radio label="no"></el-radio>
                </el-radio-group>
              </div>
              <div v-if="question.format === 'T/F'">
                <el-radio-group v-model="question.related" type="" disabled>
                  <el-radio label="T"></el-radio>
                  <el-radio label="F"></el-radio>
                </el-radio-group>
              </div>
              <div v-if="question.format === '是/否'">
                <el-radio-group v-model="question.related" type="" disabled>
                  <el-radio label="是"></el-radio>
                  <el-radio label="否"></el-radio>
                </el-radio-group>
              </div>
            </div>
          </div>
          <!--置顶、上移、下移、置底-->
          <div class="ques-operation">
            <el-button class="small_btns" type="info" size="small" @click="top(question)">置顶</el-button>
            <el-button class="small_btns" type="info" size="small" @click="up(question)">上移</el-button>
            <el-button class="small_btns" type="info" size="small" @click="down(question)">下移</el-button>
            <el-button class="small_btns" type="info" size="small" @click="bottom(question)">置底</el-button>
          </div>
          <div class="ques-operation">
            <el-button class="small_btns" type="primary" size="small" @click="insertUp(question)">上方插入</el-button>
            <el-button class="small_btns" type="primary" size="small" @click="insertDown(question)">下方插入</el-button>
            <el-button class="small_btns" type="danger" size="small" @click="deleteQuestion(question)">删除</el-button>
          </div>
          <hr>
        </div>
        <!--保存、退出按钮-->
        <div class="ques-save">
          <el-button type="primary" size="default" @click="save" style="margin-right: 40px;">保存</el-button>
          <el-button type="danger" size="default" @click="exit">退出</el-button>
        </div>
      </el-container>
    </div>
  </div>
</template>

<script>
import navBar from "../../components/nav";

export default {
  name: "question",
  inject: ['reload'],
  components: {
    navBar
  },
  created() {
    console.log(this.questionnaire);
    this.load();
  },
  data() {
    return {
      itemType: 'type',//type, bank
      radioOptionCount: 4,//单选题选项数
      choiceOptionCount: 4,//多选题选项数
      judgeFormat: '是/否',//判断题格式
      blankCount: 1,//填空题空格数
      answerSize: '50',//简答题字数限制
      gradeFormat: '百分制',//评分题格式
      sortCount: 4,//排序题语句数
      fileSize: '2',//文件上传大小限制(MB)
      // 题库信息（即一个特殊的问卷）
      bank: {
        id: this.$route.query.bank_id,
        name: '',
        description: '',
        questions: [],
      },
      // 问卷信息
      questionnaire: {
        id: this.$route.query.questionnaire_id,
        name: this.$route.query.name,
        description: this.$route.query.description,
        // 问卷中的题目，请举出具体例子
        questions: [
          {
            type: '单选题',
            stem: '这是题干',
            options: ['选项1', '选项2', '选项3', '选项4', '选项5']
          },
          {
            type: '多选题',
            stem: '这是题干',
            options: ['选项1', '选项2', '选项3']
          },
          {
            type: '判断题',
            stem: '这是题干',
            format: 'yes/no',
            related: 'yes',
          },
          {
            type: '判断题',
            stem: '这是题干',
            format: 'T/F',
            related: 'T',
          },
          {
            type: '判断题',
            stem: '这是题干',
            format: '是/否',
            related: '是',
          },
          {
            type: '评分题',
            stem: '这是题干',
            format: '百分制',
            related: '60',
          },
          {
            type: '评分题',
            stem: '这是题干',
            format: '十分制',
            related: '6',
          },
          {
            type: '评分题',
            stem: '这是题干',
            format: '五分制',
            related: '3',
          },
          {
            type: '简答题',
            stem: '这是题干',
            related: '100',
          },
          {
            type: '文件上传',
            stem: '这是题干',
            related: '5',
          },
          {
            type: '排序题',
            stem: '这是题干',
            options: ['选项1', '选项2', '选项3', '选项4'],
          },
          {
            type: '填空题',
            stem: '这是题干',
            options: ['选项1', '选项2', '选项3', '选项4'],
          },
        ],
      }
    };
  },
  methods: {
    load() {
      console.log("load");
      console.log(this.questionnaire);
    },
    changeSortType(type) {
      this.itemType = type;
    },
  },
}
</script>

<style scoped>

:deep(input) {
  border: none;
}

#main {
  display: flex;
  flex-direction: column;
  height: 100vh; /* 设置容器高度为视口高度 */
  background-color: #eeeeee;
}

#nav {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
}

#con {
  /* 顶栏高度 */
  margin: 5% auto auto;
  display: flex;
  justify-content: space-between;
  align-items: center;
  width: 65%;
  height: 88%;
  border: #207EFF solid 1px;
}

#type {
  overflow-y: auto;
  flex-basis: calc(30% - 5px); /* 分别占据50%宽度，减去5像素的间隔 */
  height: 100%;
  background-color: #FFFFFF;
  border: #207EFF solid 1px;
  margin-right: 20px; /* 添加右侧的10像素间隔 */
  display: block;
  text-align: center;
  padding-top: 20px;
}

#content {
  overflow-y: auto;
  flex-basis: calc(70% - 10px); /* 分别占据50%宽度，减去5像素的间隔 */
  height: 100%;
  background-color: #FFFFFF;
  display: block;
  border: #207EFF solid 1px;
}

#itemType {
  background-color: #eeeeee;
  margin-bottom: 20px;
}

.item-button {
  color: black;
}

#question-type {
  display: block;
}

.types {
  display: block;
  text-align: left;
  margin-left: 40px;
  margin-bottom: 20px;
}

hr {
  margin-bottom: 20px;
  background-color: #dddddd;
  color: #dddddd;
  border: none;
  height: 1px;
}

.type-button {
  color: white;
  margin-bottom: 20px;
}

.type-format {
  align-items: center;
}

span {
  font-size: 16px;
}

#top-span {
  padding-bottom: 15px;
}

.el-button {
  padding-top: 8px;
}

.questionnaire-name {
  text-align: center;
  margin: 40px 0 20px 0;
}

.name {
  font-size: 25px;
  font-weight: bold;
}

.questionnaire-description {
  font-size: 16px;
  margin-left: 40px;
  margin-bottom: 10px;
}

.ques-type {
  font-size: 20px;
  font-weight: bold;
  margin-left: 40px;
  margin-bottom: 20px;
}

.ques-stem {
  margin-left: 60px;
  margin-bottom: 20px;
}

.stem-input {
  width: 500px !important;
}

.ques-option {
  margin-left: 80px;
}

.ques-answer {
  margin-left: 80px;
  margin-right: 160px;
}

.small_btns {
  padding-top: 5px;
}

.ques-operation {
  margin-left: 80px;
  margin-top: 20px;
  margin-bottom: 20px;
}

.ques-save {
  text-align: center;
  margin-top: 40px;
  margin-bottom: 40px;
}

</style>

<style scoped>

.option-input {
  align-items: center;
}

.option-row {
  align-items: center;
  margin-bottom: 10px;
}

.el-checkbox {
  margin-right: 10px;
  white-space: nowrap;
}

.el-input {
  flex-grow: 1;
  width: auto;
}

</style>

