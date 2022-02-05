<template>
    <el-container>
        <el-menu
                router
                default-active="1-2"
                class="el-menu-vertical-demo"
                @open="handleOpen"
                @close="handleClose"
                :collapse="isCollapse">

            <el-submenu index="1">
                <template slot="title">
                    <i class="el-icon-user-solid"></i>
                    <span slot="title">学生管理</span>
                </template>
                <el-menu-item index="/admin/addStudent">增加学生</el-menu-item>
                <el-menu-item index="/admin/allStudent">学生总览</el-menu-item>
            </el-submenu>
            <el-submenu index="2">
                <template slot="title">
                    <i class="el-icon-collection"></i>
                    <span slot="title">教师管理</span>
                </template>
                <el-menu-item-group>
                    <el-menu-item index="/admin/addTeacher">增加教师</el-menu-item>
                    <el-menu-item index="/admin/allTeacher">教师总览</el-menu-item>
                </el-menu-item-group>
            </el-submenu>
            <el-submenu index="3">
                <template slot="title">
                    <i class="el-icon-edit-outline"></i>
                    <span slot="title">课程管理</span>
                </template>
                <el-menu-item-group>
                    <el-menu-item index="/admin/addCourse">增加课程</el-menu-item>
                    <el-menu-item index="/admin/allCourse">课程总览</el-menu-item>
                </el-menu-item-group>
            </el-submenu>
            <el-menu-item index="/admin">
                <i class="el-icon-setting"></i>
                <span slot="title">返回主页</span>
            </el-menu-item>
        </el-menu>
        <el-container>
            <el-header >
                <el-radio-group v-model="isCollapse" style="float: left;margin-bottom: 20px;">
                    <el-radio-button :label="true" >收起</el-radio-button>
                    <el-radio-button :label="false" >展开</el-radio-button>
                </el-radio-group>
                <el-dropdown style="margin-right: 15px; font-size: 25px" >

                    <span class="el-dropdown-link">
                        <i class="el-icon-setting"><span></span></i>
                    </span>
                    <el-dropdown-menu slot="dropdown">
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
        name: "AdminMain",
        data(){
            return {
                isCollapse: false
            }
        },
        methods:{
            handleOpen(key, keyPath) {
                //console.log(key, keyPath);
            },
            handleClose(key, keyPath) {
                //console.log(key, keyPath);
            },
        },
        beforeCreate(){
           let t = this.$store.state.power;
            if(t===1){
                this.$router.push("/student")
            }
            if(t===2){
                this.$router.push("/teacher")
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