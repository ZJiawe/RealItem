<template>

  <section class="app-container">

    <el-col :span="24" class="toolbar" style="padding-bottom: 0px;">
      <el-form :inline="true" :model="filters" @submit.native.prevent>
        <el-form-item>
          <el-input v-model="filters.name" placeholder="姓名"></el-input>
        </el-form-item>
        <el-form-item>
          <el-input v-model="filters.follType" placeholder="随访方式"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" v-on:click="searchs"  style="background-color:#4db2ee;border-color:transparent" icon="el-icon-search">查询</el-button>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" v-on:click="findbtn"  style="background-color:#4db2ee;border-color:transparent" icon="el-icon-search">高级查询</el-button>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="handleAdd" icon="el-icon-circle-plus-outline">新增</el-button>
        </el-form-item>
      </el-form>
    </el-col>
    <!--工具条-->
   <!-- <el-col :span="0" class="toolbar" style="padding-bottom: 0px;" align="right">
      <el-form :inline="true" :model="filters" @submit.native.prevent>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" v-on:click="searchs" style="background-color:#4db2ee;border-color:transparent" icon="el-icon-search">查询</el-button>
      </div>

    </el-col>
-->
    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible2" :close-on-click-modal="false" center>
      <el-form :model="findForm" label-width="100px" :rules="findFormRules" ref="findForm">
        <el-row>
          <el-col :span="12">
        <el-form-item label="日吸烟量">
          <el-input v-model="filters.smokePer" placeholder="日吸烟量"></el-input>
        </el-form-item>
          </el-col>
          <el-col :span="12" >
        <el-form-item label="运动">
          <el-input v-model="filters.sport" placeholder="运动"></el-input>
        </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="12">
        <el-form-item label="摄盐情况">
          <el-input v-model="filters.saltSituation" placeholder="摄盐情况"></el-input>
        </el-form-item>
          </el-col>
          <el-col :span="12" >
        <el-form-item label="心理调整">
          <el-input v-model="filters.psyAdjust" placeholder="心理调整"></el-input>
        </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="12">
        <el-form-item label="遵医行为">
          <el-input v-model="filters.obeyMedical" placeholder="遵医行为"></el-input>
        </el-form-item>
          </el-col>
          <el-col :span="12" >
        <el-form-item label="服药依从性">
          <el-input v-model="filters.mediCompli" placeholder="服药依从性"></el-input>
        </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="12">
        <el-form-item label="药物不良反应">
          <el-input v-model="filters.adverdrugReact" placeholder="药物不良反应"></el-input>
        </el-form-item>
          </el-col>
          <el-col :span="12" >
        <el-form-item label="此次随访分类">
          <el-input v-model="filters.accessType" placeholder="此次随访分类"></el-input>
        </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="12">
        <el-form-item label="药物名称">
          <el-input v-model="filters.drugName" placeholder="药物名称"></el-input>
        </el-form-item>
          </el-col>
          <el-col :span="12" >
        <el-form-item label="机构及科别">
          <el-input v-model="filters.organAndDiv" placeholder="机构及科别"></el-input>
        </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="12">
        <el-form-item label="随访日期">
        <el-date-picker type="date" placeholder="选择日期" v-model="filters.follUpdate_s" style="width: 100%;"></el-date-picker>
        </el-form-item>
          </el-col>
          <el-col :span="12" >
          <el-date-picker type="date" placeholder="选择日期" v-model="filters.follUpdate_e" style="width: 100%;"></el-date-picker>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="12">
        <el-form-item label="下次随访日期">
          <el-date-picker type="date" placeholder="选择日期" v-model="filters.dateNextFollow_s" style="width: 100%;"></el-date-picker>
        </el-form-item>
          </el-col>
          <el-col :span="12" >
          <el-date-picker type="date" placeholder="选择日期" v-model="filters.dateNextFollow_e" style="width: 100%;"></el-date-picker>
          </el-col>
        </el-row>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" v-on:click="searchs" style="background-color:#4db2ee;border-color:transparent" icon="el-icon-search">查询</el-button>
      </div>

    </el-dialog>
    <!--列表-->
    <el-table :data="users" :height="tableMeight" highlight-current-row @selection-change="selsChange" border style="width: 100%;margin-top: -20px;"@row-dblclick="handleRowChange">
      <el-table-column prop="name" label="姓名" width="80">
      </el-table-column>
      <el-table-column prop="id" label="编号" width="60">
      </el-table-column>
      <el-table-column prop="follUpdate" label="随访日期" width="150">
      </el-table-column>
      <el-table-column prop="follType" label="随访方式" width="150">
      </el-table-column>
      <el-table-column prop="symptoms" label="症状" min-width="120">
      </el-table-column>
      <el-table-column prop="bloodPressure"  v-if='false' label="血压" min-width="120">
      </el-table-column>
      <el-table-column prop="weight"  v-if='false' label="体重" min-width="120">
      </el-table-column>
      <el-table-column prop="bmi"  v-if='false' label="体质指数" min-width="120">
      </el-table-column>
      <el-table-column prop="heartRate"  v-if='false' label="心率" min-width="120">
      </el-table-column>
      <el-table-column prop="otherPhy"  v-if='false' label="其他" min-width="120">
      </el-table-column>
      <el-table-column prop="smokePer"  v-if='false' label="日吸烟量" min-width="120">
      </el-table-column>
      <el-table-column prop="sport"  v-if='false' label="运动" min-width="120">
      </el-table-column>
      <el-table-column prop="saltSituation"  v-if='false' label="摄盐情况" min-width="120">
      </el-table-column>
      <el-table-column prop="psyAdjust"  v-if='false' label="心理调整" min-width="120">
      </el-table-column>
      <el-table-column prop="obeyMedical"  v-if='false' label="遵医行为" min-width="120">
      </el-table-column>
      <el-table-column prop="auxExamina"  v-if='false' label="辅助检查" min-width="120">
      </el-table-column>
      <el-table-column prop="mediCompli"  v-if='false' label="服药依从性" min-width="120">
      </el-table-column>
      <el-table-column prop="adverdrugReact"  v-if='false' label="药物不良反应" min-width="120">
      </el-table-column>
      <el-table-column prop="accessType"  v-if='false' label="此次随访分类" min-width="120">
      </el-table-column>
      <el-table-column prop="drugName"  v-if='false' label="药物名称" min-width="120">
      </el-table-column>
      <el-table-column prop="drugUsage"  v-if='false' label="用法用量" min-width="120">
      </el-table-column>
      <el-table-column prop="othDrugName"  v-if='false' label="其他药物名" min-width="120">
      </el-table-column>
      <el-table-column prop="otherDrugU"  v-if='false' label="其他药物用法用量" min-width="120">
      </el-table-column>
      <el-table-column prop="referralReason"  v-if='false' label="转诊原因" min-width="120">
      </el-table-column>
      <el-table-column prop="organAndDiv"  v-if='false' label="机构及科别" min-width="120">
      </el-table-column>
      <el-table-column prop="dateNextFollow" label="下次随访日期" min-width="120">
      </el-table-column>
      <el-table-column prop="docSignature"  v-if='false' label="随访医生签名" min-width="120">
      </el-table-column>
      <el-table-column prop="creater"  v-if='false' label="创建人" min-width="120">
      </el-table-column>
      <el-table-column prop="createDate"  v-if='false' label="创建时间" min-width="120">
      </el-table-column>
      <el-table-column prop="omdifier"  v-if='false' label="修改人" min-width="120">
      </el-table-column>
      <el-table-column prop="modifyDate"  v-if='false' label="修改时间" min-width="120">
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
     <!-- <el-button type="danger" @click="batchRemove" :disabled="this.sels.length===0">批量删除</el-button>-->
      <el-pagination layout="prev, pager, next" :current-page.sync="page" @current-change="handleCurrentChange" :page-size="20" :total="total" style="float:right;">
      </el-pagination>
    </el-col>

    <!--编辑界面-->

    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible" :close-on-click-modal="false" center>
      <el-form :model="editForm" label-width="100px" :rules="editFormRules" ref="editForm">
        <div class="container"  id="print">
          <el-tabs v-model="activeName">
              <el-row>
                <el-col :span="12">
                  <el-form-item label="姓名" prop="name">
                    <el-input v-model="editForm.name" auto-complete="off"></el-input>
                  </el-form-item>
                </el-col>

                <el-col :span="12" >
                  <el-form-item label="编号">
                    <el-input  v-model="editForm.id" auto-complete="off" ></el-input>
                  </el-form-item>
                </el-col>
              </el-row>

            <el-row>
              <el-col :span="12">
                <el-form-item label="身份证号" prop="">
                  <el-input v-model="editForm.peopleId" auto-complete="off"></el-input>
                </el-form-item>
              </el-col>

              <el-col :span="12" >
                <el-form-item label="血压">
                  <el-input v-model.number="editForm.bloodPressure">
                    <template slot="append">mmHg</template>
                  </el-input>
                </el-form-item>
              </el-col>
            </el-row>

            <el-row>
              <el-col :span="12">
                <el-form-item label="随访日期" prop="">
                  <el-date-picker type="date" placeholder="选择日期" v-model="editForm.follUpdate" style="width: 100%;"></el-date-picker>
                </el-form-item>
              </el-col>

              <el-col :span="12" >
                <el-form-item label="随访方式">
                  <el-radio-group v-model="editForm.follType">
                    <el-radio label="门诊"></el-radio>
                    <el-radio label="家庭"></el-radio>
                    <el-radio label="电话"></el-radio>
                  </el-radio-group>
                </el-form-item>
              </el-col>
            </el-row>

            <el-form-item label="第几次随访">
              <el-radio-group v-model="editForm.number">
                <el-radio label=第一次>第一次</el-radio>
                <el-radio label=第二次>第二次</el-radio>
                <el-radio label=第三次>第三次</el-radio>
                <el-radio label=第四次>第四次</el-radio>
              </el-radio-group>
            </el-form-item>

            <el-form-item label="症状" >
              <el-checkbox-group v-model="editForm.symptoms" style="width:100%">
                <el-checkbox v-for="item in symptoms" :key="item.label" :name="type" :label="item.label" :value="item.value"></el-checkbox>
              </el-checkbox-group>
            </el-form-item>

            <el-row>
              <el-col :span="12">
                <el-form-item label="体重(kg)" prop="weight">
                  <el-input v-model.number="editForm.weight">
                    <template slot="append">(kg)</template>
                  </el-input>
                </el-form-item>
              </el-col>

              <el-col :span="12" >
                <el-form-item label="心率" prop="heartRate">
                  <el-input v-model.number="editForm.heartRate">
                    <template slot="append">次/分钟</template>
                  </el-input>
                </el-form-item>
              </el-col>
            </el-row>

            <el-row>
              <el-col :span="12">
                <el-form-item label="其他" prop="">
                  <el-input v-model="editForm.otherPhy"></el-input>
                </el-form-item>
              </el-col>

              <el-col :span="12" >
                <el-form-item label="体质指数">
                  <el-input v-model="editForm.bmi">
                    <template slot="append">(BMI)kg/m^2</template>
                  </el-input>
                </el-form-item>
              </el-col>
            </el-row>


            <el-row>
              <el-col :span="12">
                <el-form-item label="日吸烟量" prop="">
                  <el-input v-model="editForm.smokePer">
                    <template slot="append">(支)</template>
                  </el-input>
                </el-form-item>
              </el-col>

              <el-col :span="12" >
                <el-form-item label="日饮酒量">
                  <el-input v-model="editForm.drinkPer">
                    <template slot="append">(两)</template>
                  </el-input>
                </el-form-item>
              </el-col>
            </el-row>

            <el-row>
              <el-col :span="12">
                <el-form-item label="运动" prop="">
                  <el-input placeholder="周/次   分钟/次" v-model="editForm.sport"></el-input>
                </el-form-item>
              </el-col>

              <el-col :span="12" >
                <el-form-item label="摄盐情况">
                  <el-input placeholder="轻/中/重   轻/中/重" v-model="editForm.saltSituation"></el-input>
                </el-form-item>
              </el-col>
            </el-row>

            <el-row>
              <el-col :span="12">
                <el-form-item label="心理调整" prop="">
                  <el-radio-group v-model="editForm.psyAdjust">
                    <el-radio label="良好"></el-radio>
                    <el-radio label="一般"></el-radio>
                    <el-radio label="差"></el-radio>
                  </el-radio-group>
                </el-form-item>
              </el-col>

              <el-col :span="12" >
                <el-form-item label="遵医行为">
                  <el-radio-group v-model="editForm.obeyMedical">
                    <el-radio label="良好"></el-radio>
                    <el-radio label="一般"></el-radio>
                    <el-radio label="差"></el-radio>
                  </el-radio-group>
                </el-form-item>
              </el-col>
            </el-row>


            <el-row>
              <el-col :span="12">
                <el-form-item label="辅助检查" prop="">
                  <el-input v-model="editForm.auxExamina"></el-input>
                </el-form-item>
              </el-col>

              <el-col :span="12" >
                <el-form-item label="服药依从性">
                  <el-radio-group v-model="editForm.mediCompli">
                    <el-radio label="规律"></el-radio>
                    <el-radio label="间断"></el-radio>
                    <el-radio label="不服药"></el-radio>
                  </el-radio-group>
                </el-form-item>
              </el-col>
            </el-row>


            <el-row>
              <el-col :span="12">
                <el-form-item label="药物不良反应" prop="">
                  <el-radio-group v-model="editForm.adverdrugReact">
                    <el-radio label="无"></el-radio>
                    <el-radio label="有"></el-radio>
                  </el-radio-group>
                </el-form-item>
              </el-col>

              <el-col :span="12" >
                <el-form-item label="此次随访分类">
                  <el-radio-group v-model="editForm.accessType">
                    <el-radio label="控制满意"></el-radio>
                    <el-radio label="控制不满意"></el-radio>
                    <el-radio label="不良反应"></el-radio>
                    <el-radio label="并发症"></el-radio>
                  </el-radio-group>
                </el-form-item>
              </el-col>
            </el-row>



            <el-row>
              <el-col :span="12">
                <el-form-item label="药物名称1" prop="">
                  <el-input v-model="editForm.drugName"></el-input>
                </el-form-item>
              </el-col>

              <el-col :span="12" >
                <el-form-item label="用法用量1">
                  <el-input placeholder="每日_次   每次_" v-model="editForm.drugUsage"></el-input>
                </el-form-item>
              </el-col>
            </el-row>


            <el-row>
              <el-col :span="12">
                <el-form-item label="药物名称2" prop="">
                  <el-input v-model="editForm.othDrugName"></el-input>
                </el-form-item>
              </el-col>

              <el-col :span="12" >
                <el-form-item label="用法用量2">
                  <el-input placeholder="每日_次   每次_" v-model="editForm.otherDrugU"></el-input>
                </el-form-item>
              </el-col>
            </el-row>

            <el-row>
              <el-col :span="12">
                <el-form-item label="药物名称3" prop="">
                  <el-input v-model="editForm.othDrugNameSan"></el-input>
                </el-form-item>
              </el-col>

              <el-col :span="12" >
                <el-form-item label="用法用量3">
                  <el-input placeholder="每日_次   每次_" v-model="editForm.otherDrugUSan"></el-input>
                </el-form-item>
              </el-col>
            </el-row>

            <el-row>
              <el-col :span="12">
                <el-form-item label="药物名称4" prop="">
                  <el-input v-model="editForm.othDrugNameSi"></el-input>
                </el-form-item>
              </el-col>

              <el-col :span="12" >
                <el-form-item label="用法用量4">
                  <el-input placeholder="每日_次   每次_" v-model="editForm.otherDrugUSi"></el-input>
                </el-form-item>
              </el-col>
            </el-row>

            <el-row>
              <el-col :span="12">
                <el-form-item label="原因" prop="">
                  <el-input v-model="editForm.referralReason"></el-input>
                </el-form-item>
              </el-col>

              <el-col :span="12" >
                <el-form-item label="机构及科别">
                  <el-input v-model="editForm.organAndDiv"></el-input>
                </el-form-item>
              </el-col>
            </el-row>

            <el-row>
              <el-col :span="12">
                <el-form-item label="下次随访日期" prop="">
                  <el-date-picker type="date" placeholder="选择日期" v-model="editForm.dateNextFollow" style="width:100%;"></el-date-picker>
                </el-form-item>
              </el-col>

              <el-col :span="12" >
                <el-form-item label="随访医生签名">
                  <el-input v-model="editForm.docSignature"></el-input>
                </el-form-item>
              </el-col>
            </el-row>

          </el-tabs>
        </div>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="conprint()">打印</el-button>
        <el-button v-if="dialogStatus=='create'" type="primary" @click="createData">添加</el-button>
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
    addUser,
    print
  } from '@/api/highbood'

  export default {
    data() {
      return {
        symptoms: [
          { value: '无症状', label: '无症状' },
          { value: '头疼头晕', label: '头疼头晕' },
          { value: '恶心呕吐', label: '恶心呕吐' },
          { value: '眼花耳鸣', label: '眼花耳鸣' },
          { value: '心悸胸闷', label: '心悸胸闷' },
          { value: '鼻鲤出血不止', label: '鼻鲤出血不止' },
          { value: '四肢发麻', label: '四肢发麻' },
          { value: '下肢肿胀', label: '下肢肿胀' }
        ],
        edit: true,
        activeName: 'first',
        dialogStatus: '',
        tableMeight: window.innerHeight - 220,
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
          follType: '',
          smokePer: '',
          sport: '',
          saltSituation: '',
          psyAdjust: '',
          obeyMedical: '',
          mediCompli: '',
          adverdrugReact: '',
          accessType: '',
          drugName: '',
          organAndDiv: '',
          follUpdate_s: '',
          follUpdate_e: '',
          dateNextFollow_s: '',
          dateNextFollow_e: ''
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
            { required: true, message: '输入正确的姓名', trigger: 'blur' },
            { min: 2, max: 5, message: '长度在 2 到 5 个字符', trigger: 'blur' },
            { pattern: /^[\u4E00-\u9FA5]+$/, message: '用户名只能为中文'
            }
          ]/*,
          bloodPressure: [
            { required: true, message: '血压不能为空', trigger: 'blur' },
            { type: 'number', message: '血压必须为数字值', trigger: 'blur' }
          ],
          weight: [
            { required: true, message: '体重不能为空', trigger: 'blur' },
            { type: 'number', message: '体重必须为数字值', trigger: 'blur' }
          ],
          heartRate: [
            { required: true, message: '心率不能为空', trigger: 'blur' },
            { type: 'number', message: '心率必须为数字值', trigger: 'blur' }
          ]*/
        },
        // 编辑界面数据
        editForm: {
          name: '',
          id: '',
          number: '',
          peopleId: '',
          follUpdate: '',
          follType: '',
          symptoms: [],
          bloodPressure: '',
          weight: '',
          bmi: '',
          heartRate: '',
          otherPhy: '',
          smokePer: '',
          drinkPer: '',
          sport: '',
          saltSituation: '',
          psyAdjust: '',
          obeyMedical: '',
          auxExamina: '',
          mediCompli: '',
          adverdrugReact: '',
          accessType: '',
          drugName: '',
          drugUsage: '',
          othDrugName: '',
          otherDrugU: '',
          othDrugNameSan: '',
          otherDrugUSan: '',
          othDrugNameSi: '',
          otherDrugUSi: '',
          referralReason: '',
          organAndDiv: '',
          dateNextFollow: '',
          docSignature: '',
          creater: '1',
          createDate: '',
          modifier: '1',
          modifyDate: ''
        },

        addFormVisible: false, // 新增界面是否显示
        addFormRules: {
          name: [{ required: true, message: '请输入姓名', trigger: 'blur' }]
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
          '    <h2 style="width:794px;text-align:center"> 高血压患者随访服务记录表</h2>  \n' +
          '    <span style="margin-left:34px;">姓    名：{{name}}</span>\n' +
          '    <span style="margin-left:510px;">编号:{{id}}</span> \n' +
          '    </div>\n' +
          '  <table id="table"  border="1"  cellspacing="0" bordercolor="#000000" style="width:730px;margin-left:30px;margin-top:5px;border-collapse:collapse;"  width = "80%">\n' +
          '    <tbody>\n' +/*
          '      <tr>\n' +
          '        <td style="width: 20%;text-align:center">第几次</td>\n' +
          '        <td colspan=\'1\'>{{number1}}</td>\n' +
          '        <td colspan=\'1\'>{{number2}}</td>\n' +
          '        <td colspan=\'1\'>{{number3}}</td>\n' +
          '        <td colspan=\'1\'>{{number4}}</td>\n' +
          '      </tr>\n' */
          '      <tr>\n' +
          '        <td style="width: 10%;text-align:center">随访日期</td>\n' +
          '        <td colspan=\'1\'style="text-align:center">{{follUpdate1}}</td>\n' +
          '\t\t<td colspan=\'1\'style="text-align:center">{{follUpdate2}}</td>\n' +
          '\t\t<td colspan=\'1\'style="text-align:center">{{follUpdate3}}</td>\n' +
          '\t\t<td colspan=\'1\'style="text-align:center">{{follUpdate4}}</td>\n' +
          '      </tr>\n' +
          '\t  <tr>\n' +
          '        <td style="width: 10%;text-align:center">随访方式</td>\n' +
          '        <td colspan=\'1\'style="text-align:center">{{follType1}}</td>\n' +
          '\t\t<td colspan=\'1\'style="text-align:center">{{follType2}}</td>\n' +
          '\t\t<td colspan=\'1\'style="text-align:center">{{follType3}}</td>\n' +
          '\t\t<td colspan=\'1\'style="text-align:center">{{follType4}}</td>\n' +
          '      </tr>\n' +
          '\t  <tr>\n' +
          '       <td style="width: 10%;text-align:center">症状</td>\n' +
          '        <td colspan=\'4\'style="text-align:center">{{symptoms}}</td>\n' +
          '      </tr>\n' +
          '\t  <tr>\n' +
          '        <td style="width: 10%;text-align:center">血压（mmhg）</td>\n' +
          '        <td colspan=\'1\'style="text-align:center">{{bloodPressure1}}</td>\n' +
          '\t\t<td colspan=\'1\'style="text-align:center">{{bloodPressure2}}</td>\n' +
          '\t\t<td colspan=\'1\'style="text-align:center">{{bloodPressure3}}</td>\n' +
          '\t\t<td colspan=\'1\'style="text-align:center">{{bloodPressure4}}</td>\n' +
          '      </tr>\n' +
          '\t  <tr>\n' +
          '        <td style="width: 10%;text-align:center">体重（kg）</td>\n' +
          '        <td colspan=\'1\'style="text-align:center">{{weight1}}</td>\n' +
          '\t\t<td colspan=\'1\'style="text-align:center">{{weight2}}</td>\n' +
          '\t\t<td colspan=\'1\'style="text-align:center">{{weight3}}</td>\n' +
          '\t\t<td colspan=\'1\'style="text-align:center">{{weight4}}</td>\n' +
          '      </tr>\n' +
          '\t  <tr>\n' +
          '        <td style="width: 10%;text-align:center">体质指数</td>\n' +
          '        <td colspan=\'1\'style="text-align:center">{{bmi1}}</td>\n' +
          '\t\t<td colspan=\'1\'style="text-align:center">{{bmi2}}</td>\n' +
          '\t\t<td colspan=\'1\'style="text-align:center">{{bmi3}}</td>\n' +
          '\t\t<td colspan=\'1\'style="text-align:center">{{bmi4}}</td>\n' +
          '      </tr>\n' +
          '\t  <tr>\n' +
          '        <td style="width: 10%;text-align:center">心率</td>\n' +
          '        <td colspan=\'1\'style="text-align:center">{{heartRate1}}</td>\n' +
          '\t\t<td colspan=\'1\'style="text-align:center">{{heartRate2}}</td>\n' +
          '\t\t<td colspan=\'1\'style="text-align:center">{{heartRate3}}</td>\n' +
          '\t\t<td colspan=\'1\'style="text-align:center">{{heartRate4}}</td>\n' +
          '      </tr>\n' +
          '\t   <tr>\n' +
          '        <td style="width: 10%;text-align:center">其他</td>\n' +
          '        <td colspan=\'1\'style="text-align:center">{{otherPhy1}}</td>\n' +
          '\t\t<td colspan=\'1\'style="text-align:center">{{otherPhy2}}</td>\n' +
          '\t\t<td colspan=\'1\'style="text-align:center">{{otherPhy3}}</td>\n' +
          '\t\t<td colspan=\'1\'style="text-align:center">{{otherPhy4}}</td>\n' +
          '      </tr>\n' +
          '\t  <tr>\n' +
          '        <td style="width: 10%;text-align:center">日吸烟量</td>\n' +
          '        <td colspan=\'1\'style="text-align:center">{{smokePer1}}</td>\n' +
          '\t\t<td colspan=\'1\'style="text-align:center">{{smokePer2}}</td>\n' +
          '\t\t<td colspan=\'1\'style="text-align:center">{{smokePer3}}</td>\n' +
          '\t\t<td colspan=\'1\'style="text-align:center">{{smokePer4}}</td>\n' +
          '      </tr>\n' +
          '\t  <tr>\n' +
          '        <td style="width: 10%;text-align:center">日饮酒量</td>\n' +
          '        <td colspan=\'1\'style="text-align:center">{{drinkPer1}}</td>\n' +
          '\t\t<td colspan=\'1\'style="text-align:center">{{drinkPer2}}</td>\n' +
          '\t\t<td colspan=\'1\'style="text-align:center">{{drinkPer3}}</td>\n' +
          '\t\t<td colspan=\'1\'style="text-align:center">{{drinkPer4}}</td>\n' +
          '      </tr>\n' +
          '\t   <tr>\n' +
          '        <td rowspan="2" style="word-wrap:break-word;width: 10%;text-align:center">运动</td>\n' +
          '        <td style="width: 20%;text-align:center">{{sport1}}</td>\n' +
          '        <td style="width: 20%;text-align:center">{{sport2}}</td>\n' +
          '\t\t<td style="width: 20%;text-align:center">{{sport3}}</td>\n' +
          '\t\t<td style="width: 20%;text-align:center">{{sport4}}</td>\n' +
          '      </tr>\n' +
          '      <tr>\n' +
          '        <td style="width: 20%;text-align:center">{{sport5}}</td>\n' +
          '        <td style="width: 20%;text-align:center">{{sport6}}</td>\n' +
          '\t\t<td style="width: 20%;text-align:center">{{sport7}}</td>\n' +
          '\t\t<td style="width: 20%;text-align:center">{{sport8}}</td>\n' +
          '      </tr>\n' +
          '\t\n' +
          '\t  <tr>\n' +
          '        <td style="width: 10%;text-align:center">摄盐情况</td>\n' +
          '        <td colspan=\'1\'style="text-align:center">{{saltSituation1}}</td>\n' +
          '\t\t<td colspan=\'1\'style="text-align:center">{{saltSituation2}}</td>\n' +
          '\t\t<td colspan=\'1\'style="text-align:center">{{saltSituation3}}</td>\n' +
          '\t\t<td colspan=\'1\'style="text-align:center">{{saltSituation4}}</td>\n' +
          '      </tr>\n' +
          '\t  \n' +
          '\t   <tr>\n' +
          '        <td style="width: 10%;text-align:center">心理调整</td>\n' +
          '        <td colspan=\'1\'style="text-align:center">{{psyAdjust1}}</td>\n' +
          '\t\t<td colspan=\'1\'style="text-align:center">{{psyAdjust2}}</td>\n' +
          '\t\t<td colspan=\'1\'style="text-align:center">{{psyAdjust3}}</td>\n' +
          '\t\t<td colspan=\'1\'style="text-align:center">{{psyAdjust4}}</td>\n' +
          '      </tr>\n' +
          '\t  <tr>\n' +
          '        <td style="width: 10%;text-align:center">遵医行为</td>\n' +
          '        <td colspan=\'1\'style="text-align:center">{{obeyMedical1}}</td>\n' +
          '\t\t<td colspan=\'1\'style="text-align:center">{{obeyMedical2}}</td>\n' +
          '\t\t<td colspan=\'1\'style="text-align:center">{{obeyMedical3}}</td>\n' +
          '\t\t<td colspan=\'1\'style="text-align:center">{{obeyMedical4}}</td>\n' +
          '      </tr>\n' +
          '\t  <tr>\n' +
          '        <td style="width: 10%;text-align:center">辅助检查</td>\n' +
          '        <td colspan=\'1\'style="text-align:center">{{auxExamina1}}</td>\n' +
          '\t\t<td colspan=\'1\'style="text-align:center">{{auxExamina2}}</td>\n' +
          '\t\t<td colspan=\'1\'style="text-align:center">{{auxExamina3}}</td>\n' +
          '\t\t<td colspan=\'1\'style="text-align:center">{{auxExamina4}}</td>\n' +
          '      </tr>\n' +
          '\t<tr>\n' +
          '        <td style="width: 10%;text-align:center">服药依从性</td>\n' +
          '        <td colspan=\'1\'style="text-align:center">{{mediCompli1}}</td>\n' +
          '\t\t<td colspan=\'1\'style="text-align:center">{{mediCompli2}}</td>\n' +
          '\t\t<td colspan=\'1\'style="text-align:center">{{mediCompli3}}</td>\n' +
          '\t\t<td colspan=\'1\'style="text-align:center">{{mediCompli4}}</td>\n' +
          '      </tr>\n' +
          '\t  <tr>\n' +
          '        <td style="width: 10%;text-align:center">药物不良反应</td>\n' +
          '        <td colspan=\'1\'style="text-align:center">{{adverdrugReact1}}</td>\n' +
          '\t\t<td colspan=\'1\'style="text-align:center">{{adverdrugReact2}}</td>\n' +
          '\t\t<td colspan=\'1\'style="text-align:center">{{adverdrugReact3}}</td>\n' +
          '\t\t<td colspan=\'1\'style="text-align:center">{{adverdrugReact4}}</td>\n' +
          '      </tr>\n' +
          '\t  <tr>\n' +
          '        <td style="width: 10%;text-align:center">此次随访分类</td>\n' +
          '        <td colspan=\'1\'style="text-align:center">{{accessType1}}</td>\n' +
          '\t\t<td colspan=\'1\'style="text-align:center">{{accessType2}}</td>\n' +
          '\t\t<td colspan=\'1\'style="text-align:center">{{accessType3}}</td>\n' +
          '\t\t<td colspan=\'1\'style="text-align:center">{{accessType4}}</td>\n' +
          '      </tr>\n' +
          '\t  <tr>\n' +
          '        <td style="width: 10%;text-align:center">药物名称1</td>\n' +
          '        <td colspan=\'1\'style="text-align:center">{{drugName1}}</td>\n' +
          '\t\t<td colspan=\'1\'style="text-align:center">{{drugName2}}</td>\n' +
          '\t\t<td colspan=\'1\'style="text-align:center">{{drugName3}}</td>\n' +
          '\t\t<td colspan=\'1\'style="text-align:center">{{drugName4}}</td>\n' +
          '      </tr>\n' +
          '\t  <tr>\n' +
          '        <td style="width: 10%;text-align:center">用药情况1</td>\n' +
          '        <td colspan=\'1\'style="text-align:center">{{drugUsage1}}</td>\n' +
          '\t\t<td colspan=\'1\'style="text-align:center">{{drugUsage2}}</td>\n' +
          '\t\t<td colspan=\'1\'style="text-align:center">{{drugUsage3}}</td>\n' +
          '\t\t<td colspan=\'1\'style="text-align:center">{{drugUsage4}}</td>\n' +
          '      </tr>\n' +
          '\t  <tr>\n' +
          '        <td style="width: 10%;text-align:center">药物名称2</td>\n' +
          '        <td colspan=\'1\'style="text-align:center">{{othDrugName1}}</td>\n' +
          '\t\t<td colspan=\'1\'style="text-align:center">{{othDrugName2}}</td>\n' +
          '\t\t<td colspan=\'1\'style="text-align:center">{{othDrugName3}}</td>\n' +
          '\t\t<td colspan=\'1\'style="text-align:center">{{othDrugName4}}</td>\n' +
          '      </tr>\n' +
          '\t  <tr>\n' +
          '        <td style="width: 10%;text-align:center">用药情况2</td>\n' +
          '        <td colspan=\'1\'style="text-align:center">{{otherDrugU1}}</td>\n' +
          '\t\t<td colspan=\'1\'style="text-align:center">{{otherDrugU2}}</td>\n' +
          '\t\t<td colspan=\'1\'style="text-align:center">{{otherDrugU3}}</td>\n' +
          '\t\t<td colspan=\'1\'style="text-align:center">{{otherDrugU4}}</td>\n' +
          '      </tr>\n' +
          '\t  <tr>\n' +
          '        <td style="width: 10%;text-align:center">药物名称3</td>\n' +
          '        <td colspan=\'1\'style="text-align:center">{{othDrugNameSan1}}</td>\n' +
          '\t\t<td colspan=\'1\'style="text-align:center">{{othDrugNameSan2}}</td>\n' +
          '\t\t<td colspan=\'1\'style="text-align:center">{{othDrugNameSan3}}</td>\n' +
          '\t\t<td colspan=\'1\'style="text-align:center">{{othDrugNameSan4}}</td>\n' +
          '      </tr>\n' +
          '\t  <tr>\n' +
          '        <td style="width: 10%;text-align:center">用药情况3</td>\n' +
          '        <td colspan=\'1\'style="text-align:center">{{otherDrugUSan1}}</td>\n' +
          '\t\t<td colspan=\'1\'style="text-align:center">{{otherDrugUSan2}}</td>\n' +
          '\t\t<td colspan=\'1\'style="text-align:center">{{otherDrugUSan3}}</td>\n' +
          '\t\t<td colspan=\'1\'style="text-align:center">{{otherDrugUSan4}}</td>\n' +
          '      </tr>\n' +
          '\t  <tr>\n' +
          '        <td style="width: 10%;text-align:center">药物名称4</td>\n' +
          '        <td colspan=\'1\'style="text-align:center">{{othDrugNameSi1}}</td>\n' +
          '\t\t<td colspan=\'1\'style="text-align:center">{{othDrugNameSi2}}</td>\n' +
          '\t\t<td colspan=\'1\'style="text-align:center">{{othDrugNameSi3}}</td>\n' +
          '\t\t<td colspan=\'1\'style="text-align:center">{{othDrugNameSi4}}</td>\n' +
          '      </tr>\n' +
          '\t  <tr>\n' +
          '        <td style="width: 10%;text-align:center">用药情况4</td>\n' +
          '        <td colspan=\'1\'style="text-align:center">{{otherDrugUSi1}}</td>\n' +
          '\t\t<td colspan=\'1\'style="text-align:center">{{otherDrugUSi2}}</td>\n' +
          '\t\t<td colspan=\'1\'style="text-align:center">{{otherDrugUSi3}}</td>\n' +
          '\t\t<td colspan=\'1\'style="text-align:center">{{otherDrugUSi4}}</td>\n' +
          '      </tr>\n' +
          '\t  <tr>\n' +
          '        <td style="width: 10%;text-align:center">原因</td>\n' +
          '        <td colspan=\'1\'style="text-align:center">{{referralReason1}}</td>\n' +
          '\t\t<td colspan=\'1\'style="text-align:center">{{referralReason2}}</td>\n' +
          '\t\t<td colspan=\'1\'style="text-align:center">{{referralReason3}}</td>\n' +
          '\t\t<td colspan=\'1\'style="text-align:center">{{referralReason4}}</td>\n' +
          '\t  <tr>\n' +
          '        <td style="width: 10%;text-align:center">机构及科别</td>\n' +
          '        <td colspan=\'1\'style="text-align:center">{{organAndDiv1}}</td>\n' +
          '\t\t<td colspan=\'1\'style="text-align:center">{{organAndDiv2}}</td>\n' +
          '\t\t<td colspan=\'1\'style="text-align:center">{{organAndDiv3}}</td>\n' +
          '\t\t<td colspan=\'1\'style="text-align:center">{{organAndDiv4}}</td>\n' +
          '      </tr>\n' +
          '\t  <tr>\n' +
          '        <td style="width: 10%;text-align:center">下次随访日期</td>\n' +
          '        <td colspan=\'1\'style="text-align:center">{{dateNextFollow1}}</td>\n' +
          '\t\t<td colspan=\'1\'style="text-align:center">{{dateNextFollow2}}</td>\n' +
          '\t\t<td colspan=\'1\'style="text-align:center">{{dateNextFollow3}}</td>\n' +
          '\t\t<td colspan=\'1\'style="text-align:center">{{dateNextFollow4}}</td>\n' +
          '      </tr>\n' +
          '\t  <tr>\n' +
          '        <td style="width: 10%;text-align:center">随访医生签名</td>\n' +
          '        <td colspan=\'1\'style="text-align:center">{{docSignature1}}</td>\n' +
          '\t\t<td colspan=\'1\'style="text-align:center">{{docSignature2}}</td>\n' +
          '\t\t<td colspan=\'1\'style="text-align:center">{{docSignature3}}</td>\n' +
          '\t\t<td colspan=\'1\'style="text-align:center">{{docSignature4}}</td>\n' +
          '      </tr>\n' +
          '      \n' +
          '    </tbody>\n' +
          '  </table>\n' +
          '</div>\n' +
          '\n' +
          '</body>'
        str = str.replace(/{{name}}/, this.printItem2.name ? this.printItem2.name : '')
        str = str.replace(/{{number1}}/, this.printItem1.number ? this.printItem1.number : '')
        str = str.replace(/{{number2}}/, this.printItem2.number ? this.printItem2.number : '')
        str = str.replace(/{{number3}}/, this.printItem3.number ? this.printItem3.number : '')
        str = str.replace(/{{number4}}/, this.printItem4.number ? this.printItem4.number : '')
        str = str.replace(/{{follType1}}/, this.printItem1.follType ? this.printItem1.follType : '')
        str = str.replace(/{{follType2}}/, this.printItem2.follType ? this.printItem2.follType : '')
        str = str.replace(/{{follType3}}/, this.printItem3.follType ? this.printItem3.follType : '')
        str = str.replace(/{{follType4}}/, this.printItem4.follType ? this.printItem4.follType : '')
        str = str.replace(/{{follUpdate1}}/, this.printItem1.follUpdate ? this.printItem1.follUpdate : '')
        str = str.replace(/{{follUpdate2}}/, this.printItem2.follUpdate ? this.printItem2.follUpdate : '')
        str = str.replace(/{{follUpdate3}}/, this.printItem3.follUpdate ? this.printItem3.follUpdate : '')
        str = str.replace(/{{follUpdate4}}/, this.printItem4.follUpdate ? this.printItem4.follUpdate : '')
        str = str.replace(/{{id}}/, this.printItem2.id ? this.printItem2.id : '')
        str = str.replace(/{{symptoms}}/, this.printItem2.symptoms ? this.printItem2.symptoms : '')
        str = str.replace(/{{bloodPressure1}}/, this.printItem1.bloodPressure ? this.printItem1.bloodPressure : '')
        str = str.replace(/{{bloodPressure2}}/, this.printItem2.bloodPressure ? this.printItem2.bloodPressure : '')
        str = str.replace(/{{bloodPressure3}}/, this.printItem3.bloodPressure ? this.printItem3.bloodPressure : '')
        str = str.replace(/{{bloodPressure4}}/, this.printItem4.bloodPressure ? this.printItem4.bloodPressure : '')
        str = str.replace(/{{weight1}}/, this.printItem1.weight ? this.printItem1.weight : '')
        str = str.replace(/{{weight2}}/, this.printItem2.weight ? this.printItem2.weight : '')
        str = str.replace(/{{weight3}}/, this.printItem3.weight ? this.printItem3.weight : '')
        str = str.replace(/{{weight4}}/, this.printItem4.weight ? this.printItem4.weight : '')
        str = str.replace(/{{bmi1}}/, this.printItem1.bmi ? this.printItem1.bmi : '')
        str = str.replace(/{{bmi2}}/, this.printItem2.bmi ? this.printItem2.bmi : '')
        str = str.replace(/{{bmi3}}/, this.printItem3.bmi ? this.printItem3.bmi : '')
        str = str.replace(/{{bmi4}}/, this.printItem4.bmi ? this.printItem4.bmi : '')
        str = str.replace(/{{heartRate1}}/, this.printItem1.heartRate ? this.printItem1.heartRate : '')
        str = str.replace(/{{heartRate2}}/, this.printItem2.heartRate ? this.printItem2.heartRate : '')
        str = str.replace(/{{heartRate3}}/, this.printItem3.heartRate ? this.printItem3.heartRate : '')
        str = str.replace(/{{heartRate4}}/, this.printItem4.heartRate ? this.printItem4.heartRate : '')

        str = str.replace(/{{otherPhy1}}/, this.printItem1.otherPhy ? this.printItem1.otherPhy : '')
        str = str.replace(/{{otherPhy2}}/, this.printItem2.otherPhy ? this.printItem2.otherPhy : '')
        str = str.replace(/{{otherPhy3}}/, this.printItem3.otherPhy ? this.printItem3.otherPhy : '')
        str = str.replace(/{{otherPhy4}}/, this.printItem4.otherPhy ? this.printItem4.otherPhy : '')
        str = str.replace(/{{smokePer1}}/, this.printItem1.smokePer ? this.printItem1.smokePer : '')
        str = str.replace(/{{smokePer2}}/, this.printItem2.smokePer ? this.printItem2.smokePer : '')
        str = str.replace(/{{smokePer3}}/, this.printItem3.smokePer ? this.printItem3.smokePer : '')
        str = str.replace(/{{smokePer4}}/, this.printItem4.smokePer ? this.printItem4.smokePer : '')
        str = str.replace(/{{drinkPer1}}/, this.printItem1.drinkPer ? this.printItem1.drinkPer : '')
        str = str.replace(/{{drinkPer2}}/, this.printItem2.drinkPer ? this.printItem2.drinkPer : '')
        str = str.replace(/{{drinkPer3}}/, this.printItem3.drinkPer ? this.printItem3.drinkPer : '')
        str = str.replace(/{{drinkPer4}}/, this.printItem4.drinkPer ? this.printItem4.drinkPer : '')
        str = str.replace(/{{sport1}}/, this.printItem1.sport ? this.printItem1.sport : '')
        str = str.replace(/{{sport2}}/, this.printItem2.sport ? this.printItem2.sport : '')
        str = str.replace(/{{sport3}}/, this.printItem3.sport ? this.printItem3.sport : '')
        str = str.replace(/{{sport4}}/, this.printItem4.sport ? this.printItem4.sport : '')
        str = str.replace(/{{sport5}}/, this.printItem1.sport ? this.printItem1.sport : '')
        str = str.replace(/{{sport6}}/, this.printItem2.sport ? this.printItem2.sport : '')
        str = str.replace(/{{sport7}}/, this.printItem3.sport ? this.printItem3.sport : '')
        str = str.replace(/{{sport8}}/, this.printItem4.sport ? this.printItem4.sport : '')
        str = str.replace(/{{saltSituation1}}/, this.printItem1.saltSituation ? this.printItem1.saltSituation : '')
        str = str.replace(/{{saltSituation2}}/, this.printItem2.saltSituation ? this.printItem2.saltSituation : '')
        str = str.replace(/{{saltSituation3}}/, this.printItem3.saltSituation ? this.printItem3.saltSituation : '')
        str = str.replace(/{{saltSituation4}}/, this.printItem4.saltSituation ? this.printItem4.saltSituation : '')
        str = str.replace(/{{psyAdjust1}}/, this.printItem1.psyAdjust ? this.printItem1.psyAdjust : '')
        str = str.replace(/{{psyAdjust2}}/, this.printItem2.psyAdjust ? this.printItem2.psyAdjust : '')
        str = str.replace(/{{psyAdjust3}}/, this.printItem3.psyAdjust ? this.printItem3.psyAdjust : '')
        str = str.replace(/{{psyAdjust4}}/, this.printItem4.psyAdjust ? this.printItem4.psyAdjust : '')
        str = str.replace(/{{obeyMedical1}}/, this.printItem1.obeyMedical ? this.printItem1.obeyMedical : '')
        str = str.replace(/{{obeyMedical2}}/, this.printItem2.obeyMedical ? this.printItem2.obeyMedical : '')
        str = str.replace(/{{obeyMedical3}}/, this.printItem3.obeyMedical ? this.printItem3.obeyMedical : '')
        str = str.replace(/{{obeyMedical4}}/, this.printItem4.obeyMedical ? this.printItem4.obeyMedical : '')
        str = str.replace(/{{auxExamina1}}/, this.printItem1.auxExamina ? this.printItem1.auxExamina : '')
        str = str.replace(/{{auxExamina2}}/, this.printItem2.auxExamina ? this.printItem2.auxExamina : '')
        str = str.replace(/{{auxExamina3}}/, this.printItem3.auxExamina ? this.printItem3.auxExamina : '')
        str = str.replace(/{{auxExamina4}}/, this.printItem4.auxExamina ? this.printItem4.auxExamina : '')
        str = str.replace(/{{mediCompli1}}/, this.printItem1.mediCompli ? this.printItem1.mediCompli : '')
        str = str.replace(/{{mediCompli2}}/, this.printItem2.mediCompli ? this.printItem2.mediCompli : '')
        str = str.replace(/{{mediCompli3}}/, this.printItem3.mediCompli ? this.printItem3.mediCompli : '')
        str = str.replace(/{{mediCompli4}}/, this.printItem4.mediCompli ? this.printItem4.mediCompli : '')
        str = str.replace(/{{adverdrugReact1}}/, this.printItem1.adverdrugReact ? this.printItem1.adverdrugReact : '')
        str = str.replace(/{{adverdrugReact2}}/, this.printItem2.adverdrugReact ? this.printItem2.adverdrugReact : '')
        str = str.replace(/{{adverdrugReact3}}/, this.printItem3.adverdrugReact ? this.printItem3.adverdrugReact : '')
        str = str.replace(/{{adverdrugReact4}}/, this.printItem4.adverdrugReact ? this.printItem4.adverdrugReact : '')
        str = str.replace(/{{accessType1}}/, this.printItem1.accessType ? this.printItem1.accessType : '')
        str = str.replace(/{{accessType2}}/, this.printItem2.accessType ? this.printItem2.accessType : '')
        str = str.replace(/{{accessType3}}/, this.printItem3.accessType ? this.printItem3.accessType : '')
        str = str.replace(/{{accessType4}}/, this.printItem4.accessType ? this.printItem4.accessType : '')
        str = str.replace(/{{drugName1}}/, this.printItem1.drugName ? this.printItem1.drugName : '')
        str = str.replace(/{{drugName2}}/, this.printItem2.drugName ? this.printItem2.drugName : '')
        str = str.replace(/{{drugName3}}/, this.printItem3.drugName ? this.printItem3.drugName : '')
        str = str.replace(/{{drugName4}}/, this.printItem4.drugName ? this.printItem4.drugName : '')
        str = str.replace(/{{drugUsage1}}/, this.printItem1.drugUsage ? this.printItem1.drugUsage : '')
        str = str.replace(/{{drugUsage2}}/, this.printItem2.drugUsage ? this.printItem2.drugUsage : '')
        str = str.replace(/{{drugUsage3}}/, this.printItem3.drugUsage ? this.printItem3.drugUsage : '')
        str = str.replace(/{{drugUsage4}}/, this.printItem4.drugUsage ? this.printItem4.drugUsage : '')
        str = str.replace(/{{othDrugName1}}/, this.printItem1.othDrugName ? this.printItem1.othDrugName : '')
        str = str.replace(/{{othDrugName2}}/, this.printItem2.othDrugName ? this.printItem2.othDrugName : '')
        str = str.replace(/{{othDrugName3}}/, this.printItem3.othDrugName ? this.printItem3.othDrugName : '')
        str = str.replace(/{{othDrugName4}}/, this.printItem4.othDrugName ? this.printItem4.othDrugName : '')
        str = str.replace(/{{otherDrugU1}}/, this.printItem1.otherDrugU ? this.printItem1.otherDrugU : '')
        str = str.replace(/{{otherDrugU2}}/, this.printItem2.otherDrugU ? this.printItem2.otherDrugU : '')
        str = str.replace(/{{otherDrugU3}}/, this.printItem3.otherDrugU ? this.printItem3.otherDrugU : '')
        str = str.replace(/{{otherDrugU4}}/, this.printItem4.otherDrugU ? this.printItem4.otherDrugU : '')
        str = str.replace(/{{othDrugNameSan1}}/, this.printItem1.othDrugNameSan ? this.printItem1.othDrugNameSan : '')
        str = str.replace(/{{othDrugNameSan2}}/, this.printItem2.othDrugNameSan ? this.printItem2.othDrugNameSan : '')
        str = str.replace(/{{othDrugNameSan3}}/, this.printItem3.othDrugNameSan ? this.printItem3.othDrugNameSan : '')
        str = str.replace(/{{othDrugNameSan4}}/, this.printItem4.othDrugNameSan ? this.printItem4.othDrugNameSan : '')
        str = str.replace(/{{otherDrugUSan1}}/, this.printItem1.otherDrugUSan ? this.printItem1.otherDrugUSan : '')
        str = str.replace(/{{otherDrugUSan2}}/, this.printItem2.otherDrugUSan ? this.printItem2.otherDrugUSan : '')
        str = str.replace(/{{otherDrugUSan3}}/, this.printItem3.otherDrugUSan ? this.printItem3.otherDrugUSan : '')
        str = str.replace(/{{otherDrugUSan4}}/, this.printItem4.otherDrugUSan ? this.printItem4.otherDrugUSan : '')
        str = str.replace(/{{othDrugNameSi1}}/, this.printItem1.othDrugNameSi ? this.printItem1.othDrugNameSi : '')
        str = str.replace(/{{othDrugNameSi2}}/, this.printItem2.othDrugNameSi ? this.printItem2.othDrugNameSi : '')
        str = str.replace(/{{othDrugNameSi3}}/, this.printItem3.othDrugNameSi ? this.printItem3.othDrugNameSi : '')
        str = str.replace(/{{othDrugNameSi4}}/, this.printItem4.othDrugNameSi ? this.printItem4.othDrugNameSi : '')
        str = str.replace(/{{otherDrugUSi1}}/, this.printItem1.otherDrugUSi ? this.printItem1.otherDrugUSi : '')
        str = str.replace(/{{otherDrugUSi2}}/, this.printItem2.otherDrugUSi ? this.printItem2.otherDrugUSi : '')
        str = str.replace(/{{otherDrugUSi3}}/, this.printItem3.otherDrugUSi ? this.printItem3.otherDrugUSi : '')
        str = str.replace(/{{otherDrugUSi4}}/, this.printItem4.otherDrugUSi ? this.printItem4.otherDrugUSi : '')
        str = str.replace(/{{referralReason1}}/, this.printItem1.referralReason ? this.printItem1.referralReason : '')
        str = str.replace(/{{referralReason2}}/, this.printItem2.referralReason ? this.printItem2.referralReason : '')
        str = str.replace(/{{referralReason3}}/, this.printItem3.referralReason ? this.printItem3.referralReason : '')
        str = str.replace(/{{referralReason4}}/, this.printItem4.referralReason ? this.printItem4.referralReason : '')
        str = str.replace(/{{organAndDiv1}}/, this.printItem1.organAndDiv ? this.printItem1.organAndDiv : '')
        str = str.replace(/{{organAndDiv2}}/, this.printItem2.organAndDiv ? this.printItem2.organAndDiv : '')
        str = str.replace(/{{organAndDiv3}}/, this.printItem3.organAndDiv ? this.printItem3.organAndDiv : '')
        str = str.replace(/{{organAndDiv4}}/, this.printItem4.organAndDiv ? this.printItem4.organAndDiv : '')
        str = str.replace(/{{dateNextFollow1}}/, this.printItem1.dateNextFollow ? this.printItem1.dateNextFollow : '')
        str = str.replace(/{{dateNextFollow2}}/, this.printItem2.dateNextFollow ? this.printItem2.dateNextFollow : '')
        str = str.replace(/{{dateNextFollow3}}/, this.printItem3.dateNextFollow ? this.printItem3.dateNextFollow : '')
        str = str.replace(/{{dateNextFollow4}}/, this.printItem4.dateNextFollow ? this.printItem4.dateNextFollow : '')
        str = str.replace(/{{docSignature1}}/, this.printItem1.docSignature ? this.printItem1.docSignature : '')
        str = str.replace(/{{docSignature2}}/, this.printItem2.docSignature ? this.printItem2.docSignature : '')
        str = str.replace(/{{docSignature3}}/, this.printItem3.docSignature ? this.printItem3.docSignature : '')
        str = str.replace(/{{docSignature4}}/, this.printItem4.docSignature ? this.printItem4.docSignature : '')
        myWindow.document.write(str)
        myWindow.focus()
        myWindow.document.close()
        myWindow.print()
        return myWindow
      },
      conprint() {
        this.printUsers()
        const windows = this.printf(document.getElementById('print').innerHTML)
        windows.close()
      },
      handleCurrentChange(val) {
        this.page = val
        this.getUsers()
      },
      printUsers() {
        const para = {
          peopleId: this.editForm.peopleId
        }
        print(para).then(res => {
          this.printItem = res.data.users
          this.printItem1 = this.printItem[0]
          this.printItem2 = this.printItem[1]
          this.printItem3 = this.printItem[2]
          this.printItem4 = this.printItem[3]
        })
      },
      // 获取用户列表
      getUsers() {
        this.dialogFormVisible2 = false
        this.filters.follUpdate_s =
          !this.filters.follUpdate_s || this.filters.follUpdate_s === ''
            ? null
            : util.formatDate.format(new Date(this.filters.follUpdate_s), 'yyyy-MM-dd')
        this.filters.follUpdate_e =
          !this.filters.follUpdate_e || this.filters.follUpdate_e === ''
            ? null
            : util.formatDate.format(new Date(this.filters.follUpdate_e), 'yyyy-MM-dd')
        this.filters.dateNextFollow_s =
          !this.filters.dateNextFollow_s || this.filters.dateNextFollow_s === ''
            ? null
            : util.formatDate.format(new Date(this.filters.dateNextFollow_s), 'yyyy-MM-dd')
        this.filters.dateNextFollow_e =
          !this.filters.dateNextFollow_e || this.filters.dateNextFollow_e === ''
            ? null
            : util.formatDate.format(new Date(this.filters.dateNextFollow_e), 'yyyy-MM-dd')
        const para = {
          page: this.page,
          name: this.filters.name,
          follType: this.filters.follType,
          smokePer: this.filters.smokePer,
          sport: this.filters.sport,
          saltSituation: this.filters.saltSituation,
          psyAdjust: this.filters.psyAdjust,
          obeyMedical: this.filters.obeyMedical,
          mediCompli: this.filters.mediCompli,
          adverdrugReact: this.filters.adverdrugReact,
          accessType: this.filters.accessType,
          drugName: this.filters.drugName,
          organAndDiv: this.filters.organAndDiv,
          follUpdate_s: this.filters.follUpdate_s,
          follUpdate_e: this.filters.follUpdate_e,
          dateNextFollow_s: this.filters.dateNextFollow_s,
          dateNextFollow_e: this.filters.dateNextFollow_e

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

      findbtn() {
        this.dialogStatus = 'search'
        this.dialogFormVisible2 = true
        this.findForm = {
          name: '',
          follType: '',
          smokePer: '',
          sport: '',
          saltSituation: '',
          psyAdjust: '',
          obeyMedical: '',
          mediCompli: '',
          adverdrugReact: '',
          accessType: '',
          drugName: '',
          organAndDiv: '',
          follUpdate_s: '',
          follUpdate_e: '',
          dateNextFollow_s: '',
          dateNextFollow_e: ''
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
        this.dialogStatus = 'update'
        this.dialogFormVisible = true
        this.editForm = Object.assign({}, row)
        this.editForm.symptoms = this.editForm.symptoms.split(',')
        this.edit = true
      },
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
          name: '',
          id: '',
          number: '',
          peopleId: '',
          follUpdate: '',
          follType: '',
          symptoms: [],
          bloodPressure: '',
          weight: '',
          bmi: '',
          heartRate: '',
          otherPhy: '',
          smokePer: '',
          drinkPer: '',
          sport: '',
          saltSituation: '',
          psyAdjust: '',
          obeyMedical: '',
          auxExamina: '',
          mediCompli: '',
          adverdrugReact: '',
          accessType: '',
          drugName: '',
          drugUsage: '',
          othDrugName: '',
          otherDrugU: '',
          othDrugNameSan: '',
          otherDrugUSan: '',
          othDrugNameSi: '',
          otherDrugUSi: '',
          referralReason: '',
          organAndDiv: '',
          dateNextFollow: '',
          docSignature: '',
          creater: this.$store.getters.name,
          createDate: '',
          modifier: '1',
          modifyDate: ''
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
                para.follUpdate =
                  !para.follUpdate || para.follUpdate === ''
                    ? null
                    : util.formatDate.format(new Date(para.follUpdate), 'yyyy-MM-dd')
                para.dateNextFollow =
                  !para.dateNextFollow || para.dateNextFollow === ''
                    ? null
                    : util.formatDate.format(new Date(para.dateNextFollow), 'yyyy-MM-dd')
                para.createDate =
                  !para.createDate || para.createDate === ''
                    ? null
                    : util.formatDate.format(new Date(para.createDate), 'yyyy-MM-dd')
                para.modifyDate = new Date()
                para.modifyDate =
                  !para.modifyDate || para.modifyDate === ''
                    ? null
                    : util.formatDate.format(new Date(para.modifyDate), 'yyyy-MM-dd')
                para.symptoms = ''
                for (const itm of this.editForm.symptoms) {
                  if (itm !== '') {
                    para.symptoms += itm
                    para.symptoms += ','
                  }
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
            this.$confirm('确认提交吗？', '提示', {
              confirmButtonText: '确定',
              cancelButtonText: '取消'
            })
              .then(() => {
                const para = Object.assign({}, this.editForm)
                console.log(para)
                para.follUpdate =
                  !para.follUpdate || para.follUpdate === ''
                    ? null
                    : util.formatDate.format(new Date(para.follUpdate), 'yyyy-MM-dd')
                para.dateNextFollow =
                  !para.dateNextFollow || para.dateNextFollow === ''
                    ? null
                    : util.formatDate.format(new Date(para.dateNextFollow), 'yyyy-MM-dd')
                para.modifyDate =
                  !para.modifyDate || para.modifyDate === ''
                    ? null
                    : util.formatDate.format(new Date(para.modifyDate), 'yyyy-MM-dd')
                para.createDate = new Date()
                para.createDate =
                  !para.createDate || para.createDate === ''
                    ? null
                    : util.formatDate.format(new Date(para.createDate), 'yyyy-MM-dd')
                para.symptoms = ''
                for (const itm of this.editForm.symptoms) {
                  if (itm !== '') {
                    para.symptoms += itm
                    para.symptoms += ','
                  }
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
