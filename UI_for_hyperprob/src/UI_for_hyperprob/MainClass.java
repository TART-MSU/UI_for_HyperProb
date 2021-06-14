package UI_for_hyperprob;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import javax.swing.JTextArea;
import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JTextPane;
import java.io.*;
public class MainClass {

    public static void main(String[] args) throws IOException, InterruptedException {
//        new_button form = new new_button();
        // JFrame frame = new JFrame("Your window name");
//        frame.setContentPane(new new_button().getJPanel());
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.pack();
//        frame.setVisible(true);
        editor e = new editor();
        String path = "/Users/tarayounessi/Downloads/sum.py";
        ProcessBuilder pb = new ProcessBuilder("python", path).inheritIO();
        Process p = pb.start();
        p.waitFor();
        BufferedReader bfr = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String line = "";
        JFrame window = new JFrame("New Window");
        JTextArea Text = new JTextArea(5, 25);
        //  JButton button = new JButton();
        while ((line = bfr.readLine()) != null) {
            Text.append(line);
            // System.out.println(line);
        }
        window.add(Text);
        window.pack();
        window.setVisible(true);
    }
           

}

