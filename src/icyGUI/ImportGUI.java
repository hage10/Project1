package icyGUI;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import database.Controller;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
        JLabel lblImportNote = new JLabel("Notes:\r\n Input can be accepted only when it's in CSV file format");
        lblImportNote.setFont(new Font("Arial", Font.PLAIN, 13));
        lblImportNote.setBounds(25, 7, 410, 53);
        getContentPane().add(lblImportNote);

        //Button kich hoat
        JButton btnConfirm = new JButton("OK");
        btnConfirm.setBounds(25, 95, 90, 30);
        getContentPane().add(btnConfirm);

        //Textfield voi file path
        textFilePath = new JTextField();
        textFilePath.setBounds(25, 55, 460, 32);
        getContentPane().add(textFilePath);
        textFilePath.setColumns(10);
//        textFilePath.setBorder(null);

        //Button chon file
        ImageIcon iconImport = new ImageIcon(System.getProperty("user.dir")+"\\rsc\\browser1.png");
        JButton btnFileChooser = new JButton("Browser",iconImport);
        btnFileChooser.setBounds(500, 55, 110, 32);
        getContentPane().add(btnFileChooser);

        //
        this.setVisible(true);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
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