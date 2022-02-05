<template>
    <div>
        <h1 style="font-size: 2em; padding: 15px;">教师信息</h1>
        <el-divider content-position="left">在下面输入需要搜索的内容</el-divider>
        <el-row>
            <el-col :span="4">
                <el-select placeholder="选择教研室" v-model="search.oname" >
                    <el-option v-for="item in offices"
                               :key="item.oname"
                               :label="item.oname"
                               :value="item.oname"></el-option>
                </el-select>
            </el-col>
            <el-col :span="4">
                <el-input placeholder="搜索工号" style="width: 220px" v-model="search.tno"></el-input>
            </el-col>
            <el-col :span="4">
                <el-input placeholder="搜索姓名" style="width: 220px" v-model="search.tname"></el-input>
            </el-col>
            <el-col :span="4" style="padding-left: 50px">
                <el-button type="primary" @click="toSearch">开始搜索</el-button>
            </el-col>

        </el-row>
        <el-table
                :data="tableData"
                border
                style="width: 100%">
            <el-table-column
                    prop="oname"
                    label="教研室">
            </el-table-column>
            <el-table-column
                    prop="cname"
                    label="学院">
            </el-table-column>
            <el-table-column
                    prop="tno"
                    label="工号">
            </el-table-column>
            <el-table-column
                    prop="tname"
                    label="姓名">
            </el-table-column>
            <el-table-column
                    label="操作"
                    fixed="right">
                <template slot-scope="scope">
                    <el-button type="text" @click="rowInfo(scope.row)">详情</el-button>
                </template>

            </el-table-column>
        </el-table>

        <el-dialog title="预览" :visible.sync="outerVisible">
                <el-row style="font-size: 1.2em">
                    <el-col>工号：{{this.detail.tno}}</el-col>
                    <el-col>姓名：{{this.detail.tname}}</el-col>
                    <el-col>拼音：{{this.detail.pinyin}}</el-col>
                    <el-col>教研室：{{this.detail.oname}}</el-col>
                    <el-col>性别：{{this.detail.sex}}</el-col>
                    <el-col>年龄：{{this.detail.age}}</el-col>
                    <el-col>学院：{{this.detail.cname}}</el-col>
                    <el-col>职称：{{this.detail.ranks}}</el-col>
                    <el-col>电话：{{this.detail.phone}}</el-col>
                    <el-col>邮箱：{{this.detail.email}}</el-col>
                    <el-col>地址：{{this.detail.address}}</el-col>
                    <el-col>邮编：{{this.detail.pcode}}</el-col>
                    <el-col>银行：{{this.detail.bank}}</el-col>
                    <el-col>卡号：{{this.detail.bno}}</el-col>
                </el-row>

            <el-dialog title="修改"
                       width="60%"
                       v-model="detail"
                       :visible.sync="innerVisible"
                       @close="innerVisible = false"
                       append-to-body>
                <el-form style="width: 600px" v-model="detail">
                    <el-form-item label="工号：" label-width="150px">
                        <el-input type="text"
                                  v-model="detail.tno"
                                  placeholder="请输入工号" disabled></el-input>
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
                        <el-radio v-model="detail.sex" label="女" ></el-radio>
                        <el-radio v-model="detail.sex" label="男" ></el-radio>
                    </el-form-item>
                    <el-form-item label="年龄：" label-width="150px">
                        <el-input type="text" v-model="detail.age"  placeholder="请输入年龄">

                        </el-input>
                    </el-form-item>
                    <el-form-item label="教研室：" label-width="150px">
                        <el-select placeholder="选择教研室" v-model="detail.oname" >
                            <el-option v-for="item in offices"
                                       :key="item.oname"
                                       :label="item.oname"
                                       :value="item.oname"></el-option>
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
                    <div  class="dialog-footer" align="right">
                        <el-button @click="innerVisible = false">取 消</el-button>
                        <el-button type="primary" @click="onSubmit">提 交</el-button>
                    </div>
                </el-form>
            </el-dialog>

            <div slot="footer" class="dialog-footer">
                <el-button @click="outerVisible = false">取 消</el-button>
                <el-button type="primary" @click="openUpdate">打开修改</el-button>
            </div>

        </el-dialog>
    </div>
</template>

<script>
    export default {
        name: "AllTeacher",
        data(){
            return {
                tableData: [],
                outerVisible: false,
                innerVisible: false,
                detail: {
                    tname: '',
                    pinyin: '',
                    oname: '',
                    tno: '',
                    sex: '',
                    age: '',
                    cname: '',
                    ranks: '',
                    cardId: '',
                    phone: '',
                    email: '',
                    address: '',
                    pcode: '',
                    bank: '',
                    bno: '',
                },
                offices: [],
                search: {
                    oname: '',
                    tno: '',
                    tname: '',
                }

            }
        },
        methods: {
            toSearch(){
                this.$store.dispatch('getTeacher', this.search).then(res => {
                    this.tableData = res.data;
                });
            },
            rowInfo(row){
                this.detail=row;
                this.outerVisible = true;
            },
            openUpdate(){
                this.$store.dispatch('getAllOffice').then(res => {
                    this.offices = res.data;

                })
                this.innerVisible = true;
            },
            onSubmit(){
                this.$store.dispatch('udpTeacher', this.detail).then(res => {
                    alert(res.data.message);
                    this.beforeMount();
                })
            }
        },
        beforeCreate() {
            this.$store.dispatch('allTeacher').then(res => {
                this.tableData = res.data;
            });
            this.$store.dispatch('getAllOffice').then(res => {
                this.offices = res.data;
            });
        }
    }
</script>

<style scoped>

</style>