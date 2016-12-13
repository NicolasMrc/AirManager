package Repository;

import Entities.*;
import config.BDDConfig;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by Nico on 10/12/2016.
 */
public class EquipageRepository {

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

    private MembreEquipageRepository membreEquipageRepository = new MembreEquipageRepository();

    /**
     * constructeur vide initialisant la connexion avec la bdd
     */
    public EquipageRepository(){
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
     * @param equipage
     *      le membre d'equipage a sauvegarder
     */
    public Equipage save(Equipage equipage){

        //TODO liste de pnc
        String query = "insert into equipage (id_pilote, id_copilote) values (?, ?)";

        try {
            PreparedStatement preparedStmt = this.connexion.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStmt.setInt (1, equipage.getPilote().getId());
            preparedStmt.setInt (2, equipage.getCopilote().getId());

            preparedStmt.execute();

            ResultSet generatedId = preparedStmt.getGeneratedKeys();

            if(generatedId.next()) {
                return this.findOneById(generatedId.getInt(1));
            }

        } catch (SQLException e ) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    public Equipage findOneById(int id){
        try {
            String query = "select * from equipage e where e.id = ?";
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

    public void delete(int id){
        try {
            String query = "delete from equipage where id = ?";
            PreparedStatement preparedStmt = this.connexion.prepareStatement(query);
            preparedStmt.setInt(1, id);
            preparedStmt.execute();

        } catch (SQLException e ) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * permet de creer un Membre d'equipage a partir du resultset
     * @param rs
     *      le resultset
     * @return
     *      un membreEquipage
     */
    public Equipage mapResultSet(ResultSet rs){
        try {
            int id = rs.getInt("id");
            int idPilote = rs.getInt("id_pilote");
            int idCopilote = rs.getInt("id_copilote");

            Pilote pilote = (Pilote)this.membreEquipageRepository.findOneById(idPilote);
            Copilote copilote = (Copilote)this.membreEquipageRepository.findOneById(idCopilote);

            ArrayList<PNC> pncs = this.findPNC(id);

            return new Equipage(id, pilote,copilote, pncs);

        } catch (SQLException e ) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public void addPNC(int idEquipage, int idPNC){
        String query = "insert into equipage_pnc (id_equipage, id_pnc) values (?, ?)";

        try {
            PreparedStatement preparedStmt = this.connexion.prepareStatement(query);
            preparedStmt.setInt (1, idEquipage);
            preparedStmt.setInt (2, idPNC);

            preparedStmt.execute();
        } catch (SQLException e ) {
            System.out.println(e.getMessage());
        }
    }

    public ArrayList<PNC> findPNC(int idEquipage){
        try {
            String query = "select * from equipage_pnc e where e.id_equipage = ?";
            PreparedStatement preparedStmt = this.connexion.prepareStatement(query);
            preparedStmt.setInt(1, idEquipage);
            ResultSet rs = preparedStmt.executeQuery();

            ArrayList<PNC> pncs = new ArrayList<>();

            while(rs.next()) {
                pncs.add((PNC)this.membreEquipageRepository.findOneById(rs.getInt("id_pnc")));
            }

            return pncs;

        } catch (SQLException e ) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public void removePNC(int idEquipage, int idPNC){
        String query = "DELETE FROM equipage_pnc WHERE id_equipage = ? AND id_pnc = ?";

        try {
            PreparedStatement preparedStmt = this.connexion.prepareStatement(query);
            preparedStmt.setInt (1, idEquipage);
            preparedStmt.setInt (2, idPNC);

            preparedStmt.execute();
        } catch (SQLException e ) {
            System.out.println(e.getMessage());
        }
    }

    public void update(Equipage equipage){
        String query = "UPDATE equipage SET id_pilote = ?, id_copilote = ? WHERE id = ?";

        try {
            PreparedStatement preparedStmt = this.connexion.prepareStatement(query);
            preparedStmt.setInt (1, equipage.getPilote().getId());
            preparedStmt.setInt (2, equipage.getCopilote().getId());
            preparedStmt.setInt (3, equipage.getId());

            preparedStmt.execute();
        } catch (SQLException e ) {
            System.out.println(e.getMessage());
        }
    }
}
