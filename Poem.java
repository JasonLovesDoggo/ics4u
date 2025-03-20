/*
 * @author Justin Jiang
 * ICS4U1
 * Mrs. Krasteva
 * Poem Randomizer 
 */

import java.io.*;
import java.util.*;

public class Poem {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("poem.txt"));
        boolean randomizeWords = true;
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Would you like to randomize words within each line? (Y/n): ");

        //take input once - if the user enters an invalid option it goes to the default (Y)
        try {
            String s = input.readLine();
            if (s.toLowerCase().equals("n"))
                randomizeWords = false;
        } catch (IOException e) {
        }
        //close buffered reader to avoid resource leak
        input.close();
        //read file until it ends
        ArrayList<String> lines = new ArrayList<String>();
        String read;
        while ((read = br.readLine()) != null) {
            if (read.length() > 3) //in case of newlines, 3 is a trivial number
                lines.add(read);
        }

        //close bufferedreader to avoid resource leak
        br.close();

        //shuffles order of lines regardless of user's choice
        Collections.shuffle(lines);
        int counter = 0;
        for (String s : lines) {
            if (counter++ % 4 == 0)
                System.out.println();
            if (randomizeWords) {
                String[] words = s.split(" ");
                ArrayList<String> line = new ArrayList<>(Arrays.asList(words)); //https://stackoverflow.com/questions/157944/create-arraylist-from-array#157950
                Collections.shuffle(line);
                for (String w : line) {
                    System.out.print(w + " ");
                }
                System.out.println();
            } else System.out.println(s);
        }
    }
}
