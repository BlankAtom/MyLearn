<template>
    <div>
        <h1 style="font-size: 2em; padding: 15px;">课程信息</h1>
        <el-divider content-position="left">在下面输入需要搜索的内容</el-divider>
        <el-row>
            <el-col :span="4">
                <el-input placeholder="搜索班级代码" style="width: 220px" v-model="search.classId" >
                </el-input>
            </el-col>
            <el-col :span="4">
                <el-input placeholder="搜索教师" style="width: 220px" v-model="search.teacher"></el-input>
            </el-col>
            <el-col :span="4">
                <el-input placeholder="搜索课程" style="width: 220px" v-model="search.course"></el-input>
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
                    prop="classId"
                    label="班级代码">
            </el-table-column>
            <el-table-column
                    prop="tname"
                    label="教师">
            </el-table-column>
            <el-table-column
                    prop="cname"
                    label="课程">
            </el-table-column>
            <el-table-column
                    prop="class_type"
                    label="开课性质">
            </el-table-column>
            <el-table-column
                    prop="score_name"
                    label="成绩录入情况">
            </el-table-column>
            <el-table-column
                    prop="deadline"
                    label="死线">
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
                <el-col>班级代码：{{this.form.classId}}</el-col>
                <el-col>教师：{{this.form.tname}}</el-col>
                <el-col>课程：{{this.form.cname}}</el-col>
                <el-col>开课性质：{{this.form.class_type}}</el-col>
                <el-col>成绩录入情况：{{this.form.score_name}}</el-col>
                <el-col>终期：{{this.form.deadline}}</el-col>
            </el-row>

            <el-dialog title="修改"
                       width="60%"
                       v-model="form"
                       :visible.sync="innerVisible"
                       @close="innerVisible = false"
                       append-to-body>
                <el-form style="width: 600px" v-model="form">
                    <el-form-item label="班级" label-width="150px">
                        <el-input type="text" :label="form.classId" :value="form.classId" disabled></el-input>
                    </el-form-item>
                    <el-form-item label="课程：" label-width="150px">
                        <el-select
                                v-model="form.cname"
                                filterable
                                allow-create
                                default-first-option
                                placeholder="请选择课程" disabled>
                            <el-option
                                    v-for="item in courses"
                                    :key="item.cname"
                                    :label="item.cname"
                                    :value="item.cname">
                            </el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item label="老师：" label-width="150px">
                        <el-select
                                v-model="form.tname"
                                filterable
                                allow-create
                                default-first-option
                                placeholder="请选择老师" disabled>
                            <el-option
                                    v-for="item in teachers"
                                    :key="item.tname"
                                    :label="item.tname"
                                    :value="item.tname" >
                            </el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item label="成绩状态：" label-width="150px">
                        <el-select
                                v-model="form.score_name"
                                filterable
                                allow-create
                                placeholder="请选择成绩状态">
                            <el-option
                                    v-for="item in defaultScores"
                                    :key="item.value"
                                    :label="item.label"
                                    :value="item.value">
                            </el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item label="死线：" label-width="150px">
                        <el-date-picker type="date"
                                        v-model="form.deadline"
                                        placeholder="请输入出生日期"
                                        value-format="yyyy-MM-dd"></el-date-picker>
                    </el-form-item>
                    <el-form-item label="开课类型：" label-width="150px">
                        <el-select
                                v-model="form.class_type"
                                filterable
                                allow-create
                                default-first-option
                                placeholder="请选择成绩状态">
                            <el-option
                                    v-for="item in ctypes"
                                    :key="item.value"
                                    :label="item.label"
                                    :value="item.value">
                            </el-option>
                        </el-select>
                    </el-form-item>
                    <div class="dialog-footer" style="padding-left: 100px">
                        <el-button type="primary" @click="onSubmit">提交</el-button>
                    </div>


                </el-form>
            </el-dialog>

            <div slot="footer" class="dialog-footer">
                <el-button @click="outerVisible = false">取 消</el-button>
                <el-button type="primary" @click="openUpdate">打开修改</el-button>
            </div>

        </el-dialog>

        <el-alert title="${showMsg}"
            type="info">

        </el-alert>
    </div>
</template>

<script>
    export default {
        name: "AllCourse",
        data() {
            return {
                tableData: [],
                classes: [],
                search: {
                    classId: '',
                    teacher: '',
                    course: '',
                },
                outerVisible: false,
                innerVisible: false,
                form: {
                    classId: '', tname: '', cname: '',
                    score_name: '', deadline: '', class_type: '',
                },
                defaultScores: [
                    {value: '1', label: '未录入'},
                    {value: '2', label: '已录入'},
                ],
                ctypes: [
                    {value: '1', label: '正常'},
                    {value: '2', label: '补考'},
                    {value: '3', label: '重修'},
                    {value: '4', label: '缓考'},
                ],
                courses: [],
                teachers: [],
            }
        },
        methods: {
            toSearch() {
                this.$store.dispatch('allCourses', this.search).then(res => {
                    this.tableData = res.data;
                })
            },
            rowInfo(row){
                this.form = row;
                this.outerVisible = true;
            },
            onSubmit() {
                this.$store.dispatch('udpCourse', this.form).then(res => {
                    console.log(res.data);
                    this.showMsg = res.data.message;
                    alert(this.showMsg)
                })
            },
            openUpdate(){
                this.innerVisible = true;
            },
        },
        beforeCreate() {
            this.$store.dispatch('allCourseName').then(res => {
                this.courses = res.data;
            });
            this.$store.dispatch('allTeacher').then(res => {
                this.teachers = res.data;
            })
            this.$store.dispatch('allCourses', {classId: '', teacher: '', course: ''}).then(res => {
                this.tableData = res.data;
            })
        }
    }
</script>

<style scoped>

</style>