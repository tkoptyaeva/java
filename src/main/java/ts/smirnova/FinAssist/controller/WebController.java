package ts.smirnova.FinAssist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ts.smirnova.FinAssist.domain.User;
import ts.smirnova.FinAssist.repos.UserRepo;


@Controller
public class WebController {
    @Autowired
    private UserRepo userRepo;

    @GetMapping("/main")
    public String main(Model model) {
        return "main";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String admin(Model model) {
        Iterable<User> users = userRepo.findAll();
        model.addAttribute("users", users);
        return "admin";
    }

}
