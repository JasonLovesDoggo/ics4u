/**
* Author: Jason Cameron
* Teacher: Ms. Krasteva
* Date: 2025/03/30
* Description: This program sorts a list of animals using bubble sort and then performs a sequential search to find a specific animal.
* */


public class AnimalSorter {

    /**
     * Performs a sequential search on a list.
     *
     * @param animals The list of animals to search.
     * @param searchTerm The animal name to search for.
     * @return The index of the animal if found, -1 if not found.
     */
    public static int sequentialSearch(String[] animals, String searchTerm) {
        for (int i = 0; i < animals.length; i++) {
            if (animals[i].equals(searchTerm)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Sorts the list using bubble sort.
     *
     * @param animals The list to sort.
     */
    public static void bubbleSort(String[] animals) {
        for (int i = 0; i < animals.length - 1; i++) {
            for (int j = 0; j < animals.length - 1 - i; j++) {
                if (animals[j].compareTo(animals[j + 1]) > 0) {
                    // Swap
                    String temp = animals[j];
                    animals[j] = animals[j + 1];
                    animals[j + 1] = temp;
                }
            }
        }
    }

    public static void main(String[] args) {
        String[] animals = {"Bat", "Cat", "Cow", "Dog", "Elk", "Fly", "Fox", "Gnu", "Hen", "Owl", "Pig", "Rat", "Yak"};

        bubbleSort(animals);

        String searchTerm = "Fox";
        int resultIndex = sequentialSearch(animals, searchTerm);
        if (resultIndex != -1) {
            System.out.println("The animal " + searchTerm + " was found at index " + resultIndex);
        } else {
            System.out.println("The animal " + searchTerm + " was not found.");
        }
    }
}
