<template>
	<el-row class="container">
		<el-col :span="24" class="header">
			<el-col :span="10" class="logo" :class="collapsed?'logo-collapse-width':'logo-width'">
				{{collapsed?'':sysName}}
			</el-col>
			<el-col :span="10">
				<div class="tools" @click.prevent="collapse">
					<i class="fa fa-align-justify"></i>
				</div>
			</el-col>

			<el-col :span="4" class="userinfo">
				<el-dropdown trigger="hover">
					<span class="el-dropdown-link userinfo-inner"><img :src="this.sysUserAvatar" /> {{sysUserName}}</span>
					<el-dropdown-menu slot="dropdown">
						<el-dropdown-item>我的消息</el-dropdown-item>
						<el-dropdown-item>设置</el-dropdown-item>
						<el-dropdown-item divided @click.native="modifyPassword">修改密码</el-dropdown-item>
						<el-dropdown-item divided @click.native="logout">退出登录</el-dropdown-item>
						<el-dropdown-item divided >删除管理员信息</el-dropdown-item>
					    <!-- 删除管理员信息必须要先输密码验证,然后再进行删除! -->
					</el-dropdown-menu>
				</el-dropdown>
			</el-col>
		</el-col>

   
		<el-col :span="24" class="main">  
			<aside :class="collapsed?'menu-collapsed':'menu-expanded'">
 
				<transition v-on:enter="menu_enter" v-on:leave="menu_leave">     
				<el-menu :default-active="$route.path" class="el-menu-vertical-demo" @open="handleopen" @close="handleclose" @select="handleselect" unique-opened router v-show="!collapsed">
					<menu-tree :items="$router.options.routes"></menu-tree>
				</el-menu>
				</transition>
		  	
		  	    <ul class="el-menu el-menu-vertical-demo collapsed" v-show="collapsed" ref="menuCollapsed">
					<li v-for="(item,index) in $router.options.routes" v-if="!item.hidden" class="el-submenu item">
						<template v-if="!item.leaf">
							<div class="el-submenu__title" style="padding-left: 20px;" @mouseover="showMenu(index,true)" @mouseout="showMenu(index,false)"><i :class="item.iconCls"></i></div>
							<ul class="el-menu submenu" :class="'submenu-hook-'+index" @mouseover="showMenu(index,true)" @mouseout="showMenu(index,false)"> 
								<li v-for="child in item.children" v-if="!child.hidden" :key="child.path" class="el-menu-item" style="padding-left: 40px;" :class="$route.path==child.path?'is-active':''" @click="$router.push(child.path)">{{child.name}}</li>
							</ul>
						</template>
						<template v-else>
							<li class="el-submenu">
								<div class="el-submenu__title el-menu-item" style="padding-left: 20px;height: 50px;line-height: 50px;padding: 0 20px;" :class="$route.path==item.children[0].path?'is-active':''" @click="$router.push(item.children[0].path)"><i :class="item.iconCls"></i></div>
							</li>
						</template>
					</li>
				  </ul>   
		      </aside>
				
            <!-- 显示所有界面 -->
            <section class="content-container">
                <div class="grid-content bg-purple-light">

                    <el-col :span="24" class="breadcrumb-container">
                        <strong class="title">{{$route.name}}</strong>
                        <el-breadcrumb separator="/" class="breadcrumb-inner">
                            <el-breadcrumb-item v-for="(item,index) in $route.matched" :key="item.path">
                                {{ item.name }}
                            </el-breadcrumb-item>
                        </el-breadcrumb>
                    </el-col>

             
                    <el-col :span="24" class="content-wrapper">
                        <transition name="fade" mode="out-in">
                        <!-- 对应的组件内容渲染到router-view中 -->
                            <router-view></router-view>
                        </transition>
                    </el-col>
                    
                </div>

            </section>

			<section>
				
			  <el-dialog title="管理员密码修改" v-model="modifyFormVisible" :close-on-click-modal="false">
              <el-form :model="ruleForm2" :rules="rules2" ref="ruleForm2" label-position="left" label-width="150px" class="demo-ruleForm login-container">
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
                
              </el-form>
             <div slot="footer" class="dialog-footer">
             	<el-button @click.native="modifyFormVisible = false">取消</el-button>
             	<el-button type="primary" @click.native.prevent="decide" :loading="modifyLoading">提交</el-button>
             </div>
            </el-dialog>
			</section>


		</el-col>
	</el-row>
</template>

<script>
  
    import {modifyRequest } from '../api/api';
    import MenuTree from './MenuTree';
    import CollapsedTree from './CollapsedTree'; 

	export default {
		data() {
			return {
                items: this.$router.options.routes,

				sysName:'TeamTalk',
				collapsed:false,
				sysUserName: '',
				sysUserAvatar: '',
				form: {
					name: '',
					region: '',
					date1: '',
					date2: '',
					delivery: false,
					type: [],
					resource: '',
					desc: ''
				},
				modifyLoading: false,
				modifyFormVisible: false,
				//密码修改界面数据
				ruleForm2: {
				  username: '',
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

				}	
			}
		},
		created() {
		//这里没有直接使用this.$router.options.routes，是因为addRoute的路由规则，在这里this.$router.options.routes获取不到
		//有兴趣的可以看一下源码，是为什么获取不到，但是却又有规则了 
		//另外在开发的时候，可能由于是热部署，也会不断重复的给items添加元素，造成导航条有重复的，简单解决，可以设置一个开关
		let isLoadNodes = sessionStorage.getItem('isLoadNodes')
		if (!isLoadNodes) {
			let data = JSON.parse(sessionStorage.getItem('routers'))
			this.items.push(...data)
			sessionStorage.setItem('isLoadNodes', 'true')
		}
	},
		methods: {
            menu_enter:function(el,done){

            },
            menu_leave:function(el,done){
                done();
            },
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
			      modifyRequest(modifyParams).then(data => {
			      
			      if(data.code==1){
			      	this.modifyLoading=false;
						this.$message({
							message:'修改管理员密码失败',
							type:'warning'
						});
			      }
			      else if(data.code==0){
			      	this.modifyLoading = false;
			      this.$message({
			                       message: "密码修改成功!",
			                       type: 'success'
			                   });
			      sessionStorage.removeItem('username');
			      sessionStorage.removeItem('userpassword');
			      this.$router.push({ path: '/Login' });
			  }else
			  {
			  	  this.modifyLoading=false;
						this.$message({
							message:'请求密码修改失败',
							type:'error'
						});
			  }
			      
			      });


			    } else {
//			      console.log('error submit!!');
			      return false;
			    }
			  });
			},
			onSubmit() {
				//console.log('submit!');
			},
			handleopen() {
				//console.log('handleopen');
			},
			handleclose() {
				//console.log('handleclose');
			},
			handleselect: function (a, b) {
			},
			//显示修改密码界面
			modifyPassword : function(){

				this.$confirm('确认更改密码吗?', '提示', {
                    type: 'warning'
                }).then(() => {
                
                            this.modifyFormVisible = true;
                            this.ruleForm2={
            				  username: sessionStorage.getItem("username"),
            				  oldPass:'',
            				  newPass:'',
            				  checkPass: ''
            				};

                }).catch(() => { });
               
                

			},
			//退出登录
			logout: function () {
				var _this = this;
				this.$confirm('确认退出吗?', '提示', {
					//type: 'warning'
				}).then(() => {
					sessionStorage.removeItem('username');
					sessionStorage.removeItem('usernamepassword');
					_this.$router.push('/login');
				}).catch(() => {

				});
			},
			//折叠导航栏
			collapse:function(){
				this.collapsed=!this.collapsed;
			},
			showMenu(i,status){
				console.log('show show show '+i);
				this.$refs.menuCollapsed.getElementsByClassName('submenu-hook-'+i)[0].style.display=status?'block':'none';
			}
		},
		mounted() {
			//var user = sessionStorage.getItem('user');
			//if (user) {
				//user = JSON.parse(user);
				//this.sysUserName = user.name || '';
				//this.sysUserAvatar = user.avatar || '';
			//}
			this.sysUserAvatar = 'static/user.png' || '';


		},
		components: {
			MenuTree,
			CollapsedTree
		}
	}

</script>

<style scoped lang="scss">
	@import '~scss_vars';
	
	.container {
		position: absolute;
		top: 0px;
		bottom: 0px;
		width: 100%;
		.header {
			height: 60px;
			line-height: 60px;
			background: $color-primary;
			color:#fff;
			.userinfo {
				text-align: right;
				padding-right: 35px;
				float: right;
				.userinfo-inner {
					cursor: pointer;
					color:#fff;
					img {
						width: 40px;
						height: 40px;
						border-radius: 20px;
						margin: 10px 0px 10px 10px;
						float: right;
					}
				}
			}
			.logo {
				//width:230px;
				height:60px;
				font-size: 22px;
				padding-left:20px;
				padding-right:20px;
				border-color: rgba(238,241,146,0.3);
				border-right-width: 1px;
				border-right-style: solid;
				img {
					width: 40px;
					float: left;
					margin: 10px 10px 10px 18px;
				}
				.txt {
					color:#fff;
				}
			}
			.logo-width{
				width:230px;
			}
			.logo-collapse-width{
				width:60px
			}
			.tools{
				padding: 0px 23px;
				width:14px;
				height: 60px;
				line-height: 60px;
				cursor: pointer;
			}
		}
		.main {
			display: flex;
			// background: #324057;
			position: absolute;
			top: 60px;
			bottom: 0px;
			overflow: hidden;
			aside {
				flex:0 0 230px;
				width: 230px;
				overflow-y: auto;
				&::-webkit-scrollbar {display:none};
				.el-menu{
					height: 100%;
				}
				.collapsed{
					width:60px;
					.item{
						position: relative;

					}
					.submenu{
						position:absolute;
						top:0px;
						left:60px;
						z-index:99999;
						height:auto;
						display:none;
					}
				}
			}

			.menu-collapsed{
				flex:0 0 60px;
				overflow: visible;
				width: 60px;
			}
			.menu-expanded{
				flex:0 0 230px;
				width: 230px;
			}
			.content-container {
				// background: #f1f2f7;
				flex:1;
				// position: absolute;
				// right: 0px;
				// top: 0px;
				// bottom: 0px;
				// left: 230px;
				overflow-y: scroll;
				padding: 20px;
				.breadcrumb-container {
					//margin-bottom: 15px;
					.title {
						width: 200px;
						float: left;
						color: #475669;
					}
					.breadcrumb-inner {
						float: right;
					}
				}
				.content-wrapper {
					background-color: #fff;
					box-sizing: border-box;
				}
			}
		}
	}
</style>