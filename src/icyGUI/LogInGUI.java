package icyGUI;

import database.Controller;
import database.DatabaseInfo;

import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;

public class LogInGUI extends JFrame {

    Controller controller = new Controller();
    private JTextField textPass;
    private JTextField textUser;

    LogInGUI(){
        //
        //Khoi tao UI Log In

        //
        super("IcyTool");
        this.setSize(480, 640);
        getContentPane().setLayout(null);

        //Tao button LogIn
        JButton btnLogIn = new JButton("Log In");
        getContentPane().add(btnLogIn);
        btnLogIn.setBounds(180, 524, 111, 38);

        //Tao textfield
        textPass = new JTextField();
        textPass.setBounds(138, 472, 279, 30);
        getContentPane().add(textPass);
        textPass.setColumns(10);

        textUser = new JTextField();
        textUser.setColumns(10);
        textUser.setBounds(138, 420, 279, 30);
        getContentPane().add(textUser);

        //Tao label cho textfield
        JLabel lblPass = new JLabel("Password:");
        lblPass.setFont(new Font("Arial", Font.BOLD, 16));
        lblPass.setBounds(32, 472, 93, 30);
        getContentPane().add(lblPass);

        JLabel lblUser = new JLabel("User name:");
        lblUser.setFont(new Font("Arial", Font.BOLD, 16));
        lblUser.setBounds(32, 420, 93, 30);
        getContentPane().add(lblUser);

        //Tao label chua gif thumbnail
        JLabel lblThumbnail = new JLabel(new ImageIcon(Toolkit.getDefaultToolkit().getImage(System.getProperty("user.dir")+ "\\rsc\\Thumbnail.gif")));
        lblThumbnail.setBounds(32, 24, 400, 350);
        getContentPane().add(lblThumbnail);

        //
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(System.getProperty("user.dir")+"\\rsc\\icon.jpg"));
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        //Xu ly cac event

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

    public static void main(String[] args) {
        new LogInGUI();
    }
}