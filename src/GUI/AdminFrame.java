package GUI;

import Entities.MembreEquipage;
import Enum.TypeUtilisateur;
import Entities.Utilisateur;
import Services.MembreEquipageService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import Enum.TypeMembreEquipage;

/**
 * Created by Nico on 29/11/2016.
 */
public class AdminFrame extends JFrame{
    private JPanel mainPanel;
    private JPanel buttonPanel;
    private JPanel supprimerMembrePanel;
    private JPanel SupprimerVolPanel;
    private JPanel AjouterMembrePanel;
    private JPanel CreerVolPanel;
    private JPanel SupprimerTypeAvionPanel;
    private JPanel CreerAvionPanel;
    private JPanel QualifierMembrePanel;
    private JPanel CreerTypeAvionPanel;
    private JPanel SupprimerAvionPanel;
    private JPanel titrePanel;
    private JLabel titreLabel;
    private JTextField nomNouveauMembre;
    private JTextField prenomNouveauMembre;
    private JComboBox typeNouveauMembre;
    private JPanel ajoutMembreSection;
    private JButton ajouterMembreButton;
    private JPanel optionPanel;
    private JButton retourButton;
    private JButton deconnexionButton;

    private Utilisateur utilisateurCourrant;

    private MembreEquipageService membreEquipageService = new MembreEquipageService();

    public AdminFrame(Utilisateur utilisateur) throws HeadlessException {

        this.setSize(new Dimension(700, 800));

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        this.setTitle("Administration");
        this.setContentPane(this.mainPanel);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.deconnexionButton.setOpaque(true);
        this.deconnexionButton.setFocusPainted(false);
        this.deconnexionButton.setBorderPainted(false);
        this.retourButton.setOpaque(true);
        this.retourButton.setFocusPainted(false);
        this.retourButton.setBorderPainted(false);
        this.retourButton.setVisible(false);
        //this.setResizable(false);

        this.typeNouveauMembre.addItem("Pilote");
        this.typeNouveauMembre.addItem("Copilote");
        this.typeNouveauMembre.addItem("Personel non Commercial");

         addPanelMouseListener(supprimerMembrePanel, "supprimerMembre");
         addPanelMouseListener(SupprimerVolPanel, "supprimerVol");
         addPanelMouseListener(AjouterMembrePanel, "ajouterMembre");
         addPanelMouseListener(CreerVolPanel, "ajouterVol");
         addPanelMouseListener(SupprimerTypeAvionPanel, "supprimerTypeAvion");
         addPanelMouseListener(CreerAvionPanel, "ajouterAvion");
         addPanelMouseListener(QualifierMembrePanel, "qualifierMembre");
         addPanelMouseListener(CreerTypeAvionPanel, "ajouterTypeAvion");
         addPanelMouseListener(SupprimerAvionPanel, "supprimerAvion");


        this.ajoutMembreSection.setVisible(false);

        retourButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ajoutMembreSection.setVisible(false);
                buttonPanel.setVisible(true);
                setSize(new Dimension(700, 800));
                retourButton.setVisible(false);
            }
        });

        deconnexionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginGUI login = new LoginGUI();
                dispose();
            }
        });
        ajouterMembreButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String type = String.valueOf(typeNouveauMembre.getSelectedItem());
                MembreEquipage membreEquipage;
                if(type.equals("Pilote")){
                    membreEquipageService.addMembreEquipage(nomNouveauMembre.getText(), prenomNouveauMembre.getText(), TypeMembreEquipage.PILOTE);
                } else if (type.equals("Copilote")){
                    membreEquipageService.addMembreEquipage(nomNouveauMembre.getText(), prenomNouveauMembre.getText(), TypeMembreEquipage.COPILOTE);
                } else {
                    membreEquipageService.addMembreEquipage(nomNouveauMembre.getText(), prenomNouveauMembre.getText(), TypeMembreEquipage.PNC);
                }
            }
        });
    }

    public void addPanelMouseListener(JPanel panel, String action){

        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                panel.setBackground(new Color(83,33,47));
                panel.repaint();
                super.mousePressed(e);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                panel.setBackground(new Color(51, 71, 105));
                repaint();
                goToSection(action);
                super.mouseReleased(e);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                panel.setBackground(new Color(51, 71, 105));
                panel.repaint();
                super.mouseEntered(e);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                panel.setBackground(new Color(43, 60, 89));
                panel.repaint();
                super.mouseExited(e);
            }
        });
    }

    public void goToSection(String section){
        this.buttonPanel.setVisible(false);
        this.retourButton.setVisible(true);
        switch (section){
            case "supprimerMembre" :
                this.titreLabel.setText("Supprimer un membre d'équipage");
                break;
            case "supprimerVol" :
                this.titreLabel.setText("Supprimer un vol");
                break;
            case "ajouterMembre" :
                this.titreLabel.setText("Ajouter un membre d'équipage");
                this.ajoutMembreSection.setVisible(true);
                this.setPreferredSize(new Dimension(700, 400));
                this.pack();
                break;
            case "ajouterVol" :
                this.titreLabel.setText("Ajouter un vol");
                break;
            case "supprimerTypeAvion" :
                this.titreLabel.setText("Supprimer un type d'avion");
                break;
            case "ajouterAvion" :
                this.titreLabel.setText("Ajouter un avion");
                break;
            case "qualifierMembre" :
                this.titreLabel.setText("Qualifier un membre d'équipage");
                break;
            case "ajouterTypeAvion" :
                this.titreLabel.setText("Ajouter un type d'avion");
                break;
            case "supprimerAvion" :
                this.titreLabel.setText("Supprimer un avion");
                break;
        }
    }

    //TODO Supprimer une fois que tout marchera
    public static void main(String[] args){
        Utilisateur user = new Utilisateur(1L, "admin", "admin", TypeUtilisateur.ADMIN);
        AdminFrame adminFrame = new AdminFrame(user);
    }
}
