public class Main {
    public static void main(String[] args) {
        System.out.println("=== Sentient Drone Demo ===");

        // Default SentientDrone
        SentientDrone defaultDrone = new SentientDrone();
        System.out.println("Manufacturer: " + defaultDrone.getManufacturer());
        System.out.println("Drone ID: " + defaultDrone.getDroneID());
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

        // Parameterized SentientDrone
        SentientDrone customDrone = new SentientDrone("AeroTech", "X-Drone", "SN12345", 2023, "DR-001",
                90, 0.0, "Hangar", "Ready");
        System.out.println("\nCustom Drone Manufacturer: " + customDrone.getManufacturer());
        customDrone.setBatteryLevel(100);
        customDrone.launch();
        customDrone.ascend(200.0);
        customDrone.performScan();
        customDrone.descend(150.0);
        customDrone.land();

        System.out.println("\n=== Explorer Drone Demo ===");

        // Parameterized ExplorerDrone
        ExplorerDrone explorer = new ExplorerDrone("AeroTech", "Explorer-X", "SN54321", 2024, "DR-EX-001",
                150.0, "AdvancedSensors", "AI v2.5", "MainBase", "StealthBlack",
                "No Data", "Map Empty", 0.0, "Connected", "Idle");
        System.out.println("Base Station: " + explorer.getBaseStation());
        explorer.startExploration();
        explorer.performTerrainScan();
        explorer.adjustSpeed(60.0);
        explorer.updateMap();
        explorer.emergencyReturn();
    }
}
