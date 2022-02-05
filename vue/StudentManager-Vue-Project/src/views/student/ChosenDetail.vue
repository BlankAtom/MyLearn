<template>

    <div>
        <h1 style="font-size: 2em; padding: 15px;">选修记录</h1>
        <el-divider content-position="left"></el-divider>
        <el-table
                :data="tableData"
                height="250"
                border
                style="width: 100%">
            <el-table-column
                    prop="term"
                    label="学期"
                    >
            </el-table-column>
            <el-table-column
                    prop="cname"
                    label="课程名">
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
        name: "ChosenDetail",
        data() {
            return {
                tableData: []
            }
        },
        beforeCreate() {
            this.$store.dispatch('getCourses').then(res => {
                this.tableData = res.data;
            });
        }
    }
</script>

<style scoped>

</style>