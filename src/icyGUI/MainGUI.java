package icyGUI;

import database.Controller;

import javax.swing.*;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Objects;
import java.util.Vector;
import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextArea;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class MainGUI extends JFrame {

    Controller controller = new Controller();
    private Vector<String> header = controller.getHeader();
    private Vector<Vector<String>> data;

    public MainGUI(){
        //
        //Khoi tao UI Main

        //
        super("IcyTool");
        this.setSize(800, 600);
        getContentPane().setLayout(null);

        //Tao scrollPane1
        JScrollPane scrollPane1 = new JScrollPane();
        scrollPane1.setToolTipText("");
        scrollPane1.setBounds(10, 100, 764, 300);
        getContentPane().add(scrollPane1);

        //Set viewport table cho scrollPane1
        JTable table = new JTable();
        scrollPane1.setViewportView(table);

        //Tao button
        ImageIcon iconExport = new ImageIcon(System.getProperty("user.dir")+"\\rsc\\export.png");
        JButton btnExportAll = new JButton("Export Data",iconExport);
        btnExportAll.setBorder(null);
        btnExportAll.setBorderPainted(false);
        btnExportAll.setContentAreaFilled(false);
        btnExportAll.setOpaque(false);
        btnExportAll.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 14));
        btnExportAll.setBounds(10, 10, 120, 32);
        getContentPane().add(btnExportAll);

        ImageIcon iconImport = new ImageIcon(System.getProperty("user.dir")+"\\rsc\\import.png");
        JButton btnImport = new JButton("Import File",iconImport);
         btnImport.setBorder(null);
        btnImport.setBorderPainted(false);
        btnImport.setContentAreaFilled(false);
        btnImport.setOpaque(false);
        btnImport.setBounds(150, 10, 150, 32);
        btnImport.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 14));
        getContentPane().add(btnImport);


        ImageIcon iconFind  = new ImageIcon(System.getProperty("user.dir")+"\\rsc\\loupe2.png");
        JButton btnFind= new JButton(iconFind);
        btnFind.setBorder(null);
        btnFind.setBorderPainted(false);
        btnFind.setContentAreaFilled(false);
        btnFind.setOpaque(false);
        btnFind.setBounds(270, 59, 32, 32);
        getContentPane().add(btnFind);


        ImageIcon iconDel = new ImageIcon(System.getProperty("user.dir")+"\\rsc\\delete.png");
        JButton btnDelAll = new JButton("Delete data",iconDel);
        btnDelAll.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 14));
        btnDelAll.setBorder(null);
        btnDelAll.setBorderPainted(false);
        btnDelAll.setContentAreaFilled(false);
        btnDelAll.setOpaque(false);
        btnDelAll.setBounds(300, 10, 150, 32);
        getContentPane().add(btnDelAll);


        ImageIcon iconNote = new ImageIcon(System.getProperty("user.dir")+"\\rsc\\note.png");
        JButton btnNote = new JButton("Note",iconNote);
        btnNote.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 14));
        btnNote.setBorder(null);
        btnNote.setBorderPainted(false);
        btnNote.setContentAreaFilled(false);
        btnNote.setOpaque(false);
        btnNote.setBounds(665, 10, 100, 32);
        getContentPane().add(btnNote);

        //Tao textfield nhap du lieu
        JTextField textField1 = new JTextField();
        textField1.setBounds(305, 59, 140, 30);
        textField1.setColumns(10);
        getContentPane().add(textField1);


        JTextField textField2 = new JTextField();
        textField2.setEnabled(false);
        textField2.setColumns(10);
        textField2.setBounds(490, 59, 140, 30);
        getContentPane().add(textField2);

        JTextField textField3 = new JTextField();
        textField3.setVisible(false);
        textField3.setColumns(10);
        textField3.setBounds(700, 59, 72, 30);
        getContentPane().add(textField3);

        //Tao comboBox
        String s1[] = {"Year or ID", "Year range", "Year and ID"};
        JComboBox comboBox = new JComboBox(s1);
        comboBox.setBounds(105, 59, 150, 30);
        getContentPane().add(comboBox);

        //Label
        JLabel lblField2 = new JLabel("To:");
        lblField2.setEnabled(false);
        lblField2.setFont(new Font("Arial Rounded MT Bold", Font.ITALIC, 13));
        lblField2.setBounds(455, 59, 30, 30);
        getContentPane().add(lblField2);

        JLabel lblCombo = new JLabel("Filter option:");
        lblCombo.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 15));
        lblCombo.setBounds(10, 59, 96, 30);
        getContentPane().add(lblCombo);

        JLabel lblField3 = new JLabel("Year:");
        lblField3.setFont(new Font("Arial Rounded MT Bold", Font.ITALIC, 13));
        lblField3.setVisible(false);
        lblField3.setBounds(660, 59, 40, 30);
        getContentPane().add(lblField3);



        JLabel backGround = new JLabel(new ImageIcon(Toolkit.getDefaultToolkit().getImage(System.getProperty("user.dir")+ "\\rsc\\bg8.png")));
        backGround.setBounds(0, 0, 800, 600);
        getContentPane().add(backGround);

        //Tao scrollPane2
        JScrollPane scrollPane2 = new JScrollPane();
        scrollPane2.setBounds(10, 411, 764, 139);
        getContentPane().add(scrollPane2);

        //Set viewport textArea cho scrollPane2
        JTextArea textArea = new JTextArea();
        scrollPane2.setViewportView(textArea);
        textArea.setEditable(false);
        textArea.setLineWrap(true);


        //
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(System.getProperty("user.dir")+"\\rsc\\icon.jpg"));
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);



        //Xu ly cac event

        //Event nut Export
        btnExportAll.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                try {
                    textArea.setText("Loading...");
                    data = controller.getAll();
                    ((DefaultTableModel)(table.getModel())).setDataVector(data, header);
                    textArea.setText("Get all data successful!!");
                } catch(Exception e1) {
                    JOptionPane.showMessageDialog(scrollPane1, "Get data failure\nDetails: " + e1, "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        //Event combobox
        comboBox.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (comboBox.getSelectedItem() == "Year or ID") {
                    lblField3.setVisible(false);
                    textField3.setVisible(false);
                    lblField2.setEnabled(false);
                    textField2.setText("");
                    textField2.setEnabled(false);
                    textField3.setText("");
                }
                else if (comboBox.getSelectedItem() == "Year range"){
                    lblField3.setVisible(false);
                    textField3.setVisible(false);
                    lblField2.setEnabled(true);
                    textField2.setEnabled(true);
                    textField3.setText("");
                }
                else if (comboBox.getSelectedItem() == "Year and ID"){
                    lblField3.setVisible(true);
                    textField3.setVisible(true);
                    lblField2.setEnabled(true);
                    textField2.setEnabled(true);
                }

            }
        });

        //Event nut Find
        btnFind.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (comboBox.getSelectedItem() == "Year or ID") {
                    try {
                        textArea.setText("Loading...");
                        data = controller.getOne(textField1.getText());
                        ((DefaultTableModel)(table.getModel())).setDataVector(data, header);
                        textArea.setText("Successful!!");
                    } catch(Exception e1) {
                        JOptionPane.showMessageDialog(scrollPane1, "Get data failure\nDetails: " + e1, "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
                else if (comboBox.getSelectedItem() == "Year range") {
                    try {
                        textArea.setText("Loading...");
                        data = controller.getYearRange(textField1.getText(), textField2.getText());
                        ((DefaultTableModel)(table.getModel())).setDataVector(data, header);
                        textArea.setText("Successful!!");
                    } catch(Exception e1) {
                        JOptionPane.showMessageDialog(scrollPane1, "Get data failure\nDetails: " + e1, "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
                else if (comboBox.getSelectedItem() == "Year and ID") {
                    try {
                        textArea.setText("Loading...");
                        data = controller.getRangeByIdYear(textField1.getText(), textField2.getText(),textField3.getText());
                        ((DefaultTableModel)(table.getModel())).setDataVector(data, header);
                        textArea.setText("Successful!!");
                    } catch(Exception e1) {
                        JOptionPane.showMessageDialog(scrollPane1, "Get data failure\nDetails: " + e1, "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        //Event nut DelAll
        btnDelAll.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                try {
                    textArea.setText("Loading...");
                    controller.DelAll();
                    textArea.setText("Deleted all data from table, query executed successful!!");
                } catch(Exception er) {
                    JOptionPane.showMessageDialog(MainGUI.this, "Delete failed, please check your database with a CVEList table");
                }
            }
        });

        //Event nut Import
        btnImport.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                new ImportGUI();
            }
        });

        //Event khi click vao mot row trong table
        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int selectedRow = table.getSelectedRow();
                textArea.setText(controller.displaySelectedRow(data, selectedRow));
                textArea.setCaretPosition(0);
            }
        });

        //Event nut ?
        btnNote.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                JOptionPane.showMessageDialog(MainGUI.this, "Note:\n - Input ID must be typed in full length\n E.g. CVE-1999-0015 have ID number is \"0015\"");
            }
        });

    }

    private Image scaleImage(Image image, int widthIconIm, int heightIconIm) {
        return  image;
    }
}