<template xmlns:el-col="http://www.w3.org/1999/html">
  <section class="app-container">

    <!--工作条-->
    <el-col :span="24" class="toolbar" style="padding-bottom: 0px;">
      <el-form :inline="true" :model="filters" @submit.native.prevent>
        <el-form-item>
          <el-input v-model="filters.name" placeholder="姓名"></el-input>
        </el-form-item>
        <el-form-item>
          <el-input v-model="filters.idNum" placeholder="身份证号"></el-input>
        </el-form-item>
        <el-form-item>
          <el-input v-model="filters.doctor" placeholder="体检医生"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" v-on:click="getUsers" style="background-color:#4db2ee;border-color:transparent" icon="el-icon-search">查询</el-button>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="handleAdd" icon="el-icon-circle-plus-outline">新增</el-button>
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
        <el-table-column prop="idNum" label="身份证号" width="150">
        </el-table-column>
        <el-table-column prop="tPhoneNum" label="联系电话" min-width="120">
        </el-table-column>
        <el-table-column prop="doctor" label="体检医生" min-width="120">
        </el-table-column>
        <el-table-column prop="checkdate" label="体检日期" min-width="120">
        </el-table-column>
        <el-table-column prop="docDate" label="建档日期" min-width="120">
        </el-table-column>
        <el-table-column prop="docPeople" label="建档人" min-width="120">
        </el-table-column>
        <el-table-column label="操作" width="150">
          <template slot-scope="scope">
            <a size="14px" @click="handleEdit(scope.$index, scope.row)" style="color:#4DB2EE;margin-right:20px;">编辑</a>
            <a size="14px" @click="handleDel(scope.$index, scope.row)" style="color:#FB4141">删除</a>
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
      <div id="print">
        <el-form :model="editForm" label-width="80px" :rules="editFormRules" ref="editForm">
          <EasyScrollbar>
                  <div class="item" v-bind:style="{ height: tablebodyHeight + 'px'}">
                <el-tabs v-model="activeName">
                  <el-row>
                    <el-col :span="8">
                      <el-form-item label="体检医生"prop="doctor">
                        <el-input v-model="editForm.doctor" auto-complete="off"></el-input>
                      </el-form-item>
                    </el-col>
                    <el-col :span="8">
                      <el-form-item label="体检日期">
                        <el-date-picker type="date" placeholder="选择日期" v-model="editForm.checkDate"></el-date-picker>
                      </el-form-item>
                    </el-col>
                    <el-col :span="8">
                      <el-form-item label="姓名" prop="name">
                        <el-input v-model="editForm.name" auto-complete="off"></el-input>
                      </el-form-item>
                    </el-col>
                  </el-row>

                  <el-row>
                    <el-col :span="8">
                      <el-form-item label="身份证号"prop="idNum">
                        <el-input  v-model="editForm.idNum" auto-complete="off" ></el-input>
                      </el-form-item>
                    </el-col>
                    <el-col :span="8">
                      <el-form-item label="性别">
                        <el-radio-group v-model="editForm.sex">
                          <el-radio label="男">男</el-radio>
                          <el-radio label="女">女</el-radio>
                          <el-radio label="未知性别">未知性别</el-radio>
                        </el-radio-group>
                      </el-form-item>
                    </el-col>
                    <el-col :span="8">
                      <el-form-item label="体温">
                        <el-input v-model="editForm.temperature">
                          <template slot="append">℃</template>
                        </el-input>
                      </el-form-item>
                    </el-col>
                  </el-row>

                  <el-row>
                    <el-col :span="6">
                      <el-form-item label="脉率">
                        <el-input v-model="editForm.pulseRate" >
                          <template slot="append">次/分钟</template>
                        </el-input>
                      </el-form-item>
                    </el-col>
                    <el-col :span="6">
                      <el-form-item label="呼吸频率">
                        <el-input v-model="editForm.breathing" >
                          <template slot="append">次/分钟</template>
                        </el-input>
                      </el-form-item>
                    </el-col>
                    <el-col :span="6">
                      <el-form-item label="身高">
                        <el-input v-model="editForm.stature" >
                          <template slot="append">cm</template>
                        </el-input>
                      </el-form-item>
                    </el-col>
                    <el-col :span="6">
                      <el-form-item label="体重">
                        <el-input v-model="editForm.weight" >
                          <template slot="append">kg</template>
                        </el-input>
                      </el-form-item>
                    </el-col>
                  </el-row>

                  <el-row>
                    <el-col :span="6">
                      <el-form-item label="腰围">
                        <el-input v-model="editForm.waistline" auto-complete="off">
                          <template slot="append">cm</template>
                        </el-input>
                      </el-form-item>
                    </el-col>
                    <el-col :span="6">
                      <el-form-item label="体质指数">
                        <el-input v-model="editForm.bodymassindex" auto-complete="off">
                          <template slot="append">kg/㎡</template>
                        </el-input>
                      </el-form-item>
                    </el-col>
                    <el-col :span="6" >
                      <el-form-item label="血压左侧">
                        <el-input v-model="editForm.pressureleft" auto-complete="off">
                          <template slot="append">mmhg</template>
                        </el-input>
                      </el-form-item>
                    </el-col>
                    <el-col :span="6" >
                      <el-form-item label="血压右侧">
                        <el-input v-model="editForm.pressureright" auto-complete="off">
                          <template slot="append">mmhg</template>
                        </el-input>
                      </el-form-item>
                    </el-col>
                  </el-row>

                  <el-row>
                    <el-col :span="24" >
                      <el-form-item label="症状" >
                        <el-checkbox-group v-model="editForm.symptoms" style="width:100%">
                          <!--                      <el-checkbox v-for="item in symptoms" :key="item.value" :name="type" :label="item.label" :value="item.value">
                                                </el-checkbox>-->
                          <table>
                            <tr>
                              <td style="padding-right: 45px"><el-checkbox label="无症状" name="type"></el-checkbox></td>
                              <td style="padding-right: 45px"><el-checkbox label="头痛" name="type"></el-checkbox></td>
                              <td style="padding-right: 45px"><el-checkbox label="头晕" name="type"></el-checkbox></td>
                              <td style="padding-right: 45px"><el-checkbox label="心悸" name="type"></el-checkbox></td>
                              <td style="padding-right: 45px"><el-checkbox label="胸闷" name="type"></el-checkbox></td>
                              <td style="padding-right: 45px"><el-checkbox label="胸痛" name="type"></el-checkbox></td>
                              <td style="padding-right: 45px"><el-checkbox label="慢性咳嗽" name="type"></el-checkbox></td>
                            </tr>
                            <tr>
                              <td style="padding-right: 45px"><el-checkbox label="咳痰" name="type"></el-checkbox></td>
                              <td style="padding-right: 45px"><el-checkbox label="呼吸困难" name="type"></el-checkbox></td>
                              <td style="padding-right: 45px"><el-checkbox label="多饮" name="type"></el-checkbox></td>
                              <td style="padding-right: 45px"><el-checkbox label="多尿" name="type"></el-checkbox></td>
                              <td style="padding-right: 45px"><el-checkbox label="体重下降" name="type"></el-checkbox></td>
                              <td style="padding-right: 45px"><el-checkbox label="乏力" name="type"></el-checkbox></td>
                              <td style="padding-right: 45px"><el-checkbox label="关节肿痛" name="type"></el-checkbox></td>
                            </tr>
                            <tr>
                              <td style="padding-right: 45px"><el-checkbox label="视力模糊" name="type"></el-checkbox></td>
                              <td style="padding-right: 45px"><el-checkbox label="手脚麻木" name="type"></el-checkbox></td>
                              <td style="padding-right: 45px"><el-checkbox label="尿急" name="type"></el-checkbox></td>
                              <td style="padding-right: 45px"><el-checkbox label="尿痛" name="type"></el-checkbox></td>
                              <td style="padding-right: 45px"><el-checkbox label="便秘" name="type"></el-checkbox></td>
                              <td style="padding-right: 45px"><el-checkbox label="腹泻" name="type"></el-checkbox></td>
                              <td style="padding-right: 45px"><el-checkbox label="恶心呕吐" name="type"></el-checkbox></td>
                            </tr>
                            <tr>
                              <td style="padding-right: 45px"><el-checkbox label="眼花" name="type"></el-checkbox></td>
                              <td style="padding-right: 45px"><el-checkbox label="耳鸣" name="type"></el-checkbox></td>
                              <td style="padding-right: 45px"><el-checkbox label="乳房胀痛" name="type"></el-checkbox></td>
                              <td style="padding-right: 45px"><el-checkbox label="其他" name="type"></el-checkbox></td>
                            </tr>
                          </table>
                        </el-checkbox-group>
                      </el-form-item>
                    </el-col>
                  </el-row>

                  <el-row>
                    <el-col :span="24" >
                      <el-form-item label="老年人健康状况自我评估" label-width="210px">
                        <el-radio-group v-model="editForm.selfAssessment">
                          <el-radio label="满意">满意</el-radio>
                          <el-radio label="基本满意">基本满意</el-radio>
                          <el-radio label="说不清楚">说不清楚</el-radio>
                          <el-radio label="不太满意">不太满意</el-radio>
                          <el-radio label="不满意">不满意</el-radio>
                        </el-radio-group>
                      </el-form-item>
                    </el-col>
                  </el-row>

                  <el-row>
                    <el-col :span="24">
                      <el-form-item label="老年人生活自理能力自我评估" label-width="210px">
                        <el-radio-group v-model="editForm.selfCare">
                          <table>
                            <tr>
                              <td style="padding-right: 10px"><el-radio label="可自理">可自理</el-radio></td>
                              <td style="padding-right: 27px"><el-radio label="轻度依赖">轻度依赖</el-radio></td>
                              <td style="padding-right: 27px"><el-radio label="中度依赖">中度依赖</el-radio></td>
                              <td style="padding-right: 25px"><el-radio label="不能自理">不能自理</el-radio></td>
                            </tr>
                          </table>
                        </el-radio-group>
                      </el-form-item>
                    </el-col>
                  </el-row>

                  <el-row>
                    <el-col :span="12">
                      <el-form-item label="老年人认知功能" label-width="210px">
                        <el-radio-group v-model="editForm.cognition">
                          <el-radio label="粗筛阴性">粗筛阴性</el-radio>
                          <el-radio label="粗筛阳性">粗筛阳性</el-radio>
                        </el-radio-group>
                      </el-form-item>
                    </el-col>
                    <el-col :span="12">
                      <el-form-item label="老年人情感状态" label-width="120px">
                        <el-radio-group v-model="editForm.mood">
                          <el-radio label="粗筛阴性">粗筛阴性</el-radio>
                          <el-radio label="粗筛阳性">粗筛阳性</el-radio>
                        </el-radio-group>
                      </el-form-item>
                    </el-col>
                  </el-row>

                  <el-row>
                    <el-col :span="24">
                      <el-form-item label="饮食习惯"  label-width="110px">
                        <el-checkbox-group v-model="editForm.diet" style="width:100%">
                          <el-checkbox v-for="item in diet" :key="item.value" :name="type" :label="item.label" :value="item.value">
                          </el-checkbox>
                        </el-checkbox-group>
                      </el-form-item>
                    </el-col>
                  </el-row>

                  <el-row>
                    <el-col :span="12">
                      <el-form-item label="锻炼频率" label-width="110px">
                        <el-radio-group v-model="editForm.exercise">
                          <el-radio label="每天">每天</el-radio>
                          <el-radio label="每周一次" style="margin-right:10px;">每周一次</el-radio>
                        </el-radio-group>
                        <el-radio-group v-model="editForm.exercise">
                          <el-radio label="偶尔">偶尔</el-radio>
                          <el-radio label="不锻炼">不锻炼</el-radio>
                        </el-radio-group>
                      </el-form-item>
                    </el-col>
                    <el-col :span="5">
                      <el-form-item label="锻炼方式" label-width="80px">
                        <el-input v-model="editForm.diet" ></el-input>
                      </el-form-item>
                    </el-col>
                    <el-col :span="7">
                      <el-form-item label="每次锻炼时间" label-width="110px">
                        <el-input v-model="editForm.exerciseTime" >
                          <template slot="append">分钟</template>
                        </el-input>
                      </el-form-item>
                    </el-col>
                  </el-row>

                  <el-row>
                    <el-col :span="6">
                      <el-form-item label="坚持锻炼时间"label-width="110px">
                        <el-input v-model="editForm.stickTime" >
                          <template slot="append">年</template>
                        </el-input>
                      </el-form-item>
                    </el-col>
                    <el-col :span="10">
                      <el-form-item label="吸烟状况">
                        <el-radio-group v-model="editForm.smoking">
                          <el-radio label="从不吸烟">从不吸烟</el-radio>
                          <el-radio label="已戒烟">已戒烟</el-radio>
                        </el-radio-group>
                        <el-radio-group v-model="editForm.smoking">
                          <el-radio label="吸烟">吸烟</el-radio>
                        </el-radio-group>
                      </el-form-item>
                    </el-col>
                    <el-col :span="8">
                      <el-form-item label="日吸烟量">
                        <el-input v-model="editForm.everySmoking">
                          <template slot="append">支</template>
                        </el-input>
                      </el-form-item>
                    </el-col>
                  </el-row>

                  <el-row>
                    <el-col :span="7">
                      <el-form-item label="开始吸烟年龄"label-width="110px">
                        <el-input v-model="editForm.smokingAge" ></el-input>
                      </el-form-item>
                    </el-col>
                    <el-col :span="6">
                      <el-form-item label="戒烟年龄">
                        <el-input v-model="editForm.noSmokingAge" ></el-input>
                      </el-form-item>
                    </el-col>
                    <el-col :span="11">
                      <el-form-item label="饮酒频率">
                        <el-radio-group v-model="editForm.drinking">
                          <el-radio label="从不">从不</el-radio>
                          <el-radio label="偶尔">偶尔</el-radio>
                        </el-radio-group>
                        <el-radio-group v-model="editForm.drinking">
                          <el-radio label="经常">经常</el-radio>
                          <el-radio label="每天">每天</el-radio>
                        </el-radio-group>
                      </el-form-item>
                    </el-col>
                  </el-row>

                  <el-row>
                    <el-col :span="7">
                      <el-form-item label="日饮酒量" label-width="110px">
                        <el-input v-model="editForm.everydrinking" >
                          <template slot="append">瓶</template>
                        </el-input>
                      </el-form-item>
                    </el-col>
                    <el-col :span="17">
                      <el-form-item label="饮酒种类">
                        <el-radio-group v-model="editForm.drinkingTypes">
                          <el-radio label="白酒">白酒</el-radio>
                          <el-radio label="啤酒">啤酒</el-radio>
                          <el-radio label="红酒">红酒</el-radio>
                          <el-radio label="黄酒">黄酒</el-radio>
                          <el-radio label="其他">其他</el-radio>
                        </el-radio-group>
                      </el-form-item>
                    </el-col>
                  </el-row>

                  <el-row>
                    <el-col :span="8">
                      <el-form-item label="是否戒酒">
                        <el-radio-group v-model="editForm.nodrinking">
                          <el-radio label="未戒酒">未戒酒</el-radio>
                          <el-radio label="已戒酒">已戒酒</el-radio>
                        </el-radio-group>
                      </el-form-item>
                    </el-col>
                    <el-col :span="8">
                      <el-form-item label="近一年内是否曾醉酒" label-width="150px">
                        <el-radio-group v-model="editForm.drunk">
                          <el-radio label="是">是</el-radio>
                          <el-radio label="否">否</el-radio>
                        </el-radio-group>
                      </el-form-item>
                    </el-col>
                    <el-col :span="8">
                      <el-form-item label="开始饮酒年龄" label-width="110px">
                        <el-input v-model="editForm.drinkingAge" ></el-input>
                      </el-form-item>
                    </el-col>
                  </el-row>

                  <el-row>
                    <el-col :span="8">
                      <el-form-item label="工种">
                        <el-input v-model="editForm.workType" ></el-input>
                      </el-form-item>
                    </el-col>
                    <el-col :span="8">
                      <el-form-item label="从业时间">
                        <el-input v-model="editForm.workingTime" ></el-input>
                      </el-form-item>
                    </el-col>
                    <el-col :span="8">
                      <el-form-item label="职业病危害因素接触史"label-width="180px">
                        <el-radio-group v-model="editForm.occExposure">
                          <el-radio label="无">无</el-radio>
                          <el-radio label="有">有</el-radio>
                        </el-radio-group>
                      </el-form-item>
                    </el-col>
                  </el-row>

                  <el-row>
                    <el-col :span="6">
                      <el-form-item label="粉尘">
                        <el-input v-model="editForm.dust" ></el-input>
                      </el-form-item>
                    </el-col>
                    <el-col :span="6">
                      <el-form-item label="防护措施">
                        <el-radio-group v-model="editForm.dustproMeasures">
                          <el-radio label="无">无</el-radio>
                          <el-radio label="有">有</el-radio>
                        </el-radio-group>
                      </el-form-item>
                    </el-col>
                    <el-col :span="6">
                      <el-form-item label="放射物质">
                        <el-input v-model="editForm.fanshe" ></el-input>
                      </el-form-item>
                    </el-col>
                    <el-col :span="6">
                      <el-form-item label="防护措施">
                        <el-radio-group v-model="editForm.fansheproMeasures">
                          <el-radio label="无">无</el-radio>
                          <el-radio label="有">有</el-radio>
                        </el-radio-group>
                      </el-form-item>
                    </el-col>
                  </el-row>

                  <el-row>
                    <el-col :span="6">
                      <el-form-item label="物理因素">
                        <el-input v-model="editForm.Physicalfactors" ></el-input>
                      </el-form-item>
                    </el-col>
                    <el-col :span="6">
                      <el-form-item label="防护措施">
                        <el-radio-group v-model="editForm.PhyproMeasures">
                          <el-radio label="无">无</el-radio>
                          <el-radio label="有">有</el-radio>
                        </el-radio-group>
                      </el-form-item>
                    </el-col>
                    <el-col :span="6">
                      <el-form-item label="化学物质">
                        <el-input v-model="editForm.chemicals"></el-input>
                      </el-form-item>
                    </el-col>
                    <el-col :span="6">
                      <el-form-item label="防护措施">
                        <el-radio-group v-model="editForm.cheproMeasures">
                          <el-radio label="无">无</el-radio>
                          <el-radio label="有">有</el-radio>
                        </el-radio-group>
                      </el-form-item>
                    </el-col>
                  </el-row>

                  <el-row>
                    <el-col :span="6">
                      <el-form-item label="其他">
                        <el-input v-model="editForm.other1" ></el-input>
                      </el-form-item>
                    </el-col>
                    <el-col :span="6">
                      <el-form-item label="防护措施">
                        <el-radio-group v-model="editForm.other1proMeasures">
                          <el-radio label="无">无</el-radio>
                          <el-radio label="有">有</el-radio>
                        </el-radio-group>
                      </el-form-item>
                    </el-col>
                  </el-row>

                  <el-row>
                    <el-col :span="6">
                      <el-form-item label="左视力">
                        <el-input v-model="editForm.visionz" ></el-input>
                      </el-form-item>
                    </el-col>
                    <el-col :span="6">
                      <el-form-item label="右视力">
                        <el-input v-model="editForm.visiony" ></el-input>
                      </el-form-item>
                    </el-col>
                    <el-col :span="6">
                      <el-form-item label="矫正左视力" label-width="100px">
                        <el-input v-model="editForm.viCorrectz" ></el-input>
                      </el-form-item>
                    </el-col>
                    <el-col :span="6">
                      <el-form-item label="矫正右视力"label-width="100px">
                        <el-input v-model="editForm.viCorrecty" ></el-input>
                      </el-form-item>
                    </el-col>
                  </el-row>

                  <el-row>
                    <el-col :span="6">
                      <el-form-item label="心率">
                        <el-input v-model="editForm.Heartrate" auto-complete="off"></el-input>
                      </el-form-item>
                    </el-col>
                    <el-col :span="6">
                      <el-form-item label="桶状胸">
                        <el-radio-group v-model="editForm.Barrelchest">
                          <el-radio label="是">是</el-radio>
                          <el-radio label="否">否</el-radio>
                        </el-radio-group>
                      </el-form-item>
                    </el-col>
                    <el-col :span="6">
                      <el-form-item label="呼吸音">
                        <el-radio-group v-model="editForm.Breathsounds">
                          <el-radio label="正常">正常</el-radio>
                          <el-radio label="异常">异常</el-radio>
                        </el-radio-group>
                      </el-form-item>
                    </el-col>
                    <el-col :span="6">
                      <el-form-item label="眼底">
                        <el-radio-group v-model="editForm.fundus">
                          <el-radio label="正常">正常</el-radio>
                          <el-radio label="异常">异常</el-radio>
                        </el-radio-group>
                      </el-form-item>
                    </el-col>
                  </el-row>

                  <el-row>
                    <el-col :span="6">
                      <el-form-item label="杂音" label-width="80px">
                        <el-radio-group v-model="editForm.noise">
                          <el-radio label="无">无</el-radio>
                          <el-radio label="有">有</el-radio>
                        </el-radio-group>
                      </el-form-item>
                    </el-col>
                    <el-col :span="8">
                      <el-form-item label="听力">
                        <el-radio-group v-model="editForm.hearing">
                          <el-radio label="听见">听见</el-radio>
                        </el-radio-group>
                        <el-radio-group v-model="editForm.hearing">
                          <el-radio label="听不清或无法听见">听不清或无法听见</el-radio>
                        </el-radio-group>
                      </el-form-item>
                    </el-col>
                    <el-col :span="9">
                      <el-form-item label="心律">
                        <el-radio-group v-model="editForm.Rhythmheart">
                          <el-radio label="齐">齐</el-radio>
                          <el-radio label="不齐">不齐</el-radio>
                          <el-radio label="绝对不齐">绝对不齐</el-radio>
                        </el-radio-group>
                      </el-form-item>
                    </el-col>
                  </el-row>

                  <el-row>
                    <el-col :span="6">
                      <el-form-item label="压痛" label-width="90px">
                        <el-radio-group v-model="editForm.tenderness">
                          <el-radio label="无">无</el-radio>
                          <el-radio label="有">有</el-radio>
                        </el-radio-group>
                      </el-form-item>
                    </el-col>
                    <el-col :span="6">
                      <el-form-item label="包块">
                        <el-radio-group v-model="editForm.Bagpiece">
                          <el-radio label="无">无</el-radio>
                          <el-radio label="有">有</el-radio>
                        </el-radio-group>
                      </el-form-item>
                    </el-col>
                    <el-col :span="6">
                      <el-form-item label="肝大">
                        <el-radio-group v-model="editForm.hepatomegaly">
                          <el-radio label="无">无</el-radio>
                          <el-radio label="有">有</el-radio>
                        </el-radio-group>
                      </el-form-item>
                    </el-col>
                    <el-col :span="6">
                      <el-form-item label="脾大">
                        <el-radio-group v-model="editForm.splenomegaly">
                          <el-radio label="无">无</el-radio>
                          <el-radio label="有">有</el-radio>
                        </el-radio-group>
                      </el-form-item>
                    </el-col>
                  </el-row>

                  <el-row>
                    <el-col :span="6">
                      <el-form-item label="移动性浊音" label-width="110px" style="margin-left:-20px;">
                        <el-radio-group v-model="editForm.Mobilevoiced">
                          <el-radio label="无">无</el-radio>
                          <el-radio label="有">有</el-radio>
                        </el-radio-group>
                      </el-form-item>
                    </el-col>
                    <el-col :span="18">
                      <el-form-item label="皮肤">
                        <el-radio-group v-model="editForm.skins">
                          <el-radio label="正常">正常</el-radio>
                          <el-radio label="潮红">潮红</el-radio>
                          <el-radio label="苍白">苍白</el-radio>
                          <el-radio label="发绀">发绀</el-radio>
                          <el-radio label="黄染">黄染</el-radio>
                          <el-radio label="色素沉着">色素沉着</el-radio>
                          <el-radio label="其他">其他</el-radio>
                        </el-radio-group>
                      </el-form-item>
                    </el-col>
                  </el-row>



                  <el-row>
                    <el-col :span="12">
                      <el-form-item label="运动功能">
                        <el-radio-group v-model="editForm.Motorfunction">
                          <el-radio label="可顺利完成">可顺利完成</el-radio>
                          <el-radio label="无法独立完成其中任何一动作">无法独立完成其中任何一动作</el-radio>
                        </el-radio-group>
                      </el-form-item>
                    </el-col>
                    <el-col :span="12">
                      <el-form-item label="巩膜">
                        <el-radio-group v-model="editForm.sclera">
                          <el-radio label="正常">正常</el-radio>
                          <el-radio label="黄染">黄染</el-radio>
                          <el-radio label="充血">充血</el-radio>
                          <el-radio label="其他">其他</el-radio>
                        </el-radio-group>
                      </el-form-item>
                    </el-col>
                  </el-row>

                  <el-row>
                    <el-col :span="12">
                      <el-form-item label="淋巴结">
                        <el-radio-group v-model="editForm.Lymphnode">
                          <el-radio label="未触及">未触及</el-radio>
                          <el-radio label="锁骨上">锁骨上</el-radio>
                          <el-radio label="腋窝">腋窝</el-radio>
                          <el-radio label="其他">其他</el-radio>
                        </el-radio-group>
                      </el-form-item>
                    </el-col>
                    <el-col :span="12">
                      <el-form-item label="罗音">
                        <el-radio-group v-model="editForm.rale">
                          <el-radio label="无">无</el-radio>
                          <el-radio label="千罗音">千罗音</el-radio>
                          <el-radio label="湿罗音">湿罗音</el-radio>
                          <el-radio label="其他">其他</el-radio>
                        </el-radio-group>
                      </el-form-item>
                    </el-col>
                  </el-row>

                  <el-row>
                    <el-col :span="12">
                      <el-form-item label="肛门指诊">
                        <el-radio-group v-model="editForm.anusdre">
                          <el-radio label="未见异常">未见异常</el-radio>
                          <el-radio label="触痛">触痛</el-radio>
                          <el-radio label="包块">包块</el-radio>
                          <el-radio label="前列腺异常">前列腺异常</el-radio>
                        </el-radio-group>
                      </el-form-item>
                    </el-col>
                    <el-col :span="12">
                      <el-form-item label="下肢水肿">
                        <el-radio-group v-model="editForm.extremityEdema">
                          <el-radio label="无">无</el-radio>
                          <el-radio label="单侧">单侧</el-radio>
                          <el-radio label="双侧不对称">双侧不对称</el-radio>
                          <el-radio label="双侧对称">双侧对称</el-radio>
                        </el-radio-group>
                      </el-form-item>
                    </el-col>
                  </el-row>

                  <el-row>
                    <el-col :span="24">
                      <el-form-item label="足背动脉搏动" label-width="110px">
                        <el-radio-group v-model="editForm.footPulsates">
                          <el-radio label="未触及">未触及</el-radio>
                          <el-radio label="触及双侧对称">触及双侧对称</el-radio>
                          <el-radio label="触及左侧弱或消失">触及左侧弱或消失</el-radio>
                          <el-radio label="触及右侧弱或消失">触及右侧弱或消失</el-radio>
                        </el-radio-group>
                      </el-form-item>
                    </el-col>
                  </el-row>

                  <el-row>
                    <el-col :span="16">
                      <el-form-item label="乳腺">
                        <el-radio-group v-model="editForm.Mammarygland">
                          <el-radio label="未见异常">未见异常</el-radio>
                          <el-radio label="乳房切除">乳房切除</el-radio>
                          <el-radio label="异常分泌">异常分泌</el-radio>
                          <el-radio label="乳腺包块">乳腺包块</el-radio>
                          <el-radio label="其他">其他</el-radio>
                        </el-radio-group>
                      </el-form-item>
                    </el-col>
                    <el-col :span="6">
                      <el-form-item label="外阴">
                        <el-radio-group v-model="editForm.vulva">
                          <el-radio label="正常">正常</el-radio>
                          <el-radio label="异常">异常</el-radio>
                        </el-radio-group>
                      </el-form-item>
                    </el-col>
                  </el-row>

                  <el-row>
                    <el-col :span="6">
                      <el-form-item label="阴道">
                        <el-radio-group v-model="editForm.vagina">
                          <el-radio label="正常">正常</el-radio>
                          <el-radio label="异常">异常</el-radio>
                        </el-radio-group>
                      </el-form-item>
                    </el-col>
                    <el-col :span="6">
                      <el-form-item label="宫颈">
                        <el-radio-group v-model="editForm.cervical">
                          <el-radio label="正常">正常</el-radio>
                          <el-radio label="异常">异常</el-radio>
                        </el-radio-group>
                      </el-form-item>
                    </el-col>
                    <el-col :span="6">
                      <el-form-item label="宫体">
                        <el-radio-group v-model="editForm.from">
                          <el-radio label="正常">正常</el-radio>
                          <el-radio label="异常">异常</el-radio>
                        </el-radio-group>
                      </el-form-item>
                    </el-col>
                    <el-col :span="6">
                      <el-form-item label="附件">
                        <el-radio-group v-model="editForm.attachment">
                          <el-radio label="正常">正常</el-radio>
                          <el-radio label="异常">异常</el-radio>
                        </el-radio-group>
                      </el-form-item>
                    </el-col>
                  </el-row>

                  <el-row>
                    <el-col :span="6">
                      <el-form-item label="其他">
                        <el-input v-model="editForm.other2" auto-complete="off"></el-input>
                      </el-form-item>
                    </el-col>
                    <el-col :span="6">
                      <el-form-item label="血红蛋白" label-width="110px">
                        <el-input v-model="editForm.hemoglobin" auto-complete="off"></el-input>
                      </el-form-item>
                    </el-col>
                    <el-col :span="6">
                      <el-form-item label="白细胞">
                        <el-input v-model="editForm.Whitebloodcells" auto-complete="off"></el-input>
                      </el-form-item>
                    </el-col>
                    <el-col :span="6">
                      <el-form-item label="血小板">
                        <el-input v-model="editForm.platelet" auto-complete="off"></el-input>
                      </el-form-item>
                    </el-col>
                  </el-row>

                  <el-row>
                    <el-col :span="6">
                      <el-form-item label="其他">
                        <el-input v-model="editForm.other3" auto-complete="off"></el-input>
                      </el-form-item>
                    </el-col>
                    <el-col :span="6">
                      <el-form-item label="尿蛋白" label-width="110px">
                        <el-input v-model="editForm.Urineprotein" auto-complete="off"></el-input>
                      </el-form-item>
                    </el-col>
                    <el-col :span="6">
                      <el-form-item label="尿糖">
                        <el-input v-model="editForm.Urinesugar" auto-complete="off"></el-input>
                      </el-form-item>
                    </el-col>
                    <el-col :span="6">
                      <el-form-item label="尿酮体">
                        <el-input v-model="editForm.Urineketone" auto-complete="off"></el-input>
                      </el-form-item>
                    </el-col>
                  </el-row>

                  <el-row>
                    <el-col :span="6">
                      <el-form-item label="尿潜血">
                        <el-input v-model="editForm.ery" auto-complete="off"></el-input>
                      </el-form-item>
                    </el-col>
                    <el-col :span="6">
                      <el-form-item label="尿微量白蛋白" label-width="110px">
                        <el-input v-model="editForm.Urinaryalbumin" auto-complete="off"></el-input>
                      </el-form-item>
                    </el-col>
                    <el-col :span="6">
                      <el-form-item label="空腹血糖">
                        <el-input v-model="editForm.Faglucose" auto-complete="off"></el-input>
                      </el-form-item>
                    </el-col>
                    <el-col :span="6">
                      <el-form-item label="心电图">
                        <el-radio-group v-model="editForm.ecg">
                          <el-radio label="正常">正常</el-radio>
                          <el-radio label="异常">异常</el-radio>
                        </el-radio-group>
                      </el-form-item>
                    </el-col>
                  </el-row>

                  <el-row>
                    <el-col :span="8">
                      <el-form-item label="大便潜血">
                        <el-radio-group v-model="editForm.Defecateblood">
                          <el-radio label="阴性">阴性</el-radio>
                          <el-radio label="阳性">阳性</el-radio>
                        </el-radio-group>
                      </el-form-item>
                    </el-col>
                    <el-col :span="8">
                      <el-form-item label="糖化血红蛋白" label-width="120px">
                        <el-input v-model="editForm.Hemoglobin" auto-complete="off"></el-input>
                      </el-form-item>
                    </el-col>
                    <el-col :span="8">
                      <el-form-item label="乙型肝炎表面抗原"label-width="140px">
                        <el-radio-group v-model="editForm.Hepatitisantigen">
                          <el-radio label="阴性">阴性</el-radio>
                          <el-radio label="阳性">阳性</el-radio>
                        </el-radio-group>
                      </el-form-item>
                    </el-col>
                  </el-row>

                  <el-row>
                    <el-col :span="8">
                      <el-form-item label="血清谷丙转氨酶"label-width="120px">
                        <el-input v-model="editForm.Serumtransa" auto-complete="off"></el-input>
                      </el-form-item>
                    </el-col>
                    <el-col :span="8">
                      <el-form-item label="血清谷草转氨酶" label-width="120px">
                        <el-input v-model="editForm.glutamictransame" auto-complete="off"></el-input>
                      </el-form-item>
                    </el-col>
                    <el-col :span="8">
                      <el-form-item label="白蛋白">
                        <el-input v-model="editForm.albumin" auto-complete="off"></el-input>
                      </el-form-item>
                    </el-col>
                  </el-row>
                  <el-row>
                    <el-col :span="8">
                      <el-form-item label="总胆红素">
                        <el-input v-model="editForm.Totalbilirubin" auto-complete="off"></el-input>
                      </el-form-item>
                    </el-col>
                    <el-col :span="8">
                      <el-form-item label="结合胆红素" label-width="120px">
                        <el-input v-model="editForm.Boundbilirubin" auto-complete="off"></el-input>
                      </el-form-item>
                    </el-col>
                    <el-col :span="8">
                      <el-form-item label="血尿素">
                        <el-input v-model="editForm.Bloodurea" auto-complete="off"></el-input>
                      </el-form-item>
                    </el-col>
                  </el-row>

                  <el-row>
                    <el-col :span="8">
                      <el-form-item label="血钾浓度">
                        <el-input v-model="editForm.Potconcen" auto-complete="off"></el-input>
                      </el-form-item>
                    </el-col>
                    <el-col :span="8">
                      <el-form-item label="血钠浓度" label-width="120px">
                        <el-input v-model="editForm.sodiumconcen" auto-complete="off"></el-input>
                      </el-form-item>
                    </el-col>
                    <el-col :span="8">
                      <el-form-item label="总胆固醇">
                        <el-input v-model="editForm.Totalcholestero" auto-complete="off"></el-input>
                      </el-form-item>
                    </el-col>
                  </el-row>

                  <el-row>
                    <el-col :span="8">
                      <el-form-item label="血清低密度脂蛋白胆固醇"label-width="180px">
                        <el-input v-model="editForm.LDLcholesterol" auto-complete="off"></el-input>
                      </el-form-item>
                    </el-col>
                    <el-col :span="8">
                      <el-form-item label="血清高密度脂蛋白胆固醇"label-width="180px">
                        <el-input v-model="editForm.HDLcholesterol" auto-complete="off"></el-input>
                      </el-form-item>
                    </el-col>
                    <el-col :span="8">
                      <el-form-item label="胸部X片">
                        <el-radio-group v-model="editForm.X-ray">
                          <el-radio label="正常">正常</el-radio>
                          <el-radio label="异常">异常</el-radio>
                        </el-radio-group>
                      </el-form-item>
                    </el-col>
                  </el-row>

                  <el-row>
                    <el-col :span="8">
                      <el-form-item label="腹部B超" label-width="110px">
                        <el-radio-group v-model="editForm.Abdomultrasound">
                          <el-radio label="正常">正常</el-radio>
                          <el-radio label="异常">异常</el-radio>
                        </el-radio-group>
                      </el-form-item>
                    </el-col>
                    <el-col :span="8">
                      <el-form-item label="宫颈切片">
                        <el-radio-group v-model="editForm.Cervicalbiopsy">
                          <el-radio label="正常">正常</el-radio>
                          <el-radio label="异常">异常</el-radio>
                        </el-radio-group>
                      </el-form-item>
                    </el-col>
                    <el-col :span="8">
                      <el-form-item label="其他">
                        <el-input v-model="editForm.other6" auto-complete="off"></el-input>
                      </el-form-item>
                    </el-col>
                  </el-row>

                  <el-form-item label="脑血管疾病" label-width="110px">
                    <el-checkbox-group v-model="editForm.cerebrovascular" style="width:100%">
                      <el-checkbox v-for="item in cerebrovascular" :key="item.value" :name="type" :label="item.label" :value="item.value">
                      </el-checkbox>
                    </el-checkbox-group>
                  </el-form-item>

                  <el-form-item label="肾脏疾病" label-width="110px">
                    <el-checkbox-group v-model="editForm.kidneyDisease" style="width:100%">
                      <el-checkbox v-for="item in kidneyDisease" :key="item.value" :name="type" :label="item.label" :value="item.value">
                      </el-checkbox>
                    </el-checkbox-group>
                  </el-form-item>

                  <el-form-item label="心脏疾病" label-width="110px">
                    <el-checkbox-group v-model="editForm.heartDisease" style="width:100%">
                      <el-checkbox v-for="item in heartDisease" :key="item.value" :name="type" :label="item.label" :value="item.value">
                      </el-checkbox>
                    </el-checkbox-group>
                  </el-form-item>

                  <el-form-item label="血管疾病" label-width="110px">
                    <el-checkbox-group v-model="editForm.vascularDisease" style="width:100%">
                      <el-checkbox v-for="item in vascularDisease" :key="item.value" :name="type" :label="item.label" :value="item.value">
                      </el-checkbox>
                    </el-checkbox-group>
                  </el-form-item>

                  <el-form-item label="眼部疾病" label-width="110px">
                    <el-checkbox-group v-model="editForm.eyeDisease" style="width:100%">
                      <el-checkbox v-for="item in eyeDisease" :key="item.value" :name="type" :label="item.label" :value="item.value">
                      </el-checkbox>
                    </el-checkbox-group>
                  </el-form-item>

                  <el-row>
                    <el-col :span="11">
                      <el-form-item label="神经系统疾病" label-width="110px">
                        <el-radio-group v-model="editForm.Nervoussystem">
                          <el-radio label="未发现">未发现</el-radio>
                          <el-radio label="有">有</el-radio>
                        </el-radio-group>
                      </el-form-item>
                    </el-col>
                    <el-col :span="13">
                      <el-form-item label="其他系统疾病" label-width="120px">
                        <el-input v-model="editForm.otherdisease" auto-complete="off"></el-input>
                      </el-form-item>
                    </el-col>
                  </el-row>

                  <el-row>
                    <el-col :span="7">
                      <el-form-item label="入/出院日期"label-width="110px">
                        <el-date-picker type="date" placeholder="选择入院日期" v-model="editForm.inHDate"style="width: 100%"></el-date-picker>
                      </el-form-item>
                    </el-col>
                    <el-col :span="5">
                      <el-date-picker type="date" placeholder="选择出院日期" v-model="editForm.outHDate"style="width: 95%"></el-date-picker>
                    </el-col>
                    <el-col span="12">
                      <el-form-item label="原因">
                        <el-input v-model="editForm.why" ></el-input>
                      </el-form-item>
                    </el-col>
                  </el-row>

                  <el-row>
                    <el-col span="12">
                      <el-form-item label="医疗机构名称" label-width="110px">
                        <el-input v-model="editForm.institutName"></el-input>
                      </el-form-item>
                    </el-col>
                    <el-col span="12">
                      <el-form-item label="病案号">
                        <el-input v-model="editForm.Medicalrecordno"></el-input>
                      </el-form-item>
                    </el-col>
                  </el-row>

                  <el-row>
                    <el-col :span="7">
                      <el-form-item label="建/撤床日期" label-width="110px">
                        <el-date-picker type="date" placeholder="选择建床日期" v-model="editForm.inBDate" style="width: 100%"></el-date-picker>
                      </el-form-item>
                    </el-col>
                    <el-col :span="5">
                      <el-date-picker type="date" placeholder="选择撤床日期" v-model="editForm.outBDate" style="width: 95%"></el-date-picker>
                    </el-col>
                    <el-col span="12">
                      <el-form-item label="原因">
                        <el-input v-model="editForm.why1" ></el-input>
                      </el-form-item>
                    </el-col>
                  </el-row>

                  <el-row>
                    <el-col span="12">
                      <el-form-item label="医疗机构名称" label-width="110px">
                        <el-input v-model="editForm.institutName1" ></el-input>
                      </el-form-item>
                    </el-col>
                    <el-col span="12">
                      <el-form-item label="病案号">
                        <el-input v-model="editForm.Medicalrecordno1" ></el-input>
                      </el-form-item>
                    </el-col>
                  </el-row>

                  <el-row>
                    <el-col span="12">
                      <el-form-item label="接种名称" label-width="110px">
                        <el-input v-model="editForm.vaccinationName" ></el-input>
                      </el-form-item>
                    </el-col>
                    <el-col :span="12">
                      <el-form-item label="接种日期">
                        <el-date-picker type="date" placeholder="选择接种日期" v-model="editForm.vaccinationDate"style="width: 380px"></el-date-picker>
                      </el-form-item>
                    </el-col>
                  </el-row>

                  <el-row>
                    <el-col :span="12">
                      <el-form-item label="接种机构" label-width="110px">
                        <el-input v-model="editForm.vaccinationAgency" ></el-input>
                      </el-form-item>
                    </el-col>

                  </el-row>

                  <el-row>
                    <el-col :span="12">
                      <el-form-item label="药物名称" label-width="110px">
                        <el-input v-model="editForm.Drugname" ></el-input>
                      </el-form-item>
                    </el-col>
                    <el-col :span="12">
                      <el-form-item label="用法">
                        <el-input v-model="editForm.usage" ></el-input>
                      </el-form-item>
                    </el-col>
                  </el-row>

                  <el-row>
                    <el-col :span="12">
                      <el-form-item label="用量" label-width="110px">
                        <el-input v-model="editForm.dosage" ></el-input>
                      </el-form-item>
                    </el-col>
                    <el-col :span="12">
                      <el-form-item label="用药时间">
                        <el-input v-model="editForm.Duration" ></el-input>
                      </el-form-item>
                    </el-col>
                  </el-row>

                  <el-form-item label="服药依从性" label-width="110px">
                    <el-radio-group v-model="editForm.Medicompliance">
                      <el-radio label="规律">规律</el-radio>
                      <el-radio label="间断">间断</el-radio>
                      <el-radio label="不服药">不服药</el-radio>
                    </el-radio-group>
                  </el-form-item>



                  <el-row>
                    <el-col :span="12">
                      <el-form-item label="健康评价" label-width="110px">
                        <el-radio-group v-model="editForm.healthAssessment">
                          <el-radio label="体检无异常">体检无异常</el-radio>
                          <el-radio label="有异常">有异常</el-radio>
                        </el-radio-group>
                      </el-form-item>
                    </el-col>
                    <el-col :span="12">
                      <el-form-item label="异常情况">
                        <el-input  auto-complete="off"></el-input>
                      </el-form-item>
                    </el-col>
                  </el-row>

                  <el-form-item label="健康指导" label-width="110px">
                    <el-checkbox-group v-model="editForm.healthGuidance" style="width:100%">
                      <el-checkbox v-for="item in healthGuidance" :key="item.value" :name="type" :label="item.label" :value="item.value">
                      </el-checkbox>
                    </el-checkbox-group>
                  </el-form-item>

                  <el-form-item label="危险因素控制" label-width="110px">
                    <el-checkbox-group v-model="editForm.Riskcontrol" style="width:100%">
                      <el-checkbox v-for="item in Riskcontrol" :key="item.value" :name="type" :label="item.label" :value="item.value">
                      </el-checkbox>
                    </el-checkbox-group>
                  </el-form-item>
                </el-tabs>
            </div>
          </EasyScrollbar>
        </el-form>
      </div>
      <div slot="footer" class="dialog-footer"center>
        <el-button @click="conprint()">打印</el-button>
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
  } from '@/api/PersonMedical'

  export default {
    name: 'Person_medical',
    data() {
      return {
        edit: true,
        tableHeight: window.innerHeight - 220,
        tablebodyHeight: window.innerHeight - 320,
        // 危险控制
        Riskcontrol: [
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
          { value: '心前区疼痛', label: '心前区疼痛' },
          { value: '其他', label: '其他' }
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
          sex: '',
          name: ''
        },
        users: [],
        total: 0,
        page: 1,
        sels: [], // 列表选中列
        editFormRules: {
          doctor: [{ required: true, message: '请输入医生姓名', trigger: 'blur' }],
          name: [{ required: true, message: '请输入姓名', trigger: 'blur' }],
          idNum: [{ required: true, message: '请输入身份证号', trigger: 'blur' }]
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
          Riskcontrol: [],
          diet: []
        },

        addFormVisible: false, // 新增界面是否显示
        addFormRules: {
          doctor: [{ required: true, message: '请输入医生姓名', trigger: 'blur' }],
          name: [{ required: true, message: '请输入姓名', trigger: 'blur' }],
          idNum: [{ required: true, message: '请输入身份证号', trigger: 'blur' }]

        }
      }
    },
    methods: {
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
          '    <h2 style="width:794px;text-align:center"> 健康体检档案</h2> \n' +
          '    <span style="margin-left:34px;">姓名:</span>\n' +
          '    <span style="margin-left:34px;">{{name}}</span>\n' +
          '\t<span style="margin-left:510px;">编号:</span>\n' +
          '    <span style="margin-left:5px;">{{id}}</span> \n' +
          '    </div>\n' +
          '<table id="table"  border="1"  cellspacing="0" bordercolor="#000000" style="width:730px;margin-left:30px;margin-top:5px;border-collapse:collapse;"  width = "734">\n' +
          '  <tr>\n' +
          '    <th width="83" scope="row">体检日期</th>\n' +
          '    <td colspan="3">&nbsp;</td>\n' +
          '    <th colspan="2" scope="row">责任医生</th>\n' +
          '    <td colspan="4">&nbsp;</td>\n' +
          '  </tr>\n' +
          '  <tr>\n' +
          '    <th scope="row">内容</th>\n' +
          '    <th colspan="9">检查项目</th>\n' +
          '  </tr>\n' +
          '  <tr>\n' +
          '    <th scope="row">症状</th>\n' +
          '    <td colspan="9">&nbsp;</td>\n' +
          '  </tr>\n' +
          '   <tr>\n' +
          '    <th rowspan="9" scope="row">一般情况</th>\n' +
          '    <td width="97">体温</td>\n' +
          '    <td colspan="2"><div align="right">℃ </div></td>\n' +
          '    <td colspan="2">脉率</td>\n' +
          '    <td colspan="4"><div align="right">次/分钟</div></td>\n' +
          '  </tr>\n' +
          '   <tr>\n' +
          '    <td rowspan="2">呼吸频率</td>\n' +
          '    <td colspan="2" rowspan="2"><div align="right">次/分钟</div></td>\n' +
          '    <td colspan="2" rowspan="2" scope="row">血压</td>\n' +
          '    <td colspan="2">左侧</td>\n' +
          '    <td colspan="2"><div align="right">mmHg</div></td>\n' +
          '  </tr>\n' +
          '   <tr>\n' +
          '     <td colspan="2">右侧</td>\n' +
          '     <td colspan="2"><div align="right">mmHg</div></td>\n' +
          '   </tr>\n' +
          '   <tr>\n' +
          '    <td>身高</td>\n' +
          '    <td colspan="2"><div align="right">cm</div></td>\n' +
          '    <td colspan="2" scope="row">体重</td>\n' +
          '    <td colspan="4"><div align="right">kg</div></td>\n' +
          '  </tr>\n' +
          '   <tr>\n' +
          '    <td>腰围</td>\n' +
          '    <td colspan="2"><div align="right">cm</div></td>\n' +
          '    <td colspan="2" scope="row">体质指数(BMI)</td>\n' +
          '    <td colspan="4"><div align="right">kg/c㎡</div></td>\n' +
          '  </tr>\n' +
          '   <tr>\n' +
          '    <th colspan="3">老年人健康状态  自我评估*</th>\n' +
          '    <td colspan="6" scope="row">&nbsp;</td>\n' +
          '  </tr>\n' +
          '   <tr>\n' +
          '    <th colspan="3">老年人生活自理  能力自我评估*</th>\n' +
          '    <td colspan="6" scope="row">&nbsp;</td>\n' +
          '  </tr>\n' +
          '   <tr>\n' +
          '    <th colspan="3">老年人  认知功能*</th>\n' +
          '    <td colspan="6" scope="row">&nbsp;</td>\n' +
          '  </tr>\n' +
          '   <tr>\n' +
          '    <th colspan="3">老年人  情感状态*</th>\n' +
          '    <td colspan="6" scope="row">&nbsp;</td>\n' +
          '  </tr>\n' +
          '   <tr>\n' +
          '    <th rowspan="13" scope="row">生活方式</th>\n' +
          '    <td rowspan="3">体育锻炼</td>\n' +
          '    <td colspan="2">锻炼频率</td>\n' +
          '    <td colspan="6">&nbsp;</td>\n' +
          '  </tr>\n' +
          '   <tr>\n' +
          '     <td colspan="2">每次锻炼时间</td>\n' +
          '     <td colspan="2"><div align="right">分钟</div></td>\n' +
          '     <td colspan="2">坚持锻炼时间</td>\n' +
          '     <td colspan="2"><div align="right">年</div></td>\n' +
          '   </tr>\n' +
          '   <tr>\n' +
          '     <td colspan="2">锻炼方式</td>\n' +
          '     <td colspan="6">&nbsp;</td>\n' +
          '   </tr>\n' +
          '   <tr>\n' +
          '    <td>饮食习惯</td>\n' +
          '    <td colspan="8">&nbsp;</td>\n' +
          '  </tr>\n' +
          '   <tr>\n' +
          '    <td rowspan="3">吸烟情况</td>\n' +
          '    <td colspan="2">吸烟状况</td>\n' +
          '    <td colspan="6">&nbsp;</td>\n' +
          '  </tr>\n' +
          '   <tr>\n' +
          '     <td colspan="2">日吸烟量</td>\n' +
          '     <td colspan="6">&nbsp;</td>\n' +
          '   </tr>\n' +
          '   <tr>\n' +
          '     <td colspan="2">开始吸烟年龄</td>\n' +
          '     <td colspan="2">&nbsp;</td>\n' +
          '     <td colspan="2">戒烟年龄</td>\n' +
          '     <td colspan="2">&nbsp;</td>\n' +
          '   </tr>\n' +
          '   <tr>\n' +
          '    <td rowspan="5">饮酒情况</td>\n' +
          '    <td colspan="2">饮酒频率</td>\n' +
          '    <td colspan="6">&nbsp;</td>\n' +
          '  </tr>\n' +
          '   <tr>\n' +
          '     <td colspan="2">日饮酒量</td>\n' +
          '     <td colspan="6">&nbsp;</td>\n' +
          '   </tr>\n' +
          '   <tr>\n' +
          '     <td colspan="2">是否戒酒</td>\n' +
          '     <td colspan="6">&nbsp;</td>\n' +
          '   </tr>\n' +
          '   <tr>\n' +
          '     <td colspan="2">开始饮酒年龄</td>\n' +
          '     <td colspan="2">&nbsp;</td>\n' +
          '     <td colspan="2">近一年内是否醉酒</td>\n' +
          '     <td colspan="2">&nbsp;</td>\n' +
          '   </tr>\n' +
          '   <tr>\n' +
          '     <td colspan="2">饮酒种类</td>\n' +
          '     <td colspan="6">&nbsp;</td>\n' +
          '   </tr>\n' +
          '   <tr>\n' +
          '    <td>职业病危害因素接触史</td>\n' +
          '    <td colspan="8">&nbsp;</td>\n' +
          '  </tr>\n' +
          '  <tr>\n' +
          '     <th rowspan="4" scope="row">脏器功能</th>\n' +
          '     <td>口腔</td>\n' +
          '     <td colspan="8">&nbsp;</td>\n' +
          '  </tr>\n' +
          '  <tr>\n' +
          '     <td>视力</td>\n' +
          '     <td width="43">左眼</td>\n' +
          '     <td width="84">&nbsp;</td>\n' +
          '     <td width="44" scope="row">右眼</td>\n' +
          '     <th width="74" scope="row">&nbsp;</th>\n' +
          '     <td width="55">矫正视力左眼</td>\n' +
          '     <td width="60">&nbsp;</td>\n' +
          '     <td width="57">矫正视力右眼</td>\n' +
          '     <td width="91">&nbsp;</td>\n' +
          '  </tr>\n' +
          '  <tr>\n' +
          '     <td>听力</td>\n' +
          '     <td colspan="8">&nbsp;</td>\n' +
          '  </tr>\n' +
          '  <tr>\n' +
          '     <td>运动功能</td>\n' +
          '     <td colspan="8">&nbsp;</td>\n' +
          '  </tr>\n' +
          '  <tr>\n' +
          '     <th rowspan="25" scope="row">查体</th>\n' +
          '     <td>眼底</td>\n' +
          '     <td colspan="8">&nbsp;</td>\n' +
          '  </tr>\n' +
          '  <tr>\n' +
          '     <td>皮肤</td>\n' +
          '     <td colspan="8">&nbsp;</td>\n' +
          '  </tr>\n' +
          '  <tr>\n' +
          '      <td>巩膜</td>\n' +
          '     <td colspan="8">&nbsp;</td>\n' +
          '  </tr>\n' +
          '  <tr>\n' +
          '      <td>淋巴结</td>\n' +
          '     <td colspan="8">&nbsp;</td>\n' +
          '  </tr>\n' +
          '  <tr>\n' +
          '      <td rowspan="3">肺</td>\n' +
          '    <td colspan="1">桶状胸</td>\n' +
          '    <td colspan="7">&nbsp;</td>\n' +
          '     \n' +
          '  </tr>\n' +
          '  <tr>\n' +
          '    <td colspan="1">呼吸音</td>\n' +
          '    <td colspan="7">&nbsp;</td>\n' +
          '  </tr>\n' +
          '  <tr>\n' +
          '     <td colspan="1">罗音</td>\n' +
          '    <td colspan="7">&nbsp;</td>\n' +
          '  </tr>\n' +
          '  <tr>\n' +
          '     <td rowspan="3">心脏</td>\n' +
          '     <td colspan="1">心率</td>\n' +
          '    <td colspan="7">&nbsp;</td>\n' +
          '  </tr>\n' +
          '  <tr>\n' +
          '     <td colspan="1">心律</td>\n' +
          '     <td colspan="7">&nbsp;</td>\n' +
          '  </tr>\n' +
          '  <tr>\n' +
          '    <td colspan="1">杂音</td>\n' +
          '    <td colspan="7">&nbsp;</td>\n' +
          '  </tr>\n' +
          '  <tr>\n' +
          '    <td rowspan="5">腹部</td>\n' +
          '    <td colspan="1">压痛</td>\n' +
          '    <td colspan="7">&nbsp;</td>\n' +
          '  </tr>\n' +
          '  <tr>\n' +
          '    <td colspan="1">包块</td>\n' +
          '    <td colspan="7">&nbsp;</td>\n' +
          '  </tr>\n' +
          '  <tr>\n' +
          '    <td colspan="1">肝大</td>\n' +
          '    <td colspan="7">&nbsp;</td>\n' +
          '  </tr>\n' +
          '  <tr>\n' +
          '    <td colspan="1">脾大</td>\n' +
          '    <td colspan="7">&nbsp;</td>\n' +
          '  </tr>\n' +
          '  <tr>\n' +
          '    <td colspan="1">移动性浊音</td>\n' +
          '    <td colspan="7">&nbsp;</td>\n' +
          '  </tr>\n' +
          '  <tr>\n' +
          '      <td>下肢水肿</td>\n' +
          '     <td colspan="8">&nbsp;</td>\n' +
          '  </tr>\n' +
          '  <tr>\n' +
          '     <td>足背动脉搏动</td>\n' +
          '     <td colspan="8">&nbsp;</td>\n' +
          '  </tr>\n' +
          '  <tr>\n' +
          '     <td>肛门指诊</td>\n' +
          '     <td colspan="8">&nbsp;</td>\n' +
          '  </tr>\n' +
          '  <tr>\n' +
          '     <td>乳腺</td>\n' +
          '     <td colspan="8">&nbsp;</td>\n' +
          '  </tr>\n' +
          '  <tr>\n' +
          '     <td rowspan="5">妇科</td>\n' +
          '     <td colspan="1">外阴</td>\n' +
          '     <td colspan="7">&nbsp;</td>\n' +
          '  </tr>\n' +
          '  <tr>\n' +
          '    <td colspan="1">阴道</td>\n' +
          '    <td colspan="7">&nbsp;</td>\n' +
          '  </tr>\n' +
          '  <tr>\n' +
          '    <td colspan="1">宫颈</td>\n' +
          '    <td colspan="7">&nbsp;</td>\n' +
          '  </tr>\n' +
          '  <tr>\n' +
          '    <td colspan="1">宫体</td>\n' +
          '    <td colspan="7">&nbsp;</td>\n' +
          '  </tr>\n' +
          '  <tr>\n' +
          '    <td colspan="1">附件</td>\n' +
          '    <td colspan="7">&nbsp;</td>\n' +
          '  </tr>\n' +
          '  <tr>\n' +
          '     <td>其他</td>\n' +
          '     <td colspan="8">&nbsp;</td>\n' +
          '  </tr>\n' +
          '  <tr>\n' +
          '     <th rowspan="21" scope="row">辅助检查</th>\n' +
          '     <td rowspan="2">血常规</td>\n' +
          '     <td colspan="2">血红蛋白</td>\n' +
          '     <td colspan="2"><div align="right">g/L</div></td>\n' +
          '     <td colspan="2">白细胞</td>\n' +
          '     <td colspan="2"><div align="right">×10^9/L</div></td>\n' +
          '  </tr>\n' +
          '  <tr>\n' +
          '     <td colspan="2">血小板</td>\n' +
          '     <td colspan="2"><div align="right">×10^9/L</div></td>\n' +
          '     <td colspan="2">其他</td>\n' +
          '    <td colspan="2"><div align="right"></div></td>\n' +
          '  </tr>\n' +
          '  <tr>\n' +
          '     <td rowspan="2">尿常规</td>\n' +
          '     <td colspan="2">尿蛋白</td>\n' +
          '     <td colspan="2">&nbsp;</td>\n' +
          '     <td colspan="2">尿糖</td>\n' +
          '    <td colspan="2">&nbsp;</td>\n' +
          '  </tr>\n' +
          '  <tr>\n' +
          '    <td colspan="2">尿酮体</td>\n' +
          '     <td colspan="2">&nbsp;</td>\n' +
          '     <td colspan="2">尿潜血</td>\n' +
          '    <td colspan="2">&nbsp;</td>\n' +
          '  </tr>\n' +
          '  <tr>\n' +
          '    <td>空腹血糖</td>\n' +
          '    <td colspan="4"><div align="right">mmol/L</div></td>\n' +
          '    <td colspan="4"><div align="right">mg/dL</div></td>\n' +
          '  </tr>\n' +
          '  <tr>\n' +
          '     <td>心电图</td>\n' +
          '     <td colspan="8">&nbsp;</td>\n' +
          '  </tr>\n' +
          '  <tr>\n' +
          '     <td>尿微量白蛋白</td>\n' +
          '     <td colspan="8">&nbsp;</td>\n' +
          '  </tr>\n' +
          '  <tr>\n' +
          '     <td>大便潜血</td>\n' +
          '     <td colspan="8">&nbsp;</td>\n' +
          '  </tr>\n' +
          '  <tr>\n' +
          '     <td>糖化血红蛋白</td>\n' +
          '     <td colspan="8">&nbsp;</td>\n' +
          '  </tr>\n' +
          '  <tr>\n' +
          '     <td>乙型肝炎  表面抗原</td>\n' +
          '     <td colspan="8">&nbsp;</td>\n' +
          '  </tr>\n' +
          '  <tr>\n' +
          '    <td rowspan="3">肝功能</td>\n' +
          '    <td colspan="2">血清谷丙转氨酶</td>\n' +
          '    <td colspan="2"><div align="right">U/L</div></td>\n' +
          '    <td colspan="2">血清谷草转氨酶</td>\n' +
          '    <td colspan="2"><div align="right">U/L</div></td>\n' +
          '  </tr>\n' +
          '  <tr>\n' +
          '    <td colspan="2">白蛋白</td>\n' +
          '     <td colspan="2"><div align="right">g/L</div></td>\n' +
          '     <td colspan="2">总胆红素</td>\n' +
          '     <td colspan="2"><div align="right">umol/L</div></td>\n' +
          '  </tr>\n' +
          '  <tr>\n' +
          '    <td colspan="2">结合胆红素</td>\n' +
          '     <td colspan="2"><div align="right">μmol/L</div></td>\n' +
          '     <td colspan="2">&nbsp;</td>\n' +
          '     <td colspan="2">&nbsp;</td>\n' +
          '  </tr>\n' +
          '  <tr>\n' +
          '     <td rowspan="2">肾功能</td>\n' +
          '     <td colspan="2">血清肌酐</td>\n' +
          '     <td colspan="2"><div align="right">μmol/L</div></td>\n' +
          '     <td colspan="2">其他</td>\n' +
          '     <td colspan="2"><div align="right">mmol/L</div></td>\n' +
          '  </tr>\n' +
          '  <tr>\n' +
          '    <td colspan="2">腹部B超</td>\n' +
          '     <td colspan="2"><div align="right">mmol/L</div></td>\n' +
          '     <td colspan="2">其他</td>\n' +
          '    <td colspan="2"><div align="right">mmol/L</div></td>\n' +
          '  </tr>\n' +
          '  <tr>\n' +
          '     <td rowspan="2">血 脂</td>\n' +
          '     <td colspan="2">总胆固醇</td>\n' +
          '     <td colspan="2"><div align="right">mmol/L </div></td>\n' +
          '     <td colspan="2">其他</td>\n' +
          '     <td colspan="2"><div align="right">mmol/L </div></td>\n' +
          '  </tr>\n' +
          '  <tr>\n' +
          '    <td colspan="2">腹部B超</td>\n' +
          '     <td colspan="2"><div align="right">mmol/L </div></td>\n' +
          '     <td colspan="2">其他</td>\n' +
          '    <td colspan="2"><div align="right">mmol/L </div></td>\n' +
          '  </tr>\n' +
          '  <tr>\n' +
          '     <td height="25">胸部 X 线片</td>\n' +
          '  <td colspan="8">&nbsp;</td>  </tr>\n' +
          '  <tr>\n' +
          '     <td>B 超</td>\n' +
          '     <td colspan="2">腹部B超</td>\n' +
          '     <td colspan="2">&nbsp;</td>\n' +
          '     <td colspan="2">其他</td>\n' +
          '     <td colspan="2">&nbsp;</td>\n' +
          '  </tr>\n' +
          '  <tr>\n' +
          '     <td>宫颈涂片</td>\n' +
          '     <td colspan="8">&nbsp;</td>\n' +
          '  </tr>\n' +
          '  <tr>\n' +
          '     <td>其他</td>\n' +
          '     <td colspan="8">&nbsp;</td>\n' +
          '  </tr>\n' +
          '  <tr>\n' +
          '     <th rowspan="7" scope="row">现存主要  健康问题</th>\n' +
          '     <td>脑血管疾病</td>\n' +
          '     <td colspan="8">&nbsp;</td>\n' +
          '  </tr>\n' +
          '  <tr>\n' +
          '     <td>肾脏疾病</td>\n' +
          '     <td colspan="8">&nbsp;</td>\n' +
          '  </tr>\n' +
          '  <tr>\n' +
          '     <td>心脏疾病</td>\n' +
          '     <td colspan="8">&nbsp;</td>\n' +
          '  </tr>\n' +
          '  <tr>\n' +
          '     <td>血管疾病</td>\n' +
          '     <td colspan="8">&nbsp;</td>\n' +
          '  </tr>\n' +
          '  <tr>\n' +
          '    <td>眼部疾病</td>\n' +
          '    <td colspan="8">&nbsp;</td>\n' +
          '  </tr>\n' +
          '  <tr>\n' +
          '    <td>神经系统疾病</td>\n' +
          '    <td colspan="8">&nbsp;</td>\n' +
          '  </tr>\n' +
          '  <tr>\n' +
          '    <td>其他系统疾病</td>\n' +
          '    <td colspan="8">&nbsp;</td>\n' +
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
          '    <td colspan="2"><div align="center">/</div></td>\n' +
          '    <td colspan="2">&nbsp;</td>\n' +
          '    <td colspan="2">&nbsp;</td>\n' +
          '    <td colspan="2">&nbsp;</td>\n' +
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
          '    <td colspan="2"><div align="center">/</div></td>\n' +
          '    <td colspan="2">&nbsp;</td>\n' +
          '    <td colspan="2">&nbsp;</td>\n' +
          '    <td colspan="2">&nbsp;</td>>\n' +
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
          '    <td colspan="2">&nbsp;</td>\n' +
          '    <td colspan="2">&nbsp;</td>\n' +
          '    <td colspan="5">&nbsp;</td>\n' +
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
          '    <td colspan="9">&nbsp;</td>\n' +
          '  </tr>\n' +
          '  <tr>\n' +
          '    <th height="47" scope="row">健康指导</th>\n' +
          '    <td colspan="9">&nbsp;</td>\n' +
          '  </tr>\n' +
          '  <tr>\n' +
          '    <th scope="row">危险因素控制</th>\n' +
          '    <td colspan="9">&nbsp;</td>\n' +
          '  </tr>\n' +
          '</table>\n' +
          '</div>\n' +
          '</body>\n' +
          '</html>\n'
        str = str.replace(/{{name}}/, this.editForm.name ? this.editForm.name : '')
        str = str.replace(/{{fileId}}/, this.editForm.id ? this.editForm.id : '')
        str = str.replace(/{{fileStatus}}/, this.editForm.fileStatus ? this.editForm.fileStatus : '')
        str = str.replace(/{{sex}}/, this.editForm.sex ? this.editForm.sex : '')
        str = str.replace(/{{birth}}/, this.editForm.birth ? this.editForm.birth : '')
        str = str.replace(/{{belongComm}}/, this.editForm.belongComm ? this.editForm.belongComm : '')
        str = str.replace(/{{docuType}}/, this.editForm.docuType ? this.editForm.docuType : '')
        str = str.replace(/{{idNum}}/, this.editForm.idNum ? this.editForm.idNum : '')
        str = str.replace(/{{workPlace}}/, this.editForm.workPlace ? this.editForm.workPlace : '')
        str = str.replace(/{{tPhoneNum}}/, this.editForm.tPhoneNum ? this.editForm.tPhoneNum : '')
        str = str.replace(/{{phonerName}}/, this.editForm.phonerName ? this.editForm.phonerName : '')
        str = str.replace(/{{phonerNum}}/, this.editForm.phonerNum ? this.editForm.phonerNum : '')
        str = str.replace(/{{local}}/, this.editForm.local ? this.editForm.local : '')
        str = str.replace(/{{medicalNum}}/, this.editForm.medicalNum ? this.editForm.medicalNum : '')
        str = str.replace(/{{residencePlace}}/, this.editForm.residencePlace ? this.editForm.residencePlace : '')
        str = str.replace(/{{ethnic}}/, this.editForm.ethnic ? this.editForm.ethnic : '')
        str = str.replace(/{{localPlace}}/, this.editForm.localPlace ? this.editForm.localPlace : '')
        str = str.replace(/{{houseAddress}}/, this.editForm.houseAddress ? this.editForm.houseAddress : '')
        str = str.replace(/{{internalNum}}/, this.editForm.internalNum ? this.editForm.internalNum : '')
        str = str.replace(/{{hyperNum}}/, this.editForm.hyperNum ? this.editForm.hyperNum : '')
        str = str.replace(/{{diabetesNum}}/, this.editForm.diabetesNum ? this.editForm.diabetesNum : '')
        str = str.replace(/{{signNum}}/, this.editForm.signNum ? this.editForm.signNum : '')
        str = str.replace(/{{bloodType}}/, this.editForm.bloodType ? this.editForm.bloodType : '')
        str = str.replace(/{{educational}}/, this.editForm.educational ? this.editForm.educational : '')
        str = str.replace(/{{occupation}}/, this.editForm.occupation ? this.editForm.occupation : '')
        str = str.replace(/{{maritalStatus}}/, this.editForm.maritalStatus ? this.editForm.maritalStatus : '')
        str = str.replace(/{{expensesPay}}/, this.editForm.expensesPay ? this.editForm.expensesPay : '')
        str = str.replace(/{{drugAllergyHistory}}/, this.editForm.drugAllergyHistory ? this.editForm.drugAllergyHistory : '')
        str = str.replace(/{{exposureHistory}}/, this.editForm.exposureHistory ? this.editForm.exposureHistory : '')
        str = str.replace(/{{pastHistory}}/, this.editForm.pastHistory ? this.editForm.pastHistory : '')
        str = str.replace(/{{familyHistory}}/, this.editForm.familyHistory ? this.editForm.familyHistory : '')
        str = str.replace(/{{fatherHistory}}/, this.editForm.fatherHistory ? this.editForm.fatherHistory : '')
        str = str.replace(/{{matherHistory}}/, this.editForm.matherHistory ? this.editForm.matherHistory : '')
        str = str.replace(/{{bortherHistory}}/, this.editForm.bortherHistory ? this.editForm.bortherHistory : '')
        str = str.replace(/{{sonHistory}}/, this.editForm.sonHistory ? this.editForm.sonHistory : '')
        str = str.replace(/{{geneticHistory}}/, this.editForm.geneticHistory ? this.editForm.geneticHistory : '')
        str = str.replace(/{{dIsability}}/, this.editForm.dIsability ? this.editForm.dIsability : '')
        str = str.replace(/{{kitchenExhaust}}/, this.editForm.kitchenExhaust ? this.editForm.kitchenExhaust : '')
        str = str.replace(/{{fuel}}/, this.editForm.fuel ? this.editForm.fuel : '')
        str = str.replace(/{{water}}/, this.editForm.water ? this.editForm.water : '')
        str = str.replace(/{{toilet}}/, this.editForm.toilet ? this.editForm.toilet : '')
        str = str.replace(/{{operation}}/, this.editForm.operation ? this.editForm.operation : '')
        str = str.replace(/{{trauma}}/, this.editForm.trauma ? this.editForm.trauma : '')
        str = str.replace(/{{transfusion}}/, this.editForm.transfusion ? this.editForm.transfusion : '')

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
      // 获取用户列表
      getUsers() {
        const para = {
          page: this.page,
          size: 20,
          filters: { name: this.filters.name }
        }
        getUserListPage(para).then(res => {
          this.total = res.detailModelList.length
          this.users = res.detailModelList
        })
      },
      // 删除
      handleDel(index, row) {
        this.$confirm('确认删除该记录吗?', '提示', {
          type: 'warning',
          confirmButtonText: ' 确定',
          cancelButtonText: ' 取消'
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
      },
      // 显示新增界面
      handleAdd() {
        this.dialogStatus = 'create'
        this.dialogFormVisible = true
        this.edit = false
        this.editForm = {
          id: 0,
          name: '',
          checkDate: null,
          doctor: '',
          symptoms: [],
          temperature: '',
          pulseRate: '',
          breathing: '',
          pressure: '',
          stature: '',
          weight: '',
          waistline: '',
          bodymassindex: '',
          selfAssessment: '',
          selfCare: '',
          cognition: '',
          mood: '',
          exercise: '',
          diet: [],
          smoking: '',
          drinking: '',
          workDiseases: '',
          oralCavity: '',
          vision: '',
          listen: '',
          sport: '',
          bottomEye: '',
          skin: '',
          sclera: '',
          lymphNode: '',
          lungs: '',
          heart: '',
          abdomen: '',
          limbEdema: '',
          footArteyPulse: '',
          analDiagnosis: '',
          mammaryGland: '',
          vulva: '',
          vagina: '',
          cervix: '',
          palace: '',
          others: '',
          bloodRoutine: '',
          urineRoutine: '',
          fastingBloodSugar: '',
          electrocardiogram: '',
          urineMicroalbumin: '',
          shitblood: '',
          glycatedGlycemic: '',
          hepatitisB: '',
          liverFunction: '',
          renalFunction: '',
          lipid: '',
          chestXRay: '',
          bSuper: '',
          cervicalSmear: '',
          cerebrovascular: [],
          kidneyDisease: [],
          heartDisease: [],
          vascularDisease: [],
          eyeDisease: [],
          nervousSystem: '',
          otherDiabetes: '',
          historyHospital: '',
          histoFamilyBed: '',
          mainDrugUse: '',
          historyVaccination: '',
          healthEvaluation: '',
          healthGuidance: [],
          Riskcontrol: []
        }
      },
      // 编辑
      updateData() {
        this.$refs.editForm.validate(valid => {
          if (valid) {
            this.$confirm('确认提交吗？', '提示', {})
              .then(() => {
                const para = Object.assign({}, this.editForm)

                para.nextDate =
                  !para.nextDate || para.nextDate === ''
                    ? ''
                    : util.formatDate.format(new Date(para.nextDate), 'yyyy-MM-dd')

                para.followDate =
                  !para.followDate || para.followDate === ''
                    ? ''
                    : util.formatDate.format(new Date(para.followDate), 'yyyy-MM-dd')

                // 字典处理
                // 危险控制
                para.Riskcontrol = ''
                for (const itm of this.editForm.Riskcontrol) {
                  para.Riskcontrol += itm
                  para.Riskcontrol += ','
                }
                para.Riskcontrol = para.Riskcontrol.substr(0, para.Riskcontrol.length - 1)
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
                editUser(para).then(res => {
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
            this.$confirm('确认提交吗？', '提示', {})
              .then(() => {
                const para = Object.assign({}, this.editForm)
                console.log(para)

                para.nextDate =
                  !para.nextDate || para.nextDate === ''
                    ? ''
                    : util.formatDate.format(new Date(para.nextDate), 'yyyy-MM-dd')

                para.followDate =
                  !para.followDate || para.followDate === ''
                    ? ''
                    : util.formatDate.format(new Date(para.followDate), 'yyyy-MM-dd')
                // 字典处理
                // 危险控制
                para.Riskcontrol = ''
                for (const itm of this.editForm.Riskcontrol) {
                  para.Riskcontrol += itm
                  para.Riskcontrol += ','
                }
                para.Riskcontrol = para.Riskcontrol.substr(0, para.Riskcontrol.length - 1)
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
                addUser(para).then(res => {
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
