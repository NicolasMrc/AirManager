package Entities;

/**
 * Created by Nico on 12/12/2016.
 */
public class Aeroport {
    private int id;
    private String nom;
    private String code;

    public Aeroport(String nom, String code) {
        this.nom = nom;
        this.code = code;
    }

    public Aeroport(int id, String nom, String code) {
        this.id = id;
        this.nom = nom;
        this.code = code;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getId() {
        return id;
    }
}
