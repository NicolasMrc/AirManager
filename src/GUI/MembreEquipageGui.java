package GUI;

import Entities.PNC;
import Entities.Utilisateur;
import Entities.Vol;
import Services.UtilisateurService;
import Enum.TypeUtilisateur;
import Services.VolService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.util.ArrayList;


/**
 * classe de l'interface graphique des membre d'equipage
 * Created by Nico on 12/12/2016.
 */
public class MembreEquipageGui extends JFrame{

    /**
     * l'utilisateur courant
     */
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
    private JButton serialiseButton;

    /**
     * le service de vol
     */
    private VolService volService = new VolService();

    /**
     * le service d'utilisateur
     */
    private UtilisateurService utilisateurService = new UtilisateurService();

    /**
     * constructeur permettant d'initialiser les composant de la vue
     * @param utilisateur
     *      l'utilisateur connecté
     */
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
                setSize(new Dimension(800, 550));
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
                    setSize(new Dimension(800, 550));
                }
            }
        });

        serialiseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<Vol> vols = volService.findAllByMembreEquipage(utilisateurCourant.getIdMembre());
                try {
                    FileOutputStream fileOutputStream = new FileOutputStream("tmp/vol.txt");
                    for(Vol vol : vols) {
                        ObjectOutputStream out = new ObjectOutputStream(fileOutputStream);
                        out.writeObject(vol);
                    }
                    JOptionPane.showMessageDialog(cardPanel, "les vols ont été serialisé dans le fichier tmp/vols.txt", "Succes !", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception ex){
                    JOptionPane.showMessageDialog(cardPanel, ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        exporterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<Vol> vols = volService.findAllByMembreEquipage(utilisateurCourant.getIdMembre());
                createTable(vols);
            }
        });
    }

    /**
     * ajout des mouseListener sur les labels de la vue
     * @param label
     *      le label sur lequel ajouter le listener
     * @param card
     *      la carte a afficher au click sur le label
     */
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

    /**
     * ajout des listener sur les click des boutons
     * @param button
     *      le bouton sur lequel ajouter le listener
     */
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

    /**
     * permet de changer de carte
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
     * permet de charger les vols du membre d'equipage concerné
     */
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

    /**
     * permet de lancer la vue de membre d'equipage sans passer par le login
     * @param args
     *      les arguments
     */
    public static void main(String[] args){
        UtilisateurService utilisateurService = new UtilisateurService();
        Utilisateur utilisateur = utilisateurService.connexion("Boucher", "password", TypeUtilisateur.MEMBRE_EQUIPAGE);
        MembreEquipageGui membreEquipageGui = new MembreEquipageGui(utilisateur);
    }


    public void createTable(ArrayList<Vol> vols){


        String body = "<div class='container'> "+
                "<div class='row'>" +
                "<div class='col-xs-12'>" +
                "<h1 class='title'>Vols</h1>" +
                "</div>" +
                "</div>" +
                "<div class='row'>" +
                "<div class='col-xs-12'>" +
                "<div class='table-div'>" +
                "<table class='table'>" +
                        "<tr>" +
                            "<th>Numéro de vol</th>" +
                            "<th>Site</th>" +
                            "<th>Destination</th>" +
                            "<th>Date</th>" +
                            "<th>Avion</th>" +
                            "<th>Pilote</th>" +
                            "<th>Copilote</th>" +
                            "<th>PNCs</th>" +
                        "</tr>";

        for (Vol vol : vols){
            body += "<tr>" +
                    "<td>" + vol.getNumero() + "</th>" +
                    "<td>" + vol.getSite().getCode() + " - " + vol.getSite().getNom() + "</th>" +
                    "<td>" + vol.getDestination().getCode() + " - " + vol.getDestination().getNom() +"</th>" +
                    "<td>" + vol.getDate() + "</th>" +
                    "<td>" + vol.getAvion().getRef() + "</th>" +
                    "<td>" + vol.getEquipage().getPilote().getPrenom() + " " + vol.getEquipage().getPilote().getNom() + "</th>" +
                    "<td>" + vol.getEquipage().getCopilote().getPrenom() + " " + vol.getEquipage().getCopilote().getNom() + "</th> <td>";

            for(PNC pnc : vol.getEquipage().getPncs()){
                body += pnc.getPrenom() + " " + pnc.getNom() + " ";
            }

            body += "</td></tr></div></div></div></div>";
        }


        String html = "<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \n" +
                "\"http://www.w3.org/TR/html4/loose.dtd\">\n" +
                "<html>\n" +
                "<head>\n" +
                "<link rel=\"stylesheet\" type=\"text/css\" href=\"bootstrap.min.css\">\n"+
                "<link rel=\"stylesheet\" type=\"text/css\" href=\"style.css\">\n"+
                "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n" +
                "<title>Tableau de vols</title>\n" +
                "</head>\n" +
                "<body> "+ body +"\n" +
                "</body>\n" +
                "</html>";

        try {
            FileWriter fstream = new FileWriter("tmp/vol_"+ utilisateurCourant.getUsername() +".html");
            BufferedWriter out = new BufferedWriter(fstream);
            out.write(html);
            out.close();

            File htmlFile = new File("tmp/vol_"+ utilisateurCourant.getUsername() +".html");
            Desktop.getDesktop().browse(htmlFile.toURI());

            JOptionPane.showMessageDialog(cardPanel, "les vols ont été sauvegardé dans le fichier tmp/vols_"+ utilisateurCourant.getUsername() +".html", "Succes !", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception ex){
            JOptionPane.showMessageDialog(cardPanel, ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }

    }
}
