package jmu.Hong.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import jmu.Hong.pojo.User;
import jmu.Hong.service.LoginService;
import jmu.Hong.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/api")
public class LoginController {

    @Autowired
    @Qualifier("LoginServiceImpl")
    private LoginService loginService;

    @RequestMapping("/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String pwd)
            throws JsonProcessingException {
        User user = loginService.userLogin(username, pwd);
        return JsonUtil.getJson(user);
    }

    @RequestMapping("/check")
    public String login(HttpSession session, String uid, String password, Model model){
        User user = loginService.userLogin(uid, password);

        if(user == null)
            return "login_failure";

        model.addAttribute("username", user.getUid());
        session.setAttribute("username", user.getUid());
        session.setAttribute("userpower", user.getUpower());

        return "redirect:/main";
    }

    @RequestMapping("/logout")
    public String logOut(HttpSession session){

        session.removeAttribute("username");
        session.removeAttribute("userpower");
        return "redirect:/main";
    }

    @ResponseBody
    @RequestMapping("/look")
    public String look(String password){
        String msg = "1";
        if (password != null) {
            if ("123".equals(password)) {
                msg = "ok";
            }
            else{
                msg = "error";
            }
        }
        System.out.println("msg:" + msg);
        return msg;
    }

}
