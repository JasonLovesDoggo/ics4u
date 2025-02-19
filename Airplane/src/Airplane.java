/*
* Jason Cameron
* Ms. Krasteva
* 2024-02-18
* This class is a blueprint for an airplane object. It has attributes for gas level, landing gear status, door status, and number of passengers.
*/

public class Airplane {
    // Private attributes
    private int gasLevel;
    private String landingGear;
    private String doorStatus;
    private int passengers;

    public Airplane() {
        gasLevel = 100;
        landingGear = "Down";
        doorStatus = "Open";
        passengers = 100;
    }

    // Methods
    public void openDoor() {
        doorStatus = "Open";
    }

    public void closeDoor() {
        doorStatus = "Closed";
    }

    public void fillUp() {
        gasLevel = 100;
    }

    public void takeOff() {
        gasLevel -= 30;
    }

    public void doneTakeOff() {
        landingGear = "Up";
    }

    public void normalFlight(int gasUsed) {
        gasLevel -= gasUsed;
    }

    public void prepLanding() {
        landingGear = "Down";
    }

    public void land() {
        gasLevel -= 15;
    }

    public void loadPass(int num) {
        passengers += num;
    }

    public void unloadPass() {
        passengers = 0;
    }

    public int getGasLevel() {
        return gasLevel;
    }

    public String getDoorStatus() {
        return doorStatus;
    }

    public int getPassengers() {
        return passengers;
    }

    public String getLandingGear() {
        // The assignment said to return an Integer, but the attribute is a String so I changed it to return a String.
        return landingGear;
    }
}
