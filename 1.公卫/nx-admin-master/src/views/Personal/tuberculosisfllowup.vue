<template>
  <section class="app-container">

    <!--工具条-->
    <el-col :span="22" class="toolbar" style="padding-bottom: 0px;">
      <el-form :inline="true" :model="filters" @submit.native.prevent>
        <el-form-item>
          <el-input v-model="filters.name" placeholder="姓名"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" v-on:click="getUsers"style="background-color:#4db2ee;border-color:transparent" icon="el-icon-search">查询</el-button>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="handleAdd"icon="el-icon-circle-plus-outline">新增</el-button>
        </el-form-item>
      </el-form>
    </el-col>


    <!--列表-->
    <el-table :data="users":height="tableHeight"  highlight-current-row @selection-change="selsChange" border style="width: 100%;">
      <el-table-column  v-if="false" type="selection" width="55">
      </el-table-column>
      <el-table-column type="index" width="60">
      </el-table-column>
      <el-table-column prop="name" label="姓名" width="120">
      </el-table-column>
      <el-table-column prop="id" label="编号" width="120">
      </el-table-column>
      <el-table-column prop="flowUpTime" label="随访时间" width="120">
      </el-table-column>
      <el-table-column prop="nextFollotime" label="下次随访时间" min-width="120">
      </el-table-column>
      <el-table-column prop="followdoctorSignature" label="随访医生" min-width="120">
      </el-table-column>

      <el-table-column label="操作" width="250">
        <template slot-scope="scope">
          <a size="14px" @click="handleEdit(scope.$index, scope.row)" style="color:#4DB2EE;margin-right:20px;">编辑</a>
          <a size="14px" @click="handleDel(scope.$index, scope.row)" style="color:#FB4141">删除</a>
        </template>
      </el-table-column>
    </el-table>

    <!--工具条-->
    <el-col :span="24" class="toolbar">
      <!--<el-button type="danger" @click="batchRemove" :disabled="this.sels.length===0">批量删除</el-button>  -->
      <el-pagination layout="prev, pager, next" @current-change="handleCurrentChange" :page-size="20" :total="total" style="float:right;">
      </el-pagination>
    </el-col>

    <!--编辑界面-->
    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible" :close-on-click-modal="false" width="80%" :before-close="handleClose" center>
      <div id="print">
      <el-form :model="editForm" label-width="120px" :rules="editFormRules" ref="editForm">
        <div class="container">
          <el-tabs v-model="activeName">
            <el-tab-pane :label="`基本信息`" name="first">
              <el-form-item label="姓名" prop="name">
                <el-col :span="11">
                  <el-input v-model="editForm.name" auto-complete="off"></el-input>
                </el-col>

              </el-form-item>
              <el-form-item label="随访时间" prop="flowUpTime">
                <el-date-picker type="date" placeholder="选择日期" v-model="editForm.flowUpTime"></el-date-picker>
              </el-form-item>

              <el-form-item label="治疗月序" prop="treatmentMonth" >
                <el-col class="line" :span="1">第</el-col>
                <el-col :span="5">
                  <el-input-number v-model="editForm.treatmentMonth" :min="1" :max="200" style="width: 160px"></el-input-number>
                </el-col>
                <el-col class="line" :span="2">月</el-col>
              </el-form-item>

              <el-form-item label=督导人员选择>
                <el-col :span="24">
                  <el-radio-group v-model="editForm.superivisor">
                    <el-radio label=医生>医生</el-radio>
                    <el-radio label=家属>家属</el-radio>
                    <el-radio label=自服药>自服药</el-radio>
                    <el-radio label=其他>其他</el-radio>
                  </el-radio-group>
                </el-col>
              </el-form-item>


              <el-form-item label="随访方式">
                <el-col :span="11">
                  <el-radio-group v-model="editForm.followUpMethods">
                    <el-radio label=门诊>门诊</el-radio>
                    <el-radio label=家庭>家庭</el-radio>
                  </el-radio-group>
                </el-col>
              </el-form-item>
            </el-tab-pane>
            <el-tab-pane :label="`其他信息1`" name="second">

              <el-form-item label=症状及体征>
                <el-col :span="20">
                  <el-checkbox-group v-model="editForm.symptomsSigns">
                    <el-checkbox v-for="item in symptomsSigns" :key="item.value":name="type":label="item.label":value="item.value"></el-checkbox>
                  </el-checkbox-group>
                </el-col>
                <el-col>
                  <el-input v-model="editForm.otherSymptoms"  placeholder="其他症状"></el-input>
                </el-col>
              </el-form-item>

              <el-form-item label="吸烟">
                <el-col :span="6">
                  <el-input  v-model="editForm.smoking"  >
                    <template slot="append">支/天</template>
                  </el-input>
                </el-col>
                <el-col class="line" :span="2">支/天</el-col>
                <el-form-item label="饮酒">
                  <el-col :span="6">
                    <el-input  v-model="editForm.alcohol"  >
                      <template slot="append">两/天</template>
                    </el-input>
                  </el-col>
                </el-form-item>
              </el-form-item>

              <el-form-item label="化疗方案" >
                <el-input v-model="editForm.chemotherapyRegimen" ></el-input>
              </el-form-item>
              <el-form-item label=用法>
                <el-radio-group v-model="editForm.usages">
                  <el-radio label=每日>每日</el-radio>
                  <el-radio label=间歇>间歇</el-radio>
                </el-radio-group>
              </el-form-item>
              <el-form-item label="药品剂型">
                <el-col>
                  <el-checkbox-group v-model="editForm.drugDosage">
                    <el-checkbox v-for="item in drugDosage" :key="item.value":name="type":label="item.label":value="item.value"></el-checkbox>
                  </el-checkbox-group>
                </el-col>
              </el-form-item>
              <el-form-item label="漏服药次数">
                <el-col :span="6">
                  <el-input  v-model="editForm.numbermissedMedications"  ></el-input>
                </el-col>
                <el-col class="line" :span="2">次</el-col>
              </el-form-item>

              <el-form-item label="药物不良反应">
                <el-col :span="4">
                  <el-radio-group v-model="editForm.adverseDrugReactions">
                    <el-radio label=无>无</el-radio>
                    <el-radio label=有>有</el-radio>
                  </el-radio-group>
                </el-col>
                <el-col >
                    <el-input  v-model="editForm.adverseReactions" placeholder="不良反应" ></el-input>
                </el-col>
              </el-form-item>

              <el-form-item label="并发症或合并症">
                <el-col :span="4">
                  <el-radio-group v-model="editForm.complicationComorbidities">
                    <el-radio label=无>无</el-radio>
                    <el-radio label=有>有</el-radio>
                  </el-radio-group>
                </el-col>
                <el-col >
                  <el-input  v-model="editForm.complications" placeholder="并发症或合并症"  ></el-input>
                </el-col>
              </el-form-item>



              <el-form-item label=转诊科别>
                <el-col :span="9">
                  <el-input  v-model="editForm.subjects"  ></el-input>
                </el-col>
                <el-form-item label=转诊原因>
                  <el-col :span="13" >
                    <el-input  v-model="editForm.causes"  style="width: 360px" ></el-input>
                  </el-col>
                </el-form-item>
              </el-form-item>

              <el-form-item label=2周内随访，随访结果：>
                <el-col :span="24">
                  <el-input  v-model="editForm.twoweeksResult"  type="textarea" rows="4"></el-input>
                </el-col>
              </el-form-item>

              <el-form-item label=处理意见>
                <el-col :span="24">
                  <el-input  v-model="editForm.treamntOptions"  type="textarea" rows="2" ></el-input>
                </el-col>
              </el-form-item>
            </el-tab-pane>

            <el-tab-pane :label="`其他信息2`" name="third">
              <el-form-item label="下次随访时间">
                <el-col :span="11">
                  <el-date-picker type="date"  v-model="editForm.nextFollotime" style="width: 100%;"></el-date-picker>
                </el-col>
                <el-col :span="11" >
                  <el-form-item label="随访医生签名">
                    <el-input  v-model="editForm.followdoctorSignature" auto-complete="off" ></el-input>
                  </el-form-item>
                </el-col>
              </el-form-item>

              <el-form-item label="停止治疗时间">
                <el-col :span="11">
                  <el-date-picker type="date"  v-model="editForm.stopTime" style="width: 100%;"></el-date-picker>
                </el-col>
              </el-form-item>

              <el-form-item label="停止治疗原因">
                <el-col :span="24">
                  <el-radio-group v-model="editForm.stopTreatment">
                    <el-radio label=完成治疗>完成治疗</el-radio>
                    <el-radio label=死亡>死亡</el-radio>
                    <el-radio label=丢失>丢失</el-radio>
                    <el-radio label=转入耐多药治疗>转入耐多药治疗</el-radio>
                  </el-radio-group>
                </el-col>
              </el-form-item>

              <el-form-item label="全程管理情况">
                <el-row>
                  应访视患者
                  <el-input style="width: 100px" v-model="editForm.shouldVisit" auto-complete="off" ></el-input>
                  次,实际访视 <el-input style="width: 100px" v-model="editForm.actualVisit" auto-complete="off" ></el-input>次
                </el-row>
                患者在疗程中，应服药<el-input style="width: 100px" v-model="editForm.mnumner"></el-input>次，实际服药<el-input style="width: 100px" v-model="editForm.amnumber" auto-complete="off" ></el-input>次，服药率
                <el-input style="width: 100px" v-model="editForm.medicineRate"></el-input>%
                <el-row>
                </el-row>
              </el-form-item>

              <el-form-item label="评估医生签名">
                <el-col span="10">
                  <el-input  v-model="editForm.evaluationDoctor" auto-complete="off" ></el-input>
                </el-col>
              </el-form-item>

            </el-tab-pane>
          </el-tabs>
        </div>
      </el-form>
      </div>
      <div slot="footer" class="dialog-footer">
        <el-button @click.native="dialogFormVisible=false">取消</el-button>
        <el-button v-if="dialogStatus=='create'" type="primary" @click="createData">添加</el-button>
        <el-button v-else type="primary" @click="updateData">修改</el-button>
        <el-button @click="conprint()">打印</el-button>
      </div>
    </el-dialog>

  </section>
</template>

<script>
  import util from '@/utils/table.js'
  import {
    getUserListPage,
    removeUser,
    batchRemoveUser,
    editUser,
    addUser
  } from '@/api/tuberculosisfllowup'

  export default {
    data() {
      return {
        symptomsSigns: [
          { value: '没有症状', label: '没有症状' },
          { value: '咳嗽咳痰', label: '咳嗽咳痰' },
          { value: '低热盗汗', label: '低热盗汗' },
          { value: '咯血或血痰', label: '咯血或血痰' },
          { value: '胸痛消廋', label: '胸痛消廋' },
          { value: '恶心纳差', label: '恶心纳差' },
          { value: '头痛失眠', label: '头痛失眠' },
          { value: '视物模糊', label: '视物模糊' },
          { value: '皮肤瘙痒、皮疹', label: '皮肤瘙痒、皮疹' },
          { value: '耳鸣、听力下降', label: '耳鸣、听力下降' }
        ],
        drugDosage: [
          { value: '固定剂量复合制剂', label: '固定剂量复合制剂' },
          { value: '散装药', label: '散装药' },
          { value: '板式组合药', label: '板式组合药' }
        ],
        activeName: 'first',
        dialogStatus: '',
        tableHeight: window.innerHeight - 260,
        textMap: {
          update: '编辑',
          create: '新增'
        },
        dialogFormVisible: false,
        filters: {
          name: '',
          flowUpTime_s: null,
          flowUpTime_e: null
        },
        users: [],
        total: 0,
        page: 1,
        sels: [], // 列表选中列
        editFormRules: {
          name: [{ required: true, message: '请输入姓名', trigger: 'blur' }],
          flowUpTime: [{ required: true, message: '请输入随访时间', trigger: 'blur' }],
          treatmentMonth: [{ required: true, message: '请输入治疗月序', trigger: 'blur' }]
        },
        // 编辑界面数据
        editForm: {
          name: '',
          flowUpTime: null,
          treatmentMonth: '',
          superivisor: '',
          followUpMethods: '',
          symptomsSigns: [],
          smoking: '',
          alcohol: '',
          chemotherapyRegimen: '',
          usages: '',
          drugDosage: [],
          numbermissedMedications: '',
          adverseDrugReactions: '',
          complicationComorbidities: '',
          subjects: '',
          causes: '',
          twoweeksResult: '',
          treamntOptions: '',
          nextFollotime: null,
          followdoctorSignature: '',
          stopTime: null,
          stopTreatment: '',
          shouldVisit: '',
          mnumner: '',
          amnumber: '',
          medicineRate: '',
          evaluationDoctor: '',
          adverseReactions: '',
          complications: '',
          otherSymptoms: '',
          actualVisit: '',
          other: ''
        },

        addFormVisible: false, // 新增界面是否显示
        addFormRules: {
          name: [{ required: true, message: '请输入姓名', trigger: 'blur' }],
          flowUpTime: [{ required: true, message: '请输入随访时间', trigger: 'blur' }],
          treatmentMonth: [{ required: true, message: '请输入治疗月序', trigger: 'blur' }]

        }
      }
    },
    methods: {
      // 性别显示转换
      formatSex: function(row, column) {
        return row.sex === 1 ? '男' : row.sex === 0 ? '女' : '未知'
      },
      handleCurrentChange(val) {
        this.page = val
        this.getUsers()
      },
      // 打印
      printf(content, w = null, h = null) {
        // Fixes dual-screen position                         Most browsers      Firefox
        const dualScreenLeft = window.screenLeft !== undefined ? window.screenLeft : screen.left
        const dualScreenTop = window.screenTop !== undefined ? window.screenTop : screen.top

        const width = window.innerWidth ? window.innerWidth : document.documentElement.clientWidth ? document.documentElement.clientWidth : screen.width
        const height = window.innerHeight ? window.innerHeight : document.documentElement.clientHeight ? document.documentElement.clientHeight : screen.height
        w = +w === 0 ? width : w
        h = +h === 0 ? height : h
        const left = ((width / 2) - (w / 2)) + dualScreenLeft
        const top = ((height / 2) - (h / 2)) + dualScreenTop
        const myWindow = window.open('', '打印', 'toolbar=no, location=no, directories=no, status=no, menubar=no, scrollbars=no, resizable=yes, copyhistory=no, width=' + w + ', height=' + h + ', top=' + top + ', left=' + left)
        const style = "<style type='text/css'>table.gridtable {font-family: verdana,arial,sans-serif;font-size:11px;color:#333333;border-width: 1px;border-color: #666666;border-collapse: collapse;}table.gridtable th {border-width: 1px;padding: 8px;border-style: solid;border-color: #666666;background-color: #dedede;}table.gridtable td {border-width: 1px;padding: 8px;border-style: solid;border-color: #666666;background-color: #ffffff;}</style>"
        var str = style
        str = str + '<html>\n' +
          '<head>\n' +
          '<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />\n' +
          '<title></title>\n' +
          '<style media="print">\n' +
          '@page {\n' +
          ' size: auto;  /* auto is the initial value */\n' +
          ' margin: 0mm; /* this affects the margin in the printer settings */\n' +
          '}\n' +
          '</style>\n' +
          '</head>\n' +
          '<body>\n' +
          '\n' +
          '<div id="dataPanel" style="position:absolute;left:50%;margin-left:-397px;">\n' +
          '  <div id="topContent">\n' +
          '    <h2 style="width:794px;text-align:center"> 肺结核患者随访记录表</h2> \n' +
          '    <span style="margin-left:34px;">姓名：</span>\n' +
          '    <span style="margin-left:34px;">{{name}}</span>\n' +
          '\t<span style="margin-left:510px;">编号:</span>\n' +
          '    <span style="margin-left:5px;">{{id}}</span> \n' +
          '    </div>\n' +
          '  <table id="table"  border="1"  cellspacing="0" bordercolor="#000000" style="width:730px;margin-left:30px;margin-top:5px;border-collapse:collapse;"  width = "80%">\n' +
          '    <tbody>\n' +
          '      \n' +
          '      <tr>\n' +
          '        <td style="word-wrap:break-word;width: 10%;text-align:center">随访时间</td>\n' +
          '        <td colspan=\'1\'>{{flowUpTime}}</td>\n' +
          '        <td style="text-align:center">治疗月序</td>\n' +
          '        <td colspan=\'2\' width="100px">{{treatmentMonth}}</td>\n' +
          '      </tr>\n' +
          '      <tr>\n' +
          '        <td style="word-wrap:break-word;width: 10%;text-align:center">督导人员</td>\n' +
          '        <td colspan=\'1\'>{{superivisor}}</td>\n' +
          '        <td style="width: 19%;text-align:center">随访方式</td>\n' +
          '        <td colspan=\'2\'>{{followUpMethods}}</td>\n' +
          '      </tr>\n' +
          '\t  \n' +
          '\t   <tr>\n' +
          '        <td rowspan=\'3\' style="word-wrap:break-word;width: 10%;text-align:center">症状及体征</td>\n' +
          '        <td colspan=\'4\'height="30px">{{symptomsSigns}}</td>   \n' +
          '      </tr> \n' +
          '\t<td rowspan=\'2\'>其他</td>\n' +
          '\t<td rowspan=\'2\' colspan=\'3\'>{{otherSymptoms}}</td>\n' +
          '\t <tr></tr>\n' +
          '\t \n' +
          '\t   \n' +
          '\t   <tr>\n' +
          '        <td rowspan=\'2\' style="word-wrap:break-word;width: 10%;text-align:center">生活方式评估</td>\n' +
          '        <td >吸烟</td>\n' +
          '        <td colspan=\'3\'>{{smoking}} 支/天</td>\n' +
          '      </tr>\n' +
          '\t  <tr>\n' +
          '\t\t<td >饮酒</td>\n' +
          '\t\t<td colspan=\'3\'>{{alcohol}} 两/天</td> \n' +
          '\t  </tr>\n' +
          '\t  \n' +
          '\t   <tr>\n' +
          '        <td rowspan=\'4\' style="word-wrap:break-word;width: 10%;text-align:center">用药</td>\n' +
          '        <td >化疗方案</td>\n' +
          '        <td colspan=\'3\'>{{chemotherapyRegimen}}</td>\n' +
          '      </tr>\n' +
          '\t  <tr>\n' +
          '\t\t<td >用法</td>\n' +
          '\t\t<td colspan=\'3\'>{{usages}}</td> \n' +
          '\t  </tr>\n' +
          '\t  <tr>\n' +
          '\t\t<td >药品剂型</td>\n' +
          '\t\t<td colspan=\'3\'>{{drugDosage}}</td> \n' +
          '\t  </tr>\n' +
          '\t  <tr>\n' +
          '\t\t<td>漏服药次数</td>\n' +
          '\t\t<td colspan=\'3\'>{{numbermissedMedications}}</td> \n' +
          '\t  </tr>\n' +
          '\t  \n' +
          '\t   <tr>\n' +
          '        <td style="word-wrap:break-word;width: 10%;text-align:center">药物不良反应</td>\n' +
          '        <td colspan=\'1\'>{{adverseDrugReactions}},{{adverseReactions}}</td>\n' +
          '        <td style="width: 19%;text-align:center">并发症或合并症</td>\n' +
          '        <td colspan=\'2\'>{{complicationComorbidities}},{{complications}}</td>\n' +
          '      </tr>\n' +
          '\t  \n' +
          '     <tr>\n' +
          '        <td rowspan=\'3\' style="word-wrap:break-word;width: 10%;text-align:center">转诊</td>\n' +
          '        <td >科别</td>\n' +
          '\t\t<td colspan=\'3\'>{{subjects}}</td> \n' +
          '      </tr>\n' +
          '\t  <tr>\n' +
          '\t\t<td >原因</td>\n' +
          '\t\t<td colspan=\'3\'>{{causes}}</td> \n' +
          '\t  </tr>\n' +
          '\t  <tr>\n' +
          '\t\t<td >2周内随访，随访结果</td>\n' +
          '\t\t<td colspan=\'3\'>{{twoweeksResult}}</td> \n' +
          '\t  </tr>\n' +
          '\t  \n' +
          '\t\n' +
          '\t   <tr>\n' +
          '        <td style="word-wrap:break-word;width: 10%;text-align:center">处理意见</td>\n' +
          '        <td colspan=\'4\'>{{treamntOptions}}</td>\n' +
          '      </tr>\n' +
          '\t    <tr>\n' +
          '        <td style="width: 10%;text-align:center">下次随访时间</td>\n' +
          '        <td colspan=\'1\'>{{nextFollotime}}</td>\n' +
          '\t\t <td style="width: 10%;text-align:center">随访医生签名</td>\n' +
          '        <td colspan=\'2\'>{{followdoctorSignature}}</td>\n' +
          '      </tr> \n' +
          '\t  <tr>\n' +
          '        <td style="width: 10%;text-align:center">停止治疗及原因</td>\n' +
          '        <td colspan=\'4\'>1.出现停止治疗时间：{{stopTime}}</br>2.停止治疗原因：{{stopTreatment}}</td>\n' +
          '      </tr>\n' +
          '\t  <tr>\n' +
          '        <td style="width: 10%;text-align:center">全程管理情况</td>\n' +
          '        <td colspan=\'4\'>应访视患者 {{shouldVisit}} 次，实际访视 {{actualVisit}} 次;</br>患者在疗程中，应服药 {{mnumner}} 次，实际服药 {{amnumber}} 次，服药率 {{medicineRate}}% </td>\n' +
          '      </tr>\n' +
          '\t  \n' +
          '\t  \n' +
          '    </tbody>\n' +
          '   \n' +
          '  \n' +
          '  </table>\n' +
          '</div>\n' +
          '</body>\n' +
          '</html>\n'
        str = str.replace(/{{name}}/, this.editForm.name ? this.editForm.name : '')
        str = str.replace(/{{id}}/, this.editForm.id ? this.editForm.id : '')
        str = str.replace(/{{flowUpTime}}/, this.editForm.flowUpTime ? this.editForm.flowUpTime : '')
        str = str.replace(/{{treatmentMonth}}/, this.editForm.treatmentMonth ? this.editForm.treatmentMonth : '')
        str = str.replace(/{{superivisor}}/, this.editForm.superivisor ? this.editForm.superivisor : '')
        str = str.replace(/{{followUpMethods}}/, this.editForm.followUpMethods ? this.editForm.followUpMethods : '')
        str = str.replace(/{{symptomsSigns}}/, this.editForm.symptomsSigns ? this.editForm.symptomsSigns : '')
        str = str.replace(/{{alcohol}}/, this.editForm.alcohol ? this.editForm.alcohol : '')
        str = str.replace(/{{smoking}}/, this.editForm.smoking ? this.editForm.smoking : '')
        str = str.replace(/{{chemotherapyRegimen}}/, this.editForm.chemotherapyRegimen ? this.editForm.chemotherapyRegimen : '')
        str = str.replace(/{{usages}}/, this.editForm.usages ? this.editForm.usages : '')
        str = str.replace(/{{drugDosage}}/, this.editForm.drugDosage ? this.editForm.drugDosage : '')
        str = str.replace(/{{numbermissedMedications}}/, this.editForm.numbermissedMedications ? this.editForm.numbermissedMedications : '')
        str = str.replace(/{{adverseDrugReactions}}/, this.editForm.adverseDrugReactions ? this.editForm.adverseDrugReactions : '')
        str = str.replace(/{{complicationComorbidities}}/, this.editForm.complicationComorbidities ? this.editForm.complicationComorbidities : '')
        str = str.replace(/{{subjects}}/, this.editForm.subjects ? this.editForm.subjects : '')
        str = str.replace(/{{causes}}/, this.editForm.causes ? this.editForm.causes : '')
        str = str.replace(/{{twoweeksResult}}/, this.editForm.twoweeksResult ? this.editForm.twoweeksResult : '')
        str = str.replace(/{{treamntOptions}}/, this.editForm.treamntOptions ? this.editForm.treamntOptions : '')
        str = str.replace(/{{nextFollotime}}/, this.editForm.nextFollotime ? this.editForm.nextFollotime : '')
        str = str.replace(/{{followdoctorSignature}}/, this.editForm.followdoctorSignature ? this.editForm.followdoctorSignature : '')
        str = str.replace(/{{stopTime}}/, this.editForm.stopTime ? this.editForm.stopTime : '')
        str = str.replace(/{{stopTreatment}}/, this.editForm.stopTreatment ? this.editForm.stopTreatment : '')
        str = str.replace(/{{shouldVisit}}/, this.editForm.shouldVisit ? this.editForm.shouldVisit : '')
        str = str.replace(/{{mnumner}}/, this.editForm.mnumner ? this.editForm.mnumner : '')
        str = str.replace(/{{amnumber}}/, this.editForm.amnumber ? this.editForm.amnumber : '')
        str = str.replace(/{{medicineRate}}/, this.editForm.medicineRate ? this.editForm.medicineRate : '')
        str = str.replace(/{{evaluationDoctor}}/, this.editForm.evaluationDoctor ? this.editForm.evaluationDoctor : '')
        str = str.replace(/{{adverseReactions}}/, this.editForm.adverseReactions ? this.editForm.adverseReactions : '')
        str = str.replace(/{{complications}}/, this.editForm.complications ? this.editForm.complications : '')
        str = str.replace(/{{otherSymptoms}}/, this.editForm.otherSymptoms ? this.editForm.otherSymptoms : '')
        str = str.replace(/{{actualVisit}}/, this.editForm.actualVisit ? this.editForm.actualVisit : '')
        myWindow.document.write(str)
        // myWindow.document.write(content + style)
        myWindow.focus()
        myWindow.document.close()
        myWindow.print()
        return myWindow
      },
      conprint() {
        const windows = this.printf(document.getElementById('print').innerHTML)
        windows.close()
      },
      // 获取用户列表
      getUsers() {
        this.filters.flowUpTime_s =
          !this.filters.flowUpTime_s || this.filters.flowUpTime_s === ''
            ? null
            : util.formatDate.format(new Date(this.filters.flowUpTime_s), 'yyyy-MM-dd')
        this.filters.flowUpTime_e =
          !this.filters.flowUpTime_e || this.filters.flowUpTime_e === ''
            ? null
            : util.formatDate.format(new Date(this.filters.flowUpTime_e), 'yyyy-MM-dd')
        const para = {
          page: this.page,
          name: this.filters.name,
          flowUpTime_s: this.filters.flowUpTime_s,
          flowUpTime_e: this.filters.flowUpTime_e
        }
        getUserListPage(para).then(res => {
          this.total = res.data.total
          this.users = res.data.hhh
        })
      },
      // 删除
      handleDel(index, row) {
        this.$confirm('确认删除该记录吗?', '提示', {
          type: 'warning'
        })
          .then(() => {
            const para = { id: row.id }
            removeUser(para).then(res => {
              this.$message({
                message: '删除成功',
                type: 'success'
              })
              this.getUsers()
            })
          })
          .catch(() => {})
      },

      // 显示编辑界面
      handleEdit(index, row) {
        this.dialogStatus = 'update'
        this.dialogFormVisible = true
        this.editForm = Object.assign({}, row)
        this.editForm.drugDosage = this.editForm.drugDosage.split(',')
        this.editForm.symptomsSigns = this.editForm.symptomsSigns.split(',')
      },
      // 显示新增界面
      handleAdd() {
        this.dialogStatus = 'create'
        this.dialogFormVisible = true
        this.editForm = {
          name: '',
          flowUpTime: null,
          treatmentMonth: '',
          superivisor: '',
          followUpMethods: '',
          symptomsSigns: [],
          smoking: '',
          alcohol: '',
          chemotherapyRegimen: '',
          usages: '',
          drugDosage: [],
          numbermissedMedications: '',
          adverseDrugReactions: '',
          complicationComorbidities: '',
          subjects: '',
          causes: '',
          twoweeksResult: '',
          treamntOptions: '',
          nextFollotime: null,
          followdoctorSignature: '',
          stopTime: null,
          stopTreatment: '',
          shouldVisit: '',
          mnumner: '',
          amnumber: '',
          medicineRate: '',
          evaluationDoctor: '',
          adverseReactions: '',
          complications: '',
          otherSymptoms: '',
          actualVisit: '',
          other: ''
        }
      },
      // 编辑
      updateData() {
        this.$refs.editForm.validate(valid => {
          if (valid) {
            this.$confirm('确认提交吗？', '提示', {})
              .then(() => {
                const para = Object.assign({}, this.editForm)
                para.flowUpTime =
                  !para.flowUpTime || para.flowUpTime === ''
                    ? null
                    : util.formatDate.format(new Date(para.flowUpTime), 'yyyy-MM-dd')
                para.nextFollotime = new Date()
                para.nextFollotime =
                  !para.nextFollotime || para.nextFollotime === ''
                    ? null
                    : util.formatDate.format(new Date(para.nextFollotime), 'yyyy-MM-dd')
                para.stopTime = new Date()
                para.stopTime =
                  !para.stopTime || para.stopTime === ''
                    ? null
                    : util.formatDate.format(new Date(para.stopTime), 'yyyy-MM-dd')
                para.drugDosage = ''
                for (const itm of this.editForm.drugDosage) {
                  para.drugDosage += itm
                  para.drugDosage += ','
                }
                para.drugDosage = para.drugDosage.substr(0, para.drugDosage.length - 1)

                para.symptomsSigns = ''
                for (const itm of this.editForm.symptomsSigns) {
                  para.symptomsSigns += itm
                  para.symptomsSigns += ','
                }
                para.symptomsSigns = para.symptomsSigns.substr(0, para.symptomsSigns.length - 1)
                editUser(para).then(res => {
                  this.$message({
                    message: '提交成功',
                    type: 'success'
                  })
                  this.$refs['editForm'].resetFields()
                  this.dialogFormVisible = false
                  this.getUsers()
                })
              })
              .catch(e => {
                // 打印一下错误
                console.log(e)
              })
          }
        })
      },
      // 新增
      createData: function() {
        this.$refs.editForm.validate(valid => {
          if (valid) {
            this.$confirm('确认提交吗？', '提示', {})
              .then(() => {
                this.editForm.id = (parseInt(Math.random() * 100)).toString() // mock a id
                const para = Object.assign({}, this.editForm)
                console.log(para)

                para.flowUpTime =
                  !para.flowUpTime || para.flowUpTime === ''
                    ? null
                    : util.formatDate.format(new Date(para.flowUpTime), 'yyyy-MM-dd')
                para.nextFollotime = new Date()
                para.nextFollotime =
                  !para.nextFollotime || para.nextFollotime === ''
                    ? null
                    : util.formatDate.format(new Date(para.nextFollotime), 'yyyy-MM-dd')
                para.stopTime = new Date()
                para.stopTime =
                  !para.stopTime || para.stopTime === ''
                    ? null
                    : util.formatDate.format(new Date(para.stopTime), 'yyyy-MM-dd')
                para.drugDosage = ''
                for (const itm of this.editForm.drugDosage) {
                  para.drugDosage += itm
                  para.drugDosage += ','
                }
                para.drugDosage = para.drugDosage.substr(0, para.drugDosage.length - 1)

                para.symptomsSigns = ''
                for (const itm of this.editForm.symptomsSigns) {
                  para.symptomsSigns += itm
                  para.symptomsSigns += ','
                }
                para.symptomsSigns = para.symptomsSigns.substr(0, para.symptomsSigns.length - 1)
                addUser(para).then(res => {
                  this.$message({
                    message: '提交成功',
                    type: 'success'
                  })
                  this.$refs['editForm'].resetFields()
                  this.dialogFormVisible = false
                  this.getUsers()
                })
              })
              .catch(e => {
                // 打印一下错误
                console.log(e)
              })
          }
        })
      },
      // 全选单选多选
      selsChange(sels) {
        this.sels = sels
      },
      // 批量删除
      batchRemove() {
        var ids = this.sels.map(item => item.id).toString()
        this.$confirm('确认删除选中记录吗？', '提示', {
          type: 'warning'
        })
          .then(() => {
            const para = { ids: ids }
            batchRemoveUser(para).then(res => {
              this.$message({
                message: '删除成功',
                type: 'success'
              })
              this.getUsers()
            })
          })
          .catch(() => {})
      }
    },
    mounted() {
      this.getUsers()
    }
  }
</script>

<style scoped>

</style>
