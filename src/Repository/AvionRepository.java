package Repository;

import Entities.Avion;
import Entities.TypeAvion;
import Services.TypeAvionService;
import config.BDDConfig;

import java.sql.*;
import java.util.ArrayList;

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
     * Service de typeAvion
     */
    TypeAvionService typeAvionService = new TypeAvionService();

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
     * @param avion
     *       l'avion a sauvegarder
     */
    public Avion save(Avion avion){

        String query = "insert into avion (id_type_avion, ref) values (?, ?)";

        try {
            PreparedStatement preparedStmt = this.connexion.prepareStatement(query);
            preparedStmt.setInt (1, avion.getTypeAvion().getId());
            preparedStmt.setString (2, avion.getRef());

           preparedStmt.execute();

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
    public Avion findOneById(int id){
        try {

            String query = "select * from avion a where a.id = ?";
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

    public ArrayList<Avion> findAll(){
        Statement stmt = null;
        String query = "SELECT * FROM avion a";

        try {

            stmt = this.connexion.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            ArrayList<Avion> avions = new ArrayList<>();

            while (rs.next()) {
                avions.add(this.mapResultSet(rs));
            }

            return avions;

        } catch (SQLException e ) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    /**
     * permet de supprimer un avion
     * @param id
     *      l'id de l'avion que l'on souhaite supprimer
     */
    public void delete(int id){
        try {
            String query = "delete from avion where id = ?";
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
    public Avion mapResultSet(ResultSet rs){
        try {
            int id = rs.getInt("id");
            int idTypeAvion = rs.getInt("id_type_avion");
            String ref = rs.getString("ref");

            TypeAvion typeAvion = this.typeAvionService.findOneById(idTypeAvion);

            return new Avion(id, typeAvion, ref);

        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }
}
