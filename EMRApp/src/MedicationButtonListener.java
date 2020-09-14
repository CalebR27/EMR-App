import java.util.EventListener;

public interface MedicationButtonListener extends EventListener {
    public void medicationEventOccurred(MedicationEvent event);
}