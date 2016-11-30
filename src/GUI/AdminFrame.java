package GUI;

import Entities.TypeUtilisateur;
import Entities.Utilisateur;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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

    private Utilisateur utilisateurCourrant;

    public AdminFrame(Utilisateur utilisateur) throws HeadlessException {

        this.setSize(new Dimension(700, 800));

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        this.setTitle("Administration");
        this.setContentPane(this.mainPanel);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //this.setResizable(false);

         addPanelMouseListener(supprimerMembrePanel);
         addPanelMouseListener(SupprimerVolPanel);
         addPanelMouseListener(AjouterMembrePanel);
         addPanelMouseListener(CreerVolPanel);
         addPanelMouseListener(SupprimerTypeAvionPanel);
         addPanelMouseListener(CreerAvionPanel);
         addPanelMouseListener(QualifierMembrePanel);
         addPanelMouseListener(CreerTypeAvionPanel);
         addPanelMouseListener(SupprimerAvionPanel);

    }

    public void addPanelMouseListener(JPanel panel){

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

    //TODO Supprimer une fois que tout marchera
    public static void main(String[] args){
        Utilisateur user = new Utilisateur(1L, "admin", "admin", TypeUtilisateur.ADMIN);
        AdminFrame adminFrame = new AdminFrame(user);
    }
}
