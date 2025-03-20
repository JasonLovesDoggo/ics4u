/*
 * @author Justin Jiang
 * ICS4U1
 * Mrs. Krasteva
 * Java program to create random sentences using ArrayLists
 */

import java.util.*;

public class Sentence { //Sentence class
    private ArrayList<String> articles = new ArrayList<String>();
    private ArrayList<String> nouns = new ArrayList<String>();
    private ArrayList<String> verbs = new ArrayList<String>();
    private ArrayList<String> prepositions = new ArrayList<String>();
    private ArrayList<String> adverbs = new ArrayList<String>();
    private ArrayList<String> adjectives = new ArrayList<String>();

    public Sentence() { //initialize all ArrayLists with daya

        articles.add("A");
        articles.add("The");

        nouns.add("cat");
        nouns.add("dog");
        nouns.add("ball");
        nouns.add("chair");
        nouns.add("phone");
        nouns.add("computer");
        nouns.add("shoe");
        nouns.add("mouse");
        nouns.add("bottle");
        nouns.add("table");
        nouns.add("orange");
        nouns.add("apple");
        nouns.add("Elon Musk");
        nouns.add("underwater basket weaver");

        verbs.add("runs");
        verbs.add("falls");
        verbs.add("crumbles");
        verbs.add("eats");
        verbs.add("rolls");
        verbs.add("buzzs");
        verbs.add("breaks");
        verbs.add("pulls");
        verbs.add("weaves");

        prepositions.add("in");
        prepositions.add("on");
        prepositions.add("at");
        prepositions.add("under");
        prepositions.add("above");
        prepositions.add("beside");
        prepositions.add("along");
        prepositions.add("over");

        adverbs.add("quickly");
        adverbs.add("slowly");
        adverbs.add("loudly");
        adverbs.add("happily");
        adverbs.add("sadly");
        adverbs.add("covertly");
        adverbs.add("brazenly");

        adjectives.add("blue");
        adjectives.add("wet");
        adjectives.add("green");
        adjectives.add("dry");
        adjectives.add("loud");
        adjectives.add("slow");
        adjectives.add("happy");
        adjectives.add("quick");
    }
    /**
     * Checks if the given string starts with a vowel.
     * This method checks if the first character of the string is a vowel (i.e., 'a', 'e', 'i', 'o', or 'u').
     * It assumes that the input string is not empty and contains at least one character.
     *
     * @param s The string to be checked.
     * @return true if the first character of the string is a vowel, false otherwise.
     * @throws NullPointerException if the input string is null.
     */
    private boolean isVowel(String s) {
        char c = s.charAt(0);
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }

    /*
     * Creates a set of randomized, gramatically correct sentences
     * 
     * Ths method uses the previously initialized ArrayLists and generates sentences using the form:
     * {article}{noun}{adverb}{verb}{preposition}{article}{adjective}{noun}
     * It assumes a positive input value.
     * 
     * @param i The number of sentences to generate
     * @return an arraylist with the generated sentences
     */
    public ArrayList<String> makeSentences(int i) {
        ArrayList<String> sentences = new ArrayList<String>();
        while (i-- > 0) {
            Collections.shuffle(articles);
            Collections.shuffle(nouns);
            Collections.shuffle(verbs);
            Collections.shuffle(prepositions);
            Collections.shuffle(adverbs);
            Collections.shuffle(adjectives);

            String article1 = articles.get(0);
            String noun1 = nouns.get(0);
            String adverb1 = adverbs.get(0);
            String verb1 = verbs.get(0);
            String preposition1 = prepositions.get(0);
            Collections.shuffle(nouns);

            String article2 = articles.get(1);
            String adjective = adjectives.get(0);
            String noun2 = nouns.get(1);

            String segment1 = (article1.equals("A") && !isVowel(noun1)) ? article1 + " " : article1 + "n ";
            String segment2 = noun1 + " " + adverb1 + " " + verb1 + " " + preposition1 + " ";
            String segment3 = (article2.equals("A") && !isVowel(noun2)) ? article2 + " ": article2 + "n ";
            String segment4 = noun2 + ".";

            
            String sentence = segment1 + segment2 + segment3.toLowerCase() + segment4;
            //my ternary operators are acting weird so here's a hacky way to circumvent that
            sentence = sentence.replaceAll("then", "the");
            sentence = sentence.replaceAll("Then", "The");
            sentences.add(sentence);
        }
        return sentences;
    }
}