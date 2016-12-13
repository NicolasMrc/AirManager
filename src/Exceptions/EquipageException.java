package Exceptions;

/**
 * La classe d'exception d'equipage
 * Created by Nico on 28/11/2016.
 */
public class EquipageException extends Exception{

    /**
     * constructeur de l'exception
     * lancée lorsqu'il y a un probleme avec l'equipage
     * @param message
     *      le message de l'exception
     */
    public EquipageException(String message) {
        super(message);
    }
}
