<template>
  <el-form
    :model="ruleForm"
    :rules="rules"
    ref="ruleForm"
    label-width="100px"
    class="demo-ruleForm"
  >
    <el-form-item label="用户名" prop="username" >
      <el-input v-model="ruleForm.username"></el-input>
    </el-form-item>
    <el-form-item label="密码" prop="pass">
      <el-input v-model="ruleForm.pass"></el-input>
    </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="submitForm('ruleForm')">添加</el-button>
      <el-button @click="resetForm('ruleForm')">重置</el-button>
    </el-form-item>
  </el-form>
</template>

<script>
export default {
  data() {
    return {
      ruleForm: {
        username: "",
        pass: "",
      },
      rules: {    //前端校验
        username: [
          { required: true, message: "请输入用户名", trigger: "blur"},
          { max:10, message: "输入内容不得超过10个字符", trigger: "blur"}
        ],
        pass: [
          { required: true, message: "请输入账户密码", trigger: "blur" },
          { max:8, message: "输入内容不得超过8个字符", trigger: "blur"}
        ],
      }
    };
  },
  methods: {
    submitForm(formName) {
      const _this = this;
      this.$refs[formName].validate(valid => {
        if (valid) {
          axios
            .post("http://localhost:8181/user/addUser", this.ruleForm)
            .then(function(resp) {
              console.log(resp.data)
              if ((resp.data = "true")) {
                _this
                  .$confirm(
                    "用户" + _this.ruleForm.username + "的信息添加成功！",
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
                      message: "添加成功!"
                    });
                    _this.$router.push('/UserManage')
                  })
              }
            })
            .catch((error) => {
              _this.$message({
              type: "fail",
              message: "添加失败，该用户已经存在或信息填写错误!"
              });
            })
        } else {
          console.log("error submit!!");
          return false;
        }
      });
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    }
  }
};
</script>