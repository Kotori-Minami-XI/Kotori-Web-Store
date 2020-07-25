<!--  -->
<template>
<div>

<el-tree :data="menus" :props="defaultProps" :expand-on-click-node="false" show-checkbox node-key="catId" :default-expanded-keys="expandedKey">
<span class="custom-tree-node" slot-scope="{ node, data }">
  <span>{{ node.label }}</span>
    <span>
        <el-button v-if="node.level<=2" type="text" size="mini" @click="() => append(data)">Append</el-button>
        <el-button v-if="node.childNodes.length==0" type="text" size="mini" @click="() => remove(node, data)"> Delete </el-button>
    </span>
  </span>
</el-tree>

<el-dialog title="提示" :visible.sync="dialogVisible" width="30%">
  <el-form :model="category">
    <el-form-item label="活动名称">
      <el-input v-model="category.name" autocomplete="off"></el-input>
    </el-form-item>
  </el-form>

  <span slot="footer" class="dialog-footer">
    <el-button @click="dialogVisible = false">取 消</el-button>
    <el-button type="primary" @click="addCategory">确 定</el-button>
  </span>
</el-dialog>

</div>
</template>

<script>
//这里可以导入其他文件（比如：组件，工具js，第三方插件js，json文件，图片文件等等）
//例如：import 《组件名称》 from '《组件路径》';

export default {
//import引入的组件需要注入到对象中才能使用
components: {},
data() {
    return {
      category: {name:"", parentCid:0, catLevel:0, showStatus:1, sort:0},
      dialogVisible : false,
      menus: [],
      expandedKey: [],
      defaultProps: {
        children: 'children',
        label: 'name'
    }
    };
},
//监听属性 类似于data概念
computed: {},
//监控data中的数据变化
watch: {},
//方法集合
methods: {

    getMenus() {
        this.dataListLoading = true
        this.$http({
          url: this.$http.adornUrl('/product/category/list/tree'),
          method: 'get'
        }).then(({data})=>{
            console.log("succeed in obtaining menu",data.data);
            this.menus = data.data;
        })
    },
    append(data) {
        this.dialogVisible = true;
        this.category.parentCid = data.catId;
        this.category.cateLevel = data.cateLevel*1 + 1;

    },
    addCategory() {
      this.$http({
        url: this.$http.adornUrl('/product/category/save'),
        method: 'post',
        data: this.$http.adornData(this.category, false)
        }).then(({data})=>{
            this.$message({
              type: 'success',
              message: '保存成功!'
            });
            this.dialogVisible = false;
            this.getMenus();
            this.expandedKey = [node.category.parentCid];
        });
    },
    remove(node, data) {
        var ids = [data.catId];
        this.$confirm(`此操作将删除[${data.name}], 是否继续?`, '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.$http({
            url: this.$http.adornUrl('/product/category/delete'),
            method: 'post',
            data: this.$http.adornData(ids, false)
          }).then(({data}) => {
            this.$message({
              type: 'success',
              message: '删除成功!'
            });
            this.getMenus();
            this.expandedKey = [node.parent.data.catId];
          })
        }).catch(() => {
          this.$message({
          type: 'info',
          message: '已取消删除'
        });          
      });
    }
},
//生命周期 - 创建完成（可以访问当前this实例）
created() {
    this.getMenus();
},
//生命周期 - 挂载完成（可以访问DOM元素）
mounted() {

},
beforeCreate() {}, //生命周期 - 创建之前
beforeMount() {}, //生命周期 - 挂载之前
beforeUpdate() {}, //生命周期 - 更新之前
updated() {}, //生命周期 - 更新之后
beforeDestroy() {}, //生命周期 - 销毁之前
destroyed() {}, //生命周期 - 销毁完成
activated() {}, //界面加载就会执行的方法。类似于jquery中的$(function){}
}
</script>
<style>
</style>