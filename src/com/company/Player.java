package com.company;
import java.util.Random;

public class Player {
    private boolean missTurn;
    private int money=0;
    private char playing_piece;
    private int space=0;
    private Random random = new Random();
    private int dice_roll=0;
    private String name;

    public Player(char playing_piece, String name){
        missTurn = false;
        dice_roll=getSpace();
        setSpace(space);
        this.playing_piece=playing_piece;
        this.name=name;

    }

    public String getName() {
        return name;
    }

    public char getPiece(){
        return playing_piece;
    }

    public int getSpace(){
       return space;
    }

    public void setSpace(int space)
    {
        this.space=space;
    }

    public boolean roll()
    {
        boolean double_dice=false;
        int dice1=(random.nextInt(6)+1);
        int dice2=(random.nextInt(6)+1);
        space=dice1+dice2+space;
        if (dice1==dice2)
        {
            double_dice=true;
        }
        return(double_dice);
    }
    public void deltaMoney(int money)
    {
        this.money+=money;
    }

    public boolean getMissTurn() {
        return missTurn;
    }

    public void setMissTurn(boolean missTurn) {
        this.missTurn = missTurn;
    }
}
