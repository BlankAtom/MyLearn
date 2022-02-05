<template>
    <div>
        <h1>获奖明细</h1>
        <el-table
                :data="tableData"
                height="250"
                border
                style="width: 100%">
            <el-table-column
                    prop="sno"
                    label="学号"
                    width="180">
            </el-table-column>
            <el-table-column
                    prop="date"
                    label="日期"
                    width="180">
            </el-table-column>
            <el-table-column
                    prop="type"
                    label="奖惩类型"
                    width="180">
            </el-table-column>
            <el-table-column
                    prop="demo"
                    label="详细描述">
            </el-table-column>
        </el-table>
    </div>
</template>

<script>
    export default {
        name: "AwardInfo",
        data() {
            return {
                tableData: [
                    {  }
                ]
            }
        },
        beforeMount() {
            this.$store.dispatch('getAwards').then(res =>{
                this.tableData = res.data;
            })
        }
    }
</script>

<style scoped>

</style>