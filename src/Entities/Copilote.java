package Entities;
import Enum.TypeMembreEquipage;

import java.util.ArrayList;

/**
 * Entite de copilote
 * Created by Nico on 28/11/2016.
 */
public class Copilote extends MembreEquipage {
    public Copilote(int id, String nom, String prenom, ArrayList<TypeAvion> qualifications) {
        super(id, nom, prenom, qualifications);
        super.setMetier(TypeMembreEquipage.COPILOTE);
    }

    public Copilote(String nom, String prenom, ArrayList<TypeAvion> qualifications) {
        super(nom, prenom, qualifications);
        super.setMetier(TypeMembreEquipage.COPILOTE);
    }

    @Override
    public String toString(){
        return super.toString() + " Metier : Copilote";
    }

    @Override
    public TypeMembreEquipage getMetier(){
        return TypeMembreEquipage.COPILOTE;
    }


}
