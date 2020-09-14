import java.util.EventListener;

public interface DirectoryButtonListener extends EventListener {
    public void directoryEventOccurred(DirectoryEvent event);
}