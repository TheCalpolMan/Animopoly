package com.company;

import java.util.ArrayList;

//trading code
public class Trade {
    //identifers for the trading class
    private int moneytoreceiver;
    private ArrayList<Animal> sendAnimals;
    private ArrayList<Animal> receiveAnimals;
    private Player sender;

    //constructors for the trading class
    public Trade(int moneytoreceiver, ArrayList<Animal> sendAnimals, ArrayList<Animal> receiveAnimals, Player sender){
        this.moneytoreceiver = moneytoreceiver;
        this.sendAnimals = sendAnimals;
        this.receiveAnimals = receiveAnimals;
        this.sender = sender;
    }

    //getters for the trading class
    public int getMoney() {
        return moneytoreceiver;
    }

    public ArrayList<Animal> getSendAnimals() {
        return sendAnimals;
    }

    public ArrayList<Animal> getReceiveAnimals() {
        return receiveAnimals;
    }

    public Player getSender(){
        return sender;
    }

    public boolean canDoTrade(Player receiver){
        if (moneytoreceiver < 0 && receiver.getMoney() < -moneytoreceiver){
            return false;
        } else if (moneytoreceiver > 0 && sender.getMoney() < moneytoreceiver){
            return false;
        }

        for (Animal animal : sendAnimals) {
            // check the sender has animal, if not return false

            if (animal.getOwner() != sender){
                return false;
            }
        }

        for (Animal animal : receiveAnimals){
            // check the receiver has animal, if not return false

            if (animal.getOwner() != receiver){
                return false;
            }
        }

        return true;
    }

    //code to finalise the trade
    public void doTrade(Player receiver){
        receiver.deltaMoney(moneytoreceiver);
        sender.deltaMoney(-moneytoreceiver);

        for (Animal animal : sendAnimals) {
            // remove all the animals from the sender
            // add them all to the receiver

            animal.setOwner(receiver);
        }

        for (Animal animal : receiveAnimals){
            // add all the animals to the sender
            // remove them all from the receiver

            animal.setOwner(sender);
        }
    }

    //code to print out the transaction invoice
    public void printTrade(){
        System.out.println("From " + sender.getName() +
                "\n" + Utility.repeatedChar('-', ("From " + sender.getName()).length()));

        if (moneytoreceiver < 0){
            System.out.println("-£" + Math.abs(moneytoreceiver));
        } else if (moneytoreceiver > 0){
            System.out.println("£" + moneytoreceiver);
        }

        for (Animal animal : sendAnimals){
            System.out.println("+ " + animal.getName() +
                    "\n└──→Level: " + animal.getLevel());
        }

        for (Animal animal : receiveAnimals){
            System.out.println("- " + animal.getName() +
                    "\n└──→Level: " + animal.getLevel());
        }

        System.out.println();
    }
}
