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
        JTextField nameField = new JTextField(15);
        TextPrompt namePrompt = new TextPrompt("Name", nameField);
        namePrompt.setForeground(Color.GRAY);
        nameField.add(namePrompt);
        gc.weightx = 1;
        gc.weighty = 1;
        gc.gridx = 0;
        gc.gridy = 0;
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

        JTextField DOBField = new JTextField(15);
        TextPrompt DOBPrompt = new TextPrompt("Date of Birth mm/dd/yyyy", DOBField);
        DOBPrompt.setForeground(Color.GRAY);
        DOBField.add(DOBPrompt);
        gc.gridx = 0;
        gc.gridy = 1;
        add(DOBField, gc);

        JTextField heightField = new JTextField(15);
        TextPrompt heightPrompt = new TextPrompt("Height (cm)", heightField);
        heightPrompt.setForeground(Color.GRAY);
        heightField.add(heightPrompt);
        gc.gridx = 0;
        gc.gridy = 2;
        add(heightField, gc);

        JTextField weightField = new JTextField(15);
        TextPrompt weightPrompt = new TextPrompt("Weight (lbs)", weightField);
        weightPrompt.setForeground(Color.GRAY);
        weightField.add(weightPrompt);
        gc.gridx = 0;
        gc.gridy = 3;
        add(weightField, gc);

        JTextField BPMField = new JTextField(15);
        TextPrompt BPMPrompt = new TextPrompt("BPM", BPMField);
        BPMPrompt.setForeground(Color.GRAY);
        BPMField.add(BPMPrompt);
        gc.gridx = 0;
        gc.gridy = 4;
        add(BPMField, gc);

        String sexOptions[] = { "Male", "Female", "Other" };
        JComboBox<String> sexField = new JComboBox<String>(sexOptions);
        sexField.setEditable(true);
        sexField.setSelectedItem(new String("<Select Sex>"));
        sexField.setBackground(Color.WHITE);
        sexField.setSize(sexField.getPreferredSize());
        Font font = new Font("Dialog", Font.PLAIN, 12);
        sexField.setFont(font);
        gc.gridx = 1;
        gc.gridy = 0;
        add(sexField, gc);

        JTextField notesField = new JTextField(15);
        TextPrompt notesPrompt = new TextPrompt("Notes", notesField);
        notesPrompt.setForeground(Color.GRAY);
        notesField.add(notesPrompt);
        gc.gridx = 1;
        gc.gridy = 1;
        add(notesField, gc);

        JTextField temperatureField = new JTextField(15);
        TextPrompt temperaturePrompt = new TextPrompt("Temperature", temperatureField);
        temperaturePrompt.setForeground(Color.GRAY);
        temperatureField.add(temperaturePrompt);
        gc.gridx = 1;
        gc.gridy = 2;
        add(temperatureField, gc);

        JTextField BPField = new JTextField(15);
        TextPrompt BPPrompt = new TextPrompt("BP", BPField);
        BPPrompt.setForeground(Color.GRAY);
        BPField.add(BPPrompt);
        gc.gridx = 1;
        gc.gridy = 3;
        add(BPField, gc);

        JTextField positionField = new JTextField(15);
        TextPrompt positionPrompt = new TextPrompt("Position", positionField);
        positionPrompt.setForeground(Color.GRAY);
        positionField.add(positionPrompt);
        gc.gridx = 1;
        gc.gridy = 4;
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

                Patient newPatient = new Patient(nameField.getText(), DOBField.getText(),
                    sexField.getSelectedItem().toString(), notesField.getText());


                // Check to see if the DOB is in the mm/dd/yyyy format
                String java_pattern = "MM/dd/yyyy";
                String patient_DOB = newPatient.getDOB();
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
                    newPatient = null;
                    System.out.println("DOB is not in the correct format");
                    JOptionPane.showMessageDialog(null, "The DOB is not in the correct format.");
                    return;
                }

                Vitals newVitals = new Vitals(Integer.valueOf(heightField.getText()).intValue(),
                        Integer.valueOf(weightField.getText()).intValue(),
                        Integer.valueOf(BPMField.getText()).intValue(),
                        Integer.valueOf(temperatureField.getText()).intValue(),
                        BPField.getText(), positionField.getText());

                fireDirectoryButtonEvent(new DirectoryEvent(this, newPatient, newVitals));
            }

        });

        // Add Finish Button
        gc.anchor = GridBagConstraints.LINE_END;
        gc.weightx = 1;
        gc.weighty = 0;
        gc.gridx = 0;
        gc.gridy = 6;
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
        gc.gridy = 6;
        add(cancelButton, gc);
    }

    // Constructor that is called when we're editing an existing patient
    public AddPatientPanel(Patient patient, Vitals vitals) {
        Dimension size = getPreferredSize();
        size.width = 400;
        setPreferredSize(size);

        setBorder(BorderFactory.createTitledBorder("Add New Patient"));

        setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();

        // Adding fields to add patient info and vitals
        JTextField nameField = new JTextField(15);
        TextPrompt namePrompt = new TextPrompt("Name", nameField);
        nameField.setText(patient.getName());
        namePrompt.setForeground(Color.GRAY);
        nameField.add(namePrompt);
        gc.weightx = 1;
        gc.weighty = 1;
        gc.gridx = 0;
        gc.gridy = 0;
        add(nameField, gc);

        JTextField PIDField = new JTextField(15);
        TextPrompt PIDPrompt = new TextPrompt("PID ######", PIDField);
        PIDField.setEditable(false);
        PIDField.setText(Integer.toString(patient.getPID()));
        PIDPrompt.setForeground(Color.GRAY);
        PIDField.add(PIDPrompt);
        gc.gridx = 0;
        gc.gridy = 1;
        PIDField.setEditable(false);
        add(PIDField, gc);

        /*
         * JTextField ageField = new JTextField(15); TextPrompt agePrompt = new
         * TextPrompt("Age", ageField);
         * ageField.setText(Integer.toString(patient.getAge()));
         * agePrompt.setForeground(Color.GRAY); ageField.add(agePrompt); gc.gridx = 0;
         * gc.gridy = 1; add(ageField, gc);
         */

        JTextField DOBField = new JTextField(15);
        TextPrompt DOBPrompt = new TextPrompt("Date of Birth mm/dd/yyyy", DOBField);
        DOBField.setText(patient.getDOB());
        DOBPrompt.setForeground(Color.GRAY);
        DOBField.add(DOBPrompt);
        gc.gridx = 0;
        gc.gridy = 2;
        add(DOBField, gc);

        JTextField heightField = new JTextField(15);
        TextPrompt heightPrompt = new TextPrompt("Height (cm)", heightField);
        heightField.setText(Integer.toString(vitals.getHeight()));
        heightPrompt.setForeground(Color.GRAY);
        heightField.add(heightPrompt);
        gc.gridx = 0;
        gc.gridy = 3;
        add(heightField, gc);

        JTextField weightField = new JTextField(15);
        TextPrompt weightPrompt = new TextPrompt("Weight (lbs)", weightField);
        weightField.setText(Integer.toString(vitals.getWeight()));
        weightPrompt.setForeground(Color.GRAY);
        weightField.add(weightPrompt);
        gc.gridx = 0;
        gc.gridy = 4;
        add(weightField, gc);

        JTextField BPMField = new JTextField(15);
        TextPrompt BPMPrompt = new TextPrompt("BPM", BPMField);
        BPMField.setText(Integer.toString(vitals.getBPM()));
        BPMPrompt.setForeground(Color.GRAY);
        BPMField.add(BPMPrompt);
        gc.gridx = 0;
        gc.gridy = 5;
        add(BPMField, gc);

        String sexOptions[] = { "Male", "Female", "Other" };
        JComboBox<String> sexField = new JComboBox<String>(sexOptions);
        sexField.setSelectedItem(patient.getSex().toString());
        sexField.setEditable(true);
        sexField.setBackground(Color.WHITE);
        sexField.setSize(sexField.getPreferredSize());
        Font font = new Font("Dialog", Font.PLAIN, 12);
        sexField.setFont(font);
        gc.gridx = 1;
        gc.gridy = 0;
        add(sexField, gc);

        JTextField notesField = new JTextField(15);
        TextPrompt notesPrompt = new TextPrompt("Notes", notesField);
        notesField.setText(patient.getNotes());
        notesPrompt.setForeground(Color.GRAY);
        notesField.add(notesPrompt);
        gc.gridx = 1;
        gc.gridy = 1;
        add(notesField, gc);

        JTextField temperatureField = new JTextField(15);
        TextPrompt temperaturePrompt = new TextPrompt("Temperature", temperatureField);
        temperatureField.setText(Integer.toString(vitals.getTemperature()));
        temperaturePrompt.setForeground(Color.GRAY);
        temperatureField.add(temperaturePrompt);
        gc.gridx = 1;
        gc.gridy = 2;
        add(temperatureField, gc);

        JTextField BPField = new JTextField(15);
        TextPrompt BPPrompt = new TextPrompt("BP", BPField);
        BPField.setText(vitals.getBP());
        BPPrompt.setForeground(Color.GRAY);
        BPField.add(BPPrompt);
        gc.gridx = 1;
        gc.gridy = 3;
        add(BPField, gc);

        JTextField positionField = new JTextField(15);
        TextPrompt positionPrompt = new TextPrompt("Position", positionField);
        positionField.setText(vitals.getPosition());
        positionPrompt.setForeground(Color.GRAY);
        positionField.add(positionPrompt);
        gc.gridx = 1;
        gc.gridy = 4;
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

                Patient newPatient = new Patient(nameField.getText(), DOBField.getText(),
                        sexField.getSelectedItem().toString(), Integer.valueOf(PIDField.getText()).intValue(),
                        notesField.getText());

                // Check to see if the DOB is in the mm/dd/yyyy format
                String java_pattern = "MM/dd/yyyy";
                SimpleDateFormat sdf = new SimpleDateFormat(java_pattern);
                String patient_DOB = newPatient.getDOB();
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
                    newPatient = null;
                    System.out.println("DOB is not in the correct format");
                    JOptionPane.showMessageDialog(null, "The DOB is not in the correct format.");
                    return;
                }

                Vitals newVitals = new Vitals(Integer.valueOf(heightField.getText()).intValue(),
                        Integer.valueOf(weightField.getText()).intValue(),
                        Integer.valueOf(BPMField.getText()).intValue(),
                        Integer.valueOf(temperatureField.getText()).intValue(),
                        Integer.valueOf(PIDField.getText()).intValue(), BPField.getText(), positionField.getText());
                fireDirectoryButtonEvent(new DirectoryEvent(this, newPatient, newVitals));
            }

        });

        // Add Finish Button
        gc.anchor = GridBagConstraints.LINE_END;
        gc.weightx = 20;
        gc.weighty = 0;
        gc.gridx = 0;
        gc.gridy = 6;
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
        gc.gridy = 6;
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