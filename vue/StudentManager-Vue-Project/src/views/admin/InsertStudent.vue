<template>
    <div>

        <el-form style="width: 600px" v-model="detail">
            <el-form-item label="学号：" label-width="150px">
                <el-input type="text"
                          v-model="detail.sno"
                          placeholder="请输入学号"></el-input>
            </el-form-item>
            <el-form-item label="姓名：" label-width="150px">
                <el-input type="text"
                          v-model="detail.sname"
                          placeholder="请输入姓名"></el-input>
            </el-form-item>
            <el-form-item label="拼音：" label-width="150px">
                <el-input type="text"
                          v-model="detail.pinyin"
                          placeholder="请输入拼音"></el-input>
            </el-form-item>
            <el-form-item label="班级：" label-width="150px">
                <el-input type="text"
                          v-model="detail.classname"
                          placeholder="请输入班级"></el-input>
            </el-form-item>
            <el-form-item label="性别：" label-width="150px">
                <el-radio v-model="detail.sex" label="女">女</el-radio>
                <el-radio v-model="detail.sex" label="男">男</el-radio>
            </el-form-item>
            <el-form-item label="出生日期：" label-width="150px">
                <el-date-picker type="date"
                          v-model="detail.birth"
                          placeholder="请输入出生日期"
                          format="yyyy-MM-dd"
                          value-format="yyyy-MM-dd"></el-date-picker>
            </el-form-item>
            <el-form-item label="学院：" label-width="150px">
                <el-input type="text"
                          v-model="detail.college"
                          placeholder="请输入学院"></el-input>
            </el-form-item>
            <el-form-item label="专业：" label-width="150px">
                <el-input type="text"
                          v-model="detail.major"
                          placeholder="请输入专业"></el-input>
            </el-form-item>
            <el-form-item label="身份证号：" label-width="150px">
                <el-input type="text"
                          v-model="detail.cardId"
                          placeholder="请输入身份证号"></el-input>
            </el-form-item>
            <el-form-item label="生源地：" label-width="150px">
                <el-input type="text"
                          v-model="detail.fromname"
                          placeholder="请输入生源地"></el-input>
            </el-form-item>
            <el-form-item label="电话：" label-width="150px">
                <el-input type="text"
                          v-model="detail.phone"
                          placeholder="请输入电话"></el-input>
            </el-form-item>
            <el-form-item label="邮箱：" label-width="150px">
                <el-input type="text"
                          v-model="detail.email"
                          placeholder="请输入邮箱"></el-input>
            </el-form-item>
            <el-form-item label="地址：" label-width="150px">
                <el-input type="text"
                          v-model="detail.address"
                          placeholder="请输入地址"></el-input>
            </el-form-item>
            <div class="dialog-footer" style="padding-left: 100px">
                <el-button type="primary" @click="onSubmit">提交</el-button>
            </div>
        </el-form>

        <el-dialog title="提示" :visible.sync="dialogVisble">
            <el-row >
               {{showMsg}}
            </el-row>

            <el-col>
                <el-button type="primary" @click="handleClose">确 定</el-button>
            </el-col>
        </el-dialog>
    </div>
</template>

<script>
    export default {
        name: "InsertStudent",
        data() {
            return {
                formData:{

                },
                dialogVisble: false,
                showMsg: '',
                detail:{
                    sno: '',
                    sname: '',
                    pinyin: '',
                    classname: '',
                    sex: '女',
                    birth: '',
                    college: '',
                    major: '',
                    cardId: '',
                    fromname: '',
                    phone: '',
                    email: '',
                    address: ''
                }
            }
        },
        methods: {
            onSubmit(){
                console.log(this.detail.birth)
                this.$store.dispatch('insertStudent', this.detail).then(res => {
                    this.showMsg = res.message;
                    this.dialogVisble = true;
                });
            },
            handleClose(){
                this.dialogVisble = false;
            }
        }

    }
</script>

<style scoped>

</style>