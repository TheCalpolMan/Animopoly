package com.company;

import java.util.ArrayList;
import java.util.Random;


// all the action classes for what cards do

class Action {
    public void execute(Player player, ArrayList<Animal> animals) {}
}

class ClockwiseMoveAction extends Action{
    int space;

    public ClockwiseMoveAction(int space){
        this.space = space;
    }

    public void execute(Player player, ArrayList<Animal> animals){
        if (player.getSpace() >= space){
            player.deltaMoney(500);
        }

        player.setSpace(space);
    }
}

class DeltaMoneyAction extends Action{
    int money;

    public DeltaMoneyAction(int money){
        this.money = money;
    }

    public void execute(Player player, ArrayList<Animal> animals){
        player.deltaMoney(money);
    }
}

class MissTurnAction extends Action{
    public void execute(Player player, ArrayList<Animal> animals){
        player.setMissTurn(true);
    }
}

class AnimalRepairAction extends Action{
    private int perAnimal, perLevel = 0;

    public AnimalRepairAction(int perAnimal){
        this.perAnimal = perAnimal;
    }

    public AnimalRepairAction(int perAnimal, int perLevel){
        this.perAnimal = perAnimal;
        this.perLevel = perLevel;
    }

    public void execute(Player player, ArrayList<Animal> animals){
        int totcost = 0;

        for (Animal animal : animals){
            if (animal == null){
                continue;
            }
            if (animal.getOwner() == player){
                totcost += perAnimal + animal.getLevel() * perLevel;
            }
        }

        player.deltaMoney(-totcost);
    }
}

class RandomMoneyAction extends Action{
    private int lower, upper;
    private Random random = new Random();

    // lower is inclusive, and upper is exclusive

    public RandomMoneyAction(int lower, int upper){
        this.lower = lower;
        this.upper = upper;
    }

    public void execute(Player player, ArrayList<Animal> animals){
        player.deltaMoney(random.nextInt(upper - lower) + lower);
    }
}

// end


class Card{
    private String text;
    private ArrayList<Action> actions;


    public Card(String text, Action action){
        this.text = text;
        this.actions = new ArrayList<>();
        this.actions.add(action);
    }

    public ArrayList<Action> getActions() {
        return actions;
    }

    public Card(String text, ArrayList<Action> actions){
        this.text = text;
        this.actions = actions;
    }

    public void execute(Player player, ArrayList<Animal> animals){
        actions.forEach(action -> action.execute(player, animals));
    }

    public String getText() {
        return text;
    }

    public void printCard(){
        System.out.println(text);
    }
}

public class Cards {
    private ArrayList<Card> cards = new ArrayList<>();
    private Random random = new Random();

    public Cards(){
        cards.add(new Card("Advance to Start", new ClockwiseMoveAction(0)));
        cards.add(new Card("Advance to Square 24, if you pass Start collect £500", new ClockwiseMoveAction(24)));
        cards.add(new Card("Bank pays you dividend of £100", new DeltaMoneyAction(100)));
        cards.add(new Card("Miss your next turn", new MissTurnAction()));
        cards.add(new Card("Take your animals to the vet! £50 per animal shall be deducted from your account.", new AnimalRepairAction(-50)));
        cards.add(new Card("It's your birthday! Have a tenner", new DeltaMoneyAction(10)));
        cards.add(new Card("One of your animals gave birth, and you sold the offspring for £250!", new DeltaMoneyAction(250)));
        cards.add(new Card("Bank error in your favour, collect £500!", new DeltaMoneyAction(500)));
        cards.add(new Card("A pipe burst! Pay £120.", new DeltaMoneyAction(-120)));
        cards.add(new Card("From sale of stock you get £150!", new DeltaMoneyAction(150)));
        cards.add(new Card("Take your animals to the vet! £50 per animal level shall be deducted from your account.", new AnimalRepairAction(-50, -50)));
        cards.add(new Card("One of your pets died. Pay £100 for it's funeral.", new DeltaMoneyAction(-100)));
        cards.add(new Card("One of your animals mauled someone! Pay them £200 so that they don't leave you a bad review!", new DeltaMoneyAction(-200)));
        cards.add(new Card("Your favourite animal got 2nd prize in a beauty competition! You receive £50!", new DeltaMoneyAction(50)));
        cards.add(new Card("You were bitten by a radioactive spider and were carted off to hospital! Miss a turn.", new MissTurnAction()));
        cards.add(new Card("Caught doing illegal breeding! Breeding fine of £40!", new DeltaMoneyAction(-40)));
        cards.add(new Card("You found a wallet, and took the money!", new RandomMoneyAction(5, 101)));
        cards.add(new Card("One of your animals bit a client, and they dropped their wallet!", new RandomMoneyAction(50, 151)));
        cards.add(new Card("One of your animals broke it's leg! Vet's fee of £50!", new DeltaMoneyAction(-50)));
        cards.add(new Card("Your animals bit a member of the Yakuza, and you had to pay them £200 for your guaranteed safety!", new DeltaMoneyAction(-200)));
    }

    public Card randomCard(){
        return cards.get(random.nextInt(cards.size()));
    }
}
