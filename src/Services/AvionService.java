package Services;

import Entities.TypeAvion;
import Repository.AvionRepository;

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
    public void addAvion(TypeAvion typeAvion, String ref){
        if(typeAvion != null && !ref.isEmpty()) {
            this.avionRepository.save(typeAvion, ref);
        }
    }
}
