package com.fitapp.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Diet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "diet_id")
    private Integer id;

    @Column(name = "diet_name")
    private String name;

    @OneToMany(fetch = FetchType.EAGER,mappedBy="diet",cascade = CascadeType.ALL)
    private Set<Meal> meals;

    @OneToMany(fetch = FetchType.EAGER,mappedBy="diet",cascade = CascadeType.PERSIST)
    private Set<AppUser> users;

    public Diet() {
        this.meals = new HashSet<Meal>();
        this.users = new HashSet<AppUser>();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Set<Meal> getMeals() {
        return meals;
    }

    public void setMeals(Set<Meal> meals) {
        this.meals = meals;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Set<AppUser> getUsers() {
        return users;
    }

    public void setUsers(Set<AppUser> users) {
        this.users = users;
    }
}
