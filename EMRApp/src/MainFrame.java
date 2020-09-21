import java.awt.*;
import javax.swing.*;
import java.util.*;

public class MainFrame extends JFrame {

    private DirectoryPanel directoryPanel;
    private MiddlePanel middlePanel;
    private MedicationPanel medicationPanel;

    private AddPatientFrame addPatientFrame;
    private AddMedicationFrame addMedicationFrame;
    private MainFrame thisFrame = this;

    private Patient patient;
    private Vitals vitals;
    private ArrayList<Medication> medications;

    public MainFrame(String title) {
        super(title);

        setLayout(new BorderLayout());
        Container container = getContentPane();

        /* Directory Panel */
        directoryPanel = new DirectoryPanel();

        // Adding event to switch frames
        directoryPanel.addDirectoryButtonListener(new DirectoryButtonListener() {
            public void directoryEventOccurred(DirectoryEvent event) {
                Patient current_patient = event.getPatient();
                Vitals current_vitals = event.getVitals();
                String action = event.getAction();

                /* If we specify the action to be "delete" and current_patient is not null, then we delete the 
                    patient along with their vitals and ALL their medication.
                    Otherwise, we check to see if both current_patient and current_vitals are null. If so, then
                    we are adding a patient. If they are not, and we specify the action to "edit", then we edit
                    the current patient */

                if(current_patient != null && action.equals("delete")) {
                    String PID = Integer.toString(current_patient.getPID());
                    Database.delete("Patients", "PID", PID);
                    Database.delete("Vitals", "PID", PID);
                    Database.delete("Medication", "PID", PID);

                    MainFrame frame = new MainFrame("EMR");
                    frame.setSize(1000,500);
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                    thisFrame.setVisible(false);
                    frame.setVisible(true);
                    thisFrame.dispose();
                } else {
                    if(current_patient == null && current_vitals == null){
                        addPatientFrame = new AddPatientFrame("Add Patient", thisFrame);
                    } else if (action.equals("edit")) {
                        addPatientFrame = new AddPatientFrame("Add Patient", current_patient, current_vitals, thisFrame);
                    }

                    addPatientFrame.setSize(500, 500);
                    addPatientFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    
                    //thisFrame.setVisible(false);
                    addPatientFrame.setVisible(true);
                }
            }
        });
        

        // Adding event to display data for patient, vitals, and medication
        directoryPanel.addDirectoryTableListener(new DirectoryTableListener() {
            public void directoryEventOccurred(DirectoryEvent event) {
                vitals = event.getVitals();
                medications = event.getMedications();
                patient = event.getPatient();

                System.out.println("Selected patient is: "+ patient.getName());

                for(Medication m : medications) {
                    System.out.println("Sending this med to table: " + m.getName());
                }
                medicationPanel.addMedications(medications);
                middlePanel.addVitals(vitals);
                middlePanel.addPatient(patient);
            }
        });

        /* Middle Panel which contains the Patient panel and the Vitals panel */
        middlePanel = new MiddlePanel();

        /* Medication Panel */
        medicationPanel = new MedicationPanel();

        // Adding event to switch frames
        medicationPanel.addMedicationButtonListener(new MedicationButtonListener() {
            public void medicationEventOccurred(MedicationEvent event) {
                Medication current_medication = event.getMedication();
                String action = event.getAction();
                
                if(current_medication != null && action.equals("delete")) {
                    String MID = current_medication.getMID();
                    Database.delete("Medication", "MID", "'" + MID + "'");

                    MainFrame frame = new MainFrame("EMR");
                    frame.selectPatient(patient);
                    frame.setSize(1000,500);
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                    thisFrame.setVisible(false);
                    frame.setVisible(true);
                    thisFrame.dispose();

                } else {
                    if (current_medication == null && action == null && patient == null) {
                        addMedicationFrame = new AddMedicationFrame("Add Medication", 0, thisFrame);
                    } else if (current_medication == null && action == null && patient != null){
                        addMedicationFrame = new AddMedicationFrame("Add Medication", patient.getPID(), thisFrame);
                    }else if (current_medication != null && action.equals("edit")) {
                        addMedicationFrame = new AddMedicationFrame("Add Medication", current_medication, thisFrame);
                    }
                
                    addMedicationFrame.setSize(500, 500);
                    addMedicationFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                    addMedicationFrame.setVisible(true);
                }
            }
        });

        container.add(directoryPanel, BorderLayout.WEST);
        container.add(middlePanel, BorderLayout.CENTER);
        container.add(medicationPanel, BorderLayout.EAST);
    }

    public void selectPatient(Patient patientToSelect){
        this.directoryPanel.selectPatient(patientToSelect);
    }

    public void selectMedication(Medication medicationToSelect){
        this.medicationPanel.selectMedication(medicationToSelect);
    }
}