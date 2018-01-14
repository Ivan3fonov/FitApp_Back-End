package com.fitapp.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Diet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "diet_id")
    private Integer id;

    private String name;

    @OneToMany(fetch = FetchType.EAGER,mappedBy="diet",cascade = CascadeType.ALL)
    private List<Meal> meals;

    @OneToMany(fetch = FetchType.EAGER,mappedBy="diet",cascade = CascadeType.ALL)
    private List<AppUser> users;

    Diet() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Meal> getMeals() {
        return meals;
    }

    public void setMeals(List<Meal> meals) {
        this.meals = meals;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public List<AppUser> getUsers() {
        return users;
    }

    public void setUsers(List<AppUser> users) {
        this.users = users;
    }
}
