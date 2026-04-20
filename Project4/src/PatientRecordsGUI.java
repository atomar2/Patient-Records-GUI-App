import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;

import javax.swing.*;
import javax.swing.table.*;


/**
 * This class creates a graphical representation of a PatientRecords system.
 * 
 * @author Jessica Young Schmidt
 * @author Michelle Glatz
 * @author Suzanne Balik
 */
@SuppressWarnings("serial")
public class PatientRecordsGUI extends JFrame implements ActionListener {
    
    /** Width */
    public static final int WIDTH = 700; 
    
    /** Height */
    public static final int HEIGHT = 800; 
    
    /** X position */
    public static final int X = 100;
    
    /** Y position */
    public static final int Y = 5;
    
    /** Grid rows */
    public static final int ROWS = 14;

    /** Grid columns */
    public static final int COLS = 2;    
    
    /** Inset size */
    public static final int INSET_SIZE = 5;
    
    /** Large field width */
    public static final int LARGE_FIELD_WIDTH = 30;
    
    /** Small field width */
    public static final int SMALL_FIELD_WIDTH = 7;  

    /** Patient list columns */
    public static final int PATIENT_LIST_COLS = 7;
    
    /** Patient list column width */
    public static final int PATIENT_LIST_COL_WIDTH = 120;
    
    /** Patient list name width */
    public static final int NAME_WIDTH = 250;   
    
    /** Patient list id max column width */
    public static final int ID_WIDTH = 50; 
   
    /** Patient list birthday max column width */
    public static final int BIRTHDAY_WIDTH = 120;
   
    /** Patient list height max column width */
    public static final int HEIGHT_WIDTH = 75;    
   
    /** Patient list weignt max column width */
    public static final int WEIGHT_WIDTH = 75;         
   
    /** Patient list temperature max column width */
    public static final int TEMP_WIDTH = 85;
    
    /** Patient list blood pressure max column width */
    public static final int HR_WIDTH = 75;  
    
    /** Patient list Id column number */
    public static final int ID_COL = 0;
       
    /** Patient list Name column number */
    public static final int NAME_COL = 1;  
    
    /** Patient list Birthdate column number */
    public static final int BDATE_COL = 2;
    
    /** Patient list Height column number */
    public static final int HEIGHT_COL = 3;
            
    /** Patient list Weigth column number */
    public static final int WEIGHT_COL = 4;
        
    /** Patient list Temperature column number */
    public static final int TEMP_COL = 5;
    
    /** Patient list Heart rate column number */
    public static final int HRATE_COL = 6;   
                            
    /** Table width */
    public static final int TABLE_WIDTH = 250;
    
    /** Table height */
    public static final int TABLE_HEIGHT = 500; 

    /** Table font size */
    public static final int TABLE_FONT_SIZE = 12;
    
    /** Label for max patients */
    private JLabel maxPatientsLabel;

    /** Text field for max patients */
    private JTextField maxPatientsText;

    /** Label for input file */
    private JLabel fileLabel;

    /** Text field input file */
    private JTextField fileText;

    /** Button for New */
    private JButton newButton;

    /** Label for adding Patient */
    private JLabel addPatientLabel;

    /** Label for id */
    private JLabel idLabel;

    /** Text field for id */
    private JTextField idText;

    /** Label for name */
    private JLabel nameLabel;

    /** Text field for name */
    private JTextField nameText;

    /** Label for birth month */
    private JLabel monthLabel;

    /** Text field for birth month */
    private JComboBox<Integer> monthText; 

    /** Label for birth day */
    private JLabel dayLabel;

    /** Text field for birth day */
    private JComboBox<Integer> dayText;

    /** Label for birth year */
    private JLabel yearLabel;

    /** Text field for birth year */
    private JComboBox<Integer> yearText;
    
    /** Label for height */
    private JLabel heightLabel;

    /** Text field for height */
    private JComboBox<Integer> heightText;

    /** Label for weight */
    private JLabel weightLabel;  

    /** Text field for weight */
    private JComboBox<Integer> weightText;

    /** Label for temperature */
    private JLabel tempLabel;  

    /** Text field for temperature */
    private JTextField tempText;

    /** Label for heart rate */
    private JLabel hrLabel;  

    /** Text field for heart rate */
    private JComboBox<Integer>  hrText;

    /** Add button */
    private JButton addButton;
    
    /** Update Patient button*/
    private JButton updateButton;
   
    /** Save button */
    private JButton saveToFileButton;

    /** Label output file */
    private JLabel fileOutputLabel;

    /** Text field for output file */
    private JTextField fileOutputText;

    /** PatientRecords for system */
    private PatientRecords patientRecords;

    /** Table for displaying patient list */
    private JTable table;

    /** Panel for data */
    private JPanel dataPanel;

    /**
     * Constructs GUI for PatientRecords
     */
    public PatientRecordsGUI() {

        super("PatientRecords GUI");
        
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
        
        patientRecords = null;

        setSize(WIDTH, HEIGHT);
        setLocation(X, Y);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Container cont = getContentPane();
        
        // Create a JPanel
        JPanel panel = new JPanel(new GridBagLayout());
    
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(INSET_SIZE,INSET_SIZE,INSET_SIZE,INSET_SIZE); 
        cont.add(panel, BorderLayout.CENTER);
        
        // first row for top panel
        c.gridx = 0;
        c.gridy = 0;    
        c.anchor = GridBagConstraints.WEST;
        
        JPanel topPanel = new JPanel(new GridLayout(ROWS, COLS));
        panel.add(topPanel, c);
         
        maxPatientsLabel = new JLabel("  Max Patients: ");
        topPanel.add(maxPatientsLabel);
        maxPatientsText = new JTextField(SMALL_FIELD_WIDTH);
        topPanel.add(maxPatientsText);

        fileLabel = new JLabel(
                "  Input filename (leave blank if no input file): ");
        topPanel.add(fileLabel);
        fileText = new JTextField(LARGE_FIELD_WIDTH);
        topPanel.add(fileText);

        newButton = new JButton("New Patient List");
        topPanel.add(newButton);

        addPatientLabel = new JLabel("Add or Update Patient: ");
        topPanel.add(addPatientLabel);
        idLabel = new JLabel("Id (1000 - 9999): ");
        topPanel.add(idLabel);
        idText = new JTextField(SMALL_FIELD_WIDTH);
        topPanel.add(idText);
        
        nameLabel = new JLabel("Name: ");
        topPanel.add(nameLabel);
        nameText = new JTextField(SMALL_FIELD_WIDTH);
        topPanel.add(nameText);
        monthLabel = new JLabel("Birth Month: ");
        topPanel.add(monthLabel);
        monthText = new JComboBox<Integer>();
        monthText.setFont(new Font("Dialog", Font.PLAIN, TABLE_FONT_SIZE));
        monthText.setBackground(Color.white); 
        for (int i = 1; i <= Birthdate.NUMBER_OF_MONTHS; i++) {
            monthText.addItem(i);
        }            
        topPanel.add(monthText);

        dayLabel = new JLabel("Birth Day: ");
        topPanel.add(dayLabel);
        dayText = new JComboBox<Integer>();
        dayText.setFont(new Font("Dialog", Font.PLAIN, TABLE_FONT_SIZE));
        dayText.setBackground(Color.white); 
        for (int i = 1; i <= Birthdate.DAYS_PER_MONTH[1]; i++) {
            dayText.addItem(i);
        }
        topPanel.add(dayText);

        yearLabel = new JLabel("Birth Year: ");
        topPanel.add(yearLabel); 
        yearText = new JComboBox<Integer>();
        yearText.setFont(new Font("Dialog", Font.PLAIN, TABLE_FONT_SIZE));
        yearText.setBackground(Color.white); 
        for (int i = Birthdate.MIN_YEAR; i <= Birthdate.MAX_YEAR; i++) {
            yearText.addItem(i);
        }       
        topPanel.add(yearText);   
        
        heightLabel = new JLabel("Height (inches): ");
        topPanel.add(heightLabel);
        heightText = new JComboBox<Integer>();
        heightText.setFont(new Font("Dialog", Font.PLAIN, TABLE_FONT_SIZE));
        heightText.setBackground(Color.white); 
        for (int i = 1; i <= Patient.MAX_HEIGHT; i++) {
            heightText.addItem(i);
        }       
        topPanel.add(heightText);  

        weightLabel = new JLabel("Weight (lbs): ");
        topPanel.add(weightLabel);
        weightText = new JComboBox<Integer>();
        weightText.setFont(new Font("Dialog", Font.PLAIN, TABLE_FONT_SIZE));
        weightText.setBackground(Color.white); 
        for (int i = 1; i <= Patient.MAX_WEIGHT; i++) {
            weightText.addItem(i);
        }       
        topPanel.add(weightText);  

        tempLabel = new JLabel("Temperature (degrees F): ");
        topPanel.add(tempLabel);
        tempText = new JTextField(SMALL_FIELD_WIDTH);
        topPanel.add(tempText); 

        hrLabel = new JLabel("Heart Rate (bpm): ");
        topPanel.add(hrLabel);
        hrText = new JComboBox<Integer>();
        hrText.setFont(new Font("Dialog", Font.PLAIN, TABLE_FONT_SIZE));
        hrText.setBackground(Color.white); 
        for (int i = Patient.MIN_HEART_RATE; i <= Patient.MAX_HEART_RATE; i++) {
            hrText.addItem(i);
        }  
        topPanel.add(hrText);        

        // Don't display an initial value in drop down menus*/ 
        monthText.setSelectedIndex(-1);       
        dayText.setSelectedIndex(-1);        
        yearText.setSelectedIndex(-1);        
        heightText.setSelectedIndex(-1); 
        weightText.setSelectedIndex(-1);
        hrText.setSelectedIndex(-1);
        
        //Label text fields for screen reader accessibility
        idLabel.setLabelFor(idText);
        nameLabel.setLabelFor(nameText);
        monthLabel.setLabelFor(monthText);
        dayLabel.setLabelFor(dayText);
        yearLabel.setLabelFor(yearText);
        heightLabel.setLabelFor(heightText);
        weightLabel.setLabelFor(weightText);
        tempLabel.setLabelFor(tempText);
        hrLabel.setLabelFor(hrText);

        addButton = new JButton("Add Patient");
        topPanel.add(addButton);
        
        updateButton = new JButton("Update Patient");
        topPanel.add(updateButton);

        dataPanel = new JPanel();
        
        //PatientRecords table
        c.gridx = 0;
        // row 1
        c.gridy = 1;       
        c.anchor = GridBagConstraints.CENTER;

        panel.add(dataPanel, c);
               
        JPanel outputpanel = new JPanel();

        fileOutputLabel = new JLabel("Output filename: ");
        
        outputpanel.add(fileOutputLabel);
        fileOutputText = new JTextField(LARGE_FIELD_WIDTH);
        outputpanel.add(fileOutputText);

        saveToFileButton = new JButton("Save to File");
        outputpanel.add(saveToFileButton);
        
     
        //outpanel
        c.gridx = 0;
        // row 3
        c.gridy = COLS;           
        c.anchor = GridBagConstraints.WEST;
        panel.add(outputpanel, c);
              
        
        JPanel space = new JPanel();
        //column 0 for bottom space
        c.gridx = 0;
        // row 4
        c.gridy = COLS + 1;       
        c.anchor = GridBagConstraints.WEST;
        c.weighty = 1;       
        panel.add(space, c);        


        addPatientLabel.setVisible(false);
        idLabel.setVisible(false);
        idText.setVisible(false);
        nameLabel.setVisible(false);
        nameText.setVisible(false);
        monthLabel.setVisible(false);
        monthText.setVisible(false);
        dayLabel.setVisible(false);
        dayText.setVisible(false);
        yearLabel.setVisible(false);
        yearText.setVisible(false);
        heightLabel.setVisible(false);
        heightText.setVisible(false);
        weightLabel.setVisible(false);
        weightText.setVisible(false);
        tempLabel.setVisible(false);
        tempText.setVisible(false);
        hrLabel.setVisible(false);
        hrText.setVisible(false);        
        addButton.setVisible(false);
        fileOutputLabel.setVisible(false);
        fileOutputText.setVisible(false);
        saveToFileButton.setVisible(false);
        updateButton.setVisible(false);
        
        
        newButton.addActionListener(this);
        addButton.addActionListener(this);
        updateButton.addActionListener(this);        
        saveToFileButton.addActionListener(this);
        

        setVisible(true);
    }

    /**
     * Starts PatientRecordsGUI
     * 
     * @param args command line arguments
     */
    public static void main(String[] args) {
        new PatientRecordsGUI();
    }

    /**
     * Handle processing of button presses
     * 
     * @param e event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == newButton) {
            try {
                int maxPatients = Integer.parseInt(maxPatientsText.getText());
                String fname = fileText.getText().trim();

                // Create PatientRecords either empty or from file
                if (fname.equals("")) {
                    patientRecords = new PatientRecords(maxPatients);
                } 
                else {
                    patientRecords = PatientRecordsFileProcessing.readPatientRecordsFromFile(fname,
                                     maxPatients);
                }

                // make initial fields and text boxes invisible         
                maxPatientsText.setEditable(false);
                fileText.setEditable(false);
                maxPatientsText.setVisible(false);
                fileText.setVisible(false);
                maxPatientsLabel.setVisible(false);
                fileLabel.setVisible(false);
                newButton.setVisible(false);

                if (patientRecords.isFull()) {
                    addPatientLabel.setText("Patient list is full.  Update Patient:");
                    addButton.setEnabled(false);
                }    

                idLabel.setVisible(true);
                idText.setVisible(true);
                nameLabel.setVisible(true);
                nameText.setVisible(true);
                monthLabel.setVisible(true);
                monthText.setVisible(true);
                dayLabel.setVisible(true);
                dayText.setVisible(true);
                yearLabel.setVisible(true);
                yearText.setVisible(true);
                heightLabel.setVisible(true);
                heightText.setVisible(true);
                weightLabel.setVisible(true);
                weightText.setVisible(true);
                tempLabel.setVisible(true);
                tempText.setVisible(true);
                hrLabel.setVisible(true);
                hrText.setVisible(true);
                
                addButton.setVisible(true);
                updateButton.setVisible(true);


                // Make fields and text boxes visible
                addPatientLabel.setVisible(true);
                fileOutputLabel.setVisible(true);
                fileOutputText.setVisible(true);
                saveToFileButton.setVisible(true);

                // Initialize table for displaying PatientRecords
                String[] columnNames = {"Id", "Name", "Birthdate",
                                        "Height", "Weight", "Temp", "HR" };

                Object[][] data = new Object[Integer
                        .parseInt(maxPatientsText.getText())][PATIENT_LIST_COLS];

                table = new JTable(data, columnNames) {
                    @Override
                    public boolean isCellEditable(int row, int column) {
                        return false;
                    }
                };
                
                
                // Get the column model to set widths
                TableColumnModel columnModel = table.getColumnModel();                
                table.setFont(new Font(Font.MONOSPACED, Font.PLAIN, TABLE_FONT_SIZE));  
                
                columnModel.getColumn(ID_COL).setMaxWidth(ID_WIDTH);
                columnModel.getColumn(BDATE_COL).setMaxWidth(BIRTHDAY_WIDTH); 
                columnModel.getColumn(HEIGHT_COL).setMaxWidth(HEIGHT_WIDTH);
                columnModel.getColumn(WEIGHT_COL).setMaxWidth(WEIGHT_WIDTH); 
                columnModel.getColumn(TEMP_COL).setMaxWidth(TEMP_WIDTH);
                columnModel.getColumn(HRATE_COL).setMaxWidth(HR_WIDTH); 
                for (int i = 0; i < PATIENT_LIST_COLS; i++) {       
                    if (i == 1) {
                        columnModel.getColumn(i).setPreferredWidth(NAME_WIDTH); 
                    } else {
                        columnModel.getColumn(i).setPreferredWidth(PATIENT_LIST_COL_WIDTH); 
                    }
                }
                     
                DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
                rightRenderer.setHorizontalAlignment(JLabel.RIGHT);
                DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
                centerRenderer.setHorizontalAlignment( JLabel.CENTER );
                table.getColumnModel().getColumn(ID_COL).setCellRenderer(centerRenderer);
                table.getColumnModel().getColumn(BDATE_COL).setCellRenderer(centerRenderer);
                table.getColumnModel().getColumn(HEIGHT_COL).setCellRenderer(centerRenderer);
                table.getColumnModel().getColumn(WEIGHT_COL).setCellRenderer(centerRenderer);
                table.getColumnModel().getColumn(TEMP_COL).setCellRenderer(centerRenderer);
                table.getColumnModel().getColumn(HRATE_COL).setCellRenderer(centerRenderer);
                table.setPreferredScrollableViewportSize(
                        new Dimension(TABLE_HEIGHT, TABLE_WIDTH));
                table.setFillsViewportHeight(true);
                
                // Create the scroll pane and add the table to it.
                JScrollPane scrollPane = new JScrollPane(table);

                // Add the scroll pane to this panel.
                dataPanel.add(scrollPane);
                fillTable(patientRecords);
            } catch (NumberFormatException nfe) {
                JOptionPane.showMessageDialog(null, 
                           "Max Patients value is invalid - must be an integer.");
            } catch (IllegalArgumentException iae) {
                if(iae.getMessage().equals("Patient list is full")) {
                    JOptionPane.showMessageDialog(null, "Invalid max patients for given file.");
                } else {
                    JOptionPane.showMessageDialog(null, iae.getMessage());
                }
            }
        } else if (e.getSource() == addButton || e.getSource() == updateButton) {
            try {  
                int id = Integer.parseInt(idText.getText());
                String name = nameText.getText().trim();
                int month = (int) monthText.getSelectedItem();
                int day = (int) dayText.getSelectedItem();
                int year = (int) yearText.getSelectedItem();               
                int height = (int) heightText.getSelectedItem();
                int weight = (int) weightText.getSelectedItem();
                double temp = Double.parseDouble(tempText.getText());
                int heartRate = (int) hrText.getSelectedItem();

                if (e.getSource() == addButton) {
                    patientRecords.addPatient(id, name, month, day, year, 
                                              height, weight, temp, heartRate);
                    if (patientRecords.isFull()) {
                        addButton.setEnabled(false);         
                        addPatientLabel.setText("Patient list is full.  Update Patient:");
                    }                           
                } else {
                    patientRecords.updatePatient(id, name, month, day, year, 
                                                 height, weight, temp, heartRate);
                }
                
                idText.setText("");
                nameText.setText("");
                monthText.setSelectedIndex(-1);
                dayText.setSelectedIndex(-1);
                yearText.setSelectedIndex(-1);
                heightText.setSelectedIndex(-1);
                weightText.setSelectedIndex(-1);
                tempText.setText("");
                hrText.setSelectedIndex(-1);


                fillTable(patientRecords);

            } catch (NumberFormatException nfe) {
                JOptionPane.showMessageDialog(null, 
                    "Id must be an integer and/or Temperature must be numerical.");
            } catch (IllegalArgumentException iae) {
                JOptionPane.showMessageDialog(null, iae.getMessage());
            } catch (NullPointerException iae) {
                JOptionPane.showMessageDialog(null, 
                    "Must select a value for Birthdate values, Height, Weight, and Heart Rate.");
            }
        } else if (e.getSource() == saveToFileButton) {
            try {
                String filename = fileOutputText.getText().trim();
                PatientRecordsFileProcessing.writePatientRecordsToFile(filename, patientRecords);
                JOptionPane.showMessageDialog(null, "Saved to " + filename);
            } catch (IllegalArgumentException iae) {
                JOptionPane.showMessageDialog(null, iae.getMessage());
            }
        } 
    }

    /**
     * Fills table with updated values
     * @param patientRecords PatientRecords
     */
    private void fillTable(PatientRecords patientRecords) {
        
        int numberOfPatients = patientRecords.getNumberOfPatients();
        int maxPatients = patientRecords.getMaxPatients();
        for (int i = 0; i < maxPatients; i++) {
            if (i < numberOfPatients) {
                int id = patientRecords.getIdAt(i);
                String name = patientRecords.getNameAt(i);
                Birthdate birthdate = patientRecords.getBirthdateAt(i);
                int height = patientRecords.getHeightAt(i);
                int weight = patientRecords.getWeightAt(i);
                double temperature = patientRecords.getTemperatureAt(i);
                int heartRate = patientRecords.getHeartRateAt(i);
                table.setValueAt(String.format("%04d", id), i, ID_COL);
                table.setValueAt(name, i, NAME_COL);
                table.setValueAt(birthdate.toString(), i, BDATE_COL);
                table.setValueAt(String.format("%d", height), i, HEIGHT_COL);
                table.setValueAt(String.format("%d", weight), i, WEIGHT_COL);
                table.setValueAt(String.format("%5.1f", temperature), i, TEMP_COL);
                table.setValueAt(String.format("%d", heartRate), i, HRATE_COL);
                
            } 
            else {
                table.setValueAt("", i, ID_COL);
                table.setValueAt("", i, NAME_COL);
                table.setValueAt("", i, BDATE_COL);
                table.setValueAt("", i, HEIGHT_COL);
                table.setValueAt("", i, WEIGHT_COL);
                table.setValueAt("", i, TEMP_COL);
                table.setValueAt("", i, HRATE_COL);
            }
        }
        table.revalidate();
    }
}