package jmu.Hong.service;

import jmu.Hong.mapper.AdminMapper;
import jmu.Hong.pojo.*;

import java.util.List;
import java.util.Map;

public class AdminServiceImpl  implements  AdminService{

    private AdminMapper adminMapper;
    public void setAdminMapper(AdminMapper adminMapper){this.adminMapper = adminMapper;}

    @Override
    public List<StudentInfo> getAllStudents(String username) {
        return adminMapper.getAllStudents(username);

    }

    @Override
    public List<Map> getAllClasses(String majorname) {
        return adminMapper.getAllClasses(majorname);
    }

    @Override
    public List<Map> getAllColleges() {
        return adminMapper.getAllColleges();
    }

    @Override
    public List<Map> getAllMajors(String collegename) {
        return adminMapper.getAllMajors(collegename);
    }

    @Override
    public List<Map> getAllFroms() {
        return adminMapper.getAllFroms();
    }

    @Override
    public int getSexIdByName(String sexname) {
        return adminMapper.getSexIdByName(sexname);
    }

    @Override
    public String getClassIdByName(String classname) {
        return adminMapper.getClassIdByName(classname);
    }

    @Override
    public int udpStudent(String sno, String sname, String sex, String birth, String classId,
                          String phone, String email, String cardId, String pinyin, String fromId) {
        return adminMapper.udpStudent(sno, sname, sex, birth, classId, phone, email, cardId, pinyin, fromId);
    }

    @Override
    public int getProvinceIdByName(String pname) {
        return adminMapper.getProvinceIdByName(pname);
    }

    @Override
    public int udpAddress(String sno, String address) {
        return adminMapper.udpAddress(sno, address);
    }

    @Override
    public List<StudentInfo> getStudentsByInfo(String classname, String sno, String sname) {
        return adminMapper.getStudentsByInfo(classname, sno, sname);
    }

    @Override
    public int insertStudent(String sno, String sname, String sex, String birth, String classId, String phone, String email, String cardId, String pinyin, String fromId) {
        return adminMapper.insertStudent(sno, sname, sex, birth, classId, phone, email, cardId, pinyin, fromId);
    }

    @Override
    public List<Office> getAllOffice() {
        return adminMapper.getAllOffice();
    }

    @Override
    public int addTeacher(String tno, String tname, String pinyin, String ranks, String sex, String age, String office, String phone, String email, String address) {
        return adminMapper.addTeacher(tno, tname, pinyin, ranks, sex, age, office, phone, email, address);
    }

    @Override
    public List<TeacherInfo> getAllTeacher() {
        return adminMapper.getAllTeacher();
    }

    @Override
    public int getOfficeIdByName(String oname) {
        return adminMapper.getOfficeIdByName(oname);
    }

    @Override
    public int udpTeacher(String tno, String tname, String pinyin, int oid, int sex, String age, String ranks, String phone, String email, String address) {
        return adminMapper.udpTeacher(tno, tname, pinyin, oid, sex, age, ranks, phone, email, address);
    }

    @Override
    public int udpBank(String user, String company, String card) {
        return adminMapper.udpBank(user, company, card);
    }

    @Override
    public List<TeacherInfo> getTeacher(String oname, String tno, String tname) {
        return adminMapper.getTeacher(oname, tno, tname);
    }

    @Override
    public List<Map> getAllClasses_2() {
        return adminMapper.getAllClasses_2();
    }

    @Override
    public List<Map> getAllCourseNames() {
        return adminMapper.getAllCourseNames();
    }

    @Override
    public int addCourse(String classname, String tname, String cname, String scoreState, String deadline, String openType) {
        return adminMapper.addCourse(classname, tname, cname, scoreState, deadline, openType);
    }

    @Override
    public String getCourseIdByName(String cname) {
        return adminMapper.getCourseIdByName(cname);
    }

    @Override
    public String getTeacherIdByName(String tname) {
        return adminMapper.getTeacherIdByName(tname);
    }

    @Override
    public List<CouClass> getAllCourse(String classId, String tname, String cname) {
        return adminMapper.getAllCourse(classId, tname, cname);
    }

    @Override
    public int udpCourse(String classId, String tno, String cno, String class_type, String score_state, String deadline) {
        return adminMapper.udpCourse(classId, tno, cno, class_type, score_state, deadline);
    }
}
