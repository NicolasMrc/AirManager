package Repository;

import Entities.TypeUtilisateur;
import Entities.Utilisateur;

import java.sql.*;

/**
 * Created by Nico on 29/11/2016.
 */
public class UtilisateurRepository {
    String url = "jdbc:mysql://localhost:8889/airmanager";
    String utilisateur = "root";
    String motDePasse = "root";
    Connection connexion = null;

    public UtilisateurRepository(){
        try {
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

                Utilisateur utilisateur = new Utilisateur(id, usernameFromBdd, passwordFromBdd, type);
                return utilisateur;

            }
        } catch (SQLException e ) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
