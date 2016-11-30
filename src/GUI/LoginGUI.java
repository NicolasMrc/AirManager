package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import Entities.MembreEquipage;
import Entities.TypeUtilisateur;
import Entities.Utilisateur;
import Services.UtilisateurService;
import com.mysql.jdbc.*;

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
    private TypeUtilisateur typeUtilisateur;


    private UtilisateurService utilisateurService;

    public LoginGUI(){
        this.utilisateurService = new UtilisateurService();

        addPanelMouseListener(this.adminPanel, TypeUtilisateur.ADMIN);
        addPanelMouseListener(this.managerPanel, TypeUtilisateur.MANAGER);
        addPanelMouseListener(this.membrePanel, TypeUtilisateur.MEMBRE_EQUIPAGE);

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
                passwordField.setText("");
                usernameField.setText("");
                typeUtilisateur = null;
            }
        });

        seConnecterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = String.valueOf(passwordField.getPassword());

                Utilisateur user = utilisateurService.connexion(username, password, typeUtilisateur);

                if (user != null){
                    if(user.getTypeUtilisateur().equals(TypeUtilisateur.ADMIN)){
                        AdminFrame adminFrame = new AdminFrame(user);
                    } else if (user.getTypeUtilisateur().equals(TypeUtilisateur.MANAGER)){
                        //ManagerFrame managerFrame = new ManagerFrame();
                    } else if (user.getTypeUtilisateur().equals(TypeUtilisateur.MEMBRE_EQUIPAGE)){
                        //MembreEquipageFrame membreEquipageFrame = new MembreEquipageFrame();
                    }
                    setVisible(false);
                    dispose();
                } else {
                    Panel panel = new Panel();
                    JOptionPane.showMessageDialog(panel, "Nom d'utilisateur ou mot de passe incorrect", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    public void addPanelMouseListener(JPanel panel, TypeUtilisateur type){

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
                typeUtilisateur = type;
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
