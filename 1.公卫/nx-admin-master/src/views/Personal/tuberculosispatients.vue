<template>
  <section class="app-container">

    <!--工具条-->
    <el-col :span="22" class="toolbar" style="padding-bottom: 0px;">
      <el-form :inline="true" :model="filters" @submit.native.prevent>
        <el-form-item>
          <el-input v-model="filters.name" placeholder="姓名"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" v-on:click="getUsers"style="background-color:#4db2ee;border-color:transparent" icon="el-icon-search">查询</el-button>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="handleAdd"icon="el-icon-circle-plus-outline">新增</el-button>
        </el-form-item>
      </el-form>
    </el-col>

    <!--列表-->
    <el-table :data="users":height="tableHeight"  highlight-current-row @selection-change="selsChange" border style="width: 100%;">
      <el-table-column  v-if="false" type="selection" width="55">
      </el-table-column>
      <el-table-column type="index" width="60">
      </el-table-column>
      <el-table-column prop="name" label="姓名" width="120">
      </el-table-column>
      <el-table-column prop="id" label="编号" width="120">
      </el-table-column>
      <el-table-column prop="followTime" label="随访时间" width="120">
      </el-table-column>
      <el-table-column prop="nextFollowtie" label="下次随访时间" min-width="120">
      </el-table-column>
      <el-table-column prop="evaluationDoctorsignature" label="评估医生" min-width="120">
      </el-table-column>

      <el-table-column label="操作" width="250">
        <template slot-scope="scope">
          <a size="14px" @click="handleEdit(scope.$index, scope.row)" style="color:#4DB2EE;margin-right:20px;">编辑</a>
          <a size="14px" @click="handleDel(scope.$index, scope.row)" style="color:#FB4141">删除</a>
        </template>
      </el-table-column>
    </el-table>

    <!--工具条-->
    <el-col :span="24" class="toolbar">
      <!--<el-button type="danger" @click="batchRemove" :disabled="this.sels.length===0">批量删除</el-button>  -->
      <el-pagination layout="prev, pager, next" @current-change="handleCurrentChange" :page-size="20" :total="total" style="float:right;" >
      </el-pagination>
    </el-col>

    <!--编辑界面-->
    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible" :close-on-click-modal="false" width="80%" :before-close="handleClose" center>
      <div id="print">
      <el-form :model="editForm" label-width="110px" :rules="editFormRules" ref="editForm">
        <div class="container">
        <el-tabs v-model="activeName">
    <el-tab-pane :label="`基本信息`" name="first">

      <el-row>
        <el-col :span="12">
            <el-form-item label="区域" prop="area">
              <el-cascader :options="options" v-model="editForm.area"style="width:330px" ></el-cascader>
            </el-form-item>
        </el-col>
        <el-col :span="12">
              <el-form-item label="身份证号" prop="peopleId">
                  <el-input v-model="editForm.peopleId" auto-complete="off" style="width:370px"></el-input>
            </el-form-item>
        </el-col>
      </el-row>

      <el-row>
        <el-col :span="12">
            <el-form-item label="姓名" prop="name">
                <el-input v-model="editForm.name" auto-complete="off"></el-input>
            </el-form-item>
        </el-col>
              <el-col :span="12">
                <el-form-item label="性别">
                <el-radio-group v-model="editForm.sex">
                  <el-radio label=男>男</el-radio>
                  <el-radio label=女>女</el-radio>
                  <el-radio label=未知性别>未知性别</el-radio>
                </el-radio-group>
                </el-form-item>
              </el-col>
      </el-row>

      <el-row>
        <el-col :span="12">
            <el-form-item label="年龄"   prop="age">
                  <el-input  v-model.number="editForm.age" auto-complete="off" ></el-input>
            </el-form-item>
              </el-col>
              <el-col :span="12" >
                <el-form-item label="民族">
                  <el-col :span="5">
                    <el-radio-group v-model="editForm.ethnic">
                      <el-radio label=汉>汉</el-radio>
                    </el-radio-group>
                  </el-col>
                  <el-col :span="19">
                    <el-input v-model="editForm.ethnic" auto-complete="off" placeholder="其他"></el-input>
                  </el-col>
                </el-form-item>
              </el-col>
      </el-row>

      <el-row>
        <el-col :span="12">
            <el-form-item label="联系方式">
              <el-input v-model="editForm.phone" auto-complete="off"></el-input>
            </el-form-item>
              </el-col>
              <el-col :span="12">
              <el-form-item label="责任医生">
                  <el-input v-model="editForm.responDoctor" auto-complete="off"></el-input>
            </el-form-item>
              </el-col>
      </el-row>
      <el-row>
        <el-col :span="12" >
              <el-form-item label="建档日期" prop="inputDate">
                <el-date-picker type="date"  v-model="editForm.inputDate" style="width: 100%;"></el-date-picker>
              </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="建档人">
            <el-input v-model="editForm.inputPerson" auto-complete="off"></el-input>
          </el-form-item>
        </el-col>
      </el-row>

      <el-row>
        <el-col :span="12" >
            <el-form-item label="最近随访">
                <el-date-picker type="date"  v-model="editForm.recentVisit" style="width: 100%;"></el-date-picker>
            </el-form-item>
            </el-col>
        <el-col :span="12" >
          <el-form-item label="随访次数">
            <el-input v-model="editForm.fNumber" ></el-input>
          </el-form-item>
        </el-col>
      </el-row>

      <el-row>
        <el-col :span="12">
          <el-form-item label="已被管理">
            <el-radio-group v-model="editForm.management">
              <el-radio label=是>是</el-radio>
              <el-radio label=否>否</el-radio>
            </el-radio-group>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="生存状态">
            <el-radio-group v-model="editForm.lifeState">
              <el-radio label=存活>治疗中</el-radio>
              <el-radio label=完成治疗>完成治疗</el-radio>
              <el-radio label=迁出>迁出</el-radio>
            </el-radio-group>
            <el-radio-group v-model="editForm.lifeState">
              <el-radio label=死亡>死亡</el-radio>
            </el-radio-group>
          </el-form-item>
        </el-col>
      </el-row>

      <el-row>
        <el-col :span="12">
          <el-form-item label="规则服药">
            <el-radio-group v-model="editForm.ruleMedicine">
              <el-radio label=是>是</el-radio>
              <el-radio label=否>否</el-radio>
            </el-radio-group>
          </el-form-item>
        </el-col>
      </el-row>
    </el-tab-pane>


          <el-tab-pane :label="`第一次随访入户记录`" name="second">
            <el-row>
              <el-form-item label="随访时间">
                <el-date-picker type="date" placeholder="选择日期" v-model="editForm.followTime"></el-date-picker>
              </el-form-item>
            </el-row>

            <el-row>
              <el-col :span="12">
              <el-form-item label="随访方式">
                  <el-radio-group v-model="editForm.followMethod">
                    <el-radio label=门诊>门诊</el-radio>
                    <el-radio label=家庭>家庭</el-radio>
                  </el-radio-group>
              </el-form-item>
              </el-col>
            </el-row>

        <el-form-item label="患者类型">
          <el-col :span="11">
            <el-radio-group v-model="editForm.patienType">
              <el-radio label=初治>初治</el-radio>
              <el-radio label=复治>复治</el-radio>
            </el-radio-group>
          </el-col>
        </el-form-item>

        <el-form-item label="痰菌情况">
          <el-col :span="11">
            <el-radio-group v-model="editForm.sputumStatus">
              <el-radio label=阳性>阳性</el-radio>
              <el-radio label=阴性>阴性</el-radio>
              <el-radio label=未查痰>未查痰</el-radio>
            </el-radio-group>
          </el-col>
        </el-form-item>
        <el-form-item label="耐药情况">
        <el-col :span="11">
          <el-radio-group v-model="editForm.drugStatus">
            <el-radio label=耐药>耐药</el-radio>
            <el-radio label=非耐药>非耐药</el-radio>
            <el-radio clabel=未检测>未检测</el-radio>
          </el-radio-group>
        </el-col>
      </el-form-item>

          <el-form-item label=症状及体征>
            <el-col :span="20">
              <el-checkbox-group v-model="editForm.symptomsSigns">
                <el-checkbox v-for="item in symptomsSigns" :key="item.value":name="type":label="item.label":value="item.value"></el-checkbox>
              </el-checkbox-group>
            </el-col>
            <el-col>
              <el-input v-model="editForm.otherSymptoms"  placeholder="其他症状"></el-input>
            </el-col>
          </el-form-item>



            <el-form-item label="化疗方案" >
                <el-input v-model="editForm.chemotherapyRegimen" ></el-input>
            </el-form-item>
            <el-form-item label=用法>
              <el-radio-group v-model="editForm.usages">
                <el-radio label=每日>每日</el-radio>
                <el-radio label=间歇>间歇</el-radio>
              </el-radio-group>
            </el-form-item>

            <el-form-item label="药品剂型">
              <el-col>
                <el-checkbox-group v-model="editForm.drugDosage">
                  <el-checkbox v-for="item in drugDosage" :key="item.value":name="type":label="item.label":value="item.value"></el-checkbox>
                </el-checkbox-group>
              </el-col>
            </el-form-item>

            <el-form-item label=督导人员选择>
              <el-col :span="24">
                <el-radio-group v-model="editForm.supervisorSelection">
                  <el-radio label=医生>医生</el-radio>
                  <el-radio label=家属>家属</el-radio>
                  <el-radio label=自服药>自服药</el-radio>
                  <el-radio label=其他>其他</el-radio>
                </el-radio-group>
              </el-col>
            </el-form-item>

            <el-form-item label=单独的居室>
              <el-col :span="24">
                <el-radio-group v-model="editForm.separateRoom">
                  <el-radio label=有>有</el-radio>
                  <el-radio label=无>无</el-radio>
                </el-radio-group>
              </el-col>
            </el-form-item>
            <el-form-item label=通风情况>
              <el-col :span="24">
                <el-radio-group v-model="editForm.ventilation">
                  <el-radio label=良好>良好</el-radio>
                  <el-radio label=一般>一般</el-radio>
                  <el-radio label=差>差</el-radio>
                </el-radio-group>
              </el-col>
            </el-form-item>

          <row>
            <el-col :span="12">
            <el-form-item label="吸烟">
              <el-input  v-model="editForm.smoking"  >
                <template slot="append">支/天</template>
              </el-input>
            </el-form-item>
              </el-col>
            <el-col :span="12">
              <el-form-item label="饮酒">
                <el-input  v-model="editForm.Drinking"  >
                  <template slot="append">两/天</template>
                </el-input>
              </el-form-item>
                </el-col>
          </row>

          <row>
            <el-col :span="12">
            <el-form-item label="取药时间">
              <el-date-picker type="date" v-model="editForm.takemedicineTime" style="width: 330px"></el-date-picker>
            </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item  label="取药地点">
                  <el-input  v-model="editForm.takemedicinePlace" ></el-input>
              </el-form-item>
                </el-col>
          </row>
          </el-tab-pane>

            <el-tab-pane :label="`健康教育及培训`" name="third" >
            <el-form-item label=服药记录卡的填写 label-width="180px">
              <el-col :span="10">
                <el-radio-group v-model="editForm.medicationRecordcard">
                  <el-radio label=掌握>掌握</el-radio>
                  <el-radio label=未掌握>未掌握</el-radio>
                </el-radio-group>
              </el-col>
              <el-col :span="12">
              <el-form-item label=服药方法及药品存放 label-width="180px">
                  <el-radio-group v-model="editForm.medicationmethodDrugstorage">
                    <el-radio label=掌握>掌握</el-radio>
                    <el-radio label=未掌握>未掌握</el-radio>
                  </el-radio-group>
              </el-form-item>
                </el-col>
              </el-form-item>




            <el-form-item label=肺结核治疗疗程 label-width="180px">
              <el-col :span="10">
                <el-radio-group v-model="editForm.treatmentTuberculosis">
                  <el-radio label=掌握>掌握</el-radio>
                  <el-radio label=未掌握>未掌握</el-radio>
                </el-radio-group>
              </el-col>
              <el-col :span="12">
              <el-form-item label=不规律服药危害 label-width="180px">

                  <el-radio-group v-model="editForm.irregularMedication">
                    <el-radio label=掌握>掌握</el-radio>
                    <el-radio label=未掌握>未掌握</el-radio>
                  </el-radio-group>
              </el-form-item>
                </el-col>
              </el-form-item>




            <el-form-item label=服药后不良反应及处理 label-width="180px">
              <el-col :span="10">
                <el-radio-group v-model="editForm.adverseReactions">
                  <el-radio label=掌握>掌握</el-radio>
                  <el-radio label=未掌握>未掌握</el-radio>
                </el-radio-group>
              </el-col>
              <el-col :span="12">
              <el-form-item label=治疗期间复诊查痰 label-width="180px">

                  <el-radio-group v-model="editForm.review">
                    <el-radio label=掌握>掌握</el-radio>
                    <el-radio label=未掌握>未掌握</el-radio>
                  </el-radio-group>
              </el-form-item>
                </el-col>
              </el-form-item>





            <el-form-item label=外出期间如何坚持服药 label-width="180px">
              <el-col :span="10">
                <el-radio-group v-model="editForm.howtakeMedicin">
                  <el-radio label=掌握>掌握</el-radio>
                  <el-radio label=未掌握>未掌握</el-radio>
                </el-radio-group>
              </el-col>
              <el-col :span="12">
              <el-form-item label=生活习惯及注意事项 label-width="180px">

                  <el-radio-group v-model="editForm.habitsPrecautions">
                    <el-radio label=掌握>掌握</el-radio>
                    <el-radio label=未掌握>未掌握</el-radio>
                  </el-radio-group>
              </el-form-item>
                </el-col>
              </el-form-item>





            <el-form-item label=密切接触者检查 label-width="180px">
              <el-col :span="24">
                <el-radio-group v-model="editForm.closeCheck">
                  <el-radio label=掌握>掌握</el-radio>
                  <el-radio label=未掌握>未掌握</el-radio>
                </el-radio-group>
              </el-col>
            </el-form-item>





        <el-form-item label="下次随访时间" label-width="180px">
          <el-col :span="10">
            <el-date-picker type="date"  v-model="editForm.nextFollowtie" style="width: 50%;"></el-date-picker>
          </el-col>
          <el-col :span="12" >
            <el-form-item label="评估医生签名" label-width="180px">
              <el-input  v-model="editForm.evaluationDoctorsignature" auto-complete="off" ></el-input>
            </el-form-item>
          </el-col>
        </el-form-item>
          </el-tab-pane>
        </el-tabs>
        </div>
      </el-form>
      </div>
      <div slot="footer" class="dialog-footer">
        <el-button @click.native="dialogFormVisible=false">取消</el-button>
        <el-button v-if="dialogStatus=='create'" type="primary" @click="createData">添加</el-button>
        <el-button v-else type="primary" @click="updateData">修改</el-button>
        <el-button @click="conprint()">打印</el-button>
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
  } from '@/api/tuberculosispatients'

  export default {
    data() {
      return {
        options: [
          {
            value: '广东省',
            label: '广东省',
            children: [
              {
                value: '广州市',
                label: '广州市',
                children: [
                  {
                    value: '天河区',
                    label: '天河区'
                  },
                  {
                    value: '海珠区',
                    label: '海珠区'
                  },
                  {
                    value: '花都区',
                    label: '花都区'
                  },
                  {
                    value: '南沙区',
                    label: '南沙区'
                  },
                  {
                    value: '番禺区',
                    label: '番禺区'
                  },
                  {
                    value: '白云区',
                    label: '白云区'
                  },
                  {
                    value: '黄浦区',
                    label: '黄浦区'
                  }
                ]
              },
              {
                value: '东莞市',
                label: '东莞市',
                children: [
                  {
                    value: '长安镇',
                    label: '长安镇'
                  },
                  {
                    value: '虎门镇',
                    label: '虎门镇'
                  }
                ]
              }
            ]
          },
          {
            value: '福建省',
            label: '福建省',
            children: [
              {
                value: '福州市',
                label: '福州市',
                children: [
                  {
                    value: '台江区',
                    label: '台江区'
                  },
                  {
                    value: '仓山区',
                    label: '仓山区'
                  },
                  {
                    value: '马尾区',
                    label: '马尾区'
                  },
                  {
                    value: '晋安区',
                    label: '晋安区'
                  }
                ]
              },
              {
                value: '厦门市',
                label: '厦门市',
                children: [
                  {
                    value: '湖里区',
                    label: '湖里区'
                  },
                  {
                    value: '思明区',
                    label: '思明区',
                    children: [
                      {
                        value: '莲前街道',
                        label: '莲前街道'
                      }
                    ]
                  },
                  {
                    value: '集美区',
                    label: '集美区'
                  },
                  {
                    value: '海沧区',
                    label: '海沧区'
                  },
                  {
                    value: '翔安区',
                    label: '翔安区'
                  },
                  {
                    value: '同安区',
                    label: '同安区'
                  }
                ]
              }
            ]
          },
          {
            value: '北京市',
            label: '北京市',
            children: [
              {
                value: '北京市',
                label: '北京市',
                children: [
                  {
                    value: '朝阳区',
                    label: '朝阳区'
                  }
                ]
              }
            ]
          },
          {
            value: '浙江省',
            label: '浙江省',
            children: [
              {
                value: '杭州市',
                label: '杭州市',
                children: [
                  {
                    value: '西湖区',
                    label: '西湖区'
                  }
                ]
              }
            ]
          }
        ],
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
        dialogStatus: '',
        tableHeight: window.innerHeight - 260,
        activeName: 'first',
        textMap: {
          update: '编辑',
          create: '新增'
        },
        dialogFormVisible: false,
        filters: {
          name: '',
          followTime_s: null,
          followTime_e: null
        },
        users: [],
        total: 0,
        page: 1,
        sels: [], // 列表选中列
        editFormRules: {
          area: [{ required: true, message: '请输入区域', trigger: 'blur' }],
          name: [{ required: true, message: '请输入姓名', trigger: 'blur' }],
          peopleId: [
            { required: true, message: '请输入身份证号码', trigger: 'blur' }
          ],
          inputDate: [{ required: true, message: '建档日期不能为空', trigger: 'blur' }],
          age: [
            { type: 'number', message: '年龄必须为数字值', trigger: 'blur' }
          ]
        },

        // 编辑界面数据
        editForm: {
          name: '',
          followTime: null,
          followMethod: '',
          patienType: '',
          sputumStatus: '',
          drugStatus: '',
          symptomsSigns: [],
          otherSymptoms: '',
          chemotherapyRegimen: '',
          usages: '',
          drugDosage: [],
          supervisorSelection: '',
          separateRoom: '',
          ventilation: '',
          smoking: '',
          Drinking: '',
          takemedicineTime: null,
          takemedicinePlace: '',
          medicationRecordcard: '',
          medicationmethodDrugstorage: '',
          treatmentTuberculosis: '',
          irregularMedication: '',
          adverseReactions: '',
          review: '',
          howtakeMedicin: '',
          habitsPrecautions: '',
          closeCheck: '',
          nextFollowtie: null,
          evaluationDoctorsignature: '',
          // 查询界面字段
          age: null,
          ethnic: '',
          area: [],
          followTime_s: null,
          followTime_e: null,
          inputDate_s: null,
          inputDate_e: null,
          inputPerson: '',
          peopleId: '',
          phone: '',
          responDoctor: '',
          lifeState: ''

        },

        addFormVisible: false, // 新增界面是否显示
        addFormRules: {
          area: [{ required: true, message: '请输入区域', trigger: 'blur' }],
          name: [{ required: true, message: '请输入姓名', trigger: 'blur' }],
          peopleId: [
            { required: true, message: '请输入身份证号码', trigger: 'blur' }
          ],
          inputDate: [{ required: true, message: '建档日期不能为空', trigger: 'blur' }],
          age: [
            { type: 'number', message: '年龄必须为数字值', trigger: 'blur' }
          ]
        }
      }
    },
    methods: {
      // 性别显示转换
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
          '    <h2 style="width:794px;text-align:center"> 肺结核患者第一次入户随访记录表</h2> \n' +
          '    <span style="margin-left:34px;">姓名:</span>\n' +
          '    <span style="margin-left:34px;">{{name}}</span>\n' +
          '\t<span style="margin-left:510px;">编号:</span>\n' +
          '    <span style="margin-left:5px;">{{id}}</span> \n' +
          '    </div>\n' +
          '  <table id="table"  border="1"  cellspacing="0" bordercolor="#000000" style="width:730px;margin-left:30px;margin-top:5px;border-collapse:collapse;"  width = "80%">\n' +
          '    <tbody>\n' +
          '      <tr>\n' +
          '        <td style="width: 10%;text-align:center">随访时间</td>\n' +
          '        <td colspan=\'4\'>{{followTime}}</td>\n' +
          '      </tr>\n' +
          '      <tr>\n' +
          '        <td style="word-wrap:break-word;width: 10%;text-align:center">随访方式</td>\n' +
          '        <td colspan=\'1\'>{{followMethod}}</td>\n' +
          '        <td style="width: 19%;text-align:center">患者类型</td>\n' +
          '        <td colspan=\'2\'>{{patienType}}</td>\n' +
          '      </tr>\n' +
          '      <tr>\n' +
          '        <td style="word-wrap:break-word;width: 10%;text-align:center">痰菌情况</td>\n' +
          '        <td colspan=\'1\'>{{sputumStatus}}</td>\n' +
          '        <td style="width: 19%;text-align:center">耐药情况</td>\n' +
          '        <td colspan=\'2\'>{{drugStatus}}</td>\n' +
          '      </tr>\n' +
          '\t  \n' +
          '\t   <tr>\n' +
          '        <td rowspan=\'3\' style="word-wrap:break-word;width: 10%;text-align:center">症状及体征</td>\n' +
          '        <td colspan=\'4\'>{{symptomsSigns}}</td>   \n' +
          '      </tr>\n' +
          '\t \n' +
          '\t<td rowspan=\'2\'>其他</td>\n' +
          '\t<td rowspan=\'2\' colspan=\'3\'>{{otherSymptoms}}</td>\n' +
          '\t <tr></tr>\n' +
          '\t  \n' +
          '     <tr>\n' +
          '        <td rowspan=\'3\' style="word-wrap:break-word;width: 10%;text-align:center">用药</td>\n' +
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
          '\t  \n' +
          '\t  <tr>\n' +
          '        <td style="width: 10%;text-align:center">督导人员选择</td>\n' +
          '        <td colspan=\'4\'>{{supervisorSelection}}</td>\n' +
          '      </tr>\n' +
          '\t  \n' +
          '\t  <tr>\n' +
          '        <td rowspan=\'2\' style="word-wrap:break-word;width: 10%;text-align:center">家庭居住环境评估</td>\n' +
          '        <td >单独的居室</td>\n' +
          '        <td colspan=\'3\'>{{separateRoom}}</td>\n' +
          '      </tr>\n' +
          '\t  <tr>\n' +
          '\t\t<td >通风情况</td>\n' +
          '\t\t<td colspan=\'3\'>{{ventilation}}</td> \n' +
          '\t  </tr>\n' +
          '\t  \n' +
          '\t   <tr>\n' +
          '        <td rowspan=\'2\' style="word-wrap:break-word;width: 10%;text-align:center">生活方式评估</td>\n' +
          '        <td >吸烟</td>\n' +
          '        <td colspan=\'3\'>{{smoking}} 支/天</td>\n' +
          '      </tr>\n' +
          '\t  <tr>\n' +
          '\t\t<td >饮酒</td>\n' +
          '\t\t<td colspan=\'3\'>{{Drinking}} 两/天</td> \n' +
          '\t  </tr>\n' +
          '\t  \n' +
          '\t  <tr>\n' +
          '        <td rowspan=\'10\' style="word-wrap:break-word;width: 10%;text-align:center">健康教育及培训</td>\n' +
          '        <td colspan=\'2\' >取药地点、时间</td>\n' +
          '        <td  colspan=\'2\'>地点：{{takemedicinePlace}}</br>时间：{{takemedicineTime}}</td>\n' +
          '      </tr>\n' +
          '\t  <tr>\n' +
          '\t\t<td  colspan=\'2\'>服药记录卡的填写</td>\n' +
          '\t\t<td  colspan=\'2\'>{{medicationRecordcard}}</td> \n' +
          '\t  </tr>\n' +
          '\t  \n' +
          '\t   <tr>\n' +
          '        <td  colspan=\'2\'>服药方法及药品存放</td>\n' +
          '        <td  colspan=\'2\'>{{medicationmethodDrugstorage}}</td>\n' +
          '      </tr>\n' +
          '\t  <tr>\n' +
          '\t\t<td  colspan=\'2\'>肺结核治疗疗程</td>\n' +
          '\t\t<td  colspan=\'2\'>{{treatmentTuberculosis}}</td> \n' +
          '\t  </tr>\n' +
          '\t   <tr>\n' +
          '\t\t<td  colspan=\'2\'>不规律服药危害</td>\n' +
          '\t\t<td  colspan=\'2\'>{{irregularMedication}}</td> \n' +
          '\t  </tr>\n' +
          '\t   <tr>\n' +
          '\t\t<td  colspan=\'2\'>服药后不良反应及处理</td>\n' +
          '\t\t<td  colspan=\'2\'>{{adverseReactions}}</td> \n' +
          '\t  </tr>\n' +
          '\t   <tr>\n' +
          '\t\t<td  colspan=\'2\'>治疗期间复诊查痰</td>\n' +
          '\t\t<td  colspan=\'2\'>{{review}}</td> \n' +
          '\t  </tr>\n' +
          '\t   <tr>\n' +
          '\t\t<td  colspan=\'2\'>外出期间如何坚持服药</td>\n' +
          '\t\t<td  colspan=\'2\'>{{howtakeMedicin}}</td> \n' +
          '\t  </tr>\n' +
          '\t   <tr>\n' +
          '\t\t<td  colspan=\'2\'>生活习惯及注意事项</td>\n' +
          '\t\t<td  colspan=\'2\'>{{habitsPrecautions}}</td> \n' +
          '\t  </tr>\n' +
          '\t   <tr>\n' +
          '\t\t<td  colspan=\'2\'>密切接触者检查</td>\n' +
          '\t\t<td  colspan=\'2\'>{{closeCheck}}</td> \n' +
          '\t  </tr>\n' +
          '\t    <tr>\n' +
          '        <td style="width: 10%;text-align:center">下次随访时间</td>\n' +
          '        <td colspan=\'4\'>{{nextFollowtie}}</td>\n' +
          '      </tr> \n' +
          '\t  <tr>\n' +
          '        <td style="width: 10%;text-align:center">评估医生签名</td>\n' +
          '        <td colspan=\'4\'>{{evaluationDoctorsignature}}</td>\n' +
          '      </tr>\n' +
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
        str = str.replace(/{{followTime}}/, this.editForm.followTime ? this.editForm.followTime : '')
        str = str.replace(/{{followMethod}}/, this.editForm.followMethod ? this.editForm.followMethod : '')
        str = str.replace(/{{patienType}}/, this.editForm.patienType ? this.editForm.patienType : '')
        str = str.replace(/{{sputumStatus}}/, this.editForm.sputumStatus ? this.editForm.sputumStatus : '')
        str = str.replace(/{{drugStatus}}/, this.editForm.drugStatus ? this.editForm.drugStatus : '')
        str = str.replace(/{{symptomsSigns}}/, this.editForm.symptomsSigns ? this.editForm.symptomsSigns : '')
        str = str.replace(/{{otherSymptoms}}/, this.editForm.otherSymptoms ? this.editForm.otherSymptoms : '')
        str = str.replace(/{{chemotherapyRegimen}}/, this.editForm.chemotherapyRegimen ? this.editForm.chemotherapyRegimen : '')
        str = str.replace(/{{usages}}/, this.editForm.usages ? this.editForm.usages : '')
        str = str.replace(/{{drugDosage}}/, this.editForm.drugDosage ? this.editForm.drugDosage : '')
        str = str.replace(/{{supervisorSelection}}/, this.editForm.supervisorSelection ? this.editForm.supervisorSelection : '')
        str = str.replace(/{{separateRoom}}/, this.editForm.separateRoom ? this.editForm.separateRoom : '')
        str = str.replace(/{{ventilation}}/, this.editForm.ventilation ? this.editForm.ventilation : '')
        str = str.replace(/{{smoking}}/, this.editForm.smoking ? this.editForm.smoking : '')
        str = str.replace(/{{Drinking}}/, this.editForm.Drinking ? this.editForm.Drinking : '')
        str = str.replace(/{{takemedicineTime}}/, this.editForm.takemedicineTime ? this.editForm.takemedicineTime : '')
        str = str.replace(/{{takemedicinePlace}}/, this.editForm.takemedicinePlace ? this.editForm.takemedicinePlace : '')
        str = str.replace(/{{medicationRecordcard}}/, this.editForm.medicationRecordcard ? this.editForm.medicationRecordcard : '')
        str = str.replace(/{{medicationmethodDrugstorage}}/, this.editForm.medicationmethodDrugstorage ? this.editForm.medicationmethodDrugstorage : '')
        str = str.replace(/{{treatmentTuberculosis}}/, this.editForm.treatmentTuberculosis ? this.editForm.treatmentTuberculosis : '')
        str = str.replace(/{{irregularMedication}}/, this.editForm.irregularMedication ? this.editForm.irregularMedication : '')
        str = str.replace(/{{adverseReactions}}/, this.editForm.adverseReactions ? this.editForm.adverseReactions : '')
        str = str.replace(/{{review}}/, this.editForm.review ? this.editForm.review : '')
        str = str.replace(/{{howtakeMedicin}}/, this.editForm.howtakeMedicin ? this.editForm.howtakeMedicin : '')
        str = str.replace(/{{habitsPrecautions}}/, this.editForm.habitsPrecautions ? this.editForm.habitsPrecautions : '')
        str = str.replace(/{{closeCheck}}/, this.editForm.closeCheck ? this.editForm.closeCheck : '')
        str = str.replace(/{{nextFollowtie}}/, this.editForm.nextFollowtie ? this.editForm.nextFollowtie : '')
        str = str.replace(/{{evaluationDoctorsignature}}/, this.editForm.evaluationDoctorsignature ? this.editForm.evaluationDoctorsignature : '')

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
        this.filters.followTime_s =
          !this.filters.followTime_s || this.filters.followTime_s === ''
            ? null
            : util.formatDate.format(new Date(this.filters.followTime_s), 'yyyy-MM-dd')
        this.filters.followTime_e =
          !this.filters.followTime_e || this.filters.followTime_e === ''
            ? null
            : util.formatDate.format(new Date(this.filters.followTime_e), 'yyyy-MM-dd')

        const para = {
          page: this.page,
          name: this.filters.name,
          followTime_s: this.filters.followTime_s,
          followTime_e: this.filters.followTime_e
        }
        getUserListPage(para).then(res => {
          this.total = res.data.total
          this.users = res.data.hhh
        })
      },
      // 删除
      handleDel(index, row) {
        this.$confirm('确认删除该记录吗?', '提示', {
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
      // 显示编辑界面
      handleEdit(index, row) {
        this.dialogStatus = 'update'
        this.dialogFormVisible = true
        this.editForm = Object.assign({}, row)
        this.editForm.drugDosage = this.editForm.drugDosage.split(',')
        this.editForm.symptomsSigns = this.editForm.symptomsSigns.split(',')
        //
        this.editForm.area = this.editForm.area.split('/')
      },
      // 显示新增界面
      handleAdd() {
        this.dialogStatus = 'create'
        this.dialogFormVisible = true
        this.editForm = {
          name: '',
          followTime: null,
          followMethod: '',
          patienType: '',
          sputumStatus: '',
          drugStatus: '',
          symptomsSigns: [],
          otherSymptoms: '',
          chemotherapyRegimen: '',
          usages: '',
          drugDosage: [],
          supervisorSelection: '',
          separateRoom: '',
          ventilation: '',
          smoking: '',
          Drinking: '',
          takemedicineTime: null,
          takemedicinePlace: '',
          medicationRecordcard: '',
          medicationmethodDrugstorage: '',
          treatmentTuberculosis: '',
          irregularMedication: '',
          adverseReactions: '',
          review: '',
          howtakeMedicin: '',
          habitsPrecautions: '',
          closeCheck: '',
          nextFollowtie: null,
          evaluationDoctorsignature: '',
          // 查询界面字段
          age: null,
          ethnic: '',
          area: [],
          followTime_s: null,
          followTime_e: null,
          inputDate_s: null,
          inputDate_e: null,
          inputPerson: '',
          peopleId: '',
          phone: '',
          responDoctor: '',
          lifeState: ''
        }
      },
      updateData() {
        this.$refs.editForm.validate(valid => {
          if (valid) {
            this.$confirm('确认提交吗？', '提示', {})
              .then(() => {
                const para = Object.assign({}, this.editForm)
                para.followTime =
                  !para.followTime || para.followTime === ''
                    ? null
                    : util.formatDate.format(new Date(para.followTime), 'yyyy-MM-dd')
                para.takemedicineTime = new Date()
                para.takemedicineTime =
                  !para.takemedicineTime || para.takemedicineTime === ''
                    ? null
                    : util.formatDate.format(new Date(para.takemedicineTime), 'yyyy-MM-dd')
                para.nextFollowtie = new Date()
                para.nextFollowtie =
                  !para.nextFollowtie || para.nextFollowtie === ''
                    ? null
                    : util.formatDate.format(new Date(para.nextFollowtie), 'yyyy-MM-dd')
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

                // 查询界面字段处理
                para.inputDate =
                  !para.inputDate || para.inputDate === ''
                    ? null
                    : util.formatDate.format(new Date(para.inputDate), 'yyyy-MM-dd')

                para.recentVisit = new Date()
                para.recentVisit =
                  !para.recentVisit || para.recentVisit === ''
                    ? null
                    : util.formatDate.format(new Date(para.recentVisit), 'yyyy-MM-dd')

                para.area = ''
                for (const itm of this.editForm.area) {
                  para.area += itm
                  para.area += '/'
                }
                para.area = para.area.substr(0, para.area.length - 1)
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
                this.editForm.id = (parseInt(Math.random() * 100)).toString() // mock a id
                const para = Object.assign({}, this.editForm)
                console.log(para)
                para.followTime =
                  !para.followTime || para.followTime === ''
                    ? null
                    : util.formatDate.format(new Date(para.followTime), 'yyyy-MM-dd')
                para.takemedicineTime = new Date()
                para.takemedicineTime =
                  !para.takemedicineTime || para.takemedicineTime === ''
                    ? null
                    : util.formatDate.format(new Date(para.takemedicineTime), 'yyyy-MM-dd')
                para.nextFollowtie = new Date()
                para.nextFollowtie =
                  !para.nextFollowtie || para.nextFollowtie === ''
                    ? null
                    : util.formatDate.format(new Date(para.nextFollowtie), 'yyyy-MM-dd')
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

                // 查询界面字段处理
                para.inputDate =
                  !para.inputDate || para.inputDate === ''
                    ? null
                    : util.formatDate.format(new Date(para.inputDate), 'yyyy-MM-dd')

                para.recentVisit = new Date()
                para.recentVisit =
                  !para.recentVisit || para.recentVisit === ''
                    ? null
                    : util.formatDate.format(new Date(para.recentVisit), 'yyyy-MM-dd')

                para.area = ''
                for (const itm of this.editForm.area) {
                  para.area += itm
                  para.area += '/'
                }
                para.area = para.area.substr(0, para.area.length - 1)
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
