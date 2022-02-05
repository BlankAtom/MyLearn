<template>
    <div>
        <h1 style="font-size: 2em; padding: 15px;">修改家庭住址</h1>
        <el-divider content-position="left">输入内容提交即可</el-divider>

        <el-form
                ref="loginForm"
                :model="changeAddress"
                :rules="rules"
                label-width="80px"
                class="login-box"
                style="width: 40%;"
        >
            <el-form-item label="邮编" prop="postCode" style="width: 50%">
                <el-input type="text" placeholder="请输入邮编"
                          v-model.trim="changeAddress.postCode"
                          tabindex="1"/>
            </el-form-item>
            <el-form-item label="地址" prop="address">
                <el-input type="text" placeholder="请输入地址"
                          v-model.trim="changeAddress.address"
                          ref="address"
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
        name: "ChangeAddress",
        data() {
            return {
                changeAddress: {
                    postCode: '',
                    address: ''
                },
                rules: {
                    postCode: [
                        {required: true, message: '邮编不可为空', trigger: 'blur'}
                    ],
                    address: [
                        {required: true, message: '地址不可为空', trigger: 'blur'}
                    ]
                },
                showMsg: '',
                dialogVisible: false
            }
        },
        methods: {
            onSubmit() {
                this.$store.dispatch('changeStuAddress', this.changeAddress)
                .then(res =>{
                    this.showMsg = res.data.message;
                    this.dialogVisible = true;
                })
            },
            dialogClose(){
                this.dialogVisible = false;
            },
            handleClose(key, keyPath) {
                console.log(key, keyPath);
            },
        },

    }
</script>

<style scoped>

</style>