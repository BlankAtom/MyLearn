<template>
    <div>
        <h1 style="font-size: 2em; padding: 15px;">修改银行信息</h1>
        <el-divider content-position="left">输入内容提交即可</el-divider>
        <el-form
                ref="loginForm"
                :model="changeBank"
                :rules="rules"
                label-width="80px"
                class="login-box"
                style="width: 40%;"
        >
            <el-form-item label="银行" prop="bank" style="width: 50%">
                <el-input type="text" placeholder="请输入银行公司"
                          v-model.trim="changeBank.bank"
                          tabindex="1"/>
            </el-form-item>
            <el-form-item label="卡号" prop="bankcard">
                <el-input type="text" placeholder="请输入银行卡号"
                          v-model.trim="changeBank.bankcard"
                          ref="bankcard"
                          tabindex="2"
                          @keyup.enter.native="onSubmit"/>
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
        name: "Bank",
        data() {
            return {
                changeBank: {
                    bank: '',
                    bankcard: ''
                },
                rules: {
                    bank: [
                        {required: true, message: '银行公司不可为空', trigger: 'blur'}
                    ],
                    bankcard: [
                        {required: true, message: '卡号不可为空', trigger: 'blur'}
                    ]
                },
                showMsg: '',
                dialogVisible: false
            }
        },
        methods: {
            onSubmit() {
                this.$store.dispatch('changeTeaBank', this.changeBank)
                    .then(res => {
                        this.showMsg = res.data.message;
                        this.dialogVisible = true;
                    })
            },
            dialogClose() {
                this.dialogVisible = false;
            },
            handleClose(key, keyPath) {
                this.dialogVisible = false;
            },
        }
    }
</script>

<style scoped>

</style>