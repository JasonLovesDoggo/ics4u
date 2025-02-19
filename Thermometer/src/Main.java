/*
 * Jason Cameron
 * Ms. Krasteva
 * 2024-02-18
 * This class is a test class for the SentientDrone and ExplorerDrone classes. It creates drone objects and simulates operations.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("=== Sentient Drone Demo ===");

        SentientDrone defaultDrone = new SentientDrone();
        defaultDrone.displayInfo();
        defaultDrone.setBatteryLevel(80);
        defaultDrone.setCurrentAltitude(100.0);
        defaultDrone.setCurrentLocation("Base");
        defaultDrone.setOperationalStatus("Idle");
        defaultDrone.setMissionStatus("Standby");
        defaultDrone.launch();
        defaultDrone.ascend(50.0);
        defaultDrone.performScan();
        defaultDrone.descend(30.0);
        defaultDrone.land();
        System.out.println("\nAfter operations:");
        defaultDrone.displayInfo();

        SentientDrone customDrone = new SentientDrone("AeroTech", "X-Drone", "SN12345", 2023, "DR-001",
                90, 0.0, "Hangar", "Ready");
        customDrone.displayInfo();
        customDrone.setBatteryLevel(100);
        customDrone.launch();
        customDrone.ascend(200.0);
        customDrone.performScan();
        customDrone.descend(150.0);
        customDrone.land();
        System.out.println("\nAfter operations:");
        customDrone.displayInfo();

        System.out.println("\n=== Explorer Drone Demo ===");

        ExplorerDrone explorer = new ExplorerDrone("AeroTech", "Explorer-X", "SN54321", 2024, "DR-EX-001",
                150.0, "AdvancedSensors", "AI v2.5", "MainBase", "StealthBlack",
                "No Data", "Map Empty", 0.0, "Connected", "Idle");
        explorer.displayExplorerInfo();
        explorer.startExploration();
        System.out.println("Exploration started.");
        explorer.performTerrainScan();
        explorer.adjustSpeed(60.0);
        explorer.updateMap();
        explorer.emergencyReturn();
        System.out.println("\nAfter operations:");
        explorer.displayExplorerInfo();
    }
}
