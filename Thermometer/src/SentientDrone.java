/*
 * Jason Cameron
 * Ms. Krasteva
 * 2024-02-18
 * This class is a blueprint for a sentient drone object. It has attributes for manufacturer, model, serial number, production year, drone ID, battery level, current altitude, current location, operational status, and mission status.
 */
public class SentientDrone {
    // Immutable attributes
    private final String manufacturer;
    private final String model;
    private final String serialNumber;
    private final int productionYear;
    private final String droneID;

    // Mutable attributes
    private int batteryLevel;
    private double currentAltitude;
    private String currentLocation;
    private String operationalStatus;
    private String missionStatus;

    public SentientDrone() {
        this.manufacturer = "DefaultManufacturer";
        this.model = "DefaultModel";
        this.serialNumber = "DEFAULT_SN";
        this.productionYear = 2020;
        this.droneID = "DR-000";
        this.batteryLevel = 50;
        this.currentAltitude = 0.0;
        this.currentLocation = "Unknown";
        this.operationalStatus = "Offline";
        this.missionStatus = "None";
    }

    public SentientDrone(String manufacturer, String model, String serialNumber, int productionYear, String droneID,
                         int batteryLevel, double currentAltitude, String currentLocation, String missionStatus) {
        this.manufacturer = manufacturer;
        this.model = model;
        this.serialNumber = serialNumber;
        this.productionYear = productionYear;
        this.droneID = droneID;
        this.batteryLevel = batteryLevel;
        this.currentAltitude = currentAltitude;
        this.currentLocation = currentLocation;
        this.operationalStatus = "Offline";
        this.missionStatus = missionStatus;
    }

    public void setBatteryLevel(int batteryLevel) {
        if (batteryLevel >= 0 && batteryLevel <= 100)
            this.batteryLevel = batteryLevel;
    }

    public void setCurrentAltitude(double altitude) {
        if (altitude >= 0)
            this.currentAltitude = altitude;
    }

    public void setCurrentLocation(String location) {
        this.currentLocation = location;
    }

    public void setOperationalStatus(String status) {
        this.operationalStatus = status;
    }

    public void setMissionStatus(String status) {
        this.missionStatus = status;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String getModel() {
        return model;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public int getProductionYear() {
        return productionYear;
    }

    public String getDroneID() {
        return droneID;
    }

    public void launch() {
        setOperationalStatus("Active");
        System.out.println("Drone launched.");
    }

    public void land() {
        setCurrentAltitude(0);
        setOperationalStatus("Idle");
        System.out.println("Drone landed.");
    }

    public void ascend(double delta) {
        setCurrentAltitude(currentAltitude + delta);
        System.out.println("Ascending. Altitude: " + currentAltitude);
    }

    public void descend(double delta) {
        double newAltitude = currentAltitude - delta;
        if (newAltitude < 0) newAltitude = 0;
        setCurrentAltitude(newAltitude);
        System.out.println("Descending. Altitude: " + currentAltitude);
    }

    public void performScan() {
        System.out.println("Scanning at location: " + currentLocation);
    }

    public void displayInfo() {
        System.out.println("Manufacturer: " + manufacturer);
        System.out.println("Model: " + model);
        System.out.println("Serial Number: " + serialNumber);
        System.out.println("Production Year: " + productionYear);
        System.out.println("Drone ID: " + droneID);
        System.out.println("Battery Level: " + batteryLevel);
        System.out.println("Current Altitude: " + currentAltitude);
        System.out.println("Current Location: " + currentLocation);
        System.out.println("Operational Status: " + operationalStatus);
        System.out.println("Mission Status: " + missionStatus);
    }
}
