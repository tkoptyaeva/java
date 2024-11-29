package ts.smirnova.FinAssist.domain;

import jakarta.persistence.*;

import java.util.Set;


@Entity
@Table(name = "users")
public class MyUser {
    // Класс описывает объект Пользователь
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id; // ид пользователя
    @Column(unique = true)
    private String username; // его логи (аннотация показывает что это значение должно быть уникальным в таблице
    @Column(unique = true)
    private String email; // почта
    private String password; // пароль
    private String roles; // роли пользователя
    private boolean active; // статус активности

    public MyUser() {
        // пустой конструктор для совместимости
    }

    public MyUser(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
