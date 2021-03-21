package com.company;

public class Animal {
    private String name;
    private int level;
    private Player owner;
    private int cost;
    private int rent;
    private int position;

    public Animal(String name, int animal_Cost, int rent, int position) {
        this.name = name;
        level = 0;
        owner = null;
        cost = animal_Cost;
        this.rent = rent;
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }

    public Player getOwner() {
        return owner;
    }

    public int getCost() {
        return cost;
    }

    public int getUpgradeCost(){
        return cost / 3;
    }

    public int getRent() {
        return rent;
    }

    public int getLandCost() {
        return rent * (level + 1);
    }

    public int getPosition() {
        return position;
    }

    // pretty gap!

    public void setLevel(int level) {
        this.level = level;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }
}
