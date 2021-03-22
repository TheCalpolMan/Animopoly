package com.company;

import java.util.ArrayList;

public class Utility {
    public static int boolToInt(boolean bool){
        if (bool){
            return 1;
        }
        return 0;
    }

    public static String paddedString(char outerpadding, char innerpadding, String topad, int size){
        while (topad.length() <= size - 4){
            topad = innerpadding + topad + innerpadding;
        }

        if (topad.length() < size - 2){
            topad = topad + innerpadding;
        }

        topad = outerpadding + topad + outerpadding;
        return topad;
    }

    public static String paddedString(char[] outerpadding, char[] innerpadding, String topad, int size){
        while (topad.length() <= size - 4){
            topad = innerpadding[0] + topad + innerpadding[1];
        }

        if (topad.length() < size - 2){
            topad = topad + innerpadding[1];
        }

        topad = outerpadding[0] + topad + outerpadding[1];
        return topad;
    }

    public static String paddedString(String topad, int size){
        return paddedString('|', ' ', topad, size);
    }

    public static String repeatedChar(char torepeat, int length){
        String returnstring = "";
        for (int i = 0; i < length; i++) {
            returnstring += torepeat;
        }
        return returnstring;
    }

    public static ArrayList<Animal> getPlayerAnimals(Player player, ArrayList<Animal> animals){
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

    public static boolean isInt(String tocheck){
        char[] ints = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};

        for (int i=0;i<tocheck.length();i++){
            if (! isInt(tocheck.charAt(i))){
                return false;
            }
        }

        return true;
    }

    public static boolean isInt(char tocheck){
        char[] ints = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};

        return doesContain(ints, tocheck);
    }

    public static boolean doesContain(char[] container, char term){
        for (int i = 0; i<container.length; i++){
            if (container[i] == term){
                return true;
            }
        }

        return false;
    }

    public static ArrayList<Animal> animalsInString(String tosearch, ArrayList<Animal> animals){
        ArrayList<Animal> returnanimals = new ArrayList<>(), returnanimalspure = new ArrayList<>();
        int cut = 0;
        boolean animalinarray;

        for (int i = 0; i < tosearch.length(); i++) {
            if (i < cut){
                continue;
            }

            for (Animal animal : animals){
                if (i + animal.getName().length() > tosearch.length()){
                    continue;
                }

                if (i + animal.getName().length() == tosearch.length()){
                    if (tosearch.substring(i).equals(animal.getName())){
                        returnanimals.add(animal);
                        cut = tosearch.length();
                    }
                } else{
                    if (tosearch.substring(i, animal.getName().length()).equals(animal.getName())){
                        returnanimals.add(animal);
                        cut = i + animal.getName().length() - 1;
                    }
                }

            }
        }

        for (Animal animal : returnanimals){
            animalinarray = false;
            for (Animal returnanimal : returnanimalspure){
                if (animal == returnanimal){
                    animalinarray = true;
                    break;
                }
            }

            if (!animalinarray){
                returnanimalspure.add(animal);
            }
        }

        return returnanimalspure;
    }
}
