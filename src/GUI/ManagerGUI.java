package GUI;

import Entities.*;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import Enum.TypeUtilisateur;
import Exceptions.EmptyFieldException;
import Services.EquipageService;
import Services.MembreEquipageService;
import Services.UtilisateurService;
import Services.VolService;

import Enum.TypeMembreEquipage;

/**
 * classe de l'interface graphique du manager
 * Created by Nico on 12/12/2016.
 */
public class ManagerGUI extends JFrame{

    private JPanel mainPanel;
    private JButton backButton;
    private JButton logoutButton;
    private JPanel cardPanel;
    private JLabel affecterMembreLabel;
    private JLabel afficherVolLabel;
    private JLabel gestionCompteLabel;
    private JTextField usernameField;
    private JPasswordField oldPasswordField;
    private JPasswordField newPasswordField;
    private JButton sauvegarderButton;
    private JTable volTable;
    private JScrollPane volScrollPane;
    private JPanel equipagePanel;
    private JPanel pncPanel;
    private JComboBox pilote;
    private JComboBox copilote;
    private JComboBox pnc;
    private JButton ajouterButton;
    private JTable pncTable;
    private JButton supprimerButton;
    private JScrollPane pncScrollPanel;

    /**
     * service d'utilisateur
     */
    private UtilisateurService utilisateurService = new UtilisateurService();

    /**
     * l'utilisateur connecté
     */
    private Utilisateur utilisateurCourant;

    /**
     * le service de vol
     */
    private VolService volService = new VolService();

    /**
     * le service de membre d'equipage
     */
    private MembreEquipageService membreEquipageService = new MembreEquipageService();

    /**
     * le service d'equipage
     */
    private EquipageService equipageService = new EquipageService();

    /**
     * permet d'initialiser les composant de la vue lors de son affichage
     * @param utilisateurCourant
     *      l'utilisateur connecté
     */
    public ManagerGUI(Utilisateur utilisateurCourant){

        this.utilisateurCourant = utilisateurCourant;

        this.setSize(new Dimension(800, 570));
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        this.setTitle("Portail Manager");
        this.setContentPane(this.mainPanel);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);

        this.addMouseListenerToLabel(this.afficherVolLabel, "afficherVolCard");
        this.addMouseListenerToLabel(this.gestionCompteLabel, "gestionCard");

        this.usernameField.setText(utilisateurCourant.getUsername());

        this.loadVols();

        this.addListener();
    }

    /**
     * permet d'ajouter les mouseLisntener aux labels
     * @param label
     *      le label auquel ajouté le mouseListener
     * @param card
     *      la carte a afficher au click sur le jlabel
     */
    public void addMouseListenerToLabel(JLabel label, String card){
        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                label.setBackground(new Color(120, 0, 0));
                label.repaint();
                super.mousePressed(e);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                label.setBackground(new Color(120, 0, 0));
                repaint();

                changeCard(card);
                super.mouseReleased(e);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                label.setBackground(new Color(120, 0, 0));
                label.repaint();
                super.mouseEntered(e);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                label.setBackground(new Color(95, 0, 0));
                label.repaint();
                super.mouseExited(e);
            }
        });
    }

    /**
     * permet le changement de cartes sur l'affichage
     * @param card
     *      la carte a afficher
     */
    public void changeCard(String card){
        if(card.equals("gestionCard")){
            this.setSize(new Dimension(800, 270));
        }
        CardLayout cl = (CardLayout)(this.cardPanel.getLayout());
        cl.show(this.cardPanel, card);
    }

    /**
     * permet de charger les vols
     */
    public void loadVols(){
        ArrayList<Vol> vols = this.volService.findAll();

        DefaultTableModel modelVol = new DefaultTableModel();

        modelVol.addColumn("Id");
        modelVol.addColumn("Num vol");
        modelVol.addColumn("Site");
        modelVol.addColumn("Destination");
        modelVol.addColumn("Date");
        modelVol.addColumn("Avion");

        if(vols != null){
            for(Vol vol: vols) {
                Object[] objs = {vol.getId(), vol.getNumero(), vol.getSite().getNom(), vol.getDestination().getNom(), vol.getDate(), vol.getAvion().getRef()};
                modelVol.addRow(objs);
            }
        }

        this.volTable = new JTable(modelVol);
        this.volScrollPane.setLayout(new ScrollPaneLayout());
        this.volScrollPane.getViewport ().add (this.volTable);
    }

    /**
     * permet d'ajouter les listener sur les boutons de l'interface manager
     */
    public void addListener(){
        this.volTable.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
            public void valueChanged(ListSelectionEvent event) {
                equipagePanel.setVisible(true);

                pilote.setSelectedItem(null);
                copilote.setSelectedItem(null);
                pnc.setSelectedItem(null);

                pnc.removeAllItems();
                pilote.removeAllItems();
                copilote.removeAllItems();

                int idVolSelectionne = (Integer)volTable.getValueAt(volTable.getSelectedRow(), 0);

                Vol volSelectionnee = volService.findOneById(idVolSelectionne);

                if(volSelectionnee != null && volSelectionnee.getEquipage() != null){

                    for (MembreEquipage membre : membreEquipageService.findAllByMetierAndQualification(TypeMembreEquipage.PILOTE, volSelectionnee.getAvion().getTypeAvion())){
                        if(membre.getId() != volSelectionnee.getEquipage().getPilote().getId()){
                            pilote.addItem(new ComboItem(membre.getPrenom() + " " + membre.getNom(), String.valueOf(membre.getId())));
                        }
                    }

                    for (MembreEquipage membre : membreEquipageService.findAllByMetierAndQualification(TypeMembreEquipage.COPILOTE, volSelectionnee.getAvion().getTypeAvion())){
                        if(membre.getId() != volSelectionnee.getEquipage().getCopilote().getId()) {
                            copilote.addItem(new ComboItem(membre.getPrenom() + " " + membre.getNom(), String.valueOf(membre.getId())));
                        }
                    }

                    pnc.addItem(null);
                    for (MembreEquipage membre : membreEquipageService.findAllByMetierAndQualification(TypeMembreEquipage.PNC, volSelectionnee.getAvion().getTypeAvion())){
                        pnc.addItem(new ComboItem(membre.getPrenom() + " " + membre.getNom(), String.valueOf(membre.getId())));
                    }

                    if(volSelectionnee.getEquipage().getPilote() != null){
                        int idPilote = volSelectionnee.getEquipage().getPilote().getId();
                        String nomPilote = volSelectionnee.getEquipage().getPilote().getPrenom() + " " + volSelectionnee.getEquipage().getPilote().getNom();
                        pilote.addItem(new ComboItem(nomPilote, String.valueOf(idPilote)));
                        pilote.setSelectedItem(new ComboItem(nomPilote, String.valueOf(idPilote)));
                    } else {
                        pilote.setSelectedItem(null);
                    }
                    if(volSelectionnee.getEquipage().getCopilote() != null){
                        int idCopilote = volSelectionnee.getEquipage().getPilote().getId();
                        String nomCopilote = volSelectionnee.getEquipage().getCopilote().getPrenom() + " " + volSelectionnee.getEquipage().getCopilote().getNom();
                        copilote.addItem(new ComboItem(nomCopilote, String.valueOf(idCopilote)));
                        copilote.setSelectedItem(new ComboItem(nomCopilote, String.valueOf(idCopilote)));
                    } else {
                        copilote.setSelectedItem(null);
                    }

                    if(volSelectionnee.getEquipage().getPncs() != null && !volSelectionnee.getEquipage().getPncs().isEmpty()){
                        DefaultTableModel modelPNC = new DefaultTableModel();

                        modelPNC.addColumn("Id");
                        modelPNC.addColumn("Prénom");
                        modelPNC.addColumn("Nom");

                        for(MembreEquipage membreEquipage : volSelectionnee.getEquipage().getPncs()){
                            JLabel pnc = new JLabel(membreEquipage.getPrenom() + " " + membreEquipage.getNom());
                            pnc.setForeground(new Color(255,255,255));
                            pncTable.add(pnc);
                            Object[] objs = {membreEquipage.getId(), membreEquipage.getPrenom(), membreEquipage.getNom()};
                            modelPNC.addRow(objs);
                        }

                        pncTable = new JTable(modelPNC);
                        pncScrollPanel.setLayout(new ScrollPaneLayout());
                        pncScrollPanel.getViewport ().add (pncTable);
                    } else {
                        pncTable = new JTable();
                        pncScrollPanel.setLayout(new ScrollPaneLayout());
                        pncScrollPanel.getViewport ().add (pncTable);
                    }
                } else {
                    equipagePanel.setVisible(false);
                }
            }
        });


        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginGUI loginGUI = new LoginGUI();
                dispose();
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout)(cardPanel.getLayout());
                cl.show(cardPanel, "menuCard");
                setSize(new Dimension(800, 570));
            }
        });

        sauvegarderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = String.valueOf(usernameField.getText());
                String newPassword = String.valueOf(newPasswordField.getPassword());
                String oldPassword = String.valueOf(oldPasswordField.getPassword());

                if((username.equals("") && oldPassword.equals("")) || oldPassword.equals("")){
                    Panel panel = new Panel();
                    JOptionPane.showMessageDialog(panel, "Veuillez renseigner l'ancien mot de passe et soit le nom d'utilisateur ou un nouveau mot de passe!", "Erreur", JOptionPane.ERROR_MESSAGE);
                } else {
                    utilisateurService.changeCredential(utilisateurCourant, username, oldPassword, newPassword);
                    Panel panel = new Panel();
                    JOptionPane.showMessageDialog(panel, "Changements enregistrés", "Succès !", JOptionPane.INFORMATION_MESSAGE);
                    changeCard("menuCard");
                    oldPasswordField.setText("");
                    newPasswordField.setText("");
                    setSize(new Dimension(800, 550));
                }
            }
        });


        ajouterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Integer idVolSelectionne = (Integer)volTable.getValueAt(volTable.getSelectedRow(), 0);
                Vol vol = volService.findOneById(idVolSelectionne);
                Integer idPNC = Integer.valueOf(((ComboItem)pnc.getSelectedItem()).getValue());

                equipageService.addPNC(vol.getEquipage().getId(), idPNC);

                try {
                    MembreEquipage newPnc = membreEquipageService.findOneById(idPNC);

                    DefaultTableModel modelPNC = (DefaultTableModel) pncTable.getModel();
                    Object[] objs = {newPnc.getId(), newPnc.getPrenom(), newPnc.getNom()};
                    modelPNC.addRow(objs);

                    pncTable = new JTable(modelPNC);
                    pncScrollPanel.setLayout(new ScrollPaneLayout());
                    pncScrollPanel.getViewport ().add (pncTable);

                } catch (Exception ex){
                    System.out.println(ex.getMessage());
                }
            }
        });


        supprimerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Integer idVolSelectionne = (Integer)volTable.getValueAt(volTable.getSelectedRow(), 0);
                Integer idPNCSelectionne = (Integer)pncTable.getValueAt(pncTable.getSelectedRow(), 0);

                if(idPNCSelectionne != null && idVolSelectionne != null) {

                    equipageService.removePNC(idVolSelectionne, idPNCSelectionne);

                    DefaultTableModel modelPNC = (DefaultTableModel) pncTable.getModel();
                    modelPNC.removeRow(pncTable.getSelectedRow());

                    pncTable = new JTable(modelPNC);
                    pncScrollPanel.setLayout(new ScrollPaneLayout());
                    pncScrollPanel.getViewport().add(pncTable);
                }
            }
        });

        this.pilote.addActionListener (new ActionListener () {
            public void actionPerformed(ActionEvent e) {
                if (pilote.getSelectedItem() != null){
                    Integer idVolSelectionne = (Integer) volTable.getValueAt(volTable.getSelectedRow(), 0);
                    Integer idPilote = Integer.valueOf(((ComboItem) pilote.getSelectedItem()).getValue());

                    Equipage equipage = volService.findOneById(idVolSelectionne).getEquipage();

                    if(idPilote != equipage.getPilote().getId()) {
                        equipageService.updatePilote(equipage, idPilote);
                    }
                 }
            }
        });

        this.copilote.addActionListener (new ActionListener () {
            public void actionPerformed(ActionEvent e) {
                if (copilote.getSelectedItem() != null) {
                    Integer idVolSelectionne = (Integer) volTable.getValueAt(volTable.getSelectedRow(), 0);
                    Integer idCoPilote = Integer.valueOf(((ComboItem) copilote.getSelectedItem()).getValue());


                    Equipage equipage = volService.findOneById(idVolSelectionne).getEquipage();
                    if(idCoPilote != equipage.getCopilote().getId()) {
                        equipageService.updateCopilote(equipage, idCoPilote);
                    }
                }
            }
        });

    }


    /**
     * permet de lancer directement la vue du manager sans passer par le login
     * @param args
     *      les arguments
     */
    public static void main(String[] args){
        UtilisateurService utilisateurService = new UtilisateurService();
        Utilisateur utilisateur = utilisateurService.connexion("manager", "manager", TypeUtilisateur.MANAGER);
        ManagerGUI managerGUI = new ManagerGUI(utilisateur);
    }
}
