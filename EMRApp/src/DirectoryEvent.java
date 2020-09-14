import java.util.*;

public class DirectoryEvent extends EventObject {

    private Patient patient;
    private Vitals vitals;
    private ArrayList<Medication> medications;
    private String action;

    public DirectoryEvent(Object source) {
        super(source);
    }

    public DirectoryEvent(Object source, Patient patient) {
        super(source);
        this.patient = patient;
    }
    

    public DirectoryEvent(Object source, Patient patient, Vitals vitals) {
        super(source);
        this.patient = patient;
        this.vitals = vitals;
    }

    public DirectoryEvent(Object source, Patient patient, Vitals vitals, String action) {
        super(source);
        this.patient = patient;
        this.vitals = vitals;
        this.action = action;
    }

    public DirectoryEvent(Object source, Patient patient, Vitals vitals, ArrayList<Medication> medications) {
        super(source);
        this.patient = patient;
        this.vitals = vitals;
        this.medications = medications;
    }

    public DirectoryEvent(Object source, ArrayList<Medication> medications) {
        super(source);
        this.medications = medications;
    }


    public Patient getPatient() {
        return this.patient;
    }

    public Vitals getVitals() {
        return this.vitals;
    }

    public ArrayList<Medication> getMedications() {
        return this.medications;
    }

    public String getAction() {
        return this.action;
    }

}