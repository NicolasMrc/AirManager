package Entities;

import Exceptions.QualificationException;

import java.util.ArrayList;

/**
 * Created by Nico on 28/11/2016.
 */
public class MembreEquipage {
    private int id;
    private String prenom;
    private String nom;
    private ArrayList<TypeAvion> typeAvion;

    public MembreEquipage(int id, String nom, String prenom) {
        this.id = id;
        this.prenom = prenom;
        this.nom = nom;
        this.typeAvion = new ArrayList<>();
    }

    public boolean peutVoler(TypeAvion typeAvion){
        for(TypeAvion type : this.typeAvion){
            if (type.getNom().equals(typeAvion.getNom())){
                return true;
            }
        }
        return false;
    }

    public Boolean addQualification(TypeAvion type) throws QualificationException {
        if (this.typeAvion.size() >= 2) {
            throw new QualificationException("Le membre d'équipage à déjà 2 type d'avion associés");
        } else {
            for(TypeAvion typeAvion : this.typeAvion){
                if(typeAvion.getNom().equals(type.getNom())){
                    throw new QualificationException("Le membre est déjà qualifié pour ce type d'avion");
                }
            }
            this.typeAvion.add(type);
            return true;
        }
    }

    public boolean delQualification(TypeAvion type, boolean fromType) throws QualificationException{
        if (this.typeAvion.size() == 0) {
            throw new QualificationException("Le membre d'équipage n'est qualifié pour aucun type d'avion");
        } else {
            for(TypeAvion typeAvion : this.typeAvion){
                if(typeAvion.getNom().equals(type.getNom())){
                    this.typeAvion.remove(typeAvion);
                }
            }
            throw new QualificationException("Le membre d'équipage est déjà qualifié pour ce type d'avion");
        }
    }

    public String getPrenom() {
        return prenom;
    }

    public String getNom() {
        return nom;
    }

    public int getId() {
        return id;
    }

    public String toString(){
        return "Id : " + this.id + " - Nom : " + this.nom + " - Prenom : " + this.prenom;
    }

    public String getMetier(){
        return "";
    }

    public ArrayList<TypeAvion> getTypeAvion() {
        return typeAvion;
    }
}
