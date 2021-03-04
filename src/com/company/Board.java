package com.company;

import java.util.ArrayList;

public class Board {
    private ArrayList<Animal> Board = new ArrayList<>();

    public Board(){
        for (int i = 0; i < 26; i++) {
            Board.add(new Animal());
        }
    }

    public Animal getSpace(int space){
        return Board.get(space);
    }


    public void upgradeSpace(int space){
        Board.get(space).setLevel(getSpace(space).getLevel() + 1);
    }


    private String paddedWord(char outerpadding, char innerpadding, String topad, int size){
        while (topad.length() <= size - 4){
            topad = innerpadding + topad + innerpadding;
        }

        if (topad.length() < size - 2){
            topad = topad + innerpadding;
        }

        topad = outerpadding + topad + outerpadding;
        return topad;
    }

    private String paddedWord(char[] outerpadding, char[] innerpadding, String topad, int size){
        while (topad.length() <= size - 4){
            topad = innerpadding[0] + topad + innerpadding[1];
        }

        if (topad.length() < size - 2){
            topad = topad + innerpadding[1];
        }

        topad = outerpadding[0] + topad + outerpadding[1];
        return topad;
    }

    private String paddedWord(String topad, int size){
        return paddedWord('|', ' ', topad, size);
    }

    private String getplayersinspace(ArrayList<Player> players, int space) {
        String playersinspace = "";

        for (Player player:players) {
            if (player.getSpace() == space){
                playersinspace = playersinspace + player.getPiece() + " ";
            }
        }

        if (playersinspace.length() > 0){
            playersinspace = playersinspace.substring(0, playersinspace.length() - 1);
        }
        return playersinspace;
    }
    
    public void printBoard(ArrayList<Player> players){
        int padsize = 20;

        String middlePad = paddedWord('│', ' ', "", 22);
        String[] deckArt = {"",
                paddedWord(new char[]{'┌', '┐'}, new char[]{'─', '─'}, "", 22),
                middlePad,
                middlePad,
                paddedWord('│', ' ', "Deck", 22),
                middlePad,
                middlePad,
                middlePad,
                paddedWord(new char[]{'└', '┘'}, new char[]{'─', '─'}, "", 22),
                ""};

        //top
        System.out.print(paddedWord("Start", padsize));
        for (int i = 1; i < 8; i++) {
            System.out.print(paddedWord(Integer.toString(i), padsize));
        }
        System.out.println();
        for (int i = 0; i < 8; i++) {
            System.out.print(paddedWord(getplayersinspace(players, i), padsize));
        }
        System.out.println();

        //middle
        for (int i = 0; i < 5; i++) {
            System.out.print(paddedWord(Integer.toString(25 - i), padsize));
            System.out.print(paddedWord(' ',' ', deckArt[i * 2], padsize * 6));
            System.out.print(paddedWord(Integer.toString(i + 8), padsize));
            System.out.println();

            for (int x = 0; x < 2; x++) {
                System.out.print(paddedWord(getplayersinspace(players, Math.abs(i + 32 * x - 25)), padsize));
                // Math.abs(i + 32 * x - 25) just gives (25 - i) then (i + 8) at x=0 and x=1 respectively
                if (x == 0){
                    System.out.print(paddedWord(' ',' ', deckArt[i * 2 + 1], padsize * 6));
                }
            }
            System.out.println();
        }

        //bottom
        for (int i = 0; i < 7; i++) {
            System.out.print(paddedWord(Integer.toString(20 - i), padsize));
        }
        System.out.println(paddedWord("Miss a turn", padsize));
        for (int i = 20; i > 12; i--) {
            System.out.print(paddedWord(getplayersinspace(players, i), padsize));
        }
    }
}
