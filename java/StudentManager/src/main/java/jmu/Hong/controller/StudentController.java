package jmu.Hong.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import jmu.Hong.pojo.*;
import jmu.Hong.service.LoginService;
import jmu.Hong.service.StudentService;
import jmu.Hong.util.JavaUtil;
import jmu.Hong.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/student")
public class StudentController {

    @Autowired
    @Qualifier("StudentServiceImpl")
    public StudentService studentService;

    @Autowired
    @Qualifier("LoginServiceImpl")
    private LoginService loginService;


    @RequestMapping("/info")
    public String getInfo(@RequestParam("username") String sno) throws JsonProcessingException {
        StudentInfo studentInfo = studentService.getStudentInfo(sno);
        return  JsonUtil.getJson(studentInfo);
    }


    @RequestMapping("/course")
    public String getCourses(@RequestParam("username") String sno) throws JsonProcessingException {
        List<StudentCoursesInfo> coursesInfos = studentService.getCoursesInfo(sno);
        return JsonUtil.getJson(coursesInfos);
    }

    @RequestMapping("/award")
    public String getAwards(@RequestParam("username") String sno) throws JsonProcessingException {
        List<Award> awards = studentService.getAwardsByID(sno);
        return JsonUtil.getJson(awards);
    }

    @RequestMapping("/grade")
    public String getSCGrade(@RequestParam("username") String sno) throws JsonProcessingException {
        List<SCGrade> scGrades = studentService.getSCGrade(sno);
        return JsonUtil.getJson(scGrades);
    }

    @RequestMapping("/grades")
    public String getFinalGrades(@RequestParam("username") String sno) throws JsonProcessingException {
        List<Grades> grades = studentService.getGrades(sno);
        String str = JsonUtil.getJson(grades);
        System.out.println(str);
        return str;
    }

    @RequestMapping("/credits")
    public String getCreditSum(@RequestParam("username") String sno) throws JsonProcessingException {
        SumCredits sumCredits = studentService.getCreditSum(sno);
        return JsonUtil.getJson(sumCredits);
    }

    @RequestMapping("/changePwd")
    public String changePwd(@RequestParam("username") String sno,
                            @RequestParam("old") String oldPwd,
                            @RequestParam("new") String newPwd) throws JsonProcessingException {
        User user = loginService.userLogin(sno, oldPwd);
        RetMessage res = null;
        if(user!=null){
            //修改密码
            studentService.changePassword(sno, newPwd);
            res = JavaUtil.getReturn(1, "修改成功");
        }
        else{
            //返回一个错误
            res = JavaUtil.getReturn(0, "修改失败，旧密码匹配失败");
        }

        String s = JsonUtil.getJson(res);
        System.out.println(s);
        return s;
    }

    @RequestMapping("/changeAddress")
    public String changeAddress(@RequestParam("username") String sno,
                                @RequestParam("post") String post,
                                @RequestParam("address")String newAddress) throws JsonProcessingException {
        int t = this.studentService.changeAddress(sno, post, newAddress);
        RetMessage res = JavaUtil.getReturn(t, "修改成功", "修改失败");
        System.out.println(res);
        return JsonUtil.getJson(res);
    }


}
