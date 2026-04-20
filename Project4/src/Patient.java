/**
 * Represents a patient in the patient records system.
 * 
 * @author Aanya Tomar
 */
public class Patient implements Comparable<Patient> {

    /** Minimum valid patient id. */
    public static final int MIN_ID = 1000;

    /** Maximum valid patient id. */
    public static final int MAX_ID = 9999;

    /** Maximum valid height. */
    public static final int MAX_HEIGHT = 100;

    /** Maximum valid weight. */
    public static final int MAX_WEIGHT = 500;

    /** Minimum valid temperature. */
    public static final int MIN_TEMPERATURE = 90;

    /** Maximum valid temperature. */
    public static final int MAX_TEMPERATURE = 110;

    /** Minimum valid heart rate. */
    public static final int MIN_HEART_RATE = 25;

    /** Maximum valid heart rate. */
    public static final int MAX_HEART_RATE = 200;

    /** Delta used when comparing temperatures. */
    public static final double DELTA = 0.001;

    /** Patient id. */
    private int id;

    /** Patient name. */
    private String name;

    /** Patient birthdate. */
    private Birthdate birthdate;

    /** Patient height. */
    private int height;

    /** Patient weight. */
    private int weight;

    /** Patient temperature. */
    private double temperature;

    /** Patient heart rate. */
    private int heartRate;

    /**
     * Constructs a Patient object.
     * 
     * @param id patient id
     * @param name patient name
     * @param month birth month
     * @param day birth day
     * @param year birth year
     * @param height patient height
     * @param weight patient weight
     * @param temperature patient temperature
     * @param heartRate patient heart rate
     * @throws IllegalArgumentException if any parameter is invalid
     */
    public Patient(int id, String name, int month, int day, int year,
            int height, int weight, double temperature, int heartRate) {

        if (id < MIN_ID || id > MAX_ID) {
            throw new IllegalArgumentException("Invalid id");
        }

        if (name == null) {
            throw new IllegalArgumentException("Null name");
        }

        if (name.trim().equals("")) {
            throw new IllegalArgumentException("Empty or all whitespace name");
        }

        Birthdate tempBirthdate = new Birthdate(month, day, year);

        if (height < 1 || height > MAX_HEIGHT) {
            throw new IllegalArgumentException("Invalid height");
        }

        if (weight < 1 || weight > MAX_WEIGHT) {
            throw new IllegalArgumentException("Invalid weight");
        }

        if (temperature < MIN_TEMPERATURE || temperature > MAX_TEMPERATURE) {
            throw new IllegalArgumentException("Invalid temperature");
        }

        if (heartRate < MIN_HEART_RATE || heartRate > MAX_HEART_RATE) {
            throw new IllegalArgumentException("Invalid heart rate");
        }

        this.id = id;
        this.name = name;
        this.birthdate = tempBirthdate;
        this.height = height;
        this.weight = weight;
        this.temperature = temperature;
        this.heartRate = heartRate;
    }

    /**
     * Gets the patient id.
     * 
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * Gets the patient name.
     * 
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the patient birthdate.
     * 
     * @return birthdate
     */
    public Birthdate getBirthdate() {
        return birthdate;
    }

    /**
     * Gets the patient height.
     * 
     * @return height
     */
    public int getHeight() {
        return height;
    }

    /**
     * Gets the patient weight.
     * 
     * @return weight
     */
    public int getWeight() {
        return weight;
    }

    /**
     * Gets the patient temperature.
     * 
     * @return temperature
     */
    public double getTemperature() {
        return temperature;
    }

    /**
     * Gets the patient heart rate.
     * 
     * @return heart rate
     */
    public int getHeartRate() {
        return heartRate;
    }

    /**
     * Returns a CSV string representation of the patient.
     * 
     * @return CSV string
     */
    @Override
    public String toString() {
        return id + "," + name + "," + birthdate + "," + height + ","
            + weight + "," + temperature + "," + heartRate;
    }

    /**
     * Returns whether this Patient equals another object.
     * 
     * @param o object to compare
     * @return true if equal, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Patient)) {
            return false;
        }

        Patient other = (Patient)o;
        return id == other.id
            && name.equals(other.name)
            && birthdate.equals(other.birthdate)
            && height == other.height
            && weight == other.weight
            && Math.abs(temperature - other.temperature) < DELTA
            && heartRate == other.heartRate;
    }

    /**
     * This method is used for sorting Patient objects by id
     * 
     * @param other Patient object to which this Patient is being compared
     * @return negative value if this Patient should be before the other Patient,
     *         positive value if this Patient should be after the other Patient,
     *         0 if the Patient ids are the same
     */
    @Override
    public int compareTo(Patient other) {
        return this.id - other.id;
    }
}