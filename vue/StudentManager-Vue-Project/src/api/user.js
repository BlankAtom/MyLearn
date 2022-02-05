import request from "../utils/request";
import qs from "qs"
export async function login(data) {
    return request({
        url: "/login",
        method: "post",
        data,
    });
}

export function getStudentProfile(data) {
    return request({
        url: "/student/info",
        method: "post",
        data,
    });
}

export function changePwd(data) {
    return request({
        url: "/student/changePwd",
        method: "post",
        data,
    });
}

export function changeAddress(data) {
    return request({
        url: "/student/changeAddress",
        method: "post",
        data,
    });
}

export function getAwards(data) {
    return request({
        url: "/student/award",
        method: "post",
        data,
    });
}

export function getCourses(data) {
    return request({
        url: "/student/grade",
        method: "post",
        data,
    });
}

export function getCredits(data) {
    return request({
        url: "/student/credits",
        method: "post",
        data,
    });
}

export function getAllGrades(data) {
    return request({
        url: "/student/grades",
        method: "post",
        data,
    });
}

export function getTeacherProfile(data) {
    return request({
        url: "/teacher/info",
        method: "post",
        data
    });
}

export function changeTeacherAddress(data) {
    return request({
        url: "/teacher/address",
        method: "post",
        data
    });
}

export function getOnlyBySno(data) {
    return request({
        url: "/teacher/onlyCourse",
        method: "post",
        data
    });
}

export function setGrade(data) {
    return request({
        url: "/teacher/grade",
        method: "post",
        data
    });
}

export function changeTeacherBank(data) {
    return request({
        url: "/teacher/bank",
        method: "post",
        data
    });
}

export function getGradeInfo(data) {
    return request({
        url: "/teacher/gradeInfo",
        method: "post",
        data
    });
}

export function getClassList(data) {
    return request({
        url: "/teacher/classes",
        method: "post",
        data
    });
}

export function getStudentDetail(data) {
    return request({
        url: "/admin/student",
        method: "post",
        data
    });
}

export function getAllStudents(data) {
    return request({
        url: "/admin/students",
        method: "post",
        data
    });
}

export function getStudentsByInfo(data) {
    return request({
        url: "/admin/getStudentByInfo",
        method: "post",
        data
    });
}

export function getAllColleges() {
    return request({
        url: "/admin/allColleges",
        method: "post"
    });
}

export function getAllMajors(data) {
    return request({
        url: "/admin/allMajors",
        method: "post",
        data
    });
}

export function getAllClasses(data) {
    return request({
        url: "/admin/allClasses",
        method: "post",
        data
    });
}

export function getAllFroms() {
    return request({
        url: "/admin/allFroms",
        method: "post"
    });
}

export function udpStudent(data) {
    return request({
        url: "/admin/udpStudent",
        method: "post",
        data
    });
}

export function insertStudent(data) {
    return request({
        url: "/admin/insertStudent",
        method: "post",
        data
    });
}

export function getAllOffice() {
    return request({
        url: "/admin/allOffice",
        method: "post"
    });
}

export function addTeacher(data) {
    return request({
        url: "/admin/addTeacher",
        method: "post",
        data
    });
}

export function allTeacher() {
    return request({
        url: "/admin/allTeacher",
        method: "post"
    });
}

export function addCourse(data) {
    return request({
        url: "/admin/addCourse",
        method: "post",
        data
    });
}

export function udpTeacher(data) {
    return request({
        url: "/admin/udpTeacher",
        method: "post",
        data
    });
}

export function getTeacher(data) {
    return request({
        url: "/admin/searchTeacher",
        method: "post",
        data
    });
}

export function getAllClasses2() {
    return request({
        url: "/admin/getAllClasses",
        method: "post"
    });
}

export function getAllCourseNames() {
    return request({
        url: "/admin/allCourseName",
        method: "post"
    });
}

export function allCourses(data) {
    return request({
        url: "/admin/allCourses",
        method: "post",
        data
    });
}

export function udpCourse(data) {
    return request({
        url: "/admin/udpCourse",
        method: "post",
        data
    });
}

export function logout() {
    return request({
        url: "/logout",
        method: "post",
    });
}

export function register() {
    return request({
        url: "/register",
        method: "post",
    });
}
