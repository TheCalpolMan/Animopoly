package com.company;

public class Animal {
    String Animal_Name;
    int Animal_Level;
    Player Owner;
    int Animal_Cost;
    int Rent;
    int Position;

    public Animal(String animal_Name, int animal_Cost, int rent, int position) {
        Animal_Name = animal_Name;
        Animal_Level = 0;
        Owner = null;
        Animal_Cost = animal_Cost;
        Rent = rent;
        Position = position;
    }
    public String getAnimal_Name() {
        return Animal_Name;
    }
    public int getAnimal_Level() {
        return Animal_Level;
    }
    public Player getOwner() {
        return Owner;
    }
    public int getAnimal_Cost() {
        return Animal_Cost;
    }
    public int getRent() {
        return Rent;
    }
    public int getPosition() {
        return Position;
    }

    public void setAnimal_Name(String animal_Name) {
        Animal_Name = animal_Name;
    }
    public void setAnimal_Level(int animal_Level) {
        Animal_Level = animal_Level;
    }
    public void setOwner(Player owner) {
        Owner = owner;
    }
    public void setAnimal_Cost(int animal_Cost) {
        Animal_Cost = animal_Cost;
    }
    public void setRent(int rent) {
        Rent = rent;
    }
    public void setPosition(int position) {
        Position = position;
    }
}
