package Services;

import Entities.Avion;
import Entities.TypeAvion;
import Repository.AvionRepository;

import java.util.ArrayList;

/**
 * Service de la classe Avion
 * Created by Nico on 30/11/2016.
 */
public class AvionService {

    /**
     * Repository de la classe Avion
     */
    private AvionRepository avionRepository = new AvionRepository();

    /**
     * permet d'ajouter un avion en base
     * @param typeAvion
     *      le type de lavion
     * @param ref
     *      la reference de l'avion
     */
    public Avion addAvion(TypeAvion typeAvion, String ref){
        if(typeAvion != null && !ref.isEmpty()) {
            return this.avionRepository.save(new Avion(typeAvion, ref));
        } else {
            return null;
        }
    }

    /**
     * permet de supprimer un avion a partir d'un id
     * @param id
     *      l'id de l'avion a supprimer
     */
    public void delete(int id){
        if(id != -1){
            this.avionRepository.delete(id);
        }
    }

    public ArrayList<Avion> findAll(){
        return this.avionRepository.findAll();
    }
}
