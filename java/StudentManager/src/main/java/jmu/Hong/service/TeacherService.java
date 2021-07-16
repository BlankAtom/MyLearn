package jmu.Hong.service;

import jmu.Hong.pojo.CnoCourse;
import jmu.Hong.pojo.TeacherGradeInfo;
import jmu.Hong.pojo.TeacherInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface TeacherService {
    TeacherInfo getInfo(String tno);
    int changeTeaAddress(String tno, String pCode,  String address);
    int changeTeaBank(String tno, String bank,  String bankCard);
    List<CnoCourse> getOnlyCoursesBySno(String sno);
    int insertGrade( String sno,  String ctype,String etype, String couese,int percent, String grade);
    List<TeacherGradeInfo> getGradeInfo(String tno, String classname, String sno, String sname);
    List<Map> getClasses(String tno);
}
