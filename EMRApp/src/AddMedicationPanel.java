import java.awt.*;
import java.awt.event.*;
import java.text.*;

import javax.swing.*;
import javax.swing.event.*;
import java.util.*;

public class AddMedicationPanel extends JPanel {

    private EventListenerList listenerList = new EventListenerList();

    public AddMedicationPanel(int PID) {
        Dimension size = getPreferredSize();
        size.width = 400;
        setPreferredSize(size);

        setBorder(BorderFactory.createTitledBorder("Add New Medication"));

        setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();

        // Adding fields to add medication info
        JLabel name = new JLabel("Medication Name");
        gc.anchor = GridBagConstraints.SOUTH;
        gc.weightx = 0;
        gc.weighty = 0.5;
        gc.gridx = 0;
        gc.gridy = 0;
        add(name, gc);

        JTextField nameField = new JTextField(20);
        TextPrompt namePrompt = new TextPrompt("Ex: Omeprazole 100mg", nameField);
        namePrompt.setForeground(Color.GRAY);
        nameField.add(namePrompt);
        gc.anchor = GridBagConstraints.NORTH;
        gc.weightx = 1;
        gc.weighty = 1;
        gc.gridx = 0;
        gc.gridy = 1;
        add(nameField, gc);

        JLabel PIDLabel = new JLabel("PID");
        gc.weightx = 0;
        gc.weighty = 0;
        gc.gridx = 0;
        gc.gridy = 2;
        add(PIDLabel, gc);

        JTextField PIDField = new JTextField(20);
        TextPrompt PIDPrompt = new TextPrompt("Ex: 123456", PIDField);
        if (PID != 0) {
            PIDField.setText(String.valueOf(PID));
        }
        PIDPrompt.setForeground(Color.GRAY);
        PIDField.add(PIDPrompt);
        gc.weightx = 1;
        gc.weighty = 1;
        gc.gridx = 0;
        gc.gridy = 3;
        add(PIDField, gc);

        JLabel time = new JLabel("Time Taken");
        gc.weightx = 0;
        gc.weighty = 0;
        gc.gridx = 0;
        gc.gridy = 4;
        add(time, gc);
        
        JTextField timeField = new JTextField(20);
        TextPrompt timePrompt = new TextPrompt("Format: MM/dd/yyyy HH:mm", timeField);
        timePrompt.setForeground(Color.GRAY);
        timeField.add(timePrompt);
        gc.weightx = 1;
        gc.weighty = 1;
        gc.gridx = 0;
        gc.gridy = 5;
        add(timeField, gc);

        JLabel frequency = new JLabel("Frequency");
        gc.anchor = GridBagConstraints.SOUTH;
        gc.weightx = 0;
        gc.weighty = 0.5;
        gc.gridx = 1;
        gc.gridy = 0;
        add(frequency, gc);

        JTextField frequencyField = new JTextField(20);
        TextPrompt frequencyPrompt = new TextPrompt("Ex: Every 2 hours", frequencyField);
        frequencyPrompt.setForeground(Color.GRAY);
        frequencyField.add(frequencyPrompt);
        gc.anchor = GridBagConstraints.NORTH;
        gc.weightx = 1;
        gc.weighty = 1;
        gc.gridx = 1;
        gc.gridy = 1;
        add(frequencyField, gc);

        JLabel route = new JLabel("Route");
        gc.weightx = 0;
        gc.weighty = 0;
        gc.gridx = 1;
        gc.gridy = 2;
        add(route, gc);

        JTextField routeField = new JTextField(20);
        TextPrompt routePrompt = new TextPrompt("Ex: Oral tablet", routeField);
        routePrompt.setForeground(Color.GRAY);
        routeField.add(routePrompt);
        gc.weightx = 1;
        gc.weighty = 1;
        gc.gridx = 1;
        gc.gridy = 3;
        add(routeField, gc);

        /*
        JTextField MIDField = new JTextField(20);
        TextPrompt MIDPrompt = new TextPrompt("MID", MIDField);
        MIDPrompt.setForeground(Color.GRAY);
        MIDField.add(MIDPrompt);
        gc.gridx = 1;
        gc.gridy = 2;
        add(MIDField, gc);
        */

        // Create Finish button to save changes
        JButton finishButton = new JButton("Finish");

        // Add a listener to change back to Main frame and save data
        finishButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                if (nameField.getText().equals("") || frequencyField.getText().equals("") || PIDField.getText().equals("") ||
                    routeField.getText().equals("") || timeField.getText().equals("")) {
                        System.out.println("Please do not leave any fields empty.");
                        JOptionPane.showMessageDialog(null, "Please do not leave any fields empty.");
                        return; 
                }

                try {
                    if(Integer.parseInt(PIDField.getText()) < 100000 || Integer.parseInt(PIDField.getText()) > 999999) {
                        System.out.println("The PID must be a valid 6-digit number.");
                        JOptionPane.showMessageDialog(null, "The PID must be a valid 6-digit number.");
                        return;
                    }
                } catch (NumberFormatException error) { 
                    System.out.println("The PID must be a valid 6-digit number.");
                    JOptionPane.showMessageDialog(null, "The PID must be a valid 6-digit number.");
                    return;
                }

                //Checking to see if the user entered a valid date & time
                String java_pattern = "MM/dd/yyyy HH:mm";
                SimpleDateFormat sdf = new SimpleDateFormat(java_pattern);
                String med_Time = timeField.getText();
                Date time = null;
                try {
                    time = sdf.parse(med_Time);
                    if(!med_Time.equals(sdf.format(time))){
                        time = null;
                    }
                } catch (ParseException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                if(time == null) {
                    System.out.println("The Time is not in the correct format.");
                    JOptionPane.showMessageDialog(null, "The Time is not in the correct format.");
                    return;
                }

                Medication newMedication = new Medication(nameField.getText(), frequencyField.getText(),
                    Integer.valueOf(PIDField.getText()).intValue(), routeField.getText(), timeField.getText());

                ArrayList<Medication> medications = new ArrayList<Medication>();
                medications.add(newMedication);
                fireDirectoryButtonEvent(new DirectoryEvent(this, medications));
            }

        });

        // Add Finish Button
        gc.anchor = GridBagConstraints.SOUTHEAST;
        gc.gridx = 0;
        gc.gridy = 5;
        add(finishButton, gc);

        //Create Cancel Button
        JButton cancelButton = new JButton("Cancel");

        //Add a listener to change back to Main frame without saving any changes
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fireDirectoryButtonEvent(new DirectoryEvent(this));
            }
        });

        //Add Cancel Button
        gc.anchor = GridBagConstraints.SOUTHWEST;
        gc.gridx = 1;
        gc.gridy = 5;
        add(cancelButton, gc);

    }

    /*******************  Constructor that is called when editing an existing medication  ****************/
    public AddMedicationPanel(Medication medication) {
        Dimension size = getPreferredSize();
        size.width = 400;
        setPreferredSize(size);

        setBorder(BorderFactory.createTitledBorder("Edit Medication"));

        setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();

        // Adding fields to add medication info

        JLabel name = new JLabel("Medication Name");
        gc.anchor = GridBagConstraints.SOUTH;
        gc.weightx = 0;
        gc.weighty = 0.5;
        gc.gridx = 0;
        gc.gridy = 0;
        add(name, gc);

        JTextField nameField = new JTextField(20);
        TextPrompt namePrompt = new TextPrompt("Ex: Omeprazole 100mg", nameField);
        nameField.setText(medication.getName());
        namePrompt.setForeground(Color.GRAY);
        nameField.add(namePrompt);
        gc.anchor = GridBagConstraints.NORTH;
        gc.weightx = 1;
        gc.weighty = 1;
        gc.gridx = 0;
        gc.gridy = 1;
        add(nameField, gc);

        JLabel PIDLabel = new JLabel("PID");
        gc.weightx = 0;
        gc.weighty = 0;
        gc.gridx = 0;
        gc.gridy = 2;
        add(PIDLabel, gc);

        JTextField PIDField = new JTextField(20);
        TextPrompt PIDPrompt = new TextPrompt("Ex: 123456", PIDField);
        PIDField.setText(Integer.toString(medication.getPID()));
        PIDPrompt.setForeground(Color.GRAY);
        PIDField.add(PIDPrompt);
        gc.weightx = 1;
        gc.weighty = 1;
        gc.gridx = 0;
        gc.gridy = 3;
        add(PIDField, gc);

        JLabel time = new JLabel("Time Taken");
        gc.weightx = 0;
        gc.weighty = 0;
        gc.gridx = 0;
        gc.gridy = 4;
        add(time, gc);
        
        JTextField timeField = new JTextField(20);
        TextPrompt timePrompt = new TextPrompt("Format: MM/dd/yyyy HH:mm", timeField);
        timeField.setText(medication.getTime());
        timePrompt.setForeground(Color.GRAY);
        timeField.add(timePrompt);
        gc.weightx = 1;
        gc.weighty = 1;
        gc.gridx = 0;
        gc.gridy = 5;
        add(timeField, gc);

        JLabel frequency = new JLabel("Frequency");
        gc.anchor = GridBagConstraints.SOUTH;
        gc.weightx = 0;
        gc.weighty = 0.5;
        gc.gridx = 1;
        gc.gridy = 0;
        add(frequency, gc);

        JTextField frequencyField = new JTextField(20);
        TextPrompt frequencyPrompt = new TextPrompt("Ex: Every 2 hours", frequencyField);
        frequencyField.setText(medication.getFrequency());
        frequencyPrompt.setForeground(Color.GRAY);
        frequencyField.add(frequencyPrompt);
        gc.anchor = GridBagConstraints.NORTH;
        gc.weightx = 1;
        gc.weighty = 1;
        gc.gridx = 1;
        gc.gridy = 1;
        add(frequencyField, gc);

        JLabel route = new JLabel("Route");
        gc.weightx = 0;
        gc.weighty = 0;
        gc.gridx = 1;
        gc.gridy = 2;
        add(route, gc);

        JTextField routeField = new JTextField(20);
        TextPrompt routePrompt = new TextPrompt("Ex: Oral tablet", routeField);
        routeField.setText(medication.getRoute());
        routePrompt.setForeground(Color.GRAY);
        routeField.add(routePrompt);
        gc.weightx = 1;
        gc.weighty = 1;
        gc.gridx = 1;
        gc.gridy = 3;
        add(routeField, gc);

        JLabel MID = new JLabel("MID");
        gc.weightx = 0;
        gc.weighty = 0;
        gc.gridx = 1;
        gc.gridy = 4;
        add(MID, gc);

        JTextField MIDField = new JTextField(20);
        TextPrompt MIDPrompt = new TextPrompt("MID", MIDField);
        MIDField.setEditable(false);
        MIDField.setText(medication.getMID());
        MIDPrompt.setForeground(Color.GRAY);
        MIDField.add(MIDPrompt);
        gc.weightx = 1;
        gc.weighty = 1;
        gc.gridx = 1;
        gc.gridy = 5;
        add(MIDField, gc);

        // Create Finish button to save changes
        JButton finishButton = new JButton("Finish");

        // Add a listener to change back to Main frame and save data
        finishButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                if (nameField.getText().equals("") || frequencyField.getText().equals("") || PIDField.getText().equals("") ||
                    routeField.getText().equals("") || timeField.getText().equals("") || MIDField.getText().equals("")) {
                        System.out.println("Please do not leave any fields empty.");
                        JOptionPane.showMessageDialog(null, "Please do not leave any fields empty.");
                        return; 
                }



                Medication newMedication = new Medication(nameField.getText(), frequencyField.getText(),
                    Integer.valueOf(PIDField.getText()).intValue(), routeField.getText(), timeField.getText(),
                    MIDField.getText());

                //Checking to see if the user entered a valid date & time
                String java_pattern = "MM/dd/yyyy hh:mm";
                SimpleDateFormat sdf = new SimpleDateFormat(java_pattern);
                String med_Time = newMedication.getTime();
                Date time = null;
                try {
                    time = sdf.parse(med_Time);
                    if(!med_Time.equals(sdf.format(time))){
                        time = null;
                    }
                } catch (ParseException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                if(time == null) {
                    newMedication = null;
                    System.out.println("The Time is not in the correct format");
                    JOptionPane.showMessageDialog(null, "The Time is not in the correct format.");
                    return;
                }

                ArrayList<Medication> medications = new ArrayList<Medication>();
                medications.add(newMedication);
                fireDirectoryButtonEvent(new DirectoryEvent(this, medications));
            }

        });

        // Add Finish Button
        gc.anchor = GridBagConstraints.LINE_END;
        gc.gridx = 0;
        gc.gridy = 6;
        add(finishButton, gc);

        //Create Cancel Button
        JButton cancelButton = new JButton("Cancel");

        //Add a listener to change back to Main frame without saving any changes
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fireDirectoryButtonEvent(new DirectoryEvent(this));
            }
        });

        //Add Cancel Button
        gc.anchor = GridBagConstraints.LINE_START;
        gc.gridx = 1;
        gc.gridy = 6;
        add(cancelButton,gc);
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
