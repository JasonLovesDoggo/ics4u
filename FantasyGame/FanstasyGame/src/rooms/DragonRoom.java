package rooms;

import entities.Player;

import java.util.ArrayList;
import java.util.Scanner;

public class DragonRoom extends Room {

    private int goldAmount = Integer.MAX_VALUE;

    public DragonRoom(String description) {
        super(description);
    }

    @Override
    public void interact(Player player) {
        System.out.println(player.getName() + " entered " + this.description);

    }

    @Override
    public void takeGold(Player player) {
        Scanner s = new Scanner(System.in);
        int choice = -1;
        boolean correct = false;
        while (!correct) {
            try {
                System.out.println("\nWOW, this room is FULL of gold!");
                System.out.println("Would you like to: \n1. Take nothing\n2. Take only as much as you can carry\n3. TAKE EVERYTHING");
                choice = Integer.parseInt(s.nextLine()) - 1;
                if (choice >= 0 && choice < 3) {
                    correct = true;
                } else {
                    System.out.println("Please enter a number between 1 and 3");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
        switch (choice) {
            case 0:
                System.out.println("It seems too good to be true, so you don't touch the gold.");
                break;
            case 1:
                int goldToTake = Math.min(goldAmount, player.getTotalCapacity() - player.getGold());
                if (goldToTake > 0) {
                    player.addGold(goldToTake);
                    goldAmount -= goldToTake;
                    System.out.println(player.getName() + " took only what they can carry. You now have " + player.getGold() + " gold");
                } else {
                    System.out.println(player.getName() + ". There is no more gold to take!");
                }
                break;
            case 2:
                goldToTake = goldAmount;
                player.addDragonGold(goldToTake - 1);
                goldAmount -= goldToTake;
                System.out.println(player.getName() + " took all the gold in this room!");
        }
    }

    @Override
    public void handleOption(int option, Player player) {
        switch (option) {
            case 0: //Try to leave
                if (goldAmount < Integer.MAX_VALUE) {
                    Scanner s = new Scanner(System.in);
                    System.out.println("\nYou reach for the door to leave this room, but as you do, you drop a bar of gold.\nThe gold clatters loudly and you freeze.\n(Press enter to continue)");
                    s.nextLine();

                    System.out.println("\n\n                               %#@      %@    \n                       #*%  %*::+@   *+-=*\n                   #*@+-=%+=+--+##+=-..-*%\n                  *++*+=+*****+=:...::=*%\n                 #====+#%*+=+=----===*#@\n                %*=-=*%+@*+=++*******##%   ######\n           %%%%%#+===*%@*::=+++***=--=#########            @@#***++++++***##%@@       \n          %*==+*========--==***+++=::*#######        @%*+=+++**##%%%%%#####%%%%%@     \n          *+=======+++**+==++*%%*=--=%%######    %#+=+**#%#+=--========++*%@          \n          %*#%#*#@%%%%*=++++*#%%%%%%##########**=+**##+=---============+#\n            *@ %%*#***+****##%#**#%**######**+**##*=======-==-========+*\n               ###*##%%%****+*****%**####*+**##*++++++++++++==========*%\n                       @----=****#%%###*==**###################%%%#****#\n                       *-:::=****%#*%##*+**%%%%#####***************#%%%%@\n                      %-:::-+*#*#%%####***%%#%#*******************%\n                     #=--:=++***%**####*+#%##%#%#*++++++++*******%        @\n                    +::.::+****%%%#####*+##***#%###*+++++++*****#%      %*%@\n                  *=::::-*+***%**######**%#*+++**%##**+++****#%%%@   *#*###%\n                *=::..::*++*#%% #*####***%****+++**%##*****%        %****#%@          \n              #=::.::::+++**%#*@ **##%#+#%*****+++**#%%#**%        ***#%%%%\n             *=:::::::=++***#%   %*###**%#******+*****%%%#@       **+*%%%%\n             =::....:-+++**#**%  %*****#%*************##%%@       %*+*%@\n            *::::::::=++++**#***+***#%%%#**********%%    @@      %+*=*#%\n           @::...:.:-+++*+******##%%%#***********#                 @**#%%\n           +::.:::::=*++*+++****%%##************#@                 **+*#%\n           =-::::.::=*+++=++++*+*#%##****#%@                        #**#%%\n           =::::::::=**======+**+**%####%#*%                        **+**##\n           +::::::::-*+======+++++*+*##***+*@                        ****##%\n           %:-:::::::+*=-====+++++*********##++#                    %**+*#%*\n            =:::::::--*++++=++++*****====+**##*%@%*%                ##***#%*\n            %====-::::-*=++++++**%%+==-====++*******@%**@  @@  @#%@%#****#%*\n             #-=-:::::==*=+++****%*====+++++++****#****=*@*==#@*+***#****%##\n            %%%+========+*+******#*++=+++++++*###**##*#*+++++=++**##***#%%*@\n             %%%+======+=*+*****##**++*+*******%%#********************#%#*@\n             @%%%%+++++=+******##*************#%%%%##***#*********##%#***\n            @@@@%%%%**+++****####*+*%####**######**###%%%%%%%%%%%#**++*#\n        --**#*#*%%%%%%*#*#####%%%+**#%#%#######%*++++++*++**++++=++===-\n     ----=+*******+===--:=+:*%%*=--:+:-*%%%%%%%*===+++++++++=====---------\n             -=:----=****=#*=---==*=**+=----=--------------------");
                    System.out.print("From the depths of the room, a dragon arises.\n . . .");
                    s.nextLine();
                    System.out.print("HOW DARE YOU STEAL MY GOLD? the dragon roars.\n . . .");
                    s.nextLine();
                    System.out.print("He spits a giant flame at you and your health plummets to 0%.\n . . .");
                    s.nextLine();
                    System.out.print("You have died.\n\nCause of death: Incineration\n");
                    System.exit(0);
                } else {
                    System.out.println("You leave.");
                }
                break;
            case 1: // Take gold
                takeGold(player);
                break;
            case 2: // Search for treasures
                int chance = rng.nextInt(100);
                if (chance < 20) {
                    int extraGold = rng.nextInt(10, 100);
                    player.addDragonGold(extraGold);
                    System.out.println(player.getName() + " took " + extraGold + " gold.");
                    goldAmount -= extraGold; //Trigger the dragon
                } else {
                    System.out.println("You search but find nothing of value.");
                }
                break;
            case 3: //Rest and recover
                System.out.println("You sit down and rest for a while...");
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e); // You can't simply ignore this
                }
                player.setHealth(player.getHealth() + 3);
                System.out.println("You wake up and have recovered 3% health.");
                System.out.println("Your health is now " + player.getHealth() + "%");
                break;

        }
    }

    public ArrayList<String> getOptions() {
        ArrayList<String> options = new ArrayList<>();
        options.add("Leave this room");
        options.add("Take LOTS of gold from this room");
        options.add("Search for hidden treasures");
        //Maybe add another option to allow the user to interact with dragon if they know it's there?
        options.add("Rest and recover (gain 3% health)");
        return options;
    }

}
