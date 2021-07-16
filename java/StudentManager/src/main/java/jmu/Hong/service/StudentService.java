package jmu.Hong.service;

import jmu.Hong.pojo.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StudentService {
    StudentInfo getStudentInfo(String sno);

    List<StudentCoursesInfo> getCoursesInfo(String sno);

    List<Award> getAwardsByID(String sno);

    List<SCGrade> getSCGrade(String sno);

    List<Grades> getGrades(String sno);

    SumCredits getCreditSum(String sno);

    int changePassword(String uid, String pwd);

    int changeAddress(String sno, String pCode, String address);
}
