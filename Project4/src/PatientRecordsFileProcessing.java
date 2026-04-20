import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * Helper class for reading and writing PatientRecords files.
 * 
 * @author Aanya Tomar
 */
public class PatientRecordsFileProcessing {

    /** Number of tokens required per CSV line. */
    public static final int TOKENS_PER_LINE = 7;

    /** Index of id token. */
    public static final int ID_INDEX = 0;

    /** Index of name token. */
    public static final int NAME_INDEX = 1;

    /** Index of birthdate token. */
    public static final int BIRTHDATE_INDEX = 2;

    /** Index of height token. */
    public static final int HEIGHT_INDEX = 3;

    /** Index of weight token. */
    public static final int WEIGHT_INDEX = 4;

    /** Index of temperature token. */
    public static final int TEMPERATURE_INDEX = 5;

    /** Index of heart rate token. */
    public static final int HEART_RATE_INDEX = 6;

    /** Number of parts in birthdate. */
    public static final int BIRTHDATE_PARTS = 3;

    /** Index of month in birthdate. */
    public static final int MONTH_INDEX = 0;

    /** Index of day in birthdate. */
    public static final int DAY_INDEX = 1;

    /** Index of year in birthdate. */
    public static final int YEAR_INDEX = 2;

    /** Expected header line. */
    public static final String HEADER_LINE =
        "Id,Name,Birthdate,Height,Weight,Temperature,HeartRate";

    /**
     * Reads patient records from a CSV file.
     * 
     * @param filepath path to input file
     * @param maxPatients maximum number of patients
     * @return PatientRecords object containing patients from file
     * @throws IllegalArgumentException if filepath or maxPatients is invalid,
     *         input file is invalid, or file cannot be read
     */
    public static PatientRecords readPatientRecordsFromFile(String filepath,
            int maxPatients) {

        if (filepath == null || filepath.trim().equals("")) {
            throw new IllegalArgumentException("Invalid filepath");
        }

        if (maxPatients <= 0) {
            throw new IllegalArgumentException("Invalid maxPatients");
        }

        Scanner fileScanner;
        try {
            fileScanner = new Scanner(Paths.get(filepath));
        }
        catch (Exception e) {
            throw new IllegalArgumentException("Input file not found");
        }

        if (!fileScanner.hasNextLine()) {
            fileScanner.close();
            throw new IllegalArgumentException("Empty input file");
        }

        String header = fileScanner.nextLine();
        if (!header.equals(HEADER_LINE)) {
            fileScanner.close();
            throw new IllegalArgumentException("Invalid file");
        }

        PatientRecords patientRecords = new PatientRecords(maxPatients);

        while (fileScanner.hasNextLine()) {

            String line = fileScanner.nextLine();
            Scanner lineScanner = new Scanner(line);
            lineScanner.useDelimiter(",");

            String[] tokens = new String[TOKENS_PER_LINE];
            int count = 0;

            while (lineScanner.hasNext()) {
                if (count >= TOKENS_PER_LINE) {
                    lineScanner.close();
                    fileScanner.close();
                    throw new IllegalArgumentException("Invalid file");
                }
                tokens[count] = lineScanner.next();
                count++;
            }

            lineScanner.close();

            if (count != TOKENS_PER_LINE) {
                fileScanner.close();
                throw new IllegalArgumentException("Invalid file");
            }

            int id;
            int height;
            int weight;
            double temperature;
            int heartRate;

            try {
                id = Integer.parseInt(tokens[ID_INDEX]);
                height = Integer.parseInt(tokens[HEIGHT_INDEX]);
                weight = Integer.parseInt(tokens[WEIGHT_INDEX]);

                if (!tokens[TEMPERATURE_INDEX].matches("\\d+(\\.\\d+)?")) {
                    fileScanner.close();
                    throw new IllegalArgumentException("Invalid file");
                }

                temperature = Double.parseDouble(tokens[TEMPERATURE_INDEX]);
                heartRate = Integer.parseInt(tokens[HEART_RATE_INDEX]);
            }
            catch (NumberFormatException e) {
                fileScanner.close();
                throw new IllegalArgumentException("Invalid file");
            }

            Scanner birthdateScanner = new Scanner(tokens[BIRTHDATE_INDEX]);
            birthdateScanner.useDelimiter("/");

            int[] birthParts = new int[BIRTHDATE_PARTS];
            int birthCount = 0;

            while (birthdateScanner.hasNext()) {

                if (!birthdateScanner.hasNextInt()) {
                    birthdateScanner.close();
                    fileScanner.close();
                    throw new IllegalArgumentException("Invalid file");
                }

                if (birthCount >= BIRTHDATE_PARTS) {
                    birthdateScanner.close();
                    fileScanner.close();
                    throw new IllegalArgumentException("Invalid file");
                }

                birthParts[birthCount] = birthdateScanner.nextInt();
                birthCount++;
            }

            birthdateScanner.close();

            if (birthCount != BIRTHDATE_PARTS) {
                fileScanner.close();
                throw new IllegalArgumentException("Invalid file");
            }

            int month = birthParts[MONTH_INDEX];
            int day = birthParts[DAY_INDEX];
            int year = birthParts[YEAR_INDEX];

            patientRecords.addPatient(id, tokens[NAME_INDEX], month, day, year,
                    height, weight, temperature, heartRate);
        }

        fileScanner.close();
        return patientRecords;
    }

    /**
     * Writes patient records to a CSV file.
     * 
     * @param filepath output filepath
     * @param patientRecords patient records to write
     * @throws IllegalArgumentException if filepath or patientRecords is invalid,
     *         output file exists, or output file cannot be created
     */
    public static void writePatientRecordsToFile(String filepath,
            PatientRecords patientRecords) {

        if (filepath == null || filepath.trim().equals("")) {
            throw new IllegalArgumentException("Invalid filepath");
        }

        if (patientRecords == null) {
            throw new IllegalArgumentException("Null patientRecords");
        }

        if (Files.exists(Paths.get(filepath))) {
            throw new IllegalArgumentException("Output file already exists");
        }

        try {
            PrintWriter writer = new PrintWriter(filepath);
            writer.print(patientRecords.toString());
            writer.close();
        }
        catch (FileNotFoundException e) {
            throw new IllegalArgumentException("Cannot create output file");
        }
    }
}