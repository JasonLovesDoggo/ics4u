public class Main {
    public static void main(String[] args) {
        // Demonstrate basic thermometer
        System.out.println("=== Basic Thermometer Demo ===");

        // Test default constructor
        Thermometer defaultTherm = new Thermometer();
        System.out.println("Default Thermometer Specs: " + defaultTherm.getSpecifications());

        // Test parameterized constructor and all features
        Thermometer basicTherm = new Thermometer(
                "ThermoTech", "Basic-100", -30.0, 100.0,
                "BT001", "Living Room"
        );

        // Demonstrate all attribute changes
        System.out.println("\nTesting all basic thermometer features:");
        System.out.println("Initial status: " + basicTherm.getStatus());

        basicTherm.togglePower();
        System.out.println("After power on: " + basicTherm.getStatus());

        basicTherm.setCurrentTemp(22.5);
        System.out.println("Current temperature: " + basicTherm.getCurrentTemp());

        basicTherm.setLocation("Kitchen");
        System.out.println("New location: " + basicTherm.getLocation());

        basicTherm.setAlarmTemp(25.0);
        System.out.println("Alarm temperature set to: " + basicTherm.getAlarmTemp());

        basicTherm.toggleAlarm();
        System.out.println("Alarm enabled: " + basicTherm.isAlarmEnabled());

        // Demonstrate all behaviors
        System.out.println("\nTesting temperature checks:");
        System.out.println("Is 15.0°C in range? " + basicTherm.isInRange(15.0));
        System.out.println(basicTherm.displayTemperature());        // Default Celsius
        System.out.println(basicTherm.displayTemperature("F"));     // Fahrenheit
        System.out.println(basicTherm.displayTemperature("K"));     // Kelvin
        System.out.println(basicTherm.checkAlarm());

        // Demonstrate Smart Thermometer
        System.out.println("\n=== Smart Thermometer Demo ===");

        // Test default constructor
        SmartThermometer _ = new SmartThermometer();
        System.out.println("Default Smart Thermometer created");

        // Test parameterized constructor and all features
        SmartThermometer smartTherm = new SmartThermometer(
                "ThermoTech", "Smart-200", -40.0, 120.0,
                "ST001", "Office", "HomeWiFi", "AA:BB:CC:DD:EE:FF"
        );

        // Demonstrate all attribute changes
        System.out.println("\nTesting all smart thermometer features:");
        smartTherm.togglePower();
        smartTherm.setCurrentTemp(23.5);

        smartTherm.connectWifi();
        System.out.println("WiFi connected: " + smartTherm.isWifiConnected());

        smartTherm.setBatteryLevel(85);
        System.out.println("Battery level: " + smartTherm.getBatteryLevel());

        smartTherm.setDisplayMode("Fahrenheit");
        System.out.println("Display mode: " + smartTherm.getDisplayMode());

        smartTherm.setAutoUpdate(true);
        System.out.println("Auto-update enabled: " + smartTherm.isAutoUpdateEnabled());

        smartTherm.setScheduledMode("Night");
        System.out.println("Scheduled mode: " + smartTherm.getScheduledMode());

        // Demonstrate all behaviors
        System.out.println("\nTesting smart features:");
        smartTherm.setDisplayMode("Fahrenheit");
        System.out.println(smartTherm.displayTemperature());        // Uses display mode (Fahrenheit)
        System.out.println(smartTherm.displayTemperature("C"));     // Override to Celsius
        System.out.println(smartTherm.displayTemperature("K"));     // Kelvin
        System.out.println(smartTherm.displayTemperature("default")); // Uses display mode again
        System.out.println(smartTherm.getBatteryStatus());
        System.out.println(smartTherm.getNetworkStatus());
        System.out.println(smartTherm.checkUpdates());
        System.out.println(smartTherm.getDeviceInfo());

        // Demonstrate inherited features still work
        System.out.println("\nTesting inherited features:");
        smartTherm.setAlarmTemp(30.0);
        smartTherm.toggleAlarm();
        System.out.println(smartTherm.checkAlarm());
        System.out.println(smartTherm.getSpecifications());
    }
}