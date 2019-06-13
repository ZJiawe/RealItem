<template>
  <section class="app-container">

    <el-col :span="24" class="toolbar" style="padding-bottom: 0px;">
      <el-form :inline="true" :model="filters" @submit.native.prevent>
        <el-form-item>
          <el-input v-model="filters.name" placeholder="姓名" clearable></el-input>
        </el-form-item>
        <el-form-item>
          <el-input v-model="filters.peopleId" placeholder="身份证号" auto-complete="off" clearable></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" v-on:click="search" style="background-color:#4db2ee;border-color:transparent;height:40px" icon="el-icon-search">查询</el-button>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" v-on:click="findbtn" style="background-color:#4db2ee;border-color:transparent;width: 120px;height:40px" icon="el-icon-search">高级查询</el-button>
        </el-form-item>
        <el-form-item>
          <el-button v-if="buttons.KPPV_C" type="primary" @click="handleAdd" style="height:40px" icon="el-icon-circle-plus-outline">新增</el-button>
        </el-form-item>
      </el-form>
    </el-col>

    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible2" :close-on-click-modal="false" center>
      <el-form :model="findForm" label-width="110px" :rules="findFormRules" ref="findForm">
        <el-row>
          <el-col :span="12">
            <el-form-item label="随访日期" style="margin-bottom: -20px;">
              <el-row>
                <el-col :span="12">
                  <el-date-picker type="date" placeholder="开始日期" v-model="findForm.date_s" style="width: 100%;"></el-date-picker>
                </el-col>
                <el-col :span="12">
                  <el-date-picker type="date" placeholder="结束日期" v-model="findForm.date_e" style="width: 100%;"></el-date-picker>
                </el-col>
              </el-row>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="出院日期"  style="margin-bottom: -20px;">
              <el-row>
                <el-col :span="12">
                  <el-date-picker type="date" placeholder="开始日期" v-model="findForm.leaveHospitale_s" style="width: 100%;"></el-date-picker>
                </el-col>
                <el-col :span="12">
                  <el-date-picker type="date" placeholder="结束日期" v-model="findForm.leaveHospitale_e" style="width: 100%;"></el-date-picker>
                </el-col>
              </el-row>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="机构及科室">
              <el-input v-model="findForm.insandDep" clearable></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="随访医生签名">
              <el-input v-model="findForm.doctor" clearable></el-input>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" v-on:click="search" style="background-color:#4db2ee;border-color:transparent;height:40px" icon="el-icon-search">查询</el-button>
      </div>
    </el-dialog>
    <!--编辑-->
    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible" :close-on-click-modal="false" center>
      <div id="print">
        <el-form :model="editForm" label-width="110px" :rules="editFormRules" ref="editForm">
          <EasyScrollbar>
            <div class="item" v-bind:style="{ height: tablebodyHeight + 'px'}">
              <el-tabs v-model="activeName">
                <table id="table"  border="1"  cellspacing="0" bordercolor="#dddddd" style="font-size:14px;margin-left:10px;margin-right:30px;border-collapse:collapse;">
                  <tbody style="color:#323232;">
                  <tr v-if="dialogStatus==='detail'">
                    <td style="word-wrap:break-word;text-align:center;width:120px;">
                      创建人
                    </td>
                    <td colspan="3" class="pad10">
                      <el-form-item label="" label-width="0px">
                        <el-input v-model="editForm.creator" style="width: 100%" v-if="dialogStatus==='detail'" readonly></el-input>
                      </el-form-item>
                    </td>
                    <td style="word-wrap:break-word;text-align:center;width:100px;">
                      创建时间
                    </td>
                    <td colspan="3" class="pad10">
                      <el-form-item label="" label-width="0px" v-if="dialogStatus==='detail'">
                        <el-date-picker type="date" placeholder="选择日期" v-model="editForm.createDate" style="width: 100%" v-if="dialogStatus==='detail'" readonly></el-date-picker>
                      </el-form-item>
                    </td>
                  </tr>
                  <tr v-if="dialogStatus==='detail'">
                    <td style="word-wrap:break-word;text-align:center;width:100px;">
                      修改人
                    </td>
                    <td colspan="3" class="pad10">
                      <el-form-item label="" label-width="0px" v-if="dialogStatus==='detail'">
                        <el-input v-model="editForm.modifier" style="width: 100%" v-if="dialogStatus==='detail'" readonly></el-input>
                      </el-form-item>
                    </td>
                    <td style="word-wrap:break-word;text-align:center;width:100px;">
                      修改时间
                    </td>
                    <td colspan="3" class="pad10">
                      <el-form-item label="" label-width="0px" v-if="dialogStatus==='detail'">
                        <el-date-picker type="date" placeholder="选择日期" v-model="editForm.modifyDate" style="width: 100%" v-if="dialogStatus==='detail'" readonly></el-date-picker>
                      </el-form-item>
                    </td>
                  </tr>
                  <tr>
                    <td colspan="1" style="width:140px;text-align:center;">
                      <span>*</span>身份证号
                    </td>
                    <td colspan="2" class="pad10" style="width:320px;text-align:left;padding-right: 0px;">
                      <el-form-item label="" label-width="0px" prop="peopleId">
                        <el-input v-model="editForm.peopleId" style="width: 100%;" v-on:input="inputFunc" placeholder="请输入身份证" id="peopleId"></el-input>
                      </el-form-item>
                    </td>
                    <td colspan="1" style="width:140px;text-align:center;">
                      <span>*</span>姓名
                    </td>
                    <td colspan="3" class="pad10" style="width:330px;text-align:left;padding-right: 0px;">
                      <el-form-item label="" label-width="0px" prop="name" ref="name">
                        <el-input  v-model="editForm.name" style="width: 100%;" disabled="false"></el-input>
                      </el-form-item>
                    </td>
                  </tr>
                  <tr>
                    <td style="word-wrap:break-word;text-align:center;width:140px;">
                      <el-form-item label="ID" v-if="false">
                        <el-input v-model="editForm.id"></el-input>
                      </el-form-item>
                      <span>*</span>区域
                    </td>
                    <td colspan="2" class="pad10" style="text-align:left;padding-right: 0px;">
                      <el-form-item label="" label-width="0px"  prop="area">
                        <el-cascader :options="options" v-model="editForm.area" style="width: 100%;" disabled="false" change-on-select></el-cascader>
                      </el-form-item>
                    </td>
                    <td colspan="1" style="text-align:center;width:140px;">
                      随访日期
                    </td>
                    <td colspan="3" class="pad10" style="text-align:left;padding-right: 0px;">
                      <el-form-item label="" label-width="0px">
                        <el-date-picker type="date" placeholder="选择日期" v-model="editForm.date" style="width: 100%;"></el-date-picker>
                      </el-form-item>
                    </td>
                  </tr>
                  <tr>
                    <td style="text-align:center">体温</td>
                    <td colspan="2" class="pad10">
                      <el-input v-model="editForm.temperature" >
                        <template slot="append">℃</template>
                      </el-input>
                    </td>
                    <td style="word-wrap:break-word;text-align:center;width:140px;">分娩日期</td>
                    <td colspan="3" class="pad10">
                      <el-date-picker type="date" placeholder="选择日期" v-model="editForm.childBirth" style="width: 100%;"></el-date-picker>
                    </td>
                  </tr>
                  <tr>
                    <td style="word-wrap:break-word;text-align:center;width: 140px;">出院日期</td>
                    <td colspan="2" class="pad10">
                      <el-date-picker type="date" placeholder="选择日期" v-model="editForm.leaveHospitale" style="width: 100%;"></el-date-picker>
                    </td>
                    <td style="word-wrap:break-word;text-align:center">温度</td>
                    <td colspan="3" style="text-align:center;" class="pad10">
                      <el-input v-model="editForm.temperature">
                        <template slot="append">℃</template>
                      </el-input>
                    </td>
                  </tr>
                  <tr>
                    <td style="word-wrap:break-word;text-align:center;">一般健康状况</td>
                    <td colspan="2" class="pad10">
                      <el-input v-model="editForm.healthy"></el-input>
                    </td>
                    <td style="word-wrap:break-word;text-align:center">一般心理状况</td>
                    <td colspan="3" class="pad10">
                      <el-input v-model="editForm.psychology"></el-input>
                    </td>
                  </tr>
                  <tr>
                    <td colspan="1"style="word-wrap:break-word;text-align:center;">血压</td>
                    <td colspan="7" class="pad10">
                      <el-input v-model="editForm.blood">
                        <template slot="append">mmHg</template>
                      </el-input>
                    </td>
                  </tr>
                  <tr>
                    <td colspan="1" style="word-wrap:break-word;text-align:center;">乳房</td>
                    <td colspan="7"  style="text-align:left;" class="pad10">
                      <el-radio-group v-model="editForm.breast">
                        <el-radio label="未见异常"></el-radio>
                        <el-radio label="异常"></el-radio>
                      </el-radio-group>
                    </td>
                  </tr>
                  <tr>
                    <td style="word-wrap:break-word;text-align:center">恶露</td>
                    <td colspan="7" style="text-align:left" class="pad10">
                      <el-radio-group v-model="editForm.lochia">
                        <el-radio label="未见异常"></el-radio>
                        <el-radio label="异常"></el-radio>
                      </el-radio-group>
                    </td>
                  </tr>
                  <tr>
                    <td style="word-wrap:break-word;text-align:center">伤口</td>
                    <td colspan="7" style="text-align:left" class="pad10">
                      <el-radio-group v-model="editForm.wound">
                        <el-radio label="未见异常"></el-radio>
                        <el-radio label="异常"></el-radio>
                      </el-radio-group>
                    </td>
                  </tr>
                  <tr>
                    <td style="word-wrap:break-word;text-align:center">子宫</td>
                    <td colspan="2" style="text-align:left" class="pad10">
                      <el-radio-group v-model="editForm.uterus">
                        <el-radio label="未见异常"></el-radio>
                        <el-radio label="异常"></el-radio>
                      </el-radio-group>
                    </td>
                    <td colspan="1" style="word-wrap:break-word;text-align:center">分类</td>
                    <td colspan="2" style="text-align:left">
                      <el-radio-group v-model="editForm.classify" class="pad10">
                        <el-radio label="未见异常"></el-radio>
                        <el-radio label="异常"></el-radio>
                      </el-radio-group>
                    </td>
                  </tr>
                  <tr>
                    <td style="word-wrap:break-word;text-align:center;width:90px;">指导</td>
                    <td colspan="7" class="pad20">
                      <el-checkbox-group v-model="editForm.guidance" style="width:100%">
                        <table>
                          <tr>
                            <td style="padding-right: 20px"><el-checkbox label="心理健康" name="type"></el-checkbox></td>
                            <td style="padding-right: 20px"><el-checkbox label="性保健和避孕" name="type"></el-checkbox></td>
                            <td style="padding-right: 20px"><el-checkbox label="婴儿喂养" name="type"></el-checkbox></td>
                            <td style="padding-right: 20px"><el-checkbox label="产妇营养" name="type"></el-checkbox></td>
                            <td style="padding-right: 20px"><el-checkbox label="其他" name="type"></el-checkbox></td>
                          </tr>
                        </table>
                        <!--<el-checkbox v-for="item in guidance" :key="item.value" :name="type" :label="item.label" :value="item.value"></el-checkbox>-->
                      </el-checkbox-group>
                    </td>
                  </tr>
                  <tr>
                    <td colspan="1" style="word-wrap:break-word;text-align:center">其他</td>
                    <td colspan="7" class="pad10">
                      <el-input v-model="editForm.other" style=""></el-input>
                    </td>
                  </tr>
                  <tr>
                    <td colspan="1" style="word-wrap:break-word;text-align:center">处理</td>
                    <td colspan="7" class="pad10" style="text-align:left;padding-right: 5px;">
                      <table style="width:100%;">
                        <tr>
                          <td>
                            <el-radio-group v-model="editForm.referral">
                              <el-radio class="radio" label="无"></el-radio>
                              <el-radio class="radio" label="有"></el-radio>
                            </el-radio-group>
                          </td>
                          <td>
                            <table style="width: 100%;">
                              <tr>
                                <td>原因</td>
                                <td><el-input v-model="editForm.reason" ></el-input></td>
                                <td style="text-align:center;">机构及科室</td>
                                <td><el-input v-model="editForm.insandDep" ></el-input></td>
                              </tr>
                            </table>
                          </td>
                        </tr>
                      </table>

                    </td>
                  </tr>
                  <tr>
                    <td style="text-align:center">随访医生签名</td>
                    <td colspan="7" class="pad10">
                      <el-input v-model="editForm.doctor"></el-input>
                    </td>
                  </tr>
                  </tbody>
                </table>
              </el-tabs>
            </div>
          </EasyScrollbar>
        </el-form>
      </div>
      <div slot="footer" class="dialog-footer">
        <el-button style="height:40px" @click="conprint()">打印</el-button>
        <el-button style="height:40px" v-if="dialogStatus=='create'" type="primary" @click="check">添加</el-button>
        <el-button style="height:40px" v-else type="primary" v-if="edit" @click="check">新增修改</el-button>
        <el-button style="height:40px" v-else type="primary" @click="updateData" v-if="edit">修改</el-button>
        <el-button style="height:40px" @click.native="dialogFormVisible=false">取消</el-button>
      </div>
    </el-dialog>

    <!--列表-->
    <el-table :data="users" :height="tableHeight" highlight-current-row @selection-change="selsChange" border style="width: 100%;" @row-dblclick="handleRowChange">
      <el-table-column type="index" label="序号" width="70">
      </el-table-column>
      <el-table-column prop="id" v-if='false' label="编号" width="80" style="text-align:center">
      </el-table-column>
      <el-table-column prop="name" label="姓名" width="80" style="text-align:center">
      </el-table-column>
      <el-table-column prop="peopleId" label="身份证" width="180">
      </el-table-column>
      <el-table-column prop="date" label="随访日期" width="120" style="text-align:center" v-if='false'>
      </el-table-column>
      <el-table-column prop="childBirth"  v-if='false' label="分娩日期" width="120">
      </el-table-column>
      <el-table-column prop="leaveHospitale" label="出院日期" width="120">
      </el-table-column>
      <el-table-column prop="healthy" v-if='false' label="一般健康状况" min-width="120">
      </el-table-column>
      <el-table-column prop="psychology" v-if='false' label="一般心理状况" min-width="120">
      </el-table-column>
      <el-table-column prop="blood" v-if='false' label="血压(mmHg)" min-width="120">
      </el-table-column>
      <el-table-column prop="breast" v-if='false' label="乳房" min-width="80">
      </el-table-column>
      <el-table-column prop="lochia" v-if='false' label="恶露" min-width="80">
      </el-table-column>
      <el-table-column prop="uterus" v-if='false' label="子宫" min-width="80">
      </el-table-column>
      <el-table-column prop="wound" v-if='false' label="伤口" min-width="80">
      </el-table-column>
      <el-table-column prop="other" v-if='false' label="其他" min-width="120">
      </el-table-column>
      <el-table-column prop="classify" v-if='false' label="分类" min-width="120">
      </el-table-column>
      <el-table-column prop="guidance" v-if='false' label="指导" min-width="120">
      </el-table-column>
      <el-table-column prop="handle" label="处理" min-width="80">
      </el-table-column>
      <el-table-column prop="reason" label="原因" min-width="120" v-if='false'>
      </el-table-column>
      <el-table-column prop="insandDep" label="机构及科室" min-width="120" v-if='false'>
      </el-table-column>
      <el-table-column prop="doctor" label="随访医生" min-width="120">
      </el-table-column>
      <el-table-column prop="creator" label="创建人" width="120">
      </el-table-column>
      <el-table-column prop="createDate" label="创建时间" width="120" :formatter="timeFormat">
      </el-table-column>
      <el-table-column prop="modifier" label="修改人" width="120">
      </el-table-column>
      <el-table-column prop="modifyDate" label="修改时间" width="120" :formatter="timeFormat">
      </el-table-column>
      <el-table-column label="操作" width="200">
        <template slot-scope="scope">
          <a size="14px" @click="goto(scope.$index, scope.row)" style="color:#4DB2EE;margin-right:20px;">查看档案</a>
          <a size="14px" v-if="buttons.KPPV_E" @click="handleEdit(scope.$index, scope.row)" style="color:#4DB2EE;margin-right:20px;">编辑</a>
          <a size="14px" v-if="buttons.KPPV_D" @click="handleDel(scope.$index, scope.row)" style="color:#FB4141">删除</a>
        </template>
      </el-table-column>
    </el-table>

    <!--工具条-->
    <el-col :span="24" class="toolbar">
      <el-pagination layout="prev, pager, next" :current-page.sync="page" @current-change="handleCurrentChange" :page-size="20" :total="total" style="float:right;">
      </el-pagination>
    </el-col>
  </section>
</template>
<script>
  import util from '@/utils/table.js'
  import {
    getList,
    removeData,
    batchRemoveData,
    updateData,
    addData
  } from '@/api/postpartumVisitFortytwo'
  import city from '@/api/city.js'
  import { checkData as getPerson } from '@/api/firstPrenatalDiagnosis'
  import {
    getList as getSearchMemberPage
  } from '@/api/Perinfor'
  import {
    getList as getFollow,
    updateData as editFollow,
    addData as addFollow
  } from '@/api/follow'

  export default {
    data() {
      return {
        buttons: {
          KPPV_C: false,
          KPPV_E: false,
          KPPV_D: false
        },
        edit: true,
        activeName: 'first',
        tableHeight: window.innerHeight - 210,
        tablebodyHeight: window.innerHeight - 320,
        options: city.city(),
        options2: city.occurPlace(),
        guidance: [
          { value: '心理健康', label: '心理健康' },
          { value: '性保健和避孕', label: '性保健和避孕' },
          { value: '婴儿喂养', label: '婴儿喂养' },
          { value: '产妇营养', label: '产妇营养' },
          { value: '其他', label: '其他' }
        ],
        dialogStatus: '',
        area: [],
        textMap: {
          update: '编辑',
          create: '添加',
          search: '查询',
          detail: '详情'
        },
        dialogFormVisible: false,
        dialogFormVisible2: false,
        filters: {
          name: '',
          peopleId: '',
          date: null
        },
        ceshi: '',
        users: [],
        total: 0,
        page: 1,
        sels: [], // 列表选中列
        editFormRules: {
          peopleId: [
            { required: true, message: ' ', trigger: 'blur' },
            { pattern: /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/, message: ' ' }
          ],
          name: [
            { required: true, message: ' ', trigger: 'blur' }
          ],
          area: [{ required: true, message: ' ', trigger: 'blur' }]
        },
        findFormRules: {
          name: [{ required: true, message: ' ', trigger: 'blur' }]
        },
        // 编辑界面数据
        editForm: {
          id: '',
          name: '',
          area: '',
          date: null,
          childBirth: null,
          leaveHospitale: null,
          temperature: '',
          healthy: '',
          psychology: '',
          blood: '',
          breast: '',
          lochia: '',
          wound: '',
          uterus: '',
          other: '',
          reason: '',
          handle: '',
          classify: '',
          guidance: [],
          insandDep: '',
          doctor: '',
          creator: '',
          createDate: null,
          modifier: '',
          modifyDate: null
        },
        // 查找界面数据
        findForm: {
          insandDep: '',
          doctor: '',
          leaveHospitale_s: null,
          leaveHospitale_e: null,
          date_s: null,
          date_e: null
        },
        addFormVisible: false, // 新增界面是否显示
        addFormRules: {
          name: [{ required: true, message: '请输入姓名', trigger: 'blur' }]
        }
      }
    },
    methods: {
      // 性别显示转换
      formatSex: function(row, column) {
        return row.sex === 1 ? '男' : row.sex === 0 ? '女' : '未知'
      },
      goto(index, row) {
        this.$router.push({
          name: '首页',
          params: {
            hid: row.peopleId
          }
        })
      },
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
        // myWindow.document.write(content + style)
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
          '\t<div id="topContent">\n' +
          '\t\t<h2 style="width:794px;text-align:center">产后访视记录表</h2>  \n' +
          '\t\t<span style="margin-left:34px;">姓名:{{name}}}</span>\n' +
          '\t\t<span style="margin-left:270px;">NO:{{id}}</span> \n' +
          '\t</div>\n' +
          '\t<table id="table"  border="1"  cellspacing="0" bordercolor="#000000" style="width:730px;margin-left:30px;margin-top:5px;border-collapse:collapse;"  width = "80%">\n' +
          '\t<tbody>\n' +
          '\t\t<tr>\n' +
          '\t\t\t<td style="width: 20%;text-align:center">随访日期</td>\n' +
          '\t\t\t<td colspan="5" style="text-align:center">{{date}}</td>\n' +
          '\t\t</tr>\n' +
          '\t\t<tr>\n' +
          '\t\t\t<td style="word-wrap:break-word;text-align:center">分娩日期</td>\n' +
          '\t\t\t<td colspan="2" style="text-align:center">{{childBirth}}}</td>\n' +
          '\t\t\t<td style="word-wrap:break-word;text-align:center">出院日期</td>\n' +
          '\t\t\t<td colspan="2" style="text-align:center">{{leaveHospitale}}</td>\n' +
          '\t\t</tr>\n' +
          '\t\t<tr>\n' +
          '\t\t\t<td style="word-wrap:break-word;text-align:center">一般健康状况</td>\n' +
          '\t\t\t<td colspan="5" style="text-align:center">{{healthy}}</td>\n' +
          '\t\t</tr>\n' +
          '\t\t<tr>\n' +
          '\t\t\t<td style="word-wrap:break-word;text-align:center">一般心理状况</td>\n' +
          '\t\t\t<td colspan="5" style="text-align:center">{{psychology}}</td>\n' +
          '\t\t</tr>\n' +
          '\t\t<tr>\n' +
          '\t\t\t<td style="word-wrap:break-word;text-align:center">血压（mmHg）</td>\n' +
          '\t\t\t<td colspan="5" style="text-align:center">{{blood}}</td>\n' +
          '\t\t</tr>\n' +
          '\t\t<tr>\n' +
          '\t\t\t<td style="word-wrap:break-word;text-align:center">乳房</td>\n' +
          '\t\t\t<td colspan="5" style="text-align:left">{{breast}}</td>\n' +
          '\t\t</tr>\n' +
          '\t\t<tr>\n' +
          '\t\t\t<td style="word-wrap:break-word;text-align:center">恶露</td>\n' +
          '\t\t\t<td colspan="5" style="text-align:left">{{lochia}}</td>\n' +
          '\t\t</tr>\n' +
          '\t\t<tr>\n' +
          '\t\t\t<td style="word-wrap:break-word;text-align:center">子宫</td>\n' +
          '\t\t\t<td colspan="5" style="text-align:left">{{uterus}}</td>\n' +
          '\t\t</tr>\n' +
          '\t\t<tr>\n' +
          '\t\t\t<td style="word-wrap:break-word;text-align:center">伤口</td>\n' +
          '\t\t\t<td colspan="5" style="text-align:left">{{wound}}</td>\n' +
          '\t\t</tr>\n' +
          '\t\t<tr>\n' +
          '\t\t\t<td style="word-wrap:break-word;text-align:center">其他</td>\n' +
          '\t\t\t<td colspan="5" style="text-align:left">{{other}}</td>\n' +
          '\t\t</tr>\n' +
          '\t\t<tr>\n' +
          '\t\t\t<td style="word-wrap:break-word;text-align:center">分类</td>\n' +
          '\t\t\t<td colspan="5" style="text-align:left">{{classify}}</td>\n' +
          '\t\t</tr>\n' +
          '\t\t<tr>\n' +
          '\t\t\t<td style="height:150px;text-align:center">指导</td>\n' +
          '\t\t\t<td colspan="5" style="text-align:left">{{guidance}}</td>\n' +
          '\t\t</tr>\n' +
          '\t\t<tr>\n' +
          '\t\t\t<td style="height:50px;text-align:center">处理</td>\n' +
          '\t\t\t<td colspan="5" style="text-align:left"><br/>{{handle}}<br/>原因:{{reason}}<br/>机构及科室:{{insandDep}}</td>\n' +
          '\t\t</tr>\n' +
          '\t\t<tr>\n' +
          '\t\t\t<td style="text-align:center">随访医生签名</td>\n' +
          '\t\t\t<td colspan="5" style="text-align:center">{{doctor}}</td>\n' +
          '\t\t</tr>\n' +
          '\t\t</tbody>\n' +
          '\t</table>\n' +
          '</div>\n' +
          '</body>\n' +
          '</html>\n'
        str = str.replace(/{{name}}/, this.editForm.name ? this.editForm.name : '')
        str = str.replace(/{{id}}/, this.editForm.id ? this.editForm.id : '')
        str = str.replace(/{{date}}/, this.editForm.date ? this.editForm.date : '')
        str = str.replace(/{{childBirth}}/, this.editForm.childBirth ? this.editForm.childBirth : '')
        str = str.replace(/{{leaveHospitale}}/, this.editForm.leaveHospitale ? this.editForm.leaveHospitale : '')
        str = str.replace(/{{healthy}}/, this.editForm.healthy ? this.editForm.healthy : '')
        str = str.replace(/{{healthy}}/, this.editForm.healthy ? this.editForm.healthy : '')
        str = str.replace(/{{psychology}}/, this.editForm.psychology ? this.editForm.psychology : '')
        str = str.replace(/{{blood}}/, this.editForm.blood ? this.editForm.blood : '')
        str = str.replace(/{{breast}}/, this.editForm.breast ? this.editForm.breast : '')
        str = str.replace(/{{lochia}}/, this.editForm.lochia ? this.editForm.lochia : '')
        str = str.replace(/{{wound}}/, this.editForm.wound ? this.editForm.wound : '')
        str = str.replace(/{{uterus}}/, this.editForm.uterus ? this.editForm.uterus : '')
        str = str.replace(/{{other}}/, this.editForm.other ? this.editForm.other : '')
        str = str.replace(/{{classify}}/, this.editForm.classify ? this.editForm.classify : '')
        str = str.replace(/{{guidance}}/, this.editForm.guidance ? this.editForm.guidance : '')
        str = str.replace(/{{handle}}/, this.editForm.handle ? this.editForm.handle : '')
        str = str.replace(/{{insandDep}}/, this.editForm.insandDep ? this.editForm.insandDep : '')
        str = str.replace(/{{reason}}/, this.editForm.reason ? this.editForm.reason : '')
        str = str.replace(/{{doctor}}/, this.editForm.doctor ? this.editForm.doctor : '')
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
      handleCurrentChange(val) {
        this.page = val
        this.getUsers()
      },
      // 列表时间格式化
      timeFormat: function(row, column) {
        let date = row[column.property]
        date =
          !date || date === ''
            ? ''
            : util.formatDate.format(new Date(date), 'yyyy-MM-dd')
        return date
      },
      search() {
        this.page = 1
        this.getUsers()
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
        this.area = []
        this.editForm = Object.assign({}, row)
        this.editForm.guidance = this.editForm.guidance.split(',')
        this.editForm.area = this.editForm.area.split('/')
        this.edit = true
      },
      // 显示新增界面
      handleAdd() {
        this.dialogStatus = 'create'
        this.dialogFormVisible = true
        this.edit = false
        this.area = []
        this.editForm = {
          id: '',
          name: '',
          area: '',
          date: new Date(),
          childBirth: new Date(),
          leaveHospitale: new Date(),
          temperature: '',
          healthy: '',
          psychology: '',
          blood: '',
          breast: '',
          lochia: '',
          wound: '',
          uterus: '',
          other: '',
          handle: '',
          reason: '',
          classify: '',
          guidance: [],
          insandDep: '',
          doctor: '',
          creator: this.$store.getters.name,
          createDate: null,
          modifier: '',
          modifyDate: null
        }
      },
      findbtn() {
        this.dialogStatus = 'search'
        this.dialogFormVisible2 = true
        this.findForm = {
          insandDep: '',
          doctor: '',
          leaveHospitale: null
        }
      },
      // 获取用户列表
      getUsers() {
        this.findForm.date_s =
          !this.findForm.date_s || this.findForm.date_s === ''
            ? ''
            : util.formatDate.format(new Date(this.findForm.date_s), 'yyyy-MM-dd')
        this.findForm.date_e =
          !this.findForm.date_e || this.findForm.date_e === ''
            ? ''
            : util.formatDate.format(new Date(this.findForm.date_e), 'yyyy-MM-dd')
        this.findForm.leaveHospitale_s =
          !this.findForm.leaveHospitale_s || this.findForm.leaveHospitale_s === ''
            ? ''
            : util.formatDate.format(new Date(this.findForm.leaveHospitale_s), 'yyyy-MM-dd')
        this.findForm.leaveHospitale_e =
          !this.findForm.leaveHospitale_e || this.findForm.leaveHospitale_e === ''
            ? ''
            : util.formatDate.format(new Date(this.findForm.leaveHospitale_e), 'yyyy-MM-dd')
        const para = {
          page: this.page,
          size: 20,
          filters: {
            name: this.filters.name,
            date_s: this.findForm.date_s,
            date_e: this.findForm.date_e,
            insandDep: this.findForm.insandDep,
            peopleId: this.filters.peopleId,
            doctor: this.findForm.doctor,
            leaveHospitale_s: this.findForm.leaveHospitale_s,
            leaveHospitale_e: this.findForm.leaveHospitale_e
          }
        }
        getList(para).then(res => {
          this.total = res.totalCount
          this.users = res.detailModelList
          if (this.page > 1 && (this.page - 1) * 20 >= this.total) {
            this.page = this.page - 1
            this.getUsers()
          }
        })
        this.dialogFormVisible2 = false
        this.findForm = {
          insandDep: '',
          doctor: '',
          leaveHospitale_s: null,
          leaveHospitale_e: null
        }
      },
      check() {
        this.$refs.editForm.validate(valid => {
          if (valid) {
            const para = {
              page: 1,
              size: 200,
              filters: { name: '', peopleId: this.editForm.peopleId, cardNumber: '' }
            }
            getPerson(para).then(res => {
              if (res.detailModelList.length !== 0) {
                this.createData()
              } else {
                this.$message.error('未建立个人档案或该居民不是孕妇')
              }
            })
          } else {
            if (this.editForm.peopleId === '' || this.editForm.peopleId === undefined) {
              this.$el.querySelector('#peopleId').scrollIntoView()
              this.$message.error('请填写身份证')
              return false
            } else {
              this.$message.error('未建立个人档案或该居民不是孕妇')
            }
          }
        })
      },
      // 删除
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
      },
      // 编辑
      updateData() {
        this.$refs.editForm.validate(valid => {
          if (valid) {
            this.$confirm('确认提交吗？', '提示', {
              confirmButtonText: '确定',
              cancelButtonText: '取消'
            })
              .then(() => {
                const para = Object.assign({}, this.editForm)
                para.modifier = this.$store.getters.name
                para.date =
                  !para.date || para.date === ''
                    ? ''
                    : util.formatDate.format(new Date(para.date), 'yyyy-MM-dd')
                para.childBirth =
                  !para.childBirth || para.childBirth === ''
                    ? ''
                    : util.formatDate.format(new Date(para.childBirth), 'yyyy-MM-dd')
                para.leaveHospitale =
                  !para.leaveHospitale || para.leaveHospitale === ''
                    ? ''
                    : util.formatDate.format(new Date(para.leaveHospitale), 'yyyy-MM-dd')
                para.guidance = ''
                for (const itm of this.editForm.guidance) {
                  if (itm !== '') {
                    para.guidance += itm
                    para.guidance += ','
                  }
                }
                para.guidance = para.guidance.substr(0, para.guidance.length - 1)
                // 获取级联数据
                para.area = ''
                for (const itm of this.editForm.area) {
                  para.area += itm
                  para.area += '/'
                }
                para.area = para.area.substr(0, para.area.length - 1)
                updateData(para).then(res => {
                  this.$message({
                    message: '提交成功',
                    type: 'success'
                  })
                  this.$refs['editForm'].resetFields()
                  this.dialogFormVisible = false
                  this.$emit('watchChild')
                  this.getUsers()
                })
                const para2 = {
                  page: 1,
                  size: 200,
                  filters: { nextFollow_s: '', nextFollow_e: '', peopleId: para.peopleId, userId: '' }
                }
                const followData = {
                  peopleId: para.peopleId,
                  nextFollow: '',
                  lastFollow: '',
                  userId: this.$store.getters.id
                }
                getFollow(para2).then(res => {
                  const follow = res.detailModelList
                  if (follow.length > 0) {
                    if (follow[0].lastFollow < para.date) {
                      follow[0].lastFollow = para.date
                    }
                    editFollow(follow[0]).then(res => {})
                  } else {
                    followData.lastFollow = para.date
                    addFollow(followData).then(res => {})
                  }
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
              cancelButtonText: '取消'
            })
              .then(() => {
                this.editForm.id = (parseInt(Math.random() * 100)).toString() // mock a id
                const para = Object.assign({}, this.editForm)
                console.log(para)
                para.date =
                  !para.date || para.date === ''
                    ? ''
                    : util.formatDate.format(new Date(para.date), 'yyyy-MM-dd')
                para.childBirth =
                  !para.childBirth || para.childBirth === ''
                    ? ''
                    : util.formatDate.format(new Date(para.childBirth), 'yyyy-MM-dd')
                para.leaveHospitale =
                  !para.leaveHospitale || para.leaveHospitale === ''
                    ? ''
                    : util.formatDate.format(new Date(para.leaveHospitale), 'yyyy-MM-dd')

                para.guidance = ''
                for (const itm of this.editForm.guidance) {
                  if (itm !== '') {
                    para.guidance += itm
                    para.guidance += ','
                  }
                }
                para.guidance = para.guidance.substr(0, para.guidance.length - 1)
                para.area = ''
                for (const itm of this.editForm.area) {
                  para.area += itm
                  para.area += '/'
                }
                para.area = para.area.substr(0, para.area.length - 1)
                addData(para).then(res => {
                  this.$message({
                    message: '提交成功',
                    type: 'success'
                  })
                  this.$refs['editForm'].resetFields()
                  this.dialogFormVisible = false
                  this.$emit('watchChild')
                  this.getUsers()
                })

                const para2 = {
                  page: 1,
                  size: 200,
                  filters: { nextFollow_s: '', nextFollow_e: '', peopleId: para.peopleId, userId: '' }
                }
                const followData = {
                  peopleId: para.peopleId,
                  nextFollow: '',
                  lastFollow: '',
                  userId: this.$store.getters.id
                }
                getFollow(para2).then(res => {
                  const follow = res.detailModelList
                  if (follow.length > 0) {
                    if (follow[0].lastFollow < para.date) {
                      follow[0].lastFollow = para.date
                    }
                    editFollow(follow[0]).then(res => {})
                  } else {
                    followData.lastFollow = para.date
                    addFollow(followData).then(res => {})
                  }
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
            batchRemoveData(para).then(res => {
              this.$message({
                message: '删除成功',
                type: 'success'
              })
              this.getUsers()
            })
          })
          .catch(() => {})
      },
      inputFunc() {
        const para = {
          page: 1,
          size: 200,
          filters: { name: '', peopleId: this.editForm.peopleId, cardNumber: '' }
        }
        if (para.filters !== '') {
          setTimeout(() => {
            this.$nextTick(function() {
              getSearchMemberPage(para).then(res => {
                const user = res.detailModelList
                if (user.length !== 0) {
                  this.editForm.name = user[0].name
                  this.editForm.area = user[0].houseAddress
                  this.editForm.area = this.editForm.area.split('/')
                }
              })
            })
          }, 200)
        }
      }
    },
    mounted() {
      this.buttons = this.getButton(this.buttons, this.$store.getters.buttons)
      this.getUsers()
    }
  }
</script>

<style scoped>
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
  span{
    color:red;
  }
</style>
