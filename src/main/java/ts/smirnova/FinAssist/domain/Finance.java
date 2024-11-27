package ts.smirnova.FinAssist.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "finances")
public class Finance {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private Integer user_id;
    private String fin_title;
    private double fin_value;
    private String fin_date;

    public Finance() {
    }

    public Finance(String fin_title, double fin_value, String fin_date) {
        this.fin_title = fin_title;
        this.fin_value = fin_value;
        this.fin_date = fin_date;
    }

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
