<template>
<div>
<!--    <el-radio-group v-model="isCollapse" style="margin-bottom: 20px;">-->
<!--        <el-radio-button :label="false">展开</el-radio-button>-->
<!--        <el-radio-button :label="true">收起</el-radio-button>-->
<!--    </el-radio-group>-->
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
                    <span slot="title">用户管理</span>
                </template>
                <el-menu-item index="/student/self">个人信息</el-menu-item>

                <el-submenu index="1-2">
                    <template slot="title">
                        <span>账号管理</span>
                    </template>
                    <el-menu-item index="/student/cpd">修改密码</el-menu-item>
                    <el-menu-item index="/student/cas">修改家庭住址</el-menu-item>
                    <el-menu-item index="/student/cbk">修改银行信息</el-menu-item>
                </el-submenu>
                <el-menu-item index="/student/awards">获奖明细</el-menu-item>
                <el-menu-item index="/student/bill">账户清单</el-menu-item>
            </el-submenu>
            <el-submenu index="2">
                <template slot="title">
                    <i class="el-icon-collection"></i>
                    <span slot="title">课程管理</span>
                </template>
                <el-menu-item-group>
                    <el-menu-item index="/student/schedule">查看课程表</el-menu-item>
                    <el-menu-item index="/student/sc">选修课程</el-menu-item>
                    <el-menu-item index="/student/cd">选修记录</el-menu-item>
                </el-menu-item-group>
            </el-submenu>
            <el-submenu index="3">
                <template slot="title">
                    <i class="el-icon-edit-outline"></i>
                    <span slot="title">学分管理</span>
                </template>
                <el-menu-item-group>
                    <el-menu-item index="/student/credit">学分查看</el-menu-item>
                    <el-menu-item index="/student/grades">成绩总览</el-menu-item>
                </el-menu-item-group>
            </el-submenu>
            <el-menu-item index="/student">
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
</div>
</template>

<script>

    export default {
        name: "Main",
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
            toProfile(){
                this.$router.push("/student/self");
            },
            logout(){
                this.$router.push("/logout");
            }
        },
        beforeCreate() {
            let t = this.$store.state.power;
            if(t===2){
                this.$router.push('/teacher')
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