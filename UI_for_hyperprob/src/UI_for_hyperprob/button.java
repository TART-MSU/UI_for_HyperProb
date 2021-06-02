package UI_for_hyperprob;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class button {
    private JPanel button;
    private JTextField textField1;
    private JButton button1;
    private final JFileChooser openFileChooser;

    public button() {
        openFileChooser = new JFileChooser();
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int returnValue = openFileChooser.showOpenDialogDialog(this);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    createFile();
                }
                else {
                    System.out.println("File not found");
                }
            }

            private void createFile() {
            }
        });
    }

    public JPanel getButton() {
        GUIForm form = new GUIForm();
        JFrame frame = new JFrame("Your window name");
        frame.setContentPane(new button().getJPanel());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        return button;
    }

}
