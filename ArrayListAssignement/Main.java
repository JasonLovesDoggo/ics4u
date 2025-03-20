/*
 * @author Justin Jiang
 * ICS4U1
 * Mrs. Krasteva
 * Driver Class for Sentence
 */

import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) {
        Sentence sentence = new Sentence();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter the number of sentences you would like to generate (default=5): ");
        int num;
        try {
            num = Integer.parseInt(br.readLine());
        } catch (Exception e) {
            num = 5;
        }
        ArrayList<String> sentences = sentence.makeSentences(Math.abs(num));
        for (String s : sentences) {
            System.out.println(s);
        }
    }
}
