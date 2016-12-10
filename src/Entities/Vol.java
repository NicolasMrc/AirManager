package Entities;

import Exceptions.EquipageException;

import java.util.Date;

/**
 * Created by Nico on 28/11/2016.
 */
public class Vol {
    private int id;
    private String numero;
    private String site;
    private String destination;
    private String date;

    private Avion avion;
    private Equipage equipage;

    //TODO change date from String to Date
    public Vol(String numero, String site, String destination, String date, Avion avion, Equipage equipage) {
        this.numero = numero;
        this.site = site;
        this.destination = destination;
        this.date = date;
        this.avion = avion;
        this.equipage = equipage;
    }

    public Vol(int id, String numero, String site, String destination, String date, Avion avion, Equipage equipage) {
        this.id = id;
        this.numero = numero;
        this.site = site;
        this.destination = destination;
        this.date = date;
        this.avion = avion;
        this.equipage = equipage;
    }

    public Vol(String numero, String date) {
        this.numero = numero;
        this.date = date;
    }

    public void addPilote(Pilote pilote) throws EquipageException {
        this.equipage.addPilote(pilote);
    }

    public void addCopilote(Copilote copilote) throws EquipageException{
        this.equipage.addCopilote(copilote);
    }

    public void addPNC(PNC p){
        this.equipage.getPncs().add(p);
    }

    public boolean equipageAuComplet() {
        return this.equipage.getPilote() != null && this.equipage.getCopilote() != null && !this.equipage.getPncs().isEmpty();
    }

    public String getNumero() {
        return numero;
    }

    public String getSite() {
        return site;
    }

    public String getDestination() {
        return destination;
    }

    public String getDate() {
        return date;
    }

    public Avion getAvion() {
        return avion;
    }

    public Equipage getEquipage() {
        return equipage;
    }
}
