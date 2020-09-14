import java.util.EventListener;

public interface DirectoryTableListener extends EventListener {
    public void directoryEventOccurred(DirectoryEvent event);
}