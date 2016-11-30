package Entities;

/**
 * Created by Nico on 28/11/2016.
 */
public class Pilote extends MembreEquipage {
    public Pilote(Long id, String prenom, String nom) {
        super(id, prenom, nom);
    }

    @Override
    public String toString(){
        return super.toString() + " Metier : Pilote";
    }
}
