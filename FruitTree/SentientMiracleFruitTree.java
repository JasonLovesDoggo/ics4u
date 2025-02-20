import java.util.Objects;

class SentientMiracleFruitTree extends MiracleFruitTree{
    // changeable attributes
    boolean defenseMode;
    int emotionalState;
    boolean isAwake;
    int consciousness;
    int defense;

    // unchangeable attributes
    double telepathicRange;
    int natureAffinity;
    String favouriteVisitor;
    int wakeFactor;
    int emotionalStability;

    // constructor
    public SentientMiracleFruitTree(){
        super(new TreeHelper());
        defenseMode = false;
        emotionalState = 0;
        isAwake = true;
        consciousness = 10;
        defense = 3;

        telepathicRange = 10;
        natureAffinity = 4;
        favouriteVisitor = "Rabbit";
        wakeFactor = 2;
        emotionalStability = 2;
    }

    public SentientMiracleFruitTree(TreeHelper treeHelper){
        super(treeHelper);
        defenseMode = false;
        emotionalState = 0;
        isAwake = true;
        consciousness = 10;
        defense = 3;

        telepathicRange = 10.0;
        natureAffinity = 4;
        favouriteVisitor = "Rabbit";
        wakeFactor = 2;
        emotionalStability = 2;
    }

    public SentientMiracleFruitTree(String name, String location, TreeHelper treeHelper, int es, int def, double range, int natAff, String favVisit, int wF, int eStab){
        super(name, location, treeHelper);
        defenseMode = false;
        emotionalState = es;
        isAwake = true;
        consciousness = 10;
        defense = def;
        telepathicRange = range;
        natureAffinity = natAff;
        favouriteVisitor = favVisit;
        wakeFactor = wF;
        emotionalStability = eStab;
    }

    public void wake(int conscInc){
        consciousness += conscInc;
        consciousness -= wakeFactor;
        if (consciousness >= 0){
            isAwake = true;
        }
    }

    public void rest(){
        consciousness = -10;
        isAwake = false;
        emotionalState += 2;
    }

    public void changeEmotionalState(int a){
        emotionalState += a;
        if (a >= 0){
            emotionalState -= emotionalStability;
        }
        else {
            emotionalState += emotionalStability;
        }
    }

    public void suddenWake(){
        isAwake = true;
        consciousness = 40;
        emotionalState = -40;
    }

    public void lowerDefense(int defDec){
        defense -= defDec;
    }

    public int getDefense(){
        return defense;
    }

    public String checkConsciousness(){
        if (consciousness < -10)
            return "in deep sleep";
        else if (consciousness < 0)
            return "asleep";
        else if (consciousness < 10)
            return "barely awake";
        else
            return "completely awake";
    }

    public String getEmotionalState(){
        if (emotionalState < -10)
            return "infuriated";
        else if (emotionalState < 0)
            return "annoyed";
        else if (emotionalState < 10)
            return "mildly happy";
        else
            return "elated";
    }

    public String getFavouriteVisitor(){
        return favouriteVisitor;
    }

    public double checkTelepathicRange(){
        return telepathicRange;
    }

    public void callOnNature(){ // Calls on nature to help defend it, sharply increasing defense
        defense += 50;
        defense += natureAffinity; // more effective with higher affinity with nature
        defense -= emotionalState; // more effective if infuriated
        defenseMode = true;
    }

    public void calmDown(){
        defenseMode = false;
        defense -= 50;
        defense += emotionalState; // some of the nature borrowed stays with it if it is happy and pleasant to be around
        // the natureAffinity stays with it
    }

    public void socialize(String visitor){
        if (Objects.equals(visitor, favouriteVisitor))
            emotionalState += 30;
        else {
        emotionalState += 5;
        emotionalState -= emotionalStability; //  goes against the change to emotion like friction to applied force
    }}

    public void ventAnger(){
        if(Objects.equals(getEmotionalState(), "infurated")){ // only works if angry
            emotionalState += (int) telepathicRange; // higher telepathicRange, more beings it can vent its anger to.
            emotionalState -= emotionalStability;
        }
    }

    public boolean achieveEnlightment(){
        if(emotionalState >= 30 && consciousness >= 30){
            emotionalState = 100;
            consciousness = 100;
            return true; // enlightment successfully achieved, tree is now forever super happy and fully awake
        }
        else{
            emotionalState -= 20;
            rest();
            return false; // enlightment failed, tree is miserable and falls back into deepSleep
        }
    }
    public boolean test() {
        boolean allTestsPassed = true;
        System.out.println("\nStarting SentientMiracleFruitTree tests...\n");

        // Test 1: Initial state
        System.out.println("Test 1: Checking initial state");
        if (consciousness == 10 && isAwake && !defenseMode && defense == 3) {
            System.out.println("✓ Initial state test passed");
        } else {
            System.out.println("✗ Initial state test failed");
            allTestsPassed = false;
        }

        // Test 2: Rest and wake cycle
        System.out.println("\nTest 2: Testing rest and wake cycle");
        rest();
        if (!isAwake && consciousness == -10) {
            wake(15);
            if (isAwake && consciousness > 0) {
                System.out.println("✓ Rest and wake cycle test passed");
            } else {
                System.out.println("✗ Rest and wake cycle test failed (wake phase)");
                allTestsPassed = false;
            }
        } else {
            System.out.println("✗ Rest and wake cycle test failed (rest phase)");
            allTestsPassed = false;
        }

        // Test 3: Emotional state changes
        System.out.println("\nTest 3: Testing emotional state changes");
        String initialState = getEmotionalState();
        changeEmotionalState(20);
        if (!getEmotionalState().equals(initialState)) {
            System.out.println("✓ Emotional state change test passed");
        } else {
            System.out.println("✗ Emotional state change test failed");
            allTestsPassed = false;
        }

        // Test 4: Defense system
        System.out.println("\nTest 4: Testing defense system");
        int initialDefense = defense;
        callOnNature();
        if (defenseMode && defense > initialDefense) {
            calmDown();
            if (!defenseMode && defense < initialDefense + 50) {
                System.out.println("✓ Defense system test passed");
            } else {
                System.out.println("✗ Defense system test failed (calm down phase)");
                allTestsPassed = false;
            }
        } else {
            System.out.println("✗ Defense system test failed (defense mode phase)");
            allTestsPassed = false;
        }

        // Test 5: Socialization
        System.out.println("\nTest 5: Testing socialization");
        int emotionBeforeSocial = emotionalState;
        socialize(favouriteVisitor);
        if (emotionalState > emotionBeforeSocial) {
            System.out.println("✓ Socialization test passed");
        } else {
            System.out.println("✗ Socialization test failed");
            allTestsPassed = false;
        }

        // Test 6: Enlightenment attempt
        System.out.println("\nTest 6: Testing enlightenment mechanics");
        emotionalState = 40;
        consciousness = 40;
        boolean enlightenmentResult = achieveEnlightment();
        if (enlightenmentResult && emotionalState == 100 && consciousness == 100) {
            System.out.println("✓ Enlightenment success test passed");
        } else {
            System.out.println("✗ Enlightenment test failed");
            allTestsPassed = false;
        }

        // Final results
        System.out.println("\nSentient Tree Test Results Summary:");
        System.out.println(allTestsPassed ? "All tests passed! ✓" : "Some tests failed! ✗");

        return allTestsPassed;
    }
}