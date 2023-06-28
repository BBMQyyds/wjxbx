<template>
  <div id="main">
    <nav-bar id="nav"></nav-bar>
    <div id="con">
      <el-container id="content">
        <div class="questionnaire-name">
          <span class="name">{{ this.questionnaire.name }}</span>
        </div>
        <div class="questionnaire-description">
          <el-row>
            <el-col :span="12">
              <span>问卷描述：{{ this.questionnaire.description }}</span>
            </el-col>
            <el-col :span="4">
            </el-col>
            <el-col :span="8">
              <span>答卷人：{{ this.username }}</span>
            </el-col>
          </el-row>
        </div>
        <hr>
        <div v-for="(question,index) in this.RelatedQuestionList" :key="index">
          <div class="ques-type" style="align-items: center;">
            <span style="margin-right: 10px">{{ index + 1 }}.&emsp;{{ question.type }}</span>
          </div>
          <div class="ques-related">
            <span v-if="question.relatedFatherList!==null && question.relatedFatherList!==undefined
            && question.relatedFatherList.length!==0"
            style="color: #C0C0C0;font-size: 12px">
              父题目：{{ fatherListToString(question.relatedFatherList) }}
            </span>
          </div>
          <!--根据type生成不同的题型，并生成真实的题目格式-->
          <!--可编辑题干、选项等内容-->
          <!--单选（序号以ABCD形式展现，序号前有单选框）-->
          <div v-if="question.type === '单选题'">
            <div class="ques-stem">
              <span>{{ question.stem }}</span>
            </div>
            <div class="ques-option">
              <el-radio-group v-model="question.answer" :class="{ 'disabled': this.$route.query.detail === 'true' }"
                              @change="console.log(question)">
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
              <span>{{ question.stem }}</span>
            </div>
            <div class="ques-option">
              <!--el-checkbox和el-input在一行-->
              <el-checkbox-group v-model="question.answer" :class="{ 'disabled': this.$route.query.detail === 'true' }">
                <div v-for="(option, index) in question.options" :key="index"
                     class="option-input">
                  <!--序号abcd形式，序号前有多选框-->
                  <div class="option-row">
                    <el-checkbox :label="index">{{ String.fromCharCode(index + 65) }}.</el-checkbox>
                    <span>{{ question.options[index] }}</span>
                  </div>
                </div>
              </el-checkbox-group>
            </div>
          </div>
          <!--排序（无序号，语句后有上下移按钮）-->
          <div v-if="question.type === '排序题'">
            <div class="ques-stem">
              <span>{{ question.stem }}</span>
            </div>
            <div class="ques-option">
              <div v-for="(option, index) in question.options" :key="index"
                   class="option-input">
                <!--语句后有上下移按钮，但disabled-->
                <div class="option-row">
                  <span style="margin-right: 40px;">{{ question.options[index] }}</span>
                  <el-button class="small_btns" size="small" type="primary" @click="optionUp(question.options,index)"
                             :class="{ 'disabled': this.$route.query.detail === 'true' }">
                    上移
                  </el-button>
                  <el-button class="small_btns" size="small" type="primary"
                             @click="optionDown(question.options,index)"
                             :class="{ 'disabled': this.$route.query.detail === 'true' }">
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
              <span>{{ question.stem }}</span>
            </div>
            <div class="ques-option">
              <div v-if="question.format === '五分制'">
                <el-rate v-model="question.answer" :max="5"
                         :class="{ 'disabled': this.$route.query.detail === 'true' }"></el-rate>
              </div>
              <div v-if="question.format === '十分制'">
                <el-radio-group v-model="question.answer" :class="{ 'disabled': this.$route.query.detail === 'true' }">
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
                <el-input-number v-model="question.answer" :max="100"
                                 :class="{ 'disabled': this.$route.query.detail === 'true' }" :min="0" :step="1"
                                 label="分数"
                                 size="small" step-strictly>
                </el-input-number>
              </div>
            </div>
          </div>
          <!--填空（有多个空）-->
          <div v-if="question.type === '填空题'">
            <div class="ques-stem">
              <span>{{ replaceBlank(question.stem) }}</span>
            </div>
            <div class="ques-answer">
              <!--循环BlankCount(question.stem)次-->
              <div v-for="(answer, index) in question.answer" :key="index"
                   class="option-input">
                <div class="option-row">
                  <el-input v-model="question.answer[index]" :resize="'none'" :rows="1"
                            :class="{ 'disabled': this.$route.query.detail === 'true' }" placeholder="请输入答案"
                            type="textarea"></el-input>
                </div>
              </div>
            </div>
          </div>
          <!--简答-->
          <div v-if="question.type === '简答题'">
            <div class="ques-stem">
              <span>{{ question.stem }}</span>
            </div>
            <div class="ques-answer">
              <div style="margin-bottom: 10px;align-items: center;">
                <span style="font-size: 12px">字数限制：{{ question.related }}</span>
              </div>
              <el-input v-model="question.answer" :maxlength="question.related" :resize="'none'" :rows="3"
                        :class="{ 'disabled': this.$route.query.detail === 'true' }" placeholder="请输入答案"
                        type="textarea"></el-input>
            </div>
          </div>
          <!--判断题（生成不同的评分形式，yes/no,T/F,是/否）-->
          <div v-if="question.type === '判断题'">
            <div class="ques-stem">
              <span>{{ question.stem }}</span>
            </div>
            <div class="ques-option">
              <div v-if="question.format === 'yes/no'">
                <el-radio-group v-model="question.answer" :class="{ 'disabled': this.$route.query.detail === 'true' }">
                  <el-radio label="yes"></el-radio>
                  <el-radio label="no"></el-radio>
                </el-radio-group>
              </div>
              <div v-if="question.format === 'T/F'">
                <el-radio-group v-model="question.answer" :class="{ 'disabled': this.$route.query.detail === 'true' }">
                  <el-radio label="T"></el-radio>
                  <el-radio label="F"></el-radio>
                </el-radio-group>
              </div>
              <div v-if="question.format === '是/否'">
                <el-radio-group v-model="question.answer" :class="{ 'disabled': this.$route.query.detail === 'true' }">
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
          <el-button v-if="this.$route.query.preview !== 'true' && this.$route.query.detail !== 'true'"
                     size="default" type="primary" @click="submit">提&emsp;交
          </el-button>
          <el-button v-if="this.$route.query.preview === 'true'"
                     size="default" type="primary" @click="save">保&emsp;存
          </el-button>
        </div>
      </el-container>
    </div>
  </div>
</template>

<script>
import navBar from "../../components/nav";
import request, {plainRequest} from "@/api";
import router from "@/router";

export default {
  name: "answer",
  inject: ['reload'],
  components: {
    navBar
  },
  created() {
    plainRequest.post('selectQuestionnaireById', this.$route.query.id).then(res => {
      if (this.$route.query.preview !== 'true' && this.$route.query.detail !== 'true' && res.data.startTime === null) {
        this.$message({
          message: '该问卷尚未发布或已过期',
          type: 'warning'
        });
        router.push({path: '/login'});
      }
      this.questionnaire.name = res.data.questionnaireName;
      this.questionnaire.description = res.data.questionnaireDescription;
    });
    if (this.$route.query.detail !== 'true') {
      plainRequest.post('selectQuestionById', this.$route.query.id).then(res => {
        if (this.questionnaire.questions.length === 0) {
          this.questionnaire.questions = res.data;
          for (let i = 0; i < this.questionnaire.questions.length; i++) {
            if (this.questionnaire.questions[i].type === '填空题') {
              this.questionnaire.questions[i].answer = Array.from({length: this.blankCount(this.questionnaire.questions[i].stem)});
            } else if (this.questionnaire.questions[i].type === '简答题'
                || this.questionnaire.questions[i].type === '评分题' || this.questionnaire.questions[i].type === '判断题') {
              this.questionnaire.questions[i].answer = '';
            } else if (this.questionnaire.questions[i].type === '单选题') {
              this.questionnaire.questions[i].answer = -1;
            } else if (this.questionnaire.questions[i].type === '多选题') {
              this.questionnaire.questions[i].answer = [];
            }
          }
        }
      });
    } else {
      this.fetchData();
    }
    console.log(this.questionnaire);
  },
  data() {
    return {
      //题目结构
      //type: 题型
      //stem: 题干
      //options: 选项(单选题、多选题、判断题、排序题)
      //format: 格式(判断题、评分题)
      //related: 相关(判断题、填空题、简答题、评分题、文件上传)
      qnId: this.$route.query.id,
      username: this.$route.query.username,
      // 问卷信息
      questionnaire: {
        id: '',
        questionnaireId: this.$route.query.id,
        name: '',
        description: '',
        // 问卷中的题目，请举出具体例子
        questions: this.$route.query.questions === undefined ? [] : JSON.parse(this.$route.query.questions),
      },
      question: {
        id: '',
        type: '',
        stem: '',
        options: [],
        format: '',
        related: '',
        answer: '',
      },
      // 关联后的题目列表
    };
  },
  computed: {
    RelatedQuestionList: function () {
      let self = this; // 保存当前上下文的引用
      return this.questionnaire.questions.filter(function (question) {
        if (question.relatedFatherList && question.relatedFatherList.length > 0) {
          console.log('包含relatedFatherList');
          for (let i = 0; i < question.relatedFatherList.length; i++) {
            let relatedFather = self.questionnaire.questions.find(function (item) {
              return item.questionId === question.relatedFatherList[i];
            });
            if (relatedFather && relatedFather.relatedSonList) {
              for (let j = 0; j < relatedFather.relatedSonList.length; j++) {
                let relatedSon = relatedFather.relatedSonList[j];
                if (relatedSon.id === question.questionId) {
                  if (relatedFather.answer === relatedSon.relatedContent) {
                    console.log('relatedFather的answer等于relatedSon的relatedContent');
                    return true;
                  }
                }
              }
            }
          }
          return false;
        } else {
          console.log('没有relatedFatherList');
          return true;
        }
      });
    }

  },
  methods: {
    async fetchData() {
      try {
        const answerResponse = await plainRequest.post('selectAnswerById', this.$route.query.id);
        let data = JSON.parse(answerResponse.data.questionnaireContent);
        for (let i = 0; i < data.length; i++) {
          if (data[i].answer !== null) {
            data[i].answer = JSON.parse(data[i].answer);
          }
        }
        this.questionnaire.id = answerResponse.data.questionnaireId;
        this.questionnaire.questions = data;

        for (let i = 0; i < this.questionnaire.questions.length; i++) {
          if (this.questionnaire.questions[i].relatedSonList) {
            let list = JSON.parse(JSON.stringify(this.questionnaire.questions[i].relatedSonList));
            this.questionnaire.questions[i].relatedSonList = [];
            for (let j = 0; j < list.length; j++) {
              this.questionnaire.questions[i].relatedSonList.push(JSON.parse(list[j]));
            }
          }
        }

        const questionnaireResponse = await plainRequest.post('selectQuestionnaireById', this.questionnaire.id);
        this.questionnaire.name = questionnaireResponse.data.questionnaireName;
        this.questionnaire.description = questionnaireResponse.data.questionnaireDescription;

        console.log(this.questionnaire);
      } catch (error) {
        // 错误处理
        console.error(error);
      }
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
      let data = {
        questionnaireId: this.questionnaire.questionnaireId,
        userId: sessionStorage.getItem('token') !== null ?
            sessionStorage.getItem('token') : '************************************',
        questions: JSON.parse(JSON.stringify(this.questionnaire.questions)),
      }
      for (let i = 0; i < this.questionnaire.questions.length; i++) {
        data.questions[i].answer = JSON.stringify(data.questions[i].answer);
      }

      console.log(this.questionnaire);
      request.post('insertAnswer', data).then(res => {
        console.log(res);
        if (res.data === 1) {
          this.$message({
            message: '提交成功',
            type: 'success'
          });
          if (sessionStorage.getItem('token') !== null) {
            router.push({
              path: '/project',
              query: {
                id: sessionStorage.getItem('token'),
              }
            })
          } else {
            router.push({path: '/login'});
          }
        } else {
          this.$message({
            message: '提交失败',
            type: 'error'
          });
        }
      });
    },
    //生成一个新字符串，将&______&替换为______
    replaceBlank(str) {
      return str.replace(/&______&/g, '______');
    },
    blankCount(str) {
      return str.split('&______&').length - 1;
    },
    async save() {
      try {
        await this.$confirm('确认保存问卷吗？', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          customClass: 'message-box',
          type: 'warning',
        });
        let data = {
          id: this.qnId,
          questions: this.questionnaire.questions,
        };
        console.log(data);
        for (let i = 0; i < data.questions.length; i++) {
          if (data.questions[i].relatedSonList) {
            let list = JSON.parse(JSON.stringify(data.questions[i].relatedSonList));
            data.questions[i].relatedSonList = [];
            for (let j = 0; j < list.length; j++) {
              data.questions[i].relatedSonList.push(JSON.stringify(list[j]));
            }
          }
        }
        const res = await request.post('/insertDesignQuestion', JSON.stringify(data));
        if (res.data === 1) {
          this.$message({
            message: '保存成功',
            type: 'success',
          });
          router.back();
        } else {
          this.$message({
            message: '保存失败',
            type: 'error',
          });
        }
      } catch (error) {
        this.$message({
          type: 'info',
          message: '已取消保存',
        });
      }
    },
    fatherListToString(array) {
      if (array === null || array === undefined) {
        return null;
      }
      let resArray = [];
      for (let i = 0; i < array.length; i++) {
        for (let j = 0; j < this.questionnaire.questions.length; j++) {
          if (array[i] === this.questionnaire.questions[j].questionId) {
            resArray.push('第' + (j + 1) + '题');
            break;
          }
        }
      }
      return resArray;
    }
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
  margin-bottom: 5px;
}

.ques-related {
  font-size: 12px;
  font-weight: normal;
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

.disabled {
  opacity: 0.8; /* 设置透明度为 0.5 或其他你想要的值 */
  pointer-events: none; /* 禁用鼠标事件 */
}


</style>

<style lang="scss">

.message-box {
  .el-button {
    padding-top: 8px;
  }
}

</style>
