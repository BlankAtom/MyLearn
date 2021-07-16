package jmu.Hong.service;

import jmu.Hong.pojo.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface AdminService {
    List<StudentInfo> getAllStudents(String username);

    List<Map> getAllColleges();
    List<Map> getAllMajors(String collegename);
    List<Map> getAllClasses(String majorname);
    List<Map> getAllFroms();
    int getSexIdByName(String sexname);
    String getClassIdByName(String classname);
    int udpStudent(String sno, String sname, String sex, String birth, String classId, String phone,
                   String email, String cardId, String pinyin, String fromId);
    int insertStudent(String sno, String sname, String sex, String birth, String classId, String phone,
                      String email, String cardId, String pinyin, String fromId);
    int getProvinceIdByName(String pname);
    int udpAddress(String sno, String address);
    List<StudentInfo> getStudentsByInfo(String classname, String sno, String sname);
    List<Office> getAllOffice();
    int addTeacher(String tno, String tname,
                   String pinyin, String ranks,
                   String sex, String age,
                   String office, String phone,
                   String email, String address);

    List<TeacherInfo> getAllTeacher();
    int getOfficeIdByName(String  oname);

    int udpTeacher(String tno,  String tname, String pinyin,int oid,  int sex,  String age,
                   String ranks,String phone, String email, String address);

    int udpBank( String user,String company,String card);

    List<TeacherInfo> getTeacher(String oname, String tno, String tname);

    List<Map> getAllClasses_2();
    List<Map> getAllCourseNames();
    int addCourse(String classId, String tno, String cno,String scoreState, String deadline, String openType);
    String getCourseIdByName(String cname);
    String getTeacherIdByName(String tname);

    List<CouClass> getAllCourse(String classId, String tname, String cname);
    int udpCourse(String classId, String tno, String cno, String class_type, String score_state,String deadline);
}
