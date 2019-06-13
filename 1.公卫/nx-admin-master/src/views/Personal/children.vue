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
          <el-button type="primary" v-on:click="search" style="background-color:#4db2ee;border-color:transparent" icon="el-icon-search">查询</el-button>
        </el-form-item>
      </el-form>
    </el-col>

    <!--工具条-->
    <el-col :span="2" class="toolbar" style="padding-bottom: 0px;" align="right">
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="search" style="background-color:#4db2ee;border-color:transparent" icon="el-icon-search">查询</el-button>
      </div>
      <el-form :inline="true" :model="filters" @submit.native.prevent>
        <el-form-item>
          <el-button type="primary" @click="handleAdd" icon="el-icon-circle-plus-outline">新增</el-button>
        </el-form-item>
      </el-form>
    </el-col>

    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible2" :close-on-click-modal="false" center>
      <el-form :model="findForm" label-width="100px" :rules="findFormRules" ref="findForm">

        <el-form-item>
          <el-input v-model="filters.sport" placeholder="运动"></el-input>
        </el-form-item>
        <el-form-item>
          <el-input v-model="filters.saltSituation" placeholder="摄盐情况"></el-input>
        </el-form-item>
        <el-form-item>
          <el-input v-model="filters.psyAdjust" placeholder="心理调整"></el-input>
        </el-form-item>
        <el-form-item>
          <el-input v-model="filters.obeyMedical" placeholder="遵医行为"></el-input>
        </el-form-item>

        <el-form-item>
          <el-input v-model="filters.mediCompli" placeholder="服药依从性"></el-input>
        </el-form-item>
        <el-form-item>
          <el-input v-model="filters.adverdrugReact" placeholder="药物不良反应"></el-input>
        </el-form-item>
        <el-form-item>
          <el-input v-model="filters.accessType" placeholder="此次随访分类"></el-input>
        </el-form-item>
        <el-form-item>
          <el-input v-model="filters.drugName" placeholder="药物名称"></el-input>
        </el-form-item>
        <el-form-item>
          <el-input v-model="filters.organAndDiv" placeholder="机构及科别"></el-input>
        </el-form-item>
        <el-form-item label="随访日期">
        </el-form-item>
        <el-form-item>
          <el-date-picker type="date" placeholder="选择日期" v-model="filters.follUpdate_s" style="width: 100%;"></el-date-picker>
        </el-form-item>
        <el-form-item>
          <el-date-picker type="date" placeholder="选择日期" v-model="filters.follUpdate_e" style="width: 100%;"></el-date-picker>
        </el-form-item>

        <el-form-item label="下次随访日期">
        </el-form-item>
        <el-form-item>
          <el-date-picker type="date" placeholder="选择日期" v-model="filters.dateNextFollow_s" style="width: 100%;"></el-date-picker>
        </el-form-item>
        <el-form-item>
          <el-date-picker type="date" placeholder="选择日期" v-model="filters.dateNextFollow_e" style="width: 100%;"></el-date-picker>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" v-on:click="getUsers" style="background-color:#4db2ee;border-color:transparent" icon="el-icon-search">查询</el-button>
      </div>

    </el-dialog>

    <!--列表-->
    <el-table :data="users" :height="tableHeight" highlight-current-row @selection-change="selsChange" style="width: 100%;" border @row-dblclick="handleRowChange">
      <el-table-column type="index" width="60">
      </el-table-column>
      <el-table-column prop="id" v-if=false label="主键" width="60" >
      </el-table-column>
      <el-table-column prop="name" label="姓名" width="200">
      </el-table-column>
      <el-table-column prop="sex" label="性别" width="120" >
      </el-table-column>
      <el-table-column prop="birthdate" label="出生月份" width="200" >
      </el-table-column>
      <el-table-column prop="peopleId" label="身份证号" width="200" >
      </el-table-column>
      <el-table-column prop="addr" label="地址" width="200" >
      </el-table-column>
      <el-table-column prop="fatherPhone" label="父亲联系方式" width="150" >
      </el-table-column>
      <el-table-column prop="motherPhone" label="母亲联系方式" width="150" >
      </el-table-column>
      <el-table-column prop="followDate" label="随访时间" width="180" >
      </el-table-column>
      <el-table-column prop="doctor" label="随访医生" width="120" >
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
      <el-form :model="editForm" label-width="70px" :rules="editFormRules" ref="editForm">
        <div class="container" id="print">
          <el-tabs v-model="activeName">

             <el-tab-pane :label="`基本信息`" name="first">

               <el-form-item label="主键" v-if="false">
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
                   <el-form-item label="性别" label-width="100px" >
                     <el-radio-group v-model="editForm.sex">
                       <el-radio label=男>男</el-radio>
                       <el-radio label=女>女</el-radio>
                       <el-radio label=未知性别>未知性别</el-radio>
                     </el-radio-group>
                   </el-form-item>
                 </el-col>
                 <el-col :span="12">
                   <el-form-item label="生日" label-width="120px">
                     <el-date-picker type="date" placeholder="选择日期" v-model="editForm.birthdate" style="width:100%"></el-date-picker>
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
                   <el-form-item label="家庭住址" label-width="120px">
                     <el-input  v-model="editForm.addr" auto-complete="off" ></el-input>
                   </el-form-item>
                 </el-col>
               </el-row>

               <el-row>
                 <el-form-item label="父亲" label-width="100px">
                   <el-row>
                     <el-col :span="6">
                       <el-input placeholder="姓名" auto-complete="off" v-model="editForm.fatherName"></el-input>
                     </el-col>
                     <el-col :span="6">
                       <el-input placeholder="职业" auto-complete="off" v-model="editForm.fatherJob"></el-input>
                     </el-col>
                     <el-col :span="6">
                       <el-input placeholder="联系方式" auto-complete="off" v-model="editForm.fatherPhone"></el-input>
                     </el-col>
                     <el-col :span="6">
                       <el-date-picker type="date" placeholder="父亲出生日期"  v-model="editForm.fatherBirthdate" style="width: 200px"></el-date-picker>
                     </el-col>
                   </el-row>
                 </el-form-item>
               </el-row>

               <el-row>
               <el-form-item label="母亲" label-width="100px">
                 <el-row>
                 <el-col :span="6">
                   <el-input placeholder="姓名" auto-complete="off" v-model="editForm.motherName"></el-input>
                 </el-col>
                 <el-col :span="6">
                   <el-input placeholder="职业" auto-complete="off" v-model="editForm.motherJob"></el-input>
                 </el-col>
                 <el-col :span="6">
                   <el-input placeholder="联系方式" auto-complete="off" v-model="editForm.motherPhone"></el-input>
                 </el-col>
                 <el-col :span="6">
                   <el-date-picker type="date" placeholder="母亲出生日期"  v-model="editForm.motherBirthdate" style="width: 200px"></el-date-picker>
                 </el-col>
                   </el-row>
               </el-form-item>
               </el-row>

        </el-tab-pane>
            <el-tab-pane :label="`胎儿基本信息`" name="second">

              <el-row>
                    <el-col :span="10">
                      <el-form-item label="出生孕周" label-width="120px">
                        <el-input v-model="editForm.week"  auto-complete="off">
                          <template slot="append">周</template>
                        </el-input>
                      </el-form-item>
                    </el-col>
                    <el-col :span="14" >
                      <el-form-item label="母亲情况" label-width="120px">
                        <el-checkbox-group v-model="editForm.motherState"a style="width:100%">
                          <el-checkbox v-for="item in motherState" :key="item.label" :name="type" :label="item.label" :value="item.value"></el-checkbox>
                        </el-checkbox-group>
                      </el-form-item>
                    </el-col>
                  </el-row>


              <el-row>
                <el-col :span="10">
                  <el-form-item label="助产机构" label-width="120px">
                    <el-input v-model="editForm.organization" auto-complete="off"></el-input>
                  </el-form-item>
                </el-col>
                <el-col :span="14" >
                  <el-form-item label="出生情况" label-width="120px">
                    <el-select v-model="editForm.state" placeholder="请选择">
                      <el-option key="顺产" label="顺产" value="顺产"></el-option>
                      <el-option key="胎头吸引" label="胎头吸引" value="胎头吸引"></el-option>
                      <el-option key="产钳" label="产钳" value="产钳"></el-option>
                      <el-option key="剖宫" label="剖宫" value="剖宫"></el-option>
                      <el-option key="双头胎" label="双头胎" value="双头胎"></el-option>
                      <el-option key="臀位" label="臀位" value="臀位"></el-option>
                      <el-option key="其他" label="其他" value="其他"></el-option>
                    </el-select>
                  </el-form-item>
                </el-col>
              </el-row>

              <el-row>
                <el-col :span="10">
                  <el-form-item label="新生儿窒息" label-width="120px">
                    <el-radio-group v-model="editForm.stifle">
                      <el-radio label=有>有</el-radio>
                      <el-radio label=无>无</el-radio>
                    </el-radio-group>
                  </el-form-item>
                </el-col>
                <el-col :span="14" >
                  <el-form-item label="畸形" label-width="120px">
                    <el-radio-group v-model="editForm.malformation">
                      <el-radio label=有>有</el-radio>
                      <el-radio label=无>无</el-radio>
                    </el-radio-group>
                  </el-form-item>
                </el-col>
              </el-row>

              <el-form-item label="新生儿听力筛查" label-width="120px">
                <el-radio-group v-model="editForm.listen">
                  <el-radio label=通过>通过</el-radio>
                  <el-radio label=未通过>未通过</el-radio>
                  <el-radio label=未筛查>未筛查</el-radio>
                  <el-radio label=不详>不详</el-radio>
                </el-radio-group>
              </el-form-item>

              <el-form-item label="新生儿疾病筛查" label-width="120px">
                <el-radio-group v-model="editForm.illness">
                  <el-radio label=未进行>未进行</el-radio>
                  <el-radio label=检查均为阴性>检查均为阴性</el-radio>
                  <el-radio label=甲低>甲低</el-radio>
                  <el-radio label=苯丙酮尿症>苯丙酮尿症</el-radio>
                  <el-radio label=其他遗传代谢病>其他遗传代谢病</el-radio>
                </el-radio-group>
              </el-form-item>

              <el-row>
                  <el-col :span="8" >
                    <el-form-item label="新生儿体重" label-width="120px">
                      <el-input v-model="editForm.birthWeight"  auto-complete="off">
                        <template slot="append">kg</template>
                      </el-input>
                    </el-form-item>
                  </el-col>
                  <el-col :span="8" >
                    <el-form-item label="目前体重" label-width="120px">
                      <el-input v-model="editForm.weight"  auto-complete="off">
                        <template slot="append">kg</template>
                      </el-input>
                    </el-form-item>
                  </el-col>
                  <el-col :span="8" >
                    <el-form-item label="出生身长" label-width="120px">
                      <el-input v-model="editForm.birthHeight"  auto-complete="off">
                        <template slot="append">cm</template>
                      </el-input>
                    </el-form-item>
                  </el-col>
              </el-row>

              <el-row>
                <el-col :span="8" >
                  <el-form-item label="喂养方式" label-width="120px">
                    <el-select v-model="editForm.feed" placeholder="请选择">
                      <el-option key="纯母乳" label="纯母乳" value="纯母乳"></el-option>
                      <el-option key="混合" label="混合" value="混合"></el-option>
                      <el-option key="人工" label="人工" value="人工"></el-option>
                    </el-select>
                  </el-form-item>
                </el-col>
                <el-col :span="8" >
                  <el-form-item label="吃奶量" label-width="120px">
                    <el-input v-model="editForm.feedMilk"  auto-complete="off">
                      <template slot="append" style="width: 20px">mL/次</template>
                    </el-input>

                  </el-form-item>
                </el-col>
                <el-col :span="8" >
                  <el-form-item label="吃奶次数" label-width="120px">
                    <el-input v-model="editForm.feedMilkCount"  auto-complete="off">
                      <template slot="append">次/天</template>
                    </el-input>

                  </el-form-item>
                </el-col>
              </el-row>

            </el-tab-pane>
            <el-tab-pane :label="`胎儿详细信息`" name="third">

              <el-row>
                <el-col :span="8">
                  <el-form-item label="呕吐" >
                    <el-radio-group v-model="editForm.emesis">
                      <el-radio label=无>无</el-radio>
                      <el-radio label=有>有</el-radio>
                    </el-radio-group>
                  </el-form-item>
                </el-col>
                <el-col :span="8" >
                  <el-form-item label="大便">
                    <el-select v-model="editForm.shit" placeholder="请选择">
                      <el-option key="糊状" label="糊状" value="糊状"></el-option>
                      <el-option key="稀" label="稀" value="稀"></el-option>
                      <el-option key="其他" label="其他" value="其他"></el-option>
                    </el-select>
                  </el-form-item>
                </el-col>
                <el-col :span="8" >
                  <el-form-item label="大便次数" label-width="120px">
                    <el-input v-model="editForm.shitCount"  auto-complete="off">
                      <template slot="append">次/天</template>
                    </el-input>
                  </el-form-item>
                </el-col>
              </el-row>

              <el-row>
                <el-col :span="8">
                  <el-form-item label="体温">
                    <el-input v-model="editForm.temperature"  auto-complete="off">
                      <template slot="append">℃</template>
                    </el-input>
                  </el-form-item>
                </el-col>
                <el-col :span="8" >
                  <el-form-item label="心率">
                    <el-input v-model="editForm.heart"  auto-complete="off" style="width: 90%" >
                      <template slot="append">次/钟</template>
                    </el-input>
                  </el-form-item>
                </el-col>
                <el-col :span="8" >
                  <el-form-item label="呼吸频率" label-width="120px">
                    <el-input v-model="editForm.breathe"  auto-complete="off">
                      <template slot="append">次/钟</template>
                    </el-input>
                  </el-form-item>
                </el-col>
              </el-row>

              <el-row>
                <el-col :span="10">
                  <el-form-item label="面色">
                    <el-radio-group v-model="editForm.face">
                      <el-radio label=红润>红润</el-radio>
                      <el-radio label=黄染>黄染</el-radio>
                      <el-radio label=其他>其他</el-radio>
                    </el-radio-group>
                  </el-form-item>
                </el-col>
                <el-col :span="13" >
                  <el-form-item label="黄疸部位" label-width="120px">
                    <el-select v-model="editForm.yellow" placeholder="请选择">
                      <el-option key="无" label="无" value="无"></el-option>
                      <el-option key="面部" label="面部" value="面部"></el-option>
                      <el-option key="躯干" label="躯干" value="躯干"></el-option>
                      <el-option key="四肢" label="四肢" value="四肢"></el-option>
                      <el-option key="手足" label="手足" value="手足"></el-option>
                    </el-select>
                  </el-form-item>
                </el-col>
              </el-row>

              <el-row>
                <el-col :span="8">
                  <el-form-item label="前囟" style="width: 300px">
                    <el-input v-model="editForm.bregmaWidth"  auto-complete="off" placeholder="前囟宽度">
                      <template slot="append">cm</template>
                    </el-input>
                  </el-form-item>
                </el-col>
                <el-col :span="8"  >
                  <el-input v-model="editForm.bregmaHeight"  auto-complete="off"  placeholder="前囟高度" style="width: 220px">
                    <template slot="append">cm</template>
                  </el-input>
                </el-col>
              </el-row>

              <el-form-item label="前囟状态"  >
                <el-radio-group v-model="editForm.bregma">
                  <el-radio label=正常>正常</el-radio>
                  <el-radio label=膨隆>膨隆</el-radio>
                  <el-radio label=凹陷>凹陷</el-radio>
                  <el-radio label=其他>其他</el-radio>
                </el-radio-group>
              </el-form-item>

              <el-row>
                <el-col :span="11">
                  <el-form-item label="眼睛">
                    <el-radio-group v-model="editForm.eye">
                      <el-radio label=未见异常>未见异常</el-radio>
                      <el-radio label=异常>异常</el-radio>
                    </el-radio-group>
                  </el-form-item>
                </el-col>
                <el-col :span="13" >
                  <el-form-item label="四肢活动度" label-width="120px">
                    <el-radio-group v-model="editForm.limb">
                      <el-radio label=未见异常>未见异常</el-radio>
                      <el-radio label=异常>异常</el-radio>
                    </el-radio-group>
                  </el-form-item>
                </el-col>
              </el-row>

              <el-row>
                <el-col :span="11">
                  <el-form-item label="耳外观">
                    <el-radio-group v-model="editForm.ear">
                      <el-radio label=未见异常>未见异常</el-radio>
                      <el-radio label=异常>异常</el-radio>
                    </el-radio-group>
                  </el-form-item>
                </el-col>
                <el-col :span="13" >
                  <el-form-item label="颈部包块" label-width="120px">
                    <el-radio-group v-model="editForm.neck">
                      <el-radio label=无>无</el-radio>
                      <el-radio label=有>有</el-radio>
                    </el-radio-group>
                  </el-form-item>
                </el-col>
              </el-row>


              <el-row>
                <el-col :span="11">
                  <el-form-item label="鼻">
                    <el-radio-group v-model="editForm.nose">
                      <el-radio label=未见异常>未见异常</el-radio>
                      <el-radio label=异常>异常</el-radio>
                    </el-radio-group>
                  </el-form-item>
                </el-col>
                <el-col :span="13" >
                  <el-form-item label="皮肤" label-width="120px">
                    <el-radio-group v-model="editForm.skin">
                      <el-radio label=未见异常>未见异常</el-radio>
                      <el-radio label=湿疹>湿疹</el-radio>
                      <el-radio label=糜烂>糜烂</el-radio>
                      <el-radio label=其他>其他</el-radio>
                    </el-radio-group>
                  </el-form-item>
                </el-col>
              </el-row>

              <el-row>
                <el-col :span="11">
                  <el-form-item label="口腔">
                    <el-radio-group v-model="editForm.oral">
                      <el-radio label=未见异常>未见异常</el-radio>
                      <el-radio label=异常>异常</el-radio>
                    </el-radio-group>
                  </el-form-item>
                </el-col>
                <el-col :span="13" >
                  <el-form-item label="肛门" label-width="120px">
                    <el-radio-group v-model="editForm.anus">
                      <el-radio label=未见异常>未见异常</el-radio>
                      <el-radio label=异常>异常</el-radio>
                    </el-radio-group>
                  </el-form-item>
                </el-col>
              </el-row>

              <el-row>
                <el-col :span="11">
                  <el-form-item label="心肺听诊">
                    <el-radio-group v-model="editForm.heartLung">
                      <el-radio label=未见异常>未见异常</el-radio>
                      <el-radio label=异常>异常</el-radio>
                    </el-radio-group>
                  </el-form-item>
                </el-col>
                <el-col :span="13" >
                  <el-form-item label="胸部" label-width="120px">
                    <el-radio-group v-model="editForm.chest">
                      <el-radio label=未见异常>未见异常</el-radio>
                      <el-radio label=异常>异常</el-radio>
                    </el-radio-group>
                  </el-form-item>
                </el-col>
              </el-row>

              <el-row>
                <el-col :span="11">
                  <el-form-item label="腹部触诊">
                    <el-radio-group v-model="editForm.abdomen">
                      <el-radio label=未见异常>未见异常</el-radio>
                      <el-radio label=异常>异常</el-radio>
                    </el-radio-group>
                  </el-form-item>
                </el-col>
                <el-col :span="13" >
                  <el-form-item label="颈柱" label-width="120px">
                    <el-radio-group v-model="editForm.vertebra">
                      <el-radio label=未见异常>未见异常</el-radio>
                      <el-radio label=异常>异常</el-radio>
                    </el-radio-group>
                  </el-form-item>
                </el-col>
              </el-row>

              <el-row>
                <el-col :span="11">
                  <el-form-item label="外生殖器">
                    <el-radio-group v-model="editForm.getPudendum">
                      <el-radio label=未见异常>未见异常</el-radio>
                      <el-radio label=异常>异常</el-radio>
                    </el-radio-group>
                  </el-form-item>
                </el-col>
                <el-col :span="13">
                  <el-form-item label="脐带" label-width="120px">
                    <el-radio-group v-model="editForm.getUmbilical">
                      <el-radio label=未脱>未脱</el-radio>
                      <el-radio label=脱落>脱落</el-radio>
                      <el-radio label=脐部有渗出>脐部有渗出</el-radio>
                      <el-radio label=其他>其他</el-radio>
                    </el-radio-group>
                  </el-form-item>
                </el-col>
              </el-row>

            </el-tab-pane>
            <el-tab-pane :label="`最后结论`" name="fourth">

              <el-row>
                <el-col :span="8">
                  <el-form-item label="转诊建议机构科室" label-width="130px">
                    <el-radio-group v-model="editForm.referral">
                      <el-radio label=有>有</el-radio>
                      <el-radio label=无>无</el-radio>
                    </el-radio-group>
                  </el-form-item>
                </el-col>
                <el-col :span="16" >
                  <el-form-item label="原因">
                    <el-input  v-model="editForm.reason" auto-complete="off" ></el-input>
                  </el-form-item>
                </el-col>
              </el-row>

        <el-form-item label="指导" label-width="130px">
          <el-checkbox-group v-model="editForm.guidance"a style="width:100%">
            <el-checkbox v-for="item in guidance" :key="item.label" :name="type" :label="item.label" :value="item.value"></el-checkbox>
          </el-checkbox-group>
        </el-form-item>

              <el-row>
                <el-col :span="12">
                  <el-form-item label="本次访问日期" label-width="130px">
                    <el-date-picker type="date"  v-model="editForm.followDate" style="width: 100%"></el-date-picker>
                  </el-form-item>
                </el-col>
                <el-col :span="12">
                  <el-form-item label="下次随访地点" label-width="130px">
                    <el-input  v-model="editForm.nextVisitAddr" auto-complete="off" style="width: 100%" ></el-input>
                  </el-form-item>
                </el-col>
              </el-row>

              <el-row>
                <el-col :span="12">
                  <el-form-item label="下次访问日期" label-width="130px">
                    <el-date-picker type="date"  v-model="editForm.nextDate" style="width: 100%"></el-date-picker>
                  </el-form-item>
                </el-col>
                <el-col :span="12" >
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
    addUser
  } from '@/api/NewbornVisit'

  export default {
    data() {
      return {
        edit: true,
        tableHeight: window.innerHeight - 220,
        motherState: [
          { value: '无', label: '无' },
          { value: '糖尿病', label: '糖尿病' },
          { value: '妊娠高血压', label: '妊娠高血压' },
          { value: '其他', label: '其他' }
        ],
        guidance: [
          { value: '喂养指导', label: '喂养指导' },
          { value: '发育指导', label: '发育指导' },
          { value: '防病指导', label: '防病指导' },
          { value: '预防伤害指导', label: '预防伤害指导' },
          { value: '口腔保健指导', label: '口腔保健指导' },
          { value: '其他', label: '其他' }
        ],
        tableMeight: window.innerHeight - 260,
        activeName: 'first',
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
          startTime: '',
          endTime: ''
        },
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
          peopleId: [
            { required: true, message: '请输入身份证号', trigger: 'blur' },
            { pattern: /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/, message: '你的身份证格式不正确' }],
          number: [
            { type: 'number', message: '必须为数字值', trigger: 'blur' }
          ]
        },

        // 编辑界面数据
        editForm: {
          motherState: [],
          guidance: []
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
          '    <h2 style="width:794px;text-align:center"> 新生儿家庭访问记录表 </h2>  \n' +
          '    <span style="margin-left:34px;">姓    名：{{name}}</span>\n' +
          '    <span style="margin-left:510px;">编号:{{number}}</span> \n' +
          '    </div>\n' +
          '  <table id="table"  border="1"  cellspacing="0" bordercolor="#000000" style="width:730px;margin-left:30px;margin-top:5px;border-collapse:collapse;"  width = "80%">\n' +
          '    <tbody>\n' +
          '      <tr>\n' +
          '        <td style="width: 10%;text-align:center">性别</td>\n' +
          '        <td colspan=\'1\'>{{sex}}</td>\n' +
          '        <td style="width: 20%;text-align:center">出生日期</td>\n' +
          '        <td colspan=\'3\'>{{birthdate}}</td>\n' +
          '      </tr>\n' +
          '\t  <tr>\n' +
          '        <td style="width: 10%;text-align:center">身份证号</td>\n' +
          '        <td colspan=\'1\'>{{peopleId}}</td>\n' +
          '        <td style="width: 20%;text-align:center">家庭住址</td>\n' +
          '        <td colspan=\'3\'>{{addr}}</td>\n' +
          '      </tr>\n' +
          '\t  <tr>\n' +
          '        <td style="width: 10%;text-align:center">父亲</td>\n' +
          '        <td colspan=\'1\'>姓名:{{fatherName}}</td>\n' +
          '\t\t<td colspan=\'1\'>职业：{{fatherJob}}</td>\n' +
          '\t\t<td colspan=\'2\'>联系方式：{{fatherPhone}}</td>\n' +
          '\t\t<td colspan=\'1\'>出生日期：{{fatherBirthdate}}</td>\n' +
          '      </tr>\n' +
          '\t  <tr>\n' +
          '        <td style="width: 10%;text-align:center">母亲</td>\n' +
          '        <td colspan=\'1\'>姓名:{{motherName}}</td>\n' +
          '\t\t<td colspan=\'1\'>职业：{{motherJob}}</td>\n' +
          '\t\t<td colspan=\'2\'>联系方式：{{motherPhone}}</td>\n' +
          '\t\t<td colspan=\'1\'>出生日期：{{motherBirthdate}}</td>\n' +
          '      </tr>\n' +
          '\t  <tr>\n' +
          '       <td style="width: 10%;text-align:center">出生孕周</td>\n' +
          '        <td colspan=\'1\'>{{week}}周</td>\n' +
          '       <td style="width: 10%;text-align:center">母亲怀孕期患病情况</td>\n' +
          '        <td colspan=\'3\'>{{motherState}}</td>\n' +
          '      </tr>\n' +
          '\t  <tr>\n' +
          '        <td style="width: 20%;text-align:center">助产机构名称:</td>\n' +
          '        <td colspan=\'2\'>{{organization}}</td>\n' +
          '        <td style="width: 20%;text-align:center">出生情况:</td>\n' +
          '        <td colspan=\'2\'>{{state}}</td>\n' +
          '      </tr>\n' +
          '\t  <tr>\n' +
          '        <td style="width: 10%;text-align:center">新生儿窒息:</td>\n' +
          '        <td colspan=\'2\'>{{stifle}}</td>\n' +
          '        <td style="width: 10%;text-align:center">畸形:</td>\n' +
          '        <td colspan=\'2\'>{{malformation}}</td>\n' +
          '      </tr>\n' +
          '\t  <tr>\n' +
          '        <td style="width: 10%;text-align:center">新生儿听力筛查</td>\n' +
          '        <td colspan=\'5\'>{{listen}}</td>\n' +
          '      </tr>\n' +
          '\t  <tr>\n' +
          '        <td style="width: 10%;text-align:center">新生儿疾病筛查</td>\n' +
          '        <td colspan=\'5\'>{{illness}}</td>\n' +
          '      </tr>\n' +
          '\t  <tr>\n' +
          '        <td style="width: 10%;text-align:center">新生儿的体重</td>\n' +
          '        <td colspan=\'1\'>{{birthWeight}}  kg</td>\n' +
          '        <td style="width: 5%;text-align:center">目前体重</td>\n' +
          '        <td colspan=\'1\'>{{weight}}  kg</td>\n' +
          '        <td style="width: 10%;text-align:center">出生生长</td>\n' +
          '        <td colspan=\'1\'>{{birthHeight}}  cm</td>\n' +
          '      </tr>\n' +
          '\t  <tr>\n' +
          '        <td style="width: 10%;text-align:center">喂养方式</td>\n' +
          '        <td colspan=\'1\'>{{feed}}</td>\n' +
          '        <td style="width: 5%;text-align:center">吃奶量</td>\n' +
          '        <td colspan=\'1\'>{{feedMilk}}  ml/次</td>\n' +
          '        <td style="width: 10%;text-align:center">吃奶次数</td>\n' +
          '        <td colspan=\'1\'>{{feedMilkCount}}  次/日</td>\n' +
          '      </tr>\n' +
          '\t  <tr>\n' +
          '        <td style="width: 10%;text-align:center">呕吐</td>\n' +
          '        <td colspan=\'1\'>{{emesis}}</td>\n' +
          '        <td style="width: 5%;text-align:center">大便</td>\n' +
          '        <td colspan=\'1\'>{{shit}}</td>\n' +
          '        <td style="width: 10%;text-align:center">大便次数</td>\n' +
          '        <td colspan=\'1\'>{{shitCount}}  次/日</td>\n' +
          '      </tr>\n' +
          '\t  <tr>\n' +
          '        <td style="width: 10%;text-align:center">体温</td>\n' +
          '        <td colspan=\'1\'>{{temperature}}  ℃</td>\n' +
          '        <td style="width: 5%;text-align:center">心率</td>\n' +
          '        <td colspan=\'1\'>{{heart}}  次/分钟</td>\n' +
          '        <td style="width: 10%;text-align:center">呼吸频率</td>\n' +
          '        <td colspan=\'1\'>{{breathe}}  次/分钟</td>\n' +
          '      </tr>\n' +
          '\t  <tr>\n' +
          '       <td style="width: 10%;text-align:center">面色</td>\n' +
          '        <td colspan=\'1\'>{{face}}</td>\n' +
          '       <td style="width: 10%;text-align:center">黄疸部位</td>\n' +
          '        <td colspan=\'3\'>{{yellow}}</td>\n' +
          '      </tr>\n' +
          '\t  <tr>\n' +
          '       <td style="width: 10%;text-align:center">前囟</td>\n' +
          '        <td colspan=\'1\'>{{bregmaWidth}}cm*</td>\n' +
          '        <td colspan=\'1\'>{{bregmaHeight}}cm</td>\n' +
          '       <td style="width: 10%;text-align:center">前囟状态</td>\n' +
          '        <td colspan=\'2\'>{{bregma}}</td>\n' +
          '      </tr>\n' +
          '\t  <tr>\n' +
          '        <td style="width: 10%;text-align:center">眼睛</td>\n' +
          '        <td colspan=\'2\'>{{eye}}</td>\n' +
          '        <td style="width: 10%;text-align:center">四肢活动度</td>\n' +
          '        <td colspan=\'2\'>{{limb}}</td>\n' +
          '      </tr>\n' +
          '\t  <tr>\n' +
          '        <td style="width: 10%;text-align:center">耳外观</td>\n' +
          '        <td colspan=\'2\'>{{ear}}</td>\n' +
          '        <td style="width: 10%;text-align:center">颈部包块</td>\n' +
          '        <td colspan=\'2\'>{{neck}}</td>\n' +
          '      </tr>\n' +
          '\t  <tr>\n' +
          '        <td style="width: 10%;text-align:center">鼻</td>\n' +
          '        <td colspan=\'2\'>{{nose}}</td>\n' +
          '        <td style="width: 10%;text-align:center">皮肤</td>\n' +
          '        <td colspan=\'2\'>{{skin}}</td>\n' +
          '      </tr>\n' +
          '\t  <tr>\n' +
          '        <td style="width: 10%;text-align:center">口腔</td>\n' +
          '        <td colspan=\'2\'>{{oral}}</td>\n' +
          '        <td style="width: 10%;text-align:center">肛门</td>\n' +
          '        <td colspan=\'2\'>{{anus}}</td>\n' +
          '      </tr>\n' +
          '\t  <tr>\n' +
          '        <td style="width: 10%;text-align:center">心肺听诊</td>\n' +
          '        <td colspan=\'2\'>{{heartLung}}</td>\n' +
          '        <td style="width: 10%;text-align:center">胸部</td>\n' +
          '        <td colspan=\'2\'>{{chest}}</td>\n' +
          '      </tr>\n' +
          '\t  <tr>\n' +
          '        <td style="width: 10%;text-align:center">腹部触诊</td>\n' +
          '        <td colspan=\'2\'>{{abdomen}}</td>\n' +
          '        <td style="width: 10%;text-align:center">脊柱</td>\n' +
          '        <td colspan=\'2\'>{{vertebra}}</td>\n' +
          '      </tr>\n' +
          '\t  <tr>\n' +
          '        <td style="width: 10%;text-align:center">外生殖器</td>\n' +
          '        <td colspan=\'2\'>{{umbilical}}</td>\n' +
          '        <td style="width: 10%;text-align:center">脐带</td>\n' +
          '        <td colspan=\'2\'>{{pudendum}}</td>\n' +
          '      </tr>\n' +
          '\t  <tr>\n' +
          '        <td style="width: 10%;text-align:center">转诊建议</td>\n' +
          '        <td colspan=\'1\'>{{referral}}</td>\n' +
          '        <td style="width: 10%;text-align:center">原因</td>\n' +
          '        <td colspan=\'3\'>{{reason}}</td>\n' +
          '\t  <tr>\n' +
          '        <td style="width: 10%;text-align:center">指导</td>\n' +
          '        <td colspan=\'5\'>{{guidance}}</td>\n' +
          '      </tr>\n' +
          '\t  <tr>\n' +
          '        <td style="width: 10%;text-align:center">本次访视日期</td>\n' +
          '        <td colspan=\'2\'>{{followDate}}</td>\n' +
          '        <td style="width: 10%;text-align:center">下次随访地点</td>\n' +
          '        <td colspan=\'2\'>{{nextVisitAddr}}</td>\n' +
          '      </tr>\n' +
          '\t  <tr>\n' +
          '        <td style="width: 10%;text-align:center">下次随访日期</td>\n' +
          '        <td colspan=\'2\'>{{nextDate}}</td>\n' +
          '        <td style="width: 10%;text-align:center">随访医生签名</td>\n' +
          '        <td colspan=\'2\'>{{doctor}}</td>\n' +
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
        str = str.replace(/{{sex}}/, this.editForm.sex ? this.editForm.sex : '')
        str = str.replace(/{{birthdate}}/, this.editForm.birthdate ? this.editForm.birthdate : '')
        str = str.replace(/{{peopleId}}/, this.editForm.peopleId ? this.editForm.peopleId : '')
        str = str.replace(/{{addr}}/, this.editForm.addr ? this.editForm.addr : '')
        str = str.replace(/{{fatherName}}/, this.editForm.fatherName ? this.editForm.fatherName : '')
        str = str.replace(/{{fatherJob}}/, this.editForm.fatherJob ? this.editForm.fatherJob : '')
        str = str.replace(/{{fatherPhone}}/, this.editForm.fatherPhone ? this.editForm.fatherPhone : '')
        str = str.replace(/{{fatherBirthdate}}/, this.editForm.fatherBirthdate ? this.editForm.fatherBirthdate : '')
        str = str.replace(/{{motherName}}/, this.editForm.motherName ? this.editForm.motherName : '')
        str = str.replace(/{{motherJob}}/, this.editForm.motherJob ? this.editForm.motherJob : '')
        str = str.replace(/{{motherPhone}}/, this.editForm.motherPhone ? this.editForm.motherPhone : '')
        str = str.replace(/{{motherBirthdate}}/, this.editForm.motherBirthdate ? this.editForm.motherBirthdate : '')
        str = str.replace(/{{week}}/, this.editForm.week ? this.editForm.week : '')
        str = str.replace(/{{motherState}}/, this.editForm.motherState ? this.editForm.motherState : '')
        str = str.replace(/{{organization}}/, this.editForm.organization ? this.editForm.organization : '')

        str = str.replace(/{{state}}/, this.editForm.state ? this.editForm.state : '')
        str = str.replace(/{{stifle}}/, this.editForm.stifle ? this.editForm.stifle : '')
        str = str.replace(/{{malformation}}/, this.editForm.malformation ? this.editForm.malformation : '')
        str = str.replace(/{{listen}}/, this.editForm.listen ? this.editForm.listen : '')
        str = str.replace(/{{illness}}/, this.editForm.illness ? this.editForm.illness : '')
        str = str.replace(/{{birthWeight}}/, this.editForm.birthWeight ? this.editForm.birthWeight : '')
        str = str.replace(/{{weight}}/, this.editForm.weight ? this.editForm.weight : '')
        str = str.replace(/{{birthHeight}}/, this.editForm.birthHeight ? this.editForm.birthHeight : '')
        str = str.replace(/{{feed}}/, this.editForm.feed ? this.editForm.feed : '')
        str = str.replace(/{{feedMilk}}/, this.editForm.feedMilk ? this.editForm.feedMilk : '')
        str = str.replace(/{{feedMilkCount}}/, this.editForm.feedMilkCount ? this.editForm.feedMilkCount : '')

        str = str.replace(/{{emesis}}/, this.editForm.emesis ? this.editForm.emesis : '')
        str = str.replace(/{{shit}}/, this.editForm.shit ? this.editForm.shit : '')
        str = str.replace(/{{shitCount}}/, this.editForm.shitCount ? this.editForm.shitCount : '')
        str = str.replace(/{{temperature}}/, this.editForm.temperature ? this.editForm.temperature : '')
        str = str.replace(/{{heart}}/, this.editForm.heart ? this.editForm.heart : '')
        str = str.replace(/{{breathe}}/, this.editForm.breathe ? this.editForm.breathe : '')
        str = str.replace(/{{face}}/, this.editForm.face ? this.editForm.face : '')
        str = str.replace(/{{yellow}}/, this.editForm.yellow ? this.editForm.yellow : '')
        str = str.replace(/{{bregma}}/, this.editForm.bregma ? this.editForm.bregma : '')
        str = str.replace(/{{bregmaWidth}}/, this.editForm.bregmaWidth ? this.editForm.bregmaWidth : '')
        str = str.replace(/{{bregmaHeight}}/, this.editForm.bregmaHeight ? this.editForm.bregmaHeight : '')

        str = str.replace(/{{eye}}/, this.editForm.eye ? this.editForm.eye : '')
        str = str.replace(/{{limb}}/, this.editForm.limb ? this.editForm.limb : '')
        str = str.replace(/{{ear}}/, this.editForm.ear ? this.editForm.ear : '')
        str = str.replace(/{{neck}}/, this.editForm.neck ? this.editForm.neck : '')
        str = str.replace(/{{nose}}/, this.editForm.nose ? this.editForm.nose : '')
        str = str.replace(/{{skin}}/, this.editForm.skin ? this.editForm.skin : '')
        str = str.replace(/{{oral}}/, this.editForm.oral ? this.editForm.oral : '')
        str = str.replace(/{{anus}}/, this.editForm.anus ? this.editForm.anus : '')
        str = str.replace(/{{heartLung}}/, this.editForm.heartLung ? this.editForm.heartLung : '')
        str = str.replace(/{{chest}}/, this.editForm.chest ? this.editForm.chest : '')
        str = str.replace(/{{abdomen}}/, this.editForm.abdomen ? this.editForm.abdomen : '')

        str = str.replace(/{{vertebra}}/, this.editForm.vertebra ? this.editForm.vertebra : '')
        str = str.replace(/{{vertebra}}/, this.editForm.vertebra ? this.editForm.vertebra : '')
        str = str.replace(/{{pudendum}}/, this.editForm.malformation ? this.editForm.pudendum : '')
        str = str.replace(/{{referral}}/, this.editForm.referral ? this.editForm.referral : '')
        str = str.replace(/{{reason}}/, this.editForm.reason ? this.editForm.reason : '')
        str = str.replace(/{{guidance}}/, this.editForm.guidance ? this.editForm.guidance : '')
        str = str.replace(/{{followDate}}/, this.editForm.followDate ? this.editForm.followDate : '')
        str = str.replace(/{{nextVisitAddr}}/, this.editForm.nextVisitAddr ? this.editForm.nextVisitAddr : '')
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
      search() {
        this.page = 1
        this.getUsers()
      },
      // 显示编辑界面
      handleEdit(index, row) {
        this.dialogStatus = 'update'
        this.dialogFormVisible = true
        this.edit = true
        this.editForm = Object.assign({}, row)
        this.editForm.motherState = this.editForm.motherState.split(',')
        this.editForm.guidance = this.editForm.guidance.split(',')
      },
      handleRowChange(row, event) {
        this.dialogStatus = 'detail'
        this.dialogFormVisible = true
        this.editForm = Object.assign({}, row)
        this.editForm.motherState = this.editForm.motherState.split(',')
        this.editForm.guidance = this.editForm.guidance.split(',')
        this.edit = false
      },
      // 显示新增界面
      handleAdd() {
        this.dialogStatus = 'create'
        this.edit = false
        this.dialogFormVisible = true
        this.editForm = {
          id: 0,
          shitCount: 0,
          feedMilkCount: 0,
          heart: 0,
          breathe: 0,
          motherState: [],
          guidance: []
        }
      },
      handprint() {
        this.dialogStatus = 'print'
        this.dialogFormVisible2 = true
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

                para.birthdate =
                  !para.birthdate || para.birthdate === ''
                    ? null
                    : util.formatDate.format(new Date(para.birthdate), 'yyyy-MM-dd')

                para.fatherBirthdate =
                  !para.fatherBirthdate || para.fatherBirthdate === ''
                    ? null
                    : util.formatDate.format(new Date(para.fatherBirthdate), 'yyyy-MM-dd')

                para.motherBirthdate =
                  !para.motherBirthdate || para.motherBirthdate === ''
                    ? null
                    : util.formatDate.format(new Date(para.motherBirthdate), 'yyyy-MM-dd')

                para.nextDate =
                  !para.nextDate || para.nextDate === ''
                    ? null
                    : util.formatDate.format(new Date(para.nextDate), 'yyyy-MM-dd')

                para.followDate =
                  !para.followDate || para.followDate === ''
                    ? null
                    : util.formatDate.format(new Date(para.followDate), 'yyyy-MM-dd')

                para.motherState = ''
                for (const itm of this.editForm.motherState) {
                  para.motherState += itm
                  para.motherState += ','
                }
                para.motherState = para.motherState.substr(0, para.motherState.length - 1)

                para.guidance = ''
                for (const itm of this.editForm.guidance) {
                  para.guidance += itm
                  para.guidance += ','
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

                para.birthdate =
                  !para.birthdate || para.birthdate === ''
                    ? null
                    : util.formatDate.format(new Date(para.birthdate), 'yyyy-MM-dd')

                para.fatherBirthdate =
                  !para.fatherBirthdate || para.fatherBirthdate === ''
                    ? null
                    : util.formatDate.format(new Date(para.fatherBirthdate), 'yyyy-MM-dd')

                para.motherBirthdate =
                  !para.motherBirthdate || para.motherBirthdate === ''
                    ? null
                    : util.formatDate.format(new Date(para.motherBirthdate), 'yyyy-MM-dd')

                para.nextDate =
                  !para.nextDate || para.nextDate === ''
                    ? null
                    : util.formatDate.format(new Date(para.nextDate), 'yyyy-MM-dd')

                para.followDate =
                  !para.followDate || para.followDate === ''
                    ? null
                    : util.formatDate.format(new Date(para.followDate), 'yyyy-MM-dd')

                para.motherState = ''
                for (const itm of this.editForm.motherState) {
                  para.motherState += itm
                  para.motherState += ','
                }
                para.motherState = para.motherState.substr(0, para.motherState.length - 1)

                para.guidance = ''
                for (const itm of this.editForm.guidance) {
                  para.guidance += itm
                  para.guidance += ','
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
