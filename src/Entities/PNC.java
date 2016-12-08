package Entities;
import Enum.TypeMembreEquipage;

import java.util.ArrayList;

/**
 * Created by Nico on 28/11/2016.
 */
public class PNC extends MembreEquipage {
    public PNC(int id, String nom, String prenom, ArrayList<TypeAvion> qualifications) {
        super(id, nom, prenom, qualifications);
        super.setMetier(TypeMembreEquipage.PNC);
    }

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
