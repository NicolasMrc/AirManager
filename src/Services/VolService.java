package Services;

import Entities.Vol;
import Repository.VolRepository;

import java.util.ArrayList;

/**
 * Created by Nico on 10/12/2016.
 */
public class VolService {

    /**
     * Repository de la classe Avion
     */
    private VolRepository volRepository = new VolRepository();

    /**
     * sauvegarde le vol en base de donnée
     * @param vol
     *      le vol a sauver
     * @return
     *      le vol
     */
    public Vol save(Vol vol){
        if(vol != null) {
            return this.volRepository.save(vol);
        } else {
            return null;
        }
    }

    /**
     * renvoi tout les vol sauvegarder en base de donnée
     * @return
     *      la liste de tout les vols
     */
    public ArrayList<Vol> findAll(){
        return this.volRepository.findAll();
    }

    /**
     * supprime un vol en base de donnée
     * @param id
     *  l'id du vol a supprimer
     *
     */
    public void delete(int id){
        this.volRepository.delete(id);
    }
}
