<template>
  <el-form :model="ruleForm2" :rules="rules2" ref="ruleForm2" label-position="left" label-width="150px" class="demo-ruleForm login-container">
    <h3 class="title">管理员密码修改</h3>
    <el-form-item label="管理员账号信息:" prop="username">
      <el-input type="text" :disabled="true" v-model="ruleForm2.username" auto-complete="off" ></el-input>
    </el-form-item>
    <el-form-item label="请输入旧密码:" prop="oldPass">
      <el-input type="password" v-model="ruleForm2.oldPass" auto-complete="off" placeholder="请输入旧密码"></el-input>
    </el-form-item>
    <el-form-item label="请输入新密码:" prop="newPass">
      <el-input type="password" v-model="ruleForm2.newPass" auto-complete="off" placeholder="请输入新密码"></el-input>
    </el-form-item>
    <el-form-item label="请再次输入新密码:" prop="checkPass">
      <el-input type="password" v-model="ruleForm2.checkPass" auto-complete="off" placeholder="请再次输入新密码"></el-input>
    </el-form-item>
    <el-form-item style="width:100%;">
      <el-button type="primary" style="width:100%;" @click.native.prevent="decide" :loading="modifyLoading">提交</el-button>
      <!--<el-button @click.native.prevent="handleReset2">重置</el-button>-->
    </el-form-item>
  </el-form>
</template>

<script>
  import {modifyRequest} from '../api/api';
  //import NProgress from 'nprogress'
  export default {
    data() {
      return {
        modifyLoading: false,
        ruleForm2: {
          username: sessionStorage.getItem("username"),
          oldPass:'',
          newPass:'',
          checkPass: ''
        },
        rules2: {
          oldPass: [
            { required: true, message: '请输入旧密码', trigger: 'blur' },

          ],
          newPass: [
            { required: true, message: '请输入新密码', trigger: 'blur' },
            
          ],
          checkPass: [
            { required: true, message: '请再次输入新密码', trigger: 'blur' },
            
          ]

        },
        checked: true
      };
    },
    methods: {
      // handleReset2() {
      //   this.$refs.ruleForm2.resetFields();
      // },
      decide(){
            if(this.ruleForm2.oldPass!=sessionStorage.getItem("userpassword")){
              this.$message({
                               message: "旧密码错误,请重新输入!",
                               type: 'warning'
                              });
            }else{
              if(this.ruleForm2.newPass!=this.ruleForm2.checkPass){
                 this.$message({
                  message: "两次新密码输入不一致,请重新输入!",
                  type: 'warning'
                 });
              }else{
                 this.handleSubmit2();
              }
            }
      },
      handleSubmit2() {
        this.$refs.ruleForm2.validate((valid) => {
          if (valid) {
            this.modifyLoading = true;
            //NProgress.start(); 
            var modifyParams = { uname: this.ruleForm2.username, pwd: this.ruleForm2.checkPass };
            //console.log(modifyParams);
            modifyRequest(modifyParams).then((res) => {
            
            this.modifyLoading = false;
            this.$message({
                             message: "密码修改成功!",
                             type: 'success'
                         });
            sessionStorage.removeItem('username');
            sessionStorage.removeItem('userpassword');
            this.$router.push({ path: '/Login' });
            });
          } else {
            console.log('error submit!!');
            return false;
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