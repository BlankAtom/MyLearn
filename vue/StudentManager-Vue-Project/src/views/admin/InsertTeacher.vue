<template>
    <div>

        <el-form style="width: 600px" v-model="detail">
            <el-form-item label="工号：" label-width="150px">
                <el-input type="text"
                          v-model="detail.tno"
                          placeholder="请输入工号"></el-input>
            </el-form-item>
            <el-form-item label="姓名：" label-width="150px">
                <el-input type="text"
                          v-model="detail.tname"
                          placeholder="请输入姓名"></el-input>
            </el-form-item>
            <el-form-item label="拼音：" label-width="150px">
                <el-input type="text"
                          v-model="detail.pinyin"
                          placeholder="请输入拼音"></el-input>
            </el-form-item>
            <el-form-item label="职阶：" label-width="150px">
                <el-input type="text"
                          v-model="detail.ranks"
                          placeholder="请输入职阶"></el-input>
            </el-form-item>
            <el-form-item label="性别：" label-width="150px">
                <el-radio v-model="detail.sex" label="1" >女</el-radio>
                <el-radio v-model="detail.sex" label="2" >男</el-radio>
            </el-form-item>
            <el-form-item label="年龄：" label-width="150px">
                <el-input type="text" v-model="detail.age"  placeholder="请输入年龄">

                </el-input>
            </el-form-item>
            <el-form-item label="教研室：" label-width="150px">
                <el-select placeholder="选择教研室" v-model="detail.office" >
                    <el-option v-for="item in offices"
                        :key="item.oid"
                        :label="item.oname"
                        :value="item.oid"></el-option>
                </el-select>
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

        <el-dialog title="提示" :visible.sync="dialogVisible">
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
        name: "InsertTeacher",
        data() {
            return {
                formData: {},
                dialogVisible: false,
                showMsg: '',
                offices: [],
                detail: {
                    tno: '',
                    tname: '',
                    pinyin: '',
                    sex: '1',
                    age: '',
                    ranks: '',
                    office: '',
                    phone: '',
                    email: '',
                    address: ''
                },
            }
        },
        methods: {
            onSubmit(){
                this.$store.dispatch('addTeacher', this.detail).then(res => {
                    this.showMsg = res.message;
                });
                this.dialogVisible = true;
            },

            handleClose(){
                this.dialogVisible = false;
            }

        },
        beforeCreate() {
            this.$store.dispatch('getAllOffice').then(res => {
                this.offices = res.data;
            })
        }
    }
</script>

<style scoped>

</style>