package Entities;

import Exceptions.EquipageException;

import java.util.Date;

/**
 * Created by Nico on 28/11/2016.
 */
public class Vol {
    private String numero;
    private String site;
    private String destination;
    private Date date;

    private Avion avion;
    private Equipage equipage;

    public Vol(String numero, String site, String destination, Date date, Avion avion) {
        this.numero = numero;
        this.site = site;
        this.destination = destination;
        this.date = date;
        this.avion = avion;
    }

    public Vol(String numero, Date date) {
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
}
