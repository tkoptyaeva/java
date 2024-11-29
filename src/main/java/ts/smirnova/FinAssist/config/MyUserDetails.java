package ts.smirnova.FinAssist.config;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ts.smirnova.FinAssist.domain.MyUser;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

public class MyUserDetails implements UserDetails {
// самописный класс MyUserDetails. Он нужен чтобы работала авторизация пользователей,
// которые добавлены в базу данных. В нем определяется как получить логин, пароль и роли пользователя
    private MyUser user;

    public MyUserDetails() {
        // пустой конструктор класса для совместимости
    }

    public MyUserDetails(MyUser user) {

        this.user = user;
    }

    @Override
    // метод получает роли пользователя
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.stream(user.getRoles().split(", "))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    // метод получает пароль пользователя
    public String getPassword() {
        return this.user.getPassword();
    }

    @Override
    // метод получает логин пользователя
    public String getUsername() {
        return this.user.getUsername();
    }

    @Override
    // остальные методы класса
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
