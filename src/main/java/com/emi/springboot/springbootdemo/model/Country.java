package com.emi.springboot.springbootdemo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by Emi on 21/01/2018.
 */
@Entity
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(example ="1",position = 0,notes = "Required to PUT but not in a POST")
    private Long id;

    @NotNull @NotBlank
    @ApiModelProperty(required = true, example = "AR", position = 1)
    private String acronym;

    @NotNull @NotBlank
    @ApiModelProperty(required = true, example = "Argentina", position = 2)
    private String name;

    public Country() {
    }

    public Country(String acronym, String name) {
        this.acronym = acronym;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAcronym() {
        return acronym;
    }

    public void setAcronym(String acronym) {
        this.acronym = acronym;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Country{" +
                "id=" + id +
                ", acronym='" + acronym + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Country country = (Country) o;

        if (id != null ? !id.equals(country.id) : country.id != null)
            return false;
        if (acronym != null ? !acronym.equals(country.acronym) : country.acronym != null)
            return false;
        return name != null ? name.equals(country.name) : country.name == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (acronym != null ? acronym.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
