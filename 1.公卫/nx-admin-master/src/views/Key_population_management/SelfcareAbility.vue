<template>
  <section class="app-container">
    <!--工具条-->
    <el-col :span="24" class="toolbar" style="padding-bottom: 0px;">
      <el-form :inline="true" :model="filters" @submit.native.prevent>
        <el-form-item>
          <el-input v-model="filters.name" placeholder="姓名" clearable></el-input>
        </el-form-item>
        <el-form-item>
          <el-input v-model="filters.peopleId" placeholder="身份证号" clearable></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" v-on:click="search" style="background-color:#4db2ee;border-color:transparent;height:40px" icon="el-icon-search">查询</el-button>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" v-on:click="searchwindow" style="background-color:#4db2ee;border-color:transparent;width:120px;height:40px" icon="el-icon-search">高级查询</el-button>
        </el-form-item>
        <el-form-item>
          <el-button v-if="buttons.KPSA_C" type="primary" @click="handleAdd"icon="el-icon-circle-plus-outline" style="height:40px">新增</el-button>
        </el-form-item>
      </el-form>
    </el-col>

 <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible2" :close-on-click-modal="false"center>
   <el-form :model="findForm" label-width="120px" :rules="editFormRules" ref="editForm">
     <el-row>
       <el-col :span="12">
         <el-form-item label="姓名">
           <el-input v-model="filters.name" placeholder="姓名" clearable></el-input>
         </el-form-item>
       </el-col>
       <el-col :span="12">
         <el-form-item label="身份证">
           <el-input v-model="filters.peopleId" placeholder="身份证号" clearable></el-input>
         </el-form-item>
       </el-col>
     </el-row>
     <el-row>
       <el-col :span="12">
         <el-form-item label="起始填报时间" >
           <el-date-picker type="date" placeholder="选择日期" v-model="findForm.formDate_s" style="width: 100%;"></el-date-picker>
         </el-form-item>
       </el-col>
       <el-col :span="12">
         <el-form-item label="结束填报时间" >
           <el-date-picker type="date" placeholder="选择日期" v-model="findForm.formDate_e" style="width: 100%;"></el-date-picker>
         </el-form-item>
       </el-col>
     </el-row>
   </el-form>
   <div slot="footer" class="dialog-footer">
     <el-button type="primary" v-on:click="highsearch" style="background-color:#4db2ee;border-color:transparent;height:40px" icon="el-icon-search">查询</el-button>
   </div>
 </el-dialog>
    <!--列表-->
    <el-table :data="users" :height="tableHeight" highlight-current-row @selection-change="selsChange" border style="width: 100%;margin-top:-20px;" @row-dblclick="handleRowChange">
      <el-table-column type="index" label="序号" width="80">
      </el-table-column>
      <el-table-column prop="name" label="姓名" width="120">
      </el-table-column>
      <el-table-column prop="peopleId" label="身份证号" width="180">
      </el-table-column>
      <el-table-column prop="workid" v-if='false' label="工作编号" width="120">
      </el-table-column>
      <el-table-column prop="id" v-if='false' label="系统编号" width="120">
      </el-table-column>
      <el-table-column prop="meal" label="进餐" width="120">
      </el-table-column>
      <el-table-column prop="washing" label="梳洗" width="120" >
      </el-table-column>
      <el-table-column prop="dressing" label="穿衣" width="120">
      </el-table-column>
      <el-table-column prop="toilet" label="如厕" width="120">
      </el-table-column>
      <el-table-column prop="activity" label="活动" min-width="160">
      </el-table-column>
      <el-table-column prop="totalScore" label="总得分" min-width="160">
      </el-table-column>
      <el-table-column prop="formDate" label="填表日期" min-width="160">
      </el-table-column>
      <el-table-column label="操作" width="200">
        <template slot-scope="scope">
          <a size="14px" @click="goto(scope.$index, scope.row)" style="color:#4DB2EE;margin-right:20px;">查看档案</a>
          <a size="14px" v-if="buttons.KPSA_E" @click="handleEdit(scope.$index, scope.row)" style="color:#4DB2EE;margin-right:20px;">编辑</a>
          <a size="14px" v-if="buttons.KPSA_D" @click="handleDel(scope.$index, scope.row)" style="color:#FB4141">删除</a>
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
    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible" :close-on-click-modal="false"center>
      <div id="print">
      <el-form :model="editForm" label-width="80px" :rules="editFormRules" ref="editForm">
        <EasyScrollbar>
          <div class="item" v-bind:style="{ height: tablebodyHeight + 'px'}">
            <el-tabs>
              <table id="table"  border="1"  cellspacing="0" bordercolor="#dddddd" style="margin-left:10px;margin-right:10px;border-collapse:collapse;">
                <tbody style="color:#323232;">
                <tr v-if="dialogStatus==='detail'">
                  <td style="word-wrap:break-word;text-align:center;">
                    创建人
                  </td>
                  <td colspan="2" class="pad10">
                    <el-form-item label="" label-width="0px">
                      <el-input v-model="editForm.creator" style="width: 100%" v-if="dialogStatus==='detail'" readonly></el-input>
                    </el-form-item>
                  </td>
                  <td style="word-wrap:break-word;text-align:center;">
                    创建时间
                  </td>
                  <td colspan="4"  class="pad10">
                    <el-form-item label="" label-width="0px" v-if="dialogStatus==='detail'">
                      <el-date-picker type="date" placeholder="选择日期" v-model="editForm.createDate" style="width: 100%" v-if="dialogStatus==='detail'" readonly></el-date-picker>
                    </el-form-item>
                  </td>
                </tr>
                <tr v-if="dialogStatus==='detail'">
                  <td style="word-wrap:break-word;text-align:center;width:100px;">
                    修改人
                  </td>
                  <td colspan="2" class="pad10">
                    <el-form-item label="" label-width="0px" v-if="dialogStatus==='detail'">
                      <el-input v-model="editForm.modifier" style="width: 100%" v-if="dialogStatus==='detail'" readonly></el-input>
                    </el-form-item>
                  </td>
                  <td style="word-wrap:break-word;text-align:center;width:100px;">
                    修改时间
                  </td>
                  <td colspan="4" class="pad10">
                    <el-form-item label="" label-width="0px" v-if="dialogStatus==='detail'">
                      <el-date-picker type="date" placeholder="选择日期" v-model="editForm.modifyDate" style="width: 100%" v-if="dialogStatus==='detail'" readonly></el-date-picker>
                    </el-form-item>
                  </td>
                </tr>
                <tr>
                  <td style="text-align:center;width:140px;" class="pad"><span>*</span>身份证号</td>
                  <td colspan="2" class="pad10" style="padding-right: 0px;">
                    <el-form-item label="" label-width="0px" prop="peopleId">
                      <el-input v-model="editForm.peopleId" v-on:input="inputFunc" placeholder="请选择"></el-input>
                    </el-form-item>
                  </td>
                  <td style="text-align:center;width:140px;" class="pad"><span>*</span>区域</td>
                  <td colspan="4" class="pad10" style="padding-right: 0px;">
                    <el-form-item label="" label-width="0px" prop="area">
                      <el-cascader :options="options" v-model="editForm.area" style="width: 100%" placeholder="请选择" disabled="false"></el-cascader>
                    </el-form-item>
                  </td>
                </tr>
                <tr>
                  <td style="word-wrap:break-word;text-align:center">工程编号</td>
                  <td colspan="1" class="pad10">
                    <el-input type="te1" v-model="editForm.workid"></el-input>
                  </td>
                  <td style="word-wrap:break-word;text-align:center;width:140px;">姓名</td>
                  <td colspan="1" class="pad10">
                    <el-input type="te1112" v-model="editForm.name" disabled="false"></el-input>
                  </td>
                  <td style="word-wrap:break-word;text-align:center;width:140px;">填表日期</td>
                  <td colspan="2" class="pad10">
                    <el-date-picker type="date" placeholder="选择日期" v-model="editForm.formDate" style="width: 100%;"></el-date-picker>
                  </td>
                </tr>
                <tr>
                  <td colspan="2" style="word-wrap:break-word;text-align:center">进餐：使用餐具将饭菜送入口、咀嚼、吞咽等活动</td>
                  <td colspan="6" class="pad10">
                    <el-select @change="score" v-model="editForm.meal" placeholder="请选择" style="width:100%;">
                      <el-option key="a" label="(0分)可自理：独立完成" value="可自理(0分)"></el-option>
                      <el-option key="b" label="(0分)轻度依赖" value="轻度依赖(0)分"></el-option>
                      <el-option key="c" label="(3分)中度依赖：需要协助、如切碎、搅拌食物" value="中度依赖(3)分"></el-option>
                      <el-option key="d" label="(5分)不能自理：完全需要帮助" value="不能自理(5)分"></el-option>
                    </el-select>
                  </td>
                </tr>
                <tr>
                  <td colspan="2" style="word-wrap:break-word;text-align:center;">梳洗：梳头、洗脸、刷牙、剃须、洗澡等活动</td>
                  <td colspan="6" class="pad10">
                    <el-select @change="score" v-model="editForm.washing" placeholder="请选择" style="width:100%;">
                      <el-option key="a1" label="(0分)可自理：独立完成" value="可自理(0分)"></el-option>
                      <el-option key="b1" label="(1分)轻度依赖：能独立洗头、梳头、洗脸、刷牙、剃须等;洗澡需要协助" value="轻度依赖(1)分"></el-option>
                      <el-option key="c1" label="(3分)中度依赖：在协助下和适当的时间内，能完成部分梳洗活动" value="中度依赖(3)分"></el-option>
                      <el-option key="d1" label="(7分)不能自理：完全需要帮助" value="不能自理(7)分"></el-option>
                    </el-select>
                  </td>
                </tr>
                <tr>
                  <td colspan="2" style="word-wrap:break-word;text-align:center">穿衣：穿衣服、袜子、鞋子等活动</td>
                  <td colspan="6" class="pad10">
                    <el-select @change="score" v-model="editForm.dressing" placeholder="请选择" style="width:100%;">
                      <el-option key="a2" label="(0分)可自理：独立完成" value="可自理(0分)"></el-option>
                      <el-option key="b2" label="(0分)轻度依赖" value="轻度依赖(0)分"></el-option>
                      <el-option key="c2" label="(3分)中度依赖：需要协助，在适当的时间内完成部分穿衣" value="中度依赖(3)分"></el-option>
                      <el-option key="d2" label="(5分)不能自理" value="不能自理(5)分"></el-option>
                    </el-select>
                  </td>
                </tr>
                <tr>
                  <td colspan="2" style="word-wrap:break-word;text-align:center">如厕：小便、大便等活动及自控</td>
                  <td colspan="6" class="pad10">
                    <el-select @change="score" v-model="editForm.toilet" placeholder="请选择" style="width:100%;">
                      <el-option key="a3" label="(0分)可自理：不需协助，可自控" value="可自理(0分)"></el-option>
                      <el-option key="b3" label="(1分)轻度依赖：偶尔失禁，但基本上能如厕或使用便具" value="轻度依赖(1)分"></el-option>
                      <el-option key="c3" label="(5分)中度依赖：经常失禁，在很多提示和协助下尚能如厕或使用便具" value="中度依赖(5)分"></el-option>
                      <el-option key="d3" label="(10分)不能自理：完全失禁，完全需要帮助" value="不能自理(10)分"></el-option>
                    </el-select>
                  </td>
                </tr>
                <tr>
                  <td colspan="2" style="word-wrap:break-word;text-align:center">活动：站立、室内行走、上下楼梯、户外活动</td>
                  <td colspan="6" class="pad10">
                    <el-select @change="score" v-model="editForm.activity" placeholder="请选择" style="width:100%;">
                      <el-option key="a4" label="(0分)可自理：独立完成所有活动" value="可自理(0分)"></el-option>
                      <el-option key="b4" label="(1分)轻度依赖：借助较小的外力或辅助装置能完成站立、行走、上下楼梯等" value="轻度依赖(1)分"></el-option>
                      <el-option key="c4" label="(5分)中度依赖：借助较大的外力才能完成站立、行走、不能上下楼梯等" value="中度依赖(5)分"></el-option>
                      <el-option key="d4" label="(10分)不能自理：卧床不起，活动完全需要帮助" value="不能自理(10)分"></el-option>
                    </el-select>
                  </td>
                </tr>
                <tr>
                  <td colspan="2" style="word-wrap:break-word;text-align:center">总得分</td>
                  <td colspan="6" class="pad10">
                    <el-input v-model="editForm.totalScore" auto-complete="off" readonly></el-input>
                  </td>
                </tr>
                </tbody>
              </table>
              <!--<el-col :span="12">-->
              <!--<el-form-item label="系统编号">-->
              <!--<el-input v-model="editForm.id" ></el-input>-->
              <!--</el-form-item>-->
              <!--</el-col>-->
            </el-tabs>
          </div>
        </EasyScrollbar>
      </el-form>
      </div>

      <div slot="footer" class="dialog-footer">
        <el-button style="height:40px"  @click="conprint()">打印</el-button>
        <el-button style="height:40px"  v-if="dialogStatus=='create'" type="primary" @click="check">添加</el-button>
        <el-button style="height:40px"  v-else type="primary" v-if="edit" @click="createData">新增修改</el-button>
        <el-button style="height:40px"  v-else type="primary" @click="updateData" v-if="edit">修改</el-button>
        <el-button style="height:40px"  @click.native="dialogFormVisible=false">取消</el-button>
      </div>
    </el-dialog>
  </section>
</template>

<script>
  import util from '@/utils/table.js'
  import {
    getList,
    removeData,
    batchremoveData,
    editData,
    addData,
    checkData as getPerson
  } from '@/api/selfcareAbility'
  import city from '@/api/city.js'
  import {
    getList as getSearchMemberPage
  } from '@/api/Perinfor'
  export default {
    data() {
      return {
        buttons: {
          KPSA_C: false,
          KPSA_E: false,
          KPSA_D: false
        },
        edit: true,
        tableHeight: window.innerHeight - 220,
        tablebodyHeight: window.innerHeight - 320,
        options: city.city(),
        dialogStatus: '',
        textMap: {
          update: '编辑',
          create: '添加',
          search: '查询',
          detail: '详情'
        },
        dialogFormVisible: false,
        dialogFormVisible2: false,
        findForm: {
          formDate_s: null,
          formDate_e: null
        },
        filters: {
          name: '',
          peopleId: ''
        },
        users: [],
        total: 0,
        page: 1,
        sels: [], // 列表选中列
        editFormRules: {
          peopleId: [
            { required: true, message: ' ', trigger: 'blur' },
            { pattern: /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/, message: ' ' }
          ],
          name: [{ required: true, message: ' ', trigger: 'blur' }],
          area: [{ required: true, message: ' ', trigger: 'blur' }]

        },
        // 编辑界面数据
        editForm: {
          id: '0',
          area: [],
          peopleId: '',
          meal: '',
          washing: '',
          dressing: '',
          activity: '',
          toilet: '',
          totalScore: '',
          name: '',
          workid: '',
          systemid: '',
          formDate: '',
          creator: '',
          createDate: null,
          modifier: '',
          modifyDate: null
        },
        addFormVisible: false, // 新增界面是否显示
        addFormRules: {
          name: [{ required: true, message: '请输入姓名', trigger: 'blur' }]
        }
      }
    },
    methods: {
      goto(index, row) {
        this.$router.push({
          name: '首页',
          params: {
            hid: row.peopleId
          }
        })
      },
      // 计分
      getscore(score) {
        if (score !== '' && score !== null) {
          score = score.substring(score.length - 3, score.length - 2)
          return Number(score)
        } else { return 0 }
      },
      score: function() {
        this.editForm.totalScore = this.getscore(this.editForm.washing) + this.getscore(this.editForm.dressing) + this.getscore(this.editForm.meal) +
        this.getscore(this.editForm.activity) + this.getscore(this.editForm.toilet)
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
        var str = style + '<div id="dataPanel" style="position:absolute;left:50%;margin-left:-397px;">\n' +
        '  <div id="topContent">\n' +
        '     <h2 style="width:794px;text-align:center">老年人生活自理能力评估表</h2>  \n' +
        '       <span style="margin-left:34px;">姓名:{{name}}</span>' +
          '     <span style="margin-left:410px;">工作编号:{{workid}}</span>            ' +
          '     <span>系统编号:{{systemid}}</span>' +
          '</div>       \n' +
        '   <table id="table"  border="1"  cellspacing="0" bordercolor="#000000" style="width:730px;margin-left:30px;margin-top:5px;border-collapse:collapse;"  width = "80%"> \n' +
        '    <tbody>\n' +
        '<tr>\n' +
        '      <td rowspan="2" style="word-wrap:break-word;width: 10%;text-align:center">评估事项,内容与评分</td>\n' +
        '      <td colspan=\'5\' style="word-wrap:break-word;width: 10%;text-align:center">程度等级</td>\n' +
        '    </tr>\n' +
        '    <tr>\n' +
        '      <td style="width: 20%;text-align:center">可自理</td>\n' +
        '      <td style="width: 20%;text-align:center">轻度依赖</td>\n' +
        '      <td style="width: 20%;text-align:center">中度依赖</td>\n' +
        '      <td style="width: 20%;text-align:center">不能自理</td>\n' +
        '      <td style="width: 20%;text-align:center">判断评分</td>\n' +
        '    </tr>\n' +
        '</tbody>\n' +
        '    <tbody id="secondContent">\n' +
        '    <tr id="tr_${varStatus.index}"><!-- pictureBookInfo -->\n' +
        '      <td style="word-wrap:break-word;width: 10%;text-align:center">进餐:使用餐具将饭送入口,咀嚼,吞咽等活动<br/>评分</td>\n' +
        '      <td style="text-align:center">独立完成<br/><br/>0</td>\n' +
        '      <td style="text-align:center">-<br/><br/>0</td>\n' +
        '      <td style="text-align:center">需要协助,如切碎,搅拌事物等<br/>3</td>\n' +
        '      <td style="text-align:center">完全需要帮助<br/><br/>5</td>\n' +
        '      <td style="text-align:center">{{meal}}</td>\n' +
        '    </tr>\n' +
        '    <tr id="tr_${varStatus.index}"><!-- pictureBookInfo -->\n' +
        '      <td style="word-wrap:break-word;width: 10%;text-align:center">梳洗:梳头,洗脸,刷牙,剃须,洗澡等<br/>评分</td>\n' +
        '      <td style="text-align:center">独立完成<br/><br/>0</td>\n' +
        '      <td style="text-align:center">能独立地洗头,梳头,洗脸,刷牙,剃须等;洗澡需要协助<br/><br/>1</td>\n' +
        '      <td style="text-align:center">在协助下和适当的时间内,能完成部分梳洗活动<br/>3</td>\n' +
        '      <td style="text-align:center">完全需要帮助<br/><br/>7</td>\n' +
        '      <td style="text-align:center">{{washing}}</td>\n' +
        '    </tr>\n' +
        '\n' +
        '    <tr id="tr_${varStatus.index}"><!-- pictureBookInfo -->\n' +
        '      <td style="word-wrap:break-word;width: 10%;text-align:center">穿衣:穿衣裤,袜子,鞋子等活动<br/>评分</td>\n' +
        '      <td style="text-align:center">独立完成<br/><br/>0</td>\n' +
        '      <td style="text-align:center">-<br/><br/>0</td>\n' +
        '      <td style="text-align:center">需要协助,在适当时间内完成部分穿衣<br/>3</td>\n' +
        '      <td style="text-align:center">完全需要帮助<br/><br/>5</td>\n' +
        '      <td style="text-align:center">{{dressing}}</td>\n' +
        '    </tr>\n' +
        '    <tr id="tr_${varStatus.index}"><!-- pictureBookInfo -->\n' +
        '      <td style="word-wrap:break-word;width: 10%;text-align:center">如厕:小便,大便等活动及自控<br/>评分</td>\n' +
        '      <td style="text-align:center">独立完成<br/><br/>0</td>\n' +
        '      <td style="text-align:center">不需要协助,可自控<br/><br/></td>\n' +
        '      <td style="text-align:center">偶尔失禁,但基本上能如厕或使用便具<br/>5</td>\n' +
        '      <td style="text-align:center">完全失禁,完全需要帮助<br/><br/>10</td>\n' +
        '      <td style="text-align:center">{{toilet}}</td>\n' +
        '    </tr>\n' +
        '    <tr id="tr_${varStatus.index}"><!-- pictureBookInfo -->\n' +
        '      <td style="word-wrap:break-word;width: 10%;text-align:center">活动:站立,室内行走,上下楼梯,户外活动<br/>评分</td>\n' +
        '      <td style="text-align:center">独立完成所有活动<br/><br/>0</td>\n' +
        '      <td style="text-align:center">借助较小的外力或辅助装置能完成站立,行走,不能上下楼梯<br/><br/>5</td>\n' +
        '      <td style="text-align:center">偶尔失禁,但基本上能如厕或使用便具<br/>5</td>\n' +
        '      <td style="text-align:center">卧床不起,活动完全需要帮助<br/><br/>10</td>\n' +
        '      <td style="text-align:center">{{activity}}</td>\n' +
        '    </tr>\n' +
        '    <td style="word-wrap:break-word;text-align:center" colspan=\'5\'>总得分</td>\n' +
        '    <td style="text-align:center">{{totalScore}}</td>\n' +
        '\n' +
        '    </tbody>\n' +
        '  </table>\n' +
          '  <div id="Content">\n' +
          '     <span style="margin-left:34px;">填表日期:{{formDate}}</span>            ' +
          '</div>       \n' +
          '</div>'
        str = str.replace(/{{name}}/, this.editForm.name ? this.editForm.name : '')
        str = str.replace(/{{workid}}/, this.editForm.workid ? this.editForm.workid : '')
        str = str.replace(/{{systemid}}/, this.editForm.systemid ? this.editForm.systemid : '')
        str = str.replace(/{{meal}}/, this.editForm.meal ? this.editForm.meal : '')
        str = str.replace(/{{washing}}/, this.editForm.washing ? this.editForm.washing : '')
        str = str.replace(/{{dressing}}/, this.editForm.dressing ? this.editForm.dressing : '')
        str = str.replace(/{{toilet}}/, this.editForm.toilet ? this.editForm.toilet : '')
        str = str.replace(/{{activity}}/, this.editForm.activity ? this.editForm.activity : '')
        str = str.replace(/{{totalScore}}/, this.editForm.totalScore ? this.editForm.totalScore : '')
        str = str.replace(/{{formDate}}/, this.editForm.formDate ? this.editForm.formDate : '')
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
      // 获取用户列表
      getUsers() {
        this.findForm.formDate_s =
          !this.findForm.formDate_s || this.findForm.formDate_s === ''
            ? null
            : util.formatDate.format(new Date(this.findForm.formDate_s), 'yyyy-MM-dd')
        this.findForm.formDate_e =
          !this.findForm.formDate_e || this.findForm.formDate_e === ''
            ? null
            : util.formatDate.format(new Date(this.findForm.formDate_e), 'yyyy-MM-dd')
        const para = {
          page: this.page,
          size: 20,
          filters: {
            name: this.filters.name,
            peopleId: this.filters.peopleId,
            formDate_s: this.findForm.formDate_s,
            formDate_e: this.findForm.formDate_e
          }
        }
        // 点击关键弹窗
        this.dialogFormVisible2 = false
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
          formDate_s: null,
          formDate_e: null
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
          .catch(() => {
          })
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
        this.edit = true
        this.editForm.area = this.editForm.area.split('/')
      },
      // 显示新增界面
      handleAdd() {
        this.dialogStatus = 'create'
        this.dialogFormVisible = true
        this.edit = false
        this.editForm = {
          id: '0',
          area: [],
          peopleId: '',
          workid: '',
          meal: '',
          washing: '',
          dressing: '',
          activity: '',
          toilet: '',
          totalScore: '',
          name: '',
          systemid: '',
          formDate: new Date(),
          creator: this.$store.getters.name,
          createDate: null,
          modifier: '',
          modifyDate: null
        }
      },
      // 查询
      search() {
        this.page = 1
        this.getUsers()
      },
      highsearch() {
        this.page = 1
        this.getUsers()
      },
      searchwindow() {
        this.findForm = {
          name: '',
          peopleId: '',
          meal: '',
          washing: '',
          formDate_s: '',
          formDate_e: ''
        }
        this.dialogStatus = 'search'
        this.dialogFormVisible2 = true
      },
      findbtn() {
        this.dialogStatus = 'find'
        this.dialogFormVisible2 = true
        this.findForm = {
          name: '',
          peopleId: '',
          meal: '',
          washing: '',
          formDate_s: '',
          formDate_e: ''
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
                para.formDate =
                  !para.formDate || para.formDate === ''
                    ? null
                    : util.formatDate.format(new Date(para.formDate), 'yyyy-MM-dd')
                para.area = ''
                for (const itm of this.editForm.area) {
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
                this.$message.error('未建立个人档案或该居民不是老人')
              }
            })
          } else {
            this.$message.error('未建立个人档案或该居民不是老人')
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
                para.formDate =
                  !para.formDate || para.formDate === ''
                    ? null
                    : util.formatDate.format(new Date(para.formDate), 'yyyy-MM-dd')
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
            batchremoveData(para).then(res => {
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
                  this.active = 2
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

