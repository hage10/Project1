package icyGUI;

import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;

import database.Controller;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.sql.*;
public class ImportGUI extends JFrame {
    Controller controller = new Controller();

    private JTextField textFilePath;

    ImportGUI(){
        //
        //Khoi tao UI Import File

        //
        super("Import File");
        this.setSize(640, 180);
        getContentPane().setLayout(null);

        //Label notes chi dan
        JLabel lblImportNote = new JLabel("Notes:\r\nInput can be accepted only when it's in CSV file format");
        lblImportNote.setFont(new Font("Arial", Font.PLAIN, 13));
        lblImportNote.setBounds(25, 11, 410, 53);
        getContentPane().add(lblImportNote);

        //Button kich hoat
        JButton btnConfirm = new JButton("Confirm");
        btnConfirm.setBounds(25, 68, 90, 30);
        getContentPane().add(btnConfirm);

        //Textfield voi file path
        textFilePath = new JTextField();
        textFilePath.setBounds(125, 68, 438, 30);
        getContentPane().add(textFilePath);
        textFilePath.setColumns(10);

        //Button chon file
        JButton btnFileChooser = new JButton("...");
        btnFileChooser.setBounds(570, 68, 30, 30);
        getContentPane().add(btnFileChooser);

        //
        this.setVisible(true);
        this.setLocationRelativeTo(null);

        //Xu ly event

        //Event cho nut Confirm
        btnConfirm.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                try {
                    controller.Import(textFilePath.getText());
                    JOptionPane.showMessageDialog(ImportGUI.this, "Successful");
                    ImportGUI.this.dispose();
                } catch(Exception er) {
                    JOptionPane.showMessageDialog(ImportGUI.this, "Get input failed, please check again");
                }
            }
        });

        //Event cho nut ...
        btnFileChooser.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                JFileChooser FileChooser = new JFileChooser();
                FileChooser.setFileFilter(new FileNameExtensionFilter("CSV or Text","csv","txt"));
                FileChooser.setMultiSelectionEnabled(false);
                FileChooser.showDialog(ImportGUI.this, "Open");

                textFilePath.setText(FileChooser.getSelectedFile().getAbsolutePath());;
            }


        });
    }
}