<template>
    <div>
        <el-row>
            <el-container>
                <el-aside>
                    <el-image :src="imgsrc"></el-image>
                    <el-row>
                        <div class="aside-box-name">{{userProfile.tname}}</div>
                        <div class="aside-box-name aside-box-pingyin">{{userProfile.pinyin}}</div>
                    </el-row>
                </el-aside>
                <section class="el-section">
                    <div style="margin: 0px 0px 20px 0px; padding-bottom: 2px;">
                        <el-divider content-position="center" >
                            <span style="font-size: 2em; ">个人信息</span>
                        </el-divider>
                    </div>
                    <div >
                        <el-row gutter="21" align="center" justify="center">
                            <el-col span="12">
                                <div class="grid-content bg-purple-light">
                                    <span class="grid-content-head">教研室：</span>{{userProfile.oname}}
                                </div>
                            </el-col>
                            <el-col span="9">
                                <div class="grid-content bg-purple-light">
                                    <span class="grid-content-head">工号：</span>{{userProfile.tno}}
                                </div>
                            </el-col>
                        </el-row>
                        <el-row justify="center" align="middle" gutter="21">
                            <el-col span="6">
                                <div class="grid-content bg-purple-light">
                                    <span class="grid-content-head">性别：</span>{{userProfile.sex}}
                                </div>
                            </el-col>
                            <el-col span="10">
                                <div class="grid-content bg-purple-light">
                                    <span class="grid-content-head">年龄：</span>{{userProfile.age}}
                                </div>
                            </el-col>
                            <el-col span="5">
                                <div class="grid-content bg-purple ">
                                </div>
                            </el-col>
                        </el-row>
                        <el-row justify="center" align="middle" gutter="20">
                            <el-col span="8">
                                <div class="grid-content bg-purple-light">
                                    <span class="grid-content-head">学院：</span>{{userProfile.cname}}
                                </div>
                            </el-col>
                            <el-col span="7">
                                <div class="grid-content bg-purple-light">
                                    <span class="grid-content-head">职阶：</span>{{userProfile.ranks}}
                                </div>
                            </el-col>
                            <el-col span="6">
                                <div class="grid-content bg-purple ">
                                </div>
                            </el-col>
                        </el-row>
                        <el-row justify="center" align="middle" gutter="20">
                            <el-col span="14">
                                <div class="grid-content bg-purple-light">
                                    <span class="grid-content-head">身份证号：</span>{{userProfile.cardId}}
                                </div>
                            </el-col>
                            <el-col span="7">
                                <div class="grid-content bg-purple ">
                                </div>
                            </el-col>
                        </el-row>
                        <el-row justify="center" align="middle" gutter="20">
                            <el-col span="10">
                                <div class="grid-content bg-purple-light">
                                    <span class="grid-content-head">联系电话：</span>{{userProfile.phone}}
                                </div>
                            </el-col>
                            <el-col span="9">
                                <div class="grid-content bg-purple-light">
                                    <span class="grid-content-head">邮箱：</span>{{userProfile.email}}
                                </div>
                            </el-col>
                            <el-col span="2">
                                <div class="grid-content bg-purple ">
                                </div>
                            </el-col>
                        </el-row>
                        <el-row justify="center" align="middle" gutter="20">
                            <el-col span="21">
                                <div class="grid-content bg-purple-light">
                                    <span class="grid-content-head">地址：</span>{{userProfile.address}}
                                </div>
                            </el-col>
                            <el-col span="0">
                                <div class="grid-content bg-purple ">
                                </div>
                            </el-col>
                        </el-row>
                        <el-row gutter="20">
                            <el-col span="6">
                                <div class="grid-content bg-purple-light">
                                    <span class="grid-content-head">邮编：</span>{{userProfile.pcode}}
                                </div>
                            </el-col>
                            <el-col span="10">
                                <div class="grid-content bg-purple-light">
                                    <span class="grid-content-head">开户银行：</span>{{userProfile.bank}}
                                </div>
                            </el-col>
                            <el-col span="5">
                                <div class="grid-content bg-purple ">
                                </div>
                            </el-col>
                        </el-row>
                        <el-row justify="center" align="middle" gutter="20">

                            <el-col span="15">
                                <div class="grid-content bg-purple-light">
                                    <span class="grid-content-head">银行卡号：</span>{{userProfile.bno}}
                                </div>
                            </el-col>
                            <el-col span="6">
                                <div class="grid-content bg-purple ">
                                </div>
                            </el-col>
                        </el-row>
                    </div>
                </section>
            </el-container>
        </el-row>
    </div>
</template>

<script>
    export default {
        name: "Profile",
        data() {
            return {
                imgsrc: 'https://cube.elemecdn.com/6/94/4d3ea53c084bad6931a56d5158a48jpeg.jpeg',
                userProfile: {
                    tname: '',pinyin: '', oname: '',tno: '',
                    sex: '',age: '',cardId: '',
                    phone: '',email: '',cname: '',
                    ranks: '',office: '',
                    address: '',pcode: '',
                    bank: '',bno: ''

                }
            }
        },
        beforeCreate() {
            this.$store.dispatch('getTeaProfile').then(res => {
                this.userProfile = res.data;
            });
        }
    }
</script>

<style scoped>
    .el-aside{
        width: 20%;
        margin: 80px 50px 0px 50px;
        min-width: 200px;
    }
    .el-section{
        margin: 20px;
        width: 80%;
        min-width: 650px;
    }
    .el-row {
        margin-bottom: 10px;
    }
    .el-col {
        /*margin-bottom: 40px;*/
        border-radius: 4px;
        font-size: 1.1em;
    }
    .bg-purple{
        background-color: #99a9bf ;
        height: 44px;
    }
    .bg-purple-light {
        background: #e5e9f2;
    }
    .grid-content {
        border-radius: 4px;
        min-height: 36px;
        padding-left: 20px;
        line-height: 1.7;
        font-family: "Helvetica Neue",Helvetica,"PingFang SC","Hiragino Sans GB","Microsoft YaHei","微软雅黑",Arial,sans-serif;

    }
    .grid-content-head{
        color: #409EFF;
        font-weight: bold;
        font-size: 1.5em;
    }
    .aside-box-name{
        margin-top: 100px;
        text-align: center;
        font-size: 3em;
        font-family: "Microsoft YaHei UI Light";
        border: #909399 dashed 3px;
        border-top: 0px;
        border-left: 0px;
        border-right: 0px;
    }
    .aside-box-pingyin{
        font-size: 1.4em;
        margin-top: 0px;
        border: none;
    }
</style>