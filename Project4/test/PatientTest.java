import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;


/**
 * Tests Patient class including constants, constructor,
 * getters, toString, equals, compareTo, and exceptions.
 * 
 * @author Jessica Young Schmidt
 * @author Michelle Glatz
 * @author Aanya Tomar
 */
public class PatientTest {
    
    /** Tolerance used for comparing double values. */
    public static final double DELTA = .001;

    /**
     * Tests that all public constants in Patient
     * have the correct values.
     */
    @Test
    public void testPublicConstants() {
        assertEquals(1000, Patient.MIN_ID,
                "Tests value of Patient.MIN_ID");
        assertEquals(9999, Patient.MAX_ID,
                "Tests value of Patient.MAX_ID");
        assertEquals(100, Patient.MAX_HEIGHT,
                "Tests value of Patient.MAX_HEIGHT");  
        assertEquals(500, Patient.MAX_WEIGHT,
                "Tests value of Patient.MAX_WEIGHT");
        assertEquals(90, Patient.MIN_TEMPERATURE,
                "Tests value of Patient.MIN_TEMPERATURE");                 
        assertEquals(110, Patient.MAX_TEMPERATURE,
                "Tests value of Patient.MAX_TEMPERATURE");
        assertEquals(25, Patient.MIN_HEART_RATE,
                "Tests value of Patient.MIN_HEART_RATE");                 
        assertEquals(200, Patient.MAX_HEART_RATE,
                "Tests value of Patient.MAX_HEART_RATE");                                
    }
    
    /**
     * Tests constructor and getter methods using valid data.
     */
    @Test
    public void testConstructorAndGetters() {
        Patient patient = new Patient(1000, "John Doe", 2, 29, 2024, 69, 160, 98.9, 60);
        Birthdate date = new Birthdate(2, 29, 2024);
        assertEquals(date, patient.getBirthdate(), "Test getBirthdate()");
        assertEquals(1000, patient.getId(), "Test getId()");
        assertEquals("John Doe", patient.getName(), "Test getName()");
        assertEquals(69, patient.getHeight(), "Test getHeight()"); 
        assertEquals(69, patient.getHeight(), "Test getHeight()"); 
        assertEquals(98.9, patient.getTemperature(), DELTA, "Test getTemperature()");
        assertEquals(60, patient.getHeartRate(), "Test getHeartRate()");
    } 

    /**
     * Tests constructor and getter methods using additional data.
     */
    @Test
    public void testConstructorAndGettersStudent() {
        Patient patient = new Patient(3456, "Mary Smith", 10, 15, 1998, 64, 125, 99.1, 72);
        Birthdate date = new Birthdate(10, 15, 1998);
        assertEquals(date, patient.getBirthdate(), "Test getBirthdate()");
        assertEquals(3456, patient.getId(), "Test getId()");
        assertEquals("Mary Smith", patient.getName(), "Test getName()");
        assertEquals(64, patient.getHeight(), "Test getHeight()");
        assertEquals(64, patient.getHeight(), "Test getHeight()");
        assertEquals(99.1, patient.getTemperature(), DELTA, "Test getTemperature()");
        assertEquals(72, patient.getHeartRate(), "Test getHeartRate()");
    }
    
    /**
     * Tests toString method formatting.
     */
    @Test
    public void testToString() {
         
        Patient patient = new Patient(9000, "Test Patient", 3, 31, 1901, 48, 80, 97, 70);
        assertEquals("9000,Test Patient,03/31/1901,48,80,97.0,70", patient.toString());
    }

    /**
     * Tests toString method using additional data.
     */
    @Test
    public void testToStringStudent() {
        Patient patient = new Patient(1234, "Alice Brown", 1, 9, 2001, 61, 115, 98.4, 66);
        assertEquals("1234,Alice Brown,01/09/2001,61,115,98.4,66", patient.toString());
    }
       
    /**
     * Tests equals and compareTo methods.
     */
    @Test
    public void testEqualsAndCompareTo() {
        Patient patient = new Patient(9999, "Max Patient", 12, 31, 2050, 100, 500, 110, 200);
        Patient patient2 = new Patient(9999, "Max Patient", 12, 31, 2050, 100, 500, 109.9995, 200);
        assertTrue(patient.equals(patient));
        assertTrue(patient.equals(patient2));
        assertTrue(patient2.equals(patient));
        assertEquals(0, patient.compareTo(patient2));
        assertEquals(0, patient2.compareTo(patient));

        Patient patient3 = new Patient(9998, "Max Patient", 12, 31, 2050, 100, 500, 110, 200);
        assertFalse(patient.equals(patient3));
        assertFalse(patient3.equals(patient));
        assertTrue(patient.compareTo(patient3) > 0);
        assertTrue(patient3.compareTo(patient) < 0);

        patient3 = new Patient(9999, "Maximum Patient", 12, 31, 2050, 100, 500, 110, 200);
        assertFalse(patient.equals(patient3));
        assertFalse(patient3.equals(patient));
        assertEquals(0, patient.compareTo(patient2));
        assertEquals(0, patient3.compareTo(patient));        

        patient3 = new Patient(9999, "Max Patient", 12, 30, 2050, 100, 500, 110, 200);
        assertFalse(patient.equals(patient3));
        assertFalse(patient3.equals(patient));
        assertEquals(0, patient.compareTo(patient3));
        assertEquals(0, patient3.compareTo(patient));         

        patient3 = new Patient(9999, "Max Patient", 12, 31, 2050, 99, 500, 110, 200);
        assertFalse(patient.equals(patient3));
        assertFalse(patient3.equals(patient));         
        assertEquals(0, patient.compareTo(patient3));
        assertEquals(0, patient3.compareTo(patient));    

        patient3 = new Patient(9999, "Max Patient", 12, 31, 2050, 100, 50, 110, 200);
        assertFalse(patient.equals(patient3));
        assertFalse(patient3.equals(patient));         
        assertEquals(0, patient.compareTo(patient3));
        assertEquals(0, patient3.compareTo(patient));             

        patient3 = new Patient(9999, "Max Patient", 12, 31, 2050, 100, 500, 98.6, 200);
        assertFalse(patient.equals(patient3));
        assertFalse(patient3.equals(patient));         
        assertEquals(0, patient.compareTo(patient3));
        assertEquals(0, patient3.compareTo(patient));

        patient3 = new Patient(9999, "Max Patient", 12, 31, 2050, 100, 500, 110, 100);
        assertFalse(patient.equals(patient3));
        assertFalse(patient3.equals(patient));         
        assertEquals(0, patient.compareTo(patient3));
        assertEquals(0, patient3.compareTo(patient));         

        assertFalse(patient.equals("Max Patient"));
        assertFalse(patient.equals(null));
   
    }
    
    /**
     * Tests constructor exception handling.
     */
    @Test
    public void testConstructorExceptions() {

        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> new Patient(0, null, 12, 32, 2000, 101, 501, 111, 201));
        assertEquals("Invalid id", exception.getMessage());
                
        exception = assertThrows(IllegalArgumentException.class,
                () -> new Patient(10000, null, 12, 32, 2000, 101, 501, 111, 201));
        assertEquals("Invalid id", exception.getMessage());
                
        exception = assertThrows(IllegalArgumentException.class,
                () -> new Patient(1000, null, 12, 32, 2000, 101, 501, 111, 201));
        assertEquals("Null name", exception.getMessage());
                
        exception = assertThrows(IllegalArgumentException.class,
                () -> new Patient(1000, "   ", 12, 32, 2000, 101, 501, 111, 201));
        assertEquals("Empty or all whitespace name", exception.getMessage());

        exception = assertThrows(IllegalArgumentException.class,
                () -> new Patient(1000, "Invalid Patient", 12, 32, 2000, 101, 501, 111, 201));
        assertEquals("Invalid day", exception.getMessage());

        exception = assertThrows(IllegalArgumentException.class,
                () -> new Patient(1000, "Invalid Patient", 1, 1, 1900, 101, 501, 111, 201));
        assertEquals("Invalid height", exception.getMessage());
                
        exception = assertThrows(IllegalArgumentException.class,
                () -> new Patient(1000, "Invalid Patient", 1, 1, 1900, 0, 501, 111, 201));
        assertEquals("Invalid height", exception.getMessage());
               
        exception = assertThrows(IllegalArgumentException.class,
                () -> new Patient(1000, "Invalid Patient", 1, 1, 1900, 1, 501, 111, 201));
        assertEquals("Invalid weight", exception.getMessage());
                            
        exception = assertThrows(IllegalArgumentException.class,
                () -> new Patient(1000, "Invalid Patient", 1, 1, 1900, 1, 0, 111, 201));
        assertEquals("Invalid weight", exception.getMessage());

        exception = assertThrows(IllegalArgumentException.class,
                () -> new Patient(1000, "Invalid Patient", 1, 1, 1900, 1, 1, 111, 201));
        assertEquals("Invalid temperature", exception.getMessage());
                
        exception = assertThrows(IllegalArgumentException.class,
                () -> new Patient(1000, "Invalid Patient", 1, 1, 1900, 1, 1, 89, 201));
        assertEquals("Invalid temperature", exception.getMessage());

        exception = assertThrows(IllegalArgumentException.class,
                () -> new Patient(1000, "Invalid Patient", 1, 1, 1900, 1, 1, 90, 201));
        assertEquals("Invalid heart rate", exception.getMessage());
                
        exception = assertThrows(IllegalArgumentException.class,
                () -> new Patient(1000, "Invalid Patient", 1, 1, 1900, 1, 1, 90, 24));
        assertEquals("Invalid heart rate", exception.getMessage());
    }
}