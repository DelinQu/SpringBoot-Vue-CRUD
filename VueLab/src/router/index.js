import Vue from 'vue'
import VueRouter from 'vue-router'
import UserManage from '../views/UserManage.vue'
import AddUser from '../views/AddUser.vue'
import PersonManage from '../views/PersonManage.vue'
import AddPerson from '../views/AddPerson.vue'
import index from '../index.vue'
import SearchPerson from '../views/SearchPerson'
import SearchUser from '../views/SearchUser'


Vue.use(VueRouter)
//路由
const routes = [
  {
    path:'/',
    name:'用户',
    component:index,
    redirect:'/UserManage',
    children:[
      {
        path: '/UserManage',
        name: '用户数据库',
        component: UserManage,
        children:[]
      },
      {
        path: '/AddUser',
        name: '添加用户',
        component: AddUser
      },
      {
        path: '/SearchUser',
        name: '搜索用户',
        component: SearchUser
      }
    ]
  },
  {
    path:'/',
    name:'人员',
    component:index,
    redirect:'/PersonManage',
    children:[
      {
        path: '/PersonManage',
        name: '人员数据库',
        component: PersonManage,
        children:[]
      },
      {
        path: '/AddPerson',
        name: '添加人员',
        component: AddPerson
      },
      {
        path: '/SearchPserson',
        name: '搜索人员',
        component: SearchPerson
      }
    ]
  },
  
]
const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
