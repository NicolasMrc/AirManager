package Services;

import Entities.Vol;
import Repository.VolRepository;

import java.util.ArrayList;

/**
 * Service de vol
 * Created by Nico on 10/12/2016.
 */
public class VolService implements ServiceInterface<Vol>{

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

    @Override
    public ArrayList<Vol> findAll(){
        return this.volRepository.findAll();
    }

    @Override
    public Vol findOneById(Integer id){
        return this.volRepository.findOneById(id);
    }

    @Override
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
