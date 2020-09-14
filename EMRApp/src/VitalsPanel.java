import javax.swing.*;
import java.awt.*;

public class VitalsPanel extends JPanel {
    public VitalsPanel () {

        setBorder(BorderFactory.createTitledBorder("Patient Vitals"));

        setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();

        JLabel heightLabel = new JLabel("Height: ");
        gc.anchor = GridBagConstraints.LINE_END;
        gc.weightx = 0.1;
        gc.weighty = 0.5;
        gc.gridx = 0;
        gc.gridy = 0;
        add(heightLabel, gc);

        JLabel weightLabel = new JLabel("Weight: ");
        gc.anchor = GridBagConstraints.LINE_END;
        gc.weightx = 0.1;
        gc.weighty = 0.5;
        gc.gridx = 0;
        gc.gridy = 1;
        add(weightLabel, gc);

        JLabel BPMLabel = new JLabel("BPM: ");
        gc.anchor = GridBagConstraints.LINE_END;
        gc.weightx = 0.1;
        gc.weighty = 0.5;
        gc.gridx = 0;
        gc.gridy = 2;
        add(BPMLabel, gc);

        JLabel temperatureLabel = new JLabel("Temperature: ");
        gc.anchor = GridBagConstraints.LINE_END;
        gc.weightx = 1;
        gc.weighty = 0.5;
        gc.gridx = 2;
        gc.gridy = 0;
        add(temperatureLabel, gc);

        JLabel BPLabel = new JLabel("BP: ");
        gc.anchor = GridBagConstraints.LINE_END;
        gc.weightx = 1;
        gc.weighty = 0.5;
        gc.gridx = 2;
        gc.gridy = 1;
        add(BPLabel, gc);

        JLabel positionLabel = new JLabel("Position: ");
        gc.anchor = GridBagConstraints.LINE_END;
        gc.weightx = 1;
        gc.weighty = 0.5;
        gc.gridx = 2;
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

        JLabel heightLabel = new JLabel("Height: ");
        gc.anchor = GridBagConstraints.LINE_END;
        gc.weightx = 0.1;
        gc.weighty = 0.5;
        gc.gridx = 0;
        gc.gridy = 0;
        add(heightLabel, gc);

        JLabel height = new JLabel(heightval);
        gc.anchor = GridBagConstraints.LINE_START;
        gc.weightx = 2;
        gc.weighty = 0.5;
        gc.gridx = 1;
        gc.gridy = 0;
        add(height, gc);

        JLabel weightLabel = new JLabel("Weight: ");
        gc.anchor = GridBagConstraints.LINE_END;
        gc.weightx = 0.1;
        gc.weighty = 0.5;
        gc.gridx = 0;
        gc.gridy = 1;
        add(weightLabel, gc);

        JLabel weight = new JLabel(weightval);
        gc.anchor = GridBagConstraints.LINE_START;
        gc.weightx = 0.5;
        gc.weighty = 0.5;
        gc.gridx = 1;
        gc.gridy = 1;
        add(weight, gc);

        JLabel BPMLabel = new JLabel("BPM: ");
        gc.anchor = GridBagConstraints.LINE_END;
        gc.weightx = 0.1;
        gc.weighty = 0.5;
        gc.gridx = 0;
        gc.gridy = 2;
        add(BPMLabel, gc);

        JLabel BPM = new JLabel(BPMval);
        gc.anchor = GridBagConstraints.LINE_START;
        gc.weightx = 0.5;
        gc.weighty = 0.5;
        gc.gridx = 1;
        gc.gridy = 2;
        add(BPM, gc);

        JLabel temperatureLabel = new JLabel("Temperature: ");
        gc.anchor = GridBagConstraints.LINE_END;
        gc.weightx = 1;
        gc.weighty = 0.5;
        gc.gridx = 2;
        gc.gridy = 0;
        add(temperatureLabel, gc);

        JLabel temperature = new JLabel(temperatureval);
        gc.anchor = GridBagConstraints.LINE_START;
        gc.weightx = 1;
        gc.weighty = 0.5;
        gc.gridx = 3;
        gc.gridy = 0;
        add(temperature, gc);

        JLabel BPLabel = new JLabel("BP: ");
        gc.anchor = GridBagConstraints.LINE_END;
        gc.weightx = 0.1;
        gc.weighty = 0.5;
        gc.gridx = 2;
        gc.gridy = 1;
        add(BPLabel, gc);

        JLabel BP = new JLabel(BPval);
        gc.anchor = GridBagConstraints.LINE_START;
        gc.weightx = 1;
        gc.weighty = 0.5;
        gc.gridx = 3;
        gc.gridy = 1;
        add(BP, gc);

        JLabel positionLabel = new JLabel("Position: ");
        gc.anchor = GridBagConstraints.LINE_END;
        gc.weightx = 0.1;
        gc.weighty = 0.5;
        gc.gridx = 2;
        gc.gridy = 2;
        add(positionLabel, gc);

        JLabel position = new JLabel(positionval);
        gc.anchor = GridBagConstraints.LINE_START;
        gc.weightx = 1;
        gc.weighty = 0.5;
        gc.gridx = 3;
        gc.gridy = 2;
        add(position, gc);

        this.revalidate();
        this.repaint();
    }
}