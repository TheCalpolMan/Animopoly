package com.company;

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
}
