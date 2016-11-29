package Entities;

/**
 * Created by Nico on 28/11/2016.
 */
public class TypeAvion {
    private String nom;
    private int nbPNCmin;
    private int nbPNCmax;

    public TypeAvion(String nom, int nbPNCmin, int nbPNCmax) {
        this.nom = nom;
        this.nbPNCmin = nbPNCmin;
        this.nbPNCmax = nbPNCmax;
    }

    public TypeAvion(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }
}
