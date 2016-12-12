package config;
import Entities.Utilisateur;
import Entities.Vol;
import Services.VolService;
import java.io.*;
import java.util.ArrayList;

/**
 * Created by Florian on 12/12/2016.
 */
public class EcritureLectureFichier {

    private String nomFichier = "listeVols";
    private VolService volService = new VolService();
    private Utilisateur utilisateurCourant;

    /**
     * constructeur qui va créer un fichier avec les vols de l'utilisateur courant
     * @param utilisateur : c'est l'utilisateur courant dont on doit mettre les vols dans le fichier
     */
    public EcritureLectureFichier(Utilisateur utilisateur){

        //on initialise le fichier avec le nom de l'utilisateur courant
        this.utilisateurCourant = utilisateur;
        this.nomFichier = this.nomFichier + "_" + this.utilisateurCourant.getUsername() + ".txt";

        try {
            FileOutputStream fos = new FileOutputStream(this.nomFichier);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            //on récupère la liste des vols de l'utilisateur courant
            ArrayList<Vol> vols = this.volService.findAllByMembreEquipage(this.utilisateurCourant.getIdMembre());

            //si la liste de vol n'est pas nule on ajoute tous les vols dans le fichier
            if(vols != null){
                for(Vol vol: vols) {
                    //on récupère les données du vols
                    String numero = "Numéro de vol : " + vol.getNumero();
                    String nomSite = ", Nom site : " + vol.getSite().getNom();
                    String nomDestination = ", Nom destination : " + vol.getDestination().getNom();
                    String date = ", Date : " + vol.getDate();
                    String refAvion = ", Ref avion : " + vol.getAvion().getRef();
                    String pilote =  ", Pilote : " + vol.getEquipage().getPilote().getNom();
                    String copilote = ", Copilote : " + vol.getEquipage().getCopilote().getNom() + ". \n";
                    //on concatène les strings
                    Object[] objs = {numero, nomSite, nomDestination, date, refAvion, pilote, copilote};
                    //on ajoute une ligne dans le fichier
                    oos.writeObject(objs);
                    //on vide le tampon
                    oos.flush();
                }
            }

            //on termine
            oos.close();


        }
        catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }

    public String getNomFichier() {
        return nomFichier;
    }
}
