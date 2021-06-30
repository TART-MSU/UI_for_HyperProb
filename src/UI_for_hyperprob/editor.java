package UI_for_hyperprob;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.*;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.metal.*;
class editor extends JFrame implements ActionListener {

    JTextArea modt;
    JTextArea propt;
    JFrame f;
    JPanel P;
    JTextArea out;
    //StringBuilder output;
    editor() {
        f = new JFrame("HyperProb");
        P = new JPanel();
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");

            MetalLookAndFeel.setCurrentTheme(new OceanTheme());
        } catch (Exception e) {
        }

        modt = new JTextArea(47,70);
        JScrollPane scrollm = new JScrollPane(modt);
        scrollm.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        propt = new JTextArea(47,70);
        JScrollPane scrollp = new JScrollPane(propt);
        scrollp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        //menu bar
        JMenuBar mb = new JMenuBar();

        // Create a menu for menu
        JMenu m1 = new JMenu("File");

        // menu items
        JMenuItem mi1 = new JMenuItem("New");
        JMenuItem mi2 = new JMenuItem("Open");
        JMenuItem mi3 = new JMenuItem("Save");

        // Add action listener
        mi1.addActionListener(this);
        mi2.addActionListener(this);
        mi3.addActionListener(this);

        m1.add(mi1);
        m1.add(mi2);
        m1.add(mi3);

        // Create a menu for menu
        JMenu m2 = new JMenu("Edit");

        // Create menu items
        JMenuItem mi4 = new JMenuItem("Cut");
        JMenuItem mi5 = new JMenuItem("Copy");
        JMenuItem mi6 = new JMenuItem("Paste");

        // Add action listener
        mi4.addActionListener(this);
        mi5.addActionListener(this);
        mi6.addActionListener(this);

        m2.add(mi4);
        m2.add(mi5);
        m2.add(mi6);

        JMenuItem mc = new JMenuItem("Close");

        mc.addActionListener(this);

        mb.add(m1);
        mb.add(m2);
        mb.add(mc);


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
        JButton b = new JButton("Run");
        b.addActionListener(this);
        b.setBounds(50,100,95,30);
        run.add(b);

        out = new JTextArea(35,45);
        JScrollPane scrollo = new JScrollPane(out);
        scrollm.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        run.add(scrollo);
        out.setVisible(false);

        String path_file = "source.py";
        String path_model = "benchmark_files/mdp/TA/timing_attack2.nm";
        File myObj = new File("property.txt");
        Scanner myReader = null;
        try {
            myReader = new Scanner(myObj);
        } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        }

        String path_property = myReader.nextLine();
        System.out.println(path_property);
        String cmd_array[] = new String[]{"python", path_file, path_model, path_property};

        //String cmd_array[] = new String[]{"python", "sum.py"};

        Process p = null;
        try {
            //p = Runtime.getRuntime().exec("python " + path);
            //System.out.println("python " + path_file + " " + path_model + " " + path_property);
            p = Runtime.getRuntime().exec(cmd_array);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

        assert p != null;
        InputStream error = p.getErrorStream();
        int c = 0;
        while (true) {
            try {
                if ((c = error.read()) == -1) break;
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            System.out.print((char) c);
        }
        BufferedReader bfr = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String line = "";
        StringBuilder output = new StringBuilder();
        while (true) {
            try {
                if ((line = bfr.readLine()) == null)
                    break;
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            output.append('\n').append(line);

        }
        out.setText(output.toString());

        f.setJMenuBar(mb);
        model.setBorder (new TitledBorder(new EtchedBorder(), "Model"));
        //model.add(t);
        model.add(scrollm);
        //prop.add(t);
        model.setSize(250,240);
        prop.setBorder (new TitledBorder(new EtchedBorder(), "Property"));
        prop.add(scrollp);
        prop.setSize(250,250);
        f.setSize(800, 800);
        f.getContentPane().add(tab);
        f.show();
    }

    // If a button is pressed
    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();

        switch (s) {
            case "Cut" -> modt.cut();
            case "Copy" -> modt.copy();
            case "Paste" -> modt.paste();
            case "Save" -> {

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
                        w.write(modt.getText());

                        w.flush();
                        w.close();
                    } catch (Exception evt) {
                        JOptionPane.showMessageDialog(f, evt.getMessage());
                    }
                }
                // If the user cancelled the operation
                else
                    JOptionPane.showMessageDialog(f, "the user cancelled the operation");
                break;
            }
            case "Open" -> {
                // Create an object of JFileChooser class
                JFileChooser j1 = new JFileChooser("f:");

                // Invoke the showsOpenDialog function to show the save dialog
                int r = j1.showOpenDialog(null);

                // If the user selects a file
                if (r == JFileChooser.APPROVE_OPTION) {
                    // Set the label to the path of the selected directory
                    File fi = new File(j1.getSelectedFile().getAbsolutePath());

                    try {
                        // String
                        String s1 = "";
                        StringBuilder sl = new StringBuilder();

                        // File reader
                        FileReader fr = new FileReader(fi);

                        // Buffered reader
                        BufferedReader br = new BufferedReader(fr);

                        // Initialize sl
                        sl = new StringBuilder(br.readLine());

                        // Take the input from the file
                        while ((s1 = br.readLine()) != null) {
                            sl.append("\n").append(s1);
                        }

                        // Set the text
                        modt.setText(sl.toString());
                    } catch (Exception evt) {
                        JOptionPane.showMessageDialog(f, evt.getMessage());
                    }
                } else
                    JOptionPane.showMessageDialog(f, "the user cancelled the operation");
                break;
            }
            case "New" -> modt.setText("");
            case "Close" -> f.setVisible(false);
            case "Run" -> out.setVisible(true);
        }
    }
}
