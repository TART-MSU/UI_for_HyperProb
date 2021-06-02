package UI_for_hyperprob;

import javax.swing.*;

public class MainClass {

    public static void main(String args[]) {
            GUIForm form = new GUIForm();
            JFrame frame = new JFrame("Your window name");
            frame.setContentPane(new button().getJPanel());
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack();
            frame.setVisible(true);


        // program execution will start from here. We will create an object of the Basic_interface class here and call its functions
    }
}
