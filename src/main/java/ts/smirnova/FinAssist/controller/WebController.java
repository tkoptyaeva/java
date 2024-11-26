package ts.smirnova.FinAssist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ts.smirnova.FinAssist.domain.User;
import ts.smirnova.FinAssist.repos.UserRepo;


@Controller
public class WebController {
    @Autowired
    private UserRepo userRepo;

    @GetMapping("/auth")
    public String auth() {
        return "auth";
    }

    @GetMapping("/reg")
    public String reg() {
        return "reg";
    }

    @GetMapping("/admin")
    public String admin(Model model) {
        Iterable<User> users = userRepo.findAll();
        model.addAttribute("users", users);
        return "admin";
    }

    @PostMapping("/reg")
    private String regUser(@RequestParam String email, @RequestParam String login, @RequestParam String password) {
        User newUser = new User(email, login, password);
        userRepo.save(newUser);
        return "index";
    }
}
