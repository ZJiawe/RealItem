<template>
  <div class="app-container">

    <div id="print">
    <el-tag style="margin-bottom:20px;">
      <a href="https://github.com/PanJiaChen/vue-element-admin/tree/master/src/components/TreeTable" target="_blank">Documentation</a>
    </el-tag>
    </div>
    <nx-tree-table :data="data" :columns="columns" border></nx-tree-table>
    <el-button @click="conprint()">打印</el-button>
  </div>
</template>

<script>
  /**
   Auth: Lei.j1ang
   Created: 2018/1/19-14:54
   */
  import nxTreeTable from '@/components/nx-tree-table'

  export default {
    name: 'treeTableDemo',
    components: { nxTreeTable },
    data() {
      return {
        columns: [
          {
            text: '体质类型及对应条目',
            value: 'event',
            width: 200
          },
          {
            text: '条件',
            value: 'id'
          },
          {
            text: '判定结果',
            value: 'timeLine'
          },
          {
            text: '备注',
            value: 'comment'
          }
        ],
        data: [
          {
            id: '各条目得分相加≥11分',
            event: 'A.气虚质(2)(3)(4)(14)' +
              'A.湿热质(23)(25)(27)(30)' +
              'A.气郁质(5)(6)(7)(8)',
            timeLine: '是',
            comment: '评判标准适用于A类型条目'
          },
          {
            id: '各条目得分相加9~10分',
            event: 'A.阳虚质(11)(12)(13)(29)' +
              'A.血瘀质(19)(22)(24)(33)' +
              'A.特禀质(15)(17)(18)(20)',
            timeLine: '倾向是',
            comment: '评判标准适用于A类型条目'
          },
          {
            id: '各条目得分相加≤8 分',
            event: 'A.阴虚质(10)(21)(26)(31)A.痰湿质(9)(16)(28)(32)',
            timeLine: '否',
            comment: '评判标准适用于A类型条目'
          },
          {
            id: '各条目得分相加≥17分，同时其他8种体质得分都≤8分',
            event: 'B.平和质(1)(2)(4)(5)(13)(其中,(2)(4)(5)(13)反向计分,即1→5,2→4,3→3,4→2,5→1)',
            timeLine: '是',
            comment: '评判标准适用于B类型条目'
          },
          {
            id: '各条目得分相加≥17分，同时其他8种体质得分都≤10分',
            event: 'B.平和质(1)(2)(4)(5)(13)(其中,(2)(4)(5)(13)反向计分,即1→5,2→4,3→3,4→2,5→1)',
            timeLine: '是',
            comment: '评判标准适用于B类型条目'
          },
          {
            id: '不满足上述条件者',
            event: 'B.平和质(1)(2)(4)(5)(13)(其中,(2)(4)(5)(13)反向计分,即1→5,2→4,3→3,4→2,5→1)',
            timeLine: '否',
            comment: '评判标准适用于B类型条目'
          }
        ]
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
        var str = style + '<div id="dataPanel" style="position:absolute;left:50%;margin-left:-397px;">\n' +
          '  <div id="topContent">\n' +
          '    <h2 style="width:794px;text-align:center">体质判定标准表</h2>\n' +
          '    <span style="margin-left:34px;">{0}</span>\n' +
          '    <span style="margin-left:510px;">NO:{1}</span>\n' +
          '  </div>\n' +
          '  <table id="table"  border="1"  cellspacing="0" bordercolor="#000000" style="width:730px;margin-left:30px;margin-top:5px;border-collapse:collapse;"  width = "80%">\n' +
          '\n' +
          '    <tbody id="secondContent">\n' +
          '    <tr id="tr_${varStatus.index}"><!-- pictureBookInfo -->\n' +
          '      <td style="word-wrap:break-word;width: 15%;text-align:center">类型及对应条目</td>\n' +
          '      <td style="text-align:center">条件</td>\n' +
          '      <td style="text-align:center">判定结果</t>\n' +
          '      <td style="word-wrap:break-word;width: 25%;text-align:center">类型及对应条目</td>\n' +
          '      <td style="text-align:center">条件</td>\n' +
          '      <td style="word-wrap:break-word;width: 10%;text-align:center">类型及对应条目</td>\n' +
          '    </tr>\n' +
          '    <tr id="tr_${varStatus.index}"><!-- pictureBookInfo -->\n' +
          '      <td rowspan="3"style="word-wrap:break-word;width: 10%;text-align:center">气虚质(2)(3)(4)(14)<br/>阳虚质(11)(12)(13)(29)<br/>阴虚质(10)(21)(26)(31)<br/>痰湿质(9)(16)(28)(32)<br/>湿热质(23)(25)(27)(30)<br/>血瘀质(19)(22)(24)(33)<br/>气郁质(5)(6)(7)(8)<br/>特禀质(15)(17)(18)(20)</td>\n' +
          '      <td style="text-align:center">各条目得分相加≥11分</td>\n' +
          '      <td style="text-align:center">是</td>\n' +
          '      <td rowspan="3"style="word-wrap:break-word;width: 10%;text-align:center">平和质(1)(2)(4)(5)(13)<br/>(其中，(2)(4)(5)(13)反向计分，<br/>即 1→5，2→4，3→3，4→2，5→1)</td>\n' +
          '      <td style="text-align:center">各条目得分相加≥17分,同时其他8种体质得分都≤8分</td>\n' +
          '      <td style="text-align:center">是</td>\n' +
          '    </tr>\n' +
          '    <tr id="tr_${varStatus.index}"><!-- pictureBookInfo -->\n' +
          '      <td style="word-wrap:break-word;width: 10%;text-align:center">各条目得分相加9~10分</td>\n' +
          '      <td style="word-wrap:break-word;width: 10%;text-align:center">倾向是</td>\n' +
          '      <td style="word-wrap:break-word;width: 10%;text-align:center">各条目得分相加≥17分,同时其他8种体质得分都≤10分</td>\n' +
          '      <td style="word-wrap:break-word;width: 10%;text-align:center">基本是</td>\n' +
          '    </tr>\n' +
          '    <tr id="tr_${varStatus.index}"><!-- pictureBookInfo -->\n' +
          '      <td style="word-wrap:break-word;width: 10%;text-align:center">各条目得分相加≤8分</td>\n' +
          '      <td style="text-align:center">否</td>\n' +
          '      <td style="word-wrap:break-word;width: 10%;text-align:center">不满足上述条件者</td>\n' +
          '      <td style="text-align:center">否</td>\n' +
          '    </tr>\n' +
          '    </tbody>\n' +
          '  </table>\n' +
          '</div>\n'
        myWindow.document.write(str)
        myWindow.focus()
        myWindow.document.close()
        myWindow.print()
        return myWindow
      },
      conprint() {
        const windows = this.printf(document.getElementById('print').innerHTML)
        windows.close()
      }
    },
    mounted() {
      this.getUsers()
    }
  }
</script>

<style scoped>

</style>
