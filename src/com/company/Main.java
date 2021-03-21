package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        ArrayList<Player> players = new ArrayList<>();
        ArrayList<Animal> playeranimals;
        ArrayList<Action> cardactions;
        Board board = new Board();
        Cards deck = new Cards();
        int readinint = 0, currentplayerindex = 0;
        Player currentplayer;
        String readinstring;
        char readinchar;
        boolean boardprinted = false, doubledice, isdead;
        Card card;

        System.out.println("Welcome to Animopoly! Take a shot!");
        readinint = 1;
        while (readinint != 0 || players.size() < 2){
            System.out.println("Enter player " + (players.size() + 1) + "'s name!");
            readinstring = reader.nextLine();
            System.out.println("Enter player " + (players.size() + 1) + "'s piece!");
            readinchar = reader.next().charAt(0);
            System.out.println("If you want to add another player, enter an integer other than 0");
            readinint = reader.nextInt();
            reader.nextLine();

            players.add(new Player(readinchar, readinstring));
        }

        while (players.size() > 1){
            currentplayerindex = currentplayerindex % players.size();

            currentplayer = players.get(currentplayerindex);

            if (! boardprinted){
                board.printBoard(players);
                System.out.println(currentplayer.getName() + "'s go!\n");
                boardprinted = true;
            }

            currentplayer.printMoney();

            if (currentplayer.getMissTurn()){
                System.out.println("This player misses a go!");
                currentplayer.setMissTurn(false);
                currentplayerindex++;
                continue;
            }

            readinint = 0;
            while (! (0 < readinint && readinint < 5)){
                System.out.println("Enter an integer between 1 and 4 (inclusive) \n" +
                        "1. Upgrade animals\n" +
                        "2. Send trade\n" +
                        "3. Check trades\n" +
                        "4. Roll");
                readinint = reader.nextInt();
            }

            switch (readinint){
                case 1: // animal upgrade
                    readinint = 1;

                    while (readinint != 0){
                        playeranimals = getPlayerAnimals(currentplayer, board.getAnimals());

                        if (playeranimals.size() == 0){
                            System.out.println("You have no animals, and as such cannot upgrade them!");
                            break;
                        }

                        for (Animal animal : playeranimals){
                            System.out.println(animal.getName() + "\n" + Utility.paddedString('-', '-', "", animal.getName().length()) +
                                    "\nLevel: " + animal.getLevel() +
                                    "\nUpgrade cost: " + animal.getUpgradeCost() +
                                    "\nCurrent rent: " + animal.getLandCost() +
                                    "\nUpgraded rent: " + animal.getRent() * (animal.getLevel() + 2) + "\n");
                        }

                        System.out.println("If you'd still like to upgrade an animal, enter one of your animals' names. If not, enter literally anything else");
                        reader.nextLine();
                        readinstring = reader.nextLine();

                        for (Animal animal : playeranimals){
                            if (animal.getName().equals(readinstring)){
                                if (currentplayer.getMoney() > animal.getUpgradeCost() && animal.getLevel() < 3){
                                    animal.setLevel(animal.getLevel() + 1);
                                    System.out.println("You upgraded your " + animal.getName() + " to level " + animal.getLevel() + "!");
                                    currentplayer.deltaMoney(-animal.getUpgradeCost());
                                } else if (animal.getLevel() > 2){
                                    System.out.println(animal.getName() + " was already max level! (3)");
                                } else{
                                    System.out.println("You were too poor to upgrade " + animal.getName() + "! Lol!");
                                }

                                break;
                            }
                        }

                        currentplayer.printMoney();
                        System.out.println("If you want to upgrade another animal, enter an integer that isn't 0. If you don't, enter 0.");
                        readinint = reader.nextInt();
                    }

                    break;
                case 2: // send trade

                    break;
                case 3: // check trades

                    break;
                case 4: // roll
                    doubledice = currentplayer.roll();

                    board.printBoard(players);

                    System.out.println("You moved " + currentplayer.getSpacesmoved() + " spaces!");

                    if (currentplayer.getSpace() >= 0 && currentplayer.getSpace() - currentplayer.getSpacesmoved() < 0){
                        System.out.println("You got £500 for making it round the board!");
                        currentplayer.deltaMoney(500);
                    }

                    isdead = afterMove(board, currentplayer, players, reader);
                    if (isdead){
                        boardprinted = false;
                        break;
                    }

                    if (doubledice){
                        System.out.println("You rolled a double!");

                        card = deck.randomCard();
                        card.printCard();
                        card.execute(currentplayer, board.getAnimals());

                        if (currentplayer.getMoney() <= 0){
                            System.out.println("It's the end of the line for you, buckaroo. Sayonara. (You were charged more money that you have)");
                            killPlayer(currentplayer, board, players);
                            break;
                        }

                        cardactions = card.getActions();

                        for (Action action : cardactions){
                            if (action.getClass().getSimpleName().equals("ClockwiseMoveAction")){
                                isdead = afterMove(board, currentplayer, players, reader);

                                break;
                            }
                        }
                    }

                    if (isdead){
                        boardprinted = false;
                        break;
                    }

                    boardprinted = false;
                    currentplayerindex++;
                    break;
                default:
                    System.out.println("You broke the game lmao");
                    break;
            }
        }
    }

    private static boolean afterMove(Board board, Player currentplayer, ArrayList<Player> players, Scanner reader) {
        int readinint;
        Animal animal = board.getSpace(currentplayer.getSpace());

        if (currentplayer.getSpace() == 0){
            System.out.println("You got £500 for landing on start!");
            currentplayer.deltaMoney(500);
        } else if (currentplayer.getSpace() == 13){
            System.out.println("Miss a turn!");
            currentplayer.setMissTurn(true);
        } else if (board.getSpace(currentplayer.getSpace()).getOwner() == null){
            if (board.getSpace(currentplayer.getSpace()).getCost() <= currentplayer.getMoney()){
                System.out.println("\n" + animal.getName() + "\n" + Utility.repeatedChar('-', animal.getName().length()) +
                        "\nCost: " + animal.getCost() + "\nInitial rent: " + animal.getRent());

                for (int i = 1; i < 4; i++) {
                    System.out.println("Rent at level " + i + ": " + animal.getRent() * (i + 1));
                }
                System.out.println();

                currentplayer.printMoney();
                System.out.println("Would you like to purchase this animal? If you would, enter '1'. If not, enter any other integer");
                readinint = reader.nextInt();
                if (readinint == 1){
                    board.getSpace(currentplayer.getSpace()).setOwner(currentplayer);
                    currentplayer.deltaMoney(-board.getSpace(currentplayer.getSpace()).getCost());
                    System.out.println("Excellent! You purchased " + board.getSpace(currentplayer.getSpace()).getName() + "!");
                } else{
                    System.out.println("So be it.");
                }
            }
        } else if (board.getSpace(currentplayer.getSpace()).getOwner() != currentplayer){
            if (currentplayer.getMoney() > board.getSpace(currentplayer.getSpace()).getLandCost()){
                System.out.println("You've been charged £" + board.getSpace(currentplayer.getSpace()).getLandCost() + " for landing on " +
                        board.getSpace(currentplayer.getSpace()).getName() + "!");
                currentplayer.deltaMoney(-board.getSpace(currentplayer.getSpace()).getLandCost());
                board.getSpace(currentplayer.getSpace()).getOwner().deltaMoney(board.getSpace(currentplayer.getSpace()).getLandCost());
            } else {
                System.out.println("It's the end of the line for you, buckaroo. Sayonara. (You were charged more money that you have)");
                killPlayer(currentplayer, board.getSpace(currentplayer.getSpace()).getOwner(), board, players);
                return true;
            }
        }

        return false;
    }

    private static ArrayList<Animal> getPlayerAnimals(Player player, ArrayList<Animal> animals){
        ArrayList<Animal> playeranimals = new ArrayList<>();

        for (Animal animal : animals){
            if (animal != null){
                if (animal.getOwner() == player){
                    playeranimals.add(animal);
                }
            }
        }

        return playeranimals;
    }

    private static void killPlayer(Player killee, Player killer, Board board, ArrayList<Player> players){
        killer.deltaMoney(killee.getMoney());
        for (Animal animal : getPlayerAnimals(killee, board.getAnimals())){
            animal.setOwner(killer);
        }

        binDeadTrades(killee, players);
        players.remove(killee);
    }

    private static void killPlayer(Player killee, Board board, ArrayList<Player> players){
        for (Animal animal : getPlayerAnimals(killee, board.getAnimals())){
            animal.setOwner(null);
        }

        binDeadTrades(killee, players);
        players.remove(killee);
    }

    private static void binDeadTrades(Player killee, ArrayList<Player> players) {
        ArrayList<Integer> tradestobin;

        for (Player player : players){
            tradestobin = new ArrayList<Integer>();
            for (Trade trade : player.getTrades()){
                if (trade.getSender() == killee){
                    tradestobin.add(player.getTrades().indexOf(trade));
                }
            }

            for (int i = 0; i < tradestobin.size(); i++) {
                player.getTrades().remove(tradestobin.get(i) - i);
            }
        }
    }
}
