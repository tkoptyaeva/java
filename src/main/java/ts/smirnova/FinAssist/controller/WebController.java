package ts.smirnova.FinAssist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
    public String reg(Model model) {
        // обработка запроса страницы авторизации
        model.addAttribute("showForm", "true");
        return "reg";
    }

    @PostMapping("/reg")
    public String newUser(MyUser user, Model model) {
        // обработка запроса регистрации пользователя, срабатывает при отправке формы
        Optional<MyUser> newFromDB = userRepo.findByUsername(user.getUsername());
        if (newFromDB.isPresent()) {
            // если пользователь уже есть, то пишеь сообщение на странице
            model.addAttribute("message", "Пользователь уже есть");
            return "reg";
        }
        // Если пользователь новый, то он будет сохранен в бд
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        user.setActive(true); // меняем статус
        user.setRoles("ROLE_USER"); // добавляем роль
        user.setPassword(passwordEncoder.encode(user.getPassword())); // шифруем пароль
        userRepo.save(user);
        model.addAttribute("message", "Вы успешно зарегистрировались. Войдите под своим пользователем");
        model.addAttribute("isNew", "true");
        return "reg";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')") // аннотация указывает, что страница разрешена только админам
    public String admin(Model model) {
        // обработка запроса админки, выподит список всех пользователей
        Iterable<MyUser> users = userRepo.findAll();
        model.addAttribute("users", users);
        return "admin";
    }

}
