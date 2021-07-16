package jmu.Hong.mapper;

import jmu.Hong.pojo.CouClass;
import jmu.Hong.pojo.Office;
import jmu.Hong.pojo.StudentInfo;
import jmu.Hong.pojo.TeacherInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

public interface AdminMapper {
    List<StudentInfo> getAllStudents(@Param("username") String username);

    List<Map> getAllColleges();
    List<Map> getAllMajors(@Param("collegename")String collegename);
    List<Map> getAllClasses(@Param("majorname")String majorname);
    List<Map> getAllFroms();
    int udpStudent(@Param("sno")String sno, @Param("sname")String sname, @Param("sex")String sex,
                   @Param("birth")String birth, @Param("classId")String classId, @Param("phone")String phone,
                   @Param("email")String email, @Param("cardId")String cardId, @Param("pinyin")String pinyin,
                    @Param("fromId")String fromId);
    int getSexIdByName(@Param("sexname")String sexname);
    String getClassIdByName(@Param("classname")String classname);
    int getProvinceIdByName(@Param("pname")String pname);
    int udpAddress(@Param("sno")String sno, @Param("address")String address);
    int insertStudent(@Param("sno")String sno, @Param("sname")String sname, @Param("sex")String sex,
                      @Param("birth")String birth, @Param("classId")String classId, @Param("phone")String phone,
                      @Param("email")String email, @Param("cardId")String cardId, @Param("pinyin")String pinyin,
                      @Param("fromId")String fromId);
    List<StudentInfo> getStudentsByInfo(@Param("classname")String classname, @Param("sno")String sno,@Param("sname")String sname);
    List<Office> getAllOffice();
    
    int addTeacher(@Param("tno")String tno, @Param("tname")String tname,
                   @Param("pinyin")String pinyin, @Param("ranks")String ranks,
                   @Param("sex")String sex, @Param("age")String age,
                   @Param("office")String office, @Param("phone")String phone,
                   @Param("email")String email, @Param("address")String address);

    List<TeacherInfo> getAllTeacher();
    int getOfficeIdByName(@Param("oname")String  oname);
    
    int udpTeacher(@Param("tno") String tno, @Param("tname") String tname, @Param("pinyin") String pinyin,
                   @Param("oid") int oid, @Param("sex") int sex, @Param("age") String age,
                   @Param("ranks") String ranks, @Param("phone") String phone, @Param("email") String email,
                   @Param("address") String address);

    int udpBank(@Param("user") String user, @Param("company")String company, @Param("card")String card);

    List<TeacherInfo> getTeacher(@Param("oname") String oname,@Param("tno") String tno,@Param("tname") String tname);

    List<Map> getAllClasses_2();

    List<Map> getAllCourseNames();

    int addCourse(@Param("classId")String classId, @Param("tno")String tno, @Param("cno")String cno,
                  @Param("scoreState")String scoreState, @Param("deadline")String deadline, @Param("classType")String openType);

    String getCourseIdByName(@Param("cname")String cname);
    String getTeacherIdByName(@Param("tname")String tname);
    List<CouClass> getAllCourse(@Param("classId")String classId, @Param("tname")String tname, @Param("cname")String cname);
    int udpCourse(@Param("classId")String classId, @Param("tno")String tno, @Param("cno")String cno,
                  @Param("ctype")String class_type, @Param("sstate")String score_state, @Param("deadline")String deadline);
}
