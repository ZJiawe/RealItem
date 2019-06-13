<template>
  <section class="app-container">
    <!--工具条-->
    <el-col :span="24" class="toolbar" style="padding-bottom: 0px;">
      <el-form :inline="true" :model="filters" @submit.native.prevent>
        <el-form-item>
          <el-input v-model="filters.peopleId" placeholder="身份证号" auto-complete="off"></el-input>
        </el-form-item>
          <el-form-item>
            <el-date-picker type="date" placeholder="(随访/督促)开始日期" v-model="filters.date_s" style="width: 100%;"></el-date-picker>
          </el-form-item>
        <el-form-item>
          -
        </el-form-item>
          <el-form-item>
            <el-date-picker type="date" placeholder="(随访/督促)结束日期" v-model="filters.date_e" style="width: 100%;"></el-date-picker>
          </el-form-item>
        <el-form-item>
          <el-button type="primary" v-on:click="search" style="background-color:#4db2ee;border-color:transparent" icon="el-icon-search">查询</el-button>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" v-on:click="findbtn" style="background-color:#4db2ee;border-color:transparent" icon="el-icon-search">高级查询</el-button>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="handleAdd" icon="el-icon-circle-plus-outline">新增</el-button>
        </el-form-item>
      </el-form>
    </el-col>
    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible" :close-on-click-modal="false" center>
      <div id="print">
      <el-form :model="editForm" label-width="110px" :rules="editFormRules" ref="editForm" >
        <EasyScrollbar>
          <div class="item" v-bind:style="{ height: tablebodyHeight + 'px'}">
            <el-tabs v-model="activeName">
              <el-row>
                <el-col :span="12" >
                  <el-form-item label="创建人" v-if="dialogStatus==='detail'">
                    <el-input v-model="editForm.creator" style="width: 100%" v-if="dialogStatus==='detail'" readonly></el-input>
                  </el-form-item>
                </el-col>
                <el-col :span="12">
                  <el-form-item label="创建时间" v-if="dialogStatus==='detail'">
                    <el-date-picker type="date" placeholder="选择日期" v-model="editForm.createDate" style="width: 100%" v-if="dialogStatus==='detail'" readonly></el-date-picker>
                  </el-form-item>
                </el-col>
              </el-row>
              <el-row>
                <el-col :span="12" >
                  <el-form-item label="修改人" v-if="dialogStatus==='detail'">
                    <el-input v-model="editForm.modifier" style="width: 100%" v-if="dialogStatus==='detail'" readonly></el-input>
                  </el-form-item>
                </el-col>
                <el-col :span="12">
                  <el-form-item label="修改时间" v-if="dialogStatus==='detail'">
                    <el-date-picker type="date" placeholder="选择日期" v-model="editForm.modifyDate" style="width: 100%" v-if="dialogStatus==='detail'" readonly></el-date-picker>
                  </el-form-item>
                </el-col>
              </el-row>
              <el-row>
                  <el-form-item label="ID" v-if="false">
                    <el-input v-model="editForm.id"></el-input>
                  </el-form-item>
                <el-col :span="12">
                  <el-form-item label="区域">
                    <el-input v-model="editForm.area"></el-input>
                  </el-form-item>
                </el-col>
                <el-col :span="12">
                  <el-form-item label="第几次" prop="number">
                    <el-radio-group v-model="editForm.number">
                      <el-radio label="第二次">二</el-radio>
                      <el-radio label="第三次">三</el-radio>
                      <el-radio label="第四次">四</el-radio>
                      <el-radio label="第五次">五</el-radio>
                    </el-radio-group>
                  </el-form-item>
                </el-col>
              </el-row>
              <el-row>
                <el-col :span="12">
                  <el-form-item label="身份证号" prop="peopleId">
                    <el-input v-model="editForm.peopleId" auto-complete="off"></el-input>
                  </el-form-item>
                </el-col>
                <el-col :span="12">
                  <el-form-item label="姓名" prop="name">
                    <el-input v-model="editForm.name" auto-complete="off"></el-input>
                  </el-form-item>
                </el-col>
              </el-row>
              <el-row>
                <el-col :span="12">
                  <el-form-item label="(随访/督促)日期" label-width="140px" style="margin-left: -30px;">
                    <el-date-picker type="date" placeholder="选择日期" v-model="editForm.date" style="width: 100%;"></el-date-picker>
                  </el-form-item>
                </el-col>
                <el-col :span="5">
                  <el-form-item label="随访状态">
                    <el-radio-group v-model="editForm.status">
                      <table>
                        <tr>
                          <td><el-radio class="radio" label="规范"></el-radio></td>
                          <td width="50px"><el-radio class="radio" label="失访"></el-radio></td>
                        </tr>
                      </table>
                    </el-radio-group>
                  </el-form-item>
                </el-col>
                <el-col :span="7">
                  <el-form-item label="孕周">
                    <!--<el-input v-model="editForm.gestationalWeeks"></el-input>-->
                    <el-input-number v-model="editForm.gestationalWeeks" :min="1" :max="20" label="描述文字" style="width:100%;"></el-input-number>
                  </el-form-item>
                </el-col>
              </el-row>
              <el-row>
                <el-col :span="6">
                  <el-form-item label="主诉">
                    <el-input v-model="editForm.mainSuit"></el-input>
                  </el-form-item>
                </el-col>
                <el-col :span="6">
                  <el-form-item label="体重" label-width="50px">
                    <el-input v-model="editForm.weight">
                      <template slot="append">Kg</template>
                    </el-input>
                  </el-form-item>
                </el-col>

                <el-col :span="7">
                  <el-form-item label="宫底高度">
                    <el-input v-model="editForm.fundusHeight">
                      <template slot="append">cm</template>
                    </el-input>
                  </el-form-item>
                </el-col>
                <el-col :span="5">
                  <el-form-item label="腹围" label-width="50px">
                    <el-input v-model="editForm.abdominal">
                      <template slot="append">cm</template>
                    </el-input>
                  </el-form-item>
                </el-col>
              </el-row>
              <el-row>
                <el-col :span="6">
                  <el-form-item label="胎位">
                    <el-input v-model="editForm.fetalPosition"></el-input>
                  </el-form-item>
                </el-col>
                <el-col :span="6">
                  <el-form-item label="胎心率" label-width="65px">
                    <el-input v-model="editForm.fhr">
                      <template slot="append">次/分钟</template>
                    </el-input>
                  </el-form-item>
                </el-col>
                <el-col :span="6">
                  <el-form-item label="血压" label-width="60px">
                    <el-input v-model="editForm.blood">
                      <template slot="append">mmHg</template>
                    </el-input>
                  </el-form-item>
                </el-col>
                <el-col :span="6" >
                  <el-form-item label="血红蛋白" label-width="80px">
                    <el-input v-model="editForm.hemoglobin">
                      <template slot="append">g/L</template>
                    </el-input>
                  </el-form-item>
                </el-col>
              </el-row>
              <el-row>
                <el-col :span="6">
                  <el-form-item label="尿蛋白">
                    <el-input v-model="editForm.urineProtein"></el-input>
                  </el-form-item>
                </el-col>
                <el-col :span="12">
                  <el-form-item label="其他辅助检查">
                    <el-checkbox-group v-model="editForm.otherSassist">
                      <table>
                        <tr>
                          <td width="100"><el-checkbox label="肝功能" name="type"></el-checkbox></td>
                          <td width="100"><el-checkbox label="乙肝两对半" name="type"></el-checkbox></td>
                          <td width="100"><el-checkbox label="肾功能" name="type"></el-checkbox></td>
                          <td width="110"><el-checkbox label="4B超" name="type"></el-checkbox></td>
                        </tr>
                      </table>
                    </el-checkbox-group>
                  </el-form-item>
                </el-col>
                <el-col :span="6">
                  <el-form-item label="分类" label-width="50px">
                    <el-radio-group v-model="editForm.classify">
                      <el-radio  class="radio" label="未见异常" ></el-radio>
                      <el-radio  class="radio" label="异常"></el-radio>
                    </el-radio-group>
                  </el-form-item>
                </el-col>
                <el-col :span="14">
                  <el-form-item label="指导">
                    <el-checkbox-group v-model="editForm.guidance" style="width:98%">
                      <el-checkbox v-for="item in guidance" :key="item.value" :name="type" :label="item.label" :value="item.value"></el-checkbox>
                    </el-checkbox-group>
                  </el-form-item>
                </el-col>
                <el-col :span="10">
                  <el-input v-model="editForm.guidances" style="width:405px;margin-left:-20px;"></el-input>
                </el-col>
                </el-row>
                <er-row>
                  <el-col :span="6">
                    <el-form-item label="转诊">
                      <el-radio-group v-model="editForm.referral">
                        <el-radio class="radio" label="无"></el-radio>
                        <el-radio class="radio" label="有"></el-radio>
                      </el-radio-group>
                    </el-form-item>
                  </el-col>
                  <el-col :span="6">
                    <el-form-item label="原因" label-width="53px">
                      <el-input v-model="editForm.reason"></el-input>
                    </el-form-item>
                  </el-col>
                  <el-col :span="12">
                    <el-form-item label="机构及科室" prop="insandDep" label-width="120px">
                      <el-input v-model="editForm.insandDep"></el-input>
                    </el-form-item>
                  </el-col>
                </er-row>
                <el-col :span="12">
                  <el-form-item label="下次随访日期">
                    <el-date-picker type="date" placeholder="选择日期" v-model="editForm.nextDate" style="width: 100%;"></el-date-picker>
                  </el-form-item>
                </el-col>
                <el-col :span="12">
                  <el-form-item label="随访医生签名" prop="doctor" label-width="120px">
                    <el-input v-model="editForm.doctor"></el-input>
                  </el-form-item>
                </el-col>
            </el-tabs>
          </div>
        </EasyScrollbar>
      </el-form>
      </div>
      <div slot="footer" class="dialog-footer">
        <el-button @click="conprint()">打印</el-button>
        <el-button v-if="dialogStatus=='create'" type="primary" @click="createData">添加</el-button>
        <el-button v-else type="primary" v-if="edit" @click="createData">新增修改</el-button>
        <el-button v-else type="primary" @click="updateData" v-if="edit">修改</el-button>
        <el-button @click.native="dialogFormVisible=false">取消</el-button>
      </div>
    </el-dialog>
    <!--查找-->
    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible2" :close-on-click-modal="false" center>
      <el-form :model="findForm" label-width="110px" :rules="findFormRules" ref="findForm">
            <el-form ref="findForm" :model="findForm" label-width="120px">
              <el-row>
                <el-col :span="12">
                  <el-form-item label="姓名">
                    <el-input v-model="filters.name"></el-input>
                  </el-form-item>
                </el-col>
                <el-col :span="12">
                  <el-form-item label="机构及科室">
                    <el-input v-model="findForm.insandDep"></el-input>
                  </el-form-item>
                </el-col>
              </el-row>

              <el-row>
                <el-col :span="12">
                  <el-form-item label="(随访/督促)日期">
                      <el-date-picker type="date" placeholder="开始日期" v-model="filters.date_s" style="width: 100%;"></el-date-picker>
                      <el-date-picker type="date" placeholder="结束日期" v-model="filters.date_e" style="width: 100%;"></el-date-picker>
                  </el-form-item>
                </el-col>
                <el-col :span="12">
                  <el-form-item label="随访医生签名">
                    <el-input v-model="findForm.doctor"></el-input>
                  </el-form-item>
                </el-col>
              </el-row>
            </el-form>
      </el-form>

      <div slot="footer" class="dialog-footer">
        <el-button type="primary" v-on:click="getUsers" style="background-color:#4db2ee;border-color:transparent" icon="el-icon-search">查询</el-button>
      </div>
    </el-dialog>
    <!--列表-->
    <el-table :data="users" :height="tableHeight" highlight-current-row @selection-change="selsChange" @row-dblclick="handleRowChange" border style="width: 100%;" >
<!--      <el-table-column type="selection" width="55">
      </el-table-column>-->
      <el-table-column type="index" width="60">
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
      <el-table-column prop="creator" label="创建人" min-width="120" >
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
          <a size="14px" @click="handleEdit(scope.$index, scope.row)" style="color:#4DB2EE;margin-right:20px;">编辑</a>
          <a size="14px" @click="handleDel(scope.$index, scope.row)" style="color:#FB4141">删除</a>
        </template>
      </el-table-column>
    </el-table>
    <!--工具条-->
    <el-col :span="24" class="toolbar">
<!--      <el-button type="danger" @click="batchRemove" :disabled="this.sels.length===0">批量删除</el-button>-->
      <el-pagination layout="prev, pager, next" :current-page.sync="page" @current-change="handleCurrentChange" :page-size="20" :total="total" style="float:right;">
      </el-pagination>
    </el-col>
  </section>
</template>
<script>
  import util from '@/utils/table.js'
  import {
    getUserListPage,
    removeUser,
    batchRemoveUser,
    editUser,
    addUser,
    print
  } from '@/api/keypopulationPrenatal'
  export default {
    data() {
      return {
        edit: true,
        activeName: 'first',
        tableHeight: window.innerHeight - 210,
        tablebodyHeight: window.innerHeight - 320,
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
            { required: true, message: '输入正确的姓名', trigger: 'blur' },
            { min: 2, max: 5, message: '长度在 2 到 5 个字符', trigger: 'blur' },
            { pattern: /^[\u4E00-\u9FA5]+$/, message: '用户名只能为中文'
            }
          ],
          insandDep: [
            { required: true, message: '输入正确的机构及科室', trigger: 'blur' }
          ],
          number: [
            { required: true, message: '第几次必选', trigger: 'blur' }
          ],
          doctor: [
            { required: true, message: '输入医生姓名', trigger: 'blur' }
          ],
          peopleId: [
            { required: true, message: '请输入身份证ID', trigger: 'blur' },
            { pattern: /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/, message: '你的身份证格式不正确' }
          ]
        },
        findFormRules: {
          name: [{ required: true, message: '请输入姓名', trigger: 'blur' }]
        },
        // 编辑界面数据
        editForm: {
          id: '',
          name: '',
          date: '',
          area: '',
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
          name: 'dashboard',
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
          '\t\t<span style="margin-left:510px;">NO:{{id}}</span> \n' +
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
      printUsers() {
        const para = {
          page: this.page,
          size: 20,
          filters: { peopleId: this.editForm.peopleId }
        }
        print(para).then(res => {
          this.printItem = res.detailModelList
          this.printItem1 = this.printItem[0]
          this.printItem2 = this.printItem[1]
          this.printItem3 = this.printItem[2]
          this.printItem4 = this.printItem[3]
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
        this.edit = false
      },
      // 显示编辑界面
      handleEdit(index, row) {
        this.dialogStatus = 'update'
        this.dialogFormVisible = true
        this.editForm = Object.assign({}, row)
        this.editForm.guidance = this.editForm.guidance.split(',')
        this.editForm.otherSassist = this.editForm.otherSassist.split(',')
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
          date: '',
          area: '',
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
            ? null
            : util.formatDate.format(new Date(this.filters.date_s), 'yyyy-MM-dd')
        this.filters.date_e =
          !this.filters.date_e || this.filters.date_e === ''
            ? null
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
        getUserListPage(para).then(res => {
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
      // 删除
      handleDel(index, row) {
        this.$confirm('确认删除该记录吗?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        })
          .then(() => {
            const para = row.id
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
                    ? null
                    : util.formatDate.format(new Date(para.date), 'yyyy-MM-dd')
                para.nextDate =
                  !para.nextDate || para.nextDate === ''
                    ? null
                    : util.formatDate.format(new Date(para.nextDate), 'yyyy-MM-dd')
                para.guidance = ''
                for (const itm of this.editForm.guidance) {
                  if (itm !== '') {
                    para.guidance += itm
                    para.guidance += ','
                  }
                }
                para.guidance = para.guidance.substr(0, para.guidance.length - 1)

                para.otherSassist = ''
                for (const itm of this.editForm.otherSassist) {
                  if (itm !== '') {
                    para.otherSassist += itm
                    para.otherSassist += ','
                  }
                }
                para.otherSassist = para.otherSassist.substr(0, para.otherSassist.length - 1)
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
                    ? null
                    : util.formatDate.format(new Date(para.date), 'yyyy-MM-dd')
                para.nextDate =
                  !para.nextDate || para.nextDate === ''
                    ? null
                    : util.formatDate.format(new Date(para.nextDate), 'yyyy-MM-dd')
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

