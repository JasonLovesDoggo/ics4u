package UniversityRanker;

import java.io.*;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;


class University {
    /* dataclass */
    final String name;
    final String city;
    final short globalRanking; // index in the array of top 100 unis (-1 if not in the array)
    String programOfInterest;
    String secondProgramOfInterest;
    double costPerYear;
    float requiredAdmissionGrade;
    short personalRanking; // the user's personal ranking out of 10


    University(String name, String city, short globalRanking, String programOfInterest, String secondProgramOfInterest, double costPerYear, float requiredAdmissionGrade, short personalRanking) {
        this.name = name;
        this.city = city;
        this.globalRanking = globalRanking;
        this.programOfInterest = programOfInterest;
        this.secondProgramOfInterest = secondProgramOfInterest;
        this.costPerYear = costPerYear;
        this.requiredAdmissionGrade = requiredAdmissionGrade;
        this.personalRanking = personalRanking;
    }

    public String toString() {
        StringBuilder data = new StringBuilder();
        data.append(this.name);
        data.append(";");
        data.append(this.city);
        data.append(";");
        data.append(this.globalRanking);
        data.append(";");
        data.append(this.programOfInterest);
        data.append(";");
        data.append(this.secondProgramOfInterest);
        data.append(";");
        data.append(this.costPerYear);
        data.append(";");
        data.append(this.requiredAdmissionGrade);
        data.append(";");
        data.append(this.personalRanking);
        return data.toString();
    }
}


public class UniversityRanker {
    private final String DATA_PATH;
    private final ArrayList<University> universities;

    private Scanner stdin;
    // todo test loading/setting unis

    UniversityRanker(String dataPath) {
        universities = new ArrayList<>();
        DATA_PATH = dataPath;
    }

    void promptContinue() {
        System.out.println("\npress any key to continue...");
        stdin.nextLine();
    }

    void start() {
        stdin = new Scanner(System.in);

        System.out.println("\nWelcome to UNIVERSITY RANKER!!!\n");
        while (true) {
            System.out.println("\nThere are currently " + universities.size() + " universities stored in this system.");
            System.out.println("You may:\n\t1. display all\n\t2. view specific university\n\t3. add new university\n\t4. remove a university\n\t5. edit a university\n\t6. save to file\n\t7. load from file\n\t8. exit the program");

            try {
                int choice = Integer.parseInt(stdin.nextLine());

                switch (choice) {
                    case 1: {
                        System.out.format("| %-15s | %-20s | %-15s | %-23s | %-28s | %-10s | %-8s | %-8s |%n",
                                "Name", "City", "Global Rank", "Top Choice", "2nd Choice", "Cost/Year", "Grade", "Ranking");
                        for (final University university : universities) {


                            System.out.format("| %-15s | %-20s | %-15s | %-23s | %-28s | %-10s | %-8s | %-8s |%n",
                                    (Object[]) university.toString().split(";"));
                        }
                        promptContinue();
                        break;
                    }
                    case 2: {
                        University uni = getUniversity("Please enter the uni you want to view");
                        System.out.println("Name: " + uni.name);
                        System.out.println("City: " + uni.city);
                        System.out.println("Top 100 Universities position:\t\t " + uni.globalRanking);
                        System.out.println("1st Choice: " + uni.programOfInterest);
                        System.out.println("2nd Choice: " + uni.secondProgramOfInterest);
                        System.out.println("Cost Per Year: " + uni.costPerYear);
                        System.out.println("Required Admission Grade: " + uni.requiredAdmissionGrade);
                        System.out.println("Personal Ranking: " + uni.personalRanking);
                        promptContinue();
                        break;
                    }
                    case 3: {
                        try {
                            System.out.println("Please enter the name of the university:");
                            String name = stdin.nextLine();
                            System.out.println("Please enter the city of the university:");
                            String city = stdin.nextLine();
                            System.out.println("Please enter the global ranking of the university:");
                            short globalRanking;
                            while (true) {
                                try {
                                    globalRanking = Short.parseShort(stdin.nextLine());
                                    break;
                                } catch (Exception e) {
                                    System.out.println("That is not a valid short. Please keep your input to a integer");
                                }
                            }
                            System.out.println("Please enter the program of interest:");
                            String programOfInterest = stdin.nextLine();
                            System.out.println("Please enter the second program of interest:");
                            String secondProgramOfInterest = stdin.nextLine();
                            System.out.println("Please enter the cost per year:");
                            double costPerYear;
                            while (true) {
                                try {
                                    costPerYear = Double.parseDouble(stdin.nextLine());
                                    break;
                                } catch (Exception e) {
                                    System.out.println("That is not a valid double. Please keep your input to a double");
                                }
                            }
                            System.out.println("Please enter the required admission grade:");
                            float requiredAdmissionGrade;
                            while (true) {
                                try {
                                    requiredAdmissionGrade = Float.parseFloat(stdin.nextLine());
                                    break;
                                } catch (Exception e) {
                                    System.out.println("That is not a valid float. Please keep your input to a float");
                                }
                            }
                            System.out.println("Please enter the personal ranking:");
                            short personalRanking;
                            while (true) {
                                try {
                                    personalRanking = Short.parseShort(stdin.nextLine());
                                    break;
                                } catch (Exception e) {
                                    System.out.println("That is not a valid short. Please keep your input to a integer");
                                }
                            }

                            universities.add(new University(name, city, globalRanking, programOfInterest, secondProgramOfInterest, costPerYear, requiredAdmissionGrade, personalRanking));

                            System.out.println(name + " added successfully.");
                            promptContinue();
                            break;
                        } catch (Exception e) {
                            System.out.println("Error with your  input. Please try again");
                            promptContinue();
                        }
                        break;

                    }
                    case 4: {
                        University university = getUniversity("Which university do you want to remove?");

                        universities.remove(university);

                        System.out.println(university.name + " removed successfully.");

                        promptContinue();
                        break;
                    }
                    case 5: {
                        University university = getUniversity("Which university do you want to edit?");

                        System.out.println("Your current personal ranking for " +university.name + " is: " + university.personalRanking + ". What do you want to set it to?");
                        short personalRanking;
                        while (true) {
                            try {
                                personalRanking = Short.parseShort(stdin.nextLine());
                                if (personalRanking > 10 || 0 > personalRanking) {
                                    System.out.println("Your value of: " + personalRanking + " is too high. Please keep your input within the range of 0-10");
                                    continue;
                                }
                                break;
                            } catch (Exception e) {
                                System.out.println("That is not a valid short. Please keep your input to a integer from 0-10");
                            }
                        }

                        updateUniversity(university, personalRanking);

                        System.out.println(university.name + "'s personal ranking updated to " + university.personalRanking + " successfully.");
                        promptContinue();
                        break;
                    }
                    case 6:
                        saveData();
                        promptContinue();
                        break;
                    case 7:
                        loadData();
                        promptContinue();
                        break;
                    case 8: {
                        saveData();
                        System.out.println("Thank you for rating!");
                        System.exit(0);
                    }

                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
                promptContinue();
            }
        }
    }

    private University getUniversity(String prompt) {
        short index;
        while (true) {
            short count = 0;
            System.out.println(prompt);
            for (final University university : universities) {
                System.out.println(++count + ": " + university.name);
            }
            try {
                index = Short.parseShort(stdin.nextLine());
                if (index > universities.size() || index < 0) {
                    System.out.println("Invalid index. Please try one in the valid range..");
                    continue;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid index, please try again..");
                continue;
            }
            break;
        }

        return universities.get(index - 1);
    }

    void updateUniversity(University university, short personalRanking) {
        universities.get(universities.indexOf(university)).personalRanking = personalRanking;
    }

    boolean saveData() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(DATA_PATH, false))) {

            short count = (short) universities.size();
            writer.println(count);
            for (University university : universities) {
                writer.println(university.toString());
            }
        } catch (IOError e) {
            System.out.println("Error accessing file: " + e);
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false; // count not save
        }
        return true;
    }

    int loadData() {
        short ignoredLines = 0;
        short numLines = 0;
        try {
            Scanner s = new Scanner(new File(DATA_PATH));

            try {
                String rawNumLines = s.nextLine();
                numLines = Short.parseShort(rawNumLines);
            } catch (NumberFormatException | NoSuchElementException e) {
                System.out.println("your data file: " + DATA_PATH + " is corrupted. Please reset it");
                System.exit(0);
            }
            universities.clear();

            for (Integer i = 0; i < numLines; i++) {
                String rawData = i.toString();
                try {
                    rawData = s.nextLine();
                    String[] data = rawData.split(";");
                    if (data.length != 8) {
                        continue;
                    }

                    universities.add(new University(data[0], data[1], Short.parseShort(data[2]), data[3], data[4], Double.parseDouble(data[5]), Float.parseFloat(data[6]), Short.parseShort(data[7])));

                } catch (NumberFormatException | IndexOutOfBoundsException | NoSuchElementException e) {
                    System.out.println("Entry: " + rawData + " is corrupted. Ignoring..");
                    ignoredLines++;
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println("The file is not found :( it should be relative to: " + new File("").getAbsolutePath());
            System.exit(0);

        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);

        }

        return numLines - ignoredLines;
    }

}

