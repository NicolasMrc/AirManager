package Entities;

/**
 * Created by Nico on 28/11/2016.
 */
public class PNC extends MembreEquipage {
    public PNC(int id, String nom, String prenom) {
        super(id, nom, prenom);
    }

    @Override
    public String toString(){
        return super.toString() + " Metier : PNC";
    }

    @Override
    public String getMetier(){
        return "PNC";
    }
}
