package jmu.Hong.mapper;

import jmu.Hong.pojo.StudentInfo;
import jmu.Hong.pojo.User;
import org.apache.ibatis.annotations.Param;

public interface LoginMapper {
    User userLogin(@Param("uid") String uid, @Param("password") String pwd);

}
