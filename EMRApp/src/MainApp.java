import javax.swing.*;

public class MainApp {
    public static void main(String[] args){

        SwingUtilities.invokeLater(new Runnable () {
            public void run(){

                JFrame frame = new MainFrame("EMR");

                frame.setSize(1000,500);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);

            }
        });
    }
}