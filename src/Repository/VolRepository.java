package Repository;

import Entities.Avion;
import Entities.Equipage;
import Entities.Vol;
import config.BDDConfig;

import java.sql.*;

/**
 * Created by Nico on 10/12/2016.
 */
public class VolRepository {
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
     * repository de la classe avion
     */
    private AvionRepository avionRepository = new AvionRepository();

    private EquipageRepository equipageRepository = new EquipageRepository();

    /**
     * constructeur vide initialisant la connexion avec la bdd
     */
    public VolRepository(){
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
     * @param vol
     *      le membre d'equipage a sauvegarder
     */
    public Vol save(Vol vol){

        String query = "insert into vol (numero, site, destination, date, id_avion, id_equipage) values (?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement preparedStmt = this.connexion.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStmt.setString (1, vol.getNumero());
            preparedStmt.setString (2, vol.getSite());
            preparedStmt.setString (3, vol.getDestination());
            preparedStmt.setString (4, vol.getDate());
            preparedStmt.setInt (5, vol.getAvion().getId());
            preparedStmt.setInt (6, vol.getEquipage().getId());

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

    public Vol findOneById(int id){
        try {
            String query = "select * from vol v where v.id = ?";
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
    public Vol mapResultSet(ResultSet rs){
        try {
            int id = rs.getInt("id");
            String numero = rs.getString("numero");
            String site = rs.getString("site");
            String destination = rs.getString("destination");
            String date = rs.getString("date");
            int idAvion = rs.getInt("id_avion");
            int idEquipage = rs.getInt("id_equipage");

            Equipage equipage = this.equipageRepository.findOneById(idEquipage);
            Avion avion = this.avionRepository.findOneById(idAvion);

            return new Vol(id, numero, site, destination, date, avion, equipage);

        } catch (SQLException e ) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
