package config;

/**
 * Service de configuration de base de donnée
 * Created by Nico on 30/11/2016.
 */
public class BDDConfig {
    /**
     * port
     */
    public String port = "8889";

    /**
     * url de connexion a la bdd
     */
    public String url = "jdbc:mysql://localhost:";

    /**
     * username de la bdd
     */
    public String utilisateur = "root";

    /**
     * mot de passe de la bdd
     */
    public String motDePasse = "root";

    /**
     * nom de la base
     */
    public String nomBDD = "airmanager";

    /**
     * retourne l'url de connexion en bdd
     * @return
     *  l'url
     */
    public String getUrl() {
        return url + port + "/" + nomBDD;
    }

    /**
     * retourne l'utilisateur de la bdd
     * @return
     *      l'utilisateur
     */
    public String getUtilisateur() {
        return utilisateur;
    }

    /**
     * retourne le mot de passe de la bdd
     * @return
     *      le mot de passe
     */
    public String getMotDePasse() {
        return motDePasse;
    }
}
