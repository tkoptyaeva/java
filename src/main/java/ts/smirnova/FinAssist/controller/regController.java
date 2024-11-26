package ts.smirnova.FinAssist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ts.smirnova.FinAssist.domain.Role;
import ts.smirnova.FinAssist.domain.User;
import ts.smirnova.FinAssist.repos.UserRepo;

import java.util.Collections;
import java.util.Optional;

@Controller
public class regController {
    @Autowired
    private UserRepo userRepo;
    private PasswordEncoder passwordEncoder;

    @GetMapping("/reg")
    public String reg() {
        return "reg";
    }

    @PostMapping("/reg")
    public String reqUser(Model model, User user) {
        Optional<User> newFromDB = userRepo.findByUsername(user.getUsername());
        if (newFromDB != null) {
            model.addAttribute("message", "Пользователь уже есть");
            return "reg";
        }

        user.setActive(true); // меняем статус
        user.setRoles(Collections.singleton(Role.ADMIN)); // добавляем роль
        user.setPassword(passwordEncoder.encode(user.getPassword())); // шифруем пароль
        userRepo.save(user);
        return "redirect:/login";
    }
}
