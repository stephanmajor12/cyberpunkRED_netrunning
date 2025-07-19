package org.major.Essentials;

public class Dice {
    public static int roll(int sides) {
        return (int)(Math.random() * sides) + 1;
    }
}
