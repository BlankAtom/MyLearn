package jmu.Hong.service;

import jmu.Hong.mapper.LoginMapper;
import jmu.Hong.pojo.User;

public class LoginServiceImpl implements LoginService{
    private LoginMapper loginMapper;

    public void setLoginMapper(LoginMapper loginMapper){
        this.loginMapper = loginMapper;
    }


    @Override
    public User userLogin(String uid, String pwd) {
        return this.loginMapper.userLogin(uid, pwd);
    }
}
