<template >
  <div id="app">
    <div style="height: 50px;margin-top:5px;margin-left:10px;margin-buttom:0;">
      <el-breadcrumb separator="/" >
      
        <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
        <el-breadcrumb-item>  
          <i class="el-icon-upload" style="color:#66CDAA;"><a href="/">屈德林的实验信息管理系统</a> </i>
        </el-breadcrumb-item>
        
      </el-breadcrumb>
    </div>
    
    <el-header >屈德林的实验信息管理系统 <i class="el-icon-s-promotion" style="color:#66CDAA;"></i></el-header>
    <el-container style="height: 440px; border: 1px solid #eee">
      <el-aside width="200px" style="background-color: rgb(238, 241, 246)">
        <el-menu router :default-openeds="['0','1']">
          <el-submenu v-for="(item,index) in $router.options.routes" :index="index+''" :key="index">
            
            <template slot="title"><!-- 设置菜单图标 -->
              <i class="el-icon-message" style="color:#67C23A"></i>{{item.name+''}}
            </template>

          <el-menu-item v-for="(item2,index2) in item.children" :index="item2.path" 
          :class="$route.path==item2.path?'is-active':''" :key="index2">{{item2.name}}</el-menu-item>

          </el-submenu>
        </el-menu>
      </el-aside>
        <el-main>
            <router-view></router-view>
        </el-main>
    </el-container>
  </div>
</template>

<script>

import HelloWorld from "./components/HelloWorld.vue";

export default {
  name: "app",
  components: {
    HelloWorld
  },
  data() {
    const item = {
      date: "2016-05-02",
      name: "王小虎",
      address: "上海市普陀区金沙江路 1518 弄"
    };
    return {
      tableData: Array(20).fill(item)
    };
  }
};
</script>

<style>
#app {
  font-family: "Avenir", Helvetica, Arial, sans-serif;
  color:#66CDAA;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 0px;
}
.el-header {
  background-color:	#F2F6FC;
  color: 	#409EFF;
  line-height: 60px;
}

.el-aside {
  color: #333;
}
</style>
