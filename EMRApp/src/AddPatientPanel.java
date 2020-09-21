import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.text.*;
import java.util.Date;

public class AddPatientPanel extends JPanel {

    private EventListenerList listenerList = new EventListenerList();

    // Constructor that is called when we're adding a new patient
    public AddPatientPanel() {
        Dimension size = getPreferredSize();
        size.width = 400;
        setPreferredSize(size);

        setBorder(BorderFactory.createTitledBorder("Add New Patient"));

        setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();

        // Adding fields to add patient info and vitals
        JLabel name = new JLabel("Name");
        gc.weightx = 0;
        gc.weighty = 0;
        gc.gridx = 0;
        gc.gridy = 0;
        add(name,gc);

        JTextField nameField = new JTextField(15);
        TextPrompt namePrompt = new TextPrompt("Ex: John Doe", nameField);
        namePrompt.setForeground(Color.GRAY);
        nameField.add(namePrompt);
        gc.anchor = GridBagConstraints.NORTH;
        gc.weightx = 1;
        gc.weighty = 1;
        gc.gridx = 0;
        gc.gridy = 1;
        add(nameField, gc);

        /*
         * JTextField ageField = new JTextField(15); TextPrompt agePrompt = new
         * TextPrompt("Age", ageField); agePrompt.setForeground(Color.GRAY);
         * ageField.add(agePrompt); gc.gridx = 0; gc.gridy = 1; add(ageField, gc);
         */

        /*
        JTextField PIDField = new JTextField(15);
        TextPrompt PIDPrompt = new TextPrompt("PID ######", PIDField);
        PIDPrompt.setForeground(Color.GRAY);
        PIDField.add(PIDPrompt);
        gc.gridx = 0;
        gc.gridy = 1;
        add(PIDField, gc);
        */

        JLabel DOB = new JLabel("Date of Birth");
        gc.weightx = 0;
        gc.weighty = 0;
        gc.gridx = 0;
        gc.gridy = 2;
        add(DOB, gc);

        JTextField DOBField = new JTextField(15);
        TextPrompt DOBPrompt = new TextPrompt("mm/dd/yyyy", DOBField);
        DOBPrompt.setForeground(Color.GRAY);
        DOBField.add(DOBPrompt);
        gc.weightx = 1;
        gc.weighty = 1;
        gc.gridx = 0;
        gc.gridy = 3;
        add(DOBField, gc);

        JLabel height = new JLabel("Height (cm)");
        gc.weightx = 0;
        gc.weighty = 0;
        gc.gridx = 0;
        gc.gridy = 4;
        add(height, gc);

        JTextField heightField = new JTextField(15);
        TextPrompt heightPrompt = new TextPrompt("Ex: 155", heightField);
        heightPrompt.setForeground(Color.GRAY);
        heightField.add(heightPrompt);
        gc.weightx = 0;
        gc.weighty = 0;
        gc.gridx = 0;
        gc.gridy = 5;
        add(heightField, gc);

        JLabel weight = new JLabel("Weight (lbs)");
        gc.weightx = 0;
        gc.weighty = 0;
        gc.gridx = 0;
        gc.gridy = 6;
        add(weight, gc);

        JTextField weightField = new JTextField(15);
        TextPrompt weightPrompt = new TextPrompt("Ex: 165", weightField);
        weightPrompt.setForeground(Color.GRAY);
        weightField.add(weightPrompt);
        gc.weightx = 1;
        gc.weighty = 1;
        gc.gridx = 0;
        gc.gridy = 7;
        add(weightField, gc);

        JLabel BPM = new JLabel("BPM");
        gc.weightx = 0;
        gc.weighty = 0;
        gc.gridx = 0;
        gc.gridy = 8;
        add(BPM, gc);

        JTextField BPMField = new JTextField(15);
        TextPrompt BPMPrompt = new TextPrompt("Ex: 85", BPMField);
        BPMPrompt.setForeground(Color.GRAY);
        BPMField.add(BPMPrompt);
        gc.weightx = 1;
        gc.weighty = 1;
        gc.gridx = 0;
        gc.gridy = 9;
        add(BPMField, gc);

        JLabel sex = new JLabel("Sex");
        gc.weightx = 0;
        gc.weighty = 0;
        gc.gridx = 1;
        gc.gridy = 0;
        add(sex, gc);

        String sexOptions[] = { "Male", "Female", "Other" };
        JComboBox<String> sexField = new JComboBox<String>(sexOptions);
        sexField.setEditable(true);
        sexField.setSelectedItem(new String("<Select Sex>"));
        sexField.setBackground(Color.WHITE);
        sexField.setSize(sexField.getPreferredSize());
        Font font = new Font("Dialog", Font.PLAIN, 12);
        sexField.setFont(font);
        gc.weightx = 1;
        gc.weighty = 1;
        gc.gridx = 1;
        gc.gridy = 1;
        add(sexField, gc);

        JLabel notes = new JLabel("Notes");
        gc.weightx = 0;
        gc.weighty = 0;
        gc.gridx = 1;
        gc.gridy = 2;
        add(notes, gc);

        JTextField notesField = new JTextField(15);
        TextPrompt notesPrompt = new TextPrompt("Notes...", notesField);
        notesPrompt.setForeground(Color.GRAY);
        notesField.add(notesPrompt);
        gc.weightx = 1;
        gc.weighty = 1;
        gc.gridx = 1;
        gc.gridy = 3;
        add(notesField, gc);

        JLabel temperature = new JLabel("Temperature (F)");
        gc.weightx = 0;
        gc.weighty = 0;
        gc.gridx = 1;
        gc.gridy = 4;
        add(temperature, gc);

        JTextField temperatureField = new JTextField(15);
        TextPrompt temperaturePrompt = new TextPrompt("Ex: 98.2", temperatureField);
        temperaturePrompt.setForeground(Color.GRAY);
        temperatureField.add(temperaturePrompt);
        gc.weightx = 1;
        gc.weighty = 1;
        gc.gridx = 1;
        gc.gridy = 5;
        add(temperatureField, gc);

        JLabel BP = new JLabel("Blood Pressure");
        gc.weightx = 0;
        gc.weighty = 0;
        gc.gridx = 1;
        gc.gridy = 6;
        add(BP, gc);

        JTextField BPField = new JTextField(15);
        TextPrompt BPPrompt = new TextPrompt("Ex: 120/75", BPField);
        BPPrompt.setForeground(Color.GRAY);
        BPField.add(BPPrompt);
        gc.weightx = 1;
        gc.weighty = 1;
        gc.gridx = 1;
        gc.gridy = 7;
        add(BPField, gc);

        JLabel position = new JLabel("Position");
        gc.weightx = 0;
        gc.weighty = 0;
        gc.gridx = 1;
        gc.gridy = 8;
        add(position, gc);

        JTextField positionField = new JTextField(15);
        TextPrompt positionPrompt = new TextPrompt("Ex: Sitting up", positionField);
        positionPrompt.setForeground(Color.GRAY);
        positionField.add(positionPrompt);
        gc.gridx = 1;
        gc.gridy = 9;
        add(positionField, gc);

        // Create Add New Patient Button
        JButton finishButton = new JButton("Finish");

        // Add a listener to change back to Main frame and save data
        finishButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                if (nameField.getText().equals("") || DOBField.getText().equals("")
                        || sexField.getSelectedItem().toString().equals("") || notesField.getText().equals("")
                        || heightField.getText().equals("") || weightField.getText().equals("")
                        || BPMField.getText().equals("") || temperatureField.getText().equals("")
                        || BPField.getText().equals("") || positionField.getText().equals("")) {
                    System.out.println("Please do not leave any fields empty.");
                    JOptionPane.showMessageDialog(null, "Please do not leave any fields empty.");
                    return;
                }

                // Checking to see if the sex field is not male, female, or other
                if (!sexField.getSelectedItem().toString().equals("Male")
                        && !sexField.getSelectedItem().toString().equals("Female")
                        && !sexField.getSelectedItem().toString().equals("Other")) {
                    System.out.println("The patient's sex must be selected from the dropdown.");
                    JOptionPane.showMessageDialog(null, "The patient's sex must be selected from the dropdown.");
                    return;
                }

                /*
                //Checking to see if PID is a valid integer
                if (PIDField.getText().size() == 6) {
                    try {
                        if(Integer.parseInt(PIDField.getText()) <= 0) {
                            System.out.println("The PID must be a 6-digit integer.");
                            JOptionPane.showMessageDialog(null, "The PID must be a 6-digit integer.");
                            return;
                        }
                    } catch (NumberFormatException e) { 
                        System.out.println("The PID must be a 6-digit integer.");
                        JOptionPane.showMessageDialog(null, "The PID must be a 6-digit integer.");
                        return;
                    }
                } else {
                    System.out.println("The PID must be a 6-digit integer.");
                    JOptionPane.showMessageDialog(null, "The PID must be a 6-digit integer.");
                    return;
                }
                */

                //Checking to see if the height is a valid number
                try {
                    if(Integer.parseInt(heightField.getText()) <= 0 || Integer.parseInt(heightField.getText()) > 999) {
                        System.out.println("The height must be a valid number between 0 and 999.");
                        JOptionPane.showMessageDialog(null, "The height must be a valid number between 0 and 999.");
                        return;
                    }
                } catch (NumberFormatException error) { 
                    System.out.println("The height must be a valid number between 0 and 999.");
                    JOptionPane.showMessageDialog(null, "The height must be a valid number between 0 and 999.");
                    return;
                }

                //Checking to see if the weight is a valid number
                try {
                    if(Integer.parseInt(weightField.getText()) <= 0 || Integer.parseInt(weightField.getText()) > 9999) {
                        System.out.println("The weight must be a valid number between 0 and 9999.");
                        JOptionPane.showMessageDialog(null, "The weight must be a valid number between 0 and 9999.");
                        return;
                    }
                } catch (NumberFormatException error) { 
                    System.out.println("The weight must be a valid number between 0 and 9999.");
                    JOptionPane.showMessageDialog(null, "The weight must be a valid number between 0 and 9999.");
                    return;
                }

                try {
                    if(Integer.parseInt(BPMField.getText()) < 0 || Integer.parseInt(BPMField.getText()) > 999) {
                        System.out.println("The BPM must be a valid number between 0 and 999.");
                        JOptionPane.showMessageDialog(null, "The BPM must be a valid number between 0 and 999.");
                        return;
                    }
                } catch (NumberFormatException error) { 
                    System.out.println("The BPM must be a valid number between 0 and 999.");
                    JOptionPane.showMessageDialog(null, "The BPM must be a valid number between 0 and 999.");
                    return;
                }

                try {
                    if(Float.parseFloat(temperatureField.getText()) <= 0 || Float.parseFloat(temperatureField.getText()) > 999) {
                        System.out.println("The temperature must be a valid number between 0 and 999.");
                        JOptionPane.showMessageDialog(null, "The temperature must be a valid number between 0 and 999.");
                        return;
                    }
                } catch (NumberFormatException error) { 
                    System.out.println("The temperature must be a valid number between 0 and 999.");
                    JOptionPane.showMessageDialog(null, "The temperature must be a valid number between 0 and 999.");
                    return;
                }

                try {
                    String[] bloodPressure = BPField.getText().split("/");
                    if(bloodPressure.length != 2 || Integer.parseInt(bloodPressure[0]) < 0 
                        || Integer.parseInt(bloodPressure[0]) > 999 || Integer.parseInt(bloodPressure[1]) < 0
                        || Integer.parseInt(bloodPressure[1]) > 999) {
                        System.out.println("The blood pressure must be in the format ###/## with valid numbers.");
                        JOptionPane.showMessageDialog(null, "The blood pressure must be in the format ###/## with valid numbers.");
                        return;
                    }
                } catch (NumberFormatException error) { 
                    System.out.println("The blood pressure must be in the format ###/## with valid numbers.");
                    JOptionPane.showMessageDialog(null, "The blood pressure must be in the format ###/## with valid numbers.");
                    return;
                }

                // Check to see if the DOB is in the mm/dd/yyyy format
                String java_pattern = "MM/dd/yyyy";
                String patient_DOB = DOBField.getText();
                SimpleDateFormat sdf = new SimpleDateFormat(java_pattern);
                Date DOB = null;
                try {
                    DOB = sdf.parse(patient_DOB);
                    if (!patient_DOB.equals(sdf.format(DOB))) {
                        DOB = null;
                    }
                } catch (ParseException e2) {
                    // TODO Auto-generated catch block
                    e2.printStackTrace();
                }
                if (DOB == null) {
                    System.out.println("DOB is not in the correct format");
                    JOptionPane.showMessageDialog(null, "The DOB is not in the correct format.");
                    return;
                }

                //Adjust the format of the temperature
                String tempFormat = String.format("%.1f", Float.valueOf(temperatureField.getText()).floatValue());

                Patient newPatient = new Patient(nameField.getText(), DOBField.getText(),
                sexField.getSelectedItem().toString(), notesField.getText());

                Vitals newVitals = new Vitals(Integer.valueOf(heightField.getText()).intValue(),
                        Integer.valueOf(weightField.getText()).intValue(),
                        Integer.valueOf(BPMField.getText()).intValue(),
                        Float.valueOf(tempFormat).floatValue(),
                        BPField.getText(), positionField.getText());

                fireDirectoryButtonEvent(new DirectoryEvent(this, newPatient, newVitals));
            }

        });

        // Add Finish Button
        gc.anchor = GridBagConstraints.LINE_END;
        gc.weightx = 1;
        gc.weighty = 0;
        gc.gridx = 0;
        gc.gridy = 10;
        add(finishButton, gc);

        // Create Cancel Button
        JButton cancelButton = new JButton("Cancel");

        // Add a listener to change back to Main frame without saving any changes
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fireDirectoryButtonEvent(new DirectoryEvent(this));
            }
        });

        // Add Cancel Button
        gc.anchor = GridBagConstraints.LINE_START;
        gc.weightx = 1;
        gc.weighty = 0;
        gc.gridx = 1;
        gc.gridy = 10;
        add(cancelButton, gc);
    }

    // Constructor that is called when we're editing an existing patient
    public AddPatientPanel(Patient patient, Vitals vitals) {
        Dimension size = getPreferredSize();
        size.width = 400;
        setPreferredSize(size);

        setBorder(BorderFactory.createTitledBorder("Edit Patient"));

        setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();

        // Adding fields to add patient info and vitals
        JLabel name = new JLabel("Name");
        gc.anchor = GridBagConstraints.NORTH;
        gc.weightx = 0;
        gc.weighty = 0;
        gc.gridx = 0;
        gc.gridy = 0;
        add(name,gc);

        JTextField nameField = new JTextField(15);
        TextPrompt namePrompt = new TextPrompt("Ex: John Doe", nameField);
        nameField.setText(patient.getName());
        namePrompt.setForeground(Color.GRAY);
        nameField.add(namePrompt);
        gc.weightx = 1;
        gc.weighty = 1;
        gc.gridx = 0;
        gc.gridy = 1;
        add(nameField, gc);

        JLabel PID = new JLabel("PID");
        gc.weightx = 0;
        gc.weighty = 0;
        gc.gridx = 0;
        gc.gridy = 2;
        add(PID,gc);

        JTextField PIDField = new JTextField(15);
        TextPrompt PIDPrompt = new TextPrompt("Ex: 123456", PIDField);
        PIDField.setEditable(false);
        PIDField.setText(Integer.toString(patient.getPID()));
        PIDPrompt.setForeground(Color.GRAY);
        PIDField.add(PIDPrompt);
        gc.weightx = 1;
        gc.weighty = 1;
        gc.gridx = 0;
        gc.gridy = 3;
        PIDField.setEditable(false);
        add(PIDField, gc);

        /*
         * JTextField ageField = new JTextField(15); TextPrompt agePrompt = new
         * TextPrompt("Age", ageField);
         * ageField.setText(Integer.toString(patient.getAge()));
         * agePrompt.setForeground(Color.GRAY); ageField.add(agePrompt); gc.gridx = 0;
         * gc.gridy = 1; add(ageField, gc);
         */

        JLabel DOB = new JLabel("Date of Birth");
        gc.weightx = 0;
        gc.weighty = 0;
        gc.gridx = 0;
        gc.gridy = 4;
        add(DOB, gc);

        JTextField DOBField = new JTextField(15);
        TextPrompt DOBPrompt = new TextPrompt("mm/dd/yyyy", DOBField);
        DOBField.setText(patient.getDOB());
        DOBPrompt.setForeground(Color.GRAY);
        DOBField.add(DOBPrompt);
        gc.weightx = 1;
        gc.weighty = 1;
        gc.gridx = 0;
        gc.gridy = 5;
        add(DOBField, gc);

        JLabel height = new JLabel("Height (cm)");
        gc.weightx = 0;
        gc.weighty = 0;
        gc.gridx = 0;
        gc.gridy = 6;
        add(height, gc);

        JTextField heightField = new JTextField(15);
        TextPrompt heightPrompt = new TextPrompt("Ex: 155", heightField);
        heightField.setText(Integer.toString(vitals.getHeight()));
        heightPrompt.setForeground(Color.GRAY);
        heightField.add(heightPrompt);
        gc.weightx = 1;
        gc.weighty = 1;
        gc.gridx = 0;
        gc.gridy = 7;
        add(heightField, gc);

        JLabel weight = new JLabel("Weight (lbs)");
        gc.weightx = 0;
        gc.weighty = 0;
        gc.gridx = 0;
        gc.gridy = 8;
        add(weight, gc);

        JTextField weightField = new JTextField(15);
        TextPrompt weightPrompt = new TextPrompt("Ex: 165", weightField);
        weightField.setText(Integer.toString(vitals.getWeight()));
        weightPrompt.setForeground(Color.GRAY);
        weightField.add(weightPrompt);
        gc.weightx = 1;
        gc.weighty = 1;
        gc.gridx = 0;
        gc.gridy = 9;
        add(weightField, gc);

        JLabel BPM = new JLabel("BPM");
        gc.weightx = 0;
        gc.weighty = 0;
        gc.gridx = 0;
        gc.gridy = 10;
        add(BPM, gc);

        JTextField BPMField = new JTextField(15);
        TextPrompt BPMPrompt = new TextPrompt("Ex: 85", BPMField);
        BPMField.setText(Integer.toString(vitals.getBPM()));
        BPMPrompt.setForeground(Color.GRAY);
        BPMField.add(BPMPrompt);
        gc.weightx = 1;
        gc.weighty = 1;
        gc.gridx = 0;
        gc.gridy = 11;
        add(BPMField, gc);

        JLabel sex = new JLabel("Sex");
        gc.weightx = 0;
        gc.weighty = 0;
        gc.gridx = 1;
        gc.gridy = 0;
        add(sex, gc);

        String sexOptions[] = { "Male", "Female", "Other" };
        JComboBox<String> sexField = new JComboBox<String>(sexOptions);
        sexField.setSelectedItem(patient.getSex().toString());
        sexField.setEditable(true);
        sexField.setBackground(Color.WHITE);
        sexField.setSize(sexField.getPreferredSize());
        Font font = new Font("Dialog", Font.PLAIN, 12);
        sexField.setFont(font);
        gc.weightx = 1;
        gc.weighty = 1;
        gc.gridx = 1;
        gc.gridy = 1;
        add(sexField, gc);

        JLabel notes = new JLabel("Notes");
        gc.weightx = 0;
        gc.weighty = 0;
        gc.gridx = 1;
        gc.gridy = 2;
        add(notes, gc);

        JTextField notesField = new JTextField(15);
        TextPrompt notesPrompt = new TextPrompt("Notes...", notesField);
        notesField.setText(patient.getNotes());
        notesPrompt.setForeground(Color.GRAY);
        notesField.add(notesPrompt);
        gc.weightx = 1;
        gc.weighty = 1;
        gc.gridx = 1;
        gc.gridy = 3;
        add(notesField, gc);

        JLabel temperature = new JLabel("Temperature (F)");
        gc.weightx = 0;
        gc.weighty = 0;
        gc.gridx = 1;
        gc.gridy = 4;
        add(temperature, gc);

        JTextField temperatureField = new JTextField(15);
        TextPrompt temperaturePrompt = new TextPrompt("Ex: 98.2", temperatureField);
        temperatureField.setText(Float.toString(vitals.getTemperature()));
        temperaturePrompt.setForeground(Color.GRAY);
        temperatureField.add(temperaturePrompt);
        gc.weightx = 1;
        gc.weighty = 1;
        gc.gridx = 1;
        gc.gridy = 5;
        add(temperatureField, gc);

        JLabel BP = new JLabel("Blood Pressure");
        gc.weightx = 0;
        gc.weighty = 0;
        gc.gridx = 1;
        gc.gridy = 6;
        add(BP, gc);

        JTextField BPField = new JTextField(15);
        TextPrompt BPPrompt = new TextPrompt("Ex: 120/75", BPField);
        BPField.setText(vitals.getBP());
        BPPrompt.setForeground(Color.GRAY);
        BPField.add(BPPrompt);
        gc.weightx = 1;
        gc.weighty = 1;
        gc.gridx = 1;
        gc.gridy = 7;
        add(BPField, gc);

        JLabel position = new JLabel("Position");
        gc.weightx = 0;
        gc.weighty = 0;
        gc.gridx = 1;
        gc.gridy = 8;
        add(position, gc);

        JTextField positionField = new JTextField(15);
        TextPrompt positionPrompt = new TextPrompt("Ex: Sitting up", positionField);
        positionField.setText(vitals.getPosition());
        positionPrompt.setForeground(Color.GRAY);
        positionField.add(positionPrompt);
        gc.weightx = 1;
        gc.weighty = 1;
        gc.gridx = 1;
        gc.gridy = 9;
        add(positionField, gc);

        // Create Add New Patient Button
        JButton finishButton = new JButton("Finish");

        // Add a listener to change back to Main frame and save data
        finishButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                // Checking to see if any fields were left empty
                if (nameField.getText().equals("") || DOBField.getText().equals("")
                        || sexField.getSelectedItem().toString().equals("") || PIDField.getText().equals("")
                        || notesField.getText().equals("") || heightField.getText().equals("")
                        || weightField.getText().equals("") || BPMField.getText().equals("")
                        || temperatureField.getText().equals("") || BPField.getText().equals("")
                        || positionField.getText().equals("")) {
                    System.out.println("Please do not leave any fields empty.");
                    JOptionPane.showMessageDialog(null, "Please do not leave any fields empty.");
                    return;
                }

                // Checking to see if the sex field does not contain male, female, or other
                if (!sexField.getSelectedItem().toString().equals("Male")
                        && !sexField.getSelectedItem().toString().equals("Female")
                        && !sexField.getSelectedItem().toString().equals("Other")) {
                    System.out.println("The patient's sex must be selected from the dropdown.");
                    JOptionPane.showMessageDialog(null, "The patient's sex must be selected from the dropdown.");
                    return;
                }

                //Checking to see if the height is a valid number
                try {
                    if(Integer.parseInt(heightField.getText()) <= 0 || Integer.parseInt(heightField.getText()) > 999) {
                        System.out.println("The height must be a valid number between 0 and 999.");
                        JOptionPane.showMessageDialog(null, "The height must be a valid number between 0 and 999.");
                        return;
                    }
                } catch (NumberFormatException error) { 
                    System.out.println("The height must be a valid number between 0 and 999.");
                    JOptionPane.showMessageDialog(null, "The height must be a valid number between 0 and 999.");
                    return;
                }

                //Checking to see if the weight is a valid number
                try {
                    if(Integer.parseInt(weightField.getText()) <= 0 || Integer.parseInt(weightField.getText()) > 9999) {
                        System.out.println("The weight must be a valid number between 0 and 9999.");
                        JOptionPane.showMessageDialog(null, "The weight must be a valid number between 0 and 9999.");
                        return;
                    }
                } catch (NumberFormatException error) { 
                    System.out.println("The weight must be a valid number between 0 and 9999.");
                    JOptionPane.showMessageDialog(null, "The weight must be a valid number between 0 and 9999.");
                    return;
                }

                try {
                    if(Integer.parseInt(BPMField.getText()) < 0 || Integer.parseInt(BPMField.getText()) > 999) {
                        System.out.println("The BPM must be a valid number between 0 and 999.");
                        JOptionPane.showMessageDialog(null, "The BPM must be a valid number between 0 and 999.");
                        return;
                    }
                } catch (NumberFormatException error) { 
                    System.out.println("The BPM must be a valid number between 0 and 999.");
                    JOptionPane.showMessageDialog(null, "The BPM must be a valid number between 0 and 999.");
                    return;
                }

                try {
                    if(Float.parseFloat(temperatureField.getText()) <= 0 || Float.parseFloat(temperatureField.getText()) > 999) {
                        System.out.println("The temperature must be a valid number between 0 and 999.");
                        JOptionPane.showMessageDialog(null, "The temperature must be a valid number between 0 and 999.");
                        return;
                    }
                } catch (NumberFormatException error) { 
                    System.out.println("The temperature must be a valid number between 0 and 999.");
                    JOptionPane.showMessageDialog(null, "The temperature must be a valid number between 0 and 999.");
                    return;
                }

                try {
                    String[] bloodPressure = BPField.getText().split("/");
                    if(bloodPressure.length != 2 || Integer.parseInt(bloodPressure[0]) < 0 
                        || Integer.parseInt(bloodPressure[0]) > 999 || Integer.parseInt(bloodPressure[1]) < 0
                        || Integer.parseInt(bloodPressure[1]) > 999) {
                        System.out.println("The blood pressure must be in the format ###/## with valid numbers.");
                        JOptionPane.showMessageDialog(null, "The blood pressure must be in the format ###/## with valid numbers.");
                        return;
                    }
                } catch (NumberFormatException error) { 
                    System.out.println("The blood pressure must be in the format ###/## with valid numbers.");
                    JOptionPane.showMessageDialog(null, "The blood pressure must be in the format ###/## with valid numbers.");
                    return;
                }

                // Check to see if the DOB is in the mm/dd/yyyy format
                String java_pattern = "MM/dd/yyyy";
                SimpleDateFormat sdf = new SimpleDateFormat(java_pattern);
                String patient_DOB = DOBField.getText();
                Date DOB = null;
                try {
                    DOB = sdf.parse(patient_DOB);
                    if (!patient_DOB.equals(sdf.format(DOB))) {
                        DOB = null;
                    }
                } catch (ParseException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                if (DOB == null) {
                    System.out.println("DOB is not in the correct format");
                    JOptionPane.showMessageDialog(null, "The DOB is not in the correct format.");
                    return;
                }

                //Adjust the format of the temperature
                String tempFormat = String.format("%.1f", Float.valueOf(temperatureField.getText()).floatValue());

                Patient newPatient = new Patient(nameField.getText(), DOBField.getText(),
                sexField.getSelectedItem().toString(), Integer.valueOf(PIDField.getText()).intValue(),
                notesField.getText());

                Vitals newVitals = new Vitals(Integer.valueOf(heightField.getText()).intValue(),
                        Integer.valueOf(weightField.getText()).intValue(),
                        Integer.valueOf(BPMField.getText()).intValue(),
                        Float.valueOf(tempFormat).floatValue(),
                        Integer.valueOf(PIDField.getText()).intValue(), BPField.getText(), positionField.getText());
                fireDirectoryButtonEvent(new DirectoryEvent(this, newPatient, newVitals));
            }

        });

        // Add Finish Button
        gc.anchor = GridBagConstraints.LINE_END;
        gc.weightx = 1;
        gc.weighty = 0;
        gc.gridx = 0;
        gc.gridy = 12;
        add(finishButton, gc);

        // Create Cancel Button
        JButton cancelButton = new JButton("Cancel");

        // Add a listener to change back to Main frame without saving any changes
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fireDirectoryButtonEvent(new DirectoryEvent(this));
            }
        });

        // Add Cancel Button
        gc.anchor = GridBagConstraints.LINE_START;
        gc.weightx = 1;
        gc.weighty = 0;
        gc.gridx = 1;
        gc.gridy = 12;
        add(cancelButton, gc);
    }

    public void fireDirectoryButtonEvent(DirectoryEvent event) {
        Object[] listeners = listenerList.getListenerList();
        for (int i = 0; i < listeners.length; i += 2) {
            if (listeners[i] == DirectoryButtonListener.class) {
                ((DirectoryButtonListener) listeners[i + 1]).directoryEventOccurred(event);
            }
        }
    }

    public void addDirectoryButtonListener(DirectoryButtonListener listener) {
        listenerList.add(DirectoryButtonListener.class, listener);
    }

    public void removeDirectoryButtonListener(DirectoryButtonListener listener) {
        listenerList.remove(DirectoryButtonListener.class, listener);
    }
}