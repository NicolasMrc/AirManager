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

    public Utilisateur(long id, String username, String password, TypeUtilisateur typeUtilisateur) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.typeUtilisateur = typeUtilisateur;
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
}
