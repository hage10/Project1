package icyGUI;

import database.Controller;

import javax.swing.*;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
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
        JButton btnExportAll = new JButton("Export Data", new ImageIcon(System.getProperty("user.dir")+"\\rsc\\export.png"));
        btnExportAll.setBorderPainted(false);
        btnExportAll.setContentAreaFilled(false);
        btnExportAll.setOpaque(false);
        btnExportAll.setFocusable(false);
        btnExportAll.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 14));
        btnExportAll.setBounds(10, 7, 150, 40);
        getContentPane().add(btnExportAll);

        JButton btnImport = new JButton("Import File", new ImageIcon(System.getProperty("user.dir")+"\\rsc\\import.png"));
        btnImport.setBorderPainted(false);
        btnImport.setContentAreaFilled(false);
        btnImport.setOpaque(false);
        btnImport.setFocusable(false);
        btnImport.setBounds(170, 7, 150, 40);
        btnImport.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 14));
        getContentPane().add(btnImport);
        
        ImageIcon iconDel = new ImageIcon(System.getProperty("user.dir")+"\\rsc\\delete.png");
        JButton btnDelAll = new JButton("Delete data",iconDel);
        btnDelAll.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 14));
        btnDelAll.setBorderPainted(false);
        btnDelAll.setContentAreaFilled(false);
        btnDelAll.setOpaque(false);
        btnDelAll.setFocusable(false);
        btnDelAll.setBounds(330, 7, 150, 40);
        getContentPane().add(btnDelAll);

        JButton btnFind= new JButton(new ImageIcon(System.getProperty("user.dir")+"\\rsc\\loupe2.png"));
        btnFind.setBorderPainted(false);
        btnFind.setContentAreaFilled(false);
        btnFind.setOpaque(false);
        btnFind.setFocusable(false);
        btnFind.setBounds(265, 59, 30, 30);
        getContentPane().add(btnFind);

        JButton btnNote = new JButton("Note", new ImageIcon(System.getProperty("user.dir")+"\\rsc\\note.png"));
        btnNote.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 14));
        btnNote.setBorderPainted(false);
        btnNote.setContentAreaFilled(false);
        btnNote.setOpaque(false);
        btnNote.setFocusable(false);
        btnNote.setBounds(665, 7, 110, 40);
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
        textField3.setBounds(690, 59, 72, 30);
        getContentPane().add(textField3);

        //Tao comboBox
        String s1[] = {"Year or ID", "Year range", "Year and ID"};
        JComboBox comboBox = new JComboBox(s1);
        comboBox.setBounds(105, 59, 150, 30);
        comboBox.setFocusable(false);
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
        lblField3.setBounds(640, 59, 40, 30);
        getContentPane().add(lblField3);
        
        //Background
        JLabel lblBackGround = new JLabel(new ImageIcon(Toolkit.getDefaultToolkit().getImage(System.getProperty("user.dir")+ "\\rsc\\bg8.png")));
        lblBackGround.setBounds(0, 0, 800, 600);
        getContentPane().add(lblBackGround);

        //Tao scrollPane2
        JScrollPane scrollPane2 = new JScrollPane();
        scrollPane2.setBounds(10, 411, 764, 139);
        lblBackGround.add(scrollPane2);

        //Set viewport textArea cho scrollPane2
        JTextArea textArea = new JTextArea();
        scrollPane2.setViewportView(textArea);
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setText("Welcome to IcyTool\nChoose a button above to start...");

        //
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(System.getProperty("user.dir")+"\\rsc\\icon.jpg"));
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);



        //Xu ly cac event
        
        //Event tao hieu ung cho cac button
        btnExportAll.addMouseListener(new MouseAdapter() {
        	public void mouseEntered(MouseEvent e) {
        		btnExportAll.setBorderPainted(true);
        	}
        	public void mouseExited(MouseEvent e) {
        		btnExportAll.setBorderPainted(false);
        	}
        });
        
        btnImport.addMouseListener(new MouseAdapter() {
        	public void mouseEntered(MouseEvent e) {
        		btnImport.setBorderPainted(true);
        	}
        	public void mouseExited(MouseEvent e) {
        		btnImport.setBorderPainted(false);
        	}
        });
        
        btnDelAll.addMouseListener(new MouseAdapter() {
        	public void mouseEntered(MouseEvent e) {
        		btnDelAll.setBorderPainted(true);
        	}
        	public void mouseExited(MouseEvent e) {
        		btnDelAll.setBorderPainted(false);
        	}
        });
        
        btnFind.addMouseListener(new MouseAdapter() {
        	public void mouseEntered(MouseEvent e) {
        		btnFind.setBorderPainted(true);
        	}
        	public void mouseExited(MouseEvent e) {
        		btnFind.setBorderPainted(false);
        	}
        });
        
        btnNote.addMouseListener(new MouseAdapter() {
        	public void mouseEntered(MouseEvent e) {
        		btnNote.setBorderPainted(true);
        	}
        	public void mouseExited(MouseEvent e) {
        		btnNote.setBorderPainted(false);
        	}
        });
        
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
                        data = controller.getByYearRange(textField1.getText(), textField2.getText());
                        ((DefaultTableModel)(table.getModel())).setDataVector(data, header);
                        textArea.setText("Successful!!");
                    } catch(Exception e1) {
                        JOptionPane.showMessageDialog(scrollPane1, "Get data failure\nDetails: " + e1, "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
                else if (comboBox.getSelectedItem() == "Year and ID") {
                    try {
                        textArea.setText("Loading...");
                        data = controller.getByIdYearRange(textField1.getText(), textField2.getText(),textField3.getText());
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
                    controller.delAll();
                    textArea.setText("Deleted all data from table, query executed successful!!");
                } catch(Exception er) {
                    JOptionPane.showMessageDialog(MainGUI.this, "Delete failed, please check your database");
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
                JOptionPane.showMessageDialog(MainGUI.this, "Instruction:"
                		+ "\n - Year or ID: full length ID or year"
                		+ "\n E.g. \"CVE-2000\" or \"CVE-2008-0018\""
                		+ "\n - Year range: type only year"
                		+ "\n E.g. \"2001\" to \"2004\""
                		+ "\n Year and ID: type full ID NUMBER"
                		+ "\n E.g. CVE-1999-0015 have ID number is \"0015\"");
            }
        });

    }
}