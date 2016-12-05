package Repository;

import Entities.*;
import Enum.TypeMembreEquipage;
import config.BDDConfig;

import java.sql.*;
import java.util.ArrayList;

/**
 * Repository de la classe membreEquipage
 * Created by Nico on 30/11/2016.
 */
public class MembreEquipageRepository {

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
    public MembreEquipageRepository(){
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
     * permet de sauvegarder un membre en base
     * @param nom
     *      le nom du membre d'equipage
     * @param prenom
     *      le prenom du membre d'equipage
     * @param typeMembreEquipage
     *      le type du membre d'équipage
     */
    public void save(String nom, String prenom, TypeMembreEquipage typeMembreEquipage){

        int type;
        if (typeMembreEquipage.equals(TypeMembreEquipage.PILOTE)){
            type = 0;
        } else if (typeMembreEquipage.equals(TypeMembreEquipage.COPILOTE)){
            type = 1;
        } else {
            type = 2;
        }

        String query = "insert into membre (nom, prenom, type) values (?, ?, ?)";

        try {
            PreparedStatement preparedStmt = this.connexion.prepareStatement(query);
            preparedStmt.setString (1, nom);
            preparedStmt.setString (2, prenom);
            preparedStmt.setInt    (3, type);

            preparedStmt.execute();

        } catch (SQLException e ) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * permet de retourner tout les membre d'equipage inscrits en base
     * @return
     *      la liste des membre d'equipage
     */
    public ArrayList<MembreEquipage> findAllMembres(){
        Statement stmt = null;
        String query = "SELECT * FROM membre u";

        try {

            stmt = this.connexion.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            ArrayList<MembreEquipage> membreEquipages = new ArrayList<>();

            while (rs.next()) {
                membreEquipages.add(mapResultSet(rs));
            }

            if (!membreEquipages.isEmpty()){
                return membreEquipages;
            }

        } catch (SQLException e ) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    /**
     * permet de supprimer un membre
     * @param id
     *      l'id du membre que l'on souhaite supprimer
     */
    public void delete(int id){
        try {
            String query = "delete from membre where id = ?";
            PreparedStatement preparedStmt = this.connexion.prepareStatement(query);
            preparedStmt.setInt(1, id);
            preparedStmt.execute();

        } catch (SQLException e ) {
            System.out.println(e.getMessage());
        }
    }

    public MembreEquipage findOneById(int id){
        try {

            String query = "select * from membre m where m.id = ?";
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
     * permet de creer un Membre d'equipage a partir du resultset
     * @param rs
     *      le resultset
     * @return
     *      un membreEquipage
     */
    public MembreEquipage mapResultSet(ResultSet rs){
        try {
            int id = rs.getInt("id");
            String nom = rs.getString("nom");
            String prenom = rs.getString("prenom");
            int type = rs.getInt("type");

            if(type == 0){
                return new Pilote(id, nom, prenom);
            } else if (type == 1){
                return new Copilote(id, nom, prenom);
            } else {
                return new PNC(id, nom, prenom);
            }
        } catch (SQLException e ) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
