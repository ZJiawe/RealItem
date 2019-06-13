<template>
  <section class="app-container">

    <!--工具条-->
    <el-col :span="22" class="toolbar" style="padding-bottom: 0px;">
      <el-form :inline="true" :model="filters" @submit.native.prevent>
        <el-form-item>
          <el-input v-model="filters.name" placeholder="姓名"></el-input>
        </el-form-item>
        <el-form-item>
          <el-input v-model="filters.peopleId" placeholder="身份证查询"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" v-on:click="getUsers"style="background-color:#4db2ee;border-color:transparent ;height:40px" icon="el-icon-search">查询</el-button>
        </el-form-item>
        <el-form-item>
          <el-button  v-if="buttons.KPFV_C" type="primary"  style="height:40px" @click="handleAdd"icon="el-icon-circle-plus-outline">新增</el-button>
        </el-form-item>
      </el-form>
    </el-col>

    <!--列表-->
    <el-table :data="users":height="tableHeight"  highlight-current-row @selection-change="selsChange" border style="width: 100%;"  @row-dblclick="handleRowChange" >
      <el-table-column type="index" label="序号" width="80">
      </el-table-column>
      <el-table-column prop="name" label="姓名" width="120">
      </el-table-column>
      <el-table-column prop="id" label="编号" width="120">
      </el-table-column>
      <el-table-column prop="flowUpTime" label="随访时间" width="120">
      </el-table-column>
      <el-table-column prop="nextFollotime" label="下次随访时间" min-width="120">
      </el-table-column>
      <el-table-column prop="followdoctorSignature" label="随访医生" min-width="120">
      </el-table-column>

      <el-table-column label="操作" width="200">
        <template slot-scope="scope">
          <a size="14px" @click="goto(scope.$index, scope.row)" style="color:#4DB2EE;margin-right:20px;">查看档案</a>
          <a size="14px"  v-if="buttons.KPFV_E" @click="handleEdit(scope.$index, scope.row)" style="color:#4DB2EE;margin-right:20px;">编辑</a>
          <a size="14px" v-if="buttons.KPFV_D"  @click="handleDel(scope.$index, scope.row)" style="color:#FB4141">删除</a>
        </template>
      </el-table-column>
    </el-table>

    <!--工具条-->
    <el-col :span="24" class="toolbar">
      <el-pagination layout="prev, pager, next" @current-change="handleCurrentChange" :page-size="20" :total="total" style="float:right;">
      </el-pagination>
    </el-col>

    <!--编辑界面-->
  <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible" :close-on-click-modal="false" width="80%" :before-close="handleClose" center>
      <div id="print">
        <el-form :model="editForm" label-width="120px" :rules="editFormRules" ref="editForm">
          <EasyScrollbar>
            <div class="item" v-bind:style="{ height: tablebodyHeight + 'px'}">
              <el-tabs v-model="activeName">
                <div id="dataPanel">
                  <table id="table"  border="1"  cellspacing="0" bordercolor="#dddddd" style="margin-left:10px;margin-right:30px;border-collapse:collapse;">
                    <tbody>
                    <tr v-if="dialogStatus==='detail'">
                      <td style="word-wrap:break-word;text-align:center;">
                        创建人
                      </td>
                      <td  style="text-align:center" class="pad10">
                        <el-form-item label="" label-width="0px">
                          <el-input v-model="editForm.creator" style="width: 100%" v-if="dialogStatus==='detail'" readonly></el-input>
                        </el-form-item>
                      </td>
                      <td style="word-wrap:break-word;text-align:center;" >
                        创建时间
                      </td>
                      <td colspan="2" style="text-align:center" class="pad10">
                        <el-form-item label="" label-width="0px" v-if="dialogStatus==='detail'">
                          <el-date-picker type="date" placeholder="选择日期" v-model="editForm.createDate" style="width: 100%" v-if="dialogStatus==='detail'" readonly></el-date-picker>
                        </el-form-item>
                      </td>
                    </tr>
                    <tr v-if="dialogStatus==='detail'">
                      <td style="word-wrap:break-word;text-align:center;">
                        修改人
                      </td>
                      <td  style="text-align:center" class="pad10">
                        <el-form-item label="" label-width="0px" v-if="dialogStatus==='detail'">
                          <el-input v-model="editForm.modifier" style="width: 100%" v-if="dialogStatus==='detail'" readonly></el-input>
                        </el-form-item>
                      </td>
                      <td style="word-wrap:break-word;text-align:center;">
                        修改时间
                      </td>
                      <td colspan="2" style="text-align:center" class="pad10">
                        <el-form-item label="" label-width="0px" v-if="dialogStatus==='detail'">
                          <el-date-picker type="date" placeholder="选择日期" v-model="editForm.modifyDate" style="width: 100%" v-if="dialogStatus==='detail'" readonly></el-date-picker>
                        </el-form-item>
                      </td>
                    </tr>
                    <tr>
                      <td style="width: 140px;word-wrap:break-word;text-align:center;" class="pad10">
                        <span>*</span>身份证号
                      </td>
                      <td style="text-align:center;padding-right: 0px" class="pad10">
                        <el-form-item label="" label-width="0px" prop="peopleId" >
                          <el-input v-model="editForm.peopleId"  id="peopleId" auto-complete="off"  v-on:input="inputFunc1" placeholder="请输入身份证号"></el-input>
                        </el-form-item>
                      </td>
                      <td style="width:140px;word-wrap:break-word;text-align:center;" class="pad"><span>*</span>姓名</td>
                      <td colspan="2" style="text-align:center;padding-right: 0px" class="pad10">
                        <el-form-item  label="" label-width="0px">
                          <el-input v-model="editForm.name" auto-complete="off" disabled></el-input>
                        </el-form-item>
                      </td>
                    </tr>
                    <tr>
                      <td style="word-wrap:break-word;width: 140px;text-align:center;" >随访时间</td>
                      <td  class="pad10" style="padding-right: 0px">
                        <el-form-item label="" label-width="0px"  >
                          <el-date-picker type="date" placeholder="请输入随访时间" v-model="editForm.flowUpTime"  id="flowUpTime"  ref="flowUpTime" style="width: 100%;"></el-date-picker>
                        </el-form-item>
                      </td>
                      <td style="text-align:center">治疗月序</td>
                      <td colspan='2' style="text-align:left;padding-right: 0px" class="pad10">
                        <el-form-item label="" label-width="0px" prop="treatmentMonth" >
                          <el-input-number v-model="editForm.treatmentMonth" :min="1" :max="200">
                          </el-input-number>
                        </el-form-item>
                      </td>
                    </tr>
                    <tr>
                      <td style="word-wrap:break-word;width: 140px;text-align:center">督导人员</td>
                      <td colspan='1' style="text-align:left" class="pad20">
                        <el-form-item label="" label-width="0px" >
                          <el-radio-group v-model="editForm.superivisor">
                            <el-radio label=医生>医生</el-radio>
                            <el-radio label=家属>家属</el-radio>
                            <el-radio label=自服药>自服药</el-radio>
                          </el-radio-group>
                          <el-radio-group v-model="editForm.superivisor">
                            <el-radio label=其他>其他</el-radio>
                          </el-radio-group>
                        </el-form-item>
                      </td>
                      <td style="width: 19%;text-align:center">随访方式</td>
                      <td colspan='2' style="text-align:left" class="pad20">
                        <el-form-item label="" label-width="0px" >
                          <el-radio-group v-model="editForm.followUpMethods">
                            <el-radio label=门诊>门诊</el-radio>
                            <el-radio label=家庭>家庭</el-radio>
                          </el-radio-group>
                        </el-form-item>
                      </td>
                    </tr>

                    <tr>
                      <td rowspan='3' style="word-wrap:break-word;width: 140px;text-align:center">症状及体征</td>
                      <td colspan='4' style="text-align:left" class="pad20">
                        <el-form-item label="" label-width="0px" >
                          <el-checkbox-group v-model="editForm.symptomsSigns">
                            <table>
                              <tr>
                                <td style="padding-right: 15px"><el-checkbox label="没有症状" name="type"></el-checkbox></td>
                                <td style="padding-right: 15px"><el-checkbox label="咳嗽咳痰" name="type"></el-checkbox></td>
                                <td style="padding-right: 15px"><el-checkbox label="低热盗汗" name="type"></el-checkbox></td>
                                <td style="padding-right: 15px"><el-checkbox label="咯血或血痰" name="type"></el-checkbox></td>
                                <td style="padding-right: 15px"><el-checkbox label="胸痛消廋" name="type"></el-checkbox></td>
                              </tr>
                              <tr>
                                <td style="padding-right: 15px"><el-checkbox label="恶心纳差" name="type"></el-checkbox></td>
                                <td style="padding-right: 15px"><el-checkbox label="头痛失眠" name="type"></el-checkbox></td>
                                <td style="padding-right: 15px"><el-checkbox label="视物模糊" name="type"></el-checkbox></td>
                                <td style="padding-right: 15px"><el-checkbox label="皮肤瘙痒、皮疹" name="type"></el-checkbox></td>
                                <td style="padding-right: 15px"><el-checkbox label="耳鸣、听力下降" name="type"></el-checkbox></td>
                              </tr>
                            </table>
                          </el-checkbox-group>
                        </el-form-item>
                      </td>
                    </tr>
                    <td rowspan='2' style="text-align:center;">其他</td>
                    <td rowspan='2' colspan='3'class="pad10" style="padding-right: 0px">
                      <el-form-item label=""label-width="0px" >
                        <el-input v-model="editForm.otherSymptoms" ></el-input>
                      </el-form-item>
                    </td>
                    <tr></tr>


                    <tr>
                      <td rowspan='2' style="word-wrap:break-word;width: 140px;text-align:center">生活方式评估</td>
                      <td style="text-align:center;">吸烟</td>
                      <td colspan='3' class="pad10" style="padding-right: 0px">
                        <el-form-item label=""label-width="0px" >
                          <el-input  v-model="editForm.smoking"  >
                            <template slot="append">支/天</template>
                          </el-input>
                        </el-form-item>
                      </td>
                    </tr>
                    <tr>
                      <td style="text-align:center;">饮酒</td>
                      <td colspan='3' class="pad10" style="padding-right: 0px">
                        <el-form-item label=""label-width="0px" >
                          <el-input  v-model="editForm.alcohol"  >
                            <template slot="append">两/天</template>
                          </el-input>
                        </el-form-item>
                      </td>
                    </tr>

                    <tr>
                      <td rowspan='4' style="width: 140px;word-wrap:break-word;text-align:center">用药</td>
                      <td style="text-align:center;" >化疗方案</td>
                      <td colspan='3' class="pad10" style="padding-right: 0px" >
                        <el-form-item label=""label-width="0px" >
                          <el-input v-model="editForm.chemotherapyRegimen" ></el-input>
                        </el-form-item>
                      </td>
                    </tr>
                    <tr>
                      <td  style="text-align:center;">用法</td>
                      <td colspan='3'style="text-align:left" class="pad20">
                        <el-form-item label=""label-width="0px" >
                          <el-radio-group v-model="editForm.usages">
                            <el-radio label=每日>每日</el-radio>
                            <el-radio label=间歇>间歇</el-radio>
                          </el-radio-group>
                        </el-form-item>
                      </td>
                    </tr>
                    <tr>
                      <td  style="text-align:center;">药品剂型</td>
                      <td colspan='3' style="text-align:left" class="pad20">
                        <el-form-item label=""label-width="0px" >
                          <el-checkbox-group v-model="editForm.drugDosage">
                            <el-checkbox v-for="item in drugDosage" :key="item.value":name="type":label="item.label":value="item.value"></el-checkbox>
                          </el-checkbox-group>
                        </el-form-item>
                      </td>
                    </tr>
                    <tr>
                      <td style="text-align:center;">漏服药次数</td>
                      <td colspan='3' style="text-align:center;padding-right: 0px" class="pad10">
                        <el-form-item label=""label-width="0px" >
                          <el-input  v-model="editForm.numbermissedMedications"  >
                            <template slot="append">次</template>
                          </el-input>
                        </el-form-item>
                      </td>
                    </tr>

                    <tr>
                      <td style="width: 140px;word-wrap:break-word;text-align:center">药物不良反应</td>
                      <td colspan='1' style="text-align:left" class="pad20">
                        <el-form-item label=""label-width="0px" >
                          <el-radio-group v-model="editForm.adverseDrugReactions">
                            <table cellspacing="0">
                              <tr>
                                <td><el-radio label=无>无</el-radio></td>
                                <td><el-radio label=有>有</el-radio></td>
                                <td class="pad10" style="padding-right: 0px"><el-input  v-model="editForm.adverseReactions" placeholder="不良反应" style="width: 100%" ></el-input></td>
                              </tr>
                            </table>
                          </el-radio-group>
                        </el-form-item>
                      </td>
                      <td style="width: 19%;text-align:center">并发症或合并症</td>
                      <td colspan='2' style="text-align:left" class="pad20">
                        <el-form-item label=""label-width="0px" >
                          <el-radio-group v-model="editForm.complicationComorbidities">
                            <table cellspacing="0">
                              <tr>
                                <td><el-radio label=无>无</el-radio></td>
                                <td><el-radio label=有>有</el-radio></td>
                                <td class="pad10" style="padding-right: 0px"><el-input  v-model="editForm.complications" placeholder="并发症或合并症" style="width: 100%" ></el-input></td>
                              </tr>
                            </table>
                          </el-radio-group>
                        </el-form-item>
                      </td>
                    </tr>

                    <tr>
                      <td rowspan='3' style="width: 140px;word-wrap:break-word;text-align:center">转诊</td>
                      <td  style="text-align:center;">科别</td>
                      <td colspan='3' class="pad10" style="padding-right: 0px">
                        <el-form-item label=""label-width="0px" >
                          <el-input  v-model="editForm.subjects"  ></el-input>
                        </el-form-item>
                      </td>
                    </tr>
                    <tr>
                      <td style="text-align:center;">原因</td>
                      <td colspan='3' class="pad10" style="padding-right: 0px">
                        <el-form-item label=""label-width="0px" >
                          <el-input  v-model="editForm.causes"  ></el-input>
                        </el-form-item>
                      </td>
                    </tr>
                    <tr>
                      <td  style="text-align:center;">2周内随访，随访结果</td>
                      <td colspan='3' class="pad10"style=" padding-top: 5px;padding-bottom: 5px;padding-right: 0px">
                        <el-form-item label=""label-width="0px" >
                          <el-input  v-model="editForm.twoweeksResult"  type="textarea" rows="4"></el-input>
                        </el-form-item>
                      </td>
                    </tr>


                    <tr>
                      <td style="width: 140px;word-wrap:break-word;text-align:center">处理意见</td>
                      <td colspan='4' style="text-align:center;padding-top: 5px;padding-bottom: 5px;padding-right: 0px" class="pad10">
                        <el-form-item label=""label-width="0px" >
                          <el-input  v-model="editForm.treamntOptions"  type="textarea" rows="2" ></el-input>
                        </el-form-item>
                      </td>
                    </tr>
                    <tr>
                      <td style="text-align:center">下次随访时间</td>
                      <td colspan='1' class="pad10" style="padding-right: 0px">
                        <el-form-item label=""label-width="0px" >
                          <el-date-picker type="date"  v-model="editForm.nextFollotime" style="width: 100%;"></el-date-picker>
                        </el-form-item>
                      </td>
                      <td style="text-align:center">随访医生签名</td>
                      <td colspan='2' class="pad10" style="padding-right: 0px">
                        <el-form-item label=""label-width="0px" >
                          <el-input  v-model="editForm.followdoctorSignature" auto-complete="off" ></el-input>
                        </el-form-item>
                      </td>
                    </tr>
                    <tr>
                      <td  rowspan="2" style="width: 140px;text-align:center">停止治疗及原因</td>
                      <td colspan="1">出现停止治疗时间：</td>
                      <td colspan="3" class="pad10" style="padding-right: 0px" ><el-form-item label=""label-width="0px" >
                        <el-date-picker type="date"  v-model="editForm.stopTime" style="width: 100%;"></el-date-picker>
                      </el-form-item>
                      </td>
                    </tr>
                    <tr>
                      <td colspan="1">停止治疗原因：</td>
                      <td colspan="3" class="pad20 " style="text-align:left">
                        <el-form-item label=""label-width="0px" >
                          <el-radio-group v-model="editForm.stopTreatment">
                            <el-radio label=完成治疗>完成治疗</el-radio>
                            <el-radio label=死亡>死亡</el-radio>
                            <el-radio label=丢失>丢失</el-radio>
                            <el-radio label=转入耐多药治疗>转入耐多药治疗</el-radio>
                          </el-radio-group>
                        </el-form-item>
                      </td>
                    </tr>

                    <tr>
                      <td style="width: 140px;text-align:center"  rowspan='2' >全程管理情况</td>
                      <td colspan='4'style="text-align:left;padding-top: 5px;padding-bottom: 5px;" class="pad20">
                        <table  style="width: 100%;" >
                          <tr class="pad10" >
                            <td class="pad10"  style="padding-top: 10px;">
                            应访视患者  <el-input style="width:  20.3%"  placeholder="请输入"v-model="editForm.shouldVisit" auto-complete="off" >
                            <template slot="append">次</template>
                          </el-input>，实际访视 <el-input style="width:  20.3%" placeholder="请输入" v-model="editForm.actualVisit" auto-complete="off" >
                            <template slot="append">次</template>
                          </el-input>;
                            </td>
                          </tr>
                          <tr >
                            <td class="pad10" style="padding-top: 20px;padding-bottom: 10px;padding-right: 0px">
                              患者在疗程中，应服药 <el-input style="width: 21.1%" placeholder="请输入" @change="score"v-model="editForm.mnumner"  >
                              <template slot="append">次</template>
                            </el-input>，实际服药<el-input style="width: 21.1%"  placeholder="请输入"@change="score" v-model="editForm.amnumber" auto-complete="off"   >
                              <template slot="append">次</template>
                            </el-input>，服药率  <el-input style="width: 21.1%" placeholder="请输入" v-model="editForm.medicineRate"auto-complete="off">
                              <template slot="append">%</template>
                            </el-input>
                            </td>
                          </tr>
                        </table>
                     </td>
                    </tr>
                    <tr>
                      <td style="text-align:center">评估医生签名</td>
                      <td colspan='3'class="pad10" style="padding-right: 0px">
                        <el-form-item label=""label-width="0px" >
                          <el-input  v-model="editForm.evaluationDoctor" auto-complete="off" ></el-input>
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
        <el-button style="height:40px" @click="conprint()">打印</el-button>
        <el-button   style="height:40px"v-if="dialogStatus=='create'" type="primary" @click="check">添加</el-button>
        <el-button   style="height:40px"v-else type="primary"  @click="createData" v-if="edit">新增修改</el-button>
        <el-button   style="height:40px"v-else type="primary" @click="updateData" v-if="edit">修改</el-button>
        <el-button   style="height:40px"@click.native="dialogFormVisible=false">取消</el-button>
      </div>
    </el-dialog>

  </section>
</template>

<script>
  import util from '@/utils/table.js'
  import {
    getList,
    removeData,
    batchRemoveUser,
    updateData,
    addData
  } from '@/api/Followupservicerecord'
  import {
    getList as getSearchMemberPage
  } from '@/api/Perinfor'

  import {
    getList as getFollow,
    updateData as editFollow,
    addData as addFollow
  } from '@/api/follow'
  import { getList as getPerson } from '@/api/FirsthomevisitRecord'

  export default {
    components: {},

    data() {
      return {
        buttons: {
          KPFV_C: false,
          KPFV_E: false,
          KPFV_D: false
        },
        edit: true,
        symptomsSigns: [
          { value: '没有症状', label: '没有症状' },
          { value: '咳嗽咳痰', label: '咳嗽咳痰' },
          { value: '低热盗汗', label: '低热盗汗' },
          { value: '咯血或血痰', label: '咯血或血痰' },
          { value: '胸痛消廋', label: '胸痛消廋' },
          { value: '恶心纳差', label: '恶心纳差' },
          { value: '头痛失眠', label: '头痛失眠' },
          { value: '视物模糊', label: '视物模糊' },
          { value: '皮肤瘙痒、皮疹', label: '皮肤瘙痒、皮疹' },
          { value: '耳鸣、听力下降', label: '耳鸣、听力下降' }
        ],
        drugDosage: [
          { value: '固定剂量复合制剂', label: '固定剂量复合制剂' },
          { value: '散装药', label: '散装药' },
          { value: '板式组合药', label: '板式组合药' }
        ],
        activeName: 'first',
        dialogStatus: '',
        tableHeight: window.innerHeight - 220,
        tablebodyHeight: window.innerHeight - 320,
        textMap: {
          update: '编辑',
          create: '添加',
          detail: '详情'
        },
        dialogFormVisible: false,
        filters: {
          name: '',
          flowUpTime_s: null,
          flowUpTime_e: null,
          peopleId: ''

        },
        users: [],
        total: 0,
        page: 1,
        sels: [], // 列表选中列
        editFormRules: {
          name: [{ required: true, message: '  ', trigger: 'blur' }],
          flowUpTime: [{ required: true, message: ' ', trigger: 'blur' }],
          peopleId: [
            { required: true, message: ' ', trigger: 'blur' },
            { pattern: /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/, message: ' ' }
          ],
          treatmentMonth: [{ required: true, message: '请输入治疗月序', trigger: 'blur' }]
        },
        // 编辑界面数据
        editForm: {
          name: '',
          flowUpTime: null,
          treatmentMonth: '',
          superivisor: '',
          followUpMethods: '',
          symptomsSigns: [],
          smoking: '',
          alcohol: '',
          chemotherapyRegimen: '',
          usages: '',
          drugDosage: [],
          numbermissedMedications: '',
          adverseDrugReactions: '',
          complicationComorbidities: '',
          subjects: '',
          causes: '',
          twoweeksResult: '',
          treamntOptions: '',
          nextFollotime: null,
          followdoctorSignature: '',
          stopTime: null,
          stopTreatment: '',
          shouldVisit: '',
          mnumner: '',
          amnumber: '',
          medicineRate: '',
          evaluationDoctor: '',
          adverseReactions: '',
          complications: '',
          otherSymptoms: '',
          actualVisit: '',
          other: ''
        },

        addFormVisible: false, // 新增界面是否显示
        addFormRules: {
          name: [{ required: true, message: ' ', trigger: 'blur' }],
          flowUpTime: [{ required: true, message: ' ', trigger: 'blur' }],
          peopleId: [
            { required: true, message: ' ', trigger: 'blur' },
            { pattern: /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/, message: ' ' }
          ],
          treatmentMonth: [{ required: true, message: '请输入治疗月序', trigger: 'blur' }]
        }
      }
    },
    methods: {
      // 根据身份证赋值
      inputFunc1() {
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
                  this.active = 2
                }
              })
            })
          }, 200)
        }
      },

      // 服药率
      score: function() {
        this.editForm.medicineRate = ((this.editForm.amnumber / this.editForm.mnumner) * 100).toFixed(2)
      },
      formatSex: function(row, column) {
        return row.sex === 1 ? '男' : row.sex === 0 ? '女' : '未知'
      },
      handleCurrentChange(val) {
        this.page = val
        this.getUsers()
      },
      // 打印
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
          '  <div id="topContent">\n' +
          '    <h2 style="width:794px;text-align:center"> 肺结核患者随访记录表</h2> \n' +
          '    <span style="margin-left:34px;">姓名：</span>\n' +
          '    <span style="margin-left:34px;">{{name}}</span>\n' +
          '\t<span style="margin-left:250px;">编号:</span>\n' +
          '    <span style="margin-left:5px;">{{id}}</span> \n' +
          '    </div>\n' +
          '  <table id="table"  border="1"  cellspacing="0" bordercolor="#000000" style="width:730px;margin-left:30px;margin-top:5px;border-collapse:collapse;"  width = "80%">\n' +
          '    <tbody>\n' +
          '      \n' +
          '      <tr>\n' +
          '        <td style="word-wrap:break-word;width: 10%;text-align:center">随访时间</td>\n' +
          '        <td colspan=\'2\' >{{flowUpTime}}</td>\n' +
          '        <td style="text-align:center">治疗月序</td>\n' +
          '        <td colspan=\'1\'>{{treatmentMonth}}</td>\n' +
          '      </tr>\n' +
          '      <tr>\n' +
          '        <td style="word-wrap:break-word;width: 10%;text-align:center">督导人员</td>\n' +
          '        <td colspan=\'2\'>{{superivisor}}</td>\n' +
          '        <td style="width: 19%;text-align:center">随访方式</td>\n' +
          '        <td colspan=\'1\'>{{followUpMethods}}</td>\n' +
          '      </tr>\n' +
          '\t  \n' +
          '\t   <tr>\n' +
          '        <td rowspan=\'3\' style="word-wrap:break-word;width: 10%;text-align:center">症状及体征</td>\n' +
          '        <td colspan=\'4\' style="height:50px">{{symptomsSigns}}</td>   \n' +
          '      </tr> \n' +
          '\t<td rowspan=\'2\'>其他</td>\n' +
          '\t<td rowspan=\'2\' colspan=\'3\'>{{otherSymptoms}}</td>\n' +
          '\t <tr></tr>\n' +
          '\t \n' +
          '\t   \n' +
          '\t   <tr>\n' +
          '        <td rowspan=\'2\' style="word-wrap:break-word;width: 10%;text-align:center">生活方式评估</td>\n' +
          '        <td >吸烟</td>\n' +
          '        <td colspan=\'3\'>{{smoking}} 支/天</td>\n' +
          '      </tr>\n' +
          '\t  <tr>\n' +
          '\t\t<td >饮酒</td>\n' +
          '\t\t<td colspan=\'3\'>{{alcohol}} 两/天</td> \n' +
          '\t  </tr>\n' +
          '\t  \n' +
          '\t   <tr>\n' +
          '        <td rowspan=\'4\' style="word-wrap:break-word;width: 10%;text-align:center">用药</td>\n' +
          '        <td >化疗方案</td>\n' +
          '        <td colspan=\'3\'>{{chemotherapyRegimen}}</td>\n' +
          '      </tr>\n' +
          '\t  <tr>\n' +
          '\t\t<td >用法</td>\n' +
          '\t\t<td colspan=\'3\'>{{usages}}</td> \n' +
          '\t  </tr>\n' +
          '\t  <tr>\n' +
          '\t\t<td >药品剂型</td>\n' +
          '\t\t<td colspan=\'3\'>{{drugDosage}}</td> \n' +
          '\t  </tr>\n' +
          '\t  <tr>\n' +
          '\t\t<td>漏服药次数</td>\n' +
          '\t\t<td colspan=\'3\'>{{numbermissedMedications}}</td> \n' +
          '\t  </tr>\n' +
          '\t  \n' +
          '\t   <tr>\n' +
          '        <td style="word-wrap:break-word;width: 10%;text-align:center">药物不良反应</td>\n' +
          '        <td colspan=\'1\'>{{adverseDrugReactions}},{{adverseReactions}}</td>\n' +
          '        <td style="width: 19%;text-align:center">并发症或合并症</td>\n' +
          '        <td colspan=\'2\'>{{complicationComorbidities}},{{complications}}</td>\n' +
          '      </tr>\n' +
          '\t  \n' +
          '     <tr>\n' +
          '        <td rowspan=\'3\' style="word-wrap:break-word;width: 10%;text-align:center">转诊</td>\n' +
          '        <td >科别</td>\n' +
          '\t\t<td colspan=\'3\'>{{subjects}}</td> \n' +
          '      </tr>\n' +
          '\t  <tr>\n' +
          '\t\t<td >原因</td>\n' +
          '\t\t<td colspan=\'3\'>{{causes}}</td> \n' +
          '\t  </tr>\n' +
          '\t  <tr>\n' +
          '\t\t<td >2周内随访，随访结果</td>\n' +
          '\t\t<td colspan=\'3\'>{{twoweeksResult}}</td> \n' +
          '\t  </tr>\n' +
          '\t  \n' +
          '\t\n' +
          '\t   <tr>\n' +
          '        <td style="word-wrap:break-word;width: 10%;text-align:center">处理意见</td>\n' +
          '        <td colspan=\'4\'>{{treamntOptions}}</td>\n' +
          '      </tr>\n' +
          '\t    <tr>\n' +
          '        <td style="width: 10%;text-align:center">下次随访时间</td>\n' +
          '        <td colspan=\'2\'>{{nextFollotime}}</td>\n' +
          '\t\t <td style="width: 10%;text-align:center">随访医生签名</td>\n' +
          '        <td colspan=\'1\'>{{followdoctorSignature}}</td>\n' +
          '      </tr> \n' +
          '\t  <tr>\n' +
          '        <td style="width: 10%;text-align:center">停止治疗及原因</td>\n' +
          '        <td colspan=\'4\'>1.出现停止治疗时间：{{stopTime}}</br>2.停止治疗原因：{{stopTreatment}}</td>\n' +
          '      </tr>\n' +
          '\t  <tr>\n' +
          '        <td style="width: 10%;text-align:center"  rowspan=\'2\' >全程管理情况</td>\n' +
          '        <td colspan=\'4\'>应访视患者 {{shouldVisit}}次，实际访视 {{actualVisit}}次;</br>患者在疗程中，应服药 {{mnumner}}次，实际服药 {{amnumber}}次，服药率 {{medicineRate}}%</td>\n' +
          '      </tr>\n' +
          '\t  <tr>\n' +
          '\t  <td style="width: 10%;text-align:center">评估医生签名</td>\n' +
          '        <td colspan=\'3\'>{{evaluationDoctor}}</td>\n' +
          '\t  </tr>\n' +
          '\t  \n' +
          '\t  \n' +
          '    </tbody>\n' +
          '   \n' +
          '  \n' +
          '  </table>\n' +
          '</div>\n' +
          '</body>\n' +
          '</html>\n'
        str = str.replace(/{{name}}/, this.editForm.name ? this.editForm.name : '')
        str = str.replace(/{{id}}/, this.editForm.id ? this.editForm.id : '')
        str = str.replace(/{{flowUpTime}}/, this.editForm.flowUpTime ? this.editForm.flowUpTime : '')
        str = str.replace(/{{treatmentMonth}}/, this.editForm.treatmentMonth ? this.editForm.treatmentMonth : '')
        str = str.replace(/{{superivisor}}/, this.editForm.superivisor ? this.editForm.superivisor : '')
        str = str.replace(/{{followUpMethods}}/, this.editForm.followUpMethods ? this.editForm.followUpMethods : '')
        str = str.replace(/{{symptomsSigns}}/, this.editForm.symptomsSigns ? this.editForm.symptomsSigns : '')
        str = str.replace(/{{alcohol}}/, this.editForm.alcohol ? this.editForm.alcohol : '')
        str = str.replace(/{{smoking}}/, this.editForm.smoking ? this.editForm.smoking : '')
        str = str.replace(/{{chemotherapyRegimen}}/, this.editForm.chemotherapyRegimen ? this.editForm.chemotherapyRegimen : '')
        str = str.replace(/{{usages}}/, this.editForm.usages ? this.editForm.usages : '')
        str = str.replace(/{{drugDosage}}/, this.editForm.drugDosage ? this.editForm.drugDosage : '')
        str = str.replace(/{{numbermissedMedications}}/, this.editForm.numbermissedMedications ? this.editForm.numbermissedMedications : '')
        str = str.replace(/{{adverseDrugReactions}}/, this.editForm.adverseDrugReactions ? this.editForm.adverseDrugReactions : '')
        str = str.replace(/{{complicationComorbidities}}/, this.editForm.complicationComorbidities ? this.editForm.complicationComorbidities : '')
        str = str.replace(/{{subjects}}/, this.editForm.subjects ? this.editForm.subjects : '')
        str = str.replace(/{{causes}}/, this.editForm.causes ? this.editForm.causes : '')
        str = str.replace(/{{twoweeksResult}}/, this.editForm.twoweeksResult ? this.editForm.twoweeksResult : '')
        str = str.replace(/{{treamntOptions}}/, this.editForm.treamntOptions ? this.editForm.treamntOptions : '')
        str = str.replace(/{{nextFollotime}}/, this.editForm.nextFollotime ? this.editForm.nextFollotime : '')
        str = str.replace(/{{followdoctorSignature}}/, this.editForm.followdoctorSignature ? this.editForm.followdoctorSignature : '')
        str = str.replace(/{{stopTime}}/, this.editForm.stopTime ? this.editForm.stopTime : '')
        str = str.replace(/{{stopTreatment}}/, this.editForm.stopTreatment ? this.editForm.stopTreatment : '')
        str = str.replace(/{{shouldVisit}}/, this.editForm.shouldVisit ? this.editForm.shouldVisit : '')
        str = str.replace(/{{mnumner}}/, this.editForm.mnumner ? this.editForm.mnumner : '')
        str = str.replace(/{{amnumber}}/, this.editForm.amnumber ? this.editForm.amnumber : '')
        str = str.replace(/{{medicineRate}}/, this.editForm.medicineRate ? this.editForm.medicineRate : '')
        str = str.replace(/{{evaluationDoctor}}/, this.editForm.evaluationDoctor ? this.editForm.evaluationDoctor : '')
        str = str.replace(/{{adverseReactions}}/, this.editForm.adverseReactions ? this.editForm.adverseReactions : '')
        str = str.replace(/{{complications}}/, this.editForm.complications ? this.editForm.complications : '')
        str = str.replace(/{{otherSymptoms}}/, this.editForm.otherSymptoms ? this.editForm.otherSymptoms : '')
        str = str.replace(/{{actualVisit}}/, this.editForm.actualVisit ? this.editForm.actualVisit : '')
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
      // 获取用户列表
      getUsers() {
        this.filters.flowUpTime_s =
          !this.filters.flowUpTime_s || this.filters.flowUpTime_s === ''
            ? null
            : util.formatDate.format(new Date(this.filters.flowUpTime_s), 'yyyy-MM-dd')
        this.filters.flowUpTime_e =
          !this.filters.flowUpTime_e || this.filters.flowUpTime_e === ''
            ? null
            : util.formatDate.format(new Date(this.filters.flowUpTime_e), 'yyyy-MM-dd')

        const para = {
          page: this.page,
          size: 20,
          filters: { name: this.filters.name,
            flowUpTime_s: this.filters.flowUpTime_s,
            flowUpTime_e: this.filters.flowUpTime_e,
            peopleId: this.filters.peopleId }
        }
        getList(para).then(res => {
          this.total = res.totalCount
          this.users = res.detailModelList
          if (this.page > 1 && (this.page - 1) * 20 >= this.total) {
            this.page = this.page - 1
            this.getUsers()
          }
        })
      },
      // 判断管理
      check() {
        this.$refs.editForm.validate(valid => {
          if (valid) {
            const para = {
              page: 1,
              size: 200,
              filters: { name: '',
                followTime_s: '',
                followTime_e: '',
                inputDate_s: '',
                inputDate_e: '',
                area: '',
                ethnic: '',
                phone: '',
                responDoctor: '',
                inputPerson: '',
                lifeState: '',
                peopleId: this.editForm.peopleId,
                age: ''
              }
            }
            getPerson(para).then(res => {
              if (res.detailModelList.length !== 0) {
                this.createData()
              } else {
                this.$message.error('请先建立肺结核管理')
              }

            })
          }
          else {
              this.$message.error('请先建立肺结核管理..')

          }
        })
      },
      // 删除
      handleDel(index, row) {
        this.$confirm('确认删除该记录吗?', '提示', {
          confirmButtonText: ' 确定',
          cancelButtonText: ' 取消',
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
      // 查看档案
      goto(index, row) {
        this.$router.push({
          name: '首页',
          params: {
            hid: row.peopleId
          }
        })
      },
      // 显示详情界面
      handleRowChange(row, event) {
        this.dialogStatus = 'detail'
        this.dialogFormVisible = true
        this.editForm = Object.assign({}, row)
        this.editForm.drugDosage = this.editForm.drugDosage.split(',')
        this.editForm.symptomsSigns = this.editForm.symptomsSigns.split(',')
        this.edit = false
      },
      // 显示编辑界面
      handleEdit(index, row) {
        this.dialogStatus = 'update'
        this.dialogFormVisible = true
        this.editForm = Object.assign({}, row)
        this.editForm.drugDosage = this.editForm.drugDosage.split(',')
        this.editForm.symptomsSigns = this.editForm.symptomsSigns.split(',')
        this.edit = true
      },
      // 显示新增界面
      handleAdd() {
        this.dialogStatus = 'create'
        this.dialogFormVisible = true
        this.edit = false
        this.editForm = {
          name: '',
          flowUpTime: null,
          treatmentMonth: '',
          superivisor: '',
          followUpMethods: '',
          symptomsSigns: [],
          smoking: '',
          alcohol: '',
          chemotherapyRegimen: '',
          usages: '',
          drugDosage: [],
          numbermissedMedications: '',
          adverseDrugReactions: '',
          complicationComorbidities: '',
          subjects: '',
          causes: '',
          twoweeksResult: '',
          treamntOptions: '',
          nextFollotime: null,
          followdoctorSignature: '',
          stopTime: null,
          stopTreatment: '',
          shouldVisit: '',
          mnumner: '',
          amnumber: '',
          medicineRate: '',
          evaluationDoctor: '',
          adverseReactions: '',
          complications: '',
          otherSymptoms: '',
          actualVisit: '',
          other: '',
          creator: this.$store.getters.name
        }
      },
      // 编辑
      updateData() {
        this.$refs.editForm.validate(valid => {
          if (valid) {
            this.$confirm('确认提交吗？', '提示', {
              confirmButtonText: ' 确定',
              cancelButtonText: ' 取消'
            })
              .then(() => {
                const para = Object.assign({}, this.editForm)
                para.flowUpTime =
                  !para.flowUpTime || para.flowUpTime === ''
                    ? null
                    : util.formatDate.format(new Date(para.flowUpTime), 'yyyy-MM-dd')
                para.nextFollotime =
                para.nextFollotime =
                  !para.nextFollotime || para.nextFollotime === ''
                    ? null
                    : util.formatDate.format(new Date(para.nextFollotime), 'yyyy-MM-dd')
                para.stopTime =
                para.stopTime =
                  !para.stopTime || para.stopTime === ''
                    ? null
                    : util.formatDate.format(new Date(para.stopTime), 'yyyy-MM-dd')
                para.drugDosage = ''
                for (const itm of this.editForm.drugDosage) {
                  para.drugDosage += itm
                  para.drugDosage += ','
                }
                para.drugDosage = para.drugDosage.substr(0, para.drugDosage.length - 1)

                para.symptomsSigns = ''
                for (const itm of this.editForm.symptomsSigns) {
                  para.symptomsSigns += itm
                  para.symptomsSigns += ','
                }
                para.symptomsSigns = para.symptomsSigns.substr(0, para.symptomsSigns.length - 1)
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

                // 随访记录
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
                    if (follow[0].lastFollow < para.flowUpTime) {
                      follow[0].lastFollow = para.flowUpTime
                    }
                    if (follow[0].nextFollow === '' || follow[0].nextFollow === null) {
                      follow[0].nextFollow = para.nextFollotime
                    } else if (follow[0].nextFollow < para.nextFollotime || para.nextFollotime === '' || para.nextFollotime === null) {
                      follow[0].nextFollow = para.nextFollotime
                    }
                    editFollow(follow[0]).then(res => {})
                  } else {
                    followData.lastFollow = para.flowUpTime
                    followData.nextFollow = para.nextFollotime
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
              confirmButtonText: ' 确定',
              cancelButtonText: ' 取消'
            })
              .then(() => {
                this.editForm.id = (parseInt(Math.random() * 100)).toString() // mock a id
                const para = Object.assign({}, this.editForm)
                console.log(para)

                para.flowUpTime =
                  !para.flowUpTime || para.flowUpTime === ''
                    ? null
                    : util.formatDate.format(new Date(para.flowUpTime), 'yyyy-MM-dd')
                para.nextFollotime =
                para.nextFollotime =
                  !para.nextFollotime || para.nextFollotime === ''
                    ? null
                    : util.formatDate.format(new Date(para.nextFollotime), 'yyyy-MM-dd')
                para.stopTime =
                para.stopTime =
                  !para.stopTime || para.stopTime === ''
                    ? null
                    : util.formatDate.format(new Date(para.stopTime), 'yyyy-MM-dd')
                para.drugDosage = ''
                for (const itm of this.editForm.drugDosage) {
                  para.drugDosage += itm
                  para.drugDosage += ','
                }
                para.drugDosage = para.drugDosage.substr(0, para.drugDosage.length - 1)

                para.symptomsSigns = ''
                for (const itm of this.editForm.symptomsSigns) {
                  para.symptomsSigns += itm
                  para.symptomsSigns += ','
                }
                para.symptomsSigns = para.symptomsSigns.substr(0, para.symptomsSigns.length - 1)
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

                // 随访记录
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
                    if (follow[0].lastFollow < para.flowUpTime) {
                      follow[0].lastFollow = para.flowUpTime
                    }
                    if (follow[0].nextFollow === '' || follow[0].nextFollow === null) {
                      follow[0].nextFollow = para.nextFollotime
                    } else if (follow[0].nextFollow < para.nextFollotime || para.nextFollotime === '' || para.nextFollotime === null) {
                      follow[0].nextFollow = para.nextFollotime
                    }
                    editFollow(follow[0]).then(res => {})
                  } else {
                    followData.lastFollow = para.flowUpTime
                    followData.nextFollow = para.nextFollotime
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

 .lastrow td {
    border-bottom: 0;
  }

  .lastCol {
    border-right: 0;
  }

</style>
