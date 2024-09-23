<template>
  <el-card class="box-card">
    <!--面包屑-->
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item>商品管理</el-breadcrumb-item>
      <el-breadcrumb-item>分类管理</el-breadcrumb-item>
    </el-breadcrumb>
    <el-button @click="addCategoryDialog()" style="margin-top: 20px">增加</el-button>
    <!--新增一级对话框-->
    <el-dialog
      title="新增一级"
      :visible.sync="addDialogVisible"
      width="30%"
    >
      <el-form ref="form" :model="addForm" label-width="80px">
        <el-form-item label="名称">
          <el-input v-model="addForm.name"></el-input>
        </el-form-item>
      </el-form>
      <el-button @click="addDialogVisible = false">取 消</el-button>
      <el-button type="primary" @click="submitCategory">确 定</el-button>
    </el-dialog>
    <!--tree-->
    <el-tree
      :props="defaultProps"
      :data="treeData"
      show-checkbox
      node-key="id"
      :default-expand-all="false"
      :expand-on-click-node="false">
      <span class="custom-tree-node" slot-scope="{ node, data }">
        <span>{{ node.label }}</span>
        <span>
            <el-button
              type="text"
              size="mini"
              @click="() => append(data.id,data.isParent)">
            增加
          </el-button>
          <el-button
            type="text"
            size="mini"
            @click="() => remove(data.id)">
            删除
          </el-button>
            <el-button
              type="text"
              size="mini"
              @click="() => edit(data.id,data.isParent,data.parentId,data.name)">
            编辑
          </el-button>
        </span>
      </span>
    </el-tree>

    <!--新增子菜单对话框-->
    <el-dialog
      title="新增子菜单"
      :visible.sync="addCategoryByParentIdDialogVisible"
      width="30%"
    >
      <el-form ref="form" :model="addForm" label-width="80px">
        <el-form-item label="名称">
          <el-input v-model="addForm.name"></el-input>
        </el-form-item>
      </el-form>
      <el-button @click="addCategoryByParentIdDialogVisible = false">取 消</el-button>
      <el-button type="primary" @click="submitCategoryByParentId">确 定</el-button>
    </el-dialog>


    <!--编辑节点对话框-->
    <el-dialog
      title="编辑"
      :visible.sync="editCategoryByParentIdDialogVisible"
      width="30%"
    >
      <el-form ref="form" :model="addForm" label-width="80px">
        <el-form-item label="名称">
          <el-input v-model="addForm.name"></el-input>
        </el-form-item>
      </el-form>
      <el-button @click="editCategoryByParentIdDialogVisible = false">取 消</el-button>
      <el-button type="primary" @click="submitEditCategoryById">确 定</el-button>
    </el-dialog>
  </el-card>
</template>

<script>
import {getCategoryList,addCategory,deleteCategoryById,addCategoryByParentId,editCategoryById} from "./category";

export default {
  name: "mycategory",
  data() {
    return {
      treeData: [],
      defaultProps: {
        label: 'name'
      },
      addDialogVisible: false,
      addForm: {
        name: ''
      },
      addCategoryByParentIdDialogVisible:false,
      parentId:'',
      isParent:'',
      id:'',
      editCategoryByParentIdDialogVisible:false


    }
  },
  created() {
    this.getlist();

  },
  methods: {
    /**
     * 获取数据
     */
    getlist() {
      getCategoryList().then(res => {
        console.log("res")
        console.log(res)
        console.log("~~~~~~~~~")
        this.treeData = this.arraytotree(res.data);
        console.log(this.treeData)
      }).catch(res => {

      })

    },
    handleNodeClick(data) {
      console.log(data);
    },

    //数组转化为树
    arraytotree(arr) {
      var top = [], sub = [], tempObj = {};
      arr.forEach(function (item) {
        if (item.parentId === 0) { // 顶级分类
          top.push(item)
        } else {
          sub.push(item) // 其他分类
        }
        item.children = []; // 默然添加children属性
        tempObj[item.id] = item // 用当前分类的id做key，存储在tempObj中
      })

      sub.forEach(function (item) {
        // 取父级
        var parent = tempObj[item.parentId] || {'children': []}
        // 把当前分类加入到父级的children中
        parent.children.push(item)
      })

      return top
    },

    addCategoryDialog() {
      this.addDialogVisible = true;
    },
    /**
     * 新增一级目录
     */
    submitCategory(){
      addCategory(this.addForm).then(res=>{
        console.log(res)
        if (res.code===200){
          this.$message({
            type: 'success',
            message: '新增一级目录成功'
          });
          this.addForm={}
          this.addDialogVisible=false
          this.getlist();
        }
        if (res.code===601){
          this.$message({
            type: 'error',
            message: '已存在相同的一级目录'
          });
          this.addForm={}
          this.addDialogVisible=false
          this.getlist();
        }
      }).catch(res=>{

      })
    },

    append(id,isParent) {
      this.id=id
      this.isParent=isParent
      console.log(id)
      console.log(isParent)
      this.addCategoryByParentIdDialogVisible=true;
    },

    /**
     * 新增子节点
     */
    submitCategoryByParentId(){
      //把新增子节点的parentId设置为获取到的节点id
      addCategoryByParentId({name:this.addForm.name,isParent:this.isParent,parentId:this.id}).then(res=>{
        if (res.code===200){
          this.$message({
            type:'success',
            message:'新增成功'
          })
          this.addCategoryByParentIdDialogVisible=false;
          this.addForm={}
          this.getlist();
        }
      }).catch(res=>{

      })
    },


    /**
     * 通过id删除节点
     * @param id
     */
    remove(id) {
      console.log(id)
      deleteCategoryById({id:id}).then(res=>{
        if (res.data===200){
          this.$message({
            type: 'success',
            message: '删除成功'
          });

        }
        this.getlist();
      }).catch(res=>{

      })
    },

    edit(id,isParent,parentId,name) {
      this.id=id;
      this.isParent=isParent;
      this.parentId=parentId
      this.addForm.name=name;
      this.editCategoryByParentIdDialogVisible=true;
    },

    /**
     * 根据id编辑节点
     */
    submitEditCategoryById(){
      editCategoryById({id:this.id,name:this.addForm.name,isParent:this.isParent,parentId:this.parentId}).then(res=>{
        if (res.code===200){
          this.$message({
            type: 'success',
            message: '更新成功'
          });
          this.addForm={}
          this.editCategoryByParentIdDialogVisible=false
          this.getlist();
        }
        if (res.code===801){
          this.$message({
            type: 'error',
            message: '更新失败'
          });
          this.addForm={}
          this.editCategoryByParentIdDialogVisible=false
          this.getlist();
        }
      }).catch(res=>{

      })
    }
  }
}
</script>

<style scoped>
.el-tree {
  margin-top: 20px;
}

.custom-tree-node {
  flex: 1;
  display: flex;
  align-items: center;
  justify-content: space-between;
  font-size: 14px;
  padding-right: 8px;
}
</style>
