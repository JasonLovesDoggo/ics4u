import java.io.*;
import java.nio.charset.StandardCharsets; // For UTF-8
import java.util.*;
import java.text.NumberFormat;

// Standard Class Definition for Country (Replaces record)
final class Country {
    private final String name;
    private final String capital;
    private final String area;
    private final long population;

    /**
     * Constructs a new Country object.
     * @param name Country name
     * @param capital Capital city name
     * @param area Area as a String
     * @param population Population as a long
     */
    public Country(String name, String capital, String area, long population) {
        // Use trim() during construction for consistency
        this.name = (name != null) ? name.trim() : "";
        this.capital = (capital != null) ? capital.trim() : "";
        this.area = (area != null) ? area.trim() : "";
        this.population = population;
    }

    // Getter methods
    public String getName() { return name; }
    public String getCapital() { return capital; }
    public String getArea() { return area; }
    public long getPopulation() { return population; }

    /** Gets safe, non-empty country name. */
    public String getNameSafe() {
        return (!name.isEmpty()) ? name : "Unknown Country";
    }

    /** Gets safe, non-empty capital name, returning "N/A" if blank. */
    public String getCapitalSafe() {
        return (!capital.isEmpty()) ? capital : "N/A";
    }

    // Optional: Override toString for debugging
    @Override
    public String toString() {
        return "Country{" +
                "name='" + name + '\'' +
                ", capital='" + capital + '\'' +
                ", area='" + area + '\'' +
                ", population=" + population +
                '}';
    }
}


/**
 * Sorts country data read from a file based on country name and population.
 * Uses a hardcoded list of known country names for parsing.
 * <p>
 * Input: Reads data from "Countries-Populations.txt" (UTF-8 assumed).
 * </p>
 * <p>
 * Output: Sorted lists to "sortedByCountry.txt" and "sortedByPopulation.txt".
 * </p>
 *
 * @version 3.1 Standard Class + Refined Parsing/Encoding Checks
 * @author [Your Name/Group Names Here]
 */
public class CountrySorter {

    // --- Configuration Constants ---
    private static final String INPUT_FILE_NAME = "Countries-Population.txt";
    private static final String OUTPUT_FILE_SORTED_BY_NAME = "sortedByCountry.txt";
    private static final String OUTPUT_FILE_SORTED_BY_POPULATION = "sortedByPopulation.txt";
    private static final String OUTPUT_SEPARATOR = "\t\t\t";

    // --- Hardcoded List of Known Country Names ---
    // IMPORTANT: Manually sorted by length, DESCENDING.
    // ** Use exact characters matching the UTF-8 encoded input file. **
    // ** Ensure this .java file is also saved as UTF-8. **
    private static final List<String> KNOWN_COUNTRY_NAMES = Arrays.asList(
            "Congo, Democratic Republic of the",
            "St. Vincent and the Grenadines",
            "Central African Republic",
            "Bosnia and Herzegovina",
            "Antigua and Barbuda",
            "Dominican Republic",
            "Equatorial Guinea",
            "Papua New Guinea",
            "São Tomé and Príncipe", // Use correct UTF-8 characters
            "Trinidad and Tobago",
            "Congo, Republic of",
            "United Arab Emirates",
            "Marshall Islands",
            "Solomon Islands",
            "Burkina Faso",
            "Guinea-Bissau",
            "Czech Republic",
            "Côte d'Ivoire", // Use correct UTF-8 characters
            "Korea, North",
            "Korea, South",
            "New Zealand",
            "Saudi Arabia",
            "Sierra Leone",
            "South Africa",
            "United Kingdom",
            "United States",
            "Western Sahara",
            "Costa Rica",
            "El Salvador",
            "East Timor",
            "Kazakhstan",
            "Kyrgyzstan",
            "Madagascar",
            "Mauritania",
            "Montenegro",
            "Mozambique",
            "Bangladesh",
            "Azerbaijan",
            "Liechtenstein",
            "Luxembourg",
            "Macedonia",
            "Mauritius",
            "Micronesia",
            "Nicaragua",
            "Paraguay",
            "Philippines",
            "St. Kitts and Nevis",
            "San Marino",
            "Seychelles",
            "Singapore",
            "Slovakia",
            "Slovenia",
            "Sri Lanka",
            "Swaziland",
            "Switzerland",
            "Tajikistan",
            "Tanzania",
            "Turkmenistan",
            "Uzbekistan",
            "Vatican City",
            "Afghanistan",
            "Argentina",
            "Australia",
            "Barbados",
            "Belgium",
            "Cambodia",
            "Cameroon",
            "Colombia",
            "Djibouti",
            "Ethiopia",
            "Guatemala",
            "Indonesia",
            "Jerusalem",
            "Lithuania",
            "Malaysia",
            "Mongolia",
            "Pakistan",
            "Portugal",
            "St. Lucia",
            "Suriname",
            "Thailand",
            "Venezuela",
            "Zimbabwe",
            "Albania",
            "Algeria",
            "Andorra",
            "Armenia",
            "Austria",
            "Bahrain",
            "Belarus",
            "Belize",
            "Bolivia",
            "Botswana",
            "Brazil",
            "Brunei",
            "Bulgaria",
            "Burundi",
            "Canada",
            "Chile",
            "China",
            "Comoros",
            "Croatia",
            "Cyprus",
            "Denmark",
            "Dominica",
            "Ecuador",
            "Eritrea",
            "Estonia",
            "Finland",
            "France",
            "Georgia",
            "Germany",
            "Ghana", // Added Ghana
            "Grenada",
            "Honduras",
            "Hungary",
            "Iceland",
            "Ireland",
            "Israel",
            "Jamaica",
            "Jordan",
            "Kiribati",
            "Lebanon",
            "Lesotho",
            "Liberia",
            "Maldives",
            "Moldova",
            "Morocco",
            "Myanmar (Burma)",
            "Namibia",
            "Netherlands",
            "Nigeria",
            "Norway",
            "Palau",
            "Panama",
            "Romania",
            "Russia",
            "Rwanda",
            "Senegal",
            "Serbia",
            "Somalia",
            "Sweden",
            "Taiwan",
            "Tunisia",
            "Turkey",
            "Tuvalu",
            "Uganda",
            "Ukraine",
            "Uruguay",
            "Vanuatu",
            "Vietnam",
            "Yemen",
            "Zambia",
            "Angola",
            "Bahamas",
            "Benin",
            "Bhutan",
            "Cape Verde",
            "Chad",
            "Cuba",
            "Egypt",
            "Gabon",
            "Gambia",
            "Greece",
            "Guinea",
            "Guyana",
            "Haiti",
            "India",
            "Iran",
            "Iraq",
            "Italy",
            "Japan",
            "Kenya",
            "Kuwait",
            "Laos",   // Added Laos
            "Latvia",
            "Libya",
            "Malawi",
            "Mali",   // Added Mali
            "Malta",
            "Mexico",
            "Monaco",
            "Nauru",
            "Nepal",
            "Niger",
            "Oman",
            "Peru",
            "Poland",
            "Qatar",
            "Samoa",
            "Spain",
            "Sudan",
            "Syria",
            "Togo",
            "Tonga",
            "Fiji"
    );


    /**
     * Main method to execute the country sorting process.
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {

        // --- Parse countries using the hardcoded name list ---
        System.out.println("Parsing file using hardcoded country names...");
        List<Country> countries = parseCountriesUsingHardcodedNames(INPUT_FILE_NAME);
        System.out.println("Successfully parsed " + countries.size() + " countries.");

        if (countries.isEmpty()) {
            System.out.println("No country data was successfully parsed. Exiting.");
            return;
        }

        // --- B. Process the data and output ---

        // Sort by Country Name using the getter from the standard class
        System.out.println("Sorting countries by name...");
        countries.sort(Comparator.comparing(Country::getNameSafe));

        System.out.println("Writing sorted data to " + OUTPUT_FILE_SORTED_BY_NAME + "...");
        writeSortedFile(OUTPUT_FILE_SORTED_BY_NAME, countries);
        System.out.println("Successfully wrote sorted data by country name.");

        // Sort by Population using the getter from the standard class
        System.out.println("Sorting countries by population (descending)...");
        countries.sort(Comparator.comparingLong(Country::getPopulation).reversed());

        System.out.println("Writing sorted data to " + OUTPUT_FILE_SORTED_BY_POPULATION + "...");
        writeSortedFile(OUTPUT_FILE_SORTED_BY_POPULATION, countries);
        System.out.println("Successfully wrote sorted data by population.");

        System.out.println("\nProgram finished.");
    }


    /**
     * Reads the input file, parsing each line by identifying the longest
     * matching known country name from the hardcoded list.
     *
     * @param filename The input file path.
     * @return A List of parsed Country objects.
     */
    private static List<Country> parseCountriesUsingHardcodedNames(String filename) {
        List<Country> countries = new ArrayList<>();
        File inputFile = new File(filename);
        if (!inputFile.exists()) {
            System.err.println("Error: Input file not found: " + filename);
            return countries;
        }

        int lineCount = 0;
        // Ensure UTF-8 reading
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(inputFile), StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Handle potential Byte Order Mark (BOM)
                if (lineCount == 0 && line.startsWith("\uFEFF")) {
                    line = line.substring(1);
                }
                line = line.trim();
                lineCount++;
                if (line.isEmpty()) continue;

                // --- DEBUG --- (Remove later)
                // System.out.println("Processing Line " + lineCount + ": " + line);
                // --- END DEBUG ---

                try {
                    // 1. Isolate Population and Area/Name+Capital Part
                    int lastSpace = line.lastIndexOf(' ');
                    if (lastSpace == -1) throw new IllegalArgumentException("No space found");

                    String populationStr = line.substring(lastSpace + 1).replace(",", "");
                    long population = Long.parseLong(populationStr);

                    // Find end of country/capital part (more robustly)
                    // This is the index *before* the area string starts.
                    int nameCapEndIndex = -1;
                    for (int i = lastSpace - 1; i >= 0; i--) {
                        char c = line.charAt(i);
                        // If it's NOT a digit, comma, period, or a space immediately preceding the number part... it's the end of the name/cap
                        if (!Character.isDigit(c) && c != ',' && c != '.' && !(c == ' ' && i < lastSpace -1) ) {
                            nameCapEndIndex = i;
                            break;
                        }
                        // Special case: handle single space before area like "Monaco Monaco 1 32,543"
                        // if we hit the start of the string, nameCapEndIndex stays -1
                        if (i == 0 && (Character.isDigit(c) || c == '.')) { // Check if first char is part of area
                            nameCapEndIndex = -1; // Indicates area starts immediately
                            break;
                        }
                    }

                    String countryAndCapitalPart;
                    String areaStr;

                    if (nameCapEndIndex != -1) {
                        countryAndCapitalPart = line.substring(0, nameCapEndIndex + 1).trim();
                        areaStr = line.substring(nameCapEndIndex + 1, lastSpace).trim();
                    } else {
                        // This suggests the format might be "CountryOrArea AreaOrPop Pop" (e.g. Vatican)
                        // Or only "Country Pop"
                        int firstSpace = line.indexOf(' ');
                        if (firstSpace == lastSpace) { // Only one space in the whole line? -> "Name Pop"
                            countryAndCapitalPart = line.substring(0, firstSpace).trim();
                            areaStr = ""; // Assume no area if only one space
                        } else { // nameCapEndIndex was -1 but multiple spaces -> "Name Area Pop"?
                            countryAndCapitalPart = line.substring(0, firstSpace).trim(); // Assume first part is Name
                            areaStr = line.substring(firstSpace, lastSpace).trim(); // Assume middle is area
                            // This is a heuristic guess for cases like Vatican City
                        }
                    }

                    // --- DEBUG --- (Remove later)
                    // System.out.println("  -> Country/Cap Part: '" + countryAndCapitalPart + "'");
                    // System.out.println("  -> Area Part: '" + areaStr + "'");
                    // --- END DEBUG ---

                    // 2. Match Country Name (longest first from hardcoded list)
                    String foundCountryName = null;
                    String capitalName = ""; // Default to empty

                    for (String knownName : KNOWN_COUNTRY_NAMES) {
                        if (countryAndCapitalPart.equals(knownName)) {
                            foundCountryName = knownName;
                            capitalName = ""; // Explicitly no capital
                            break;
                        }
                        // Use startsWith but ensure it's followed by a space or it's the end of the string
                        if (countryAndCapitalPart.startsWith(knownName) &&
                                (countryAndCapitalPart.length() == knownName.length() ||
                                        countryAndCapitalPart.charAt(knownName.length()) == ' '))
                        {
                            foundCountryName = knownName;
                            capitalName = countryAndCapitalPart.substring(knownName.length()).trim();
                            break;
                        }
                    }

                    // 3. Handle parsing results / Create Country object
                    if (foundCountryName != null) {
                        // Refinement for Vatican City / Western Sahara where area might land in capitalName initially
                        if ((foundCountryName.equals("Vatican City") || foundCountryName.equals("Western Sahara"))
                                && capitalName.matches("^[\\d.,\\s]+$") && areaStr.isEmpty()) {
                            areaStr = capitalName; // Move the numeric-looking part to area
                            capitalName = "";
                        }

                        // --- DEBUG ---
                        // System.out.println("  ==> MATCH FOUND: Country='" + foundCountryName + "', Capital='" + capitalName + "'");
                        // --- END DEBUG ---

                        countries.add(new Country(foundCountryName, capitalName, areaStr, population));

                    } else {
                        System.err.println("Warning: Failed to identify known country name for: '" + countryAndCapitalPart + "' on line " + lineCount + ": '" + line + "'. Skipping.");
                    }

                } catch (NumberFormatException e) {
                    System.err.println("Error parsing population on line " + lineCount + ": '" + line + "'. Skipping. Error: " + e.getMessage());
                } catch (IllegalArgumentException | StringIndexOutOfBoundsException e) {
                    System.err.println("Error parsing line structure on line " + lineCount + ": '" + line + "'. Skipping. Error: " + e.getMessage());
                } catch (Exception e) { // Catch unexpected errors during parse
                    System.err.println("Unexpected error processing line " + lineCount + ": '" + line + "'. Skipping. Error: " + e.getMessage());
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file " + filename + ": " + e.getMessage());
            e.printStackTrace();
        }

        return countries;
    }


    /**
     * Writes the country data (name and population) from the list to the
     * specified file (UTF-8 encoded).
     * @param filename        Output file path.
     * @param sortedCountries List of Country objects (assumed sorted).
     */
    private static void writeSortedFile(String filename, List<Country> sortedCountries) {
        // Ensure UTF-8 writing
        try (PrintWriter writer = new PrintWriter(new OutputStreamWriter(new FileOutputStream(filename), StandardCharsets.UTF_8))) {
            NumberFormat numberFormatter = NumberFormat.getNumberInstance(Locale.US);

            for (Country country : sortedCountries) {
                String formattedPopulation = numberFormatter.format(country.getPopulation());
                String countryNameToWrite = country.getNameSafe(); // Use safe getter

                writer.printf("%s%s%s%n", countryNameToWrite, OUTPUT_SEPARATOR, formattedPopulation);
            }
        } catch (IOException e) {
            System.err.println("Error writing to file " + filename + ": " + e.getMessage());
            e.printStackTrace();
        }
    }
}