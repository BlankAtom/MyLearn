<template>
    <div>
        <h1></h1>
        <h1 style="font-size: 2em; padding: 15px;">成绩查看</h1>
        <el-divider content-position="left"></el-divider>
        <el-table
                :data="tableData"
                height="250"
                border
                style="width: 100%">
            <el-table-column
                    prop="term"
                    label="学期">
            </el-table-column>
            <el-table-column
                    prop="cname"
                    label="课程">
            </el-table-column>
            <el-table-column
                    prop="tname"
                    label="教师">
            </el-table-column>
            <el-table-column
                    prop="gtype"
                    label="成绩类型">
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
        name: "Grades",
        data() {
            return {
                tableData: [
                    {  }
                ]
            }
        },
        beforeCreate() {
            this.$store.dispatch('getAllGrades').then(res => {
                this.tableData = res.data;
            })
        },
        computed: {
        }
    }
</script>

<style scoped>

</style>