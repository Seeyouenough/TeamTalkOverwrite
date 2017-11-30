<template>
    <section>
        <!--工具条-->
        <el-col :span="24" class="toolbar" style="padding-bottom: 0px;">
            <el-form :inline="true" :model="filters">
                <el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="handleAdd">新增</el-button>
                </el-form-item>
                </el-form-item>
            </el-form>
        </el-col>

        <!--列表-->
        <el-table :data="users" highlight-current-row v-loading="listLoading" @selection-change="selsChange" style="width: 100%" >
            <el-table-column type="selection" width="55">
            </el-table-column>

            <el-table-column type="index" label="ID" width="60">
            </el-table-column>

            <el-table-column prop="name" label="姓名" width="100" sortable>
            </el-table-column> 
            
            <el-table-column prop="introduction" label="介绍" width="100" sortable>
            </el-table-column> 
            
            <el-table-column label="操作" width="200">
                <template scope="scope">

                    <el-button size="small" @click="EditPassword(scope.$index, scope.row)">改密</el-button>

                    <el-button size="small" @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
                  
                    <!-- <el-button size="small" @click="handleEdit(scope.$index, scope.row)">编辑</el-button> -->
                    <el-button type="danger" size="small" @click="handleDel(scope.$index, scope.row)">删除</el-button>

                </template>
            </el-table-column>
        </el-table>

        <!--工具条-->
        <el-col :span="24" class="toolbar">
            <el-button type="danger" @click="batchRemove" :disabled="this.sels.length===0">批量删除</el-button>
            <el-pagination layout="prev, pager, next" @current-change="handleCurrentChange" :page-size="20" :total="total" style="float:right;">
            </el-pagination>
        </el-col>

        <!--编辑界面-->
        <el-dialog title="编辑" v-model="editFormVisible" :close-on-click-modal="false">
            <el-form :model="editForm" label-width="80px" :rules="editFormRules" ref="editForm">
                <el-form-item label="姓名" prop="name">
                    <el-input v-model="editForm.name" ></el-input>
                </el-form-item>
                
                <el-form-item label="介绍" prop="name">
                    <el-input v-model="editForm.name" ></el-input>
                </el-form-item>

            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click.native="editFormVisible = false">取消</el-button>
                <el-button type="primary" @click.native="editSubmit" :loading="editLoading">提交</el-button>
            </div>
        </el-dialog>

        <!--新增界面-->
        <el-dialog title="新增" v-model="addFormVisible"  :close-on-click-modal="false" >
            <el-form :model="addForm" label-width="80px" :rules="addFormRules" ref="addForm">
                <el-form-item label="姓名">
                    <el-input v-model="addForm.name" auto-complete="off"></el-input>
                </el-form-item>


                <el-form-item  label="密码">
                    <el-input type="password" v-model="addForm.password" auto-complete="off"></el-input>
                </el-form-item>

                <el-form-item label="介绍">
                    <el-input v-model="addForm.introduction" auto-complete="off"></el-input>
                </el-form-item>

            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click.native="addFormVisible = false">取消</el-button>
                <el-button type="primary" @click.native="addSubmit" :loading="addLoading">提交</el-button>
            </div>
        </el-dialog>


        <!--改密界面-->
        <el-dialog title="改密" v-model="editPasswordVisible" :close-on-click-modal="false">
            <el-form :model="editPassword" label-width="150px" :rules="editPasswordRules" ref="editPassword">



                <el-form-item  label="管理员信息">
                    <el-input type="text" :disabled="true" v-model="editPassword.name" auto-complete="off"></el-input>
                    
                </el-form-item>

                <el-form-item  label="请输入新密码">
                    <el-input type="password" v-model="editPassword.password" auto-complete="off"></el-input>
                    
                </el-form-item>

                <el-form-item  label="请再次输入新密码">
                    <el-input type="password" v-model="editPassword.passwordagain" auto-complete="off"></el-input>
                    
                </el-form-item>

            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click.native="editPasswordVisible = false">取消</el-button>
                <el-button type="primary" @click.native="decidePassword" :loading="editPasswordLoading">提交</el-button>
            </div>
        </el-dialog> 

    </section>
</template>

<script >
    import util from '@/common/js/util'
    //import md5 from 'js-md5';
    //import NProgress from 'nprogress'
    import { listManagerRequest,addManagerRequest,removeManagerRequest,updateManagerRequest,updatePasswordRequest } from '@/api/manager'
    
  
    export default {
        data() {
            return {
                filters: {
                    name: ''
                },
                users: [],
                total: 0,
                page: 1,


                listLoading: false,
                sels: [],//列表选中列

                editFormVisible: false,//编辑界面是否显示
                editLoading: false,
                editFormRules: {
                    name: [
                        { required: true, message: '请输入姓名', trigger: 'blur' }
                    ]
                },
                //编辑界面数据
                editForm: {
                    id: 0,
                    name: '',
                    introduction: ''
                },

                addFormVisible: false,//新增界面是否显示
                addLoading: false,
                addFormRules: {
                    name: [
                        { required: true, message: '请输入姓名', trigger: 'blur' }
                    ],
                    password: [
                        { required: true, message: '请输入密码', trigger: 'blur' }
                    ]
                },
                //新增界面数据
                addForm: {
                    name : '',
                    password : '',
                    introduction : ''
                },

                editPasswordVisible: false,//改密界面是否显示
                editPasswordLoading: false,//改密界面是否加载
                editPasswordRules:{        //改密界面文本框填写规则
                    
                    password: [
                        { required: true, message: '请输入新密码', trigger: 'blur' }
                    ],
                    passwordagain: [
                        { required: true, message: '请再次输入新密码', trigger: 'blur' }
                    ]
                },
                editPassword : {  //改密界面数据
                    id:'',
                    name:'',
                    password:'',
                    passwordagain:''
                }

            }
        },
        methods: {
            decidePassword (){
              
                   if(this.editPassword.password!=this.editPassword.passwordagain)
                   {
                     this.$message({
                                    message: '两次密码不一致',
                                    type: 'warning'
                                });
                   }
                   else{
                    this. passwordSubmit();
                   }
                   
            },
            handleCurrentChange(val) {
                this.page = val;
                this.getUsers();
            },
            //获取用户列表
            getUsers() {
                let para = {
                    page: this.page,
                    name: this.filters.name
                };
                this.listLoading = true;
                //NProgress.start();
                
                

                listManagerRequest(para).then(data => {
                    if(data.data.code==1){
                        this.users=[];
                        this.total=0;
                        this.listLoading = false;
                    }
                    else if (data.data.code==0)
                    {  
                       this.users = JSON.parse(data.data.data).user;
                       this.total = this.users.length; 
                       this.listLoading = false;
                    }
                    else{
                        this.listLoading=false;
                        this.$message({
                            message:'获取用户数据失败',
                            type:'warning'
                        });
                    } 
                    
                    //NProgress.done();
                });
            },
            //删除
            handleDel: function (index, row) {
                this.$confirm('确认删除该记录吗?', '提示', {
                    type: 'warning'
                }).then(() => {
                    this.listLoading = true;
                    //NProgress.start();
                 
                    let para =[];
                    para.push({id:row.id});   

                    removeManagerRequest(para).then(data => {
                        this.listLoading = false;
                        //NProgress.done();
                        if(data.data.code==0){
                                this.$message({
                                message: '删除成功',
                                type: 'success'
                            });
                            }
                            else if(data.data.code==1){
                                this.$message({
                                message: '删除失败',
                                type: 'warning'
                            });
                            }
                            else {
                                    this.$message({
                                    message: '服务器或网络错误',
                                    type: 'error'
                                });
                            }
                        this.getUsers();
                    });
                }).catch(() => {

                });
            },
            //显示编辑界面
            handleEdit: function (index, row) {
                this.editFormVisible = true;

                this.editForm = Object.assign({}, row);
                /*this.editForm ={
                    name: '',
                    password:'',
                    sex: -1,
                    age: 0,
                    birth: '',
                    email: ''
                };*/
            },
            //显示改密界面
            EditPassword: function (index, row) {
                this.$confirm('确认更改密码吗?', '提示', {
                    type: 'warning'
                }).then(() => {
                
                    this.editPasswordVisible = true;
                    this.editPassword={
                        name:'',
                        password:'',
                        passwordagain:''
                    };
                    this.editPassword.name=row.name;
                    this.editPassword.id=row.id;

                }).catch(() => { });

            },
            //显示新增界面
            handleAdd: function () {
                this.addFormVisible = true;
                this.addForm = {
                    name: '',
                    password:'',
                    introduction:''
                };
            },
            //编辑
            editSubmit: function () {
                this.$refs.editForm.validate((valid) => {
                    if (valid) {
                        this.$confirm('确认提交吗？', '提示', {}).then(() => {
                            this.editLoading = true;
                            //NProgress.start();
                            //this.editForm.password=this.md5(this.editForm.password);
                            let para = Object.assign({}, this.editForm);
                            //para.birth = (!para.birth || para.birth == '') ? '' : util.formatDate.format(new Date(para.birth), 'yyyy-MM-dd');
                            updateManagerRequest(para).then(data => {
                                this.editLoading = false;
                                //NProgress.done();
                                if(data.data.code==0){
                                   this.$message({
                                    message: '提交成功',
                                    type: 'success'
                                });
                                }
                                else if(data.data.code==1){
                                    this.$message({
                                    message: '提交失败',
                                    type: 'warning'
                                });
                                }
                                else {
                                    this.$message({
                                        message: '服务器或网络错误',
                                        type: 'error'
                                    });
                                }
                                this.$refs['editForm'].resetFields();
                                this.editFormVisible = false;
                                this.getUsers();
                            });
                        });
                    }
                });
            },
            //新增
            addSubmit: function () {
                this.$refs.addForm.validate((valid) => {
                    if (valid) {
                        this.$confirm('确认提交吗？', '提示', {}).then(() => {
                            this.addLoading = true;
                            //NProgress.start();
                            this.addForm.password=this.md5(this.addForm.password);
                            let para = Object.assign({}, this.addForm);
                            //para.birth = (!para.birth || para.birth == '') ? '' : util.formatDate.format(new Date(para.birth), 'yyyy-MM-dd');
                            addManagerRequest(para).then(data => {
                                
                                this.addLoading = false;
                                if(data.data.code==0){
                                   this.$message({
                                    message: '提交成功',
                                    type: 'success'
                                });
                                }
                                else if(data.data.code==1){
                                    this.$message({
                                    message: '提交失败',
                                    type: 'warning'
                                });
                                }
                                else {
                                    this.$message({
                                        message: '服务器或网络错误',
                                        type: 'error'
                                    });
                                }
                                
                                this.$refs['addForm'].resetFields();
                                this.addFormVisible = false;
                                this.getUsers();
                            });
                        });
                    }
                });
            },
            //改密
            passwordSubmit: function () {
                this.$refs.editPassword.validate((valid) => {
                    if (valid) {
                        this.$confirm('确认提交吗？', '提示', {}).then(() => {
                            this.editPasswordLoading = true;
                            
                          
                            this.editPassword.password=this.md5(this.editPassword.password);  
                            //console.log(this.editPassword.id);
                            
                            let para ={id:this.editPassword.id,password:this.editPassword.password};
               
                            //console.log("内容是:"+para);
                            updatePasswordRequest(para).then(data => {

                                this.editPasswordLoading = false;
                                //NProgress.done();
                                if(data.data.code==0){
                                   this.$message({
                                    message: '提交成功',
                                    type: 'success'
                                });
                                }
                                else if(data.data.code==1){
                                    this.$message({
                                    message: '提交失败',
                                    type: 'warning'
                                });
                                }
                                else {
                                    this.$message({
                                        message: '服务器或网络错误',
                                        type: 'error'
                                    });
                                }
                                this.$refs['editPassword'].resetFields();
                                this.editPasswordVisible = false;
                                this.getUsers();
                            });
                        });
                    }
                });
            },
            selsChange: function (sels) {
                this.sels = sels;
            },
            //批量删除
            batchRemove: function () {
                //console.log(this.sels.map(item => item.id));
                //var ids = this.sels.map(item => item.id).toString();
                
                let idss=[ ];
                let sss =this.sels.map(item => item.id);
                for(var key in sss)  
                {
                     idss.push({id:sss[key]})  ;  
                };

                //console.log(idss);

            
                this.$confirm('确认删除选中记录吗？', '提示', {
                    type: 'warning'
                }).then(() => {
                    this.listLoading = true;
                    //NProgress.start();
                    removeManagerRequest(idss).then(data => {
                        this.listLoading = false;
                        //NProgress.done();
                        if(data.data.code==0){
                                this.$message({
                                message: '删除成功',
                                type: 'success'
                            });
                            }
                            else if(data.data.code==1){
                                this.$message({
                                message: '删除失败',
                                type: 'warning'
                            });
                            }
                            else {
                                this.$message({
                                message: '服务器或网络错误',
                                type: 'error'
                                });
                                }
                        this.getUsers();
                    });
                }).catch(() => {

                });
            }
        },
        mounted() {
            this.getUsers();
        }
    }

</script>


<style  scoped>
       

</style>