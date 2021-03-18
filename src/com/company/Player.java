package com.company;
import java.util.Random;

public class Player {
    private boolean missTurn;
    private int money=0;
    private String playing_piece="a";
    private int space=0;
    private Random random = new Random();
    private int dice_roll=0;

    public Player(){
        missTurn = false;
        dice_roll=getSpace();
        setSpace(space);

    }

    public char getPiece(){
        return '*';
    }

    public int getSpace(){
        boolean double_dice;
        int dice1=(random.nextInt(6)+1);
        int dice2=(random.nextInt(6)+1);
        this.space=(dice1+dice2+space);
        if (dice1==dice2)
        {
            double_dice=true;
        }
        return(this.space);
    }

    public void setSpace(int space)
    {

    }

    public void deltaMoney(int money)
    {
        this.money=money;
    }

    public boolean getMissTurn() {
        return missTurn;
    }

    public void setMissTurn(boolean missTurn) {
        this.missTurn = missTurn;
    }
}
