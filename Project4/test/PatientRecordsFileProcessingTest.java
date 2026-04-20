import static org.junit.jupiter.api.Assertions.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

/**
 * Tests PatientRecordsFileProcessing
 * 
 * @author Jessica Young Schmidt
 * @author Michelle Glatz
 */
public class PatientRecordsFileProcessingTest {
    
    
    /** tolerance used for comparing double values */
    public static final double DELTA = .001;        

    /**
     * Tests class constant
     */
    @Test
    public void testPublicConstant() {
        assertEquals(7, PatientRecordsFileProcessing.TOKENS_PER_LINE,
                "Tests value of PatientRecordsFileProcessing.TOKENS_PER_LINE");
               
    }
    
    /**
     * Tests readPatientRecordsFromFile method with valid file
     */
    @Test
    public void testReadPatientRecordsFromFile() {

        PatientRecords patientRecords = PatientRecordsFileProcessing
                .readPatientRecordsFromFile("test-files/patients.csv", 15);
                

        assertEquals(3, patientRecords.getNumberOfPatients(), 
                     "patientRecords.getNumberOfPatients() for patients.csv file");
        assertEquals(15, patientRecords.getMaxPatients(), 
            "patientRecords.getMaxPatients() for " + 
            "readPatientRecordsFromFile(\"test-files/patients.csv\", 15)");        
        assertEquals("Id,Name,Birthdate,Height,Weight,Temperature,HeartRate\n" +
                     "1001,Mary Beth Jones,04/21/1972,62,135,102.4,68\n" +
                     "1002,Thomas A. Berry,06/19/1967,73,198,98.6,70\n" +
                     "1003,Patricia Patel,11/18/1956,64,105,100.2,65\n",
                patientRecords.toString(),"check patients list for patients.csv file");
    }

    /**
     * Tests readPatientRecordsFromFile method with maxPatients that is too small
     */
    @Test
    public void testReadPatientRecordsFromFileSizeTooSmall() {
      
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> PatientRecordsFileProcessing.readPatientRecordsFromFile(
                        "test-files/patients.csv", 2), "Patient list is full");
        assertEquals("Patient list is full", exception.getMessage(),
                "Testing file input when maxPatients is too small exception message");

    }

    /** 
     * Tests writePatientRecordsToFile with valid input
     */
    @Test
    public void testWritePatientRecordsToFile() {
        PatientRecords a = new PatientRecords();
        a.addPatient(1001,"Mary Beth Jones",04,21,1972,62,135,102.4,68);
        a.addPatient(1003,"Patricia Patel",11,18,1956,64,105,100.2,65);
        a.addPatient(1002,"Thomas A. Berry",06,19,1967,73,198,98.6,70);

        String filename = "test-files/Output-patients.csv";
        Path path = Path.of(filename);
        try {
            Files.deleteIfExists(path);
        } catch (IOException e) {
            // Nothing needs to be done
            e.printStackTrace();
        } 

        PatientRecordsFileProcessing.writePatientRecordsToFile(filename, a);

        String message = "Testing write to file";

        String expectedContents = "Id,Name,Birthdate,Height,Weight,Temperature,HeartRate\n" +
                     "1001,Mary Beth Jones,04/21/1972,62,135,102.4,68\n" +
                     "1002,Thomas A. Berry,06/19/1967,73,198,98.6,70\n" +
                     "1003,Patricia Patel,11/18/1956,64,105,100.2,65\n";


        try {
            Scanner expected = new Scanner(expectedContents);
            Scanner actual = new Scanner(new FileInputStream(filename));
            testFileContents(expected, actual, message);
            expected.close();
            actual.close();
        } catch (FileNotFoundException e) {
            fail("File does not exist");
        }
    }

    /**
     * Testing contents of scanner
     * 
     * @param expected
     *            expected scanner
     * @param actual
     *            actual scanner
     * @param message
     *            message for test
     */
    public void testFileContents(Scanner expected, Scanner actual,
            String message) {
        int line = 0;
        while (expected.hasNextLine()) {
            line++;
            if (actual.hasNextLine()) {
                assertEquals(expected.nextLine(), actual.nextLine(),
                        message + ": Testing line " + line);
            } else {
                fail(message + ": Too few lines: line " + line);
            }
        }
        if (actual.hasNextLine()) {
            fail(message + ": Too many lines");
        }
    }

    /**
     * Tests for Exceptions for writePatientRecordsToFile
     */
    @Test
    public void testForExceptionsWritePatientRecordsToFile() {
        String filename = "test-files/Output-exceptions.csv";
        Path path = Path.of(filename);
        try {
            Files.deleteIfExists(path);
        } catch (IOException e) {
            // Nothing needs to be done
            e.printStackTrace();
        }

        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> PatientRecordsFileProcessing.writePatientRecordsToFile(filename, null),
                "Null patientRecords when writing to file");
        assertEquals("Null patientRecords", exception.getMessage(),
                "Testing exception message for Null patientRecords when writing to file");


        exception = assertThrows(IllegalArgumentException.class,
                () -> PatientRecordsFileProcessing.writePatientRecordsToFile(null, null),
                "null filepath when writing to file");
        assertEquals("Invalid filepath", exception.getMessage(),
                "Testing exception message for null filepath when writing to file");
                
                
        PatientRecords patientRecords = new PatientRecords();
        patientRecords.addPatient(1000, "John Doe", 2, 29, 2024, 69, 160, 98.9, 60);
        patientRecords.addPatient(2000, "Jane Doe", 3, 5, 2020, 48, 55, 98.6, 65);        

        exception = assertThrows(IllegalArgumentException.class,
                () -> PatientRecordsFileProcessing.writePatientRecordsToFile(
                       " \t ", patientRecords),
                "whitespace filepath when writing to file");
        assertEquals("Invalid filepath", exception.getMessage(),
                "Testing exception message for whitespace filepath when writing to file");
                
        exception = assertThrows(IllegalArgumentException.class,
                () -> PatientRecordsFileProcessing.writePatientRecordsToFile(
                       "not-a-directory/output.csv", patientRecords),
                "output file cannot be created for writing");
        assertEquals("Cannot create output file", exception.getMessage(),
                "Testing exception message for unable to create output file");                

        path = Path.of(filename);
        try {
            Files.deleteIfExists(path);
        } catch (IOException e) {
            // Nothing needs to be done
            e.printStackTrace();
        }

        try {
            PrintWriter out = new PrintWriter(new FileOutputStream(filename));
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        exception = assertThrows(IllegalArgumentException.class,
                () -> PatientRecordsFileProcessing.writePatientRecordsToFile(
                      filename, patientRecords),
                "output file already exists");
        assertEquals("Output file already exists", exception.getMessage(),
                "Testing exception message for output file already exists");

    }

    /**
     * Tests for Exceptions for readPatientRecordsFromFile
     */
    @Test
    public void testForExceptionsReadPatientRecordsFromFile() {
        

        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> PatientRecordsFileProcessing.readPatientRecordsFromFile(null, 10),
                "invalid filepath when reading from file");
        assertEquals("Invalid filepath", exception.getMessage(),
                "Testing exception message for null filepath when reading from file");
        exception = assertThrows(IllegalArgumentException.class,
                () -> PatientRecordsFileProcessing.readPatientRecordsFromFile("\t\t\t", 10),
                "invalid filepath when reading from file");
        assertEquals("Invalid filepath", exception.getMessage(),
                "Testing exception message for all whitespace filepath when reading from file");
        exception = assertThrows(IllegalArgumentException.class,
                () -> PatientRecordsFileProcessing.readPatientRecordsFromFile(
                        "test-files/patients.csv", 0),
                "non-positive maxPatients");
        assertEquals("Invalid maxPatients", exception.getMessage(),
                "Testing exception message for non-positive maxPatients");
        exception = assertThrows(IllegalArgumentException.class,
                () -> PatientRecordsFileProcessing
                        .readPatientRecordsFromFile("test-files/not-here.csv", 10),
                "Input file not found");
        assertEquals("Input file not found", exception.getMessage(),
                "Testing exception message for file not found");
        exception = assertThrows(IllegalArgumentException.class,
                () -> PatientRecordsFileProcessing
                        .readPatientRecordsFromFile("test-files/empty.csv", 10),
                "file is empty");
        assertEquals("Empty input file", exception.getMessage(),
                "Testing exception message for file that is empty");
              
        exception = assertThrows(IllegalArgumentException.class,
                () -> PatientRecordsFileProcessing
                        .readPatientRecordsFromFile("test-files/too-few-elements.csv", 10),
                "too few elements on line");
        assertEquals("Invalid file", exception.getMessage(),
                "Testing exception message for less than seven elements on a line");
                
        exception = assertThrows(IllegalArgumentException.class,
                () -> PatientRecordsFileProcessing
                        .readPatientRecordsFromFile("test-files/too-many-elements.csv", 10),
                "too many elements on line");
        assertEquals("Invalid file", exception.getMessage(),
                "Testing exception message for more than seven elements on a line");
                        
        exception = assertThrows(IllegalArgumentException.class,
                () -> PatientRecordsFileProcessing.readPatientRecordsFromFile(
                        "test-files/non-int-height.csv", 10),
                "patient with non-int height");               
        assertEquals("Invalid file", exception.getMessage(),
                "Testing exception message for patient with non-int height");
                
        exception = assertThrows(IllegalArgumentException.class,
                () -> PatientRecordsFileProcessing
                        .readPatientRecordsFromFile("test-files/non-int-weight.csv", 10),
                "patient with non-int weight");
        assertEquals("Invalid file", exception.getMessage(),
                "Testing exception message for patient with non-int weight");
                
        exception = assertThrows(IllegalArgumentException.class,
                () -> PatientRecordsFileProcessing.readPatientRecordsFromFile(
                        "test-files/non-int-hrate.csv", 10),
                "patient with non-int heart rate");               
        assertEquals("Invalid file", exception.getMessage(),
                "Testing exception message for patient with non-int heart rate");               
                
        exception = assertThrows(IllegalArgumentException.class,
                () -> PatientRecordsFileProcessing.readPatientRecordsFromFile(
                        "test-files/non-double-temp.csv", 10),
                "patient with non-double temperature");               
        assertEquals("Invalid file", exception.getMessage(),
                "Testing exception message for patient with non-double temperature"); 
               
        exception = assertThrows(IllegalArgumentException.class,
                () -> PatientRecordsFileProcessing.readPatientRecordsFromFile(
                        "test-files/inv-birthdate-format.csv", 10),
                "patient with incorrect birthdate format");               
        assertEquals("Invalid file", exception.getMessage(),
                "Testing exception message for patient with invalid birthdate format"); 
        
        exception = assertThrows(IllegalArgumentException.class,
                () -> PatientRecordsFileProcessing.readPatientRecordsFromFile(
                        "test-files/missing-name.csv", 10),
                "patient with empty name");               
        assertEquals("Empty or all whitespace name", exception.getMessage(),
                "Testing exception message for patient with empty name");                
  
        exception = assertThrows(IllegalArgumentException.class,
                () -> PatientRecordsFileProcessing.readPatientRecordsFromFile(
                        "test-files/invalid-header.csv", 10),
                "Invalid header line");               
        assertEquals("Invalid file", exception.getMessage(),
                "Testing exception message for file with invalid header line"); 
    }

}