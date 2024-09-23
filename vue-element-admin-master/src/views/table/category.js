import {post, get, deletefn, put} from './axios'

//获取分类
export const getCategoryList = data => get('/category/list');

//新增一级菜单
export const addCategory = data=> post("/category/add",data);
//根据id删除节点
export const deleteCategoryById=data=>deletefn(`/category/${data.id}`,data);
//新增子节点
export  const addCategoryByParentId=data=>post(`/category/addByParentId`,data);

//根据id编辑节点
export const editCategoryById=data=>put(`/category/${data.id}`,data);
