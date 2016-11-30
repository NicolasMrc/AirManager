package Services;

import Entities.MembreEquipage;
import Enum.TypeMembreEquipage;
import Repository.MembreEquipageRepository;

/**
 * Created by Nico on 30/11/2016.
 */
public class MembreEquipageService {
    private MembreEquipageRepository membreEquipageRepository = new MembreEquipageRepository();

    public void addMembreEquipage(String nom, String prenom, TypeMembreEquipage typeMembreEquipage){
        System.out.println("nom : " + nom + " prenom : " + prenom + " metier : " + typeMembreEquipage);
        if(!prenom.isEmpty() && !nom.isEmpty() && typeMembreEquipage != null ){
            this.membreEquipageRepository.addMembre(nom, prenom, typeMembreEquipage);
        }
    }
}
