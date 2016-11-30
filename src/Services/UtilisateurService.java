package Services;

import Entities.TypeUtilisateur;
import Entities.Utilisateur;
import Repository.UtilisateurRepository;

/**
 * Service d'utilisateur
 * Created by Nico on 29/11/2016.
 */
public class UtilisateurService {

    public UtilisateurService(){

    }

    /**
     * Repository d'utilisateur
     */
    private UtilisateurRepository utilisateurRepository = new UtilisateurRepository();

    /**
     * permet de recuperer l'utilisateur en base de donnée si il existe via le repository
     * @param username
     *      le nom d'utilisateur rentré par l'utilisateur courrant
     * @param password
     *      le mot de passe entré par l'utilisateur courant
     * @param typeUtilisateur
     *      le type d'utilisateur courant
     * @return
     *      l'utilisateur en bdd
     */
    public Utilisateur connexion(String username, String password, TypeUtilisateur typeUtilisateur){
        Utilisateur utilisateur = this.utilisateurRepository.connexion(username,password);

        if(utilisateur != null && utilisateur.getPassword().equals(password) && utilisateur.getTypeUtilisateur().equals(typeUtilisateur)){
            return utilisateur;
        } else {
            return null;
        }
    }
}
