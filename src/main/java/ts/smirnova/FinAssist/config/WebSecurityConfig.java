package ts.smirnova.FinAssist.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import ts.smirnova.FinAssist.services.MyUserDetailsService;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class WebSecurityConfig {
    @Autowired
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // настраиваем доступ к страницам
        http
                .csrf(AbstractHttpConfigurer::disable)
//                .authorizeHttpRequests((requests) -> requests // пишем доступные всем страницы
//                        .requestMatchers("/", "/style.css", "/reg", "/index").permitAll()
////                        .requestMatchers(HttpMethod.POST, "/reg").permitAll()
//                        .anyRequest().authenticated()
//                )
//                Пришлось писать по другому, так как прошлый способ перестал работать
                .authorizeHttpRequests(auth -> auth.requestMatchers("/", "/style.css", "/reg").permitAll()
                        .requestMatchers("**").authenticated()
                )
                .formLogin((form) -> form // указываем страница авториазации
                        .loginPage("/login")
                        .defaultSuccessUrl("/")
                        .permitAll()
                )
                .logout((logout) -> logout.permitAll()); // разрешаем кнопку разлогина
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() { //(PasswordEncoder encoder) {
//        предоставляет сведения о пользователе в контексте безопасности
//        этот метод ищет пользователя по учетным данным, которые введены на странице авторизации
        return new MyUserDetailsService();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
//        шифрует введенный при авторизации пароль, чтобы его сравнить с тем что у пользователя,
//        так как у него в бд он зашифрован
        return new BCryptPasswordEncoder();
    }
    @Bean
    public AuthenticationProvider authenticationProvider() {
//        Этот метод задает как действовать придожению когда кто то авторизуется. он вызывается при авторизации
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService()); // чтобы найти пользователя использовать метод userDetailsService
        provider.setPasswordEncoder(passwordEncoder()); // чтобы зашифровать пароль использовать метод passwordEncoder
        return provider;
    }
}