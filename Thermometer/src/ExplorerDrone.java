/*
 * Jason Cameron
 * Ms. Krasteva
 * 2024-02-18
 * This class is a blueprint for an explorer drone object. It extends the SentientDrone class and has additional attributes for exploration range, sensor package, onboard "AI" version, base station, and color scheme.
 */
public class ExplorerDrone extends SentientDrone {
    // Immutable attributes
    private final double explorationRange;
    private final String sensorPackage;
    private final String onboardAIversion;
    private final String baseStation;
    private final String colorScheme;

    // Mutable attributes
    private String currentScanResult;
    private String mapData;
    private double speed;
    private String communicationStatus;
    private String currentTask;

    public ExplorerDrone() {
        super();
        this.explorationRange = 100.0;
        this.sensorPackage = "StandardSensors";
        this.onboardAIversion = "AI v1.0";
        this.baseStation = "DefaultBase";
        this.colorScheme = "White";
        this.currentScanResult = "None";
        this.mapData = "No Data";
        this.speed = 0.0;
        this.communicationStatus = "Disconnected";
        this.currentTask = "Idle";
    }

    public ExplorerDrone(String manufacturer, String model, String serialNumber, int productionYear, String droneID,
                         double explorationRange, String sensorPackage, String onboardAIversion, String baseStation,
                         String colorScheme, String currentScanResult, String mapData, double speed,
                         String communicationStatus, String currentTask) {
        super(manufacturer, model, serialNumber, productionYear, droneID, 100, 0.0, "Base", "None");
        this.explorationRange = explorationRange;
        this.sensorPackage = sensorPackage;
        this.onboardAIversion = onboardAIversion;
        this.baseStation = baseStation;
        this.colorScheme = colorScheme;
        this.currentScanResult = currentScanResult;
        this.mapData = mapData;
        this.speed = speed;
        this.communicationStatus = communicationStatus;
        this.currentTask = currentTask;
    }

    public void setCurrentScanResult(String result) {
        this.currentScanResult = result;
    }

    public void setMapData(String data) {
        this.mapData = data;
    }

    public void setSpeed(double speed) {
        if (speed >= 0)
            this.speed = speed;
    }

    public void setCommunicationStatus(String status) {
        this.communicationStatus = status;
    }

    public void setCurrentTask(String task) {
        this.currentTask = task;
    }

    public double getExplorationRange() {
        return explorationRange;
    }

    public String getSensorPackage() {
        return sensorPackage;
    }

    public String getOnboardAIversion() {
        return onboardAIversion;
    }

    public String getBaseStation() {
        return baseStation;
    }

    public String getColorScheme() {
        return colorScheme;
    }

    public void startExploration() {
        setCurrentTask("Exploring");
        setSpeed(30.0);
    }

    public void performTerrainScan() {
        setCurrentScanResult("Terrain features detected");
        System.out.println("Scan result: " + currentScanResult);
    }

    public void updateMap() {
        setMapData("Map updated with latest scan data");
        System.out.println("Map data: " + mapData);
    }

    public void adjustSpeed(double newSpeed) {
        setSpeed(newSpeed);
        System.out.println("Speed adjusted to " + speed + " km/h.");
    }

    public void emergencyReturn() {
        setCurrentTask("Emergency Return");
        setSpeed(50.0);
        System.out.println("Emergency return initiated.");
    }

    public void displayExplorerInfo() {
        super.displayInfo();
        System.out.println("Exploration Range: " + explorationRange);
        System.out.println("Sensor Package: " + sensorPackage);
        System.out.println("Onboard AI Version: " + onboardAIversion);
        System.out.println("Base Station: " + baseStation);
        System.out.println("Color Scheme: " + colorScheme);
        System.out.println("Current Scan Result: " + currentScanResult);
        System.out.println("Map Data: " + mapData);
        System.out.println("Speed: " + speed);
        System.out.println("Communication Status: " + communicationStatus);
        System.out.println("Current Task: " + currentTask);
    }
}
