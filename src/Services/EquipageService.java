package Services;

import Entities.*;
import Repository.EquipageRepository;

/**
 * Created by Nico on 10/12/2016.
 */
public class EquipageService {

    private EquipageRepository equipageRepository = new EquipageRepository();

    private MembreEquipageService membreEquipageService = new MembreEquipageService();

    private VolService volService = new VolService();

    public Equipage save(Equipage equipage){
        if(equipage != null) {
            return this.equipageRepository.save(equipage);
        } else {
            return null;
        }
    }

    public void addPNC(Integer idEquipage, Integer idPNC){
        if(idEquipage != null || idPNC != null){
            Equipage equipage = this.equipageRepository.findOneById(idEquipage);
            if (equipage != null && equipage.getPncs()!= null && !equipage.getPncs().isEmpty() ){
                boolean alreadyAdded = false;
                for(MembreEquipage membreEquipage : equipage.getPncs()){
                    if(membreEquipage.getId() == idPNC){
                        alreadyAdded = true;
                    }
                }
                if(!alreadyAdded){
                    this.equipageRepository.addPNC(idEquipage, idPNC);
                    try {
                        equipage.addPnc((PNC)this.membreEquipageService.findOneById(idPNC));
                    } catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                }
            } else {
                this.equipageRepository.addPNC(idEquipage, idPNC);
                try {
                    equipage.getPncs().add((PNC)this.membreEquipageService.findOneById(idPNC));
                } catch (Exception e){
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    public void removePNC(int idVolSelectione, int idPNC){
        Equipage equipage = this.volService.findOneById(idVolSelectione).getEquipage();
        this.equipageRepository.removePNC(equipage.getId(), idPNC);
    }

    public void updatePilote(Equipage equipage, int idPilote){
        try {
            Pilote pilote = (Pilote) membreEquipageService.findOneById(idPilote);
            equipage.addPilote(pilote);
            this.equipageRepository.update(equipage);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void updateCopilote(Equipage equipage, int idCopilote){
        try {
            Copilote copilote = (Copilote) membreEquipageService.findOneById(idCopilote);
            equipage.addCopilote(copilote);
            this.equipageRepository.update(equipage);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
