<template>
    <div>
        <h1 style="font-size: 2em; padding: 15px;">课程设定</h1>
        <el-divider content-position="left">未开放的内容</el-divider>
    </div>
</template>

<script>
    export default {
        name: "CourseSettings",
        data() {
            return {
                options: [{
                    value: '选项1',
                    label: '黄金糕'
                }],
                value: ''
            }
        }
    }
</script>

<style scoped>

</style>