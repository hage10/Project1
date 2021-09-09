package icyGUI;

import database.Controller;
import database.DatabaseInfo;

import java.awt.*;
import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
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
        super("IcyTool");
        this.setSize(400, 500);
        getContentPane().setLayout(null);

        //Tao button
        JButton btnLogIn = new JButton("Sign in");
        btnLogIn.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 18));
        btnLogIn.setForeground(Color.DARK_GRAY);
        btnLogIn.setOpaque(true);
        btnLogIn.setBorderPainted(true);
        btnLogIn.setFocusable(false);
        getContentPane().add(btnLogIn);
        btnLogIn.setBounds(120, 360, 140, 30);

        JButton btnSetting = new JButton("Setting", new ImageIcon(System.getProperty("user.dir")+"\\rsc\\setting.png"));
        btnSetting.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 11));
        btnSetting.setBorderPainted(false);
        btnSetting.setMargin(new Insets(0,0,0,0));
        btnSetting.setContentAreaFilled(false);
        btnSetting.setBounds(40, 315, 80, 24);
        getContentPane().add(btnSetting);

        //Tao textfield
        textPass = new JPasswordField();
        textPass.setBounds(40, 272, 300, 30);
        getContentPane().add(textPass);
        textPass.setColumns(10);

        textUser = new JTextField();
        textUser.setColumns(10);
        textUser.setBounds(40, 202, 300, 30);
        getContentPane().add(textUser);

        //Tao label cho textfield
        JLabel lblLogin = new JLabel("Login");
        lblLogin.setHorizontalAlignment(SwingConstants.CENTER);
        lblLogin.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 40));
        lblLogin.setBounds(110, 60, 160, 50);
        lblLogin.setForeground(Color.WHITE);
        getContentPane().add(lblLogin);

        JLabel lblPass = new JLabel("Password:");
        lblPass.setFont(new Font("Arial Rounded MT Bold", Font.ITALIC, 15));
        lblPass.setBounds(40, 242, 93, 30);
        getContentPane().add(lblPass);

        JLabel lblUser = new JLabel("Username:");
        lblUser.setFont(new Font("Arial Rounded MT Bold", Font.ITALIC, 15));
        lblUser.setBounds(40, 172, 93, 30);
        getContentPane().add(lblUser);
        
        JLabel lblVersion = new JLabel("Ver. 1.0.1");
        lblVersion.setHorizontalAlignment(SwingConstants.CENTER);
        lblVersion.setBounds(320, 445, 65, 14);
        getContentPane().add(lblVersion);
        
        //Background
        JLabel lblThumbnail = new JLabel(new ImageIcon(Toolkit.getDefaultToolkit().getImage(System.getProperty("user.dir")+ "\\rsc\\Artboard 1.png")));
        lblThumbnail.setBounds(-16, 0, 400, 475);
        getContentPane().add(lblThumbnail);

        //Lay setting.txt
        try {
            DatabaseInfo.getSetting();
        } catch (IOException e1) {
            JOptionPane.showMessageDialog(LogInGUI.this, "Error: setting.txt not found");
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
            //Hieu ung
            public void mouseEntered(MouseEvent e) {
            	btnSetting.setBorderPainted(true);
            }
            public void mouseExited(MouseEvent e) {
            	btnSetting.setBorderPainted(false);
            }
        });

        //Event cho button LogIn
        btnLogIn.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                try {
                    DatabaseInfo.setAccount(textUser.getText(),textPass.getText());
                    controller.getConnect();
                    JOptionPane.showMessageDialog(LogInGUI.this, "Log in successful");
                    LogInGUI.this.dispose();
                    new MainGUI();
                } catch (Exception er) {
                    JOptionPane.showMessageDialog(LogInGUI.this, "Log in failed\nCheck your username, password and setting!!!");
                }
            }
        });

        //Event enter tai textPass -> dang nhap
        textPass.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    try {
                    	btnLogIn.doClick();
                    	DatabaseInfo.setAccount(textUser.getText(),textPass.getText());
                        controller.getConnect();
                        JOptionPane.showMessageDialog(LogInGUI.this, "Log in successful");
                        LogInGUI.this.dispose();
                        new MainGUI();
                    } catch (Exception er) {
                        JOptionPane.showMessageDialog(LogInGUI.this, "Log in failed\nCheck your username, password and setting!!!");
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
