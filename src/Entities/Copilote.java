package Entities;

/**
 * Created by Nico on 28/11/2016.
 */
public class Copilote extends MembreEquipage {
    public Copilote(int id, String nom, String prenom) {
        super(id, nom, prenom);
    }

    @Override
    public String toString(){
        return super.toString() + " Metier : Copilote";
    }

    @Override
    public String getMetier(){
        return "Copilote";
    }
}
