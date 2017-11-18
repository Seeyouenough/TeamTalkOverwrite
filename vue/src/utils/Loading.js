export default (name) => () => import(`@/views/${name}.vue`)
