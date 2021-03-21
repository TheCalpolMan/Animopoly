package com.company;
import java.util.ArrayList;
import java.util.Random;

public class Player {
    private boolean missTurn;
    private char playing_piece;
    private int space=0, money = 1500, spacesmoved = 0;
    private Random random = new Random();
    private String name;
    private ArrayList<Trade> trades;

    public Player(char playing_piece, String name){
        missTurn = false;
        setSpace(space);
        this.playing_piece=playing_piece;
        this.name=name;
    }

    public ArrayList<Trade> getTrades() {
        return trades;
    }

    public void addTrade(Trade trade){
        trades.add(trade);
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

    public int getMoney(){
        return money;
    }

    public void setSpace(int space)
    {
        this.space=space;
    }

    public int getSpacesmoved() {
        return spacesmoved;
    }

    public boolean roll()
    {
        boolean double_dice=false;
        int dice1=(random.nextInt(6)+1);
        int dice2=(random.nextInt(6)+1);
        spacesmoved = dice1+dice2;
        space=(spacesmoved+space) % 26;
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

    public void setSpacesmoved(int spacesmoved) {
        this.spacesmoved = spacesmoved;
    }

    public void printMoney(){
        System.out.println(name + " has £" + money + "!");
    }
}
