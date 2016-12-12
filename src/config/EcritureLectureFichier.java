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

    private String nomFichier = "listeVols.txt";
    private VolService volService = new VolService();
    private Utilisateur utilisateurCourant;

    public EcritureLectureFichier(Utilisateur utilisateur){

        this.utilisateurCourant = utilisateur;

        try {
            FileOutputStream fos = new FileOutputStream(this.nomFichier);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

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
                    String copilote = ", Copilote : " + vol.getEquipage().getCopilote().getNom() + ".";
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
