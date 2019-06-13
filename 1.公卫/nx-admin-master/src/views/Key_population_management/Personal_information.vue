<template>

  <section class="app-container">
    <!--工具条-->
    <el-col :span="24" class="toolbar" style="padding-bottom: 0px;">
      <el-form :inline="true" :model="filters" @submit.native.prevent>
        <el-form-item label-width="160px">
          <el-input v-model="filters.name" placeholder="姓名"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" v-on:click="searchs" style="background-color:#4db2ee;border-color:transparent" icon="el-icon-search" >查询</el-button>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" v-on:click="findBtn"  style="background-color:#4db2ee;border-color:transparent;width: 120px" icon="el-icon-search">高级查询</el-button>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="handleAdd" icon="el-icon-circle-plus-outline">新增</el-button>
        </el-form-item>
      </el-form>
    </el-col>

    <!--查询-->
    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible2" :close-on-click-modal="false" center>
      <el-form :model="editForm" label-width="80px" :rules="editFormRules" ref="editForm">
        <el-row>
          <el-col span="12">
            <el-form-item label="身份证号" label-width="160px">
              <el-input v-model="filters.peopleId" placeholder="身份证号"></el-input>
            </el-form-item>
          </el-col>
          <el-col span="12">
            <el-form-item label="姓名" label-width="160px">
              <el-input v-model="filters.name" placeholder="姓名"></el-input>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col span="12">
            <el-form-item label="监护人" label-width="160px">
              <el-input v-model="filters.guardianName" placeholder="监护人"></el-input>
            </el-form-item>
          </el-col>
          <el-col span="12">
            <el-form-item label="辖区村委会联系人" label-width="160px">
              <el-input v-model="filters.commitPersonName" placeholder="辖区村委会联系人"></el-input>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col span="12">
            <el-form-item label="户别" label-width="160px">
              <el-input v-model="filters.accountType" placeholder="户别"></el-input>
            </el-form-item>
          </el-col>
        </el-row>


        <!--<el-row>-->
          <!--<el-col span="12">-->
            <!--<el-form-item label="初次发病时间" label-width="160px">-->
              <!--<el-date-picker type="date" v-model="filters.firstOnsetTime" style="width: 100%;"></el-date-picker>-->
            <!--</el-form-item>-->
          <!--</el-col>-->
          <!--<el-col span="12">-->
            <!--<el-form-item label="首次抗精神病治疗时间" label-width="160px">-->
              <!--<el-date-picker type="date" placeholder="首次抗精神病治疗时间" v-model="filters.firstAntipsyTreat" style="width: 100%;"></el-date-picker>-->
            <!--</el-form-item>-->
          <!--</el-col>-->
        <!--</el-row>-->


        <el-row>
          <el-col :span="12">
            <el-form-item label="初次发病时间范围查询"  label-width="160px">
              <el-date-picker type="date" placeholder="初次发病开始时间" v-model="filters.firstOnsetTime_s" style="width: 100%;"></el-date-picker>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="——————————"  label-width="160px">
              <el-date-picker type="date" placeholder="初次发病结束时间" v-model="filters.firstOnsetTime_e" style="width: 100%;"></el-date-picker>
            </el-form-item>
          </el-col>
        </el-row>



      </el-form>
      <div slot="footer" class="dialog-footer" align="center">
        <el-button type="primary" v-on:click="searchs" style="background-color:#4db2ee;border-color:transparent" icon="el-icon-search" >查询</el-button>
      </div>
    </el-dialog>

    <!--列表-->
    <el-table :data="users" :height="tableHeight" highlight-current-row @selection-change="selsChange" border style="width: 100%;margin-top:-20px;" @row-dblclick="handleRowChange">
      <!--<el-table-column type="selection"  width="55">-->
      <!--</el-table-column>-->
      <el-table-column type="index" label="序号" width="50" >
      </el-table-column>
      <el-table-column prop="peopleId" label="身份证号" width="250">
      </el-table-column>
      <el-table-column prop="name" label="姓名" width="120">
      </el-table-column>
      <el-table-column prop="guardianName" label="监护人" width="120" >
      </el-table-column>
      <el-table-column prop="relaPatients" label="与患者关系" width="100" v-if="false">
      </el-table-column>
      <el-table-column prop="guardianAddre" label="监护人住址" width="150" v-if="false">
      </el-table-column>
      <el-table-column prop="guardPhone" label="监护人电话" width="150" v-if="false">
      </el-table-column>
      <el-table-column prop="commitPersonName" label="辖区村(居)委会联系人" width="160" v-if="false">
      </el-table-column>
      <el-table-column prop="commitPersonNum" label="辖区村(居)委会联系人电话" width="200" v-if="false">
      </el-table-column>
      <el-table-column prop="accountType" label="户别" width="100" v-if="false">
      </el-table-column>
      <el-table-column prop="employSituat" label="就业情况" width="100" v-if="false">
      </el-table-column>
      <el-table-column prop="infConsent" label="知情同意" width="100"  v-if="false">
      </el-table-column>
      <el-table-column prop="firstOnsetTime" label="初次发病时间" width="150" v-if="false">
      </el-table-column>
      <el-table-column prop="pastSymptoms" label="既往主要症状" width="150" v-if="false">
      </el-table-column>
      <el-table-column prop="pastLocking" label="既往关锁情况" width="150" v-if="false">
      </el-table-column>
      <el-table-column prop="outService" label="门诊" width="100" v-if="false">
      </el-table-column>
      <el-table-column prop="firstAntipsyTreat" label="首次抗精神药治疗时间" width="200" v-if="false">
      </el-table-column>
      <el-table-column prop="inHospital" label="住院" width="100" v-if="false">
      </el-table-column>
      <el-table-column prop="diagnosis" label="诊断" width="100"  v-if="false">
      </el-table-column>
      <el-table-column prop="hospitalDia" label="确诊医院" min-width="100"v-if="false">
      </el-table-column>
      <el-table-column prop="confiDate" label="确诊日期" width="100" v-if="false">
      </el-table-column>
      <el-table-column prop="lastTreatEff" label="最近一次治疗效果" width="150" v-if="false">
      </el-table-column>
      <el-table-column prop="dangerBehav" label="危险行为" width="100" v-if="false">
      </el-table-column>
      <el-table-column prop="stateEconomy" label="经济状况" width="100" v-if="false">
      </el-table-column>
      <el-table-column prop="speciaOpinion" label="专科医生的建议" width="150" v-if="false">
      </el-table-column>
      <el-table-column prop="dateFill" label="填表日期" width="150"v-if="false" >
      </el-table-column>
      <el-table-column prop="docSign" label="医生签字" width="100" v-if="false">
      </el-table-column>
      <el-table-column prop="creator" label="创建人" width="120" align="center" >
      </el-table-column>
      <el-table-column prop="createDate" label="创建时间" width="120" :formatter="timeFormat" align="center"   >
      </el-table-column>
      <el-table-column prop="modifier" label="修改人" width="120" align="center" >
      </el-table-column>
      <el-table-column prop="modifyDate" label="修改时间" width="120" align="center" :formatter="timeFormat">
      </el-table-column>
      <el-table-column prop="area" label="地区" min-width="240" align="center" v-if="false" >
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
      <!--<el-button type="danger" @click="batchRemove" :disabled="this.sels.length===0">批量删除</el-button>-->
      <el-pagination layout="prev, pager, next" :current-page.sync="page" @current-change="handleCurrentChange" :page-size="20" :total="total" style="float:right;">
      </el-pagination>
    </el-col>

    <!--编辑界面-->
    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible" :close-on-click-modal="false" center>
      <el-form :model="editForm" label-width="160px" :rules="editFormRules" ref="editForm">
          <div id="print">
              <el-row>
                <el-col span="12">
                  <el-form-item label="身份证号" prop="peopleId">
                    <el-input v-model="editForm.peopleId" ></el-input>
                  </el-form-item>
                </el-col>
                <el-col span="12">
                  <el-form-item label="姓名"  prop="name">
                    <el-input v-model="editForm.name" auto-complete="off"></el-input>
                  </el-form-item>
                </el-col>
              </el-row>

                <el-row>
                  <el-col :span="12">
                    <el-form-item label="监护人姓名"  prop="guardianName">
                      <el-input v-model="editForm.guardianName"></el-input>
                    </el-form-item>
                  </el-col>
                  <el-col :span="12">
                    <el-form-item label="与患者关系" >
                      <el-input v-model="editForm.relaPatients"></el-input>
                    </el-form-item>
                  </el-col>
                </el-row>

              <el-row>
                <el-col :span="12">
                  <el-form-item label="监护人住址" >
                    <el-input v-model="editForm.guardianAddre"></el-input>
                  </el-form-item>
                </el-col>
                <el-col :span="12">
                  <el-form-item label="监护人电话" >
                    <el-input v-model="editForm.guardPhone"></el-input>
                  </el-form-item>
                </el-col>
              </el-row>

              <el-row>
                <el-col :span="12">
                  <el-form-item label="辖区村(居)委会联系人"  >
                    <el-input v-model="editForm.commitPersonName"></el-input>
                  </el-form-item>
                </el-col>
                <el-col :span="12">
                  <el-form-item label="村(居)委会联系人电话">
                    <el-input v-model="editForm.commitPersonNum"></el-input>
                  </el-form-item>
                </el-col>
              </el-row>

              <el-row>
                <el-col :span="12">
                  <el-form-item label="户别" >
                    <el-radio-group v-model="editForm.accountType">
                      <el-radio label="1城镇"></el-radio>
                      <el-radio label="2农村"></el-radio>
                    </el-radio-group>
                  </el-form-item>
                </el-col>
                <el-col :span="12">
                  <el-form-item label="就业情况" >
                    <el-select v-model="editForm.employSituat" placeholder="请选择" style="width: 100%">
                      <el-option key="1" label="1在岗工人" value="在岗工人"></el-option>
                      <el-option key="2" label="2在岗管理者" value="在岗管理者"></el-option>
                      <el-option key="3" label="3农民" value="农民"></el-option>
                      <el-option key="4" label="4下岗或无业" value="下岗或无业"></el-option>
                      <el-option key="5" label="5在校学生" value="在校学生"></el-option>
                      <el-option key="6" label="6退休" value="退休"></el-option>
                      <el-option key="7" label="7专业技术人员" value="专业技术人员"></el-option>
                      <el-option key="8" label="8其他" value="其他"></el-option>
                      <el-option key="9" label="9不详" value="不详"></el-option>
                    </el-select>
                  </el-form-item>
                </el-col>
              </el-row>

              <el-row>
                <el-col :span="12">
                  <el-form-item label="知情同意" >
                    <el-radio-group v-model="editForm.infConsent">
                      <el-radio label="1同意参加管理"></el-radio>
                      <el-radio label="2不同意参加管理"></el-radio>
                    </el-radio-group>
                  </el-form-item>
                </el-col>
                <el-col :span="12">
                  <el-form-item label="签字人" >
                    <el-input v-model="editForm.signatory" auto-complete="off"></el-input>
                  </el-form-item>
                </el-col>
              </el-row>

              <el-row>
                <el-col :span="12">
                  <el-form-item label="签字时间">
                    <!--<el-date-picker v-model="editForm.firstOnsetTime" type="datetime" placeholder="选择日期时间" style="width: 100%"> </el-date-picker>-->
                    <el-date-picker type="date" placeholder="选择日期" v-model="editForm.signatureDate" style="width: 100%;"></el-date-picker>
                  </el-form-item>
                </el-col>
                <el-col :span="12">
                  <el-form-item label="初次发病时间">
                    <!--<el-date-picker v-model="editForm.firstOnsetTime" type="datetime" placeholder="选择日期时间" style="width: 100%"> </el-date-picker>-->
                    <el-date-picker type="date" placeholder="选择日期" v-model="editForm.firstOnsetTime" style="width: 100%;"></el-date-picker>
                  </el-form-item>
                </el-col>
              </el-row>


            <el-row>
              <el-col span="24">
                <el-form-item label="既往主要症状" >
                  <el-checkbox-group v-model="editForm.pastSymptoms" style=" width: 100%;">
  <!--                  <el-checkbox v-for="item in pastSymptoms" :key="item.value" :name="type" :label="item.label" :value="item.value"></el-checkbox>-->
                    <table>
                      <tr>
                        <td style="padding-right: 35px"><el-checkbox label="幻觉" name="type"></el-checkbox></td>
                        <td style="padding-right: 35px"><el-checkbox label="交流困难" name="type"></el-checkbox></td>
                        <td style="padding-right: 35px"><el-checkbox label="猜疑" name="type"></el-checkbox></td>
                        <td style="padding-right: 35px"><el-checkbox label="喜怒无常" name="type"></el-checkbox></td>
                        <td style="padding-right: 35px"><el-checkbox label="行为怪异" name="type"></el-checkbox></td>
                        <td style="padding-right: 35px"><el-checkbox label="兴奋话多" name="type"></el-checkbox></td>
                      </tr>
                      <tr>
                        <td style="padding-right: 35px"><el-checkbox label="伤人伤物" name="type"></el-checkbox></td>
                        <td style="padding-right: 35px"><el-checkbox label="悲观厌世" name="type"></el-checkbox></td>
                        <td style="padding-right: 35px"><el-checkbox label="无故外走" name="type"></el-checkbox></td>
                        <td style="padding-right: 35px"><el-checkbox label="自语自笑" name="type"></el-checkbox></td>
                        <td style="padding-right: 35px"><el-checkbox label="孤僻懒散" name="type"></el-checkbox></td>
                        <td style="padding-right: 35px"><el-checkbox label="其他" name="type"></el-checkbox></td>
                      </tr>
                    </table>
                  </el-checkbox-group>
                </el-form-item>
              </el-col>
            </el-row>

            <el-row>
              <el-col>
                <el-form-item label="既往关锁情况" >
                  <el-radio-group v-model="editForm.pastLocking">
                    <el-radio label="1无关锁"></el-radio>
                    <el-radio label="2关锁"></el-radio>
                    <el-radio label="3关锁已解除"></el-radio>
                  </el-radio-group>
                </el-form-item>
              </el-col>
            </el-row>

            <el-row>
              <el-col span="12">
                <el-form-item label="门诊">
                  <el-select v-model="editForm.outService" placeholder="请选择" style="width:100%">
                    <el-option key="1" label="1未治疗" value="1未治疗"></el-option>
                    <el-option key="2" label="2间断门诊治疗" value="2间断门诊治疗"></el-option>
                    <el-option key="3" label="3连续门诊治疗" value="3连续门诊治疗"></el-option>
                  </el-select>
                </el-form-item>
              </el-col>
              <el-col span="12">
                <el-form-item label="首次抗精神药治疗时间" label-width="170px">
                  <!--<el-date-picker v-model="editForm.firstAntipsyTreat" type="datetime" placeholder="选择日期时间" style="width: 100%"> </el-date-picker>-->
                  <el-date-picker type="date" placeholder="选择日期" v-model="editForm.firstAntipsyTreat" style="width: 100%;"></el-date-picker>
                </el-form-item>
              </el-col>
            </el-row>

            <el-row>
              <el-col span="12">
                <el-form-item label="曾住精神医院次数："  >
                  <el-input v-model="editForm.inHospital">
                    <template slot="append">次</template>
                  </el-input>
                </el-form-item>
              </el-col>
            </el-row>

            <el-row>
              <el-form-item label="目前整段情况诊断" >
              <el-col span="8">
                  <el-input v-model="editForm.diagnosis" placeholder="诊断" ></el-input>
              </el-col>
              <el-col span="8">
                  <el-input v-model="editForm.hospitalDia"  placeholder="确诊医院"></el-input>
              </el-col>
              <el-col span="8">
                  <el-date-picker type="date" placeholder="确诊日期" v-model="editForm.confiDate" style="width: 100%;"></el-date-picker>
              </el-col>
              </el-form-item>
            </el-row>

            <el-row>
              <el-col>
                <el-form-item label="最近一次治疗效果" >
                  <el-radio-group v-model="editForm.lastTreatEff">
                    <el-radio label="1临床痊愈"></el-radio>
                    <el-radio label="2好转"></el-radio>
                    <el-radio label="3无变化"></el-radio>
                    <el-radio label="4加重"></el-radio>
                  </el-radio-group>
                </el-form-item>
              </el-col>
            </el-row>

            <el-row>
              <el-col>
                <el-form-item label="危险行为">
                  <el-checkbox-group v-model="editForm.dangerBehav" style="width:100%">
                    <el-checkbox v-for="item in dangerBehav" :key="item.value" :name="type" :label="item.label" :value="item.value"></el-checkbox>
                  </el-checkbox-group>
                </el-form-item>
              </el-col>
            </el-row>

            <el-row>
              <el-col>
                <el-form-item label="经济状况" >
                  <el-radio-group v-model="editForm.stateEconomy">
                    <el-radio label="贫困，在当地平困线标准以下"></el-radio>
                    <el-radio label="非贫困"></el-radio>
                  </el-radio-group>
                </el-form-item>
              </el-col>
            </el-row>

            <el-row>
              <el-col>
                <el-form-item label="专科医生的建议(如果有请记录)" label-width="220px">
                  <el-input type="textarea" rows="5" v-model="editForm.speciaOpinion"></el-input>
                </el-form-item>
              </el-col>
            </el-row>


            <el-row>
              <el-col :span="12">
                <el-form-item label="填表日期">
                  <el-date-picker type="date" placeholder="选择日期" v-model="editForm.dateFill" style="width: 100%;"></el-date-picker>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="医生签字">
                  <el-input v-model="editForm.docSign"></el-input>
                </el-form-item>
              </el-col>
            </el-row>

            <el-row>
              <el-col :span="12">
                <el-form-item label="地区">
                  <el-cascader :options="options" v-model="area" style="width: 100%" placeholder="请选择"></el-cascader>
                </el-form-item>
              </el-col>
            </el-row>


        </div>

      </el-form>
      <div slot="footer" class="dialog-footer" align="center">
        <el-button @click="conPrint()">打印</el-button>
        <el-button v-if="dialogStatus=='create'" type="primary" @click="createData">添加</el-button>
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
    getUserListPage,
    removeUser,
    batchRemoveUser,
    editUser,
    addUser
  } from '@/api/SevereMentalSupplement'
  import city from '@/api/city.js'
  export default {
    data() {
      return {
        edit: true,
        activeName: 'first',
        tableHeight: window.innerHeight - 220,
        options: city.city(),
        dangerBehav: [
          { value: '轻度滋事', label: '轻度滋事' },
          { value: '肇事', label: '肇事' },
          { value: '肇祸', label: '肇祸' },
          { value: '其他危险行为', label: '其他危险行为' },
          { value: '自杀', label: '自杀' },
          { value: '自杀未遂', label: '自杀未遂' },
          { value: '无', label: '无' }
        ],
        pastSymptoms: [
          { value: '幻觉', label: '幻觉' },
          { value: '交流困难', label: '交流困难' },
          { value: '猜疑', label: '猜疑' },
          { value: '喜怒无常', label: '喜怒无常' },
          { value: '行为怪异', label: '行为怪异' },
          { value: '兴奋话多', label: '兴奋话多' },
          { value: '伤人伤物', label: '伤人伤物' },
          { value: '悲观厌世', label: '悲观厌世' },
          { value: '无故外走', label: '无故外走' },
          { value: '自语自笑', label: '自语自笑' },
          { value: '孤僻懒散', label: '孤僻懒散' },
          { value: '其他', label: '其他' }
        ],
        area: [],
        dialogStatus: '',
        textMap: {
          update: '编辑',
          create: '添加',
          search: '查询',
          detail: '详细'
        },
        dialogFormVisible: false,
        dialogFormVisible2: false,
        filters: {
          peopleId: '',
          name: '',
          guardianName: '',
          guardianAddre: '',
          guardPhone: '',
          commitPersonName: '',
          commitPersonNum: '',
          accountType: '',
          outService: '',
          firstOnsetTime: '',
          firstAntipsyTreat: '',
          firstOnsetTime_s: '',
          firstOnsetTime_e: ''
        },

        users: [],
        total: 0,
        page: 1,
        sels: [], // 列表选中列
        editFormRules: {
          name: [{ required: true, message: '请输入姓名', trigger: 'blur' }],
          peopleId: [
            { required: true, message: '请输入身份证ID', trigger: 'blur' },
            { pattern: /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/, message: '你的身份证格式不正确' }
          ]
        },
        // 编辑界面数据
        editForm: {
          peopleId: '',
          name: '',
          area: '',
          guardianName: '',
          relaPatients: '',
          guardianAddre: '',
          guardPhone: '',
          commitPersonName: '',
          commitPersonNum: '',
          accountType: '',
          employSituat: '',
          infConsent: '',
          signatory: '',
          signatureDate: '',
          firstOnsetTime: '',
          pastSymptoms: [],
          pastLocking: '',
          outService: '',
          firstAntipsyTreat: '',
          inHospital: '',
          diagnosis: '',
          hospitalDia: '',
          confiDate: '',
          lastTreatEff: '',
          dangerBehav: [],
          stateEconomy: '',
          speciaOpinion: '',
          dateFill: '',
          docSign: '',
          creator: '',
          createDate: '',
          modifier: '',
          modifyDate: ''
        },

        addFormVisible: false, // 新增界面是否显示
        addFormRules: {
          name: [{ required: true, message: '请输入姓名', trigger: 'blur' }]
        }
      }
    },
    methods: {
      // 跳转
      goto(index, row) {
        this.$router.push({
          name: 'dashboard',
          params: {
            hid: row.peopleId
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
          '    <h2 style="width:794px;text-align:center"> 严重精神障碍患者个人信息补充</h2>  \n' +
          '    <span style="margin-left:34px;">姓名:{{name}}</span>\n' +
          '    <span style="margin-left:510px;">NO:{{id}}</span> \n' +
          '    </div>\n' +
          '  <table id="table"  border="1"  cellspacing="0" bordercolor="#000000" style="width:730px;margin-left:30px;margin-top:5px;border-collapse:collapse;"  width = "80%">\n' +
          '    <tbody>\n' +
          '      <tr>\n' +
          '        <td style="width: 20%;text-align:center">监护人姓名</td>\n' +
          '        <td colspan=\'2\'>{{guardianName}}</td>\n' +
          '        <td style="width: 20%;text-align:center">与患者关系</td>\n' +
          '        <td colspan=\'2\'>{{relaPatients}}</td>\n' +
          '      </tr>\n' +
          '\t  <tr>\n' +
          '        <td style="width: 20%;text-align:center">监护人地址</td>\n' +
          '        <td colspan=\'2\'>{{guardianAddre}}</td>\n' +
          '        <td style="width: 20%;text-align:center">监护人电话</td>\n' +
          '        <td colspan=\'2\'>{{guardPhone}}</td>\n' +
          '      </tr>\n' +
          '\t  <tr>\n' +
          '        <td style="width: 20%;text-align:center">辖区村(居)委会联系人</td>\n' +
          '        <td colspan=\'2\'>{{commitPersonName}}</td>\n' +
          '        <td style="width: 20%;text-align:center">辖区村(居)委会联系人电话</td>\n' +
          '        <td colspan=\'2\'>{{commitPersonNum}}</td>\n' +
          '      </tr>\n' +
          '\t  <tr>\n' +
          '        <td style="word-wrap:break-word;width: 20%;text-align:center">户别</td>\n' +
          '        <td colspan=\'5\'>{{accountType}}</td>\n' +
          '      </tr>\n' +
          '\t  <tr>\n' +
          '        <td style="word-wrap:break-word;width: 20%;text-align:center">就业情况</td>\n' +
          '        <td colspan=\'5\'>{{employSituat}}</td>\n' +
          '      </tr>\n' +
          '\t  <tr >\n' +
          '\t\t<td style="word-wrap:break-word;width: 20%;text-align:center" rowspan=3>知情同意</td>\n' +
          '\t\t<td colspan=\'5\'>{{infConsent}}</td>\n' +
          '\t  </tr>\n' +
          '\t  <tr >\n' +
          '\t\t<td colspan=\'5\'>签字</td>\n' +
          '\t  </tr>\n' +
          '\t  \n' +
          '\t  <tr >\n' +
          '\t\t<td colspan=\'5\'>签字时间：______年______月_____日</td>\n' +
          '\t  </tr>\n' +
          '\t  <tr>\n' +
          '        <td style="word-wrap:break-word;width: 20%;text-align:center">初次发病时间</td>\n' +
          '        <td colspan=\'5\'>{{firstOnsetTime}}</td>\n' +
          '      </tr>\n' +
          '\t  <tr>\n' +
          '        <td style="width: 20%;text-align:center">既往主要症状</td>\n' +
          '        <td colspan=\'5\' style="width: 20%;text-align:left">{{pastSymptoms}}</td>\n' +
          '      </tr>\n' +
          '\t  <tr>\n' +
          '        <td style="word-wrap:break-word;width: 20%;text-align:center">既往关锁情况</td>\n' +
          '        <td colspan=\'5\'>{{pastLocking}}</td>\n' +
          '      </tr>\n' +
          '\t</tbody>\n' +
          '\t<tbody>  \n' +
          '\t  <tr >\n' +
          '\t\t<td style="word-wrap:break-word;width: 20%;text-align:center" rowspan=3>既往治疗情况</td>\n' +
          '\t\t<td rowspan=2 style="word-wrap:break-word;width: 20%;text-align:center">门诊</td>\n' +
          '\t\t<td colspan=\'5\'>{{outService}}</td>\n' +
          '\t  </tr>\n' +
          '\t  <tr>\n' +
          '\t\t<td colspan=\'5\' >首次抗精神药治疗时间:{{firstAntipsyTreat}}</td>\n' +
          '\t  </tr>\n' +
          '\t  <tr>\n' +
          '\t    <td style="word-wrap:break-word;width: 20%;text-align:center">住院</td>\n' +
          '\t\t<td colspan=\'5\'>曾住精神专科医院/综合医院精神专科次数：{{inHospital}}</td>\n' +
          '\t  </tr>\n' +
          '\t</tbody> \n' +
          '\t<tbody> \n' +
          '\t  <tr>\n' +
          '\t    <td style="word-wrap:break-word;width: 20%;text-align:center">目前诊断情况</td>\n' +
          '\t\t<td colspan=\'5\'>诊断:{{diagnosis}}     确诊医院:{{hospitalDia}}     确诊日期:{{confiDate}}</td>\n' +
          '\t  </tr>\n' +
          '\t  <tr>\n' +
          '        <td style="word-wrap:break-word;width: 20%;text-align:center">最近一次治疗效果</td>\n' +
          '        <td colspan=\'5\'>{{lastTreatEff}}</td>\n' +
          '      </tr>\n' +
          '\t  <tr>\n' +
          '        <td style="width: 20%;text-align:center">危险行为</td>\n' +
          '        <td colspan=\'5\' style="width: 20%;text-align:left"> {{dangerBehav}}</tr>\n' +
          '\t   <tr>\n' +
          '        <td style="word-wrap:break-word;width: 20%;text-align:center">经济状况</td>\n' +
          '        <td colspan=\'5\'>{{stateEconomy}}</td>\n' +
          '      </tr>\n' +
          '\t  <tr>\n' +
          '        <td style="word-wrap:break-word;width: 20%;text-align:center">专科医生的建议(如果有请记录)</td>\n' +
          '        <td colspan=\'4\'>{{speciaOpinion}}</td>\n' +
          '      </tr>\n' +
          '\t  <tr>\n' +
          '        <td style="width: 10%;text-align:center">填表日期</td>\n' +
          '        <td colspan=\'2\'>{{dateFill}}</td>\n' +
          '        <td style="width: 10%;text-align:center">医生签字</td>\n' +
          '        <td colspan=\'2\'>{{docSign}}</td>\n' +
          '      </tr>\n' +
          ' \n' +
          '    </tbody> \n' +
          '    \n' +
          '     \n' +
          '\n' +
          '  </table>\n' +
          '</div>'
        str = str.replace(/{{name}}/, this.editForm.name ? this.editForm.name : '')
        str = str.replace(/{{id}}/, this.editForm.id ? this.editForm.id : '')
        str = str.replace(/{{guardianName}}/, this.editForm.guardianName ? this.editForm.guardianName : '')
        str = str.replace(/{{relaPatients}}/, this.editForm.relaPatients ? this.editForm.relaPatients : '')
        str = str.replace(/{{guardianAddre}}/, this.editForm.guardianAddre ? this.editForm.guardianAddre : '')
        str = str.replace(/{{guardPhone}}/, this.editForm.guardPhone ? this.editForm.guardPhone : '')
        str = str.replace(/{{commitPersonName}}/, this.editForm.commitPersonName ? this.editForm.commitPersonName : '')
        str = str.replace(/{{commitPersonNum}}/, this.editForm.commitPersonNum ? this.editForm.commitPersonNum : '')
        str = str.replace(/{{accountType}}/, this.editForm.accountType ? this.editForm.accountType : '')
        str = str.replace(/{{employSituat}}/, this.editForm.employSituat ? this.editForm.employSituat : '')
        str = str.replace(/{{infConsent}}/, this.editForm.infConsent ? this.editForm.infConsent : '')
        str = str.replace(/{{firstOnsetTime}}/, this.editForm.firstOnsetTime ? this.editForm.firstOnsetTime : '')
        str = str.replace(/{{pastSymptoms}}/, this.editForm.pastSymptoms ? this.editForm.pastSymptoms : '')
        str = str.replace(/{{pastLocking}}/, this.editForm.pastLocking ? this.editForm.pastLocking : '')
        str = str.replace(/{{outService}}/, this.editForm.outService ? this.editForm.outService : '')
        str = str.replace(/{{firstAntipsyTreat}}/, this.editForm.firstAntipsyTreat ? this.editForm.firstAntipsyTreat : '')
        str = str.replace(/{{inHospital}}/, this.editForm.inHospital ? this.editForm.inHospital : '')
        str = str.replace(/{{diagnosis}}/, this.editForm.diagnosis ? this.editForm.diagnosis : '')
        str = str.replace(/{{hospitalDia}}/, this.editForm.hospitalDia ? this.editForm.hospitalDia : '')
        str = str.replace(/{{confiDate}}/, this.editForm.confiDate ? this.editForm.confiDate : '')
        str = str.replace(/{{lastTreatEff}}/, this.editForm.lastTreatEff ? this.editForm.lastTreatEff : '')
        str = str.replace(/{{dangerBehav}}/, this.editForm.dangerBehav ? this.editForm.dangerBehav : '')
        str = str.replace(/{{stateEconomy}}/, this.editForm.stateEconomy ? this.editForm.stateEconomy : '')
        str = str.replace(/{{speciaOpinion}}/, this.editForm.speciaOpinion ? this.editForm.speciaOpinion : '')
        str = str.replace(/{{dateFill}}/, this.editForm.dateFill ? this.editForm.dateFill : '')
        str = str.replace(/{{docSign}}/, this.editForm.inHospital ? this.editForm.inHospital : '')
        myWindow.document.write(str)
        // myWindow.document.write(content + style)
        myWindow.focus()
        myWindow.document.close()
        myWindow.print()
        return myWindow
      },
      conPrint() {
        const windows = this.printf(document.getElementById('print').innerHTML)
        windows.close()
      },

      handleCurrentChange(val) {
        this.page = val
        this.getUsers()
      },
      // 获取用户列表
      getUsers() {
        this.filters.firstOnsetTime =
          !this.filters.firstOnsetTime || this.filters.firstOnsetTime === ''
            ? null
            : util.formatDate.format(new Date(this.filters.firstOnsetTime), 'yyyy-MM-dd')
        this.filters.firstAntipsyTreat =
          !this.filters.firstAntipsyTreat || this.filters.firstAntipsyTreat === ''
            ? null
            : util.formatDate.format(new Date(this.filters.firstAntipsyTreat), 'yyyy-MM-dd')
        this.filters.firstOnsetTime_s =
          !this.filters.firstOnsetTime_s || this.filters.firstOnsetTime_s === ''
            ? null
            : util.formatDate.format(new Date(this.filters.firstOnsetTime_s), 'yyyy-MM-dd')
        this.filters.firstOnsetTime_e =
          !this.filters.firstOnsetTime_e || this.filters.firstOnsetTime_e === ''
            ? null
            : util.formatDate.format(new Date(this.filters.firstOnsetTime_e), 'yyyy-MM-dd')

        const para = {
          page: this.page,
          size: 20,
          filters: {
            peopleId: this.filters.peopleId,
            name: this.filters.name,
            guardianName: this.filters.guardianName,
            guardianAddre: this.filters.guardianAddre,
            guardPhone: this.filters.guardPhone,
            commitPersonName: this.filters.commitPersonName,
            commitPersonNum: this.filters.commitPersonNum,
            accountType: this.filters.accountType,
            outService: this.filters.outService,
            firstOnsetTime: this.filters.firstOnsetTime,
            firstAntipsyTreat: this.filters.firstAntipsyTreat,
            firstOnsetTime_s: this.filters.firstOnsetTime_s,
            firstOnsetTime_e: this.filters.firstOnsetTime_e
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
          peopleId: '',
          name: '',
          guardianName: '',
          guardianAddre: '',
          guardPhone: '',
          commitPersonName: '',
          commitPersonNum: '',
          accountType: '',
          outService: '',
          firstOnsetTime: '',
          firstAntipsyTreat: '',
          firstOnsetTime_s: '',
          firstOnsetTime_e: ''
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
      handleRowChange(row, event) {
        this.dialogStatus = 'detail'
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
        this.editForm.dangerBehav = this.editForm.dangerBehav.split(',')
        this.editForm.pastSymptoms = this.editForm.pastSymptoms.split(',')
        this.area = this.editForm.area.split('/')
        this.edit = true
      },
      // 查询框
      findBtn() {
        this.dialogStatus = 'search'
        this.dialogFormVisible2 = true
        this.findForm = {
          peopleId: '',
          name: '',
          guardianName: '',
          guardianAddre: '',
          guardPhone: '',
          commitPersonName: '',
          commitPersonNum: '',
          accountType: '',
          outService: '',
          firstOnsetTime: '',
          firstAntipsyTreat: '',
          firstOnsetTime_s: '',
          firstOnsetTime_e: ''
        }
      },
      // 查询
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
          peopleId: '',
          name: '',
          area: '',
          guardianName: '',
          relaPatients: '',
          guardianAddre: '',
          guardPhone: '',
          commitPersonName: '',
          commitPersonNum: '',
          accountType: '',
          employSituat: '',
          infConsent: '',
          signatory: '',
          signatureDate: '',
          firstOnsetTime: '',
          pastSymptoms: [],
          pastLocking: '',
          outService: '',
          firstAntipsyTreat: '',
          inHospital: '',
          diagnosis: '',
          hospitalDia: '',
          confiDate: '',
          lastTreatEff: '',
          dangerBehav: [],
          stateEconomy: '',
          speciaOpinion: '',
          dateFill: '',
          docSign: '',
          creator: this.$store.getters.name,
          createDate: null,
          modifier: '',
          modifyDate: null
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
                para.firstOnsetTime =
                  !para.firstOnsetTime || para.firstOnsetTime === ''
                    ? null
                    : util.formatDate.format(new Date(para.firstOnsetTime), 'yyyy-MM-dd')
                para.signatureDate =
                  !para.signatureDate || para.signatureDate === ''
                    ? null
                    : util.formatDate.format(new Date(para.signatureDate), 'yyyy-MM-dd')
                para.firstAntipsyTreat =
                  !para.firstAntipsyTreat || para.firstAntipsyTreat === ''
                    ? null
                    : util.formatDate.format(new Date(para.firstAntipsyTreat), 'yyyy-MM-dd ')
                para.confiDate =
                  !para.confiDate || para.confiDate === ''
                    ? null
                    : util.formatDate.format(new Date(para.confiDate), 'yyyy-MM-dd')
                para.dateFill =
                  !para.dateFill || para.dateFill === ''
                    ? null
                    : util.formatDate.format(new Date(para.dateFill), 'yyyy-MM-dd')
                para.area = ''
                for (const itm of this.area) {
                  para.area += itm
                  para.area += '/'
                }
                para.area = para.area.substr(0, para.area.length - 1)
                para.dangerBehav = ''
                for (const itm of this.editForm.dangerBehav) {
                  if (itm !== '') {
                    para.dangerBehav += itm
                    para.dangerBehav += ','
                  }
                }
                para.dangerBehav = para.dangerBehav.substr(0, para.dangerBehav.length - 1)
                para.pastSymptoms = ''
                for (const itm of this.editForm.pastSymptoms) {
                  if (itm !== '') {
                    para.pastSymptoms += itm
                    para.pastSymptoms += ','
                  }
                }
                para.pastSymptoms = para.pastSymptoms.substr(0, para.pastSymptoms.length - 1)
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
              cancelButtonText: '取消',
              type: 'warning'
            })
              .then(() => {
                this.editForm.id = (parseInt(Math.random() * 100)).toString() // mock a id
                const para = Object.assign({}, this.editForm)
                console.log(para)
                para.firstOnsetTime =
                  !para.firstOnsetTime || para.firstOnsetTime === ''
                    ? null
                    : util.formatDate.format(new Date(para.firstOnsetTime), 'yyyy-MM-dd')
                para.signatureDate =
                  !para.signatureDate || para.signatureDate === ''
                    ? null
                    : util.formatDate.format(new Date(para.signatureDate), 'yyyy-MM-dd')
                para.firstAntipsyTreat =
                  !para.firstAntipsyTreat || para.firstAntipsyTreat === ''
                    ? null
                    : util.formatDate.format(new Date(para.firstAntipsyTreat), 'yyyy-MM-dd')
                para.confiDate =
                  !para.confiDate || para.confiDate === ''
                    ? null
                    : util.formatDate.format(new Date(para.confiDate), 'yyyy-MM-dd')
                para.dateFill =
                  !para.dateFill || para.dateFill === ''
                    ? null
                    : util.formatDate.format(new Date(para.dateFill), 'yyyy-MM-dd')
                para.area = ''
                for (const itm of this.area) {
                  para.area += itm
                  para.area += '/'
                }
                para.area = para.area.substr(0, para.area.length - 1)
                para.dangerBehav = ''
                for (const itm of this.editForm.dangerBehav) {
                  if (itm !== '') {
                    para.dangerBehav += itm
                    para.dangerBehav += ','
                  }
                }
                para.dangerBehav = para.dangerBehav.substr(0, para.dangerBehav.length - 1)
                para.pastSymptoms = ''
                for (const itm of this.editForm.pastSymptoms) {
                  if (itm !== '') {
                    para.pastSymptoms += itm
                    para.pastSymptoms += ','
                  }
                }
                para.pastSymptoms = para.pastSymptoms.substr(0, para.pastSymptoms.length - 1)
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

