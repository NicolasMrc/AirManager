package Entities;

/**
 * Created by Nico on 28/11/2016.
 */
public class Pilote extends MembreEquipage {
    public Pilote(int id, String nom, String prenom) {
        super(id, nom, prenom);
    }

    @Override
    public String toString(){
        return super.toString() + " Metier : Pilote";
    }

    @Override
    public String getMetier(){
        return "Pilote";
    }
}
