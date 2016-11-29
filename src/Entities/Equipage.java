package Entities;

import Exceptions.EquipageException;

import java.util.ArrayList;

/**
 * Created by Nico on 28/11/2016.
 */
public class Equipage {
    private boolean atMin;
    private boolean atMax;
    private Pilote pilote;
    private Copilote copilote;
    private ArrayList<PNC> pncs;
    private Vol vol;

    public Equipage(Vol vol) {
        this.vol = vol;
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
}
