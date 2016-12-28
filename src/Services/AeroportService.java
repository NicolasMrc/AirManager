package Services;

import Entities.Aeroport;
import Repository.AeroportRepository;

import java.util.ArrayList;

/**
 * Service d'aeroport
 * Created by Nico on 12/12/2016.
 */
public class AeroportService implements ServiceInterface<Aeroport> {

    /**
     * le repository de l'aeroport
     */
    private AeroportRepository aeroportRepository = new AeroportRepository();

    @Override
    public Aeroport findOneById(Integer id){
        return this.aeroportRepository.findOneById(id);
    }

    @Override
    public ArrayList<Aeroport> findAll(){
        return this.aeroportRepository.findAll();
    }

    @Override
    public void delete(int id){
        //TODO this.aeroportRepository.delete(id);
    }

    @Override
    public Aeroport save(Aeroport aeroport){
        return this.aeroportRepository.save(aeroport);
    }
}
