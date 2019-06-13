<template>
  <section class="app-container">

    <!--顶部工具栏部分-->
    <el-col :span="24" class="toolbar" style="padding-bottom: 0px;" >
      <el-form :inline="true" :model="filters" @submit.native.prevent :rules="TopbarRules">

        <el-form-item  prop="name">
          <el-input v-model.number="filters.name" placeholder="请输入姓名..." clearable></el-input>
        </el-form-item>

        <el-form-item >
          <el-button type="primary"  v-on:click="search" class="searchbtn"
                     icon="el-icon-search">查询
          </el-button>
        </el-form-item>

        <el-form-item >
          <el-button type="primary" v-on:click="findbtn" class="searchbtn"
                     icon="el-icon-search">高级查询
          </el-button>
        </el-form-item>


      </el-form>
    </el-col>

    <!--点击高级查询按钮跳出的小页面-->
    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible2" :close-on-click-modal="false" center>
      <el-form :model="editForm" label-width="100px"  ref="editForm">

        <el-row>
          <el-col>
            <el-form-item label="身份证号码">
              <el-input v-model="filters.idNumber" placeholder="身份证号码" ></el-input>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col>
            <el-form-item label="联系方式">
              <el-input v-model="filters.contact" placeholder="联系方式" ></el-input>
            </el-form-item>
          </el-col>
        </el-row>


        <el-form-item label="转诊时间">
          <el-col :span="5" >
            <el-date-picker type="date" placeholder="选择日期" v-model="filters.transtreatinTime_s"
                            style="width: 100%;"></el-date-picker>
          </el-col>
          <el-col class="line" :span="1" align="center">-</el-col>
          <el-col :span="5">
            <el-date-picker type="date" placeholder="选择日期" v-model="filters.transtreatinTime_e"
                            style="width: 100%;"></el-date-picker>
          </el-col>
        </el-form-item>
      </el-form>


      <div slot="footer" class="dialog-footer" >
        <el-button type="primary" v-on:click="search" class="searchbtn" icon="el-icon-search" >查询</el-button>
      </div>

    </el-dialog>

    <!--列表-->
    <el-table :data="users" :height="tableHeight" border highlight-current-row @selection-change="selsChange" style="width: 100%;"  @row-dblclick="handleRowChange">

      <el-table-column type="index"  label="序号"  width="60">
      </el-table-column>
      <el-table-column prop="transState" label="转诊状态" width="120">
      </el-table-column>
      <el-table-column prop="facilityAndDepart" label="转入机构科室" min-width="160">
      </el-table-column>
      <el-table-column prop="name" label="姓名" width="120">
      </el-table-column>
      <el-table-column prop="sex" label="性别" width="60" :formatter="formatSex">
      </el-table-column>
      <el-table-column prop="age" label="年龄" width="120">
      </el-table-column>
      <el-table-column prop="idNumber" label="身份证号码" width="230">
      </el-table-column>
      <el-table-column prop="address" label="家庭地址" min-width="160">
      </el-table-column>
      <el-table-column prop="transTime" label="转诊时间" min-width="160">
      </el-table-column>

      <el-table-column label="操作" width="150">
        <template slot-scope="scope">
          <a size="14px" v-if="buttons.TTI_E" @click="handleEdit(scope.$index, scope.row) " style="color:#4DB2EE;margin-right:20px;">审核</a>
        </template>
      </el-table-column>
    </el-table>

    <!--工具条-->
    <el-col :span="24" class="toolbar">
      <el-pagination layout="prev, pager, next" @current-change="handleCurrentChange" :page-size="20" :total="total" style="float:right;">
      </el-pagination>
    </el-col>

    <!--编辑界面(新增)-->
    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible" :close-on-click-modal="false" center>
      <div id="print">
        <el-form :model="editForm" label-width="90px"  ref="editForm">
          <EasyScrollbar>
            <div class="item" v-bind:style="{ height: tablebodyHeight + 'px'}">
              <el-tabs v-model="activeName">
                <table id="table"  border="1"  cellspacing="0" bordercolor="#dddddd" style="margin-left:10px;margin-right:10px;border-collapse:collapse;width: 95%;">
                  <tbody>
                  <tr>
                    <td style="width:120px;word-wrap:break-word;text-align:center;" class="pad10">身份证号码</td>
                    <td colspan="4" style="text-align:center;padding-right: 0px;" class="pad10">
                      <el-form-item label="" prop="idNumber" label-width="0">
                        <el-input  disabled  v-model="editForm.idNumber" style="width: 100%" v-on:input="inputFunc1"></el-input>
                      </el-form-item>
                    </td>
                    <td style="width:80px;word-wrap:break-word;text-align:center;" >姓名</td>
                    <td colspan="5" style=";word-wrap:break-word;text-align:center;padding-right: 0px;" class="pad10">
                      <el-form-item label="" prop="name" label-width="0">
                        <el-input disabled v-model="editForm.name"  style="width: 100%"  disabled ></el-input>
                      </el-form-item>
                    </td>
                  </tr>


                  <tr>
                    <td style="word-wrap:break-word;text-align:center">性别</td>
                    <td colspan="4" style="text-align:center;padding-right: 0px;" class="pad10">
                      <el-form-item label="" label-width="0" >
                        <el-radio-group disabled v-model="editForm.sex" style="width: 100%" >
                          <el-radio label="男"></el-radio>
                          <el-radio label="女"></el-radio>
                          <el-radio label="未知性别"></el-radio>
                        </el-radio-group>
                      </el-form-item>
                    </td>
                    <td style="width:80px;word-wrap:break-word;text-align:center">年龄</td>
                    <td colspan="5" style="text-align:center;width:180px;;padding-right: 0px;" class="pad10">
                      <el-form-item label="" label-width="0" prop="age">
                        <el-input disabled v-model="editForm.age" style="width: 100%"> <template slot="append">(岁)</template> </el-input >
                      </el-form-item>
                    </td>
                  </tr>


                  <tr>
                    <td style="width:80px;word-wrap:break-word;text-align:center">住址</td>
                    <td colspan="9" style="text-align:center;padding-right: 0px;" class="pad10">
                      <el-form-item label="" label-width="0px">
                        <el-input disabled v-model="editForm.address" style="width: 100%"></el-input>
                      </el-form-item>
                    </td>
                  </tr>



                  <tr>
                    <td style="width:100px;word-wrap:break-word;text-align:center">患者联系方式</td>
                    <td colspan="4" style="text-align:center;padding-right: 0px;" class="pad10">
                      <el-form-item label="" label-width="0">
                        <el-input  disabled v-model="editForm.contact" style="width: 100%"></el-input>
                      </el-form-item>
                    </td>
                    <td style="word-wrap:break-word;text-align:center">转诊类型</td>
                    <td colspan="3" style="text-align:center;padding-right: 0px;" class="pad10">
                      <el-form-item label="" label-width="0px">
                        <el-radio-group  v-model="editForm.transType" style="width: 100%">
                          <el-radio label="类型1"></el-radio>
                          <el-radio label="类型2"></el-radio>
                        </el-radio-group>
                      </el-form-item>
                    </td>
                  </tr>



                  <tr>
                    <td style="word-wrap:break-word;text-align:center">转诊前诊断</td>
                    <td colspan="10" style="text-align:center;padding-bottom: 5px;padding-top: 5px;padding-right: 0px;" class="pad10">
                      <el-form-item label="" label-width="0">
                        <el-input disabled type="textarea" rows="3" v-model="editForm.prediagnosis" style="width: 100%"></el-input>
                      </el-form-item>
                    </td>
                  </tr>

                  <tr>
                    <td style="word-wrap:break-word;text-align:center">病情摘要</td>
                    <td colspan="10" style="text-align:center;padding-bottom: 5px;padding-top: 5px;padding-right: 0px;" class="pad10">
                      <el-form-item label="" label-width="0">
                        <el-input disabled type="textarea" rows="3" v-model="editForm.illnessDigest"></el-input>
                      </el-form-item>
                    </td>
                  </tr>

                  <tr>
                    <td style="word-wrap:break-word;text-align:center">转诊原因</td>
                    <td colspan="10" style="text-align:center;padding-bottom: 5px;padding-top: 5px;padding-right: 0px;" class="pad10">
                      <el-form-item label="" label-width="0">
                        <el-input disabled type="textarea" rows="3" v-model="editForm.referralReason"></el-input>
                      </el-form-item>
                    </td>
                  </tr>


                  <tr>
                    <td style="word-wrap:break-word;text-align:center">机构及科室</td>
                    <td colspan="4" style="text-align:center;padding-right: 0px;" class="pad10">
                      <el-form-item label="" label-width="0">
                        <el-cascader :options="options" disabled
                                     placeholder="请选择机构及科室"  v-model="facilityAndDepart" style="width: 100%"></el-cascader>
                      </el-form-item>
                    </td>



                    <td style="word-wrap:break-word;text-align:center">转诊时间</td>
                    <td colspan="5" style="text-align:center;padding-right: 0px;" class="pad10">
                      <el-form-item label="" label-width="0">
                        <el-date-picker disabled type="date" placeholder="选择日期时间"  v-model="editForm.transTime" style="width: 100%;"></el-date-picker>
                      </el-form-item>
                    </td>
                  </tr>

                  <tr>
                    <td style="word-wrap:break-word;text-align:center">转诊状态</td>
                    <td colspan="9" style="text-align:left"  class="pad20">
                      <el-form-item label="" label-width="0">
                        <el-radio-group v-model="editForm.transState" style="width:  100%">
                          <table>
                            <tr>
                              <td>  <el-radio label="待接收"></el-radio></td>
                              <td>  <el-radio label="拒绝"></el-radio></td>
                              <td>  <el-radio label="待就诊"></el-radio></td>
                              <td>  <el-radio label="已撤回"></el-radio></td>
                              <td>  <el-radio label="已就诊"></el-radio></td>
                            </tr>
                            <tr>
                              <td>  <el-radio label="待院内审核"></el-radio></td>
                              <td>  <el-radio label="院内审核失败"></el-radio></td>
                              <td>  <el-radio label="康复出院"></el-radio></td>
                              <td>  <el-radio label="自主择医"></el-radio></td>
                              <td>  <el-radio label="未到诊"></el-radio></td>
                            </tr>
                          </table>
                        </el-radio-group>
                      </el-form-item>
                    </td>
                  </tr>
                  </tbody>
                </table>
              </el-tabs>
            </div>
          </EasyScrollbar>
        </el-form>
      </div>  <!--打印范围end-->
      <!--编辑界面下方按钮-->
      <div slot="footer" class="dialog-footer">
        <el-button @click="conprint()">打印</el-button>
        <el-button @click.native="dialogFormVisible=false">取消</el-button>
        <el-button v-if="edit" v-else type="primary" @click="updateData" >审核确认</el-button>
      </div>
    </el-dialog>




  </section>
</template>


<script>
  import util from '@/utils/table.js'
  import {
    getList,
    updateData,
    addData
  } from '@/api/transTreatin'
  import {
    addData2
  } from '@/api/transTreatlog'
  export default {
    data: function() {
      return {
        buttons: {
          TTI_E: false
        },
        edit: true,
        pickerOptions1: {
          shortcuts: [{
            text: '今天',
            onClick(picker) {
              picker.$emit('pick', new Date())
            }
          }, {
            text: '昨天',
            onClick(picker) {
              const date = new Date()
              date.setTime(date.getTime() - 3600 * 1000 * 24)
              picker.$emit('pick', date)
            }
          }, {
            text: '一周前',
            onClick(picker) {
              const date = new Date()
              date.setTime(date.getTime() - 3600 * 1000 * 24 * 7)
              picker.$emit('pick', date)
            }
          }]
        },
        options: [
          {
            value: '北京积水潭医院',
            label: '北京积水潭医院',
            children: [{
              value: '儿科',
              label: '儿科'
            }, {
              value: '内科',
              label: '内科'
            }, {
              value: '外科',
              label: '外科'
            }, {
              value: '妇科',
              label: '妇科'
            }]
          },
          {
            value: '北京天坛医院',
            label: '北京天坛医院',
            children: [{
              value: '儿科',
              label: '儿科'
            }, {
              value: '骨科',
              label: '骨科'
            }, {
              value: '泌尿科',
              label: '泌尿科'
            }, {
              value: '耳鼻喉科',
              label: '耳鼻喉科'
            }]
          }
        ],
        tableHeight: window.innerHeight - 210,
        activeName: 'first',
        facilityAndDepart: [],
        textMap: {
          update: '转诊转入审核',
          create: '转诊转入添加',
          search: '转诊转入查询',
          find: '转诊转入高级查找',
          detail: '转诊转入详情'
        },
        dialogFormVisible: false,
        dialogFormVisible2: false,
        filters: {
          name: '',
          idNumber: '',
          contact: '',
          transtreatinTime_s: '',
          transtreatinTime_e: '',
          id: null
        },
        users: [],
        total: 500,
        page: 1,
        sels: [],
        editFormRules: {
          name: [{ required: true, message: '请输入姓名', trigger: 'blur' }],
          idNumber: [{ required: true, message: '请输入身份证号码', trigger: 'blur' }],
          facilityAndDepart: [{ required: true, message: '请选择转诊机构及科室', trigger: 'blur' }]
        },
        editForm: {
          idNumber: '',
          name: '',
          sex: '',
          age: '',
          address: '',
          prediagnosis: '',
          illnessDigest: '',
          referralReason: '',
          facilityAndDepart: '',
          transTime: '',
          transType: '',
          contact: '',
          transState: ''
        },
        addFormVisible: false,
        addFormRules: {
          name: [{ required: true, message: '请输入姓名', trigger: 'blur' }]
        }
      }
    },
    methods:
      {
        handleCurrentChange(val) {
          this.page = val
          this.getUsers()
        },
        timeFormat: function(row, column) {
          let date = row[column.property]
          date =
            !date || date === ''
              ? ''
              : util.formatDate.format(new Date(date), 'yyyy-MM-dd hh:mm:ss')
          return date
        },
        handleRowChange(row, event) {
          this.dialogStatus = 'detail'
          this.dialogFormVisible = true
          this.editForm = Object.assign({}, row)
          this.facilityAndDepart = this.editForm.facilityAndDepart.split('/')
          this.edit = false
        },
        printf(content, w = null, h = null) {
          const dualScreenLeft = window.screenLeft !== undefined ? window.screenLeft : screen.left
          const dualScreenTop = window.screenTop !== undefined ? window.screenTop : screen.top

          const width = window.innerWidth ? window.innerWidth : document.documentElement.clientWidth ? document.documentElement.clientWidth : screen.width
          const height = window.innerHeight ? window.innerHeight : document.documentElement.clientHeight ? document.documentElement.clientHeight : screen.height
          w = +w === 0 ? width : w
          h = +h === 0 ? height : h
          const left = ((width / 2) - (w / 2)) + dualScreenLeft
          const top = ((height / 2) - (h / 2)) + dualScreenTop
          const myWindow = window.open('', '打印', 'toolbar=no, location=no, directories=no, status=no, menubar=no, scrollbars=no, resizable=yes, copyhistory=no, width=' + w + ', height=' + h + ', top=' + top + ', left=' + left)
          const style = "<style type='text/css'>table.gridtable {font-family: verdana,arial,sans-serif;font-size:24px;color:#333333;border-width: 1px;border-color: #666666;border-collapse: collapse;}table.gridtable th {border-width: 1px;padding: 8px;border-style: solid;border-color: #666666;background-color: #dedede;}table.gridtable td {border-width: 1px;padding: 8px;border-style: solid;border-color: #666666;background-color: #ffffff;}</style>"
          var str = style
          str = str +
            '<div id="dataPanel" style="position:absolute;left:50%;margin-left:-397px;">\n' +
            '\t<div id="topContent">\n' +
            '\t\t<h2 style="width:794px;text-align:center">转诊转出信息表</h2>  \n' +
            '\t</div>\n' +
            '\t<table id="table"  border="1"  cellspacing="0" bordercolor="#000000" style="width:730px;height=100px;margin-left:30px;margin-top:5px;border-collapse:collapse;"  >\n' +
            '\t<tbody>\n' +
            '\t\n' +
            '\t\t<tr>\n' +
            '\t\t\t<td colspan="2" style="width: 15%;text-align:center">身份证号码</td>\n' +
            '\t\t\t<td style="width: 35%;text-align:center">{{idNumber}}</td>\n' +
            '\t\t\t<td colspan="2" style="width: 15%;text-align:center">姓名</td>\n' +
            '\t\t\t<td style="width: 35%;text-align:center">{{name}}</td>\n' +
            '\t\t</tr>\n' +
            '\t\t\n' +
            '\t\t<tr>\n' +
            '\t\t\t<td colspan="2" style="width: 15%;text-align:center">性别</td>\n' +
            '\t\t\t<td style="width: 35%;text-align:center">{{sex}}</td>\n' +
            '\t\t\t<td colspan="2" style="width: 15%;text-align:center">年龄</td>\n' +
            '\t\t\t<td style="width: 35%;text-align:center">{{age}}</td>\n' +
            '\t\t</tr>\n' +
            '\t\t\n' +
            '\t\t\n' +
            '\t\t<tr>\n' +
            '\t\t\t<td colspan="2" style="width: 60%;text-align:center">家庭地址</td>\n' +
            '\t\t\t<td colspan="4" style="width: 35%;text-align:center">{{address}}</td>\n' +
            '\t\t</tr>\n' +
            '\n' +
            '\t\t\n' +
            '\t\t<tr>\n' +
            '\t\t\t<td colspan="2" style="width: 60%;text-align:center">转诊前诊断</td>\n' +
            '\t\t\t<td colspan="4" style="width: 35%;text-align:center">{{prediagnosis}}</td>\n' +
            '\t\t</tr>\n' +
            '\t\t\n' +
            '\t<tr>\n' +
            '\t\t\t<td colspan="2" style="width: 60%;text-align:center">病情摘要</td>\n' +
            '\t\t\t<td colspan="4" style="width: 35%;text-align:center">{{illnessDigest}}</td>\n' +
            '\t\t</tr>\n' +
            '\t\n' +
            '\t\t\t<tr>\n' +
            '\t\t\t<td colspan="2" style="width: 60%;text-align:center">转诊原因</td>\n' +
            '\t\t\t<td colspan="4" style="width: 35%;text-align:center">{{referralReason}}</td>\n' +
            '\t\t</tr>\n' +
            '\t\t\n' +
            '\t\t<tr>\n' +
            '\t\t\t<td colspan="2" style="width: 60%;text-align:center">转往的医疗机构、科室</td>\n' +
            '\t\t\t<td colspan="4" style="width: 35%;text-align:center">{{facilityAndDepart}}</td>\n' +
            '\t\t</tr>\n' +
            '\t\t\n' +
            '\t\t<tr>\n' +
            '\t\n' +
            '\t\t<tr>\n' +
            '\t\t\t<td colspan="2" style="width: 60%;text-align:center">转诊时间</td>\n' +
            '\t\t\t<td colspan="4" style="width: 35%;text-align:center">{{transTime}}</td>\n' +
            '\t\t</tr>\n' +
            '\t\t\n' +
            '\t\t\t<tr>\n' +
            '\t\t\t<td colspan="2" style="width: 60%;text-align:center">转诊类型</td>\n' +
            '\t\t\t<td colspan="4" style="width: 35%;text-align:center">{{transType}}</td>\n' +
            '\t\t</tr>\n' +
            '\t\t\n' +
            '\t\t\n' +
            '\t\t<tr>\n' +
            '\t\t\t<td colspan="2" style="width: 15%;text-align:center">患者联系方式</td>\n' +
            '\t\t\t<td style="width: 35%;text-align:center">{{contact}}</td>\n' +
            '\t\t\t<td colspan="2" style="width: 15%;text-align:center">转诊状态</td>\n' +
            '\t\t\t<td style="width: 35%;text-align:center">{{transState}}</td>\n' +
            '\t\t</tr>\n' +
            '\t\t\n' +
            '\t\t</tbody>\n' +
            '\t</table>\n' +
            '</div>'
          str = str.replace(/{{idNumber}}/, this.editForm.idNumber ? this.editForm.idNumber : '')
          str = str.replace(/{{name}}/, this.editForm.name ? this.editForm.name : '')
          str = str.replace(/{{sex}}/, this.editForm.sex ? this.editForm.sex : '')
          str = str.replace(/{{age}}/, this.editForm.age ? this.editForm.age : '')
          str = str.replace(/{{address}}/, this.editForm.address ? this.editForm.address : '')
          str = str.replace(/{{prediagnosis}}/, this.editForm.prediagnosis ? this.editForm.prediagnosis : '')
          str = str.replace(/{{illnessDigest}}/, this.editForm.illnessDigest ? this.editForm.illnessDigest : '')
          str = str.replace(/{{referralReason}}/, this.editForm.referralReason ? this.editForm.referralReason : '')
          str = str.replace(/{{facilityAndDepart}}/, this.editForm.facilityAndDepart ? this.editForm.facilityAndDepart : '')
          str = str.replace(/{{transTime}}/, this.editForm.transTime ? this.editForm.transTime : '')
          str = str.replace(/{{transType}}/, this.editForm.transType ? this.editForm.transType : '')
          str = str.replace(/{{contact}}/, this.editForm.contact ? this.editForm.contact : '')
          str = str.replace(/{{transState}}/, this.editForm.transState ? this.editForm.transState : '')
          myWindow.document.write(str)
          myWindow.focus()
          myWindow.document.close()
          myWindow.print()
          return myWindow
        },
        conprint() {
          const windows = this.printf(document.getElementById('print').innerHTML)
          windows.close()
        },
        search() {
          this.page = 1
          this.getUsers()
          this.dialogFormVisible2 = false
        },
        getUsers() {
          this.filters.transtreatinTime_s =
            !this.filters.transtreatinTime_s || this.filters.transtreatinTime_s === ''
              ? ''
              : util.formatDate.format(new Date(this.filters.transtreatinTime_s), 'yyyy-MM-dd')

          this.filters.transtreatinTime_e =
            !this.filters.transtreatinTime_e || this.filters.transtreatinTime_e === ''
              ? ''
              : util.formatDate.format(new Date(this.filters.transtreatinTime_e), 'yyyy-MM-dd')
          const para = {
            page: this.page,
            size: 20,
            filters: { id: this.filters.id, name: this.filters.name, idNumber: this.filters.idNumber,
              contact: this.filters.contact, transtreatinTime_s: this.filters.transtreatinTime_s,
              transtreatinTime_e: this.filters.transtreatinTime_e }
          }
          getList(para).then(res => {
            this.total = res.totalCount
            this.users = res.detailModelList
            if (this.page > 1 && (this.page - 1) * 20 >= this.total) {
              this.page = this.page - 1
              this.getUsers()
            }
          })
          this.filters = {
            name: this.filters.name,
            idNumber: '',
            contact: '',
            transtreatinTime_s: '',
            transtreatinTime_e: '',
            facilityAndDepart: [],
            id: null
          }
        },
        handleEdit(index, row) {
          this.facilityAndDepart = []
          this.dialogStatus = 'update'
          this.dialogFormVisible = true
          this.editForm = Object.assign({}, row)
          this.edit = true
          this.facilityAndDepart = this.editForm.facilityAndDepart.split('/')
          this.editForm.transType = '类型1'
        },
        findbtn() {
          this.dialogStatus = 'find'
          this.dialogFormVisible2 = true
          this.filters = {
            id: null,
            idNumber: '',
            name: '',
            contact: '',
            transtreatinTime_s: '',
            transtreatinTime_e: ''

          }
        },
        updateData() {
          this.$refs.editForm.validate(valid => {
            if (valid) {
              this.$confirm('确认提交吗？', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消'
              })
                .then(() => {
                  const para = Object.assign({}, this.editForm)
                  para.transTime =
                    !para.transTime || para.transTime === ''
                      ? ''
                      : util.formatDate.format(new Date(para.transTime), 'yyyy-MM-dd hh:mm:ss')
                  para.facilityAndDepart = ''
                  for (const itm of this.facilityAndDepart) {
                    para.facilityAndDepart += itm
                    para.facilityAndDepart += '/'
                  }
                  para.facilityAndDepart = para.facilityAndDepart.substr(0, para.facilityAndDepart.length - 1)
                  para.modifyTime = new Date()
                  para.modifyTime =
                    !para.modifyTime || para.modifyTime === ''
                      ? ''
                      : util.formatDate.format(new Date(para.modifyTime), 'yyyy-MM-dd hh:mm:ss')
                  updateData(para).then(res => {
                    this.$message({
                      message: '提交成功',
                      type: 'success'
                    })
                    this.$refs['editForm'].resetFields()
                    this.dialogFormVisible = false
                    this.getUsers()
                  })
                  addData2(para).then(res => {
                    this.$message({
                      message: '提交成功',
                      type: 'success'
                    })
                    this.$refs['editForm'].resetFields()
                  })
                })
                .catch(e => {
                  console.log(e)
                })
            }
          })
        },
        createData: function() {
          this.$refs.editForm.validate(valid => {
            if (valid) {
              this.$confirm('确认提交吗？', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消'
              })
                .then(() => {
                  this.editForm.id = (parseInt(Math.random() * 100)).toString()
                  const para = Object.assign({}, this.editForm)
                  console.log(para)

                  para.transTime =
                    !para.transTime || para.transTime === ''
                      ? ''
                      : util.formatDate.format(new Date(para.transTime), 'yyyy-MM-dd hh:mm:ss')
                  para.facilityAndDepart = ''
                  for (const itm of this.facilityAndDepart) {
                    para.facilityAndDepart += itm
                    para.facilityAndDepart += '/'
                  }
                  para.facilityAndDepart = para.facilityAndDepart.substr(0, para.facilityAndDepart.length - 1)
                  addData(para).then(res => {
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
                  console.log(e)
                })
            }
          })
        },
        selsChange(sels) {
          this.sels = sels
        }
      },
    mounted() {
      this.buttons = this.getButton(this.buttons, this.$store.getters.buttons)
      this.getUsers()
    }
  }
</script>
<style scoped>
  .pad{
    padding-top: 25px;
    padding-bottom: 25px;
  }
  .searchbtn{
    background-color:#4db2ee;height: 40px;border-color:transparent
  }
  td{
    height: 50px;
  }
  .pad10{
    padding-left: 10px;
    padding-right: 10px;
  }
  .pad20{
    padding-left: 20px;
  }
</style>
