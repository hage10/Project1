package icyGUI;

import database.Controller;
import database.DatabaseInfo;

import java.awt.*;
import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class LogInGUI extends JFrame {

    Controller controller = new Controller();
    private JTextField textPass;
    private JTextField textUser;

    LogInGUI(){
        //
        //Khoi tao UI Log In

        //
        super("Login");
        this.setSize(400, 500);
        getContentPane().setLayout(null);

        //Tao button
        JButton btnLogIn = new JButton("Sign in");
        btnLogIn.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 18));
        btnLogIn.setForeground(Color.DARK_GRAY);
        btnLogIn.setOpaque(false);
        btnLogIn.setBorderPainted(false);
        getContentPane().add(btnLogIn);
        btnLogIn.setBounds(115, 324, 150, 30);

        ImageIcon settingIcon = new ImageIcon(System.getProperty("user.dir")+"\\rsc\\setting.png");
        JButton btnSetting = new JButton("Settings",settingIcon);
        btnSetting.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 11));
        btnSetting.setBorder(null);
        btnSetting.setBorderPainted(false);
        btnSetting.setContentAreaFilled(false);
        btnSetting.setBounds(40, 293, 70, 24);
        getContentPane().add(btnSetting);



        //Tao textfield
        textPass = new JPasswordField();
        textPass.setBounds(40, 250, 300, 30);
        getContentPane().add(textPass);
        textPass.setColumns(10);

        textUser = new JTextField();
        textUser.setColumns(10);
        textUser.setBounds(40, 180, 300, 30);
        getContentPane().add(textUser);

        //Tao label cho textfield
        JLabel lblLogin = new JLabel("Login");
        lblLogin.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 40));
        lblLogin.setBounds(140, 60, 150, 50);
        lblLogin.setForeground(Color.WHITE);
        getContentPane().add(lblLogin);

        JLabel lblPass = new JLabel("Password:");
        lblPass.setFont(new Font("Arial Rounded MT Bold", Font.ITALIC, 15));
        lblPass.setBounds(40, 220, 93, 30);
        getContentPane().add(lblPass);

        JLabel lblUser = new JLabel("Username:");
        lblUser.setFont(new Font("Arial Rounded MT Bold", Font.ITALIC, 15));
        lblUser.setBounds(40, 150, 93, 30);
        getContentPane().add(lblUser);

        //Tao label chua gif thumbnail
        JLabel lblThumbnail = new JLabel(new ImageIcon(Toolkit.getDefaultToolkit().getImage(System.getProperty("user.dir")+ "\\rsc\\Artboard 1.png")));
        lblThumbnail.setBounds(0, 0, 400, 600);
        getContentPane().add(lblThumbnail);

        //Lay setting.txt
        try {
            DatabaseInfo.getSetting();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        //
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(System.getProperty("user.dir")+"\\rsc\\icon.jpg"));
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        //Xu ly cac event

        //Event cho button Setting
        btnSetting.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                new SettingGUI();
            }
        });

        //Event cho button LogIn
        btnLogIn.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                try {
                    DatabaseInfo.dbUser = textUser.getText();
                    DatabaseInfo.dbPass = textPass.getText();
                    Connection connection = controller.getConnect();
                    JOptionPane.showMessageDialog(LogInGUI.this, "Log in successful");
                    LogInGUI.this.setVisible(false);
                    new MainGUI();
                } catch (Exception er) {
                    JOptionPane.showMessageDialog(LogInGUI.this, "Log in failed, wrong username or password");
                }
            }
        });

        //Event enter tai textPass -> dang nhap
        textPass.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    try {
                        DatabaseInfo.dbUser = textUser.getText();
                        DatabaseInfo.dbPass = textPass.getText();
                        Connection connection = controller.getConnect();
                        JOptionPane.showMessageDialog(LogInGUI.this, "Log in successful");
                        LogInGUI.this.setVisible(false);
                        new MainGUI();
                    } catch (Exception er) {
                        JOptionPane.showMessageDialog(LogInGUI.this, "Log in failed, wrong username or password");
                    }
                }
            }
        });

        //Event enter tai textUser -> focus toi textPass
        textUser.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    textPass.requestFocus();
                }
            }
        });

    }

    public static void main(String[] args) throws FileNotFoundException {
        new LogInGUI();
    }
}
