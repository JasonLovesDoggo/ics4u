// Thermometer.java (Superclass)
public class Thermometer {
    // Immutable attributes
    private final String manufacturer;
    private final String model;
    private final double minTemp;
    private final double maxTemp;
    private final String serialNumber;

    // Mutable attributes
    private double currentTemp;
    private String location;
    private boolean isOn;
    private double alarmTemp;
    private boolean alarmEnabled;

    // Default constructor
    public Thermometer() {
        this.manufacturer = "Generic";
        this.model = "Basic-100";
        this.minTemp = -50.0;
        this.maxTemp = 150.0;
        this.serialNumber = "DEFAULT001";
        this.currentTemp = 20.0;
        this.location = "Unknown";
        this.isOn = false;
        this.alarmTemp = 30.0;
        this.alarmEnabled = false;
    }

    // Parameterized constructor
    public Thermometer(String manufacturer, String model, double minTemp,
                       double maxTemp, String serialNumber, String location) {
        this.manufacturer = manufacturer;
        this.model = model;
        this.minTemp = minTemp;
        this.maxTemp = maxTemp;
        this.serialNumber = serialNumber;
        this.currentTemp = 20.0;
        this.location = location;
        this.isOn = false;
        this.alarmTemp = 30.0;
        this.alarmEnabled = false;
    }

    // Behaviors - Changes of attributes
    public void setCurrentTemp(double temp) {
        if (temp >= minTemp && temp <= maxTemp) {
            this.currentTemp = temp;
        }
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void togglePower() {
        this.isOn = !this.isOn;
    }

    public void setAlarmTemp(double temp) {
        this.alarmTemp = temp;
    }

    public void toggleAlarm() {
        this.alarmEnabled = !this.alarmEnabled;
    }

    // Behaviors - Access to attributes
    public double getCurrentTemp() {
        return currentTemp;
    }

    public String getLocation() {
        return location;
    }

    public boolean isPoweredOn() {
        return isOn;
    }

    public double getAlarmTemp() {
        return alarmTemp;
    }

    public boolean isAlarmEnabled() {
        return alarmEnabled;
    }

    // Behaviors - Changes of behavior
    public String checkAlarm() {
        if (!alarmEnabled) return "Alarm is disabled";
        return currentTemp > alarmTemp ? "ALARM: Temperature too high!" : "Temperature normal";
    }

    public String displayTemperature(String unit) {
        if (!isOn) return "Thermometer is off";

        double temp = currentTemp;
        return convertUnit(unit, temp);
    }

    static String convertUnit(String unit, double temp) {
        if (unit.equalsIgnoreCase("F") || unit.equalsIgnoreCase("Fahrenheit")) {
            temp = (temp * 9/5) + 32;
            return String.format("Current temperature: %.1f°F", temp);
        } else if (unit.equalsIgnoreCase("K") || unit.equalsIgnoreCase("Kelvin")) {
            temp = temp + 273.15;
            return String.format("Current temperature: %.1f K", temp);
        }
        return String.format("Current temperature: %.1f°C", temp);
    }

    // Overloaded method for backward compatibility
    public String displayTemperature() {
        return displayTemperature("C");
    }


    public String getStatus() {
        if (!isOn) return "Powered Off";
        return "Running normally";
    }

    public boolean isInRange(double temp) {
        return temp >= minTemp && temp <= maxTemp;
    }

    public String getSpecifications() {
        return String.format("Model: %s, Range: %.1f°C to %.1f°C",
                model, minTemp, maxTemp);
    }
}

