<template>
  <div class="app-container">
    <el-button class="filter-item" type="primary" icon="el-icon-edit" @click="handleCreate"> 添加 </el-button>

    <!-- 表格显示数据 -->
    <el-table
      :key="tableKey"
      v-loading="listLoading"
      :data="list"
      border
      fit
      highlight-current-row
      style="width: 100%;"
      @sort-change="sortChange"
    >
      <el-table-column align="center" label="编号" width="80" sortable="custom">
        <template slot-scope="{row}">
          <span>{{ row.id }}</span>
        </template>
      </el-table-column>

      <el-table-column align="center" label="会员ID" width="120px">
        <template slot-scope="{row}">
          <span>{{ row.memberId }}</span>
        </template>
      </el-table-column>

      <el-table-column align="center" label="日期" width="180px">
        <template slot-scope="{row}">
          <span>{{ row.date }}</span>
        </template>
      </el-table-column>

      <el-table-column min-width="300px" label="点击下载查看 - - - 体检报告链接">
        <template slot-scope="{row}">
          <a :href="row.url" target="_blank">{{ row.url }}</a>
        </template>
      </el-table-column>

      <el-table-column align="center" label="操作" width="240">
        <template slot-scope="{row, $index}">
          <el-button type="primary" size="mini" @click="handleUpdate(row)" icon="el-icon-edit"> 修改 </el-button>
          <el-button size="mini" type="danger" @click="handleDelete(row, $index)" icon="el-icon-delete"> 删除 </el-button>
        </template>
      </el-table-column>
    </el-table>

<!--    <pagination v-show="total >0 " :total="total" :page.sync="listQuery.page" :limit.sync="listQuery.limit" @pagination="getList" />-->
    <el-pagination
      background
      layout="prev, pager, next"
      :total="total"
      :page-size="listQuery.limit"
      :current-page.sync="listQuery.page"
      @current-change="getList"
    />
    <!-- 添加/编辑对话框 -->
    <el-dialog :title="dialogTitle" :visible.sync="dialogFormVisible">
      <el-form ref="dataForm" :model="temp" label-width="70px">
        <el-form-item label="编号">
          <el-input v-model="temp.id" placeholder="请输入编号" />
        </el-form-item>
        <el-form-item label="会员ID">
          <el-input v-model="temp.memberId" placeholder="请输入会员ID" />
        </el-form-item>
        <el-form-item label="日期">
          <el-date-picker
            v-model="temp.date"
            type="date"
            placeholder="选择日期"
            value-format="yyyy-MM-dd"
            style="width: 200px;"
          />
        </el-form-item>
        <el-form-item label="图片URL">
          <el-input v-model="temp.url" placeholder="请输入图片URL" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false"> 取消 </el-button>
        <el-button type="primary" @click="dialogStatus === 'create' ? createData() : updateData()"> 确定 </el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { fetchList } from '@/api/article';

export default {
  name: 'Health',

  data() {
    return {
      tableKey: 0,
      list: null,
      total: 0, // 总记录数
      listQuery: {
        page: 1,
        limit: 10,
        memberId: '', // 修改为会员ID
        sort: '+id'
      },
      listLoading: true,
      dialogFormVisible: false,
      dialogStatus: '',
      dialogTitle: '',
      temp: { // 表单数据
        id: null,
        memberId: null,
        date: '',
        url: ''
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    getList() {
      this.listLoading = true;
      let params = {
        current: this.listQuery.page,
        size: this.listQuery.limit,
      }
      this.$axios.get('/health/queryHealths', {
        params: params,
        headers: {
          'Content-Type': 'application/json; charset=utf-8'
        }
      }).then((res) => {
        this.list = res.data.records;
        this.total = res.data.total;
        this.listLoading = false;
      }).catch((error) => {
        console.log(error)
        this.listLoading = false;
      });
    },

    // queryHealthByCondition() {
    //   let params = {
    //     memberId: this.listQuery.memberId,
    //     current: parseInt(this.listQuery.page),
    //     size: parseInt(this.listQuery.limit)
    //   };
    //
    //   this.$axios.post('/health/querybycondition', params)
    //     .then((res) => {
    //       console.log(res); // 打印完整的响应数据
    //       this.list = res.data.records;
    //       this.total = res.data.total;
    //       this.listLoading = false;
    //     })
    //     .catch((error) => {
    //       console.log(error); // 打印错误信息
    //     });
    // },



    // 添加
    handleCreate() {
      this.resetTemp();
      this.dialogTitle = '添加';
      this.dialogStatus = 'create';
      this.dialogFormVisible = true;
    },

    // 编辑
    handleUpdate(row) {
      this.temp = Object.assign({}, row); // 拷贝数据
      this.dialogTitle = '编辑';
      this.dialogStatus = 'update';
      this.dialogFormVisible = true;
    },

    // 删除
    handleDelete(row, index) {
      this.$axios.delete(`/health/delete?id=${row.id}`)
        .then(res => {
          this.list.splice(index, 1); // 从列表中移除
          this.$notify({ title: '成功', message: '删除成功', type: 'success', duration: 2000 });
        })
        .catch(error => {
          console.error(error);
        });
    },

    // 创建数据
    createData() {
      this.$refs['dataForm'].validate(valid => {
        if (valid) {
          const params = {
            id: this.temp.id,
            memberId: this.temp.memberId,
            date: this.temp.date,
            url: this.temp.url,
          };
          this.$axios.post('/health/insert', params)
            .then((res) => {
              this.list.unshift(this.temp); // 将数据添加到前端列表
              this.dialogFormVisible = false; // 关闭对话框
              this.$notify({
                title: 'Success',
                message: 'Created Successfully',
                type: 'success',
                duration: 2000
              });
            })
            .catch((error) => {
              console.error("Error during insert:", error);
            });
        }
      });
    },

    // 更新数据
    updateData() {
      this.$refs['dataForm'].validate(valid => {
        if (valid) {
          const params = {
            id: this.temp.id,
            memberId: this.temp.memberId,
            date: this.temp.date,
            url: this.temp.url,
          };
          this.$axios.put('/health/update', params)
            .then(() => {
              const index = this.list.findIndex(item => item.id === this.temp.id);
              this.list.splice(index, 1, Object.assign({}, this.temp)); // 更新列表
              this.dialogFormVisible = false; // 关闭对话框
              this.$notify({ title: '成功', message: '更新成功', type: 'success', duration: 2000 });
            })
            .catch(error => {
              console.error(error);
            });
        }
      });
    },

    // 重置表单
    resetTemp() {
      this.temp = { id: '', memberId: '', date: '', url: '' };
    },


    // formatJson(filterVal) {
    //   return this.list.map(item => filterVal.map(key => {
    //     return item[key];  // 返回每个键对应的值
    //   }));
    // },
  }
};
</script>

<style scoped>
.app-container {
  padding: 20px;
}
</style>


<!--// 下载数据-->
<!--// handleDownload() {-->
<!--//   this.downloadLoading = true;-->
<!--//   // 恭喜下载成功-->
<!--//   this.$axios.get('/health/exportHealths')-->
<!--//     .then(res => {-->
<!--//       this.downloadLoading = false;-->
<!--//       this.$notify({ title: '成功', message: '下载成功', type: 'success', duration: 2000 });-->
<!--//     })-->
<!--//     .catch(error => {-->
<!--//       console.error(error);-->
<!--//     });-->
<!--//   import('@/vendor/Export2Excel').then(excel => {-->
<!--//     const tHeader = ['编号', '会员ID', '日期', '图片URL'];-->
<!--//     const filterVal = ['id', 'memberID', 'date', 'url'];  // 数据字段映射-->
<!--//     const data = this.formatJson(filterVal);  // 生成数据-->
<!--//-->
<!--//     excel.export_json_to_excel({-->
<!--//       header: tHeader,-->
<!--//       data,-->
<!--//       filename: '会员数据'-->
<!--//     });-->
<!--//-->
<!--//     this.downloadLoading = false;-->
<!--//   });-->
<!--// },-->
<!-- 搜索栏 -->
<!--    <div class="filter-container">-->
<!--      <el-input-->
<!--        v-model="listQuery.memberId"-->
<!--        placeholder="搜索会员ID"-->
<!--        style="width: 200px;"-->
<!--        class="filter-item"-->
<!--        @keyup.enter.native="queryHealthByCondition"-->
<!--      />-->
<!--      <el-button class="filter-item" type="primary" icon="el-icon-search" @click="queryHealthByCondition"> 搜索 </el-button>-->
<!--      <el-button class="filter-item" type="primary" icon="el-icon-edit" @click="handleCreate"> 添加 </el-button>-->
<!--&lt;!&ndash;      <el-button class="filter-item" type="primary" icon="el-icon-download" @click="handleDownload"> 下载 </el-button>&ndash;&gt;-->
<!--    </div>-->
