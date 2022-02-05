import Vue from 'vue'
import VueRouter from 'vue-router'
import Main from '../views/index/StudentMain'
import { routerMode } from "../config/settings";

import PageNotFound from "../views/NotFound";
Vue.use(VueRouter)

const constantRoutes = [{
        path: '/teacher',
        component: () => import('../views/index/TeacherMain'),
        props: true,
        children: [
            {
                path: '/teacher/self',
                component: () => import('../views/teacher/Profile')
            },
            {
                path: '/teacher/password',
                component: () => import('../views/student/ChangePwd')
            },
            {
                path: '/teacher/address',
                component: () => import('../views/teacher/Address')
            },
            {
                path: '/teacher/bank',
                component: () => import('../views/teacher/Bank')
            },
            {
                path: '/teacher/bill',
                component: () => import('../views/teacher/AccountList')
            },
            {
                path: '/teacher/schedule',
                component: () => import('../views/teacher/Schedule')
            },
            {
                path: '/teacher/course',
                component: () => import('../views/teacher/CourseSettings')
            },
            {
                path: '/teacher/student',
                component: () => import('../views/teacher/StudentInfo')
            },
            {
                path: '/teacher/grade',
                component: () => import('../views/teacher/SetGrade')
            },
            {
                path: '/teacher/notice',
                component: () => import('../views/teacher/Notice')
            },
            {
                path: '/main',
                redirect: '/teacher/self'
            }
        ]
    },{
        name: 'Student',
        path: '/student',
        component: () => import('../views/index/StudentMain'),
        props: true,
        children: [
            {
                path: '/student/self',
                meta: {
                    needLogin: true
                },
                component: () => import('../views/student/Profile')
            },
            {
                path: '/student/cpd',
                meta: {
                    needLogin: true
                },
                component: ()=> import('../views/student/ChangePwd')
            },
            {
                path: '/student/cas',
                meta: {
                    needLogin: true
                },
                component: () => import('../views/student/ChangeAddress')
            },
            {
                path: '/student/cbk',
                meta: {
                    needLogin: true
                },
                component: () => import('../views/student/ChangeBankinfo')
            },
            {
                path: '/student/awards',
                meta: {
                    needLogin: true
                },
                component: () => import('../views/student/AwardInfo')
            },
            {
                path: '/student/bill',
                meta: {
                    needLogin: true
                },
                component: () => import('../views/student/AccountDetail')
            },
            {
                path: '/student/schedule',
                meta: {
                    needLogin: true
                },
                component: () => import('../views/student/StudentSchedule')
            },
            {
                path: '/student/sc',
                meta: {
                    needLogin: true
                },
                component: () => import('../views/student/ChooseCourse')
            },
            {
                path: '/student/cd',
                meta: {
                    needLogin: true
                },
                component: () => import('../views/student/ChosenDetail')
            },
            {
                path: '/student/credit',
                meta: {
                    needLogin: true
                },
                component: () => import('../views/student/StudentCredit')
            },
            {
                path: '/student/grades',
                meta: {
                    needLogin: true
                },
                component: () => import('../views/student/Grades')
            }
        ]
    },{
        path: '/admin',
        component: () => import('../views/index/AdminMain'),
        children: [
            {
                path: '/admin/addStudent',
                component: () => import('../views/admin/InsertStudent'),
                meta: {
                    needLogin: true
                },
            },
            {
                path: '/admin/allStudent',
                meta: {
                    needLogin: true
                },
                component: () => import('../views/admin/AllStudent')
            },
            {
                path: '/admin/addTeacher',
                meta: {
                    needLogin: true
                },
                component: () => import('../views/admin/InsertTeacher')
            },
            {
                path: '/admin/allTeacher',
                meta: {
                    needLogin: true
                },
                component: () => import('../views/admin/AllTeacher')
            },
            {
                path: '/admin/addCourse',
                meta: {
                    needLogin: true
                },
                component: () => import('../views/admin/InsertCourse')
            },
            {
                path: '/admin/allCourse',
                meta: {
                    needLogin: true
                },
                component: () => import('../views/admin/AllCourse')
            },
        ]
    },{
        path: '/main',
        component: Main,
        props: true,

    },{
        path: '/',
        component: Main,
        meta: {
            auth: false
        }
    },{
        name: 'Login',
        path: '/login',
        component: () => import('../views/login'),
        props: true
    },{
        path: '/logout',
        redirect: '/login'
    },{
        path: '*',
        redirect: '/notfound'
    },{
        path: '/notfound',
        component: PageNotFound
    }
]

const router = new VueRouter({
    mode: routerMode,
    scrollBehavior: () => ({
        y: 0,
    }),
    routes: constantRoutes,
});



const originalPush = VueRouter.prototype.push;
VueRouter.prototype.push = function push(location) {
    return originalPush.call(this, location).catch((err) => err);
};
export function resetRouter() {
    router.matcher = new VueRouter({
        mode: routerMode,
        scrollBehavior: () => ({
            y: 0,
        }),
        routes: constantRoutes,
    }).matcher;
}

export default router
