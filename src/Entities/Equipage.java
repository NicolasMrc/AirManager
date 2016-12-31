package Entities;

import Exceptions.EquipageException;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Entite Equipage
 * Created by Nico on 28/11/2016.
 */
public class Equipage implements Serializable {

    /**
     * l'id
     */
    private int id;

    /**
     * boolean indiquant si l'equipage est au minimum ou pas
     */
    private boolean atMin;

    /**
     * indique si l'equipage a atteind sa capacité maximum
     */
    private boolean atMax;

    /**
     * le pilote de l'equipage
     */
    private Pilote pilote;

    /**
     * le copilote de l'equipage
     */
    private Copilote copilote;

    /**
     * la liste de pnc de l'equipage
     */
    private ArrayList<PNC> pncs;

    /**
     * constructeur
     * @param id
     *      l'id de l'equipage
     * @param pilote
     *      le pilote
     * @param copilote
     *      le copilote
     * @param pncs
     *      la liste des pncs
     */
    public Equipage(int id, Pilote pilote, Copilote copilote, ArrayList<PNC> pncs) {
        this.id = id;
        this.pilote = pilote;
        this.copilote = copilote;
        this.pncs = pncs;
    }

    /**
     * constructeur sans id
     * @param pilote
     *      le pilote
     * @param copilote
     *      le copilote
     * @param pncs
     *      la liste des pncs
     */
    public Equipage(Pilote pilote, Copilote copilote, ArrayList<PNC> pncs) {
        this.pilote = pilote;
        this.copilote = copilote;
        this.pncs = pncs;
    }

    /**
     * permet d'ajouter un pilote a l'equipage
     * @param pilote
     *      le pilote a ajouter
     * @throws EquipageException
     *      si le pilote est deja renseigné
     */
    public void addPilote(Pilote pilote) throws EquipageException {
        if(this.pilote == null) {
            this.pilote = pilote;
        } else {
            throw new EquipageException("Le pilote est déjà renseigné");
        }
    }

    /**
     * remplace le pilote
     * @param pilote
     *      le pilote a ajouter
     */
    public void replacePilote(Pilote pilote){
        this.pilote = pilote;
    }

    /**
     * permet d'ajouter un copilote a l'equipage
     * @param copilote
     *      le copilote a ajouter
     * @throws EquipageException
     *      si le copilote est deja renseigné
     */
    public void addCopilote(Copilote copilote) throws EquipageException{
        if (this.copilote == null) {
            this.copilote = copilote;
        } else {
            throw new EquipageException("Le copilaote est déjà renseigné");
        }
    }

    /**
     * permet d'ajouter un pnc a l'equipage
     * @param pnc
     *      le pnc a ajouter
     * @throws EquipageException
     *      si l'equipage est au complet
     */
    public void addPnc(PNC pnc) throws EquipageException{
        if(!this.atMax) {
            this.pncs.add(pnc);
        } else {
            throw new EquipageException("Equipage complet");
        }
    }

    /**
     * getter de pnc
     * @return
     *      la liste de pnc
     */
    public ArrayList<PNC> getPncs() {
        return pncs;
    }

    /**
     * getter de copilote
     * @return
     *      le copilote
     */
    public Copilote getCopilote() {
        return copilote;
    }

    /**
     * getter du pilote
     * @return
     *      le pilote
     */
    public Pilote getPilote() {
        return pilote;
    }

    /**
     * getter de l'id
     * @return
     *  l'id
     */
    public int getId() {
        return id;
    }
}
