package Services;

import Enum.TypeUtilisateur;
import Entities.Utilisateur;
import Repository.UtilisateurRepository;

/**
 * Service d'utilisateur
 * Created by Nico on 29/11/2016.
 */
public class UtilisateurService {

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

    public Utilisateur save(String username, String password, TypeUtilisateur typeUtilisateur, int idMembre){
        Utilisateur newUtilisateur = new Utilisateur(username, password, typeUtilisateur, idMembre);
        return this.utilisateurRepository.save(newUtilisateur);
    }

    public Utilisateur changeCredential(Utilisateur utilisateur, String username, String oldPassword, String newPassword){
        if(utilisateur.getPassword().equals(oldPassword)){
            if(!username.equals("")) {
                utilisateur.setUsername(username);
            }
            if(!newPassword.equals("")) {
                utilisateur.setPassword(newPassword);
            }
        }
        this.utilisateurRepository.update(utilisateur);
        return utilisateur;
    }


}
