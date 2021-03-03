package com.company;

public class Player {
    private boolean missTurn;

    public Player(){
        missTurn = false;
    }

    public char getPiece(){
        return '*';
    }

    public int getSpace(){
        return 0;
    }

    public void setSpace(int space){

    }

    public void deltaMoney(int money){

    }

    public boolean getMissTurn() {
        return missTurn;
    }

    public void setMissTurn(boolean missTurn) {
        this.missTurn = missTurn;
    }
}
