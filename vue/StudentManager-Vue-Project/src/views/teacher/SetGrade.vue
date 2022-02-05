<template>
    <div>
        <h1 style="font-size: 2em; padding: 15px;">录入成绩</h1>
        <el-divider content-position="left">课程信息会根据学生选修内容而展示</el-divider>
        <el-form ref="form" :rules="rules" :model="form" label-width="80px" style="width: 50%">
            <el-form-item label="学号" prop="sno">
                <el-input v-model="form.sno"
                          @blur="getCourse"
                          placeholder="请输入学号"></el-input>
            </el-form-item>
            <el-form-item label="检测类型" prop="ctype">
                <el-select v-model="form.ctype" placeholder="请选择" @change="getCtypeName">
                    <el-option v-for="item in ctypes"
                               :key="item.value"
                               :label="item.label"
                               :value="item.value">
                    </el-option>

                </el-select>
            </el-form-item>
            <el-form-item label="考核类型" prop="etype">
                <el-select v-model="form.etype" placeholder="请选择" @change="getEtypeName">
                    <el-option v-for="item in etypes"
                               :key="item.label"
                               :label="item.label"
                               :value="item.value">
                    </el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="课程" prop="course">
                <el-select v-model="form.course" id="course-select" placeholder="请选择" @change="getCname">
                    <el-option
                        v-for="item in courses"
                        :key="item.cname"
                        :label="item.cname"
                        :value="item.cno" ></el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="占比(%)" prop="percent">
                <el-slider
                        v-model="form.percent"
                        show-input>
                </el-slider>
            </el-form-item>
            <el-form-item label="分数" prop="grade">
                <el-input v-model="form.grade"
                          placeholder="请输入分数"
                            style="width: 150px"></el-input>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" @click="onOK">保存</el-button>
                <el-button>取消</el-button>
            </el-form-item>
        </el-form>

        <el-dialog
                title="请确定"
                :visible.sync="dialogVisible1"
                width="30%"
                :before-close="handleClose1">
            <el-row>
                <el-col>学号：{{form.sno}}</el-col>
                <el-col>检测类型：{{ctypeName}}</el-col>
                <el-col>考核类型：{{etypeName}}</el-col>
                <el-col>课程：{{courseName}}</el-col>
                <el-col>占比：{{form.percent}}%</el-col>
                <el-col>分数：{{form.grade}}</el-col>
            </el-row>
            <span slot="footer" class="dialog-footer">
                <el-button type="primary" @click="onSubmit">确 定</el-button>
                <el-button type="primary" @click="dialogClose1">取 消</el-button>
            </span>
        </el-dialog>

        <el-dialog
                title="温馨提示"
                :visible.sync="dialogVisible2"
                width="30%"
                :before-close="handleClose2">
            <span>{{showMsg}}</span>
            <span slot="footer" class="dialog-footer">
                <el-button type="primary" @click="dialogClose2">确 定</el-button>
            </span>
        </el-dialog>

    </div>
</template>

<script>
    export default {
        name: "SetGrade",
        data() {
            return {
                form: {
                    sno: '',
                    ctype: '',
                    etype: '',
                    course: '',
                    cname: '',
                    percent: 0,
                    grade: '',

                },
                courses: [],
                ctypes: [{value: '1', label: '开卷纸质考试'},
                    {value: '2', label: '开卷上机考试'},
                    {value: '3', label: '闭卷纸质考试'},
                    {value: '4', label: '闭卷上机考试'},
                    {value: '5', label: '课程设计答辩'}],
                etypes: [
                    {value: '1', label: '期末'},
                    {value: '2', label: '期中'},
                    {value: '3', label: '月考'},
                    {value: '4', label: '小测'}],
                rules: {
                    sno: [
                        {required: true, message: '请输入学号', trigger: 'blur'}
                    ],
                    ctype: [
                        {required: true, message: '请选择检测类型', trigger: 'change'}
                    ],
                    etype: [
                        {required: true, message: '请选择考核类型', trigger: 'change'}
                    ],
                    courses: [
                        {required: true, message: '请选择课程', trigger: 'change'}
                    ],
                    course: [
                        { required: true, message: '请至少选择一个占比', trigger: 'change'}
                    ],
                    grade: [
                        {required: true, message: '请输入分数', trigger: 'blur'}
                    ]
                },
                dialogVisible1: false,
                dialogVisible2: false,
                showMsg: '',
                courseName: '',
                ctypeName: '',
                etypeName: ''

            }
        },
        methods: {
            onOK(){
                this.dialogVisible1 = true;
            },
            onSubmit() {
                console.log('submit!');
                this.$store.dispatch('setGrade', this.form).then(res => {
                    this.showMsg = res.data.message;
                }).catch();
                this.dialogVisible1 = false;
                this.dialogVisible2 = true;

            },
            getCourse(){
                this.$store.dispatch('getOnlyCourse', this.form.sno).then(res => {
                    this.courses = res.data
                })
            },
            dialogClose1(){
                this.dialogVisible1 = false;
                this.dialogVisible2 = true;
            },
            handleClose1(){
                this.dialogVisible1 = false;
            },
            dialogClose2(){
                this.dialogVisible2 = false;
            },
            handleClose2(){
                this.dialogVisible2 = false;
            },
            getCname(){
                this.courseName = this.courses[this.form.course-1].cname;
                console.log(this.courseName)

            },
            getEtypeName(){
                this.etypeName = this.etypes[this.form.etype-1].label;
                console.log(this.etypeName)
            },
            getCtypeName(){
                this.ctypeName = this.ctypes[this.form.ctype-1].label;
                console.log(this.ctypeName)
            }
        },
        computed:{

        }

    }
</script>

<style scoped>

</style>