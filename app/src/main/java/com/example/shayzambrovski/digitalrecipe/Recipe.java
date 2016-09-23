package com.example.shayzambrovski.digitalrecipe;

/**
 * Created by Shay Zambrovski on 17/09/2016.
 */
public class Recipe {
    String name;
    String instructions;
    String ingredients;
    String userName;
    int rate;
    int amountOfRates;

    public Recipe(String name, String instructions, String ingredients, String userName, int rate, int amountOfRates) {
        this.name = name;
        this.instructions = instructions;
        this.ingredients = ingredients;
        this.userName = userName;
        this.rate = rate;
        this.amountOfRates = amountOfRates;
    }

    public Recipe() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public int getAmountOfRates() {
        return amountOfRates;
    }

    public void setAmountOfRates(int amountOfRates) {
        this.amountOfRates = amountOfRates;
    }
}
