/*
* Author: Jason Cameron
* Teacher: Ms. Krasteva
* Date: 2025/03/30
* Description: This program takes in a list of names and marks from a file, sorts them alphabetically by name and numerically by marks, and outputs the results.
*/
import java.io.*;
import java.util.ArrayList;

/**
 * This class demonstrates sorting and handling of names and marks from a file.
 */
public class NameMarksSorter {

    /**
     * Reads data from the file and populates the names and marks into two ArrayLists.
     *
     * @param filename The name of the file to read from.
     * @throws IOException If there's an error reading the file. // citation: the IntelliJ IDE told me that I needed to add the throws IOException to the method signature
     */
    public static void readDataFromFile(String filename, ArrayList<String> names, ArrayList<Integer> marks) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line;

        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(" ");  // Assuming the name and mark are separated by space
            names.add(parts[0]);
            marks.add(Integer.parseInt(parts[1]));
        }

        reader.close();
    }

    /**
     * Outputs the names and marks in two columns with 5 spaces between them.
     *
     * @param names The ArrayList of names.
     * @param marks The ArrayList of marks.
     */
    public static void printData(ArrayList<String> names, ArrayList<Integer> marks) {
        for (int i = 0; i < names.size(); i++) {
            System.out.printf("%-20s %5d\n", names.get(i), marks.get(i));
        }
    }

    /**
     * Sorts the data alphabetically by name using selection sort.
     *
     * @param names The ArrayList of names to sort.
     * @param marks The ArrayList of marks, which will be rearranged accordingly.
     */
    public static void selectionSortByName(ArrayList<String> names, ArrayList<Integer> marks) {
        for (int i = 0; i < names.size() - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < names.size(); j++) {
                if (names.get(j).compareTo(names.get(minIndex)) < 0) {
                    minIndex = j;
                }
            }
            // Swap names
            String tempName = names.get(i);
            names.set(i, names.get(minIndex));
            names.set(minIndex, tempName);

            // Swap marks to keep them aligned with the names
            int tempMark = marks.get(i);
            marks.set(i, marks.get(minIndex));
            marks.set(minIndex, tempMark);
        }
    }

    /**
     * Sorts the data numerically by marks using selection sort (largest to smallest).
     *
     * @param names The ArrayList of names, which will be rearranged accordingly.
     * @param marks The ArrayList of marks to sort.
     */
    public static void selectionSortByMarks(ArrayList<String> names, ArrayList<Integer> marks) {
        for (int i = 0; i < marks.size() - 1; i++) {
            int maxIndex = i;
            for (int j = i + 1; j < marks.size(); j++) {
                if (marks.get(j) > marks.get(maxIndex)) {
                    maxIndex = j;
                }
            }
            // Swap marks
            int tempMark = marks.get(i);
            marks.set(i, marks.get(maxIndex));
            marks.set(maxIndex, tempMark);

            // Swap names to keep them aligned with the marks
            String tempName = names.get(i);
            names.set(i, names.get(maxIndex));
            names.set(maxIndex, tempName);
        }
    }

    public static void main(String[] args) {
        ArrayList<String> names = new ArrayList<>();
        ArrayList<Integer> marks = new ArrayList<>();
        String filename = "src/A7-1.txt";  // Path to the file containing the names and marks

        try {
            // a) Read data from the file
            readDataFromFile(filename, names, marks);

            // Output the original data
            System.out.println("Original Data:");
            printData(names, marks);

            // b) Sort by name and output the data
            selectionSortByName(names, marks);
            System.out.println("\nSorted by Name:");
            printData(names, marks);

            // c) Sort by marks and output the data (largest to smallest)
            selectionSortByMarks(names, marks);
            System.out.println("\nSorted by Marks (Largest to Smallest):");
            printData(names, marks);

        } catch (IOException e) {
            System.out.println("Error reading the file: " + e.getMessage());
        }
    }
}
