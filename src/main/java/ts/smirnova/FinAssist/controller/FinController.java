package ts.smirnova.FinAssist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ts.smirnova.FinAssist.domain.Finance;
import ts.smirnova.FinAssist.domain.MyUser;
import ts.smirnova.FinAssist.repos.FinRepo;
import ts.smirnova.FinAssist.repos.UserRepo;

import java.util.Optional;


@Controller
// класс для обработки запросов с главной страницы
public class FinController {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private FinRepo finRepo;

    private MyUser authenticatedUser() {
        // метод получает пользователя, который открыл эту страницу, если он авторизован
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
    public String main(Model model) {
        // метод обрабатывает запрос основной страницы
        // собирает шаблон в зависимости от того кто открыл страницу
        MyUser nowUser = authenticatedUser();
        if (nowUser != null) { // если пользователя нет, значит страницу открыл кто то не авторизованный
            model.addAttribute("username", nowUser.getUsername());
            Iterable<Finance> all_fins = finRepo.findByUserId(nowUser.getId());
            model.addAttribute("all_fins", all_fins);
            double fin_total = 0;
            double fin_outcome = 0;
            double fin_income = 0;
            for(Finance item : all_fins)
            {
                double fin_value = item.getFin_value();
                fin_total += fin_value;
                if (fin_value > 0) fin_income += fin_value;
                if (fin_value < 0) fin_outcome += fin_value;
            }
            model.addAttribute("fin_total", fin_total);
            model.addAttribute("fin_income", fin_income);
            model.addAttribute("fin_outcome", fin_outcome);
        }
        return "index";
    }

    @PostMapping("/")
    public String newFinance(Model model,
                             @RequestParam(value="fin_title") String fin_title,
                             @RequestParam(value="fin_value") String fin_value,
                             @RequestParam(value="fin_date") String fin_date) {
        // обработка формы сохранения расходов и доходов
        // считывает параметры из формы
        MyUser nowUser = authenticatedUser();
        if (nowUser != null) {
            Finance newfin = new Finance(fin_title, Double.parseDouble(fin_value), fin_date);
            newfin.setUser_id(nowUser.getId());
            finRepo.save(newfin);
        }
        return main(model);
    }

}
