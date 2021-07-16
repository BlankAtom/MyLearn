package jmu.Hong.service;

import jmu.Hong.mapper.TeacherMapper;
import jmu.Hong.pojo.CnoCourse;
import jmu.Hong.pojo.TeacherGradeInfo;
import jmu.Hong.pojo.TeacherInfo;

import java.util.List;
import java.util.Map;

public class TeacherServiceImpl  implements TeacherService{
    private TeacherMapper teacherMapper;
    public void setTeacherMapper(TeacherMapper t){this.teacherMapper= t;}
    @Override
    public TeacherInfo getInfo(String tno) {
        return teacherMapper.getInfo(tno);
    }

    @Override
    public int changeTeaAddress(String tno, String pCode, String address) {
        return teacherMapper.changeTeaAddress(tno, pCode, address);
    }

    @Override
    public int changeTeaBank(String tno, String bank, String bankCard) {
        return teacherMapper.changeTeaBank(tno, bank, bankCard);
    }

    @Override
    public List<CnoCourse> getOnlyCoursesBySno(String sno) {
        return teacherMapper.getOnlyCoursesBySno(sno);
    }

    @Override
    public int insertGrade(String sno, String ctype, String etype, String couese, int percent, String grade) {
        return teacherMapper.insertGrade(sno, ctype, etype, couese, percent, grade);
    }

    @Override
    public List<TeacherGradeInfo> getGradeInfo(String tno, String classname, String sno, String sname) {
        return teacherMapper.getGradeInfo(tno, classname, sno, sname);
    }

    @Override
    public List<Map> getClasses(String tno) {
        return teacherMapper.getClasses(tno);
    }
}
