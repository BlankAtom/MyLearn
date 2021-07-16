package jmu.Hong.service;

import jmu.Hong.mapper.StudentMapper;
import jmu.Hong.pojo.*;

import java.util.List;

public class StudentServiceImpl implements StudentService {
    private StudentMapper studentMapper;
    public void setStudentMapper(StudentMapper studentMapper){this.studentMapper = studentMapper;}

    @Override
    public StudentInfo getStudentInfo(String sno) {
        return studentMapper.getStudentInfo(sno);
    }

    @Override
    public List<StudentCoursesInfo> getCoursesInfo(String sno) {
        return this.studentMapper.getCoursesInfo(sno);
    }

    @Override
    public int changePassword(String uid, String pwd) {
        return this.studentMapper.changePassword(uid, pwd);
    }

    @Override
    public int changeAddress(String sno, String pCode, String address) {
        return this.studentMapper.changeAddress(sno, pCode, address);
    }

    @Override
    public List<Grades> getGrades(String sno) {
        return this.studentMapper.getGrades(sno);
    }

    @Override
    public List<SCGrade> getSCGrade(String sno) {
        return this.studentMapper.getSCGrade(sno);
    }

    @Override
    public List<Award> getAwardsByID(String sno) {
        return this.studentMapper.getAwardsByID(sno);
    }

    @Override
    public SumCredits getCreditSum(String sno) {
        return this.studentMapper.getCreditSum(sno);
    }
}
