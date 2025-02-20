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
}