package GUI;

import Entities.MembreEquipage;
import Entities.Utilisateur;
import Services.UtilisateurService;
import Enum.TypeUtilisateur;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by Nico on 12/12/2016.
 */
public class MembreEquipageGui extends JFrame{

    private Utilisateur utilisateurCourant;
    private JPanel mainPanel;
    private JLabel consulterVolLabel;
    private JLabel gestionCompteLabel;


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

        this.addMouseListenerToLabel(this.consulterVolLabel);
        this.addMouseListenerToLabel(this.gestionCompteLabel);

    }

    public void addMouseListenerToLabel(JLabel label){
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

                ConsulterVolMembre consulterVolMembre = new ConsulterVolMembre(utilisateurCourant);
                dispose();
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

    public static void main(String[] args){
        UtilisateurService utilisateurService = new UtilisateurService();
        Utilisateur utilisateur = utilisateurService.connexion("Boucher", "password", TypeUtilisateur.MEMBRE_EQUIPAGE);
        MembreEquipageGui membreEquipageGui = new MembreEquipageGui(utilisateur);
    }
}
