public class Tester {
    public static void main(String[] args) {
        UniversityRanker universityRanker = new UniversityRanker("./data.txt");
        System.out.println(universityRanker.loadData());
        universityRanker.start();

    }
}
