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

    private TypeAvionRepository typeAvionRepository = new TypeAvionRepository();

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
     * @param membreEquipage
     *      le membre d'equipage a sauvegarder
     */
    public void save(MembreEquipage membreEquipage){

        int type;
        if (membreEquipage.getMetier().equals(TypeMembreEquipage.PILOTE)){
            type = 0;
        } else if (membreEquipage.getMetier().equals(TypeMembreEquipage.COPILOTE)){
            type = 1;
        } else {
            type = 2;
        }

        this.deleteExistingQualification(membreEquipage.getId());



        String query = "insert into membre (nom, prenom, type) values (?, ?, ?)";

        try {
            PreparedStatement preparedStmt = this.connexion.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStmt.setString (1, membreEquipage.getNom());
            preparedStmt.setString (2, membreEquipage.getPrenom());
            preparedStmt.setInt    (3, type);

            preparedStmt.execute();

            ResultSet generatedId = preparedStmt.getGeneratedKeys();

            if(generatedId.next()){
                for(TypeAvion qualification : membreEquipage.getQualifications()){
                    this.saveQualification(qualification, generatedId.getInt(1));
                }
            }

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
    public void deleteById(String table, int id){
        try {
            String query = "delete from " + table + " where id = ?";
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
                ArrayList<TypeAvion> test = this.getQualificationByMembreId(id);
                return new Pilote(id, nom, prenom, this.getQualificationByMembreId(id));
            } else if (type == 1){
                return new Copilote(id, nom, prenom, this.getQualificationByMembreId(id));
            } else {
                return new PNC(id, nom, prenom, this.getQualificationByMembreId(id));
            }
        } catch (SQLException e ) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public void deleteMembre(int id){
        MembreEquipage membreEquipage = this.findOneById(id);
        this.deleteExistingQualification(membreEquipage.getId());
        this.deleteById("membre", id);
    }

    public void deleteExistingQualification(int idMembre){
        try {
            String query = "delete from membre_type_avion where id_membre= ?";
            PreparedStatement preparedStmt = this.connexion.prepareStatement(query);
            preparedStmt.setInt(1, idMembre);
            preparedStmt.execute();

        } catch (SQLException e ) {
            System.out.println(e.getMessage());
        }
    }

    public void saveQualification(TypeAvion typeAvion, int idMembre){
        String query = "insert into membre_type_avion (id_membre, id_type_avion) values (?, ?)";

        try {
            PreparedStatement preparedStmt = this.connexion.prepareStatement(query);
            preparedStmt.setInt (1, idMembre);
            preparedStmt.setInt (2, typeAvion.getId());

            preparedStmt.execute();

        } catch (SQLException e ) {
            System.out.println(e.getMessage());
        }
    }

    public ArrayList<TypeAvion> getQualificationByMembreId(int id){
        try {
            String query = "select * from membre_type_avion where id_membre = ?";
            PreparedStatement preparedStmt = this.connexion.prepareStatement(query);
            preparedStmt.setInt(1, id);
            ResultSet rs = preparedStmt.executeQuery();

            ArrayList<TypeAvion> qualifications = new ArrayList<>();

            while(rs.next()) {
                int id_type_avion = rs.getInt("id_type_avion");

                qualifications.add(this.typeAvionRepository.findOneById(id_type_avion));
            }

            return qualifications;

        } catch (SQLException e ) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public ArrayList<MembreEquipage> getQualificationByTypeAvionId(int id){
        try {
            String query = "select * from membre_type_avion where id_type_avion = ?";
            PreparedStatement preparedStmt = this.connexion.prepareStatement(query);
            preparedStmt.setInt(1, id);
            ResultSet rs = preparedStmt.executeQuery();

            ArrayList<MembreEquipage> membreEquipages = new ArrayList<>();

            while(rs.next()) {
                int id_membre = rs.getInt("id_membre");

                membreEquipages.add(this.findOneById(id_membre));
            }

            return membreEquipages;

        } catch (SQLException e ) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public MembreEquipage updateQualifications(MembreEquipage membreEquipage){
        this.deleteExistingQualification(membreEquipage.getId());
        for(TypeAvion qualification : membreEquipage.getQualifications()){
            this.saveQualification(qualification, membreEquipage.getId());
        }
        return this.findOneById(membreEquipage.getId());
    }

    public ArrayList<MembreEquipage> findAllByMetierAndQualification(TypeMembreEquipage typeMembreEquipage, TypeAvion typeAvion){
        ArrayList<MembreEquipage> membreEquipages = this.getQualificationByTypeAvionId(typeAvion.getId());
        ArrayList<MembreEquipage> membreCorrespondant = new ArrayList<>();

        for (MembreEquipage membreEquipage : membreEquipages){
            if(membreEquipage.getMetier().equals(typeMembreEquipage)){
                membreCorrespondant.add(membreEquipage);
            }
        }

        return membreCorrespondant;
    }
}
