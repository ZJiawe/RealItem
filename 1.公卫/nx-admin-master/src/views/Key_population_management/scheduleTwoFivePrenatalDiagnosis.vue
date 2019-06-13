<template>
  <section class="app-container">

    <el-col :span="24" class="toolbar" style="padding-bottom: 0px;">
      <el-form :inline="true" :model="filters" @submit.native.prevent>
        <el-form-item>
          <el-input v-model="filters.peopleId" placeholder="身份证号" auto-complete="off" clearable></el-input>
        </el-form-item>
          <el-form-item>
            <el-date-picker type="date" placeholder="(随访/督促)开始日期" v-model="filters.date_s" style="width: 100%;"></el-date-picker>
          </el-form-item>
          <el-form-item>
            <el-date-picker type="date" placeholder="(随访/督促)结束日期" v-model="filters.date_e" style="width: 100%;"></el-date-picker>
          </el-form-item>
        <el-form-item>
          <el-button type="primary" v-on:click="search" style="background-color:#4db2ee;border-color:transparent;height:40px" icon="el-icon-search">查询</el-button>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" v-on:click="findbtn" style="background-color:#4db2ee;border-color:transparent;width: 115px;height:40px" icon="el-icon-search">高级查询</el-button>
        </el-form-item>
        <el-form-item>
          <el-button v-if="buttons.KPS_C" style="height:40px" type="primary" @click="handleAdd" icon="el-icon-circle-plus-outline">新增</el-button>
        </el-form-item>
      </el-form>
    </el-col>
    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible" :close-on-click-modal="false" center>
      <div id="print">
      <el-form :model="editForm" label-width="110px" :rules="editFormRules" ref="editForm">
        <EasyScrollbar>
          <div class="item" v-bind:style="{ height: tablebodyHeight + 'px'}">
            <el-tabs v-model="activeName">
              <table id="table"  border="1"  cellspacing="0" bordercolor="#dddddd" style="font-size:14px;margin-left:10px;margin-right:30px;border-collapse:collapse;">
                <tbody style="color:#323232;">
                <tr v-if="dialogStatus==='detail'">
                  <td style="word-wrap:break-word;text-align:center;width:100px;">
                    创建人
                  </td>
                  <td colspan="4" class="pad10">
                    <el-form-item label="" label-width="0px">
                      <el-input v-model="editForm.creator" style="width: 100%" v-if="dialogStatus==='detail'" readonly></el-input>
                    </el-form-item>
                  </td>
                  <td style="word-wrap:break-word;text-align:center;width:100px;">
                    创建时间
                  </td>
                  <td colspan="2" class="pad10">
                    <el-form-item label="" label-width="0px" v-if="dialogStatus==='detail'">
                      <el-date-picker type="date" placeholder="选择日期" v-model="editForm.createDate" style="width: 100%" v-if="dialogStatus==='detail'" readonly></el-date-picker>
                    </el-form-item>
                  </td>
                </tr>
                <tr v-if="dialogStatus==='detail'">
                  <td style="word-wrap:break-word;text-align:center;width:100px;">
                    修改人
                  </td>
                  <td colspan="4" class="pad10">
                    <el-form-item label="" label-width="0px" v-if="dialogStatus==='detail'">
                      <el-input v-model="editForm.modifier" style="width: 100%" v-if="dialogStatus==='detail'" readonly></el-input>
                    </el-form-item>
                  </td>
                  <td style="word-wrap:break-word;text-align:center;width:100px;">
                    修改时间
                  </td>
                  <td colspan="2" class="pad10">
                    <el-form-item label="" label-width="0px" v-if="dialogStatus==='detail'">
                      <el-date-picker type="date" placeholder="选择日期" v-model="editForm.modifyDate" style="width: 100%" v-if="dialogStatus==='detail'" readonly></el-date-picker>
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
                  <td colspan="4" class="pad10" style="text-align:left;padding-right: 0px;">
                    <el-form-item label="" label-width="0px" prop="area">
                      <el-cascader :options="options" v-model="editForm.area" style="width: 300px;" disabled="false" placeholder="请选择" change-on-select></el-cascader>
                    </el-form-item>
                  </td>
                  <td colspan="1" style="text-align:center;width:140px;">
                    <span>*</span>姓名
                  </td>
                  <td colspan="2" class="pad10" style="text-align:left;padding-right: 0px;">
                    <el-form-item label="" label-width="0px" prop="name" ref="name">
                      <el-input  v-model="editForm.name" style="width: 100%;" disabled="false"></el-input>
                    </el-form-item>
                  </td>
                </tr>
                <tr>
                  <td colspan="1" style="text-align:center;">
                    <span>*</span>身份证号
                  </td>
                  <td colspan="4" class="pad10" style="text-align:left;padding-right: 0px;">
                    <el-form-item label="" label-width="0px" prop="peopleId">
                      <el-input v-model="editForm.peopleId" style="width: 100%;" v-on:input="inputFunc" placeholder="请输入身份证" id="peopleId"></el-input>
                    </el-form-item>
                  </td>
                  <td colspan="1" style="text-align:center;width:80px;">
                    <span>*</span>第几次
                  </td>
                  <td colspan="2" style="text-align:left;padding-right: 0px;" class="pad10">
                    <el-form-item label="" label-width="0px" prop="number">
                      <el-radio-group v-model="editForm.number" ref="number" id="number">
                        <el-radio label="第二次">二</el-radio>
                        <el-radio label="第三次">三</el-radio>
                        <el-radio label="第四次">四</el-radio>
                        <el-radio label="第五次">五</el-radio>
                      </el-radio-group>
                    </el-form-item>
                  </td>
                </tr>
                <tr>
                  <td style="text-align:center">随访日期</td>
                  <td colspan="4" class="pad10">
                    <el-date-picker type="date" placeholder="选择日期" v-model="editForm.date" style="width: 100%;"></el-date-picker>
                  </td>
                  <td style="word-wrap:break-word;text-align:center">随访状态</td>
                  <td colspan="2" style="text-align:left;" class="pad10">
                    <el-radio-group v-model="editForm.status">
                      <el-radio class="radio" label="规范"></el-radio>
                      <el-radio class="radio" label="失访"></el-radio>
                    </el-radio-group>
                  </td>
                </tr>
                <tr>
                  <td style="word-wrap:break-word;text-align:center">孕周</td>
                  <td colspan="1" style=";width:150px;" class="pad10">
                    <el-input-number v-model="editForm.gestationalWeeks" :min="1" :max="20" label="描述文字" style="width:100%;"></el-input-number>
                  </td>
                  <td style="word-wrap:break-word;text-align:center;width:60px;">主诉</td>
                  <td colspan="2" class="pad10">
                    <el-input v-model="editForm.mainSuit"></el-input>
                  </td>
                  <td style="word-wrap:break-word;text-align:center">体重</td>
                  <td colspan="2" class="pad10">
                    <el-input v-model="editForm.weight">
                      <template slot="append">Kg</template>
                    </el-input>
                  </td>
                </tr>
                <tr>
                  <td rowspan="4" colspan="1"style="word-wrap:break-word;text-align:center;">产科检查</td>
                  <td style="word-wrap:break-word;text-align:center;">宫底高度</td>
                  <td colspan="6" class="pad10">
                    <el-input v-model="editForm.fundusHeight">
                      <template slot="append">cm</template>
                    </el-input>
                  </td>
                </tr>
                <tr>
                  <td style="word-wrap:break-word;text-align:center">腹围</td>
                  <td colspan="6" class="pad10">
                    <el-input v-model="editForm.abdominal">
                      <template slot="append">cm</template>
                    </el-input>
                  </td>
                </tr>
                <tr>
                  <td style="word-wrap:break-word;text-align:center">胎位</td>
                  <td colspan="6" class="pad10">
                    <el-input v-model="editForm.fetalPosition"></el-input>
                  </td>
                </tr>
                <tr>
                  <td style="word-wrap:break-word;text-align:center">胎心率</td>
                  <td colspan="6" style="text-align:left" class="pad10">
                    <el-input v-model="editForm.fetalPosition">
                      <template slot="append">次/分钟</template>
                    </el-input>
                  </td>
                </tr>
                <tr>
                  <td style="word-wrap:break-word;text-align:center">血压</td>
                  <td colspan="2" style="text-align:left" class="pad10">
                    <el-input v-model="editForm.blood">
                      <template slot="append">mmHg</template>
                    </el-input>
                  </td>
                  <td style="word-wrap:break-word;text-align:center;width:90px;">血红蛋白</td>
                  <td colspan="2" style="text-align:left" class="pad10">
                    <el-input v-model="editForm.hemoglobin">
                      <template slot="append">g/L</template>
                    </el-input>
                  </td>
                  <td style="word-wrap:break-word;text-align:center;width:100px;">尿蛋白</td>
                  <td colspan="1" style="text-align:left" class="pad10">
                    <el-input v-model="editForm.urineProtein"></el-input>
                  </td>
                </tr>
                <tr>
                  <td style="word-wrap:break-word;text-align:center">其他辅助检查</td>
                  <td colspan="7" class="pad20">
                    <el-checkbox-group v-model="editForm.otherSassist">
                      <table>
                        <tr>
                          <td style="padding-right: 20px"><el-checkbox label="肝功能" name="type"></el-checkbox></td>
                          <td style="padding-right: 20px"><el-checkbox label="乙肝两对半" name="type"></el-checkbox></td>
                          <td style="padding-right: 20px"><el-checkbox label="肾功能" name="type"></el-checkbox></td>
                          <td style="padding-right: 20px"><el-checkbox label="4B超" name="type"></el-checkbox></td>
                        </tr>
                      </table>
                    </el-checkbox-group>
                  </td>
                </tr>
                <tr>
                  <td style="word-wrap:break-word;text-align:center">分类</td>
                  <td colspan="7" style="text-align:left" class="pad10">
                    <el-radio-group v-model="editForm.classify">
                      <el-radio  class="radio" label="未见异常"></el-radio>
                      <el-radio  class="radio" label="异常"></el-radio>
                    </el-radio-group>
                  </td>
                </tr>
                <tr>
                  <td style="text-align:center">指导</td>
                  <td colspan="7" class="pad20">
                    <el-checkbox-group v-model="editForm.guidance" style="width:100%">
                      <table>
                        <tr>
                          <td style="padding-right: 20px"><el-checkbox label="生活方式" name="type"></el-checkbox></td>
                          <td style="padding-right: 20px"><el-checkbox label="营养" name="type"></el-checkbox></td>
                          <td style="padding-right: 20px"><el-checkbox label="心理" name="type"></el-checkbox></td>
                          <td style="padding-right: 20px"><el-checkbox label="运动" name="type"></el-checkbox></td>
                          <td style="padding-right: 20px"><el-checkbox label="其他" name="type"></el-checkbox></td>
                        </tr>
                      </table>
                    </el-checkbox-group>
                  </td>
                </tr>
                <tr>
                  <td style="word-wrap:break-word;text-align:center">转诊</td>
                  <td colspan="1" style="text-align:left" class="pad10">
                    <el-radio-group v-model="editForm.referral">
                      <el-radio class="radio" label="无"></el-radio>
                      <el-radio class="radio" label="有"></el-radio>
                    </el-radio-group>
                  </td>
                  <td colspan="6" class="pad10">
                    <table style="width: 100%;">
                      <tr>
                        <td>原因</td>
                        <td><el-input v-model="editForm.reason" ></el-input></td>
                        <td>机构及科室</td>
                        <td><el-input v-model="editForm.insandDep" ></el-input></td>
                      </tr>
                    </table>
                  </td>
                </tr>
                <tr>
                  <td style="text-align:center">下次随访日期</td>
                  <td colspan="4" class="pad10">
                    <el-date-picker type="date" placeholder="选择日期" v-model="editForm.nextDate" style="width: 100%;"></el-date-picker>
                  </td>
                  <td style="text-align:center">随访签名</td>
                  <td colspan="2" class="pad10">
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

    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible2" :close-on-click-modal="false" center>
      <el-form :model="findForm" label-width="110px" :rules="findFormRules" ref="findForm">
        <el-form ref="findForm" :model="findForm" label-width="120px">
          <el-row>
            <el-col :span="12">
              <el-form-item label="姓名">
                <el-input v-model="filters.name" clearable></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="机构及科室">
                <el-input v-model="findForm.insandDep" clearable></el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="12">
              <el-form-item label="(随访/督促)日期">
                <el-row>
                  <el-col :span="12">
                    <el-date-picker type="date" placeholder="开始日期" v-model="filters.date_s" style="width: 100%;"></el-date-picker>
                  </el-col>
                  <el-col :span="12">
                    <el-date-picker type="date" placeholder="结束日期" v-model="filters.date_e" style="width: 100%;"></el-date-picker>
                  </el-col>
                </el-row>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="随访医生签名">
                <el-input v-model="findForm.doctor" clearable></el-input>
              </el-form-item>
            </el-col>
          </el-row>
        </el-form>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" v-on:click="getUsers" style="background-color:#4db2ee;border-color:transparent" icon="el-icon-search">查询</el-button>
      </div>
    </el-dialog>

    <el-table :data="users" :height="tableHeight" highlight-current-row @selection-change="selsChange" @row-dblclick="handleRowChange" border style="width: 100%;">
      <el-table-column type="index" label="序号" width="70">
      </el-table-column>
      <el-table-column prop="id" label="编号" width="60" style="text-align:center" v-if='false'>
      </el-table-column>
      <el-table-column prop="number" label="第几次" width="80">
      </el-table-column>
      <el-table-column prop="name" label="姓名" width="80">
      </el-table-column>
      <el-table-column prop="date"  v-if='false' label="(随访/督促)日期" width="150">
      </el-table-column>
      <el-table-column prop="peopleId" label="身份证号" width="180">
      </el-table-column>
      <el-table-column prop="gestationalWeeks" v-if='false' label="孕周" min-width="80">
      </el-table-column>
      <el-table-column prop="mainSuit" v-if='false' label="主诉" min-width="80">
      </el-table-column>
      <el-table-column prop="weight" v-if='false' label="体重" min-width="80">
      </el-table-column>
      <el-table-column prop="fundusHeight" v-if='false' label="宫底高度" min-width="100">
      </el-table-column>
      <el-table-column prop="abdominal" v-if='false' label="腹围" min-width="80">
      </el-table-column>
      <el-table-column prop="fetalPosition" v-if='false' label="胎位" min-width="80">
      </el-table-column>
      <el-table-column prop="fhr" v-if='false' label="胎心率" min-width="80">
      </el-table-column>
      <el-table-column prop="blood" v-if='false' label="血压" min-width="120">
      </el-table-column>
      <el-table-column prop="hemoglobin" v-if='false' label="血红蛋白" min-width="120">
      </el-table-column>
      <el-table-column prop="urineProtein" v-if='false' label="尿蛋白" min-width="120">
      </el-table-column>
      <el-table-column prop="otherSassist" v-if='false' label="其他(辅助检查的)" min-width="160">
      </el-table-column>
      <el-table-column prop="classify" v-if='false' label="分类" min-width="120">
      </el-table-column>
      <el-table-column prop="guidance" v-if='false'  label="指导" min-width="120">
      </el-table-column>
      <el-table-column prop="insandDep" label="机构及科室" min-width="120">
      </el-table-column>
      <el-table-column prop="referral" v-if='false'label="转诊" min-width="120">
      </el-table-column>
      <el-table-column prop="reason" v-if='false' label="原因" min-width="120">
      </el-table-column>
      <el-table-column prop="nextDate" v-if='false' label="下次随访日期" min-width="120">
      </el-table-column>
      <el-table-column prop="doctor" label="随访医生" min-width="100">
      </el-table-column>
      <el-table-column prop="creator" label="创建人" min-width="120">
      </el-table-column>
      <el-table-column prop="createDate" label="创建时间" min-width="120" :formatter="timeFormat">
      </el-table-column>
      <el-table-column prop="modifier" label="修改人" min-width="120">
      </el-table-column>
      <el-table-column prop="modifyDate" label="修改时间" min-width="120" :formatter="timeFormat">
      </el-table-column>
      <el-table-column label="操作" width="200">
        <template slot-scope="scope">
          <a size="14px" @click="goto(scope.$index, scope.row)" style="color:#4DB2EE;margin-right:20px;">查看档案</a>
          <a size="14px" v-if="buttons.KPS_E" @click="handleEdit(scope.$index, scope.row)" style="color:#4DB2EE;margin-right:20px;">编辑</a>
          <a size="14px" v-if="buttons.KPS_D" @click="handleDel(scope.$index, scope.row)" style="color:#FB4141">删除</a>
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
    printData,
    addData
  } from '@/api/scheduleTwoFivePrenatalDiagnosis'
  import {
    getList as getFollow,
    updateData as editFollow,
    addData as addFollow
  } from '@/api/follow'
  import city from '@/api/city.js'
  import { checkData as getPerson } from '@/api/firstPrenatalDiagnosis'
  import {
    getList as getSearchMemberPage
  } from '@/api/Perinfor'

  export default {
    data() {
      return {
        buttons: {
          KPS_C: false,
          KPS_E: false,
          KPS_D: false
        },
        edit: true,
        activeName: 'first',
        tableHeight: window.innerHeight - 210,
        tablebodyHeight: window.innerHeight - 320,
        options: city.city(),
        options2: city.occurPlace(),
        guidance: [
          { value: '生活方式', label: '生活方式' },
          { value: '营养', label: '营养' },
          { value: '心理', label: '心理' },
          { value: '运动', label: '运动' },
          { value: '其他', label: '其他' }
        ],
        dialogStatus: '',
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
          date_e: null,
          date_s: null
        },
        ceshi: '',
        users: [],
        printItem: [],
        printItem1: [],
        printItem2: [],
        printItem3: [],
        printItem4: [],
        total: 0,
        page: 1,
        sels: [], // 列表选中列
        editFormRules: {
          name: [
            { required: true, message: ' ', trigger: 'blur' }
          ],
          number: [
            { required: true, message: ' ', trigger: 'blur' }
          ],
          peopleId: [
            { required: true, message: ' ', trigger: 'blur' },
            { pattern: /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/, message: ' ' }
          ],
          area: [{ required: true, message: ' ', trigger: 'blur' }]
        },
        findFormRules: {
          name: [{ required: true, message: '请输入姓名', trigger: 'blur' }]
        },
        // 编辑界面数据
        editForm: {
          id: '',
          name: '',
          area: [],
          date: '',
          number: '',
          peopleId: '',
          status: '',
          gestationalWeeks: '',
          mainSuit: '',
          weight: '',
          fundusHeight: '',
          abdominal: '',
          fetalPosition: '',
          fhr: '',
          blood: '',
          hemoglobin: '',
          urineProtein: '',
          otherSassist: [],
          classify: '',
          guidance: [],
          guidances: '',
          referral: '',
          reason: '',
          insandDep: '',
          nextDate: '',
          doctor: '',
          creator: '',
          createDate: null,
          modifier: '',
          modifyDate: null
        },
        // 查找界面数据
        findForm: {
          insandDep: '',
          doctor: ''
        },
        addFormVisible: false, // 新增界面是否显示
        addFormRules: {
          name: [{ required: true, message: '请输入姓名', trigger: 'blur' }]
        }
      }
    },
    methods: {
      findbtn() {
        this.dialogStatus = 'search'
        this.dialogFormVisible2 = true
        this.findForm = {
          insandDep: '',
          doctor: ''
        }
      },
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
          '\t\t<h2 style="width:794px;text-align:center">第2~5次产前检查服务记录表</h2>  \n' +
          '\t\t<span style="margin-left:34px;">姓名:{{name}}</span>\n' +
          '\t\t<span style="margin-left:270px;">NO:{{id}}</span> \n' +
          '\t</div>\n' +
          '\t<table id="table"  border="1"  cellspacing="0" bordercolor="#000000" style="width:730px;margin-left:30px;margin-top:5px;border-collapse:collapse;"  width = "80%">\n' +
          '\t<tbody>\n' +
          '\t\t<tr>\n' +
          '\t\t\t<td colspan="2" style="width: 20%;text-align:center">项目</td>\n' +
          '\t\t\t<td style="width: 15%;text-align:center">{{number1}}</td>\n' +
          '\t\t\t<td style="width: 15%;text-align:center">{{number2}}</td>\n' +
          '\t\t\t<td style="width: 15%;text-align:center">{{number3}}</td>\n' +
          '\t\t\t<td style="width: 15%;text-align:center">{{number4}}</td>\n' +
          '\t\t</tr>\n' +
          '\t\t<tr>\n' +
          '\t\t\t<td colspan="2" style="word-wrap:break-word;width: 10%;text-align:center">（随访/督促）日期</td>\n' +
          '\t\t\t<td style="text-align:center">{{date1}}</td>\n' +
          '\t\t\t<td style="text-align:center">{{date2}}</td>\n' +
          '\t\t\t<td style="text-align:center">{{date3}}</td>\n' +
          '\t\t\t<td style="text-align:center">{{date4}}</td>\n' +
          '\t\t</tr>\n' +
          '\t\t<tr>\n' +
          '\n' +
          '\t\t\t<td colspan="2" style="word-wrap:break-word;width: 10%;text-align:center">孕周</td>\n' +
          '\t\t\t<td style="text-align:center">{{gestationalWeeks1}}</td>\n' +
          '\t\t\t<td style="text-align:center">{{gestationalWeeks2}}</td>\n' +
          '\t\t\t<td style="text-align:center">{{gestationalWeeks3}}</td>\n' +
          '\t\t\t<td style="text-align:center">{{gestationalWeeks4}}</td>\n' +
          '\t\t</tr>\n' +
          '\t\t<tr>\n' +
          '\t\t\t<td colspan="2" style="word-wrap:break-word;width: 10%;height:50px;text-align:center">主诉</td>\n' +
          '\t\t\t<td style="text-align:center">{{mainSuit1}}</td>\n' +
          '\t\t\t<td style="text-align:center">{{mainSuit2}}</td>\n' +
          '\t\t\t<td style="text-align:center">{{mainSuit3}}</td>\n' +
          '\t\t\t<td style="text-align:center">{{mainSuit4}}</td>\n' +
          '\t\t</tr>\n' +
          '\t\t<tr>\n' +
          '\t\t\t<td colspan="2" style="word-wrap:break-word;width: 10%;text-align:center">体重</td>\n' +
          '\t\t\t<td style="text-align:center">{{weight1}}</td>\n' +
          '\t\t\t<td style="text-align:center">{{weight2}}</td>\n' +
          '\t\t\t<td style="text-align:center">{{weight3}}</td>\n' +
          '\t\t\t<td style="text-align:center">{{weight4}}</td>\n' +
          '\t\t</tr>\n' +
          '\t\t<tr>\n' +
          '\t\t\t<td rowspan="4" style="text-align:center">产科检查</td>\n' +
          '\t\t\t<td style="text-align:center">宫底高度（cm）</td>\n' +
          '\t\t\t<td style="text-align:center">{{fundusHeight1}}</td>\n' +
          '\t\t\t<td style="text-align:center">{{fundusHeight2}}</td>\n' +
          '\t\t\t<td style="text-align:center">{{fundusHeight3}}</td>\n' +
          '\t\t\t<td style="text-align:center">{{fundusHeight4}}</td>\n' +
          '\t\t</tr>\n' +
          '\t\t<tr>\n' +
          '\t\t\t<td style="text-align:center">腹围（cm）</td>\n' +
          '\t\t\t<td style="text-align:center">{{abdominal1}}</td>\n' +
          '\t\t\t<td style="text-align:center">{{abdominal2}}</td>\n' +
          '\t\t\t<td style="text-align:center">{{abdominal3}}</td>\n' +
          '\t\t\t<td style="text-align:center">{{abdominal4}}</td>\n' +
          '\t\t</tr>\n' +
          '\t\t<tr>\n' +
          '\t\t\t<td style="text-align:center">胎位</td>\n' +
          '\t\t\t<td style="text-align:center">{{fetalPosition1}}</td>\n' +
          '\t\t\t<td style="text-align:center">{{fetalPosition2}}</td>\n' +
          '\t\t\t<td style="text-align:center">{{fetalPosition3}}</td>\n' +
          '\t\t\t<td style="text-align:center">{{fetalPosition4}}</td>\n' +
          '\t\t</tr>\n' +
          '\t\t<tr>\n' +
          '\t\t\t<td style="text-align:center">胎心率</td>\n' +
          '\t\t\t<td style="text-align:center">{{fhr1}}</td>\n' +
          '\t\t\t<td style="text-align:center">{{fhr2}}</td>\n' +
          '\t\t\t<td style="text-align:center">{{fhr3}}</td>\n' +
          '\t\t\t<td style="text-align:center">{{fhr4}}</td>\n' +
          '\t\t</tr>\n' +
          '\t\t<tr>\n' +
          '\t\t\t<td colspan="2" style="text-align:center">血压（mmHg）</td>\n' +
          '\t\t\t<td style="text-align:center">{{blood1}}</td>\n' +
          '\t\t\t<td style="text-align:center">{{blood2}}</td>\n' +
          '\t\t\t<td style="text-align:center">{{blood3}}</td>\n' +
          '\t\t\t<td style="text-align:center">{{blood4}}</td>\n' +
          '\t\t</tr>\n' +
          '\t\t<tr>\n' +
          '\t\t\t<td colspan="2" style="text-align:center">血红蛋白（g/L）</td>\n' +
          '\t\t\t<td style="text-align:center">{{hemoglobin1}}</td>\n' +
          '\t\t\t<td style="text-align:center">{{hemoglobin2}}</td>\n' +
          '\t\t\t<td style="text-align:center">{{hemoglobin3}}</td>\n' +
          '\t\t\t<td style="text-align:center">{{hemoglobin4}}</td>\n' +
          '\t\t</tr>\n' +
          '\t\t<tr>\n' +
          '\t\t\t<td colspan="2" style="text-align:center">尿蛋白</td>\n' +
          '\t\t\t<td style="text-align:center">{{urineProtein1}}</td>\n' +
          '\t\t\t<td style="text-align:center">{{urineProtein2}}</td>\n' +
          '\t\t\t<td style="text-align:center">{{urineProtein3}}</td>\n' +
          '\t\t\t<td style="text-align:center">{{urineProtein4}}</td>\n' +
          '\t\t</tr>\n' +
          '\t\t<tr>\n' +
          '\t\t\t<td colspan="2" style="height:50px;text-align:center">其他辅助检查</td>\n' +
          '\t\t\t<td style="text-align:center">{{otherSassist1}}</td>\n' +
          '\t\t\t<td style="text-align:center">{{otherSassist2}}</td>\n' +
          '\t\t\t<td style="text-align:center">{{otherSassist3}}</td>\n' +
          '\t\t\t<td style="text-align:center">{{otherSassist4}}</td>\n' +
          '\t\t</tr>\n' +
          '\t\t<tr>\n' +
          '\t\t\t<td colspan="2" style="height:50px;text-align:center">分类</td>\n' +
          '\t\t\t<td style="text-align:center">{{classify1}}</td>\n' +
          '\t\t\t<td style="text-align:center">{{classify2}}</td>\n' +
          '\t\t\t<td style="text-align:center">{{classify3}}</td>\n' +
          '\t\t\t<td style="text-align:center">{{classify4}}</td>\n' +
          '\t\t<tr>\n' +
          '\t\t\t<td colspan="2" style="height:150px;text-align:center">指导</td>\n' +
          '\t\t\t<td style="text-align:left">{{guidance1}}</td>\n' +
          '\t\t\t<td style="text-align:left">{{guidance2}}</td>\n' +
          '\t\t\t<td style="text-align:left">{{guidance3}}</td>\n' +
          '\t\t\t<td style="text-align:left">{{guidance4}}</td>\n' +
          '\n' +
          '\t\t</tr>\n' +
          '\t\t<tr>\n' +
          '\t\t\t<td colspan="2" style="height:50px;text-align:center">转诊</td>\n' +
          '\t\t\t<td style="text-align:left">{{referral1}}<br/>原因:{{reason1}}<br/>机构及科室{{insandDep1}}</td>\n' +
          '\t\t\t<td style="text-align:left">{{referral2}}<br/>原因:{{reason2}}<br/>机构及科室{{insandDep2}}</td>\n' +
          '\t\t\t<td style="text-align:left">{{referral3}}<br/>原因:{{reason3}}<br/>机构及科室{{insandDep3}}</td>\n' +
          '\t\t\t<td style="text-align:left">{{referral4}}<br/>原因:{{reason4}}<br/>机构及科室{{insandDep4}}</td>\n' +
          '\t\t</tr>\n' +
          '\t\t<tr>\n' +
          '\t\t\t<td colspan="2" style="text-align:center">下次随访日期</td>\n' +
          '\t\t\t<td style="text-align:center">{{nextDate1}}</td>\n' +
          '\t\t\t<td style="text-align:center">{{nextDate2}}</td>\n' +
          '\t\t\t<td style="text-align:center">{{nextDate3}}</td>\n' +
          '\t\t\t<td style="text-align:center">{{nextDate4}}</td>\n' +
          '\t\t</tr>\n' +
          '\t\t<tr>\n' +
          '\t\t\t<td colspan="2" style="text-align:center">随访签名</td>\n' +
          '\t\t\t<td style="text-align:center">{{doctor1}}</td>\n' +
          '\t\t\t<td style="text-align:center">{{doctor2}}</td>\n' +
          '\t\t\t<td style="text-align:center">{{doctor3}}</td>\n' +
          '\t\t\t<td style="text-align:center">{{doctor4}}</td>\n' +
          '\t\t</tr>\n' +
          '\t\t</tbody>\n' +
          '\t</table>\n' +
          '</div>\n' +
          '</body>\n' +
          '</html>\n'
        str = str.replace(/{{name}}/, this.printItem1.name ? this.printItem1.name : '')
        str = str.replace(/{{id}}/, this.printItem1.id ? this.printItem1.id : '')
        str = str.replace(/{{number1}}/, this.printItem1.number ? this.printItem1.number : '')
        str = str.replace(/{{number2}}/, this.printItem2.number ? this.printItem2.number : '')
        str = str.replace(/{{number3}}/, this.printItem3.number ? this.printItem3.number : '')
        str = str.replace(/{{number4}}/, this.printItem4.number ? this.printItem4.number : '')

        str = str.replace(/{{date1}}/, this.printItem1.date ? this.printItem1.date : '')
        str = str.replace(/{{date2}}/, this.printItem2.date ? this.printItem2.date : '')
        str = str.replace(/{{date3}}/, this.printItem3.date ? this.printItem3.date : '')
        str = str.replace(/{{date4}}/, this.printItem4.date ? this.printItem4.date : '')

        str = str.replace(/{{gestationalWeeks1}}/, this.printItem1.gestationalWeeks ? this.printItem1.gestationalWeeks : '')
        str = str.replace(/{{gestationalWeeks2}}/, this.printItem2.gestationalWeeks ? this.printItem2.gestationalWeeks : '')
        str = str.replace(/{{gestationalWeeks3}}/, this.printItem3.gestationalWeeks ? this.printItem3.gestationalWeeks : '')
        str = str.replace(/{{gestationalWeeks4}}/, this.printItem4.gestationalWeeks ? this.printItem4.gestationalWeeks : '')

        str = str.replace(/{{mainSuit1}}/, this.printItem1.mainSuit ? this.printItem1.mainSuit : '')
        str = str.replace(/{{mainSuit2}}/, this.printItem2.mainSuit ? this.printItem2.mainSuit : '')
        str = str.replace(/{{mainSuit3}}/, this.printItem3.mainSuit ? this.printItem3.mainSuit : '')
        str = str.replace(/{{mainSuit4}}/, this.printItem4.mainSuit ? this.printItem4.mainSuit : '')

        str = str.replace(/{{weight1}}/, this.printItem1.weight ? this.printItem1.weight : '')
        str = str.replace(/{{weight2}}/, this.printItem2.weight ? this.printItem2.weight : '')
        str = str.replace(/{{weight3}}/, this.printItem3.weight ? this.printItem3.weight : '')
        str = str.replace(/{{weight4}}/, this.printItem4.weight ? this.printItem4.weight : '')

        str = str.replace(/{{fundusHeight1}}/, this.printItem1.fundusHeight ? this.printItem1.fundusHeight : '')
        str = str.replace(/{{fundusHeight2}}/, this.printItem2.fundusHeight ? this.printItem2.fundusHeight : '')
        str = str.replace(/{{fundusHeight3}}/, this.printItem3.fundusHeight ? this.printItem3.fundusHeight : '')
        str = str.replace(/{{fundusHeight4}}/, this.printItem4.fundusHeight ? this.printItem4.fundusHeight : '')

        str = str.replace(/{{gestationalWeeks1}}/, this.printItem1.gestationalWeeks ? this.printItem1.gestationalWeeks : '')
        str = str.replace(/{{gestationalWeeks2}}/, this.printItem2.gestationalWeeks ? this.printItem2.gestationalWeeks : '')
        str = str.replace(/{{gestationalWeeks3}}/, this.printItem3.gestationalWeeks ? this.printItem3.gestationalWeeks : '')
        str = str.replace(/{{gestationalWeeks4}}/, this.printItem4.gestationalWeeks ? this.printItem4.gestationalWeeks : '')

        str = str.replace(/{{abdominal1}}/, this.printItem1.abdominal ? this.printItem1.abdominal : '')
        str = str.replace(/{{abdominal2}}/, this.printItem2.abdominal ? this.printItem2.abdominal : '')
        str = str.replace(/{{abdominal3}}/, this.printItem3.abdominal ? this.printItem3.abdominal : '')
        str = str.replace(/{{abdominal4}}/, this.printItem4.abdominal ? this.printItem4.abdominal : '')

        str = str.replace(/{{fetalPosition1}}/, this.printItem1.fetalPosition ? this.printItem1.fetalPosition : '')
        str = str.replace(/{{fetalPosition2}}/, this.printItem2.fetalPosition ? this.printItem2.fetalPosition : '')
        str = str.replace(/{{fetalPosition3}}/, this.printItem3.fetalPosition ? this.printItem3.fetalPosition : '')
        str = str.replace(/{{fetalPosition4}}/, this.printItem4.fetalPosition ? this.printItem4.fetalPosition : '')

        str = str.replace(/{{fhr1}}/, this.printItem1.fhr ? this.printItem1.fhr : '')
        str = str.replace(/{{fhr2}}/, this.printItem2.fhr ? this.printItem2.fhr : '')
        str = str.replace(/{{fhr3}}/, this.printItem3.fhr ? this.printItem3.fhr : '')
        str = str.replace(/{{fhr4}}/, this.printItem4.fhr ? this.printItem4.fhr : '')

        str = str.replace(/{{blood1}}/, this.printItem1.blood ? this.printItem1.blood : '')
        str = str.replace(/{{blood2}}/, this.printItem2.blood ? this.printItem2.blood : '')
        str = str.replace(/{{blood3}}/, this.printItem3.blood ? this.printItem3.blood : '')
        str = str.replace(/{{blood4}}/, this.printItem4.blood ? this.printItem4.blood : '')

        str = str.replace(/{{hemoglobin1}}/, this.printItem1.hemoglobin ? this.printItem1.hemoglobin : '')
        str = str.replace(/{{hemoglobin2}}/, this.printItem2.hemoglobin ? this.printItem2.hemoglobin : '')
        str = str.replace(/{{hemoglobin3}}/, this.printItem3.hemoglobin ? this.printItem3.hemoglobin : '')
        str = str.replace(/{{hemoglobin4}}/, this.printItem4.hemoglobin ? this.printItem4.hemoglobin : '')

        str = str.replace(/{{urineProtein1}}/, this.printItem1.urineProtein ? this.printItem1.urineProtein : '')
        str = str.replace(/{{urineProtein2}}/, this.printItem2.urineProtein ? this.printItem2.urineProtein : '')
        str = str.replace(/{{urineProtein3}}/, this.printItem3.urineProtein ? this.printItem3.urineProtein : '')
        str = str.replace(/{{urineProtein4}}/, this.printItem4.urineProtein ? this.printItem4.urineProtein : '')

        str = str.replace(/{{otherSassist1}}/, this.printItem1.otherSassist ? this.printItem1.otherSassist : '')
        str = str.replace(/{{otherSassist2}}/, this.printItem2.otherSassist ? this.printItem2.otherSassist : '')
        str = str.replace(/{{otherSassist3}}/, this.printItem3.otherSassist ? this.printItem3.otherSassist : '')
        str = str.replace(/{{otherSassist4}}/, this.printItem4.otherSassist ? this.printItem4.otherSassist : '')

        str = str.replace(/{{urineProtein1}}/, this.printItem1.urineProtein ? this.printItem1.urineProtein : '')
        str = str.replace(/{{urineProtein2}}/, this.printItem2.urineProtein ? this.printItem2.urineProtein : '')
        str = str.replace(/{{urineProtein3}}/, this.printItem3.urineProtein ? this.printItem3.urineProtein : '')
        str = str.replace(/{{urineProtein4}}/, this.printItem4.urineProtein ? this.printItem4.urineProtein : '')

        str = str.replace(/{{classify1}}/, this.printItem1.classify ? this.printItem1.classify : '')
        str = str.replace(/{{classify2}}/, this.printItem2.classify ? this.printItem2.classify : '')
        str = str.replace(/{{classify3}}/, this.printItem3.classify ? this.printItem3.classify : '')
        str = str.replace(/{{classify4}}/, this.printItem4.classify ? this.printItem4.classify : '')

        str = str.replace(/{{guidance1}}/, this.printItem1.guidance ? this.printItem1.guidance : '')
        str = str.replace(/{{guidance2}}/, this.printItem2.guidance ? this.printItem2.guidance : '')
        str = str.replace(/{{guidance3}}/, this.printItem3.guidance ? this.printItem3.guidance : '')
        str = str.replace(/{{guidance4}}/, this.printItem4.guidance ? this.printItem4.guidance : '')

        str = str.replace(/{{referral1}}/, this.printItem1.referral ? this.printItem1.referral : '')
        str = str.replace(/{{referral2}}/, this.printItem2.referral ? this.printItem2.referral : '')
        str = str.replace(/{{referral3}}/, this.printItem3.referral ? this.printItem3.referral : '')
        str = str.replace(/{{referral4}}/, this.printItem4.referral ? this.printItem4.referral : '')
        str = str.replace(/{{reason1}}/, this.printItem1.reason ? this.printItem1.reason : '')
        str = str.replace(/{{reason2}}/, this.printItem2.reason ? this.printItem2.reason : '')
        str = str.replace(/{{reason3}}/, this.printItem3.reason ? this.printItem3.reason : '')
        str = str.replace(/{{reason4}}/, this.printItem4.reason ? this.printItem4.reason : '')
        str = str.replace(/{{insandDep1}}/, this.printItem1.insandDep ? this.printItem1.insandDep : '')
        str = str.replace(/{{insandDep2}}/, this.printItem2.insandDep ? this.printItem2.insandDep : '')
        str = str.replace(/{{insandDep3}}/, this.printItem3.insandDep ? this.printItem3.insandDep : '')
        str = str.replace(/{{insandDep4}}/, this.printItem4.insandDep ? this.printItem4.insandDep : '')

        str = str.replace(/{{nextDate1}}/, this.printItem1.nextDate ? this.printItem1.nextDate : '')
        str = str.replace(/{{nextDate2}}/, this.printItem2.nextDate ? this.printItem2.nextDate : '')
        str = str.replace(/{{nextDate3}}/, this.printItem3.nextDate ? this.printItem3.nextDate : '')
        str = str.replace(/{{nextDate4}}/, this.printItem4.nextDate ? this.printItem4.nextDate : '')
        str = str.replace(/{{doctor1}}/, this.printItem1.doctor ? this.printItem1.doctor : '')
        str = str.replace(/{{doctor2}}/, this.printItem2.doctor ? this.printItem2.doctor : '')
        str = str.replace(/{{doctor3}}/, this.printItem3.doctor ? this.printItem3.doctor : '')
        str = str.replace(/{{doctor4}}/, this.printItem4.doctor ? this.printItem4.doctor : '')

        myWindow.document.write(str)
        myWindow.focus()
        myWindow.document.close()
        myWindow.print()
        return myWindow
      },
      conprint() {
        this.printUsers()
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
      printUsers() {
        const para = {
          page: this.page,
          size: 20,
          filters: { peopleId: this.editForm.peopleId }
        }
        printData(para).then(res => {
          this.printItem = res.detailModelList
          this.printItem1 = this.printItem[0]
          this.printItem2 = this.printItem[1]
          this.printItem3 = this.printItem[2]
          this.printItem4 = this.printItem[3]
          const windows = this.printf(document.getElementById('print').innerHTML)
          windows.close()
        })
      },
      search() {
        this.page = 1
        this.getUsers()
      },
      handleRowChange(row, event) {
        this.dialogStatus = 'detail'
        this.dialogFormVisible = true
        this.editForm = Object.assign({}, row)
        this.editForm.guidance = this.editForm.guidance.split(',')
        this.editForm.otherSassist = this.editForm.otherSassist.split(',')
        this.editForm.area = this.editForm.area.split('/')
        this.edit = false
      },
      // 显示编辑界面
      handleEdit(index, row) {
        this.dialogStatus = 'update'
        this.dialogFormVisible = true
        this.editForm = Object.assign({}, row)
        this.editForm.guidance = this.editForm.guidance.split(',')
        this.editForm.otherSassist = this.editForm.otherSassist.split(',')
        this.editForm.area = this.editForm.area.split('/')
        this.edit = true
      },
      // 显示新增界面
      handleAdd() {
        this.dialogStatus = 'create'
        this.dialogFormVisible = true
        this.edit = false
        this.editForm = {
          id: '',
          name: '',
          area: [],
          date: '',
          number: '',
          peopleId: '',
          status: '',
          gestationalWeeks: '',
          mainSuit: '',
          weight: '',
          fundusHeight: '',
          abdominal: '',
          fetalPosition: '',
          fhr: '',
          blood: '',
          hemoglobin: '',
          urineProtein: '',
          otherSassist: [],
          classify: '',
          guidance: [],
          guidances: '',
          referral: '',
          reason: '',
          insandDep: '',
          nextDate: '',
          doctor: '',
          creator: this.$store.getters.name,
          createDate: null,
          modifier: '',
          modifyDate: null
        }
      },
      // 获取用户列表filters.date_s
      getUsers() {
        this.filters.date_s =
          !this.filters.date_s || this.filters.date_s === ''
            ? ''
            : util.formatDate.format(new Date(this.filters.date_s), 'yyyy-MM-dd')
        this.filters.date_e =
          !this.filters.date_e || this.filters.date_e === ''
            ? ''
            : util.formatDate.format(new Date(this.filters.date_e), 'yyyy-MM-dd')
        const para = {
          page: this.page,
          size: 20,
          filters: {
            name: this.filters.name,
            date_s: this.filters.date_s,
            date_e: this.filters.date_e,
            peopleId: this.filters.peopleId,
            insandDep: this.findForm.insandDep,
            doctor: this.findForm.doctor
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
          doctor: ''
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
            if (this.editForm.peopleId === '') {
              this.$el.querySelector('#peopleId').scrollIntoView()
              this.$message.error('请填写身份证')
              return false
            } else if (this.$refs.number.value === ''|| this.$refs.number.value === undefined) {
              this.$el.querySelector('#number').scrollIntoView()
              this.$message.error('请选择第几次填表')
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
          .catch(() => {
          })
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
                para.nextDate =
                  !para.nextDate || para.nextDate === ''
                    ? ''
                    : util.formatDate.format(new Date(para.nextDate), 'yyyy-MM-dd')
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
                para.otherSassist = ''
                for (const itm of this.editForm.otherSassist) {
                  if (itm !== '') {
                    para.otherSassist += itm
                    para.otherSassist += ','
                  }
                }
                para.otherSassist = para.otherSassist.substr(0, para.otherSassist.length - 1)
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
                    if (follow[0].nextFollow === '' || follow[0].nextFollow === null) {
                      follow[0].nextFollow = para.nextDate
                    } else if (follow[0].nextFollow < para.nextDate || para.nextDate === '' || para.nextDate === null) {
                      follow[0].nextFollow = para.nextDate
                    }
                    editFollow(follow[0]).then(res => {})
                  } else {
                    followData.lastFollow = para.date
                    followData.nextFollow = para.nextDate
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
                const para = Object.assign({}, this.editForm)
                console.log(para)
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
                para.otherSassist = ''
                for (const itm of this.editForm.otherSassist) {
                  if (itm !== '') {
                    para.otherSassist += itm
                    para.otherSassist += ','
                  }
                }
                para.otherSassist = para.otherSassist.substr(0, para.otherSassist.length - 1)
                para.date =
                  !para.date || para.date === ''
                    ? ''
                    : util.formatDate.format(new Date(para.date), 'yyyy-MM-dd')
                para.nextDate =
                  !para.nextDate || para.nextDate === ''
                    ? ''
                    : util.formatDate.format(new Date(para.nextDate), 'yyyy-MM-dd')
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
                    if (follow[0].nextFollow === '' || follow[0].nextFollow === null) {
                      follow[0].nextFollow = para.nextDate
                    } else if (follow[0].nextFollow < para.nextDate || para.nextDate === '' || para.nextDate === null) {
                      follow[0].nextFollow = para.nextDate
                    }
                    editFollow(follow[0]).then(res => {})
                  } else {
                    followData.lastFollow = para.date
                    followData.nextFollow = para.nextDate
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
          .catch(() => {
          })
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


