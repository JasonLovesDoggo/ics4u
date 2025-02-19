// SmartThermometer.java (Subclass)
public class SmartThermometer extends Thermometer {
    // Immutable attributes
    private final String wifiSSID;
    private final String macAddress;
    private final String firmwareVersion;
    private final int batteryCapacity;
    private final String displayType;

    // Mutable attributes
    private boolean wifiConnected;
    private int batteryLevel;
    private String displayMode;
    private boolean autoUpdate;
    private String scheduledMode;

    // Default constructor
    public SmartThermometer() {
        super();
        this.wifiSSID = "DEFAULT_WIFI";
        this.macAddress = "00:00:00:00:00:00";
        this.firmwareVersion = "1.0.0";
        this.batteryCapacity = 2000;
        this.displayType = "LCD";
        this.wifiConnected = false;
        this.batteryLevel = 100;
        this.displayMode = "Celsius";
        this.autoUpdate = true;
        this.scheduledMode = "Normal";
    }

    // Parameterized constructor
    public SmartThermometer(String manufacturer, String model, double minTemp,
                            double maxTemp, String serialNumber, String location,
                            String wifiSSID, String macAddress) {
        super(manufacturer, model, minTemp, maxTemp, serialNumber, location);
        this.wifiSSID = wifiSSID;
        this.macAddress = macAddress;
        this.firmwareVersion = "1.0.0";
        this.batteryCapacity = 2000;
        this.displayType = "LCD";
        this.wifiConnected = false;
        this.batteryLevel = 100;
        this.displayMode = "Celsius";
        this.autoUpdate = true;
        this.scheduledMode = "Normal";
    }

    // Behaviors - Changes of attributes
    public void connectWifi() {
        this.wifiConnected = true;
    }

    public void setBatteryLevel(int level) {
        if (level >= 0 && level <= 100) {
            this.batteryLevel = level;
        }
    }

    public void setDisplayMode(String mode) {
        if (mode.equals("Celsius") || mode.equals("Fahrenheit")) {
            this.displayMode = mode;
        }
    }

    public void setAutoUpdate(boolean enabled) {
        this.autoUpdate = enabled;
    }

    public void setScheduledMode(String mode) {
        this.scheduledMode = mode;
    }

    // Behaviors - Access to attributes
    public boolean isWifiConnected() {
        return wifiConnected;
    }

    public int getBatteryLevel() {
        return batteryLevel;
    }

    public String getDisplayMode() {
        return displayMode;
    }

    public boolean isAutoUpdateEnabled() {
        return autoUpdate;
    }

    public String getScheduledMode() {
        return scheduledMode;
    }

    // Behaviors - Changes of behavior
    @Override
    public String displayTemperature(String unit) {
        if (!isPoweredOn()) return "Thermometer is off";

        double temp = getCurrentTemp();
        // If display mode is set, use it unless explicitly overridden by parameter
        if (unit.equals("default")) {
            unit = displayMode;
        }

        return convertUnit(unit, temp); // Uses the inherited method from Thermometer
    }

    // Overloaded method that uses the display mode setting
    @Override
    public String displayTemperature() {
        return displayTemperature("default");
    }


    public String getBatteryStatus() {
        if (batteryLevel <= 20) return "Low Battery!";
        return String.format("Battery Level: %d%%", batteryLevel);
    }

    public String getNetworkStatus() {
        return wifiConnected ?
                "Connected to: " + wifiSSID :
                "Not connected to WiFi";
    }

    public String checkUpdates() {
        if (!wifiConnected) return "Cannot check updates: No WiFi connection";
        return "Firmware version " + firmwareVersion + " is up to date";
    }

    public String getDeviceInfo() {
        return String.format("MAC: %s, Firmware: %s, Battery: %d%%",
                macAddress, firmwareVersion, batteryLevel);
    }
}
