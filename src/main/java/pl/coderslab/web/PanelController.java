package pl.coderslab.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import pl.coderslab.model.User;
import pl.coderslab.service.UserService;

@Controller
public class PanelController {

    private final UserService userService;

    public PanelController(UserService userService) {
        this.userService = userService;
    }
    
    @RequestMapping("/login")
    public String login() {
        return "panel/login";
    }

    @RequestMapping("/logoutpage")
    public String logoutpage() {
        return "panel/logoutpage";
    }
    
    @RequestMapping({"/panel", "/"})
    public String panel() {
        return "panel/index";
    }

    @GetMapping("/create-user")
    @ResponseBody
    public String createUser() {
        User user = new User();
        user.setUsername("admin");
        user.setEmail("admin@admin.pl");
        user.setPassword("admin");
        userService.saveUser(user);
        return "created id = " + user.getId();
    }
}
