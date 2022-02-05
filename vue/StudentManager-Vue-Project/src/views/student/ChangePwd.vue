<template>
    <div>
        <h1 style="font-size: 2em; padding: 15px;">修改密码</h1>
        <el-divider content-position="left">输入内容提交即可</el-divider>
        <el-form
                ref="loginForm"
                :model="changePwd"
                :rules="checkRules"
                label-width="80px"
                class="login-box"
                style="width: 40%; min-width: 300px"
        >
            <el-form-item label="旧密码" prop="oldpassword">
                <el-input type="text" placeholder="请输入旧的密码"
                          v-model.trim="changePwd.oldpassword"
                          tabindex="1"/>
            </el-form-item>
            <el-form-item label="新密码" prop="newpassword">
                <el-input type="password" placeholder="请输入新的密码"
                          v-model.trim="changePwd.newpassword"
                          ref="newpassword"
                          tabindex="2"
                          show-password/>
            </el-form-item>
            <el-form-item label="重复输入" prop="repassword">
                <el-input type="password" placeholder="请重复输入密码"
                          v-model.trim="changePwd.repassword"
                          ref="repassword"
                          tabindex="3"
                          @keyup.enter.native="onSubmit"
                          show-password/>
            </el-form-item>
            <el-form-item style="padding-left: 60px;">
                <el-button type="primary" v-on:click="onSubmit" >提交</el-button>
            </el-form-item>
        </el-form>

        <el-dialog
                title="温馨提示"
                :visible.sync="dialogVisible"
                width="30%"
                :before-close="handleClose">
            <span>{{showMsg}}</span>
            <span slot="footer" class="dialog-footer">
                <el-button type="primary" @click="dialogClose">确 定</el-button>
            </span>
        </el-dialog>

    </div>
</template>

<script>
    export default {
        name: "ChangePwd",
        data() {
            let validateCheck = (rule, value, callback) => {
                if(this.changePwd.newpassword !== value){
                    callback(new Error("两次密码不一致"));
                }
                else{
                    callback();
                }
            };
            let validatePassword = (rule, value, callback) => {
                if("" === value)
                {
                    callback(new Error("新密码不可为空"));
                }
                else  if( value.length<6){
                    callback(new Error("新密码不可低于6位"));
                }
                else{
                    callback();
                }
            }
            return{
                changePwd: {
                    oldpassword: '',
                    newpassword: '',
                    repassword: ''
                },
                dialogVisible: false,
                checkRules: {
                    oldpassword: [
                        {required: true, message: '旧密码不可为空', trigger: 'blur'}
                    ],
                    newpassword: [
                        {required: true, message: '密码不可为空', trigger: 'blur'},
                        {required: true, trigger: 'blur', validator: validatePassword}
                    ],
                    repassword:[
                        {required: true, message: '旧密码不可为空', trigger: 'blur'},
                        {required: true, trigger: 'blur', validator: validateCheck}
                    ]
                },
                showMsg: ''
            };
        },

        methods: {
            onSubmit() {
                if(this.changePwd.repassword !== this.changePwd.newpassword){
                    this.dialogVisible = true;
                }
                else{
                    this.$store.dispatch('changePwd', this.changePwd).then(res =>{
                        console.log(res);
                        if(res.data.result === 'true'){
                            //this.showMsg = res.data.message;
                        }
                        else{
                            //this.showMsg = '修改失败';
                        }
                        this.showMsg = res.data.message;
                        this.dialogVisible = true;
                    });


                }
            },
            dialogClose(){
                this.dialogVisible = false;
            },
            handleClose(key, keyPath) {
                console.log(key, keyPath);
            },
        }
    }
</script>

<style scoped>

</style>