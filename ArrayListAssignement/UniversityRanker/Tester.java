package UniversityRanker;

public class Tester {
    public static void main(String[] args) {
        UniversityRanker universityRanker = new UniversityRanker("data.txt");
        System.out.println("Loaded " + universityRanker.loadData() + " universities from data.txt");
        universityRanker.start();

    }
}
