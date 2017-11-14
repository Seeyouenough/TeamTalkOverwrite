import Login from './views/Login.vue'
import NotFound from './views/404.vue'
import Home from './views/Home.vue'
import Main from './views/Main.vue'
import Member from './views/nav1/Member.vue'
import Depart from './views/nav1/Depart.vue'
import Group from './views/nav1/Group.vue'
import Discovery from './views/nav1/Discovery.vue'
import Add from './views/nav1/addUser.vue'




let routes = [
    {
        path: '/login',
        component: Login,
        name: '',
        hidden: true
    },
    {
        path: '/add',
        component: Add,
        name: '',
        hidden: true
    },
    /*{
        path: '/modify',
        component: Modify,
        name: '',
        hidden: true
    },*/
    {
        path: '/404',
        component: NotFound,
        name: '',
        hidden: true
    },
    //{ path: '/main', component: Main },
    {
        path: '/',
        component: Home,
        name: '用户管理',
        iconCls: 'el-icon-arrow-right',//图标样式class
        //leaf: true,
        children: [
            { path: '/main', component: Main, name: '主页', hidden: true },
            { path: '/Member', component: Member, name: '用户管理' }
        ]
    },
    {
        path: '/',
        component: Home,
        name: '组织架构',
        iconCls: 'el-icon-arrow-right',
        //leaf: true,
        children: [
            { path: '/Depart', component: Depart, name: '组织架构' }
        ]
    },
    {
        path: '/',
        component: Home,
        name: '群组管理',
        iconCls: 'el-icon-arrow-right',
        //leaf: true,//只有一个节点
        children: [
            { path: '/Group', component: Group, name: '群组管理' }
        ]
    },
    {
        path: '/',
        component: Home,
        name: '系统设置',
        iconCls: 'el-icon-arrow-right',
        //leaf: true,
        children: [
            { path: '/Discovery', component: Discovery, name: '系统设置' }
        ]
    },
    {
        path: '*',
        hidden: true,
        redirect: { path: '/404' }
    }
];

export default routes


/*
let routes = [
    {
        path: '/login',
        component: Login,
        name: '',
        hidden: true
    },
    {
        path: '/404',
        component: NotFound,
        name: '',
        hidden: true
    },
    //{ path: '/main', component: Main },
    {
        path: '/',
        component: Home,
        name: '用户管理',
        iconCls: 'el-icon-message',//图标样式class
        children: [
            { path: '/main', component: Main, name: '主页', hidden: true },
            { path: '/Table', component: Table, name: 'Table' },
            { path: '/form', component: Form, name: 'Form' },
            { path: '/user', component: user, name: '列表' },
        ]
    },
    {
        path: '/',
        component: Home,
        name: '组织架构',
        iconCls: 'fa fa-id-card-o',
        children: [
            { path: '/page4', component: Page4, name: '页面4' },
            { path: '/page5', component: Page5, name: '页面5' }
        ]
    },
    {
        path: '/',
        component: Home,
        name: '群组管理',
        iconCls: 'fa fa-address-card',
        leaf: true,//只有一个节点
        children: [
            { path: '/page6', component: Page6, name: '导航三' }
        ]
    },
    {
        path: '/',
        component: Home,
        name: '系统设置',
        iconCls: 'fa fa-bar-chart',
        children: [
            { path: '/echarts', component: echarts, name: 'echarts' }
        ]
    },
    {
        path: '*',
        hidden: true,
        redirect: { path: '/404' }
    }
];*/