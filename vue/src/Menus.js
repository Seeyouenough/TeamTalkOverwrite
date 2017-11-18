
 let menus =[
    {
        "path": "/",
        "component": "Home",
        "name": "用户管理",
        "iconCls": "el-icon-arrow-right",
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
        "path": "/",
        "component": "Home",
        "name": "组织架构",
        "iconCls": "el-icon-arrow-right",
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
        "name": "群组管理",
        "iconCls": "el-icon-arrow-right",
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
        "name": "系统设置",
        "iconCls": "el-icon-arrow-right",
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
        "iconCls": "el-icon-arrow-right",
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