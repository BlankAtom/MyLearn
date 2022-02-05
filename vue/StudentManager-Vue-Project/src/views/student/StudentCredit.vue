<template>
    <div>
        <el-card style="width: 50%" v-cloak>
            当前的总学分为：{{csum}}
        </el-card>
    </div>
</template>

<script>
    export default {
        name: "StudentCredit",
        data(){
            return{
                csum: ""
            }
        },
        beforeCreate() {
            this.$store.dispatch('getCredits').then(res => {
                this.csum = res.data.csum;
            })
        }

    }
</script>

<style scoped>
    [v-cloak]{
        visibility: hidden;
    }
</style>