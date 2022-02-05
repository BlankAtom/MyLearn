<template>
    <div>
        <el-form style="width: 600px" v-model="form">
            <el-form-item label="班级" label-width="150px">
                <el-select
                        v-model="form.classname"
                        filterable
                        allow-create
                        default-first-option
                        placeholder="请选择班级或创建">
                    <el-option
                            v-for="item in classes"
                            :key="item.classname"
                            :label="item.classname"
                            :value="item.classname">
                    </el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="课程：" label-width="150px">
                <el-select
                        v-model="form.course"
                        filterable
                        allow-create
                        default-first-option
                        placeholder="请选择课程">
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
                        placeholder="请选择老师">
                    <el-option
                            v-for="item in teachers"
                            :key="item.tname"
                            :label="item.tname"
                            :value="item.tname">
                    </el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="成绩状态：" label-width="150px">
                <el-select
                        v-model="form.defaultScore"
                        filterable
                        allow-create
                        default-first-option
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
                        v-model="form.ctype"
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


        <el-dialog title="提示" :visible.sync="dialogVisible">
            <el-row >
                {{showMsg}}
            </el-row>

            <el-col>
                <el-button type="primary" @click="dialogVisible = false">确 定</el-button>
            </el-col>
        </el-dialog>
    </div>
</template>

<script>
    export default {
        name: "InsertCourse",
        data() {
            return {
                form: {
                    classname: '', course: '', tname: '',
                    defaultScore: '', deadline: '', ctype: '',
                },
                classes: [],
                courses: [],
                teachers: [],
                defaultScores: [
                    {value: '1', label: '未录入'},
                    {value: '2', label: '已录入'},
                ],
                deadline: "",
                ctypes: [
                    {value: '1', label: '正常'},
                    {value: '2', label: '补考'},
                    {value: '3', label: '重修'},
                    {value: '4', label: '缓考'},
                ],
                dialogVisible: false,
                showMsg: '',
            }
        },
        methods: {
            onSubmit() {
                this.$store.dispatch('addCourse', this.form).then(res => {
                    this.showMsg = res.data.message;
                })
                this.dialogVisible = true;
            }
        },
        beforeCreate() {
            this.$store.dispatch('allClasses').then(res => {
                this.classes = res.data;
            });
            this.$store.dispatch('allCourseName').then(res => {
                this.courses = res.data;
            });
            this.$store.dispatch('allTeacher').then(res => {
                this.teachers = res.data;
            })
        }
    }
</script>

<style scoped>

</style>