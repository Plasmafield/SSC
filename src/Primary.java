import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;
import javax.swing.border.*;
import javax.swing.table.*;

public class Primary extends Object {
    private JFrame primaryFrame = new JFrame("Bank Viewer");
    private JFrame addFrame = new JFrame("Add");
    private JFrame modifyFrame = new JFrame("Modify");
    private JFrame searchFrame = new JFrame("Search");
    private JFrame aboutFrame = new JFrame("About");
    private JMenuBar menuBar = new JMenuBar();
    private JMenu fileMenu = new JMenu("File");
    private JMenu clientMenu = new JMenu("Clients");
    private JMenu helpMenu = new JMenu("Help");
    private JTable table = new JTable();
    private Container c = primaryFrame.getContentPane();
    private Container p = new JFrame().getContentPane();

    //fileMenu members
    private JMenuItem newItem = new JMenuItem("New");
    private JMenuItem openItem = new JMenuItem("Open");
    private JMenuItem saveItem = new JMenuItem("Save");
    private JMenuItem exitItem = new JMenuItem("Exit");

    //abonatMenu members
    private JMenuItem addItem = new JMenuItem("Add");
    private JMenuItem searchItem = new JMenuItem("Search");
    private JMenuItem deleteItem = new JMenuItem("Delete");
    private JMenuItem modifyItem = new JMenuItem("Modify");

    //helpMenu members
    private JMenuItem logItem = new JMenuItem("Login");
    private JMenuItem aboutItem = new JMenuItem("About");

    //Window buttons
    private JButton addButton = new JButton("Add");
    private JButton deleteButton = new JButton("Delete");
    private JButton modifyButton = new JButton("Modify");
    private JButton sortButton = new JButton("Sort");
    private JButton searchButton = new JButton("Search");
    private JButton exitButton = new JButton("Exit");

    //others
    private String[] choice = new String[] {"Name","Surname","PIN"};
    private JComboBox<String> sortChoice = new JComboBox<String>(choice);
    private Database tModel = new Database();
    private JCheckBox complete = new JCheckBox("Complete");
    private JFileChooser fileChooser = new JFileChooser();
    private JLabel fileLabel = new JLabel();
    private int content;
    private int oldMousePos;
    private int timeoutSeconds;
    private int inactiveSeconds;
    private TimerTask logoutTask;
    private java.util.Timer logoutTimer;

    //addFrame
    private JLabel addName = new JLabel("Name:");
    private JLabel addSurname = new JLabel("Surname");
    private JLabel addPIN = new JLabel("PIN:");
    private JLabel addIBAN = new JLabel("IBAN:");
    private JTextField addNamet = new JTextField(20);
    private JTextField addSurnamet = new JTextField(20);
    private JTextField addPINt = new JTextField(20);
    private JTextField addIBANt = new JTextField(20);
    private JButton addWindowButton = new JButton("Add");

    //modifyFrame
    private JLabel modName = new JLabel("Name:");
    private JLabel modSurname = new JLabel("Surname:");
    private JLabel modPIN = new JLabel("PIN:");
    private JLabel modIBAN = new JLabel("IBAN:");
    private JTextField modNamet = new JTextField(20);
    private JTextField modSurnamet = new JTextField(20);
    private JTextField modPINt = new JTextField(20);
    private JTextField modIBANt = new JTextField(20);
    private JButton modWindowButton = new JButton("Modify");

    //searchFrame
    private JLabel searchName = new JLabel("Name:");
    private JLabel searchSurname = new JLabel("Surname");
    private JLabel searchPIN = new JLabel("PIN:");
    private JLabel searchIBAN = new JLabel("IBAN:");
    private JTextField searchNamet = new JTextField(20);
    private JTextField searchSurnamet = new JTextField(20);
    private JTextField searchPINt = new JTextField(20);
    private JTextField searchIBANt = new JTextField(20);
    private JButton searchWindowButton = new JButton("Search");

    private JLabel banLabel = new JLabel();

    //aboutFrame
    private JLabel aboutTitle = new JLabel("BANK VIEWER");
    private JLabel aboutContent = new JLabel("Product of 2017");
    private JLabel aboutCopy = new JLabel("Property of team RAMBO!");

    public Primary() {
        
        //Pregatire componente pentru desenare
        table.setModel(tModel);
        table.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        fileMenu.add(newItem); fileMenu.add(openItem); fileMenu.add(saveItem); fileMenu.addSeparator(); fileMenu.add(exitItem);
        clientMenu.add(addItem); clientMenu.add(searchItem); clientMenu.add(deleteItem); clientMenu.add(modifyItem);
        helpMenu.add(logItem); helpMenu.addSeparator(); helpMenu.add(aboutItem);
        menuBar.add(fileMenu); menuBar.add(clientMenu); menuBar.add(helpMenu);
        primaryFrame.setJMenuBar(menuBar);
        primaryFrame.getContentPane().setLayout(new GridBagLayout());
        design();
        fileLabel.setText("File: "+tModel.getSaveFilePath());
        
        content = 0;
        oldMousePos = 0;
        timeoutSeconds = 300;
        inactiveSeconds = 0;

        //Atribuire functionalitate componente
        function();

        primaryFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        primaryFrame.setBounds(new Rectangle(350,350,600,500));
        primaryFrame.pack();
        //primaryFrame.setResizable(false);
        primaryFrame.setVisible(true);
    }
    
    private void switchContent()
    {
        if (content == 0)
        {
            primaryFrame.setVisible(false);
            primaryFrame.setContentPane(c);
            newItem.setEnabled(true);
            openItem.setEnabled(true);
            saveItem.setEnabled(true);
            addItem.setEnabled(true);
            searchItem.setEnabled(true);
            deleteItem.setEnabled(true);
            modifyItem.setEnabled(true);
            //inregistrareItem.setEnabled(false);
            complete.setSelected(true);
            primaryFrame.pack();
            primaryFrame.setVisible(true);
            
            content = 1;
        }
        else
        {
            primaryFrame.setVisible(false);
            primaryFrame.setContentPane(p);
            newItem.setEnabled(false);
            openItem.setEnabled(false);
            saveItem.setEnabled(false);
            addItem.setEnabled(false);
            searchItem.setEnabled(false);
            deleteItem.setEnabled(false);
            modifyItem.setEnabled(false);
            //inregistrareItem.setEnabled(true);
            complete.setSelected(false);
            primaryFrame.pack();
            primaryFrame.setVisible(true);
            logoutTimer.cancel();
            
            content = 0;
        }
    }

    private void design() {
        primaryFrame.setContentPane(p);
        p.add(banLabel);
        
        primaryFrame.pack();

        fileMenu.setMnemonic(KeyEvent.VK_F);
        clientMenu.setMnemonic(KeyEvent.VK_A);
        helpMenu.setMnemonic(KeyEvent.VK_H);

        newItem.setEnabled(false);
        openItem.setEnabled(false);
        saveItem.setEnabled(false);
        addItem.setEnabled(false);
        searchItem.setEnabled(false);
        deleteItem.setEnabled(false);
        modifyItem.setEnabled(false);

        GridBagConstraints gbc = new GridBagConstraints();

        //addFrame
        addFrame.getContentPane().setLayout(new GridLayout(6,2));
        addFrame.setBounds(new Rectangle(400,400,0,0));
        addFrame.add(addName);
        addFrame.add(addNamet);
        addFrame.add(addSurname);
        addFrame.add(addSurnamet);
        addFrame.add(addPIN);
        addFrame.add(addPINt);
        addFrame.add(addIBAN);
        addFrame.add(addIBANt);
        addFrame.add(addWindowButton);

        //modifyFrame
        modifyFrame.getContentPane().setLayout(new GridLayout(6,2));
        modifyFrame.setBounds(new Rectangle(400,400,0,0));
        modifyFrame.add(modName);
        modifyFrame.add(modNamet);
        modifyFrame.add(modSurname);
        modifyFrame.add(modSurnamet);
        modifyFrame.add(modPIN);
        modifyFrame.add(modPINt);
        modifyFrame.add(modIBAN);
        modifyFrame.add(modIBANt);
        modifyFrame.add(modWindowButton);

        //searchFrame
        searchFrame.getContentPane().setLayout(new GridLayout(6,2));
        searchFrame.setBounds(new Rectangle(400,400,0,0));
        searchFrame.add(searchName);
        searchFrame.add(searchNamet);
        searchFrame.add(searchSurname);
        searchFrame.add(searchSurnamet);
        searchFrame.add(searchPIN);
        searchFrame.add(searchPINt);
        searchFrame.add(searchIBAN);
        searchFrame.add(searchIBANt);
        searchFrame.add(searchWindowButton);

        //cautaButton
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5,10,10,0);
        gbc.weightx = 0.4;
        c.add(searchButton,gbc);

        //sort
        JPanel sortPanel = new JPanel();
        sortPanel.add(sortButton);
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
        leftPanel.add(addButton); leftPanel.add(modifyButton); leftPanel.add(deleteButton); leftPanel.add(complete);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridheight = 3;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        c.add(leftPanel,gbc);
        searchButton.setToolTipText("Search a client after Name/Surname/PIN");
        addButton.setToolTipText("Add a new client");
        modifyButton.setToolTipText("Modify an existing client");
        deleteButton.setToolTipText("Delete a client");
        sortButton.setToolTipText("Sort clients");
        exitButton.setToolTipText("Exit from the application");

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

        //aboutFrame
        aboutTitle.setHorizontalAlignment(SwingConstants.CENTER);
        aboutContent.setHorizontalAlignment(SwingConstants.CENTER);
        aboutCopy.setHorizontalAlignment(SwingConstants.CENTER);
        aboutFrame.add(aboutTitle,BorderLayout.NORTH); aboutFrame.add(aboutContent,BorderLayout.CENTER); aboutFrame.add(aboutCopy,BorderLayout.SOUTH);
        aboutFrame.setBounds(new Rectangle(500,500,200,100));

    }

    private void modify() {
        int sr = table.getSelectedRow();
        if (sr==-1)
            JOptionPane.showMessageDialog(primaryFrame.getContentPane(),"Select a client!","Information",JOptionPane.INFORMATION_MESSAGE);
        else {
            modNamet.setText(tModel.getValueAt(sr,0).toString());
            modSurnamet.setText(tModel.getValueAt(sr,1).toString());
            modPINt.setText(tModel.getValueAt(sr,2).toString());
            modIBANt.setText(tModel.getValueAt(sr,3).toString());
            modifyFrame.pack();
            modifyFrame.setVisible(true);
        }
    }

    private void delete() {
        int s = table.getSelectedRow();
        if (s==-1) JOptionPane.showMessageDialog(primaryFrame.getContentPane(),"Select a client!","Information",JOptionPane.INFORMATION_MESSAGE);
        else {
            String nume = (String)tModel.getValueAt(s,0)+" "+tModel.getValueAt(s,1);
            int a = JOptionPane.showConfirmDialog(primaryFrame.getContentPane(),"Delete client "+nume+"?","Confirm",JOptionPane.YES_NO_OPTION);
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
                if (content == 1)
                {
                    switchContent();
                    return;
                }
                String input = JOptionPane.showInputDialog(primaryFrame.getContentPane(),"Please type your product key below:","Registration",JOptionPane.PLAIN_MESSAGE);
                if (input != null && input.equals("pass")) {
                    //JOptionPane.showMessageDialog(primaryFrame.getContentPane(),"WELCOME!");
                    switchContent();
                    logoutTask = new TimerTask() {
                        public void run() {
                            Point mouseLoc = MouseInfo.getPointerInfo().getLocation();
                            int mouseCoords = mouseLoc.x * mouseLoc.y;
                            if (mouseCoords == oldMousePos)
                            {
                                inactiveSeconds++;
                            }
                            else
                            {
                                oldMousePos = mouseCoords;
                                inactiveSeconds = 0;
                            }
                            if (inactiveSeconds == timeoutSeconds)
                            {
                                switchContent();
                            }
                        }
                    };
                    logoutTimer = new java.util.Timer();
                    logoutTimer.schedule(logoutTask, 1000, 1000);
                }
                else if (input != null) {
                    JOptionPane.showMessageDialog(primaryFrame.getContentPane(),"Password incorrect!");
                }
            }
        };
        logItem.addActionListener(event);

        //add
        event = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addFrame.pack();
                addFrame.setVisible(true);
            }};
        addItem.addActionListener(event);
        addButton.addActionListener(event);

        //addFrame
        event  = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    tModel.add(addNamet.getText(),addSurnamet.getText(),addPINt.getText(),addIBANt.getText());
                    addNamet.setText("");
                    addSurnamet.setText("");
                    addPINt.setText("");
                    addIBANt.setText("");
                    addFrame.setVisible(false);
                }
                catch(IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(primaryFrame.getContentPane(),ex.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
                }
            }
        };
        addWindowButton.addActionListener(event);

        //delete
        event = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                delete();
            }
        };
        deleteButton.addActionListener(event);
        deleteItem.addActionListener(event);

        table.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE,0),"delete");
        table.getActionMap().put("delete",new AbstractAction() {
                public void actionPerformed(ActionEvent e) {
                    delete();
                }
            });

        //modify
        event = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                modify();
            }
        };
        modifyButton.addActionListener(event);
        modifyItem.addActionListener(event);

        //modifyFrame
        event = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    tModel.modify(modNamet.getText(),modSurnamet.getText(),modPINt.getText(),modIBANt.getText(),table.getSelectedRow());
                    modNamet.setText("");
                    modSurnamet.setText("");
                    modPINt.setText("");
                    modIBANt.setText("");
                    modifyFrame.setVisible(false);
                }
                catch(IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(primaryFrame.getContentPane(),ex.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
                }
            }
        };
        modWindowButton.addActionListener(event);

        //select
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

        //sort
        event = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tModel.sort((String)sortChoice.getSelectedItem());
            }
        };
        sortButton.addActionListener(event);

        //complete
        event = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tModel.set(complete.isSelected());
            }
        };
        complete.addActionListener(event);

        //cauta
        event = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                searchFrame.pack();
                searchFrame.setVisible(true);
            }
        };
        searchButton.addActionListener(event);
        searchItem.addActionListener(event);

        //searchFrame
        event = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                complete.setSelected(false);
                tModel.search(searchNamet.getText(),searchSurnamet.getText(),searchPINt.getText(),searchIBANt.getText());
                searchNamet.setText("");
                searchSurnamet.setText("");
                searchPINt.setText("");
                searchIBANt.setText("");
                searchFrame.setVisible(false);
            }
        };
        searchWindowButton.addActionListener(event);

        //newItem
        event = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tModel.newData();
                complete.setSelected(true);
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
                    if (f.getName().endsWith(".dba")) {
                        try {
                            if (!f.exists()) f.createNewFile();
                            tModel.open(f);
                        }
                        catch(Exception ex) {
                            JOptionPane.showMessageDialog(primaryFrame.getContentPane(),"Error opening file","Error",JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    else JOptionPane.showMessageDialog(primaryFrame.getContentPane(),"Wrong file format! Expected format: .dba","Error",JOptionPane.ERROR_MESSAGE);
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
                        if (f.getName().endsWith(".dba")) {
                            try {
                                if (!f.exists()) f.createNewFile();
                                tModel.save(f);
                            }
                            catch(Exception ex) {
                                JOptionPane.showMessageDialog(primaryFrame.getContentPane(),"Error saving file","Error",JOptionPane.ERROR_MESSAGE);
                            }
                        }
                        else JOptionPane.showMessageDialog(primaryFrame.getContentPane(),"Wrong file format! Expected format: .dba","Eroare",JOptionPane.ERROR_MESSAGE);
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