package Entities;

import Enum.TypeMembreEquipage;

import java.util.ArrayList;

/**
 * Created by Nico on 28/11/2016.
 */
public class Pilote extends MembreEquipage {
    public Pilote(int id, String nom, String prenom, ArrayList<TypeAvion> qualifications) {
        super(id, nom, prenom, qualifications);
        super.setMetier(TypeMembreEquipage.PILOTE);
    }

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
