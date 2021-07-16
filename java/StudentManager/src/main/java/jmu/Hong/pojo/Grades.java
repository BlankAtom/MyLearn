package jmu.Hong.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Grades {
    private String sno;
    private String term;
    private String cname;
    private String tname;
    private String gtype;
    private String percent;
    private String grade;
}
