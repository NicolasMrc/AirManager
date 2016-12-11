package GUI;

import Entities.*;
import Enum.TypeUtilisateur;
import Exceptions.EmptyFieldException;
import Services.*;

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
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.Exchanger;

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
    private JPanel suppressionSection;
    private JTable supressionTable;
    private JScrollPane listeSupression;
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
    private JButton supprimerTypeAvion;
    private JButton supprimerAvion;
    private JTable qualificationTable;
    private JScrollPane qualificationScrollPane;
    private JPanel qualificationSection;
    private JComboBox qualification1Combo;
    private JComboBox qualification2Combo;
    private JButton sauvegarderQualificationButton;
    private JPanel creerVolSection;
    private JTextField numeroVol;
    private JTextField siteVol;
    private JTextField destinationVol;
    private JTextField dateVol;
    private JComboBox piloteVol;
    private JComboBox copiloteVol;
    private JComboBox avionVol;
    private JButton supprimerVolButton;
    private JButton ajouterVolButton;

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
     * Service de vol
     */
    private VolService volService = new VolService();

    /**
     * Service de vol
     */
    private EquipageService equipageService = new EquipageService();

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
        this.suppressionSection.setVisible(false);
        this.ajoutAvionSection.setVisible(false);
        this.ajoutTypeAvionSection.setVisible(false);
        this.qualificationSection.setVisible(false);
        this.creerVolSection.setVisible(false);

        this.supprimerMembreButton.setVisible(false);
        this.supprimerTypeAvion.setVisible(false);
        this.supprimerAvion.setVisible(false);
        this.supprimerVolButton.setVisible(false);

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
                this.suppressionSection.setVisible(true);
                this.setPreferredSize(new Dimension(700, 400));
                ArrayList<MembreEquipage> membres = this.membreEquipageService.getMembres();

                DefaultTableModel modelMembre = new DefaultTableModel();

                modelMembre.addColumn("Id");
                modelMembre.addColumn("Nom");
                modelMembre.addColumn("Prenom");
                modelMembre.addColumn("Metier");
                modelMembre.addColumn("Qualification");
                modelMembre.addColumn("Qualification");

                if(modelMembre != null) {
                    for (MembreEquipage membreEquipage : membres) {
                        String qualif1 = "";
                        String qualif2 = "";
                        if (membreEquipage.getQualifications().size() > 0){
                            qualif1 = membreEquipage.getQualifications().get(0).getNom();
                        }
                        if (membreEquipage.getQualifications().size() >= 2) {
                            qualif2 = membreEquipage.getQualifications().get(1).getNom();
                        }
                        Object[] objs = {membreEquipage.getId(), membreEquipage.getNom(), membreEquipage.getPrenom(), membreEquipage.getMetier(), qualif1, qualif2};
                        modelMembre.addRow(objs);
                    }
                }

                this.supprimerMembreButton.setVisible(true);
                this.supressionTable = new JTable(modelMembre);
                this.listeSupression.setLayout(new ScrollPaneLayout());
                this.listeSupression.getViewport ().add (this.supressionTable);
                this.pack();
                break;
            case "ajouterMembre" :
                this.titreLabel.setText("Ajouter un membre d'équipage");
                this.ajoutMembreSection.setVisible(true);
                this.setPreferredSize(new Dimension(700, 400));
                this.pack();
                break;
            case "supprimerVol" :
                this.titreLabel.setText("Supprimer un vol");

                this.suppressionSection.setVisible(true);
                this.setPreferredSize(new Dimension(700, 400));

                ArrayList<Vol> vols = this.volService.findAll();

                DefaultTableModel modelVol = new DefaultTableModel();

                modelVol.addColumn("Id");
                modelVol.addColumn("Num vol");
                modelVol.addColumn("Site");
                modelVol.addColumn("Destination");
                modelVol.addColumn("Date");
                modelVol.addColumn("Avion");
                modelVol.addColumn("Pilote");
                modelVol.addColumn("Copilote");

                for(Vol vol: vols){
                    String pilote = vol.getEquipage().getPilote().getPrenom() + " " + vol.getEquipage().getPilote().getNom();
                    String copilote = vol.getEquipage().getCopilote().getPrenom() + " " + vol.getEquipage().getCopilote().getNom();
                    Object[] objs = {vol.getId(), vol.getNumero(), vol.getSite(), vol.getDestination(), vol.getDate(), vol.getAvion().getRef(), pilote, copilote};
                    modelVol.addRow(objs);
                }

                this.supprimerVolButton.setVisible(true);
                this.supressionTable = new JTable(modelVol);
                this.listeSupression.setLayout(new ScrollPaneLayout());
                this.listeSupression.getViewport ().add (this.supressionTable);
                this.pack();
                break;
            case "ajouterVol" :
                this.titreLabel.setText("Ajouter un vol");
                this.creerVolSection.setVisible(true);

                this.avionVol.addItem(null);

                for(Avion avion : avionService.findAll()){
                    this.avionVol.addItem(new ComboItem(avion.getRef(), String.valueOf(avion.getId())));
                }

                this.avionVol.addActionListener (new ActionListener () {
                    public void actionPerformed(ActionEvent e) {
                        piloteVol.setEnabled(true);
                        copiloteVol.setEnabled(true);
                        Integer idAvion = Integer.valueOf(((ComboItem) avionVol.getSelectedItem()).getValue());

                        Avion selectedAvion = avionService.findOneById(idAvion);

                        if(selectedAvion != null){
                            piloteVol.removeAllItems();
                            copiloteVol.removeAllItems();

                            copiloteVol.addItem(null);
                            piloteVol.addItem(null);

                            for(MembreEquipage copilote : membreEquipageService.findAllByMetierAndQualification(TypeMembreEquipage.COPILOTE, selectedAvion.getTypeAvion())){
                                copiloteVol.addItem(new ComboItem(copilote.getPrenom() + " " + copilote.getNom(), String.valueOf(copilote.getId())));
                            }

                            for(MembreEquipage pilote : membreEquipageService.findAllByMetierAndQualification(TypeMembreEquipage.PILOTE, selectedAvion.getTypeAvion())){
                                piloteVol.addItem(new ComboItem(pilote.getPrenom() + " " + pilote.getNom(), String.valueOf(pilote.getId())));
                            }

                        }
                    }
                });

                this.setPreferredSize(new Dimension(700, 350));
                this.pack();
                break;
            case "supprimerTypeAvion" :
                this.titreLabel.setText("Supprimer un type d'avion");
                this.suppressionSection.setVisible(true);
                this.setPreferredSize(new Dimension(700, 400));
                ArrayList<TypeAvion> typeAvions = this.typeAvionService.findAll();

                DefaultTableModel modelTypeAvion = new DefaultTableModel();

                modelTypeAvion.addColumn("Id");
                modelTypeAvion.addColumn("Nom");
                modelTypeAvion.addColumn("Nombre de PNC min");
                modelTypeAvion.addColumn("Nombre de PNC max");

                for(TypeAvion typeAvion: typeAvions){
                    Object[] objs = {typeAvion.getId(), typeAvion.getNom(), typeAvion.getNbPNCmin(), typeAvion.getNbPNCmax()};
                    modelTypeAvion.addRow(objs);
                }

                this.supprimerTypeAvion.setVisible(true);
                this.supressionTable = new JTable(modelTypeAvion);
                this.listeSupression.setLayout(new ScrollPaneLayout());
                this.listeSupression.getViewport ().add (this.supressionTable);
                this.pack();
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
                this.qualificationSection.setVisible(true);

                ArrayList<MembreEquipage> membresQualification = this.membreEquipageService.getMembres();

                DefaultTableModel modelMembrQualification = new DefaultTableModel();

                modelMembrQualification.addColumn("Id");
                modelMembrQualification.addColumn("Nom");
                modelMembrQualification.addColumn("Prenom");
                modelMembrQualification.addColumn("Metier");
                modelMembrQualification.addColumn("Qualification");
                modelMembrQualification.addColumn("Qualification");

                if(membresQualification != null) {
                    for (MembreEquipage membreEquipage : membresQualification) {
                        String qualif1 = "";
                        String qualif2 = "";
                        if (membreEquipage.getQualifications().size() > 0){
                            qualif1 = membreEquipage.getQualifications().get(0).getNom();
                        }
                        if (membreEquipage.getQualifications().size() >= 2) {
                            qualif2 = membreEquipage.getQualifications().get(1).getNom();
                        }
                        Object[] objs = {membreEquipage.getId(), membreEquipage.getNom(), membreEquipage.getPrenom(), membreEquipage.getMetier(), qualif1, qualif2};
                        modelMembrQualification.addRow(objs);
                    }
                }

                this.qualificationTable = new JTable(modelMembrQualification);
                this.qualificationScrollPane.setLayout(new ScrollPaneLayout());
                this.qualificationScrollPane.getViewport ().add (this.qualificationTable);

                ArrayList<TypeAvion> typesAvions = this.typeAvionService.findAll();

                //TODO reword ca parce que ca peut etre mieux
                this.qualificationTable.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
                    public void valueChanged(ListSelectionEvent event) {
                        try{
                            int idMembreSelectionne = (Integer)qualificationTable.getValueAt(qualificationTable.getSelectedRow(), 0);
                            MembreEquipage membreSelectionne =  membreEquipageService.findOneById(idMembreSelectionne);

                            qualification1Combo.removeAllItems();
                            qualification2Combo.removeAllItems();

                            qualification1Combo.addItem(null);
                            qualification2Combo.addItem(null);

                            for(TypeAvion typeAvion : typesAvions){

                                ComboItem combo1Item = new ComboItem(typeAvion.getNom(), String.valueOf(typeAvion.getId()));
                                ComboItem combo2Item = new ComboItem(typeAvion.getNom(), String.valueOf(typeAvion.getId()));

                                qualification1Combo.addItem(combo1Item);
                                qualification2Combo.addItem(combo2Item);

                                if (membreSelectionne.getQualifications().size() >= 1 && membreSelectionne.getQualifications().get(0) != null) {
                                    if(membreSelectionne.getQualifications().get(0).getNom().equals(typeAvion.getNom())){
                                        qualification1Combo.setSelectedItem(combo1Item);
                                    }
                                }
                                if (membreSelectionne.getQualifications().size() >= 2 && membreSelectionne.getQualifications().get(1) != null) {
                                    if(membreSelectionne.getQualifications().get(1).getNom().equals(typeAvion.getNom())){
                                        qualification2Combo.setSelectedItem(combo2Item);
                                    }
                                }
                            }


                        } catch (EmptyFieldException e){
                            System.out.println(e.getMessage());
                        }
                    }
                });

                break;
            case "ajouterTypeAvion" :
                this.titreLabel.setText("Ajouter un type d'avion");
                this.ajoutTypeAvionSection.setVisible(true);
                this.setPreferredSize(new Dimension(700, 280));
                this.pack();
                break;
            case "supprimerAvion" :
                this.titreLabel.setText("Supprimer un avion");
                this.suppressionSection.setVisible(true);
                this.setPreferredSize(new Dimension(700, 400));
                ArrayList<Avion> avions = this.avionService.findAll();

                DefaultTableModel modelAvion = new DefaultTableModel();

                modelAvion.addColumn("Id");
                modelAvion.addColumn("Type d'avion");
                modelAvion.addColumn("Reference");

                if(avions != null) {
                    for (Avion avion : avions) {
                        Object[] objs = {avion.getId(), avion.getTypeAvion().getNom(), avion.getRef()};
                        modelAvion.addRow(objs);
                    }
                }

                this.supprimerTypeAvion.setVisible(true);
                this.supressionTable = new JTable(modelAvion);
                this.listeSupression.setLayout(new ScrollPaneLayout());
                this.listeSupression.getViewport ().add (this.supressionTable);
                this.pack();
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
                suppressionSection.setVisible(false);
                ajoutAvionSection.setVisible(false);
                ajoutTypeAvionSection.setVisible(false);
                qualificationSection.setVisible(false);
                buttonPanel.setVisible(true);
                setSize(new Dimension(700, 800));
                retourButton.setVisible(false);
                titreLabel.setText("Administration");
                supprimerMembreButton.setVisible(false);
                supprimerTypeAvion.setVisible(false);
                supprimerAvion.setVisible(false);
                creerVolSection.setVisible(false);
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

        supprimerTypeAvion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = getIdSelectedRow();
                typeAvionService.delete(id);
                deleteSelectedRow();
            }
        });

        supprimerMembreButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = getIdSelectedRow();
                membreEquipageService.delete(id);
                deleteSelectedRow();
            }
        });

        supprimerAvion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = getIdSelectedRow();
                avionService.delete(id);
                deleteSelectedRow();
            }
        });

        ajouterVolButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Integer idAvion = Integer.valueOf(((ComboItem) avionVol.getSelectedItem()).getValue());
                Integer idPilote = Integer.valueOf(((ComboItem) piloteVol.getSelectedItem()).getValue());
                Integer idCopilote = Integer.valueOf(((ComboItem) copiloteVol.getSelectedItem()).getValue());

                String numVol = numeroVol.getText();
                String site = siteVol.getText();
                String destination = destinationVol.getText();
                String date = dateVol.getText();

                Avion avion = avionService.findOneById(idAvion);

                try {
                    Pilote pilote = (Pilote)membreEquipageService.findOneById(idPilote);
                    Copilote copilote = (Copilote)membreEquipageService.findOneById(idCopilote);
                    //TODO liste de pnc
                    Equipage equipage = new Equipage(pilote, copilote, null);

                    equipage = equipageService.save(equipage);

                    if(avion != null && pilote != null && copilote != null  && equipage != null && !numVol.equals("")
                            && !site.equals("") && !destination.equals("") && !date.equals("")){
                        Vol vol = new Vol(numVol, site, destination, date, avion, equipage);
                        volService.save(vol);
                    }

                    siteVol.setText("");
                    destinationVol.setText("");
                    numeroVol.setText("");
                    dateVol.setText("");
                    avionVol.setSelectedItem(null);
                    piloteVol.setSelectedItem(null);
                    copiloteVol.setSelectedItem(null);

                    creerVolSection.setVisible(false);
                    buttonPanel.setVisible(true);
                    setSize(new Dimension(700, 800));
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }

            }
        });

        sauvegarderQualificationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int idMembreSelectionne = (Integer) qualificationTable.getValueAt(qualificationTable.getSelectedRow(), 0);
                    MembreEquipage membreSelectionne = membreEquipageService.findOneById(idMembreSelectionne);
                    Integer idQualification1 = Integer.valueOf(((ComboItem) qualification1Combo.getSelectedItem()).getValue());
                    Integer idQualification2 = Integer.valueOf(((ComboItem) qualification2Combo.getSelectedItem()).getValue());
                    MembreEquipage membreEquipage = membreEquipageService.qualification(membreSelectionne, idQualification1, idQualification2);

                    String qualif1 = "";
                    String qualif2 = "";
                    if (membreEquipage.getQualifications().size() > 0){
                        qualif1 = membreEquipage.getQualifications().get(0).getNom();
                    }
                    if (membreEquipage.getQualifications().size() >= 2) {
                        qualif2 = membreEquipage.getQualifications().get(1).getNom();
                    }

                    DefaultTableModel modelMembreQualification = (DefaultTableModel)qualificationTable.getModel();
                    modelMembreQualification.setValueAt(qualif1, qualificationTable.getSelectedRow(), 4);
                    modelMembreQualification.setValueAt(qualif2, qualificationTable.getSelectedRow(), 5);
                } catch (Exception ex){
                    System.out.println(ex.getMessage());
                }
            }
        });

        supprimerVolButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = getIdSelectedRow();
                volService.delete(id);
                deleteSelectedRow();
            }
        });
    }

    public int getIdSelectedRow(){
        int selectedRow = supressionTable.getSelectedRow();
        try{
            Object id = (supressionTable.getValueAt(selectedRow, 0));
            return (int)id;
        } catch (ArrayIndexOutOfBoundsException ex){
            JPanel panel = new JPanel();
            JOptionPane.showMessageDialog(panel, "Vous devez selectionner un membre à supprimer", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
        return -1;
    }

    public void deleteSelectedRow(){
        int selectedRow = supressionTable.getSelectedRow();
        ((DefaultTableModel) supressionTable.getModel()).removeRow(selectedRow);
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
