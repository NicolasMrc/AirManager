package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by Nico on 28/11/2016.
 */
public class LoginGUI extends JFrame{
    private JPanel mainPanel;
    private JLabel adminLabel;
    private JLabel managerLabel;
    private JLabel membreLabel;
    private JPanel adminPanel;
    private JPanel managerPanel;
    private JPanel membrePanel;
    private JPanel rolePanel;
    private JPanel credentialPanel;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton seConnecterButton;
    private JButton retourButton;
    private String roleUtilisateur;

    public LoginGUI(){

        addPanelMouseListener(this.adminPanel, "admin");
        addPanelMouseListener(this.managerPanel, "manager");
        addPanelMouseListener(this.membrePanel, "membre");



        this.setSize(new Dimension(510, 260));
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        this.setTitle("Login");
        this.setContentPane(this.mainPanel);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);

        this.credentialPanel.setVisible(false);


        retourButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rolePanel.setVisible(true);
                credentialPanel.setVisible(false);
                roleUtilisateur = "";
            }
        });

        seConnecterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = String.valueOf(passwordField.getPassword());
            }
        });
    }

    public void addPanelMouseListener(JPanel panel, String role){

        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                panel.setBackground(new Color(83,33,47));
                panel.repaint();
                super.mousePressed(e);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                rolePanel.setVisible(false);
                credentialPanel.setVisible(true);
                roleUtilisateur = role;
                repaint();
                super.mouseReleased(e);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                panel.setBackground(new Color(83,33,47));
                panel.repaint();
                super.mouseEntered(e);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                panel.setBackground(new Color(70,28,40));
                panel.repaint();
                super.mouseExited(e);
            }
        });
    }

    public static void main(String[] args){
        new LoginGUI();
    }

}
