package Services;

import Entities.TypeAvion;
import Exceptions.EmptyFieldException;
import Exceptions.EntityNotFoundException;
import Repository.TypeAvionRepository;

import java.util.ArrayList;

/**
 * Service de la classe TypeAvion
 * Created by Nico on 30/11/2016.
 */
public class TypeAvionService implements ServiceInterface<TypeAvion>{

    /**
     * Repository de TypeAvion
     */
    private TypeAvionRepository typeAvionRepository = new TypeAvionRepository();

    /**
     * permet d'ajouter un TypeAvion en base
     * @param nom
     *      le nom du TypeAvion
     * @param nbPNCMin
     *      le nombre de PNC min du typeAvion
     * @param nbPNCMax
     *      le nombre de PNC max du typeAvion
     * @throws EmptyFieldException
     *      Si un des champs est vide
     */
    public TypeAvion addTypeAvion(String nom, Integer nbPNCMin, Integer nbPNCMax) throws EmptyFieldException{
        if(!nom.isEmpty() && nbPNCMax != null && nbPNCMin != null) {
            return this.save(new TypeAvion(nom, nbPNCMin, nbPNCMax));
        } else {
            throw new EmptyFieldException();
        }
    }

    @Override
    public TypeAvion save(TypeAvion typeAvion){
        return this.typeAvionRepository.save(typeAvion);
    }

    @Override
    public TypeAvion findOneById(Integer id) {
        if(id != null) {
            TypeAvion typeAvion = this.typeAvionRepository.findOneById(id);
            if(typeAvion != null){
                return typeAvion;
            }
        }
        return null;
    }

    @Override
    public ArrayList<TypeAvion> findAll(){
        return this.typeAvionRepository.findAll();
    }

    @Override
    public void delete(int id){
        if(id != -1){
            this.typeAvionRepository.delete(id);
        }
    }
}
