package jmu.Hong.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeacherGradeInfo {
    private String classname;
    private String sno;
    private String sname;
    private String cname;
    private String etype;
    private String percent;
    private String grade;
}
