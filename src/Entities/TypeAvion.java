package Entities;

import java.io.Serializable;

/**
 * Entite Type avion
 * Created by Nico on 28/11/2016.
 */
public class TypeAvion implements Serializable {

    /**
     * id du type avion
     */
    private int id;

    /**
     * nom du type avion
     */
    private String nom;

    /**
     * nombre de pnc minimum
     */
    private int nbPNCmin;

    /**
     * nombre de pnc maximum
     */
    private int nbPNCmax;

    /**
     * constructeur
     * @param id
     *      l'id du type avion
     * @param nom
     *      le nom du type avion
     * @param nbPNCmin
     *      le nombre de pnc min
     * @param nbPNCmax
     *      le nombre de pnc max
     */
    public TypeAvion(int id, String nom, int nbPNCmin, int nbPNCmax) {
        this.id = id;
        this.nom = nom;
        this.nbPNCmin = nbPNCmin;
        this.nbPNCmax = nbPNCmax;
    }

    /**
     * constructeur sans id
     * @param nom
     *      le nom du type avion
     * @param nbPNCmin
     *      le nombre de pnc minimum
     * @param nbPNCmax
     *      le nombre de pnc maximum
     */
    public TypeAvion(String nom, int nbPNCmin, int nbPNCmax) {
        this.nom = nom;
        this.nbPNCmin = nbPNCmin;
        this.nbPNCmax = nbPNCmax;
    }

    /**
     * constructeur rien quavec le nom
     * @param nom
     */
    public TypeAvion(String nom) {
        this.nom = nom;
    }

    /**
     * getter de nom
     * @return
     *      le nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * le getter de l'id
     * @return
     *      l'id
     */
    public int getId() {
        return id;
    }

    /**
     * getter de pnc min
     * @return
     *      le nombre de pnc minimal
     */
    public int getNbPNCmin() {
        return nbPNCmin;
    }

    /**
     * le nombre de pnc maximal
     * @return
     *      le nombre de pnc max
     */
    public int getNbPNCmax() {
        return nbPNCmax;
    }
}
