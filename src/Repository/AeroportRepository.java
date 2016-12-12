package Repository;

import Entities.Aeroport;
import Services.TypeAvionService;
import config.BDDConfig;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by Nico on 12/12/2016.
 */
public class AeroportRepository {
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
     * Service de typeAvion
     */
    TypeAvionService typeAvionService = new TypeAvionService();

    /**
     * constructeur vide initialisant la connexion avec la bdd
     */
    public AeroportRepository(){
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
     * permet de recupere un aeroport par son id en base
     * @param id
     *      l'id tu type d'aeroport a recuperer
     * @return
     *      aeroport
     */
    public Aeroport findOneById(int id){
        try {

            String query = "select * from aeroport a where a.id = ?";
            PreparedStatement preparedStmt = this.connexion.prepareStatement(query);
            preparedStmt.setInt(1, id);
            ResultSet rs = preparedStmt.executeQuery();

            if(rs.next()) {
                return this.mapResultSet(rs);
            }

        } catch (SQLException e ) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public ArrayList<Aeroport> findAll(){
        Statement stmt = null;
        String query = "SELECT * FROM aeroport a";

        try {

            stmt = this.connexion.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            ArrayList<Aeroport> aeroports = new ArrayList<>();

            while (rs.next()) {
                aeroports.add(this.mapResultSet(rs));
            }

            return aeroports;

        } catch (SQLException e ) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    /**
     * permet d'ajouter un aeroport en base
     * @param aeroport
     *       l'aeroport a sauvegarder
     */
    public Aeroport save(Aeroport aeroport){

        String query = "insert into aeroport (nom, code) values (?, ?)";

        try {
            PreparedStatement preparedStmt = this.connexion.prepareStatement(query);
            preparedStmt.setString (1, aeroport.getNom());
            preparedStmt.setString (2, aeroport.getCode());

            preparedStmt.execute();

        } catch (SQLException e ) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    /**
     * permet de mapper le resultset avec la classe TypeAvion
     * @param rs
     *      le resultset
     * @return
     *      une instance de TypeAvion
     */
    public Aeroport mapResultSet(ResultSet rs){
        try {
            int id = rs.getInt("id");
            String nom = rs.getString("nom");
            String code = rs.getString("code");

            return new Aeroport(id, nom, code);

        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }
}



