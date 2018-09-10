package pl.coderslab.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {


    @ResponseBody
    @RequestMapping("/")
    public String home() {
        return "123 2222 123 -- home --";
    }

    @RequestMapping("/index")
    public String index() {
        return "index";
    }
}
