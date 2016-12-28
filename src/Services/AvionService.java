package Services;

import Entities.Avion;
import Entities.TypeAvion;
import Repository.AvionRepository;

import java.util.ArrayList;

/**
 * Service de la classe Avion
 * Created by Nico on 30/11/2016.
 */
public class AvionService implements ServiceInterface<Avion>{

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

    @Override
    public Avion save(Avion avion){
        return this.avionRepository.save(avion);
    }

    @Override
    public void delete(int id){
        if(id != -1){
            this.avionRepository.delete(id);
        }
    }

    @Override
    public ArrayList<Avion> findAll(){
        return this.avionRepository.findAll();
    }

    @Override
    public Avion findOneById(Integer id){
        return this.avionRepository.findOneById(id);
    }
}
