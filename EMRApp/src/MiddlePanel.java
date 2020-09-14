import javax.swing.*;
import java.awt.*;

public class MiddlePanel extends JPanel {

    private PatientPanel patientPanel;
    private VitalsPanel vitalsPanel;

    public MiddlePanel () {

        patientPanel = new PatientPanel();
        vitalsPanel = new VitalsPanel();

        setLayout(new GridLayout(2, 1));
        add(patientPanel, BorderLayout.NORTH);
        add(vitalsPanel, BorderLayout.CENTER);
    }

    public void addPatient (Patient patient) {
        patientPanel.addPatient(patient);
        this.revalidate();
        this.repaint();
    }

    public void addVitals (Vitals vitals) {
        vitalsPanel.addVitals(vitals);
        this.revalidate();
        this.repaint();
    }
}