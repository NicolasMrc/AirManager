package Entities;

/**
 * Created by Nico on 28/11/2016.
 */
public class Copilote extends MembreEquipage {
    public Copilote(Long id, String prenom, String nom) {
        super(id, prenom, nom);
    }

    @Override
    public String toString(){
        return super.toString() + " Metier : Copilote";
    }
}
