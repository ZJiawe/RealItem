<template>
  <section class="app-container">

    <!--工具条-->
    <el-col :span="24" class="toolbar">
      <el-form :inline="true" :model="filters" @submit.native.prevent>
        <el-form-item>
          <el-input v-model="filters.name" placeholder="姓名"></el-input>
        </el-form-item>
        <el-form-item>
          <el-date-picker type="date" placeholder="随访日期" v-model="filters.date" style="width: 100%;"></el-date-picker>
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
    <el-scrollbar>
    <!--查询-->
    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible2" :close-on-click-modal="false" :width="400" center >
      <el-form :model="findForm" label-width="130px" :rules="findFormRules" ref="findForm">
        <el-row>
          <el-col :span="11">
            <el-form-item label="姓名">
              <el-input v-model="filters.name" placeholder="姓名"></el-input>
            </el-form-item>
          </el-col></el-row>
        <el-row>
          <el-col :span="11">
            <el-form-item label="随访日期">
              <el-date-picker type="date" placeholder="随访日期" v-model="filters.date" style="width: 100%;"></el-date-picker>
            </el-form-item>
          </el-col>
          <el-col :span="11">
            <el-form-item label="出院日期">
              <el-date-picker type="date" placeholder="选择日期" v-model="findForm.leaveHospitale" style="width: 100%;"></el-date-picker>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="11">
            <el-form-item label="机构及科室">
              <el-input v-model="findForm.insandDep"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="11">
            <el-form-item label="随访医生签名">
              <el-input v-model="findForm.doctor"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>

      <div slot="footer" class="dialog-footer">
        <el-button type="primary" v-on:click="getUsers" style="background-color:#4db2ee;border-color:transparent" icon="el-icon-search">查询</el-button>
      </div>
    </el-dialog>
    </el-scrollbar>
    <!--编辑界面-->
    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible" :close-on-click-modal="false" center style="overflow:auto" >
      <div id="print">
      <el-form :model="editForm" label-width="110px" :rules="editFormRules" ref="editForm">
              <el-row>
                <el-col :span="12">
                  <el-form-item label="编号" prop="id">
                    <el-input v-model.number="editForm.id"></el-input>
                  </el-form-item>
                </el-col>
                <el-col :span="12">
                  <el-form-item label="姓名" prop="name">
                    <el-input v-model="editForm.name"></el-input>
                  </el-form-item>
                </el-col>
              </el-row>
              <el-row>
                <el-col :span="12">
                  <el-form-item label="随访日期">
                    <el-date-picker type="date" placeholder="选择日期" v-model="editForm.date" style="width: 100%;"></el-date-picker>
                  </el-form-item>
                </el-col>
                <el-col :span="12">
                  <el-form-item label="体温">
                    <el-input v-model="editForm.temperature">
                      <template slot="append">℃</template>
                    </el-input>
                  </el-form-item>
                </el-col>
              </el-row>
              <el-row>
                <el-col :span="12">
                  <el-form-item label="分娩日期">
                    <el-date-picker type="date" placeholder="选择日期" v-model="editForm.childBirth" style="width: 100%;"></el-date-picker>
                  </el-form-item>
                </el-col>
                <el-col :span="12">
                  <el-form-item label="出院日期">
                    <el-date-picker type="date" placeholder="选择日期" v-model="editForm.leaveHospitale" style="width: 100%;"></el-date-picker>
                  </el-form-item>
                </el-col>
              </el-row>
              <el-row>
                <el-col :span="12">
                  <el-form-item label="一般健康状况">
                    <el-input v-model="editForm.healthy"></el-input>
                  </el-form-item>
                </el-col>
                <el-col :span="12">
                  <el-form-item label="一般心理状况">
                    <el-input v-model="editForm.psychology"></el-input>
                  </el-form-item>
                </el-col>
              </el-row>
              <el-row>
                <el-col :span="24">
                  <el-form-item label="血压">
                    <el-input v-model="editForm.blood">
                      <template slot="append">mmHg</template>
                    </el-input>
                  </el-form-item>
                </el-col>
              </el-row>
              <el-row>
                <table>
                  <tr>
                    <td width="290">
                      <el-form-item label="乳房">
                        <el-radio-group v-model="editForm.breast">
                          <el-radio label="未见异常"></el-radio>
                          <el-radio label="异常"></el-radio>
                        </el-radio-group>
                      </el-form-item>
                    </td>
                    <td width="290">
                      <el-form-item label="恶露">
                        <el-radio-group v-model="editForm.lochia">
                          <el-radio label="未见异常"></el-radio>
                          <el-radio label="异常"></el-radio>
                        </el-radio-group>
                      </el-form-item>
                    </td>
                    <td>
                      <el-form-item label="分类">
                        <el-radio-group v-model="editForm.classify">
                          <el-radio label="未见异常"></el-radio>
                          <el-radio label="异常"></el-radio>
                        </el-radio-group>
                      </el-form-item>
                    </td>
                  </tr>
                </table>
              </el-row>
              <el-row>
                <table>
                  <tr>
                    <td width="290">
                      <el-form-item label="子宫">
                        <el-radio-group v-model="editForm.uterus">
                          <el-radio label="未见异常"></el-radio>
                          <el-radio label="异常"></el-radio>
                        </el-radio-group>
                      </el-form-item>
                    </td>
                    <td width="290">
                      <el-form-item label="伤口">
                        <el-radio-group v-model="editForm.wound">
                          <el-radio label="未见异常"></el-radio>
                          <el-radio label="异常"></el-radio>
                        </el-radio-group>
                      </el-form-item>
                    </td>
                    <td>
                      <el-form-item label="转诊">
                        <el-radio-group v-model="editForm.referral">
                          <el-radio label="无"></el-radio>
                          <el-radio label="有"></el-radio>
                        </el-radio-group>
                      </el-form-item>
                    </td>
                  </tr>
                </table>
              </el-row>
              <el-row>
                <el-col>
                  <el-form-item label="指导">
                    <el-checkbox-group v-model="editForm.guidance" style="width:100%">
                      <el-checkbox v-for="item in guidance" :key="item.value" :name="type" :label="item.label" :value="item.value"></el-checkbox>
                    </el-checkbox-group>
                  </el-form-item>
                </el-col>
              </el-row>
              <el-row>
                <el-col :span="12">
                  <el-form-item label="其他">
                    <el-input v-model="editForm.other"></el-input>
                  </el-form-item>
                </el-col>
                <el-col :span="12">
                  <el-form-item label="原因">
                    <el-input v-model="editForm.reason"></el-input>
                  </el-form-item>
                </el-col>
              </el-row>
              <el-row>
                <el-col :span="24">
                  <el-form-item label="机构及科室" prop="insandDep">
                    <el-input v-model="editForm.insandDep"></el-input>
                  </el-form-item>
                </el-col>
              </el-row>
              <el-row>
                <el-col :span="12">
                  <el-form-item label="下次随访日期">
                    <el-date-picker type="date" placeholder="选择日期" v-model="editForm.nextDate" style="width: 100%;"></el-date-picker>
                  </el-form-item>
                </el-col>
                <el-col :span="12">
                  <el-form-item label="随访医生签名">
                    <el-input v-model="editForm.doctor"></el-input>
                  </el-form-item>
                </el-col>
              </el-row>
      </el-form>
      </div>
      <div slot="footer" class="dialog-footer">
        <el-button @click="conprint()">打印</el-button>
        <el-button v-if="dialogStatus=='create'" type="primary" @click="createData">添加</el-button>
        <el-button v-else type="primary" @click="updateData" v-if="edit">修改</el-button>
        <el-button @click.native="dialogFormVisible=false">取消</el-button>
      </div>
    </el-dialog>

    <!--列表-->
    <el-table :data="users" :height="tableHeight" highlight-current-row @selection-change="selsChange" border style="width: 100%;margin-top:-20px;" @row-dblclick="handleRowChange">
<!--      <el-table-column type="selection" width="55">
      </el-table-column>-->
      <el-table-column type="index" width="60">
      </el-table-column>
      <el-table-column prop="id" label="编号" width="60">
      </el-table-column>
      <el-table-column prop="name" label="姓名" width="80" style="text-align:center" >
      </el-table-column>
      <el-table-column prop="date" label="随访日期" width="120">
      </el-table-column>
      <el-table-column prop="childBirth" label="分娩日期" width="120">
      </el-table-column>
      <el-table-column prop="leaveHospitale" label="出院日期" width="120">
      </el-table-column>
      <el-table-column prop="temperature" v-if='show' label="体温" min-width="80">
      </el-table-column>
      <el-table-column prop="healthy" label="一般健康状况" min-width="120">
      </el-table-column>
      <el-table-column prop="psychology" v-if='false' label="一般心理状况" min-width="120">
      </el-table-column>
      <el-table-column prop="blood" v-if='false' label="血压(mmHg)" min-width="150">
      </el-table-column>
      <el-table-column prop="breast" v-if='false' label="乳房" min-width="80">
      </el-table-column>
      <el-table-column prop="lochia" v-if='false' label="恶露" min-width="140">
      </el-table-column>
      <el-table-column prop="wound" v-if='false' label="伤口" min-width="100">
      </el-table-column>
      <el-table-column prop="uterus" v-if='false' label="子宫" min-width="100">
      </el-table-column>
      <el-table-column prop="other" v-if='false' label="其他" min-width="120">
      </el-table-column>
      <el-table-column prop="classify" v-if='false' label="分类" min-width="120">
      </el-table-column>
      <el-table-column prop="guidance" v-if='false' label="指导" min-width="120">
      </el-table-column>
      <el-table-column prop="insandDep" label="机构及科室" min-width="120">
      </el-table-column>
      <el-table-column prop="referral" v-if='false' label="转诊" min-width="120">
      </el-table-column>
      <el-table-column prop="reason" v-if='false' label="原因" min-width="120">
      </el-table-column>
      <el-table-column prop="nextDate" label="下次随访日期" min-width="120">
      </el-table-column>
      <el-table-column prop="doctor" label="随访医生" min-width="120">
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
      <!--      <el-button type="danger" @click="batchRemove" :disabled="this.sels.length===0">批量删除</el-button>-->
      <el-pagination layout="prev, pager, next" :current-page.sync="page" @current-change="handleCurrentChange" :page-size="10" :total="total" style="float:right;">
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
    addUser
  } from '@/api/keypopulationPostpartum'
  export default {
    data() {
      return {
        edit: true,
        activeName: 'first',
        tableHeight: window.innerHeight - 220,
        guidance: [
          { value: '个人卫生', label: '个人卫生' },
          { value: '心理', label: '心理' },
          { value: '营养', label: '营养' },
          { value: '母乳喂养', label: '母乳喂养' },
          { value: '新生儿护理与喂养', label: '新生儿护理与喂养' },
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
          date: null
        },
        ceshi: '',
        users: [],
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
          id: [
            { required: true, message: '编号不能为空', trigger: 'blur' },
            { type: 'number', message: '编号必须为数字值', trigger: 'blur' }
          ],
          insandDep: [
            { required: true, message: '输入正确的机构及科室', trigger: 'blur' }
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
          childBirth: '',
          leaveHospitale: '',
          temperature: '',
          healthy: '',
          psychology: '',
          blood: '',
          breast: '',
          lochia: '',
          wound: '',
          uterus: '',
          other: '',
          classify: '',
          guidance: [],
          insandDep: '',
          referral: '',
          nextDate: '',
          doctor: ''
        },
        // 查找界面数据
        findForm: {
          insandDep: '',
          doctor: '',
          leaveHospitale: null
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
          '\t\t<span style="margin-left:34px;">姓名:{{name}}</span>\n' +
          '\t\t<span style="margin-left:510px;">NO:{{id}}</span> \n' +
          '\t</div>\n' +
          '\t<table id="table"  border="1"  cellspacing="0" bordercolor="#000000" style="width:730px;margin-left:30px;margin-top:5px;border-collapse:collapse;"  width = "80%">\n' +
          '\t<tbody>\n' +
          '\t\t<tr>\n' +
          '\t\t\t<td style="width: 20%;text-align:center">随访日期</td>\n' +
          '\t\t\t<td colspan="5" style="text-align:center">{{date}}</td>\n' +
          '\t\t</tr>\n' +
          '\t\t<tr>\n' +
          '\t\t\t<td style="word-wrap:break-word;text-align:center">分娩日期</td>\n' +
          '\t\t\t<td colspan="2" style="text-align:center">{{childBirth}}</td>\n' +
          '\t\t\t<td style="word-wrap:break-word;text-align:center">出院日期</td>\n' +
          '\t\t\t<td colspan="2" style="text-align:center">{{leaveHospitale}}</td>\n' +
          '\t\t</tr>\n' +
          '\t\t<tr>\n' +
          '\t\t\t<td style="word-wrap:break-word;text-align:center">体温(℃)</td>\n' +
          '\t\t\t<td colspan="5" style="text-align:center">{{temperature}}</td>\n' +
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
          '\t\t\t<td colspan="5" style="text-align:center">{{classify}}</td>\n' +
          '\t\t</tr>\n' +
          '\t\t<tr>\n' +
          '\t\t\t<td style="height:150px;text-align:center">指导</td>\n' +
          '\t\t\t<td colspan="5" style="text-align:left">{{guidance}}</td>\n' +
          '\t\t</tr>\n' +
          '\t\t<tr>\n' +
          '\t\t\t<td style="height:50px;text-align:center">转诊</td>\n' +
          '\t\t\t<td colspan="5" style="text-align:left">{{referral}}<br/>原因:{{reason}}<br/>{{insandDep}}</td>\n' +
          '\t\t</tr>\n' +
          '\t\t<tr>\n' +
          '\t\t\t<td style="text-align:center">下次随访日期</td>\n' +
          '\t\t\t<td colspan="5" style="text-align:center">{{nextDate}}</td>\n' +
          '\t\t</tr>\n' +
          '\t\t<tr>\n' +
          '\t\t\t<td style="text-align:center">随访签名</td>\n' +
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
        str = str.replace(/{{temperature}}/, this.editForm.temperature ? this.editForm.temperature : '')
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
        str = str.replace(/{{insandDep}}/, this.editForm.insandDep ? this.editForm.insandDep : '')
        str = str.replace(/{{referral}}/, this.editForm.referral ? this.editForm.referral : '')
        str = str.replace(/{{reason}}/, this.editForm.reason ? this.editForm.reason : '')
        str = str.replace(/{{nextDate}}/, this.editForm.nextDate ? this.editForm.nextDate : '')
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
          childBirth: '',
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
          classify: '',
          guidance: [],
          insandDep: '',
          referral: '',
          reason: '',
          nextDate: '',
          doctor: ''
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
        this.filters.date =
          !this.filters.date || this.filters.date === ''
            ? null
            : util.formatDate.format(new Date(this.filters.date), 'yyyy-MM-dd')
        this.findForm.leaveHospitale =
          !this.findForm.leaveHospitale || this.findForm.leaveHospitale === ''
            ? null
            : util.formatDate.format(new Date(this.findForm.leaveHospitale), 'yyyy-MM-dd')
        const para = {
          page: this.page,
          name: this.filters.name,
          date: this.filters.date,
          insandDep: this.findForm.insandDep,
          doctor: this.findForm.doctor,
          leaveHospitale: this.findForm.leaveHospitale
        }
        getUserListPage(para).then(res => {
          this.total = res.data.total
          this.users = res.data.hhh
          if (this.page > 1 && (this.page - 1) * 10 >= this.total) {
            this.page = this.page - 1
            this.getUsers()
          }
        })
        this.dialogFormVisible2 = false
        this.findForm = {
          insandDep: '',
          doctor: '',
          leaveHospitale: null
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
                para.date =
                  !para.date || para.date === ''
                    ? null
                    : util.formatDate.format(new Date(para.date), 'yyyy-MM-dd')
                para.childBirth =
                  !para.childBirth || para.childBirth === ''
                    ? null
                    : util.formatDate.format(new Date(para.childBirth), 'yyyy-MM-dd')
                para.leaveHospitale =
                  !para.leaveHospitale || para.leaveHospitale === ''
                    ? null
                    : util.formatDate.format(new Date(para.leaveHospitale), 'yyyy-MM-dd')
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
                this.editForm.id = (parseInt(Math.random() * 100)).toString() // mock a id
                const para = Object.assign({}, this.editForm)
                console.log(para)
                para.date =
                  !para.date || para.date === ''
                    ? null
                    : util.formatDate.format(new Date(para.date), 'yyyy-MM-dd')
                para.childBirth =
                  !para.childBirth || para.childBirth === ''
                    ? null
                    : util.formatDate.format(new Date(para.childBirth), 'yyyy-MM-dd')
                para.leaveHospitale =
                  !para.leaveHospitale || para.leaveHospitale === ''
                    ? null
                    : util.formatDate.format(new Date(para.leaveHospitale), 'yyyy-MM-dd')
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

