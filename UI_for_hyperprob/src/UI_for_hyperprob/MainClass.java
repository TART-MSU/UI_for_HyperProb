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

        editor e = new editor();
        String path = "sum.py";
        // String home = System.getProperty("user.home");
        // File file = new File(home+"/Downloads/" + fileName + ".txt");

        // ProcessBuilder pb = new ProcessBuilder("python", file).inheritIO();
//        ProcessBuilder pb = new ProcessBuilder("python", path);
//        pb.inheritIO();
//        pb.redirectErrorStream(true);
//        Process p = pb.start();
//        p.waitFor();
        Process p = Runtime.getRuntime().exec("python " + path);
        BufferedReader bfr = new BufferedReader(new InputStreamReader(p.getInputStream()));
        BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));
        String line = "";
        JFrame window = new JFrame("New Window");
        JPanel p1 = new JPanel();
        JPanel p2 = new JPanel();
        JTextArea Text = new JTextArea(5, 10);
        JTextArea Text1 = new JTextArea(5, 10);
        // Text.setText("HI");
        Text1.setText("Hey");
        p2.add(Text1);
        // JFrame f = new JFrame("frame");
        // JSplitPane sp = new JSplitPane(SwingConstants.VERTICAL, p1, p2);
        //   sp.setOrientation(SwingConstants.VERTICAL);
        //  window.add(sp);
        //  window.setSize(300, 300);
        //  window.setVisible(true);

        while ((line = bfr.readLine()) != null) {
            Text.setText(line);
            System.out.println(line);
        }
        p1.add(Text);
        String err;
        while ((err = stdError.readLine()) != null) {
            System.out.println(err);
        }
        //   window.add(Text);
        //  window.pack();
        //  window.setVisible(true);
        JSplitPane sp = new JSplitPane(SwingConstants.VERTICAL, p1, p2);
        sp.setOrientation(SwingConstants.VERTICAL);
        window.add(sp);
        window.setSize(300, 300);
        window.setVisible(true);
    }

}