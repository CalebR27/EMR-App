import javax.swing.*;
import java.awt.*;

public class VitalsPanel extends JPanel {
    public VitalsPanel () {

        setBorder(BorderFactory.createTitledBorder("Patient Vitals"));

        setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();

        JLabel heightLabel = new JLabel("Height (cm): ");
        gc.anchor = GridBagConstraints.LINE_START;
        gc.weightx = 0.1;
        gc.weighty = 0.1;
        gc.gridx = 0;
        gc.gridy = 0;
        add(heightLabel, gc);

        JLabel weightLabel = new JLabel("Weight (lbs): ");
        gc.anchor = GridBagConstraints.LINE_START;
        gc.weightx = 0.1;
        gc.weighty = 0.1;
        gc.gridx = 0;
        gc.gridy = 1;
        add(weightLabel, gc);

        JLabel BPMLabel = new JLabel("BPM: ");
        gc.anchor = GridBagConstraints.LINE_START;
        gc.weightx = 0.1;
        gc.weighty = 0.1;
        gc.gridx = 0;
        gc.gridy = 2;
        add(BPMLabel, gc);

        JLabel temperatureLabel = new JLabel("Temperature (F): ");
        gc.anchor = GridBagConstraints.LINE_START;
        gc.weightx = 0.1;
        gc.weighty = 0.1;
        gc.gridx = 1;
        gc.gridy = 0;
        add(temperatureLabel, gc);

        JLabel BPLabel = new JLabel("BP: ");
        gc.anchor = GridBagConstraints.LINE_START;
        gc.weightx = 0.1;
        gc.weighty = 0.1;
        gc.gridx = 1;
        gc.gridy = 1;
        add(BPLabel, gc);

        JLabel positionLabel = new JLabel("Position: ");
        gc.anchor = GridBagConstraints.LINE_START;
        gc.weightx = 0.1;
        gc.weighty = 0.1;
        gc.gridx = 1;
        gc.gridy = 2;
        add(positionLabel, gc);
    }

    public void addVitals(Vitals vitals) {

        removeAll();

        setBorder(BorderFactory.createTitledBorder("Patient Vitals"));

        setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();

        String heightval = String.valueOf(vitals.getHeight());
        String weightval = String.valueOf(vitals.getWeight());
        String BPMval = String.valueOf(vitals.getBPM());
        String temperatureval = String.valueOf(vitals.getTemperature());
        String BPval = vitals.getBP();
        String positionval = vitals.getPosition();

        JLabel heightLabel = new JLabel("Height (cm): " + heightval);
        gc.anchor = GridBagConstraints.LINE_START;
        gc.weightx = 0.1;
        gc.weighty = 0.1;
        gc.gridx = 0;
        gc.gridy = 0;
        add(heightLabel, gc);

        JLabel weightLabel = new JLabel("Weight (lbs): " + weightval);
        gc.anchor = GridBagConstraints.LINE_START;
        gc.weightx = 0.1;
        gc.weighty = 0.1;
        gc.gridx = 0;
        gc.gridy = 1;
        add(weightLabel, gc);

        JLabel BPMLabel = new JLabel("BPM: " + BPMval);
        gc.anchor = GridBagConstraints.LINE_START;
        gc.weightx = 0.1;
        gc.weighty = 0.1;
        gc.gridx = 0;
        gc.gridy = 2;
        add(BPMLabel, gc);

        JLabel temperatureLabel = new JLabel("Temperature (F): " + temperatureval);
        gc.anchor = GridBagConstraints.LINE_START;
        gc.weightx = 0.1;
        gc.weighty = 0.1;
        gc.gridx = 1;
        gc.gridy = 0;
        add(temperatureLabel, gc);

        JLabel BPLabel = new JLabel("BP: " + BPval);
        gc.anchor = GridBagConstraints.LINE_START;
        gc.weightx = 0.1;
        gc.weighty = 0.1;
        gc.gridx = 1;
        gc.gridy = 1;
        add(BPLabel, gc);

        JLabel positionLabel = new JLabel("Position: " + positionval);
        gc.anchor = GridBagConstraints.LINE_START;
        gc.weightx = 0.1;
        gc.weighty = 0.1;
        gc.gridx = 1;
        gc.gridy = 2;
        add(positionLabel, gc);

        this.revalidate();
        this.repaint();
    }
}