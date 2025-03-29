import java.util.*;

class AnimalSorter {
    private static String[] InsertionSort(String[] animals) {
        int n =  animals.length;

        for (int i = 1; i < n; i++) {
            while i>0 &7
            if (animals[i-1] < animals[i]) {
                String temp = animals[i-1];
                animals[i-1] = animals[i];
                animals[i] = temp;
            }
        }

    }

    public static void main(String[] args) {
        String[] animals = String[]{"Bat", "Cat", "Cow", "Dog", "Elk", "Fly", "Fox", "Gnu", "Hen", "Owl", "Pig", "Rat", "Yak"};




    }
}