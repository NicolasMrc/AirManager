package Services;

import Entities.Aeroport;
import Repository.AeroportRepository;

import java.util.ArrayList;

/**
 * Created by Nico on 12/12/2016.
 */
public class AeroportService {

    private AeroportRepository aeroportRepository = new AeroportRepository();

    public Aeroport findOneById(int id){
        return this.aeroportRepository.findOneById(id);
    }

    public ArrayList<Aeroport> findAll(){
        return this.aeroportRepository.findAll();
    }

    public Aeroport save(Aeroport aeroport){
        return this.aeroportRepository.save(aeroport);
    }
}
