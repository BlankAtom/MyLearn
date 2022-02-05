<template>
    <div>
    <div>
        <el-radio-group v-model="isCollapse" style="margin-bottom: 20px;">
            <el-radio-button :label="true" >收起</el-radio-button>
            <el-radio-button :label="false" >展开</el-radio-button>
        </el-radio-group>
    </div>
    <div>
    <el-menu
            router
            default-active="1-4-1"
            class="el-menu-vertical-demo"
            @open="handleOpen"
            @close="handleClose"
            :collapse="isCollapse">

        <el-submenu index="1">
            <template slot="title">
                <i class="el-icon-user-solid"></i>
                <span slot="title">用户管理</span>
            </template>
                <el-menu-item index="1-1">个人信息</el-menu-item>

                <el-submenu index="1-2">
                    <template slot="title">
                        <span>账号管理</span>
                    </template>
                        <el-menu-item index="1-2-1">修改密码</el-menu-item>
                        <el-menu-item index="1-2-2">修改家庭住址</el-menu-item>
                        <el-menu-item index="1-2-3">修改银行信息</el-menu-item>
                </el-submenu>
                <el-menu-item index="1-3">获奖明细</el-menu-item>
                <el-menu-item index="1-4">账户清单</el-menu-item>
        </el-submenu>
        <el-submenu index="2">
            <template slot="title">
                <i class="el-icon-collection"></i>
                <span slot="title">课程管理</span>
            </template>
            <el-menu-item-group>
                <el-menu-item index="2-1">查看课程表</el-menu-item>
                <el-menu-item index="2-2">选修课程</el-menu-item>
                <el-menu-item index="2-3">选修记录</el-menu-item>
            </el-menu-item-group>
        </el-submenu>
        <el-submenu index="3">
            <template slot="title">
                <i class="el-icon-edit-outline"></i>
                <span slot="title">学分管理</span>
            </template>
            <el-menu-item-group>
                <el-menu-item index="3-1">学分查看</el-menu-item>
                <el-menu-item index="3-2">成绩总览</el-menu-item>
            </el-menu-item-group>
        </el-submenu>
        <el-menu-item index="4">
            <i class="el-icon-setting"></i>
            <span slot="title">返回主页</span>
        </el-menu-item>
    </el-menu>
    </div>
    </div>
</template>

<script>
    export default {
        name: "SiderBar",
        data(){
            return {
                isCollapse: false
            }
        },
        methods: {
            handleOpen(key, keyPath) {
                console.log(key, keyPath);
            },
            handleClose(key, keyPath) {
                console.log(key, keyPath);
            },
            toProfile(){
                this.$router.push("/1-1");
            },
            changeCollapse(){
                if(this.isCollapse === true){
                    this.isCollapse = false;
                }
                else{
                    this.isCollapse = true;
                }
            }
        },
    }
</script>

<style scoped>

    .el-header{
        padding-top: 10px;
        text-align: right;
        font-size: 20px;
        background-color: #f0fcff;

    }
    .el-menu-vertical-demo:not(.el-menu--collapse) {
        width: 250px;
        min-height: 500px;
    }
</style>