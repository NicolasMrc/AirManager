package GUI;

import Entities.Utilisateur;
import Entities.Vol;
import Services.VolService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by Nico on 12/12/2016.
 */
public class ConsulterVolMembre extends JFrame {
    private JPanel mainPanel;
    private JTable volTable;
    private JButton backButton;
    private JButton logoutButton;
    private JScrollPane volScrollPane;

    private VolService volService = new VolService();

    public ConsulterVolMembre(Utilisateur utilisateur){
        this.setSize(new Dimension(800, 550));
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        this.setTitle("Portail membre");
        this.setContentPane(this.mainPanel);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MembreEquipageGui membreEquipageGui = new MembreEquipageGui(utilisateur);
                dispose();
            }
        });

        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginGUI loginGUI = new LoginGUI();
                dispose();
            }
        });

        ArrayList<Vol> vols = this.volService.findAllByMembreEquipage(utilisateur.getIdMembre());

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
        this.pack();
    }
}
