import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests PatientRecords
 * 
 * @author Jessica Young Schmidt
 * @author Michelle Glatz
 * @author Aanya Tomar
 */
public class PatientRecordsTest {
    // Reminder that magic numbers are allowed in test program
    
    /** tolerance used for comparing double values */
    public static final double DELTA = .001;      

    /** PatientRecords object used for testing. */
    private PatientRecords patientRecords;

    /**
     * Sets up test fixture before each test method.
     */
    @BeforeEach
    public void setUp() {
        patientRecords = new PatientRecords();
    }

    /**
     * Tests public constant.
     */
    @Test
    public void testPublicConstant() {
        assertEquals(10, patientRecords.DEFAULT_MAX_PATIENTS);
    }

    /**
     * Tests constructor with no parameters.
     */
    @Test
    public void testConstructorNoParameters() {
        // checks that no entries and default values
        assertEquals(0, patientRecords.getNumberOfPatients());
        assertEquals(10, patientRecords.getMaxPatients());       
        assertEquals("Id,Name,Birthdate,Height,Weight,Temperature,HeartRate\n", 
                      patientRecords.toString());
    }

    /**
     * Tests constructor with one parameter.
     */
    @Test
    public void testConstructorOneParameterStudent() {
        PatientRecords records = new PatientRecords(3);
        assertEquals(0, records.getNumberOfPatients());
        assertEquals(3, records.getMaxPatients());
        assertEquals("Id,Name,Birthdate,Height,Weight,Temperature,HeartRate\n",
                records.toString());
    }
      

    /**
     * Tests equals method.
     */
    @Test
    public void testEquals() {
        PatientRecords b = new PatientRecords(10);
        // Different size so c and patientRecords will never be equal
        PatientRecords c = new PatientRecords(20);
        assertTrue(patientRecords.equals(b), "Empty patientRecords with same size");
        assertFalse(c.equals(patientRecords), "Empty patientRecords with different sizes");
        assertTrue(patientRecords.equals(patientRecords), "patientRecords equals itself");
        
        patientRecords.addPatient(1000, "John Doe", 2, 29, 2024, 69, 160, 98.9, 60);
        assertFalse(patientRecords.equals(b), "Same size, different number of patients");
        assertFalse(patientRecords.equals(c), "Different size and different number of patients");

        b.addPatient(1000, "John Doe", 2, 29, 2024, 69, 160, 98.9, 60);
        c.addPatient(1000, "John Doe", 2, 29, 2024, 69, 160, 98.9, 60);
        assertTrue(patientRecords.equals(b), "Same size and same patients");
        assertFalse(patientRecords.equals(c), "Different size and same patients");
       
        patientRecords.addPatient(2000, "Jane Doe", 3, 5, 2020, 48, 50, 98.6, 65);
        b.addPatient(2000, "Jane Doe", 3, 5, 2020, 48, 55, 98.6, 65);
        assertFalse(patientRecords.equals(b), 
                    "Same size and number of patients, one weight different");

        assertFalse(patientRecords.equals(null), "patientRecords with null");

        assertFalse(patientRecords.equals("John Doe"), "patientRecords with String");
    }

    /**
     * Tests equals with two different PatientRecords objects
     * that contain the same three patients.
     */
    @Test
    public void testEqualsThreeIdenticalPatientsStudent() {
        PatientRecords a = new PatientRecords(5);
        PatientRecords b = new PatientRecords(5);

        a.addPatient(3000, "Shannon", 7, 26, 1988, 66, 150, 97.6, 75);
        a.addPatient(2999, "Brittany", 8, 14, 1990, 59, 200, 98.6, 85);
        a.addPatient(3001, "Chris", 1, 10, 2000, 70, 180, 99.2, 72);

        b.addPatient(3001, "Chris", 1, 10, 2000, 70, 180, 99.2, 72);
        b.addPatient(3000, "Shannon", 7, 26, 1988, 66, 150, 97.6, 75);
        b.addPatient(2999, "Brittany", 8, 14, 1990, 59, 200, 98.6, 85);

        assertTrue(a.equals(b));
        assertTrue(b.equals(a));
    }


    /**
     * Tests isFull method.
     */
    @Test
    public void testIsFull() {
        PatientRecords t = new PatientRecords(2);
        assertFalse(t.isFull(),
                "Test empty PatientRecords with max 2 patients - not full");
        t.addPatient(3000, "Shannon", 7, 26, 1988, 66, 150, 98.6, 75);
        assertFalse(t.isFull(),
                "Test PatientRecords with one patient and 2 max patients - not full");
        t.addPatient(3001, "Brittany", 8, 14, 1990, 59, 200, 98.6, 85);
        assertTrue(t.isFull(),
                "Test PatientRecords with two patients and max 2 patients - full");
    }

    /**
     * Tests addPatient with one Patient parameter.
     */
    @Test
    public void testAddPatientOneParameter() {
        // add first patient
        Patient patient1 = new Patient(3000, "Shannon", 7, 26, 1988, 66, 150, 97.6, 75);
        patientRecords.addPatient(patient1);
        assertEquals(1, patientRecords.getNumberOfPatients());
        assertEquals(10, patientRecords.getMaxPatients());       
        assertEquals("Id,Name,Birthdate,Height,Weight,Temperature,HeartRate\n" +
                     "3000,Shannon,07/26/1988,66,150,97.6,75\n", patientRecords.toString());

        // add second patient that would be sorted before first
        Patient patient2 = new Patient(2999, "Brittany", 8, 14, 1990, 59, 200, 98.6, 85);
        patientRecords.addPatient(patient2);
        assertEquals(2, patientRecords.getNumberOfPatients());      
        assertEquals("Id,Name,Birthdate,Height,Weight,Temperature,HeartRate\n" +
                     "2999,Brittany,08/14/1990,59,200,98.6,85\n" +
                     "3000,Shannon,07/26/1988,66,150,97.6,75\n", patientRecords.toString());
    }


    /**
     * Tests getAt methods with two patients.
     */
    @Test
    public void testGetAtMethodsWithTwoPatients() {
        patientRecords.addPatient(3000, "Shannon", 7, 26, 1988, 66, 150, 97.6, 75);
        patientRecords.addPatient(2999, "Brittany", 8, 14, 1990, 59, 200, 98.6, 85);

        assertEquals(new Birthdate(8, 14, 1990), patientRecords.getBirthdateAt(0),
                "Get birthdate at index 0");
        assertEquals(2999,
                patientRecords.getIdAt(0), "Get Id at index 0");
        assertEquals("Brittany", patientRecords.getNameAt(0), "Get patient name at index 0");
        assertEquals(59, patientRecords.getHeightAt(0), "Get height at index 0");
        assertEquals(200, patientRecords.getWeightAt(0), "Get weight at index 0");
        assertEquals(98.6,
                patientRecords.getTemperatureAt(0), DELTA, "Get temperature at index 0");
        assertEquals(85,
                patientRecords.getHeartRateAt(0), "Get heart rate at index 0");
    }

    /**
     * Tests all getAt methods using a PatientRecords object with three patients.
     */
    @Test
    public void testGetAtMethodsWithThreePatientsStudent() {
        patientRecords.addPatient(3002, "Taylor", 5, 20, 1995, 65, 140, 98.7, 78);
        patientRecords.addPatient(3000, "Alex", 3, 12, 1992, 70, 180, 99.1, 80);
        patientRecords.addPatient(3001, "Jordan", 9, 1, 1998, 62, 130, 98.4, 74);

        assertEquals(new Birthdate(9, 1, 1998), patientRecords.getBirthdateAt(1),
                "Get birthdate at index 1");
        assertEquals(3001, patientRecords.getIdAt(1), "Get id at index 1");
        assertEquals("Jordan", patientRecords.getNameAt(1), "Get name at index 1");
        assertEquals(62, patientRecords.getHeightAt(1), "Get height at index 1");
        assertEquals(130, patientRecords.getWeightAt(1), "Get weight at index 1");
        assertEquals(98.4, patientRecords.getTemperatureAt(1), DELTA,
                "Get temperature at index 1");
        assertEquals(74, patientRecords.getHeartRateAt(1),
                "Get heart rate at index 1");
    }
    


    /**
     * Tests addPatient with two patients.
     */
    @Test
    public void testAddPatientTwoPatients() {
        // add first patient
        patientRecords.addPatient(3000, "Shannon", 7, 26, 1988, 66, 150, 97.6, 75);
        assertEquals(1, patientRecords.getNumberOfPatients());
        assertEquals(10, patientRecords.getMaxPatients());       
        assertEquals("Id,Name,Birthdate,Height,Weight,Temperature,HeartRate\n" +
                     "3000,Shannon,07/26/1988,66,150,97.6,75\n", patientRecords.toString());

        // add second patient that would be sorted before first
        patientRecords.addPatient(2999, "Brittany", 8, 14, 1990, 59, 200, 98.6, 85);
        assertEquals(2, patientRecords.getNumberOfPatients());      
        assertEquals("Id,Name,Birthdate,Height,Weight,Temperature,HeartRate\n" +
                     "2999,Brittany,08/14/1990,59,200,98.6,85\n" +
                     "3000,Shannon,07/26/1988,66,150,97.6,75\n", patientRecords.toString());
    }

    /**
     * Tests adding three entries to a PatientRecords object.
     */
    @Test
    public void testAddPatientThreePatientsStudent() {
        patientRecords.addPatient(3002, "Taylor", 5, 20, 1995, 65, 140, 98.7, 78);
        patientRecords.addPatient(3000, "Alex", 3, 12, 1992, 70, 180, 99.1, 80);
        patientRecords.addPatient(3001, "Jordan", 9, 1, 1998, 62, 130, 98.4, 74);

        assertEquals(3, patientRecords.getNumberOfPatients());
        assertEquals("Id,Name,Birthdate,Height,Weight,Temperature,HeartRate\n" +
                     "3000,Alex,03/12/1992,70,180,99.1,80\n" +
                     "3001,Jordan,09/01/1998,62,130,98.4,74\n" +
                     "3002,Taylor,05/20/1995,65,140,98.7,78\n",
                patientRecords.toString());
    } 



    /**
     * Tests updatePatient with two patients.
     */
    @Test
    public void testUpdatePatientTwoPatients() {
        
        patientRecords.addPatient(3000, "Shannon", 7, 26, 1988, 66, 150, 97.6, 75);
        patientRecords.addPatient(2999, "Brittany", 8, 14, 1990, 59, 200, 98.6, 85);
        
        patientRecords.updatePatient(3000, "Shannon", 7, 26, 1988, 66, 160, 98, 80);
        assertEquals(2, patientRecords.getNumberOfPatients());      
        assertEquals("Id,Name,Birthdate,Height,Weight,Temperature,HeartRate\n" +
                     "2999,Brittany,08/14/1990,59,200,98.6,85\n" +
                     "3000,Shannon,07/26/1988,66,160,98.0,80\n", patientRecords.toString());
    }


    /**
     * Tests exceptions in PatientRecords methods.
     */
    @Test
    public void testForExceptions() {

        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> new PatientRecords(-116),
                "constructor with negative max patients");
        assertEquals("Invalid maxPatients", exception.getMessage(),
            "Testing PatientRecords constructor Invalid maxPatients exception message (negative)");

        exception = assertThrows(IllegalArgumentException.class,
                () -> new PatientRecords(0),
                "constructor with 0 maxPatients");
        assertEquals("Invalid maxPatients", exception.getMessage(),
            "Testing PatientRecords constructor Invalid maxPatients exception message (0)");

        PatientRecords t = new PatientRecords(2);
        t.addPatient(1000, "John Doe", 2, 29, 2024, 69, 160, 98.9, 60);
        
        exception = assertThrows(IllegalArgumentException.class,
                () -> t.getIdAt(-1), "getIdAt() with negative index");
        assertEquals("Invalid index", exception.getMessage(),
                "Testing getIdAt() exception message for negative index");
        exception = assertThrows(IllegalArgumentException.class,
                () -> t.getIdAt(1), "getIdAt() with index too large");
        assertEquals("Invalid index", exception.getMessage(),
                "Testing getIdAt() exception message for index too large");
                
        exception = assertThrows(IllegalArgumentException.class,
                () -> t.getNameAt(-1), "getNameAt() with negative index");
        assertEquals("Invalid index", exception.getMessage(),
                "Testing getNameAt() exception message for negative index");
        exception = assertThrows(IllegalArgumentException.class,
                () -> t.getNameAt(1), "getNameAt() with index too large");
        assertEquals("Invalid index", exception.getMessage(),
                "Testing getNameAt() exception message for index too large");
                
        exception = assertThrows(IllegalArgumentException.class,
                () -> t.getHeightAt(-1), "getHeightAt() with negative index");
        assertEquals("Invalid index", exception.getMessage(),
                "Testing getHeightAt() exception message for negative index");
        exception = assertThrows(IllegalArgumentException.class,
                () -> t.getHeightAt(1), "getHeightAt() with index too large");
        assertEquals("Invalid index", exception.getMessage(),
                "Testing getHeightAt() exception message for index too large");
                
        exception = assertThrows(IllegalArgumentException.class,
                () -> t.getWeightAt(-1), "getWeightAt() with negative index");
        assertEquals("Invalid index", exception.getMessage(),
                "Testing getWeightAt() exception message for negative index");
        exception = assertThrows(IllegalArgumentException.class,
                () -> t.getWeightAt(1), "getWeightAt() with index too large");
        assertEquals("Invalid index", exception.getMessage(),
                "Testing getWeightAt() exception message for index too large");
  
        exception = assertThrows(IllegalArgumentException.class,
                () -> t.getTemperatureAt(-1), "getTemperatureAt() with negative index");
        assertEquals("Invalid index", exception.getMessage(),
                "Testing getTemperatureAt() exception message for negative index");
        exception = assertThrows(IllegalArgumentException.class,
                () -> t.getTemperatureAt(1), "getTemperatureAt() with index too large");
        assertEquals("Invalid index", exception.getMessage(),
                "Testing getTemperatureAt() exception message for index too large");
               
        exception = assertThrows(IllegalArgumentException.class,
                () -> t.getHeartRateAt(-1), "getHeartRateAt() with negative index");
        assertEquals("Invalid index", exception.getMessage(),
                "Testing getHeartRateAt() exception message for negative index");
        exception = assertThrows(IllegalArgumentException.class,
                () -> t.getHeartRateAt(1), "getHeartRateAt() with index too large");
        assertEquals("Invalid index", exception.getMessage(),
                "Testing getHeartRateAt() exception message for index too large"); 
                
        exception = assertThrows(IllegalArgumentException.class,
                () -> t.getBirthdateAt(-1), "getBirthdateAt() with negative index");
        assertEquals("Invalid index", exception.getMessage(),
                "Testing getBirthdateAt() exception message for negative index");
        exception = assertThrows(IllegalArgumentException.class,
                () -> t.getBirthdateAt(1), "getBirthdateAt() with index too large");
        assertEquals("Invalid index", exception.getMessage(),
                "Testing getBirthdateAt() exception message for index too large");

        exception = assertThrows(IllegalArgumentException.class,
                () -> t.addPatient(null), "Checking null patient");
        assertEquals("Null patient", exception.getMessage(),
                "Testing Null patient exception message");
                
        exception = assertThrows(IllegalArgumentException.class,
                () -> t.addPatient(0, null, 12, 32, 2000, 101, 501, 111, 201),
                "addPatient - Invalid id");
        assertEquals("Invalid id", exception.getMessage(),
                "Testing addPatient Invalid id exception message");
                
        exception = assertThrows(IllegalArgumentException.class,
                () ->t.addPatient(9000, null, 12, 32, 2000, 101, 501, 111, 201),
                "addPatient - Invalid name - null");
        assertEquals("Null name",
                exception.getMessage(),
                "Testing addPatient Null name exception message");
                
        exception = assertThrows(IllegalArgumentException.class,
                () -> new Patient(9000, "   ", 12, 32, 2000, 101, 501, 111, 201),
                "addPatient - Invalid name - spaces");
        assertEquals("Empty or all whitespace name",
                exception.getMessage(),
                "Testing addPatient Empty or all whitespace name exception message");

        exception = assertThrows(IllegalArgumentException.class,
                () ->t.addPatient(9000, "Invalid Patient", 12, 32, 2000, 101, 501, 111, 201),
                "addPatient - Invalid birthdate (day)");
        assertEquals("Invalid day", exception.getMessage(),
                "Testing addPatient Invalid day exception message");

        exception = assertThrows(IllegalArgumentException.class,
                () -> t.addPatient(9000, "Invalid Patient", 1, 1, 1900, 101, 501, 111, 201),
                "addPatient - Invalid height");
        assertEquals("Invalid height",
                exception.getMessage(),
                "Testing addPatient Invalid height exception message");
               
        exception = assertThrows(IllegalArgumentException.class,
                () -> t.addPatient(9000, "Invalid Patient", 1, 1, 1900, 1, 501, 111, 201),
                "addPatient - Invalid weight");
        assertEquals("Invalid weight",
                exception.getMessage(),
                "Testing addPatient Invalid weight exception message");

        exception = assertThrows(IllegalArgumentException.class,
                () -> t.addPatient(9000, "Invalid Patient", 1, 1, 1900, 1, 1, 110.1, 201),
                "addPatient - Invalid temperature");
        assertEquals("Invalid temperature",
                exception.getMessage(),
                "Testing addPatient Invalid temperature exception message");

        exception = assertThrows(IllegalArgumentException.class,
                () -> t.addPatient(9000, "Invalid Patient", 1, 1, 1900, 1, 1, 90, 201),
                "addPatient - Invalid heart rate");
        assertEquals("Invalid heart rate", exception.getMessage(),
                "Testing addPatient Invalid heart rate exception message");
              
        exception = assertThrows(IllegalArgumentException.class,
                () -> t.addPatient(1000, null, 12, 32, 2000, 101, 501, 111, 201),
                "Checking duplicate id when adding patient");
        assertEquals("Duplicate id", exception.getMessage(),
                "Testing Duplicate id exception message when adding patient");
                             
        exception = assertThrows(IllegalArgumentException.class,
                () -> t.addPatient(new Patient(1000, "Jane Doe", 3, 5, 2020, 48, 55, 98.6, 65)),
                "Checking duplicate id when adding patient with one parameter");
        assertEquals("Duplicate id", exception.getMessage(),
                "Testing Duplicate id exception message when adding patient with one parameter");
                
        t.addPatient(2000, "Jane Doe", 3, 5, 2020, 48, 55, 98.6, 65);        
        exception = assertThrows(IllegalArgumentException.class,
                () -> t.addPatient(1000, null, 12, 32, 2000, 101, 501, 111, 201),
                "Checking full Patient list when adding patient");
        assertEquals("Patient list is full", exception.getMessage(),
                "Testing Patient list is full exception message when adding patient"); 
                
        //full list should be checked before null patient
        exception = assertThrows(IllegalArgumentException.class,
                () -> t.addPatient(null),
                "Checking full Patient list when adding patient with one parameter");
        assertEquals("Patient list is full", exception.getMessage(),
            "Testing Patient list is full exception message when add patient with one parameter");

        exception = assertThrows(IllegalArgumentException.class,
                () -> t.updatePatient(3000, "Jane Doe", 3, 5, 2020, 48, 55, 98.6, 70),
                "Checking Patient not found when updating patient");
        assertEquals("Patient not found", exception.getMessage(),
            "Testing Patient not found exception message when updating patient");
  
    }
}