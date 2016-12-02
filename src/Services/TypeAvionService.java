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
public class TypeAvionService {

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
            return this.typeAvionRepository.save(new TypeAvion(nom, nbPNCMin, nbPNCMax));
        } else {
            throw new EmptyFieldException();
        }
    }

    /**
     * permet de trouver un typeAvion a partir d'un id
     * @param id
     *      l'id du typeAvion
     * @return
     *      Le typeAvion
     * @throws EmptyFieldException
     *      Si l'id est vide
     * @throws EntityNotFoundException
     *      Si l'id ne correspond aucun TypeAvion
     */
    public TypeAvion findOneById(Integer id) throws EmptyFieldException, EntityNotFoundException{
        if(id != null) {
            TypeAvion typeAvion = this.typeAvionRepository.findOneById(id);
            if(typeAvion != null){
                return typeAvion;
            } else {
                throw new EntityNotFoundException();
            }
        } else {
            throw new EmptyFieldException();
        }
    }

    /**
     * permet de trouver tout les typeAvion
     * @return
     *      la liste des TypeAvion
     */
    public ArrayList<TypeAvion> findAll(){
        return this.typeAvionRepository.findAll();
    }

    /**
     * permet de supprimer un type d'avion a partir d'un id
     * @param id
     *      l'id du type avion a supprimer
     */
    public void delete(int id){
        if(id != -1){
            this.typeAvionRepository.delete(id);
        }
    }
}
