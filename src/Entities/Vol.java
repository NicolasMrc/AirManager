package Entities;

import Exceptions.EquipageException;

import java.io.Serializable;
import java.util.Date;

/**
 * entite Vol
 * Created by Nico on 28/11/2016.
 */
public class Vol implements Serializable{

    //TODO utiliser les classe non utilisée

    /**
     * id du vol
     */
    private int id;

    /**
     * numero du vol
     */
    private String numero;

    /**
     * aeroport de depart
     */
    private Aeroport site;

    /**
     * aeroport d'arrivée
     */
    private Aeroport destination;

    /**
     * date du vol
     */
    private String date;

    /**
     * avion utilisé pour le vol
     */
    private Avion avion;

    /**
     * equipage du vol
     */
    private Equipage equipage;

    //TODO change date from String to Date

    /**
     * constructeur de vol
     * @param numero
     *      le numero de vol
     * @param site
     *      l'aeroport de depart
     * @param destination
     *      l'aeroport d'arrivée
     * @param date
     *      la date du vol
     * @param avion
     *      l'avion utilisé
     * @param equipage
     *      l'equipage du vol
     */
    public Vol(String numero, Aeroport site, Aeroport destination, String date, Avion avion, Equipage equipage) {
        this.numero = numero;
        this.site = site;
        this.destination = destination;
        this.date = date;
        this.avion = avion;
        this.equipage = equipage;
    }

    /**
     * consgtructeur avec id
     * @param id
     *      l'id du vol
     * @param numero
     *      le numero de vol
     * @param site
     *      l'aeroport de depart
     * @param destination
     *      l'aeroport d'arrivée
     * @param date
     *      la date du vol
     * @param avion
     *      l'avion utilisé
     * @param equipage
     *      l'equipage du vol
     */
    public Vol(int id, String numero, Aeroport site, Aeroport destination, String date, Avion avion, Equipage equipage) {
        this.id = id;
        this.numero = numero;
        this.site = site;
        this.destination = destination;
        this.date = date;
        this.avion = avion;
        this.equipage = equipage;
    }

    /**
     * constructeur avec seulement numero et date
     * @param numero
     *      numero du vol
     * @param date
     *      date du vol
     */
    public Vol(String numero, String date) {
        this.numero = numero;
        this.date = date;
    }

    /**
     * ajout d'un pilote dans l'equipage du vol
     * @param pilote
     *      le pilote du vol
     */
    public void addPilote(Pilote pilote) {
        try {
            this.equipage.addPilote(pilote);
        } catch(EquipageException e){
            this.equipage.replacePilote(pilote);
        }
    }

    /**
     * ajoute un copilote dans le vol
     * @param copilote
     *      le copilote a ajouter
     * @throws EquipageException
     *      si le copilote est deja renseigné
     */
    public void addCopilote(Copilote copilote) throws EquipageException{
        this.equipage.addCopilote(copilote);
    }

    /**
     * ajoute un pnc a la liste de pnc de l'equipage
     * @param p
     *      le pnc a ajouter
     */
    public void addPNC(PNC p){
        this.equipage.getPncs().add(p);
    }

    /**
     * renvoi true si l'equipage est au complet
     * @return
     *      true ou false
     */
    public boolean equipageAuComplet() {
        return this.equipage.getPilote() != null && this.equipage.getCopilote() != null && !this.equipage.getPncs().isEmpty();
    }

    /**
     * getter du numero du vol
     * @return
     *      le numero de vol
     */
    public String getNumero() {
        return numero;
    }

    /**
     * getter de laeroport de depart
     * @return
     *      laeroport de depart
     */
    public Aeroport getSite() {
        return site;
    }

    /**
     * getter de laeroport de destination
     * @return
     *      laeroport d'arrivée
     */
    public Aeroport getDestination() {
        return destination;
    }

    /**
     * getter de la date du vol
     * @return
     *      la date
     */
    public String getDate() {
        return date;
    }

    /**
     * getter de l'avion du vol
     * @return
     *      l'avion
     */
    public Avion getAvion() {
        return avion;
    }

    /**
     * getter de l'equipage du vo:
     * @return
     *     l'equipage du vol
     */
    public Equipage getEquipage() {
        return equipage;
    }

    /**
     * getter de l'id
     * @return
     *      l'id
     */
    public int getId() {
        return id;
    }
}
