/*
 * Jason Cameron
 * Ms. Krasteva
 * 2024-02-18
 * This class is a test class for the Airplane class. It creates an airplane object and simulates flight operations.
 */
public class AirplaneTest {
    public static void main(String[] args) {
        Airplane plane = new Airplane();

        // Display initial state
        System.out.println("Initial Gas Level: " + plane.getGasLevel());
        System.out.println("Initial Door Status: " + plane.getDoorStatus());
        System.out.println("Initial Landing Gear: " + plane.getLandingGear());
        System.out.println("Initial Passengers: " + plane.getPassengers());

        // Simulate flight operations
        plane.closeDoor();
        plane.takeOff();
        plane.doneTakeOff();
        plane.normalFlight(50);
        plane.prepLanding();
        plane.land();
        plane.unloadPass();

        // Display state after operations
        System.out.println("\nAfter Flight Operations:");
        System.out.println("Gas Level: " + plane.getGasLevel());
        System.out.println("Door Status: " + plane.getDoorStatus());
        System.out.println("Landing Gear: " + plane.getLandingGear());
        System.out.println("Passengers: " + plane.getPassengers());
    }
}
