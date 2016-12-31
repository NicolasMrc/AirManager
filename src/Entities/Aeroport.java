package Entities;

import java.io.Serializable;

/**
 * Entite Aeroport
 * Created by Nico on 12/12/2016.
 */
public class Aeroport implements Serializable {

    /**
     * id de l'aeroport
     */
    private int id;

    /**
     * nom de l'aeroport
     */
    private String nom;

    /**
     * code de l'aeroport
     */
    private String code;

    /**
     * constructeur d'aeroport
     * @param nom
     *      nom de l'aeroport
     * @param code
     *      code de l'aeroport
     */
    public Aeroport(String nom, String code) {
        this.nom = nom;
        this.code = code;
    }

    /**
     * constructeur avec id
     * @param id
     *      l'id de laeroport
     * @param nom
     *      nom de l'aeroport
     * @param code
     *      code de l'aeroport
     */
    public Aeroport(int id, String nom, String code) {
        this.id = id;
        this.nom = nom;
        this.code = code;
    }

    /**
     * getter du nom de l'aeroport
     * @return
     *      le nom de laeroport
     */
    public String getNom() {
        return nom;
    }

    /**
     * setter du nom
     * @param nom
     *      le nom de l'aeroport
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * getter du code
     * @return
     *      le code IATA de l'aeroport
     */
    public String getCode() {
        return code;
    }

    /**
     * setter du code
     * @param code
     *      le code IATA de laeroport
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * getter de l'id de l'aeroport
     * @return
     */
    public int getId() {
        return id;
    }
}
