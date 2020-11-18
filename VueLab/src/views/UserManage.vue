<template>
  <div>
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

<script>
export default {
  methods: {

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
          _this.total = resp.data.total;
          _this.size = resp.data.pageSize;
        });
    }
  },
  //加载
  created() {
    const _this = this;
    axios
      .get("http://localhost:8181/user/getPageInfo?pageNum=1&pageSize=5")
      .then(function(resp) {
        console.log(resp.data);
        //覆盖假数据
        _this.tableData = resp.data.list;
        _this.total = resp.data.total;
        _this.size = resp.data.pageSize;
      });
  },
  data() {
    return {
      ruleForm: {
        username: "",
        pass: "",
      },
      rules: {
        username: [
          { required: true, message: "请输入用户名", trigger: "blur"},
          { max:10, message: "输入内容不得超过10个字符", trigger: "blur"}
        ],
        pass: [
          { required: true, message: "请输入账户密码", trigger: "blur" },
          { max:8, message: "输入内容不得超过8个字符", trigger: "blur"}
        ],
      },
      total: null,
      size: null,
      confirmButtonText: "confirmButtonText",
      dialogFormVisible: false,
      tableData: [
        {
          username: "root",
          pass: "88888",
        }
      ]
    };
  }
};
</script>