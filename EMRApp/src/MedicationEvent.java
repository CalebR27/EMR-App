import java.util.EventObject;

public class MedicationEvent extends EventObject {

    private Medication medication;
    private String action;

    public MedicationEvent(Object source) {
        super(source);
    }

    public MedicationEvent(Object source, Medication medication) {
        super(source);
        this.medication = medication;
    }

    public MedicationEvent(Object source, Medication medication, String action) {
        super(source);
        this.medication = medication;
        this.action = action;
    }

    public Medication getMedication() {
        return this.medication;
    }

    public String getAction() {
        return this.action;
    }

}