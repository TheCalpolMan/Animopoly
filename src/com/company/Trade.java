package com.company;

import java.util.ArrayList;

public class Trade {
    private int sendMoney;
    private ArrayList<Animal> sendAnimals;
    private ArrayList<Animal> receiveAnimals;
    private Player sender;

    public Trade(int sendMoney, ArrayList<Animal> sendAnimals, ArrayList<Animal> receiveAnimals, Player sender){
        this.sendMoney = sendMoney;
        this.sendAnimals = sendAnimals;
        this.receiveAnimals = receiveAnimals;
        this.sender = sender;
    }

    public int getMoney() {
        return sendMoney;
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
        if (sendMoney < 0 && sender.getMoney() < -sendMoney){
            return false;
        } else if (sendMoney > 0 && receiver.getMoney() < sendMoney){
            return false;
        }
        receiver.deltaMoney(-sendMoney);
        sender.deltaMoney(sendMoney);

        for (Animal animal : sendAnimals) {
            // check the sender has animal, if not return false
        }

        for (Animal animal : receiveAnimals){
            // check the receiver has animal, if not return false
        }

        return true;
    }

    public void doTrade(Player receiver){
        receiver.deltaMoney(sendMoney);
        sender.deltaMoney(-sendMoney);

        for (Animal animal : sendAnimals) {
            // remove all the animals from the sender
            // add them all to the receiver
        }

        for (Animal animal : receiveAnimals){
            // add all the animals to the sender
            // remove them all from the receiver
        }
    }

    public void printTrade(){
        System.out.println();

        if (sendMoney < 0){
            System.out.println("-£" + sendMoney);
        } else if (sendMoney > 0){
            System.out.println("£" + sendMoney);
        }

        for (Animal animal : sendAnimals){
            System.out.println("+ " + animal.getName());
        }

        for (Animal animal : receiveAnimals){
            System.out.println("- " + animal.getName());
        }
    }
}
