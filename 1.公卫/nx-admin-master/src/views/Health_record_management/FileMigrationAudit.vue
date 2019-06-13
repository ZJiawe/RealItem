<template>
  <section class="app-container">


    <!--列表-->
    <el-table :data="users" :height="tableHeight" border highlight-current-row @selection-change="selsChange" style="width: 100%;margin-left: 20px;">
      <el-table-column type="index"  label="序号" width="60">
    </el-table-column>
      <el-table-column prop="peopleId"  label="身份证号码" min-width="200">
      </el-table-column>
      <el-table-column prop="houseAddress1" label="户籍迁出地" min-width="230">
      </el-table-column>
      <el-table-column prop="houseAddress2" label="户籍申请迁入" min-width="230" >
      </el-table-column>

      <el-table-column label="操作" width="200">
        <template slot-scope="scope">
          <a size="14px" v-if="buttons.Audit" @click="handleEdit(scope.$index, scope.row) "
             style="color:#4DB2EE;margin-right:10px;">审核</a>
          <a size="14px" v-if="buttons.Audit_D" @click="handleDel(scope.$index, scope.row) "
             style="color:#FB4141;margin-right:10px;">删除</a>
        </template>
      </el-table-column>
    </el-table>



    <!--编辑界面-->
    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible" :close-on-click-modal="false" center>
      <el-form :model="editForm" label-width="130px" :rules="editFormRules" ref="editForm" disabled >
        <el-form-item label="身份证号码" prop="peopleId">
          <el-input v-model="editForm.peopleId" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="户籍迁出地">
          <el-input type="textarea" v-model="editForm.houseAddress1"></el-input>
        </el-form-item>
        <el-form-item label="户籍申请迁入">
          <el-input type="textarea" v-model="editForm.houseAddress2"></el-input>
        </el-form-item>
        <el-form-item label="户籍迁移原因">
          <el-input type="textarea" v-model="editForm.migrationReason"></el-input>
        </el-form-item>
        <el-form-item label="申请迁移时间">
          <el-input type="textarea" v-model="editForm.createDate"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click.native="dialogFormVisible=false">取消</el-button>
        <el-button v-if="dialogStatus=='create'" type="primary" @click="createData">添加</el-button>
        <el-button v-else type="primary" @click="updateData(index,row)">同意变更</el-button>
      </div>
    </el-dialog>
  </section>
</template>

<script>
  import {
    updateData,
    getList
  } from '@/api/Perinfor'
  import {
    getList0,
    removeData0
  } from '@/api/PerinforFileEmigrate'

  export default {
    data() {
      return {
        buttons: {
          Audit: false,
          Audit_D: false
        },
        tableHeight: window.innerHeight - 220,
        dialogStatus: '',
        textMap: {
          update: '户籍档案变更',
          create: '户籍档案变更添加'
        },
        dialogFormVisible: false,
        filters: {
          name: ''
        },
        users: [],
        total: 0,
        page: 1,
        sels: [],
        editFormRules: {
          name: [{ required: true, message: '请输入姓名', trigger: 'blur' }]
        },
        editForm: {
          id: '0',
          peopleId: '',
          houseAddress: '',
          houseAddress1: '',
          houseAddress2: ''
        },

        addFormVisible: false,
        addFormRules: {
          name: [{ required: true, message: '请输入姓名', trigger: 'blur' }]
        }
      }
    },
    methods: {
      handleCurrentChange(val) {
        this.page = val
        this.getUsers2()
      },
      getUsers2() {
        const para = {
          page: this.page,
          size: 20,
          filters: { peopleId: this.filters.peopleId }
        }
        getList0(para).then(res => {
          this.total = res.detailModelList.length
          this.users = res.detailModelList
        })
      },
      handleEdit(index, row) {
        this.dialogStatus = 'update'
        this.dialogFormVisible = true
        this.editForm = Object.assign({}, row)
      },
      updateData() {
        this.$refs.editForm.validate(valid => {
          if (valid) {
            this.$confirm('确认提交吗？', '提示', {})
              .then(() => {
                const para = {
                  page: 1,
                  size: 200,
                  filters: { name: '', peopleId: this.editForm.peopleId, cardNumber: '' }
                }
                const para2 = this.editForm.id
                getList(para).then(res => {
                  if (res.detailModelList.length !== 0) {
                    const itm = res.detailModelList[0]
                    itm.houseAddress = this.editForm.houseAddress2
                    updateData(itm).then(res => {
                      this.$message({
                        message: '提交成功',
                        type: 'success'
                      })
                      this.$refs['editForm'].resetFields()
                      this.dialogFormVisible = false
                    })
                  }
                })
                removeData0(para2).then(res => {
                  this.getUsers2()
                })
              })
              .catch(e => {
                console.log(e)
              })
          }
        })
      },
      handleDel(index, row) {
        this.$confirm('确认删除该记录吗?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        })
          .then(() => {
            const para = row.id
            removeData0(para).then(res => {
              this.$message({
                message: '删除成功',
                type: 'success'
              })
              this.getUsers2()
            })
          })
          .catch(() => {})
      },
      selsChange(sels) {
        this.sels = sels
      }
    },
    mounted() {
      this.buttons = this.getButton(this.buttons, this.$store.getters.buttons)
      this.getUsers2()
    }
  }
</script>

<style scoped>

</style>
