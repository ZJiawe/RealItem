<template>
  <section class="app-container">
    <!--工具条-->
    <el-col :span="22" class="toolbar" style="padding-bottom: 0px;">
      <el-form :inline="true" :model="filters" @submit.native.prevent>
      <el-form-item>
        <el-input v-model="filters.name" placeholder="填表人"></el-input>
      </el-form-item>
        <el-form-item>
          <el-input v-model="filters.peopleId" placeholder="身份证号"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" v-on:click="searchs" style="background-color:#4db2ee;border-color:transparent" icon="el-icon-search">查询</el-button>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" v-on:click="findbtn"  style="background-color:#4db2ee;border-color:transparent;width :120px;" icon="el-icon-search">高级查询</el-button>
        </el-form-item>
        <el-form-item>
        <el-button type="primary" @click="handleAdd" icon="el-icon-circle-plus-outline" v-if="buttons.PHEF_C">新增</el-button>
        </el-form-item>
        <el-button type="primary" v-on:click="clear" style="background-color:#ffffff;color:#909090;border-color: #c1c1c1;font-size: 14px;margin-left:10px;" icon="el-icon-back" >重置</el-button>
      </el-form>
    </el-col>

    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible2" :close-on-click-modal="false" center>
      <el-form :model="findForm" label-width="200px" :rules="findFormRules" ref="findForm">
        <el-row>
          <el-col :span="12">
        <el-form-item label="健康教育资料发放种类及数量">
          <el-input v-model="filters.people" placeholder="健康教育资料发放种类及数量"></el-input>
        </el-form-item>
          </el-col>
          <el-col :span="12" >
        <el-form-item label="接受健康教育人员类别">
          <el-input v-model="filters.creditId" placeholder="接受健康教育人员类别"></el-input>
        </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="12">
        <el-form-item label="活动形式">
          <el-input v-model="filters.phone" placeholder="活动形式"></el-input>
        </el-form-item>
          </el-col>
          <el-col :span="12" >
        <el-form-item label="主讲人">
          <el-input v-model="filters.panPeople" placeholder="主讲人"></el-input>
        </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="12">
        <el-form-item label="负责人">
          <el-input v-model="filters.healthGoal" placeholder="负责人"></el-input>
        </el-form-item>
          </el-col>
          <el-col :span="12" >
        <el-form-item label="活动地点">
          <el-input v-model="filters.place" placeholder="活动地点"></el-input>
        </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="12">
        <el-form-item label="活动时间">
          <el-date-picker type="date" placeholder="选择日期" v-model="filters.healthTime_s" style="width: 100%;"></el-date-picker>
        </el-form-item>
          </el-col>
          <el-col :span="12" >
            <el-form-item label="--------------------------------------">
              <el-date-picker type="date" placeholder="选择日期" v-model="filters.healthTime_e" style="width: 100%;"></el-date-picker>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" v-on:click="searchs" style="background-color:#4db2ee;border-color:transparent" icon="el-icon-search">查询</el-button>
      </div>


    </el-dialog>
    <!--列表-->
    <el-table :data="users" :height="tableMeight" highlight-current-row @selection-change="selsChange" border style="width: 100%;margin-top: -20px;"@row-dblclick="handleRowChange">
      <el-table-column type="index" label="序号" width="60">
      </el-table-column>
      <el-table-column prop="name" label="填表人" min-width="80">
      </el-table-column>
      <el-table-column prop="sex"  v-if='false' label="存档材料请附后" min-width="160">
      </el-table-column>
      <el-table-column prop="age"  v-if='false' label="接受健康教育人数" min-width="160">
      </el-table-column>
      <el-table-column prop="people"  v-if='false' label="健康教育资料发放种类及数量" min-width="150">
      </el-table-column>
      <el-table-column prop="creditId"  v-if='false' label="接受健康教育人员类别" width="150">
      </el-table-column>
      <el-table-column prop="phone"  label="活动形式" min-width="120">
      </el-table-column>
      <el-table-column prop="panPeople" label="主讲人" min-width="100">
      </el-table-column>
      <el-table-column prop="healthGoal"  label="负责人" min-width="100">
      </el-table-column>
      <el-table-column prop="orgpenple" v-if='false' label="组织者" min-width="100">
      </el-table-column>
      <el-table-column prop="healthTime" label="活动时间" min-width="120">
      </el-table-column>
      <el-table-column prop="note" label="活动内容" min-width="160">
      </el-table-column>
      <el-table-column prop="institutions"  v-if='false' label="活动总结评价" min-width="160">
      </el-table-column>
      <el-table-column prop="place" label="活动地点" min-width="120">
      </el-table-column>
      <el-table-column prop="penple"  v-if='false' label="活动主题" min-width="160">
      </el-table-column>
      <el-table-column prop="creator"  v-if='false' label="创建人" min-width="100">
      </el-table-column>
      <el-table-column prop="createDate"  v-if='false' label="创建时间" :formatter="timeFormat" min-width="120">
      </el-table-column>
      <el-table-column prop="omdifier"  v-if='false' label="修改人" min-width="100">
      </el-table-column>
      <el-table-column prop="modifyDate"  v-if='false' label="修改时间" :formatter="timeFormat" min-width="120">
      </el-table-column>
      <el-table-column label="操作" width="150">
        <template slot-scope="scope">
          <a size="14px" @click="handleEdit(scope.$index, scope.row)" style="color:#4DB2EE;margin-right:20px;" v-if="buttons.PHEF_E">编辑</a>
          <a size="14px" @click="handleDel(scope.$index, scope.row)" style="color:#FB4141" v-if="buttons.PHEF_D">删除</a>
        </template>
      </el-table-column>
    </el-table>

    <!--工具条-->
    <el-col :span="24" class="toolbar">
      <el-pagination layout="prev, pager, next" :current-page.sync="page" @current-change="handleCurrentChange" :page-size="20" :total="total" style="float:right;">
      </el-pagination>
    </el-col>

    <!--编辑界面-->
    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible" :close-on-click-modal="false" center>
      <el-form :model="editForm" label-width="200px" :rules="editFormRules" ref="editForm">
        <EasyScrollbar>
          <div class="item" v-bind:style="{ height: tablebodyHeight + 'px'}">
            <div id="print">


              <table id="table"  border="1"  cellspacing="0" bordercolor="#dddddd" style="width:880px;margin-left:30px;margin-top:5px;border-collapse:collapse;"  width = "80%">

                <tbody>
                <tr>
                  <td style="width: 140px;text-align:center"><span>*</span>身份证号</td>
                  <td colspan='2' style="" class="pad10">
                    <el-form-item label="" label-width="0px" prop="peopleId" >
                      <el-input v-model="editForm.peopleId" placeholder="请输入身份证号" style="width: 300px;"></el-input>
                    </el-form-item>
                  </td>
                  <td style="width: 140px;text-align:center"><span>*</span>填表人</td>
                  <td colspan='2' class="pad10" style="text-align: left;">
                    <el-form-item label="" label-width="0px" prop="name">
                      <el-input v-model="editForm.name" placeholder="请输入填表人" auto-complete="off" style="width: 300px;"></el-input>
                    </el-form-item>
                  </td>
                </tr>

                <tr>
                  <td style="width: 140px;text-align:center">负责人</td>
                  <td colspan='2' style="" class="pad10">
                      <el-input v-model="editForm.healthGoal" auto-complete="off" style="width: 300px;"></el-input>
                  <td style="width: 140px;text-align:center"><span>*</span>接受健康教育人数</td>
                  <td colspan='2' style="" class="pad10">
                    <el-form-item label="" label-width="0px" prop="age">
                      <el-input v-model.number="editForm.age" placeholder="请输入接受健康教育人数" auto-complete="off" style="width: 300px;"></el-input>
                    </el-form-item>
                  </td>
                </tr>

                <tr>
                  <td style="width: 140px;text-align:center">健康教育资料发放种类及数量</td>
                  <td colspan='2' class="pad10" style="text-align: left;">
                    <el-input v-model.number="editForm.people" auto-complete="off" style="width: 300px;"></el-input>
                  <td style="width: 140px;text-align:center">接受健康教育人员类别</td>
                  <td colspan='2' class="pad10" style="text-align: left;">
                    <el-form-item label="" label-width="0px">
                      <el-input v-model="editForm.creditId" auto-complete="off" style="width: 300px;"></el-input>
                    </el-form-item>
                  </td>
                </tr>

                <tr>
                  <td style="width: 140px;text-align:center">主讲人</td>
                  <td colspan='2' class="pad10" style="text-align: left;">
                    <el-input v-model="editForm.panPeople" auto-complete="off" style="width: 300px;"></el-input>
                  <td style="width: 140px;text-align:center">活动主题</td>
                  <td colspan='2' class="pad10" style="text-align: left;">
                    <el-input v-model="editForm.penple" auto-complete="off" style="width: 300px;"></el-input>
                  </td>
                </tr>


                <tr>
                  <td style="width: 140px;text-align:center">活动地点</td>
                  <td colspan='5' class="pad10" style="text-align: left;">
                    <el-form-item label=""label-width="0px">
                    <el-input  v-model="editForm.place" auto-complete="off" ></el-input>
                    </el-form-item>
                  </td>
                </tr>

                <tr>
                  <td style="width: 140px;word-wrap:break-word;text-align:center">区域</td>
                  <td colspan="5" style="text-align:center;padding-right: 0px;"  class="pad10">
                    <el-form-item label=""label-width="0px">
                      <el-cascader :options="options" style="width: 100%" v-model="area"
                                   placeholder="请选择" change-on-select></el-cascader>
                    </el-form-item>
                  </td>
                </tr>



                <tr>
                  <td style="width: 140px;text-align:center">存档材料请附后</td>
                  <td colspan='4' class="pad20" style="text-align: left;">
                    <el-checkbox-group v-model="editForm.sex">
                      <el-checkbox v-for="item in sex" :key="item.label" :name="type" :label="item.label" :value="item.value"></el-checkbox>
                    </el-checkbox-group>
                  </td>
                </tr>

                <tr>
                  <td style="width: 140px;text-align:center">组织者</td>
                  <td colspan='2' class="pad10" style="text-align: left;">
                    <el-input v-model="editForm.orgpenple" auto-complete="off" style="width: 300px;"></el-input>
                  <td style="width: 140px;text-align:center">活动时间</td>
                  <td colspan='2' class="pad10" style="text-align: left;">
                    <el-date-picker type="date" placeholder="选择日期" v-model="editForm.healthTime" style="width: 300px;"></el-date-picker>
                  </td>
                </tr>

                <tr>
                  <td style="width: 140px;text-align:center">活动形式</td>
                  <td colspan='4' class="pad10" style="text-align: left;">
                    <el-input v-model="editForm.phone" auto-complete="off" ></el-input>
                  </td>
                </tr>

                <tr>
                  <td style="width: 140px;text-align:center">活动总结评价</td>
                  <td colspan='4' class="pad10" style="text-align: left;">
                    <el-input v-model="editForm.institutions" auto-complete="off" ></el-input>
                  </td>
                </tr>
                <tr>
                  <td style="width: 140px;text-align:center">活动内容</td>
                  <td colspan='5' class="pad10" style="text-align: left;">
                    <el-input type="textarea" v-model="editForm.note" ></el-input>
                  </td>
                </tr>
                </tbody>
              </table>


             <!-- <el-tabs v-model="activeName">
              <el-row>
                <el-col :span="12">
                  <el-form-item label="填表人" prop="name">
                    <el-input v-model="editForm.name" auto-complete="off"></el-input>
                  </el-form-item>
                </el-col>

                <el-col :span="12" >
                  <el-form-item label="身份证号" prop="peopleId" >
                    <el-input v-model="editForm.peopleId" auto-complete="off"></el-input>
                  </el-form-item>
                </el-col>
              </el-row>

              <el-row>
                <el-col :span="12">
                  <el-form-item label="负责人" prop="">
                    <el-input v-model="editForm.healthGoal" auto-complete="off"></el-input>
                  </el-form-item>
                </el-col>

                <el-col :span="12" >
                  <el-form-item label="接受健康教育人数" prop="age">
                    <el-input  v-model.number="editForm.age" auto-complete="off" ></el-input>
                  </el-form-item>
                </el-col>
              </el-row>

              <el-row>
                <el-col :span="12">
                  <el-form-item label="健康教育资料发放种类及数量" prop="">
                    <el-input v-model.number="editForm.people" auto-complete="off"></el-input>
                  </el-form-item>
                </el-col>

                <el-col :span="12" >
                  <el-form-item label="接受健康教育人员类别" prop="creditId">
                    <el-input  v-model.number="editForm.creditId" auto-complete="off" ></el-input>
                  </el-form-item>
                </el-col>
              </el-row>

              <el-row>
                <el-col :span="12">
                  <el-form-item label="主讲人" prop="">
                    <el-input v-model="editForm.panPeople" auto-complete="off"></el-input>
                  </el-form-item>
                </el-col>

                <el-col :span="12" >
                  <el-form-item label="活动总结评价">
                    <el-input v-model="editForm.institutions" auto-complete="off"></el-input>
                  </el-form-item>
                </el-col>
              </el-row>

              <el-row>
                <el-col :span="8">
                  <el-form-item label="活动地点" prop="">
                    <el-input v-model="editForm.place" auto-complete="off"></el-input>
                  </el-form-item>
                </el-col>

                <el-col :span="8" >
                  <el-form-item label="活动主题" label-width="70px">
                    <el-input v-model="editForm.penple" auto-complete="off"></el-input>
                  </el-form-item>
                </el-col>
                <el-col :span="8">
                  <el-form-item label="活动形式" label-width="70px">
                    <el-input v-model="editForm.phone" auto-complete="off"></el-input>
                  </el-form-item>
                </el-col>
              </el-row>


              <el-form-item label="存档材料请附后" >
                <el-checkbox-group v-model="editForm.sex" style="width:100%">
                  <el-checkbox v-for="item in sex" :key="item.label" :name="type" :label="item.label" :value="item.value"></el-checkbox>
                </el-checkbox-group>
              </el-form-item>

              <el-row>

                <el-col :span="8" >
                  <el-form-item label="组织者" prop="">
                    <el-input v-model="editForm.orgpenple" auto-complete="off"></el-input>
                  </el-form-item>
                </el-col>
                <el-col :span="8" >
                  <el-form-item label="活动时间" label-width="70px">
                    <el-date-picker type="date" placeholder="选择日期" v-model="editForm.healthTime" style="width: 100%"></el-date-picker>
                  </el-form-item>
                </el-col>

                <el-col :span="8" >
                  <el-form-item label="地区" label-width="40px">
                    <el-cascader :options="options" v-model="area" style="width: 100%" placeholder="请选择"></el-cascader>
                  </el-form-item>
                </el-col>
              </el-row>

       <el-form-item label="活动内容">
         <el-input type="textarea" v-model="editForm.note"></el-input>
       </el-form-item>
              </el-tabs>-->
            </div>
          </div>
        </EasyScrollbar>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="conprint()">打印</el-button>
        <el-button v-if="dialogStatus=='create'" type="primary" @click="check">添加</el-button>
        <el-button v-else type="primary" v-if="edit" @click="createData">新增修改</el-button>
        <el-button v-else type="primary" @click="updateData" v-if="edit">修改</el-button>
        <el-button @click.native="dialogFormVisible=false">取消</el-button>
      </div>
    </el-dialog>
  </section>
</template>

<script>
  import util from '@/utils/table.js'
  import {
    getList,
    removeData,
    batchRemoveData,
    editData,
    addData
  } from '@/api/severeHealthPlanFollow'
  import city from '@/api/city.js'
  import { getList as getPerson } from '@/api/Perinfor'
  export default {
    data() {
      return {
        buttons: {
          PHEF_C: false,
          PHEF_E: false,
          PHEF_D: false
        },
        sex: [
          { value: '书面材料', label: '书面材料' },
          { value: '图片材料', label: '图片材料' },
          { value: '印刷材料', label: '印刷材料' },
          { value: '影音材料', label: '影音材料' },
          { value: '签到表', label: '签到表' },
          { value: '其他材料', label: '其他材料' }
        ],
        edit: true,
        activeName: 'first',
        dialogStatus: '',
        tableMeight: window.innerHeight - 220,
        tablebodyHeight: window.innerHeight - 320,
        options: city.city(),
        area: [],
        textMap: {
          update: '编辑',
          create: '新增',
          search: '查询',
          detail: '详情'
        },
        dialogFormVisible: false,
        dialogFormVisible2: false,
        filters: {
          name: '',
          age_s: '',
          age_e: '',
          people: '',
          peopleId: '',
          creditId: '',
          panPeople: '',
          phone: '',
          place: '',
          institutions: '',
          healthGoal: '',
          healthTime_s: '',
          healthTime_e: ''
        },
        users: [],
        total: 0,
        page: 1,
        sels: [], // 列表选中列
        editFormRules: {
          name: [
            { required: true, message: ' ', trigger: 'blur' },
            { min: 2, max: 5, message: ' ', trigger: 'blur' },
            { pattern: /^[\u4E00-\u9FA5]+$/, message: ' '
            }
          ],
          peopleId: [
            { required: true, message: ' ', trigger: 'blur' },
            { pattern: /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/, message: ' ' }
          ],
          age: [
            { required: true, message: ' ', trigger: 'blur' },
            { type: 'number', message: ' ', trigger: 'blur' },
            { pattern: /(^\S{0,10}$)/, message: ' ' }
          ]
        },
        // 编辑界面数据
        editForm: {
          name: '',
          creditId: '',
          area: '',
          sex: [],
          age: '',
          people: '',
          peopleId: '',
          phone: '',
          orgpenple: '',
          panPeople: '',
          institutions: '',
          healthGoal: '',
          place: '',
          penple: '',
          creator: '1',
          createDate: '',
          modifier: '1',
          modifydate: '',
          healthTime: '',
          note: ''
        },

        addFormVisible: false, // 新增界面是否显示
        addFormRules: {
          name: [{ required: true, message: '请输入姓名', trigger: 'blur' }]
        }
      }
    },
    methods: {
      clear() {
        this.filters = {
          name: '',
          age_e: '',
          age_s: '',
          people: '',
          peopleId: '',
          creditId: '',
          panPeople: '',
          phone: '',
          place: '',
          institutions: '',
          healthGoal: '',
          healthTime_s: '',
          healthTime_e: ''
        }
      },
      // 身份验证
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
                this.$message.error('请先建立个人档案')
              }
            })
          }
        })
      },
      timeFormat: function(row, column) {
        let date = row[column.property]
        date =
          !date || date === ''
            ? ''
            : util.formatDate.format(new Date(date), 'yyyy-MM-dd')
        return date
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
          '    <h2 style="width:794px;text-align:center"> 健康教育活动记录表</h2>  \n' +
          '    </div>\n' +
          '  <table id="table"  border="1"  cellspacing="0" bordercolor="#000000" style="width:730px;margin-left:30px;margin-top:5px;border-collapse:collapse;"  width = "80%">\n' +
          '    <tbody>\n' +
          '      <tr>\n' +
          '        <td style="width: 20%;text-align:center">活动时间</td>\n' +
          '        <td colspan=\'2\'style="text-align:center">{{healthTime}}</td>\n' +
          '        <td style="width: 20%;text-align:center">活动地点</td>\n' +
          '        <td colspan=\'2\'style="text-align:center">{{place}}</td>\n' +
          '      </tr>\n' +
          '\t  <tr>\n' +
          '        <td style="word-wrap:break-word;width: 20%;text-align:center">活动形式</td>\n' +
          '        <td colspan=\'5\'style="text-align:center">{{phone}}</td>\n' +
          '      </tr>\n' +
        '\t  <tr>\n' +
        '        <td style="word-wrap:break-word;width: 20%;text-align:center">活动主题</td>\n' +
        '        <td colspan=\'5\'style="text-align:center">{{penple}}</td>\n' +
        '      </tr>\n' +
          '\t  <tr>\n' +
          '        <td style="word-wrap:break-word;width: 20%;text-align:center">组织者</td>\n' +
          '        <td colspan=\'5\'style="text-align:center">{{orgpenple}}</td>\n' +
          '      </tr>\n' +
          '\t  <tr>\n' +
          '        <td style="word-wrap:break-word;width: 20%;text-align:center">主讲人</td>\n' +
          '        <td colspan=\'5\'style="text-align:center">{{panPeople}}</td>\n' +
          '      </tr>\n' +
          '      <tr>\n' +
          '        <td style="width: 20%;text-align:center">接受健康教育人员类别</td>\n' +
          '        <td colspan=\'2\'style="text-align:center">{{creditId}}</td>\n' +
          '        <td style="width: 20%;text-align:center">接受健康教育人数</td>\n' +
          '        <td colspan=\'2\'style="text-align:center">{{age}}</td>\n' +
          '      </tr>\n' +
          '\t  <tr>\n' +
          '        <td style="word-wrap:break-word;width: 20%;text-align:center">健康教育种类及数量</td>\n' +
          '        <td colspan=\'5\'style="text-align:center">{{people}}</td>\n' +
          '      </tr>\n' +
          '\t  <tr>\n' +
          '        <td style="word-wrap:break-word;width: 20%;text-align:center">活动内容</td>\n' +
          '        <td colspan=\'4\'style="text-align:center">{{note}}</td>\n' +
          '      </tr>\n' +
          '\t  <tr>\n' +
          '        <td style="word-wrap:break-word;width: 20%;text-align:center">活动总结评价</td>\n' +
          '        <td colspan=\'4\'style="text-align:center">{{institutions}}</td>\n' +
          '      </tr>\n' +
          '\t  <tr>\n' +
          '        <td style="word-wrap:break-word;width: 20%;text-align:center">存档材料请附后</td>\n' +
          '        <td colspan=\'4\'style="text-align:center">{{sex}}</td>\n' +
          '      </tr>\n' +
          '\t  <tr>\n' +
          '        <td style="width: 10%;text-align:center">填表人（签名）</td>\n' +
          '        <td colspan=\'2\'style="text-align:center">{{name}}</td>\n' +
          '        <td style="width: 10%;text-align:center">负责人（签名）</td>\n' +
          '        <td colspan=\'2\'style="text-align:center">{{healthGoal}}</td>\n' +
          '      </tr>\n' +
          ' \n' +
          '    </tbody> \n' +
          '    \n' +
          '     \n' +
          '\n' +
          '  </table>\n' +
          '</div>'
        str = str.replace(/{{healthTime}}/, this.editForm.healthTime ? this.editForm.healthTime : '')
        str = str.replace(/{{place}}/, this.editForm.place ? this.editForm.place : '')
        str = str.replace(/{{phone}}/, this.editForm.phone ? this.editForm.phone : '')
        str = str.replace(/{{penple}}/, this.editForm.penple ? this.editForm.penple : '')
        str = str.replace(/{{panPeople}}/, this.editForm.panPeople ? this.editForm.panPeople : '')
        str = str.replace(/{{creditId}}/, this.editForm.creditId ? this.editForm.creditId : '')
        str = str.replace(/{{age}}/, this.editForm.age ? this.editForm.age : '')
        str = str.replace(/{{people}}/, this.editForm.people ? this.editForm.people : '')
        str = str.replace(/{{note}}/, this.editForm.note ? this.editForm.note : '')
        str = str.replace(/{{institutions}}/, this.editForm.institutions ? this.editForm.institutions : '')
        str = str.replace(/{{sex}}/, this.editForm.sex ? this.editForm.sex : '')
        str = str.replace(/{{name}}/, this.editForm.name ? this.editForm.name : '')
        str = str.replace(/{{orgpenple}}/, this.editForm.orgpenple ? this.editForm.orgpenple : '')
        str = str.replace(/{{healthGoal}}/, this.editForm.healthGoal ? this.editForm.healthGoal : '')
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
      // 性别显示转换
      function(row, column) {
        return row.sex === 1 ? '男' : row.sex === 0 ? '女' : '未知'
      },
      handleCurrentChange(val) {
        this.page = val
        this.getUsers()
      },
      // 获取用户列表
      getUsers() {
        this.dialogFormVisible2 = false
        this.filters.healthTime_s =
          !this.filters.healthTime_s || this.filters.healthTime_s === ''
            ? ''
            : util.formatDate.format(new Date(this.filters.healthTime_s), 'yyyy-MM-dd')
        this.filters.healthTime_e =
          !this.filters.healthTime_e || this.filters.healthTime_e === ''
            ? ''
            : util.formatDate.format(new Date(this.filters.healthTime_e), 'yyyy-MM-dd')
        const para = {
          page: this.page,
          size: 20,
          filters: { name: this.filters.name, age_s: this.filters.age_s, age_e: this.filters.age_e, orgpenple: this.filters.orgpenple, peopleId: this.filters.peopleId, people: this.filters.people, creditId: this.filters.creditId, phone: this.filters.phone, panPeople: this.filters.panPeople, place: this.filters.place, institutions: this.filters.institutions, healthGoal: this.filters.healthGoal, healthTime_s: this.filters.healthTime_s, healthTime_e: this.filters.healthTime_e
          }
        }
        getList(para).then(res => {
          this.total = res.totalCount
          this.users = res.detailModelList
        })
        if (this.page > 1 && (this.page - 1) * 20 >= this.total) {
          this.page = this.page - 1
          this.getUsers()
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
      findbtn() {
        this.dialogStatus = 'search'
        this.dialogFormVisible2 = true
        this.findForm = {
          name: '',
          age_e: '',
          age_s: '',
          people: '',
          peopleId: '',
          creditId: '',
          panPeople: '',
          phone: '',
          place: '',
          institutions: '',
          healthGoal: '',
          healthTime_s: '',
          healthTime_e: ''
        }
      },
      handleRowChange(row, event) {
        this.dialogStatus = 'datail'
        this.dialogFormVisible = true
        this.editForm = Object.assign({}, row)
        this.edit = false
      },
      // 显示编辑界面
      handleEdit(index, row) {
        this.area = []
        this.dialogStatus = 'update'
        this.dialogFormVisible = true
        this.editForm = Object.assign({}, row)
        this.area = this.editForm.area.split('/')
        this.editForm.sex = this.editForm.sex.split(',')
        this.edit = true
      },
      searchs() {
        this.page = 1
        this.getUsers()
      },
      // 显示新增界面
      handleAdd() {
        this.area = []
        this.dialogStatus = 'create'
        this.dialogFormVisible = true
        this.edit = false
        this.editForm = {
          name: '',
          creditId: '',
          area: '',
          sex: [],
          age: '',
          people: '',
          orgpenple: '',
          peopleId: '',
          phone: '',
          panPeople: '',
          institutions: '',
          healthGoal: '',
          place: '',
          penple: '',
          creator: this.$store.getters.name,
          createDate: '',
          modifier: '1',
          modifydate: '',
          healthTime: '',
          note: ''
        }
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
                para.panTime =
                  !para.panTime || para.panTime === ''
                    ? ''
                    : util.formatDate.format(new Date(para.panTime), 'yyyy-MM-dd')
                para.healthTime =
                  !para.healthTime || para.healthTime === ''
                    ? ''
                    : util.formatDate.format(new Date(para.healthTime), 'yyyy-MM-dd')
                para.sex = ''
                for (const itm of this.editForm.sex) {
                  if (itm !== '') {
                    para.sex += itm
                    para.sex += ','
                  }
                }
                para.sex = para.sex.substr(0, para.sex.length - 1)
                para.area = ''
                for (const itm of this.area) {
                  para.area += itm
                  para.area += '/'
                }
                para.area = para.area.substr(0, para.area.length - 1)
                editData(para).then(res => {
                  this.$message({
                    message: '提交成功',
                    type: 'success'
                  })
                  this.$refs['editForm'].resetFields()
                  this.dialogFormVisible = false
                  this.$emit('watchChild')
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
                para.panTime =
                  !para.panTime || para.panTime === ''
                    ? ''
                    : util.formatDate.format(new Date(para.panTime), 'yyyy-MM-dd')
                para.healthTime =
                  !para.healthTime || para.healthTime === ''
                    ? ''
                    : util.formatDate.format(new Date(para.healthTime), 'yyyy-MM-dd')
                para.sex = ''
                for (const itm of this.editForm.sex) {
                  if (itm !== '') {
                    para.sex += itm
                    para.sex += ','
                  }
                }
                para.sex = para.sex.substr(0, para.sex.length - 1)
                para.area = ''
                for (const itm of this.area) {
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
