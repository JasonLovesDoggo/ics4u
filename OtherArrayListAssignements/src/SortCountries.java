import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Author: Jason Cameron
 * Assignment: Array Sorting Assignment 3
 * Date: 2023-10-27 // Or current date
 *
 * Description: This program reads country data from "Countries-Populations.txt".
 * It uses a parsing strategy that identifies population (last token) and area
 * (second-last token). It employs a Map for specific multi-word country names
 * (edge cases) to correctly identify them. For other lines, it concatenates
 * all tokens before the area/population as the country name. Capital extraction
 * remains unreliable and is stored as an empty string.
 *
 * The program stores data in 4 parallel ArrayLists as required.
 * It then creates a list of helper objects (CountryRecord) for easier sorting.
 * Sorts alphabetically by country name, outputting to sortedByCountry.txt.
 * Sorts numerically (descending) by population, outputting to sortedByPopulation.txt.
 *
 * Compiled and run as a single Java file.
 */
public class SortCountries {

    // Helper record to hold country name and population for easier sorting
    // (Introduced for simplification as requested)
    private record CountryRecord(String name, long population) {}

    // Map for known multi-word or tricky country names
    // Key: The full name as it appears starting the line. Value: The canonical name to use.
    private static final Map<String, String> edgeCaseCountries = createEdgeCaseMap();

    private static Map<String, String> createEdgeCaseMap() {
        Map<String, String> map = new HashMap<>();
        // Add entries where simple splitting might fail
        map.put("Antigua and Barbuda", "Antigua and Barbuda");
        map.put("Bosnia and Herzegovina", "Bosnia and Herzegovina");
        map.put("Burkina Faso", "Burkina Faso");
        map.put("Cape Verde", "Cape Verde");
        map.put("Central African Republic", "Central African Republic");
        map.put("Costa Rica", "Costa Rica");
        // Handle potential encoding issues or variations if needed:
        // map.put("C�te d'Ivoire", "Cote d'Ivoire"); // Example
        map.put("Cote d'Ivoire", "Cote d'Ivoire"); // Assuming simple apostrophe
        map.put("Czech Republic", "Czech Republic");
        map.put("Dominican Republic", "Dominican Republic");
        map.put("East Timor", "East Timor");
        map.put("El Salvador", "El Salvador");
        map.put("Equatorial Guinea", "Equatorial Guinea");
        map.put("Guinea-Bissau", "Guinea-Bissau");
        // Handle comma if present in input:
        map.put("Korea, North", "Korea, North");
        map.put("Korea, South", "Korea, South");
        map.put("Marshall Islands", "Marshall Islands");
        map.put("New Zealand", "New Zealand");
        map.put("Papua New Guinea", "Papua New Guinea");
        map.put("St. Kitts and Nevis", "St. Kitts and Nevis");
        map.put("St. Lucia", "St. Lucia");
        map.put("St. Vincent and the Grenadines", "St. Vincent and the Grenadines");
        map.put("San Marino", "San Marino");
        // Handle potential encoding issues or variations if needed:
        // map.put("S�o Tom� and Pr�ncipe", "Sao Tome and Principe"); // Example
        map.put("Sao Tome and Principe", "Sao Tome and Principe"); // Assuming simpler input chars
        map.put("Saudi Arabia", "Saudi Arabia");
        map.put("Sierra Leone", "Sierra Leone");
        map.put("Solomon Islands", "Solomon Islands");
        map.put("South Africa", "South Africa");
        map.put("Sri Lanka", "Sri Lanka");
        map.put("Trinidad and Tobago", "Trinidad and Tobago");
        map.put("United Arab Emirates", "United Arab Emirates");
        map.put("United Kingdom", "United Kingdom");
        map.put("United States", "United States");
        map.put("Western Sahara", "Western Sahara"); // Capital might be missing
        // Add more as needed based on the exact file content
        return Collections.unmodifiableMap(map);
    }


    public static void main(String[] args) {

        // --- Configuration ---
        String inputFileName = "Countries-Population.txt";
        String outputFileNameByName = "sortedByCountry.txt";
        String outputFileNameByPopulation = "sortedByPopulation.txt";

        // --- A. Input data from the file ---

        // 2. Declare 4 ArrayLists
        ArrayList<String> countryNamesList = new ArrayList<>();
        ArrayList<String> capitalsList = new ArrayList<>(); // Will store "" or unreliable data
        ArrayList<Double> areasList = new ArrayList<>();
        ArrayList<Long> populationsList = new ArrayList<>();

        int lineCount = 0; // Total lines processed

        // 1 & 3. Read lines, determine count, and fill arrays using a while loop
        System.out.println("Reading data from " + inputFileName + "...");
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFileName))) {
            String currentLine;
            while ((currentLine = reader.readLine()) != null) {
                lineCount++;
                currentLine = currentLine.trim();
                if (currentLine.isEmpty()) continue; // Skip blank lines

                String countryName = null;
                String remainingPart = null;

                // Check for edge cases first (longest match principle implicitly handled by HashMap lookup)
                String matchedEdgeCase = null;
                for (String key : edgeCaseCountries.keySet()) {
                    if (currentLine.startsWith(key)) {
                        // Basic longest match: if we found a match, check if this new key is longer
                        if (matchedEdgeCase == null || key.length() > matchedEdgeCase.length()) {
                            matchedEdgeCase = key;
                        }
                    }
                }

                if (matchedEdgeCase != null) {
                    countryName = edgeCaseCountries.get(matchedEdgeCase); // Get canonical name
                    // The part after the country name contains capital (maybe), area, population
                    remainingPart = currentLine.substring(matchedEdgeCase.length()).trim();
                } else {
                    // No edge case match - use fallback heuristic
                    // Split by space, assume last two are numbers, rest is name/capital combined
                    remainingPart = currentLine; // Process the whole line in the fallback
                }

                // Now parse the remainingPart (or the whole line in fallback)
                // to find area and population
                String[] parts = remainingPart.split("\\s+");

                if (parts.length >= 2) { // Need at least area and population
                    try {
                        // Extract Population (last part)
                        String populationStr = parts[parts.length - 1].replace(",", "");
                        long population = Long.parseLong(populationStr);

                        // Extract Area (second-to-last part)
                        String areaStr = parts[parts.length - 2].replace(",", "");
                        double area = Double.parseDouble(areaStr);

                        // Determine Country Name if it wasn't an edge case
                        if (countryName == null) { // Fallback case
                            StringBuilder nameBuilder = new StringBuilder();
                            for (int i = 0; i < parts.length - 2; i++) {
                                nameBuilder.append(parts[i]).append(" ");
                            }
                            countryName = nameBuilder.toString().trim();
                            // Check for Vatican City edge case within fallback (no capital before numbers)
                            if (countryName.equals("Vatican City") && parts.length == 3) {
                                // special case handled correctly by default logic if parts are ["Vatican", "City", "0.17", "932"]
                                // but if parts are ["Vatican", "City", "0.17", "932"] this might fail
                                // Let's adjust - if parts.length-2 is 0, name is empty.

                                // Re-evaluate fallback: If only 2 parts after split remain (area, pop),
                                // the country name must have been determined by the edge case logic or is missing.
                                // If parts.length > 2, then indices 0 to parts.length - 3 form the name/capital.
                                if (parts.length - 2 == 0 && matchedEdgeCase == null) {
                                    // This likely means the line ONLY contained area and population, which is invalid data.
                                    // OR it means the split failed unexpectedly. Let's treat it as an error for now.
                                    System.err.println("Warning: Skipping line " + lineCount + ". Could not determine country name in fallback for: '" + currentLine + "'");
                                    continue; // Skip this line
                                }
                                //If countryName ended up empty and it wasn't Vatican City, log warning
                                if (countryName.isEmpty() && !currentLine.contains("Vatican City")) {
                                    System.err.println("Warning: Parsed empty country name for line " + lineCount + ": '" + currentLine + "'");
                                }

                            }

                            // Handle empty country name after fallback (e.g. Vatican City line structure)
                            // Vatican City line might be "Vatican City 0.17 932" -> parts = ["Vatican", "City", "0.17", "932"] -> name = "Vatican City" (correct)
                            // Or "Vatican City   0.17 932" -> handled by split("\\s+")
                            // If line was just "0.17 932", countryName would be empty here.
                            if (countryName.isEmpty() && parts.length == 2) {
                                System.err.println("Warning: Skipping line " + lineCount + " with only potential area/population found: '" + currentLine + "'");
                                continue;
                            }
                        }


                        // Add data to the parallel ArrayLists
                        // Only add if countryName is valid (not null and not empty, unless it's a known case like Vatican City potentially having empty capital part)
                        if (countryName != null && !countryName.isEmpty()) {
                            countryNamesList.add(countryName);
                            capitalsList.add(""); // Capital extraction is unreliable
                            areasList.add(area);
                            populationsList.add(population);
                        } else if (countryName != null && countryName.isEmpty() && currentLine.contains("Vatican City")){
                            // Special allowance? Or should Vatican City be an edge case? Add it.
                            countryName = "Vatican City"; // Force name if parsed empty but line indicates it.
                            countryNamesList.add(countryName);
                            capitalsList.add("");
                            areasList.add(area);
                            populationsList.add(population);
                        }
                        else {
                            System.err.println("Warning: Skipping line " + lineCount + " due to missing country name after parsing: '" + currentLine + "'");
                        }


                    } catch (NumberFormatException e) {
                        System.err.println("Warning: Skipping line " + lineCount + " due to invalid number format: '" + currentLine + "' -> " + e.getMessage());
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.err.println("Warning: Skipping line " + lineCount + " due to unexpected structure (ArrayIndexOutOfBounds): '" + currentLine + "' -> " + e.getMessage());
                    }
                } else {
                    System.err.println("Warning: Skipping line " + lineCount + " due to insufficient parts after splitting remaining text: '" + remainingPart + "' (from line: '" + currentLine + "')");
                }
            }
        } catch (IOException e) {
            System.err.println("FATAL ERROR: Could not read input file '" + inputFileName + "'.");
            e.printStackTrace(); // Print stack trace for more detail
            return;
        }

        int numberOfCountries = countryNamesList.size();
        System.out.println("Successfully parsed data for " + numberOfCountries + " countries from " + lineCount + " lines processed.");
        if (numberOfCountries == 0) {
            System.out.println("No country data loaded. Exiting.");
            return;
        }

        // --- B. Process the data and output ---

        // Create a list of CountryRecord objects for sorting
        List<CountryRecord> countryRecords = new ArrayList<>();
        for (int i = 0; i < numberOfCountries; i++) {
            countryRecords.add(new CountryRecord(countryNamesList.get(i), populationsList.get(i)));
        }

        // --- 4. Sort by Country Name (Alphabetically) ---
        // Use Comparator.comparing with the record's name accessor
        countryRecords.sort(Comparator.comparing(CountryRecord::name));

        // Write sorted list by name
        System.out.println("\nWriting sorted data by country name to " + outputFileNameByName + "...");
        try (PrintWriter writer = new PrintWriter(new FileWriter(outputFileNameByName))) {
            NumberFormat populationFormatter = NumberFormat.getNumberInstance(Locale.US);
            for (CountryRecord record : countryRecords) {
                // Format: CountryName<TAB><TAB><TAB>Population (with commas)
                writer.printf("%s\t\t\t%s%n", record.name(), populationFormatter.format(record.population()));
            }
            System.out.println("Successfully wrote sorted list by name.");
        } catch (IOException e) {
            System.err.println("ERROR: Could not write to output file '" + outputFileNameByName + "'.");
            e.printStackTrace();
        }

        // --- 5. Sort by Population (Numerically, Descending) ---
        // Use Comparator.comparingLong with the record's population accessor, then reverse
        countryRecords.sort(Comparator.comparingLong(CountryRecord::population).reversed());

        // Write sorted list by population
        System.out.println("\nWriting sorted data by population (descending) to " + outputFileNameByPopulation + "...");
        try (PrintWriter writer = new PrintWriter(new FileWriter(outputFileNameByPopulation))) {
            NumberFormat populationFormatter = NumberFormat.getNumberInstance(Locale.US);
            for (CountryRecord record : countryRecords) {
                // Format: CountryName<TAB><TAB><TAB>Population (with commas)
                writer.printf("%s\t\t\t%s%n", record.name(), populationFormatter.format(record.population()));
            }
            System.out.println("Successfully wrote sorted list by population.");
        } catch (IOException e) {
            System.err.println("ERROR: Could not write to output file '" + outputFileNameByPopulation + "'.");
            e.printStackTrace();
        }

        System.out.println("\nProcessing complete.");
    } // End of main method

} // End of class SortCountries