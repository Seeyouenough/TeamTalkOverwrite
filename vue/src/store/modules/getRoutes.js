import jsonFormatTree from '@/utils/jsonFormatTree'
import MenuUtils from '@/utils/MenuUtils'
import { constantRouterMap } from '@/router'
import { getToken } from '@/utils/auth'
import { getRoute } from '@/api/power'
/*import menu from '@/menu'*/


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
          //state.routers = routers
        }
    },
    actions:{
        generatorRoutes( { commit},token){

        return new Promise((resolve, reject) => {
            
            /*let routes_ex=[]
            jsonFormatTree(routes_ex,menu)
            MenuUtils(routes_ex) 
         
            commit('SET_ROUTERS', routes_ex)
            
            resolve()*/
            getRoute(token).then(response => {
              let {code, msg, data}=response.data
              let power_router=JSON.parse(data).power
            
              let routes_ex=[]
              jsonFormatTree(routes_ex,power_router)

              MenuUtils(routes_ex)
              
              commit('SET_ROUTERS', routes_ex)

              resolve(response)
            }).catch(error =>{
               reject(error)
            })
        })
        }
    }



}

export default getRoutes
