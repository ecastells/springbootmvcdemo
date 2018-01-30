package com.emi.springboot.springbootdemo.model;

import com.emi.springboot.springbootdemo.auditory.AuditListener;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by Emi on 11/12/2017.
 */
@Entity
@EntityListeners(AuditListener.class)
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotNull
    private String user;
    @NotNull
    private String desc;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date targetDate;
    private boolean done;

    @ManyToOne
    @JoinColumn(name = "country_id", foreignKey = @ForeignKey(name="countryid_todo_fkey"))
    private Country country;

    public Todo() {
    }

    public Todo(String user, String desc, Date targetDate, boolean done, Country country) {
        this.user = user;
        this.desc = desc;
        this.targetDate = targetDate;
        this.done = done;
        this.country = country;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Date getTargetDate() {
        return targetDate;
    }

    public void setTargetDate(Date targetDate) {
        this.targetDate = targetDate;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "Todo{" +
                "id=" + id +
                ", user='" + user + '\'' +
                ", desc='" + desc + '\'' +
                ", targetDate=" + targetDate +
                ", done=" + done +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Todo todo = (Todo) o;

        if (id != todo.id) return false;
        if (done != todo.done) return false;
        if (user != null ? !user.equals(todo.user) : todo.user != null) return false;
        if (desc != null ? !desc.equals(todo.desc) : todo.desc != null) return false;
        return targetDate != null ? targetDate.equals(todo.targetDate) : todo.targetDate == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (desc != null ? desc.hashCode() : 0);
        result = 31 * result + (targetDate != null ? targetDate.hashCode() : 0);
        result = 31 * result + (done ? 1 : 0);
        return result;
    }
}

