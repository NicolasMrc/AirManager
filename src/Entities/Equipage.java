package Entities;

import Exceptions.EquipageException;

import java.util.ArrayList;

/**
 * Created by Nico on 28/11/2016.
 */
public class Equipage {
    private int id;
    private boolean atMin;
    private boolean atMax;
    private Pilote pilote;
    private Copilote copilote;
    private ArrayList<PNC> pncs;

    //TODO ajouter le vol ? je ne vois pas l'interet

    public Equipage(int id, Pilote pilote, Copilote copilote, ArrayList<PNC> pncs) {
        this.id = id;
        this.pilote = pilote;
        this.copilote = copilote;
        this.pncs = pncs;
    }

    public Equipage(Pilote pilote, Copilote copilote, ArrayList<PNC> pncs) {
        this.pilote = pilote;
        this.copilote = copilote;
        this.pncs = pncs;
    }

    public void addPilote(Pilote pilote) throws EquipageException {
        if(this.pilote == null) {
            this.pilote = pilote;
        } else {
            throw new EquipageException("Le pilote est déjà renseigné");
        }
    }

    public void addCopilote(Copilote copilote) throws EquipageException{
        if (this.copilote == null) {
            this.copilote = copilote;
        } else {
            throw new EquipageException("Le copilaote est déjà renseigné");
        }
    }

    public void addPncs(PNC pnc) throws EquipageException{
        this.pncs.add(pnc);
    }

    public ArrayList<PNC> getPncs() {
        return pncs;
    }

    public Copilote getCopilote() {
        return copilote;
    }

    public Pilote getPilote() {
        return pilote;
    }

    public int getId() {
        return id;
    }
}
