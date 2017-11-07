import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;
import javax.swing.border.*;
import javax.swing.table.*;

public class Primary extends Object {
    private JFrame primaryFrame = new JFrame("Phone Book");
    private JFrame adaugaFrame = new JFrame("Adaugare");
    private JFrame modificaFrame = new JFrame("Modificare");
    private JFrame cautaFrame = new JFrame("Cautare");
    private JFrame aboutFrame = new JFrame("About");
    private JMenuBar menuBar = new JMenuBar();
    private JMenu fileMenu = new JMenu("File");
    private JMenu abonatMenu = new JMenu("Abonati");
    private JMenu helpMenu = new JMenu("Help");
  //  private JMenu optionsMenu = new JMenu("Options");
    private JTable table = new JTable();
    private Container c = primaryFrame.getContentPane();
    private Container p = new JFrame().getContentPane();

    //fileMenu members
    private JMenuItem newItem = new JMenuItem("New");
    private JMenuItem openItem = new JMenuItem("Open");
    private JMenuItem saveItem = new JMenuItem("Save");
    private JMenuItem exitItem = new JMenuItem("Exit");

    //abonatMenu members
    private JMenuItem adaugaItem = new JMenuItem("Adauga");
    private JMenuItem cautaItem = new JMenuItem("Cauta");
    private JMenuItem stergeItem = new JMenuItem("Sterge");
    private JMenuItem modificaItem = new JMenuItem("Modifica");

    //helpMenu members
    private JMenuItem inregistrareItem = new JMenuItem("Inregistrare");
    private JMenuItem aboutItem = new JMenuItem("About");
    
    //optionsMenu members
    //private JMenuItem proprietatiItem = new JMenuItem("Proprietati");
    //private JMenuItem setariItem = new JMenuItem("Setari");
    
    //Window buttons
    private JButton adaugaButton = new JButton("Adauga");
    private JButton stergeButton = new JButton("Sterge");
    private JButton modificaButton = new JButton("Modifica");
    private JButton sorteazaButton = new JButton("Sorteaza");
    private JButton cautaButton = new JButton("Cauta");
    private JButton exitButton = new JButton("Exit");
   // private JButton setariButton = new JButton("Setari");
   // private JButton proprietatiButton = new JButton("Proprietati");
    
    //others
    private String[] choice = new String[] {"Nume","Prenume","CNP","Numar fix","Numar mobil","Adresa"};
    private JComboBox<String> sortChoice = new JComboBox<String>(choice);
    private CarteDeTelefon tModel = new CarteDeTelefon();
    private JCheckBox complet = new JCheckBox("Complet");
    private JFileChooser fileChooser = new JFileChooser();
    private JLabel fileLabel = new JLabel();

    //adaugaFrame
    private JLabel adNume = new JLabel("Nume:");
    private JLabel adPrenume = new JLabel("Prenume");
    private JLabel adCNP = new JLabel("CNP:");
    private JLabel adNrFix = new JLabel("Numar fix:");
    private JLabel adNrMob = new JLabel("Numar mobil:");
    private JLabel adAdresa = new JLabel("Adresa:");
    private JTextField adNumet = new JTextField(20);
    private JTextField adPrenumet = new JTextField(20);
    private JTextField adCNPt = new JTextField(20);
    private JTextField adNrFixt = new JTextField(20);
    private JTextField adNrMobt = new JTextField(20);
    private JTextField adAdresat = new JTextField(20);
    private JButton adButton = new JButton("Adauga");

    //modificaFrame
    private JLabel modNume = new JLabel("Nume:");
    private JLabel modPrenume = new JLabel("Prenume");
    private JLabel modCNP = new JLabel("CNP:");
    private JLabel modNrFix = new JLabel("Numar fix:");
    private JLabel modNrMob = new JLabel("Numar mobil:");
    private JLabel modAdresa = new JLabel("Adresa:");
    private JTextField modNumet = new JTextField(20);
    private JTextField modPrenumet = new JTextField(20);
    private JTextField modCNPt = new JTextField(20);
    private JTextField modNrFixt = new JTextField(20);
    private JTextField modNrMobt = new JTextField(20);
    private JTextField modAdresat = new JTextField(20);
    private JButton modButton = new JButton("Modifica");

    //cautaFrame
    private JLabel caNume = new JLabel("Nume:");
    private JLabel caPrenume = new JLabel("Prenume");
    private JLabel caCNP = new JLabel("CNP:");
    private JLabel caNrFix = new JLabel("Numar fix:");
    private JLabel caNrMob = new JLabel("Numar mobil:");
    private JLabel caAdresa = new JLabel("Adresa:");
    private JTextField caNumet = new JTextField(20);
    private JTextField caPrenumet = new JTextField(20);
    private JTextField caCNPt = new JTextField(20);
    private JTextField caNrFixt = new JTextField(20);
    private JTextField caNrMobt = new JTextField(20);
    private JTextField caAdresat = new JTextField(20);
    private JButton caButton = new JButton("Cauta");

    //ads
    private String a = "first.jpg";
    private String b = "second.jpg";
    private String c1 = "third.jpg";
    private String d = "fourth.jpg";
    private String e = "fifth.jpg";
    private ImageIcon[] poze = new ImageIcon[] {new ImageIcon(a),new ImageIcon(b),new ImageIcon(c1),new ImageIcon(d),new ImageIcon(e)};
    private JLabel banLabel = new JLabel();

    //aboutFrame
    private JLabel aLabel = new JLabel("PHONE BOOK");
    private JLabel aLabel1 = new JLabel("Product of 2013");
    private JLabel aLabel2 = new JLabel("Property of some guy!");

    public Primary() {
        
        //Pregatire componente pentru desenare
        table.setModel(tModel);
        table.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        fileMenu.add(newItem); fileMenu.add(openItem); fileMenu.add(saveItem); fileMenu.addSeparator(); fileMenu.add(exitItem);
        abonatMenu.add(adaugaItem); abonatMenu.add(cautaItem); abonatMenu.add(stergeItem); abonatMenu.add(modificaItem);
        helpMenu.add(inregistrareItem); helpMenu.addSeparator(); helpMenu.add(aboutItem);
        menuBar.add(fileMenu); menuBar.add(abonatMenu); menuBar.add(helpMenu);//menuBar.add(optionsMenu);
        primaryFrame.setJMenuBar(menuBar);
        primaryFrame.getContentPane().setLayout(new GridBagLayout());
        design();
        fileLabel.setText("File: "+tModel.getSaveFilePath());

        //Atribuire functionalitate componente
        function();

        primaryFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        primaryFrame.setBounds(new Rectangle(350,350,600,500));
        primaryFrame.pack();
        //primaryFrame.setResizable(false);
        primaryFrame.setVisible(true);
    }

    private void design() {
        primaryFrame.setContentPane(p);
        p.add(banLabel);
        banLabel.setIcon(new ImageIcon("splash.jpeg"));
        banLabel.setText("Ghita Tudor");
        banLabel.setVerticalTextPosition(SwingConstants.BOTTOM);
        banLabel.setHorizontalTextPosition(SwingConstants.CENTER);
        primaryFrame.pack();

        fileMenu.setMnemonic(KeyEvent.VK_F);
        abonatMenu.setMnemonic(KeyEvent.VK_A);
        helpMenu.setMnemonic(KeyEvent.VK_H);
        //optionsMenu.setMnemonic(KeyEvent.VK_O);

        newItem.setEnabled(false);
        openItem.setEnabled(false);
        saveItem.setEnabled(false);
        adaugaItem.setEnabled(false);
        cautaItem.setEnabled(false);
        stergeItem.setEnabled(false);
        modificaItem.setEnabled(false);
      //  setariItem.setEnabled(false);
       // proprietatiItem.setEnabled(false);

        GridBagConstraints gbc = new GridBagConstraints();

        //adaugaFrame
        adaugaFrame.getContentPane().setLayout(new GridLayout(6,2));
        adaugaFrame.setBounds(new Rectangle(400,400,0,0));
        adaugaFrame.add(adNume);
        adaugaFrame.add(adNumet);
        adaugaFrame.add(adPrenume);
        adaugaFrame.add(adPrenumet);
        adaugaFrame.add(adCNP);
        adaugaFrame.add(adCNPt);
        adaugaFrame.add(adNrFix);
        adaugaFrame.add(adNrFixt);
        adaugaFrame.add(adNrMob);
        adaugaFrame.add(adNrMobt);
        adaugaFrame.add(adAdresa);
        adaugaFrame.add(adAdresat);
        adaugaFrame.add(adButton);

        //modificaFrame
        modificaFrame.getContentPane().setLayout(new GridLayout(6,2));
        modificaFrame.setBounds(new Rectangle(400,400,0,0));
        modificaFrame.add(modNume);
        modificaFrame.add(modNumet);
        modificaFrame.add(modPrenume);
        modificaFrame.add(modPrenumet);
        modificaFrame.add(modCNP);
        modificaFrame.add(modCNPt);
        modificaFrame.add(modNrFix);
        modificaFrame.add(modNrFixt);
        modificaFrame.add(modNrMob);
        modificaFrame.add(modNrMobt);
        modificaFrame.add(modAdresa);
        modificaFrame.add(modAdresat);
        modificaFrame.add(modButton);

        //cautaFrame
        cautaFrame.getContentPane().setLayout(new GridLayout(6,2));
        cautaFrame.setBounds(new Rectangle(400,400,0,0));
        cautaFrame.add(caNume);
        cautaFrame.add(caNumet);
        cautaFrame.add(caPrenume);
        cautaFrame.add(caPrenumet);
        cautaFrame.add(caCNP);
        cautaFrame.add(caCNPt);
        cautaFrame.add(caNrFix);
        cautaFrame.add(caNrFixt);
        cautaFrame.add(caNrMob);
        cautaFrame.add(caNrMobt);
        cautaFrame.add(caAdresa);
        cautaFrame.add(caAdresat);
        cautaFrame.add(caButton);

        //cautaButton
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5,10,10,0);
        gbc.weightx = 0.4;
        c.add(cautaButton,gbc);

        //sort
        JPanel sortPanel = new JPanel();
        sortPanel.add(sorteazaButton);
        sortPanel.add(sortChoice);
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.insets = new Insets(5,0,5,10);
        gbc.weightx = 0.6;
        c.add(sortPanel,gbc);

        //left panel
        gbc = new GridBagConstraints();
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new GridLayout(4,1));
        leftPanel.add(adaugaButton); leftPanel.add(modificaButton); leftPanel.add(stergeButton); leftPanel.add(complet);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridheight = 3;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        c.add(leftPanel,gbc);
        cautaButton.setToolTipText("Cauta un abonat dupa Nume/Prenume/CNP/Numar fix/Numar mobil");
        adaugaButton.setToolTipText("Adauga in tabel un nou abonat");
        modificaButton.setToolTipText("Modifica atributele unui abonat existent");
        stergeButton.setToolTipText("Sterge un abonat din tabel");
        sorteazaButton.setToolTipText("Sorteaza abonatii dupa criteriu:");
        exitButton.setToolTipText("Iesire din aplicatie");

        //table
        JScrollPane tPanel = new JScrollPane(table);
        tPanel.setPreferredSize(new Dimension(500,200));
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.gridheight = 3;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.insets = new Insets(5,10,5,10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        c.add(tPanel,gbc);

        //exitButton
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.insets = new Insets(5,10,10,0);
        gbc.anchor = GridBagConstraints.NORTHWEST;
        c.add(exitButton,gbc);
        
        //file info
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.WEST;
        c.add(fileLabel,gbc);

        //temporary contentPane
        TimerTask tt = new TimerTask() {
                int i=0;
                public void run() {
                    banLabel.setText("");
                    banLabel.setIcon(poze[i++]);
                    if (i==poze.length) i=0;
                }
            };
        java.util.Timer t = new java.util.Timer(true);
        t.schedule(tt,2000,2000);

        //aboutFrame
        aLabel.setHorizontalAlignment(SwingConstants.CENTER);
        aLabel1.setHorizontalAlignment(SwingConstants.CENTER);
        aLabel2.setHorizontalAlignment(SwingConstants.CENTER);
        aboutFrame.add(aLabel,BorderLayout.NORTH); aboutFrame.add(aLabel1,BorderLayout.CENTER); aboutFrame.add(aLabel2,BorderLayout.SOUTH);
        aboutFrame.setBounds(new Rectangle(500,500,200,100));

    }

    private void modify() {
        int sr = table.getSelectedRow();
        if (sr==-1)
            JOptionPane.showMessageDialog(primaryFrame.getContentPane(),"Selectati un abonat!","Information",JOptionPane.INFORMATION_MESSAGE);
        else {
            modNumet.setText(tModel.getValueAt(sr,0).toString());
            modPrenumet.setText(tModel.getValueAt(sr,1).toString());
            modCNPt.setText(tModel.getValueAt(sr,2).toString());
            modNrFixt.setText(tModel.getValueAt(sr,3).toString());
            modNrMobt.setText(tModel.getValueAt(sr,4).toString());
            modificaFrame.pack();
            modificaFrame.setVisible(true);
        }
    }

    private void delete() {
        int s = table.getSelectedRow();
        if (s==-1) JOptionPane.showMessageDialog(primaryFrame.getContentPane(),"Selectati un abonat!","Information",JOptionPane.INFORMATION_MESSAGE);
        else {
            String nume = (String)tModel.getValueAt(s,0)+" "+tModel.getValueAt(s,1);
            int a = JOptionPane.showConfirmDialog(primaryFrame.getContentPane(),"Stergeti abonatul "+nume+"?","Confirmare",JOptionPane.YES_NO_OPTION);
            if (a==0) tModel.delete(table.getSelectedRow());
        }
    }

    private void function() {

        //sort by column selection
        table.getTableHeader().addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int col = table.columnAtPoint(e.getPoint());
                tModel.sort((String)table.getColumnModel().getColumn(col).getHeaderValue());
            }
        });

        //exit
        ActionListener event = new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    tModel.last();
                    System.exit(0);
                }
            };
        exitItem.addActionListener(event);
        exitButton.addActionListener(event);

        //register
        event = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String input = JOptionPane.showInputDialog(primaryFrame.getContentPane(),"Please type your product key below:","Registration",JOptionPane.PLAIN_MESSAGE);
                if (input != null && input.equals("0101-firstcopy")) {
                    JOptionPane.showMessageDialog(primaryFrame.getContentPane(),"WELCOME!");
                    primaryFrame.setVisible(false);
                    primaryFrame.setContentPane(c);
                    newItem.setEnabled(true);
                    openItem.setEnabled(true);
                    saveItem.setEnabled(true);
                    adaugaItem.setEnabled(true);
                    cautaItem.setEnabled(true);
                    stergeItem.setEnabled(true);
                    modificaItem.setEnabled(true);
                    inregistrareItem.setEnabled(false);
                    complet.setSelected(true);
                    JPanel banPanel = new JPanel();
                    GridBagConstraints gbc = new GridBagConstraints();
                    gbc.gridx = 0;
                    gbc.gridy = 5;
                    gbc.gridwidth = 4;
                    gbc.weightx = 1.0;
                    gbc.fill = GridBagConstraints.HORIZONTAL;
                    gbc.insets = new Insets(15,10,10,10);
                    gbc.ipadx = 0;
                    gbc.ipady = 0;
                    gbc.anchor = GridBagConstraints.CENTER;
                    banPanel.setLayout(new GridLayout(1,1));
                    banPanel.add(banLabel);
                    banPanel.setPreferredSize(new Dimension(100,100));
                    c.add(banPanel,gbc);
                    primaryFrame.pack();
                    primaryFrame.setVisible(true);
                }
                else if (input != null) {
                    JOptionPane.showMessageDialog(primaryFrame.getContentPane(),"Product key not valid!");
                }
            }
        };
        inregistrareItem.addActionListener(event);

        //adauga
        event = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                adaugaFrame.pack();
                adaugaFrame.setVisible(true);
            }};
        adaugaItem.addActionListener(event);
        adaugaButton.addActionListener(event);

        //adaugaFrame
        event  = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    tModel.add(adNumet.getText(),adPrenumet.getText(),adCNPt.getText(),adNrFixt.getText(),adNrMobt.getText(),adAdresat.getText());
                    adNumet.setText("");
                    adPrenumet.setText("");
                    adCNPt.setText("");
                    adNrFixt.setText("");
                    adNrMobt.setText("");
                    adAdresat.setText("");
                    adaugaFrame.setVisible(false);
                }
                catch(IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(primaryFrame.getContentPane(),ex.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
                }
            }
        };
        adButton.addActionListener(event);

        //sterge
        event = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                delete();
            }
        };
        stergeButton.addActionListener(event);
        stergeItem.addActionListener(event);

        table.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE,0),"delete");
        table.getActionMap().put("delete",new AbstractAction() {
                public void actionPerformed(ActionEvent e) {
                    delete();
                }
            });

        //modifica
        event = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                modify();
            }
        };
        modificaButton.addActionListener(event);
        modificaItem.addActionListener(event);

        //modificaFrame
        event = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    tModel.modify(modNumet.getText(),modPrenumet.getText(),modCNPt.getText(),modNrFixt.getText(),modNrMobt.getText(), table.getSelectedRow());
                    modNumet.setText("");
                    modPrenumet.setText("");
                    modCNPt.setText("");
                    modNrFixt.setText("");
                    modNrMobt.setText("");
                    modAdresat.setText("");
                    modificaFrame.setVisible(false);
                }
                catch(IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(primaryFrame.getContentPane(),ex.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
                }
            }
        };
        modButton.addActionListener(event);

        //selectie
        table.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    if (e.getClickCount()==2) {
                        modify();
                    }
                }});

        table.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER,0),"modify");
        table.getActionMap().put("modify",new AbstractAction() {
                public void actionPerformed(ActionEvent e) {
                    modify();
                }});

        //sortare
        event = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tModel.sort((String)sortChoice.getSelectedItem());
            }
        };
        sorteazaButton.addActionListener(event);

        //complet
        event = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tModel.set(complet.isSelected());
            }
        };
        complet.addActionListener(event);

        //cauta
        event = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cautaFrame.pack();
                cautaFrame.setVisible(true);
            }
        };
        cautaButton.addActionListener(event);
        cautaItem.addActionListener(event);

        //cautaFrame
        event = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                complet.setSelected(false);
                tModel.search(caNumet.getText(),caPrenumet.getText(),caCNPt.getText(),caNrFixt.getText(),caNrMobt.getText(),caAdresat.getText());
                caNumet.setText("");
                caPrenumet.setText("");
                caCNPt.setText("");
                caNrFixt.setText("");
                caNrMobt.setText("");
                caAdresat.setText("");
                cautaFrame.setVisible(false);
            }
        };
        caButton.addActionListener(event);

        //newItem
        event = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tModel.newData();
                complet.setSelected(true);
                fileLabel.setText("File: "+tModel.getSaveFilePath());
            }
        };
        newItem.addActionListener(event);

        //fileChooser
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

        //openItem
        event  = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fileChooser.showOpenDialog(primaryFrame.getContentPane());
                File f = fileChooser.getSelectedFile();
                if (f!=null) {
                    if (f.getName().endsWith(".adb")) {
                        try {
                            if (!f.exists()) f.createNewFile();
                            tModel.open(f);
                        }
                        catch(Exception ex) {
                            JOptionPane.showMessageDialog(primaryFrame.getContentPane(),"Datorita unei erori, fisierul nu a putut fi deschis","Eroare",JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    else JOptionPane.showMessageDialog(primaryFrame.getContentPane(),"Extensie gresita! Extensie asteptata: .adb","Eroare",JOptionPane.ERROR_MESSAGE);
                }
                fileLabel.setText("File: "+tModel.getSaveFilePath());
            }
        };
        openItem.addActionListener(event);

        //saveItem
        event = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (tModel.isSaved()) tModel.save();
                else {
                    fileChooser.showSaveDialog(primaryFrame.getContentPane());
                    File f = fileChooser.getSelectedFile();
                    if (f!=null) {
                        if (f.getName().endsWith(".adb")) {
                            try {
                                if (!f.exists()) f.createNewFile();
                                tModel.save(f);
                            }
                            catch(Exception ex) {
                                JOptionPane.showMessageDialog(primaryFrame.getContentPane(),"Datorita unei erori, fisierul nu a putut fi salvat","Eroare",JOptionPane.ERROR_MESSAGE);
                            }
                        }
                        else JOptionPane.showMessageDialog(primaryFrame.getContentPane(),"Extensie gresita! Extensie asteptata: .adb","Eroare",JOptionPane.ERROR_MESSAGE);
                    }
                }
                fileLabel.setText("File: "+tModel.getSaveFilePath());
            }
        };
        saveItem.addActionListener(event);

        //aboutFrame
        event  = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                aboutFrame.setVisible(true);
            }
        };
        aboutItem.addActionListener(event);

    }

    public static void main(String[] args) {
        Primary c = new Primary();
    }
}