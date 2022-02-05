<template>
    <div class="login-container">
        <div>
        <el-form
                ref="loginForm"
                :model="loginForm"
                :rules="rules"
                label-width="80px"
                class="login-box"
                >
            <h3 class="login-title">欢迎登陆</h3>
            <el-form-item label="账号" prop="username">
                <el-input type="text" placeholder="请输入账号"
                          v-model.trim="loginForm.username"
                          tabindex="1"/>
            </el-form-item>
            <el-form-item label="密码" prop="password">
                <el-input type="password" placeholder="请输入密码"
                          v-model.trim="loginForm.password"
                          ref="password"
                          tabindex="2"
                          @keyup.enter.native="handleLogin"
                          show-password/>
            </el-form-item>
            <el-form-item style="padding-left: 60px;">
                <el-button type="primary" v-on:click="handleLogin" >登录</el-button>
            </el-form-item>
        </el-form>
        </div>
        <el-dialog
            title="温馨提示"
            :visible.sync="dialogVisible"
            width="30%"
            :before-close="handleClose">
            <span>请输入账号和密码</span>
            <span slot="footer" class="dialog-footer">
                <el-button type="primary" @click="dialogClose">确 定</el-button>
            </span>
        </el-dialog>
    </div>
</template>

<script>
    /*import api from "@/api/api"*/

    export default {
        name: "Login",
        deactivated() {

        },
        data() {
            return{
                loginForm: {
                    username: '',
                    password: ''
                },

                //表单的验证，需要在 form-item 中添加prop属性
                rules:{
                    username: [
                        {required: true, message: '账号不可为空', trigger: 'blur'}
                    ],
                    password: [
                        {required: true, message: '密码不可为空', trigger: 'blur'}
                    ]
                },
                //对话框的显示和隐藏
                dialogVisible: false,
                loading: false,
                passwordType: "password",
                redirect: undefined,
            }
        },
        watch: {
            $route: {
                handler(route) {
                    this.redirect = route.query && route.query.redirect;
                },
                immediate: true,
            },
        },
        methods: {
            dialogClose(){
                this.dialogVisible = false;
            },
            handleClose(key, keyPath) {
                console.log(key, keyPath);
            },
            handleLogin() {
                this.$refs.loginForm.validate(async (valid) => {
                    if (valid) {
                        this.loading = true;
                        await this.$store.dispatch("login", this.loginForm);
                        console.log(this.$store.state.username)
                        console.log(this.$store.state.power)
                        if(this.$store.state.power === 1){
                            this.$router.push('/student')
                                .catch((error) => {});
                        }
                        if(this.$store.state.power === 2){
                            this.$router.push('/teacher')
                                .catch((error) => {});
                        }
                        if(this.$store.state.power === 9){
                            this.$router.push('/admin')
                                .catch((error) => {});
                        }

                        this.loading = false;
                    } else {
                        this.dialogVisible = true;
                        return false;
                    }
                });
            },
        },

    }
</script>

<style scoped>
    .login-container{
    }
.login-box{
    border: 1px solid #DCDFE6;
    width: 350px;
    margin: 180px auto;
    padding: 35px 35px 15px 35px;
    border-radius: 5px;
    -webkit-border-radius: 5px;
    -moz-border-radius: 5px;
    box-shadow: 0 0 25px #909399;
}
    .login-title{
        text-align: center;
        max-resolution: 0 auto 40px auto;
        color: #303133;
    }
</style>