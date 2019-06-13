<template>
  <section class="app-container">

    <!--工具条-->
    <el-col :span="24" class="toolbar" >
      <el-form :inline="true" :model="filters" @submit.native.prevent>
        <el-form-item>
          <el-input v-model="filters.name" placeholder="姓名" ></el-input>
        </el-form-item>
        <el-form-item>
          <el-input v-model="filters.idnumber" placeholder="身份证号"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" v-on:click="search" style="background-color: #4db2ee;border-color: transparent" icon="el-icon-search">查询</el-button>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="handleAdd" style="background-color: #05cc96;border-color: transparent"  icon="el-icon-circle-plus-outline" >新增</el-button>
        </el-form-item>
        <!--<el-form-item>-->
          <!--<el-button type="primary" v-on:click="cz" style="background-color: #4db2ee;border-color: transparent" icon="el-icon-back">重置</el-button>-->
        <!--</el-form-item>-->
      </el-form>
    <!--</el-col>-->
    <!--<el-col :span="2" class="toolbar" style="padding-bottom: 0px;" align="right">-->
      <!--<el-form :inline="true" :model="filters" @submit.native.prevent>-->
        <!--&lt;!&ndash;<el-form-item>&ndash;&gt;-->
          <!--&lt;!&ndash;<el-input v-model="filters.name" placeholder="姓名"></el-input>&ndash;&gt;-->
        <!--&lt;!&ndash;</el-form-item>&ndash;&gt;-->
        <!--&lt;!&ndash;<el-form-item>&ndash;&gt;-->
          <!--&lt;!&ndash;<el-input v-model="filters.idnumber" placeholder="身份证号"></el-input>&ndash;&gt;-->
        <!--&lt;!&ndash;</el-form-item>&ndash;&gt;-->
        <!--<el-form-item>-->
          <!--<el-button type="primary" @click="handleAdd" style="-->
          <!--background-color: #05cc96;border-color: transparent"  icon="el-icon-circle-plus-outline" >新增</el-button>-->
        <!--</el-form-item>-->
        <!--&lt;!&ndash;<el-form-item>&ndash;&gt;-->
          <!--&lt;!&ndash;<el-button @click="conprint()" style="color:#4DB2EE;margin-right:40px;">打印</el-button>&ndash;&gt;-->
        <!--&lt;!&ndash;</el-form-item>&ndash;&gt;-->
      <!--</el-form>-->
    </el-col>
    <!--列表-->
    <div id="print">
    <el-table :data="users" :height="tableHeight" highlight-current-row @selection-change="selsChange"  border style="width: 100%;"@row-dblclick="handleRowChange">
      <!--<el-table-column type="selection"  min-width="60">-->
      <!--</el-table-column>-->
      <el-table-column type="index"  min-width="60">
      </el-table-column>
      <el-table-column prop="name" label="姓名"  min-width="80" align="center">
      </el-table-column>
      <el-table-column prop="id" label="编号"  min-width="60" align="center">
      </el-table-column>
      <el-table-column prop="Count"v-if="false" label="次数"  min-width="60" align="center">
      </el-table-column>
      <el-table-column prop="blood" v-if="false" label="血型"  min-width="60" align="center">
      </el-table-column>
      <el-table-column prop="symptoms"label="症状"  min-width="80" align="center">
      </el-table-column>
      <el-table-column prop="footArteyPulse"v-if="false" label="足背动脉搏动" min-width="120" align="center">
      </el-table-column>
      <el-table-column prop="pressure"v-if="false" label="血压(mmHg)" min-width="120" align="center">
      </el-table-column>
      <el-table-column prop="weight"v-if="false" label="体重(Kg)" min-width="100" align="center">
      </el-table-column>
      <el-table-column prop="bodyMassIndex"v-if="false" label="体质指数(kg/m²)" min-width="140" align="center">
      </el-table-column>
      <el-table-column prop="dailySmoking"v-if="false" label="日吸烟量(支)" min-width="120" align="center">
      </el-table-column>
      <el-table-column prop="dailyDrinking" v-if="false"label="日饮酒量(两)" min-width="120" align="center">
      </el-table-column>
        <!--<el-col>-->
          <!--<el-table-column prop="exercise1" label="运动(天)" min-width="60" align="center">-->
          <!--</el-table-column>-->
        <!--</el-col>-->
        <!--<el-col>-->
          <!--<el-table-column prop="exercise2" label="运动(天)" min-width="60" align="center">-->
          <!--</el-table-column>-->
        <!--</el-col>-->
      <el-table-column prop="exercise" v-if="false"label="运动" min-width="60" align="center">
      </el-table-column>
      <el-table-column prop="stapleFood" v-if="false" label="主食(克/天)" min-width="120" align="center">
      </el-table-column>
      <el-table-column prop="other" v-if="false" label="其他" min-width="80" align="center">
      </el-table-column>
      <el-table-column prop="psychologicalAdjust" v-if="false" label="心理调整" min-width="100" align="center">
      </el-table-column>
      <el-table-column prop="complianceBehavior" v-if="false" label="遵医行为" min-width="100" align="center">
      </el-table-column>
      <el-table-column prop="fastingBlood" v-if="false"label="空腹血糖值(mol/L)" min-width="160" align="center">
      </el-table-column>
      <el-table-column prop="hemoglobin" v-if="false"label="糖化血红蛋白(%)" min-width="150" align="center">
      </el-table-column>
      <el-table-column prop="examinationDate"v-if="false" label="检查日期" min-width="100" align="center">
      </el-table-column>
      <el-table-column prop="diabetescol" label="服药依从性" min-width="110" align="center">
      </el-table-column>
      <el-table-column prop="drugsAdversed" label="药物不良反应" min-width="120" align="center">
      </el-table-column>
      <el-table-column prop="hypoglycemia" label="低血糖反应" min-width="110" align="center">
      </el-table-column>
      <el-table-column prop="followClassification" label="此次随访分类" min-width="120">
      </el-table-column>
      <el-table-column prop="drugName"v-if="false" label="药物名称" min-width="100" align="center">
      </el-table-column>
      <el-table-column prop="usageDosage"v-if="false" label="用法用量" min-width="100" align="center">
      </el-table-column>
      <el-table-column prop="referralCause" label="转诊原因" min-width="100" align="center">
      </el-table-column>
      <el-table-column prop="referralInstitution" label="转诊机构及科别" min-width="140">
      </el-table-column>
      <el-table-column prop="followDate" label="本次随访日期" min-width="120" align="center">
      </el-table-column>
      <el-table-column prop="nextFollowDate" label="下次随访日期" min-width="120" align="center">
      </el-table-column>
      <el-table-column prop="followWay" label="随访方式" min-width="100" align="center">
      </el-table-column>
      <el-table-column prop="doctorSign" label="随访医生签名" min-width="120" align="center">
      </el-table-column>
      <el-table-column label="操作" width="150" align="center">
        <template slot-scope="scope">
          <a  size="14px" @click="handleEdit(scope.$index, scope.row)" style="color:#4DB2EE;margin-right:20px;">编辑</a>
          <a  size="14px" @click="handleDel(scope.$index, scope.row)"style="color:#FB4141;margin-right:20px;">删除</a>
          <!--<a size="14px" @click="conprint(scope.$index, scope.row)" style="color:#4DB2EE;">打印</a>-->
        </template>
      </el-table-column>
    </el-table>
  </div>

    <el-table :data="printItem"  v-if="false" :height="tableHeight" highlight-current-row @selection-change="selsChange"  border style="width: 100%;">
      <!--<el-table-column type="selection"  min-width="60">-->
      <!--</el-table-column>-->
      <el-table-column type="index"  min-width="60">
      </el-table-column>
      <el-table-column prop="name" label="姓名"  min-width="80" align="center">
      </el-table-column>
      <el-table-column prop="id" label="编号"  min-width="60" align="center">
      </el-table-column>
      <el-table-column prop="Count"v-if="false" label="次数"  min-width="60" align="center">
      </el-table-column>
      <el-table-column prop="blood" v-if="false" label="血型"  min-width="60" align="center">
      </el-table-column>
      <el-table-column prop="symptoms"label="症状"  min-width="80" align="center">
      </el-table-column>
      <el-table-column prop="footArteyPulse"v-if="false" label="足背动脉搏动" min-width="120" align="center">
      </el-table-column>
      <el-table-column prop="pressure" v-if="false"label="血压(mmHg)" min-width="120" align="center">
      </el-table-column>
      <el-table-column prop="weight" v-if="false"label="体重(Kg)" min-width="100" align="center">
      </el-table-column>
      <el-table-column prop="bodyMassIndex"v-if="false" label="体质指数(kg/m²)" min-width="140" align="center">
      </el-table-column>
      <el-table-column prop="dailySmoking"v-if="false" label="日吸烟量(支)" min-width="120" align="center">
      </el-table-column>
      <el-table-column prop="dailyDrinking" v-if="false"label="日饮酒量(两)" min-width="120" align="center">
      </el-table-column>
      <!--<el-col>-->
      <!--<el-table-column prop="exercise1" label="运动(天)" min-width="60" align="center">-->
      <!--</el-table-column>-->
      <!--</el-col>-->
      <!--<el-col>-->
      <!--<el-table-column prop="exercise2" label="运动(天)" min-width="60" align="center">-->
      <!--</el-table-column>-->
      <!--</el-col>-->
      <el-table-column prop="exercise" v-if="false"label="运动" min-width="60" align="center">
      </el-table-column>
      <el-table-column prop="stapleFood" v-if="false" label="主食(克/天)" min-width="120" align="center">
      </el-table-column>
      <el-table-column prop="other" v-if="false" label="其他" min-width="80" align="center">
      </el-table-column>
      <el-table-column prop="psychologicalAdjust" v-if="false" label="心理调整" min-width="100" align="center">
      </el-table-column>
      <el-table-column prop="complianceBehavior" v-if="false" label="遵医行为" min-width="100" align="center">
      </el-table-column>
      <el-table-column prop="fastingBlood" v-if="false"label="空腹血糖值(mol/L)" min-width="160" align="center">
      </el-table-column>
      <el-table-column prop="hemoglobin" v-if="false"label="糖化血红蛋白(%)" min-width="150" align="center">
      </el-table-column>
      <el-table-column prop="examinationDate"v-if="false" label="检查日期" min-width="100" align="center">
      </el-table-column>
      <el-table-column prop="diabetescol" label="服药依从性" min-width="110" align="center">
      </el-table-column>
      <el-table-column prop="drugsAdversed" label="药物不良反应" min-width="120" align="center">
      </el-table-column>
      <el-table-column prop="hypoglycemia" label="低血糖反应" min-width="110" align="center">
      </el-table-column>
      <el-table-column prop="followClassification" label="此次随访分类" min-width="120">
      </el-table-column>
      <el-table-column prop="drugName"v-if="false" label="药物名称" min-width="100" align="center">
      </el-table-column>
      <el-table-column prop="usageDosage"v-if="false" label="用法用量" min-width="100" align="center">
      </el-table-column>
      <el-table-column prop="referralCause" label="转诊原因" min-width="100" align="center">
      </el-table-column>
      <el-table-column prop="referralInstitution" label="转诊机构及科别" min-width="140">
      </el-table-column>
      <el-table-column prop="followDate" label="本次随访日期" min-width="120" align="center">
      </el-table-column>
      <el-table-column prop="nextFollowDate" label="下次随访日期" min-width="120" align="center">
      </el-table-column>
      <el-table-column prop="followWay" label="随访方式" min-width="100" align="center">
      </el-table-column>
      <el-table-column prop="doctorSign" label="随访医生签名" min-width="120" align="center">
      </el-table-column>
      <el-table-column label="操作" width="200" align="center">
        <template slot-scope="scope">
          <a  size="14px" @click="handleEdit(scope.$index, scope.row)" style="color:#4DB2EE;margin-right:20px;">编辑</a>
          <a  size="14px" @click="handleDel(scope.$index, scope.row)"style="color:#FB4141;margin-right:20px;">删除</a>
          <a size="14px" @click="conprint(scope.$index, scope.row)" style="color:#4DB2EE;">打印</a>
        </template>
      </el-table-column>
    </el-table>
    <!--工具条-->
    <el-col :span="24" class="toolbar">
      <!--<el-button type="danger" @click="batchRemove" :disabled="this.sels.length===0">批量删除</el-button>-->
      <el-pagination layout="prev, pager, next" @current-change="handleCurrentChange" :page-size="20" :total="total" style="float:right;">
      </el-pagination>
    </el-col>

    <!--编辑界面-->
    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible" :close-on-click-modal="false" center>
      <el-form :model="editForm" label-width="120px" :rules="editFormRules" ref="editForm">
        <div class="container">
          <el-tabs v-model="activeName">

            <el-tab-pane :label="`基本信息`" name="first">
              <el-row>
              <el-col :span="12">
              <el-form-item label="姓名" prop="name">
                  <el-input v-model="editForm.name" auto-complete="off" ></el-input>
              </el-form-item>
                </el-col>
                <el-col :span="12" >
                    <el-form-item label="身份证号" >
                      <el-input  v-model="editForm.idnumber" auto-complete="off"   ></el-input>
                    </el-form-item>
                </el-col>
                </el-row>

              <el-form-item label="症状">
                  <el-checkbox-group v-model="editForm.symptoms" style="width: 100%" >
                    <el-checkbox v-for="item in symptoms" style="margin-left: 6px" :key="item.label" :name="type" :value="item.value" :label="item.label"></el-checkbox>
                  </el-checkbox-group>
              </el-form-item>

              <el-row>
                <el-col :span="12">
                  <el-form-item label="血型"  >
                    <el-radio-group v-model="editForm.blood" >
                      <el-radio label=A型 style="width: 30px">A型</el-radio>
                      <el-radio label=B型  style="width: 30px">B型</el-radio>
                      <el-radio label=AB型 style="width: 30px">AB型</el-radio>
                      <el-radio label=O型  style="width: 30px">O型</el-radio>
                      <el-radio label=其他 style="width: 30px">其他</el-radio>
                    </el-radio-group>
              </el-form-item>
                </el-col>
                  <el-col :span="12">
                    <el-form-item label="血压">
                      <el-input  v-model="editForm.pressure" auto-complete="off" >
                        <template slot="append">mmHg</template>
                      </el-input>
                    </el-form-item>
                  </el-col>
              </el-row>

              <el-row>
              <el-col :span="12">
              <el-form-item label="体重">
                  <el-input v-model="editForm.weight" auto-complete="off" >
                    <template slot="append">Kg</template>
                  </el-input>
              </el-form-item>
              </el-col>
                <el-col :span="12" >
                  <el-form-item label="体质指数">
                    <el-input  v-model="editForm.bodyMassIndex" auto-complete="off">
                    <template slot="append">kg/m²</template>
                    </el-input>
                  </el-form-item>
                </el-col>
              </el-row>

              <el-row>
                <el-col :span="12">
                  <el-form-item label="足背动脉搏动" aria-placeholder="请选择">
              <!--<el-input v-model="editForm.footArteyPulse" auto-complete="off"></el-input>-->
                     <el-cascader :options="options" v-model="footArteyPulse" style="width:100%" ></el-cascader>
                  </el-form-item>
                </el-col>
                <el-col :span="12" >
			    	      <el-form-item label="其他">
                    <el-input  v-model="editForm.other" auto-complete="off" ></el-input>
                  </el-form-item>
                </el-col>
               </el-row>
              <el-row>
              <el-col :span="12" >
                <el-form-item label="次数">
                  <el-input  v-model="editForm.Count" auto-complete="off" ></el-input>
                </el-form-item>
              </el-col>
              </el-row>
             </el-tab-pane>

            <el-tab-pane :label="`生活方式指导`" name="second">
              <el-row>
                <el-col :span="12">
                 <el-form-item label="运动">
                      <el-input v-model="editForm.exercise" auto-complete="off">
                   <template slot="append">次/周</template>
                      </el-input>
                 </el-form-item>
                </el-col>
                <el-col :span="12" >
                  <el-form-item label="主食">
                    <el-input v-model="editForm.stapleFood" auto-complete="off" >
                    <template slot="append">克/天</template>
                    </el-input>
                  </el-form-item>
                </el-col>
              </el-row>

              <el-row>
                <el-col :span="12">
              <el-form-item label="日吸烟量">
                  <el-input v-model="editForm.dailySmoking" auto-complete="off" >
                <template slot="append">支</template>
                  </el-input>
              </el-form-item>
                </el-col>
                <el-col :span="12" >
                  <el-form-item label="日饮酒量">
                    <el-input  v-model="editForm.dailyDrinking" auto-complete="off">
                    <template slot="append">两</template>
                    </el-input>
                  </el-form-item>
                </el-col>
              </el-row>

              <el-row>
                <el-col :span="12">
              <el-form-item label="心理调整">
                  <el-radio-group v-model="editForm.psychologicalAdjust">
                    <el-radio label=良好 style="width: 60px">良好</el-radio>
                    <el-radio label=一般 style="width: 60px">一般</el-radio>
                    <el-radio label=差 style="width: 60px">差</el-radio>
                  </el-radio-group>
              </el-form-item>
                </el-col>
                <el-col :span="12" >
                  <el-form-item label="遵医行为">
                    <el-radio-group v-model="editForm.complianceBehavior">
                      <el-radio label=良好 style="width: 60px">良好</el-radio>
                      <el-radio label=一般 style="width: 60px">一般</el-radio>
                      <el-radio label=差 style="width: 60px">差</el-radio>
                    </el-radio-group>
                  </el-form-item>
                </el-col>
              </el-row>

              <el-row>
              <el-col :span="12">
			     <el-form-item label="空腹血糖值">
                  <el-input v-model="editForm.fastingBlood" >
             <template slot="append">mol/L</template>
                  </el-input>
           </el-form-item>
              </el-col>
              </el-row>
            </el-tab-pane>

            <el-tab-pane :label="`辅助检查`" name="third">
              <el-row>
                <el-col :span="12">
                    <el-form-item label="糖化血红蛋白">
                        <el-input v-model="editForm.hemoglobin" auto-complete="off" >
                      <template slot="append">(%)</template>
                        </el-input>
                    </el-form-item>
                      </el-col>
                      <el-col :span="12" >
                        <el-form-item label="检查日期">
                          <el-date-picker type="date"  v-model="editForm.examinationDate" style="width: 100%;"></el-date-picker>
                        </el-form-item>
                      </el-col>
               </el-row>

              <el-row>
                <el-col :span="12">
                    <el-form-item label="服药依从性">
                        <el-radio-group v-model="editForm.diabetescol">
                          <el-radio label=规律 style="width: 60px">规律</el-radio>
                          <el-radio label=间断 style="width: 60px">间断</el-radio>
                          <el-radio label=不服药 style="width: 60px">不服药</el-radio>
                        </el-radio-group>
                    </el-form-item>
                      </el-col>
                      <el-col :span="12" >
                        <el-form-item label="药物不良反应">
                          <el-radio-group v-model="editForm.drugsAdversed">
                            <el-radio label=无 style="width: 60px">无</el-radio>
                            <el-radio label=有 style="width: 60px">有</el-radio>
                          </el-radio-group>
                        </el-form-item>
                      </el-col>
              </el-row>

                    <el-form-item label="低血糖反应">
                        <el-radio-group v-model="editForm.hypoglycemia">
                          <el-radio label=无 style="width: 60px">无</el-radio>
                          <el-radio label=偶尔 style="width: 60px">偶尔</el-radio>
                          <el-radio label=频繁 style="width: 60px">频繁</el-radio>
                        </el-radio-group>
                    </el-form-item>


              <el-form-item label="此次随访分类">
                <el-radio-group v-model="editForm.followClassification">
                  <el-radio label=控制满意 style="width: 100px">控制满意</el-radio>
                  <el-radio label=控制不满意 style="width: 100px">控制不满意</el-radio>
                  <el-radio label=不良反应 style="width: 100px" >不良反应</el-radio>
                  <el-radio label=并发症 style="width: 100px">并发症</el-radio>
                </el-radio-group>
              </el-form-item>

              <el-row>
                <el-col :span="12">
              <el-form-item label="药物名称">
                  <el-input v-model="editForm.drugName" auto-complete="off"></el-input>
              </el-form-item>
                </el-col>
                <el-col :span="12" >
                  <el-form-item label="用法用量">
                    <el-input  v-model="editForm.usageDosage" auto-complete="off" ></el-input>
                  </el-form-item>
                </el-col>
              </el-row>
            </el-tab-pane>

            <el-tab-pane :label="`转诊`" name="four">
              <el-row>
                <el-col :span="12">
              <el-form-item label="转诊原因">
                        <el-input v-model="editForm.referralCause" auto-complete="off"></el-input>
              </el-form-item>
                </el-col>
                <el-col :span="12" >
                  <el-form-item label="转诊机构及科别">
                    <el-input  v-model="editForm.referralInstitution" auto-complete="off" ></el-input>
                  </el-form-item>
                </el-col>
              </el-row>

              <el-row>
                <el-col :span="12">
               <el-form-item label="本次随访日期">
                 <el-date-picker type="date"  v-model="editForm.followDate" style="width: 100%;"></el-date-picker>
               </el-form-item>
                </el-col>
                <el-col :span="12" >
                  <el-form-item label="随访方式">
                    <el-radio-group v-model="editForm.followWay">
                      <el-radio label=门诊 style="width: 40px">门诊</el-radio>
                      <el-radio label=家庭 style="width: 40px">家庭</el-radio>
                      <el-radio label=电话 style="width: 40px">电话</el-radio>
                    </el-radio-group>
                  </el-form-item>
                </el-col>
              </el-row>

              <el-row>
                <el-col :span="12">
              <el-form-item label="下次随访日期">
                <el-date-picker type="date"  v-model="editForm.nextFollowDate" style="width: 100%;"></el-date-picker>
              </el-form-item>
                </el-col>
                <el-col :span="12" >
                  <el-form-item label="随访医生签名">
                    <el-input  v-model="editForm.doctorSign" auto-complete="off" ></el-input>
                        </el-form-item>
                </el-col>
              </el-row>
            </el-tab-pane>

          </el-tabs>
        </div>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click.native="dialogFormVisible=false">取消</el-button>
        <el-button v-if="dialogStatus=='create'" type="primary" @click="createData">添加</el-button>
        <el-button v-else type="primary" @click="updateData" v-if="edit">修改</el-button>
        <el-button v-else type="primary" @click="conprint">打印</el-button>
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
  } from '@/api/diabetes'

  export default {
    data() {
      return {
        edit: true,
        activeName: 'first',
        dialogStatus: '',
        tableHeight: window.innerHeight - 220,
        textMap: {
          update: '编辑',
          create: '添加',
          detail: '详情'

        },
        dialogFormVisible: false,
        filters: {
          name: '',
          idnumber: ''
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
          name: [{ required: true, message: '请输入姓名', trigger: 'blur' }]
        },
        // 编辑界面数据
        editForm: {
          id: null,
          name: '',
          sex: '',
          age: '',
          idnumber: '',
          phone: '',
          doctor: '',
          livestatus: '',
          followWay: '',
          followDate: '',
          followCount: '',
          symptoms: [],
          blood: '',
          weight: '',
          bodyMassIndex: '',
          footArteyPulse: '',
          other: '',
          dailySmoking: '',
          dailyDrinking: '',
          exercise: '',
          stapleFood: '',
          psychologicalAdjust: '',
          complianceBehavior: '',
          fastingBlood: '',
          hemoglobin: '',
          examinationDate: '',
          diabetescol: '',
          drugsAdversed: '',
          pressure: '',
          hypoglycemia: '',
          followClassification: '',
          usageDosage: '',
          drugName: '',
          nextFollowDate: '',
          referralInstitution: '',
          referralCause: '',
          startTime: '',
          endTime: '',
          doctorSign: '',
          modifier: null,
          modifyDate: null,
          creater: null,
          creatdate: null
        },
        addFormVisible: false, // 新增界面是否显示
        addFormRules: {
          name: [{ required: true, message: '请输入姓名', trigger: 'blur' }]
        },
        symptoms: [
          { value: '无症状', label: '无症状' },
          { value: '多饮', label: '多饮' },
          { value: '多食', label: '多食' },
          { value: '多屎', label: '多屎' },
          { value: '视力模糊', label: '视力模糊' },
          { value: '感染', label: '感染' },
          { value: '手脚麻木', label: '手脚麻木' },
          { value: '下肢浮肿', label: '下肢浮肿' },
          { value: '体重明显下降', label: '体重明显下降' }],
        options: [
          {
            value: '触及正常',
            label: '触及正常'
          },
          {
            value: '减弱',
            label: '减弱',
            children: [
              {
                value: '双侧',
                label: '双侧'
              },
              {
                value: '左侧',
                label: '左侧'
              },
              {
                value: '右侧',
                label: '右侧'
              }
            ]
          },
          {
            value: '消失',
            label: '消失',
            children: [
              {
                value: '双侧',
                label: '双侧'
              },

              {
                value: '左侧',
                label: '左侧'
              },
              {
                value: '右侧',
                label: '右侧'
              }

            ]
          }
        ],
        footArteyPulse: []
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
        str = str + ' <div id="dataPanel" style="position:absolute;left:50%;margin-left:-397px;">\n' +
          '      <div id="topContent">\n' +
          '        <h2 style="width:794px;text-align:center">2型糖尿病患者随访服务记录表</h2>\n' +
          '        <span style="margin-left:50px;">姓   名：{{name}}</span>\n' +
          '        <span style="margin-left:400px;">编号：{{id}}</span>\n' +
          '      </div>\n' +
          '      <table id="table"  border="1"  cellspacing="0" bordercolor="#000000" style="width:730px;margin-left:30px;margin-top:5px;border-collapse:collapse;"  width = "80%">\n' +
          '        <tbody>\n' +
          '        <tr>\n' +
          '          <td colspan="2"style="width: 20%; text-align:center">次数</td>\n' +
          '          <td style="width: 15%;text-align:center">{{Conut1}}</td>\n' +
          '          <td style="width: 15%;text-align:center">{{Count2}}</td>\n' +
          '          <td style="width: 15%;text-align:center">{{Conut3}}</td>\n' +
          '          <td style="width: 15%;text-align:center">{{Conut3}}</td>\n' +
          '       </tr>\n' +
          '        <tr>\n' +
          '          <td colspan="2"style="width: 20%; text-align:center">随访日期</td>\n' +
          '          <td style="width: 15%;text-align:center">{{followDate1}}</td>\n' +
          '          <td style="width: 15%;text-align:center">{{followDate2}}</td>\n' +
          '          <td style="width: 15%;text-align:center">{{followDate3}}</td>\n' +
          '          <td style="width: 15%;text-align:center">{{followDate4}}</td>\n' +
          '        </tr>\n' +
          '        <tr>\n' +
          '          <td colspan="2" style="word-wrap:break-word;text-align:center">随访方式</td>\n' +
          '          <td style="width: 15%;text-align:center"> {{followWay1}} </td>\n' +
          '          <td style="width: 15%;text-align:center"> {{followWay2}} </td>\n' +
          '          <td style="width: 15%;text-align:center"> {{followWay3}} </td>\n' +
          '          <td style="width: 15%;text-align:center"> {{followWay4}} </td>\n' +
          '         \n' +
          '        <tr>\n' +
          '          <td rowspan="4"style="word-wrap:break-word;width:80px;text-align:center"> 症状</td>\n' +
          '          <td rowspan="4"style="word-wrap:break-word;width:80px;text-align:left">\n' +
          '            1.症状<br>\n' +
          '            2.多食<br>\n' +
          '            3.多饮<br>\n' +
          '            4.多屎<br>\n' +
          '            5.视力模糊<br>\n' +
          '            6.感染<br>\n' +
          '            7.手脚麻木<br>\n' +
          '            8.下肢浮肿<br>\n' +
          '            9.体重明显下降<br>\n' +
          '          </td>\n' +
          '          <td rowspan="4"style="word-wrap:break-word;width:80px;text-align:center">{{symptoms1}}\n' +
          '          </td>\n' +
          '          <td rowspan="4"style="word-wrap:break-word;width:80px;text-align:center">{{symptoms2}}\n' +
          '\n' +
          '          </td>\n' +
          '          <td rowspan="4"style="word-wrap:break-word;width:80px;text-align:center">{{symptoms3}}\n' +
          '\n' +
          '          </td>\n' +
          '          <td rowspan="4"style="word-wrap:break-word;width:80px;text-align:center">{{symptoms4}}\n' +
          '\n' +
          '          </td>\n' +
          '        </tr>\n' +
          '        <tr></tr><tr></tr><tr></tr><tr></tr>\n' +
          '        <tr>\n' +
          '          <td rowspan="5"style="word-wrap:break-word;width:80px;text-align:center"> 体征</td>\n' +
          '          <td  style="width: 15%;text-align:center">血压(mmHg)</td>\n' +
          '          <td  style="width: 15%;text-align:center">{{pressure1}}</td>\n' +
          '          <td  style="width: 15%;text-align:center">{{pressure2}}</td>\n' +
          '          <td  style="width: 15%;text-align:center">{{pressure3}}</td>\n' +
          '          <td  style="width: 15%;text-align:center">{{pressure4}}</td>\n' +
          '        </tr>\n' +
          '        <tr>\n' +
          '          <td  style="width: 15%;text-align:center">体重(Kg)</td>\n' +
          '          <td  style="width: 15%;text-align:center">{{weight1}}</td>\n' +
          '          <td  style="width: 15%;text-align:center">{{weight2}}</td>\n' +
          '          <td  style="width: 15%;text-align:center">{{weight3}}</td>\n' +
          '          <td  style="width: 15%;text-align:center">{{weight4}}</td>\n' +
          '        </tr>\n' +
          '        <tr>\n' +
          '          <td  style="width: 15%;text-align:center">体质指数(Kg/m²)</td>\n' +
          '          <td  style="width: 15%;text-align:center">{{bodyMassIndex1}}</td>\n' +
          '          <td  style="width: 15%;text-align:center">{{bodyMassIndex2}}</td>\n' +
          '          <td  style="width: 15%;text-align:center">{{bodyMassIndex3}}</td>\n' +
          '          <td  style="width: 15%;text-align:center">{{bodyMassIndex4}}</td>\n' +
          '        </tr>\n' +
          '        <tr>\n' +
          '          <td  style="width: 15%;text-align:center">足背动脉搏动</td>\n' +
          '        \t\t  <td  style="width: 15%;text-align:center">{{footArteyPulse1}}</td>\n' +
          '                  <td  style="width: 15%;text-align:center">{{footArteyPulse2}}</td>\n' +
          '                  <td  style="width: 15%;text-align:center">{{footArteyPulse3}}</td>\n' +
          '                  <td  style="width: 15%;text-align:center">{{footArteyPulse4}}</td>\n' +
          '        </tr>\n' +
          '        <tr>\n' +
          '          <td  style="width: 15%;text-align:center">其他</td>\n' +
          '          <td  style="width: 15%;text-align:center">{{other1}}</td>\n' +
          '          <td  style="width: 15%;text-align:center">{{other2}}</td>\n' +
          '          <td  style="width: 15%;text-align:center">{{other3}}</td>\n' +
          '          <td  style="width: 15%;text-align:center">{{other4}}</td>\n' +
          '        </tr>\n' +
          '        <tr>\n' +
          '          <td rowspan="6"style="word-wrap:break-word;width:80px;text-align:center"> 生活方式指导</td>\n' +
          '          <td style="text-align:center">日吸烟量</td>\n' +
          '          <td style="text-align:center">{{dailySmoking}}/支</td>\n' +
          '          <td style="text-align:center">{{dailySmoking}}/支</td>\n' +
          '          <td style="text-align:center">{{dailySmoking}}/支</td>\n' +
          '          <td style="text-align:center">{{dailySmoking}}/支</td>\n' +
          '        </tr>\n' +
          '        <tr>\n' +
          '          <td style="text-align:center">日饮酒量</td>\n' +
          '          <td style="text-align:center">{{dailyDrinking}}/两</td>\n' +
          '          <td style="text-align:center">{{dailyDrinking}}/两</td>\n' +
          '          <td style="text-align:center">{{dailyDrinking}}/两</td>\n' +
          '          <td style="text-align:center">{{dailyDrinking}}/两</td>\n' +
          '        </tr>\n' +
          '        <tr>\n' +
          '          <td  style="text-align:center">运动</td>\n' +
          '          <td style="text-align:center">{{exercise}}次/周</td>\n' +
          '          <td style="text-align:center">{{exercise}}次/周</td>\n' +
          '          <td style="text-align:center">{{exercise}}次/周</td>\n' +
          '          <td style="text-align:center">{{exercise}}次/周</td>\n' +
          '        </tr>\n' +
          '        <tr>\n' +
          '          <td style="text-align:center">主食(克/天)</td>\n' +
          '          <td style="text-align:center">{{stapleFood}}</td>\n' +
          '          <td style="text-align:center">{{stapleFood}}</td>\n' +
          '          <td style="text-align:center">{{stapleFood}}</td>\n' +
          '          <td style="text-align:center">{{stapleFood}}</td>\n' +
          '        </tr>\n' +
          '        <tr>\n' +
          '          <td style="height:50px;text-align:center">心理调整</td>\n' +
          '           <td style="text-align:center">{{psychologicalAdjust}}</td>\n' +
          '           <td style="text-align:center">{{psychologicalAdjust}}</td>\n' +
          '           <td style="text-align:center">{{psychologicalAdjust}}</td>\n' +
          '           <td style="text-align:center">{{psychologicalAdjust}}</td>\n' +
          '          \n' +
          '        </tr>\n' +
          '        <tr>\n' +
          '          <td style="height:50px;text-align:center">遵医行为</td>\n' +
          '  \t\t\t <td style="text-align:center">{{complianceBehavior}}</td>\n' +
          '             <td style="text-align:center">{{complianceBehavior}}</td>\n' +
          '             <td style="text-align:center">{{complianceBehavior}}</td>\n' +
          '             <td style="text-align:center">{{complianceBehavior}}</td>\n' +
          '        </tr>\n' +
          '\n' +
          '        <tr>\n' +
          '          <td rowspan="2"style="word-wrap:break-word;width:80px;text-align:center"> 辅助检查</td>\n' +
          '          <td  style="height:50px;text-align:center">空腹血糖值</td>\n' +
          '          <td style="text-align:center">{{fastingBlood}}mol/L</td>\n' +
          '          <td style="text-align:center">{{fastingBlood}}mol/L</td>\n' +
          '          <td style="text-align:center">{{fastingBlood}}mol/L</td>\n' +
          '          <td style="text-align:center">{{fastingBlood}}mol/L</td>\n' +
          '        </tr>\n' +
          '        <tr>\n' +
          '          <td  style="height:50px;text-align:center">其他检查</td>\n' +
          '          <td style="text-align:center">糖化血红蛋白:&nbsp&nbsp&nbsp{{hemoglobin}}%<br>检查日期：{{examinationDate}}</td>\n' +
          '          <td style="text-align:center">糖化血红蛋白:&nbsp&nbsp&nbsp{{hemoglobin}}%<br>检查日期：{{examinationDate}}</td>\n' +
          '          <td style="text-align:center">糖化血红蛋白:&nbsp&nbsp&nbsp{{hemoglobin}}%<br>检查日期：{{examinationDate}}</td>\n' +
          '          <td style="text-align:center">糖化血红蛋白:&nbsp&nbsp&nbsp{{hemoglobin}}%<br>检查日期：{{examinationDate}}</td>\n' +
          '        </tr>\n' +
          '        <tr>\n' +
          '          <td colspan="2"  style="word-wrap:break-word;width:80px;text-align:center"> 服药依从性</td>\n' +
          '           \t <td style="text-align:center">{{diabetescol}}</td>\n' +
          '             <td style="text-align:center">{{diabetescol}}</td>\n' +
          '             <td style="text-align:center">{{diabetescol}}</td>\n' +
          '             <td style="text-align:center">{{diabetescol}}</td>\n' +
          '\n' +
          '        </tr>\n' +
          '        <tr>\n' +
          '          <td colspan="2"  style="word-wrap:break-word;width:80px;text-align:center"> 药物不良反应</td>\n' +
          '           \t <td style="text-align:center">{{drugsAdversed}}</td>\n' +
          '             <td style="text-align:center">{{drugsAdversed}}</td>\n' +
          '             <td style="text-align:center">{{drugsAdversed}}</td>\n' +
          '             <td style="text-align:center">{{drugsAdversed}}</td>\n' +
          '        </tr>\n' +
          '        <tr>\n' +
          '          <td colspan="2"  style="word-wrap:break-word;width:80px;text-align:center"> 低血糖反应</td>\n' +
          '          \t <td style="text-align:center">{{hypoglycemia}}</td>\n' +
          '             <td style="text-align:center">{{hypoglycemia}}</td>\n' +
          '             <td style="text-align:center">{{hypoglycemia}}</td>\n' +
          '             <td style="text-align:center">{{hypoglycemia}}</td>\n' +
          '        </tr>\n' +
          '        <tr>\n' +
          '          <td colspan="2"  style="word-wrap:break-word;width:80px;text-align:center"> 此次随访分类</td>\n' +
          '             <td style="text-align:center">{{followClassification}}</td>\n' +
          '             <td style="text-align:center">{{followClassification}}</td>\n' +
          '             <td style="text-align:center">{{followClassification}}</td>\n' +
          '             <td style="text-align:center">{{followClassification}}</td>\n' +
          '        </tr>\n' +
          '        <tr>\n' +
          '          <td rowspan="2"style="word-wrap:break-word;width:80px;text-align:center"> 用药情况</td>\n' +
          '          <td  style="height:50px;text-align:center">药物名称</td>\n' +
          '  \t\t\t <td style="text-align:center">{{drugName}}</td>\n' +
          '             <td style="text-align:center">{{drugName}}</td>\n' +
          '             <td style="text-align:center">{{drugName}}</td>\n' +
          '             <td style="text-align:center">{{drugName}}</td>\n' +
          '        </tr>\n' +
          '        <tr>\n' +
          '          <td style="height:50px;text-align:center">用法用量</td>\n' +
          '  \t\t\t <td style="text-align:center">{{usageDosage}}</td>\n' +
          '             <td style="text-align:center">{{usageDosage}}</td>\n' +
          '             <td style="text-align:center">{{usageDosage}}</td>\n' +
          '             <td style="text-align:center">{{usageDosage}}</td>\n' +
          '        </tr>\n' +
          '        <tr>\n' +
          '          <td rowspan="2" style="height:50px;text-align:center">转诊</td>\n' +
          '          \t <td style="text-align:left">转诊原因</td>\n' +
          '           \t <td style="text-align:center">{{referralCause}}</td>\n' +
          '             <td style="text-align:center">{{referralCause}}</td>\n' +
          '             <td style="text-align:center">{{referralCause}}</td>\n' +
          '             <td style="text-align:center">{{referralCause}}</td>\n' +
          '          \n' +
          '        </tr>\n' +
          '         <tr>\n' +
          '          \t <td style="text-align:left">转诊机构及科别</td>\n' +
          '           \t <td style="text-align:center">{{referralInstitution}}</td>\n' +
          '             <td style="text-align:center">{{referralInstitution}}</td>\n' +
          '             <td style="text-align:center">{{referralInstitution}}</td>\n' +
          '             <td style="text-align:center">{{referralInstitution}}</td>\n' +
          '          \n' +
          '        </tr>\n' +
          '        <tr>\n' +
          '          <td colspan="2" style="text-align:center">下次随访日期</td>\n' +
          '          <td style="text-align:center">{{nextFollowDate}}</td>\n' +
          '          <td style="text-align:center">{{nextFollowDate}}</td>\n' +
          '          <td style="text-align:center">{{nextFollowDate}}</td>\n' +
          '          <td style="text-align:center">{{nextFollowDate}}</td>\n' +
          '        </tr>\n' +
          '        <tr>\n' +
          '          <td colspan="2" style="text-align:center">随访医生签名</td>\n' +
          '          <td style="text-align:center">{{doctorSign}}</td>\n' +
          '          <td style="text-align:center">{{doctorSign}}</td>\n' +
          '          <td style="text-align:center">{{doctorSign}}</td>\n' +
          '          <td style="text-align:center">{{doctorSign}}</td>\n' +
          '        </tr>\n' +
          '        </tbody>\n' +
          '      </table>\n' +
          '    </div>'
        str = str.replace(/{{id}}/, this.printItem1.id ? this.printItem1.id : '')
        str = str.replace(/{{name}}/, this.printItem1.name ? this.printItem1.name : '')
        str = str.replace(/{{Count1}}/, this.printItem1.Count ? this.printItem1.Count : '')
        str = str.replace(/{{followWay2}}/, this.printItem2.followWay ? this.printItem2.Count : '')
        str = str.replace(/{{followWay3}}/, this.printItem3.followWay ? this.printItem3.Count : '')
        str = str.replace(/{{followWay4}}/, this.printItem4.followWay ? this.printItem4.followWay : '')

        str = str.replace(/{{followWay1}}/, this.printItem1.followWay ? this.printItem1.followWay : '')
        str = str.replace(/{{followWay2}}/, this.printItem2.followWay ? this.printItem2.followWay : '')
        str = str.replace(/{{followWay3}}/, this.printItem3.followWay ? this.printItem3.followWay : '')
        str = str.replace(/{{followWay4}}/, this.printItem4.followWay ? this.printItem4.followWay : '')

        str = str.replace(/{{symptoms1}}/, this.printItem1.symptoms ? this.printItem1.symptoms : '')
        str = str.replace(/{{symptoms2}}/, this.printItem2.symptoms ? this.printItem2.symptoms : '')
        str = str.replace(/{{symptoms3}}/, this.printItem3.symptoms ? this.printItem3.symptoms : '')
        str = str.replace(/{{symptoms4}}/, this.printItem4.symptoms ? this.printItem4.symptoms : '')

        str = str.replace(/{{pressure1}}/, this.printItem1.pressure ? this.printItem1.pressure : '')
        str = str.replace(/{{pressure2}}/, this.printItem2.pressure ? this.printItem2.pressure : '')
        str = str.replace(/{{pressure3}}/, this.printItem3.pressure ? this.printItem3.pressure : '')
        str = str.replace(/{{pressure4}}/, this.printItem4.pressure ? this.printItem4.pressure : '')

        str = str.replace(/{{weight1}}/, this.printItem1.weight ? this.printItem1.weight : '')
        str = str.replace(/{{weight2}}/, this.printItem2.weight ? this.printItem2.weight : '')
        str = str.replace(/{{weight3}}/, this.printItem3.weight ? this.printItem3.weight : '')
        str = str.replace(/{{weight4}}/, this.printItem4.weight ? this.printItem4.weight : '')

        str = str.replace(/{{bodyMassIndex}}/, this.printItem1.bodyMassIndex ? this.printItem1.bodyMassIndex : '')
        str = str.replace(/{{bodyMassIndex}}/, this.printItem2.bodyMassIndex ? this.printItem2.bodyMassIndex : '')
        str = str.replace(/{{bodyMassIndex}}/, this.printItem3.bodyMassIndex ? this.printItem3.bodyMassIndex : '')
        str = str.replace(/{{bodyMassIndex}}/, this.printItem4.bodyMassIndex ? this.printItem4.bodyMassIndex : '')

        str = str.replace(/{{footArteyPulse}}/, this.printItem1.footArteyPulse ? this.printItem1.footArteyPulse : '')
        str = str.replace(/{{footArteyPulse}}/, this.printItem2.footArteyPulse ? this.printItem2.footArteyPulse : '')
        str = str.replace(/{{footArteyPulse}}/, this.printItem3.footArteyPulse ? this.printItem3.footArteyPulse : '')
        str = str.replace(/{{footArteyPulse}}/, this.printItem3.footArteyPulse ? this.printItem3.footArteyPulse : '')

        str = str.replace(/{{other}}/, this.printItem1.other ? this.printItem1.other : '')
        str = str.replace(/{{other}}/, this.printItem2.other ? this.printItem2.other : '')
        str = str.replace(/{{other}}/, this.printItem3.other ? this.printItem3.other : '')
        str = str.replace(/{{other}}/, this.printItem4.other ? this.printItem4.other : '')

        str = str.replace(/{{dailySmoking}}/, this.printItem1.dailySmoking ? this.printItem1.dailySmoking : '')
        str = str.replace(/{{dailySmoking}}/, this.printItem2.dailySmoking ? this.printItem2.dailySmoking : '')
        str = str.replace(/{{dailySmoking}}/, this.printItem3.dailySmoking ? this.printItem3.dailySmoking : '')
        str = str.replace(/{{dailySmoking}}/, this.printItem4.dailySmoking ? this.printItem4.dailySmoking : '')

        str = str.replace(/{{dailyDrinking}}/, this.printItem1.dailyDrinking ? this.printItem1.dailyDrinking : '')
        str = str.replace(/{{dailyDrinking}}/, this.printItem.dailyDrinking ? this.printItem.dailyDrinking : '')
        str = str.replace(/{{dailyDrinking}}/, this.printItem.dailyDrinking ? this.printItem.dailyDrinking : '')
        str = str.replace(/{{dailyDrinking}}/, this.printItem.dailyDrinking ? this.printItem.dailyDrinking : '')

        str = str.replace(/{{exercise}}/, this.printItem.exercise ? this.printItem.exercise : '')
        str = str.replace(/{{exercise}}/, this.printItem.exercise ? this.printItem.exercise : '')
        str = str.replace(/{{exercise}}/, this.printItem.exercise ? this.printItem.exercise : '')
        str = str.replace(/{{exercise}}/, this.printItem.exercise ? this.printItem.exercise : '')

        str = str.replace(/{{stapleFood}}/, this.printItem.stapleFood ? this.printItem.stapleFood : '')
        str = str.replace(/{{stapleFood}}/, this.printItem.stapleFood ? this.printItem.stapleFood : '')
        str = str.replace(/{{stapleFood}}/, this.printItem.stapleFood ? this.printItem.stapleFood : '')
        str = str.replace(/{{stapleFood}}/, this.printItem.stapleFood ? this.printItem.stapleFood : '')

        str = str.replace(/{{psychologicalAdjust}}/, this.printItem.psychologicalAdjust ? this.printItem.psychologicalAdjust : '')
        str = str.replace(/{{psychologicalAdjust}}/, this.printItem.psychologicalAdjust ? this.printItem.psychologicalAdjust : '')
        str = str.replace(/{{psychologicalAdjust}}/, this.printItem.psychologicalAdjust ? this.printItem.psychologicalAdjust : '')
        str = str.replace(/{{psychologicalAdjust}}/, this.printItem.psychologicalAdjust ? this.printItem.psychologicalAdjust : '')

        str = str.replace(/{{complianceBehavior}}/, this.printItem.complianceBehavior ? this.printItem.complianceBehavior : '')
        str = str.replace(/{{complianceBehavior}}/, this.printItem.complianceBehavior ? this.printItem.complianceBehavior : '')
        str = str.replace(/{{complianceBehavior}}/, this.printItem.complianceBehavior ? this.printItem.complianceBehavior : '')
        str = str.replace(/{{complianceBehavior}}/, this.printItem.complianceBehavior ? this.printItem.complianceBehavior : '')

        str = str.replace(/{{footArteyPulse}}/, this.printItem.footArteyPulse ? this.printItem.footArteyPulse : '')
        str = str.replace(/{{footArteyPulse}}/, this.printItem.footArteyPulse ? this.printItem.footArteyPulse : '')
        str = str.replace(/{{footArteyPulse}}/, this.printItem.footArteyPulse ? this.printItem.footArteyPulse : '')
        str = str.replace(/{{footArteyPulse}}/, this.printItem.footArteyPulse ? this.printItem.footArteyPulse : '')

        str = str.replace(/{{fastingBlood}}/, this.printItem.fastingBlood ? this.printItem.fastingBlood : '')
        str = str.replace(/{{fastingBlood}}/, this.printItem.fastingBlood ? this.printItem.fastingBlood : '')
        str = str.replace(/{{fastingBlood}}/, this.printItem.fastingBlood ? this.printItem.fastingBlood : '')
        str = str.replace(/{{fastingBlood}}/, this.printItem.fastingBlood ? this.printItem.fastingBlood : '')

        str = str.replace(/{{hemoglobin}}/, this.printItem.hemoglobin ? this.printItem.hemoglobin : '')
        str = str.replace(/{{hemoglobin}}/, this.printItem.hemoglobin ? this.printItem.hemoglobin : '')
        str = str.replace(/{{hemoglobin}}/, this.printItem.hemoglobin ? this.printItem.hemoglobin : '')
        str = str.replace(/{{hemoglobin}}/, this.printItem.hemoglobin ? this.printItem.hemoglobin : '')

        str = str.replace(/{{examinationDate}}/, this.printItem.examinationDate ? this.printItem.examinationDate : '')
        str = str.replace(/{{examinationDate}}/, this.printItem.examinationDate ? this.printItem.examinationDate : '')
        str = str.replace(/{{examinationDate}}/, this.printItem.examinationDate ? this.printItem.examinationDate : '')
        str = str.replace(/{{examinationDate}}/, this.printItem.examinationDate ? this.printItem.examinationDate : '')

        str = str.replace(/{{diabetescol}}/, this.printItem.diabetescol ? this.printItem.diabetescol : '')
        str = str.replace(/{{diabetescol}}/, this.printItem.diabetescol ? this.printItem.diabetescol : '')
        str = str.replace(/{{diabetescol}}/, this.printItem.diabetescol ? this.printItem.diabetescol : '')
        str = str.replace(/{{diabetescol}}/, this.printItem.diabetescol ? this.printItem.diabetescol : '')

        str = str.replace(/{{drugsAdversed}}/, this.printItem.drugsAdversed ? this.printItem.drugsAdversed : '')
        str = str.replace(/{{drugsAdversed}}/, this.printItem.drugsAdversed ? this.printItem.drugsAdversed : '')
        str = str.replace(/{{drugsAdversed}}/, this.printItem.drugsAdversed ? this.printItem.drugsAdversed : '')
        str = str.replace(/{{drugsAdversed}}/, this.printItem.drugsAdversed ? this.printItem.drugsAdversed : '')

        str = str.replace(/{{hypoglycemia}}/, this.printItem.hypoglycemia ? this.printItem.hypoglycemia : '')
        str = str.replace(/{{hypoglycemia}}/, this.printItem.hypoglycemia ? this.printItem.hypoglycemia : '')
        str = str.replace(/{{hypoglycemia}}/, this.printItem.hypoglycemia ? this.printItem.hypoglycemia : '')
        str = str.replace(/{{hypoglycemia}}/, this.printItem.hypoglycemia ? this.printItem.hypoglycemia : '')

        str = str.replace(/{{followDate}}/, this.printItem.followDate ? this.printItem.followDate : '')
        str = str.replace(/{{followDate}}/, this.printItem.followDate ? this.printItem.followDate : '')
        str = str.replace(/{{followDate}}/, this.printItem.followDate ? this.printItem.followDate : '')
        str = str.replace(/{{followDate}}/, this.printItem.followDate ? this.printItem.followDate : '')

        str = str.replace(/{{followClassification}}/, this.printItem.followClassification ? this.printItem.followClassification : '')
        str = str.replace(/{{followClassification}}/, this.printItem.followClassification ? this.printItem.followClassification : '')
        str = str.replace(/{{followClassification}}/, this.printItem.followClassification ? this.printItem.followClassification : '')
        str = str.replace(/{{followClassification}}/, this.printItem.followClassification ? this.printItem.followClassification : '')

        str = str.replace(/{{drugName}}/, this.printItem.drugName ? this.printItem.drugName : '')
        str = str.replace(/{{drugName}}/, this.printItem.drugName ? this.printItem.drugName : '')
        str = str.replace(/{{drugName}}/, this.printItem.drugName ? this.printItem.drugName : '')
        str = str.replace(/{{drugName}}/, this.printItem.drugName ? this.printItem.drugName : '')

        str = str.replace(/{{usageDosage}}/, this.printItem.usageDosage ? this.printItem.usageDosage : '')
        str = str.replace(/{{usageDosage}}/, this.printItem.usageDosage ? this.printItem.usageDosage : '')
        str = str.replace(/{{usageDosage}}/, this.printItem.usageDosage ? this.printItem.usageDosage : '')
        str = str.replace(/{{usageDosage}}/, this.printItem.usageDosage ? this.printItem.usageDosage : '')

        str = str.replace(/{{referralCause}}/, this.printItem.referralCause ? this.printItem.referralCause : '')
        str = str.replace(/{{referralCause}}/, this.printItem.referralCause ? this.printItem.referralCause : '')
        str = str.replace(/{{referralCause}}/, this.printItem.referralCause ? this.printItem.referralCause : '')
        str = str.replace(/{{referralCause}}/, this.printItem.referralCause ? this.printItem.referralCause : '')

        str = str.replace(/{{referralInstitution}}/, this.printItem.referralInstitution ? this.printItem.referralInstitution : '')
        str = str.replace(/{{referralInstitution}}/, this.printItem.referralInstitution ? this.printItem.referralInstitution : '')
        str = str.replace(/{{referralInstitution}}/, this.printItem.referralInstitution ? this.printItem.referralInstitution : '')
        str = str.replace(/{{referralInstitution}}/, this.printItem.referralInstitution ? this.printItem.referralInstitution : '')

        str = str.replace(/{{nextFollowDate}}/, this.printItem.nextFollowDate ? this.printItem.nextFollowDate : '')
        str = str.replace(/{{nextFollowDate}}/, this.printItem.nextFollowDate ? this.printItem.nextFollowDate : '')
        str = str.replace(/{{nextFollowDate}}/, this.printItem.nextFollowDate ? this.printItem.nextFollowDate : '')
        str = str.replace(/{{nextFollowDate}}/, this.printItem.nextFollowDate ? this.printItem.nextFollowDate : '')

        str = str.replace(/{{doctorSign}}/, this.printItem.doctorSign ? this.printItem.doctorSign : '')
        str = str.replace(/{{doctorSign}}/, this.printItem.doctorSign ? this.printItem.doctorSign : '')
        str = str.replace(/{{doctorSign}}/, this.printItem.doctorSign ? this.printItem.doctorSign : '')
        str = str.replace(/{{doctorSign}}/, this.printItem.doctorSign ? this.printItem.doctorSign : '')

        str = str.replace(/{{followDate}}/, this.printItem.followDate ? this.printItem.followDate : '')
        str = str.replace(/{{followDate}}/, this.printItem.followDate ? this.printItem.followDate : '')
        str = str.replace(/{{followDate}}/, this.printItem.followDate ? this.printItem.followDate : '')
        str = str.replace(/{{followDate}}/, this.printItem.followDate ? this.printItem.followDate : '')

        str = str.replace(/{{followDate}}/, this.printItem.followDate ? this.printItem.followDate : '')
        str = str.replace(/{{followDate}}/, this.printItem.followDate ? this.printItem.followDate : '')
        str = str.replace(/{{followDate}}/, this.printItem.followDate ? this.printItem.followDate : '')
        str = str.replace(/{{followDate}}/, this.printItem.followDate ? this.printItem.followDate : '')
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
      printUsers() {
        const para = {
          idnumber: this.editForm.idnumber
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
          idnumber: this.filters.idnumber
        }
        getUserListPage(para).then(res => {
          this.total = res.data.total
          this.users = res.data.hhh
          if (this.page > 1 && (this.page - 1) * 10 >= this.total) {
            this.page = this.page - 1
            this.getUsers()
          }
        })
      },
      // 查询
      search() {
        this.page = 1
        this.getUsers()
      },
      // 重置
      cz() {
        this.filters = {
          name: '',
          age: '',
          startTime: '',
          endTime: '',
          idnumber: '',
          liveStatus: '',
          doctor: '',
          ethnic: '',
          sex: '',
          phone: '',
          area: [],
          creater: ''
        }
        this.area = []
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
      handleRowChange(row, event) {
        this.dialogStatus = 'detai'
        this.dialogFormVisible = 'true'
        this.editForm = Object.assign({}, row)
        this.edit = false
      },
      // 显示编辑界面
      handleEdit(index, row) {
        this.dialogStatus = 'update'
        this.dialogFormVisible = true
        this.editForm.symptoms = []
        this.editForm = Object.assign({}, row)
        this.footArteyPulse = this.editForm.footArteyPulse.split('/')
        this.editForm.symptoms = this.editForm.symptoms.split('/')
        if (this.editForm.symptoms == null) this.editForm.symptoms = this.editForm.symptoms.split('/')
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
          sex: '',
          age: '',
          phone: '',
          doctor: '',
          idnumber: '',
          followWay: '',
          followDate: '',
          Count: '',
          followCount: '',
          symptoms: [],
          blood: '',
          weight: '',
          bodyMassIndex: '',
          footArteyPulse: '',
          other: '',
          dailySmoking: '',
          dailyDrinking: '',
          exercise: '',
          stapleFood: '',
          psychologicalAdjust: '',
          complianceBehavior: '',
          fastingBlood: '',
          hemoglobin: '',
          examinationDate: '',
          diabetescol: '',
          drugsAdversed: '',
          pressure: '',
          hypoglycemia: '',
          followClassification: '',
          usageDosage: '',
          drugName: '',
          nextFollowDate: '',
          referralInstitution: '',
          referralCause: '',
          doctorSign: '',
          creater: this.$store.getters.name
        }
        this.footArteyPulse = []
      },
      // 编辑
      updateData() {
        this.$refs.editForm.validate(valid => {
          if (valid) {
            this.$confirm('确认提交吗？', '提示', {})
              .then(() => {
                const para = Object.assign({}, this.editForm)
                para.modifier = this.$store.getters.name
                para.examinationDate =
                  !para.examinationDate || para.examinationDate === ''
                    ? null
                    : util.formatDate.format(new Date(para.examinationDate), 'yyyy-MM-dd')
                para.nextFollowDate =
                  !para.nextFollowDate || para.nextFollowDate === ''
                    ? null
                    : util.formatDate.format(new Date(para.nextFollowDate), 'yyyy-MM-dd')
                para.followDate =
                  !para.followDate || para.followDate === ''
                    ? null
                    : util.formatDate.format(new Date(para.followDate), 'yyyy-MM-dd')
                para.modifyDate =
                para.modifyDate =
                  !para.modifyDate || para.modifyDate === ''
                    ? null
                    : util.formatDate.format(new Date(para.modifyDate), 'yyyy-MM-dd')
                para.footArteyPulse = ''
                for (const itm of this.footArteyPulse) {
                  para.footArteyPulse += itm
                  para.footArteyPulse += '/'
                }
                para.footArteyPulse = para.footArteyPulse.substr(0, para.footArteyPulse.length - 1)
                para.symptoms = ''
                for (const itm of this.editForm.symptoms) {
                  para.symptoms += itm
                  para.symptoms += '/'
                }
                para.symptoms = para.symptoms.substr(0, para.symptoms.length - 1)
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
            this.$confirm('确认提交吗？', '提示', {})
              .then(() => {
                const para = Object.assign({}, this.editForm)
                console.log(para)
                para.examinationDate =
                  !para.examinationDate || para.examinationDate === ''
                    ? null
                    : util.formatDate.format(new Date(para.examinationDate), 'yyyy-MM-dd')
                para.nextFollowDate =
                  !para.nextFollowDate || para.nextFollowDate === ''
                    ? null
                    : util.formatDate.format(new Date(para.nextFollowDate), 'yyyy-MM-dd')
                para.followDate =
                  !para.followDate || para.followDate === ''
                    ? null
                    : util.formatDate.format(new Date(para.followDate), 'yyyy-MM-dd')
                para.createDate = new Date()
                para.createDate =
                  !para.createDate || para.createDate === ''
                    ? null
                    : util.formatDate.format(new Date(para.createDate), 'yyyy-MM-dd')
                para.footArteyPulse = ''
                for (const itm of this.footArteyPulse) {
                  para.footArteyPulse += itm
                  para.footArteyPulse += '/'
                }
                para.footArteyPulse = para.footArteyPulse.substr(0, para.footArteyPulse.length - 1)
                para.symptoms = ''
                for (const itm of this.editForm.symptoms) {
                  para.symptoms += itm
                  para.symptoms += '/'
                }
                para.symptoms = para.symptoms.substr(0, para.symptoms.length - 1)
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
