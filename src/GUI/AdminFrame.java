package GUI;

import Entities.MembreEquipage;
import Entities.TypeAvion;
import Enum.TypeUtilisateur;
import Entities.Utilisateur;
import Exceptions.EmptyFieldException;
import Services.AvionService;
import Services.MembreEquipageService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import Enum.TypeMembreEquipage;
import Services.TypeAvionService;

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
    private JPanel suppressionMembreSection;
    private JTable supprimerMembreTable;
    private JScrollPane supprimerMembreScrollPanel;
    private JButton supprimerMembreButton;
    private JPanel ajoutTypeAvionSection;
    private JTextField nomTypeAvion;
    private JTextField nbPNCMin;
    private JTextField nbPNCMax;
    private JButton ajouterTypeAvionButton;
    private JPanel ajoutAvionSection;
    private JTextField referenceAvion;
    private JButton ajouterAvionButton;
    private JComboBox typeAvionCombo;

    /**
     * L'utilisateur courrant
     */
    private Utilisateur utilisateurCourrant;

    /**
     * Service de la classe MembreEquipage
     */
    private MembreEquipageService membreEquipageService = new MembreEquipageService();

    /**
     * Service de la classe avion
     */
    private AvionService avionService = new AvionService();

    /**
     * Service de la class typeAvion
     */
    private TypeAvionService typeAvionService = new TypeAvionService();

    /**
     * constructeur du tableau d'administration
     * @param utilisateur
     *      l'utilisateur courant
     * @throws HeadlessException
     */
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

        //TODO utiliser la classe custom comboItem !
        this.typeNouveauMembre.addItem("Pilote");
        this.typeNouveauMembre.addItem("Copilote");
        this.typeNouveauMembre.addItem("Personnel non Commercial");

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
        this.suppressionMembreSection.setVisible(false);
        this.ajoutAvionSection.setVisible(false);
        this.ajoutTypeAvionSection.setVisible(false);

        this.addButtonActionListener();
    }

    /**
     * permet d'ajouter a un panel un mouse listener pour reconnaitre les interraction avec l'utilisateur
     * @param panel
     *      le panel pour lequel il faut ajouter un mouseListener
     * @param action
     *      l'action a ajouter au click sur le panel
     */
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

    /**
     * permet la navigation entre les section
     * @param section
     *      la section a afficher
     */
    public void goToSection(String section){
        this.buttonPanel.setVisible(false);
        this.retourButton.setVisible(true);
        switch (section){
            case "supprimerMembre" :
                this.titreLabel.setText("Supprimer un membre d'équipage");
                this.suppressionMembreSection.setVisible(true);
                this.setPreferredSize(new Dimension(700, 400));
                ArrayList<MembreEquipage> membres = this.membreEquipageService.getMembres();

                DefaultTableModel model = new DefaultTableModel();

                model.addColumn("Id");
                model.addColumn("Nom");
                model.addColumn("Prenom");
                model.addColumn("Metier");

                for(MembreEquipage membreEquipage : membres){
                    Object[] objs = {membreEquipage.getId(), membreEquipage.getNom(), membreEquipage.getPrenom(), membreEquipage.getMetier()};
                    model.addRow(objs);
                }

                this.supprimerMembreTable = new JTable(model);
                this.supprimerMembreScrollPanel.setLayout(new ScrollPaneLayout());
                this.supprimerMembreScrollPanel.getViewport ().add (this.supprimerMembreTable);
                this.pack();
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

                for(TypeAvion typeAvion : typeAvionService.findAll()){
                    this.typeAvionCombo.addItem(new ComboItem(typeAvion.getNom(), String.valueOf(typeAvion.getId())));
                }

                this.ajoutAvionSection.setVisible(true);
                this.setPreferredSize(new Dimension(700, 280));
                this.pack();
                break;
            case "qualifierMembre" :
                this.titreLabel.setText("Qualifier un membre d'équipage");
                break;
            case "ajouterTypeAvion" :
                this.titreLabel.setText("Ajouter un type d'avion");
                this.ajoutTypeAvionSection.setVisible(true);
                this.setPreferredSize(new Dimension(700, 280));
                this.pack();
                break;
            case "supprimerAvion" :
                this.titreLabel.setText("Supprimer un avion");
                break;
        }
    }


    /**
     * permet d'ajouter les action Listener sur les boutons
     */
    public void addButtonActionListener(){
        retourButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ajoutMembreSection.setVisible(false);
                suppressionMembreSection.setVisible(false);
                ajoutAvionSection.setVisible(false);
                ajoutTypeAvionSection.setVisible(false);
                buttonPanel.setVisible(true);
                setSize(new Dimension(700, 800));
                retourButton.setVisible(false);
                titreLabel.setText("Administration");
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
                String nom = nomNouveauMembre.getText();
                String prenom = prenomNouveauMembre.getText();
                if(!nom.isEmpty() && !prenom.isEmpty()){
                    switch (type) {
                        case "Pilote" :
                            membreEquipageService.addMembreEquipage(nom, prenom, TypeMembreEquipage.PILOTE);
                            break;
                        case "Copilote" :
                            membreEquipageService.addMembreEquipage(nom, prenom, TypeMembreEquipage.COPILOTE);
                            break;
                        case "Personnel non Commercial" :
                            membreEquipageService.addMembreEquipage(nom, prenom, TypeMembreEquipage.PNC);
                            break;
                    }
                    ajoutMembreSection.setVisible(false);
                    buttonPanel.setVisible(true);
                    setSize(new Dimension(700, 800));
                    retourButton.setVisible(false);
                    prenomNouveauMembre.setText("");
                    nomNouveauMembre.setText("");
                    titreLabel.setText("Administration");
                } else {
                    JPanel panel = new JPanel();
                    JOptionPane.showMessageDialog(panel, "Le nom et le prenom doivent être rensigné", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        supprimerMembreButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = supprimerMembreTable.getSelectedRow();
                try{
                    Object id = (supprimerMembreTable.getValueAt(selectedRow, 0));
                    membreEquipageService.delete((int)id);
                    ((DefaultTableModel)supprimerMembreTable.getModel()).removeRow(selectedRow);
                } catch (ArrayIndexOutOfBoundsException ex){
                    JPanel panel = new JPanel();
                    JOptionPane.showMessageDialog(panel, "Vous devez selectionner un membre à supprimer", "Erreur", JOptionPane.ERROR_MESSAGE);
                }

            }
        });


        ajouterTypeAvionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    typeAvionService.addTypeAvion(nomTypeAvion.getText(), Integer.valueOf(nbPNCMin.getText()), Integer.valueOf(nbPNCMax.getText()));

                    ajoutTypeAvionSection.setVisible(false);
                    buttonPanel.setVisible(true);
                    setSize(new Dimension(700, 800));
                    retourButton.setVisible(false);
                    nomTypeAvion.setText("");
                    nbPNCMax.setText("");
                    nbPNCMin.setText("");
                    titreLabel.setText("Administration");
                } catch (EmptyFieldException ex){
                    JPanel panel = new JPanel();
                    JOptionPane.showMessageDialog(panel, ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        ajouterAvionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Object item = typeAvionCombo.getSelectedItem();
                    int id = Integer.valueOf(((ComboItem)item).getValue());

                    avionService.addAvion(typeAvionService.findOneById(id), referenceAvion.getText());

                    ajoutAvionSection.setVisible(false);
                    buttonPanel.setVisible(true);
                    setSize(new Dimension(700, 800));
                    retourButton.setVisible(false);
                    referenceAvion.setText("");
                    titreLabel.setText("Administration");
                } catch (Exception ex){
                    JPanel panel = new JPanel();
                    JOptionPane.showMessageDialog(panel, ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    /**
     * Main permettant de lancer directement le tableau d'administration sans avoir à s'authentifier
     * @param args
     */
    public static void main(String[] args){
        Utilisateur user = new Utilisateur(1L, "admin", "admin", TypeUtilisateur.ADMIN);
        AdminFrame adminFrame = new AdminFrame(user);
    }

}
