<template>
  <div id="main">
    <nav-bar id="nav"></nav-bar>
    <div id="con">
      <el-container id="content">
        <div class="questionnaire-name">
          <span class="name">{{ this.questionnaire.name }}</span>
        </div>
        <div class="questionnaire-description">
          <span>问卷描述：{{ this.questionnaire.description }}</span>
        </div>
        <hr>
        <div v-for="(question,index) in this.questionnaire.questions" :key="index">
          <div class="ques-type" style="align-items: center;">
            <span style="margin-right: 10px">{{ index + 1 }}.&emsp;{{ question.type }}</span>
          </div>
          <!--根据type生成不同的题型，并生成真实的题目格式-->
          <!--可编辑题干、选项等内容-->
          <!--单选（序号以ABCD形式展现，序号前有单选框）-->
          <div v-if="question.type === '单选题'">
            <div class="ques-stem">
              <span>{{ question.stem }}</span>
            </div>
            <div class="ques-option">
              <el-radio-group v-model="question.answer">
                <div v-for="(option, index) in question.options" :key="index"
                     class="option-input">
                  <!--序号abcd形式，序号前有单选框-->
                  <div class="option-row">
                    <el-radio :label="index">{{ String.fromCharCode(index + 65) }}.</el-radio>
                    <span>{{ question.options[index] }}</span>
                  </div>
                </div>
              </el-radio-group>
            </div>
          </div>
          <!--多选（序号以ABCD形式展现，序号前有多选框）-->
          <div v-if="question.type === '多选题'">
            <div class="ques-stem">
              <el-form-item prop="stem">
                <el-input type="textarea" :resize="'none'" :rows="1" style="width: 75%;" autosize
                          v-model="question.stem" placeholder="请输入题干"></el-input>
              </el-form-item>
            </div>
            <div class="ques-option">
              <!--el-checkbox和el-input在一行-->
              <div v-for="(option, index) in question.options" :key="index"
                   class="option-input">
                <!--序号abcd形式，序号前有多选框，但disabled-->
                <div class="option-row">
                  <el-checkbox disabled>{{ String.fromCharCode(index + 65) }}.</el-checkbox>
                  <el-input v-model="question.options[index]" placeholder="请输入选项"></el-input>
                </div>
              </div>
            </div>
          </div>
          <!--排序（无序号，语句后有上下移按钮）-->
          <div v-if="question.type === '排序题'">
            <div class="ques-stem">
              <el-form-item prop="stem">
                <el-input type="textarea" :resize="'none'" :rows="1" style="width: 75%;" autosize
                          v-model="question.stem" placeholder="请输入题干"></el-input>
              </el-form-item>
            </div>
            <div class="ques-option">
              <div v-for="(option, index) in question.options" :key="index"
                   class="option-input">
                <!--语句后有上下移按钮，但disabled-->
                <div class="option-row">
                  <el-input v-model="question.options[index]" placeholder="请输入选项"
                            style="margin-right: 40px;"></el-input>
                  <el-button class="small_btns" type="primary" size="small" @click="optionUp(question.options,index)">
                    上移
                  </el-button>
                  <el-button class="small_btns" type="primary" size="small"
                             @click="optionDown(question.options,index)">
                    下移
                  </el-button>
                </div>
              </div>
            </div>
          </div>
          <!--评分（根据评分格式生成不同的评分形式）-->
          <!--评分格式：五分制(el-rate)、十分制(el-radio)、百分制(el-input-number)-->
          <div v-if="question.type === '评分题'">
            <div class="ques-stem">
              <el-form-item prop="stem">
                <el-input type="textarea" :resize="'none'" :rows="1" style="width: 75%;" autosize
                          v-model="question.stem" placeholder="请输入题干"></el-input>
              </el-form-item>
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
              <el-form-item prop="stem">
                <el-input type="textarea" :resize="'none'" :rows="1" style="width: 75%;"
                          v-model="question.stem" placeholder="请输入题干" autosize></el-input>
              </el-form-item>
            </div>
          </div>
          <!--简答-->
          <div v-if="question.type === '简答题'">
            <div class="ques-stem">
              <el-form-item prop="stem">
                <el-input type="textarea" :resize="'none'" :rows="1" style="width: 75%;" autosize
                          v-model="question.stem" placeholder="请输入题干"></el-input>
              </el-form-item>
            </div>
            <div class="ques-answer">
              <div style="margin-bottom: 10px;align-items: center;">
                <span>字数限制：{{ question.related }}</span>
              </div>
              <el-input disabled type="textarea" placeholder="请输入答案"
                        :rows="3" :resize="'none'"></el-input>
            </div>
          </div>
          <!--文件上传-->
          <div v-if="question.type === '文件上传'">
            <div class="ques-stem">
              <el-form-item prop="stem">
                <el-input type="textarea" :resize="'none'" :rows="1" style="width: 75%;" autosize
                          v-model="question.stem" placeholder="请输入题干"></el-input>
              </el-form-item>
            </div>
            <div class="ques-answer">
              <div style="margin-bottom: 10px;align-items: center;">
                <span>大小限制（MB）：{{ question.related }}</span>
              </div>
              <el-upload disabled>
                <el-button size="default" type="primary">点击上传</el-button>
              </el-upload>
            </div>
          </div>
          <!--判断题（生成不同的评分形式，yes/no,T/F,是/否）-->
          <div v-if="question.type === '判断题'">
            <div class="ques-stem">
              <el-form-item prop="stem">
                <el-input type="textarea" :resize="'none'" :rows="1" style="width: 75%;" autosize
                          v-model="question.stem" placeholder="请输入题干"></el-input>
              </el-form-item>
            </div>
            <div class="ques-option">
              <div v-if="question.format === 'yes/no'">
                <el-radio-group v-model="question.related" disabled>
                  <el-radio label="yes"></el-radio>
                  <el-radio label="no"></el-radio>
                </el-radio-group>
              </div>
              <div v-if="question.format === 'T/F'">
                <el-radio-group v-model="question.related" disabled>
                  <el-radio label="T"></el-radio>
                  <el-radio label="F"></el-radio>
                </el-radio-group>
              </div>
              <div v-if="question.format === '是/否'">
                <el-radio-group v-model="question.related" disabled>
                  <el-radio label="是"></el-radio>
                  <el-radio label="否"></el-radio>
                </el-radio-group>
              </div>
            </div>
          </div>
          <hr>
        </div>
        <!--保存、退出按钮-->
        <div class="ques-save">
          <el-button type="primary" size="default" @click="submit">提&emsp;交</el-button>
        </div>
      </el-container>
    </div>
  </div>
</template>

<script>
import navBar from "../../components/nav";

export default {
  name: "answer",
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
      //题目结构
      //type: 题型
      //stem: 题干
      //options: 选项(单选题、多选题、判断题、排序题)
      //format: 格式(判断题、评分题)
      //related: 相关(判断题、填空题、简答题、评分题、文件上传)
      // 问卷信息
      questionnaire: {
        id: this.$route.query.questionnaire_id,
        name: 'this.$route.query.name',
        description: 'this.$route.query.description',
        // 问卷中的题目，请举出具体例子
        questions: [
          {
            type: '单选题',
            stem: '单选题题干',
            options: ['选项1', '选项2', '选项3', '选项4'],
          },
          {
            type: '多选题',
            stem: '多选题题干',
            options: ['选项1', '选项2', '选项3', '选项4'],
          },
          {
            type: '判断题',
            stem: '判断题题干',
            format: '是/否',
          },
          {
            type: '填空题',
            stem: '填空题题干',
            related: '4',
          },
          {
            type: '简答题',
            stem: '简答题题干',
            related: '100',
          },
          {
            type: '评分题',
            stem: '评分题题干',
            format: '百分制',
            related: '60',
          },
          {
            type: '排序题',
            stem: '排序题题干',
            options: ['选项1', '选项2', '选项3', '选项4'],
          },
          {
            type: '文件上传',
            stem: '文件上传题干',
            related: '2',
          },
        ],
      },
    };
  },
  methods: {
    load() {
      console.log("load");
    },
    optionUp(options, index) {
      if (index > 0) {
        let temp = options[index];
        options.splice(index, 1);
        options.splice(index - 1, 0, temp);
      }
    },
    optionDown(options, index) {
      if (index < options.length - 1) {
        let temp = options[index];
        options.splice(index, 1);
        options.splice(index + 1, 0, temp);
      }
    },
    submit() {
      console.log("submit");
      this.reload();
    },
  }
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
  width: 40%;
  height: 88%;
}

#content {
  overflow-y: auto;
  flex-basis: calc(70% - 10px); /* 分别占据50%宽度，减去5像素的间隔 */
  height: 100%;
  background-color: #FFFFFF;
  display: block;
}

hr {
  margin-top: 20px;
  margin-bottom: 20px;
  background-color: #dddddd;
  color: #dddddd;
  border: none;
  height: 1px;
}

span {
  font-size: 16px;
}

.el-button {
  padding-top: 8px;
}

.questionnaire-name {
  text-align: center;
  margin: 30px 0 20px 0;
}

.name {
  font-size: 25px;
  font-weight: bold;
}

.questionnaire-description {
  font-size: 16px;
  margin-left: 40px;
  margin-bottom: 20px;
}

.ques-type {
  font-size: 20px;
  font-weight: bold;
  margin-left: 40px;
  margin-bottom: 20px;
}

.ques-stem {
  margin-left: 80px;
  margin-bottom: 20px;
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

<style lang="scss">

.message-box {
  .el-button {
    padding-top: 8px;
  }
}

</style>
