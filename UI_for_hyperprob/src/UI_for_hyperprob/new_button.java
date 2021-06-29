package UI_for_hyperprob;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.*;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.plaf.metal.*;
class editor extends JFrame implements ActionListener {

    JTextArea t;
    JFrame f;
    JPanel P;
    editor() {
        f = new JFrame("editor");
        P = new JPanel();
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");

            MetalLookAndFeel.setCurrentTheme(new OceanTheme());
        } catch (Exception e) {
        }

        t = new JTextArea();

        //menu bar
        JMenuBar mb = new JMenuBar();

        // Create a menu for menu
        JMenu m1 = new JMenu("File");

        // menu items
        JMenuItem mi1 = new JMenuItem("New");
        JMenuItem mi2 = new JMenuItem("Open");
        JMenuItem mi3 = new JMenuItem("Save");
        JMenuItem mi9 = new JMenuItem("Print");

        // Add action listener
        mi1.addActionListener(this);
        mi2.addActionListener(this);
        mi3.addActionListener(this);
        mi9.addActionListener(this);

        m1.add(mi1);
        m1.add(mi2);
        m1.add(mi3);
        m1.add(mi9);

        // Create a menu for menu
        JMenu m2 = new JMenu("Edit");

        // Create menu items
        JMenuItem mi4 = new JMenuItem("cut");
        JMenuItem mi5 = new JMenuItem("copy");
        JMenuItem mi6 = new JMenuItem("paste");

        // Add action listener
        mi4.addActionListener(this);
        mi5.addActionListener(this);
        mi6.addActionListener(this);

        m2.add(mi4);
        m2.add(mi5);
        m2.add(mi6);

        JMenuItem mc = new JMenuItem("close");

        mc.addActionListener(this);


        mb.add(m1);
        mb.add(m2);
        mb.add(mc);

        String path = "sum.py";

        Process p = null;
        try {
            p = Runtime.getRuntime().exec("python " + path);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        BufferedReader bfr = new BufferedReader(new InputStreamReader(p.getInputStream()));
        BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));
        String line = "";
        String save = "";
        while (true) {
            try {
                if (!((line = bfr.readLine()) != null))
                    break;
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            save+=line;

        }

        //Tab
        JTabbedPane tab = new JTabbedPane();
        tab.setTabPlacement(SwingConstants.BOTTOM);
        JPanel model = new JPanel();
        JPanel prop = new JPanel();
        JPanel run = new JPanel();
        tab.addTab("Model",model);
        tab.addTab("Property",prop);
        tab.addTab("Run", run);

        //Run Panel
        JTextField input1 = new JTextField(20);
        JTextField input2 = new JTextField(20);
        run.add(new JLabel("Model:"));
        run.add(input1);
        run.add(Box.createHorizontalStrut(20));
        run.add(new JLabel("Property:"));
        run.add(input2);

        JTextArea out = new JTextArea(5,10);
        out.setText(save);
        JButton b = new JButton("Run");
        b.setBounds(50,100,95,30);
        run.add(b);
        run.add(out);
        out.setVisible(false);
        b.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                out.setVisible(true);
            }
        });

        f.setJMenuBar(mb);
         model.add(t);
       //  prop.add(t);
        model.setSize(300,300);
        f.setSize(700, 700);
        f.getContentPane().add(tab);
        f.show();
    }

    // If a button is pressed
    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();

        if (s.equals("cut")) {
            t.cut();
        } else if (s.equals("copy")) {
            t.copy();
        } else if (s.equals("paste")) {
            t.paste();
        } else if (s.equals("Save")) {

            JFileChooser j = new JFileChooser("f:");

            // Invoke the showsSaveDialog function to show the save dialog
            int r = j.showSaveDialog(null);

            if (r == JFileChooser.APPROVE_OPTION) {

                // Set the label to the path of the selected directory
                File fi = new File(j.getSelectedFile().getAbsolutePath());

                try {
                    // Create a file writer
                    FileWriter wr = new FileWriter(fi, false);

                    // Create buffered writer to write
                    BufferedWriter w = new BufferedWriter(wr);

                    // Write
                    w.write(t.getText());

                    w.flush();
                    w.close();
                } catch (Exception evt) {
                    JOptionPane.showMessageDialog(f, evt.getMessage());
                }
            }
            // If the user cancelled the operation
            else
                JOptionPane.showMessageDialog(f, "the user cancelled the operation");
        } else if (s.equals("Print")) {
            try {
                // print the file
                t.print();
            } catch (Exception evt) {
                JOptionPane.showMessageDialog(f, evt.getMessage());
            }
        } else if (s.equals("Open")) {
            // Create an object of JFileChooser class
            JFileChooser j = new JFileChooser("f:");

            // Invoke the showsOpenDialog function to show the save dialog
            int r = j.showOpenDialog(null);

            // If the user selects a file
            if (r == JFileChooser.APPROVE_OPTION) {
                // Set the label to the path of the selected directory
                File fi = new File(j.getSelectedFile().getAbsolutePath());

                try {
                    // String
                    String s1 = "", sl = "";

                    // File reader
                    FileReader fr = new FileReader(fi);

                    // Buffered reader
                    BufferedReader br = new BufferedReader(fr);

                    // Initialize sl
                    sl = br.readLine();

                    // Take the input from the file
                    while ((s1 = br.readLine()) != null) {
                        sl = sl + "\n" + s1;
                    }

                    // Set the text
                    t.setText(sl);
                } catch (Exception evt) {
                    JOptionPane.showMessageDialog(f, evt.getMessage());
                }
            }
            else
                JOptionPane.showMessageDialog(f, "the user cancelled the operation");
        } else if (s.equals("New")) {
            t.setText("");

        } else if (s.equals("close")) {
            f.setVisible(false);

        }
    }
}
