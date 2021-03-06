import java.awt.*;
import javax.swing.*;
import java.util.*;

public class AddMedicationFrame extends JFrame {

    private MainFrame mainFrame;
    private AddMedicationFrame thisFrame = this;

    private AddMedicationPanel addMedicationPanel;

    public AddMedicationFrame (String title, int PID, MainFrame frame) {
        super(title);

        setLayout(new BorderLayout());
        Container container = getContentPane();

        //Initializing panel
        this.mainFrame = frame;
        this.addMedicationPanel = new AddMedicationPanel(PID);

        //Adding event to switch frames back to the Main Frame, and add the medication(s) to the database
        addMedicationPanel.addDirectoryButtonListener(new DirectoryButtonListener() {
            public void directoryEventOccurred(DirectoryEvent event) {
                ArrayList<Medication> medications = event.getMedications();
                Patient patient = null;
                if(medications != null) {
                    for(Medication m : medications) {
                        ArrayList<String> update = new ArrayList<String>();

                        try {
                            if (Database.get("Medication", "MID", m.getMID()).size() > 0) {
                                System.out.println("Medication with this MID already exists.");
                                JOptionPane.showMessageDialog(null, "Medication with this MID already exists.");
                                return;
                            }
                            ArrayList<String> row = Database.get("Patients", "PID", m.getPID()).get(0);

                            if (row == null) {
                                System.out.println("Cannot find patient with this PID.");
                                JOptionPane.showMessageDialog(null, "Cannot find patient with this PID.");
                                return;
                            }

                            patient = new Patient(row.get(0), Integer.valueOf(row.get(1)).intValue(), row.get(2), row.get(3),
                                Integer.valueOf(row.get(4)).intValue(), row.get(5));

                        } catch (HeadlessException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        } catch (Exception e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }

                        update.add("'" + m.getName() + "'");
                        update.add("'" + m.getFrequency() + "'");
                        update.add(Integer.toString(m.getPID()));
                        update.add("'" + m.getRoute() + "'");
                        update.add("'" + m.getTime() + "'");
                        update.add("'" + m.getMID() + "'");
                        Database.post("Medication", update);
                    }
                    mainFrame.dispose();
                    mainFrame = new MainFrame("EHR");
                    mainFrame.selectPatient(patient);
                    mainFrame.selectMedication(medications.get(0));
                    mainFrame.setSize(1000,500);
                    mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    thisFrame.setVisible(false);
                    mainFrame.setVisible(true);
                }     
                thisFrame.dispose();
            }            
        });

        container.add(addMedicationPanel, BorderLayout.CENTER);
    }
    
    public AddMedicationFrame (String title, Medication medication, MainFrame frame) {
        super(title);

        setLayout(new BorderLayout());
        Container container = getContentPane();

        //Initializing panel
        this.mainFrame = frame;
        this.addMedicationPanel = new AddMedicationPanel(medication);

        //Adding event to switch frames back to the Main Frame, and add the medication(s) to the database
        addMedicationPanel.addDirectoryButtonListener(new DirectoryButtonListener() {
            public void directoryEventOccurred(DirectoryEvent event) {
                ArrayList<Medication> medications = event.getMedications();
                Patient patient = null;
                if(medications != null) {
                    for(Medication m : medications) {
                        try {

                            ArrayList<String> row = Database.get("Patients", "PID", m.getPID()).get(0);

                            if (row == null) {
                                System.out.println("Cannot find patient with this PID.");
                                JOptionPane.showMessageDialog(null, "Cannot find patient with this PID.");
                                return;
                            }

                            patient = new Patient(row.get(0), Integer.valueOf(row.get(1)).intValue(), row.get(2), row.get(3),
                                Integer.valueOf(row.get(4)).intValue(), row.get(5));

                        } catch (HeadlessException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        } catch (Exception e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                        String MID = "'" + m.getMID() + "'";
                        String name = "'" + m.getName() + "'";
                        String frequency = "'" + m.getFrequency() + "'";
                        String PID = Integer.toString(m.getPID());
                        String route = "'" + m.getRoute() + "'";
                        String time = "'" + m.getTime() + "'";

                        Database.updateTable("Medication", "medication", name, MID);
                        Database.updateTable("Medication", "frequency", frequency, MID);
                        Database.updateTable("Medication", "PID", PID, MID);
                        Database.updateTable("Medication", "route", route, MID);
                        Database.updateTable("Medication", "time", time, MID);
                    }
                    mainFrame.dispose();
                    mainFrame = new MainFrame("EHR");
                    mainFrame.selectPatient(patient);
                    mainFrame.selectMedication(medications.get(0));
                    mainFrame.setSize(1000,500);
                    mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    thisFrame.setVisible(false);
                    mainFrame.setVisible(true);
                }

                thisFrame.dispose();
            }            
        });

        container.add(addMedicationPanel, BorderLayout.CENTER);
    }

}