package jmu.Hong.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Course {
    private int cou_id;
    private String cno;
    private String cname;
    private String tno;
    private int class_hours;
    private int credit;
    private int check_id;
}
