package Services;

import Entities.MembreEquipage;
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

    private MembreEquipageService membreEquipageService = new MembreEquipageService();

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


    public Vol findOneById(int id){
        return this.volRepository.findOneById(id);
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

    public ArrayList<Vol> findAllByMembreEquipage(int idMembre){
        try {
            if (this.membreEquipageService.findOneById(idMembre) != null) {
                return this.volRepository.findAllByMembreEquipage(idMembre);
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }
}
