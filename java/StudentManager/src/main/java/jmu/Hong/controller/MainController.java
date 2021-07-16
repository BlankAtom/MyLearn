package jmu.Hong.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*@Controller*/
@Controller
public class MainController {


    @RequestMapping("/main")
    public String main(){
        return "main";
    }

    @RequestMapping("a3")
    public String a3(){
        String msg = "hhh";
        System.out.println("a3:" + msg);
        return msg;
    }
}
