package Services;

import Entities.Equipage;
import Repository.EquipageRepository;

/**
 * Created by Nico on 10/12/2016.
 */
public class EquipageService {

    private EquipageRepository equipageRepository = new EquipageRepository();

    public Equipage save(Equipage equipage){
        if(equipage != null) {
            return this.equipageRepository.save(equipage);
        } else {
            return null;
        }
    }
}
