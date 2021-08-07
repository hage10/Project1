package icyGUI;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.SwingConstants;

import database.DatabaseInfo;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class SettingGUI extends JFrame {

    SettingGUI() {
        //
        //Khoi tao khung setting

        //
        super("Setting");
        this.setSize(240, 320);
        getContentPane().setLayout(null);

        this.setIconImage(Toolkit.getDefaultToolkit().getImage(System.getProperty("user.dir")+"\\rsc\\icon.jpg"));
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);

        //textField
        JTextField textURL = new JTextField();
        textURL.setBounds(62, 108, 140, 30);
        getContentPane().add(textURL);
        textURL.setColumns(10);
        textURL.setText(DatabaseInfo.dbURL);

        JTextField textTable = new JTextField();
        textTable.setColumns(10);
        textTable.setBounds(62, 163, 140, 30);
        getContentPane().add(textTable);
        textTable.setText(DatabaseInfo.dbTable);

        //Tao button
        JButton btnApply = new JButton("Apply");
        btnApply.setBounds(15, 218, 89, 30);
        getContentPane().add(btnApply);

        JButton btnClose = new JButton("Close");
        btnClose.setBounds(120, 218, 89, 30);
        getContentPane().add(btnClose);

        //Tao label
        JLabel lblURL = new JLabel("URL:");
        lblURL.setFont(new Font("Arial", Font.PLAIN, 15));
        lblURL.setBounds(15, 108, 48, 30);
        getContentPane().add(lblURL);

        JLabel lblTable = new JLabel("Table:");
        lblTable.setFont(new Font("Arial", Font.PLAIN, 15));
        lblTable.setBounds(15, 163, 48, 30);
        getContentPane().add(lblTable);

        JLabel lblLabel = new JLabel("IcyTool setting");
        lblLabel.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 20));
        lblLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblLabel.setBounds(31, 25, 157, 46);
        getContentPane().add(lblLabel);

        //Event btn Apply
        btnApply.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                try {
                    DatabaseInfo.dbURL = textURL.getText();
                    DatabaseInfo.dbTable = textTable.getText();
                    DatabaseInfo.setSetting();
                    JOptionPane.showMessageDialog(SettingGUI.this, "Apply successful");
                    SettingGUI.this.dispose();
                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(SettingGUI.this, "Apply failed");
                }
            }
        });

        //Event btn Close
        btnClose.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                SettingGUI.this.dispose();
            }
        });
    }
}
