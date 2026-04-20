import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Tests the Birthdate class including constants, constructor,
 * getters, toString, equals, and exception handling.
 * 
 * @author Jessica Young Schmidt
 * @author Michelle Glatz
 * @author Aanya Tomar
 */
public class BirthdateTest {

    /**
     * Tests that all public constants in Birthdate
     * have the correct values.
     */
    @Test
    public void testPublicConstants() {
        assertEquals(1900, Birthdate.MIN_YEAR,
                "Tests value of Birthdate.MIN_YEAR");
        assertEquals(2050, Birthdate.MAX_YEAR,
                "Tests value of Birthdate.MAX_YEAR"); 
        assertEquals(12, Birthdate.NUMBER_OF_MONTHS,
                "Tests value of Birthdate.NUMBER_OF_MONTHS");
        assertEquals(2, Birthdate.FEB,
                "Tests value of Birthdate.FEB"); 
        assertEquals(4, Birthdate.LEAP_YEAR_FREQUENCY,
                "Tests value of Birthdate.LEAP_YEAR_FREQUENCY");
        assertEquals(100, Birthdate.CENTURY,
                "Tests value of Birthdate.CENTURY");               
        assertArrayEquals(new int[] {0, 31, 28, 31, 30, 31, 30,
                                     31, 31, 30, 31, 30, 31},
                Birthdate.DAYS_PER_MONTH,
                "Tests value of Birthdate.DAYS_PER_MONTH");                                
    }
    
    /**
     * Tests the constructor and getter methods
     * using valid Birthdate objects.
     */
    @Test
    public void testConstructorAndGetters() {
        Birthdate date = new Birthdate(7, 19, 1924);
        assertEquals(1924, date.getYear());
        assertEquals(7, date.getMonth());
        assertEquals(19, date.getDay());

        date = new Birthdate(11, 8, 2040);
        assertEquals(2040, date.getYear());
        assertEquals(11, date.getMonth());
        assertEquals(8, date.getDay());
    }

    /**
     * Tests the constructor and getter methods
     * using additional valid Birthdate values.
     */
    @Test
    public void testConstructorAndGettersStudent() {
        Birthdate date = new Birthdate(12, 31, 2000);
        assertEquals(2000, date.getYear(),
                "Testing getYear()");
        assertEquals(12, date.getMonth(),
                "Testing getMonth()");
        assertEquals(31, date.getDay(),
                "Testing getDay()");
    }

    /**
     * Tests the toString method to ensure
     * correct formatting of dates.
     */
    @Test
    public void testToString() {
        Birthdate date = new Birthdate(2, 29, 2024);
        assertEquals("02/29/2024", date.toString());
        date = new Birthdate(11, 5, 2040);
        assertEquals("11/05/2040", date.toString());
    }

    /**
     * Tests the toString method using additional
     * Birthdate values.
     */
    @Test
    public void testToStringStudent() {
        Birthdate date = new Birthdate(1, 1, 1900);
        assertEquals("01/01/1900", date.toString(),
                "Testing toString()");
    } 

    /**
     * Tests the equals method for correct comparison
     * of Birthdate objects.
     */
    @Test
    public void testEquals() {
        Birthdate date = new Birthdate(2, 28, 2024);
        Birthdate date2 = new Birthdate(2, 28, 2024);
        assertTrue(date.equals(date));
        assertTrue(date.equals(date2));
        assertTrue(date2.equals(date));
        Birthdate date3 = new Birthdate(2, 28, 2025);
        assertFalse(date.equals(date3));
        date3 = new Birthdate(2, 27, 2024);
        assertFalse(date.equals(date3));
        date3 = new Birthdate(3, 28, 2024);
        assertFalse(date.equals(date3));
        assertFalse(date.equals("03/28/2024"));
        assertFalse(date.equals(null));
    }    
    
    /**
     * Tests that the constructor throws appropriate
     * exceptions for invalid inputs.
     */
    @Test
    public void testConstructorExceptions() {

        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> new Birthdate(1, 2, 1899));
        assertEquals("Invalid year", exception.getMessage());

        exception = assertThrows(IllegalArgumentException.class,
                () -> new Birthdate(1, 2, 3000));
        assertEquals("Invalid year", exception.getMessage());

        exception = assertThrows(IllegalArgumentException.class,
                () -> new Birthdate(11, 31, 2020));
        assertEquals("Invalid day", exception.getMessage());

        exception = assertThrows(IllegalArgumentException.class,
                () -> new Birthdate(11, -11, 2020));
        assertEquals("Invalid day", exception.getMessage());

        exception = assertThrows(IllegalArgumentException.class,
                () -> new Birthdate(2, 29, 2021));
        assertEquals("Invalid day", exception.getMessage());

        exception = assertThrows(IllegalArgumentException.class,
                () -> new Birthdate(13, 1, 2040));
        assertEquals("Invalid month", exception.getMessage());

        exception = assertThrows(IllegalArgumentException.class,
                () -> new Birthdate(-1, -1, -2040));
        assertEquals("Invalid year", exception.getMessage());

        exception = assertThrows(IllegalArgumentException.class,
                () -> new Birthdate(1, 0, 2040));
        assertEquals("Invalid day", exception.getMessage());

        exception = assertThrows(IllegalArgumentException.class,
                () -> new Birthdate(0, 2, 2040));
        assertEquals("Invalid month", exception.getMessage());

        exception = assertThrows(IllegalArgumentException.class,
                () -> new Birthdate(-1, 2, 2040));
        assertEquals("Invalid month", exception.getMessage());
    }
}