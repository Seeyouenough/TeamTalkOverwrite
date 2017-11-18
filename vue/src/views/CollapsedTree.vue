<template> 
    <ul class="el-menu el-menu-vertical-demo collapsed" v-show="collapsed" ref="menuCollapsed">
       <li v-for="(item,index) in items" v-if="!item.hidden" class="el-submenu item">
            <template v-if="!item.leaf">
              <div class="el-submenu__title" style="padding-left: 20px;" @mouseover="showMenu(index,item.name,true)" @mouseout="showMenu(index,item.name,false)"><i :class="item.iconCls"></i></div>
              <ul class="el-menu submenu" :class="'submenu-hook-'+item.name+'-'+index" @mouseover="showMenu(index,item.name,true)" @mouseout="showMenu(index,item.name,false)"> 
                <li v-for="child in item.children" v-if="!child.hidden" :key="child.path" class="el-menu-item" style="padding-left: 40px;" :class="$route.path==child.path?'is-active':''" @click="$router.push(child.path)">{{child.name}}
                   <template v-if="child.children!=null && child.children.length>0"> 
                     <ul class="el-menu el-menu-vertical-demo collapsed" v-show="collapsed" ref="menuCollapsed">
                        <li v-for="(it,index) in child.children" v-if="!it.hidden" class="el-submenu item">
                             <template v-if="!it.leaf">
                               <div class="el-submenu__title" style="padding-left: 20px;" @mouseover="showMenu(index,it.name,true)" @mouseout="showMenu(index,it.name,false)"><i :class="it.iconCls"></i></div>
                               <ul class="el-menu submenu" :class="'submenu-hook-'+it.name+'-'+index" @mouseover="showMenu(index,it.name,true)" @mouseout="showMenu(index,it.name,false)"> 
                                 <li v-for="child_item in it.children" v-if="!child.hidden" :key="child.path" class="el-menu-item" style="padding-left: 40px;" :class="$route.path==child_item.path?'is-active':''" @click="$router.push(child_item.path)">{{child_item.name}}
                                     
                                 </li>
                               </ul>
                             </template>
                             <template v-else>
                               <li class="el-submenu">
                                 <div class="el-submenu__title el-menu-item" style="padding-left: 20px;height: 56px;line-height: 56px;padding: 0 20px;" :class="$route.path==(it.children!=null && it.children[0].path)?'is-active':''" @click="$router.push((it.children!=null && it.children[0].path)? it.children[0].path:"/")"><i :class="it.iconCls"></i></div>
                               </li>
                             </template>
                         </li>
                     </ul> 
                    </template> 


                </li>
              </ul>
            </template>
            <template v-else>
              <li class="el-submenu">
                <div class="el-submenu__title el-menu-item" style="padding-left: 20px;height: 56px;line-height: 56px;padding: 0 20px;" :class="$route.path==item.children[0].path?'is-active':''" @click="$router.push(item.children[0].path)"><i :class="item.iconCls"></i></div>
              </li>
            </template>
        </li>
    </ul> 
</template>

<script type="text/ecmascript-6">
export default {
  name: 'CollapsedTree',
  data() {
    return {
      collapsed:true
    }
  },
  methods:
  {
      //折叠导航栏
      collapse:function(){
        this.collapsed=!this.collapsed;
      },
      showMenu(i,name,status){
        console.log(i+"show show show "+name)
        this.$refs.menuCollapsed.getElementsByClassName('submenu-hook-'+name+'-'+i)[0].style.display=status?'block':'none';
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