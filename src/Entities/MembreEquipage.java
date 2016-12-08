package Entities;

import Exceptions.QualificationException;

import java.util.ArrayList;
import Enum.TypeMembreEquipage;

/**
 * Created by Nico on 28/11/2016.
 */
public class MembreEquipage {
    private int id;
    private String prenom;
    private String nom;
    private ArrayList<TypeAvion> qualifications;
    private TypeMembreEquipage metier;

    public MembreEquipage(int id, String nom, String prenom, ArrayList<TypeAvion> qualifications) {
        this.qualifications = qualifications;
        this.id = id;
        this.prenom = prenom;
        this.nom = nom;
    }

    public MembreEquipage(String prenom, String nom, ArrayList<TypeAvion> qualifications) {
        this.prenom = prenom;
        this.nom = nom;
        this.qualifications = qualifications;
    }

    public boolean peutVoler(TypeAvion typeAvion){
        for(TypeAvion type : this.qualifications){
            if (type.getNom().equals(typeAvion.getNom())){
                return true;
            }
        }
        return false;
    }

    public Boolean addQualification(TypeAvion type) throws QualificationException {
        if (this.qualifications.size() >= 2) {
            throw new QualificationException("Le membre d'équipage à déjà 2 type d'avion associés");
        } else {
            for(TypeAvion qualification : this.qualifications){
                if(qualification.getNom().equals(type.getNom())){
                    throw new QualificationException("Le membre est déjà qualifié pour ce type d'avion");
                }
            }
            this.qualifications.add(type);
            return true;
        }
    }

    public boolean delQualification(TypeAvion type, boolean fromType) throws QualificationException{
        if (this.qualifications.size() == 0) {
            throw new QualificationException("Le membre d'équipage n'est qualifié pour aucun type d'avion");
        } else {
            for(TypeAvion qualification : this.qualifications){
                if(qualification.getNom().equals(type.getNom())){
                    this.qualifications.remove(qualification);
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

    public ArrayList<TypeAvion> getQualifications() {
        return qualifications;
    }

    public TypeMembreEquipage getMetier() {
        return metier;
    }

    public void setMetier(TypeMembreEquipage metier) {
        this.metier = metier;
    }

    public void setId(int id) {
        this.id = id;
    }
}
