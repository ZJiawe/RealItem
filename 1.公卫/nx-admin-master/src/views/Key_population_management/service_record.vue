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
          <el-button type="primary" v-on:click="findbtn" style="background-color:#4db2ee;border-color:transparent;width: 120px" icon="el-icon-search">高级查询</el-button>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="handleAdd" icon="el-icon-circle-plus-outline">新增</el-button>
        </el-form-item>
      </el-form>
    </el-col>


    <!--列表-->
    <el-table :data="users" :height="tableHeight" highlight-current-row @selection-change="selsChange" border style="width: 100%;margin-top:-20px;" @row-dblclick="handleRowChange">
      <!--<el-table-column type="selection"  width="55">-->
      <!--</el-table-column>-->

      <el-table-column type="index" label="序号" width="50">
      </el-table-column>
      <el-table-column prop="peopleId" label="身份证号" min-width="200">
      </el-table-column>
      <el-table-column prop="name" label="姓名" width="120">
      </el-table-column>
      <el-table-column prop="follDate" label="随访日期" min-width="120" v-if="false">
      </el-table-column>
      <el-table-column prop="formFollow" label="本次随访形式" width="120" v-if="false">
      </el-table-column>
      <el-table-column prop="lostReason"  label="若失访原因" width="100" v-if="false" >
      </el-table-column>
      <el-table-column prop="dateDeath" label="死亡日期" width="100"  v-if="false">
      </el-table-column>
      <el-table-column prop="causeDeath" label="死亡原因" width="100"  v-if="false">
      </el-table-column>
      <el-table-column prop="riskAssess" label="危险性评估" width="100"  v-if="false">
      </el-table-column>
      <el-table-column prop="currentSymptoms" label="目前症状" width="100"  v-if="false">
      </el-table-column>
      <el-table-column prop="selfKnowLedge" label="自知力" width="100"  v-if="false">
      </el-table-column>
      <el-table-column prop="sleepCondition" label="睡眠情况" width="100"  v-if="false">
      </el-table-column>
      <el-table-column prop="dietCondition" label="饮食情况" width="100"  v-if="false">
      </el-table-column>
      <el-table-column prop="pelLifeCondition" label="个人生活料理" width="120"  v-if="false">
      </el-table-column>
      <el-table-column prop="houseWork" label="家务劳动" width="100"  v-if="false">
      </el-table-column>
      <el-table-column prop="proAndWork" label="生产劳动及工作" width="150"  v-if="false">
      </el-table-column>
      <el-table-column prop="abilityLearn" label="学习能力" width="100"  v-if="false">
      </el-table-column>
      <el-table-column prop="socialCommu" label="社会人际交往" width="120"  v-if="false">
      </el-table-column>
      <el-table-column prop="dangerBeha" label="危险行为" width="100"  v-if="false">
      </el-table-column>
      <el-table-column prop="twoLockCondition" label="两次随访期间关锁情况" width="160"  v-if="false">
      </el-table-column>
      <el-table-column prop="twoInHospCondition" label="两次随访期间住院情况" width="160"  v-if="false">
      </el-table-column>
      <el-table-column prop="laborExamin" label="实验室检查" width="100"  v-if="false">
      </el-table-column>
      <el-table-column prop="medCompliance" label="用药依从性" min-width="160" v-if="false">
      </el-table-column>
      <el-table-column prop="badDrugReact" label="药物不良反应" width="120"  v-if="false">
      </el-table-column>
      <el-table-column prop="treatEffect" label="治疗效果" width="100" v-if="false">
      </el-table-column>
      <el-table-column prop="ifReferral" label="是否转诊" width="100" v-if="false">
      </el-table-column>
      <el-table-column prop="referralReason" label="转诊原因" min-width="100"  v-if="false">
      </el-table-column>
      <el-table-column prop="refToDepart" label="转诊至机构及科室" min-width="150" v-if="false">
      </el-table-column>
      <el-table-column prop="useDrugCondiA" label="用药情况1" width="100"  v-if="false">
      </el-table-column>
      <el-table-column prop="useDrugCondiB" label="用药情况2" width="100"  v-if="false">
      </el-table-column>
      <el-table-column prop="useDrugCondiC" label="用药情况3" width="100"  v-if="false">
      </el-table-column>
      <el-table-column prop="guideUseDrugA" label="用药指导1" width="100"  v-if="false">
      </el-table-column>
      <el-table-column prop="guideUseDrugB" label="用药指导2" width="100"  v-if="false">
      </el-table-column>
      <el-table-column prop="guideUseDrugC" label="用药指导3" width="100"  v-if="false">
      </el-table-column>
      <el-table-column prop="rehaMeasure" label="康复措施" width="100"  v-if="false">
      </el-table-column>
      <el-table-column prop="thisClassifi" label="本次随分类" min-width="120" v-if="false">
      </el-table-column>
      <el-table-column prop="nextDate" label="下次随访日期" min-width="120" v-if="false">
      </el-table-column>
      <el-table-column prop="docSign" label="随访医生签名" min-width="120" v-if="false">
      </el-table-column>
      <el-table-column prop="area" label="地区" min-width="150" align="center" >
      </el-table-column>
      <el-table-column prop="creator" label="创建人" width="120" align="center"  >
      </el-table-column>
      <el-table-column prop="createDate" label="创建时间" width="120" align="center" :formatter="timeFormat">
      </el-table-column>
      <el-table-column prop="modifier" label="修改人" width="120" align="center"  >
      </el-table-column>
      <el-table-column prop="modifyDate" label="修改时间" width="120" align="center" :formatter="timeFormat">
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
              <el-col :span="12">
                <el-form-item label="地区">
                  <el-cascader :options="options" v-model="area" style="width: 100%" placeholder="请选择"></el-cascader>
                </el-form-item>
              </el-col>
          </el-row>


            <el-row>
              <el-col :span="12">
              <el-form-item label="姓名" prop="name">
                <el-input v-model="editForm.name"></el-input>
              </el-form-item>
              </el-col>
              <el-col span="12">
                <el-form-item label="随访日期" >
                  <el-date-picker type="date" placeholder="选择日期" v-model="editForm.follDate" style="width: 100%;"></el-date-picker>
                </el-form-item>
              </el-col>
            </el-row>


            <el-row>
              <el-col span="24">
                <el-form-item label="本次随访形式"  >
                <el-radio-group v-model="editForm.formFollow">
                  <el-radio label="1门诊"></el-radio>
                  <el-radio label="2家庭视访"></el-radio>
                  <el-radio label="3电话"></el-radio>
                </el-radio-group>
                </el-form-item>
              </el-col>
            </el-row>

            <el-row>
              <el-col span="24">
                <el-form-item label="若失访，原因"  >
                  <el-radio-group v-model="editForm.lostReason" >
                    <el-radio label="1外出打工"></el-radio>
                    <el-radio label="2迁居他地"></el-radio>
                    <el-radio label="3走失"></el-radio>
                    <el-radio label="4连续3次未到访"></el-radio>
                  </el-radio-group>
                </el-form-item>
              </el-col>
            </el-row>

            <el-row>
              <el-col :span="12">
                <el-form-item label="如死亡，请选择日期" >
                  <el-date-picker type="date" placeholder="选择日期" v-model="editForm.dateDeath" style="width: 100%;"></el-date-picker>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="如死亡，请选择原因"  >
                  <el-cascader :options="Death" placeholder="请选择" v-model="causeDeath" ></el-cascader>
                </el-form-item>
              </el-col>
            </el-row>

            <el-row>
              <el-col span="24">
                <el-form-item label="危险性评估"  >
                  <el-radio-group v-model="editForm.riskAssess" >
                    <el-radio label="0级"></el-radio>
                    <el-radio label="1级"></el-radio>
                    <el-radio label="2级"></el-radio>
                    <el-radio label="3级"></el-radio>
                    <el-radio label="4级"></el-radio>
                    <el-radio label="5级"></el-radio>
                  </el-radio-group>
                </el-form-item>
              </el-col>
            </el-row>


            <el-row>
              <el-col>
                <el-form-item label="目前症状" >
                  <el-checkbox-group v-model="editForm.currentSymptoms" style=" width: 100%;">
                    <!--<el-checkbox v-for="item in currentSymptoms" :key="item.value" :name="type" :label="item.label" :value="item.value"></el-checkbox>-->
                    <table>
                      <tr>
                        <td style="padding-right: 25px"><el-checkbox label="幻觉" name="type"></el-checkbox></td>
                        <td style="padding-right: 25px"><el-checkbox label="交流困难" name="type"></el-checkbox></td>
                        <td style="padding-right: 25px"><el-checkbox label="猜疑" name="type"></el-checkbox></td>
                        <td style="padding-right: 25px"><el-checkbox label="喜怒无常" name="type"></el-checkbox></td>
                        <td style="padding-right: 25px"><el-checkbox label="行为怪异" name="type"></el-checkbox></td>
                        <td style="padding-right: 25px"><el-checkbox label="兴奋话多" name="type"></el-checkbox></td>
                        <td style="padding-right: 25px"><el-checkbox label="伤人伤物" name="type"></el-checkbox></td>
                      </tr>
                      <tr>
                        <td style="padding-right: 25px"><el-checkbox label="悲观厌世" name="type"></el-checkbox></td>
                        <td style="padding-right: 25px"><el-checkbox label="无故外走" name="type"></el-checkbox></td>
                        <td style="padding-right: 25px"><el-checkbox label="自语自笑" name="type"></el-checkbox></td>
                        <td style="padding-right: 25px"><el-checkbox label="孤僻懒散" name="type"></el-checkbox></td>
                        <td style="padding-right: 25px"><el-checkbox label="其他" name="type"></el-checkbox></td>
                      </tr>
                    </table>
                  </el-checkbox-group>
                </el-form-item>
              </el-col>
            </el-row>

            <el-row>
              <el-col>
                <el-form-item label="自知力"  >
                  <el-radio-group v-model="editForm.selfKnowLedge" >
                    <el-radio label="1 自知力完全"></el-radio>
                    <el-radio label="2 自知力不全"></el-radio>
                    <el-radio label="3 自知力缺失"></el-radio>
                  </el-radio-group>
                </el-form-item>
              </el-col>
            </el-row>

            <el-row>
              <el-col :span="12">
                <el-form-item label="睡眠情况" >
                  <el-radio-group v-model="editForm.sleepCondition" >
                    <el-radio label="1 良好"></el-radio>
                    <el-radio label="2 一般"></el-radio>
                    <el-radio label="3 较差"></el-radio>
                  </el-radio-group>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="饮食情况" >
                  <el-radio-group v-model="editForm.dietCondition" >
                    <el-radio label="1 良好"></el-radio>
                    <el-radio label="2 一般"></el-radio>
                    <el-radio label="3 较差"></el-radio>
                  </el-radio-group>
                </el-form-item>
              </el-col>
            </el-row>

            <el-row>
              <el-col :span="12">
                <el-form-item label="个人生活料理"  >
                  <el-radio-group v-model="editForm.pelLifeCondition">
                    <el-radio label="1 良好"></el-radio>
                    <el-radio label="2 一般"></el-radio>
                    <el-radio label="3 较差"></el-radio>
                  </el-radio-group>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="家务劳动" >
                  <el-radio-group v-model="editForm.houseWork" >
                    <el-radio label="1 良好"></el-radio>
                    <el-radio label="2 一般"></el-radio>
                    <el-radio label="3 较差"></el-radio>
                  </el-radio-group>
                </el-form-item>
              </el-col>
            </el-row>


            <el-row>
              <el-col :span="12">
                <el-form-item label="学习能力" >
                  <el-radio-group v-model="editForm.abilityLearn">
                    <el-radio label="1 良好"></el-radio>
                    <el-radio label="2 一般"></el-radio>
                    <el-radio label="3 较差"></el-radio>
                  </el-radio-group>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="社会人际交往" >
                  <el-radio-group v-model="editForm.socialCommu" >
                    <el-radio label="1 良好"></el-radio>
                    <el-radio label="2 一般"></el-radio>
                    <el-radio label="3 较差"></el-radio>
                  </el-radio-group>
                </el-form-item>
              </el-col>
            </el-row>

            <el-row>
              <el-col>
                <el-form-item label="生产劳动工作"  >
                  <el-radio-group v-model="editForm.proAndWork" >
                    <el-radio label="1 良好"></el-radio>
                    <el-radio label="2 一般"></el-radio>
                    <el-radio label="3 较差"></el-radio>
                  </el-radio-group>
                </el-form-item>
              </el-col>
            </el-row>

            <el-row>
              <el-col>
                <el-form-item label="危险行为">
                  <el-checkbox-group v-model="editForm.dangerBeha" style=" width: 100%;">
                    <el-checkbox v-for="item in dangerBeha" :key="item.value" :name="type" :label="item.label" :value="item.value"></el-checkbox>
                  </el-checkbox-group>
                </el-form-item>
              </el-col>
            </el-row>

            <el-row>
              <el-col :span="12">
                <el-form-item label="两次随访期间关锁情况" label-width="170px" style="margin-left: -10px;">
                  <el-select v-model="editForm.twoLockCondition" placeholder="请选择" style="width: 100%">
                    <el-option key="1" label="1 无关锁" value="无关锁"></el-option>
                    <el-option key="2" label="2 关锁" value="关锁"></el-option>
                    <el-option key="3" label="3 关锁已解除" value="关锁已解除"></el-option>
                  </el-select>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="两次随访期间住院情况" label-width="170px" >
                  <el-select v-model="editForm.twoInHospCondition" placeholder="请选择" style="width: 100%">
                    <el-option key="1" label="1 未住院" value="未住院"></el-option>
                    <el-option key="2" label="2 目前正在住院" value="目前正在住院"></el-option>
                    <el-option key="3" label="3 曾住院，现未住院" value="曾住院，现未住院"></el-option>
                  </el-select>
                </el-form-item>
              </el-col>
            </el-row>

            <el-row>
              <el-col :span="12">
                <el-form-item label="末次出院时间" >
                  <el-date-picker type="date" placeholder="选择日期" v-model="editForm.outHospitalDate" style="width: 100%;"></el-date-picker>
                </el-form-item>
              </el-col>
            </el-row>

            <el-row>
              <el-col :span="24">
                <el-form-item label="实验室检查" >
                  <el-radio-group v-model="editForm.laborExamin">
                    <el-radio label="1 无"></el-radio>
                    <el-radio label="2 有">
                      <el-input  placeholder="2 有（请输入详情）"></el-input>
                    </el-radio>
                  </el-radio-group>
                </el-form-item>
              </el-col>
            </el-row>

            <el-row>
              <el-col >
                <el-form-item label="用药依从性"  >
                  <el-radio-group v-model="editForm.medCompliance" >
                    <el-radio label="1 按医嘱规律用药"></el-radio>
                    <el-radio label="2 间断用药"></el-radio>
                    <el-radio label="3 不用药"></el-radio>
                    <el-radio label="4 医嘱勿需用药"></el-radio>
                  </el-radio-group>
                </el-form-item>
              </el-col>
            </el-row>

            <el-row>
              <el-col >
                <el-form-item label="药物不良反应" >
                  <el-radio-group v-model="editForm.badDrugReact" >
                    <el-radio label="1 无"></el-radio>
                    <el-radio label="2 有">
                      <el-input  placeholder="2 有（请输入详情）"></el-input>
                    </el-radio>
                    <el-radio label="3 此项不适用"></el-radio>
                  </el-radio-group>
                </el-form-item>
              </el-col>
            </el-row>

            <el-row>
              <el-col>
                <el-form-item label="治疗效果"  >
                  <el-radio-group v-model="editForm.treatEffect" >
                    <el-radio label="痊愈"></el-radio>
                    <el-radio label="好转"></el-radio>
                    <el-radio label="无变化"></el-radio>
                    <el-radio label="加重"></el-radio>
                    <el-radio label="此项不适用"></el-radio>
                  </el-radio-group>
                </el-form-item>
              </el-col>
            </el-row>


            <el-row>
              <el-col>
                <el-form-item label="是否转诊"   >
                  <el-radio-group v-model="editForm.ifReferral" >
                    <el-radio label="否"></el-radio>
                    <el-radio label="是"></el-radio>
                  </el-radio-group>
                </el-form-item>
              </el-col>
            </el-row>

            <el-row>
              <el-col :span="12">
                <el-form-item label="转诊原因" >
                  <el-input v-model="editForm.referralReason"></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="转诊至机构及科室"  >
                  <el-input v-model="editForm.refToDepart"></el-input>
                </el-form-item>
              </el-col>
            </el-row>

            <el-row>
              <el-col>
                <el-form-item label="用药情况">
                  <el-col :span="8">
                    <el-input v-model="editForm.useDrugCondiA" placeholder="药物1"></el-input>
                  </el-col>
                  <el-col :span="8">
                    <el-input v-model="editForm.useUsageA" >
                      <template slot="prepend">每日（月）</template>
                      <template slot="append">次</template>
                    </el-input>
                  </el-col>
                  <el-col :span="8">
                    <el-input v-model="editForm.useMeasurementA" placeholder="每次计量">
                      <template slot="append">mg</template>
                    </el-input>
                  </el-col>

                  <el-col :span="8">
                    <el-input v-model="editForm.useDrugCondiB" placeholder="药物2"></el-input>
                  </el-col>
                  <el-col :span="8">
                    <el-input v-model="editForm.useUsageB" >
                      <template slot="prepend">每日（月）</template>
                      <template slot="append">次</template>
                    </el-input>
                  </el-col>
                  <el-col :span="8">
                    <el-input v-model="editForm.useMeasurementB" placeholder="每次计量">
                      <template slot="append">mg</template>
                    </el-input>
                  </el-col>

                  <el-col :span="8">
                    <el-input v-model="editForm.useDrugCondiC" placeholder="药物3"></el-input>
                  </el-col>
                  <el-col :span="8">
                    <el-input v-model="editForm.useUsageC" >
                      <template slot="prepend">每日（月）</template>
                      <template slot="append">次</template>
                    </el-input>
                  </el-col>
                  <el-col :span="8">
                    <el-input v-model="editForm.useMeasurementC" placeholder="每次计量">
                      <template slot="append">mg</template>
                    </el-input>
                  </el-col>
                </el-form-item>
              </el-col>
            </el-row>


            <el-row>
              <el-col>
                <el-form-item label="用药指导">
                  <el-col :span="8">
                    <el-input v-model="editForm.guideUseDrugA" placeholder="药物1"></el-input>
                  </el-col>
                  <el-col :span="8">
                    <el-input v-model="editForm.guideUsageA" >
                      <template slot="prepend">每日（月）</template>
                      <template slot="append">次</template>
                    </el-input>
                  </el-col>
                  <el-col :span="8">
                    <el-input v-model="editForm.guideMeasurementA" placeholder="每次计量">
                      <template slot="append">mg</template>
                    </el-input>
                  </el-col>

                  <el-col :span="8">
                    <el-input v-model="editForm.guideUseDrugB" placeholder="药物2"></el-input>
                  </el-col>
                  <el-col :span="8">
                    <el-input v-model="editForm.guideUsageB" >
                      <template slot="prepend">每日（月）</template>
                      <template slot="append">次</template>
                    </el-input>
                  </el-col>
                  <el-col :span="8">
                    <el-input v-model="editForm.guideMeasurementB" placeholder="每次计量">
                      <template slot="append">mg</template>
                    </el-input>
                  </el-col>

                  <el-col :span="8">
                    <el-input v-model="editForm.guideUseDrugC" placeholder="药物3"></el-input>
                  </el-col>
                  <el-col :span="8">
                    <el-input v-model="editForm.guideUsageC" >
                      <template slot="prepend">每日（月）</template>
                      <template slot="append">次</template>
                    </el-input>
                  </el-col>
                  <el-col :span="8">
                    <el-input v-model="editForm.guideMeasurementC" placeholder="每次计量">
                      <template slot="append">mg</template>
                    </el-input>
                  </el-col>

                </el-form-item>
              </el-col>
            </el-row>

            <el-row>
              <el-col>
                <el-form-item label="康复措施">
                  <el-checkbox-group v-model="editForm.rehaMeasure" style=" width: 100%;">
                    <el-checkbox v-for="item in rehaMeasure" :key="item.value" :name="type" :label="item.label" :value="item.value"></el-checkbox>
                  </el-checkbox-group>
                </el-form-item>
              </el-col>
            </el-row>

            <el-row>
              <el-col>
                <el-form-item label="本次随访分类">
                  <el-radio-group v-model="editForm.thisClassifi">
                    <el-radio label="1 不稳定"></el-radio>
                    <el-radio label="2 基本稳定"></el-radio>
                    <el-radio label="3 稳定"></el-radio>
                  </el-radio-group>
                </el-form-item>
              </el-col>
            </el-row>


            <el-row>
              <el-col :span="12">
                <el-form-item label="下次随访日期" >
                  <el-date-picker type="date" placeholder="选择日期" v-model="editForm.nextDate" style="width: 100%;"></el-date-picker>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="医生签字"  >
                  <el-input v-model="editForm.docSign"></el-input>
                </el-form-item>
              </el-col>
            </el-row>


      </div>
    </el-form>
    <div slot="footer" class="dialog-footer" align="center">
      <el-button @click="conprint()">打印</el-button>
      <el-button v-if="dialogStatus=='create'" type="primary" @click="createData">添加</el-button>
      <el-button v-else type="primary" v-if="edit" @click="createData">新增修改</el-button>
      <el-button v-else type="primary" @click="updateData" v-if="edit">修改</el-button>
      <el-button @click.native="dialogFormVisible=false">取消</el-button>
    </div>
  </el-dialog>



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
            <el-form-item label="本次随访形式" label-width="160px">
              <el-input v-model="filters.formFollow" placeholder="本次随访形式"></el-input>
            </el-form-item>
          </el-col>
          <el-col span="12">
            <el-form-item label="若失访原因" label-width="160px">
              <el-input v-model="filters.lostReason" placeholder="若失访原因"></el-input>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col span="12">
            <el-form-item label="危险性评估" label-width="160px">
              <el-input v-model="filters.riskAssess" placeholder="危险性评估"></el-input>
            </el-form-item>
          </el-col>
          <el-col span="12">
            <el-form-item label="是否转诊" label-width="160px">
              <el-input v-model="filters.ifReferral" placeholder="是否转诊"></el-input>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col span="12">
            <el-form-item label="本次随分类" label-width="160px">
              <el-input v-model="filters.thisClassifi" placeholder="本次随分类"></el-input>
            </el-form-item>
          </el-col>
        </el-row>

        <!--<el-row>-->
          <!--<el-col span="12">-->
            <!--<el-form-item label="随访日期" label-width="160px">-->
              <!--<el-date-picker type="date" placeholder="随访日期" v-model="filters.follDate" style="width: 100%;"></el-date-picker>-->
            <!--</el-form-item>-->
          <!--</el-col>-->
          <!--<el-col span="12">-->
            <!--<el-form-item label="下次随访日期" label-width="160px">-->
              <!--<el-date-picker type="date" placeholder="下次随访日期" v-model="filters.nextDate" style="width: 100%;"></el-date-picker>-->
            <!--</el-form-item>-->
          <!--</el-col>-->
        <!--</el-row>-->



        <el-row>
          <el-col :span="12">
            <el-form-item label="随访日期范围查询"  label-width="160px">
                <el-date-picker type="date" placeholder="随访开始日期" v-model="filters.follDate_s" style="width: 100%;"></el-date-picker>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="——————————"  label-width="160px">
              <el-date-picker type="date" placeholder="随访结束日期" v-model="filters.follDate_e" style="width: 100%;"></el-date-picker>
            </el-form-item>
          </el-col>
        </el-row>



      </el-form>
      <div slot="footer" class="dialog-footer" align="center">
        <el-button type="primary" v-on:click="searchs" style="background-color:#4db2ee;border-color:transparent" icon="el-icon-search">查询</el-button>
      </div>
    </el-dialog>
  </section>
</template>



<style scoped>

</style>

<script>
  import util from '@/utils/table.js'
  import {
    getUserListPage,
    removeUser,
    batchRemoveUser,
    editUser,
    addUser
  } from '@/api/SevereMentalFollow'
  import city from '@/api/city.js'
  export default {
    data() {
      return {
        radio2: 1,
        edit: true,
        tableHeight: window.innerHeight - 220,
        options: city.city(),
        Death: [
          { value: '躯体疾病', label: '躯体疾病',
            children: [
              { value: '传染病和寄生虫病', label: '传染病和寄生虫病' },
              { value: '肿瘤', label: '肿瘤' },
              { value: '心脏病', label: '心脏病' },
              { value: '脑血管病', label: '脑血管病' },
              { value: '呼吸系统疾病', label: '呼吸系统疾病' },
              { value: '消化系统疾病', label: '消化系统疾病' },
              { value: '其他疾病', label: '其他疾病' },
              { value: '不详', label: '不详' }
            ]
          },
          { value: '自杀', label: '自杀' },
          { value: '他杀', label: '他杀' },
          { value: '意外', label: '意外' },
          { value: '精神疾病相关并发症', label: '精神疾病相关并发症' },
          { value: '其他', label: '其他' }
        ],
        selectedOptions2: [],
        currentSymptoms: [
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
        dangerBeha: [
          { value: '轻度滋事', label: '轻度滋事' },
          { value: '肇事', label: '肇事' },
          { value: '肇祸', label: '肇祸' },
          { value: '其他危险行为', label: '其他危险行为' },
          { value: '自杀', label: '自杀' },
          { value: '自杀未遂', label: '自杀未遂' },
          { value: '无', label: '无' }
        ],
        rehaMeasure: [
          { value: '生活劳动能力', label: '生活劳动能力' },
          { value: '职业训练', label: '职业训练' },
          { value: '学习能力', label: '学习能力' },
          { value: '社会交往能力', label: '社会交往能力' },
          { value: '其他', label: '其他' }
        ],
        causeDeath: [],
        area: [],
        dialogStatus: '',
        textMap: {
          update: '编辑',
          create: '新增',
          search: '查询',
          detail: '详细'
        },
        dialogFormVisible: false,
        dialogFormVisible2: false,
        filters: {
          peopleId: '',
          name: '',
          formFollow: '',
          lostReason: '',
          riskAssess: '',
          ifReferral: '',
          thisClassifi: '',
          docSign: '',
          follDate: '',
          nextDate: '',
          follDate_s: '',
          follDate_e: ''
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
          follDate: '',
          formFollow: '',
          lostReason: '',
          dateDeath: '',
          causeDeath: '',
          riskAssess: '',
          currentSymptoms: [],
          selfKnowLedge: '',
          sleepCondition: '',
          dietCondition: '',
          pelLifeCondition: '',
          houseWork: '',
          proAndWork: '',
          abilityLearn: '',
          socialCommu: '',
          dangerBeha: [],
          twoLockCondition: '',
          twoInHospCondition: '',
          outHospitalDate: '',
          laborExamin: '',
          medCompliance: '',
          badDrugReact: '',
          treatEffect: '',
          ifReferral: '',
          referralReason: '',
          refToDepart: '',
          useDrugCondiA: '',
          useUsageA: '',
          useMeasurementA: '',
          useDrugCondiB: '',
          useUsageB: '',
          useMeasurementB: '',
          useDrugCondiC: '',
          useUsageC: '',
          useMeasurementC: '',
          guideUseDrugA: '',
          guideUsageA: '',
          guideMeasurementA: '',
          guideUseDrugB: '',
          guideUsageB: '',
          guideMeasurementB: '',
          guideUseDrugC: '',
          guideUsageC: '',
          guideMeasurementC: '',
          rehaMeasure: [],
          thisClassifi: '',
          nextDate: '',
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
          '    <h2 style="width:794px;text-align:center">严重精神障碍患者随访服务记录表</h2>  \n' +
          '    <span style="margin-left:34px;">姓名:{{name}}</span>\n' +
          '    <span style="margin-left:510px;">NO:{{id}}</span> \n' +
          '    </div>\n' +
          '  <table id="table"  border="1"  cellspacing="0" bordercolor="#000000" style="width:730px;margin-left:30px;margin-top:5px;border-collapse:collapse;"  width = "80%">\n' +
          '    <tbody>\n' +
          '\t\n' +
          '\t  <tr>\n' +
          '        <td style="word-wrap:break-word;width: 20%;text-align:center">随访日期</td>\n' +
          '        <td colspan=\'5\'>{{follDate}}</td>\n' +
          '      </tr>\n' +
          '\t  <tr>\n' +
          '        <td style="word-wrap:break-word;width: 20%;text-align:center">本次随访形式</td>\n' +
          '        <td colspan=\'5\'>{{formFollow}}</td>\n' +
          '      </tr>\n' +
          '\t  <tr>\n' +
          '        <td style="word-wrap:break-word;width: 20%;text-align:center">若失访，原因</td>\n' +
          '        <td colspan=\'5\'>{{lostReason}}</td>\n' +
          '      </tr>\n' +
          '\t <tr >\n' +
          '\t\t<td style="word-wrap:break-word;width: 20%;text-align:center" rowspan=2>如死亡，日期和原因</td>\n' +
          '\t\t<td style="word-wrap:break-word;width: 20%;text-align:center">死亡日期</td>\n' +
          '\t\t<td colspan=\'5\'>{{dateDeath}}</td>\n' +
          '\t  </tr>\n' +
          '\t  <tr>\n' +
          '\t    <td  style="word-wrap:break-word;width: 20%;text-align:center">死亡原因</td>\n' +
          '\t\t<td colspan=\'5\'>{{causeDeath}}</td>\n' +
          '\t  </tr>\n' +
          '\t  <tr>\n' +
          '\t    <td  style="word-wrap:break-word;width: 20%;text-align:center">危险性评估</td>\n' +
          '\t\t<td colspan=\'5\'>{{riskAssess}}</td>\n' +
          '\t  </tr>\n' +
          '\t  <tr>\n' +
          '        <td style="width: 20%;text-align:center">目前症状</td>\n' +
          '        <td colspan=\'5\' style="width: 20%;text-align:left">{{currentSymptoms}}</td>\n' +
          '      </tr>\n' +
          '\t  <tr>\n' +
          '        <td style="word-wrap:break-word;width: 20%;text-align:center">自知力</td>\n' +
          '        <td colspan=\'5\'>{{selfKnowLedge}}</td>\n' +
          '      </tr>\n' +
          '\t  <tr>\n' +
          '        <td style="word-wrap:break-word;width: 20%;text-align:center">睡眠情况</td>\n' +
          '        <td colspan=\'5\'>{{sleepCondition}}</td>\n' +
          '      </tr>\n' +
          '\t  <tr>\n' +
          '        <td style="word-wrap:break-word;width: 20%;text-align:center">饮食情况</td>\n' +
          '        <td colspan=\'5\'>{{dietCondition}}</td>\n' +
          '      </tr>\n' +
          '\t   <tr >\n' +
          '\t\t<td style="word-wrap:break-word;width: 20%;text-align:center" rowspan=5>社会功能情况</td>\n' +
          '\t\t<td style="word-wrap:break-word;width: 20%;text-align:center">个人生活料理</td>\n' +
          '        <td colspan=\'5\'>{{pelLifeCondition}}</td>\n' +
          '\t  </tr>\n' +
          '\t  <tr >\n' +
          '\t\t<td style="word-wrap:break-word;width: 20%;text-align:center">家务劳动</td>\n' +
          '        <td colspan=\'5\'>{{houseWork}}</td>\n' +
          '\t  </tr> \n' +
          '\t  <tr >\n' +
          '\t\t<td style="word-wrap:break-word;width: 20%;text-align:center">生产劳动及工作</td>\n' +
          '        <td colspan=\'5\'>{{proAndWork}}</td>\n' +
          '\t  </tr>\n' +
          '\t  <tr >\n' +
          '\t\t<td style="word-wrap:break-word;width: 20%;text-align:center">学习能力</td>\n' +
          '        <td colspan=\'5\'>{{abilityLearn}}</td>\n' +
          '\t  </tr>\n' +
          '\t  <tr >\n' +
          '\t\t<td style="word-wrap:break-word;width: 20%;text-align:center">社会人际交往</td>\n' +
          '        <td colspan=\'5\'>{{socialCommu}}</td>\n' +
          '\t  </tr>\n' +
          '\t  <tr>\n' +
          '        <td style="width: 20%;text-align:center">危险行为</td>\n' +
          '        <td colspan=\'5\' style="width: 20%;text-align:left">{{dangerBeha}}</td>\n' +
          '      </tr>\n' +
          '\t  <tr >\n' +
          '\t\t<td style="word-wrap:break-word;width: 20%;text-align:center">两次随访期间关锁情况</td>\n' +
          '        <td colspan=\'5\'>{{twoLockCondition}}</td>\n' +
          '\t  </tr>\n' +
          '\t   <tr >\n' +
          '\t\t<td style="word-wrap:break-word;width: 20%;text-align:center" rowspan=1>两次随访期间住院情况</td>\n' +
          '        <td colspan=\'5\'>{{twoInHospCondition}}</td>\n' +
          '\t  </tr>\n' +
          '\t  <tr >\n' +
          '\t\t<td style="word-wrap:break-word;width: 20%;text-align:center" >实验室检查</td>\n' +
          '        <td colspan=\'5\'>{{laborExamin}}</td>\n' +
          '\t  </tr>\n' +
          '\t  <tr >\n' +
          '\t\t<td style="word-wrap:break-word;width: 20%;text-align:center" >用药依从性</td>\n' +
          '        <td colspan=\'5\'>{{medCompliance}}</td>\n' +
          '\t  </tr>\n' +
          '\t  <tr >\n' +
          '\t\t<td style="word-wrap:break-word;width: 20%;text-align:center" >药物不良反应</td>\n' +
          '        <td colspan=\'5\'>{{badDrugReact}}</td>\n' +
          '\t  </tr>\n' +
          '\t\t<td style="word-wrap:break-word;width: 20%;text-align:center" >治疗效果</td>\n' +
          '        <td colspan=\'5\'>{{treatEffect}}</td>\n' +
          '\t  </tr>\n' +
          '\t  <tr >\n' +
          '\t\t<td style="word-wrap:break-word;width: 20%;text-align:center" rowspan=3>是否转诊</td>\n' +
          '\t\t<td colspan=\'5\'>{{ifReferral}}</td>\n' +
          '\t  </tr>\n' +
          '\t   <tr >\n' +
          '\t\t<td colspan=\'5\'>转诊原因：{{referralReason}}</td>\n' +
          '\t  </tr>\n' +
          '\t  <tr >\n' +
          '\t\t<td colspan=\'5\'>转诊至机构及科室：{{refToDepart}}</td>\n' +
          '\t  </tr>\n' +
      ' <tr>\n' +
          '        <td style="width: 20%;text-align:center" rowspan=3>用药情况</td>\n' +
          '        <td colspan=\'2\'>药物1:{{useDrugCondiA}}</td> \n' +
          '\t\t    <td colspan=\'1\'>用法：每日(月){{useUsageA}}次 </td>\n' +
          '\t\t    <td >每次计量{{useMeasurementA}}mg</td>\n' +
          '      </tr>\n' +
          '\t  <tr>\n' +
          '        <td colspan=\'2\'>药物2:{{useDrugCondiB}}</td> \n' +
          '\t\t    <td colspan=\'1\'>用法：每日(月){{useUsageB}}次 </td>\n' +
          '\t\t    <td >每次计量{{useMeasurementB}}mg</td>\n' +
          '      </tr>\n' +
          '\t  <tr>\n' +
          '        <td colspan=\'2\'>药物3:{{useDrugCondiC}}</td> \n' +
          '\t\t    <td colspan=\'1\'>用法：每日(月){{useUsageC}}次 </td>\n' +
          '\t\t    <td >每次计量{{useMeasurementC}}mg</td>\n' +
          '      </tr>\n' +
         ' <tr>\n' +
         '        <td style="width: 20%;text-align:center" rowspan=3>用药指导</td>\n' +
         '        <td colspan=\'2\'>药物1:{{guideUseDrugA}}</td> \n' +
          '\t\t    <td colspan=\'1\'>用法：每日(月){{guideUsageA}}次 </td>\n' +
          '\t\t    <td >每次计量{{guideMeasurementA}}mg</td>\n' +
         '      </tr>\n' +
         '\t  <tr>\n' +
         '        <td colspan=\'2\'>药物2:{{guideUseDrugB}}</td> \n' +
          '\t\t    <td colspan=\'1\'>用法：每日(月){{guideUsageB}}次 </td>\n' +
          '\t\t    <td >每次计量{{guideMeasurementB}}mg</td>\n' +
         '      </tr>\n' +
         '\t  <tr>\n' +
         '        <td colspan=\'2\'>药物3:{{guideUseDrugC}}</td> \n' +
          '\t\t    <td colspan=\'1\'>用法：每日(月){{guideUsageC}}次 </td>\n' +
          '\t\t    <td >每次计量{{guideMeasurementC}}mg</td>\n' +
         '      </tr>\n' +
          '\t  <tr>\n' +
          '        <td style="width: 20%;text-align:center">康复措施</td>\n' +
          '        <td colspan=\'5\' style="width: 20%;text-align:left">{{rehaMeasure}}</td>\n' +
          '      </tr>\n' +
          '\t  <tr>\n' +
          '        <td style="word-wrap:break-word;width: 20%;text-align:center">本次随访分类</td>\n' +
          '        <td colspan=\'5\'>{{thisClassifi}}</td>\n' +
          '      </tr>\n' +
          '\t  <tr>\n' +
          '        <td style="width: 20%;text-align:center">下次随访日期</td>\n' +
          '        <td colspan=\'2\'>{{nextDate}}</td>\n' +
          '        <td style="width: 30%;text-align:center">随访医生签字</td>\n' +
          '        <td colspan=\'2\'>{{docSign}}</td>\n' +
          '      </tr>\n' +
          '\t  \n' +
          '\t \n' +
          '    </tbody> \n' +
          '    \n' +
          '     \n' +
          '\n' +
          '  </table>\n' +
          '</div>'
        str = str.replace(/{{name}}/, this.editForm.name ? this.editForm.name : '')
        str = str.replace(/{{id}}/, this.editForm.id ? this.editForm.id : '')
        str = str.replace(/{{follDate}}/, this.editForm.follDate ? this.editForm.follDate : '')
        str = str.replace(/{{formFollow}}/, this.editForm.formFollow ? this.editForm.formFollow : '')
        str = str.replace(/{{lostReason}}/, this.editForm.lostReason ? this.editForm.lostReason : '')
        str = str.replace(/{{dateDeath}}/, this.editForm.dateDeath ? this.editForm.dateDeath : '')
        str = str.replace(/{{causeDeath}}/, this.editForm.causeDeath ? this.editForm.causeDeath : '')
        str = str.replace(/{{riskAssess}}/, this.editForm.riskAssess ? this.editForm.riskAssess : '')
        str = str.replace(/{{currentSymptoms}}/, this.editForm.currentSymptoms ? this.editForm.currentSymptoms : '')
        str = str.replace(/{{selfKnowLedge}}/, this.editForm.selfKnowLedge ? this.editForm.selfKnowLedge : '')
        str = str.replace(/{{sleepCondition}}/, this.editForm.sleepCondition ? this.editForm.sleepCondition : '')
        str = str.replace(/{{dietCondition}}/, this.editForm.dietCondition ? this.editForm.dietCondition : '')
        str = str.replace(/{{pelLifeCondition}}/, this.editForm.pelLifeCondition ? this.editForm.pelLifeCondition : '')
        str = str.replace(/{{houseWork}}/, this.editForm.houseWork ? this.editForm.houseWork : '')
        str = str.replace(/{{proAndWork}}/, this.editForm.proAndWork ? this.editForm.proAndWork : '')
        str = str.replace(/{{abilityLearn}}/, this.editForm.abilityLearn ? this.editForm.abilityLearn : '')
        str = str.replace(/{{socialCommu}}/, this.editForm.socialCommu ? this.editForm.socialCommu : '')
        str = str.replace(/{{dangerBeha}}/, this.editForm.dangerBeha ? this.editForm.dangerBeha : '')
        str = str.replace(/{{twoLockCondition}}/, this.editForm.twoLockCondition ? this.editForm.twoLockCondition : '')
        str = str.replace(/{{twoInHospCondition}}/, this.editForm.twoInHospCondition ? this.editForm.twoInHospCondition : '')
        str = str.replace(/{{laborExamin}}/, this.editForm.laborExamin ? this.editForm.laborExamin : '')
        str = str.replace(/{{medCompliance}}/, this.editForm.medCompliance ? this.editForm.medCompliance : '')
        str = str.replace(/{{badDrugReact}}/, this.editForm.badDrugReact ? this.editForm.badDrugReact : '')
        str = str.replace(/{{treatEffect}}/, this.editForm.treatEffect ? this.editForm.treatEffect : '')
        str = str.replace(/{{ifReferral}}/, this.editForm.ifReferral ? this.editForm.ifReferral : '')
        str = str.replace(/{{referralReason}}/, this.editForm.referralReason ? this.editForm.referralReason : '')
        str = str.replace(/{{refToDepart}}/, this.editForm.refToDepart ? this.editForm.refToDepart : '')
        str = str.replace(/{{useDrugCondiA}}/, this.editForm.useDrugCondiA ? this.editForm.useDrugCondiA : '')
        str = str.replace(/{{useUsageA}}/, this.editForm.useUsageA ? this.editForm.useUsageA : '')
        str = str.replace(/{{useMeasurementA}}/, this.editForm.useMeasurementA ? this.editForm.useMeasurementA : '')
        str = str.replace(/{{useDrugCondiB}}/, this.editForm.useDrugCondiB ? this.editForm.useDrugCondiB : '')
        str = str.replace(/{{useUsageB}}/, this.editForm.useUsageB ? this.editForm.useUsageB : '')
        str = str.replace(/{{useMeasurementB}}/, this.editForm.useMeasurementB ? this.editForm.useMeasurementB : '')
        str = str.replace(/{{useDrugCondiC}}/, this.editForm.useDrugCondiC ? this.editForm.useDrugCondiC : '')
        str = str.replace(/{{useUsageC}}/, this.editForm.useUsageC ? this.editForm.useUsageC : '')
        str = str.replace(/{{useMeasurementC}}/, this.editForm.useMeasurementC ? this.editForm.useMeasurementC : '')
        str = str.replace(/{{guideUseDrugA}}/, this.editForm.guideUseDrugA ? this.editForm.guideUseDrugA : '')
        str = str.replace(/{{guideUsageA}}/, this.editForm.guideUsageA ? this.editForm.guideUsageA : '')
        str = str.replace(/{{guideMeasurementA}}/, this.editForm.guideMeasurementA ? this.editForm.guideMeasurementA : '')
        str = str.replace(/{{guideUseDrugB}}/, this.editForm.guideUseDrugB ? this.editForm.guideUseDrugB : '')
        str = str.replace(/{{guideUsageB}}/, this.editForm.guideUsageB ? this.editForm.guideUsageB : '')
        str = str.replace(/{{guideMeasurementB}}/, this.editForm.guideMeasurementB ? this.editForm.guideMeasurementB : '')
        str = str.replace(/{{guideUseDrugC}}/, this.editForm.guideUseDrugC ? this.editForm.guideUseDrugC : '')
        str = str.replace(/{{guideUsageC}}/, this.editForm.guideUsageC ? this.editForm.guideUsageC : '')
        str = str.replace(/{{guideMeasurementC}}/, this.editForm.guideMeasurementC ? this.editForm.guideMeasurementC : '')
        str = str.replace(/{{rehaMeasure}}/, this.editForm.rehaMeasure ? this.editForm.rehaMeasure : '')
        str = str.replace(/{{thisClassifi}}/, this.editForm.thisClassifi ? this.editForm.thisClassifi : '')
        str = str.replace(/{{nextDate}}/, this.editForm.nextDate ? this.editForm.nextDate : '')
        str = str.replace(/{{docSign}}/, this.editForm.docSign ? this.editForm.docSign : '')
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

      handleCurrentChange(val) {
        this.page = val
        this.getUsers()
      },
      // 获取用户列表
      getUsers() {
        this.filters.follDate =
          !this.filters.follDate || this.filters.follDate === ''
            ? null
            : util.formatDate.format(new Date(this.filters.follDate), 'yyyy-MM-dd')
        this.filters.nextDate =
          !this.filters.nextDate || this.filters.nextDate === ''
            ? null
            : util.formatDate.format(new Date(this.filters.nextDate), 'yyyy-MM-dd')
        this.filters.follDate_s =
          !this.filters.follDate_s || this.filters.follDate_s === ''
            ? null
            : util.formatDate.format(new Date(this.filters.follDate_s), 'yyyy-MM-dd')
        this.filters.follDate_e =
          !this.filters.follDate_e || this.filters.follDate_e === ''
            ? null
            : util.formatDate.format(new Date(this.filters.follDate_e), 'yyyy-MM-dd')
        const para = {
          page: this.page,
          size: 20,
          filters: {
            peopleId: this.filters.peopleId,
            name: this.filters.name,
            formFollow: this.filters.formFollow,
            lostReason: this.filters.lostReason,
            riskAssess: this.filters.riskAssess,
            ifReferral: this.filters.ifReferral,
            thisClassifi: this.filters.thisClassifi,
            docSign: this.filters.docSign,
            follDate: this.filters.follDate,
            nextDate: this.filters.nextDate,
            follDate_s: this.filters.follDate_s,
            follDate_e: this.filters.follDate_e
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
          formFollow: '',
          lostReason: '',
          riskAssess: '',
          ifReferral: '',
          thisClassifi: '',
          docSign: '',
          follDate: '',
          nextDate: '',
          follDate_s: '',
          follDate_e: ''
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
        this.causeDeath = []
        this.area = []
        this.dialogStatus = 'update'
        this.dialogFormVisible = true
        this.editForm = Object.assign({}, row)
        this.editForm.currentSymptoms = this.editForm.currentSymptoms.split(',')
        this.editForm.dangerBeha = this.editForm.dangerBeha.split(',')
        this.editForm.rehaMeasure = this.editForm.rehaMeasure.split(',')
        this.causeDeath = this.editForm.causeDeath.split('/')
        this.area = this.editForm.area.split('/')
        this.edit = true
      },
      // 查询框
      findbtn() {
        this.dialogStatus = 'search'
        this.dialogFormVisible2 = true
        this.findForm = {
          peopleId: '',
          name: '',
          formFollow: '',
          lostReason: '',
          riskAssess: '',
          ifReferral: '',
          thisClassifi: '',
          docSign: '',
          follDate: '',
          nextDate: '',
          follDate_s: '',
          follDate_e: ''
        }
      },
      handleChange(value) {
        console.log(value)
      },
      // 查询
      searchs() {
        this.page = 1
        this.getUsers()
      },
      // 显示新增界面
      handleAdd() {
        this.dialogStatus = 'create'
        this.dialogFormVisible = true
        this.edit = false
        this.editForm = {
          peopleId: '',
          name: '',
          area: '',
          follDate: '',
          formFollow: '',
          lostReason: '',
          dateDeath: '',
          causeDeath: '',
          riskAssess: '',
          currentSymptoms: [],
          selfKnowLedge: '',
          sleepCondition: '',
          dietCondition: '',
          pelLifeCondition: '',
          houseWork: '',
          proAndWork: '',
          abilityLearn: '',
          socialCommu: '',
          dangerBeha: [],
          twoLockCondition: '',
          twoInHospCondition: '',
          outHospitalDate: '',
          laborExamin: '',
          medCompliance: '',
          badDrugReact: '',
          treatEffect: '',
          ifReferral: '',
          referralReason: '',
          refToDepart: '',
          useDrugCondiA: '',
          useUsageA: '',
          useMeasurementA: '',
          useDrugCondiB: '',
          useUsageB: '',
          useMeasurementB: '',
          useDrugCondiC: '',
          useUsageC: '',
          useMeasurementC: '',
          guideUseDrugA: '',
          guideUsageA: '',
          guideMeasurementA: '',
          guideUseDrugB: '',
          guideUsageB: '',
          guideMeasurementB: '',
          guideUseDrugC: '',
          guideUsageC: '',
          guideMeasurementC: '',
          rehaMeasure: [],
          thisClassifi: '',
          nextDate: '',
          docSign: '',
          creator: this.$store.getters.name,
          createDate: '',
          modifier: '',
          modifyDate: ''
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
                para.follDate =
                  !para.follDate || para.follDate === ''
                    ? null
                    : util.formatDate.format(new Date(para.follDate), 'yyyy-MM-dd')
                para.dateDeath =
                  !para.dateDeath || para.dateDeath === ''
                    ? null
                    : util.formatDate.format(new Date(para.dateDeath), 'yyyy-MM-dd')
                para.outHospitalDate =
                  !para.outHospitalDate || para.outHospitalDate === ''
                    ? null
                    : util.formatDate.format(new Date(para.outHospitalDate), 'yyyy-MM-dd')
                para.nextDate =
                  !para.nextDate || para.nextDate === ''
                    ? null
                    : util.formatDate.format(new Date(para.nextDate), 'yyyy-MM-dd')
                para.causeDeath = ''
                for (const itm of this.causeDeath) {
                  para.causeDeath += itm
                  para.causeDeath += '/'
                }
                para.causeDeath = para.causeDeath.substr(0, para.causeDeath.length - 1)
                para.area = ''
                for (const itm of this.area) {
                  para.area += itm
                  para.area += '/'
                }
                para.area = para.area.substr(0, para.area.length - 1)
                para.currentSymptoms = ''
                for (const itm of this.editForm.currentSymptoms) {
                  if (itm !== '') {
                    para.currentSymptoms += itm
                    para.currentSymptoms += ','
                  }
                }
                para.currentSymptoms = para.currentSymptoms.substr(0, para.currentSymptoms.length - 1)
                para.dangerBeha = ''
                for (const itm of this.editForm.dangerBeha) {
                  if (itm !== '') {
                    para.dangerBeha += itm
                    para.dangerBeha += ','
                  }
                }
                para.dangerBeha = para.dangerBeha.substr(0, para.dangerBeha.length - 1)
                para.rehaMeasure = ''
                for (const itm of this.editForm.rehaMeasure) {
                  if (itm !== '') {
                    para.rehaMeasure += itm
                    para.rehaMeasure += ','
                  }
                }
                para.rehaMeasure = para.rehaMeasure.substr(0, para.rehaMeasure.length - 1)
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
                para.follDate =
                  !para.follDate || para.follDate === ''
                    ? null
                    : util.formatDate.format(new Date(para.follDate), 'yyyy-MM-dd')
                para.dateDeath =
                  !para.dateDeath || para.dateDeath === ''
                    ? null
                    : util.formatDate.format(new Date(para.dateDeath), 'yyyy-MM-dd')
                para.outHospitalDate =
                  !para.outHospitalDate || para.outHospitalDate === ''
                    ? null
                    : util.formatDate.format(new Date(para.outHospitalDate), 'yyyy-MM-dd')
                para.nextDate =
                  !para.nextDate || para.nextDate === ''
                    ? null
                    : util.formatDate.format(new Date(para.nextDate), 'yyyy-MM-dd')
                para.causeDeath = ''
                for (const itm of this.causeDeath) {
                  para.causeDeath += itm
                  para.causeDeath += '/'
                }
                para.causeDeath = para.causeDeath.substr(0, para.causeDeath.length - 1)
                para.area = ''
                for (const itm of this.area) {
                  para.area += itm
                  para.area += '/'
                }
                para.area = para.area.substr(0, para.area.length - 1)
                para.currentSymptoms = ''
                for (const itm of this.editForm.currentSymptoms) {
                  if (itm !== '') {
                    para.currentSymptoms += itm
                    para.currentSymptoms += ','
                  }
                }
                para.currentSymptoms = para.currentSymptoms.substr(0, para.currentSymptoms.length - 1)
                para.dangerBeha = ''
                for (const itm of this.editForm.dangerBeha) {
                  if (itm !== '') {
                    para.dangerBeha += itm
                    para.dangerBeha += ','
                  }
                }
                para.dangerBeha = para.dangerBeha.substr(0, para.dangerBeha.length - 1)
                para.rehaMeasure = ''
                for (const itm of this.editForm.rehaMeasure) {
                  if (itm !== '') {
                    para.rehaMeasure += itm
                    para.rehaMeasure += ','
                  }
                }
                para.rehaMeasure = para.rehaMeasure.substr(0, para.rehaMeasure.length - 1)
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
