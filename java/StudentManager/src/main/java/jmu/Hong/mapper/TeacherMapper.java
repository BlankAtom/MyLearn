package jmu.Hong.mapper;

import jmu.Hong.pojo.CnoCourse;
import jmu.Hong.pojo.TeacherGradeInfo;
import jmu.Hong.pojo.TeacherInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface TeacherMapper {
    TeacherInfo getInfo(@Param("tno")String tno);
    int changeTeaAddress(@Param("tno") String tno, @Param("pcode") String pCode, @Param("address") String address);
    int changeTeaBank(@Param("tno") String tno, @Param("bankCompany") String bank, @Param("bankCard") String bankCard);
    List<CnoCourse> getOnlyCoursesBySno(@Param("sno") String sno);
    int insertGrade(@Param("sno") String sno, @Param("ctype") String ctype,
                    @Param("etype") String etype, @Param("course")String couese,
                    @Param("percent") int percent, @Param("grade")String grade);
    List<TeacherGradeInfo> getGradeInfo(@Param("tno") String tno, @Param("classname") String classname,
                                        @Param("sno")String sno, @Param("sname")String sname);

    List<Map> getClasses(@Param("tno") String tno);
}
