/**
 * Represents a birthdate in the patient records system.
 * 
 * @author Aanya Tomar
 */
public class Birthdate {

    /** Number of months in a year. */
    public static final int NUMBER_OF_MONTHS = 12;

    /** Days per month where index 0 is unused. */
    public static final int[] DAYS_PER_MONTH =
        {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    /** Minimum valid year. */
    public static final int MIN_YEAR = 1900;

    /** Maximum valid year. */
    public static final int MAX_YEAR = 2050;

    /** February month number. */
    public static final int FEB = 2;

    /** Leap year frequency. */
    public static final int LEAP_YEAR_FREQUENCY = 4;

    /** Century divisor. */
    public static final int CENTURY = 100;

    /** Divisor used for 400-year leap rule. */
    private static final int FOUR_HUNDRED = 400;

    /** Birth month. */
    private int month;

    /** Birth day. */
    private int day;

    /** Birth year. */
    private int year;

    /**
     * Constructs a Birthdate object.
     * 
     * @param month birth month
     * @param day birth day
     * @param year birth year
     * @throws IllegalArgumentException if year, month, or day is invalid
     */
    public Birthdate(int month, int day, int year) {
        if (year < MIN_YEAR || year > MAX_YEAR) {
            throw new IllegalArgumentException("Invalid year");
        }

        if (month < 1 || month > NUMBER_OF_MONTHS) {
            throw new IllegalArgumentException("Invalid month");
        }

        int maxDay = DAYS_PER_MONTH[month];
        if (month == FEB && isLeapYear(year)) {
            maxDay++;
        }

        if (day < 1 || day > maxDay) {
            throw new IllegalArgumentException("Invalid day");
        }

        this.month = month;
        this.day = day;
        this.year = year;
    }

    /**
     * Returns whether a year is a leap year.
     * 
     * @param year year to check
     * @return true if leap year, false otherwise
     * @throws IllegalArgumentException if year is invalid
     */
    private boolean isLeapYear(int year) {
        if (year < MIN_YEAR || year > MAX_YEAR) {
            throw new IllegalArgumentException("Invalid year");
        }

        return year > 0
            && year % LEAP_YEAR_FREQUENCY == 0
            && (year % CENTURY != 0 || year % FOUR_HUNDRED == 0);
    }

    /**
     * Gets the month.
     * 
     * @return month
     */
    public int getMonth() {
        return month;
    }

    /**
     * Gets the day.
     * 
     * @return day
     */
    public int getDay() {
        return day;
    }

    /**
     * Gets the year.
     * 
     * @return year
     */
    public int getYear() {
        return year;
    }

    /**
     * Returns the birthdate in MM/DD/YYYY format.
     * 
     * @return formatted birthdate
     */
    @Override
    public String toString() {
        return String.format("%02d/%02d/%04d", month, day, year);
    }

    /**
     * Returns whether this Birthdate equals another object.
     * 
     * @param o object to compare
     * @return true if equal, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Birthdate)) {
            return false;
        }

        Birthdate other = (Birthdate)o;
        return month == other.month && day == other.day && year == other.year;
    }
}