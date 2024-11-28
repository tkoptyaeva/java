package ts.smirnova.FinAssist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;
import ts.smirnova.FinAssist.domain.MyUser;
import ts.smirnova.FinAssist.repos.FinRepo;
import ts.smirnova.FinAssist.repos.UserRepo;

import java.util.Optional;

public class FinController {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private FinRepo finRepo;

    private MyUser autenticatedUser() { // Получает текущего авторизованного пользователя
        String currentUserName = "";
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            currentUserName = authentication.getName();
        }
        if (currentUserName == "") return null;
        Optional<MyUser> now_user = userRepo.findByUsername(currentUserName);
        return now_user.orElse(null);
    }

    @GetMapping("/")
    public String main(Model model) { // обработка запроса основной страницы
        MyUser nowUser = autenticatedUser();
        if (nowUser != null){
//        Iterable<Finance> all_fins = finRepo.findAll();
            model.addAttribute("username", nowUser.getUsername());
        }
        return "index";
    }

    @PostMapping("/addFin")
    public String newFinance() { // обработка формы сохранения расходов
//        Iterable<Finance> all_fins = finRepo.findAll();
//        model.addAttribute("fins", all_fins);
        return "redirect: /";
    }

}
