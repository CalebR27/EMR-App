import java.awt.*;
import javax.swing.*;

public class PatientPanel extends JPanel {
    public PatientPanel() {

        setBorder(BorderFactory.createTitledBorder("Patient Information"));

        setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();

        JLabel nameLabel = new JLabel("Name: ");
        gc.anchor = GridBagConstraints.LINE_END;
        gc.weightx = 0.1;
        gc.weighty = 0.5;
        gc.gridx = 0;
        gc.gridy = 0;
        add(nameLabel, gc);

        JLabel ageLabel = new JLabel("Age: ");
        gc.anchor = GridBagConstraints.LINE_END;
        gc.weightx = 0.1;
        gc.weighty = 0.5;
        gc.gridx = 0;
        gc.gridy = 1;
        add(ageLabel, gc);

        JLabel DOBLabel = new JLabel("DOB: ");
        gc.anchor = GridBagConstraints.LINE_END;
        gc.weightx = 0.1;
        gc.weighty = 0.5;
        gc.gridx = 0;
        gc.gridy = 2;
        add(DOBLabel, gc);

        JLabel sexLabel = new JLabel("Sex: ");
        gc.anchor = GridBagConstraints.LINE_END;
        gc.weightx = 1;
        gc.weighty = 0.5;
        gc.gridx = 2;
        gc.gridy = 0;
        add(sexLabel, gc);

        JLabel PIDLabel = new JLabel("PID: ");
        gc.anchor = GridBagConstraints.LINE_END;
        gc.weightx = 1;
        gc.weighty = 0.5;
        gc.gridx = 2;
        gc.gridy = 1;
        add(PIDLabel, gc);

        JLabel notesLabel = new JLabel("Notes: ");
        gc.anchor = GridBagConstraints.SOUTHEAST;
        gc.weightx = 1;
        gc.weighty = 0;
        gc.gridx = 2;
        gc.gridy = 2;
        add(notesLabel, gc);

        JTextArea notes = new JTextArea();
        notes.setEditable(false);
        JScrollPane scroll = new JScrollPane(notes);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        gc.anchor = GridBagConstraints.NORTHEAST;
        gc.weightx = 1;
        gc.weighty = 0;
        gc.gridx = 2;
        gc.gridy = 3;
        notes.setColumns(25);
        notes.setRows(3);
        add(notes, gc);
    }

    public void addPatient(Patient patient) {

        removeAll();

        setBorder(BorderFactory.createTitledBorder("Patient Information"));

        setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();

        String patientName = patient.getName();
        String patientAge = String.valueOf(patient.getAge());
        String patientDOB = patient.getDOB();
        String patientSex = patient.getSex();
        String patientPID = String.valueOf(patient.getPID()); 
        String patientNotes = patient.getNotes();

        JLabel nameLabel = new JLabel("Name: ");
        gc.anchor = GridBagConstraints.LINE_END;
        gc.weightx = 0.1;
        gc.weighty = 0.5;
        gc.gridx = 0;
        gc.gridy = 0;
        add(nameLabel, gc);

        JLabel name = new JLabel(patientName);
        gc.anchor = GridBagConstraints.LINE_START;
        gc.weightx = 0.5;
        gc.weighty = 0.5;
        gc.gridx = 1;
        gc.gridy = 0;
        add(name, gc);

        JLabel ageLabel = new JLabel("Age: ");
        gc.anchor = GridBagConstraints.LINE_END;
        gc.weightx = 0.1;
        gc.weighty = 0.5;
        gc.gridx = 0;
        gc.gridy = 1;
        add(ageLabel, gc);

        JLabel age = new JLabel(patientAge);
        gc.anchor = GridBagConstraints.LINE_START;
        gc.weightx = 0.5;
        gc.weighty = 0.5;
        gc.gridx = 1;
        gc.gridy = 1;
        add(age, gc);

        JLabel DOBLabel = new JLabel("DOB: ");
        gc.anchor = GridBagConstraints.LINE_END;
        gc.weightx = 0.1;
        gc.weighty = 0.5;
        gc.gridx = 0;
        gc.gridy = 2;
        add(DOBLabel, gc);

        JLabel DOB = new JLabel(patientDOB);
        gc.anchor = GridBagConstraints.LINE_START;
        gc.weightx = 0.5;
        gc.weighty = 0.5;
        gc.gridx = 1;
        gc.gridy = 2;
        add(DOB, gc);

        JLabel sexLabel = new JLabel("Sex: ");
        gc.anchor = GridBagConstraints.LINE_END;
        gc.weightx = 1;
        gc.weighty = 0.5;
        gc.gridx = 2;
        gc.gridy = 0;
        add(sexLabel, gc);

        JLabel sex = new JLabel(patientSex);
        gc.anchor = GridBagConstraints.LINE_START;
        gc.weightx = 1;
        gc.weighty = 0.5;
        gc.gridx = 3;
        gc.gridy = 0;
        add(sex, gc);

        JLabel PIDLabel = new JLabel("PID: ");
        gc.anchor = GridBagConstraints.LINE_END;
        gc.weightx = 1;
        gc.weighty = 0.5;
        gc.gridx = 2;
        gc.gridy = 1;
        add(PIDLabel, gc);

        JLabel PID = new JLabel(patientPID);
        gc.anchor = GridBagConstraints.LINE_START;
        gc.weightx = 1;
        gc.weighty = 0.5;
        gc.gridx = 3;
        gc.gridy = 1;
        add(PID, gc);

        JLabel notesLabel = new JLabel("Notes: ");
        gc.anchor = GridBagConstraints.SOUTHEAST;
        gc.weightx = 1;
        gc.weighty = 0;
        gc.gridx = 2;
        gc.gridy = 2;
        add(notesLabel, gc);

        JTextArea notes = new JTextArea();
        notes.setEditable(false);
        JScrollPane scroll = new JScrollPane(notes);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        gc.anchor = GridBagConstraints.NORTHEAST;
        gc.weightx = 1;
        gc.weighty = 0;
        gc.gridx = 2;
        gc.gridy = 3;
        notes.setColumns(25);
        notes.setRows(3);
        notes.append(patientNotes);
        add(notes, gc);

        this.revalidate();
        this.repaint();
    }
}