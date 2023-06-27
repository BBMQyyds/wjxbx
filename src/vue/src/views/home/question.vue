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
            <el-button class="type-button" type="primary" title="不少于2个选项，不多于6个选项"
                       @click="addSimple(this.questionnaire.questions.length-1,'down')">单选题
            </el-button>
            <div class="type-format">
              <span>请输入选项数：</span>
              <el-input-number size="small" step-strictly
                               v-model="radioOptionCount" :min="2" :max="6" :step="1" label="选项数">
              </el-input-number>
            </div>
          </div>
          <hr>
          <div class="types">
            <el-button class="type-button" type="primary" title="不少于2个选项，不多于6个选项"
                       @click="addMultiple(this.questionnaire.questions.length-1,'down')">多选题
            </el-button>
            <div class="type-format">
              <span>请输入选项数：</span>
              <el-input-number size="small" step-strictly
                               v-model="choiceOptionCount" :min="2" :max="6" :step="1" label="选项数">
              </el-input-number>
            </div>
          </div>
          <hr>
          <div class="types">
            <el-button class="type-button" type="primary" title="不少于1个空格，不多于4个空格"
                       @click="addBlank(this.questionnaire.questions.length-1,'down')">填空题
            </el-button>
            <div class="type-format">
              <span>请输入空格数：</span>
              <el-input-number size="small" step-strictly
                               v-model="blankCount" :min="1" :max="4" :step="1" label="空格数">
              </el-input-number>
            </div>
          </div>
          <hr>
          <div class="types">
            <el-button class="type-button" type="primary" title="不少于2个语句，不多于6个语句"
                       @click="addSort(this.questionnaire.questions.length-1,'down')">排序题
            </el-button>
            <div class="type-format">
              <span>请选择语句数：</span>
              <el-input-number size="small" step-strictly
                               v-model="sortCount" :min="2" :max="6" :step="1" label="语句数">
              </el-input-number>
            </div>
          </div>
          <hr>
          <div class="types">
            <el-button class="type-button" type="primary" title="分为是/否,T/F,yes/no三种格式"
                       @click="addJudge(this.questionnaire.questions.length-1,'down')">判断题
            </el-button>
            <!--选择格式：是/否,T/F,yes/no-->
            <div class="type-format">
              <div id="top-span">
                <span>请选择判断格式：</span>
              </div>
              <el-radio-group v-model="judgeFormat">
                <el-radio label="是/否"></el-radio>
                <el-radio label="T/F"></el-radio>
                <el-radio label="yes/no"></el-radio>
              </el-radio-group>
            </div>
          </div>
          <hr>
          <div class="types">
            <el-button class="type-button" type="primary" title="分为百分制，十分制，五分制三种格式"
                       @click="addGrade(this.questionnaire.questions.length-1,'down')">评分题
            </el-button>
            <div class="type-format">
              <div id="top-span">
                <span>请选择评分格式：</span>
              </div>
              <el-radio-group v-model="gradeFormat">
                <el-radio label="百分制"></el-radio>
                <el-radio label="十分制"></el-radio>
                <el-radio label="五分制"></el-radio>
              </el-radio-group>
            </div>
          </div>
          <hr>
          <div class="types">
            <el-button class="type-button" type="primary" title="不少于50字，不多于500字"
                       @click="addSimpleAnswer(this.questionnaire.questions.length-1,'down')">简答题
            </el-button>
            <div class="type-format">
              <span>请输入字数限制：</span>
              <el-input-number size="small" step-strictly
                               v-model="answerSize" :min="50" :max="500" :step="50" label="字数限制">
              </el-input-number>
            </div>
          </div>
          <hr>
          <!--          <div class="types">-->
          <!--            <el-button class="type-button" type="primary" title="不少于2MB，不多于5MB"-->
          <!--                       @click="addFile(this.questionnaire.questions.length-1,'down')">文件题-->
          <!--            </el-button>-->
          <!--            <div class="type-format">-->
          <!--              <div id="top-span">-->
          <!--                <span>请输入大小限制（MB）：</span>-->
          <!--              </div>-->
          <!--              <el-input-number size="small" step-strictly-->
          <!--                               v-model="fileSize" :min="2" :max="5" :step="1" label="大小限制">-->
          <!--              </el-input-number>-->
          <!--            </div>-->
          <!--          </div>-->
        </el-container>
        <el-container id="question-type" v-if="itemType === 'bank'">
          <hr>
          <div id="bank-private" style="text-align: center;">
            <span style="font-size: 20px;font-weight: bold;margin-bottom: 20px;">个人题库</span>
            <!--下面列出privateBank中的问题类型和问题题干，点击即可添加至问卷-->
            <div style="text-align: left">
              <el-row v-for="(question,index) in this.privateBank" :key="index"
                      style="margin-top: 10px;margin-bottom: 10px">
                <el-col :span="3"></el-col>
                <el-col :span="16">
                  <el-button type="text" @click="addToQuestionnaire(this.privateBank,index)"
                             :title="question.stem">
                    {{ question.type }}&ensp;
                    {{ question.stem.length > 15 ? question.stem.substring(0, 15) + '...' : question.stem }}
                  </el-button>
                </el-col>
                <el-col :span="3">
                  <el-button type="text" @click="deleteFromPrivateBank(index)">
                    <i class="el-icon-delete"></i>
                  </el-button>
                </el-col>
                <el-col :span="2"></el-col>
              </el-row>
            </div>
          </div>
          <hr>
          <div id="bank-public" style="text-align: center;">
            <span style="font-size: 20px;font-weight: bold;margin-bottom: 20px;">公共题库</span>
            <!--下面列出publicBank中的问题类型和问题题干，点击即可添加至问卷-->
            <div style="text-align: left">
              <el-row v-for="(question,index) in this.publicBank" :key="index"
                      style="margin-top: 10px;margin-bottom: 10px">
                <el-col :span="3"></el-col>
                <el-col :span="16">
                  <el-button type="text" @click="addToQuestionnaire(this.publicBank,index)"
                             :title="question.stem">
                    {{ question.type }}&ensp;
                    {{ question.stem.length > 20 ? question.stem.substring(0, 20) + '...' : question.stem }}
                  </el-button>
                </el-col>
                <el-col :span="5"></el-col>
              </el-row>
            </div>
          </div>
        </el-container>
      </el-container>
      <el-container id="content">
        <div class="questionnaire-name">
          <span class="name">{{ this.questionnaire.name }}</span>
        </div>
        <div class="questionnaire-description">
          <span>问卷描述：{{ this.questionnaire.description }}</span>
        </div>
        <hr>
        <div v-for="(question,index) in this.questionnaire.questions" :key="index">
          <el-form :model="question" :rules="rules">
            <div class="ques-type" style="align-items: center;">
              <span style="margin-right: 10px">{{ index + 1 }}.&emsp;{{ question.type }}</span>
              <el-icon v-if="question.star!==0" class="el-icon-star-on"
                       @click="starOff(question)" style=" color: #207EFF;"></el-icon>
              <el-icon v-if="question.star===0" class="el-icon-star-on"
                       @click="starOn(question)" style="color: #C0C0C0;"></el-icon>
            </div>
            <!--根据type生成不同的题型，并生成真实的题目格式-->
            <!--可编辑题干、选项等内容-->
            <!--单选（序号以ABCD形式展现，序号前有单选框）-->
            <div v-if="question.type === '单选题'">
              <div class="ques-stem">
                <el-form-item prop="stem">
                  <el-input type="textarea" :resize="'none'" :rows="1" style="width: 75%;" autosize
                            v-model="question.stem" placeholder="请输入题干"></el-input>
                </el-form-item>
              </div>
              <div class="ques-option">
                <el-radio-group>
                  <div v-for="(option, index) in question.options" :key="index"
                       class="option-input">
                    <!--序号abcd形式，序号前有单选框-->
                    <div class="option-row">
                      <el-radio :label="index" disabled>{{ String.fromCharCode(index + 65) }}.</el-radio>
                      <el-input v-model="question.options[index]" placeholder="请输入选项"></el-input>
                    </div>
                  </div>
                </el-radio-group>
                <div class="ques-addition">
                  <el-button class="small_btns" :disabled="question.options.length === radioMax"
                             type="success" size="small" @click="plus(question.options,radioMax)">加
                  </el-button>
                  <el-button class="small_btns" :disabled="question.options.length === radioMin"
                             type="warning" size="small" @click="sub(question.options,radioMin)">减
                  </el-button>
                </div>
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
                <div class="ques-addition">
                  <el-button class="small_btns" :disabled="question.options.length === choiceMax"
                             type="success" size="small" @click="plus(question.options,choiceMax)">加
                  </el-button>
                  <el-button class="small_btns" :disabled="question.options.length === choiceMin"
                             type="warning" size="small" @click="sub(question.options,choiceMin)">减
                  </el-button>
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
                <div class="ques-addition">
                  <el-button class="small_btns" :disabled="question.options.length === sortMax"
                             type="success" size="small" @click="plus(question.options,sortMax)">加
                  </el-button>
                  <el-button class="small_btns" :disabled="question.options.length === sortMin"
                             type="warning" size="small" @click="sub(question.options,sortMin)">减
                  </el-button>
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
              <!--添加空格按钮-->
              <div class="ques-operation">
                <el-button :disabled="(question.stem.split(' &______& ').length - 1) >= 4"
                           style="margin-right: 20px"
                           class="small_btns" type="success" size="small" @click="addSpace(question)">添加空格符
                </el-button>
                <span style="color: #C0C0C0">&______&为填空符，不少于1个，不多于4个</span>
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
                  <span>字数限制：</span>
                  <el-input-number size="small" step-strictly
                                   v-model="question.related" :min="50" :max="500" :step="50" label="字数">
                  </el-input-number>
                </div>
                <el-input disabled type="textarea" placeholder="请输入答案"
                          :rows="3" :resize="'none'"></el-input>
              </div>
            </div>
            <!--            &lt;!&ndash;文件上传&ndash;&gt;-->
            <!--            <div v-if="question.type === '文件上传'">-->
            <!--              <div class="ques-stem">-->
            <!--                <el-form-item prop="stem">-->
            <!--                  <el-input type="textarea" :resize="'none'" :rows="1" style="width: 75%;" autosize-->
            <!--                            v-model="question.stem" placeholder="请输入题干"></el-input>-->
            <!--                </el-form-item>-->
            <!--              </div>-->
            <!--              <div class="ques-answer">-->
            <!--                <div style="margin-bottom: 10px;align-items: center;">-->
            <!--                  <span>大小限制（MB）：</span>-->
            <!--                  <el-input-number size="small" step-strictly-->
            <!--                                   v-model="question.related" :min="2" :max="5" :step="1" label="大小限制">-->
            <!--                  </el-input-number>-->
            <!--                </div>-->
            <!--                <el-upload disabled>-->
            <!--                  <el-button size="default" type="primary">点击上传</el-button>-->
            <!--                </el-upload>-->
            <!--              </div>-->
            <!--            </div>-->
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
            <!--置顶、上移、下移、置底-->
            <div class="ques-operation">
              <el-button class="small_btns" type="info" size="small" @click="toTop(index)">置顶</el-button>
              <el-button class="small_btns" type="info" size="small" @click="toUp(index)">上移</el-button>
              <el-button class="small_btns" type="info" size="small" @click="toDown(index)">下移</el-button>
              <el-button class="small_btns" type="info" size="small" @click="toBottom(index)">置底</el-button>
            </div>
            <div class="ques-operation">
              <el-button class="small_btns" type="primary" size="small" @click="insertUp(index)">上方插入</el-button>
              <el-button class="small_btns" type="primary" size="small" @click="insertDown(index)">下方插入</el-button>
              <el-button class="small_btns" type="danger" size="small" @click="deleteQuestion(index)">删除</el-button>
            </div>
            <hr>
          </el-form>
        </div>
        <!--保存、退出按钮-->
        <div class="ques-save">
          <el-button type="primary" size="default" @click="save" style="margin-right: 40px;">保&emsp;存</el-button>
          <el-button type="success" size="default" @click="preview" style="margin-right: 40px;">预&emsp;览</el-button>
          <el-button type="danger" size="default" @click="exit">退&emsp;出</el-button>
        </div>
      </el-container>
    </div>
    <!--选择题型弹窗,从七种题型中选一种进行插入-->
    <!--questionTypes: ['单选题', '多选题', '判断题', '填空题', '简答题', '评分题', '排序题', '文件上传'],-->
    <el-dialog title="选择题型" v-model="dialogVisible" :before-close="handleClose">
      <div style="text-align: center;margin-bottom: 40px">
        <el-radio-group v-model="questionType">
          <el-radio-button label="单选题">单选题</el-radio-button>
          <el-radio-button label="多选题">多选题</el-radio-button>
          <el-radio-button label="判断题">判断题</el-radio-button>
          <el-radio-button label="填空题">填空题</el-radio-button>
          <el-radio-button label="简答题">简答题</el-radio-button>
          <el-radio-button label="评分题">评分题</el-radio-button>
          <el-radio-button label="排序题">排序题</el-radio-button>
          <!--          <el-radio-button label="文件上传">文件上传</el-radio-button>-->
        </el-radio-group>
      </div>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false" style="margin-right: 40px;">取 消</el-button>
        <el-button type="primary" @click="insert">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
import navBar from "../../components/nav";
import router from "@/router";
import request, {plainRequest} from "@/api";
import {v4 as uuidv4} from 'uuid';


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
      dialogVisible: false,
      itemType: 'type',//type, bank
      radioOptionCount: 4,//单选题选项数
      radioMin: 2,
      radioMax: 6,
      choiceOptionCount: 4,//多选题选项数
      choiceMin: 2,
      choiceMax: 6,
      judgeFormat: '是/否',//判断题格式
      blankCount: 1,//填空题空格数
      blankMin: 1,
      blankMax: 10,
      answerSize: '50',//简答题字数限制
      gradeFormat: '百分制',//评分题格式
      sortCount: 4,//排序题语句数
      sortMin: 2,
      sortMax: 6,
      fileSize: '2',//文件上传大小限制(MB)

      //题目结构
      //type: 题型
      //stem: 题干
      //options: 选项(单选题、多选题、判断题、排序题)
      //format: 格式(判断题、评分题)
      //related: 相关(判断题、填空题、简答题、评分题、文件上传)

      // 题库信息（即一个特殊的问卷）
      //个人题库
      privateBank: [],
      //公共题库
      publicBank: [],
      // 问卷信息
      questionnaire: {
        id: this.$route.query.questionnaire_id,
        name: this.$route.query.name,
        description: this.$route.query.description,
        // 问卷中的题目，请举出具体例子
        questions: [],
      },
      questionType: '单选题',
      insertIndex: 0,
      insertType: 'down',
      rules: {
        stem: [
          {required: true, message: '请输入题干', trigger: 'blur'},
          {min: 2, max: 100, message: '长度在 2 到 100 个字符', trigger: 'blur'},
        ],
      },
    };
  },
  methods: {
    load() {
      console.log("load");
      plainRequest.post('/selectQuestionById', this.questionnaire.id).then(res => {
        console.log(res);
        this.questionnaire.questions = res.data;
      }).catch(err => {
        console.log(err);
      });
      plainRequest.post('/selectPrivateQuestion', this.$route.query.user_id).then(res => {
        console.log(res);
        this.privateBank = res.data;
      }).catch(err => {
        console.log(err);
      });
      plainRequest.post('/selectPrivateQuestion','************************************').then(res => {
        console.log(res);
        this.publicBank = res.data;
      }).catch(err => {
        console.log(err);
      });
    },
    addToQuestionnaire(questions, index) {
      this.questionnaire.questions.push(questions[index]);
    },
    deleteFromPrivateBank(index) {
      plainRequest.post('/deletePrivateQuestionById', this.privateBank[index].questionId).then(res => {
        if (res.data === 1) {
          this.$message({
            message: '删除成功',
            type: 'success'
          });
          for (let i = 0; i < this.questionnaire.questions.length; i++) {
            if (this.questionnaire.questions[i].questionId === this.privateBank[index].questionId) {
              console.log(this.questionnaire.questions[i]);
              this.questionnaire.questions[i].star = 0;
              break;
            }
          }
          this.privateBank.splice(index, 1);
        } else {
          this.$message({
            message: '删除失败',
            type: 'error'
          });
        }
      }).catch(err => {
        console.log(err);
      });
    },
    starOn(question) {
      request.post('/insertPrivateQuestion', JSON.stringify({
        userId: this.$route.query.user_id,
        questionId: question.questionId,
      })).then(res => {
        if (res.data === 1) {
          question.star = 1;
          this.privateBank.push(question);
          this.$message({
            message: '收藏成功',
            type: 'success'
          });
        } else {
          this.$message({
            message: '收藏失败',
            type: 'error'
          });
        }
      }).catch(err => {
        console.log(err);
      });
    },
    starOff(question) {
      plainRequest.post('/deletePrivateQuestionById', question.questionId).then(res => {
        if (res.data === 1) {
          this.$message({
            message: '取消收藏成功',
            type: 'success'
          });
          question.star = 0;
          this.privateBank.splice(this.privateBank.indexOf(question), 1);
        } else {
          this.$message({
            message: '取消收藏失败',
            type: 'error'
          });
        }
      }).catch(err => {
        console.log(err);
      });
    },
    changeSortType(type) {
      this.itemType = type;
    },
    handleClose() {
      this.dialogVisible = false;
    },
    addSpace(question) {
      let count = question.stem.split(' &______& ').length - 1;
      question.stem += ' &______& ';
      question.related = count + 1;
    },
    // index为题目在数组中的下标
    // type为上面插入或下面插入（up,down）
    addSimple(index, type) {
      let question = {
        type: '单选题',
        stem: '这是题干',
        star: 0,
        questionId: uuidv4(),
        options: [],
      };
      for (let i = 0; i < this.radioOptionCount; i++) {
        question.options.push('选项' + (i + 1));
      }
      if (type === 'up') {
        this.questionnaire.questions.splice(index, 0, question);
      } else {
        this.questionnaire.questions.splice(index + 1, 0, question);
      }
    },
    addMultiple(index, type) {
      let question = {
        type: '多选题',
        stem: '这是题干',
        star: 0,
        questionId: uuidv4(),
        options: [],
      };
      for (let i = 0; i < this.choiceOptionCount; i++) {
        question.options.push('选项' + (i + 1));
      }
      if (type === 'up') {
        this.questionnaire.questions.splice(index, 0, question);
      } else {
        this.questionnaire.questions.splice(index + 1, 0, question);
      }
    },
    addJudge(index, type) {
      let question = {
        type: '判断题',
        stem: '这是题干',
        star: 0,
        questionId: uuidv4(),
        format: this.judgeFormat,
        related: this.judgeFormat.split('/')[0],
      };
      if (type === 'up') {
        this.questionnaire.questions.splice(index, 0, question);
      } else {
        this.questionnaire.questions.splice(index + 1, 0, question);
      }
    },
    addGrade(index, type) {
      let question = {
        type: '评分题',
        stem: '这是题干',
        star: 0,
        questionId: uuidv4(),
        format: this.gradeFormat,
        related: this.gradeFormat === '百分制' ? '60' : (this.gradeFormat === '十分制' ? '6' : '3'),
      };
      if (type === 'up') {
        this.questionnaire.questions.splice(index, 0, question);
      } else {
        this.questionnaire.questions.splice(index + 1, 0, question);
      }
    },
    addSimpleAnswer(index, type) {
      let question = {
        type: '简答题',
        stem: '这是题干',
        star: 0,
        questionId: uuidv4(),
        related: this.answerSize,
      };
      if (type === 'up') {
        this.questionnaire.questions.splice(index, 0, question);
      } else {
        this.questionnaire.questions.splice(index + 1, 0, question);
      }
    },
    // addFile(index, type) {
    //   let question = {
    //     type: '文件上传',
    //     stem: '这是题干',
    //     star: 0,
    //     questionId: uuidv4(),
    //     related: this.fileSize,
    //   };
    //   if (type === 'up') {
    //     this.questionnaire.questions.splice(index, 0, question);
    //   } else {
    //     this.questionnaire.questions.splice(index + 1, 0, question);
    //   }
    // },
    addSort(index, type) {
      let question = {
        type: '排序题',
        stem: '这是题干',
        star: 0,
        questionId: uuidv4(),
        options: [],
      };
      for (let i = 0; i < this.sortCount; i++) {
        question.options.push('语句' + (i + 1));
      }
      if (type === 'up') {
        this.questionnaire.questions.splice(index, 0, question);
      } else {
        this.questionnaire.questions.splice(index + 1, 0, question);
      }
    },
    addBlank(index, type) {
      let question = {
        type: '填空题',
        stem: '这是题干',
        star: 0,
        questionId: uuidv4(),
        related: this.blankCount,
      };
      for (let i = 0; i < this.blankCount; i++) {
        question.stem = question.stem + ' &______& ' + '这是题干';
      }
      if (type === 'up') {
        this.questionnaire.questions.splice(index, 0, question);
      } else {
        this.questionnaire.questions.splice(index + 1, 0, question);
      }
    },
    toTop(index) {
      let temp = this.questionnaire.questions[index];
      this.questionnaire.questions.splice(index, 1);
      this.questionnaire.questions.unshift(temp);
    },
    toBottom(index) {
      let temp = this.questionnaire.questions[index];
      this.questionnaire.questions.splice(index, 1);
      this.questionnaire.questions.push(temp);
    },
    toUp(index) {
      if (index > 0) {
        let temp = this.questionnaire.questions[index];
        this.questionnaire.questions.splice(index, 1);
        this.questionnaire.questions.splice(index - 1, 0, temp);
      }
    },
    toDown(index) {
      if (index < this.questionnaire.questions.length - 1) {
        let temp = this.questionnaire.questions[index];
        this.questionnaire.questions.splice(index, 1);
        this.questionnaire.questions.splice(index + 1, 0, temp);
      }
    },
    insertUp(index) {
      this.insertIndex = index;
      this.insertType = 'up';
      this.dialogVisible = true;
    },
    insertDown(index) {
      this.insertIndex = index;
      this.insertType = 'down';
      this.dialogVisible = true;
    },
    insert() {
      if (this.questionType === '单选题') {
        this.addSimple(this.insertIndex, this.insertType);
      } else if (this.questionType === '多选题') {
        this.addMultiple(this.insertIndex, this.insertType);
      } else if (this.questionType === '判断题') {
        this.addJudge(this.insertIndex, this.insertType);
      } else if (this.questionType === '评分题') {
        this.addGrade(this.insertIndex, this.insertType);
      } else if (this.questionType === '简答题') {
        this.addSimpleAnswer(this.insertIndex, this.insertType);
      }
      // else if (this.questionType === '文件上传') {
      //   this.addFile(this.insertIndex, this.insertType);
      // }
      else if (this.questionType === '排序题') {
        this.addSort(this.insertIndex, this.insertType);
      } else if (this.questionType === '填空题') {
        this.addBlank(this.insertIndex, this.insertType);
      }
      this.dialogVisible = false;
    },
    deleteQuestion(index) {
      this.questionnaire.questions.splice(index, 1);
    },
    plus(options, max) {
      if (options.length < max) {
        options.push('选项' + (options.length + 1));
      }
    },
    sub(options, min) {
      if (options.length > min) {
        options.pop();
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
    exit() {
      this.$confirm('确认退出编辑吗？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        customClass: 'message-box',
        type: 'warning',
      }).then(() => {
        router.back();
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消退出',
        });
      });
    },
    preview() {
      router.push({
        path: '/answer',
        query: {
          id: this.questionnaire.id,
          preview: true,
        },
      });
    },
    async save() {
      let validate = true;
      for (let i = 0; i < this.questionnaire.questions.length; i++) {
        validate = await new Promise((resolve) => {
          this.validateQuestion(this.questionnaire.questions[i], i, (result) => {
            resolve(result);
          });
        });
        if (!validate) {
          break;
        }
      }
      if (validate) {
        try {
          await this.$confirm('确认保存问卷吗？', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            customClass: 'message-box',
            type: 'warning',
          });
          let data = {
            id: this.questionnaire.id,
            questions: this.questionnaire.questions,
          };
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
      }
    },
    // 验证题目是否合法
    validateQuestion(question, index, callback) {
      if (question.stem === '') {
        this.$message.error('第' + (index + 1) + '题题干不能为空');
        callback(false);
        return;
      }
      if (question.stem.length < 2 || question.stem.length > 100) {
        this.$message.error('第' + (index + 1) + '题题干长度应在2-100之间');
        callback(false);
        return;
      }
      if (question.type === '单选题' || question.type === '多选题' || question.type === '排序题') {
        if (question.options.length < 2 || question.options.length > 6) {
          this.$message.error('第' + (index + 1) + '题选项数应在2-6之间');
          callback(false);
          return;
        }
        for (let i = 0; i < question.options.length; i++) {
          if (question.options[i] === '') {
            this.$message.error('第' + (index + 1) + '题选项不能为空');
            callback(false);
            return;
          }
          if (question.options[i].length < 2 || question.options[i].length > 50) {
            this.$message.error('第' + (index + 1) + '题选项长度应在2-50之间');
            callback(false);
            return;
          }
        }
      }
      if (question.type === '填空题') {
        question.related = question.stem.split('&______&').length - 1;
        if (question.related < 1 || question.related > 4) {
          this.$message.error('第' + (index + 1) + '题填空数应在1-4之间');
          callback(false);
          return;
        }
      }
      callback(true);
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
  width: 65%;
  height: 88%;
}

#type {
  overflow-y: auto;
  flex-basis: calc(30% - 5px); /* 分别占据50%宽度，减去5像素的间隔 */
  height: 100%;
  background-color: #FFFFFF;
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
  margin-left: 60px;
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


el-dialog .el-dialog__body {
  display: flex;
  justify-content: center;
  align-items: center;
}

.dialog-footer {
  display: flex;
  justify-content: center;
}

:deep(.el-dialog) {
  display: flex;
  flex-direction: column;
  margin: 0 !important;
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  width: 40%;
}

</style>

<style scoped>

</style>

<style lang="scss">

.message-box {
  .el-button {
    padding-top: 8px;
  }
}

</style>
