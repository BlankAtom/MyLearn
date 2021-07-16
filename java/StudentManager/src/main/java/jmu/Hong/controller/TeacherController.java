package jmu.Hong.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import jmu.Hong.pojo.CnoCourse;
import jmu.Hong.pojo.RetMessage;
import jmu.Hong.pojo.TeacherGradeInfo;
import jmu.Hong.pojo.TeacherInfo;
import jmu.Hong.service.TeacherService;
import jmu.Hong.util.JavaUtil;
import jmu.Hong.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/teacher")
public class TeacherController {
    @Autowired
    @Qualifier("TeacherServiceImpl")
    private TeacherService teacherService;

    @RequestMapping("/info")
    public String getInfo(@RequestParam("username") String tno ) throws JsonProcessingException {
        TeacherInfo teacherInfo = teacherService.getInfo(tno);
        return JsonUtil.getJson(teacherInfo);
    }

    @RequestMapping("/address")
    public String changeAddress(@RequestParam("username") String tno,
                                @RequestParam("post") String post,
                                @RequestParam("address")String newAddress) throws JsonProcessingException {
        int t = this.teacherService.changeTeaAddress(tno, post, newAddress);
        RetMessage res = JavaUtil.getReturn(t, "修改成功", "修改失败");
        System.out.println(res);
        return JsonUtil.getJson(res);
    }

    @RequestMapping("/bank")
    public String changeBank(@RequestParam("username") String tno,
                                @RequestParam("bank") String bank,
                                @RequestParam("bankcard")String bankCard) throws JsonProcessingException {
        int t = this.teacherService.changeTeaBank(tno, bank, bankCard);
        RetMessage res = JavaUtil.getReturn(t, "修改成功", "修改失败");
        System.out.println(res);
        return JsonUtil.getJson(res);
    }

    @RequestMapping("/onlyCourse")
    public String getOnlyCourses (@RequestParam("sno") String sno) throws JsonProcessingException {
        List<CnoCourse> courses = this.teacherService.getOnlyCoursesBySno(sno);
        return JsonUtil.getJson(courses);
    }

    @RequestMapping("/grade")
    public String insertGrade(@RequestParam("sno") String sno, @RequestParam("ctype") String ctype,
                              @RequestParam("etype")String etype, @RequestParam("course") String course,
                              @RequestParam("percent") int percent, @RequestParam("grade") String grade) throws JsonProcessingException {
        int t = teacherService.insertGrade(sno, ctype, etype, course, percent, grade);
        RetMessage message = JavaUtil.getReturn(t, "插入成功", "插入失败");
        return JsonUtil.getJson(message);
    }

    @RequestMapping("/gradeInfo")
    public String getGradeInfo(@RequestParam("username") String tno, @RequestParam("classname")String classname,
                               @RequestParam("sno")String sno, @RequestParam("sname") String sname) throws JsonProcessingException {
        List<TeacherGradeInfo> infos = teacherService.getGradeInfo(tno, classname, sno, sname);
        return JsonUtil.getJson(infos);
    }

    @RequestMapping("/classes")
    public String getClasses(@RequestParam("username")String tno) throws JsonProcessingException {
        List<Map> classes = teacherService.getClasses(tno);
        System.out.println(classes);
        return JsonUtil.getJson(classes);
    }

}
