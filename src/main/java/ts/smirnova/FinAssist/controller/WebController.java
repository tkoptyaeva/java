package ts.smirnova.FinAssist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ts.smirnova.FinAssist.domain.Finance;
import ts.smirnova.FinAssist.domain.MyUser;
import ts.smirnova.FinAssist.repos.FinRepo;
import ts.smirnova.FinAssist.repos.UserRepo;

import java.util.Optional;


@Controller
public class WebController {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private FinRepo finRepo;

    @GetMapping("/reg")
    public String reg() { // обработка запроса страницы авторизации
        return "reg";
    }

    @PostMapping("/reg")
    public String newUser(MyUser user, Model model) { // обработка запроса регистрации пользователя
        Optional<MyUser> newFromDB = userRepo.findByUsername(user.getUsername());
        if (newFromDB.isPresent()) {
            model.addAttribute("message", "Пользователь уже есть");
            return "reg";
        }
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        user.setActive(true); // меняем статус
        user.setRoles("ROLE_USER"); // добавляем роль
        user.setPassword(passwordEncoder.encode(user.getPassword())); // шифруем пароль
        userRepo.save(user);
        return "redirect:/login,";
//        return "reg";
    }

    @GetMapping("/")
    public String main(Model model) { // обработка запроса основной страницы
//        Iterable<Finance> all_fins = finRepo.findAll();
//        model.addAttribute("all_fins", all_fins);
        return "index";
    }

    @PostMapping("/")
    public String newFinance(Model model) { // обработка формы сохранения расходов
//        Iterable<Finance> all_fins = finRepo.findAll();
//        model.addAttribute("fins", all_fins);
        return "index";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')") // страница разрешена только админам
    public String admin(Model model) { // обработка запроса админки
        Iterable<MyUser> users = userRepo.findAll();
        model.addAttribute("users", users);
        return "admin";
    }
}
