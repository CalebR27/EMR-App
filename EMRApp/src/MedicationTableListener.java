import java.util.EventListener;

public interface MedicationTableListener extends EventListener {
    public void MedicationEventOccurred(MedicationEvent event);
}