package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        ArrayList<Player> players = new ArrayList<>();
        ArrayList<Animal> playeranimals, sendtradeanimals, receivetradeanimals;
        ArrayList<Action> cardactions;
        ArrayList<Integer> tradestomodify;
        Board board = new Board();
        Cards deck = new Cards();
        int readinint, currentplayerindex = 0, trademoney, donetrades;
        Player currentplayer, tradeplayer;
        String readinstring = "";
        char readinchar = ' ';
        boolean boardprinted = false, doubledice, isdead, attributetaken;
        String[] splitstring;
        Trade tradetosend;
        Card card;

        System.out.println("Welcome to Animopoly! Take a shot!");
        readinint = 1;
        while (readinint != 0 || players.size() < 2){
            attributetaken = true;
            while (attributetaken){
                System.out.println("Enter player " + (players.size() + 1) + "'s name!");
                attributetaken = false;
                readinstring = reader.nextLine();
                for (Player player : players){
                    if (player.getName().equals(readinstring)){
                        attributetaken = true;
                        break;
                    }
                }
            }
            attributetaken = true;
            while (attributetaken){
                System.out.println("Enter player " + (players.size() + 1) + "'s piece!");
                attributetaken = false;
                readinchar = reader.next().charAt(0);
                for (Player player : players){
                    if (player.getPiece() == readinchar){
                        attributetaken = true;
                        break;
                    }
                }
            }
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
                if (currentplayer.getNewTrade()){
                    System.out.println("You've got at least 1 new trade offer!");
                }

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
                        playeranimals = Utility.getPlayerAnimals(currentplayer, board.getAnimals());

                        if (playeranimals.size() == 0){
                            System.out.println("You have no animals, and as such cannot upgrade them!");
                            break;
                        }

                        for (Animal animal : playeranimals){
                            System.out.println(animal.getName() + "\n" + Utility.paddedString('-', '-', "", animal.getName().length()) +
                                    "\nLevel: " + animal.getLevel() +
                                    "\nUpgrade cost: " + animal.getUpgradeCost() +
                                    "\nCurrent rent: " + animal.getLandCost());

                            if (animal.getLevel() == 3){
                                System.out.println("Upgraded rent: N/A - " + animal.getName() + " is max level already");
                            } else{
                                System.out.println("Upgraded rent: " + animal.getRent() * (animal.getLevel() + 2) + "\n");
                            }
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
                    for (Player player : players){
                        if (player == currentplayer){
                            continue;
                        }

                        player.printData(board);
                    }

                    System.out.println("Which player would you like to trade with?\n" +
                            "Enter anything but a player's name if you've decided you don't want to trade.");
                    reader.nextLine();
                    readinstring = reader.nextLine();
                    tradeplayer = null;

                    for (Player player : players){
                        if (player.getName().equals(readinstring) && player != currentplayer){
                            tradeplayer = player;
                            break;
                        }
                    }

                    if (tradeplayer == null){
                        break;
                    }

                    currentplayer.printData(board);
                    tradeplayer.printData(board);

                    System.out.println("Please enter the amount of money you'd like to trade. Enter a negative amount if you'd like to be receiving it.");
                    trademoney = reader.nextInt();
                    System.out.println("Please enter the animals you'd like to receive. Enter them with one space between each animal");
                    reader.nextLine();
                    readinstring = reader.nextLine();

                    receivetradeanimals = Utility.animalsInString(readinstring, Utility.getPlayerAnimals(tradeplayer, board.getAnimals()));

                    System.out.println("Please enter the animals you'd like to send. Enter them with one space between each animal");
                    readinstring = reader.nextLine();

                    sendtradeanimals = Utility.animalsInString(readinstring, Utility.getPlayerAnimals(currentplayer, board.getAnimals()));

                    tradetosend = new Trade(trademoney, sendtradeanimals, receivetradeanimals, currentplayer);
                    tradetosend.printTrade();
                    System.out.println("If this trade is okay to send, enter 1. If not, enter any other integer");
                    readinint = reader.nextInt();

                    if (readinint == 1){
                        System.out.println("Trade sent!");
                        tradeplayer.addTrade(tradetosend);
                        tradeplayer.setNewTrade(true);
                    } else{
                        System.out.println("Trade cancelled.");
                    }

                    break;
                case 3: // check trades
                    currentplayer.setNewTrade(false);

                    for (int i = 0; i < currentplayer.getTrades().size(); i++) {
                        System.out.println(i + 1 + ".");
                        currentplayer.getTrades().get(i).printTrade();
                    }

                    System.out.println("Enter 1 if you'd like to accept trades, or 2 if you'd like to delete them. Enter any other integer to do neither");
                    readinint = reader.nextInt();

                    switch (readinint){
                        case 1: // accept trades
                            tradestomodify = new ArrayList<>();
                            donetrades = 0;

                            System.out.println("Enter the indexes of the trades you'd like to accept, with spaces between them.");
                            reader.nextLine();
                            readinstring = reader.nextLine();

                            splitstring = readinstring.split(" ");
                            for (String index : splitstring){
                                if (Utility.isInt(index)){
                                    if (0 < Integer.parseInt(index) && Integer.parseInt(index) <= currentplayer.getTrades().size()){
                                        tradestomodify.add(Integer.parseInt(index) - 1);
                                    }
                                }
                            }

                            for (int i = 0; i < tradestomodify.size(); i++) {
                                if (currentplayer.getTrades().get(tradestomodify.get(i) - donetrades).canDoTrade(currentplayer)){
                                    currentplayer.getTrades().get(tradestomodify.get(i) - donetrades).doTrade(currentplayer);
                                    currentplayer.getTrades().remove(tradestomodify.get(i) - donetrades);
                                    donetrades++;
                                } else{
                                    System.out.println("This trade was unable to be done: ");
                                    currentplayer.getTrades().get(i - donetrades).printTrade();
                                }
                            }

                            break;
                        case 2: // delete trades
                            tradestomodify = new ArrayList<>();

                            System.out.println("Enter the indexes of the trades you'd like to delete, with spaces between them.");
                            reader.nextLine();
                            readinstring = reader.nextLine();

                            splitstring = readinstring.split(" ");
                            for (String index : splitstring){
                                if (Utility.isInt(index)){
                                    if (0 < Integer.parseInt(index) && Integer.parseInt(index) <= currentplayer.getTrades().size()){
                                        tradestomodify.add(Integer.parseInt(index) - 1);
                                    }
                                }
                            }

                            for (int i = 0; i < tradestomodify.size(); i++) {
                                currentplayer.getTrades().remove(tradestomodify.get(i) - i);
                            }

                            break;
                        default:
                            break;
                    }

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
            } else{
                System.out.println("You're too poor to buy the animal on this square!");
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

    private static void killPlayer(Player killee, Player killer, Board board, ArrayList<Player> players){
        killer.deltaMoney(killee.getMoney());
        for (Animal animal : Utility.getPlayerAnimals(killee, board.getAnimals())){
            animal.setOwner(killer);
        }

        binDeadTrades(killee, players);
        players.remove(killee);
    }

    private static void killPlayer(Player killee, Board board, ArrayList<Player> players){
        for (Animal animal : Utility.getPlayerAnimals(killee, board.getAnimals())){
            animal.setOwner(null);
        }

        binDeadTrades(killee, players);
        players.remove(killee);
    }

    private static void binDeadTrades(Player killee, ArrayList<Player> players) {
        ArrayList<Integer> tradestobin;

        for (Player player : players){
            tradestobin = new ArrayList<>();
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
