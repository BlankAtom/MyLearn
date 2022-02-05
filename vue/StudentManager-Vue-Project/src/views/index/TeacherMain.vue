<template>
    <el-container>
        <el-menu router
                 default-active="/teacher/password"
                 class="el-menu-vertical-demo"
                 @open="handleOpen"
                 @close="handleClose"
                 :collapse="isCollapse">
            <el-submenu index="1">
                <template slot="title">
                    <i class="el-icon-user-solid"></i>
                    <span slot="title">用户管理</span>
                </template>
                <el-menu-item index="/teacher/self">个人信息</el-menu-item>
                <el-submenu index="1-2">
                    <template slot="title">
                        <span>账号管理</span>
                    </template>
                    <el-menu-item index="/teacher/password">修改密码</el-menu-item>
                    <el-menu-item index="/teacher/address">修改家庭住址</el-menu-item>
                    <el-menu-item index="/teacher/bank">修改银行信息</el-menu-item>
                </el-submenu>
                <el-menu-item index="/teacher/bill">账户清单</el-menu-item>
            </el-submenu>
            <el-submenu index="2">
                <template slot="title">
                    <i class="el-icon-collection"></i>
                    <span slot="title">课程管理</span>
                </template>
                <el-menu-item-group>
                    <el-menu-item index="/teacher/schedule">查看课程表</el-menu-item>
                    <el-menu-item index="/teacher/course">课程设定</el-menu-item>
                </el-menu-item-group>
            </el-submenu>
            <el-submenu index="3">
                <template slot="title">
                    <i class="el-icon-edit-outline"></i>
                    <span slot="title">学生管理</span>
                </template>
                <el-menu-item-group>
                    <el-menu-item index="/teacher/student">学生信息</el-menu-item>
                    <el-menu-item index="/teacher/grade">成绩录入</el-menu-item>
                </el-menu-item-group>
            </el-submenu>
            <el-menu-item index="/teacher/notice">
                <template slot="title">
                    <i class="el-icon-edit-outline"></i>
                    <span slot="title">发布通知</span>
                </template>
            </el-menu-item>
            <el-menu-item index="/main">
                <i class="el-icon-setting"></i>
                <span slot="title">返回主页</span>
            </el-menu-item>
        </el-menu>
        <el-container>
            <el-header style="padding-top: 10px;text-align: right; font-size: 20px; background-color: #f0fcff">
                <el-radio-group v-model="isCollapse" style="float: left;margin-bottom: 20px;">
                    <el-radio-button :label="false">展开</el-radio-button>
                    <el-radio-button :label="true">收起</el-radio-button>
                </el-radio-group>
                <el-dropdown style="margin-right: 15px; font-size: 25px" >
                <span class="el-dropdown-link">
                    <i class="el-icon-setting"><span></span></i>
                </span>
                    <el-dropdown-menu slot="dropdown">
                        <el-dropdown-item @click.native="toProfile" >个人信息</el-dropdown-item>
                        <el-dropdown-item @click.native="logout">退出登录</el-dropdown-item>
                    </el-dropdown-menu>
                </el-dropdown>
            </el-header>
            <el-main>
                <router-view></router-view>
            </el-main>
        </el-container>
    </el-container>
</template>

<script>
    export default {
        name: "TeacherMain",
        props: ['username'],
        data(){
            return {
                isCollapse: false,
                screenHeight: ''
            }
        },
        methods: {
            handleOpen(key, keyPath) {
                //console.log(key, keyPath);
            },
            handleClose(key, keyPath) {
                //console.log(key, keyPath);
            },
            logout(){
                this.$router.push("/logout");
            },
            toProfile(){
                this.$router.push("/teacher/self");
            },
        },
        beforeCreate() {
            let t = this.$store.state.power;
            if(t===1){
                this.$router.push('/student')
            }
            if( t===9){
                this.$router.push('/admin')
            }
        }
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
    .el-dropdown-link{
        cursor: pointer;
    }
</style>