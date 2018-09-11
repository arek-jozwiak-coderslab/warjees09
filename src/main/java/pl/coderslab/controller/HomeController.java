package pl.coderslab.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.hero.HeroRepository;
import pl.coderslab.model.User;
import pl.coderslab.service.UserService;

@Controller
public class HomeController {

    private final HeroRepository heroRepository;
    private final UserService userService;

    public HomeController(HeroRepository heroRepository, UserService userService) {
        this.heroRepository = heroRepository;
        this.userService = userService;
    }

    @ResponseBody
    @RequestMapping("/")
    public String home() {
        return "123 2222 123 -- home --";
    }

    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    @GetMapping("/admin")
    @ResponseBody
    public String admin() {
        return "admin";
    }

    @GetMapping("/create-user")
    @ResponseBody
    public String createUser() {
        User user = new User();
        user.setUsername("admin");
        user.setPassword("admin");
        userService.saveUser(user);
        return "-created-";
    }

}
