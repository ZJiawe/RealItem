<template>
  <div>
    <el-tooltip effect="dark" content="疑问" placement="bottom">
      <el-button class=" btn-text can-hover" type="text" @click="dialogVisible = true">
        <nx-icon name="question-circle"/>
      </el-button>
    </el-tooltip>
    <el-dialog title="帮助" width="700px" :visible.sync="dialogVisible" append-to-body>
      <div style="margin-top: -25px;">
        <h1 >如果你有问题可以加入交流群或者联系作者</h1>
        <el-row :gutter="20">
          <el-col :span="12">

          </el-col>
          <el-col :span="12">

          </el-col>
        </el-row>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import nxIcon from '@/components/nx-icon'
export default {
  name: 'nx-help',
  components: {
    nxIcon
  },
  data() {
    return {
      dialogVisible: false
    }
  }
}
</script>

<style lang="scss" scoped>
img {
  &.qr {
    width: 100%;
  }
}
 .btn-text {
        color: #5a5e66;
        &.can-hover {
          &:hover {
            color: #FFEBA4;
            background-color:#5a5e66;
          }
        }
      }

</style>
