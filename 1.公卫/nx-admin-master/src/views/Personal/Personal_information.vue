<template>

  <section class="app-container">
    <!--工具条-->
    <el-col :span="24" class="toolbar" style="padding-bottom: 0px;">
      <el-form :inline="true" :model="filters" @submit.native.prevent>
        <el-form-item label-width="160px">
          <el-input v-model="filters.name" placeholder="姓名"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" v-on:click="searchs" style="background-color:#4db2ee;border-color:transparent" icon="el-icon-search" >查询</el-button>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" v-on:click="findBtn"  style="background-color:#4db2ee;border-color:transparent;width: 120px" icon="el-icon-search">高级查询</el-button>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="handleAdd" icon="el-icon-circle-plus-outline">新增</el-button>
        </el-form-item>
      </el-form>
    </el-col>

    <!--查询-->
    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible2" :close-on-click-modal="false" >
      <el-form :model="editForm" label-width="80px" :rules="editFormRules" ref="editForm">
        <el-form-item label-width="160px">
          <el-input v-model="filters.name" placeholder="姓名"></el-input>
        </el-form-item>
        <el-form-item label-width="160px">
          <el-input v-model="filters.guardianName" placeholder="监护人"></el-input>
        </el-form-item>
        <el-form-item label-width="160px">
          <el-input v-model="filters.guardianAddre" placeholder="监护人住址"></el-input>
        </el-form-item>
        <el-form-item label-width="160px">
          <el-input v-model="filters.guardPhone" placeholder="监护人电话"></el-input>
        </el-form-item>
        <el-form-item label-width="160px">
          <el-input v-model="filters.commitPersonName" placeholder="辖区村委会联系人"></el-input>
        </el-form-item>
        <el-form-item label-width="160px">
          <el-input v-model="filters.commitPersonNum" placeholder="辖区村委会联系人电话"></el-input>
        </el-form-item>
        <el-form-item label-width="160px">
          <el-input v-model="filters.accountType" placeholder="户别"></el-input>
        </el-form-item>
        <el-form-item label-width="160px">
          <el-input v-model="filters.outService" placeholder="门诊"></el-input>
        </el-form-item>
        <el-form-item label-width="160px">
          <el-date-picker type="date" placeholder="初次发病时间" v-model="filters.firstOnsetTime" style="width: 100%;"></el-date-picker>
        </el-form-item>
        <el-form-item label-width="160px">
          <el-date-picker type="date" placeholder="首次抗精神病治疗时间" v-model="filters.firstAntipsyTreat" style="width: 100%;"></el-date-picker>
        </el-form-item>

        <el-form-item  label-width="160px">
          <el-col :span="11">
            <el-date-picker type="date" placeholder="初次发病开始时间" v-model="filters.firstOnsetTime_s" style="width: 100%;"></el-date-picker>
          </el-col>
          <el-col class="line" :span="2">——————</el-col>
          <el-col :span="11">
            <el-date-picker type="date" placeholder="初次发病结束时间" v-model="filters.firstOnsetTime_e" style="width: 100%;"></el-date-picker>
          </el-col>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer" align="center">
        <el-button type="primary" v-on:click="searchs" style="background-color:#4db2ee;border-color:transparent" icon="el-icon-search" >查询</el-button>
      </div>
    </el-dialog>

    <!--列表-->
    <el-table :data="users" :height="tableHeight" highlight-current-row @selection-change="selsChange" border style="width: 100%;margin-top:-20px;" @row-dblclick="handleRowChange">
      <!--<el-table-column type="selection"  width="55">-->
      <!--</el-table-column>-->
      <el-table-column type="index" label="序号" width="50" >
      </el-table-column>
      <el-table-column prop="idNumber" label="身份证号" width="200">
      </el-table-column>
      <el-table-column prop="name" label="姓名" width="100">
      </el-table-column>
      <el-table-column prop="guardianName" label="监护人" width="100" >
      </el-table-column>
      <el-table-column prop="relaPatients" label="与患者关系" width="100" v-if="false">
      </el-table-column>
      <el-table-column prop="guardianAddre" label="监护人住址" width="150" v-if="false">
      </el-table-column>
      <el-table-column prop="guardPhone" label="监护人电话" width="150" v-if="false">
      </el-table-column>
      <el-table-column prop="commitPersonName" label="辖区村(居)委会联系人" width="160" v-if="false">
      </el-table-column>
      <el-table-column prop="commitPersonNum" label="辖区村(居)委会联系人电话" width="200" v-if="false">
      </el-table-column>
      <el-table-column prop="accountType" label="户别" width="100">
      </el-table-column>
      <el-table-column prop="employSituat" label="就业情况" width="100">
      </el-table-column>
      <el-table-column prop="infConsent" label="知情同意" width="100"  v-if="false">
      </el-table-column>
      <el-table-column prop="firstOnsetTime" label="初次发病时间" width="150" >
      </el-table-column>
      <el-table-column prop="pastSymptoms" label="既往主要症状" width="150" v-if="false">
      </el-table-column>
      <el-table-column prop="pastLocking" label="既往关锁情况" width="150" v-if="false">
      </el-table-column>
      <el-table-column prop="outService" label="门诊" width="100" >
      </el-table-column>
      <el-table-column prop="firstAntipsyTreat" label="首次抗精神药治疗时间" width="200" v-if="false">
      </el-table-column>
      <el-table-column prop="inHospital" label="住院" width="100" v-if="false">
      </el-table-column>
      <el-table-column prop="diagnosis" label="诊断" width="100">
      </el-table-column>
      <el-table-column prop="hospitalDia" label="确诊医院" width="100" v-if="false">
      </el-table-column>
      <el-table-column prop="confiDate" label="确诊日期" width="100" v-if="false">
      </el-table-column>
      <el-table-column prop="lastTreatEff" label="最近一次治疗效果" width="150" v-if="false">
      </el-table-column>
      <el-table-column prop="dangerBehav" label="危险行为" width="100" v-if="false">
      </el-table-column>
      <el-table-column prop="stateEconomy" label="经济状况" width="100" v-if="false">
      </el-table-column>
      <el-table-column prop="speciaOpinion" label="专科医生的建议" width="150" v-if="false">
      </el-table-column>
      <el-table-column prop="dateFill" label="填表日期" width="100" >
      </el-table-column>
      <el-table-column prop="docSign" label="医生签字" width="100" v-if="false">
      </el-table-column>



      <el-table-column label="操作" width="150">
        <template slot-scope="scope">
          <a size="14px" @click="handleEdit(scope.$index, scope.row)" style="color:#4DB2EE;margin-right:20px;">编辑</a>
          <a size="14px" @click="handleDel(scope.$index, scope.row)" style="color:#FB4141">删除</a>
        </template>
      </el-table-column>
    </el-table>

    <!--工具条-->
    <el-col :span="24" class="toolbar">
      <!--<el-button type="danger" @click="batchRemove" :disabled="this.sels.length===0">批量删除</el-button>-->
      <el-pagination layout="prev, pager, next" :current-page.sync="page" @current-change="handleCurrentChange" :page-size="20" :total="total" style="float:right;">
      </el-pagination>
    </el-col>

    <!--编辑界面-->
    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible" :close-on-click-modal="false" center>
      <el-form :model="editForm" label-width="160px" :rules="editFormRules" ref="editForm">
        <div class="container">
          <div id="print">
          <el-tabs v-model="activeName">

            <el-tab-pane :label="`基本信息`" name="first">

                <el-row>
                  <el-col :span="12">
                  <el-form-item label="姓名"  prop="name">
                    <el-input v-model="editForm.name" auto-complete="off"></el-input>
                  </el-form-item>
                  </el-col>
                  <el-col :span="12">
                    <el-form-item label="监护人姓名"  prop="guardianName">
                      <el-input v-model="editForm.guardianName"></el-input>
                    </el-form-item>
                  </el-col>
                </el-row>

              <el-row>
                <el-col :span="12">
                  <el-form-item label="与患者关系" >
                    <el-input v-model="editForm.relaPatients"></el-input>
                  </el-form-item>
                </el-col>
                <el-col :span="12">
                  <el-form-item label="监护人住址" >
                    <el-input v-model="editForm.guardianAddre"></el-input>
                  </el-form-item>
                </el-col>
              </el-row>

              <el-row>
                <el-col :span="12">
                  <el-form-item label="监护人电话" >
                    <el-input v-model="editForm.guardPhone"></el-input>
                  </el-form-item>
                </el-col>
                <el-col :span="12">
                  <el-form-item label="户别" >
                    <el-select v-model="editForm.accountType" placeholder="请选择" style="width: 100%">
                      <el-option key="1" label="城镇" value="城镇"></el-option>
                      <el-option key="2" label="农村" value="农村"></el-option>
                    </el-select>
                  </el-form-item>
                </el-col>
              </el-row>

              <el-row>
                <el-col :span="12">
                  <el-form-item label="辖区村(居)委会联系人"  >
                    <el-input v-model="editForm.commitPersonName"></el-input>
                  </el-form-item>
                </el-col>
                <el-col :span="12">
                  <el-form-item label="村(居)委会联系人电话">
                    <el-input v-model="editForm.commitPersonNum"></el-input>
                  </el-form-item>
                </el-col>
              </el-row>

              <el-row>
                <el-col :span="12">
                  <el-form-item label="就业情况" >
                    <el-select v-model="editForm.employSituat" placeholder="请选择" style="width: 100%">
                      <el-option key="1" label="在岗工人" value="在岗工人"></el-option>
                      <el-option key="2" label="在岗管理者" value="在岗管理者"></el-option>
                      <el-option key="3" label="农民" value="农民"></el-option>
                      <el-option key="4" label="下岗或无业" value="下岗或无业"></el-option>
                      <el-option key="5" label="在校学生" value="在校学生"></el-option>
                      <el-option key="6" label="退休" value="退休"></el-option>
                      <el-option key="7" label="专业技术人员" value="专业技术人员"></el-option>
                      <el-option key="8" label="其他" value="其他"></el-option>
                      <el-option key="9" label="不详" value="不详"></el-option>
                    </el-select>
                  </el-form-item>
                </el-col>
                <el-col :span="12">
                  <el-form-item label="知情同意" >
                    <el-select v-model="editForm.infConsent" placeholder="请选择" style="width: 100%">
                      <el-option key="1" label="同意参加管理" value="同意参加管理"></el-option>
                      <el-option key="2" label="不同意参加管理" value="不同意参加管理"></el-option>
                    </el-select>
                  </el-form-item>
                </el-col>
              </el-row>
              </el-tab-pane>

            <el-tab-pane :label="`患者详情`" name="second">

              <el-row>
                <el-col>
                </el-col>
              </el-row>

              <el-row>
                <el-col>
                  <el-form-item label="初次发病时间">
                    <!--<el-date-picker v-model="editForm.firstOnsetTime" type="datetime" placeholder="选择日期时间" style="width: 100%"> </el-date-picker>-->
                    <el-date-picker type="date" placeholder="选择日期" v-model="editForm.firstOnsetTime" style="width: 100%;"></el-date-picker>
                  </el-form-item>
                </el-col>
              </el-row>


              <el-row>
                <el-col>
                  <el-form-item label="既往主要症状" >
                  <el-checkbox-group v-model="editForm.pastSymptoms" style=" width: 100%;">
                    <el-checkbox v-for="item in pastSymptoms" :key="item.value" :name="type" :label="item.label" :value="item.value"></el-checkbox>
                  </el-checkbox-group>
                  </el-form-item>
                </el-col>
              </el-row>

              <el-row>
                <el-col>
                  <el-form-item label="既往关锁情况" >
                    <el-radio-group v-model="editForm.pastLocking">
                      <el-radio label="无关锁"></el-radio>
                      <el-radio label="关锁"></el-radio>
                      <el-radio label="关锁已解除"></el-radio>
                    </el-radio-group>
                  </el-form-item>
                </el-col>
              </el-row>

              <el-row>
                <el-col span="12">
                  <el-form-item label="门诊">
                      <el-select v-model="editForm.outService" placeholder="请选择" style="width:100%">
                        <el-option key="1" label="未治疗" value="未治疗"></el-option>
                        <el-option key="2" label="间断门诊治疗" value="间断门诊治疗"></el-option>
                        <el-option key="3" label="连续门诊治疗" value="连续门诊治疗"></el-option>
                      </el-select>
                  </el-form-item>
                </el-col>
                <el-col span="12">
                  <el-form-item label="首次抗精神药治疗时间">
                      <!--<el-date-picker v-model="editForm.firstAntipsyTreat" type="datetime" placeholder="选择日期时间" style="width: 100%"> </el-date-picker>-->
                      <el-date-picker type="date" placeholder="选择日期" v-model="editForm.firstAntipsyTreat" style="width: 100%;"></el-date-picker>
                  </el-form-item>
                </el-col>
              </el-row>

              <el-row>
                <el-col span="12">
                  <el-form-item label="曾住精神医院次数："  >
                    <el-input v-model="editForm.inHospital">
                      <template slot="append">次</template>
                    </el-input>
                  </el-form-item>
                </el-col>
                <el-col span="12">
                  <el-form-item label="诊断" >
                    <el-input v-model="editForm.diagnosis"></el-input>
                  </el-form-item>
                </el-col>
              </el-row>


              <el-row>
                <el-col span="12">
                  <el-form-item label="确诊医院">
                    <el-input v-model="editForm.hospitalDia"></el-input>
                  </el-form-item>
                </el-col>
                <el-col span="12">
                  <el-form-item label="确诊日期" >
                    <el-date-picker type="date" placeholder="选择日期" v-model="editForm.confiDate" style="width: 100%;"></el-date-picker>
                  </el-form-item>
                </el-col>
              </el-row>

              <el-row>
                <el-col>
                  <el-form-item label="最近一次治疗效果" >
                    <el-radio-group v-model="editForm.lastTreatEff">
                      <el-radio label="临床痊愈"></el-radio>
                      <el-radio label="好转"></el-radio>
                      <el-radio label="无变化"></el-radio>
                      <el-radio label="加重"></el-radio>
                    </el-radio-group>
                  </el-form-item>
                </el-col>
              </el-row>

              <el-row>
                <el-col>
                  <el-form-item label="危险行为">
                    <el-checkbox-group v-model="editForm.dangerBehav" style="width:100%">
                      <el-checkbox v-for="item in dangerBehav" :key="item.value" :name="type" :label="item.label" :value="item.value"></el-checkbox>
                    </el-checkbox-group>
                  </el-form-item>
                </el-col>
              </el-row>

              <el-row>
                <el-col>
                  <el-form-item label="经济状况" >
                    <el-radio-group v-model="editForm.stateEconomy">
                      <el-radio label="贫困，在当地平困线标准以下"></el-radio>
                      <el-radio label="非贫困"></el-radio>
                    </el-radio-group>
                  </el-form-item>
                </el-col>
              </el-row>

              </el-tab-pane>

            <el-tab-pane :label="`医生建议`" name="third">
              <el-row>
                <el-col>
                  <el-form-item label="专科医生的建议(如果有请记录)">
                    <el-input type="textarea" rows="5" v-model="editForm.speciaOpinion"></el-input>
                  </el-form-item>
                </el-col>
              </el-row>


              <el-row>
                <el-col :span="12">
                  <el-form-item label="填表日期">
                      <el-date-picker type="date" placeholder="选择日期" v-model="editForm.dateFill" style="width: 100%;"></el-date-picker>
                  </el-form-item>
                </el-col>
                <el-col :span="12">
                  <el-form-item label="医生签字">
                    <el-input v-model="editForm.docSign"></el-input>
                  </el-form-item>
                </el-col>
              </el-row>
            </el-tab-pane>

          </el-tabs>
        </div>
        </div>
      </el-form>
      <div slot="footer" class="dialog-footer" align="center">
        <el-button @click="conPrint()">打印</el-button>
        <el-button v-if="dialogStatus=='create'" type="primary" @click="createData">添加</el-button>
        <el-button v-else type="primary" @click="updateData" v-if="edit">修改</el-button>
        <el-button @click.native="dialogFormVisible=false">取消</el-button>
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
  } from '@/api/severeMentalSupplement'

  export default {
    data() {
      return {
        edit: true,
        activeName: 'first',
        tableHeight: window.innerHeight - 220,
        dangerBehav: [
          { value: '轻度滋事', label: '轻度滋事' },
          { value: '肇事', label: '肇事' },
          { value: '肇祸', label: '肇祸' },
          { value: '其他危险行为', label: '其他危险行为' },
          { value: '自杀', label: '自杀' },
          { value: '自杀未遂', label: '自杀未遂' },
          { value: '无', label: '无' }
        ],
        pastSymptoms: [
          { value: '幻觉', label: '幻觉' },
          { value: '交流困难', label: '交流困难' },
          { value: '猜疑', label: '猜疑' },
          { value: '喜怒无常', label: '喜怒无常' },
          { value: '行为怪异', label: '行为怪异' },
          { value: '兴奋话多', label: '兴奋话多' },
          { value: '伤人伤物', label: '伤人伤物' },
          { value: '悲观厌世', label: '悲观厌世' },
          { value: '无故外走', label: '无故外走' },
          { value: '自语自笑', label: '自语自笑' },
          { value: '孤僻懒散', label: '孤僻懒散' }
        ],
        dialogStatus: '',
        textMap: {
          update: '编辑',
          create: '添加',
          search: '查询',
          detail: '详细'
        },
        dialogFormVisible: false,
        dialogFormVisible2: false,
        filters: {
          name: '',
          guardianName: '',
          guardianAddre: '',
          guardPhone: '',
          commitPersonName: '',
          commitPersonNum: '',
          accountType: '',
          outService: '',
          firstOnsetTime: '',
          firstAntipsyTreat: '',
          firstOnsetTime_s: '',
          firstOnsetTime_e: ''
        },

        users: [],
        total: 0,
        page: 1,
        sels: [], // 列表选中列
        editFormRules: {
          name: [{ required: true, message: '请输入姓名', trigger: 'blur' }]
        },
        // 编辑界面数据
        editForm: {
          name: '',
          guardianName: '',
          relaPatients: '',
          guardianAddre: '',
          guardPhone: '',
          commitPersonName: '',
          commitPersonNum: '',
          accountType: '',
          employSituat: '',
          infConsent: '',
          firstOnsetTime: '',
          pastSymptoms: [],
          pastLocking: '',
          outService: '',
          firstAntipsyTreat: '',
          inHospital: '',
          diagnosis: '',
          hospitalDia: '',
          confiDate: '',
          lastTreatEff: '',
          dangerBehav: [],
          stateEconomy: '',
          speciaOpinion: '',
          dateFill: '',
          docSign: '',
          creater: '1',
          createDate: '',
          modifier: '1',
          modifyDate: ''
        },

        addFormVisible: false, // 新增界面是否显示
        addFormRules: {
          name: [{ required: true, message: '请输入姓名', trigger: 'blur' }]
        }
      }
    },
    methods: {
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
        str = str + '<div id="dataPanel" style="position:absolute;left:50%;margin-left:-397px;">\n' +
          '  <div id="topContent">\n' +
          '    <h2 style="width:794px;text-align:center"> 严重精神障碍患者个人信息补充</h2>  \n' +
          '    <span style="margin-left:34px;">姓名:{{name}}</span>\n' +
          '    <span style="margin-left:510px;">NO:{{id}}</span> \n' +
          '    </div>\n' +
          '  <table id="table"  border="1"  cellspacing="0" bordercolor="#000000" style="width:730px;margin-left:30px;margin-top:5px;border-collapse:collapse;"  width = "80%">\n' +
          '    <tbody>\n' +
          '      <tr>\n' +
          '        <td style="width: 20%;text-align:center">监护人姓名</td>\n' +
          '        <td colspan=\'2\'>{{guardianName}}</td>\n' +
          '        <td style="width: 20%;text-align:center">与患者关系</td>\n' +
          '        <td colspan=\'2\'>{{relaPatients}}</td>\n' +
          '      </tr>\n' +
          '\t  <tr>\n' +
          '        <td style="width: 20%;text-align:center">监护人地址</td>\n' +
          '        <td colspan=\'2\'>{{guardianAddre}}</td>\n' +
          '        <td style="width: 20%;text-align:center">监护人电话</td>\n' +
          '        <td colspan=\'2\'>{{guardPhone}}</td>\n' +
          '      </tr>\n' +
          '\t  <tr>\n' +
          '        <td style="width: 20%;text-align:center">辖区村(居)委会联系人</td>\n' +
          '        <td colspan=\'2\'>{{commitPersonName}}</td>\n' +
          '        <td style="width: 20%;text-align:center">辖区村(居)委会联系人电话</td>\n' +
          '        <td colspan=\'2\'>{{commitPersonNum}}</td>\n' +
          '      </tr>\n' +
          '\t  <tr>\n' +
          '        <td style="word-wrap:break-word;width: 20%;text-align:center">户别</td>\n' +
          '        <td colspan=\'5\'>{{accountType}}</td>\n' +
          '      </tr>\n' +
          '\t  <tr>\n' +
          '        <td style="word-wrap:break-word;width: 20%;text-align:center">就业情况</td>\n' +
          '        <td colspan=\'5\'>{{employSituat}}</td>\n' +
          '      </tr>\n' +
          '\t  <tr >\n' +
          '\t\t<td style="word-wrap:break-word;width: 20%;text-align:center" rowspan=3>知情同意</td>\n' +
          '\t\t<td colspan=\'5\'>{{infConsent}}</td>\n' +
          '\t  </tr>\n' +
          '\t  <tr >\n' +
          '\t\t<td colspan=\'5\'>签字</td>\n' +
          '\t  </tr>\n' +
          '\t  \n' +
          '\t  <tr >\n' +
          '\t\t<td colspan=\'5\'>签字时间：______年______月_____日</td>\n' +
          '\t  </tr>\n' +
          '\t  <tr>\n' +
          '        <td style="word-wrap:break-word;width: 20%;text-align:center">初次发病时间</td>\n' +
          '        <td colspan=\'5\'>{{firstOnsetTime}}</td>\n' +
          '      </tr>\n' +
          '\t  <tr>\n' +
          '        <td style="width: 20%;text-align:center">既往主要症状</td>\n' +
          '        <td colspan=\'5\' style="width: 20%;text-align:left">{{pastSymptoms}}</td>\n' +
          '      </tr>\n' +
          '\t  <tr>\n' +
          '        <td style="word-wrap:break-word;width: 20%;text-align:center">既往关锁情况</td>\n' +
          '        <td colspan=\'5\'>{{pastLocking}}</td>\n' +
          '      </tr>\n' +
          '\t</tbody>\n' +
          '\t<tbody>  \n' +
          '\t  <tr >\n' +
          '\t\t<td style="word-wrap:break-word;width: 20%;text-align:center" rowspan=3>既往治疗情况</td>\n' +
          '\t\t<td rowspan=2 style="word-wrap:break-word;width: 20%;text-align:center">门诊</td>\n' +
          '\t\t<td colspan=\'5\'>{{outService}}</td>\n' +
          '\t  </tr>\n' +
          '\t  <tr>\n' +
          '\t\t<td colspan=\'5\' >首次抗精神药治疗时间:{{firstAntipsyTreat}}</td>\n' +
          '\t  </tr>\n' +
          '\t  <tr>\n' +
          '\t    <td style="word-wrap:break-word;width: 20%;text-align:center">住院</td>\n' +
          '\t\t<td colspan=\'5\'>曾住精神专科医院/综合医院精神专科次数：{{inHospital}}</td>\n' +
          '\t  </tr>\n' +
          '\t</tbody> \n' +
          '\t<tbody> \n' +
          '\t  <tr>\n' +
          '\t    <td style="word-wrap:break-word;width: 20%;text-align:center">目前诊断情况</td>\n' +
          '\t\t<td colspan=\'5\'>诊断:{{diagnosis}}     确诊医院:{{hospitalDia}}     确诊日期:{{confiDate}}</td>\n' +
          '\t  </tr>\n' +
          '\t  <tr>\n' +
          '        <td style="word-wrap:break-word;width: 20%;text-align:center">最近一次治疗效果</td>\n' +
          '        <td colspan=\'5\'>{{lastTreatEff}}</td>\n' +
          '      </tr>\n' +
          '\t  <tr>\n' +
          '        <td style="width: 20%;text-align:center">危险行为</td>\n' +
          '        <td colspan=\'5\' style="width: 20%;text-align:left"> {{dangerBehav}}</tr>\n' +
          '\t   <tr>\n' +
          '        <td style="word-wrap:break-word;width: 20%;text-align:center">经济状况</td>\n' +
          '        <td colspan=\'5\'>{{stateEconomy}}</td>\n' +
          '      </tr>\n' +
          '\t  <tr>\n' +
          '        <td style="word-wrap:break-word;width: 20%;text-align:center">专科医生的建议(如果有请记录)</td>\n' +
          '        <td colspan=\'4\'>{{speciaOpinion}}</td>\n' +
          '      </tr>\n' +
          '\t  <tr>\n' +
          '        <td style="width: 10%;text-align:center">填表日期</td>\n' +
          '        <td colspan=\'2\'>{{dateFill}}</td>\n' +
          '        <td style="width: 10%;text-align:center">医生签字</td>\n' +
          '        <td colspan=\'2\'>{{docSign}}</td>\n' +
          '      </tr>\n' +
          ' \n' +
          '    </tbody> \n' +
          '    \n' +
          '     \n' +
          '\n' +
          '  </table>\n' +
          '</div>'
        str = str.replace(/{{name}}/, this.editForm.name ? this.editForm.name : '')
        str = str.replace(/{{id}}/, this.editForm.id ? this.editForm.id : '')
        str = str.replace(/{{guardianName}}/, this.editForm.guardianName ? this.editForm.guardianName : '')
        str = str.replace(/{{relaPatients}}/, this.editForm.relaPatients ? this.editForm.relaPatients : '')
        str = str.replace(/{{guardianAddre}}/, this.editForm.guardianAddre ? this.editForm.guardianAddre : '')
        str = str.replace(/{{guardPhone}}/, this.editForm.guardPhone ? this.editForm.guardPhone : '')
        str = str.replace(/{{commitPersonName}}/, this.editForm.commitPersonName ? this.editForm.commitPersonName : '')
        str = str.replace(/{{commitPersonNum}}/, this.editForm.commitPersonNum ? this.editForm.commitPersonNum : '')
        str = str.replace(/{{accountType}}/, this.editForm.accountType ? this.editForm.accountType : '')
        str = str.replace(/{{employSituat}}/, this.editForm.employSituat ? this.editForm.employSituat : '')
        str = str.replace(/{{infConsent}}/, this.editForm.infConsent ? this.editForm.infConsent : '')
        str = str.replace(/{{firstOnsetTime}}/, this.editForm.firstOnsetTime ? this.editForm.firstOnsetTime : '')
        str = str.replace(/{{pastSymptoms}}/, this.editForm.pastSymptoms ? this.editForm.pastSymptoms : '')
        str = str.replace(/{{pastLocking}}/, this.editForm.pastLocking ? this.editForm.pastLocking : '')
        str = str.replace(/{{outService}}/, this.editForm.outService ? this.editForm.outService : '')
        str = str.replace(/{{firstAntipsyTreat}}/, this.editForm.firstAntipsyTreat ? this.editForm.firstAntipsyTreat : '')
        str = str.replace(/{{inHospital}}/, this.editForm.inHospital ? this.editForm.inHospital : '')
        str = str.replace(/{{diagnosis}}/, this.editForm.diagnosis ? this.editForm.diagnosis : '')
        str = str.replace(/{{hospitalDia}}/, this.editForm.hospitalDia ? this.editForm.hospitalDia : '')
        str = str.replace(/{{confiDate}}/, this.editForm.confiDate ? this.editForm.confiDate : '')
        str = str.replace(/{{lastTreatEff}}/, this.editForm.lastTreatEff ? this.editForm.lastTreatEff : '')
        str = str.replace(/{{dangerBehav}}/, this.editForm.dangerBehav ? this.editForm.dangerBehav : '')
        str = str.replace(/{{stateEconomy}}/, this.editForm.stateEconomy ? this.editForm.stateEconomy : '')
        str = str.replace(/{{speciaOpinion}}/, this.editForm.speciaOpinion ? this.editForm.speciaOpinion : '')
        str = str.replace(/{{dateFill}}/, this.editForm.dateFill ? this.editForm.dateFill : '')
        str = str.replace(/{{docSign}}/, this.editForm.inHospital ? this.editForm.inHospital : '')
        myWindow.document.write(str)
        // myWindow.document.write(content + style)
        myWindow.focus()
        myWindow.document.close()
        myWindow.print()
        return myWindow
      },
      conPrint() {
        const windows = this.printf(document.getElementById('print').innerHTML)
        windows.close()
      },

      handleCurrentChange(val) {
        this.page = val
        this.getUsers()
      },
      // 获取用户列表
      getUsers() {
        this.filters.firstOnsetTime =
          !this.filters.firstOnsetTime || this.filters.firstOnsetTime === ''
            ? null
            : util.formatDate.format(new Date(this.filters.firstOnsetTime), 'yyyy-MM-dd')
        this.filters.firstAntipsyTreat =
          !this.filters.firstAntipsyTreat || this.filters.firstAntipsyTreat === ''
            ? null
            : util.formatDate.format(new Date(this.filters.firstAntipsyTreat), 'yyyy-MM-dd')
        this.filters.firstOnsetTime_s =
          !this.filters.firstOnsetTime_s || this.filters.firstOnsetTime_s === ''
            ? null
            : util.formatDate.format(new Date(this.filters.firstOnsetTime_s), 'yyyy-MM-dd')
        this.filters.firstOnsetTime_e =
          !this.filters.firstOnsetTime_e || this.filters.firstOnsetTime_e === ''
            ? null
            : util.formatDate.format(new Date(this.filters.firstOnsetTime_e), 'yyyy-MM-dd')

        const para = {
          page: this.page,
          name: this.filters.name,
          guardianName: this.filters.guardianName,
          guardianAddre: this.filters.guardianAddre,
          guardPhone: this.filters.guardPhone,
          commitPersonName: this.filters.commitPersonName,
          commitPersonNum: this.filters.commitPersonNum,
          accountType: this.filters.accountType,
          outService: this.filters.outService,
          firstOnsetTime: this.filters.firstOnsetTime,
          firstAntipsyTreat: this.filters.firstAntipsyTreat,
          firstOnsetTime_s: this.filters.firstOnsetTime_s,
          firstOnsetTime_e: this.filters.firstOnsetTime_e
        }
        getUserListPage(para).then(res => {
          this.total = res.data.total
          this.users = res.data.severeMentalSupplement
          if (this.page > 1 && (this.page - 1) * 10 >= this.total) {
            this.page = this.page - 1
            this.getUsers()
          }
        })
        this.dialogFormVisible2 = false
        this.findForm = {
          name: '',
          guardianName: '',
          guardianAddre: '',
          guardPhone: '',
          commitPersonName: '',
          commitPersonNum: '',
          accountType: '',
          outService: '',
          firstOnsetTime: '',
          firstAntipsyTreat: '',
          firstOnsetTime_s: '',
          firstOnsetTime_e: ''
        }
      },
      // 删除
      handleDel(index, row) {
        this.$confirm('确认删除该记录吗?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
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
      handleRowChange(row, event) {
        this.dialogStatus = 'detail'
        this.dialogFormVisible = true
        this.editForm = Object.assign({}, row)
        this.edit = false
      },
      // 显示编辑界面
      handleEdit(index, row) {
        this.dialogStatus = 'update'
        this.dialogFormVisible = true
        this.editForm = Object.assign({}, row)
        this.editForm.dangerBehav = this.editForm.dangerBehav.split(',')
        this.editForm.pastSymptoms = this.editForm.pastSymptoms.split(',')
        this.edit = true
      },
      // 查询框
      findBtn() {
        this.dialogStatus = 'search'
        this.dialogFormVisible2 = true
        this.findForm = {
          name: '',
          guardianName: '',
          guardianAddre: '',
          guardPhone: '',
          commitPersonName: '',
          commitPersonNum: '',
          accountType: '',
          outService: '',
          firstOnsetTime: '',
          firstAntipsyTreat: '',
          firstOnsetTime_s: '',
          firstOnsetTime_e: ''
        }
      },
      // 查询
      searchs() {
        this.page = 1
        this.getUsers()
      },
      // 显示新增界面
      handleAdd() {
        this.dialogStatus = 'create'
        this.dialogFormVisible = true
        this.edit = false
        this.editForm = {
          name: '',
          guardianName: '',
          relaPatients: '',
          guardianAddre: '',
          guardPhone: '',
          commitPersonName: '',
          commitPersonNum: '',
          accountType: '',
          employSituat: '',
          infConsent: '',
          firstOnsetTime: '',
          pastSymptoms: [],
          pastLocking: '',
          outService: '',
          firstAntipsyTreat: '',
          inHospital: '',
          diagnosis: '',
          hospitalDia: '',
          confiDate: '',
          lastTreatEff: '',
          dangerBehav: [],
          stateEconomy: '',
          speciaOpinion: '',
          dateFill: '',
          docSign: '',
          creater: this.$store.getters.name,
          createDate: '',
          modifier: '111',
          modifyDate: ''
        }
      },
      // 编辑
      updateData() {
        this.$refs.editForm.validate(valid => {
          if (valid) {
            this.$confirm('确认提交吗？', '提示', {
              confirmButtonText: '确定',
              cancelButtonText: '取消',
              type: 'warning'
            })
              .then(() => {
                const para = Object.assign({}, this.editForm)
                para.modifier = this.$store.getters.name
                para.firstOnsetTime =
                  !para.firstOnsetTime || para.firstOnsetTime === ''
                    ? null
                    : util.formatDate.format(new Date(para.firstOnsetTime), 'yyyy-MM-dd')
                para.firstAntipsyTreat =
                  !para.firstAntipsyTreat || para.firstAntipsyTreat === ''
                    ? null
                    : util.formatDate.format(new Date(para.firstAntipsyTreat), 'yyyy-MM-dd ')
                para.confiDate =
                  !para.confiDate || para.confiDate === ''
                    ? null
                    : util.formatDate.format(new Date(para.confiDate), 'yyyy-MM-dd')
                para.dateFill =
                  !para.dateFill || para.dateFill === ''
                    ? null
                    : util.formatDate.format(new Date(para.dateFill), 'yyyy-MM-dd')
                para.modifyDate = new Date()
                para.modifyDate =
                  !para.modifyDate || para.modifyDate === ''
                    ? ''
                    : util.formatDate.format(new Date(para.modifyDate), 'yyyy-MM-dd')
                para.dangerBehav = ''
                for (const itm of this.editForm.dangerBehav) {
                  if (itm !== '') {
                    para.dangerBehav += itm
                    para.dangerBehav += ','
                  }
                }
                para.dangerBehav = para.dangerBehav.substr(0, para.dangerBehav.length - 1)
                para.pastSymptoms = ''
                for (const itm of this.editForm.pastSymptoms) {
                  if (itm !== '') {
                    para.pastSymptoms += itm
                    para.pastSymptoms += ','
                  }
                }
                para.pastSymptoms = para.pastSymptoms.substr(0, para.pastSymptoms.length - 1)
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
            this.$confirm('确认提交吗？', '提示', {
              confirmButtonText: '确定',
              cancelButtonText: '取消',
              type: 'warning'
            })
              .then(() => {
                this.editForm.id = (parseInt(Math.random() * 100)).toString() // mock a id
                const para = Object.assign({}, this.editForm)
                console.log(para)
                para.firstOnsetTime =
                  !para.firstOnsetTime || para.firstOnsetTime === ''
                    ? null
                    : util.formatDate.format(new Date(para.firstOnsetTime), 'yyyy-MM-dd')
                para.firstAntipsyTreat =
                  !para.firstAntipsyTreat || para.firstAntipsyTreat === ''
                    ? null
                    : util.formatDate.format(new Date(para.firstAntipsyTreat), 'yyyy-MM-dd')
                para.confiDate =
                  !para.confiDate || para.confiDate === ''
                    ? null
                    : util.formatDate.format(new Date(para.confiDate), 'yyyy-MM-dd')
                para.dateFill =
                  !para.dateFill || para.dateFill === ''
                    ? null
                    : util.formatDate.format(new Date(para.dateFill), 'yyyy-MM-dd')
                para.createDate = new Date()
                para.createDate =
                  !para.createDate || para.createDate === ''
                    ? ''
                    : util.formatDate.format(new Date(para.createDate), 'yyyy-MM-dd')
                para.dangerBehav = ''
                for (const itm of this.editForm.dangerBehav) {
                  if (itm !== '') {
                    para.dangerBehav += itm
                    para.dangerBehav += ','
                  }
                }
                para.dangerBehav = para.dangerBehav.substr(0, para.dangerBehav.length - 1)
                para.pastSymptoms = ''
                for (const itm of this.editForm.pastSymptoms) {
                  if (itm !== '') {
                    para.pastSymptoms += itm
                    para.pastSymptoms += ','
                  }
                }
                para.pastSymptoms = para.pastSymptoms.substr(0, para.pastSymptoms.length - 1)
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

