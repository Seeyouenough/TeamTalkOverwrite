import jsonFormatTree from '@/utils/jsonFormatTree'
import MenuUtils from '@/utils/MenuUtils'
import { constantRouterMap } from '@/router'
import { getToken } from '@/utils/auth'
import menu from '@/menu'


const getRoutes = {
    state: {
      routers: constantRouterMap,
      addRouters: [],
      token:getToken()
    },
    mutations: {
        SET_ROUTERS: (state, routers) => {
          state.addRouters = routers
          state.routers = constantRouterMap.concat(routers)
        }
    },
    actions:{
        generatorRoutes( { commit,state }){
        return new Promise((resolve, reject) => {
            
            let routes_ex=[]
            jsonFormatTree(routes_ex,menu)
          
            MenuUtils(routes_ex)

            commit('SET_ROUTERS', routes_ex)

            console.log("完成")
            resolve()
           /* getRoute(state.token).then(response => {
              let {code, msg, data}=response.data
              let routes_ex=[]
              jsonFormatTree(routes_ex,data)

              MenuUtils(routes_ex)
              
              commit('SET_ROUTERS', routes_ex)

              resolve(response)
            }).catch(error =>{
               reject(error)
            })*/
        })
        }
    }



}

export default getRoutes
