
 let menus =[
    {
        "path": "/",
        "component": "Home",
        "name": "用户",
        "iconCls": "el-icon-menu",
        "leaf": false,
        "children": [
            { 
                "path": "Member", 
                "component": "Member",
                "name": "用户管理",
                "leaf": true
            }
        ]
    },
    {
      "path": '/example',
      "component": "Home",
      "redirect": 'noredirect',
      "iconCls": "el-icon-circle-check",
      "name": '综合实例',
      "leaf": false,
      "icon": 'example',
      "children": [
        {
          "path": '/example/table',
          "component": 'example/table/index',
          "redirect": '/table',
          "leaf": false,
          "iconCls": "el-icon-circle-check",
          "name": 'Table',
          "children": [
            { 
                "path": '/example/table/dynamictable', "component": 'example/table/dynamictable/index',  "name": '动态table', "leaf": true 
            },
            { 
                "path": '/example/table/dragtable', "component": 'example/table/dragTable', "name": '拖拽table',"leaf": true 
            },
            { 
                "path": '/example/table/inline_edit_table', "component": 'example/table/inlineEditTable', "name": 'table内编辑',"leaf": true 
            },
            { 
                "path": '/example/table/table', "component": 'example/table/table', "name": '综合table',"leaf": true 
            }
          ]
        },
        { path: 'form/edit', icon: 'form', component: 'example/form', name: '编辑Form', meta: { isEdit: true },"leaf": true },
        { path: 'form/create', icon: 'form', component:'example/fo', name: '创建Form' ,"leaf": true },
        { path: 'tab/index', icon: 'tab', component: 'example/tab/index', name: 'Tab' ,"leaf": true }
      ]
    },
    {
        "path": "/",
        "component": "Home",
        "name": "架构",
        "iconCls": "el-icon-message",
        "leaf": false,
        "children": [
            { 
                "path": "Depart", 
                "component":"Depart",
                "leaf": true,
                "name": "组织架构"
            }
        ]
    },
    {
        "path": "/",
        "component": "Home",
        "name": "群组",
        "iconCls": "el-icon-star-on",
        "leaf": false,
        "children": [
            { 
                "path": "Group", 
                "component": "Group",
                "leaf": true, 
                "name": "群组管理" 
            }
        ]
    },
    {
        "path": "/",
        "component": "Home",
        "name": "系统",
        "iconCls": "el-icon-setting",
        "leaf": false,
        "children": [
            { 
                "path": "Discovery", 
                "component": "Discovery",
                "leaf": true,
                "name": "系统设置" 
            }
        ]
    },
    {
        "path": "/",
        "component": "Home",
        "name": "管理员权限设置",
        "iconCls": "el-icon-menu",
        "leaf": false,
        "children": [
            { 
                "path": "Admin", 
                "component": "Admin",
                "leaf": true,
                "name": "管理员设置" 
            },
            { 
                "path": "Role", 
                "component": "Role",
                "leaf": true,
                "name": "角色设置" 
            },
            { 
                "path": "Power", 
                "component": "Power",
                "leaf": true,
                "name": "权限设置" 
            }
        ]
    }

  ]

  export default menus