package UI_for_hyperprob;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.*;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.lang.Object;
import java.awt.event.ActionListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.net.URI;
import javax.swing.text.*;
import java.util.*;
import java.awt.Color;
import java.awt.Font;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.MouseInfo;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.metal.*;

class editor extends JFrame implements ActionListener {

   // JTextArea modt;
    //JTextArea propt;
    JTextPane modt;
    JTextPane propt;
   // JEditorPane modt;
   // JEditorPane propt;
    JFrame f;
    JPanel P;
    JTextArea out;
    String mod_path = null;
    private Object Token;


    //StringBuilder output;
    editor() {
        f = new JFrame("HyperProb");
        P = new JPanel();
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");

            MetalLookAndFeel.setCurrentTheme(new OceanTheme());
        } catch (Exception e) {
        }

        modt = new JTextPane();
        //modt = new JEditorPane();
        modt.setPreferredSize(new Dimension(500,200));
       // modt = new JTextArea(15,50);
        JScrollPane scrollm = new JScrollPane(modt);
        scrollm.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        
        propt = new JTextPane();
       // propt = new JEditorPane();
        propt.setPreferredSize(new Dimension(500,50));
       // propt = new JTextArea(10,50);
        JScrollPane scrollp = new JScrollPane(propt);
        scrollp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);


        //menu bar
        JMenuBar mb = new JMenuBar();

        // Create a menu for menu
        JMenu m1 = new JMenu("File");

        // menu items
        JMenuItem mi1 = new JMenuItem("New Model");
        JMenuItem m01 = new JMenuItem("New Property");
        JMenuItem mi2 = new JMenuItem("Open Model");
        JMenuItem m02 = new JMenuItem("Open Property");
        JMenuItem mi3 = new JMenuItem("Save Model");
        JMenuItem m03 = new JMenuItem("Save Property");

        // Add action listener
        mi1.addActionListener(this);
        m01.addActionListener(this);
        mi2.addActionListener(this);
        m02.addActionListener(this);
        mi3.addActionListener(this);
        m03.addActionListener(this);

        m1.add(mi1);
        m1.add(m01);
        m1.add(mi2);
        m1.add(m02);
        m1.add(mi3);
        m1.add(m03);

        // Create a menu for menu
        JMenu m2 = new JMenu("Edit");


        // Create menu items
        JMenuItem mi4 = new JMenuItem("Cut");
        mi4.setMnemonic(KeyEvent.VK_X);
        JMenuItem mi5 = new JMenuItem("Copy");
        mi5.setMnemonic(KeyEvent.VK_C);
        JMenuItem mi6 = new JMenuItem("Paste");
        mi6.setMnemonic(KeyEvent.VK_V);

        // Add action listener
        mi4.addActionListener(this);
        mi5.addActionListener(this);
        mi6.addActionListener(this);

        m2.add(mi4);
        m2.add(mi5);
        m2.add(mi6);

        JMenu m3 = new JMenu("Help");

        JMenuItem mi7 = new JMenuItem("User Manual");
        JMenuItem mi8 = new JMenuItem("Example");
        JMenuItem mi9 = new JMenuItem("Prism Website");

        mi7.addActionListener(this);
        mi8.addActionListener(this);
        mi9.addActionListener(this);

        m3.add(mi7);
        m3.add(mi8);
        m3.add(mi9);



        mb.add(m1);
        mb.add(m2);
        mb.add(m3);



        JPanel model = new JPanel();
        JPanel prop = new JPanel();
        JPanel run = new JPanel();


        //Run Panel
        out = new JTextArea(15,60);
        JScrollPane scrollo = new JScrollPane(out);
        scrollo.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        run.setBorder (new TitledBorder(new EtchedBorder(), "Output"));
        run.add(scrollo);
        out.setVisible(false);
        JButton b = new JButton("Run");
        b.addActionListener(this);
        b.setBounds(50,100,95,30);
        run.add(b);

      /*  String path_file = "source.py";
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
        String cmd_array[] = new String[]{"python", path_file, path_model, path_property}; /*

        //String cmd_array[] = new String[]{"python", "sum.py"};

      //  Process p = null;
      //  try {
            //p = Runtime.getRuntime().exec("python " + path);
            //System.out.println("python " + path_file + " " + path_model + " " + path_property);
          //  p = Runtime.getRuntime().exec(cmd_array);
     //   } catch (IOException ioException) {
         //   ioException.printStackTrace();
      //  }

    /* assert p != null;
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
        out.setText(output.toString()); */

        f.setJMenuBar(mb);
        model.setBorder (new TitledBorder(new EtchedBorder(), "Model"));
        model.add(scrollm);
        model.setSize(250,250);
        JButton c1 = new JButton("Compile Model");



        c1.addActionListener(this);
        c1.setBounds(50,100,95,30);
        model.add(c1);
        prop.setBorder (new TitledBorder(new EtchedBorder(), "Property"));
        prop.add(scrollp);
        prop.setSize(250,250);
        JButton c2 = new JButton("Compile Property");
        c2.addActionListener(this);
        c2.setBounds(50,100,95,30);
        prop.add(c2);
        JSplitPane sp = new JSplitPane(JSplitPane.VERTICAL_SPLIT, model, prop);
        sp.setDividerLocation(0.5);
        f.setSize(750, 750);
        f.getContentPane().add(sp);
        f.add(run,BorderLayout.SOUTH);
        f.show();
    }

    // If a button is pressed
    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();


       modt.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                if (s.equals("Cut")) {
                    modt.cut();
                } if (s.equals("Copy")) {
                    modt.copy();
                } if (s.equals("Paste")) {
                    modt.paste();
                }

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

      /*  propt.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                if (s.equals("Cut")) {
                    propt.cut();
                } if (s.equals("Copy")) {
                    propt.copy();
                } if (s.equals("Paste")) {
                    propt.paste();
                }

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        }); */

        //String open_name = null;
        Component focusOwner = KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusOwner();
      /*  modt.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e1) {
                if (s.equals("Cut")) {
                    modt.cut();
                } if (s.equals("Copy")) {
                    modt.copy();
                } if (s.equals("Paste")) {
                    modt.paste();
                }
            }

            public void focusLost(FocusEvent e1) {

            }
        }); */

        propt.addFocusListener(new FocusListener() {

            public void focusGained(FocusEvent e) {
                if (s.equals("Cut")) {
                    propt.cut();
                } if (s.equals("Copy")) {
                    propt.copy();
                } if (s.equals("Paste")) {
                    propt.paste();
                }


            }

            @Override
            public void focusLost(FocusEvent e) {
                // propt.cut();

            }
        });

        switch (s) {

        /*    case "Cut" -> {
                if (modt.isFocusOwner()) {
                    modt.cut();
                } else {
                    propt.cut();
                }

            }
        /*    case "Copy" -> {
                if (focusOwner.equals(propt)) {
                    propt.copy();
                } else {
                    modt.copy();
                }
            }
            case "Paste" -> {
                if (Objects.equals(focusOwner, propt)) {
                    propt.paste();
                } else {
                    modt.paste();
                }
            } */

            // case "Cut" -> modt.cut();
           // case "Copy" -> modt.copy();
           // case "Paste" -> modt.paste();
            case "Save Model" -> {

                JFileChooser j = new JFileChooser("f:");
                j.addChoosableFileFilter(new FileNameExtensionFilter("*.nm", "nm"));

                // Invoke the showsSaveDialog function to show the save dialog
                int r = j.showSaveDialog(null);

                if (r == JFileChooser.APPROVE_OPTION) {

                    // Set the label to the path of the selected directory
                    File fi = new File(j.getSelectedFile().getAbsolutePath());

                    try {
                        String filewr = j.getSelectedFile().getAbsolutePath();

                        if (!filewr.substring(filewr.lastIndexOf(".")+1).equals("nm")) {
                            filewr += ".nm";
                        }
                        // Create a file writer
                      //  FileWriter wr = new FileWriter(fi, false);
                        FileWriter wr = new FileWriter(filewr);
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
            case "Save Property" -> {

                JFileChooser jf = new JFileChooser("f:");
                jf.addChoosableFileFilter(new FileNameExtensionFilter("*.txt", "txt"));

                // Invoke the showsSaveDialog function to show the save dialog
                int r = jf.showSaveDialog(null);

                if (r == JFileChooser.APPROVE_OPTION) {

                    // Set the label to the path of the selected directory
                    File fi = new File(jf.getSelectedFile().getAbsolutePath());

                    try {
                        String filewr = jf.getSelectedFile().getAbsolutePath();

                        if (!filewr.substring(filewr.lastIndexOf(".")+1).equals("txt")) {
                            filewr += ".txt";
                        }
                        // Create a file writer
                       // FileWriter wr = new FileWriter(fi, false);
                        FileWriter wr = new FileWriter(filewr);
                        // Create buffered writer to write
                        BufferedWriter w = new BufferedWriter(wr);

                            // Write
                            w.write(propt.getText());

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
            case "Open Model" -> {
                // Create an object of JFileChooser class
                JFileChooser j1 = new JFileChooser("f:");
                j1.addChoosableFileFilter(new FileNameExtensionFilter("*.nm", "nm"));
                // Invoke the showsOpenDialog function to show the save dialog
                int r = j1.showOpenDialog(null);
                String [] options = {".txt", ".nm"};
                JComboBox file = new JComboBox(options);
                j1.add(file);

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
                        String open_name = fi.toString();
                        System.out.print("\nFile opened:" + open_name);
                        mod_path = open_name;
                        StyledDocument doc = (StyledDocument) modt.getDocument();

                        ArrayList<String> keywordList = new ArrayList<String>();
                        while ((s1 = br.readLine()) != null) {
                            sl.append("\n").append(s1);


                            /*    for (int i = constants.COMMENT+1; i < constants.NOT; i++) {
                                    keywordList.add(constants.tokenImage[i].replaceAll("\u005c"", ""));
                                }

                            int i;
                            String ss, res = "";
                            // break into lines
                            while ((i = s1.indexOf("\u005cn")) != -1) {
                                ss = s1.substring(0, i);
                                s1 = s1.substring(i+1);
                                // add "//" to non-empty lines
                                if (ss.trim().length()>0) res += "// " + ss;
                                res += "\u005cn";
                            }
                            // deal with any trailing characters (with no new line ending them)
                            if (s1.trim().length()>0) {
                                res += "// " + s1 + "\u005cn";
                                modt.setText(res);
                            } */


                        }
                        // Set the text

                        modt.setText(sl.toString());



                        modt.getDocument().addDocumentListener(new DocumentListener() {
                            @Override
                            public void insertUpdate(DocumentEvent e) {


                                System.out.print(" Text added");
                                String s = constants.module;
                                String content = modt.getText();
                                BufferedReader reader = new BufferedReader(fr);

                                String key = constants.tokenImage.toString();
                                for (int i =0; i < constants.tokenImage.length; i++) {
                                    if(content.contains(constants.tokenImage[i])) {
                                        System.out.print("True");
                                       /* switch(key) {
                                            case "module": {
                                                System.out.print("True");
                                            }
                                        } */


                                    }
                                }



                                //while(content.lastIndexOf(s) >= 0) {

                                    //int index = content.lastIndexOf(s);
                                   // int end = index + s.length();


                                  //  modt.setCaretPosition(end);

                                   // System.out.print("'" + s + "' found. Press ESC to end search");

                                  //  content = content.substring(0, index - 1);
                                }

                          //  }

                            @Override
                            public void removeUpdate(DocumentEvent e) {
                                System.out.print(" Text removed");

                            }

                            @Override
                            public void changedUpdate(DocumentEvent e) {

                            }
                        });


                    } catch (Exception evt) {
                        JOptionPane.showMessageDialog(f, evt.getMessage());
                    }
                } else
                    JOptionPane.showMessageDialog(f, "the user cancelled the operation");
                    break;
            }
            case "Open Property" -> {
                // Create an object of JFileChooser class
                JFileChooser j2 = new JFileChooser("f:");
                j2.addChoosableFileFilter(new FileNameExtensionFilter("*.txt", "txt"));
                // Invoke the showsOpenDialog function to show the save dialog
                int r = j2.showOpenDialog(null);
                String [] options = {".txt", ".nm"};
                JComboBox file = new JComboBox(options);
                j2.add(file);

                // If the user selects a file
                if (r == JFileChooser.APPROVE_OPTION) {
                    // Set the label to the path of the selected directory
                    File fi = new File(j2.getSelectedFile().getAbsolutePath());

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
                        String open_name = fi.toString();
                        System.out.print("\nFile opened:" + open_name);
                        mod_path = open_name;
                        // Take the input from the file
                        while ((s1 = br.readLine()) != null) {
                            sl.append("\n").append(s1);


                        }

                        // Set the text
                        propt.setText(sl.toString());
                    } catch (Exception evt) {
                        JOptionPane.showMessageDialog(f, evt.getMessage());
                    }
                } else
                    JOptionPane.showMessageDialog(f, "the user cancelled the operation");
                break;
            }
            case "New Model" -> modt.setText("");
            case "New Property" -> propt.setText("");
            case "Run" -> out.setVisible(true);
            case "Compile Model" -> {
                if (mod_path != null){
                    String cmd_array[] = new String[]{"python", "check_model.py", mod_path};
                    Process p = null;
                    try {
                        p = Runtime.getRuntime().exec(cmd_array);
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                    assert p != null;
                    InputStream error = p.getErrorStream();
                    int c = 0;
                    StringBuilder output = new StringBuilder();
                    while (true) {
                    try {
                        if ((c = error.read()) == -1) break;
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                    //System.out.print((char) c);
                        output.append((char) c);
                    }
                    BufferedReader bfr = new BufferedReader(new InputStreamReader(p.getInputStream()));
                    String line = "";

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
                    out.setVisible(true);
                }

            }
            case "User Manual" -> {
                try {
                    String home = "https://oyendrila-dobe.github.io/HyperProb/";
                    Desktop.getDesktop().browse(URI.create(home));
                } catch (IOException ioException) {
                    System.out.println("Error");
                }

            }
            case "Example" -> {
                try {
                    String ex = "https://oyendrila-dobe.github.io/HyperProb/benchmarks/";
                    Desktop.getDesktop().browse(URI.create(ex));
                } catch (IOException ioException) {
                    System.out.println("Error");
                }

            }
            case "Prism Website" -> {
                try {
                    String prism = "https://www.prismmodelchecker.org/manual/ThePRISMLanguage/Introduction";
                    Desktop.getDesktop().browse(URI.create(prism));
                } catch (IOException ioException) {
                    System.out.println("Error");
                }

            }
        }
    }
}
