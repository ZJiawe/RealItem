<template>
  <div class="app-container" >



    <div class="container" >
      <div class="form-box">

        <el-form ref="editForm" :model="editForm"  :rules="editFormRules"  >
          <el-row>
            <el-col :span="12">
              <el-form-item label="转诊负责人">
                <el-input v-model="editForm.chargePeople"  ></el-input>
              </el-form-item>
            </el-col>
          </el-row>

          <el-row>
            <el-col :span="12">
              <el-form-item label="联系方式">
                <el-input v-model="editForm.contact" ></el-input>
              </el-form-item>
            </el-col>
          </el-row>

          <el-row>
            <el-col :span="12">
              <el-form-item label="转诊报到处信息">
                <el-input v-model="editForm.referralInformation" ></el-input>
              </el-form-item>
            </el-col>
          </el-row>

          <el-row>
            <el-col :span="8">
              <el-form-item label="门诊资源" prop="transApartmentNum">
                <el-input v-model="editForm.transApartmentNum" ><template slot="append">个</template></el-input>
              </el-form-item>
            </el-col>



            <el-col :span="8">
              <el-form-item label="检查资源" prop="transInspectNum">
                <el-input v-model="editForm.transInspectNum" ><template slot="append">个</template></el-input>
              </el-form-item>
            </el-col>



            <el-col :span="8">
              <el-form-item label="住院资源" prop="transHospitalizationNum">
                <el-input v-model="editForm.transHospitalizationNum" ><template slot="append">个</template></el-input>
              </el-form-item>
            </el-col>
          </el-row>


          <el-row>
            <el-col :span="12">
              <el-form-item label="短信设置" label-width="100px">
                <el-radio-group v-model="editForm.messageSet">
                  <el-radio label="接收"  ></el-radio>
                  <el-radio label="不接收" ></el-radio>
                </el-radio-group>
              </el-form-item>
            </el-col>
          </el-row>

          <el-row>
            <el-col :span="12">
          <el-form-item label="转康复" label-width="100px">
            <el-radio-group v-model="editForm.transRecovery">
              <el-radio label="开放"  ></el-radio>
              <el-radio label="不开放" ></el-radio>
            </el-radio-group>
          </el-form-item>
            </el-col>
          </el-row>

          <el-form-item>
            <el-button type="primary"  v-if="buttons.TTR_C" @click="updateData">保存</el-button>
          </el-form-item>
        </el-form>
      </div>
    </div>

  </div>
</template>



<script>
  import {
    updateData,
    getList
  } from '@/api/transTreatset'
  export default {
    data: function() {
      return {
        buttons: {
          TTR_C: false
        },
        tableHeight: window.innerHeight - 210,
        editForm: {
          id: null,
          messageSet: '',
          chargePeople: '',
          contact: '',
          referralInformation: '',
          transApartmentNum: '',
          transInspectNum: '',
          transHospitalizationNum: '',
          transRecovery: ''
        },
        users: [],
        total: 0,
        page: 1,
        sels: [],
        editFormRules: {
          name: [{ required: true, message: '请输入姓名', trigger: 'blur' }],
          transApartmentNum: [{ pattern: /^[1-9]\d*|0$/, message: '只能为大于0的整数' }],
          transInspectNum: [{ pattern: /^[1-9]\d*|0$/, message: '只能为大于0的整数' }],
          transHospitalizationNum: [{ pattern: /^[1-9]\d*|0$/, message: '只能为大于0的整数' }]
        }
      }
    },
    methods: {

      getUsers() {
        const para = {
          page: this.page,
          size: 20,
          id: 1,
          filters: { id: 1 }
        }
        getList(para).then(res => {
          this.total = res.detailModelList.length
          this.users = res.detailModelList
          this.editForm.messageSet = this.users[0].messageSet
          this.editForm.chargePeople = this.users[0].chargePeople
          this.editForm.contact = this.users[0].contact
          this.editForm.referralInformation = this.users[0].referralInformation
          this.editForm.transApartmentNum = this.users[0].transApartmentNum
          this.editForm.transInspectNum = this.users[0].transInspectNum
          this.editForm.transHospitalizationNum = this.users[0].transHospitalizationNum
          this.editForm.transRecovery = this.users[0].transRecovery
        })
      },
      selsChange(sels) {
        this.sels = sels
      },
      handleCurrentChange(val) {
        this.page = val
        this.getUsers()
      },
      updateData() {
        this.$refs.editForm.validate(valid => {
          if (valid) {
            this.$confirm('确认提交吗？', '提示', {
              confirmButtonText: '确定',
              cancelButtonText: '取消'
            })
              .then(() => {
                const para = Object.assign({}, this.editForm)
                para.id = 1
                updateData(para).then(res => {
                  this.$message({
                    message: '提交成功',
                    type: 'success'
                  })
                  this.$refs['editForm'].resetFields()
                })
              })
              .catch(e => {
                console.log(e)
              })
          }
        })
      }
    },
    mounted() {
      this.buttons = this.getButton(this.buttons, this.$store.getters.buttons)
      this.getUsers()
    }
  }
</script>
