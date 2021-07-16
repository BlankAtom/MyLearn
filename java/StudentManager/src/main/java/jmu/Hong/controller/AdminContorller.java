package jmu.Hong.controller;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonProcessingException;
import jmu.Hong.pojo.*;
import jmu.Hong.service.AdminService;
import jmu.Hong.util.JavaUtil;
import jmu.Hong.util.JsonUtil;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin")
public class AdminContorller {
    @Autowired
    @Qualifier("AdminServiceImpl")
    private AdminService adminService;

    @RequestMapping("/students")
    public String allStudents(@RequestParam("username") String username) throws JsonProcessingException {
        List<StudentInfo> infos = adminService.getAllStudents(username);
        return JsonUtil.getJson(infos);
    }


    @RequestMapping("/allColleges")
    public String allColleges() throws JsonProcessingException {
        List<Map> infos = adminService.getAllColleges();
        return JsonUtil.getJson(infos);
    }

    @RequestMapping("/allMajors")
    public String allMajors(@RequestParam("college")String college) throws JsonProcessingException {
        List<Map> infos = adminService.getAllMajors(college);
        return JsonUtil.getJson(infos);
    }

    @RequestMapping("/allClasses")
    public String allClasses(@RequestParam("major")String major) throws JsonProcessingException {
        List<Map> infos = adminService.getAllClasses(major);
        return JsonUtil.getJson(infos);
    }

    @RequestMapping("/allFroms")
    public String allFroms() throws JsonProcessingException {
        List<Map> infos = adminService.getAllFroms();
        return JsonUtil.getJson(infos);
    }

    @RequestMapping("/udpStudent")
    public String udpStudent(@Param("sno")String sno, @Param("sname")String sname, @Param("pinyin")String pinyin,
                             @Param("classname")String classname, @Param("sex")String sex, @Param("birth")String birth,
                             @Param("college")String college, @Param("major")String major, @Param("cardId")String cardId,
                             @Param("fromname")String fromname, @Param("phone")String phone, @Param("email")String email,
                             @Param("address")String address) throws JsonProcessingException, ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        int sexId = getSexIdByName(sex);
        String classId = getClassIdByName(classname);
        Date date = sdf.parse(birth.trim());
        birth = date.toString();
        int fromId=getProvinceIdByName(fromname);
        int t = adminService.udpStudent(sno,  sname, Integer.toString(sexId), birth, classId, phone, email, cardId, pinyin, Integer.toString(fromId));
        int t2 = adminService.udpAddress(sno, address);

        RetMessage message = JavaUtil.getReturn(t|t2, "修改成功", "修改失败");
        return JsonUtil.getJson(message);
    }

    @RequestMapping("/insertStudent")
    public String insertStudent(@Param("sno")String sno, @Param("sname")String sname, @Param("pinyin")String pinyin,
                                @Param("classname")String classname, @Param("sex")String sex, @Param("birth")String birth,
                                @Param("college")String college, @Param("major")String major, @Param("cardId")String cardId,
                                @Param("fromname")String fromname, @Param("phone")String phone, @Param("email")String email,
                                @Param("address")String address) throws ParseException, JsonProcessingException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
        int sexId = getSexIdByName(sex);
        String classId = getClassIdByName(classname);
        System.out.println(birth);
        //birth = sdf.format(birth);
        System.out.println(birth);
        int fromId=getProvinceIdByName(fromname);
        int t = adminService.insertStudent(sno,  sname, Integer.toString(sexId), birth, classId, phone, email, cardId, pinyin, Integer.toString(fromId));
        int t2 = adminService.udpAddress(sno, address);

        RetMessage message = JavaUtil.getReturn(t|t2, "修改成功", "修改失败");
        return JsonUtil.getJson(message);
    }

    @RequestMapping("/allOffice")
    public String getAllOffice() throws JsonProcessingException {
        List<Office> offices = adminService.getAllOffice();
        return JsonUtil.getJson(offices);
    }

    @RequestMapping("/getSexIdByName")
    public int getSexIdByName(@RequestParam("sexname") String sexname){
        return adminService.getSexIdByName(sexname);
    }

    @RequestMapping("/getClassIdByName")
    public String getClassIdByName(@RequestParam("classname") String classname){
        return adminService.getClassIdByName(classname);
    }

    @RequestMapping("/getProvinceIdByName")
    public int getProvinceIdByName(@RequestParam("pname") String pname){
        return adminService.getProvinceIdByName(pname);
    }

    @RequestMapping("/getStudentByInfo")
    public String getStudentByInfo(@RequestParam("classname") String classname,
                                   @RequestParam("sno") String sno,
                                   @RequestParam("sname") String sname ) throws JsonProcessingException {
        List<StudentInfo> infos = adminService.getStudentsByInfo(classname, sno, sname);
        return JsonUtil.getJson(infos);
    }


    @RequestMapping("/addTeacher")
    public String addTeacher(@RequestParam("tno")String tno, @RequestParam("tname")String tname,
                             @RequestParam("pinyin")String pinyin, @RequestParam("ranks")String ranks,
                             @RequestParam("sex")String sex, @RequestParam("age")String age,
                             @RequestParam("office")String office, @RequestParam("phone")String phone,
                             @RequestParam("email")String email, @RequestParam("address")String address ) throws JsonProcessingException {
        //性别已经是1-2
        //需要转化的是office
        int t = adminService.addTeacher(tno, tname, pinyin, ranks, sex, age, office, phone, email, address);
        RetMessage message = JavaUtil.getReturn(t, "插入成功", "插入失败");
        return JsonUtil.getJson(message);
    }

    @RequestMapping("/allTeacher")
    public String allTeacher() throws JsonProcessingException {
        List<TeacherInfo> infos = adminService.getAllTeacher();
        return JsonUtil.getJson(infos);
    }

    @RequestMapping("/udpTeacher")
    public String udpTeacher(@RequestParam("tno") String tno, @RequestParam("tname") String tname,@RequestParam("pinyin") String pinyin,
                             @RequestParam("oname") String oname,@RequestParam("sex") String sex,@RequestParam("age") String age,
                            @RequestParam("ranks") String ranks,@RequestParam("phone") String phone,
                             @RequestParam("email") String email,@RequestParam("address") String address,
                             @RequestParam("bank") String bank,@RequestParam("bno") String bno) throws JsonProcessingException {
        int sexId = getSexIdByName(sex);
        int oid = getOfficeIdByName(oname);
        int t = adminService.udpTeacher(tno, tname, pinyin, oid, sexId, age, ranks, phone, email, address);
        int t2 = adminService.udpBank(tno, bank, bno);

        RetMessage message = JavaUtil.getReturn(t|t2, "修改成功", "修改失败");
        return JsonUtil.getJson(message);
    }

    @RequestMapping("/getOfficeIdByName")
    public int getOfficeIdByName(@RequestParam("oname")String oname){
        int t = adminService.getOfficeIdByName(oname);
        return t;
    }

    @RequestMapping("/searchTeacher")
    public String searchTeacher(@RequestParam("oname")String oname, @RequestParam("tno")String tno,
                                @RequestParam("tname")String tname) throws JsonProcessingException {
        List<TeacherInfo> infos = adminService.getTeacher(oname, tno, tname);
        return JsonUtil.getJson(infos);
    }

    @RequestMapping("/getAllClasses")
    public String getAllClasses_2() throws JsonProcessingException {
        List<Map> maps = adminService.getAllClasses_2();
        return JsonUtil.getJson(maps);
    }

    @RequestMapping("/allCourseName")
    public String getAllCourseNames() throws JsonProcessingException {
        List<Map> maps = this.adminService.getAllCourseNames();
        return JsonUtil.getJson(maps);
    }

    @RequestMapping("/addCourse")
    public String addCourse(@RequestParam("classname")String classname, @RequestParam("course")String course,
                            @RequestParam("tname")String tname, @RequestParam("defaultScore")String defaultScore,
                            @RequestParam("deadline")String deadline, @RequestParam("ctype")String ctype) throws JsonProcessingException {
        String classId = getClassIdByName(classname);
        String cno = getCourseIdByName(course);
        String tno = getTeacherIdByName(tname);
        System.out.println("cno: "+cno + "  tno: " + tno);
        int t = this.adminService.addCourse(classId, tno, cno, defaultScore, deadline, ctype);
        RetMessage message = JavaUtil.getReturn(t, "插入成功", "插入失败");
        return JsonUtil.getJson(message);
    }

    @RequestMapping("/getCourseIdByName")
    public String getCourseIdByName(@RequestParam("cname") String name){
        return adminService.getCourseIdByName(name);
    }

    @RequestMapping("/getTeacherIdByName")
    public String getTeacherIdByName(@RequestParam("tname")String name){
        return adminService.getTeacherIdByName(name);
    }

    @RequestMapping("/allCourses")
    public String getAllCourses(@RequestParam("classId")String classId,
                                @RequestParam("teacher")String tname,
                                @RequestParam("course")String cname) throws JsonProcessingException {
        List<CouClass> couClasses = adminService.getAllCourse(classId, tname, cname);
        return JsonUtil.getJson(couClasses);
    }

    @RequestMapping("/udpCourse")
    public String udpCourse(@RequestParam("classId")String classId, @RequestParam("cname")String cname,
                            @RequestParam("tname")String tname, @RequestParam("score_name")String score,
                            @RequestParam("deadline")String deadline, @RequestParam("class_type")String ctype) throws JsonProcessingException {
        String cno = getCourseIdByName(cname);
        String tno = getTeacherIdByName(tname);
        if("未录入".equals(score)){
            score = "1";
        }else{
            score = "2";
        }

        int t = adminService.udpCourse(classId, tno, cno, ctype, score, deadline);
        RetMessage message = JavaUtil.getReturn( t, "修改成功", "修改失败");
        return JsonUtil.getJson(message);
    }




}
