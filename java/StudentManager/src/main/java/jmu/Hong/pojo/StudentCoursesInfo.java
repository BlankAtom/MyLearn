package jmu.Hong.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentCoursesInfo {
    private String sno;
    private String sname;
    private String cname;
    private String term;
    private String hours;
    private String tname;
}
