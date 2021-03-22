package com.company;

import java.util.ArrayList;

public class Board {
    private ArrayList<Animal> Board = new ArrayList<>();

    //animal names list
    public Board(){
        ArrayList<String> animalnames = new ArrayList<>();

        animalnames.add("Skunk");
        animalnames.add("Mosquito");
        animalnames.add("Nondescript Egg");
        animalnames.add("Handbag Dog");
        animalnames.add("Hyena");
        animalnames.add("Mole");
        animalnames.add("Bear");
        animalnames.add("Mouse");
        animalnames.add("Panther");
        animalnames.add("Armadillo");
        animalnames.add("Bonobo");
        animalnames.add("Frog");
        animalnames.add("Tortoise");
        animalnames.add("Kitefin Shark");
        animalnames.add("Killer Whale");
        animalnames.add("Giraffe");
        animalnames.add("Bearded Dragon");
        animalnames.add("Salmon");
        animalnames.add("Monke");
        animalnames.add("Tardigrade");
        animalnames.add("Skink");
        animalnames.add("Cat");
        animalnames.add("Gaot");
        animalnames.add("Bilbo");

        for (int i = 0; i < 26; i++) {
            if (i == 0 || i == 13){
                Board.add(null);
            } else{
                Board.add(new Animal(animalnames.get(i - 1 - Utility.boolToInt(i > 13)), i * 50, i * 25, i));
            }
        }
    }

    //code to retrieve animal name list
    public ArrayList<Animal> getAnimals() {
        return Board;
    }

    //code to retrieve the position of the animal on the board
    public Animal getSpace(int space){
        return Board.get(space);
    }

    //code to get the upgraded value of the animal
    public void upgradeSpace(int space){
        Board.get(space).setLevel(getSpace(space).getLevel() + 1);
    }

    //this code is used to show how many players are in a certian part of the board
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

    //this code is used to print out the board for the players to see
    public void printBoard(ArrayList<Player> players){
        int padsize = 20;

        String middlePad = Utility.paddedString('│', ' ', "", 22);
        String[] deckArt = {"",
                Utility.paddedString(new char[]{'┌', '┐'}, new char[]{'─', '─'}, "", 22),
                middlePad,
                middlePad,
                Utility.paddedString('│', ' ', "Deck", 22),
                middlePad,
                middlePad,
                middlePad,
                Utility.paddedString(new char[]{'└', '┘'}, new char[]{'─', '─'}, "", 22),
                ""};

        //print top of board
        System.out.print(Utility.paddedString("Start", padsize));
        for (int i = 1; i < 8; i++) {
            System.out.print(Utility.paddedString(Integer.toString(i), padsize));
        }
        System.out.println();
        for (int i = 0; i < 8; i++) {
            System.out.print(Utility.paddedString(getplayersinspace(players, i), padsize));
        }
        System.out.println();

        //print middle of board
        for (int i = 0; i < 5; i++) {
            System.out.print(Utility.paddedString(Integer.toString(25 - i), padsize));
            System.out.print(Utility.paddedString(' ',' ', deckArt[i * 2], padsize * 6));
            System.out.print(Utility.paddedString(Integer.toString(i + 8), padsize));
            System.out.println();

            for (int x = 0; x < 2; x++) {
                System.out.print(Utility.paddedString(getplayersinspace(players, Math.abs(i + 33 * x - 25)), padsize));
                // Math.abs(i + 33 * x - 25) just gives (25 - i) then (i + 8) at x=0 and x=1 respectively
                if (x == 0){
                    System.out.print(Utility.paddedString(' ',' ', deckArt[i * 2 + 1], padsize * 6));
                }
            }
            System.out.println();
        }

        //print bottom of board
        for (int i = 0; i < 7; i++) {
            System.out.print(Utility.paddedString(Integer.toString(20 - i), padsize));
        }
        System.out.println(Utility.paddedString("Miss a turn", padsize));
        for (int i = 20; i > 12; i--) {
            System.out.print(Utility.paddedString(getplayersinspace(players, i), padsize));
        }
        System.out.println();
    }
}
