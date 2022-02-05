<template>
    <div>
        <h1 style="font-size: 2em; padding: 15px;">学生信息</h1>
        <el-divider content-position="left">在下面输入需要搜索的内容</el-divider>
        <el-row>
            <el-col :span="4">
                <el-input placeholder="选择班级" v-model="search.classname"></el-input>
            </el-col>
            <el-col :span="4">
                <el-input placeholder="搜索学号" style="width: 220px" v-model="search.sno"></el-input>
            </el-col>
            <el-col :span="4">
                <el-input placeholder="搜索姓名" style="width: 220px" v-model="search.sname"></el-input>
            </el-col>
            <el-col :span="4" style="padding-left: 50px">
                <el-button type="primary" @click="toSearch">开始搜索</el-button>
            </el-col>

        </el-row>

        <el-table-filter-panel></el-table-filter-panel>
        <el-table
                :data="tableData"
                border
                style="width: 100%">
            <el-table-column
                    prop="college"
                    label="学院"
            >
            </el-table-column>
            <el-table-column
                    prop="major"
                    label="专业">
            </el-table-column>
            <el-table-column
                    prop="classname"
                    label="班级">
            </el-table-column>
            <el-table-column
                    prop="sno"
                    label="学号">
            </el-table-column>
            <el-table-column
                    prop="sname"
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

        <el-dialog title="外层 Dialog" :visible.sync="outerVisible">
            <el-row style="font-size: 1.2em">
                <el-col>学号：{{this.detail.sno}}</el-col>
                <el-col>姓名：{{this.detail.sname}}</el-col>
                <el-col>拼音：{{this.detail.pinyin}}</el-col>
                <el-col>班级：{{this.detail.classname}}</el-col>
                <el-col>性别：{{this.detail.sex}}</el-col>
                <el-col>出生日期：{{this.detail.birth}}</el-col>
                <el-col>学院：{{this.detail.college}}</el-col>
                <el-col>专业：{{this.detail.major}}</el-col>
                <el-col>身份证号：{{this.detail.cardId}}</el-col>
                <el-col>生源地：{{this.detail.fromname}}</el-col>
                <el-col>电话：{{this.detail.phone}}</el-col>
                <el-col>邮箱：{{this.detail.email}}</el-col>
                <el-col>地址：{{this.detail.address}}</el-col>
            </el-row>



            <el-dialog
                    width="60%"
                    title="修改内容"
                    v-model="detail"
                    :visible.sync="innerVisible"
                    @close="dialogClose2"
                    append-to-body>
                <el-form style="width: 600px">
                    <el-form-item label="学号：" label-width="150px">
                        <el-input type="text"
                                  v-model="detail.sno"
                                  placeholder="请输入学号" disabled></el-input>
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
                    <el-form-item label="性别：" label-width="150px">
                        <el-radio v-model="detail.sex" label="女">女</el-radio>
                        <el-radio v-model="detail.sex" label="男">男</el-radio>
                    </el-form-item>

                    <el-form-item label="学院：" label-width="150px">
                        <el-select placeholder="选择专业" v-model="detail.college" @change="collegeChanged">
                            <el-option
                                    v-for="item in colleges"
                                    :key="item.collegename"
                                    :label="item.collegename"
                                    :value="item.collegename"></el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item label="专业：" label-width="150px">
                        <el-select placeholder="选择专业" v-model="detail.major" @change="majorChanged">
                            <el-option
                                    v-for="item in majors"
                                    :key="item.majorname"
                                    :label="item.majorname"
                                    :value="item.majorname"></el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item label="班级：" label-width="150px">
                        <el-select placeholder="选择班级" v-model="detail.classname">
                            <el-option
                                    v-for="item in classes"
                                    :key="item.classname"
                                    :label="item.classname"
                                    :value="item.classname"></el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item label="出生日期：" label-width="150px">
                        <el-date-picker type="date"
                                        v-model="detail.birth"
                                        placeholder="请输入出生日期"></el-date-picker>
                    </el-form-item>

                    <el-form-item label="身份证号：" label-width="150px">
                        <el-input type="text"
                                  v-model="detail.cardId"
                                  placeholder="请输入身份证号"></el-input>
                    </el-form-item>
                    <el-form-item label="生源地：" label-width="150px">
                        <el-select placeholder="选择地点" v-model="detail.fromname">
                            <el-option
                                    v-for="item in froms"
                                    :key="item.pname"
                                    :label="item.pname"
                                    :value="item.pname"></el-option>
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
                </el-form>
                <div slot="footer" class="dialog-footer">
                    <el-button @click="innerVisible = false">取 消</el-button>
                    <el-button type="primary" @click="onSubmit">提 交</el-button>
                </div>
            </el-dialog>
            <div slot="footer" class="dialog-footer">
                <el-button @click="outerVisible = false">取 消</el-button>
                <el-button type="primary" @click="update">打开修改</el-button>
            </div>
        </el-dialog>
    </div>
</template>

<script>
    export default {
        name: "AllStudent",
        data() {
            return {
                tableData: [
                    {
                        sno: '123'
                    }
                ],
                search: {
                    sno: '',
                    sname: '',
                    classname: ''
                },
                detail: {

                },
                showMessage: '',
                outerVisible: false,
                innerVisible: false,
                classes: [],
                colleges: [],
                majors: [],
                froms: [],
                searchClasses: [],
            }
        },
        methods:{
            rowInfo(row){
                this.showMessage = row;
                this.detail = row;

                this.outerVisible = true;
            },
            toSearch(){
                this.$store.dispatch('getStudentByInfo', {classname: this.search.classname, sno: this.search.sno, sname: this.search.sname})
                    .then(res => {
                        this.tableData = res.data;
                    });
            },
            update(){
                this.$store.dispatch('getAllColleges').then(res => {
                    this.colleges = res.data;
                });
                this.$store.dispatch('getAllMajorsByCollege', this.detail.college).then(res => {
                    this.majors = res.data;
                });
                this.$store.dispatch('getAllClassesByMajor', this.detail.major).then(res => {
                    this.classes = res.data;
                })
                this.$store.dispatch('getAllFroms').then(res => {
                    this.froms = res.data;
                });
                this.innerVisible = true;
            },
            onSubmit(){
                this.$store.dispatch('udpStudent', this.detail).then(res => {
                    this.beforeMount();
                });
            },
            collegeChanged(){
                this.$store.dispatch('getAllMajorsByCollege', this.detail.college).then(res => {
                    this.majors = res.data;
                })
            },
            majorChanged(){
                this.$store.dispatch('getAllClassesByMajor', this.detail.major).then(res => {
                    this.classes = res.data;
                    this.detail.classname=this.classes[0].classname;
                })
            },
            classChanged(){
            },
            dialogClose2(){
                this.innerVisible = false;
            },
            getClassList(){
                this.$store.dispatch('getClassList').then(res => {
                    this.searchClasses = res.data;
                })
            },
        },
        beforeCreate() {
            this.$store.dispatch('getAllStudents').then(res => {
                this.tableData = res.data;
            });
            this.getClassList();
        }
    }
</script>

<style scoped>
.el-col{
    padding-bottom: 5px;
}
</style>