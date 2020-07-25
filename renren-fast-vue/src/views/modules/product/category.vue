<!--  -->
<template>
<div>
<el-switch v-model="draggable" active-text="开启拖拽" inactive-text="关闭拖拽"></el-switch>

<el-tree :data="menus" :props="defaultProps" :expand-on-click-node="false" show-checkbox node-key="catId" :default-expanded-keys="expandedKey" :draggable="draggable" :allow-drop="allowDrop"
         @node-drop="handleDrop">
<span class="custom-tree-node" slot-scope="{ node, data }">
  <span>{{ node.label }}</span>
    <span>
        <el-button v-if="node.level<=2" type="text" size="mini" @click="() => append(data)">Append</el-button>
        <el-button type="text" size="mini" @click="() => edit(data)">edit</el-button>
        <el-button v-if="node.childNodes.length==0" type="text" size="mini" @click="() => remove(node, data)"> Delete </el-button>
    </span>
  </span>
</el-tree>

<el-dialog :title="title" :visible.sync="dialogVisible" width="30%" :close-on-click-modal="false">
  <el-form :model="category">
    <el-form-item label="活动名称">
      <el-input v-model="category.name" autocomplete="off"></el-input>
    </el-form-item>
    <el-form-item label="图标">
      <el-input v-model="category.icon" autocomplete="off"></el-input>
    </el-form-item>
    <el-form-item label="计量单位">
      <el-input v-model="category.productUnit" autocomplete="off"></el-input>
    </el-form-item>
  </el-form>

  <span slot="footer" class="dialog-footer">
    <el-button @click="dialogVisible = false">取 消</el-button>
    <el-button type="primary" @click="submitData">确 定</el-button>
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
      draggable: false,
      updateNodes: [],
      maxLevel: 0,
      title: "",
      dialogType: "", //edit add
      category: {name:"", parentCid:0, catLevel:0, showStatus:1, sort:0, catId:null, icon:null, productUnit:""},
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
        this.dialogType = "add";
        this.title = "添加分类";
        this.category.parentCid = data.catId;
        this.category.cateLevel = data.cateLevel*1 + 1;
        this.category.catId = null;
        this.category.name = "";
        this.category.icon = "";
        this.category.productUnit = "";
        this.category.sort = 0;
        this.category.showStatus = 1;
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
    editCategory() {
        var {catId,name,productUnit,icon} = this.category;
        var data = {catId:catId, name:name, productUnit:productUnit, icon:icon};
        this.$http({
          url: this.$http.adornUrl('/product/category/update'),
          method: 'post',
          data: this.$http.adornData({catId,name,productUnit,icon}, false)
        }).then(({data})=>{
            this.$message({
              type: 'success',
              message: '保存成功!'
            });
            this.dialogVisible = false;
            this.getMenus();
            this.expandedKey = [this.category.parentCid];
        });
    },
    edit(data) {
        this.dialogVisible = true;
        this.dialogType = "edit";
        this.title = "修改分类";

        // Obtain the most recent records
        this.$http({
          url: this.$http.adornUrl(`/product/category/info/${data.catId}`),
          method: 'get',
        }).then(({data})=>{
            this.category.name = data.data.name;
            this.category.catId = data.data.catId;
            this.category.icon = data.data.icon;
            this.category.productUnit = data.data.productUnit;
            this.category.parentCid = data.data.parentCid;
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
    },
    submitData() {
        if (this.dialogType == "add") {
            this.addCategory();
        } else {
            this.editCategory();
        }
    },
    allowDrop(draggingNode,dropNode,type) {
        this.findMaxLevel(draggingNode.data);
        let depth = this.maxLevel -  draggingNode.data.catLevel + 1;
        if (type == "inner") {
            return (depth + dropNode.level) <= 3;
        } else {
            return (depth + dropNode.parent.level) <= 3;
        }
        return true;
    },
    handleDrop(draggingNode,dropNode,type, ev) {
        let pCid = 0;
        let siblings = null;
        if (type == "inner") {
            pCid = dropNode.data.catId = dropNode.data.catId;
            siblings = dropNode.data.childNodes;
        } else {
            pCid = dropNode.data.parent.catId ==  undefined ? 0 : dropNode.data.parent.catId;
            siblings = dropNode.data.parent.childNodes;
        }

        for (let i = 0;i < siblings.length;i++) {
            if (siblings[i].data.catId == draggingNode.data.catId) {
                let catLevel = dropNode.data.catLevel;
                if (siblings[i].level != draggingNode.level) {
                    catLevel = siblings[i].level;
                    this.updateChildNodeLevel(siblings[i]);
                }
                this.updateNodes.push({catId:siblings[i].data.catId, sort:i, catLevel:catLevel}, pCid);
            } else {
                this.updateNodes.push({catId:siblings[i].data.catId, sort:i});
            }
        }
        console.log("updateNodes",this.updateNodes);

        this.$http({
          url: this.$http.adornUrl('/product/category/update/sort'),
          method: 'post',
          data: this.$http.adornData(this.updateNodes, false)
        }).then(({data})=>{
            this.$message({
              type: 'success',
              message: '更新成功!'
            });
        });
        this.getMenus();
        this.expandedKey = [pCid];
    },
    updateChildNodeLevel(node) {
        if (node.childNodes.length > 0) {
            for (let i = 0;i < node.childNodes.length;i++) {
                cNode = node.childNodes[i].data;
                this.updateNodes.push({catId:cNode.catId,catLevel:node.childNodes[i].level});
                THIS.updateChildNodeLevel(node.childNodes[i]);
            }
        }
    },
    findMaxLevel(node) {
        if (node.children != null && node.children.length > 0) {
            for (let i = 0;i < node.children.length;i++) {
                if (node.children[i].catLevel > this.maxLevel) {
                    this.maxLevel = node.children[i].catLevel;
                }
                this.findMaxLevel(node.children[i]);
            }
        }
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