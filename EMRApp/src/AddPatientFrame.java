import java.awt.*;
import javax.swing.*;
import java.util.*;

public class AddPatientFrame extends JFrame {

    private MainFrame mainFrame;
    private AddPatientFrame thisFrame = this;

    private AddPatientPanel addPatientPanel;

    public AddPatientFrame(String title) {
        super(title);
        setLayout(new BorderLayout());
        Container container = getContentPane();

        // Initializing panel
        this.addPatientPanel = new AddPatientPanel();

        // Adding event to switch frames back to the Main Frame, and add the new
        // information to the database
        addPatientPanel.addDirectoryButtonListener(new DirectoryButtonListener() {
            public void directoryEventOccurred(DirectoryEvent event) {
                Patient newPatient = event.getPatient();

                if (newPatient != null) {

                    ArrayList<String> update = new ArrayList<String>();

                    update.add("'" + newPatient.getName() + "'");
                    update.add(Integer.toString(newPatient.getAge()));
                    update.add("'" + newPatient.getDOB() + "'");
                    update.add("'" + newPatient.getSex() + "'");
                    update.add("'" + newPatient.getNotes() + "'");

                    try {
                        if (Database.get("Patients", "PID", newPatient.getPID()).size() > 0) {
                            System.out.println("Patient with this PID already exists.");
                            JOptionPane.showMessageDialog(null, "Patient with this PID already exists.");
                            return;
                        }
                    } catch (HeadlessException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }

                    int PID = Integer.valueOf(Database.post("Patients", update)).intValue();
                    System.out.println("Just added a new user with PID " + PID);
                    newPatient.setPID(PID);

                    Vitals newVitals = event.getVitals();
                    newVitals.setPID(PID);
                    update = new ArrayList<String>();
                    update.add(Integer.toString(newVitals.getHeight()));
                    update.add(Integer.toString(newVitals.getWeight()));
                    update.add(Integer.toString(newVitals.getBPM()));
                    update.add(Integer.toString(newVitals.getTemperature()));
                    update.add(Integer.toString(newVitals.getPID()));
                    update.add("'" + newVitals.getBP() + "'");
                    update.add("'" + newVitals.getPosition() + "'");
                    Database.post("Vitals", update);

                }

                mainFrame = new MainFrame("EHR");
                mainFrame.setSize(1000,500);
                mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                thisFrame.setVisible(false);
                mainFrame.setVisible(true);
            }
        });
        
        container.add(addPatientPanel, BorderLayout.CENTER);
    }

    public AddPatientFrame (String title, Patient patient, Vitals vitals) {
        super(title);
        setLayout(new BorderLayout());
        Container container = getContentPane();

        //Initializing panel
        this.addPatientPanel = new AddPatientPanel(patient, vitals);

        // Adding event to switch frames back to the Main Frame, and update the information in the database
        addPatientPanel.addDirectoryButtonListener(new DirectoryButtonListener() {
            public void directoryEventOccurred(DirectoryEvent event) {
                Patient newPatient = event.getPatient();

                if (newPatient != null) {
                    int PID = newPatient.getPID();

                    String name = "'" + newPatient.getName() + "'";
                    String age = Integer.toString(newPatient.getAge());
                    String DOB = "'" + newPatient.getDOB() + "'";
                    String sex = "'" + newPatient.getSex() + "'";
                    String notes = "'" + newPatient.getNotes() + "'";

                    Database.updateTable("Patients", "name", name, PID);
                    Database.updateTable("Patients", "age", age, PID);
                    Database.updateTable("Patients", "DOB", DOB, PID);
                    Database.updateTable("Patients", "sex", sex, PID);
                    Database.updateTable("Patients", "notes", notes, PID);

                    Vitals newVitals = event.getVitals();

                    String height = Integer.toString(newVitals.getHeight());
                    String weight = Integer.toString(newVitals.getWeight());
                    String BPM = Integer.toString(newVitals.getBPM());
                    String temperature = Integer.toString(newVitals.getTemperature());
                    String vitalsPID = Integer.toString(newVitals.getPID());
                    String BP = "'" + newVitals.getBP() + "'";
                    String position = "'" + newVitals.getPosition() + "'";

                    Database.updateTable("Vitals", "height", height, PID);
                    Database.updateTable("Vitals", "weight", weight, PID);
                    Database.updateTable("Vitals", "BPM", BPM, PID);
                    Database.updateTable("Vitals", "temperature", temperature, PID);
                    Database.updateTable("Vitals", "PID", vitalsPID, PID);
                    Database.updateTable("Vitals", "BP", BP, PID);
                    Database.updateTable("Vitals", "position", position, PID);

                }

                mainFrame = new MainFrame("EHR");
                mainFrame.setSize(1000,500);
                mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                thisFrame.setVisible(false);
                mainFrame.setVisible(true);
            }
        });
        
        container.add(addPatientPanel, BorderLayout.CENTER);
    }

}