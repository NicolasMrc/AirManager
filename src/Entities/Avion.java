package Entities;

/**
 * Created by Nico on 28/11/2016.
 */
public class Avion {
    private int id;
    private TypeAvion typeAvion;
    private String ref;

    public Avion(TypeAvion typeAvion, String ref){
        this.typeAvion = typeAvion;
        this.ref = ref;
    }

    public Avion(int id, TypeAvion typeAvion, String ref) {
        this.id = id;
        this.typeAvion = typeAvion;
        this.ref = ref;
    }

    public TypeAvion getTypeAvion() {
        return typeAvion;
    }

    public String getRef() {
        return ref;
    }

    public int getId() {
        return id;
    }
}
