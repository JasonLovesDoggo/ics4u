package moria;

import entities.Player;

import java.util.Random;
import java.util.Scanner;

public class Moria {
    /*
    - Caverns where gold can be mind
    - User MUST purchase a pickaxe upon leaving the castle if they want to enter Moria (pickaxe costs 50?)
    - User can choose to enter Moria or just go home upon leaving castle
    - in addition to gold, users can mine mithril, which will all be converted to gold when they exit Moria
    - All pets will be left behind in the castle, since Moria is inhospitable for animals
    - Users risk encoutering radioactive weapons from other creatures

    - dwarves will come along and rob you
    */
    private int roomNum;
    Random rand;
    Player player;
    Scanner s;

    public Moria(Player p) {
        player = p;
        player.removeGold(100); //Pickaxe costs 100 gold
        player.reduceHealth(15); //15 health spent journeying to Moria
        roomNum = 0;
        rand = new Random();
        s = new Scanner(System.in);
        initializeGame();
    }

    private void initializeGame() {
        s.nextLine();
        System.out.println("Would you like to purchase a pickaxe that allows entrance into the dwarf-kingdom of Moria? (y/n)");
        String answer = s.nextLine();
        if (answer.toLowerCase().equals("no") || answer.toLowerCase().equals("n")) {
            System.out.println("You decline the offer and head home. Safe choice!");
            System.exit(0);
        }
        System.out.println("You buy the pickaxe and are magically transported to Moria!\n . . .");
        s.nextLine();
        System.out.println("Welcome, " + player.getName() + ", to the long-abandoned dwarven kingdom of Moria.");
        System.out.println("Here, you can mine gold or mithril, a special lightweight metal.\nIn fact, it's so lightweight that you can carry an INFINITE amount of mithril!");
        System.out.println("Unfortunately, since Moria was abandoned a long time ago, all of  your pets must stay behind.\n\nBut . . .\nMoria was abandoned for a reason.");
        System.out.println("There is always a chance of orcs or dwarves coming along and stealing from you, so be careful!");

        boolean run = true;

        while (run) {
            displayStatus();
            displayOptions();
            checkHealth();
        }

    }

    private void displayStatus() {
        System.out.println("\n==== Status ====");
        System.out.println("Name: " + player.getName());
        System.out.println("Health: " + player.getHealth() + "%");
        System.out.println("Gold: " + player.getGold() + "/" + player.getTotalCapacity());
        System.out.println("Mithril: " + player.getMithril());
        System.out.println("Inventory: pickaxe");

    }

    private void checkHealth() {
        if (!player.isAlive()) {
            System.out.println("You have run out of health. You have died.");
        } else if (player.isCritical()) {
            System.out.println("WARNING! Your health is critical. You should leave Moria before your health drops to 25%");
        }
    }

    private void displayOptions() {
        System.out.println("\nWhat would you like to do?\n1. Mine for gold\n2. Mine for mithril\n3. Rest to recover some health\n4. Exit Moria");
        int choice = -1;
        while (choice < 0) {
            try {
                choice = Integer.parseInt(s.nextLine());
            } catch (Exception e) {
                System.out.println("Please enter a number between 1 and 4");
            }
        }
        switch (choice) {
            case 1: //Mine for gold
                mineGold();
                break;
            case 2: //Mine for mithril
                mineMithril();
                break;
            case 3: //Rest
                System.out.println("You rest for a while . . .");
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                }
                int newHealth = rand.nextInt(2, 10);
                System.out.println("You recover " + newHealth + "% health!");
                player.setHealth(newHealth + player.getHealth());
                break;
            case 4:  //Exit
                int newGold = player.getMithril() * 5;

                System.out.println(player.getName() + " trades " + player.getMithril() + " mithril into " + newGold + " bars of gold!");
                System.out.println(player.getName() + " exits Moria with " + (player.getGold() + newGold) + " gold!");
                if ((player.getGold() + newGold) <= 0) {
                    System.out.println("You leave empty handed, but at least you survived!");
                } else if ((player.getGold() + newGold) > 250) {
                    System.out.println("Wow, that's impressive! You might be richer than even Smaug!");
                } else if ((player.getGold() + newGold) > 100) {
                    System.out.println("Impressive haul! You'll live comfortably for the rest of your long elven life");
                } else {
                    System.out.println("Decent job, you'll go home with some money and an adventure to tell of.");
                }
                System.exit(0);
                break;
        }
    }

    private void mineGold() {
        if (player.getGold() < player.getGoldCapacity()) { //While player is able to mine gold
            int temp = rand.nextInt(100);
            if (temp < 55) { //Player gets more gold
                int moreGold = rand.nextInt(20);
                if (player.getGold() + moreGold > player.getGoldCapacity()) {
                    moreGold = player.getGoldCapacity() - moreGold;
                }
                player.addGold(moreGold);
                if (moreGold > 0) {
                    System.out.println(player.getName() + " mined an extra " + moreGold + " bars of gold!");
                } else {
                    System.out.println("You mine a chunk of worthless rock.");
                }
            } else if (temp < 65) { //Orc comes along and steals gold
                int loseGold = rand.nextInt((player.getGold() + 2) / 2) - 1; //Orc can steal up to half of a player's gold
                int loseHealth = rand.nextInt((player.getHealth() + 2) / 2) - 1; //Orc will also attack players with a radioactive club
                player.removeGold(loseGold);
                player.reduceHealth(loseHealth);
                if (loseGold > 0) {
                    System.out.println("An orc came along and stole " + loseGold + " bars of gold from " + player.getName() + "!");
                }
                System.out.println("An orc whacked you with a radioactive club! You now have " + player.getHealth() + "% health left.");
            } else if (temp < 75) { //Dwarves come along and steal all gold and some health
                int loseHealth = rand.nextInt(player.getHealth()); //Dwarf may end up taking all the health with their radioactive axes
                player.removeGold(player.getGold()); //Remove all gold
                player.reduceHealth(loseHealth);
                System.out.println("An angry group of dwarves came along and stole all your gold!\nThey also attacked you with their radioactive axes.\nYou now have " + player.getHealth() + "% health.");
            } else if (temp < 76) { //One percent chance of the balrog, resulting in instant death
                player.reduceHealth(player.getHealth()); //Remove all health
                System.out.println("You delved too deep and awoke the nameless evil!\nA balrog rises from the shadows of the caverns of Moria.\n . . .");
                s.nextLine();
                System.out.println("It is a tall and menacing being, shrouded in darkness, shadow, and fire.\n . . .");
                s.nextLine();
                System.out.println("The balrog raises one of it's whip-like thonged tails and smites you with it.\n . . .");
                s.nextLine();
                System.out.println("In the face of such a terrible creature, you die.\nAll your gold and mithril will lie forever in Moria until the end of time.");
                System.exit(0);
            } else {
                System.out.println("You mine a chunk of worthless rock.");
            }
        } else {
            System.out.println(player.getName() + " can't carry any more gold!");
        }

    }

    private void mineMithril() {
        int temp = rand.nextInt(100);
        if (temp < 20) { //Mithril
            int newAmount = rand.nextInt(1, 5);
            player.addMithril(newAmount);
            if (newAmount > 0) {
                System.out.println(player.getName() + " mined an extra " + newAmount + " mithril");
            }
        } else if (temp < 30) { //Lose some mithril to an orc
            int amountLost = rand.nextInt(player.getMithril() + 1);
            if (amountLost <= 0 || player.getMithril() == 0) {
                System.out.println("You mine a chunk of worthless rock.");
            } else {
                player.removeMithril(amountLost);
                System.out.println("An orc sneaks into Khazad-Dum and steals some of your mithril! You lost " + amountLost + " mithril and now have " + player.getMithril());
            }
        } else if (temp < 40) { //Attacked by orc, and loses mithril
            int amountLost = rand.nextInt(player.getMithril() + 1);
            int lostHealth = rand.nextInt(player.getHealth());
            if (player.getMithril() == 0 || amountLost == 0) {
                System.out.println("You mine a chunk of worthless rock.");
            } else {
                player.removeMithril(amountLost);
                player.reduceHealth(lostHealth);
                System.out.println("An orc steals some of your mithril! You lost " + amountLost + " and now have " + player.getMithril());
                System.out.println("You try to fight back, but the orc whacks you on the head with a radioactive club and you lose " + lostHealth + "% health");
            }
        } else if (temp < 50) {
            System.out.println("You find a random rock, but at least it's shiny.");
        } else {
            System.out.println("You mine a chunk of worthless rock.");
        }
    }

}
