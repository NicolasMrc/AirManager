package Services;

import Entities.*;
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
            ArrayList<TypeAvion> qualifications = new ArrayList<>();
            if (typeMembreEquipage.equals(TypeMembreEquipage.PILOTE)){
                Pilote pilote = new Pilote(prenom, nom, qualifications);
                this.membreEquipageRepository.save(pilote);
            } else if (typeMembreEquipage.equals(TypeMembreEquipage.COPILOTE)){
                Copilote copilote = new Copilote(prenom, nom, qualifications);
                this.membreEquipageRepository.save(copilote);
            } else if (typeMembreEquipage.equals(TypeMembreEquipage.PNC)){
                PNC pnc = new PNC(prenom, nom, qualifications);
                this.membreEquipageRepository.save(pnc);
            }
            //TODO creer un utilisateur avec l'id du membre
        }
    }

    public ArrayList<MembreEquipage> findAll(){
        return this.membreEquipageRepository.findAllMembres();
    }

    public MembreEquipage findOneById(Integer id) throws EmptyFieldException{
        if(id != null){
            return this.membreEquipageRepository.findOneById(id);
        } else throw new EmptyFieldException();
    }

    public void delete(int id){
        this.membreEquipageRepository.deleteMembre(id);
    }

    public MembreEquipage qualification(MembreEquipage membreEquipage, Integer idQualification1, Integer idQualification2){
        if (membreEquipage != null){
            if(idQualification1 != null && idQualification2 != null) {
                try {
                    membreEquipage.getQualifications().add(0, this.typeAvionService.findOneById(idQualification1));
                    membreEquipage.getQualifications().add(1, this.typeAvionService.findOneById(idQualification2));
                    return this.membreEquipageRepository.updateQualifications(membreEquipage);
                } catch (Exception e){
                    System.out.println(e.getMessage());
                }
            }
        }
        return null;
    }

    public ArrayList<MembreEquipage> findAllByMetierAndQualification(TypeMembreEquipage typeMembreEquipage, TypeAvion typeAvion){
        if(typeMembreEquipage != null && typeAvion != null) {
            return this.membreEquipageRepository.findAllByMetierAndQualification(typeMembreEquipage, typeAvion);
        } else {
            return null;
        }
    }
}
