import java.util.Arrays;
import java.util.Comparator;

/**
 * Represents the patient records system.
 * 
 * @author Aanya Tomar
 */
public class PatientRecords {

    /** Comparator used to sort Patient objects */
    private static Comparator<Patient> patientCompWithNull =
        new Comparator<Patient>() {
            @Override
            public int compare(Patient o1, Patient o2) {
                if (o1 == null && o2 == null) {
                    return 0;
                }
                if (o1 == null) {
                    return 1;
                }
                if (o2 == null) {
                    return -1;
                }
                return o1.compareTo(o2);
            }
        };

    /** Default maximum number of patients. */
    public static final int DEFAULT_MAX_PATIENTS = 10;

    /** Maximum number of patients allowed. */
    private int maxPatients;

    /** Current number of patients stored. */
    private int numberOfPatients;

    /** Array of patients. */
    private Patient[] patients;

    /**
     * Constructs a PatientRecords object with default maximum size.
     */
    public PatientRecords() {
        maxPatients = DEFAULT_MAX_PATIENTS;
        numberOfPatients = 0;
        patients = new Patient[DEFAULT_MAX_PATIENTS];
    }

    /**
     * Constructs a PatientRecords object with a given maximum size.
     * 
     * @param maxPatients maximum number of patients
     * @throws IllegalArgumentException if maxPatients is non-positive
     */
    public PatientRecords(int maxPatients) {
        if (maxPatients <= 0) {
            throw new IllegalArgumentException("Invalid maxPatients");
        }

        this.maxPatients = maxPatients;
        numberOfPatients = 0;
        patients = new Patient[maxPatients];
    }

    /**
     * Returns whether the list is full.
     * 
     * @return true if full, false otherwise
     */
    public boolean isFull() {
        return numberOfPatients == maxPatients;
    }

    /**
     * Gets the maximum number of patients.
     * 
     * @return maximum number of patients
     */
    public int getMaxPatients() {
        return maxPatients;
    }

    /**
     * Gets the current number of patients.
     * 
     * @return number of patients
     */
    public int getNumberOfPatients() {
        return numberOfPatients;
    }

    /**
     * Gets the id of the patient at index i.
     * 
     * @param i index
     * @return id at index
     * @throws IllegalArgumentException if index is invalid
     */
    public int getIdAt(int i) {
        if (i < 0 || i >= numberOfPatients) {
            throw new IllegalArgumentException("Invalid index");
        }
        return patients[i].getId();
    }

    /**
     * Gets the name of the patient at index i.
     * 
     * @param i index
     * @return name at index
     * @throws IllegalArgumentException if index is invalid
     */
    public String getNameAt(int i) {
        if (i < 0 || i >= numberOfPatients) {
            throw new IllegalArgumentException("Invalid index");
        }
        return patients[i].getName();
    }

    /**
     * Gets the birthdate of the patient at index i.
     * 
     * @param i index
     * @return birthdate at index
     * @throws IllegalArgumentException if index is invalid
     */
    public Birthdate getBirthdateAt(int i) {
        if (i < 0 || i >= numberOfPatients) {
            throw new IllegalArgumentException("Invalid index");
        }
        return patients[i].getBirthdate();
    }

    /**
     * Gets the height of the patient at index i.
     * 
     * @param i index
     * @return height at index
     * @throws IllegalArgumentException if index is invalid
     */
    public int getHeightAt(int i) {
        if (i < 0 || i >= numberOfPatients) {
            throw new IllegalArgumentException("Invalid index");
        }
        return patients[i].getHeight();
    }

    /**
     * Gets the weight of the patient at index i.
     * 
     * @param i index
     * @return weight at index
     * @throws IllegalArgumentException if index is invalid
     */
    public int getWeightAt(int i) {
        if (i < 0 || i >= numberOfPatients) {
            throw new IllegalArgumentException("Invalid index");
        }
        return patients[i].getWeight();
    }

    /**
     * Gets the temperature of the patient at index i.
     * 
     * @param i index
     * @return temperature at index
     * @throws IllegalArgumentException if index is invalid
     */
    public double getTemperatureAt(int i) {
        if (i < 0 || i >= numberOfPatients) {
            throw new IllegalArgumentException("Invalid index");
        }
        return patients[i].getTemperature();
    }

    /**
     * Gets the heart rate of the patient at index i.
     * 
     * @param i index
     * @return heart rate at index
     * @throws IllegalArgumentException if index is invalid
     */
    public int getHeartRateAt(int i) {
        if (i < 0 || i >= numberOfPatients) {
            throw new IllegalArgumentException("Invalid index");
        }
        return patients[i].getHeartRate();
    }

    /**
     * Sorts the patients array.
     */
    private void sort() {
        Arrays.sort(patients, patientCompWithNull);
    }

    /**
     * Adds a Patient object to the patient list.
     * 
     * @param patient patient to add
     * @throws IllegalArgumentException if list is full, patient is null,
     *         or duplicate id exists
     */
    public void addPatient(Patient patient) {
        if (isFull()) {
            throw new IllegalArgumentException("Patient list is full");
        }

        if (patient == null) {
            throw new IllegalArgumentException("Null patient");
        }

        for (int i = 0; i < numberOfPatients; i++) {
            if (patients[i].getId() == patient.getId()) {
                throw new IllegalArgumentException("Duplicate id");
            }
        }

        patients[numberOfPatients] = patient;
        numberOfPatients++;
        sort();
    }

    /**
     * Adds a patient with the given data.
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
     * @throws IllegalArgumentException if any argument is invalid
     */
    public void addPatient(int id, String name, int month, int day, int year,
            int height, int weight, double temperature, int heartRate) {

        if (isFull()) {
            throw new IllegalArgumentException("Patient list is full");
        }

        for (int i = 0; i < numberOfPatients; i++) {
            if (patients[i].getId() == id) {
                throw new IllegalArgumentException("Duplicate id");
            }
        }

        Patient patient = new Patient(id, name, month, day, year, height,
                weight, temperature, heartRate);
        patients[numberOfPatients] = patient;
        numberOfPatients++;
        sort();
    }

    /**
     * Updates the patient with the given id.
     * 
     * @param id patient id
     * @param name updated name
     * @param month updated birth month
     * @param day updated birth day
     * @param year updated birth year
     * @param height updated height
     * @param weight updated weight
     * @param temperature updated temperature
     * @param heartRate updated heart rate
     * @throws IllegalArgumentException if patient not found or data invalid
     */
    public void updatePatient(int id, String name, int month, int day, int year,
            int height, int weight, double temperature, int heartRate) {

        int index = -1;
        for (int i = 0; i < numberOfPatients; i++) {
            if (patients[i].getId() == id) {
                index = i;
                break;
            }
        }

        if (index == -1) {
            throw new IllegalArgumentException("Patient not found");
        }

        patients[index] = new Patient(id, name, month, day, year, height,
                weight, temperature, heartRate);
        sort();
    }

    /**
     * Returns a CSV representation of the patient records.
     * 
     * @return CSV string
     */
    @Override
    public String toString() {
        String result = "Id,Name,Birthdate,Height,Weight,Temperature,HeartRate\n";

        for (int i = 0; i < numberOfPatients; i++) {
            result += patients[i].toString() + "\n";
        }

        return result;
    }

    /**
     * Returns whether this PatientRecords object equals another object.
     * 
     * @param o object to compare
     * @return true if equal, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof PatientRecords)) {
            return false;
        }

        PatientRecords other = (PatientRecords)o;

        if (maxPatients != other.maxPatients) {
            return false;
        }

        if (numberOfPatients != other.numberOfPatients) {
            return false;
        }

        for (int i = 0; i < numberOfPatients; i++) {
            if (!patients[i].equals(other.patients[i])) {
                return false;
            }
        }

        return true;
    }
}