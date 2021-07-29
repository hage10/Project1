package icyGUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import database.Controller;

import java.awt.event.ActionListener;
import java.util.Vector;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JTextField;

public class GUI extends JFrame {

    Controller abc = new Controller();
    private JTable table;
    boolean check = false;
    private JTextField textFwi;
    Controller controller = new Controller();


    GUI() {
        super("BKDATA");
        this.setSize(800, 600);
        getContentPane().setLayout(null);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setToolTipText("");
        scrollPane.setBounds(10, 100, 764, 450);

        table = new JTable();
        scrollPane.setViewportView(table);

        ImageIcon background = new ImageIcon("D:\\bg.jpg");
        Image img = background.getImage();
        Image temp = img.getScaledInstance(800, 600, Image.SCALE_SMOOTH);
        background = new ImageIcon(temp);
        JLabel back = new JLabel(background);
        back.setLayout(null);
        back.setBounds(0, 0, 800, 600);
        getContentPane().add(back);
        JButton btnImportOne = new JButton("Find Id");
        btnImportOne.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String Id = textFwi.getText();
                    Vector<Vector<String>> data = controller.getOne(Id);
                    Vector<String> header = new Vector<String>();
                    header.add("Id");
                    header.add("Sta");
                    header.add("Des");
                    header.add("Ref");
                    header.add("Phase");
                    header.add("Votes");
                    header.add("Comments");
                    ((DefaultTableModel) (table.getModel())).setDataVector(data, header);
                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(scrollPane, "Get data failure\nDetails: " + e1, "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        btnImportOne.setBounds(109, 11, 89, 23);
        getContentPane().add(btnImportOne);
        textFwi = new JTextField();
        textFwi.setBounds(208, 11, 153, 23);
        getContentPane().add(textFwi);
        textFwi.setColumns(10);

        ImageIcon iconn = new ImageIcon("D:\\import.png");
        int width = iconn.getIconWidth();
        int height = iconn.getIconHeight();
        Image scaled = scaleImage(iconn.getImage(), width, height);
        ImageIcon scaledIcon = new ImageIcon(scaled);
        JButton btnNewButton = new JButton("Import", scaledIcon);

        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                if (!check) {
                    getContentPane().add(scrollPane);
                }
                check = true;
                try {
                    Vector<Vector<String>> data = abc.getAll();
                    Vector<String> header = new Vector<String>();
                    header.add("Id");
                    header.add("Sta");
                    header.add("Des");
                    header.add("Ref");
                    header.add("Phase");
                    header.add("Votes");
                    header.add("Comments");
                    ((DefaultTableModel) (table.getModel())).setDataVector(data, header);
                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(scrollPane, "Add new failure\nDetails: " + e1, "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        btnNewButton.setBounds(10, 11, 95, 25);
        getContentPane().add(btnNewButton);
        Image icon = Toolkit.getDefaultToolkit().getImage("D:\\icon.jpg");
        this.setIconImage(icon);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
    }


    private Image scaleImage(Image image, int w, int h) {
        Image scaled = image.getScaledInstance(w, h, Image.SCALE_SMOOTH);
        return scaled;
    }

    public static void main(String[] args) {
        new GUI();
    }
}
