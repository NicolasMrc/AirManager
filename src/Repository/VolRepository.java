package Repository;

import Entities.*;
import config.BDDConfig;

import java.sql.*;
import java.util.ArrayList;

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

    private AeroportRepository aeroportRepository = new AeroportRepository();

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

        String query = "insert into vol (numero, id_site, id_destination, date, id_avion, id_equipage) values (?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement preparedStmt = this.connexion.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStmt.setString (1, vol.getNumero());
            preparedStmt.setInt (2, vol.getSite().getId());
            preparedStmt.setInt (3, vol.getDestination().getId());
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

    public ArrayList<Vol> findAll(){
        String query = "SELECT * FROM vol";

        try {

            Statement stmt = this.connexion.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            ArrayList<Vol> vols = new ArrayList<>();

            while (rs.next()) {
                vols.add(this.mapResultSet(rs));
            }

            return vols;

        } catch (SQLException e ) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public ArrayList<Vol> findAllByMembreEquipage(int id){
        String query = "SELECT * FROM vol";

        try {

            Statement stmt = this.connexion.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            ArrayList<Vol> vols = new ArrayList<>();

            while (rs.next()) {
                vols.add(this.mapResultSet(rs));
            }

            ArrayList<Vol> volsWithMembre = new ArrayList<>();

            for(Vol vol : vols){
                if(vol.getEquipage().getCopilote().getId() == id || vol.getEquipage().getPilote().getId() == id){
                    volsWithMembre.add(vol);
                }
                if(vol.getEquipage().getPncs() != null && !vol.getEquipage().getPncs().isEmpty()){
                    for(MembreEquipage membreEquipage : vol.getEquipage().getPncs()){
                        if(membreEquipage.getId() == id){
                            volsWithMembre.add(vol);
                        }
                    }
                }
            }

            return volsWithMembre;

        } catch (SQLException e ) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public void delete(int id){
        try {
            int idEquipage = this.findOneById(id).getEquipage().getId();

            String query = "delete from vol where id = ?";
            PreparedStatement preparedStmt = this.connexion.prepareStatement(query);
            preparedStmt.setInt(1, id);
            preparedStmt.execute();

            this.equipageRepository.delete(idEquipage);

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
    public Vol mapResultSet(ResultSet rs){
        try {
            int id = rs.getInt("id");
            String numero = rs.getString("numero");
            int idSite = rs.getInt("id_site");
            int idDestination = rs.getInt("id_destination");
            String date = rs.getString("date");
            int idAvion = rs.getInt("id_avion");
            int idEquipage = rs.getInt("id_equipage");

            Equipage equipage = this.equipageRepository.findOneById(idEquipage);
            Avion avion = this.avionRepository.findOneById(idAvion);
            Aeroport site = this.aeroportRepository.findOneById(idSite);
            Aeroport destination = this.aeroportRepository.findOneById(idDestination);

            return new Vol(id, numero, site, destination, date, avion, equipage);

        } catch (SQLException e ) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public void addPNC(int idEquipage, int idPNC){
        String query = "insert into equipage_pnc (id_membre_pnc, id_equipage) values (?, ?)";

        try {
            PreparedStatement preparedStmt = this.connexion.prepareStatement(query);

            preparedStmt.setInt (1, idPNC);
            preparedStmt.setInt (2, idEquipage);

            preparedStmt.execute();

        } catch (SQLException e ) {
            System.out.println(e.getMessage());
        }
    }
}
