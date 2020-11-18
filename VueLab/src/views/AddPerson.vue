<template>
  <el-form
    :model="ruleForm"
    :rules="rules"
    ref="ruleForm"
    label-width="100px"
    class="demo-ruleForm"
  >
    <el-form-item label="用户名" prop="username" width="150">
      <el-input v-model="ruleForm.username" width="150"></el-input>
    </el-form-item>

    <el-form-item label="姓名" prop="name">
      <el-input v-model="ruleForm.name"></el-input>
    </el-form-item>
    <el-form-item label="年龄" prop="age">
      <el-input v-model="ruleForm.age" ></el-input>
    </el-form-item>
    <el-form-item label="电话" prop="teleno" width="150" type="number">
      <el-input v-model="ruleForm.teleno"></el-input>
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
        name: "",
        age: "",
        stuSex: "",
        teleno: ""
      },
      rules: {
        name: [
          { required: true, message: "请输入姓名", trigger: "blur" },
          { max:20, message: "输入内容不得超过20个字符", trigger: "blur"}
        ],
        username: [
          { required: true, message: "请输入用户名", trigger: "blur" },
          { max:10, message: "输入内容不得超过10个字符", trigger: "blur"}
        ],
        age:[
          { type: 'number', message: '年龄必须为数字值',transform: (value) => Number(value)},
          { type:'number',min:0,max:200,message: "请保证输入年龄在0～200之间!",transform: (value) => Number(value), trigger: "blur"}
        ],
        teleno: [
          { type:'number', message: "电话必须为数字值", trigger: "blur",transform: (value) => Number(value)},
          { type:'number',min:0,max:99999999999,message: "请保证电话不超过11位!",transform: (value) => Number(value), trigger: "blur"}
        ]
      }
    };
  },
  
  methods: {
    submitForm(formName) {
      const _this = this;
      this.$refs[formName].validate(valid => {
        if (valid) {
          axios
            .post("http://localhost:8181/person/addPerson", this.ruleForm)
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
                    _this.$router.push('/PersonManage')
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