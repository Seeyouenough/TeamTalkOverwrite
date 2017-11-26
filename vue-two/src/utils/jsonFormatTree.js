

export default(routers_ex,routers_data) => {
      //let routers =JSON.parse(routers_data)
      
      let jsonData=[]
      routers_data.forEach(item =>{
              /*let router_info={
                  id:item.power_id,
                  pid:item.parent_id,
                  name:item.power_name,
                  path:item.power_url,
                  hidden: false,
                  iconCls:"el-icon-menu",
                  leaf:true,
              }*/
              let router_info={
                  id:item.id,
                  pid:item.pid,
                  name:item.name,
                  path:item.path,
                  com:item.path,
                  hidden: false,
                  icon:"star",
                  leaf:true,
              }
              if(router_info.pid == 0){
                router_info.path="/"+router_info.path
              }
              
              jsonData.push(router_info)
      });

     transData(routers_ex,jsonData,'id','pid','children')

}


function transData(r,a, idStr, pidStr, chindrenStr){    
    var hash = {}, id = idStr, pid = pidStr;
    var children = chindrenStr, i = 0, j = 0, len = a.length;
    

    for(; i < len; i++){    
        hash[a[i][id]] = a[i];    
    }    
    for(; j < len; j++){    
        var aVal = a[j], hashVP = hash[aVal[pid]];
        
        if(hashVP){    

            if(!hashVP[children]){
                hashVP[children] = []
                hashVP.leaf = false 
            }

            delete aVal.id
            delete aVal.pid

            hashVP[children].push(aVal);

        }else{
            /*if(aVal.children.length == 1){
              aVal.leaf = false
              console.log(aVal)
            }*/

            delete aVal.id
            delete aVal.pid   
            r.push(aVal);   

        }    
    }    
    return r;    
}  