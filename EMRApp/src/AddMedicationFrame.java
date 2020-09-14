import java.awt.*;
import javax.swing.*;
import java.util.*;

public class AddMedicationFrame extends JFrame {

    private MainFrame mainFrame;
    private AddMedicationFrame thisFrame = this;

    private AddMedicationPanel addMedicationPanel;

    public AddMedicationFrame (String title) {
        super(title);

        setLayout(new BorderLayout());
        Container container = getContentPane();

        //Initializing panel
        this.addMedicationPanel = new AddMedicationPanel();

        //Adding event to switch frames back to the Main Frame, and add the medication(s) to the database
        addMedicationPanel.addDirectoryButtonListener(new DirectoryButtonListener() {
            public void directoryEventOccurred(DirectoryEvent event) {
                ArrayList<Medication> medications = event.getMedications();
                if(medications != null) {
                    for(Medication m : medications) {
                        ArrayList<String> update = new ArrayList<String>();

                        update.add("'" + m.getName() + "'");
                        update.add("'" + m.getFrequency() + "'");
                        update.add(Integer.toString(m.getPID()));
                        update.add("'" + m.getRoute() + "'");
                        update.add("'" + m.getTime() + "'");
                        update.add("'" + m.getMID() + "'");
                        Database.post("Medication", update);
                    }
                }     

                mainFrame = new MainFrame("EHR");
                mainFrame.setSize(1000,500);
                mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                thisFrame.setVisible(false);
                mainFrame.setVisible(true);
            }            
        });

        container.add(addMedicationPanel, BorderLayout.CENTER);
    }

    public AddMedicationFrame (String title, Medication medication) {
        super(title);

        setLayout(new BorderLayout());
        Container container = getContentPane();

        //Initializing panel
        this.addMedicationPanel = new AddMedicationPanel(medication);

        //Adding event to switch frames back to the Main Frame, and add the medication(s) to the database
        addMedicationPanel.addDirectoryButtonListener(new DirectoryButtonListener() {
            public void directoryEventOccurred(DirectoryEvent event) {
                ArrayList<Medication> medications = event.getMedications();
                if(medications != null) {
                    for(Medication m : medications) {
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
                }

                mainFrame = new MainFrame("EHR");
                mainFrame.setSize(1000,500);
                mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                thisFrame.setVisible(false);
                mainFrame.setVisible(true);
            }            
        });

        container.add(addMedicationPanel, BorderLayout.CENTER);
    }

}