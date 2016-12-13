package Entities;
import Enum.TypeMembreEquipage;

import java.util.ArrayList;

/**
 * Entite pnc
 * Created by Nico on 28/11/2016.
 */
public class PNC extends MembreEquipage {

    /**
     * constructeur
     * @param id
     *      id du pnc
     * @param nom
     *      le nom du pnc
     * @param prenom
     *      le prenom du pnc
     * @param qualifications
     *      la liste de qualification du pnc
     */
    public PNC(int id, String nom, String prenom, ArrayList<TypeAvion> qualifications) {
        super(id, nom, prenom, qualifications);
        super.setMetier(TypeMembreEquipage.PNC);
    }

    /**
     * constructeur sans id
     * @param nom
     *      le nom du pnc
     * @param prenom
     *      le prenom du pnc
     * @param qualifications
     *      la liste de qualification du pnc
     */
    public PNC(String nom, String prenom, ArrayList<TypeAvion> qualifications) {
        super(nom, prenom, qualifications);
        super.setMetier(TypeMembreEquipage.PNC);
    }


    @Override
    public String toString(){
        return super.toString() + " Metier : PNC";
    }

    @Override
    public TypeMembreEquipage getMetier(){
        return TypeMembreEquipage.PNC;
    }
}
