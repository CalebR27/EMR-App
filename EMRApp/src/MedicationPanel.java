import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import javax.swing.table.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MedicationPanel extends JPanel {

    private Medication medicationToEdit;

    public MedicationPanel() {
        Dimension size = getPreferredSize();
        size.width = 400;
        setPreferredSize(size);

        setBorder(BorderFactory.createTitledBorder("Active Medication"));

        setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();

        // Creating button to Add New Medication
        JButton newMedicationButton = new JButton("New Medication");

        // Creating button to Edit selected Medication. This will be disabled until a
        // medication is selected
        JButton editMedicationButton = new JButton("Edit Medication");
        editMedicationButton.setEnabled(false);

        // Creating button to Delete selected Medication. This will be disabled until a
        // medication is selected
        JButton deleteMedicationButton = new JButton("Delete Medication");
        deleteMedicationButton.setEnabled(false);

        // Create Medication Table
        int numrows = 0;
        int numcols = 5;
        String[] col = { "Name", "Freq", "Route", "Last Taken", "MID" };
        Object[][] data = new Object[numrows][numcols];

        JTable medicationTable = new JTable(data, col);
        medicationTable.setPreferredScrollableViewportSize(new Dimension(350, 350));
        medicationTable.setFillsViewportHeight(true);

        JScrollPane scrollPane = new JScrollPane(medicationTable);

        // Add Scroll Pane which contains the table
        gc.anchor = GridBagConstraints.NORTH;
        gc.fill = GridBagConstraints.HORIZONTAL;
        gc.gridwidth = 3;
        gc.weightx = 1;
        gc.weighty = 1;
        gc.gridx = 0;
        gc.gridy = 0;
        add(scrollPane, gc);

        gc.anchor = GridBagConstraints.SOUTH;
        gc.fill = GridBagConstraints.NONE;
        gc.gridwidth = 1;
        gc.weightx = 0;
        gc.weighty = 0;
        gc.gridx = 0;
        gc.gridy = 1;
        add(newMedicationButton, gc);

        gc.gridx = 1;
        add(editMedicationButton, gc);

        gc.gridx = 2;
        add(deleteMedicationButton, gc);

        newMedicationButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fireMedicationButtonEvent(new MedicationEvent(this));
            }
        });
    }

    public void addMedications(ArrayList<Medication> medications) {
        removeAll();

        Dimension size = getPreferredSize();
        size.width = 400;
        setPreferredSize(size);
        setBorder(BorderFactory.createTitledBorder("Active Medication"));
        setLayout(new GridBagLayout());

        GridBagConstraints gc = new GridBagConstraints();

        JButton newMedicationButton = new JButton("New Medication");

        JButton editMedicationButton = new JButton("Edit Medication");
        editMedicationButton.setEnabled(false);

        JButton deleteMedicationButton = new JButton("Delete Medication");
        deleteMedicationButton.setEnabled(false);

        // Create Medication Table
        int numrows = medications.size();
        int numcols = 5;
        String[] col = { "Name", "Freq", "Route", "Last Taken", "MID" };
        Object[][] data = new Object[numrows][numcols];

        // Fill in Data array
        for (int r = 0; r < numrows; r++) {
            //System.out.println("Current Med is: " + medications.get(r).getName());
            data[r][0] = medications.get(r).getName();
            data[r][1] = medications.get(r).getFrequency();
            data[r][2] = medications.get(r).getRoute();
            data[r][3] = LocalDateTime.parse(medications.get(r).getTime().replace(" ", "T"));
            data[r][4] = medications.get(r).getMID();
        }
        JTable medicationTable = new JTable(data, col);

        medicationTable.setAutoCreateRowSorter(true);
        medicationTable.getRowSorter().toggleSortOrder(3);
        medicationTable.setPreferredScrollableViewportSize(new Dimension(350, 350));
        medicationTable.setFillsViewportHeight(true);

        JScrollPane scrollPane = new JScrollPane(medicationTable);

        // Fix up column widths
        TableColumnModel columnModel = medicationTable.getColumnModel();
        for (int column = 0; column < medicationTable.getColumnCount(); column++) {
            int width = 10; // Min width
            for (int row = 0; row < medicationTable.getRowCount(); row++) {

                //Creating a renderer to format the Time Taken cells
                TableCellRenderer renderer = new DefaultTableCellRenderer() {

                    DateTimeFormatter format = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm");

                    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                            boolean hasFocus, int row, int column) {
                        if (value instanceof LocalDateTime) {
                            value = format.format((LocalDateTime)value);
                        }
                        return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                    }
                };
                medicationTable.getColumnModel().getColumn(3).setCellRenderer(renderer);
                Component comp = medicationTable.prepareRenderer(renderer, row, column);
                width = Math.max(comp.getPreferredSize().width + 1, width);
            }
            if (width > 150)
                width = 150; // Max width
            columnModel.getColumn(column).setPreferredWidth(width);
        }

        // Add a listener for when a certain medication is selected
        medicationTable.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {
                Point point = e.getPoint();
                int index = medicationTable.rowAtPoint(point);

                if (index >= medications.size() || index < 0) {
                    medicationTable.clearSelection();
                    editMedicationButton.setEnabled(false);
                    deleteMedicationButton.setEnabled(false);
                    return;
                }

                Medication value = medications.get(index);
                editMedicationButton.setEnabled(true);
                deleteMedicationButton.setEnabled(true);
                medicationToEdit = value;
                fireMedicationTableEvent(new MedicationEvent(this, value));
            }

            public void mouseExited(MouseEvent e) {
            }

            public void mouseEntered(MouseEvent e) {
            }

            public void mouseReleased(MouseEvent e) {
            }

            public void mousePressed(MouseEvent e) {
            }

        });

        // Add Scroll Pane which contains the table
        gc.anchor = GridBagConstraints.NORTH;
        gc.fill = GridBagConstraints.HORIZONTAL;
        gc.gridwidth = 3;
        gc.weightx = 1;
        gc.weighty = 1;
        gc.gridx = 0;
        gc.gridy = 0;
        add(scrollPane, gc);

        // Adding listener to add New Medication, then adding it to panel
        newMedicationButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fireMedicationButtonEvent(new MedicationEvent(this));
            }
        });
        gc.anchor = GridBagConstraints.SOUTH;
        gc.fill = GridBagConstraints.NONE;
        gc.gridwidth = 1;
        gc.weightx = 0.5;
        gc.weighty = 0.5;
        gc.gridx = 0;
        gc.gridy = 1;
        add(newMedicationButton, gc);

        // Adding listener to edit Medication, then adding it to panel
        editMedicationButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fireMedicationButtonEvent(new MedicationEvent(this, medicationToEdit, "edit"));
            }
        });
        gc.gridx = 1;
        add(editMedicationButton, gc);

        // Adding listener to delete Medication, then adding it to panel
        deleteMedicationButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fireMedicationButtonEvent(new MedicationEvent(this, medicationToEdit, "delete"));
            }
        });
        gc.gridx = 2;
        add(deleteMedicationButton, gc);

    }

    public void fireMedicationButtonEvent(MedicationEvent event) {
        Object[] listeners = listenerList.getListenerList();

        for (int i = 0; i < listeners.length; i += 2) {
            if (listeners[i] == MedicationButtonListener.class) {
                ((MedicationButtonListener) listeners[i + 1]).medicationEventOccurred(event);
            }
        }
    }

    public void addMedicationButtonListener(MedicationButtonListener listener) {
        listenerList.add(MedicationButtonListener.class, listener);
    }

    public void removeMedicationButtonListener(MedicationButtonListener listener) {
        listenerList.remove(MedicationButtonListener.class, listener);
    }

    public void fireMedicationTableEvent(MedicationEvent event) {
        Object[] listeners = listenerList.getListenerList();
        for (int i = 0; i < listeners.length; i += 2) {
            if (listeners[i] == MedicationTableListener.class) {
                ((MedicationTableListener) listeners[i + 1]).MedicationEventOccurred(event);
            }
        }
    }

    public void addMedicationTableListener(MedicationTableListener listener) {
        listenerList.add(MedicationTableListener.class, listener);
    }

    public void removeMedicationTableListener(MedicationTableListener listener) {
        listenerList.remove(MedicationTableListener.class, listener);
    }

}