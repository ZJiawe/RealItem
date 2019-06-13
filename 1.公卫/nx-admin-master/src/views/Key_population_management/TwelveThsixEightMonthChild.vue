<template>
  <section class="app-container">

    <el-col :span="24" class="toolbar" style="padding-bottom: 0px;">
      <el-form :inline="true" :model="filters" @submit.native.prevent>
        <el-form-item>
          <el-input v-model="filters.name" placeholder="姓名" style="width: 100%;"></el-input>
        </el-form-item>
        <el-form-item>
          <el-input v-model="filters.peopleId" placeholder="身份证号" style="width: 100%;"></el-input>
        </el-form-item>
        <el-form-item>
          <el-date-picker type="date" placeholder="开始日期" v-model="filters.startTime" style="width: 100%;"></el-date-picker>
        </el-form-item>
        <el-form-item>
          <el-date-picker type="date" placeholder="结束日期" v-model="filters.endTime" style="width: 100%;"></el-date-picker>
        </el-form-item>

        <el-form-item>
          <el-button type="primary" v-on:click="search" style="background-color:#4db2ee;border-color:transparent;height: 40px" icon="el-icon-search">查询</el-button>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="handleAdd" style="height: 40px" icon="el-icon-circle-plus-outline" v-if="buttons.KPT_C">新增</el-button>
        </el-form-item>
      </el-form>
    </el-col>

    <!--列表-->
    <el-table :data="users" :height="tableHeight" highlight-current-row @selection-change="selsChange" style="width: 200%;" border @row-dblclick="handleRowChange">
      <el-table-column type="index" label="序号" width="80">
      </el-table-column>
      <el-table-column prop="id" v-if="false" label="主键" width="120">
      </el-table-column>
      <el-table-column prop="deleteState" v-if=false label="删除状态" width="60" >
      </el-table-column>
      <el-table-column prop="name" label="姓名" width="150">
      </el-table-column>
      <el-table-column prop="peopleId" label="身份证号" width="250">
      </el-table-column>
      <el-table-column prop="month" label="月龄" min-width="120">
      </el-table-column>
      <el-table-column prop="followDate" label="随访日期" min-width="120">
      </el-table-column>
      <el-table-column prop="development" label="发育评估" min-width="180">
      </el-table-column>
      <el-table-column prop="guidance" label="指导" min-width="120">
      </el-table-column>
      <el-table-column prop="doctor" label="医生" min-width="120">
      </el-table-column>
      <el-table-column label="操作" width="200">
        <template slot-scope="scope">
          <a size="14px" @click="goto(scope.$index, scope.row)" style="color:#4DB2EE;margin-right:20px;">查看档案</a>
          <a size="14px" @click="handleEdit(scope.$index, scope.row)" style="color:#4DB2EE;margin-right:20px;" v-if="buttons.KPT_E">编辑</a>
          <a size="14px" @click="handleDel(scope.$index, scope.row)" style="color:#FB4141" v-if="buttons.KPT_D">删除</a>
        </template>
      </el-table-column>
    </el-table >
    <!--工具条-->
    <el-col :span="24" class="toolbar">
      <el-button style="height: 40px" type="danger" v-if="false" @click="batchRemove" :disabled="this.sels.length===0">批量删除</el-button>
      <el-pagination layout="prev, pager, next" :current-page.sync="page" @current-change="handleCurrentChange" :page-size="20" :total="total" style="float:right;">
      </el-pagination>
    </el-col>
    <!--编辑界面-->
    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible" :close-on-click-modal="false" center>
      <div id="print">
        <el-form :model="editForm" label-width="110px" :rules="editFormRules" ref="editForm">
          <EasyScrollbar>
            <div class="item" v-bind:style="{ height: tablebodyHeight + 'px'}">
              <el-tabs v-model="activeName">
                <div id="dataPanel">
                  <table id="table"  border="1"  cellspacing="0" bordercolor="#dddddd" style="margin-left:10px;margin-right:30px;border-collapse:collapse;">
                    <tbody>
                    <tr v-if="dialogStatus==='detail'">
                      <td style="word-wrap:break-word;text-align:center;width:100px;">
                        创建人
                      </td>
                      <td colspan="3" class="pad10" style="text-align:center">
                        <el-form-item label="" label-width="0px">
                          <el-input v-model="editForm.creator" style="width: 100%" v-if="dialogStatus==='detail'" readonly></el-input>
                        </el-form-item>
                      </td>
                      <td style="word-wrap:break-word;text-align:center;">
                        创建时间
                      </td>
                      <td colspan="3" class="pad10" style="text-align:center">
                        <el-form-item label="" label-width="0px" v-if="dialogStatus==='detail'">
                          <el-date-picker type="date" placeholder="选择日期" v-model="editForm.createDate" style="width: 100%" v-if="dialogStatus==='detail'" readonly></el-date-picker>
                        </el-form-item>
                      </td>
                    </tr>
                    <tr v-if="dialogStatus==='detail'">
                      <td style="word-wrap:break-word;text-align:center;width:100px;">
                        修改人
                      </td>
                      <td colspan="3" class="pad10" style="text-align:center">
                        <el-form-item label="" label-width="0px" v-if="dialogStatus==='detail'">
                          <el-input v-model="editForm.modifier" style="width: 100%" v-if="dialogStatus==='detail'" readonly></el-input>
                        </el-form-item>
                      </td>
                      <td style="word-wrap:break-word;text-align:center;width:100px;">
                        修改时间
                      </td>
                      <td colspan="3" class="pad10" style="text-align:center">
                        <el-form-item label="" label-width="0px" v-if="dialogStatus==='detail'">
                          <el-date-picker type="date" placeholder="选择日期" v-model="editForm.modifyDate" style="width: 100%" v-if="dialogStatus==='detail'" readonly></el-date-picker>
                        </el-form-item>
                      </td>
                    </tr>
                    <tr>
                      <td style="word-wrap:break-word;text-align:center" class="pad10">
                        <el-col :span="12" v-if='false'>
                          <el-form-item label="编号">
                            <el-input v-model="editForm.id"></el-input>
                          </el-form-item>
                        </el-col>
                        <span>*</span>身份证
                      </td>
                      <td colspan="3" class="pad10" style="text-align:center;">
                        <el-form-item label="" label-width="0px" prop="peopleId">
                          <el-input v-model="editForm.peopleId" style="width: 103%;" placeholder="请输入身份证" v-on:input="inputFunc"></el-input>
                        </el-form-item>
                      </td>
                      <td style="width:140px;word-wrap:break-word;text-align:center;" class="pad"><span>*</span>姓名</td>
                      <td colspan="2" class="pad10" style="text-align:center">
                        <el-form-item label="" label-width="0px" prop="name">
                          <el-input v-model="editForm.name" style="width: 104%;" placeholder="请输入姓名" disabled="false"></el-input>
                        </el-form-item>
                      </td>
                    </tr>

                    <tr>
                      <td style="word-wrap:break-word;text-align:center" class="pad"><span>*</span>月龄</td>
                      <td colspan="3" class="pad20" style="text-align:left;">
                          <el-radio-group v-model="editForm.month">
                            <table><tr>
                              <td><el-radio label=12月龄>12月龄</el-radio></td>
                              <td><el-radio label=18月龄>18月龄</el-radio></td>
                              <td><el-radio label=24月龄>24月龄</el-radio></td>
                              <td><el-radio label=30月龄>30月龄</el-radio></td>
                            </tr></table>
                          </el-radio-group>
                      </td>
                      <td style="word-wrap:break-word;text-align:center">随访日期</td>
                      <td colspan="2" class="pad10" style="text-align:center">
                        <el-date-picker type="date" placeholder="选择日期" v-model="editForm.followDate"  style="width: 100%;" ></el-date-picker>
                      </td>
                    </tr>

                    <tr>
                      <td style="word-wrap:break-word;text-align:center">体重</td>
                      <td colspan="3" class="pad10" style="text-align:center">
                        <el-input v-model="editForm.weight"  auto-complete="off">
                          <template slot="append">kg</template>
                        </el-input>
                      </td>
                      <td style="word-wrap:break-word;text-align:center">身长(高)</td>
                      <td colspan="2" class="pad10" style="text-align:center">
                        <el-input  v-model="editForm.height" auto-complete="off" >
                          <template slot="append">cm</template>
                        </el-input>
                      </td>
                    </tr>

                    <tr>
                      <td rowspan="10" colspan="1"style="word-wrap:break-word;text-align:center">体格检查</td>
                      <td colspan="1" style="text-align:center; width: 100px">面色</td>
                      <td colspan="2" class="pad20" style="text-align:left">
                        <el-radio-group v-model="editForm.faceColor">
                          <el-radio label=红润>红润</el-radio>
                          <el-radio label=其他>其他</el-radio>
                        </el-radio-group>
                      </td>
                      <td colspan="1" style="text-align:center;width: 100px">皮肤</td>
                      <td colspan="2" class="pad20" style="text-align:left">
                        <el-radio-group v-model="editForm.skin">
                          <el-radio label=未见异常>未见异常</el-radio>
                          <el-radio label=异常>异常</el-radio>
                        </el-radio-group>
                      </td>
                    </tr>

                    <tr>
                      <td colspan="1" style="word-wrap:break-word;text-align:center;width: 100px">前囟</td>
                      <td colspan="5" style="text-align:left" class="pad20">
                        <table>
                          <tr>
                            <td style="text-align:left;padding:0px;" class="pad20">
                              <el-radio-group v-model="editForm.bregma">
                                <el-radio label=闭合>闭合</el-radio>
                                <el-radio label=未闭>未闭</el-radio>
                              </el-radio-group>
                            </td>
                          </tr>
                          <tr>
                            <td style="text-align:right;padding:0px;" class="pad5">
                              <el-input v-model="editForm.bregmaWidth"  auto-complete="off" >
                                <template slot="append">cm*</template>
                              </el-input>
                            </td>
                            <td style="text-align:right;padding:0px;" class="pad5">
                              <el-input v-model="editForm.bregmaHeight"  auto-complete="off" >
                                <template slot="append">cm</template>
                              </el-input>
                            </td>
                          </tr>
                        </table>
                      </td>
                    </tr>

                    <tr>
                      <td colspan="1" style="text-align:center;width: 100px">眼睛</td>
                      <td colspan="2" class="pad20" style="text-align:left">
                        <el-radio-group v-model="editForm.eye">
                          <el-radio label=未见异常>未见异常</el-radio>
                          <el-radio label=异常>异常</el-radio>
                        </el-radio-group>
                      </td>
                      <td colspan="1" style="text-align:center;width: 100px">耳外观</td>
                      <td colspan="2" class="pad20" style="text-align:left">
                        <el-radio-group v-model="editForm.ear">
                          <el-radio label=未见异常>未见异常</el-radio>
                          <el-radio label=异常>异常</el-radio>
                        </el-radio-group>
                      </td>
                    </tr>

                    <tr>
                      <td colspan="1" style="text-align:center;width: 100px">听力</td>
                      <td colspan="2" class="pad10">
                        <el-input v-model="editForm.hearing" auto-complete="off"></el-input>
                      </td>
                      <td colspan="1" style="text-align:center;width: 100px"></td>
                      <td colspan="2" class="pad10">
                      </td>
                    </tr>

                    <tr>
                      <td colspan="1" style="text-align:center;width: 100px">出牙数</td>
                      <td colspan="2" class="pad10">
                        <el-input v-model="editForm.mouth" auto-complete="off" >
                          <template slot="append">颗</template>
                        </el-input>
                      </td>
                      <td colspan="1" style="text-align:center;width: 100px">龋齿数</td>
                      <td colspan="2" class="pad10">
                        <el-input v-model="editForm.bmouth" auto-complete="off" >
                          <template slot="append">颗</template>
                        </el-input>
                      </td>
                    </tr>

                    <tr>
                      <td colspan="1" style="text-align:center;width: 100px">胸部</td>
                      <td colspan="2" class="pad20" style="text-align:left">
                        <el-radio-group v-model="editForm.chest">
                          <el-radio label=未见异常>未见异常</el-radio>
                          <el-radio label=异常>异常</el-radio>
                        </el-radio-group>
                      </td>
                      <td colspan="1" style="text-align:center;width: 100px">腹部</td>
                      <td colspan="2" class="pad20" style="text-align:left">
                        <el-radio-group v-model="editForm.abdomen">
                          <el-radio label=未见异常>未见异常</el-radio>
                          <el-radio label=异常>异常</el-radio>
                        </el-radio-group>
                      </td>
                    </tr>

                    <tr>
                      <td colspan="1" style="text-align:center;width: 100px">四肢</td>
                      <td colspan="2" class="pad20" style="text-align:left">
                        <el-radio-group v-model="editForm.limb">
                          <el-radio label=未见异常>未见异常</el-radio>
                          <el-radio label=异常>异常</el-radio>
                        </el-radio-group>
                      </td>
                      <td colspan="1" style="text-align:center;width: 100px">步态</td>
                      <td colspan="2" class="pad10">
                        <el-input  v-model="editForm.gait" auto-complete="off"  ></el-input>
                      </td>
                    </tr>


                    <tr>
                      <td colspan="1" style="text-align:center;width: 100px">可疑佝偻病症状</td>
                      <td colspan="2" class="pad10">
                        <el-select v-model="editForm.ricketsBody" placeholder="请选择" style="width:100%;">
                          <el-option key="无" label="无" value="无"></el-option>
                          <el-option key="肋串珠" label="肋串珠" value="肋串珠"></el-option>
                          <el-option key="肋软骨沟" label="肋软骨沟" value="肋软骨沟"></el-option>
                          <el-option key="鸡胸" label="鸡胸" value="鸡胸"></el-option>
                          <el-option key="手足镯" label="手足镯" value="手足镯"></el-option>
                          <el-option key="“O”型腿" label="“O”型腿" value="“O”型腿"></el-option>
                          <el-option key="“X”型腿" label="“X”型腿" value="“X”型腿"></el-option>
                        </el-select>
                      </td>
                      <td colspan="1" style="text-align:center;width: 100px">血红蛋白值</td>
                      <td colspan="2" class="pad10">
                        <el-input v-model="editForm.hemoglobin" >
                          <template slot="append">g/L</template>
                        </el-input>
                      </td>
                    </tr>

                    <tr>
                      <td colspan="1" style="text-align:center;width: 100px">户外活动</td>
                      <td colspan="2" class="pad10">
                        <el-input v-model="editForm.outActivities"  auto-complete="off">
                          <template slot="append">小时/日</template>
                        </el-input>
                      </td>
                      <td colspan="1" style="text-align:center;width: 100px">服用维生素</td>
                      <td colspan="2" class="pad10">
                        <el-input v-model="editForm.vitaminD"  auto-complete="off">
                          <template slot="append">IU/日</template>
                        </el-input>
                      </td>
                    </tr>

                    <tr>
                      <td colspan="1" style="text-align:center;">发育评估</td>
                      <td colspan="5" style="text-align:left;" class="pad20">

                        <el-checkbox-group v-model="editForm.development"a style="width:100%;text-align:left;">
                          <table>
                            <tr>
                              <td style="padding-right: 15px"><el-checkbox label="对很大的声音没有反应" name="type"></el-checkbox></td>
                              <td style="padding-right: 15px"><el-checkbox label="呼唤名字无反应" name="type"></el-checkbox></td>
                              <td style="padding-right: 15px"><el-checkbox label="不会模仿“再见”或者“欢迎”动作" name="type"></el-checkbox></td>
                            </tr>
                            <tr>
                              <td style="padding-right: 15px"><el-checkbox label="不会使用拇指对程小物品" name="type"></el-checkbox></td>
                              <td style="padding-right: 15px"><el-checkbox label="不会扶物站立" name="type"></el-checkbox></td>
                            </tr>
                          </table>
                        </el-checkbox-group>
                      </td>
                    </tr>

                    <tr>
                      <td rowspan="2" colspan="1"style="word-wrap:break-word;text-align:center">两次随访期间患病情况</td>
                      <td colspan="1"style="word-wrap:break-word;text-align:center">肺炎</td>
                      <td colspan="2" class="pad10" style="text-align:left">
                        <el-input v-model="editForm.pneumonia"  auto-complete="off">
                          <template slot="append">次</template>
                        </el-input>
                      </td>
                      <td colspan="1"style="word-wrap:break-word;text-align:center">腹泻</td>
                      <td colspan="4" class="pad10" style="text-align:left">
                        <el-input v-model="editForm.diarrhea"  auto-complete="off">
                          <template slot="append">次</template>
                        </el-input>
                      </td>
                    </tr>

                    <tr>
                      <td colspan="1"style="word-wrap:break-word;text-align:center">外伤</td>
                      <td colspan="2" class="pad10" style="text-align:left">
                        <el-input v-model="editForm.trauma"  auto-complete="off">
                          <template slot="append">次</template>
                        </el-input>
                      </td>
                      <td colspan="1"style="word-wrap:break-word;text-align:center">其他</td>
                      <td  colspan="4" class="pad10" style="text-align:left">
                        <el-input v-model="editForm.other"  auto-complete="off">
                        </el-input>
                      </td>

                    </tr>

                    <tr>
                      <td style="word-wrap:break-word;text-align:center">转诊建议</td>
                      <td colspan="2" style="text-align:left" class="pad20">
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
                      <td style="text-align:center">指导</td>
                      <td colspan="6" class="pad20" style="text-align:left">
                        <el-checkbox-group v-model="editForm.guidance"a style="width:100%">
                          <el-checkbox v-for="item in guidance" :key="item.label"  :label="item.label" :value="item.value"></el-checkbox>
                        </el-checkbox-group>
                      </td>
                    </tr>

                    <tr>
                      <td style="text-align:center">中医药健康服务</td>
                      <td colspan="6" class="pad20" style="text-align:left">
                        <el-checkbox-group v-model="editForm.medicine"a style="width:100%">
                          <el-checkbox v-for="item in medicine" :key="item.label" :name="type" :label="item.label" :value="item.value"></el-checkbox>
                        </el-checkbox-group>
                      </td>
                    </tr>

                    <tr>
                      <td style="text-align:center">下次随访日期</td>
                      <td colspan="3" class="pad10" style="text-align:center;">
                        <el-date-picker type="date" placeholder="选择日期" v-model="editForm.nextDate" style="width: 100%;" ></el-date-picker>
                      </td>
                      <td style="text-align:center" class="pad"><span>*</span>随访医生签名</td>
                      <td colspan="2" class="pad10" style="text-align:center;">
                        <el-form-item label="" label-width="0px" prop="doctor">
                          <el-input v-model="editForm.doctor" style="width: 104%" placeholder="请输入随访医生签名" ></el-input>
                        </el-form-item>
                      </td>
                    </tr>
                    </tbody>
                  </table>
                </div>
              </el-tabs>
            </div>
          </EasyScrollbar>
        </el-form>
      </div>
      <div slot="footer" class="dialog-footer">
        <el-button @click="conprint()" style="height: 40px">打印</el-button>
        <el-button v-if="dialogStatus=='create'" type="primary" @click="check" style="height: 40px">添加</el-button>
        <el-button v-else type="primary" v-if="edit" @click="check" style="height: 40px">新增修改</el-button>
        <el-button v-else type="primary" v-if="edit" @click="updateData" style="height: 40px">修改</el-button>
        <el-button @click.native="dialogFormVisible=false" style="height: 40px">取消</el-button>
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
    addData,
    printData
  } from '@/api/twelveThsixEightMonthChild'
  import { checkData as getPerson } from '@/api/OneEightMonthChild'
  import {
    getList as getSearchMemberPage
  } from '@/api/Perinfor'
  export default {
    data() {
      return {
        buttons: {
          KPT_C: false,
          KPT_E: false,
          KPT_D: false
        },
        edit: false,
        activeName: 'first',
        tableHeight: window.innerHeight - 220,
        tablebodyHeight: window.innerHeight - 320,
        development: [
          { value: '对很大的声音没有反应', label: '对很大的声音没有反应' },
          { value: '呼唤名字无反应', label: '呼唤名字无反应' },
          { value: '不会模仿“再见”或者“欢迎”动作', label: '不会模仿“再见”或者“欢迎”动作' },
          { value: '不会使用拇指对程小物品', label: '不会使用拇指对程小物品' },
          { value: '不会扶物站立', label: '不会扶物站立' }
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
          endTime: '',
          peopleId: ''
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
            { required: true, message: ' ', trigger: 'blur' },
            { pattern: /^[\u4E00-\u9FA5]+$/, message: ' '
            }
          ],
          month: [
            { required: true, message: ' ', trigger: 'blur' }
          ],
          peopleId: [
            { required: true, message: ' ', trigger: 'blur' },
            { pattern: /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/, message: ' ' }],
          number: [
            { type: 'number', message: ' ', trigger: 'blur' }
          ],
          doctor: [
            { required: true, message: ' ', trigger: 'blur' }
          ]
        },
        // 编辑界面数据
        editForm: {
          name: '',
          guidance: [],
          development: [],
          medicine: [],
          creator: '',
          createDate: null,
          modifier: '',
          modifyDate: null
        },
        addFormVisible: false, // 新增界面是否显示
        addFormRules: {
          name: [
            { required: true, message: ' ', trigger: 'blur' },
            { pattern: /^[\u4E00-\u9FA5]+$/, message: ' '
            }
          ],
          month: [
            { required: true, message: ' ', trigger: 'blur' }
          ],
          peopleId: [
            { required: true, message: ' ', trigger: 'blur' },
            { pattern: /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/, message: ' ' }],
          number: [
            { type: 'number', message: ' ', trigger: 'blur' }
          ],
          doctor: [
            { required: true, message: ' ', trigger: 'blur' }
          ]
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
          '    <h2 style="width:794px;text-align:center"> 12~30月龄儿童健康检查记录表 </h2>  \n' +
          '    <span style="margin-left:34px;">姓    名：{{name}}</span>\n' +
          '    <span style="margin-left:260px;">编号:{{number}}</span> \n' +
          '    </div>\n' +
          '  <table id="table"  border="1"  cellspacing="0" bordercolor="#000000" style="width:730px;margin-left:30px;margin-top:5px;border-collapse:collapse;"  width = "80%">\n' +
          '    <tbody>\n' +
          '      <tr>\n' +
          '        <td style="width: 146px;text-align:center">月龄</td>\n' +
          '        <td colspan=\'1\' style="width: 146px">{{month1}}</td>\n' +
          '        <td colspan=\'1\' style="width: 146px">{{month2}}</td>\n' +
          '        <td colspan=\'1\' style="width: 146px">{{month3}}</td>\n' +
          '        <td colspan=\'1\' style="width: 146px">{{month4}}</td>\n' +
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
          '        <td style="width: 20%;text-align:center">身长/cm</td>\n' +
          '        <td colspan=\'1\' style="width: 146px">{{height1}}</td>\n' +
          '        <td colspan=\'1\' style="width: 146px">{{height2}}</td>\n' +
          '        <td colspan=\'1\' style="width: 146px">{{height3}}</td>\n' +
          '        <td colspan=\'1\' style="width: 146px">{{height4}}</td>\n' +
          '      </tr>\n' +
          '      <tr>\n' +
          '        <td style="width: 20%;text-align:center">面色</td>\n' +
          '        <td colspan=\'1\' style="width: 146px">{{faceColor1}}</td>\n' +
          '        <td colspan=\'1\' style="width: 146px">{{faceColor2}}</td>\n' +
          '        <td colspan=\'1\' style="width: 146px">{{faceColor3}}</td>\n' +
          '        <td colspan=\'1\' style="width: 146px">{{faceColor4}}</td>\n' +
          '      </tr>\n' +
          '      <tr>\n' +
          '        <td style="width: 146px;text-align:center">皮肤</td>\n' +
          '        <td colspan=\'1\' style="width: 146px">{{skin1}}</td>\n' +
          '        <td colspan=\'1\' style="width: 146px">{{skin2}}</td>\n' +
          '        <td colspan=\'1\' style="width: 146px">{{skin3}}</td>\n' +
          '        <td colspan=\'1\' style="width: 146px">{{skin4}}</td>\n' +
          '      </tr>\n' +
          '      <tr>\n' +
          '        <td style="width: 146px;text-align:center">前囟</td>\n' +
          '        <td colspan=\'1\' style="width: 146px">{{bregma1}}</td>\n' +
          '        <td colspan=\'1\' style="width: 146px">{{bregma2}}</td>\n' +
          '        <td colspan=\'1\' style="width: 146px">{{bregma3}}</td>\n' +
          '        <td colspan=\'1\' style="width: 146px">{{bregma4}}</td>\n' +
          '      </tr>\n' +
          '      <tr>\n' +
          '        <td style="width: 146px;text-align:center">前囟宽度/cm</td>\n' +
          '        <td colspan=\'1\' style="width: 146px">{{bregmaWidth1}}</td>\n' +
          '        <td colspan=\'1\' style="width: 146px">{{bregmaWidth2}}</td>\n' +
          '        <td colspan=\'1\' style="width: 146px">{{bregmaWidth3}}</td>\n' +
          '        <td colspan=\'1\' style="width: 146px">{{bregmaWidth4}}</td>\n' +
          '      </tr>\n' +
          '      <tr>\n' +
          '        <td style="width: 146px;text-align:center">前囟高度/cm</td>\n' +
          '        <td colspan=\'1\' style="width: 146px">{{bregmaHeight1}}</td>\n' +
          '        <td colspan=\'1\' style="width: 146px">{{bregmaHeight2}}</td>\n' +
          '        <td colspan=\'1\' style="width: 146px">{{bregmaHeight3}}</td>\n' +
          '        <td colspan=\'1\' style="width: 146px">{{bregmaHeight4}}</td>\n' +
          '      </tr>\n' +
          '      <tr>\n' +
          '        <td style="width: 146px;text-align:center">眼睛</td>\n' +
          '        <td colspan=\'1\' style="width: 146px">{{eye1}}</td>\n' +
          '        <td colspan=\'1\' style="width: 146px">{{eye2}}</td>\n' +
          '        <td colspan=\'1\' style="width: 146px">{{eye3}}</td>\n' +
          '        <td colspan=\'1\' style="width: 146px">{{eye4}}</td>\n' +
          '      </tr>\n' +
          '      <tr>\n' +
          '        <td style="width: 146px;text-align:center">耳外观</td>\n' +
          '        <td colspan=\'1\' style="width: 146px">{{ear1}}</td>\n' +
          '        <td colspan=\'1\' style="width: 146px">{{ear2}}</td>\n' +
          '        <td colspan=\'1\' style="width: 146px">{{ear3}}</td>\n' +
          '        <td colspan=\'1\' style="width: 146px">{{ear4}}</td>\n' +
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
          '        <td style="width: 146px;text-align:center">四肢</td>\n' +
          '        <td colspan=\'1\' style="width: 146px">{{limb1}}</td>\n' +
          '        <td colspan=\'1\' style="width: 146px">{{limb2}}</td>\n' +
          '        <td colspan=\'1\' style="width: 146px">{{limb3}}</td>\n' +
          '        <td colspan=\'1\' style="width: 146px">{{limb4}}</td>\n' +
          '      </tr>\n' +
          '      <tr>\n' +
          '        <td style="width: 146px;text-align:center">步态</td>\n' +
          '        <td colspan=\'1\' style="width: 146px">{{gait1}}</td>\n' +
          '        <td colspan=\'1\' style="width: 146px">{{gait2}}</td>\n' +
          '        <td colspan=\'1\' style="width: 146px">{{gait3}}</td>\n' +
          '        <td colspan=\'1\' style="width: 146px">{{gait4}}</td>\n' +
          '      </tr>\n' +
          '      <tr>\n' +
          '        <td style="width: 146px;text-align:center">可疑佝偻病体征</td>\n' +
          '        <td colspan=\'1\' style="width: 146px">{{ricketsBody1}}</td>\n' +
          '        <td colspan=\'1\' style="width: 146px">{{ricketsBody2}}</td>\n' +
          '        <td colspan=\'1\' style="width: 146px">{{ricketsBody3}}</td>\n' +
          '        <td colspan=\'1\' style="width: 146px">{{ricketsBody4}}</td>\n' +
          '      </tr>\n' +
          '      <tr>\n' +
          '        <td style="width: 146px;text-align:center">血红蛋白值</td>\n' +
          '        <td colspan=\'1\' style="width: 146px">{{hemoglobin1}}</td>\n' +
          '        <td colspan=\'1\' style="width: 146px">{{hemoglobin2}}</td>\n' +
          '        <td colspan=\'1\' style="width: 146px">{{hemoglobin3}}</td>\n' +
          '        <td colspan=\'1\' style="width: 146px">{{hemoglobin4}}</td>\n' +
          '      </tr>\n' +
          '      <tr>\n' +
          '        <td style="width: 146px;text-align:center">户外运动</td>\n' +
          '        <td colspan=\'1\' style="width: 146px">{{outActivities1}}</td>\n' +
          '        <td colspan=\'1\' style="width: 146px">{{outActivities2}}</td>\n' +
          '        <td colspan=\'1\' style="width: 146px">{{outActivities3}}</td>\n' +
          '        <td colspan=\'1\' style="width: 146px">{{outActivities4}}</td>\n' +
          '      </tr>\n' +
          '      <tr>\n' +
          '        <td style="width: 146px;text-align:center">服用维生素D</td>\n' +
          '        <td colspan=\'1\' style="width: 146px">{{vitaminD1}}</td>\n' +
          '        <td colspan=\'1\' style="width: 146px">{{vitaminD2}}</td>\n' +
          '        <td colspan=\'1\' style="width: 146px">{{vitaminD3}}</td>\n' +
          '        <td colspan=\'1\' style="width: 146px">{{vitaminD4}}</td>\n' +
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
          '        <td style="width: 146px;text-align:center">转诊机构及科室</td>\n' +
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
          '        <td style="width: 146px;text-align:center">中医健康服务</td>\n' +
          '        <td colspan=\'1\' style="width: 146px">{{medicine1}}</td>\n' +
          '        <td colspan=\'1\' style="width: 146px">{{medicine2}}</td>\n' +
          '        <td colspan=\'1\' style="width: 146px">{{medicine3}}</td>\n' +
          '        <td colspan=\'1\' style="width: 146px">{{medicine4}}</td>\n' +
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

        str = str.replace(/{{month1}}/, this.printItem1.month ? this.printItem1.month : '')
        str = str.replace(/{{month2}}/, this.printItem2.month ? this.printItem2.month : '')
        str = str.replace(/{{month3}}/, this.printItem3.month ? this.printItem3.month : '')
        str = str.replace(/{{month4}}/, this.printItem4.month ? this.printItem4.month : '')

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

        str = str.replace(/{{faceColor1}}/, this.printItem1.faceColor ? this.printItem1.faceColor : '')
        str = str.replace(/{{faceColor2}}/, this.printItem2.faceColor ? this.printItem2.faceColor : '')
        str = str.replace(/{{faceColor3}}/, this.printItem3.faceColor ? this.printItem3.faceColor : '')
        str = str.replace(/{{faceColor4}}/, this.printItem4.faceColor ? this.printItem4.faceColor : '')

        str = str.replace(/{{skin1}}/, this.printItem1.skin ? this.printItem1.skin : '')
        str = str.replace(/{{skin2}}/, this.printItem2.skin ? this.printItem2.skin : '')
        str = str.replace(/{{skin3}}/, this.printItem3.skin ? this.printItem3.skin : '')
        str = str.replace(/{{skin4}}/, this.printItem4.skin ? this.printItem4.skin : '')

        str = str.replace(/{{bregma1}}/, this.printItem1.bregma ? this.printItem1.bregma : '')
        str = str.replace(/{{bregma2}}/, this.printItem2.bregma ? this.printItem2.bregma : '')
        str = str.replace(/{{bregma3}}/, this.printItem3.bregma ? this.printItem3.bregma : '')
        str = str.replace(/{{bregma4}}/, this.printItem4.bregma ? this.printItem4.bregma : '')

        str = str.replace(/{{bregmaWidth1}}/, this.printItem1.bregmaWidth ? this.printItem1.bregmaWidth : '')
        str = str.replace(/{{bregmaWidth2}}/, this.printItem2.bregmaWidth ? this.printItem2.bregmaWidth : '')
        str = str.replace(/{{bregmaWidth3}}/, this.printItem3.bregmaWidth ? this.printItem3.bregmaWidth : '')
        str = str.replace(/{{bregmaWidth4}}/, this.printItem4.bregmaWidth ? this.printItem4.bregmaWidth : '')

        str = str.replace(/{{bregmaHeight1}}/, this.printItem1.bregmaHeight ? this.printItem1.bregmaHeight : '')
        str = str.replace(/{{bregmaHeight2}}/, this.printItem2.bregmaHeight ? this.printItem2.bregmaHeight : '')
        str = str.replace(/{{bregmaHeight3}}/, this.printItem3.bregmaHeight ? this.printItem3.bregmaHeight : '')
        str = str.replace(/{{bregmaHeight4}}/, this.printItem4.bregmaHeight ? this.printItem4.bregmaHeight : '')

        str = str.replace(/{{eye1}}/, this.printItem1.eye ? this.printItem1.eye : '')
        str = str.replace(/{{eye2}}/, this.printItem2.eye ? this.printItem2.eye : '')
        str = str.replace(/{{eye3}}/, this.printItem3.eye ? this.printItem3.eye : '')
        str = str.replace(/{{eye4}}/, this.printItem4.eye ? this.printItem4.eye : '')

        str = str.replace(/{{ear1}}/, this.printItem1.ear ? this.printItem1.ear : '')
        str = str.replace(/{{ear2}}/, this.printItem2.ear ? this.printItem2.ear : '')
        str = str.replace(/{{ear3}}/, this.printItem3.ear ? this.printItem3.ear : '')
        str = str.replace(/{{ear4}}/, this.printItem4.ear ? this.printItem4.ear : '')

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

        str = str.replace(/{{limb1}}/, this.printItem1.limb ? this.printItem1.limb : '')
        str = str.replace(/{{limb2}}/, this.printItem2.limb ? this.printItem2.limb : '')
        str = str.replace(/{{limb3}}/, this.printItem3.limb ? this.printItem3.limb : '')
        str = str.replace(/{{limb4}}/, this.printItem4.limb ? this.printItem4.limb : '')

        str = str.replace(/{{gait1}}/, this.printItem1.gait ? this.printItem1.gait : '')
        str = str.replace(/{{gait2}}/, this.printItem2.gait ? this.printItem2.gait : '')
        str = str.replace(/{{gait3}}/, this.printItem3.gait ? this.printItem3.gait : '')
        str = str.replace(/{{gait4}}/, this.printItem4.gait ? this.printItem4.gait : '')

        str = str.replace(/{{ricketsBody1}}/, this.printItem1.ricketsBody ? this.printItem1.ricketsBody : '')
        str = str.replace(/{{ricketsBody2}}/, this.printItem2.ricketsBody ? this.printItem2.ricketsBody : '')
        str = str.replace(/{{ricketsBody3}}/, this.printItem3.ricketsBody ? this.printItem3.ricketsBody : '')
        str = str.replace(/{{ricketsBody4}}/, this.printItem4.ricketsBody ? this.printItem4.ricketsBody : '')

        str = str.replace(/{{hemoglobin1}}/, this.printItem1.hemoglobin ? this.printItem1.hemoglobin : '')
        str = str.replace(/{{hemoglobin2}}/, this.printItem2.hemoglobin ? this.printItem2.hemoglobin : '')
        str = str.replace(/{{hemoglobin3}}/, this.printItem3.hemoglobin ? this.printItem3.hemoglobin : '')
        str = str.replace(/{{hemoglobin4}}/, this.printItem4.hemoglobin ? this.printItem4.hemoglobin : '')

        str = str.replace(/{{outActivities1}}/, this.printItem1.outActivities ? this.printItem1.outActivities : '')
        str = str.replace(/{{outActivities2}}/, this.printItem2.outActivities ? this.printItem2.outActivities : '')
        str = str.replace(/{{outActivities3}}/, this.printItem3.outActivities ? this.printItem3.outActivities : '')
        str = str.replace(/{{outActivities4}}/, this.printItem4.outActivities ? this.printItem4.outActivities : '')

        str = str.replace(/{{vitaminD1}}/, this.printItem1.vitaminD ? this.printItem1.vitaminD : '')
        str = str.replace(/{{vitaminD2}}/, this.printItem2.vitaminD ? this.printItem2.vitaminD : '')
        str = str.replace(/{{vitaminD3}}/, this.printItem3.vitaminD ? this.printItem3.vitaminD : '')
        str = str.replace(/{{vitaminD4}}/, this.printItem4.vitaminD ? this.printItem4.vitaminD : '')

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

        str = str.replace(/{{medicine1}}/, this.printItem1.medicine ? this.printItem1.medicine : '')
        str = str.replace(/{{medicine2}}/, this.printItem2.medicine ? this.printItem2.medicine : '')
        str = str.replace(/{{medicine3}}/, this.printItem3.medicine ? this.printItem3.medicine : '')
        str = str.replace(/{{medicine4}}/, this.printItem4.medicine ? this.printItem4.medicine : '')

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
                this.$message.error('未建立个人档案或居民不是儿童！')
              }
            })
          } else {
            this.$message.error('必要信息还未填写完整！')
          }
        })
      },
      conprint() {
        this.printDatas()
        const windows = this.printf(document.getElementById('print').innerHTML)
        windows.close()
      },
      handleCurrentChange(val) {
        this.page = val
        this.getDatas()
      },
      printDatas() {
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
                  this.editForm.doctor = user[0].creator
                }
              })
            })
          }, 200)
        }
      },
      // 获取用户列表
      getDatas() {
        this.filters.startTime =
          !this.filters.startTime || this.filters.startTime === ''
            ? null
            : util.formatDate.format(new Date(this.filters.startTime), 'yyyy-MM-dd')

        this.filters.endTime =
          !this.filters.endTime || this.filters.endTime === ''
            ? null
            : util.formatDate.format(new Date(this.filters.endTime), 'yyyy-MM-dd')
        const para = {
          page: this.page,
          size: 20,
          filters: { name: this.filters.name, startTime: this.filters.startTime, endTime: this.filters.endTime, area: this.filters.area, peopleId: this.filters.peopleId }
        }

        getList(para).then(res => {
          this.total = res.totalCount
          this.users = res.detailModelList
          if (this.page > 1 && (this.page - 1) * 20 >= this.total) {
            this.page = this.page - 1
            this.getDatas()
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
              this.getDatas()
            })
          })
          .catch(() => {})
      },
      search() {
        this.page = 1
        this.getDatas()
      },
      // 显示编辑界面
      handleEdit(index, row) {
        this.dialogStatus = 'update'
        this.dialogFormVisible = true
        this.editForm = Object.assign({}, row)
        this.editForm.guidance = this.editForm.guidance.split(',')
        this.editForm.development = this.editForm.development.split(',')
        this.editForm.medicine = this.editForm.medicine.split(',')
        this.edit = true
      },
      handleRowChange(row, event) {
        this.dialogStatus = 'detail'
        this.dialogFormVisible = true
        this.editForm = Object.assign({}, row)
        this.editForm.guidance = this.editForm.guidance.split(',')
        this.editForm.development = this.editForm.development.split(',')
        this.editForm.medicine = this.editForm.medicine.split(',')
        this.edit = false
      },
      // 显示新增界面
      handleAdd() {
        this.dialogStatus = 'create'
        this.dialogFormVisible = true
        this.editForm = {
          id: null,
          name: '',
          hemoglobin: 0,
          outActivities: 0,
          vitaminD: 0,
          guidance: [],
          development: [],
          followDate: Date.now(),
          medicine: [],
          creator: this.$store.getters.name,
          createDate: null,
          modifier: '',
          modifyDate: null
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

                para.guidance = ''
                for (const itm of this.editForm.guidance) {
                  if (itm !== '') {
                    para.guidance += itm
                    para.guidance += ','
                  }
                }
                para.guidance = para.guidance.substr(0, para.guidance.length - 1)

                para.development = ''
                for (const itm of this.editForm.development) {
                  if (itm !== '') {
                    para.development += itm
                    para.development += ','
                  }
                }
                para.development = para.development.substr(0, para.development.length - 1)

                para.medicine = ''
                for (const itm of this.editForm.medicine) {
                  if (itm !== '') {
                    para.medicine += itm
                    para.medicine += ','
                  }
                }
                para.medicine = para.medicine.substr(0, para.medicine.length - 1)

                para.modifier = this.$store.getters.name

                editData(para).then(res => {
                  this.$message({
                    message: '提交成功',
                    type: 'success'
                  })
                  this.$refs['editForm'].resetFields()
                  this.dialogFormVisible = false
                  this.$emit('watchChild')
                  this.getDatas()
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

                para.guidance = ''
                for (const itm of this.editForm.guidance) {
                  if (itm !== '') {
                    para.guidance += itm
                    para.guidance += ','
                  }
                }
                para.guidance = para.guidance.substr(0, para.guidance.length - 1)

                para.development = ''
                for (const itm of this.editForm.development) {
                  if (itm !== '') {
                    para.development += itm
                    para.development += ','
                  }
                }
                para.development = para.development.substr(0, para.development.length - 1)

                para.medicine = ''
                for (const itm of this.editForm.medicine) {
                  if (itm !== '') {
                    para.medicine += itm
                    para.medicine += ','
                  }
                }
                para.medicine = para.medicine.substr(0, para.medicine.length - 1)

                para.creater = this.$store.getters.name

                addData(para).then(res => {
                  this.$message({
                    message: '提交成功',
                    type: 'success'
                  })
                  this.$refs['editForm'].resetFields()
                  this.dialogFormVisible = false
                  this.$emit('watchChild')
                  this.getDatas()
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
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        })
          .then(() => {
            const para = { ids: ids }
            batchRemoveData(para).then(res => {
              this.$message({
                message: '删除成功',
                type: 'success'
              })
              this.getDatas()
            })
          })
          .catch(() => {})
      }
    },
    mounted() {
      this.buttons = this.getButton(this.buttons, this.$store.getters.buttons)
      this.getDatas()
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
  .pad5{
    padding-left: 5px;
    padding-right: 5px;
  }
  span{
    color:red;
  }

</style>
