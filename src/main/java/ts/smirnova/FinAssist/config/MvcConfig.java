package ts.smirnova.FinAssist.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// Настраивается связь ссылки в браузере с шаблоном страницы.
// Это сделано для того чтобы приложение знало какой шаблон использовать чтобы открыть страницу авторизации.
@Configuration //указывает что тут конфигурационный класс
public class MvcConfig implements WebMvcConfigurer {

    public void addViewControllers(ViewControllerRegistry registry) {
        // задаем шаблон для страницы авторизации
        registry.addViewController("/login").setViewName("login");

    }

}