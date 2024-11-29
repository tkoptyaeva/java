package ts.smirnova.FinAssist.domain;

import jakarta.persistence.*;

@Entity(name = "finances")
@Table(name = "finances")
public class Finance {
    // Класс описывает объект для хранения финансов пользователя
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id; // идентификатор, автогенерируемый

    private Integer user_id; // id пользователя, который создал эту запись
    private String fin_title; // название расходов/доходов
    private double fin_value; // значение расходов/доходов
    private String fin_date; // дата этого события

    public Finance() {
        // пустой класс для совместимости
    }

    public Finance(String fin_title, double fin_value, String fin_date) {
        this.fin_title = fin_title;
        this.fin_value = fin_value;
        this.fin_date = fin_date;
    }

// стандартные методы для получания и записи значений в объекте
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getFin_title() {
        return fin_title;
    }

    public void setFin_title(String fin_title) {
        this.fin_title = fin_title;
    }

    public double getFin_value() {
        return fin_value;
    }

    public void setFin_value(double fin_value) {
        this.fin_value = fin_value;
    }

    public String getFin_date() {
        return fin_date;
    }

    public void setFin_date(String fin_date) {
        this.fin_date = fin_date;
    }
}
