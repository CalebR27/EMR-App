import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
import java.util.*;

public class DirectoryPanel extends JPanel {

    private EventListenerList listenerList = new EventListenerList();

    private ArrayList<Patient> patients;
    private ArrayList<Vitals> vitals;
    private ArrayList<Medication> medications;

    private Patient patientToEdit;
    private Vitals vitalsToEdit;

    private JButton newPatientButton;
    private JButton editPatientButton;
    private JButton deletePatientButton;
    private JTable patientTable;

    public DirectoryPanel() {
        Dimension size = getPreferredSize();
        size.width = 200;
        setPreferredSize(size);

        setBorder(BorderFactory.createTitledBorder("Patient Directory"));

        setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();

        // Create array of Patients, Vitals, and Medications
        patients = new ArrayList<Patient>();
        vitals = new ArrayList<Vitals>();
        medications = new ArrayList<Medication>();
        try {
            ArrayList<ArrayList<String>> results = Database.get("Patients");

            // Adding results into an array of Patients
            for (ArrayList<String> row : results) {
                patients.add(new Patient(row.get(0), Integer.valueOf(row.get(1)).intValue(), row.get(2), row.get(3),
                        Integer.valueOf(row.get(4)).intValue(), row.get(5)));
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        try {
            ArrayList<ArrayList<String>> results = Database.get("Vitals");

            // Adding results into an array of Vitals
            for (ArrayList<String> row : results) {
                vitals.add(new Vitals(Integer.valueOf(row.get(0)).intValue(), Integer.valueOf(row.get(1)).intValue(),
                        Integer.valueOf(row.get(2)).intValue(), Float.valueOf(row.get(3)).floatValue(),
                        Integer.valueOf(row.get(4)).intValue(), row.get(5), row.get(6)));
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        try {
            ArrayList<ArrayList<String>> results = Database.get("Medication");

            // Adding results into an array of Medications
            for (ArrayList<String> row : results) {
                medications.add(new Medication(row.get(0), row.get(1), Integer.valueOf(row.get(2)).intValue(),
                        row.get(3), row.get(4), row.get(5)));
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        // Create Patient Table
        int numrows = patients.size();
        int numcols = 2;
        String[] col = { "PID", "Name" };
        Object[][] data = new Object[numrows][numcols];

        // Fill in Data array
        for (int r = 0; r < numrows; r++) {
            data[r][0] = patients.get(r).getPID();
            data[r][1] = patients.get(r).getName();
        }
        patientTable = new JTable(data, col);
        patientTable.setDefaultEditor(Object.class, null);
        patientTable.setAutoCreateRowSorter(true);
        patientTable.getRowSorter().toggleSortOrder(0);
        patientTable.setPreferredScrollableViewportSize(new Dimension(150, 250));
        patientTable.setFillsViewportHeight(true);

        JScrollPane scrollPane = new JScrollPane(patientTable);

        // Fix up column widths
        TableColumnModel columnModel = patientTable.getColumnModel();
        for (int column = 0; column < patientTable.getColumnCount(); column++) {
            int width = 15; // Min width
            for (int row = 0; row < patientTable.getRowCount(); row++) {
                TableCellRenderer renderer = patientTable.getCellRenderer(row, column);
                Component comp = patientTable.prepareRenderer(renderer, row, column);
                width = Math.max(comp.getPreferredSize().width + 1, width);
            }
            if (width > 150)
                width = 150;
            columnModel.getColumn(column).setPreferredWidth(width);
        }
        // Create Add New Patient Button
        newPatientButton = new JButton("New Patient");

        // Create Edit Patient Button
        editPatientButton = new JButton("Edit Patient");
        editPatientButton.setEnabled(false);

        // Create Delete Patient Button
        deletePatientButton = new JButton("Delete Patient");
        deletePatientButton.setEnabled(false);

        // Add a listener for when a certain patient is selected
        patientTable.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {
            }

            public void mouseExited(MouseEvent e) {
            }

            public void mouseEntered(MouseEvent e) {
            }

            public void mouseReleased(MouseEvent e) {
            }

            public void mousePressed(MouseEvent e) {
                Point point = e.getPoint();
                int index = patientTable.rowAtPoint(point);

                if (index >= patients.size() || index < 0) {
                    patientTable.clearSelection();
                    editPatientButton.setEnabled(false);
                    deletePatientButton.setEnabled(false);
                    return;
                }
                int PID = ((Integer)patientTable.getValueAt(index, 0)).intValue();
                Patient temp = new Patient();
                temp.setPID(PID);
                Patient value = patients.get(patients.indexOf(temp));
                selectPatient(value);
            }

        });

        // Add a listener to delete a patient
        deletePatientButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fireDirectoryButtonEvent(new DirectoryEvent(this, patientToEdit, vitalsToEdit, "delete"));
            }
        });

        // Add a listener to change to AddPatient frame, but for editing
        editPatientButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fireDirectoryButtonEvent(new DirectoryEvent(this, patientToEdit, vitalsToEdit, "edit"));
            }
        });

        // Add a listener to change to AddPatient frame
        newPatientButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fireDirectoryButtonEvent(new DirectoryEvent(this));
            }
        });

        // Add Scroll Pane which contains the table
        gc.anchor = GridBagConstraints.NORTH;
        gc.weightx = 1;
        gc.weighty = 1;
        gc.gridx = 0;
        gc.gridy = 0;
        add(scrollPane, gc);

        // Adding Add New Patient Button
        gc.anchor = GridBagConstraints.SOUTH;
        gc.weightx = 0;
        gc.weighty = 0.5;
        gc.gridx = 0;
        gc.gridy = 1;
        add(newPatientButton, gc);

        // Adding Edit Patient Button. It is set to Disabled until a patient is selected
        gc.gridy = 2;
        add(editPatientButton, gc);

        // Adding Delete Patient Button. It is set to Disabled until a patient is
        // selected
        gc.gridy = 3;
        add(deletePatientButton, gc);
    }

    public void selectPatient(Patient patientToSelect) {
        if(patientTable.getSelectionModel().isSelectionEmpty()) {
            int index = 0;
            int column = 0;
            int PID = patientToSelect.getPID();
            for (int row = 0; row < patientTable.getRowCount(); row++) {
                int tempPID = ((Integer)patientTable.getValueAt(row, column)).intValue();
                if(PID == tempPID) {
                    index = row;
                    break;
                }
            }
            patientTable.setRowSelectionInterval(index, index);
        }
        editPatientButton.setEnabled(true);
        deletePatientButton.setEnabled(true);

        // This patient will be edited if "Edit Patient" button is clicked, or deleted
        // if "Delete Patient" button is clicked
        patientToEdit = patientToSelect;

        // Get PID of selected patient
        int currentPID = patientToSelect.getPID();

        // Search for vitals with corresponding PID
        Vitals value2 = null;
        for (Vitals v : vitals) {
            if (v.getPID() == currentPID) {
                value2 = v;
            }
        }

        // This patient's vitals will be edited if "Edit Patient" button is clicked, or
        // deleted if "Delete Patient" button is clicked
        vitalsToEdit = value2;

        // Search for medication with corresponding PID and add to another array to send
        // back
        ArrayList<Medication> value3 = new ArrayList<Medication>();
        for (Medication m : medications) {
            if (m.getPID() == currentPID) {
                value3.add(m);
            }
        }
        fireDirectoryTableEvent(new DirectoryEvent(this, patientToEdit, value2, value3));
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

    public void fireDirectoryTableEvent(DirectoryEvent event) {
        Object[] listeners = listenerList.getListenerList();
        for (int i = 0; i < listeners.length; i += 2) {
            if (listeners[i] == DirectoryTableListener.class) {
                ((DirectoryTableListener) listeners[i + 1]).directoryEventOccurred(event);
            }
        }
    }

    public void addDirectoryTableListener(DirectoryTableListener listener) {
        listenerList.add(DirectoryTableListener.class, listener);
    }

    public void removeDirectoryTableListener(DirectoryTableListener listener) {
        listenerList.remove(DirectoryTableListener.class, listener);
    }

}