package UI_for_hyperprob;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JTextPane;
import java.io.*;
public class MainClass {

    public static void main(String[] args) throws IOException, InterruptedException {

        editor e = new editor();
        String path = "sum.py";
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
        p1.add(Text1);
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
        e.button.setText(line);
        p2.add(Text);
        String err;
        while ((err = stdError.readLine()) != null) {
            System.out.println(err);
        }
        
       // JList list  = new JList((ListModel) e);
        //JScrollPane scroll = new JScrollPane(list);
        //scroll.add(p1);
       //JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, scroll, list);
        //window.add(split);
        JSplitPane sp = new JSplitPane(SwingConstants.VERTICAL, p1, p2);
        
       // p2.add(e);
       // JSeparator sp = new JSeparator();
        sp.setOrientation(SwingConstants.HORIZONTAL);
        
        window.add(sp);
        window.setSize(300, 300);
        window.setVisible(true);
    }

}