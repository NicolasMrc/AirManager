package Repository;

import Entities.*;
import Enum.TypeMembreEquipage;
import config.BDDConfig;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by Nico on 30/11/2016.
 */
public class MembreEquipageRepository {
    private String url;
    private String utilisateur;
    private String motDePasse;
    private Connection connexion = null;

    public MembreEquipageRepository(){
        try {
            BDDConfig config = new BDDConfig();
            this.url = config.getUrl();
            this.utilisateur = config.getUtilisateur();
            this.motDePasse = config.getMotDePasse();
            connexion = DriverManager.getConnection( url, utilisateur, motDePasse );
            System.out.println("successfully connected");
        } catch ( SQLException ex ) {
            System.out.println(ex.getMessage());
        }
    }

    public void addMembre(String nom, String prenom, TypeMembreEquipage typeMembreEquipage){

        int type;
        if (typeMembreEquipage.equals(TypeMembreEquipage.PILOTE)){
            type = 0;
        } else if (typeMembreEquipage.equals(TypeMembreEquipage.COPILOTE)){
            type = 1;
        } else {
            type = 2;
        }

        //Statement stmt = null;
        // the mysql insert statement
        String query = " insert into membre (nom, prenom, type) values (?, ?, ?)";

        // create the mysql insert preparedstatement

        //String query = "INSERT INTO membre m (nom, prenom, type) VALUES ('" + nom + "','" + prenom + "','" + type + "')";
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
