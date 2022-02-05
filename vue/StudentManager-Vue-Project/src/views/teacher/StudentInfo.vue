<template>
    <div>
        <h1 style="font-size: 2em; padding: 15px;">学生信息</h1>
        <el-divider content-position="left">班级选项会自动根据已存在信息筛选</el-divider>
        <el-row>
            <el-col :span="4">
                <el-input placeholder="选择班级" v-model="search.classname">
                    <el-option
                        v-for="item in classes"
                        :key="item.classname"
                        :label="item.classname"
                        :value="item.classname"></el-option>
                </el-input>
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
                    prop="classname"
                    label="班级"
            >
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
                    prop="cname"
                    label="课程">
            </el-table-column>
            <el-table-column
                    prop="etype"
                    label="考核类型">
            </el-table-column>
            <el-table-column
                    prop="percent"
                    label="占比">
            </el-table-column>
            <el-table-column
                    prop="grade"
                    label="成绩">
            </el-table-column>
        </el-table>
    </div>
</template>

<script>
    export default {
        name: "StudentInfo",
        data() {
            return {
                classes: [],
                tableData: [],
                search: {
                    classname: '',
                    sno: '',
                    sname: ''
                }
            }
        },
        beforeMount() {
            this.toSearch();
            this.getClassList();
        },
        methods: {
            toSearch(){
                this.$store.dispatch('getGradeInfo', {classname: this.search.classname, sno: this.search.sno, sname: this.search.sname})
                    .then(res => {
                        this.tableData = res.data;
                        console.log(res.data);
                 });
            },
            getClassList(){
                this.$store.dispatch('getClassList').then(res => {
                    this.classes = res.data;
                })
            },
            classChanged(){
            }
        }
    }
</script>

<style scoped>
    .el-row {
        margin-bottom: 10px;
    }
    .el-col {
        /*margin-bottom: 40px;*/
        border-radius: 4px;
        font-size: 1.1em;
    }
</style>