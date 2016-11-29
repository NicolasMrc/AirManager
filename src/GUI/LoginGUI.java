package GUI;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Nico on 28/11/2016.
 */
public class LoginGUI extends JFrame{
    private JPanel mainPanel;
    private JLabel adminLabel;
    private JLabel managerLabel;
    private JLabel membreLabel;

    public LoginGUI(){
        this.setTitle("Login");
        this.setContentPane(this.mainPanel);
        this.setVisible(true);
        this.setSize(new Dimension(500, 400));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args){
        new LoginGUI();
    }
}
