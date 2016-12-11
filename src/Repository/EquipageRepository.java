package Repository;

import Entities.Copilote;
import Entities.Equipage;
import Entities.Pilote;
import config.BDDConfig;

import java.sql.*;

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

            return new Equipage(id, pilote,copilote, null);

        } catch (SQLException e ) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
