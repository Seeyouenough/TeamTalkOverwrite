<template>
 
     <el-form :model="register" label-width="150px" label-position="left" :rules="registerRules" ref="register" class="demo-ruleForm login-container">
        <h3 class="title">管理员注册</h3>

          <el-form-item  label="管理员账号">
          <el-input type="text" v-model="register.addName" auto-complete="off"></el-input>
          
        </el-form-item>

        <el-form-item  label="请输入新密码">
          <el-input type="password" v-model="register.addPassword" auto-complete="off"></el-input>
          
        </el-form-item>

        <el-form-item  label="请再次输入新密码">
          <el-input type="password" v-model="register.addPasswordAgain" auto-complete="off"></el-input>
          
        </el-form-item>

        <el-form-item style="width:100%;">
        <el-button type="primary"  @click.native.prevent="goback">取消</el-button>
        <el-button type="primary" @click="checkPassword" >提交</el-button>
        </el-form-item>

      </el-form>


</template>

<script>
  import {addRequest} from '../api/api';
  //import NProgress from 'nprogress'
  export default {
    data() {
      return {
        register:{
          addName : '',
          addPassword : '',
          addPasswordAgain :''
        },
        registerRules:{
          addName: [
            { required: true, message: '请输入账号', trigger: 'blur' },
          ],
          addPassword: [
            { required: true, message: '请输入密码', trigger: 'blur' },
          ],
          addPasswordAgain: [
            { required: true, message: '请再次输入密码', trigger: 'blur' },
          ]
        },
        checked: true
      };
    },
    methods: {
      goback(){
       this.$router.push({ path: '/login' });
      },
      checkPassword(){
              if(this.register.addPassword!=this.register.addPasswordAgain)
                   {
                     this.$message({
                     message: '两次密码不一致',
                     type: 'warning'
                });
                   }
                   else{
                    this.addUser();
                   }
                   
      },
      addUser:function(){
          this.$refs.register.validate((valid) => {
          if (valid) {
            this.$confirm('确认提交吗？', '提示', {}).then(() => {
              this.registerLoading = true;
              //NProgress.start();
              this.register.addPassword=this.md5(this.register.addPasswordAgain);
              let para ={uname:this.register.addName,pwd:this.register.addPassword};
              //para.birth = (!para.birth || para.birth == '') ? '' : util.formatDate.format(new Date(para.birth), 'yyyy-MM-dd');
              addRequest(para).then(data => {
            
                if(data.code==0){
                  this.$message({
                  message: '提交成功',
                  type: 'success'
                });
                this.$router.push({ path: '/login' });
                }
                else if(data.code==1){
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
                
                this.$refs['register'].resetFields();
              });
            });
          }
        });
      }
    }
  }

</script>

<style lang="scss" scoped>
  .login-container {
    /*box-shadow: 0 0px 8px 0 rgba(0, 0, 0, 0.06), 0 1px 0px 0 rgba(0, 0, 0, 0.02);*/
    -webkit-border-radius: 5px;
    border-radius: 5px;
    -moz-border-radius: 5px;
    background-clip: padding-box;
    margin: 180px auto;
    width: 350px;
    padding: 35px 35px 15px 35px;
    background: #fff;
    border: 1px solid #eaeaea;
    box-shadow: 0 0 25px #cac6c6;
    .title {
      margin: 0px auto 40px auto;
      text-align: center;
      color: #505458;
    }
    .remember {
      margin: 0px 0px 35px 0px;
    }
  }
</style>