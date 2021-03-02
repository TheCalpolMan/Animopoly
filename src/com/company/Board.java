package com.company;

import java.util.ArrayList;

public class Board {
    private ArrayList<Animal> Board = new ArrayList<Animal>();

    public Board(){
        for (int i = 0; i < 26; i++) {
            Board.add(new Animal());
        }
    }

    public Animal getSpace(int space){
        return Board.get(space);
    }


    public void upgradeSpace(int space){
        getSpace(space).setLevel(getSpace(space).getLevel() + 1);
    }
}
