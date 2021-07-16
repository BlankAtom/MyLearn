package jmu.Hong.mapper;

import jmu.Hong.pojo.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StudentMapper {
    StudentInfo getStudentInfo(@Param("sno") String uid);
    List<StudentCoursesInfo> getCoursesInfo(@Param("sno") String sno);
    List<Award> getAwardsByID(@Param("sno") String sno);
    List<SCGrade> getSCGrade(@Param("sno") String sno);
    List<Grades> getGrades(@Param("sno") String sno);
    SumCredits getCreditSum(@Param("sno") String sno);

    int changePassword(@Param("uid") String uid, @Param("upwd") String pwd);
    int changeAddress(@Param("sno")String sno, @Param("post")String pCode, @Param("address")String address);
}
