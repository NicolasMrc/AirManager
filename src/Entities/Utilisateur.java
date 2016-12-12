package Entities;

import Enum.TypeUtilisateur;

/**
 * Created by Nico on 29/11/2016.
 */
public class Utilisateur {
    private long id;
    private String username;
    private String password;
    private TypeUtilisateur typeUtilisateur;
    private int idMembre;

    public Utilisateur(long id, String username, String password, TypeUtilisateur typeUtilisateur) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.typeUtilisateur = typeUtilisateur;
    }

    public Utilisateur(String username, String password, TypeUtilisateur typeUtilisateur, int idMembre) {
        this.username = username;
        this.password = password;
        this.typeUtilisateur = typeUtilisateur;
        this.idMembre = idMembre;
    }

    public Utilisateur(long id, String username, String password, TypeUtilisateur typeUtilisateur, int idMembre) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.typeUtilisateur = typeUtilisateur;
        this.idMembre = idMembre;
    }

    public long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public TypeUtilisateur getTypeUtilisateur() {
        return typeUtilisateur;
    }

    public String getPassword() {
        return password;
    }

    public int getIdMembre() {
        return idMembre;
    }
}
