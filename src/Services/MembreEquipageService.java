package Services;

import Entities.MembreEquipage;
import Enum.TypeMembreEquipage;
import Repository.MembreEquipageRepository;

import java.util.ArrayList;

/**
 * Created by Nico on 30/11/2016.
 */
public class MembreEquipageService {

    private MembreEquipageRepository membreEquipageRepository = new MembreEquipageRepository();

    public void addMembreEquipage(String nom, String prenom, TypeMembreEquipage typeMembreEquipage){
        if(!prenom.isEmpty() && !nom.isEmpty() && typeMembreEquipage != null ){
            this.membreEquipageRepository.save(nom, prenom, typeMembreEquipage);
        }
    }

    public ArrayList<MembreEquipage> getMembres(){
        return this.membreEquipageRepository.findAllMembres();
    }

    public void delete(int id){
        this.membreEquipageRepository.delete(id);
    }
}
