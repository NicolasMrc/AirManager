package Entities;

/**
 * Entite Avion
 * Created by Nico on 28/11/2016.
 */
public class Avion {
    /**
     * l'id
     */
    private int id;

    /**
     * le Type d'avion
     */
    private TypeAvion typeAvion;

    /**
     * la reference de l'avion
     */
    private String ref;

    /**
     * constructeur
     * @param typeAvion
     *      le type d'avion
     * @param ref
     *      la reference de l'avion
     */
    public Avion(TypeAvion typeAvion, String ref){
        this.typeAvion = typeAvion;
        this.ref = ref;
    }

    /**
     * constructeur avec id
     * @param id
     *      l'id de l'avion
     * @param typeAvion
     *      le type d'avion
     * @param ref
     *      la refenrece de l'avion
     */
    public Avion(int id, TypeAvion typeAvion, String ref) {
        this.id = id;
        this.typeAvion = typeAvion;
        this.ref = ref;
    }

    /**
     * getter
     * @return
     *      le type de l'avion
     */
    public TypeAvion getTypeAvion() {
        return typeAvion;
    }

    /**
     * getter de la refenrece de l'avion
     * @return
     *      la reference de l'avion
     */
    public String getRef() {
        return ref;
    }

    /**
     * getter de l'id
     * @return
     *      l'id de l'avion
     */
    public int getId() {
        return id;
    }
}
