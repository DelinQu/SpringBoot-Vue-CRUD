<template>
  <div>
<div style="margin-top: 15px;">
  <el-input placeholder="请输入内容" v-model="searchkey" class="input-with-select" >
    <el-select v-model="select" slot="prepend" placeholder="请选择" style="color:#409EFF">
      <el-option label="模糊查询" value="1"></el-option>
      <el-option label="精确搜索" value="2"></el-option>
    </el-select>
    <el-button slot="append" icon="el-icon-search" style="color:#67C23A" @click="submitSearch()"></el-button>
  </el-input>
</div>
    <el-table :data="tableData" border style>

      <el-table-column prop="username" label="用户名" width="150" align="center"></el-table-column>
      <el-table-column prop="pass" label="密码" width="150" align="center"></el-table-column>

      <el-table-column label="操作" width="100" align="center">
        <template slot-scope="scope">
          <el-button @click="edit(scope.row)" type="text" size="small">修改</el-button>
          <el-button type="text" size="small" @click="del(scope.row)">删除</el-button>
          <!-- 模态块 -->
          <el-dialog title="修改用户信息" :visible.sync="dialogFormVisible">
            <el-form
              :model="ruleForm"
              :rules="rules"
              ref="ruleForm"
              label-width="100px"
              class="demo-ruleForm"
            >
              <el-form-item label="用户名" prop="username">
                <el-input v-model="ruleForm.username" :disabled="edit"></el-input>
              </el-form-item>
              <el-form-item label="密码" prop="pass">
                <el-input v-model="ruleForm.pass"></el-input>
              </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
              <el-button @click="dialogFormVisible = false">取 消</el-button>
              <el-button type="primary" @click="submitForm('ruleForm')">确 定</el-button>
            </div>
          </el-dialog>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination
      background
      layout="prev, pager, next"
      :total="total"
      :page-size="size"
      @current-change="page"
    ></el-pagination>
  </div>
</template>
<style>
  .el-select .el-input {
    width: 109px;
  }
  .input-with-select .el-input-group__prepend {
    background-color: #fff;
    width: 109px;
  }
</style>

<script>
export default {
  methods: {
    //提交查找
    submitSearch(){
      const _this = this;
      var key=_this.searchkey
      if(this.select==1){     //模糊查询
        axios
        .get("http://localhost:8181/user/getUserLike?username="+key)
        .then(function(resp) {
          console.log(resp.data);
          //覆盖假数据
          _this.tableData = resp.data;
          _this.total = resp.data.length;
          _this.size = 5;
        });
      }
      else{                   //精确查询
        axios
        .get("http://localhost:8181/user/getUserByUsername?username="+key)
        .then(function(resp) {
          //转一下数据类型obj--->[]
          var arr = []
          arr.push({username:resp.data.username,pass:resp.data.pass})
          _this.tableData=arr;
          _this.total = 1//resp.data.total;
          _this.size = 1//resp.data.pageSize;
        });
      }
    
    },
    //提交表单
    submitForm(formName) {
      this.dialogFormVisible = false;
      const _this = this;
      this.$refs[formName].validate(valid => {
        if (valid) {
          axios
            .put("http://localhost:8181/user/updateUser", this.ruleForm)
            .then(function(resp) {
              if ((resp.data = "true")) {
                _this
                  .$confirm(
                    "用户" + _this.ruleForm.username + "的信息修改成功！",
                    "提示",
                    {
                      confirmButtonText: "确定",
                      cancelButtonText: "取消",
                      type: "warning"
                    }
                  )
                  .then(() => {
                    _this.$message({
                      type: "success",
                      message: "修改成功!"
                    });
                    window.location.reload();
                  });
              }
            });
        } else {
          console.log("error submit!!");
          return false;
        }
      });
    },
    edit(row) {
      this.dialogFormVisible = true;
      this.ruleForm.username = row.username;
      this.ruleForm.pass = row.pass;
    },
    //删除
    del(row) {
      const _this = this;
      axios
        .delete("http://localhost:8181/user/deleteUserByUsername?username=" + row.username)
        .then(function(resp) {
          _this
            .$confirm("是否要删除用户" + row.username + "的信息？", "提示", {
              confirmButtonText: "确定",
              cancelButtonText: "取消",
              type: "warning"
            })
            .then(() => {
              _this.$message({
                type: "success",
                message: "删除成功!"
              }),

              axios
                .get("http://localhost:8181/user/getPageInfo?pageNum=1&pageSize=5")
                .then(function(resp) {
                  console.log(resp.data);
                  //覆盖假数据
                  _this.tableData = resp.data.list;
                  _this.total = resp.data.total;
                  _this.size = resp.data.pageSize;
                });
            });
        });
    },
    //分页
    page(currentPage) {
      const _this = this;
      axios
        .get("http://localhost:8181/user/getPageInfo?pageNum=" + currentPage + "&pageSize=5")
        .then(function(resp) {
          console.log(resp.data);
          //覆盖假数据
          _this.tableData = resp.data.list;
          _this.total = 10;resp.data.total;
          _this.size = 10;//resp.data.pageSize;
        });
    }
  },
  //加载
  created() {
  },
  data() {
    return {
      searchkey: '',
       select: '',
      ruleForm: {
        username: "",
        pass: "",
      },
      rules: {
        username: [
          { required: true, message: "请输入用户用户名", trigger: "blur" }
        ],
        pass: [
          { required: true, message: "请输入用户密码", trigger: "blur" }
        ]
      },
      total: null,
      size: null,
      confirmButtonText: "confirmButtonText",
      dialogFormVisible: false,
      tableData: [
        {
          username: "空",
          pass: "空",
        }
      ]
    };
  }
};
</script>