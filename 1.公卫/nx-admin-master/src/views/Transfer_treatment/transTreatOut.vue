<template>
  <section class="app-container">

    <!--顶部工具栏部分-->
    <el-col :span="24" class="toolbar" style="padding-bottom: 0px;" >
      <el-form :inline="true" :model="filters" @submit.native.prevent :rules="TopbarRules">

        <el-form-item  prop="name">
          <el-input v-model.number="filters.name" placeholder="请输入姓名查询" clearable></el-input>
        </el-form-item>

        <el-form-item >
          <el-button type="primary" v-on:click="search" class="searchbtn"
                     icon="el-icon-search">查询
          </el-button>
        </el-form-item>

        <el-form-item >
          <el-button type="primary" v-on:click="findbtn" class="searchbtn"
                     icon="el-icon-search">高级查询
          </el-button>
        </el-form-item>

        <el-form-item >
          <el-button type="primary" v-if="buttons.TTO_C" @click="handleAdd"  icon="el-icon-circle-plus-outline">新增转诊</el-button>
        </el-form-item>


      </el-form>
    </el-col>

    <!--点击高级查询按钮跳出的小页面-->
    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible2" :close-on-click-modal="false" center>
      <el-form :model="editForm" label-width="100px" :rules="editFormRules" ref="editForm">

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
            <el-date-picker type="date" placeholder="选择日期" v-model="filters.transtreatTime_s"
                            style="width: 100%;"></el-date-picker>
          </el-col>
          <el-col class="line" :span="1" align="center">-</el-col>
          <el-col :span="5">
            <el-date-picker type="date" placeholder="选择日期" v-model="filters.transtreatTime_e"
                            style="width: 100%;"></el-date-picker>
          </el-col>
        </el-form-item>
      </el-form>


      <div slot="footer" class="dialog-footer" >
        <el-button type="primary" v-on:click="search" class="searchbtn" icon="el-icon-search" >查询</el-button>
      </div>

    </el-dialog>

    <!--列表-->
    <el-table :data="users" :height="tableHeight" border highlight-current-row @selection-change="selsChange"  @row-dblclick="handleRowChange">

      <el-table-column type="index"  label="序号"  width="60">
      </el-table-column>
      <el-table-column prop="name" label="姓名" width="120">
      </el-table-column>
      <el-table-column prop="sex" label="性别" width="60" :formatter="formatSex">
      </el-table-column>
      <el-table-column prop="idNumber" label="身份证号码" width="180">
      </el-table-column>
      <el-table-column prop="address" label="家庭地址" min-width="160">
      </el-table-column>
      <el-table-column prop="facilityAndDepart" label="转诊机构科室" min-width="160">
      </el-table-column>
      <el-table-column prop="transTime" label="转诊时间" min-width="160">
      </el-table-column>
      <el-table-column prop="transState" label="转诊状态" width="120">
      </el-table-column>

      <el-table-column label="操作" width="200">
        <template slot-scope="scope">
          <a size="14px" v-if="buttons.TTO_E" @click="handleEdit(scope.$index, scope.row) " style="color:#4DB2EE;margin-right:20px;">编辑</a>
          <a size="14px" @click="handleAddLog(scope.$index, scope.row) " style="color:#4DB2EE;margin-right:20px;">查看日志</a>
          <a size="14px" v-if="buttons.TTO_D" @click="handleDel(scope.$index, scope.row) " style="color:#FB4141">删除</a>
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
      <el-form :model="editForm" label-width="90px" :rules="editFormRules" ref="editForm">
        <EasyScrollbar>
          <div class="item" v-bind:style="{ height: tablebodyHeight + 'px'}">
            <el-tabs v-model="activeName">
              <table id="table"  border="1"  cellspacing="0" bordercolor="#dddddd" style="margin-left:10px;margin-right:10px;border-collapse:collapse;width: 95%;">
                <tbody>
                  <tr>
                    <td style="width:120px;word-wrap:break-word;text-align:center;" class="pad"><span class="red">*</span>身份证号码</td>
                    <td colspan="4" style="text-align:center;padding-right: 0px;" class="pad10">
                      <el-form-item label="" prop="idNumber" label-width="0">
                        <el-input v-model="editForm.idNumber" placeholder="请输入18位或者15位身份证号码" clearable minlength="15" style="width: 100%" v-on:input="inputFunc1"></el-input>
                      </el-form-item>
                    </td>
                    <td style="width:80px;word-wrap:break-word;text-align:center;" ><span class="red">*</span>姓名</td>
                    <td colspan="5" style=";word-wrap:break-word;text-align:center;padding-right: 0px;" class="pad10">
                    <el-form-item label="" prop="name" label-width="0">
                      <el-input v-model="editForm.name"  style="width: 100%"  disabled ></el-input>
                    </el-form-item>
                    </td>
                  </tr>


                  <tr>
                    <td style="word-wrap:break-word;text-align:center">性别</td>
                    <td colspan="4" style="text-align:center;padding-right: 0px;" class="pad10">
                      <el-form-item label="" label-width="0" prop="sex">
                        <el-radio-group v-model="editForm.sex" style="width: 100%" >
                          <el-radio label="男"></el-radio>
                          <el-radio label="女"></el-radio>
                          <el-radio label="未知性别"></el-radio>
                        </el-radio-group>
                      </el-form-item>
                    </td>
                    <td style="width:80px;word-wrap:break-word;text-align:center">年龄</td>
                    <td colspan="5" style="text-align:center;width:180px;padding-right: 0px;" class="pad10">
                      <el-form-item label="" label-width="0" prop="age">
                        <el-input v-model="editForm.age" style="width: 100%"> <template slot="append">(岁)</template> </el-input >
                      </el-form-item>
                    </td>
                  </tr>


                  <tr>
                    <td style="width:80px;word-wrap:break-word;text-align:center">住址</td>
                    <td colspan="9" style="text-align:center;padding-right: 0px;" class="pad10">
                      <el-form-item label="" label-width="0px">
                        <el-input v-model="editForm.address" style="width: 100%"></el-input>
                      </el-form-item>
                    </td>
                  </tr>



                  <tr>
                    <td style="width:100px;word-wrap:break-word;text-align:center">患者联系方式</td>
                    <td colspan="4" style="text-align:center;padding-right: 0px;" class="pad10">
                      <el-form-item label="" label-width="0">
                        <el-input  v-model="editForm.contact" style="width: 100%"></el-input>
                      </el-form-item>
                    </td>
                    <td style="word-wrap:break-word;text-align:center">转诊类型</td>
                    <td colspan="3" style="text-align:center;padding-right: 0px;" class="pad10">
                      <el-form-item label="" label-width="0px">
                        <el-radio-group v-model="editForm.transType" style="width: 100%" disabled>
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
                        <el-input type="textarea" rows="3" v-model="editForm.prediagnosis" style="width: 100%"></el-input>
                      </el-form-item>
                    </td>
                  </tr>

                  <tr>
                    <td style="word-wrap:break-word;text-align:center">病情摘要</td>
                    <td colspan="10" style="text-align:center;padding-bottom: 5px;padding-top: 5px;padding-right: 0px;" class="pad10">
                      <el-form-item label="" label-width="0">
                        <el-input type="textarea" rows="3" v-model="editForm.illnessDigest"></el-input>
                      </el-form-item>
                    </td>
                  </tr>

                  <tr>
                    <td style="word-wrap:break-word;text-align:center">转诊原因</td>
                    <td colspan="10" style="text-align:center;padding-bottom: 5px;padding-top: 5px;padding-right: 0px;" class="pad10">
                      <el-form-item label="" label-width="0">
                        <el-input type="textarea" rows="3" v-model="editForm.referralReason"></el-input>
                      </el-form-item>
                    </td>
                  </tr>


                  <tr>
                    <td style="word-wrap:break-word;text-align:center"><span class="red">*</span>机构及科室</td>
                    <td colspan="4" style="text-align:center;padding-right: 0px;" class="pad10">
                      <el-form-item label="" label-width="0" prop="facilityAndDepart">
                        <el-cascader :options="options"
                                     placeholder="请选择机构及科室"  v-model="editForm.facilityAndDepart"  id="houseAddress"  style="width: 100%"></el-cascader>
                      </el-form-item>
                    </td>



                    <td style="word-wrap:break-word;text-align:center">转诊时间</td>
                    <td colspan="5" style="text-align:center;padding-right: 0px;" class="pad10">
                      <el-form-item label="" label-width="0">
                        <el-date-picker type="date" placeholder="选择日期时间"  v-model="editForm.transTime" style="width: 100%;"></el-date-picker>
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
        <el-button v-if="dialogStatus=='create'" type="primary" @click="check">添加</el-button>
        <el-button v-else type="primary" @click="updateData" v-if="edit">修改</el-button>
        <el-button @click.native="dialogFormVisible=false">取消</el-button>
      </div>
    </el-dialog>



    <!--编辑界面(修改)-->
    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisibleEDIT" :close-on-click-modal="false" center>
      <el-form  :model="editForm" label-width="90px" :rules="editFormRules" ref="editForm">

        <el-row>
          <el-col span="12">
            <el-form-item  label="姓名" prop="name" label-width="100px">
              <el-input disabled  v-model="editForm.name"></el-input>
            </el-form-item>
          </el-col>


          <el-col span="12">
            <el-form-item label="身份证号码" prop="idNumber" label-width="100px">
              <el-input disabled v-model="editForm.idNumber"></el-input>
            </el-form-item>
          </el-col>
        </el-row>



        <el-row>
          <el-col span="12">
            <el-form-item label="性别" label-width="100px" >
              <el-radio-group v-model="editForm.sex" >
                <el-radio label=" 男"></el-radio>
                <el-radio label=" 女"></el-radio>
              </el-radio-group>
            </el-form-item>
          </el-col>


          <el-col span="12">
            <el-form-item label="年龄" label-width="100px">
              <el-input v-model="editForm.age" > <template slot="append">(岁)</template> </el-input >
            </el-form-item>
          </el-col>
        </el-row>


        <el-row>
          <el-col>
            <el-form-item label="住址" label-width="100px">
              <el-input v-model="editForm.address"></el-input>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col>
            <el-form-item label="患者联系方式" label-width="100px">
              <el-input  v-model="editForm.contact"></el-input>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col>
            <el-form-item label="转诊类型" label-width="100px">
              <el-radio-group v-model="editForm.transType">
                <el-radio label="类型1"></el-radio>
                <el-radio label="类型2"></el-radio>
              </el-radio-group>
            </el-form-item>
          </el-col>
        </el-row>



        <el-row>
          <el-col>
            <el-form-item label="转诊前诊断">
              <el-input type="textarea" rows="5" v-model="editForm.prediagnosis"></el-input>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col>
            <el-form-item label="病情摘要">
              <el-input type="textarea" rows="5" v-model="editForm.illnessDigest"></el-input>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col>
            <el-form-item label="转诊原因">
              <el-input type="textarea" rows="5" v-model="editForm.referralReason"></el-input>
            </el-form-item>
          </el-col>
        </el-row>


        <el-row>
          <el-col span="12">
            <el-form-item label="机构及科室" >
              <el-cascader :options="options"
                           placeholder="请选择机构及科室"  v-model="facilityAndDepart" style="width: 250px"></el-cascader>
            </el-form-item>
          </el-col>



          <el-col :span="12" style="width: 350px">
            <el-form-item label="转诊时间">
              <el-date-picker type="datetime" placeholder="选择日期时间" default-time="12:00:00" v-model="editForm.transTime" style="width: 100%;"></el-date-picker>
            </el-form-item>
          </el-col>
        </el-row>




        <el-form-item label="转诊状态" label-width="100px">
          <el-radio-group v-model="editForm.transState" style="width: 1100px">
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


      </el-form>
      <!--修改界面下方按钮-->
      <div slot="footer" class="dialog-footer">
        <el-button @click.native="dialogFormVisibleEDIT=false">取消</el-button>
        <el-button v-if="edit" v-else type="primary" @click="updateData" >修改</el-button>
        <el-button  v-if="edit" v-else type="primary" @click="createData">新增修改</el-button>
      </div>
    </el-dialog>


    <!--日志界面-->
    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisibleLog" :close-on-click-modal="false" center>
      <el-form :model="editForm" label-width="90px" :rules="editFormRules" ref="editForm">

        <!--日志记录列表-->
        <el-table :data="users2" :height="tableHeight" border highlight-current-row @selection-change="selsChange" style="width: 100%;"  @row-dblclick="handleRowChange">

          <el-table-column prop="modifyDate"  label="修改时间"  width="160" :formatter="timeFormat">
          </el-table-column>
          <el-table-column prop="transState" label="转诊状态" min-width="160">
          </el-table-column>
          <el-table-column prop="name" label="姓名" width="120">
          </el-table-column>
          <el-table-column prop="sex" label="性别" width="120" :formatter="formatSex">
          </el-table-column>
          <el-table-column prop="age" label="年龄" width="120">
          </el-table-column>
          <el-table-column prop="idNumber" label="身份证号码" width="230">
          </el-table-column>
          <el-table-column prop="address" label="家庭地址" min-width="160">
          </el-table-column>
          <el-table-column prop="facilityAndDepart" label="转诊机构科室" min-width="160">
          </el-table-column>
          <el-table-column prop="prediagnosis" label="转诊前诊断" min-width="160">
          </el-table-column>
          <el-table-column prop="illnessDigest" label="病情摘要" min-width="160">
          </el-table-column>
          <el-table-column prop="referralReason" label="转诊原因" min-width="160">
          </el-table-column>
          <el-table-column prop="transType" label="转诊类型" min-width="160">
          </el-table-column>
          <el-table-column prop="transTime" label="转诊时间" min-width="160" :formatter="timeFormat">
          </el-table-column>
        </el-table>
      </el-form>
    </el-dialog>

  </section>
</template>


<script>
  import util from '@/utils/table.js'
  import {
    getList,
    removeData,
    updateData,
    addData
  } from '@/api/transTreat'
  import {
    addData2,
    getList2
  } from '@/api/transTreatlog'
  import { getList as getPerson } from '@/api/Perinfor'
  export default {
    data: function() {
      return {
        buttons: {
          TTO_C: false,
          TTO_E: false,
          TTO_D: false
        },
        tablebodyHeight: window.innerHeight - 210,
        edit: true,
        houseAddress: [],
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
        value1: '',
        value2: '',
        value3: '',
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
            }, {
              value: '烧伤科',
              label: '烧伤科'
            }, {
              value: '产科',
              label: '产科'
            }, {
              value: '血透室',
              label: '血透室'
            }, {
              value: '重症监护室',
              label: '重症监护室'
            }, {
              value: '心内科',
              label: '心内科'
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
            }, {
              value: '重症监护室',
              label: '重症监护室'
            }, {
              value: '心内科',
              label: '心内科'
            }]
          }
        ],
        dialogStatus: '',
        tableHeight: window.innerHeight - 260,
        activeName: 'first',
        facilityAndDepart: [],
        textMap: {
          update: '转诊转出编辑',
          create: '转诊转出添加',
          search: '转诊转出查询',
          find: '转诊转出高级查找',
          detail: '转诊转出详情',
          transtreatLog: '转诊转出日志详情'
        },
        dialogFormVisible: false,
        dialogFormVisible2: false,
        dialogFormVisibleEDIT: false,
        dialogFormVisibleLog: false,
        filters: {
          name: '',
          idNumber: '',
          contact: '',
          transtreatTime_s: '',
          transtreatTime_e: '',
          id: null
        },
        users: [],
        users2: [],
        total: 0,
        page: 1,
        sels: [],
        editFormRules: {
          facilityAndDepart: [
            { required: true, message: ' ', trigger: 'blur' }
          ],
          idNumber: [
            { required: true, message: ' ', trigger: 'blur' },
            { pattern: /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/, message: ' ' }
          ]
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
          facilityAndDepart: [],
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
          : util.formatDate.format(new Date(date), 'yyyy-MM-dd')
      return date
    },
    handleRowChange(row, event) {
      this.dialogStatus = 'detail'
      this.facilityAndDepart = []
      this.dialogFormVisible = true
      this.editForm = Object.assign({}, row)
      this.editForm.facilityAndDepart = this.editForm.facilityAndDepart.split('/')
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
      this.filters.transtreatTime_s =
        !this.filters.transtreatTime_s || this.filters.transtreatTime_s === ''
          ? ''
          : util.formatDate.format(new Date(this.filters.transtreatTime_s), 'yyyy-MM-dd hh:mm:ss')

      this.filters.transtreatTime_e =
        !this.filters.transtreatTime_e || this.filters.transtreatTime_e === ''
          ? ''
          : util.formatDate.format(new Date(this.filters.transtreatTime_e), 'yyyy-MM-dd hh:mm:ss')
      const para = {
        page: this.page,
        size: 20,
        filters: { id: this.filters.id, name: this.filters.name, idNumber: this.filters.idNumber,
          contact: this.filters.contact, transtreatTime_s: this.filters.transtreatTime_s,
          transtreatTime_e: this.filters.transtreatTime_e }
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
        transtreatTime_s: '',
        transtreatTime_e: '',
        id: null
      }
    },
    inputFunc1() {
      const para = {
        page: 1,
        size: 200,
        filters: { name: '', peopleId: this.editForm.idNumber, cardNumber: '' }
      }
      if (para.filters !== '') {
        setTimeout(() => {
          this.$nextTick(function() {
            getPerson(para).then(res => {
              const user = res.detailModelList
              if (user.length !== 0) {
                this.editForm.name = user[0].name
                this.editForm.sex = user[0].sex
                this.active = 2
              }
            })
          })
        }, 200)
      }
    },
    getUsers2(index, row) {
      this.filters.transtreatTime_s =
        !this.filters.transtreatTime_s || this.filters.transtreatTime_s === ''
          ? ''
          : util.formatDate.format(new Date(this.filters.transtreatTime_s), 'yyyy-MM-dd hh:mm:ss')

      this.filters.transtreatTime_e =
        !this.filters.transtreatTime_e || this.filters.transtreatTime_e === ''
          ? ''
          : util.formatDate.format(new Date(this.filters.transtreatTime_e), 'yyyy-MM-dd hh:mm:ss')

      const para = {
        page: this.page,
        size: 20,
        filters: { id: this.filters.id, name: this.filters.name, idNumber: row.idNumber,
          contact: this.filters.contact, transtreatinTime_s: this.filters.transtreatinTime_s,
          transtreatinTime_e: this.filters.transtreatinTime_e }
      }
      getList2(para).then(res => {
        this.total = res.totalCount
        this.users2 = res.detailModelList
        if (this.page > 1 && (this.page - 1) * 20 >= this.total) {
          this.page = this.page - 1
          this.getUsers2()
        }
      })
      this.filters = {
        name: this.filters.name,
        idNumber: '',
        contact: '',
        transtreatTime_s: '',
        transtreatTime_e: '',
        id: null
      }
    },
    handleEdit(index, row) {
      this.dialogStatus = 'update'
      this.dialogFormVisible = true
      this.editForm = Object.assign({}, row)
      this.edit = true
      this.editForm.facilityAndDepart = this.editForm.facilityAndDepart.split('/')
    },
    handleAdd() {
      this.facilityAndDepart = []
      this.dialogStatus = 'create'
      this.dialogFormVisible = true
      this.edit = false
      this.editForm = {
        id: null,
        idNumber: '',
        name: '',
        sex: '',
        age: null,
        address: '',
        prediagnosis: '',
        illnessDigest: '',
        referralReason: '',
        facilityAndDepart: [],
        transTime: new Date(),
        transType: '',
        contact: '',
        transState: '待接收'
      }
    },
    handleAddLog(index, row) {
      this.page = 1
      this.getUsers2(index, row)
      this.facilityAndDepart = []
      this.dialogStatus = 'transtreatLog'
      this.dialogFormVisibleLog = true
      this.edit = false
    },
    findbtn() {
      this.dialogStatus = 'find'
      this.dialogFormVisible2 = true
      this.filters = {
        id: null,
        idNumber: '',
        name: '',
        contact: '',
        transtreatTime_s: '',
        transtreatTime_e: ''

      }
    },
    updateData() {
      this.$refs.editForm.validate(valid => {
        if (valid) {
          this.$confirm('确认提交吗？', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消' })
            .then(() => {
              const para = Object.assign({}, this.editForm)
              para.transTime =
                  !para.transTime || para.transTime === ''
                    ? ''
                    : util.formatDate.format(new Date(para.transTime), 'yyyy-MM-dd hh:mm:ss')
              para.facilityAndDepart = ''
              for (const itm of this.editForm.facilityAndDepart) {
                para.facilityAndDepart += itm
                para.facilityAndDepart += '/'
              }
              para.facilityAndDepart = para.facilityAndDepart.substr(0, para.facilityAndDepart.length - 1)
              para.modifyDate = new Date()
              para.modifyDate =
                !para.modifyDate || para.modifyDate === ''
                  ? null
                  : util.formatDate.format(new Date(para.modifyDate), 'yyyy-MM-dd hh:mm:ss')
              updateData(para).then(res => {
                this.$message({
                  message: '提交成功',
                  type: 'success'
                })
                this.$refs['editForm'].resetFields()
                this.dialogFormVisibleEDIT = false
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
    check() {
      this.$refs.editForm.validate(valid => {
        if (!valid) {
          this.$nextTick(function() {
            this.$el.querySelector('#facilityAndDepart').scrollIntoView()
          })
        }
        if (valid) {
          const para = {
            page: 1,
            size: 200,
            filters: { name: '', peopleId: this.editForm.idNumber, cardNumber: '' }
          }
          getPerson(para).then(res => {
            if (res.detailModelList.length !== 0) {
              this.createData()
            } else {
              this.$message.error('该身份证还未建立个人档案')
            }
          })
        }
      })
    },
    createData: function() {
      this.$refs.editForm.validate(valid => {
        if (valid) {
          this.$confirm('确认提交吗？', '提示', {})
            .then(() => {
              this.editForm.id = (parseInt(Math.random() * 100)).toString()
              const para = Object.assign({}, this.editForm)
              console.log(para)
              para.transState = '待接收'
              para.transTime =
                  !para.transTime || para.transTime === ''
                    ? ''
                    : util.formatDate.format(new Date(para.transTime), 'yyyy-MM-dd hh:mm:ss')
              para.facilityAndDepart = ''
              for (const itm of this.editForm.facilityAndDepart) {
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
                this.dialogFormVisibleEDIT = false
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
    selsChange(sels) {
      this.sels = sels
    },
    handleDel(index, row) {
      this.$confirm('确认删除该记录吗?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      })
        .then(() => {
          const para = row.id
          removeData(para).then(res => {
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
      this.buttons = this.getButton(this.buttons, this.$store.getters.buttons)
      this.getUsers()
    }
  }
</script>

<style scoped>
  .el-table--fit {
    margin-top: 2px;
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
  .red{
    color:red;
  }
</style>
