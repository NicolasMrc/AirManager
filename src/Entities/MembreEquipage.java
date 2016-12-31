package Entities;

import Exceptions.QualificationException;

import java.io.Serializable;
import java.util.ArrayList;
import Enum.TypeMembreEquipage;

/**
 * Classe abstraite de mempre d'equipage
 * Created by Nico on 28/11/2016.
 */
public abstract class MembreEquipage implements Serializable {

    /**
     * l'id du membre d'equipage
     */
    private int id;

    /**
     * le prenom du membre d'equipage
     */
    private String prenom;

    /**
     * le nom du membre d'équipage
     */
    private String nom;

    /**
     * les qualifications du membre d'equipage
     */
    private ArrayList<TypeAvion> qualifications;

    /**
     * le metier du membre d'equipage
     */
    private TypeMembreEquipage metier;


    /**
     * constructeur avec id du membre d'equipage
     * @param id
     *      l'id du membre d'equipage
     * @param nom
     *      le nom du membre d'equipage
     * @param prenom
     *      le prenom du membre d'equipage
     * @param qualifications
     *      les qualifications du membre d'equipage
     */
    public MembreEquipage(int id, String nom, String prenom, ArrayList<TypeAvion> qualifications) {
        this.qualifications = qualifications;
        this.id = id;
        this.prenom = prenom;
        this.nom = nom;
    }

    /**
     * constructeur sans id
     * @param prenom
     *      le prenom du membre d'equipage
     * @param nom
     *      le nom du membre d'equipage
     * @param qualifications
     *      les qualifications du membre d'equipage
     */
    public MembreEquipage(String prenom, String nom, ArrayList<TypeAvion> qualifications) {
        this.prenom = prenom;
        this.nom = nom;
        this.qualifications = qualifications;
    }

    /**
     * verifie si le membre d'equipage est qualificer pour le type d'avion passé en parametre
     * @param typeAvion
     *      le type d'avion
     * @return
     *      true si il peut voler
     */
    public boolean peutVoler(TypeAvion typeAvion){
        for(TypeAvion type : this.qualifications){
            if (type.getNom().equals(typeAvion.getNom())){
                return true;
            }
        }
        return false;
    }

    /**
     * permet d'ajouter une qualification a l'utilisateur
     * @param type
     *      la qualification a ajouter
     * @return
     *      true si l'ajout est bien effectué
     * @throws QualificationException
     *      si jamais le membre est deja qualifié pour ce type
     */
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

    //TODO : utiliser toute ces methodes

    /**
     * permet de supprimer une qualification
     * @param type
     *      la qualification a supprimer
     * @return
     *      true si la suppression s'est bien effectué
     * @throws QualificationException
     *      si le membre d'equipage n'est pas qualifié pour ce type d'avion
     *
     */
    public boolean delQualification(TypeAvion type) throws QualificationException{
        if (this.qualifications.size() == 0) {
            throw new QualificationException("Le membre d'équipage n'est qualifié pour aucun type d'avion");
        } else {
            for(TypeAvion qualification : this.qualifications){
                if(qualification.getNom().equals(type.getNom())){
                    this.qualifications.remove(qualification);
                }
            }
            return true;
        }
    }

    /**
     * getter du prenom
     * @return
     *      le prenom
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * getter du nom
     * @return
     *      le nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * getter de l'id
     * @return
     *      l'id
     */
    public int getId() {
        return id;
    }

    /**
     * permet de renvoyer la classe sous forme de string
     * @return
     *      la classe sous forme de string
     */
    public String toString(){
        return "Id : " + this.id + " - Nom : " + this.nom + " - Prenom : " + this.prenom;
    }

    /**
     * getter des qualifications
     * @return
     *      la liste des qualifications
     */
    public ArrayList<TypeAvion> getQualifications() {
        return qualifications;
    }

    /**
     * getter du metier
     * @return
     *      le metier du membre d'equipage
     */
    public TypeMembreEquipage getMetier() {
        return metier;
    }

    /**
     * le setter du metier
     * @param metier
     *      le metier
     */
    public void setMetier(TypeMembreEquipage metier) {
        this.metier = metier;
    }

    /**
     * le setter de l'id
     * @param id
     *      l'id a setter
     */
    public void setId(int id) {
        this.id = id;
    }
}
