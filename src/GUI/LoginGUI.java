package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import Enum.TypeUtilisateur;
import Entities.Utilisateur;
import Services.UtilisateurService;

/**
 * la classe gerant l'interface graphique du login
 * Created by Nico on 28/11/2016.
 */
public class LoginGUI extends JFrame{
    private JPanel mainPanel;
    private JPanel adminPanel;
    private JPanel managerPanel;
    private JPanel membrePanel;
    private JPanel rolePanel;
    private JPanel credentialPanel;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton seConnecterButton;
    private JButton retourButton;

    /**
     * le type d'utilisateur pour la connection
     */
    private TypeUtilisateur typeUtilisateur;

    /**
     * le service d'utilisateur
     */
    private UtilisateurService utilisateurService = new UtilisateurService();

    /**
     * defini les elements de la vue
     */
    public LoginGUI(){

        addPanelMouseListener(this.adminPanel, TypeUtilisateur.ADMIN);
        addPanelMouseListener(this.managerPanel, TypeUtilisateur.MANAGER);
        addPanelMouseListener(this.membrePanel, TypeUtilisateur.MEMBRE_EQUIPAGE);

        this.setSize(new Dimension(700, 500));
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        this.setTitle("Login");
        this.setContentPane(this.mainPanel);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);

        this.credentialPanel.setVisible(false);


        /**
         * ajoute un actionListener sur le bouton retour
         */
        retourButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rolePanel.setVisible(true);
                credentialPanel.setVisible(false);
                passwordField.setText("");
                usernameField.setText("");
                typeUtilisateur = null;
                setSize(new Dimension(700, 500));
            }
        });

        /**
         * ajout d'un actionListener sur le bouton Connexion
         */
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
                        ManagerGUI managerGUI = new ManagerGUI(user);
                    } else if (user.getTypeUtilisateur().equals(TypeUtilisateur.MEMBRE_EQUIPAGE)){
                        MembreEquipageGui membreEquipageFrame = new MembreEquipageGui(user);
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

    /**
     * ajout des mouseListener sur les panel de choix de type utilisateur pour la connexion
     * @param panel
     *      le panel sur lequel ajouter le mouseListener
     * @param type
     *      le type d'utilisateur
     */
    public void addPanelMouseListener(JPanel panel, TypeUtilisateur type){

        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                panel.setBackground(new Color(124, 50, 72));
                panel.repaint();
                super.mousePressed(e);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                rolePanel.setVisible(false);
                credentialPanel.setVisible(true);
                setSize(new Dimension(700, 250));
                typeUtilisateur = type;
                repaint();
                super.mouseReleased(e);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                panel.setBackground(new Color(124, 50, 72));
                panel.repaint();
                super.mouseEntered(e);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                panel.setBackground(new Color(108,43,62));
                panel.repaint();
                super.mouseExited(e);
            }
        });
    }

    /**
     * permet de lancer l'interface de connexion
     * @param args
     */
    public static void main(String[] args){
        new LoginGUI();
    }

}
