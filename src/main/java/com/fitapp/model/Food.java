package com.fitapp.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
public class Food {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "food_id")
    private Integer id;

    private String name;
    private int proteins;
    private int fats;
    private int carbs;
    private double calsPerUnit;
    private int unit;

    private int amount;

    private String predominatMacros;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "meal_id", nullable = false)
    private Meal meal;

    public Food() {

    }


    public Food(String name , double calsPerUnit, String predominatMacros) {
        this.name = name;
        this.calsPerUnit = calsPerUnit;
        this.predominatMacros = predominatMacros;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getProteins() {
        return proteins;
    }

    public void setProteins(int proteins) {
        this.proteins = proteins;
    }

    public int getFats() {
        return fats;
    }

    public void setFats(int fats) {
        this.fats = fats;
    }

    public int getCarbs() {
        return carbs;
    }

    public void setCarbs(int carbs) {
        this.carbs = carbs;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Meal getMeal() {
        return meal;
    }

    public void setMeal(Meal meal) {
        this.meal = meal;
    }

    public double getCalsPerUnit() {
        return calsPerUnit;
    }

    public void setCalsPerUnit(double calsPerUnit) {
        this.calsPerUnit = calsPerUnit;
    }

    public int getUnit() {
        return unit;
    }

    public void setUnit(int unit) {
        this.unit = unit;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getPredominatMacros() {
        return predominatMacros;
    }

    public void setPredominatMacros(String predominatMacros) {
        this.predominatMacros = predominatMacros;
    }
}
