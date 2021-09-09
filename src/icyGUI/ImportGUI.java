package icyGUI;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import database.Controller;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ImportGUI extends JFrame {
    Controller controller = new Controller();

    ImportGUI(){
        //
        //Khoi tao UI Import File

        //
        super("Import File");
        this.setSize(640, 180);
        getContentPane().setLayout(null);

        //Label notes chi dan
        JLabel lblImportNote = new JLabel("Notes:\r\n Input can only be accepted when it's .csv or .txt file with CSV format");
        lblImportNote.setFont(new Font("Arial", Font.BOLD, 13));
        lblImportNote.setBounds(25, 7, 585, 53);
        getContentPane().add(lblImportNote);

        //Button kich hoat
        JButton btnConfirm = new JButton("OK", new ImageIcon(System.getProperty("user.dir") + "\\rsc\\tick.png"));
        btnConfirm.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnConfirm.setBounds(25, 95, 75, 30);
        btnConfirm.setMargin(new Insets(0,0,0,0));
        btnConfirm.setBorderPainted(false);
        btnConfirm.setContentAreaFilled(false);
        btnConfirm.setOpaque(false);
        btnConfirm.setFocusable(false);
        getContentPane().add(btnConfirm);

        //Textfield voi file path
        JTextField textFilePath = new JTextField();
        textFilePath.setBounds(25, 55, 460, 32);
        getContentPane().add(textFilePath);
        textFilePath.setColumns(10);

        //Button chon file
        JButton btnBrowse = new JButton("Browse", new ImageIcon(System.getProperty("user.dir")+"\\rsc\\browser1.png"));
        btnBrowse.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnBrowse.setMargin(new Insets(0,0,0,0));
        btnBrowse.setBounds(500, 55, 110, 32);
        btnBrowse.setBorderPainted(false);
        btnBrowse.setContentAreaFilled(false);
        btnBrowse.setOpaque(false);
        btnBrowse.setFocusable(false);
        getContentPane().add(btnBrowse);
        
        //Background
        JLabel lblBackGround = new JLabel(new ImageIcon(Toolkit.getDefaultToolkit().getImage(System.getProperty("user.dir")+ "\\rsc\\setting-background.png")));
        lblBackGround.setBounds(0, 0, 625, 140);
        getContentPane().add(lblBackGround);
        
        //
        this.setVisible(true);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);

        //Xu ly event
        
        //Event hieu ung button
        btnConfirm.addMouseListener(new MouseAdapter() {
        	public void mouseEntered(MouseEvent e) {
        		btnConfirm.setBorderPainted(true);
        	}
        	public void mouseExited(MouseEvent e) {
        		btnConfirm.setBorderPainted(false);
        	}
        });
        
        btnBrowse.addMouseListener(new MouseAdapter() {
        	public void mouseEntered(MouseEvent e) {
        		btnBrowse.setBorderPainted(true);
        	}
        	public void mouseExited(MouseEvent e) {
        		btnBrowse.setBorderPainted(false);
        	}
        });

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

        //Event cho nut Browse
        btnBrowse.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                JFileChooser FileChooser = new JFileChooser();
                FileChooser.setFileFilter(new FileNameExtensionFilter("CSV or Text","csv","txt"));
                FileChooser.setMultiSelectionEnabled(false);
                FileChooser.showDialog(ImportGUI.this, "Open");

                if (FileChooser.getSelectedFile() != null) textFilePath.setText(FileChooser.getSelectedFile().getAbsolutePath());;
            }


        });
    }
}