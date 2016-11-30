package Repository;

import Enum.TypeUtilisateur;
import Entities.Utilisateur;
import config.BDDConfig;

import java.sql.*;

/**
 * Created by Nico on 29/11/2016.
 */
public class UtilisateurRepository {
    private String url;
    private String utilisateur;
    private String motDePasse;
    private Connection connexion = null;

    public UtilisateurRepository(){
        try {
            BDDConfig config = new BDDConfig();
            this.url = config.getUrl();
            this.utilisateur = config.getUtilisateur();
            this.motDePasse = config.getMotDePasse();
            connexion = DriverManager.getConnection( url, utilisateur, motDePasse );
            System.out.println("successfully connected");
        } catch ( SQLException ex ) {
            System.out.println(ex.getMessage());
        }
    }

    public Utilisateur connexion(String username, String password){
        Statement stmt = null;
        String query = "SELECT * FROM utilisateur u WHERE u.username = '" + username + "'";
        try {

            stmt = this.connexion.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {

                long id = rs.getLong("id");
                String usernameFromBdd = rs.getString("username");
                String passwordFromBdd = rs.getString("password");
                String roleFromBdd = rs.getString("role");

                TypeUtilisateur type;

                if(roleFromBdd.equals("admin")){
                    type = TypeUtilisateur.ADMIN;
                } else if (roleFromBdd.equals("manager")){
                    type = TypeUtilisateur.MANAGER;
                } else {
                    type = TypeUtilisateur.MEMBRE_EQUIPAGE;
                }

                return new Utilisateur(id, usernameFromBdd, passwordFromBdd, type);

            }
        } catch (SQLException e ) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
