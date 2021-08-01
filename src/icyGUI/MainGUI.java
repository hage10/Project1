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
        JButton btnExportAll = new JButton("Export");
        btnExportAll.setBounds(10, 59, 90, 30);
        getContentPane().add(btnExportAll);

        JButton btnExportPart = new JButton("Find Id");
        btnExportPart.setBounds(109, 59, 90, 30);
        getContentPane().add(btnExportPart);

        JButton btnDelAll = new JButton("Del Table");
        btnDelAll.setBounds(664, 18, 90, 30);
        getContentPane().add(btnDelAll);

        JButton btnImport = new JButton("Import");
        btnImport.setBounds(664, 59, 90, 30);
        getContentPane().add(btnImport);

        //Tao textfield nhap du lieu
        JTextField textFwi = new JTextField();
        textFwi.setBounds(208, 59, 200, 30);
        getContentPane().add(textFwi);
        textFwi.setColumns(10);

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
                    data = controller.getAll();
                    ((DefaultTableModel)(table.getModel())).setDataVector(data, header);
                } catch(Exception e1) {
                    JOptionPane.showMessageDialog(scrollPane1, "Get data failure\nDetails: " + e1, "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });


        //Event nut Find Id
        btnExportPart.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String Id = textFwi.getText();
                    data = controller.getOne(Id);
                    ((DefaultTableModel)(table.getModel())).setDataVector(data, header);
                } catch(Exception e1) {
                    JOptionPane.showMessageDialog(scrollPane1, "Get data failure\nDetails: " + e1, "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        //Event nut DelAll
        btnDelAll.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                try {
                    controller.DelAll();
                    JOptionPane.showMessageDialog(MainGUI.this, "Delete table successful");
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
    }
}