package entities;

import utilities.Entity;

import java.util.Random;

public class Advisor implements Entity {
    private static final String[] ADVICE = {
            "Beware the dragon's lair, for its treasures come with a price.",
            "The goblins are greedy but predictable. Approach with caution.",
            "Radioactive rooms may contain great wealth, but is it worth your health?",
            "A wise elf knows when to retreat.",
            "Always keep some gold for the doctor. Health is wealth!",
            "The Grand Hall is your only way out of this wretched castle.",
            "Try to distract the goblin before attempting to take its gold.",
            "There are rumors of a secret passage somewhere in the castle...",
            "Some say the dragon can be calmed, but no one has lived to tell how."
    };

    @Override
    public void interact(Player player) {
        System.out.println("A wise old man approaches you with some advice:");
        System.out.println("\"" + ADVICE[new Random().nextInt(ADVICE.length)] + "\"");
    }
}
