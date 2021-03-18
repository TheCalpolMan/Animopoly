package com.company;

import java.util.ArrayList;

public class Trade {
    private int sendMoney;
    private ArrayList<Animal> sendAnimals;
    private ArrayList<Animal> receiveAnimals;

    public Trade(int sendMoney, ArrayList<Animal> sendAnimals, ArrayList<Animal> receiveAnimals){
        this.sendMoney = sendMoney;
        this.sendAnimals = sendAnimals;
        this.receiveAnimals = receiveAnimals;
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

    public boolean canDoTrade(Player sender, Player receiver){
        if (sendMoney < 0 && sender.getMoney() < -sendMoney){
            return false;
        }
        receiver.deltaMoney(-sendMoney);
        sender.deltaMoney(sendMoney);

        for (Animal animal : sendAnimals) {
            // remove all the animals from the sender
            // add them all to the receiver
        }

        for (Animal animal : receiveAnimals){
            // add all the animals to the sender
            // remove them all from the receiver
        }
    }

    public void doTrade(Player sender, Player receiver){
        receiver.deltaMoney(-sendMoney);
        sender.deltaMoney(sendMoney);

        for (Animal animal : sendAnimals) {
            // remove all the animals from the sender
            // add them all to the receiver
        }

        for (Animal animal : receiveAnimals){
            // add all the animals to the sender
            // remove them all from the receiver
        }
    }
}
