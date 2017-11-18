<template>
  <div>
    <template v-for="(item,index) in items" v-if="!item.hidden">
      <el-submenu :index="index+''" v-if="!item.leaf">
        <template slot="title">
          <i :class="item.iconCls"></i>{{item.name}}</template>
        <menu-tree :items="item.children"></menu-tree>
      </el-submenu>
      <!-- 这里是用:route="{name:item.name}，如果不使用name跳转的话，可以在数据源那里把path写成全路径即可 -->
      <el-menu-item v-if="item.leaf" :index="item.path" :route="{name:item.name}">
        <i :class="item.iconCls"></i>{{item.name}}</el-menu-item>
    </template>
  </div>
</template>

<script type="text/ecmascript-6">
export default {
  name: 'MenuTree',
  data() {
    return {

    }
  },
  props: {
    items: {
      required: true
    }
  }
}
</script>

<style lang="scss" scoped>

</style>