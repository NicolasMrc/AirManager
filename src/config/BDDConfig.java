package config;

/**
 * Created by Nico on 30/11/2016.
 */
public class BDDConfig {
    public String url = "jdbc:mysql://localhost:8889/airmanager";
    public String utilisateur = "root";
    public String motDePasse = "root";

    public String getUrl() {
        return url;
    }

    public String getUtilisateur() {
        return utilisateur;
    }

    public String getMotDePasse() {
        return motDePasse;
    }
}
