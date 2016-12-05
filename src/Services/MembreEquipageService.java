package Services;

import Entities.MembreEquipage;
import Enum.TypeMembreEquipage;
import Exceptions.EmptyFieldException;
import Repository.MembreEquipageRepository;

import java.util.ArrayList;

/**
 * Created by Nico on 30/11/2016.
 */
public class MembreEquipageService {

    private MembreEquipageRepository membreEquipageRepository = new MembreEquipageRepository();

    private TypeAvionService typeAvionService = new TypeAvionService();

    public void addMembreEquipage(String nom, String prenom, TypeMembreEquipage typeMembreEquipage){
        if(!prenom.isEmpty() && !nom.isEmpty() && typeMembreEquipage != null ){
            this.membreEquipageRepository.save(nom, prenom, typeMembreEquipage);
        }
    }

    public ArrayList<MembreEquipage> getMembres(){
        return this.membreEquipageRepository.findAllMembres();
    }

    public MembreEquipage findOneById(Integer id) throws EmptyFieldException{
        if(id != null){
            return this.membreEquipageRepository.findOneById(id);
        } else throw new EmptyFieldException();
    }

    public void delete(int id){
        this.membreEquipageRepository.delete(id);
    }

    public void qualification(MembreEquipage membreEquipage, Integer idQualification1, Integer idQualification2){
        if (membreEquipage != null){
            if(idQualification1 != null && idQualification2 != null) {
                try {
                    membreEquipage.getTypeAvion().add(0, this.typeAvionService.findOneById(idQualification1));
                    membreEquipage.getTypeAvion().add(1, this.typeAvionService.findOneById(idQualification2));
                } catch (Exception e){
                    System.out.println(e.getMessage());
                }
            }
        }
    }
}
