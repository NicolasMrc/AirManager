package Entities;

import Enum.TypeMembreEquipage;

import java.util.ArrayList;

/**
 * Classe pilote
 * Created by Nico on 28/11/2016.
 */
public class Pilote extends MembreEquipage {

    /**
     * constructeur du pilote
     * utilise le constructeur de la classe parent
     * specifie le metier
     * @param id
     *      l'id du pilote
     * @param nom
     *      le nom du pilote
     * @param prenom
     *      le prenom du pilote
     * @param qualifications
     *      les qualifications du pilote
     */
    public Pilote(int id, String nom, String prenom, ArrayList<TypeAvion> qualifications) {
        super(id, nom, prenom, qualifications);
        super.setMetier(TypeMembreEquipage.PILOTE);
    }

    /**
     * constructeur sans id
     * @param nom
     *      le nom du pilote
     * @param prenom
     *      le prenom du pilote
     * @param qualifications
     *      la liste de qualification du pilote
     */
    public Pilote(String nom, String prenom, ArrayList<TypeAvion> qualifications) {
        super(nom, prenom, qualifications);
        super.setMetier(TypeMembreEquipage.PILOTE);
    }

    @Override
    public String toString(){
        return super.toString() + " Metier : Pilote";
    }

    @Override
    public TypeMembreEquipage getMetier(){
        return TypeMembreEquipage.PILOTE;
    }
}
