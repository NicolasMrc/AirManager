package GUI;

import Entities.MembreEquipage;
import Entities.Utilisateur;
import Entities.Vol;
import Services.UtilisateurService;
import Enum.TypeUtilisateur;
import Services.VolService;
import config.EcritureLectureFichier;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 * Created by Nico on 12/12/2016.
 */
public class MembreEquipageGui extends JFrame{

    private Utilisateur utilisateurCourant;
    private JPanel mainPanel;
    private JLabel consulterVolLabel;
    private JLabel gestionCompteLabel;
    private JButton backButton;
    private JButton logoutButton;
    private JPanel cardPanel;
    private JScrollPane volScrollPane;
    private JTable volTable;
    private JTextField usernameField;
    private JPasswordField oldPasswordField;
    private JButton sauvegarderButton;
    private JPasswordField newPasswordField;
    private JButton exporterButton;

    private VolService volService = new VolService();

    private UtilisateurService utilisateurService = new UtilisateurService();

    public MembreEquipageGui(Utilisateur utilisateur){
        this.utilisateurCourant = utilisateur;

        this.setSize(new Dimension(800, 550));
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        this.setTitle("Portail membre");
        this.setContentPane(this.mainPanel);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);

        consulterVolLabel.setOpaque(true);
        consulterVolLabel.setBackground(new Color(44,62,80));
        gestionCompteLabel.setOpaque(true);
        gestionCompteLabel.setBackground(new Color(44,62,80));

        this.addMouseListenerToLabel(this.consulterVolLabel, "volsCard");
        this.addMouseListenerToLabel(this.gestionCompteLabel, "gestionCard");
        this.addMouseListenerToButton(this.backButton);
        this.addMouseListenerToButton(this.logoutButton);
        this.addMouseListenerToButton(this.sauvegarderButton);

        this.usernameField.setText(utilisateur.getUsername());

        this.loadVolsMembre();

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout)(cardPanel.getLayout());
                cl.show(cardPanel, "menuCard");
            }
        });
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginGUI loginGUI = new LoginGUI();
                dispose();
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
                }
            }
        });
        exporterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EcritureLectureFichier fichier = new EcritureLectureFichier(utilisateurCourant);
            }
        });
    }

    public void addMouseListenerToLabel(JLabel label, String card){
        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                label.setBackground(new Color(52,73,94));
                label.repaint();
                super.mousePressed(e);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                label.setBackground(new Color(52,73,94));
                repaint();

                changeCard(card);
                super.mouseReleased(e);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                label.setBackground(new Color(52,73,94));
                label.repaint();
                super.mouseEntered(e);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                label.setBackground(new Color(44,62,80));
                label.repaint();
                super.mouseExited(e);
            }
        });
    }

    public void addMouseListenerToButton(JButton button){
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                button.setBackground(new Color(72,103,132));
                button.repaint();
                super.mousePressed(e);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                button.setBackground(new Color(72,103,132));
                repaint();
                super.mouseReleased(e);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(new Color(72,103,132));
                button.repaint();
                super.mouseEntered(e);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(new Color(32,45,58));
                button.repaint();
                super.mouseExited(e);
            }
        });
    }

    public void changeCard(String card){
        CardLayout cl = (CardLayout)(this.cardPanel.getLayout());
        cl.show(this.cardPanel, card);
    }

    public static void main(String[] args){
        UtilisateurService utilisateurService = new UtilisateurService();
        Utilisateur utilisateur = utilisateurService.connexion("Boucher", "password", TypeUtilisateur.MEMBRE_EQUIPAGE);
        MembreEquipageGui membreEquipageGui = new MembreEquipageGui(utilisateur);
    }

    public void loadVolsMembre(){
        ArrayList<Vol> vols = this.volService.findAllByMembreEquipage(this.utilisateurCourant.getIdMembre());

        DefaultTableModel modelVol = new DefaultTableModel();

        modelVol.addColumn("Num vol");
        modelVol.addColumn("Site");
        modelVol.addColumn("Destination");
        modelVol.addColumn("Date");
        modelVol.addColumn("Avion");
        modelVol.addColumn("Pilote");
        modelVol.addColumn("Copilote");

        if(vols != null){
            for(Vol vol: vols) {
                String pilote =  vol.getEquipage().getPilote().getNom();
                String copilote = vol.getEquipage().getCopilote().getNom();
                Object[] objs = {vol.getNumero(), vol.getSite().getNom(), vol.getDestination().getNom(), vol.getDate(), vol.getAvion().getRef(), pilote, copilote};
                modelVol.addRow(objs);
            }
        }

        this.volTable = new JTable(modelVol);
        this.volScrollPane.setLayout(new ScrollPaneLayout());
        this.volScrollPane.getViewport ().add (this.volTable);
    }
}
