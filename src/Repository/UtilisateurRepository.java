package Repository;

import Enum.TypeUtilisateur;
import Entities.Utilisateur;
import config.BDDConfig;

import java.sql.*;

/**
 * Repository de la classe Utilisateur
 * Created by Nico on 29/11/2016.
 */
public class UtilisateurRepository {

    /**
     * L'url de la bdd
     */
    private String url;

    /**
     * l'utilisateur de la bdd
     */
    private String utilisateur;

    /**
     * le mot de passe de la bdd
     */
    private String motDePasse;

    /**
     * la connexion a la bddd
     */
    private Connection connexion = null;

    /**
     * constructeur vide initialisant la connexion avec la bdd
     */
    public UtilisateurRepository(){
        try {
            BDDConfig config = new BDDConfig();
            this.url = config.getUrl();
            this.utilisateur = config.getUtilisateur();
            this.motDePasse = config.getMotDePasse();
            connexion = DriverManager.getConnection( url, utilisateur, motDePasse );
        } catch ( SQLException ex ) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * permet a l'utilisateur de se connecter
     * @param username
     *      le nom d'utilisateur
     * @param password
     *      le mot de passe
     * @return
     *      l'utilisateur
     */
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
