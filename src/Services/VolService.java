package Services;

import Entities.Vol;
import Repository.VolRepository;

/**
 * Created by Nico on 10/12/2016.
 */
public class VolService {

        /**
         * Repository de la classe Avion
         */
        private VolRepository volRepository = new VolRepository();

        public Vol save(Vol vol){
            return this.volRepository.save(vol);
        }
}
