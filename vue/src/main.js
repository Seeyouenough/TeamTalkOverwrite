import babelpolyfill from 'babel-polyfill'
import Vue from 'vue'
import App from './App'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-default/index.css'
//import './assets/theme/theme-green/index.css'
import VueRouter from 'vue-router'
import store from './vuex/store'
import Vuex from 'vuex'
//import NProgress from 'nprogress'
//import 'nprogress/nprogress.css'
import routes from './routes'
import MenuUtils from '@/utils/MenuUtils'

//Mock.bootstrap();
import 'font-awesome/css/font-awesome.min.css'

import md5 from 'js-md5';
 
Vue.prototype.md5 = md5;


Vue.use(ElementUI)
Vue.use(VueRouter)
Vue.use(Vuex)

const router = new VueRouter({
      routes
})


let data = JSON.parse(sessionStorage.getItem('routers'));
if (data){
  //这里是防止用户手动刷新页面，整个app要重新加载,动态新增的路由，会消失，所以我们重新add一次
  let routes = []
  MenuUtils(routes,data);
  router.addRoutes(routes);
  sessionStorage.removeItem('isLoadNodes');
}

router.beforeEach((to, from, next) => {
  //console.log("111111111111111"+to.path)
  //NProgress.start();
let data = JSON.parse(sessionStorage.getItem('routers'));

  if (!data&&to.path == '/login') {
    sessionStorage.removeItem('username');
    sessionStorage.removeItem('usernamepassword');
    sessionStorage.removeItem('routers');
    sessionStorage.removeItem('isLoadNodes');
  }
  if (to.path) {
    next()
  } else {
    next({ path: '/404' })
  }
  //let user = JSON.parse(sessionStorage.getItem('username'));
  let user=sessionStorage.getItem('username');
  if (!user &&(to.path != '/login')&&(to.path != '/add') ) {
    next({ path: '/login' })
  } else {
    next()
  }
})


new Vue({
  //el: '#app',
  //template: '<App/>',
  router,
  store,
  //components: { App }
  render: h => h(App)
}).$mount('#app')

