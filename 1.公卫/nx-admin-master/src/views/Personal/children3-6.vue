<template>
  <section class="app-container">
    <el-col :span="22" class="toolbar" style="padding-bottom: 0px;">
      <el-form :inline="true" :model="filters" @submit.native.prevent>
        <el-form-item>
          <el-input v-model="filters.name" placeholder="姓名"></el-input>
        </el-form-item>
        <el-form-item label="本次随访时间">
          <el-date-picker type="date" placeholder="开始日期" v-model="filters.startTime" style="width: 100%;"></el-date-picker>
        </el-form-item>
        <el-form-item>
          —
        </el-form-item>
        <el-form-item>
          <el-date-picker type="date" placeholder="结束日期" v-model="filters.endTime" style="width: 100%;"></el-date-picker>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" v-on:click="getUsers" style="background-color:#4db2ee;border-color:transparent" icon="el-icon-search">查询</el-button>
        </el-form-item>
      </el-form>
    </el-col>
    <!--工具条-->
    <el-col :span="2" class="toolbar" style="padding-bottom: 0px;" align="right">
      <el-form :inline="true" :model="filters" @submit.native.prevent>
        <el-form-item>
          <el-button type="primary" @click="handleAdd" icon="el-icon-circle-plus-outline">新增</el-button>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" v-on:click="search" style="background-color:#4db2ee;border-color:transparent" icon="el-icon-search">查询</el-button>
      </div>
    </el-col>

    <!--列表-->
    <el-table :data="users" :height="tableHeight" highlight-current-row @selection-change="selsChange" style="width: 100%;" border @row-dblclick="handleRowChange">
      <el-table-column type="index" width="60">
      </el-table-column>
      <el-table-column prop="name" label="姓名" width="80">
      </el-table-column>
      <el-table-column prop="peopleId" label="身份证号" width="150">
      </el-table-column>
      <el-table-column prop="physical" label="体格发育状态" min-width="120">
      </el-table-column>
      <el-table-column prop="followDate" label="访问日期" min-width="120">
      </el-table-column>
      <el-table-column prop="nextDate" label="下次访问日期" min-width="120">
      </el-table-column>
      <el-table-column prop="doctor" label="责任医生" min-width="120">
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
      <el-button type="danger" v-if="false" @click="batchRemove" :disabled="this.sels.length===0">批量删除</el-button>
      <el-pagination layout="prev, pager, next" :current-page.sync="page" @current-change="handleCurrentChange" :page-size="20" :total="total" style="float:right;">
      </el-pagination>
    </el-col>
    <!--编辑界面-->

    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible" :close-on-click-modal="false" center>
      <el-form :model="editForm" label-width="80px" :rules="editFormRules" ref="editForm">
        <div class="container" id="print">
          <el-tabs v-model="activeName">

            <el-tab-pane :label="`基本信息`" name="first">

              <el-form-item label="主键"  v-if="false">
                <el-input v-model="editForm.id" auto-complete="off"></el-input>
              </el-form-item>

              <el-row>
                <el-col :span="12">
                  <el-form-item label="姓名" prop="name" label-width="100px">
                  <el-input v-model="editForm.name" auto-complete="off"></el-input>
                  </el-form-item>
                </el-col>
                <el-col :span="12" >
                  <el-form-item label="编号" label-width="120px">
                    <el-input  v-model="editForm.number" auto-complete="off" ></el-input>
                  </el-form-item>
                </el-col>
              </el-row>

              <el-row>
                <el-col :span="12">
                  <el-form-item label="年龄" label-width="100px">
                    <el-radio-group v-model="editForm.age">
                      <el-radio label=3岁>3岁</el-radio>
                      <el-radio label=4岁>4岁</el-radio>
                      <el-radio label=5岁>5岁</el-radio>
                      <el-radio label=6岁>6岁</el-radio>
                    </el-radio-group>
                  </el-form-item>
                </el-col>
                <el-col :span="12">
                  <el-form-item label="随访日期" label-width="120px">
                    <el-date-picker type="date"  v-model="editForm.followDate" style="width: 100%;"></el-date-picker>
                  </el-form-item>
                </el-col>
              </el-row>

              <el-row>
                <el-col :span="12" >
                  <el-form-item label="体重" label-width="100px">
                    <el-input v-model="editForm.weight"  auto-complete="off" style="width: 200px">
                      <template slot="append">kg</template>
                    </el-input>
                  </el-form-item>
                </el-col>
                <el-col :span="12" >
                  <el-form-item label="身长" label-width="120px">
                    <el-input v-model="editForm.height"  auto-complete="off" style="width: 200px">
                      <template slot="append">cm</template>
                    </el-input>
                  </el-form-item>
                </el-col>
              </el-row>

              <el-row>
                <el-col :span="12">
                  <el-form-item label="身份证号" prop="peopleId" label-width="100px">
                    <el-input v-model="editForm.peopleId" auto-complete="off"></el-input>
                  </el-form-item>
                </el-col>
                <el-col :span="12" >
                  <el-form-item label="身高/体重" label-width="120px">
                    <el-input v-model="editForm.weightheight"  auto-complete="off"></el-input>
                  </el-form-item>
                </el-col>
              </el-row>

              <el-row>
                <el-col :span="12">
                  <el-form-item label="体格发育评价" label-width="100px">
                    <el-select v-model="editForm.physical" placeholder="请选择" style="width: 100%">
                      <el-option key="正常" label="正常" value="正常"></el-option>
                      <el-option key="低体重" label="低体重" value="低体重"></el-option>
                      <el-option key="消瘦" label="消瘦" value="消瘦"></el-option>
                      <el-option key="生长迟缓" label="生长迟缓" value="生长迟缓"></el-option>
                      <el-option key="超重" label="超重" value="超重"></el-option>
                    </el-select>
                  </el-form-item>
                </el-col>
                <el-col :span="7" >
                  <el-form-item label="出牙/龋齿数" label-width="120px">
                    <el-input v-model="editForm.mouth" auto-complete="off" style="width: 100%">
                      <template slot="append">颗</template>
                    </el-input>
                  </el-form-item>
                </el-col>
                <el-col :span="5" >
                  <el-input v-model="editForm.bmouth" auto-complete="off" style="width: 70%">
                    <template slot="append">颗</template>
                  </el-input>
                </el-col>
              </el-row>

            </el-tab-pane>

            <el-tab-pane :label="`详细情况`" name="second">

              <el-row>
                <el-col :span="12">
                  <el-form-item label="视力" label-width="120px">
                    <el-input v-model="editForm.eye"></el-input>
                  </el-form-item>
                </el-col>
                <el-col :span="12">
                  <el-form-item label="听力">
                    <el-input v-model="editForm.hearing"></el-input>
                  </el-form-item>
                </el-col>
              </el-row>

              <el-row>
                <el-col :span="12">
                  <el-form-item label="胸部" label-width="120px">
                    <el-radio-group v-model="editForm.chest">
                      <el-radio label=未见异常>未见异常</el-radio>
                      <el-radio label=异常>异常</el-radio>
                    </el-radio-group>
                  </el-form-item>
                </el-col>
                <el-col :span="12" >
                  <el-form-item label="腹部">
                    <el-radio-group v-model="editForm.abdomen">
                      <el-radio label=未见异常>未见异常</el-radio>
                      <el-radio label=异常>异常</el-radio>
                    </el-radio-group>
                  </el-form-item>
                </el-col>
              </el-row>

              <el-row>
                <el-col :span="12" >
                  <el-form-item label="血红蛋白值" label-width="120px">
                    <el-input v-model="editForm.hemoglobin" auto-complete="off">
                      <template slot="append" >g/L</template>
                    </el-input>
                  </el-form-item>
                </el-col>
                <el-col :span="12" >
                  <el-form-item label="其他检查">
                    <el-input v-model="editForm.otherCheck" auto-complete="off"></el-input>
                  </el-form-item>
                </el-col>
              </el-row>

              <el-form-item label="发育评估" label-width="120px">
                <el-checkbox-group v-model="editForm.development"a style="width:100%">
                  <el-checkbox v-for="item in development" :key="item.label" :name="type" :label="item.label" :value="item.value"></el-checkbox>
                </el-checkbox-group>
              </el-form-item>

            </el-tab-pane>

            <el-tab-pane :label="`最后结论`" name="third">

              <el-row>
                <el-form-item label="两次随访患病情况" label-width="130px">
                  <el-form-item label="肺炎">
                    <el-col :span="6">
                      <el-input v-model="editForm.pneumonia"  auto-complete="off" style="width: 130px">
                        <template slot="append">次</template>
                      </el-input>
                    </el-col>
                    <el-col :span="8" >
                      <el-form-item label="腹泻">
                        <el-input v-model="editForm.diarrhea"  auto-complete="off">
                          <template slot="append">次</template>
                        </el-input>
                      </el-form-item>
                    </el-col>
                    <el-col :span="8" >
                      <el-form-item label="外伤">
                        <el-input v-model="editForm.trauma"  auto-complete="off">
                          <template slot="append">次</template>
                        </el-input>
                      </el-form-item>
                    </el-col>
                  </el-form-item>
                </el-form-item>
              </el-row>

              <el-form-item label="其他" label-width="130px">
                <el-input v-model="editForm.other"  auto-complete="off"></el-input>
              </el-form-item>

              <el-form-item label="转诊建议" label-width="130px">
                <el-col>
                  <el-radio-group v-model="editForm.referral">
                    <el-radio label="无"></el-radio>
                    <el-radio label="有"></el-radio>
                  </el-radio-group>
                </el-col>
              </el-form-item>
              <el-row>
                <el-col>
                  <el-form-item label="转诊原因" label-width="130px">
                    <el-input type="textarea" rows="3" v-model="editForm.reason"></el-input>
                  </el-form-item>
                </el-col>
              </el-row>

              <el-row>
                <el-col>
                  <el-form-item label="机构及科室" label-width="130px">
                    <el-input type="textarea" rows="3" v-model="editForm.insandDep"></el-input>
                  </el-form-item>
                </el-col>
              </el-row>

              <el-row>
                <el-col>
                  <el-form-item label="指导" label-width="130px">
                    <el-checkbox-group v-model="editForm.guidance"a style="width:100%">
                      <el-checkbox v-for="item in guidance" :key="item.label" :name="type" :label="item.label" :value="item.value"></el-checkbox>
                    </el-checkbox-group>
                  </el-form-item>
                </el-col>
              </el-row>

              <el-form-item label="中医药健康服务" label-width="130px">
                <el-checkbox-group v-model="editForm.medicine"a style="width:100%">
                  <el-checkbox v-for="item in medicine" :key="item.label" :name="type" :label="item.label" :value="item.value"></el-checkbox>
                </el-checkbox-group>
              </el-form-item>

              <el-row>
                <el-col :span="12">
                  <el-form-item label="下次访问日期" label-width="130px">
                    <el-date-picker type="date"  v-model="editForm.nextDate" style="width: 100%"></el-date-picker>
                  </el-form-item>
                </el-col>
                <el-col :span="12">
                  <el-form-item label="随访医生签名" label-width="130px">
                    <el-input  v-model="editForm.doctor" auto-complete="off" style="width: 100%"></el-input>
                  </el-form-item>
                </el-col>
              </el-row>
            </el-tab-pane>

          </el-tabs>

        </div>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="conprint()">打印</el-button>
        <el-button v-if="dialogStatus=='create'" type="primary" @click="createData">添加</el-button>
        <el-button v-else type="primary" v-if="edit" @click="updateData">修改</el-button>
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
    addUser,
    print
  } from '@/api/ThreeSixYearChild'

  export default {
    data() {
      return {
        edit: true,
        tableMeight: window.innerHeight - 220,
        development: [
          { value: '不会说带形容词的句子', label: '不会说带形容词的句子' },
          { value: '不能按要求等待或轮流', label: '不能按要求等待或轮流' },
          { value: '不能独立穿衣', label: '不能独立穿衣' },
          { value: '不会单脚站立', label: '不会单脚站立' }
        ],
        guidance: [
          { value: '喂养指导', label: '喂养指导' },
          { value: '发育指导', label: '发育指导' },
          { value: '防病指导', label: '防病指导' },
          { value: '预防伤害指导', label: '预防伤害指导' },
          { value: '口腔保健指导', label: '口腔保健指导' },
          { value: '其他', label: '其他' }
        ],
        medicine: [
          { value: '中医饮食调养指导', label: '中医饮食调养指导' },
          { value: '中医起居调摄指导', label: '中医起居调摄指导' },
          { value: '传授摩腹', label: '传授摩腹' }
        ],
        activeName: 'first',
        dialogStatus: '',
        textMap: {
          update: '编辑',
          create: '添加',
          search: '查询',
          detail: '详情'
        },
        dialogFormVisible: false,
        filters: {
          name: '',
          startTime: '',
          endTime: ''
        },
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
          peopleId: [
            { required: true, message: '请输入身份证号', trigger: 'blur' },
            { pattern: /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/, message: '你的身份证格式不正确' }],
          number: [
            { type: 'number', message: '必须为数字值', trigger: 'blur' }
          ]
        },
        // 编辑界面数据
        editForm: {
          development: [],
          guidance: [],
          medicine: []
        },

        addFormVisible: false, // 新增界面是否显示
        addFormRules: {
          name: [
            { required: true, message: '输入正确的姓名', trigger: 'blur' },
            { min: 2, max: 5, message: '长度在 2 到 5 个字符', trigger: 'blur' },
            { pattern: /^[\u4E00-\u9FA5]+$/, message: '用户名只能为中文'
            }
          ],
          peopleId: [
            { required: true, message: '请输入身份证号', trigger: 'blur' },
            { pattern: /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/, message: '你的身份证格式不正确' }],
          number: [
            { type: 'number', message: '必须为数字值', trigger: 'blur' }
          ]
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
        var str = style
        str = str + '<div id="dataPanel" style="position:absolute;left:50%;margin-left:-397px;">\n' +
          '  <div id="topContent">\n' +
          '    <h2 style="width:794px;text-align:center"> 3~6岁儿童健康检查记录表 </h2>  \n' +
          '    <span style="margin-left:34px;">姓    名：{{name}}</span>\n' +
          '    <span style="margin-left:520px;">编号:{{number}}</span> \n' +
          '    </div>\n' +
          '  <table id="table"  border="1"  cellspacing="0" bordercolor="#000000" style="width:730px;margin-left:30px;margin-top:5px;border-collapse:collapse;"  width = "80%">\n' +
          '    <tbody>\n' +
          '      <tr>\n' +
          '        <td style="width: 146px;text-align:center">月龄</td>\n' +
          '        <td colspan=\'1\' style="width: 146px">{{age1}}</td>\n' +
          '        <td colspan=\'1\' style="width: 146px">{{age2}}</td>\n' +
          '        <td colspan=\'1\' style="width: 146px">{{age3}}</td>\n' +
          '        <td colspan=\'1\' style="width: 146px">{{age4}}</td>\n' +
          '      </tr>\n' +
          '      <tr>\n' +
          '        <td style="width: 146px;text-align:center">随访日期</td>\n' +
          '        <td colspan=\'1\' style="width: 146px">{{followDate1}}</td>\n' +
          '        <td colspan=\'1\' style="width: 146px">{{followDate2}}</td>\n' +
          '        <td colspan=\'1\' style="width: 146px">{{followDate3}}</td>\n' +
          '        <td colspan=\'1\' style="width: 146px">{{followDate4}}</td>\n' +
          '      </tr>\n' +
          '      <tr>\n' +
          '        <td style="width: 25%;text-align:center">体重/kg</td>\n' +
          '        <td colspan=\'1\' style="width: 146px">{{weight1}}</td>\n' +
          '        <td colspan=\'1\' style="width: 146px">{{weight2}}</td>\n' +
          '        <td colspan=\'1\' style="width: 146px">{{weight3}}</td>\n' +
          '        <td colspan=\'1\' style="width: 146px">{{weight4}}</td>\n' +
          '      </tr>\n' +
          '      <tr>\n' +
          '        <td style="width: 20%;text-align:center">身高/cm</td>\n' +
          '        <td colspan=\'1\' style="width: 146px">{{height1}}</td>\n' +
          '        <td colspan=\'1\' style="width: 146px">{{height2}}</td>\n' +
          '        <td colspan=\'1\' style="width: 146px">{{height3}}</td>\n' +
          '        <td colspan=\'1\' style="width: 146px">{{height4}}</td>\n' +
          '      </tr>\n' +
          '      <tr>\n' +
          '        <td style="width: 20%;text-align:center">体重/身高</td>\n' +
          '        <td colspan=\'1\' style="width: 146px">{{weightheight1}}</td>\n' +
          '        <td colspan=\'1\' style="width: 146px">{{weightheight2}}</td>\n' +
          '        <td colspan=\'1\' style="width: 146px">{{weightheight3}}</td>\n' +
          '        <td colspan=\'1\' style="width: 146px">{{weightheight4}}</td>\n' +
          '      </tr>\n' +
          '      <tr>\n' +
          '        <td style="width: 20%;text-align:center">体格发育评价</td>\n' +
          '        <td colspan=\'1\' style="width: 146px">{{physical1}}</td>\n' +
          '        <td colspan=\'1\' style="width: 146px">{{physical2}}</td>\n' +
          '        <td colspan=\'1\' style="width: 146px">{{physical3}}</td>\n' +
          '        <td colspan=\'1\' style="width: 146px">{{physical4}}</td>\n' +
          '      </tr>\n' +
          '      <tr>\n' +
          '        <td style="width: 146px;text-align:center">视力</td>\n' +
          '        <td colspan=\'1\' style="width: 146px">{{eye1}}</td>\n' +
          '        <td colspan=\'1\' style="width: 146px">{{eye2}}</td>\n' +
          '        <td colspan=\'1\' style="width: 146px">{{eye3}}</td>\n' +
          '        <td colspan=\'1\' style="width: 146px">{{eye4}}</td>\n' +
          '      </tr>\n' +
          '      <tr>\n' +
          '        <td style="width: 146px;text-align:center">听力</td>\n' +
          '        <td colspan=\'1\' style="width: 146px">{{hearing1}}</td>\n' +
          '        <td colspan=\'1\' style="width: 146px">{{hearing2}}</td>\n' +
          '        <td colspan=\'1\' style="width: 146px">{{hearing3}}</td>\n' +
          '        <td colspan=\'1\' style="width: 146px">{{hearing4}}</td>\n' +
          '      </tr>\n' +
          '      <tr>\n' +
          '        <td style="width: 146px;text-align:center">出牙/龋齿数(颗)</td>\n' +
          '        <td colspan=\'1\' style="width: 146px">{{mouth1}}</td>\n' +
          '        <td colspan=\'1\' style="width: 146px">{{mouth2}}</td>\n' +
          '        <td colspan=\'1\' style="width: 146px">{{mouth3}}</td>\n' +
          '        <td colspan=\'1\' style="width: 146px">{{mouth4}}</td>\n' +
          '      </tr>\n' +
          '      <tr>\n' +
          '        <td style="width: 146px;text-align:center">胸部</td>\n' +
          '        <td colspan=\'1\' style="width: 146px">{{chest1}}</td>\n' +
          '        <td colspan=\'1\' style="width: 146px">{{chest2}}</td>\n' +
          '        <td colspan=\'1\' style="width: 146px">{{chest3}}</td>\n' +
          '        <td colspan=\'1\' style="width: 146px">{{chest4}}</td>\n' +
          '      </tr>\n' +
          '      <tr>\n' +
          '        <td style="width: 146px;text-align:center">腹部</td>\n' +
          '        <td colspan=\'1\' style="width: 146px">{{abdomen1}}</td>\n' +
          '        <td colspan=\'1\' style="width: 146px">{{abdomen2}}</td>\n' +
          '        <td colspan=\'1\' style="width: 146px">{{abdomen3}}</td>\n' +
          '        <td colspan=\'1\' style="width: 146px">{{abdomen4}}</td>\n' +
          '      </tr>\n' +
          '      <tr>\n' +
          '        <td style="width: 146px;text-align:center">血红蛋白值</td>\n' +
          '        <td colspan=\'1\' style="width: 146px">{{hemoglobin1}}</td>\n' +
          '        <td colspan=\'1\' style="width: 146px">{{hemoglobin2}}</td>\n' +
          '        <td colspan=\'1\' style="width: 146px">{{hemoglobin3}}</td>\n' +
          '        <td colspan=\'1\' style="width: 146px">{{hemoglobin4}}</td>\n' +
          '      </tr>\n' +
          '      <tr>\n' +
          '        <td style="width: 146px;text-align:center">其他体格检查</td>\n' +
          '        <td colspan=\'1\' style="width: 146px">{{otherCheck1}}</td>\n' +
          '        <td colspan=\'1\' style="width: 146px">{{otherCheck2}}</td>\n' +
          '        <td colspan=\'1\' style="width: 146px">{{otherCheck3}}</td>\n' +
          '        <td colspan=\'1\' style="width: 146px">{{otherCheck4}}</td>\n' +
          '      </tr>\n' +
          '      <tr>\n' +
          '        <td style="width: 146px;text-align:center">发育评估</td>\n' +
          '        <td colspan=\'1\' style="width: 146px">{{development1}}</td>\n' +
          '        <td colspan=\'1\' style="width: 146px">{{development2}}</td>\n' +
          '        <td colspan=\'1\' style="width: 146px">{{development3}}</td>\n' +
          '        <td colspan=\'1\' style="width: 146px">{{development4}}</td>\n' +
          '      </tr>\n' +
          '      <tr>\n' +
          '        <td colspan=\'5\' style="width: 20%;text-align:center">两次随访患病情况</td>\n' +
          '      </tr>\n' +
          '      <tr>\n' +
          '        <td style="width: 20%;text-align:center">肺炎/次</td>\n' +
          '        <td colspan=\'1\' style="width: 146px">{{pneumonia1}}</td>\n' +
          '        <td colspan=\'1\' style="width: 146px">{{pneumonia2}}</td>\n' +
          '        <td colspan=\'1\' style="width: 146px">{{pneumonia3}}</td>\n' +
          '        <td colspan=\'1\' style="width: 146px">{{pneumonia4}}</td>\n' +
          '      </tr>\n' +
          '      <tr>\n' +
          '        <td style="width: 146px;text-align:center">腹泻/次</td>\n' +
          '        <td colspan=\'1\' style="width: 146px">{{diarrhea1}}</td>\n' +
          '        <td colspan=\'1\' style="width: 146px">{{diarrhea2}}</td>\n' +
          '        <td colspan=\'1\' style="width: 146px">{{diarrhea3}}</td>\n' +
          '        <td colspan=\'1\' style="width: 146px">{{diarrhea4}}</td>\n' +
          '      </tr>\n' +
          '      <tr>\n' +
          '        <td style="width: 146px;text-align:center">外伤/次</td>\n' +
          '        <td colspan=\'1\' style="width: 146px">{{trauma1}}</td>\n' +
          '        <td colspan=\'1\' style="width: 146px">{{trauma2}}</td>\n' +
          '        <td colspan=\'1\' style="width: 146px">{{trauma3}}</td>\n' +
          '        <td colspan=\'1\' style="width: 146px">{{trauma4}}</td>\n' +
          '      </tr>\n' +
          '      <tr>\n' +
          '        <td style="width: 146px;text-align:center">其他</td>\n' +
          '        <td colspan=\'1\' style="width: 146px">{{other1}}</td>\n' +
          '        <td colspan=\'1\' style="width: 146px">{{other2}}</td>\n' +
          '        <td colspan=\'1\' style="width: 146px">{{other3}}</td>\n' +
          '        <td colspan=\'1\' style="width: 146px">{{other4}}</td>\n' +
          '      </tr>\n' +
          '      <tr>\n' +
          '        <td style="width: 146px;text-align:center">转诊建议</td>\n' +
          '        <td colspan=\'1\' style="width: 146px">{{referral1}}</td>\n' +
          '        <td colspan=\'1\' style="width: 146px">{{referral2}}</td>\n' +
          '        <td colspan=\'1\' style="width: 146px">{{referral3}}</td>\n' +
          '        <td colspan=\'1\' style="width: 146px">{{referral4}}</td>\n' +
          '      </tr>\n' +
          '      <tr>\n' +
          '        <td style="width: 146px;text-align:center">转诊原因</td>\n' +
          '        <td colspan=\'1\' style="width: 146px">{{reason1}}</td>\n' +
          '        <td colspan=\'1\' style="width: 146px">{{reason2}}</td>\n' +
          '        <td colspan=\'1\' style="width: 146px">{{reason3}}</td>\n' +
          '        <td colspan=\'1\' style="width: 146px">{{reason4}}</td>\n' +
          '      </tr>\n' +
          '      <tr>\n' +
          '        <td style="width: 146px;text-align:center">机构及科室</td>\n' +
          '        <td colspan=\'1\' style="width: 146px">{{insandDep1}}</td>\n' +
          '        <td colspan=\'1\' style="width: 146px">{{insandDep2}}</td>\n' +
          '        <td colspan=\'1\' style="width: 146px">{{insandDep3}}</td>\n' +
          '        <td colspan=\'1\' style="width: 146px">{{insandDep4}}</td>\n' +
          '      </tr>\n' +
          '      <tr>\n' +
          '        <td style="width: 146px;text-align:center">指导</td>\n' +
          '        <td colspan=\'1\' style="width: 146px">{{guidance1}}</td>\n' +
          '        <td colspan=\'1\' style="width: 146px">{{guidance2}}</td>\n' +
          '        <td colspan=\'1\' style="width: 146px">{{guidance3}}</td>\n' +
          '        <td colspan=\'1\' style="width: 146px">{{guidance4}}</td>\n' +
          '      </tr>\n' +
          '      <tr>\n' +
          '        <td style="width: 146px;text-align:center">下次随访日期</td>\n' +
          '        <td colspan=\'1\' style="width: 146px">{{nextDate1}}</td>\n' +
          '        <td colspan=\'1\' style="width: 146px">{{nextDate2}}</td>\n' +
          '        <td colspan=\'1\' style="width: 146px">{{nextDate3}}</td>\n' +
          '        <td colspan=\'1\' style="width: 146px">{{nextDate4}}</td>\n' +
          '      </tr>\n' +
          '      <tr>\n' +
          '        <td style="width: 146px;text-align:center">随访医生签名</td>\n' +
          '        <td colspan=\'1\' style="width: 146px">{{doctor1}}</td>\n' +
          '        <td colspan=\'1\' style="width: 146px">{{doctor2}}</td>\n' +
          '        <td colspan=\'1\' style="width: 146px">{{doctor3}}</td>\n' +
          '        <td colspan=\'1\' style="width: 146px">{{doctor4}}</td>\n' +
          '      </tr>\n' +
          '      \n' +
          '    </tbody>\n' +
          '  </table>\n' +
          '  <div id="footerContent"> <span style="margin-left:33px;margin-top:-5px">地    址：北京市阜外大街34号 <span style="margin-left:5px"></span>*********** <span style="margin-left:51px"></span>电    话：*********** <span style="margin-left:50px"></span>军    线：*********** </span> </div>\n' +
          '</div>\n' +
          '\n' +
          '</body>'
        str = str.replace(/{{name}}/, this.editForm.name ? this.editForm.name : '')
        str = str.replace(/{{number}}/, this.editForm.number ? this.editForm.number : '')

        str = str.replace(/{{age1}}/, this.printItem1.age ? this.printItem1.age : '')
        str = str.replace(/{{age2}}/, this.printItem2.age ? this.printItem2.age : '')
        str = str.replace(/{{age3}}/, this.printItem3.age ? this.printItem3.age : '')
        str = str.replace(/{{age4}}/, this.printItem4.age ? this.printItem4.age : '')

        str = str.replace(/{{followDate1}}/, this.printItem1.followDate ? this.printItem1.followDate : '')
        str = str.replace(/{{followDate2}}/, this.printItem2.followDate ? this.printItem2.followDate : '')
        str = str.replace(/{{followDate3}}/, this.printItem3.followDate ? this.printItem3.followDate : '')
        str = str.replace(/{{followDate4}}/, this.printItem4.followDate ? this.printItem4.followDate : '')

        str = str.replace(/{{weight1}}/, this.printItem1.weight ? this.printItem1.weight : '')
        str = str.replace(/{{weight2}}/, this.printItem2.weight ? this.printItem2.weight : '')
        str = str.replace(/{{weight3}}/, this.printItem3.weight ? this.printItem3.weight : '')
        str = str.replace(/{{weight4}}/, this.printItem4.weight ? this.printItem4.weight : '')

        str = str.replace(/{{height1}}/, this.printItem1.height ? this.printItem1.height : '')
        str = str.replace(/{{height2}}/, this.printItem2.height ? this.printItem2.height : '')
        str = str.replace(/{{height3}}/, this.printItem3.height ? this.printItem3.height : '')
        str = str.replace(/{{height4}}/, this.printItem4.height ? this.printItem4.height : '')

        str = str.replace(/{{weightheight1}}/, this.printItem1.weightheight ? this.printItem1.weightheight : '')
        str = str.replace(/{{weightheight2}}/, this.printItem2.weightheight ? this.printItem2.weightheight : '')
        str = str.replace(/{{weightheight3}}/, this.printItem3.weightheight ? this.printItem3.weightheight : '')
        str = str.replace(/{{weightheight4}}/, this.printItem4.weightheight ? this.printItem4.weightheight : '')

        str = str.replace(/{{physical1}}/, this.printItem1.physical ? this.printItem1.physical : '')
        str = str.replace(/{{physical2}}/, this.printItem2.physical ? this.printItem2.physical : '')
        str = str.replace(/{{physical3}}/, this.printItem3.physical ? this.printItem3.physical : '')
        str = str.replace(/{{physical4}}/, this.printItem4.physical ? this.printItem4.physical : '')

        str = str.replace(/{{eye1}}/, this.printItem1.eye ? this.printItem1.eye : '')
        str = str.replace(/{{eye2}}/, this.printItem2.eye ? this.printItem2.eye : '')
        str = str.replace(/{{eye3}}/, this.printItem3.eye ? this.printItem3.eye : '')
        str = str.replace(/{{eye4}}/, this.printItem4.eye ? this.printItem4.eye : '')

        str = str.replace(/{{hearing1}}/, this.printItem1.hearing ? this.printItem1.hearing : '')
        str = str.replace(/{{hearing2}}/, this.printItem2.hearing ? this.printItem2.hearing : '')
        str = str.replace(/{{hearing3}}/, this.printItem3.hearing ? this.printItem3.hearing : '')
        str = str.replace(/{{hearing4}}/, this.printItem4.hearing ? this.printItem4.hearing : '')

        str = str.replace(/{{mouth1}}/, this.printItem1.mouth ? this.printItem1.mouth : '')
        str = str.replace(/{{mouth2}}/, this.printItem2.mouth ? this.printItem2.mouth : '')
        str = str.replace(/{{mouth3}}/, this.printItem3.mouth ? this.printItem3.mouth : '')
        str = str.replace(/{{mouth4}}/, this.printItem4.mouth ? this.printItem4.mouth : '')

        str = str.replace(/{{chest1}}/, this.printItem1.chest ? this.printItem1.chest : '')
        str = str.replace(/{{chest2}}/, this.printItem2.chest ? this.printItem2.chest : '')
        str = str.replace(/{{chest3}}/, this.printItem3.chest ? this.printItem3.chest : '')
        str = str.replace(/{{chest4}}/, this.printItem4.chest ? this.printItem4.chest : '')

        str = str.replace(/{{abdomen1}}/, this.printItem1.abdomen ? this.printItem1.abdomen : '')
        str = str.replace(/{{abdomen2}}/, this.printItem2.abdomen ? this.printItem2.abdomen : '')
        str = str.replace(/{{abdomen3}}/, this.printItem3.abdomen ? this.printItem3.abdomen : '')
        str = str.replace(/{{abdomen4}}/, this.printItem4.abdomen ? this.printItem4.abdomen : '')

        str = str.replace(/{{hemoglobin1}}/, this.printItem1.hemoglobin ? this.printItem1.hemoglobin : '')
        str = str.replace(/{{hemoglobin2}}/, this.printItem2.hemoglobin ? this.printItem2.hemoglobin : '')
        str = str.replace(/{{hemoglobin3}}/, this.printItem3.hemoglobin ? this.printItem3.hemoglobin : '')
        str = str.replace(/{{hemoglobin4}}/, this.printItem4.hemoglobin ? this.printItem4.hemoglobin : '')

        str = str.replace(/{{otherCheck1}}/, this.printItem1.otherCheck ? this.printItem1.otherCheck : '')
        str = str.replace(/{{otherCheck2}}/, this.printItem2.otherCheck ? this.printItem2.otherCheck : '')
        str = str.replace(/{{otherCheck3}}/, this.printItem3.otherCheck ? this.printItem3.otherCheck : '')
        str = str.replace(/{{otherCheck4}}/, this.printItem4.otherCheck ? this.printItem4.otherCheck : '')

        str = str.replace(/{{development1}}/, this.printItem1.development ? this.printItem1.development : '')
        str = str.replace(/{{development2}}/, this.printItem2.development ? this.printItem2.development : '')
        str = str.replace(/{{development3}}/, this.printItem3.development ? this.printItem3.development : '')
        str = str.replace(/{{development4}}/, this.printItem4.development ? this.printItem4.development : '')

        str = str.replace(/{{pneumonia1}}/, this.printItem1.pneumonia ? this.printItem1.pneumonia : '')
        str = str.replace(/{{pneumonia2}}/, this.printItem2.pneumonia ? this.printItem2.pneumonia : '')
        str = str.replace(/{{pneumonia3}}/, this.printItem3.pneumonia ? this.printItem3.pneumonia : '')
        str = str.replace(/{{pneumonia4}}/, this.printItem4.pneumonia ? this.printItem4.pneumonia : '')

        str = str.replace(/{{diarrhea1}}/, this.printItem1.diarrhea ? this.printItem1.diarrhea : '')
        str = str.replace(/{{diarrhea2}}/, this.printItem2.diarrhea ? this.printItem2.diarrhea : '')
        str = str.replace(/{{diarrhea3}}/, this.printItem3.diarrhea ? this.printItem3.diarrhea : '')
        str = str.replace(/{{diarrhea4}}/, this.printItem4.diarrhea ? this.printItem4.diarrhea : '')

        str = str.replace(/{{trauma1}}/, this.printItem1.trauma ? this.printItem1.trauma : '')
        str = str.replace(/{{trauma2}}/, this.printItem2.trauma ? this.printItem2.trauma : '')
        str = str.replace(/{{trauma3}}/, this.printItem3.trauma ? this.printItem3.trauma : '')
        str = str.replace(/{{trauma4}}/, this.printItem4.trauma ? this.printItem4.trauma : '')

        str = str.replace(/{{other1}}/, this.printItem1.other ? this.printItem1.other : '')
        str = str.replace(/{{other2}}/, this.printItem2.other ? this.printItem2.other : '')
        str = str.replace(/{{other3}}/, this.printItem3.other ? this.printItem3.other : '')
        str = str.replace(/{{other4}}/, this.printItem4.other ? this.printItem4.other : '')

        str = str.replace(/{{referral1}}/, this.printItem1.referral ? this.printItem1.referral : '')
        str = str.replace(/{{referral2}}/, this.printItem2.referral ? this.printItem2.referral : '')
        str = str.replace(/{{referral3}}/, this.printItem3.referral ? this.printItem3.referral : '')
        str = str.replace(/{{referral4}}/, this.printItem4.referral ? this.printItem4.referral : '')

        str = str.replace(/{{reason1}}/, this.printItem1.reason ? this.printItem1.reason : '')
        str = str.replace(/{{reason2}}/, this.printItem2.reason ? this.printItem2.reason : '')
        str = str.replace(/{{reason3}}/, this.printItem3.reason ? this.printItem3.reason : '')
        str = str.replace(/{{reason4}}/, this.printItem4.reason ? this.printItem4.reason : '')

        str = str.replace(/{{insandDep1}}/, this.printItem1.insandDep ? this.printItem1.insandDep : '')
        str = str.replace(/{{insandDep2}}/, this.printItem2.insandDep ? this.printItem1.insandDep : '')
        str = str.replace(/{{insandDep3}}/, this.printItem3.insandDep ? this.printItem3.insandDep : '')
        str = str.replace(/{{insandDep4}}/, this.printItem4.insandDep ? this.printItem4.insandDep : '')

        str = str.replace(/{{guidance1}}/, this.printItem1.guidance ? this.printItem1.guidance : '')
        str = str.replace(/{{guidance2}}/, this.printItem2.guidance ? this.printItem2.guidance : '')
        str = str.replace(/{{guidance3}}/, this.printItem3.guidance ? this.printItem3.guidance : '')
        str = str.replace(/{{guidance4}}/, this.printItem4.guidance ? this.printItem4.guidance : '')

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
      printUsers() {
        const para = {
          peopleId: this.editForm.peopleId
        }
        print(para).then(res => {
          this.printItem = res.data.users
          this.printItem1 = this.printItem[0]
          this.printItem2 = this.printItem[1]
          this.printItem3 = this.printItem[2]
          this.printItem4 = this.printItem[3]
        })
      },
      handleCurrentChange(val) {
        this.page = val
        this.getUsers()
      },
      // 获取用户列表
      getUsers() {
        const para = {
          page: this.page,
          name: this.filters.name,
          startTime: this.filters.startTime,
          endTime: this.filters.endTime
        }
        para.startTime =
          !para.startTime || para.startTime === ''
            ? null
            : util.formatDate.format(new Date(para.startTime), 'yyyy-MM-dd')

        para.endTime =
          !para.endTime || para.endTime === ''
            ? null
            : util.formatDate.format(new Date(para.endTime), 'yyyy-MM-dd')
        getUserListPage(para).then(res => {
          this.total = res.data.total
          this.users = res.data.users
          if (this.page > 1 && (this.page - 1) * 10 >= this.total) {
            this.page = this.page - 1
            this.getUsers()
          }
        })
      },
      search() {
        this.page = 1
        this.getUsers()
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
      // 显示编辑界面
      handleEdit(index, row) {
        this.dialogStatus = 'update'
        this.dialogFormVisible = true
        this.editForm = Object.assign({}, row)
        this.editForm.development = this.editForm.development.split(',')
        this.editForm.guidance = this.editForm.guidance.split(',')
        this.editForm.medicine = this.editForm.medicine.split(',')
        this.edit = true
      },
      handleRowChange(row, event) {
        this.dialogStatus = 'detail'
        this.dialogFormVisible = true
        this.editForm = Object.assign({}, row)
        this.editForm.development = this.editForm.development.split(',')
        this.editForm.guidance = this.editForm.guidance.split(',')
        this.editForm.medicine = this.editForm.medicine.split(',')
        this.edit = false
      },
      // 显示新增界面
      handleAdd() {
        this.dialogStatus = 'create'
        this.dialogFormVisible = true
        this.editForm = {
          id: 0,
          hemoglobin: 0,
          development: [],
          guidance: [],
          followDate: Date.now(),
          medicine: []
        }
        this.edit = false
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

                para.nextDate =
                  !para.nextDate || para.nextDate === ''
                    ? null
                    : util.formatDate.format(new Date(para.nextDate), 'yyyy-MM-dd')

                para.followDate =
                  !para.followDate || para.followDate === ''
                    ? null
                    : util.formatDate.format(new Date(para.followDate), 'yyyy-MM-dd')

                para.development = ''
                for (const itm of this.editForm.development) {
                  para.development += itm
                  para.development += ','
                }
                para.development = para.development.substr(0, para.development.length - 1)

                para.guidance = ''
                for (const itm of this.editForm.guidance) {
                  para.guidance += itm
                  para.guidance += ','
                }
                para.guidance = para.guidance.substr(0, para.guidance.length - 1)

                para.medicine = ''
                for (const itm of this.editForm.medicine) {
                  para.medicine += itm
                  para.medicine += ','
                }
                para.medicine = para.medicine.substr(0, para.medicine.length - 1)

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

                para.nextDate =
                  !para.nextDate || para.nextDate === ''
                    ? null
                    : util.formatDate.format(new Date(para.nextDate), 'yyyy-MM-dd')

                para.followDate =
                  !para.followDate || para.followDate === ''
                    ? null
                    : util.formatDate.format(new Date(para.followDate), 'yyyy-MM-dd')

                para.development = ''
                for (const itm of this.editForm.development) {
                  para.development += itm
                  para.development += ','
                }
                para.development = para.development.substr(0, para.development.length - 1)

                para.guidance = ''
                for (const itm of this.editForm.guidance) {
                  para.guidance += itm
                  para.guidance += ','
                }
                para.guidance = para.guidance.substr(0, para.guidance.length - 1)

                para.medicine = ''
                for (const itm of this.editForm.medicine) {
                  para.medicine += itm
                  para.medicine += ','
                }
                para.medicine = para.medicine.substr(0, para.medicine.length - 1)

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
