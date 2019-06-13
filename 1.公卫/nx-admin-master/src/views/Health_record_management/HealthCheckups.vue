<template xmlns:el-col="http://www.w3.org/1999/html">
  <section class="app-container">

    <!--工作条-->
    <el-col :span="24" class="toolbar" style="padding-bottom: 0px;">
      <el-form :inline="true" :model="filters" @submit.native.prevent>
        <el-form-item>
          <el-input v-model="filters.name" placeholder="姓名"></el-input>
        </el-form-item>
        <el-form-item>
          <el-input v-model="filters.peopleId" placeholder="身份证号"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" v-on:click="getUsers" style="background-color:#4db2ee;border-color:transparent" icon="el-icon-search">查询</el-button>
        </el-form-item>
        <el-form-item>
          <el-button type="primary"  v-if="buttons.P_C" @click="handleAdd" icon="el-icon-circle-plus-outline">新增</el-button>
        </el-form-item>
      </el-form>
    </el-col>
    <!--列表-->
    <div >
      <el-table :data="users" :height="tableHeight" highlight-current-row @selection-change="selsChange" border style="width: 100%;"center @row-dblclick="handleRowChange">
        <el-table-column type="index" label="序号" width="60">
        </el-table-column>
        <el-table-column prop="name" label="姓名" width="120">
        </el-table-column>
        <el-table-column prop="sex" label="性别" width="60" :formatter="formatSex">
        </el-table-column>
        <el-table-column prop="peopleId" label="身份证号" width="170">
        </el-table-column>
        <el-table-column prop="doctor" label="体检医生" min-width="120">
        </el-table-column>
        <el-table-column prop="checkDate" label="体检日期" min-width="120">
        </el-table-column>
        <el-table-column label="操作" width="150">
          <template slot-scope="scope">
            <a size="14px" v-if="buttons.HRH_E" @click="handleEdit(scope.$index, scope.row)" style="color:#4DB2EE;margin-right:20px;">编辑</a>
            <a size="14px" v-if="buttons.HRH_D" @click="handleDel(scope.$index, scope.row)" style="color:#FB4141">删除</a>
          </template>
        </el-table-column>
      </el-table>
    </div>
    <!--工具条-->

    <el-col :span="24" class="toolbar">
      <!-- <el-button type="danger" @click="batchRemove" :disabled="this.sels.length===0">批量删除</el-button>-->
      <el-pagination layout="prev, pager, next" @current-change="handleCurrentChange" :page-size="20" :total="total" style="float:right;">
      </el-pagination>
    </el-col>


    <!--编辑界面-->

    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible" :close-on-click-modal="false"center>
      <div id="printData">
        <el-form :model="editForm" label-width="80px" :rules="editFormRules" ref="editForm">
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
                      <td colspan="3" style="text-align:center">
                        <el-form-item label="" label-width="0px">
                          <el-input v-model="editForm.creator" style="width: 100%" v-if="dialogStatus==='detail'" readonly></el-input>
                        </el-form-item>
                      </td>
                      <td style="word-wrap:break-word;text-align:center;">
                        创建时间
                      </td>
                      <td colspan="3" style="text-align:center">
                        <el-form-item label="" label-width="0px" v-if="dialogStatus==='detail'">
                          <el-date-picker type="date" placeholder="选择日期" v-model="editForm.createDate" style="width: 100%" v-if="dialogStatus==='detail'" readonly></el-date-picker>
                        </el-form-item>
                      </td>
                    </tr>
                    <tr v-if="dialogStatus==='detail'">
                      <td style="word-wrap:break-word;text-align:center;">
                        修改人
                      </td>
                      <td colspan="3" style="text-align:center">
                        <el-form-item label="" label-width="0px" v-if="dialogStatus==='detail'">
                          <el-input v-model="editForm.modifier" style="width: 100%" v-if="dialogStatus==='detail'" readonly></el-input>
                        </el-form-item>
                      </td>
                      <td style="word-wrap:break-word;text-align:center;">
                        修改时间
                      </td>
                      <td colspan="3" style="text-align:center">
                        <el-form-item label="" label-width="0px" v-if="dialogStatus==='detail'">
                          <el-date-picker type="date" placeholder="选择日期" v-model="editForm.modifyDate" style="width: 100%" v-if="dialogStatus==='detail'" readonly></el-date-picker>
                        </el-form-item>
                      </td>
                    </tr>
                    <tr>
                      <td width="140px" scope="row">体检日期</td>
                      <td colspan="3"class="pad10">
                        <el-form-item label=""label-width="0px">
                          <el-date-picker style="width: 100%" type="date" placeholder="选择日期" v-model="editForm.checkDate"></el-date-picker>
                        </el-form-item>
                      </td>
                      <td  width="140px" colspan="2" scope="row" class="pad"><span>*</span>体检医生</td>
                      <td colspan="4"class="pad10">
                        <el-form-item label=""label-width="0px" prop="doctor">
                          <el-input v-model="editForm.doctor" auto-complete="off"></el-input>
                        </el-form-item>
                      </td>
                    </tr>
                    <tr>
                      <td width="140px" scope="row" class="pad"><span>*</span>姓名</td>
                      <td colspan="3"class="pad10">
                        <el-form-item label=""label-width="0px" prop="name" disabled>
                          <el-input v-model="editForm.name" auto-complete="off"></el-input>
                        </el-form-item>
                      </td>
                      <td  width="140px" colspan="2" scope="row" class="pad"><span>*</span>身份证号</td>
                      <td colspan="4"class="pad10">
                        <el-form-item label=""label-width="0px"prop="peopleId">
                          <el-input  v-model="editForm.peopleId" auto-complete="off"  v-on:input="inputFunc1"></el-input>
                        </el-form-item>
                      </td>
                    </tr>
                    <tr>
                      <td  width="140px" scope="row">内容</td>
                      <td colspan="9">检查项目</td>
                    </tr>

                    <tr>
                      <td  width="140px" scope="row">症状</td>
                      <td colspan="9"style="text-align:left" class="pad20">
                        <el-form-item label=""label-width="0px" >
                          <el-checkbox-group v-model="editForm.symptoms" style="width:100%">
                            <table>
                              <tr>
                                <td style="width: 16%;" ><el-checkbox label="无症状" name="type"></el-checkbox></td>
                                <td style="width: 16%;"><el-checkbox label="头痛" name="type"></el-checkbox></td>
                                <td style="width: 16%;" ><el-checkbox label="头晕" name="type"></el-checkbox></td>
                                <td style="width: 16%;" ><el-checkbox label="心悸" name="type"></el-checkbox></td>
                                <td style="width: 16%;" ><el-checkbox label="胸闷" name="type"></el-checkbox></td>
                                <td style="width: 16%;" ><el-checkbox label="胸痛" name="type"></el-checkbox></td>

                                <td style="width: 16%;" ><el-checkbox label="慢性咳嗽" name="type"></el-checkbox></td>
                              </tr>
                              <tr>
                                <td style="width: 16%;" ><el-checkbox label="咳痰" name="type"></el-checkbox></td>
                                <td style="width: 16%;"><el-checkbox label="呼吸困难" name="type"></el-checkbox></td>
                                <td style="width: 16%;" ><el-checkbox label="多饮" name="type"></el-checkbox></td>
                                <td style="width: 16%;"><el-checkbox label="多尿" name="type"></el-checkbox></td>
                                <td style="width: 16%;" ><el-checkbox label="体重下降" name="type"></el-checkbox></td>

                                <td style="width: 16%;" ><el-checkbox label="乏力" name="type"></el-checkbox></td>

                                <td style="width: 16%;" ><el-checkbox label="关节肿痛" name="type"></el-checkbox></td>
                              </tr>
                              <tr>
                                <td style="width: 16%;" ><el-checkbox label="视力模糊" name="type"></el-checkbox></td>
                                <td style="width: 16%;" ><el-checkbox label="手脚麻木" name="type"></el-checkbox></td>
                                <td style="width: 16%;" ><el-checkbox label="尿急" name="type"></el-checkbox></td>
                                <td style="width: 16%;" ><el-checkbox label="尿痛" name="type"></el-checkbox></td>

                                <td style="width: 16%;" ><el-checkbox label="便秘" name="type"></el-checkbox></td>
                                <td style="width: 16%;" ><el-checkbox label="腹泻" name="type"></el-checkbox></td>

                                <td style="width: 16%;" ><el-checkbox label="眼花" name="type"></el-checkbox></td>
                              </tr>
                              <tr>
                                <td style="width: 16%;" ><el-checkbox label="耳鸣" name="type"></el-checkbox></td>
                                <td style="width: 16%;" ><el-checkbox label="恶心呕吐" name="type"></el-checkbox></td>
                                <td style="width: 16%;" ><el-checkbox label="乳房胀痛" name="type"></el-checkbox></td>

                                <td style="width: 16%;"><el-checkbox label="其他" name="type"></el-checkbox></td>
                              </tr>
                              <tr>
                              </tr>
                            </table>
                          </el-checkbox-group>
                        </el-form-item>
                      </td>
                    </tr>

                    <tr>
                      <td  width="140px" rowspan="9" scope="row">一般情况</td>
                      <td width="97">体温</td>
                      <td colspan="2"class="pad10">
                        <el-form-item label=""label-width="0px">
                          <el-input v-model="editForm.temperature">
                            <template slot="append">℃</template>
                          </el-input>
                        </el-form-item>
                      </td>
                      <td colspan="2">脉率</td>
                      <td colspan="4"class="pad10">
                        <el-form-item label=""label-width="0px">
                          <el-input v-model="editForm.pulseRate" >
                            <template slot="append">次/分钟</template>
                          </el-input>
                        </el-form-item>
                      </td>
                    </tr>
                    <tr>
                      <td rowspan="2">呼吸频率</td>
                      <td colspan="2" rowspan="2"class="pad10">
                        <el-form-item label=""label-width="0px">
                          <el-input v-model="editForm.breathing" >
                            <template slot="append">次/分钟</template>
                          </el-input>
                        </el-form-item>
                      </td>
                      <td colspan="2" rowspan="2" scope="row">血压</td>
                      <td colspan="2">左侧</td>
                      <td colspan="2"class="pad10">
                        <el-form-item label=""label-width="0px">
                          <el-input v-model="editForm.pressureleft" auto-complete="off">
                            <template slot="append">mmhg</template>
                          </el-input>
                        </el-form-item>
                      </td>
                    </tr>
                    <tr>
                      <td colspan="2">右侧</td>
                      <td colspan="2"class="pad10">
                        <el-form-item label=""label-width="0px">
                          <el-input v-model="editForm.pressureright" auto-complete="off">
                            <template slot="append">mmhg</template>
                          </el-input>
                        </el-form-item>
                      </td>
                    </tr>
                    <tr>
                      <td>身高</td>
                      <td colspan="2"class="pad10">
                        <el-form-item label=""label-width="0px">
                          <el-input v-model="editForm.stature" >
                            <template slot="append">cm</template>
                          </el-input>
                        </el-form-item>
                      </td>
                      <td colspan="2" scope="row">体重</td>
                      <td colspan="4"class="pad10">
                        <el-form-item label=""label-width="0px">
                          <el-input v-model="editForm.weight" >
                            <template slot="append">kg</template>
                          </el-input>
                        </el-form-item>
                      </td>
                    </tr>
                    <tr>
                      <td>腰围</td>
                      <td colspan="2"class="pad10">
                        <el-form-item label=""label-width="0px">
                          <el-input v-model="editForm.waistline" auto-complete="off">
                            <template slot="append">cm</template>
                          </el-input>
                        </el-form-item>
                      </td>
                      <td colspan="2" scope="row">体质指数(BMI)</td>
                      <td colspan="4"class="pad10">
                        <el-form-item label=""label-width="0px">
                          <el-input v-model="editForm.bodymassindex" auto-complete="off">
                            <template slot="append">kg/㎡</template>
                          </el-input>
                        </el-form-item>
                      </td>
                    </tr>
                    <tr>
                      <td colspan="3">老年人健康状态自我评估</td>
                      <td colspan="6" scope="row"style="text-align:left" class="pad20">
                        <el-form-item label="" label-width="0px">
                          <el-radio-group v-model="editForm.selfAssessment">
                            <el-radio label="满意">满意</el-radio>
                            <el-radio label="基本满意">基本满意</el-radio>
                            <el-radio label="说不清楚">说不清楚</el-radio>
                            <el-radio label="不太满意">不太满意</el-radio>
                            <el-radio label="不满意">不满意</el-radio>
                          </el-radio-group>
                        </el-form-item>
                      </td>
                    </tr>
                    <tr>
                      <td colspan="3">老年人生活自理能力自我评估</td>
                      <td colspan="6" scope="row"style="text-align:left" class="pad20">
                        <el-form-item label="" label-width="0px">
                          <el-radio-group v-model="editForm.selfCare">
                            <table>
                              <tr>
                                <td ><el-radio label="可自理">可自理</el-radio></td>
                                <td ><el-radio label="轻度依赖">轻度依赖</el-radio></td>
                                <td ><el-radio label="中度依赖">中度依赖</el-radio></td>
                                <td ><el-radio label="不能自理">不能自理</el-radio></td>
                              </tr>
                            </table>
                          </el-radio-group>
                        </el-form-item>
                      </td>
                    </tr>
                    <tr>
                      <td colspan="3">老年人认知功能</td>
                      <td colspan="6" scope="row"style="text-align:left" class="pad20">
                        <el-form-item label="" label-width="0px">
                          <el-radio-group v-model="editForm.cognition">
                            <el-radio label="粗筛阴性">粗筛阴性</el-radio>
                            <el-radio label="粗筛阳性">粗筛阳性</el-radio>
                          </el-radio-group>
                        </el-form-item>
                      </td>
                    </tr>
                    <tr>
                      <td colspan="3">老年人情感状态</td>
                      <td colspan="6" scope="row"style="text-align:left" class="pad20">
                        <el-form-item label="" label-width="0px">
                          <el-radio-group v-model="editForm.mood">
                            <el-radio label="粗筛阴性">粗筛阴性</el-radio>
                            <el-radio label="粗筛阳性">粗筛阳性</el-radio>
                          </el-radio-group>
                        </el-form-item>
                      </td>
                    </tr>

                   <tr>
                     <td  width="140px" rowspan="19" scope="row">生活方式</td>
                     <td rowspan="3">体育锻炼</td>
                     <td colspan="2">锻炼频率</td>
                     <td colspan="6"style="text-align:left" class="pad20">
                       <el-form-item label="" label-width="0px">
                         <el-radio-group v-model="editForm.exercise">
                           <el-radio label="每天">每天</el-radio>
                           <el-radio label="每周一次" >每周一次</el-radio>
                           <el-radio label="偶尔">偶尔</el-radio>
                           <el-radio label="不锻炼">不锻炼</el-radio>
                         </el-radio-group>
                       </el-form-item>
                     </td>
                   </tr>
                   <tr>
                     <td colspan="2">每次锻炼时间</td>
                     <td colspan="2"class="pad10">
                       <el-form-item label="" label-width="0px">
                         <el-input v-model="editForm.exerciseTime" >
                           <template slot="append">分钟</template>
                         </el-input>
                       </el-form-item>
                     </td>
                     <td colspan="2">坚持锻炼时间</td>
                     <td colspan="2"class="pad10">
                       <el-form-item label=""label-width="0px">
                         <el-input v-model="editForm.stickTime" >
                           <template slot="append">年</template>
                         </el-input>
                       </el-form-item>
                     </td>
                   </tr>
                   <tr>
                     <td colspan="2">锻炼方式</td>
                     <td colspan="6"class="pad10">
                       <el-form-item label="" label-width="0px">
                         <el-input v-model="editForm.exercisestyle" ></el-input>
                       </el-form-item>
                     </td>
                   </tr>
                   <tr>
                     <td>饮食习惯</td>
                     <td colspan="8"style="text-align:left" class="pad20">
                       <el-form-item label=""  label-width="0px">
                         <el-checkbox-group v-model="editForm.diet" style="width:100%">
                           <el-checkbox v-for="item in diet" :key="item.value" :name="type" :label="item.label" :value="item.value">
                           </el-checkbox>
                         </el-checkbox-group>
                       </el-form-item>
                     </td>
                   </tr>
                   <tr>
                     <td rowspan="3">吸烟情况</td>
                     <td colspan="2">吸烟状况</td>
                     <td colspan="6"style="text-align:left" class="pad20">
                       <el-form-item label=""label-width="0px">
                         <el-radio-group v-model="editForm.smoking">
                           <el-radio label="从不吸烟">从不吸烟</el-radio>
                           <el-radio label="已戒烟">已戒烟</el-radio>
                         </el-radio-group>
                         <el-radio-group v-model="editForm.smoking">
                           <el-radio label="吸烟">吸烟</el-radio>
                         </el-radio-group>
                       </el-form-item>
                     </td>
                   </tr>
                   <tr>
                     <td colspan="2">日吸烟量</td>
                     <td colspan="6"class="pad10">
                       <el-form-item label=""label-width="0px">
                         <el-input v-model="editForm.everySmoking">
                           <template slot="append">支</template>
                         </el-input>
                       </el-form-item>
                     </td>
                   </tr>
                   <tr>
                     <td colspan="2">开始吸烟年龄</td>
                     <td colspan="2"class="pad10">
                       <el-form-item label=""label-width="0px">
                         <el-input v-model="editForm.smokingAge" > <template slot="append">岁</template></el-input>
                       </el-form-item>
                     </td>
                     <td colspan="2">戒烟年龄</td>
                     <td colspan="2"class="pad10">
                       <el-form-item label=""label-width="0px">
                         <el-input v-model="editForm.noSmokingAge" > <template slot="append">岁</template></el-input>
                       </el-form-item>
                     </td>
                   </tr>
                   <tr>
                     <td rowspan="5">饮酒情况</td>
                     <td colspan="2">饮酒频率</td>
                     <td colspan="6"style="text-align:left" class="pad20">
                       <el-form-item label=""label-width="0px">
                         <el-radio-group v-model="editForm.drinking">
                           <el-radio label="从不">从不</el-radio>
                           <el-radio label="偶尔">偶尔</el-radio>
                         </el-radio-group>
                         <el-radio-group v-model="editForm.drinking">
                           <el-radio label="经常">经常</el-radio>
                           <el-radio label="每天">每天</el-radio>
                         </el-radio-group>
                       </el-form-item>
                     </td>
                   </tr>
                   <tr>
                     <td colspan="2">日饮酒量</td>
                     <td colspan="6"class="pad10">
                       <el-form-item label="" label-width="0px">
                         <el-input v-model="editForm.everydrinking" >
                           <template slot="append">瓶</template>
                         </el-input>
                       </el-form-item>
                     </td>
                   </tr>
                   <tr>
                     <td colspan="2">是否戒酒</td>
                     <td colspan="6"style="text-align:left" class="pad20">
                       <el-form-item label=""label-width="0px">
                         <el-radio-group v-model="editForm.nodrinking">
                           <el-radio label="未戒酒">未戒酒</el-radio>
                           <el-radio label="已戒酒">已戒酒</el-radio>
                         </el-radio-group>
                       </el-form-item>
                     </td>
                   </tr>
                   <tr>
                     <td colspan="2">开始饮酒年龄</td>
                     <td colspan="2"class="pad10">
                       <el-form-item label="" label-width="0px">
                         <el-input v-model="editForm.drinkingAge" > <template slot="append">岁</template></el-input>
                       </el-form-item>
                     </td>
                     <td colspan="2">近一年内是否醉酒</td>
                     <td colspan="2"style="text-align:left" class="pad20">
                       <el-form-item label="" label-width="0px">
                         <el-radio-group v-model="editForm.drunk">
                           <el-radio label="是">是</el-radio>
                           <el-radio label="否">否</el-radio>
                         </el-radio-group>
                       </el-form-item>
                     </td>
                   </tr>
                   <tr>
                     <td colspan="2">饮酒种类</td>
                     <td colspan="6"style="text-align:left" class="pad20">
                       <el-form-item label=""label-width="0px">
                         <el-radio-group v-model="editForm.drinkingTypes">
                           <el-radio label="白酒">白酒</el-radio>
                           <el-radio label="啤酒">啤酒</el-radio>
                           <el-radio label="红酒">红酒</el-radio>
                           <el-radio label="黄酒">黄酒</el-radio>
                           <el-radio label="其他">其他</el-radio>
                         </el-radio-group>
                       </el-form-item>
                     </td>
                   </tr>
                   <tr>
                     <td rowspan="7">职业病危害因素接触史</td>
                     <td colspan="8"style="text-align:left" class="pad20">
                       <el-form-item label=""label-width="0px">
                         <el-radio-group v-model="editForm.workDiseases">
                           <el-radio label="无">无</el-radio>
                           <el-radio label="有">有</el-radio>
                         </el-radio-group>
                       </el-form-item>
                     </td>
                   </tr>
                   <tr>
                     <td colspan="2">工种</td>
                     <td colspan="2"class="pad10">
                       <el-form-item label=""label-width="0px">
                         <el-input v-model="editForm.workType" ></el-input>
                       </el-form-item>
                     </td>
                     <td colspan="2">工作时间</td>
                     <td colspan="2"class="pad10">
                       <el-form-item label=""label-width="0px">
                         <el-input v-model="editForm.workingTime" > <template slot="append">年</template></el-input>
                       </el-form-item>
                     </td>
                   </tr>
                   <tr>
                     <td colspan="2">粉尘</td>
                     <td colspan="2"class="pad10">
                       <el-form-item label=""label-width="0px">
                         <el-input v-model="editForm.dust" ></el-input>
                       </el-form-item>
                     </td>
                     <td colspan="2">防护措施</td>
                     <td colspan="2"style="text-align:left" class="pad20">
                       <el-form-item label=""label-width="0px">
                         <el-radio-group v-model="editForm.dustproMeasures">
                           <el-radio label="无">无</el-radio>
                           <el-radio label="有">有</el-radio>
                         </el-radio-group>
                       </el-form-item>
                     </td>
                   </tr>
                   <tr>
                     <td colspan="2">放射物质</td>
                     <td colspan="2"class="pad10">
                       <el-form-item label=""label-width="0px">
                         <el-input v-model="editForm.fanshe" ></el-input>
                       </el-form-item>
                     </td>
                     <td colspan="2">防护措施</td>
                     <td colspan="2"style="text-align:left" class="pad20">
                       <el-form-item label=""label-width="0px">
                         <el-radio-group v-model="editForm.fansheproMeasures">
                           <el-radio label="无">无</el-radio>
                           <el-radio label="有">有</el-radio>
                         </el-radio-group>
                       </el-form-item>
                     </td>
                   </tr>
                   <tr>
                     <td colspan="2">物理因素</td>
                     <td colspan="2"class="pad10">
                       <el-form-item label=""label-width="0px">
                         <el-input v-model="editForm.physicalfactors" ></el-input>
                       </el-form-item>
                     </td>
                     <td colspan="2">防护措施</td>
                     <td colspan="2"style="text-align:left" class="pad20">
                       <el-form-item label=""label-width="0px">
                         <el-radio-group v-model="editForm.phyproMeasures">
                           <el-radio label="无">无</el-radio>
                           <el-radio label="有">有</el-radio>
                         </el-radio-group>
                       </el-form-item>
                     </td>
                   </tr>
                   <tr>
                     <td colspan="2">化学物质</td>
                     <td colspan="2"class="pad10">
                       <el-form-item label=""label-width="0px">
                         <el-input v-model="editForm.chemicals"></el-input>
                       </el-form-item>
                     </td>
                     <td colspan="2">防护措施</td>
                     <td colspan="2"style="text-align:left" class="pad20">
                       <el-form-item label=""label-width="0px">
                         <el-radio-group v-model="editForm.cheproMeasures">
                           <el-radio label="无">无</el-radio>
                           <el-radio label="有">有</el-radio>
                         </el-radio-group>
                       </el-form-item>
                     </td>
                   </tr>
                   <tr>
                     <td colspan="2">其他</td>
                     <td colspan="2"class="pad10">
                       <el-form-item label=""label-width="0px">
                         <el-input v-model="editForm.other1" ></el-input>
                       </el-form-item>
                     </td>
                     <td colspan="2">防护措施</td>
                     <td colspan="2"style="text-align:left" class="pad20">
                       <el-form-item label=""label-width="0px">
                         <el-radio-group v-model="editForm.other1ProMeasures">
                           <el-radio label="无">无</el-radio>
                           <el-radio label="有">有</el-radio>
                         </el-radio-group>
                       </el-form-item>
                     </td>
                   </tr>

                   <tr>
                     <td  width="140px"  rowspan="4" scope="row">脏器功能</td>
                     <td>口腔</td>
                     <td colspan="8"class="pad10">
                       <el-form-item label=""label-width="0px">
                         <el-input v-model="editForm.oralCavity" ></el-input>
                       </el-form-item>
                     </td>
                   </tr>
                   <tr>
                     <td>视力</td>
                     <td width="30">左眼</td>
                     <td width="65"class="pad10">
                       <el-form-item label=""label-width="0px">
                         <el-input v-model="editForm.visionz" ></el-input>
                       </el-form-item>
                     </td>
                     <td width="3">右眼</td>
                     <td width="65" class="pad10">
                       <el-form-item label=""label-width="0px">
                         <el-input v-model="editForm.visiony" ></el-input>
                       </el-form-item>
                     </td>
                     <td width="20">矫正视力左眼</td>
                     <td width="80"class="pad10">
                       <el-form-item label="" label-width="0px">
                         <el-input v-model="editForm.vicorrectz" ></el-input>
                       </el-form-item>
                     </td>
                     <td width="70">矫正视力右眼</td>
                     <td width="80"class="pad10">
                       <el-form-item label=""label-width="0px">
                         <el-input v-model="editForm.vicorrecty" ></el-input>
                       </el-form-item>
                     </td>
                   </tr>
                   <tr>
                     <td>听力</td>
                     <td colspan="8"style="text-align:left" class="pad20">
                       <el-form-item label=""label-width="0px">
                         <el-radio-group v-model="editForm.listen">
                           <el-radio label="听见">听见</el-radio>
                           <el-radio label="听不清或无法听见">听不清或无法听见</el-radio>
                         </el-radio-group>
                       </el-form-item>
                     </td>
                   </tr>
                   <tr>
                     <td>运动功能</td>
                     <td colspan="8"style="text-align:left" class="pad20">
                       <el-form-item label=""label-width="0px">
                         <el-radio-group v-model="editForm.sport">
                           <el-radio label="可顺利完成">可顺利完成</el-radio>
                           <el-radio label="无法独立完成其中任何一动作">无法独立完成其中任何一动作</el-radio>
                         </el-radio-group>
                       </el-form-item>
                     </td>
                   </tr>

                  <tr>
                    <td  width="140px" rowspan="25" scope="row">查体</td>
                    <td>眼底</td>
                    <td colspan="8"style="text-align:left" class="pad20">
                      <el-form-item label=""label-width="0px">
                        <el-radio-group v-model="editForm.bottomEye">
                          <el-radio label="正常">正常</el-radio>
                          <el-radio label="异常">异常</el-radio>
                        </el-radio-group>
                      </el-form-item>
                    </td>
                  </tr>
                  <tr>
                    <td>皮肤</td>
                    <td colspan="8"style="text-align:left" class="pad20">
                      <el-form-item label=""label-width="0px">
                        <el-radio-group v-model="editForm.skin">
                          <el-radio label="正常">正常</el-radio>
                          <el-radio label="潮红">潮红</el-radio>
                          <el-radio label="苍白">苍白</el-radio>
                          <el-radio label="发绀">发绀</el-radio>
                          <el-radio label="黄染">黄染</el-radio>
                          <el-radio label="色素沉着">色素沉着</el-radio>
                          <el-radio label="其他">其他</el-radio>
                        </el-radio-group>
                      </el-form-item>
                    </td>
                  </tr>
                  <tr>
                    <td>巩膜</td>
                    <td colspan="8"style="text-align:left" class="pad20">
                      <el-form-item label=""label-width="0px">
                        <el-radio-group v-model="editForm.sclera">
                          <el-radio label="正常">正常</el-radio>
                          <el-radio label="黄染">黄染</el-radio>
                          <el-radio label="充血">充血</el-radio>
                          <el-radio label="其他">其他</el-radio>
                        </el-radio-group>
                      </el-form-item>
                    </td>
                  </tr>
                  <tr>
                    <td>淋巴结</td>
                    <td colspan="8"style="text-align:left" class="pad20">
                      <el-form-item label=""label-width="0px">
                        <el-radio-group v-model="editForm.lymphNode">
                          <el-radio label="未触及">未触及</el-radio>
                          <el-radio label="锁骨上">锁骨上</el-radio>
                          <el-radio label="腋窝">腋窝</el-radio>
                          <el-radio label="其他">其他</el-radio>
                        </el-radio-group>
                      </el-form-item>
                    </td>
                  </tr>
                  <tr>
                    <td rowspan="3">肺</td>
                    <td colspan="1">桶状胸</td>
                    <td colspan="7"style="text-align:left" class="pad20">
                      <el-form-item label=""label-width="0px">
                        <el-radio-group v-model="editForm.barrelchest">
                          <el-radio label="是">是</el-radio>
                          <el-radio label="否">否</el-radio>
                        </el-radio-group>
                      </el-form-item>
                    </td>

                  </tr>
                  <tr>
                    <td colspan="1">呼吸音</td>
                    <td colspan="7"style="text-align:left" class="pad20">
                      <el-form-item label=""label-width="0px">
                        <el-radio-group v-model="editForm.breathSounds">
                          <el-radio label="正常">正常</el-radio>
                          <el-radio label="异常">异常</el-radio>
                        </el-radio-group>
                      </el-form-item>
                    </td>
                  </tr>
                  <tr>
                    <td colspan="1">罗音</td>
                    <td colspan="7"style="text-align:left" class="pad20">
                      <el-form-item label=""label-width="0px">
                        <el-radio-group v-model="editForm.rale">
                          <el-radio label="无">无</el-radio>
                          <el-radio label="千罗音">千罗音</el-radio>
                          <el-radio label="湿罗音">湿罗音</el-radio>
                          <el-radio label="其他">其他</el-radio>
                        </el-radio-group>
                      </el-form-item>
                    </td>
                  </tr>
                  <tr>
                    <td rowspan="3">心脏</td>
                    <td colspan="1">心率</td>
                    <td colspan="7"class="pad10">
                      <el-form-item label=""label-width="0px">
                        <el-input v-model="editForm.heartrate" auto-complete="off">
                          <template slot="append">次/分钟</template>
                        </el-input>
                      </el-form-item>
                    </td>
                  </tr>
                  <tr>
                    <td colspan="1">心律</td>
                    <td colspan="7"style="text-align:left" class="pad20">
                      <el-form-item label=""label-width="0px">
                        <el-radio-group v-model="editForm.hythmheart">
                          <el-radio label="齐">齐</el-radio>
                          <el-radio label="不齐">不齐</el-radio>
                          <el-radio label="绝对不齐">绝对不齐</el-radio>
                        </el-radio-group>
                      </el-form-item>
                    </td>
                  </tr>
                  <tr>
                    <td colspan="1">杂音</td>
                    <td colspan="7"style="text-align:left" class="pad20">
                      <el-form-item label="" label-width="0px">
                        <el-radio-group v-model="editForm.noise">
                          <el-radio label="无">无</el-radio>
                          <el-radio label="有">有</el-radio>
                        </el-radio-group>
                      </el-form-item>
                    </td>
                  </tr>
                  <tr>
                    <td rowspan="5">腹部</td>
                    <td colspan="1">压痛</td>
                    <td colspan="7"style="text-align:left" class="pad20">
                      <el-form-item label="" label-width="0px">
                        <el-radio-group v-model="editForm.tenderness">
                          <el-radio label="无">无</el-radio>
                          <el-radio label="有">有</el-radio>
                        </el-radio-group>
                      </el-form-item>
                    </td>
                  </tr>
                  <tr>
                    <td colspan="1">包块</td>
                    <td colspan="7"style="text-align:left" class="pad20">
                      <el-form-item label=""label-width="0px">
                        <el-radio-group v-model="editForm.bagpiece">
                          <el-radio label="无">无</el-radio>
                          <el-radio label="有">有</el-radio>
                        </el-radio-group>
                      </el-form-item>
                    </td>
                  </tr>
                  <tr>
                    <td colspan="1">肝大</td>
                    <td colspan="7"style="text-align:left" class="pad20">
                      <el-form-item label=""label-width="0px">
                        <el-radio-group v-model="editForm.hepatomegaly">
                          <el-radio label="无">无</el-radio>
                          <el-radio label="有">有</el-radio>
                        </el-radio-group>
                      </el-form-item>
                    </td>
                  </tr>
                  <tr>
                    <td colspan="1">脾大</td>
                    <td colspan="7"style="text-align:left" class="pad20">
                      <el-form-item label=""label-width="0px">
                        <el-radio-group v-model="editForm.splenomegaly">
                          <el-radio label="无">无</el-radio>
                          <el-radio label="有">有</el-radio>
                        </el-radio-group>
                      </el-form-item>
                    </td>
                  </tr>
                  <tr>
                    <td colspan="1">移动性浊音</td>
                    <td colspan="7"style="text-align:left" class="pad20">
                      <el-form-item label="" label-width="0px">
                        <el-radio-group v-model="editForm.mobilevoiced">
                          <el-radio label="无">无</el-radio>
                          <el-radio label="有">有</el-radio>
                        </el-radio-group>
                      </el-form-item>
                    </td>
                  </tr>
                  <tr>
                    <td>下肢水肿</td>
                    <td colspan="8"style="text-align:left" class="pad20">
                      <el-form-item label=""label-width="0px">
                        <el-radio-group v-model="editForm.limbEdema">
                          <el-radio label="无">无</el-radio>
                          <el-radio label="单侧">单侧</el-radio>
                          <el-radio label="双侧不对称">双侧不对称</el-radio>
                          <el-radio label="双侧对称">双侧对称</el-radio>
                        </el-radio-group>
                      </el-form-item>
                    </td>
                  </tr>
                  <tr>
                    <td>足背动脉搏动</td>
                    <td colspan="8"style="text-align:left" class="pad20">
                      <el-form-item label="" label-width="0px">
                        <el-radio-group v-model="editForm.footArteyPulse">
                          <el-radio label="未触及">未触及</el-radio>
                          <el-radio label="触及双侧对称">触及双侧对称</el-radio>
                          <el-radio label="触及左侧弱或消失">触及左侧弱或消失</el-radio>
                          <el-radio label="触及右侧弱或消失">触及右侧弱或消失</el-radio>
                        </el-radio-group>
                      </el-form-item>
                    </td>
                  </tr>
                  <tr>
                    <td>肛门指诊</td>
                    <td colspan="8"style="text-align:left" class="pad20">
                      <el-form-item label=""label-width="0px">
                        <el-radio-group v-model="editForm.analDiagnosis">
                          <el-radio label="未见异常">未见异常</el-radio>
                          <el-radio label="触痛">触痛</el-radio>
                          <el-radio label="包块">包块</el-radio>
                          <el-radio label="前列腺异常">前列腺异常</el-radio>
                        </el-radio-group>
                      </el-form-item>
                    </td>
                  </tr>
                  <tr>
                    <td>乳腺</td>
                    <td colspan="8"style="text-align:left" class="pad20">
                      <el-form-item label=""label-width="0px">
                        <el-radio-group v-model="editForm.mammaryGland">
                          <el-radio label="未见异常">未见异常</el-radio>
                          <el-radio label="乳房切除">乳房切除</el-radio>
                          <el-radio label="异常分泌">异常分泌</el-radio>
                          <el-radio label="乳腺包块">乳腺包块</el-radio>
                          <el-radio label="其他">其他</el-radio>
                        </el-radio-group>
                      </el-form-item>
                    </td>
                  </tr>
                  <tr>
                    <td rowspan="5">妇科</td>
                    <td colspan="1">外阴</td>
                    <td colspan="7"style="text-align:left" class="pad20">
                      <el-form-item label=""label-width="0px">
                        <el-radio-group v-model="editForm.vulva">
                          <el-radio label="正常">正常</el-radio>
                          <el-radio label="异常">异常</el-radio>
                        </el-radio-group>
                      </el-form-item>
                    </td>
                  </tr>
                  <tr>
                    <td colspan="1">阴道</td>
                    <td colspan="7"style="text-align:left" class="pad20">
                      <el-form-item label=""label-width="0px">
                        <el-radio-group v-model="editForm.vagina">
                          <el-radio label="正常">正常</el-radio>
                          <el-radio label="异常">异常</el-radio>
                        </el-radio-group>
                      </el-form-item>
                    </td>
                  </tr>
                  <tr>
                    <td colspan="1">宫颈</td>
                    <td colspan="7"style="text-align:left" class="pad20">
                      <el-form-item label=""label-width="0px">
                        <el-radio-group v-model="editForm.cervix">
                          <el-radio label="正常">正常</el-radio>
                          <el-radio label="异常">异常</el-radio>
                        </el-radio-group>
                      </el-form-item>
                    </td>
                  </tr>
                  <tr>
                    <td colspan="1">宫体</td>
                    <td colspan="7"style="text-align:left" class="pad20">
                      <el-form-item label=""label-width="0px">
                        <el-radio-group v-model="editForm.palace">
                          <el-radio label="正常">正常</el-radio>
                          <el-radio label="异常">异常</el-radio>
                        </el-radio-group>
                      </el-form-item>
                    </td>
                  </tr>
                  <tr>
                    <td colspan="1">附件</td>
                    <td colspan="7"style="text-align:left" class="pad20">
                      <el-form-item label=""label-width="0px">
                        <el-radio-group v-model="editForm.attachment">
                          <el-radio label="正常">正常</el-radio>
                          <el-radio label="异常">异常</el-radio>
                        </el-radio-group>
                      </el-form-item>
                    </td>
                  </tr>
                  <tr>
                    <td>其他</td>
                    <td colspan="8"class="pad10">
                      <el-form-item label=""label-width="0px">
                        <el-input v-model="editForm.other2" auto-complete="off"type="textarea" ></el-input>
                      </el-form-item>
                    </td>
                  </tr>

                <tr>
                  <td  width="140px"  rowspan="21" scope="row">辅助检查</td>
                  <td rowspan="2">血常规</td>
                  <td colspan="2">血红蛋白</td>
                  <td colspan="2"class="pad10">
                    <el-form-item label="" label-width="0px">
                      <el-input v-model="editForm.hemoglobin" auto-complete="off">
                        <template slot="append">g/l</template>
                      </el-input>
                    </el-form-item>
                  </td>
                  <td colspan="2">白细胞</td>
                  <td colspan="2"class="pad10">
                    <el-form-item label=""label-width="0px">
                      <el-input v-model="editForm.whitebloodcells" auto-complete="off">
                        <template slot="append">×10^9/L</template>
                      </el-input>
                    </el-form-item>
                  </td>
                </tr>
                <tr>
                  <td colspan="2">血小板</td>
                  <td colspan="2"class="pad10">
                    <el-form-item label=""label-width="0px">
                      <el-input v-model="editForm.platelet" auto-complete="off">
                        <template slot="append">×10^9/L</template>
                      </el-input>
                    </el-form-item>
                  </td>
                  <td colspan="2">其他</td>
                  <td colspan="2"class="pad10">
                    <el-form-item label=""label-width="0px">
                      <el-input v-model="editForm.other3" auto-complete="off"></el-input>
                    </el-form-item>
                  </td>
                </tr>
                <tr>
                  <td rowspan="2">尿常规</td>
                  <td colspan="2">尿蛋白</td>
                  <td colspan="2"class="pad10">
                    <el-form-item label="" label-width="0px">
                      <el-input v-model="editForm.urineprotein" auto-complete="off"></el-input>
                    </el-form-item>
                  </td>
                  <td colspan="2">尿糖</td>
                  <td colspan="2"class="pad10">
                    <el-form-item label=""label-width="0px">
                      <el-input v-model="editForm.urinesugar" auto-complete="off"></el-input>
                    </el-form-item>
                  </td>
                </tr>
                <tr>
                  <td colspan="2">尿酮体</td>
                  <td colspan="2"class="pad10">
                    <el-form-item label=""label-width="0px">
                      <el-input v-model="editForm.urineketone" auto-complete="off"></el-input>
                    </el-form-item>
                  </td>
                  <td colspan="2">尿潜血</td>
                  <td colspan="2"class="pad10">
                    <el-form-item label=""label-width="0px">
                      <el-input v-model="editForm.ery" auto-complete="off"></el-input>
                    </el-form-item>
                  </td>
                </tr>
                <tr>
                  <td>空腹血糖</td>
                  <td colspan="8">
                    <el-form-item label=""label-width="0px">
                      <el-input v-model="editForm.fastingBloodSugar" auto-complete="off">
                        <template slot="append">mmol/L或mg/dL</template>
                      </el-input>
                    </el-form-item>
                  </td>
                </tr>
                <tr>
                  <td>心电图</td>
                  <td colspan="8"style="text-align:left" class="pad20">
                    <el-form-item label=""label-width="0px">
                      <el-radio-group v-model="editForm.electrocardiogram">
                        <el-radio label="正常">正常</el-radio>
                        <el-radio label="异常">异常</el-radio>
                      </el-radio-group>
                    </el-form-item>
                  </td>
                </tr>
                <tr>
                  <td>尿微量白蛋白</td>
                  <td colspan="8"style="text-align:left" class="pad20">
                    <el-form-item label="" label-width="0px">
                      <el-input v-model="editForm.urineMicroalbumin" auto-complete="off">
                        <template slot="append">mg/dL</template>
                      </el-input>
                    </el-form-item>
                  </td>
                </tr>
                <tr>
                  <td>大便潜血</td>
                  <td colspan="8"style="text-align:left" class="pad20">
                    <el-form-item label=""label-width="0px">
                      <el-radio-group v-model="editForm.shitblood">
                        <el-radio label="阴性">阴性</el-radio>
                        <el-radio label="阳性">阳性</el-radio>
                      </el-radio-group>
                    </el-form-item>
                  </td>
                </tr>
                <tr>
                  <td>糖化血红蛋白</td>
                  <td colspan="8"class="pad10">
                    <el-form-item label="" label-width="0px">
                      <el-input v-model="editForm.glycatedGlycemic" auto-complete="off">
                        <template slot="append">%</template>
                      </el-input>
                    </el-form-item>
                  </td>
                </tr>
                <tr>
                  <td>乙型肝炎表面抗原</td>
                  <td colspan="8"style="text-align:left" class="pad20">
                    <el-form-item label=""label-width="0px">
                      <el-radio-group v-model="editForm.hepatitisb">
                        <el-radio label="阴性">阴性</el-radio>
                        <el-radio label="阳性">阳性</el-radio>
                      </el-radio-group>
                    </el-form-item>
                  </td>
                </tr>
                <tr>
                  <td rowspan="3">肝功能</td>
                  <td colspan="2">血清谷丙转氨酶</td>
                  <td colspan="2"class="pad10">
                    <el-form-item label=""label-width="0px">
                      <el-input v-model="editForm.serumtransa" auto-complete="off">
                        <template slot="append">U/L</template>
                      </el-input>
                    </el-form-item>
                  </td>
                  <td colspan="2">血清谷草转氨酶</td>
                  <td colspan="2"class="pad10">
                    <el-form-item label="" label-width="0px">
                      <el-input v-model="editForm.glutamictransame" auto-complete="off">
                        <template slot="append">U/L</template>
                      </el-input>
                    </el-form-item>
                  </td>
                </tr>
                <tr>
                  <td colspan="2">白蛋白</td>
                  <td colspan="2"class="pad10">
                    <el-form-item label=""label-width="0px">
                      <el-input v-model="editForm.albumin" auto-complete="off">
                        <template slot="append">g/l</template>
                      </el-input>
                    </el-form-item>
                  </td>
                  <td colspan="2">总胆红素</td>
                  <td colspan="2"class="pad10">
                    <el-form-item label=""label-width="0px">
                      <el-input v-model="editForm.totalbilirubin" auto-complete="off">
                        <template slot="append">umol/l</template>
                      </el-input>
                    </el-form-item>
                  </td>
                </tr>
                <tr>
                  <td colspan="2">结合胆红素</td>
                  <td colspan="2"class="pad10">
                    <el-form-item label="" label-width="0px">
                      <el-input v-model="editForm.boundbilirubin" auto-complete="off">
                        <template slot="append">umol/l</template>
                      </el-input>
                    </el-form-item>
                  </td>
                  <td colspan="2">&nbsp;</td>
                  <td colspan="2">&nbsp;</td>
                </tr>
                <tr>
                  <td rowspan="2">肾功能</td>
                  <td colspan="2">血尿素</td>
                  <td colspan="2"class="pad10">
                    <el-form-item label=""label-width="0px">
                      <el-input v-model="editForm.bloodurea" auto-complete="off">
                        <template slot="append">mmol/l</template>
                      </el-input>
                    </el-form-item>
                  </td>
                  <td colspan="2">血钾浓度</td>
                  <td colspan="2"class="pad10">
                    <el-form-item label=""label-width="0px">
                      <el-input v-model="editForm.potconcen" auto-complete="off">
                        <template slot="append">mmol/l</template>
                      </el-input>
                    </el-form-item>
                  </td>
                </tr>
                <tr>
                  <td colspan="2">血钠浓度</td>
                  <td colspan="2"class="pad10">
                    <el-form-item label="" label-width="0px">
                      <el-input v-model="editForm.sodiumconcen" auto-complete="off">
                        <template slot="append">mmol/l</template>
                      </el-input>
                    </el-form-item>
                  </td>
                  <td colspan="2"></td>
                  <td colspan="2"></td>
                </tr>
                <tr>
                  <td rowspan="2">血 脂</td>
                  <td colspan="2">总胆固醇</td>
                  <td colspan="2"class="pad10">
                    <el-form-item label=""label-width="0px">
                      <el-input v-model="editForm.totalcholestero" auto-complete="off">
                        <template slot="append">mmol/l</template>
                      </el-input>
                    </el-form-item>
                  </td>
                  <td colspan="2">血清低密度脂蛋白胆固醇</td>
                  <td colspan="2"class="pad10">
                    <el-form-item label=""label-width="0px">
                      <el-input v-model="editForm.ldlcholesterol" auto-complete="off">
                        <template slot="append">mmol/l</template>
                      </el-input>
                    </el-form-item>
                  </td>
                </tr>
                <tr>
                  <td colspan="2">血清高密度脂蛋白胆固醇</td>
                  <td colspan="2"class="pad10">
                    <el-form-item label=""label-width="0px">
                      <el-input v-model="editForm.hdlcholesterol" auto-complete="off">
                        <template slot="append">mmol/l</template>
                      </el-input>
                    </el-form-item>
                  </td>
                  <td colspan="2"></td>
                  <td colspan="2"></td>
                </tr>
                <tr>
                  <td height="25">胸部 X 线片</td>
                  <td colspan="8"style="text-align:left" class="pad20">
                    <el-form-item label=""label-width="0px">
                      <el-radio-group v-model="editForm.chestxray">
                        <el-radio label="正常">正常</el-radio>
                        <el-radio label="异常">异常</el-radio>
                      </el-radio-group>
                    </el-form-item>
                  </td>
                </tr>
                <tr>
                  <td>B 超</td>
                  <td colspan="2">腹部B超</td>
                  <td colspan="6"style="text-align:left" class="pad20">
                    <el-form-item label="" label-width="0px">
                      <el-radio-group v-model="editForm.bsuper">
                        <el-radio label="正常">正常</el-radio>
                        <el-radio label="异常">异常</el-radio>
                      </el-radio-group>
                    </el-form-item>
                  </td>
                </tr>
                <tr>
                  <td>宫颈涂片</td>
                  <td colspan="8"style="text-align:left" class="pad20">
                    <el-form-item label=""label-width="0px">
                      <el-radio-group v-model="editForm.cervicalSmear">
                        <el-radio label="正常">正常</el-radio>
                        <el-radio label="异常">异常</el-radio>
                      </el-radio-group>
                    </el-form-item>
                  </td>
                </tr>
                <tr>
                  <td>其他</td>
                  <td colspan="8"style="text-align:center;padding-bottom: 5px;padding-top: 5px;"class="pad10">
                    <el-form-item label=""label-width="0px">
                      <el-input v-model="editForm.other6" auto-complete="off"type="textarea"></el-input>
                    </el-form-item>
                  </td>
                </tr>

               <tr>
                 <td  width="140px" rowspan="7" scope="row">现存主要  健康问题</td>
                 <td>脑血管疾病</td>
                 <td colspan="8"style="text-align:left" class="pad20">
                   <el-form-item label="" label-width="0px">
                     <el-checkbox-group v-model="editForm.cerebrovascular" style="width:100%">
                       <table>
                         <tr>
                           <td style="width: 16%;" ><el-checkbox label="未发现" name="type"></el-checkbox></td>
                           <td style="width: 16%;"><el-checkbox label="缺血性卒中" name="type"></el-checkbox></td>
                           <td style="width: 16%;" ><el-checkbox label="脑出血" name="type"></el-checkbox></td>
                           <td style="width: 16%;" ><el-checkbox label="蛛网膜下腔出血" name="type"></el-checkbox></td>
                           <td style="width: 16%;" ><el-checkbox label="短暂性缺血发作" name="type"></el-checkbox></td>
                         </tr>
                         <tr>
                           <td style="width: 16%;" ><el-checkbox label="其他" name="type"></el-checkbox></td>
                         </tr>
                       </table>
                     </el-checkbox-group>
                   </el-form-item>
                 </td>
               </tr>
               <tr>
                 <td>肾脏疾病</td>
                 <td colspan="8"style="text-align:left" class="pad20">
                   <el-form-item label="" label-width="0px">
                     <el-checkbox-group v-model="editForm.kidneyDisease" style="width:100%">
                       <el-checkbox v-for="item in kidneyDisease" :key="item.value" :name="type" :label="item.label" :value="item.value">
                       </el-checkbox>
                     </el-checkbox-group>
                   </el-form-item>
                 </td>
               </tr>
               <tr>
                 <td>心脏疾病</td>
                 <td colspan="8"style="text-align:left" class="pad20">
                   <el-form-item label="" label-width="0px">
                     <el-checkbox-group v-model="editForm.heartDisease" style="width:100%">
                       <table>
                         <tr>
                           <td style="width: 16%;" ><el-checkbox label="未发现" name="type"></el-checkbox></td>
                           <td style="width: 16%;"><el-checkbox label="心肌梗死" name="type"></el-checkbox></td>
                           <td style="width: 16%;" ><el-checkbox label="心绞痛" name="type"></el-checkbox></td>
                           <td style="width: 16%;" ><el-checkbox label="冠状动脉血运重建" name="type"></el-checkbox></td>
                           <td style="width: 16%;" ><el-checkbox label="充血性心力衰竭" name="type"></el-checkbox></td>
                         </tr>
                         <tr>
                           <td style="width: 16%;" ><el-checkbox label="心前区疼痛" name="type"></el-checkbox></td>
                         </tr>
                       </table>
                     </el-checkbox-group>
                   </el-form-item>
                 </td>
               </tr>
               <tr>
                 <td>血管疾病</td>
                 <td colspan="8"style="text-align:left" class="pad20">
                   <el-form-item label="" label-width="0px">
                     <el-checkbox-group v-model="editForm.vascularDisease" style="width:100%">
                       <el-checkbox v-for="item in vascularDisease" :key="item.value" :name="type" :label="item.label" :value="item.value">
                       </el-checkbox>
                     </el-checkbox-group>
                   </el-form-item>
                 </td>
               </tr>
               <tr>
                 <td>眼部疾病</td>
                 <td colspan="8"style="text-align:left" class="pad20">
                   <el-form-item label="" label-width="0px">
                     <el-checkbox-group v-model="editForm.eyeDisease" style="width:100%">
                       <el-checkbox v-for="item in eyeDisease" :key="item.value" :name="type" :label="item.label" :value="item.value">
                       </el-checkbox>
                     </el-checkbox-group>
                   </el-form-item>
                 </td>
               </tr>
               <tr>
                 <td>神经系统疾病</td>
                 <td colspan="8"style="text-align:left" class="pad20">
                   <el-form-item label="" label-width="0px">
                     <el-radio-group v-model="editForm.nervousSystem">
                       <el-radio label="未发现">未发现</el-radio>
                       <el-radio label="有">有</el-radio>
                     </el-radio-group>
                   </el-form-item>
                 </td>
               </tr>
               <tr>
                 <td>其他系统疾病</td>
                 <td colspan="8"style="text-align:center;padding-bottom: 5px;padding-top: 5px;"class="pad10">
                   <el-form-item label="" label-width="0px">
                     <el-input v-model="editForm.otherDiabetes" auto-complete="off"type="textarea" ></el-input>
                   </el-form-item>
                 </td>
               </tr>

             <tr>
               <td   width="140px" rowspan="6" scope="row">住院治疗情况</td>
               <td rowspan="2">住院史</td>
               <td colspan="2">入/出院日期</td>
               <td colspan="2">原 因</td>
               <td colspan="2">医疗机构名称</td>
               <td colspan="2">病案号</td>
             </tr>
             <tr>
               <td colspan="2"style="text-align:center;padding-bottom: 5px;padding-top: 5px;">
                 <el-date-picker type="date" placeholder="入院日期" v-model="editForm.inhDate"style="width: 100%"></el-date-picker>
                 <div align="center">/</div>
                 <el-date-picker type="date" placeholder="出院日期" v-model="editForm.outhDate"style="width: 100%"></el-date-picker>
               </td>
               <td colspan="2"class="pad10">
                   <el-input v-model="editForm.why" type="textarea"clearable ></el-input>
               </td>
               <td colspan="2"class="pad10">
                 <el-input v-model="editForm.institutName"type="textarea"></el-input>
               </td>
               <td colspan="2"class="pad10">
                 <el-input v-model="editForm.medicalrecordno"type="textarea"></el-input>
               </td>
             </tr>
             <tr>
               <td rowspan="2">家庭病床史</td>
               <td colspan="2">建/撤床日期</td>
               <td colspan="2">原 因</td>
               <td colspan="2">医疗机构名称</td>
               <td colspan="2">病案号</td>
             </tr>
             <tr>
               <td colspan="2"style="text-align:center;padding-bottom: 5px;padding-top: 5px;">
                 <el-date-picker type="date" placeholder="建床日期" v-model="editForm.inbDate" style="width: 100%"></el-date-picker>
                 <div align="center">/</div>
                 <el-date-picker type="date" placeholder="撤床日期" v-model="editForm.outbDate" style="width: 100%"></el-date-picker>
               </td>
               <td colspan="2"class="pad10">
                 <el-input v-model="editForm.why1"type="textarea" ></el-input>
               </td>
               <td colspan="2"class="pad10">
                 <el-input v-model="editForm.institutName1" type="textarea"></el-input>
               </td>
               <td colspan="2"class="pad10">
                 <el-input v-model="editForm.medicalrecordno1"type="textarea" ></el-input>
               </td>
             </tr>
             <tr>
               <td rowspan="2" scope="row">主要用药情况</td>
               <td colspan="2">药物名称</td>
               <td colspan="1">用法</td>
               <td colspan="1">用量</td>
               <td colspan="1">用药时间</td>
               <td colspan="3">用药依从性</td>
             </tr>
             <tr>
               <td colspan="2"style="text-align:center;padding-bottom: 5px;padding-top: 5px;"class="pad10">
                 <el-form-item label="" label-width="0px">
                   <el-input v-model="editForm.drugname" type="textarea" ></el-input>
                 </el-form-item>
               </td>
               <td colspan="1"style="text-align:center;padding-bottom: 5px;padding-top: 5px;"class="pad10">
                 <el-form-item label="" label-width="0px">
                   <el-input v-model="editForm.useing"type="textarea" ></el-input>
                 </el-form-item>
               </td>
               <td colspan="1"style="text-align:center;padding-bottom: 5px;padding-top: 5px;"class="pad10">
                 <el-form-item label="" label-width="0px">
                   <el-input v-model="editForm.dosage"type="textarea" ></el-input>
                 </el-form-item>
               </td>
               <td colspan="1"style="text-align:center;padding-bottom: 5px;padding-top: 5px;"class="pad10">
                 <el-form-item label=""label-width="0px">
                   <el-input v-model="editForm.duration" type="textarea"></el-input>
                 </el-form-item>
               </td>
               <td colspan="3"style="text-align:left" class="pad20">
                 <el-form-item label="" label-width="0px">
                   <el-radio-group v-model="editForm.medicompliance">
                     <el-radio label="规律">规律</el-radio>
                     <el-radio label="间断">间断</el-radio>
                   </el-radio-group>
                   <el-radio-group v-model="editForm.medicompliance">
                     <el-radio label="不服药">不服药</el-radio>
                   </el-radio-group>
                 </el-form-item>
               </td>
             </tr>
                    <tr>
                    <td  width="140px"  rowspan="2" scope="row">非免疫  规划预防  接种史</td>
                    <td colspan="3">名称</td>
                    <td colspan="3">接种日期</td>
                    <td colspan="3">接种机构</td>
                  </tr>
                  <tr>
                    <td colspan="3"class="pad10">
                      <el-input v-model="editForm.vaccinationName" type="textarea" ></el-input>
                    </td>
                    <td colspan="3"class="pad10">
                      <el-date-picker type="date" placeholder="选择接种日期" v-model="editForm.vaccinationDate"style="width: 100%"></el-date-picker>
                    </td>
                    <td colspan="3"class="pad10" >
                      <el-input v-model="editForm.vaccinationAgency" type="textarea"></el-input>
                    </td>
                  </tr>
                  <tr>
                    <td  width="140px" height="49" scope="row">健康评价</td>
                    <td colspan="9"style="text-align:left" class="pad20">
                      <el-form-item label="" label-width="0px">
                        <el-radio-group v-model="editForm.healthEvaluation">
                          <el-radio label="体检无异常">体检无异常</el-radio>
                          <el-radio label="有异常">有异常</el-radio>
                        </el-radio-group>
                      </el-form-item>
                    </td>
                  </tr>
                  <tr>
                    <td  width="140px" height="47" scope="row">健康指导</td>
                    <td colspan="9"style="text-align:left" class="pad20">
                      <el-form-item label="" label-width="0px">
                        <el-checkbox-group v-model="editForm.healthGuidance" style="width:100%">
                          <el-checkbox v-for="item in healthGuidance" :key="item.value" :name="type" :label="item.label" :value="item.value">
                          </el-checkbox>
                        </el-checkbox-group>
                      </el-form-item>
                    </td>
                  </tr>
                  <tr>
                    <td  width="140px" scope="row">危险因素控制</td>
                    <td colspan="9"style="text-align:left" class="pad20">
                      <el-form-item label="" label-width="0px">
                        <el-checkbox-group v-model="editForm.riskcontrol" style="width:100%">
                          <el-checkbox v-for="item in riskcontrol" :key="item.value" :name="type" :label="item.label" :value="item.value">
                          </el-checkbox>
                        </el-checkbox-group>
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
      <div slot="footer" class="dialog-footer"center>
        <el-button @click="conprint()">打印</el-button>
        <el-button v-if="dialogStatus=='create'" type="primary" @click="check">添加</el-button>
        <el-button v-else type="primary" v-if="edit" @click="check">新增修改</el-button>
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
    batchRemove,
    updateData,
    addData
  } from '@/api/HealthCheckups'
  import { getList as getPerson } from '@/api/Perinfor'

  export default {
    name: 'HealthCheckups',
    data() {
      return {
        buttons: {
          P_C: false,
          HRH_E: false,
          HRH_D: false
        },
        edit: true,
        tableHeight: window.innerHeight - 220,
        tablebodyHeight: window.innerHeight - 320,
        // 危险控制
        riskcontrol: [
          { value: '定期随访', label: '定期随访' },
          { value: '纳入慢性病患者健康管理', label: '纳入慢性病患者健康管理' },
          { value: '建议复诊', label: '建议复诊' },
          { value: '其他', label: '其他' }
        ],
        // 健康指导
        healthGuidance: [
          { value: '戒烟', label: '戒烟' },
          { value: '健康饮酒', label: '健康饮酒' },
          { value: '饮食', label: '饮食' },
          { value: '锻炼', label: '锻炼' },
          { value: '减体重', label: '减体重' },
          { value: '建议接种疫苗', label: '建议接种疫苗' },
          { value: '其他', label: '其他' }
        ],
        // 眼部疾病
        eyeDisease: [
          { value: '未发现', label: '未发现' },
          { value: '视网膜出血或渗出', label: '视网膜出血或渗出' },
          { value: '视乳头水肿', label: '视乳头水肿' },
          { value: '白内障', label: '白内障' },
          { value: '其他', label: '其他' }
        ],
        // 血管疾病
        vascularDisease: [
          { value: '未发现', label: '未发现' },
          { value: '夹层动脉瘤', label: '夹层动脉瘤' },
          { value: '动脉闭塞性疾病', label: '动脉闭塞性疾病' },
          { value: '其他', label: '其他' }
        ],
        // 心脏疾病
        heartDisease: [
          { value: '未发现', label: '未发现' },
          { value: '心肌梗死', label: '心肌梗死' },
          { value: '心绞痛', label: '心绞痛' },
          { value: '冠状动脉血运重建', label: '冠状动脉血运重建' },
          { value: '充血性心力衰竭', label: '充血性心力衰竭' },
          { value: '心前区疼痛', label: '心前区疼痛' }
        ],
        // 肾脏疾病
        kidneyDisease: [
          { value: '未发现', label: '未发现' },
          { value: '糖尿病肾病', label: '糖尿病肾病' },
          { value: '肾功能衰竭', label: '肾功能衰竭' },
          { value: '急性肾炎', label: '急性肾炎' },
          { value: '慢性肾炎', label: '慢性肾炎' },
          { value: '其他', label: '其他' }
        ],
        // 脑血管疾病
        cerebrovascular: [
          { value: '未发现', label: '未发现' },
          { value: '缺血性卒中', label: '缺血性卒中' },
          { value: '脑出血', label: '脑出血' },
          { value: '蛛网膜下腔出血', label: '蛛网膜下腔出血' },
          { value: '短暂性脑缺血发作', label: '短暂性脑缺血发作' },
          { value: '其他', label: '其他' }
        ],
        // 饮食
        diet: [
          { value: '荤素均衡', label: '荤素均衡' },
          { value: '荤食为主', label: '荤食为主' },
          { value: '嗜盐', label: '嗜盐' },
          { value: '嗜糖', label: '嗜糖' },
          { value: '嗜油', label: '嗜油' }
        ],
        // 症状
        symptoms: [
          { value: '无症状', label: '无症状' },
          { value: '头痛', label: '头痛' },
          { value: '头晕', label: '头晕' },
          { value: '心悸', label: '心悸' },
          { value: '胸闷', label: '胸闷' },
          { value: '胸痛', label: '胸痛' },
          { value: '慢性咳嗽', label: '慢性咳嗽' },
          { value: '咳痰', label: '咳痰' },
          { value: '呼吸困难', label: '呼吸困难' },
          { value: '多饮', label: '多饮' },
          { value: '多尿', label: '多尿' },
          { value: '体重下降', label: '体重下降' },
          { value: '乏力', label: '乏力' },
          { value: '关节肿痛', label: '关节肿痛' },
          { value: '视力模糊', label: '视力模糊' },
          { value: '手脚麻木', label: '手脚麻木' },
          { value: '尿急', label: '尿急' },
          { value: '尿痛', label: '尿痛' },
          { value: '便秘', label: '便秘' },
          { value: '腹泻', label: '腹泻' },
          { value: '恶心呕吐', label: '恶心呕吐' },
          { value: '眼花', label: '眼花' },
          { value: '耳鸣', label: '耳鸣' },
          { value: '乳房胀痛', label: '乳房胀痛' },
          { value: '其他', label: '其他' }],
        activeName: 'first',
        dialogStatus: '',
        textMap: {
          update: '编辑',
          create: '新增',
          detail: '详情'
        },
        dialogFormVisible: false,
        filters: {
          name: '',
          peopleId: ''
        },
        users: [],
        total: 0,
        page: 1,
        sels: [], // 列表选中列
        editFormRules: {
          doctor: [{ required: true, message: '请输入医生姓名', trigger: 'blur' }],
          name: [{ required: true, message: '请输入姓名', trigger: 'blur' }],
          peopleId: [{ required: true, message: '请输入身份证号', trigger: 'blur' },
            { pattern: /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/, message: '你的身份证格式不正确' }]
        },
        // 编辑界面数据
        editForm: {
          id: 0,
          symptoms: [],
          cerebrovascular: [],
          kidneyDisease: [],
          heartDisease: [],
          vascularDisease: [],
          eyeDisease: [],
          healthGuidance: [],
          riskcontrol: [],
          diet: []
        },

        addFormVisible: false, // 新增界面是否显示
        addFormRules: {
          // doctor: [{ required: true, message: '请输入医生姓名', trigger: 'blur' }],
          name: [{ required: true, message: '请输入姓名', trigger: 'blur' }],
          peopleId: [{ required: true, message: '请输入身份证号', trigger: 'blur' },
            { pattern: /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/, message: '你的身份证格式不正确' }]

        }
      }
    },
    methods: {
      inputFunc1() {
        const para = {
          page: 1,
          size: 200,
          filters: { name: '', peopleId: this.editForm.peopleId, cardNumber: '' }
        }
        if (para.filters !== '') {
          setTimeout(() => {
            this.$nextTick(function() {
              getPerson(para).then(res => {
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

      // 性别显示转换
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
        str = str + '<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">\n' +
          '<html xmlns="http://www.w3.org/1999/xhtml">\n' +
          '<head>\n' +
          '<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />\n' +
          '<title>无标题文档</title>\n' +
          '</head>\n' +
          '\n' +
          '<body>\n' +
          '\n' +
          '<div id="dataPanel" style="position:absolute;left:50%;margin-left:-397px;">\n' +
          '  <div id="topContent">\n' +
          '    <h2 style="width:794px;text-align:center"> 健康体检表档案</h2> \n' +
          '    <span style="margin-left:34px;">姓名:</span>\n' +
          '    <span style="margin-left:34px;">{{name}}</span>\n' +
          '\t<span style="margin-left:300px;">编号:</span>\n' +
          '    <span style="margin-left:5px;">{{id}}</span>\n' +
          '    </div>\n' +
          '  <table id="table"  border="1"  cellspacing="0" bordercolor="#000000" style="width:730px;margin-left:30px;margin-top:5px;border-collapse:collapse;"  width = "80%">\n' +
          '    <tbody>\n' +
          '  <tr>\n' +
          '    <th width="83" scope="row">体检日期</th>\n' +
          '    <td colspan="3">{{checkDate}}</td>\n' +
          '    <th colspan="2" scope="row">责任医生</th>\n' +
          '    <td colspan="4">{{doctor}}</td>\n' +
          '  </tr>\n' +
          '  <tr>\n' +
          '    <th scope="row">内容</th>\n' +
          '    <th colspan="9">检查项目</th>\n' +
          '  </tr>\n' +
          '  <tr>\n' +
          '    <th scope="row">症状</th>\n' +
          '    <td colspan="9">{{symptoms}}</td>\n' +
          '  </tr>\n' +
          '   <tr>\n' +
          '    <th rowspan="9" scope="row">一般情况</th>\n' +
          '    <td width="97">体温</td>\n' +
          '    <td colspan="2"><div align="right">{{temperature}}℃ </div></td>\n' +
          '    <td colspan="2">脉率</td>\n' +
          '    <td colspan="4"><div align="right">{{pulseRate}}次/分钟</div></td>\n' +
          '  </tr>\n' +
          '   <tr>\n' +
          '    <td rowspan="2">呼吸频率</td>\n' +
          '    <td colspan="2" rowspan="2"><div align="right">{{breathing}}次/分钟</div></td>\n' +
          '    <td colspan="2" rowspan="2" scope="row">血压</td>\n' +
          '    <td colspan="2">左侧</td>\n' +
          '    <td colspan="2"><div align="right">{{pressureleft}}mmHg</div></td>\n' +
          '  </tr>\n' +
          '   <tr>\n' +
          '     <td colspan="2">右侧</td>\n' +
          '     <td colspan="2"><div align="right">{{pressureright}}mmHg</div></td>\n' +
          '   </tr>\n' +
          '   <tr>\n' +
          '    <td>身高</td>\n' +
          '    <td colspan="2"><div align="right">{{stature}}CM</div></td>\n' +
          '    <td colspan="2" scope="row">体重</td>\n' +
          '    <td colspan="4"><div align="right">{{weight}}Kg</div></td>\n' +
          '  </tr>\n' +
          '   <tr>\n' +
          '    <td>腰围</td>\n' +
          '    <td colspan="2"><div align="right">{{waistline}}CM</div></td>\n' +
          '    <td colspan="2" scope="row">体质指数(BMI)</td>\n' +
          '    <td colspan="4"><div align="right">{{bodymassindex}}kg/c㎡</div></td>\n' +
          '  </tr>\n' +
          '   <tr>\n' +
          '    <th colspan="3">老年人健康状态  自我评估*</th>\n' +
          '    <td colspan="6" scope="row">{{selfAssessment}}</td>\n' +
          '  </tr>\n' +
          '   <tr>\n' +
          '    <th colspan="3">老年人生活自理  能力自我评估*</th>\n' +
          '    <td colspan="6" scope="row">{{selfCare}}</td>\n' +
          '  </tr>\n' +
          '   <tr>\n' +
          '    <th colspan="3">老年人  认知功能*</th>\n' +
          '    <td colspan="6" scope="row">{{cognition}}</td>\n' +
          '  </tr>\n' +
          '   <tr>\n' +
          '    <th colspan="3">老年人  情感状态*</th>\n' +
          '    <td colspan="6" scope="row">{{mood}}</td>\n' +
          '  </tr>\n' +
          '   <tr>\n' +
          '    <th rowspan="13" scope="row">生活方式</th>\n' +
          '    <td rowspan="3">体育锻炼</td>\n' +
          '    <td colspan="2">锻炼频率</td>\n' +
          '    <td colspan="6">{{exercise}}</td>\n' +
          '  </tr>\n' +
          '   <tr>\n' +
          '     <td colspan="2">每次锻炼时间</td>\n' +
          '     <td colspan="2"><div align="right">{{exerciseTime}}分钟</div></td>\n' +
          '     <td colspan="2">坚持锻炼时间</td>\n' +
          '     <td colspan="2"><div align="right">{{stickTime}}年</div></td>\n' +
          '   </tr>\n' +
          '   <tr>\n' +
          '     <td colspan="2">锻炼方式</td>\n' +
          '     <td colspan="6">{{exercisestyle}}</td>\n' +
          '   </tr>\n' +
          '   <tr>\n' +
          '    <td>饮食习惯</td>\n' +
          '    <td colspan="8">{{diet}}</td>\n' +
          '  </tr>\n' +
          '   <tr>\n' +
          '    <td rowspan="3">吸烟情况</td>\n' +
          '    <td colspan="2">吸烟状况</td>\n' +
          '    <td colspan="6">{{smoking}}</td>\n' +
          '  </tr>\n' +
          '   <tr>\n' +
          '     <td colspan="2">日吸烟量</td>\n' +
          '     <td colspan="6">{{everySmoking}}</td>\n' +
          '   </tr>\n' +
          '   <tr>\n' +
          '     <td colspan="2">开始吸烟年龄</td>\n' +
          '     <td colspan="2">{{smokingAge}}</td>\n' +
          '     <td colspan="2">戒烟年龄</td>\n' +
          '     <td colspan="2">{{noSmokingAge}}</td>\n' +
          '   </tr>\n' +
          '   <tr>\n' +
          '    <td rowspan="5">饮酒情况</td>\n' +
          '    <td colspan="2">饮酒频率</td>\n' +
          '    <td colspan="6">{{drinking}}</td>\n' +
          '  </tr>\n' +
          '   <tr>\n' +
          '     <td colspan="2">日饮酒量</td>\n' +
          '     <td colspan="6">{{everydrinking}}</td>\n' +
          '   </tr>\n' +
          '   <tr>\n' +
          '     <td colspan="2">是否戒酒</td>\n' +
          '     <td colspan="6">{{nodrinking}}</td>\n' +
          '   </tr>\n' +
          '   <tr>\n' +
          '     <td colspan="2">开始饮酒年龄</td>\n' +
          '     <td colspan="2">{{drinkingAge}}</td>\n' +
          '     <td colspan="2">近一年内是否醉酒</td>\n' +
          '     <td colspan="2">{{drunk}}</td>\n' +
          '   </tr>\n' +
          '   <tr>\n' +
          '     <td colspan="2">饮酒种类</td>\n' +
          '     <td colspan="6">{{drinkingTypes}}</td>\n' +
          '   </tr>\n' +
          '   <tr>\n' +
          '    <td>职业病危害因素接触史</td>\n' +
          '    <td colspan="8">{{workDiseases}}</td>\n' +
          '  </tr>\n' +
          '  <tr>\n' +
          '     <th rowspan="4" scope="row">脏器功能</th>\n' +
          '     <td>口腔</td>\n' +
          '     <td colspan="8">{{oralCavity}}</td>\n' +
          '  </tr>\n' +
          '  <tr>\n' +
          '     <td>视力</td>\n' +
          '     <td width="43">左眼</td>\n' +
          '     <td width="84">{{visionz}}</td>\n' +
          '     <td width="44" scope="row">右眼</td>\n' +
          '     <th width="74" scope="row">{{visiony}}</th>\n' +
          '     <td width="55">矫正视力左眼</td>\n' +
          '     <td width="60">{{vicorrectz}}</td>\n' +
          '     <td width="57">矫正视力右眼</td>\n' +
          '     <td width="91">{{vicorrecty}}</td>\n' +
          '  </tr>\n' +
          '  <tr>\n' +
          '     <td>听力</td>\n' +
          '     <td colspan="8">{{listen}}</td>\n' +
          '  </tr>\n' +
          '  <tr>\n' +
          '     <td>运动功能</td>\n' +
          '     <td colspan="8">{{sport}}</td>\n' +
          '  </tr>\n' +
          '  <tr>\n' +
          '     <th rowspan="25" scope="row">查体</th>\n' +
          '     <td>眼底</td>\n' +
          '     <td colspan="8">{{bottomEye}}</td>\n' +
          '  </tr>\n' +
          '  <tr>\n' +
          '     <td>皮肤</td>\n' +
          '     <td colspan="8">{{skin}}</td>\n' +
          '  </tr>\n' +
          '  <tr>\n' +
          '      <td>巩膜</td>\n' +
          '     <td colspan="8">{{sclera}}</td>\n' +
          '  </tr>\n' +
          '  <tr>\n' +
          '      <td>淋巴结</td>\n' +
          '     <td colspan="8">{{lymphNode}}</td>\n' +
          '  </tr>\n' +
          '  <tr>\n' +
          '      <td rowspan="3">肺</td>\n' +
          '    <td colspan="1">桶状胸</td>\n' +
          '    <td colspan="7">{{barrelchest}}</td>\n' +
          '     \n' +
          '  </tr>\n' +
          '  <tr>\n' +
          '    <td colspan="1">呼吸音</td>\n' +
          '    <td colspan="7">{{breathSounds}}</td>\n' +
          '  </tr>\n' +
          '  <tr>\n' +
          '     <td colspan="1">罗音</td>\n' +
          '    <td colspan="7">{{rale}}</td>\n' +
          '  </tr>\n' +
          '  <tr>\n' +
          '     <td rowspan="3">心脏</td>\n' +
          '     <td colspan="1">心率</td>\n' +
          '    <td colspan="7">{{heartrate}}</td>\n' +
          '  </tr>\n' +
          '  <tr>\n' +
          '     <td colspan="1">心律</td>\n' +
          '     <td colspan="7">{{hythmheart}}</td>\n' +
          '  </tr>\n' +
          '  <tr>\n' +
          '    <td colspan="1">杂音</td>\n' +
          '    <td colspan="7">{{noise}}</td>\n' +
          '  </tr>\n' +
          '  <tr>\n' +
          '    <td rowspan="5">腹部</td>\n' +
          '    <td colspan="1">压痛</td>\n' +
          '    <td colspan="7">{{tenderness}}</td>\n' +
          '  </tr>\n' +
          '  <tr>\n' +
          '    <td colspan="1">包块</td>\n' +
          '    <td colspan="7">{{bagpiece}}</td>\n' +
          '  </tr>\n' +
          '  <tr>\n' +
          '    <td colspan="1">肝大</td>\n' +
          '    <td colspan="7">{{hepatomegaly}}</td>\n' +
          '  </tr>\n' +
          '  <tr>\n' +
          '    <td colspan="1">脾大</td>\n' +
          '    <td colspan="7">{{splenomegaly}}</td>\n' +
          '  </tr>\n' +
          '  <tr>\n' +
          '    <td colspan="1">移动性浊音</td>\n' +
          '    <td colspan="7">{{mobilevoiced}}</td>\n' +
          '  </tr>\n' +
          '  <tr>\n' +
          '      <td>下肢水肿</td>\n' +
          '     <td colspan="8">{{limbEdema}}</td>\n' +
          '  </tr>\n' +
          '  <tr>\n' +
          '     <td>足背动脉搏动</td>\n' +
          '     <td colspan="8">{{footArteyPulse}}</td>\n' +
          '  </tr>\n' +
          '  <tr>\n' +
          '     <td>肛门指诊</td>\n' +
          '     <td colspan="8">{{analDiagnosis}}</td>\n' +
          '  </tr>\n' +
          '  <tr>\n' +
          '     <td>乳腺</td>\n' +
          '     <td colspan="8">{{mammaryGland}}</td>\n' +
          '  </tr>\n' +
          '  <tr>\n' +
          '     <td rowspan="5">妇科</td>\n' +
          '     <td colspan="1">外阴</td>\n' +
          '     <td colspan="7">{{vulva}}</td>\n' +
          '  </tr>\n' +
          '  <tr>\n' +
          '    <td colspan="1">阴道</td>\n' +
          '    <td colspan="7">{{vagina}}</td>\n' +
          '  </tr>\n' +
          '  <tr>\n' +
          '    <td colspan="1">宫颈</td>\n' +
          '    <td colspan="7">{{cervix}}</td>\n' +
          '  </tr>\n' +
          '  <tr>\n' +
          '    <td colspan="1">宫体</td>\n' +
          '    <td colspan="7">{{palace}}</td>\n' +
          '  </tr>\n' +
          '  <tr>\n' +
          '    <td colspan="1">附件</td>\n' +
          '    <td colspan="7">{{attachment}}</td>\n' +
          '  </tr>\n' +
          '  <tr>\n' +
          '     <td>其他</td>\n' +
          '     <td colspan="8">{{other2}}</td>\n' +
          '  </tr>\n' +
          '  <tr>\n' +
          '     <th rowspan="21" scope="row">辅助检查</th>\n' +
          '     <td rowspan="2">血常规</td>\n' +
          '     <td colspan="2">血红蛋白</td>\n' +
          '     <td colspan="2"><div align="right">{{hemoglobin}}g/L</div></td>\n' +
          '     <td colspan="2">白细胞</td>\n' +
          '     <td colspan="2"><div align="right">{{whitebloodcells}}×10^9/L</div></td>\n' +
          '  </tr>\n' +
          '  <tr>\n' +
          '     <td colspan="2">血小板</td>\n' +
          '     <td colspan="2"><div align="right">{{platelet}}×10^9/L</div></td>\n' +
          '     <td colspan="2">其他</td>\n' +
          '    <td colspan="2"><div align="right">{{other3}}</div></td>\n' +
          '  </tr>\n' +
          '  <tr>\n' +
          '     <td rowspan="2">尿常规</td>\n' +
          '     <td colspan="2">尿蛋白</td>\n' +
          '     <td colspan="2">{{urineprotein}}</td>\n' +
          '     <td colspan="2">尿糖</td>\n' +
          '    <td colspan="2">{{urinesugar}}</td>\n' +
          '  </tr>\n' +
          '  <tr>\n' +
          '    <td colspan="2">尿酮体</td>\n' +
          '     <td colspan="2">{{urineketone}}</td>\n' +
          '     <td colspan="2">尿潜血</td>\n' +
          '    <td colspan="2">{{ery}}</td>\n' +
          '  </tr>\n' +
          '  <tr>\n' +
          '    <td>空腹血糖</td>\n' +
          '    <td colspan="4"><div align="right">{{fastingBloodSugar}}mmol/L</div></td>\n' +
          '    <td colspan="4"><div align="right">mg/dL</div></td>\n' +
          '  </tr>\n' +
          '  <tr>\n' +
          '     <td>心电图</td>\n' +
          '     <td colspan="8">{{electrocardiogram}}</td>\n' +
          '  </tr>\n' +
          '  <tr>\n' +
          '     <td>尿微量白蛋白</td>\n' +
          '     <td colspan="8">{{urineMicroalbumin}}</td>\n' +
          '  </tr>\n' +
          '  <tr>\n' +
          '     <td>大便潜血</td>\n' +
          '     <td colspan="8">{{shitblood}}</td>\n' +
          '  </tr>\n' +
          '  <tr>\n' +
          '     <td>糖化血红蛋白</td>\n' +
          '     <td colspan="8">{{glycatedGlycemic}}</td>\n' +
          '  </tr>\n' +
          '  <tr>\n' +
          '     <td>乙型肝炎  表面抗原</td>\n' +
          '     <td colspan="8">{{hepatitisb}}</td>\n' +
          '  </tr>\n' +
          '  <tr>\n' +
          '    <td rowspan="3">肝功能</td>\n' +
          '    <td colspan="2">血清谷丙转氨酶</td>\n' +
          '    <td colspan="2"><div align="right">{{serumtransa}}U/L</div></td>\n' +
          '    <td colspan="2">血清谷草转氨酶</td>\n' +
          '    <td colspan="2"><div align="right">{{glutamictransame}}U/L</div></td>\n' +
          '  </tr>\n' +
          '  <tr>\n' +
          '    <td colspan="2">白蛋白</td>\n' +
          '     <td colspan="2"><div align="right">{{albumin}}g/L</div></td>\n' +
          '     <td colspan="2">总胆红素</td>\n' +
          '     <td colspan="2"><div align="right">{{totalbilirubin}}umol/L</div></td>\n' +
          '  </tr>\n' +
          '  <tr>\n' +
          '    <td colspan="2">结合胆红素</td>\n' +
          '     <td colspan="2"><div align="right">{{boundbilirubin}}μmol/L</div></td>\n' +
          '     <td colspan="2">&nbsp;</td>\n' +
          '     <td colspan="2">&nbsp;</td>\n' +
          '  </tr>\n' +
          '  <tr>\n' +
          '     <td rowspan="2">肾功能</td>\n' +
          '     <td colspan="2">血尿素</td>\n' +
          '     <td colspan="2"><div align="right">{{bloodurea}}mmol/L</div></td>\n' +
          '     <td colspan="2">血钾浓度</td>\n' +
          '     <td colspan="2"><div align="right">{{potconcen}}mmol/L</div></td>\n' +
          '  </tr>\n' +
          '  <tr>\n' +
          '     <td colspan="2">血钠浓度</td>\n' +
          '     <td colspan="2"><div align="right">{{sodiumconcen}}mmol/L</div></td>\n' +
          '     <td colspan="2"></td>\n' +
          '     <td colspan="2"></td>\n' +
          '  </tr>\n' +
          '  <tr>\n' +
          '     <td rowspan="2">血 脂</td>\n' +
          '     <td colspan="2">总胆固醇</td>\n' +
          '     <td colspan="2"><div align="right">{{totalcholestero}}mmol/L </div></td>\n' +
          '     <td colspan="2">血清低密度脂蛋白胆固醇</td>\n' +
          '     <td colspan="2"><div align="right">{{ldlcholesterol}}mmol/L </div></td>\n' +
          '  </tr>\n' +
          '  <tr>\n' +
          '    <td colspan="2">血清高密度脂蛋白胆固醇</td>\n' +
          '     <td colspan="2"><div align="right">{{hdlcholesterol}}mmol/L </div></td>\n' +
          '     <td colspan="2"></td>\n' +
          '    <td colspan="2"></td>\n' +
          '  </tr>\n' +
          '  <tr>\n' +
          '     <td height="25">胸部 X 线片</td>\n' +
          '  <td colspan="8">{{chestxray}}</td>  ' +
          '  </tr>\n' +
          '  <tr>\n' +
          '     <td>B 超</td>\n' +
          '     <td colspan="2">腹部B超</td>\n' +
          '     <td colspan="2">{{bsuper}}</td>\n' +
          '     <td colspan="2">其他</td>\n' +
          '     <td colspan="2">&nbsp;</td>\n' +
          '  </tr>\n' +
          '  <tr>\n' +
          '     <td>宫颈涂片</td>\n' +
          '     <td colspan="8">{{cervicalSmear}}</td>\n' +
          '  </tr>\n' +
          '  <tr>\n' +
          '     <td>其他</td>\n' +
          '     <td colspan="8">{{other6}}</td>\n' +
          '  </tr>\n' +
          '  <tr>\n' +
          '     <th rowspan="7" scope="row">现存主要  健康问题</th>\n' +
          '     <td>脑血管疾病</td>\n' +
          '     <td colspan="8">{{cerebrovascular}}</td>\n' +
          '  </tr>\n' +
          '  <tr>\n' +
          '     <td>肾脏疾病</td>\n' +
          '     <td colspan="8">{{kidneyDisease}}</td>\n' +
          '  </tr>\n' +
          '  <tr>\n' +
          '     <td>心脏疾病</td>\n' +
          '     <td colspan="8">{{heartDisease}}</td>\n' +
          '  </tr>\n' +
          '  <tr>\n' +
          '     <td>血管疾病</td>\n' +
          '     <td colspan="8">{{vascularDisease}}</td>\n' +
          '  </tr>\n' +
          '  <tr>\n' +
          '    <td>眼部疾病</td>\n' +
          '    <td colspan="8">{{eyeDisease}}</td>\n' +
          '  </tr>\n' +
          '  <tr>\n' +
          '    <td>神经系统疾病</td>\n' +
          '    <td colspan="8">{{nervousSystem}}</td>\n' +
          '  </tr>\n' +
          '  <tr>\n' +
          '    <td>其他系统疾病</td>\n' +
          '    <td colspan="8">{{otherDiabetes}}</td>\n' +
          '  </tr>\n' +
          '  <tr>\n' +
          '    <th rowspan="6" scope="row">住院治疗情况</th>\n' +
          '    <td rowspan="3">住院史</td>\n' +
          '    <td colspan="2">入/出院日期</td>\n' +
          '    <td colspan="2">原 因</td>\n' +
          '    <td colspan="2">医疗机构名称</td>\n' +
          '    <td colspan="2">病案号</td>\n' +
          '  </tr>\n' +
          '  <tr>\n' +
          '    <td colspan="2"><div align="center">{{inhDate}}/{{outhDate}}</div></td>\n' +
          '    <td colspan="2">{{institutName}}</td>\n' +
          '    <td colspan="2">{{medicalrecordno}}</td>\n' +
          '    <td colspan="2">{{why}}</td>\n' +
          '  </tr>\n' +
          '  <tr>\n' +
          '    <td colspan="2"><div align="center">/</div></td>\n' +
          '    <td colspan="2">&nbsp;</td>\n' +
          '    <td colspan="2">&nbsp;</td>\n' +
          '    <td colspan="2">&nbsp;</td>\n' +
          '  </tr>\n' +
          '  <tr>\n' +
          '    <td rowspan="3">家庭病床史</td>\n' +
          '    <td colspan="2">建/撤床日期</td>\n' +
          '    <td colspan="2">原 因</td>\n' +
          '    <td colspan="2">医疗机构名称</td>\n' +
          '    <td colspan="2">病案号</td>\n' +
          '  </tr>\n' +
          '  <tr>\n' +
          '    <td colspan="2"><div align="center">{{inbDate}}/{{outbDate}}</div></td>\n' +
          '    <td colspan="2">{{why1}}</td>\n' +
          '    <td colspan="2">{{institutName1}}</td>\n' +
          '    <td colspan="2">{{medicalrecordno1}}</td>\n' +
          '  </tr>\n' +
          '  <tr>\n' +
          '    <td colspan="2"><div align="center">/</div></td>\n' +
          '    <td colspan="2">&nbsp;</td>\n' +
          '    <td colspan="2">&nbsp;</td>\n' +
          '    <td colspan="2">&nbsp;</td>\n' +
          '  </tr>\n' +
          '  <tr>\n' +
          '    <th rowspan="7" scope="row">主要用药  情况</th>\n' +
          '    <td colspan="2">药物名称</td>\n' +
          '    <td colspan="2">用法</td>\n' +
          '    <td colspan="2">用量</td>\n' +
          '    <td colspan="2">用药时间</td>\n' +
          '    <td colspan="1">用药依从性</td>\n' +
          '  </tr>\n' +
          '  <tr>\n' +
          '    <td colspan="2">{{drugname}}</td>\n' +
          '    <td colspan="2">{{useing}}</td>\n' +
          '    <td colspan="2">{{dosage}}</td>\n' +
          '    <td colspan="2">{{duration}}</td>\n' +
          '    <td colspan="1">{{medicompliance}}</td>\n' +
          '  </tr>\n' +
          '  <tr>\n' +
          '    <td colspan="2">&nbsp;</td>\n' +
          '    <td colspan="2">&nbsp;</td>\n' +
          '    <td colspan="2">&nbsp;</td>\n' +
          '    <td colspan="2">&nbsp;</td>\n' +
          '    <td colspan="1">&nbsp;</td>\n' +
          '  </tr>\n' +
          '  <tr>\n' +
          '    <td colspan="2">&nbsp;</td>\n' +
          '    <td colspan="2">&nbsp;</td>\n' +
          '    <td colspan="2">&nbsp;</td>\n' +
          '    <td colspan="2">&nbsp;</td>\n' +
          '    <td colspan="1">&nbsp;</td>\n' +
          '  </tr>\n' +
          '  <tr>\n' +
          '    <td colspan="2">&nbsp;</td>\n' +
          '    <td colspan="2">&nbsp;</td>\n' +
          '    <td colspan="2">&nbsp;</td>\n' +
          '    <td colspan="2">&nbsp;</td>\n' +
          '    <td colspan="1">&nbsp;</td>\n' +
          '  </tr>\n' +
          '  <tr>\n' +
          '    <td colspan="2">&nbsp;</td>\n' +
          '    <td colspan="2">&nbsp;</td>\n' +
          '    <td colspan="2">&nbsp;</td>\n' +
          '    <td colspan="2">&nbsp;</td>\n' +
          '    <td colspan="1">&nbsp;</td>\n' +
          '  </tr>\n' +
          '  <tr>\n' +
          '    <td colspan="2">&nbsp;</td>\n' +
          '    <td colspan="2">&nbsp;</td>\n' +
          '    <td colspan="2">&nbsp;</td>\n' +
          '    <td colspan="2">&nbsp;</td>\n' +
          '    <td colspan="1">&nbsp;</td>\n' +
          '  </tr>\n' +
          '  <tr>\n' +
          '    <th rowspan="4" scope="row">非免疫  规划预防  接种史</th>\n' +
          '    <td colspan="2">名称</td>\n' +
          '    <td colspan="2">接种日期</td>\n' +
          '    <td colspan="5">接种机构</td>\n' +
          '  </tr>\n' +
          '  <tr>\n' +
          '    <td colspan="2">{{vaccinationName}}</td>\n' +
          '    <td colspan="2">{{vaccinationDate}}</td>\n' +
          '    <td colspan="5">{{vaccinationAgency}}</td>\n' +
          '  </tr>\n' +
          '  <tr>\n' +
          '    <td colspan="2">&nbsp;</td>\n' +
          '    <td colspan="2">&nbsp;</td>\n' +
          '    <td colspan="5">&nbsp;</td>\n' +
          '  </tr>\n' +
          '  <tr>\n' +
          '     <td colspan="2">&nbsp;</td>\n' +
          '    <td colspan="2">&nbsp;</td>\n' +
          '    <td colspan="5">&nbsp;</td>\n' +
          '  </tr>\n' +
          '  <tr>\n' +
          '    <th height="49" scope="row">健康评价</th>\n' +
          '    <td colspan="9">{{healthEvaluation}}</td>\n' +
          '  </tr>\n' +
          '  <tr>\n' +
          '    <th height="47" scope="row">健康指导</th>\n' +
          '    <td colspan="9">{{healthGuidance}}</td>\n' +
          '  </tr>\n' +
          '  <tr>\n' +
          '    <th scope="row">危险因素控制</th>\n' +
          '    <td colspan="9">{{riskcontrol}}</td>\n' +
          '  </tr>\n' +
          '</table>\n' +
          '</div>\n' +
          '</body>\n' +
          '</html>\n'
        str = str.replace(/{{name}}/, this.editForm.name ? this.editForm.name : '')
        str = str.replace(/{{id}}/, this.editForm.id ? this.editForm.id : '')
        str = str.replace(/{{checkDate}}/, this.editForm.checkDate ? this.editForm.checkDate : '')
        str = str.replace(/{{doctor}}/, this.editForm.doctor ? this.editForm.doctor : '')
        str = str.replace(/{{symptoms}}/, this.editForm.symptoms ? this.editForm.symptoms : '')
        str = str.replace(/{{temperature}}/, this.editForm.temperature ? this.editForm.temperature : '')
        str = str.replace(/{{pulseRate}}/, this.editForm.pulseRate ? this.editForm.pulseRate : '')
        str = str.replace(/{{breathing}}/, this.editForm.breathing ? this.editForm.breathing : '')
        str = str.replace(/{{stature}}/, this.editForm.stature ? this.editForm.stature : '')
        str = str.replace(/{{weight}}/, this.editForm.weight ? this.editForm.weight : '')
        str = str.replace(/{{waistline}}/, this.editForm.waistline ? this.editForm.waistline : '')
        str = str.replace(/{{bodymassindex}}/, this.editForm.bodymassindex ? this.editForm.bodymassindex : '')
        str = str.replace(/{{selfAssessment}}/, this.editForm.selfAssessment ? this.editForm.selfAssessment : '')
        str = str.replace(/{{selfCare}}/, this.editForm.selfCare ? this.editForm.selfCare : '')
        str = str.replace(/{{cognition}}/, this.editForm.cognition ? this.editForm.cognition : '')
        str = str.replace(/{{mood}}/, this.editForm.mood ? this.editForm.mood : '')
        str = str.replace(/{{exercise}}/, this.editForm.exercise ? this.editForm.exercise : '')
        str = str.replace(/{{diet}}/, this.editForm.diet ? this.editForm.diet : '')
        str = str.replace(/{{smoking}}/, this.editForm.smoking ? this.editForm.smoking : '')
        str = str.replace(/{{drinking}}/, this.editForm.drinking ? this.editForm.drinking : '')
        str = str.replace(/{{workDiseases}}/, this.editForm.workDiseases ? this.editForm.workDiseases : '')
        str = str.replace(/{{oralCavity}}/, this.editForm.oralCavity ? this.editForm.oralCavity : '')
        str = str.replace(/{{listen}}/, this.editForm.listen ? this.editForm.listen : '')
        str = str.replace(/{{sport}}/, this.editForm.sport ? this.editForm.sport : '')
        str = str.replace(/{{bottomEye}}/, this.editForm.bottomEye ? this.editForm.bottomEye : '')
        str = str.replace(/{{skin}}/, this.editForm.skin ? this.editForm.skin : '')
        str = str.replace(/{{sclera}}/, this.editForm.sclera ? this.editForm.sclera : '')
        str = str.replace(/{{lymphNode}}/, this.editForm.lymphNode ? this.editForm.lymphNode : '')
        str = str.replace(/{{limbEdema}}/, this.editForm.limbEdema ? this.editForm.limbEdema : '')
        str = str.replace(/{{footArteyPulse}}/, this.editForm.footArteyPulse ? this.editForm.footArteyPulse : '')
        str = str.replace(/{{analDiagnosis}}/, this.editForm.analDiagnosis ? this.editForm.analDiagnosis : '')
        str = str.replace(/{{mammaryGland}}/, this.editForm.mammaryGland ? this.editForm.mammaryGland : '')
        str = str.replace(/{{vulva}}/, this.editForm.vulva ? this.editForm.vulva : '')
        str = str.replace(/{{vagina}}/, this.editForm.vagina ? this.editForm.vagina : '')
        str = str.replace(/{{cervix}}/, this.editForm.cervix ? this.editForm.cervix : '')
        str = str.replace(/{{palace}}/, this.editForm.palace ? this.editForm.palace : '')
        str = str.replace(/{{fastingBloodSugar}}/, this.editForm.fastingBloodSugar ? this.editForm.fastingBloodSugar : '')
        str = str.replace(/{{electrocardiogram}}/, this.editForm.electrocardiogram ? this.editForm.electrocardiogram : '')
        str = str.replace(/{{urineMicroalbumin}}/, this.editForm.urineMicroalbumin ? this.editForm.urineMicroalbumin : '')
        str = str.replace(/{{shitblood}}/, this.editForm.shitblood ? this.editForm.shitblood : '')
        str = str.replace(/{{glycatedGlycemic}}/, this.editForm.glycatedGlycemic ? this.editForm.glycatedGlycemic : '')
        str = str.replace(/{{hepatitisb}}/, this.editForm.hepatitisb ? this.editForm.hepatitisb : '')
        str = str.replace(/{{chestxray}}/, this.editForm.chestxray ? this.editForm.chestxray : '')
        str = str.replace(/{{bsuper}}/, this.editForm.bsuper ? this.editForm.bsuper : '')
        str = str.replace(/{{cervicalSmear}}/, this.editForm.cervicalSmear ? this.editForm.cervicalSmear : '')
        str = str.replace(/{{cerebrovascular}}/, this.editForm.cerebrovascular ? this.editForm.cerebrovascular : '')
        str = str.replace(/{{kidneyDisease}}/, this.editForm.kidneyDisease ? this.editForm.kidneyDisease : '')
        str = str.replace(/{{heartDisease}}/, this.editForm.heartDisease ? this.editForm.heartDisease : '')
        str = str.replace(/{{vascularDisease}}/, this.editForm.vascularDisease ? this.editForm.vascularDisease : '')
        str = str.replace(/{{eyeDisease}}/, this.editForm.eyeDisease ? this.editForm.eyeDisease : '')
        str = str.replace(/{{nervousSystem}}/, this.editForm.nervousSystem ? this.editForm.nervousSystem : '')
        str = str.replace(/{{otherDiabetes}}/, this.editForm.otherDiabetes ? this.editForm.otherDiabetes : '')
        str = str.replace(/{{healthEvaluation}}/, this.editForm.healthEvaluation ? this.editForm.healthEvaluation : '')
        str = str.replace(/{{healthGuidance}}/, this.editForm.healthGuidance ? this.editForm.healthGuidance : '')
        str = str.replace(/{{riskcontrol}}/, this.editForm.riskcontrol ? this.editForm.riskcontrol : '')
        str = str.replace(/{{pressureleft}}/, this.editForm.pressureleft ? this.editForm.pressureleft : '')
        str = str.replace(/{{pressureright}}/, this.editForm.pressureright ? this.editForm.pressureright : '')
        str = str.replace(/{{exerciseTime}}/, this.editForm.exerciseTime ? this.editForm.exerciseTime : '')
        str = str.replace(/{{stickTime}}/, this.editForm.stickTime ? this.editForm.stickTime : '')
        str = str.replace(/{{peopleId}}/, this.editForm.peopleId ? this.editForm.peopleId : '')
        str = str.replace(/{{sex}}/, this.editForm.sex ? this.editForm.sex : '')
        str = str.replace(/{{exercisestyle}}/, this.editForm.exercisestyle ? this.editForm.exercisestyle : '')
        str = str.replace(/{{breathSounds}}/, this.editForm.breathSounds ? this.editForm.breathSounds : '')
        str = str.replace(/{{everySmoking}}/, this.editForm.everySmoking ? this.editForm.everySmoking : '')
        str = str.replace(/{{smokingAge}}/, this.editForm.smokingAge ? this.editForm.smokingAge : '')
        str = str.replace(/{{noSmokingAge}}/, this.editForm.noSmokingAge ? this.editForm.noSmokingAge : '')
        str = str.replace(/{{everydrinking}}/, this.editForm.everydrinking ? this.editForm.everydrinking : '')
        str = str.replace(/{{drinkingTypes}}/, this.editForm.drinkingTypes ? this.editForm.drinkingTypes : '')
        str = str.replace(/{{nodrinking}}/, this.editForm.nodrinking ? this.editForm.nodrinking : '')
        str = str.replace(/{{drunk}}/, this.editForm.drunk ? this.editForm.drunk : '')
        str = str.replace(/{{drinkingAge}}/, this.editForm.drinkingAge ? this.editForm.drinkingAge : '')
        str = str.replace(/{{workType}}/, this.editForm.workType ? this.editForm.workType : '')
        str = str.replace(/{{workingTime}}/, this.editForm.workingTime ? this.editForm.workingTime : '')
        str = str.replace(/{{dust}}/, this.editForm.dust ? this.editForm.dust : '')
        str = str.replace(/{{dustproMeasures}}/, this.editForm.dustproMeasures ? this.editForm.dustproMeasures : '')
        str = str.replace(/{{fanshe}}/, this.editForm.fanshe ? this.editForm.fanshe : '')
        str = str.replace(/{{fansheproMeasures}}/, this.editForm.fansheproMeasures ? this.editForm.fansheproMeasures : '')
        str = str.replace(/{{physicalfactors}}/, this.editForm.physicalfactors ? this.editForm.physicalfactors : '')
        str = str.replace(/{{phyproMeasures}}/, this.editForm.phyproMeasures ? this.editForm.phyproMeasures : '')
        str = str.replace(/{{chemicals}}/, this.editForm.chemicals ? this.editForm.chemicals : '')
        str = str.replace(/{{cheproMeasures}}/, this.editForm.cheproMeasures ? this.editForm.cheproMeasures : '')
        str = str.replace(/{{other1}}/, this.editForm.other1 ? this.editForm.other1 : '')
        str = str.replace(/{{other1ProMeasures}}/, this.editForm.other1ProMeasures ? this.editForm.other1ProMeasures : '')
        str = str.replace(/{{visionz}}/, this.editForm.visionz ? this.editForm.visionz : '')
        str = str.replace(/{{visiony}}/, this.editForm.visiony ? this.editForm.visiony : '')
        str = str.replace(/{{vicorrectz}}/, this.editForm.vicorrectz ? this.editForm.vicorrectz : '')
        str = str.replace(/{{vicorrecty}}/, this.editForm.vicorrecty ? this.editForm.vicorrecty : '')
        str = str.replace(/{{heartrate}}/, this.editForm.heartrate ? this.editForm.heartrate : '')
        str = str.replace(/{{barrelchest}}/, this.editForm.barrelchest ? this.editForm.barrelchest : '')
        str = str.replace(/{{noise}}/, this.editForm.noise ? this.editForm.noise : '')
        str = str.replace(/{{hythmheart}}/, this.editForm.hythmheart ? this.editForm.hythmheart : '')
        str = str.replace(/{{tenderness}}/, this.editForm.tenderness ? this.editForm.tenderness : '')
        str = str.replace(/{{bagpiece}}/, this.editForm.bagpiece ? this.editForm.bagpiece : '')
        str = str.replace(/{{hepatomegaly}}/, this.editForm.hepatomegaly ? this.editForm.hepatomegaly : '')
        str = str.replace(/{{splenomegaly}}/, this.editForm.splenomegaly ? this.editForm.splenomegaly : '')
        str = str.replace(/{{mobilevoiced}}/, this.editForm.mobilevoiced ? this.editForm.mobilevoiced : '')
        str = str.replace(/{{rale}}/, this.editForm.rale ? this.editForm.rale : '')
        str = str.replace(/{{attachment}}/, this.editForm.attachment ? this.editForm.attachment : '')
        str = str.replace(/{{other2}}/, this.editForm.other2 ? this.editForm.other2 : '')
        str = str.replace(/{{whitebloodcells}}/, this.editForm.whitebloodcells ? this.editForm.whitebloodcells : '')
        str = str.replace(/{{platelet}}/, this.editForm.platelet ? this.editForm.platelet : '')
        str = str.replace(/{{other3}}/, this.editForm.other3 ? this.editForm.other3 : '')
        str = str.replace(/{{urineprotein}}/, this.editForm.urineprotein ? this.editForm.urineprotein : '')
        str = str.replace(/{{urinesugar}}/, this.editForm.urinesugar ? this.editForm.urinesugar : '')
        str = str.replace(/{{urineketone}}/, this.editForm.urineketone ? this.editForm.urineketone : '')
        str = str.replace(/{{ery}}/, this.editForm.ery ? this.editForm.ery : '')
        str = str.replace(/{{serumtransa}}/, this.editForm.serumtransa ? this.editForm.serumtransa : '')
        str = str.replace(/{{glutamictransame}}/, this.editForm.glutamictransame ? this.editForm.glutamictransame : '')
        str = str.replace(/{{totalbilirubin}}/, this.editForm.totalbilirubin ? this.editForm.totalbilirubin : '')
        str = str.replace(/{{boundbilirubin}}/, this.editForm.boundbilirubin ? this.editForm.boundbilirubin : '')
        str = str.replace(/{{bloodurea}}/, this.editForm.bloodurea ? this.editForm.bloodurea : '')
        str = str.replace(/{{hemoglobin}}/, this.editForm.hemoglobin ? this.editForm.hemoglobin : '')
        str = str.replace(/{{potconcen}}/, this.editForm.potconcen ? this.editForm.potconcen : '')
        str = str.replace(/{{sodiumconcen}}/, this.editForm.sodiumconcen ? this.editForm.sodiumconcen : '')
        str = str.replace(/{{totalcholestero}}/, this.editForm.totalcholestero ? this.editForm.totalcholestero : '')
        str = str.replace(/{{ldlcholesterol}}/, this.editForm.ldlcholesterol ? this.editForm.ldlcholesterol : '')
        str = str.replace(/{{hdlcholesterol}}/, this.editForm.hdlcholesterol ? this.editForm.hdlcholesterol : '')
        str = str.replace(/{{other6}}/, this.editForm.other6 ? this.editForm.other6 : '')
        str = str.replace(/{{inhDate}}/, this.editForm.inhDate ? this.editForm.inhDate : '')
        str = str.replace(/{{outhDate}}/, this.editForm.outhDate ? this.editForm.outhDate : '')
        str = str.replace(/{{institutName}}/, this.editForm.institutName ? this.editForm.institutName : '')
        str = str.replace(/{{medicalrecordno}}/, this.editForm.medicalrecordno ? this.editForm.medicalrecordno : '')
        str = str.replace(/{{why}}/, this.editForm.why ? this.editForm.why : '')
        str = str.replace(/{{inbDate}}/, this.editForm.inbDate ? this.editForm.inbDate : '')
        str = str.replace(/{{outbDate}}/, this.editForm.outbDate ? this.editForm.outbDate : '')
        str = str.replace(/{{why1}}/, this.editForm.why1 ? this.editForm.why1 : '')
        str = str.replace(/{{institutName1}}/, this.editForm.institutName1 ? this.editForm.institutName1 : '')
        str = str.replace(/{{medicalrecordno1}}/, this.editForm.medicalrecordno1 ? this.editForm.medicalrecordno1 : '')
        str = str.replace(/{{vaccinationName}}/, this.editForm.vaccinationName ? this.editForm.vaccinationName : '')
        str = str.replace(/{{vaccinationDate}}/, this.editForm.vaccinationDate ? this.editForm.vaccinationDate : '')
        str = str.replace(/{{vaccinationAgency}}/, this.editForm.vaccinationAgency ? this.editForm.vaccinationAgency : '')
        str = str.replace(/{{drugname}}/, this.editForm.drugname ? this.editForm.drugname : '')
        str = str.replace(/{{dosage}}/, this.editForm.dosage ? this.editForm.dosage : '')
        str = str.replace(/{{duration}}/, this.editForm.duration ? this.editForm.duration : '')
        str = str.replace(/{{medicompliance}}/, this.editForm.medicompliance ? this.editForm.medicompliance : '')
        str = str.replace(/{{albumin}}/, this.editForm.albumin ? this.editForm.albumin : '')
        str = str.replace(/{{useing}}/, this.editForm.useing ? this.editForm.useing : '')

        myWindow.document.write(str)

        // myWindow.document.write(content + style)
        myWindow.focus()
        myWindow.document.close()
        myWindow.print()
        return myWindow
      },
      conprint() {
        const windows = this.printf(document.getElementById('printData').innerHTML)
        windows.close()
      },
      // 获取用户列表
      getUsers() {
        const para = {
          page: this.page,
          size: 20,
          filters: { name: this.filters.name, peopleId: this.filters.peopleId }
        }
        getList(para).then(res => {
          this.total = res.detailModelList.length
          this.users = res.detailModelList
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
              this.getUsers()
            })
          })
          .catch(() => {})
      },
      // 详情
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
        this.edit = true
        this.editForm = Object.assign({}, row)
        this.editForm.symptoms = this.editForm.symptoms.split(',')
        this.editForm.diet = this.editForm.diet.split(',')
        this.editForm.cerebrovascular = this.editForm.cerebrovascular.split(',')
        this.editForm.kidneyDisease = this.editForm.kidneyDisease.split(',')
        this.editForm.heartDisease = this.editForm.heartDisease.split(',')
        this.editForm.vascularDisease = this.editForm.vascularDisease.split(',')
        this.editForm.eyeDisease = this.editForm.eyeDisease.split(',')
        this.editForm.healthGuidance = this.editForm.healthGuidance.split(',')
        this.editForm.riskcontrol = this.editForm.riskcontrol.split(',')
      },
      // 显示新增界面
      handleAdd() {
        this.dialogStatus = 'create'
        this.dialogFormVisible = true
        this.edit = false
        this.editForm = {
          id: 0,
          peopleId: '',
          name: '',
          checkDate: '',
          doctor: '',
          symptoms: [],
          temperature: '',
          pulseRate: '',
          breathing: '',
          pressureleft: '',
          pressureright: '',
          stature: '',
          weight: '',
          waistline: '',
          bodymassindex: '',
          selfAssessment: '',
          selfCare: '',
          cognition: '',
          mood: '',
          exercise: '',
          exerciseTime: '',
          stickTime: '',
          diet: [],
          smoking: '',
          everySmoking: '',
          drinking: '',
          workDiseases: '',
          riskcontrol: [],
          oralCavity: '',
          vision: '',
          listen: '',
          sport: '',
          bottomEye: '',
          skin: '',
          sclera: '',
          lymphNode: '',
          limbEdema: '',
          footArteyPulse: '',
          analDiagnosis: '',
          mammaryGland: '',
          vulva: '',
          vagina: '',
          cervix: '',
          palace: '',
          fastingBloodSugar: '',
          electrocardiogram: '',
          urineMicroalbumin: '',
          shitblood: '',
          glycatedGlycemic: '',
          hepatitisb: '',
          chestxray: '',
          bsuper: '',
          cervicalSmear: '',
          cerebrovascular: [],
          kidneyDisease: [],
          heartDisease: [],
          vascularDisease: [],
          eyeDisease: [],
          nervousSystem: '',
          otherDiabetes: '',
          healthEvaluation: '',
          healthGuidance: [],
          exercisestyle: '',
          inhDate: '',
          outhDate: '',
          inbDate: '',
          outbDate: '',
          vaccinationDate: ''
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

                para.checkDate =
                  !para.checkDate || para.checkDate === ''
                    ? ''
                    : util.formatDate.format(new Date(para.checkDate), 'yyyy-MM-dd')

                para.inhDate =
                  !para.inhDate || para.inhDate === ''
                    ? ''
                    : util.formatDate.format(new Date(para.inhDate), 'yyyy-MM-dd')

                para.outhDate =
                  !para.outhDate || para.outhDate === ''
                    ? ''
                    : util.formatDate.format(new Date(para.outhDate), 'yyyy-MM-dd')

                para.inbDate =
                  !para.inbDate || para.inbDate === ''
                    ? ''
                    : util.formatDate.format(new Date(para.inbDate), 'yyyy-MM-dd')

                para.outbDate =
                  !para.outbDate || para.outbDate === ''
                    ? ''
                    : util.formatDate.format(new Date(para.outbDate), 'yyyy-MM-dd')

                para.vaccinationDate =
                  !para.vaccinationDate || para.vaccinationDate === ''
                    ? ''
                    : util.formatDate.format(new Date(para.vaccinationDate), 'yyyy-MM-dd')
                // 字典处理
                // 危险控制
                para.riskcontrol = ''
                for (const itm of this.editForm.riskcontrol) {
                  para.riskcontrol += itm
                  para.riskcontrol += ','
                }
                para.riskcontrol = para.riskcontrol.substr(0, para.riskcontrol.length - 1)
                // 健康指导
                para.healthGuidance = ''
                for (const itm of this.editForm.healthGuidance) {
                  para.healthGuidance += itm
                  para.healthGuidance += ','
                }
                para.healthGuidance = para.healthGuidance.substr(0, para.healthGuidance.length - 1)
                // 眼部疾病
                para.eyeDisease = ''
                for (const itm of this.editForm.eyeDisease) {
                  para.eyeDisease += itm
                  para.eyeDisease += ','
                }
                para.eyeDisease = para.eyeDisease.substr(0, para.eyeDisease.length - 1)
                // 血管疾病
                para.vascularDisease = ''
                for (const itm of this.editForm.vascularDisease) {
                  para.vascularDisease += itm
                  para.vascularDisease += ','
                }
                para.vascularDisease = para.vascularDisease.substr(0, para.vascularDisease.length - 1)
                // 心脏疾病
                para.heartDisease = ''
                for (const itm of this.editForm.heartDisease) {
                  para.heartDisease += itm
                  para.heartDisease += ','
                }
                para.heartDisease = para.heartDisease.substr(0, para.heartDisease.length - 1)
                // 肾脏疾病
                para.kidneyDisease = ''
                for (const itm of this.editForm.kidneyDisease) {
                  para.kidneyDisease += itm
                  para.kidneyDisease += ','
                }
                para.kidneyDisease = para.kidneyDisease.substr(0, para.kidneyDisease.length - 1)
                // 脑血管疾病
                para.cerebrovascular = ''
                for (const itm of this.editForm.cerebrovascular) {
                  para.cerebrovascular += itm
                  para.cerebrovascular += ','
                }
                para.cerebrovascular = para.cerebrovascular.substr(0, para.cerebrovascular.length - 1)
                // 症状
                para.symptoms = ''
                for (const itm of this.editForm.symptoms) {
                  para.symptoms += itm
                  para.symptoms += ','
                }
                para.symptoms = para.symptoms.substr(0, para.symptoms.length - 1)

                // 饮食
                para.diet = ''
                for (const itm of this.editForm.diet) {
                  para.diet += itm
                  para.diet += ','
                }
                para.diet = para.diet.substr(0, para.diet.length - 1)
                updateData(para).then(res => {
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
              confirmButtonText: ' 确定',
              cancelButtonText: ' 取消'
            })
              .then(() => {
                const para = Object.assign({}, this.editForm)
                console.log(para)

                para.checkDate =
                  !para.checkDate || para.checkDate === ''
                    ? ''
                    : util.formatDate.format(new Date(para.checkDate), 'yyyy-MM-dd')

                para.inhDate =
                  !para.inhDate || para.inhDate === ''
                    ? ''
                    : util.formatDate.format(new Date(para.inhDate), 'yyyy-MM-dd')

                para.outhDate =
                  !para.outhDate || para.outhDate === ''
                    ? ''
                    : util.formatDate.format(new Date(para.outhDate), 'yyyy-MM-dd')

                para.inbDate =
                  !para.inbDate || para.inbDate === ''
                    ? ''
                    : util.formatDate.format(new Date(para.inbDate), 'yyyy-MM-dd')

                para.outbDate =
                  !para.outbDate || para.outbDate === ''
                    ? ''
                    : util.formatDate.format(new Date(para.outbDate), 'yyyy-MM-dd')

                para.vaccinationDate =
                  !para.vaccinationDate || para.vaccinationDate === ''
                    ? ''
                    : util.formatDate.format(new Date(para.vaccinationDate), 'yyyy-MM-dd')
                // 字典处理
                // 危险控制
                para.riskcontrol = ''
                for (const itm of this.editForm.riskcontrol) {
                  para.riskcontrol += itm
                  para.riskcontrol += ','
                }
                para.riskcontrol = para.riskcontrol.substr(0, para.riskcontrol.length - 1)
                // 健康指导
                para.healthGuidance = ''
                for (const itm of this.editForm.healthGuidance) {
                  para.healthGuidance += itm
                  para.healthGuidance += ','
                }
                para.healthGuidance = para.healthGuidance.substr(0, para.healthGuidance.length - 1)
                // 眼部疾病
                para.eyeDisease = ''
                for (const itm of this.editForm.eyeDisease) {
                  para.eyeDisease += itm
                  para.eyeDisease += ','
                }
                para.eyeDisease = para.eyeDisease.substr(0, para.eyeDisease.length - 1)
                // 血管疾病
                para.vascularDisease = ''
                for (const itm of this.editForm.vascularDisease) {
                  para.vascularDisease += itm
                  para.vascularDisease += ','
                }
                para.vascularDisease = para.vascularDisease.substr(0, para.vascularDisease.length - 1)
                // 心脏疾病
                para.heartDisease = ''
                for (const itm of this.editForm.heartDisease) {
                  para.heartDisease += itm
                  para.heartDisease += ','
                }
                para.heartDisease = para.heartDisease.substr(0, para.heartDisease.length - 1)
                // 肾脏疾病
                para.kidneyDisease = ''
                for (const itm of this.editForm.kidneyDisease) {
                  para.kidneyDisease += itm
                  para.kidneyDisease += ','
                }
                para.kidneyDisease = para.kidneyDisease.substr(0, para.kidneyDisease.length - 1)
                // 脑血管疾病
                para.cerebrovascular = ''
                for (const itm of this.editForm.cerebrovascular) {
                  para.cerebrovascular += itm
                  para.cerebrovascular += ','
                }
                para.cerebrovascular = para.cerebrovascular.substr(0, para.cerebrovascular.length - 1)
                // 症状
                para.symptoms = ''
                for (const itm of this.editForm.symptoms) {
                  para.symptoms += itm
                  para.symptoms += ','
                }
                para.symptoms = para.symptoms.substr(0, para.symptoms.length - 1)

                // 饮食
                para.diet = ''
                for (const itm of this.editForm.diet) {
                  para.diet += itm
                  para.diet += ','
                }
                para.diet = para.diet.substr(0, para.diet.length - 1)

                addData(para).then(res => {
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
      // 添加前检查此人是否已建档
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
                this.$message.error('该身份证还未建立个人档案')
              }
            })
          }
        })
      },
      // 批量删除
      batchRemove() {
        var ids = this.sels.map(item => item.id).toString()
        this.$confirm('确认删除选中记录吗？', '提示', {
          type: 'warning'
        })
          .then(() => {
            const para = { ids: ids }
            batchRemove(para).then(res => {
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
