package Repository;

import Entities.TypeAvion;
import config.BDDConfig;

import java.lang.reflect.Type;
import java.sql.*;
import java.util.ArrayList;

/**
 * Repository de la class TypeAvion
 * Created by Nico on 30/11/2016.
 */
public class TypeAvionRepository {

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
    public TypeAvionRepository(){
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
     * permet de sauvegarder un Type d'avion en base
     * @param typeAvion
     *      le TypeAvion a sauvegarder
     */
    public TypeAvion save(TypeAvion typeAvion){

        String query = "insert into type_avion (nom, nbPNCMin, nbPNCMax) values (?, ?, ?)";

        try {

            PreparedStatement preparedStmt = this.connexion.prepareStatement(query);
            preparedStmt.setString (1, typeAvion.getNom());
            preparedStmt.setInt    (2, typeAvion.getNbPNCmin());
            preparedStmt.setInt    (3, typeAvion.getNbPNCmax());

            preparedStmt.executeUpdate();

        } catch (SQLException e ) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    /**
     * permet de recupere un type d'avion par son id en base
     * @param id
     *      l'id tu type d'avion a recuperer
     * @return
     *      le type d'avion
     */
    public TypeAvion findOneById(int id){
        try {

            String query = "select * from type_avion t where t.id = ?";
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

    /**
     * permet de recuperer tout les type d'avion en base
     * @return
     *      une liste de tout les types d'avion
     */
    public ArrayList<TypeAvion> findAll(){
        Statement stmt = null;
        String query = "SELECT * FROM type_avion t";

        try {

            stmt = this.connexion.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            ArrayList<TypeAvion> typeAvions = new ArrayList<>();

            while (rs.next()) {
                typeAvions.add(this.mapResultSet(rs));
            }

            return typeAvions;

        } catch (SQLException e ) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    /**
     * permet de supprimer un type avion
     * @param id
     *      l'id du type avion que l'on souhaite supprimer
     */
    public void delete(int id){
        try {
            String query = "delete from type_avion where id = ?";
            PreparedStatement preparedStmt = this.connexion.prepareStatement(query);
            preparedStmt.setInt(1, id);
            preparedStmt.execute();

        } catch (SQLException e ) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * permet de mapper le resultset avec la classe TypeAvion
     * @param rs
     *      le resultset
     * @return
     *      une instance de TypeAvion
     */
    public TypeAvion mapResultSet(ResultSet rs){
        try {
            int id = rs.getInt("id");
            String nom = rs.getString("nom");
            int nbPNCMin = rs.getInt("nbPNCMin");
            int nbPNCMax = rs.getInt("nbPNCMAX");

            return new TypeAvion(id, nom, nbPNCMin, nbPNCMax);
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }



}
