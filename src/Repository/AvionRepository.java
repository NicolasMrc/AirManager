package Repository;

import Entities.TypeAvion;
import config.BDDConfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Repository de la classe Avion
 * Created by Nico on 30/11/2016.
 */
public class AvionRepository {

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
    public AvionRepository(){
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
     * permet d'ajouter un avion en base
     * @param typeAvion
     *       le type de l'avion
     * @param ref
     *      la reference de l'avion
     */
    public void save(TypeAvion typeAvion, String ref){

        String query = "insert into avion (id_type_avion, ref) values (?, ?)";

        try {
            PreparedStatement preparedStmt = this.connexion.prepareStatement(query);
            preparedStmt.setInt(1, typeAvion.getId());
            preparedStmt.setString(2, ref);

            preparedStmt.execute();

        } catch (SQLException e ) {
            System.out.println(e.getMessage());
        }
    }
}
