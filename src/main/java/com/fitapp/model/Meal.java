package com.fitapp.model;

import com.sun.istack.internal.Nullable;

import javax.persistence.*;
import java.util.List;

@Entity
public class Meal {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "meal_id")
    private Integer id;


    private String name;

    private int calTarget;

    private int calsFromProteins;
    private int calsFromCarbs;
    private int calsFromFats;

    @OneToMany(fetch = FetchType.EAGER,mappedBy="meal",cascade = CascadeType.ALL)
    private List<Food> food;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "diet_id"/*, nullable = false*/)
    private Diet diet;

    public Meal() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public List<Food> getFood() {
        return food;
    }

    public void setFood(List<Food> food) {
        this.food = food;
    }

    public Diet getDiet() {
        return diet;
    }

    public void setDiet(Diet diet) {
        this.diet = diet;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCalTarget() {
        return calTarget;
    }

    public void setCalTarget(int calTarget) {
        this.calTarget = calTarget;
    }

    public int getCalsFromProteins() {
        return calsFromProteins;
    }

    public void setCalsFromProteins(int calsFromProteins) {
        this.calsFromProteins = calsFromProteins;
    }

    public int getCalsFromCarbs() {
        return calsFromCarbs;
    }

    public void setCalsFromCarbs(int calsFromCarbs) {
        this.calsFromCarbs = calsFromCarbs;
    }

    public int getCalsFromFats() {
        return calsFromFats;
    }

    public void setCalsFromFats(int calsFromFats) {
        this.calsFromFats = calsFromFats;
    }
}
