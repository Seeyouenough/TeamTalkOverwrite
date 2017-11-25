
const _import = require('./_import')

export default (routerdata) => {
  generaMenu(routerdata)
}

function generaMenu(routerdata){

  routerdata.forEach( item =>{
      item.component=_import(item.path)
      item.path=item.path+item.id
      delete item.id
      
      if (item.children && item.children instanceof Array) { 
            generaMenu(item.children);
      }
  })
}