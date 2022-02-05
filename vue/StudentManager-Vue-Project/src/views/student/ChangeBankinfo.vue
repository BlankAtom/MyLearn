<template>
    <div>
        <h1 style="font-size: 2em; padding: 15px;">修改银行信息</h1>
        <el-divider content-position="left">输入内容提交即可</el-divider>
        <el-form
                ref="loginForm"
                :model="changePwd"
                :rules="checkRules"
                label-width="80px"
                class="login-box"
                style="width: 40%; min-width: 300px"
        >
            <el-form-item label="银行所属" prop="bank">
                <el-input type="text" placeholder="请输入更改的银行公司"
                          v-model.trim="bankinfo.bank"
                          tabindex="1"/>
            </el-form-item>
            <el-form-item label="银行卡号" prop="card">
                <el-input type="password" placeholder="请输入新的卡号"
                          v-model.trim="bankinfo.card"
                          ref="newpassword"
                          tabindex="2"
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
            <span>{{result.message}}</span>
            <span slot="footer" class="dialog-footer">
                <el-button type="primary" @click="dialogClose">确 定</el-button>
            </span>
        </el-dialog>

    </div>
</template>

<script>
    export default {
        name: "ChangeBankinfo",
        data(){
            return {
                bankinfo:{
                    bank: "",
                    card: ""
                },
                result:{
                    result: "",
                    message: ""
                },
                dialogVisible: false
            }
        },
        methods:{
            dialogClose(){
                this.dialogVisible = false;
            },
            handleClose(key, keyPath){
                console.log(key, keyPath);
            },
            onSubmit() {
                if(this.changePwd.repassword !== this.changePwd.newpassword){
                    this.dialogVisible = true;
                }
                else{
                    console.log("1");
                }
            },
        }

    }
</script>

<style scoped>

</style>