/*
* Author: Jason Cameron
* Date: 2025-03-31
* Teacher: Ms. Krasteva
* Description: This program reads a file containing country names, populations, and areas, and sorts the data by country name and population.
* */
import java.io.*;
import java.nio.charset.StandardCharsets; // Ensure UTF-8 handling
import java.util.*;
import java.text.NumberFormat; // For population formatting

public class CountrySorter {

    // --- Configuration ---
    private static final String INPUT_FILE_NAME = "Countries-Population.txt";
    private static final String OUTPUT_FILE_SORTED_BY_NAME = "sortedByCountry.txt";
    private static final String OUTPUT_FILE_SORTED_BY_POPULATION = "sortedByPopulation.txt";
    private static final String OUTPUT_SEPARATOR = "\t\t\t"; // Three tabs

    // --- Hardcoded List of Known Country Names ---
    // Use exact characters matching the UTF-8 encoded input file.
    private static final List<String> KNOWN_COUNTRY_NAMES = Arrays.asList(
            "Congo, Democratic Republic of the", "St. Vincent and the Grenadines", "Central African Republic",
            "Bosnia and Herzegovina", "São Tomé and Príncipe", "Antigua and Barbuda", "Dominican Republic", "Equatorial Guinea",
            "Papua New Guinea", "São Tomé and Príncipe", "Trinidad and Tobago", "Congo, Republic of",
            "United Arab Emirates", "Marshall Islands", "Solomon Islands", "Burkina Faso", "Guinea-Bissau",
            "Czech Republic", "Côte d'Ivoire", "Korea, North", "Korea, South", "New Zealand",
            "Saudi Arabia", "Sierra Leone", "South Africa", "United Kingdom", "United States",
            "Western Sahara", "Costa Rica", "El Salvador", "East Timor", "Kazakhstan", "Kyrgyzstan",
            "Madagascar", "Mauritania", "Montenegro", "Mozambique", "Bangladesh", "Azerbaijan",
            "Liechtenstein", "Luxembourg", "Macedonia", "Mauritius", "Micronesia", "Nicaragua",
            "Paraguay", "Philippines", "St. Kitts and Nevis", "San Marino", "Seychelles", "Singapore",
            "Slovakia", "Slovenia", "Sri Lanka", "Swaziland", "Switzerland", "Tajikistan",
            "Tanzania", "Turkmenistan", "Uzbekistan", "Vatican City", "Afghanistan", "Argentina",
            "Australia", "Barbados", "Belgium", "Cambodia", "Cameroon", "Colombia", "Djibouti",
            "Ethiopia", "Guatemala", "Indonesia", "Jerusalem", "Lithuania", "Malaysia", "Mongolia",
            "Pakistan", "Portugal", "St. Lucia", "Suriname", "Thailand", "Venezuela", "Zimbabwe",
            "Albania", "Algeria", "Andorra", "Armenia", "Austria", "Bahrain", "Belarus", "Belize",
            "Bolivia", "Botswana", "Brazil", "Brunei", "Bulgaria", "Burundi", "Canada", "Chile",
            "China", "Comoros", "Croatia", "Cyprus", "Denmark", "Dominica", "Ecuador", "Eritrea",
            "Estonia", "Finland", "France", "Georgia", "Germany", "Ghana", "Grenada", "Honduras",
            "Hungary", "Iceland", "Ireland", "Israel", "Jamaica", "Jordan", "Kiribati", "Lebanon",
            "Lesotho", "Liberia", "Maldives", "Moldova", "Morocco", "Myanmar (Burma)", "Namibia",
            "Netherlands", "Nigeria", "Norway", "Palau", "Panama", "Romania", "Russia", "Rwanda",
            "Senegal", "Serbia", "Somalia", "Sweden", "Taiwan", "Tunisia", "Turkey", "Tuvalu",
            "Uganda", "Ukraine", "Uruguay", "Vanuatu", "Vietnam", "Yemen", "Zambia", "Angola",
            "Bahamas", "Benin", "Bhutan", "Cape Verde", "Chad", "Cuba", "Egypt", "Gabon", "Gambia",
            "Ghana", "Greece", "Guinea", "Guyana", "Haiti", "India", "Iran", "Iraq", "Italy",
            "Japan", "Kenya", "Kuwait", "Laos", "Latvia", "Libya", "Malawi", "Mali", "Malta",
            "Mexico", "Monaco", "Nauru", "Nepal", "Niger", "Oman", "Peru", "Poland", "Qatar",
            "Samoa", "Spain", "Sudan", "Syria", "Togo", "Tonga", "Fiji"
    );

    public static void main(String[] args) {

        ArrayList<String> countryNames = new ArrayList<>();
        ArrayList<String> capitals = new ArrayList<>();
        ArrayList<String> areas = new ArrayList<>();
        ArrayList<Long> populations = new ArrayList<>();

        File inputFile = new File(INPUT_FILE_NAME);
        if (!inputFile.exists()) {
            System.err.println("Error: Input file not found: " + INPUT_FILE_NAME);
            return;
        }

        System.out.println("Reading and parsing data from " + INPUT_FILE_NAME + "...");
        int lineCount = 0;
        // Ensure UTF-8 reading
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(inputFile), StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Handle Byte Order Mark (BOM)
                if (lineCount == 0 && line.startsWith("\uFEFF")) {
                    line = line.substring(1);
                }
                line = line.trim();
                lineCount++;
                if (line.isEmpty()) continue;

                try {

                    int lastSpace = line.lastIndexOf(' ');
                    if (lastSpace == -1) throw new IllegalArgumentException("No space found");
                    String populationStr = line.substring(lastSpace + 1).replace(",", "");
                    long population = Long.parseLong(populationStr);

                    int nameCapEndIndex = -1;
                    for (int i = lastSpace - 1; i >= 0; i--) {
                        char c = line.charAt(i);
                        if (!Character.isDigit(c) && c != ',' && c != '.' && !(c == ' ' && i < lastSpace -1) ) {
                            nameCapEndIndex = i;
                            break;
                        }
                        if (i == 0 && (Character.isDigit(c) || c == '.')) {
                            nameCapEndIndex = -1; break;
                        }
                    }

                    String countryAndCapitalPart;
                    String areaStr;

                    if (nameCapEndIndex != -1) {
                        countryAndCapitalPart = line.substring(0, nameCapEndIndex + 1).trim();
                        areaStr = line.substring(nameCapEndIndex + 1, lastSpace).trim();
                    } else {
                        int firstSpace = line.indexOf(' ');
                        if (firstSpace == lastSpace) { // "Name Pop" format
                            countryAndCapitalPart = line.substring(0, firstSpace).trim();
                            areaStr = "";
                        } else { // Assume "Name Area Pop" like Vatican City
                            countryAndCapitalPart = line.substring(0, firstSpace).trim();
                            areaStr = line.substring(firstSpace, lastSpace).trim();
                        }
                    }

                    // Match Country Name (longest first)
                    String foundCountryName = null;
                    String capitalName = "";

                    for (String knownName : KNOWN_COUNTRY_NAMES) {
                        if (countryAndCapitalPart.equals(knownName)) {
                            foundCountryName = knownName;
                            capitalName = "";
                            break;
                        }
                        if (countryAndCapitalPart.startsWith(knownName) &&
                                (countryAndCapitalPart.length() == knownName.length() ||
                                        countryAndCapitalPart.charAt(knownName.length()) == ' '))
                        {
                            foundCountryName = knownName;
                            capitalName = countryAndCapitalPart.substring(knownName.length()).trim();
                            break;
                        }
                    }

                    if (foundCountryName != null) {
                        // Vatican/Western Sahara area can potentially be parsed as capital
                        if ((foundCountryName.equals("Vatican City") || foundCountryName.equals("Western Sahara"))
                                && capitalName.matches("^[\\d.,\\s]+$") && areaStr.isEmpty()) {
                            areaStr = capitalName;
                            capitalName = "";
                        }

                        countryNames.add(foundCountryName);
                        capitals.add(capitalName);
                        areas.add(areaStr);
                        populations.add(population);
                    } else {
                        System.err.println("Warning: Failed to identify known country name for: '" + countryAndCapitalPart + "' on line " + lineCount + ": '" + line + "'. Skipping.");
                    }

                } catch (NumberFormatException e) {
                    System.err.println("Error parsing population on line " + lineCount + ": '" + line + "'. Skipping. Error: " + e.getMessage());
                } catch (IllegalArgumentException | StringIndexOutOfBoundsException e) {
                    System.err.println("Error parsing line structure on line " + lineCount + ": '" + line + "'. Skipping. Error: " + e.getMessage());
                } catch (Exception e) {
                    System.err.println("Unexpected error processing line " + lineCount + ": '" + line + "'. Skipping. Error: " + e.getMessage());
                    e.printStackTrace();
                }
            }

        } catch (IOException e) {
            System.err.println("Error reading file " + INPUT_FILE_NAME + ": " + e.getMessage());
            e.printStackTrace();
            return; // Exit if file reading fails fundamentally
        }

        int numCountries = countryNames.size();
        System.out.println("Finished parsing. Successfully processed " + numCountries + " countries.");

        if (numCountries == 0) {
            System.out.println("No data processed. Exiting.");
            return;
        }


        List<Integer> indices = new ArrayList<>(numCountries);
        for (int i = 0; i < numCountries; i++) {
            indices.add(i);
        }

        // Sort the indices based on countryNames alphabetically
        System.out.println("Sorting by country name...");
        indices.sort(Comparator.comparing(index -> countryNames.get(index)));

        System.out.println("Writing sorted data to " + OUTPUT_FILE_SORTED_BY_NAME + "...");
        writeSortedData(OUTPUT_FILE_SORTED_BY_NAME, indices, countryNames, populations);
        System.out.println("Successfully wrote sorted data by country name.");


        System.out.println("Sorting by population (descending)...");
        indices.sort(Comparator.comparingLong((Integer index) -> populations.get(index)).reversed());

        System.out.println("Writing sorted data to " + OUTPUT_FILE_SORTED_BY_POPULATION + "...");
        writeSortedData(OUTPUT_FILE_SORTED_BY_POPULATION, indices, countryNames, populations);
        System.out.println("Successfully wrote sorted data by population.");

        System.out.println("\nProgram finished.");
    }


    /**
     * Writes the country name and population to a file, based on the sorted order
     * provided by the indices list. Ensures UTF-8 encoding.
     *
     * @param filename     The name of the file to write to.
     * @param sortedIndices A List of integers representing the sorted order.
     * @param names        The ArrayList of country names.
     * @param pops         The ArrayList of country populations.
     */
    private static void writeSortedData(String filename, List<Integer> sortedIndices,
                                        ArrayList<String> names, ArrayList<Long> pops) {
        // Ensure UTF-8 writing
        try (PrintWriter writer = new PrintWriter(new OutputStreamWriter(new FileOutputStream(filename), StandardCharsets.UTF_8))) {
            NumberFormat numberFormatter = NumberFormat.getNumberInstance(Locale.US); // For commas

            for (int index : sortedIndices) {
                String country = names.get(index);
                long population = pops.get(index);
                String formattedPopulation = numberFormatter.format(population);

                // Output format: CountryName<SEPARATOR>Population
                writer.printf("%s%s%s%n", country, OUTPUT_SEPARATOR, formattedPopulation);
            }
        } catch (IOException e) {
            System.err.println("Error writing to file " + filename + ": " + e.getMessage());
            e.printStackTrace();
        }
    }
}