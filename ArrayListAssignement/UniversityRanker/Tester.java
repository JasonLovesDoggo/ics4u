package UniversityRanker;

/**
 * @author Jason Cameron
 * ICS4U
 * Mrs. Krasteva
 * Tester class that initializes and runs the University Ranker application
 */
public class Tester {
    /**
     * Main method that serves as the entry point for the application.
     * Creates a UniversityRanker object, loads data, and starts the application.
     *
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        UniversityRanker universityRanker = new UniversityRanker("data.txt");
        System.out.println("Loaded " + universityRanker.loadData() + " universities from data.txt");
        universityRanker.start();
    }
}