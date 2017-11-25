/* eslint-disable */
import Vue from 'vue'
import Router from 'vue-router'
const _import = require('./_import_' + process.env.NODE_ENV)
// in development env not use Lazy Loading,because Lazy Loading too many pages will cause webpack hot update too slow.so only in production use Lazy Loading

Vue.use(Router)

/* layout */
import Layout from '../views/layout/Layout'

/**
* icon : the icon show in the sidebar
* hidden : if `hidden:true` will not show in the sidebar
* redirect : if `redirect:noredirect` will no redirct in the levelbar
* noDropdown : if `noDropdown:true` will has no submenu
* meta : { role: ['admin'] }  will control the page role
**/
export const constantRouterMap = [
    { path: '/login', component: _import('login/index'), hidden: true },
    { path: '/authredirect', component: _import('login/authredirect'), hidden: true },
    { path: '/404', component: _import('errorPage/404'), hidden: true },
    { path: '/401', component: _import('errorPage/401'), hidden: true },
  {
    path: '/',
    component: Layout,
    redirect: '/dashboard',
    name: '首页',
    hidden: true,
    children: [{ path: 'dashboard', component: _import('dashboard/index') }]
  },
  {
    path: '/introduction',
    component: Layout,
    redirect: '/introduction/index',
    icon: 'people',
    noDropdown: true,
    children: [{ path: 'index', component: _import('introduction/index'), name: '简述' }]
  }
]

export default new Router({
  // mode: 'history', //后端支持可开
  scrollBehavior: () => ({ y: 0 }),
  routes: constantRouterMap
})
//将每个页面设置一个权限名,每次用户登录,先访问用户表,用户表访问角色表,
//角色表访问权限表
//权限表
//添加角色,一行,一角色对应多权限,访问角色时,将id为该角色的所有权限找出,放到一个数组中
//然后将该数组返回给用户,同时一个用户对应多个角色,将所有的权限判断之后,全部放在一
//个数组中,返回它
export const asyncRouterMap = [
  {
    path: '/permission',
    component: Layout,
    redirect: '/permission/index',
    name: '权限测试',
    icon: 'password',
    meta: { role: ['admin'] },
    noDropdown: true,
    children: [{ path: 'index', component: _import('permission/index'), name: '权限测试页', meta: { role: ['admin'] }}]
  },
  {
    path: '/example',
    component: Layout,
    redirect: 'noredirect',
    name: '综合实例',
    icon: 'example',
    children: [
      {
        path: '/example/table',
        component: _import('example/table/index'),
        redirect: '/example/table/table',
        name: 'Table',
        icon: 'table',
        children: [
          { path: 'dynamictable', component: _import('example/table/dynamictable/index'), name: '动态table' },
          { path: 'dragtable', component: _import('example/table/dragTable'), name: '拖拽table' },
          { path: 'inline_edit_table', component: _import('example/table/inlineEditTable'), name: 'table内编辑' },
          { path: 'table', component: _import('example/table/table'), name: '综合table' }
        ]
      },
      { path: 'form/edit', icon: 'form', component: _import('example/form'), name: '编辑Form', meta: { isEdit: true }},
      { path: 'form/create', icon: 'form', component: _import('example/form'), name: '创建Form' },
      { path: 'tab/index', icon: 'tab', component: _import('example/tab/index'), name: 'Tab' }
    ]
  },
  {
    path: '/member',
    component: Layout,
    redirect: 'noredirect',
    icon: 'email',
    noDropdown: true,
    meta: { role: ['admin','member'] },
    children: [
      { path: 'member', component: _import('function/Member'), name: '用户管理' }
    ]
  },
  {
    path: '/depart',
    component: Layout,
    redirect: 'noredirect',
    icon: 'star',
    noDropdown: true,
    meta: { role: ['admin','depart'] },
    children: [
      { path: 'depart', component: _import('function/Depart'), name: '部门管理' }
    ]
  },
  {
    path: '/group',
    component: Layout,
    redirect: 'noredirect',
    icon: 'drag',
    noDropdown: true,
    meta: { role: ['admin','group'] },
    children: [
      { path: 'group', component: _import('function/Group'), name: '群组管理' }
    ]
  },
  {
    path: '/discovery',
    component: Layout,
    redirect: 'noredirect',
    icon: 'example',
    noDropdown: true,
    meta: { role: ['admin','discovery'] },
    children: [
      { path: 'discovery', component: _import('function/Discovery'), name: '发现管理' }
    ]
  },
  {
    path: '/management',
    component: Layout,
    redirect: 'noredirect',
    name: '管理员权限设置',
    icon: 'lock',
    meta: { role: ['admin'] },
    children: [
      { path: 'admin', component: _import('management/Admin'), icon: 'user',name: '管理员设置' },
      { path: 'power', component: _import('management/Power'), icon: 'user',name: '角色设置' },
      { path: 'role', component: _import('management/Role'), icon: 'user',name: '权限设置' }
    ]
  },
  { path: '*', redirect: '/404', hidden: true }
]
