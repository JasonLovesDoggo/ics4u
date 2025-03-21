package UniversityRanker;

import java.io.*;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;


/**
 * University class to store information about universities.
 * This class acts as a data container for all university-related information.
 */
class University {
    /** The name of the university */
    final String name;

    /** The city and country where the university is located */
    final String city;

    /** The global ranking of the university (position in top 100 universities) */
    final short globalRanking;

    /** The primary program of interest */
    String programOfInterest;

    /** The secondary program of interest */
    String secondProgramOfInterest;

    /** The cost per year to study at the university (tuition) */
    double costPerYear;

    /** The minimum grade required for admission */
    float requiredAdmissionGrade;

    /** Personal ranking of the university on a scale of 1-10 */
    short personalRanking;

    /**
     * Constructor for the University class.
     *
     * @param name The name of the university
     * @param city The city and country where the university is located
     * @param globalRanking The global ranking of the university
     * @param programOfInterest The primary program of interest
     * @param secondProgramOfInterest The secondary program of interest
     * @param costPerYear The cost per year to study at the university
     * @param requiredAdmissionGrade The minimum grade required for admission
     * @param personalRanking Personal ranking of the university on a scale of 1-10
     */
    University(String name, String city, short globalRanking, String programOfInterest,
               String secondProgramOfInterest, double costPerYear,
               float requiredAdmissionGrade, short personalRanking) {
        this.name = name;
        this.city = city;
        this.globalRanking = globalRanking;
        this.programOfInterest = programOfInterest;
        this.secondProgramOfInterest = secondProgramOfInterest;
        this.costPerYear = costPerYear;
        this.requiredAdmissionGrade = requiredAdmissionGrade;
        this.personalRanking = personalRanking;
    }

    /**
     * Converts the University object to a string representation.
     * The fields are separated by semicolons for easy file storage.
     *
     * @return String representation of the University object
     */
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

/**
 * UniversityRanker application that allows users to manage university information.
 * This class provides functionality to load, save, display, add, remove, and edit university data.
 */
class UniversityRanker {
    /** Path to the data file */
    private final String DATA_PATH;

    /** ArrayList to store University objects */
    private final ArrayList<University> universities;

    /** Scanner for user input */
    private Scanner stdin;

    /**
     * Constructor for the UniversityRanker class.
     * Initializes the ArrayList and sets the data path.
     *
     * @param dataPath Path to the data file
     */
    UniversityRanker(String dataPath) {
        universities = new ArrayList<>();
        DATA_PATH = dataPath;
    }

    /**
     * Prompts the user to press any key to continue.
     * Used to pause the program after displaying information.
     */
    void promptContinue() {
        System.out.println("\npress any key to continue...");
        stdin.nextLine();
    }

    /**
     * Starts the application and displays the main menu.
     * This method handles user input and routes to appropriate functions.
     */
    void start() {
        stdin = new Scanner(System.in);

        System.out.println("\nWelcome to UNIVERSITY RANKER!!!\n");
        while (true) {
            System.out.println("\nThere are currently " + universities.size() + " universities stored in this system.");
            System.out.println("You may:\n\t1. display all\n\t2. view specific university\n\t3. add new university" +
                    "\n\t4. remove a university\n\t5. edit a university\n\t6. save to file\n\t7. load from file" +
                    "\n\t8. exit the program");

            try {
                int choice = Integer.parseInt(stdin.nextLine());

                switch (choice) {
                    case 1: {
                        // Display all universities in a table format
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
                        // View a specific university
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
                        // Add a new university
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
                                    System.out.println("That is not a valid short. Please enter an integer");
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
                                    System.out.println("That is not a valid double. Please enter a decimal number");
                                }
                            }
                            System.out.println("Please enter the required admission grade:");
                            float requiredAdmissionGrade;
                            while (true) {
                                try {
                                    requiredAdmissionGrade = Float.parseFloat(stdin.nextLine());
                                    break;
                                } catch (Exception e) {
                                    System.out.println("That is not a valid float. Please enter a decimal number");
                                }
                            }
                            System.out.println("Please enter the personal ranking (1-10):");
                            short personalRanking;
                            while (true) {
                                try {
                                    personalRanking = Short.parseShort(stdin.nextLine());
                                    if (personalRanking > 10 || personalRanking < 1) {
                                        System.out.println("Your value must be between 1 and 10. Please try again.");
                                        continue;
                                    }
                                    break;
                                } catch (Exception e) {
                                    System.out.println("That is not a valid short. Please enter an integer");
                                }
                            }

                            universities.add(new University(name, city, globalRanking, programOfInterest,
                                    secondProgramOfInterest, costPerYear, requiredAdmissionGrade, personalRanking));

                            System.out.println(name + " added successfully.");
                            promptContinue();
                        } catch (Exception e) {
                            System.out.println("Error with your input. Please try again");
                            promptContinue();
                        }
                        break;
                    }
                    case 4: {
                        // Remove a university
                        University university = getUniversity("Which university do you want to remove?");
                        universities.remove(university);
                        System.out.println(university.name + " removed successfully.");
                        promptContinue();
                        break;
                    }
                    case 5: {
                        // Edit a university's personal ranking
                        University university = getUniversity("Which university do you want to edit?");
                        System.out.println("Your current personal ranking for " + university.name +
                                " is: " + university.personalRanking + ". What do you want to set it to?");
                        short personalRanking;
                        while (true) {
                            try {
                                personalRanking = Short.parseShort(stdin.nextLine());
                                if (personalRanking > 10 || personalRanking < 1) {
                                    System.out.println("Your value of: " + personalRanking +
                                            " is invalid. Please enter a value between 1 and 10");
                                    continue;
                                }
                                break;
                            } catch (Exception e) {
                                System.out.println("That is not a valid short. Please enter an integer from 1-10");
                            }
                        }

                        updateUniversity(university, personalRanking);
                        System.out.println(university.name + "'s personal ranking updated to " +
                                university.personalRanking + " successfully.");
                        promptContinue();
                        break;
                    }
                    case 6:
                        // Save data to file
                        saveData();
                        System.out.println("Data saved successfully to " + DATA_PATH);
                        promptContinue();
                        break;
                    case 7:
                        // Load data from file
                        int loaded = loadData();
                        System.out.println("Loaded " + loaded + " universities from " + DATA_PATH);
                        promptContinue();
                        break;
                    case 8: {
                        // Exit the program
                        saveData();
                        System.out.println("Thank you for rating!");
                        System.exit(0);
                    }
                    default:
                        System.out.println("Invalid option. Please select a number between 1 and 8.");
                        promptContinue();
                        break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
                promptContinue();
            }
        }
    }

    /**
     * Gets a university from the user by displaying a list and
     * prompting the user to select one by index.
     *
     * @param prompt The message to display to the user
     * @return The selected University object
     */
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
                if (index > universities.size() || index < 1) {
                    System.out.println("Invalid index. Please try one in the valid range.");
                    continue;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid index, please try again.");
                continue;
            }
            break;
        }
        return universities.get(index - 1);
    }

    /**
     * Updates the personal ranking of a university.
     *
     * @param university The University object to update
     * @param personalRanking The new personal ranking value
     */
    void updateUniversity(University university, short personalRanking) {
        universities.get(universities.indexOf(university)).personalRanking = personalRanking;
    }

    /**
     * Saves the university data to the specified file.
     *
     * @return true if the data was saved successfully, false otherwise
     */
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
            return false;
        }
        return true;
    }

    /**
     * Loads university data from the specified file.
     *
     * @return The number of universities successfully loaded
     */
    int loadData() {
        short ignoredLines = 0;
        short numLines = 0;
        try {
            Scanner s = new Scanner(new File(DATA_PATH));
            try {
                String rawNumLines = s.nextLine();
                numLines = Short.parseShort(rawNumLines);
            } catch (NumberFormatException | NoSuchElementException e) {
                System.out.println("Your data file: " + DATA_PATH + " is corrupted. Please reset it");
                System.exit(0);
            }
            universities.clear();

            for (Integer i = 0; i < numLines; i++) {
                String rawData = i.toString();
                try {
                    rawData = s.nextLine();
                    String[] data = rawData.split(";");
                    if (data.length != 8) {
                        System.out.println("Entry: " + rawData + " has incorrect number of fields. Ignoring..");
                        ignoredLines++;
                        continue;
                    }
                    universities.add(new University(data[0], data[1], Short.parseShort(data[2]), data[3], data[4],
                            Double.parseDouble(data[5]), Float.parseFloat(data[6]), Short.parseShort(data[7])));
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