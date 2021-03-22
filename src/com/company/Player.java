package com.company;
import java.util.ArrayList;
import java.util.Random;

public class Player {
    private boolean missTurn = false, newTrade = false;
    private char playing_piece;
    private int space=0, money = 1500, spacesmoved = 0;
    private Random random = new Random();
    private String name;
    private ArrayList<Trade> trades = new ArrayList<>();

    public Player(char playing_piece, String name){
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

    public boolean getNewTrade() {
        return newTrade;
    }

    public void setNewTrade(boolean newTrade) {
        this.newTrade = newTrade;
    }

    public void printMoney(){
        System.out.println(name + " has £" + money + "!");
    }

    public void printData(Board board){
        ArrayList<Animal> playeranimals;
        System.out.println(name + "\n" + Utility.repeatedChar('-', Math.max(5, name.length())));
        playeranimals = Utility.getPlayerAnimals(this, board.getAnimals());
        for (Animal animal : playeranimals){
            System.out.println(animal.getName() +
                    "\n└──→Level: " + animal.getLevel() +
                    "\n└──→Cost: " + animal.getCost());
        }

        System.out.println("£" + money + "\n");
    }
}
