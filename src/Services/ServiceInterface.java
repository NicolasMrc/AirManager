package Services;

import java.util.ArrayList;

/**
 * Interface de service
 * Created by Nico on 27/12/2016.
 */
public interface ServiceInterface<T> {

    /**
     * permet de trouver une entité en base a partir de son id
     * @param id
     *      l'id de l'entité a trouvé
     * @return
     *      l'entité
     */
    T findOneById(Integer id);

    /**
     * permet de trouver toutes les entrée d'une table en base
     * @return
     *      la liste des entités
     */
    ArrayList<T> findAll();

    /**
     * supprime ue entité en base
     * @param id
     *      l'id de l'entite a supprimer
     */
    void delete(int id);

    /**
     * permet de sauvegarder une entite en base
     * @param t
     *      l'entité a sauvegarder
     * @return
     *      l'entité sauvegardée
     */
    T save(T t);
}
