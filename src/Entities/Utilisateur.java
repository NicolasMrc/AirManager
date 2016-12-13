package Entities;

import Enum.TypeUtilisateur;

/**
 * Created by Nico on 29/11/2016.
 */
public class Utilisateur {

    /**
     * id de l'utilisateur
     */
    private long id;

    /**
     * nom d'utilisateur
     */
    private String username;

    /**
     * mot de passe
     */
    private String password;

    /**
     * type d'utilisateur
     */
    private TypeUtilisateur typeUtilisateur;

    /**
     * id du membre correspondant
     */
    private int idMembre;

    /**
     * constructeur de la classe utilisateur
     * @param id
     *      id de l'utilisateur
     * @param username
     *      nom d'utilisateur
     * @param password
     *      mot de passe de l'utilisateur
     * @param typeUtilisateur
     *      type dutilisateur
     */
    public Utilisateur(long id, String username, String password, TypeUtilisateur typeUtilisateur) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.typeUtilisateur = typeUtilisateur;
    }

    /**
     * constructeur sans id
     * @param username
     *      le nom d'utilisateur
     * @param password
     *      le mot de passe de l'utilisateur
     * @param typeUtilisateur
     *      le type d'utilisateur
     * @param idMembre
     *      l'id du membre equipage associé a l'utilisateur
     */
    public Utilisateur(String username, String password, TypeUtilisateur typeUtilisateur, int idMembre) {
        this.username = username;
        this.password = password;
        this.typeUtilisateur = typeUtilisateur;
        this.idMembre = idMembre;
    }

    /**
     * constructeur
     * @param id
     *      id utilisateur
     * @param username
     *      le nom d'utilisateur
     * @param password
     *      le mot de passe de l'utilisateur
     * @param typeUtilisateur
     *      le type d'utilisateur
     * @param idMembre
     *      l'id du membre utilisateur
     */
    public Utilisateur(long id, String username, String password, TypeUtilisateur typeUtilisateur, int idMembre) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.typeUtilisateur = typeUtilisateur;
        this.idMembre = idMembre;
    }

    /**
     * le getter de l'id
     * @return
     *      l'id
     */
    public long getId() {
        return id;
    }

    /**
     * getter du nom d'utilisateur
     * @return
     *      le nom d'utilisateur
     */
    public String getUsername() {
        return username;
    }

    /**
     * getter du type d'utilisateur
     * @return
     *      le type d'utilisateur
     */
    public TypeUtilisateur getTypeUtilisateur() {
        return typeUtilisateur;
    }

    /**
     * le getter de mot de passe de l'utilisateur
     * @return
     */
    public String getPassword() {
        return password;
    }

    /**
     * getter de l'id du membre dequipage associé a l'utilisateur
     * peut etre null
     * @return
     *      l'id membre
     */
    public int getIdMembre() {
        return idMembre;
    }

    /**
     * setter du nom d'utilisateur
     * @param username
     *      le nom d'utilisateur a setter
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * setter du mot de passe utilisateur
     * @param password
     *      le mot de passe utilisateur
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
