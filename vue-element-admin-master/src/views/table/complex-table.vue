<template>
  <div class="app-container">
    <div class="filter-container">
      <el-input v-model="listQuery.title" placeholder="搜索姓名" style="width: 200px;" class="filter-item" @keyup.enter.native="handleFilter" />
      <el-button v-waves class="filter-item" type="primary" icon="el-icon-search" @click="queryStudentByCondition">
        搜索
      </el-button>
      <el-button class="filter-item" style="margin-left: 10px;" type="primary" icon="el-icon-edit" @click="handleCreate">
        添加
      </el-button>
      <el-button v-waves :loading="downloadLoading" class="filter-item" type="primary" icon="el-icon-download" @click="handleDownload">
        下载
      </el-button>
    </div>

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

      <el-table-column label="编码" prop="id" sortable="custom" align="center" width="80" :class-name="getSortClass('id')">
        <template slot-scope="{row}">
          {{ row.id }}
        </template>
      </el-table-column>

      <el-table-column label="姓名" prop="name" align="center" min-width="100">
        <template slot-scope="{row}">
          <span class="link-type" @click="handleUpdate(row)">{{ row.name }}</span>
        </template>
      </el-table-column>
      <el-table-column label="年龄" prop="age" min-width="150px" align="center">
        <template slot-scope="{row}">
          <span class="link-type" @click="handleUpdate(row)">{{ row.age }}</span>
        </template>
      </el-table-column>
      <el-table-column label="Actions" align="center" width="230" class-name="small-padding fixed-width">
        <template slot-scope="{row,$index}">
          <el-button type="primary" size="mini" @click="handleUpdate(row)" icon="el-icon-edit">
            修改
          </el-button>
          <el-button v-if="row.status!='deleted'" size="mini" type="danger" @click="handleDelete(row,$index)" icon="el-icon-delete">
            删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total>0" :total="total" :page.sync="listQuery.page" :limit.sync="listQuery.limit" @pagination="getList" />

    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible">
      <el-form ref="dataForm" :rules="rules" :model="temp" label-position="left" label-width="70px" style="width: 400px; margin-left:50px;">
        <el-form-item label="编号">
          <el-input v-model="temp.id" placeholder="请输入编号" />
        </el-form-item>
        <el-form-item label="姓名">
          <el-input v-model="temp.name" placeholder="请输入姓名" />
        </el-form-item>
        <el-form-item label="年龄" >
          <el-input v-model="temp.age"type="number" placeholder="请输入年龄" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">
          取消
        </el-button>
        <el-button type="primary" @click="dialogStatus==='create'?createData():updateData()">
          增加
        </el-button>
      </div>
    </el-dialog>

    <el-dialog :visible.sync="dialogPvVisible" title="Reading statistics">
      <el-table :data="pvData" border fit highlight-current-row style="width: 100%">
        <el-table-column prop="key" label="Channel" />
        <el-table-column prop="pv" label="Pv" />
      </el-table>
      <span slot="footer" class="dialog-footer">
        <el-button type="primary" @click="dialogPvVisible = false">Confirm</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
import { fetchList, fetchPv, createArticle, updateArticle } from '@/api/article'
import waves from '@/directive/waves' // waves directive
// import { parseTime } from '@/utils'
import Pagination from '@/components/Pagination'


const calendarTypeOptions = [
  { key: 'CN', display_name: 'China' },
  { key: 'US', display_name: 'USA' },
  { key: 'JP', display_name: 'Japan' },
  { key: 'EU', display_name: 'Eurozone' }
]

// arr to obj, such as { CN : "China", US : "USA" }
const calendarTypeKeyValue = calendarTypeOptions.reduce((acc, cur) => {
  acc[cur.key] = cur.display_name
  return acc
}, {})

export default {
  name: 'ComplexTable',
  components: { Pagination },
  directives: { waves },
  filters: {
    statusFilter(status) {
      const statusMap = {
        published: 'success',
        draft: 'info',
        deleted: 'danger'
      }
      return statusMap[status]
    },
    typeFilter(type) {
      return calendarTypeKeyValue[type]
    }
  },
  data() {
    return {
      tableKey: 0,
      list: null,
      total: 0,
      listLoading: true,
      listQuery: {
        page: 1,
        limit: 20,
        importance: undefined,
        title: undefined,
        type: undefined,
        sort: '+id'
      },
      importanceOptions: [1, 2, 3],
      calendarTypeOptions,
      sortOptions: [{ label: 'ID Ascending', key: '+id' }, { label: 'ID Descending', key: '-id' }],
      statusOptions: ['published', 'draft', 'deleted'],
      showReviewer: false,
      temp: {
        id: undefined,
        importance: 1,
        remark: '',
        timestamp: new Date(),
        title: '',
        type: '',
        status: 'published'
      },
      dialogFormVisible: false,
      dialogStatus: '',
      textMap: {
        update: 'Edit',
        create: 'Create'
      },
      dialogPvVisible: false,
      pvData: [],
      rules: {
        type: [{ required: true, message: 'type is required', trigger: 'change' }],
        timestamp: [{ type: 'date', required: true, message: 'timestamp is required', trigger: 'change' }],
        title: [{ required: true, message: 'title is required', trigger: 'blur' }]
      },
      downloadLoading: false
    }
  },
  created() {
    this.getList()
  },
  methods: {
    getList() {
      let params = {
        current: this.listQuery.page,
        size: this.listQuery.limit,
      }
      this.$axios.get('/student/queryStudents', {
        params: params,
        headers: {
          'Content-Type': 'application/json; charset=utf-8'
        }
      }).then((res) => {
        this.list = res.data.records
        this.total = res.data.total
        this.listLoading = false
      }).catch((error) => {
        console.log(error)
      })
    },
    queryStudentByCondition() {
      let params = {
        id: 0,
        name: this.listQuery.title,
        age: 0,
        current: parseInt(this.listQuery.page),
        size: parseInt(this.listQuery.limit)
      };

      this.$axios.post('student/querybycondition', params)
        .then((res) => {
          console.log(res); // 打印完整的响应数据
          this.list = res.data.records;
          this.total = res.data.total;
          this.listLoading = false;
        })
        .catch((error) => {
          console.log(error); // 打印错误信息
        });
    },

    handleFilter() {
      this.listQuery.page = 0
      this.getList()
    },
    sortChange(data) {
      const { prop, order } = data
      if (prop === 'id') {
        this.sortByID(order)
      }
    },

    sortByID(order) {
      if (order === 'ascending') {
        this.listQuery.sort = '+id'
      } else {
        this.listQuery.sort = '-id'
      }
      this.handleFilter()
    },
    resetTemp() {
      this.temp = {
        id: undefined,
        importance: 1,
        remark: '',
        timestamp: new Date(),
        title: '',
        status: 'published',
        type: ''
      }
    },
    // 创建
    handleCreate() {
      this.resetTemp()
      this.dialogStatus = 'create'
      this.dialogFormVisible = true
      this.$nextTick(() => {
        this.$refs['dataForm'].clearValidate()
      })
    },
    createData() {
      this.$refs['dataForm'].validate((valid) => {
        if (valid) {
          const params = {
            id: this.temp.id,
            name: this.temp.name,
            age: this.temp.age,
          };

          // 确保你的插入接口正确调用
          this.$axios.post('/student/insert', params)
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

    handleUpdate(row) {
      this.temp = Object.assign({}, row) // copy obj
      this.temp.timestamp = new Date(this.temp.timestamp)
      this.dialogStatus = 'update'
      this.dialogFormVisible = true
      this.$nextTick(() => {
        this.$refs['dataForm'].clearValidate()
      })
    },
    updateData() {
      this.$refs['dataForm'].validate((valid) => {
        if (valid) {
          const tempData = Object.assign({}, this.temp);

          // 使用 axios 发送 PUT 请求更新学生信息
          this.$axios.put('/student/update', tempData)
            .then(() => {
              const index = this.list.findIndex(v => v.id === this.temp.id);
              this.list.splice(index, 1, this.temp); // 更新本地列表数据
              this.dialogFormVisible = false; // 关闭对话框

              this.$notify({
                title: '成功',
                message: '更新成功',
                type: 'success',
                duration: 2000
              });

              this.getList(); // 更新成功后重新获取列表数据
            })
            .catch((error) => {
              console.error("Error during update:", error);
            });
        }
      });
    },

    handleDelete(row, index) {
      this.$axios.delete(`/student/delete?id=${row.id}`)
        .then(() => {
          this.list.splice(index, 1); // 从列表中移除数据
          this.$notify({
            title: '成功',
            message: '删除成功',
            type: 'success',
            duration: 2000
          });
          this.getList(); // 删除成功后重新获取列表数据
        })
        .catch((error) => {
          console.error("Error during delete:", error);
        });
    },

    handleDownload() {
      this.downloadLoading = true;
      import('@/vendor/Export2Excel').then(excel => {
        const tHeader = ['编号', '姓名', '年龄'];
        const filterVal = ['id', 'name', 'age'];  // 数据字段映射
        const data = this.formatJson(filterVal);  // 生成数据

        excel.export_json_to_excel({
          header: tHeader,
          data,
          filename: '数据列表'
        });

        this.downloadLoading = false;
      });
    },

    formatJson(filterVal) {
      return this.list.map(item => filterVal.map(key => {
        return item[key];  // 返回每个键对应的值
      }));
    },

    getSortClass: function(key) {
      const sort = this.listQuery.sort
      return sort === `+${key}` ? 'ascending' : 'descending'
    }
  }
}
</script>
