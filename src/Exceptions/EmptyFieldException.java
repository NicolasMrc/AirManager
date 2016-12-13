package Exceptions;

/**
 * Classe d'exception lancée lorsque qu'un champs est vide
 * Created by Nico on 01/12/2016.
 */
public class EmptyFieldException extends Exception {

    /**
     * le message de l'exception
     */
    private String message = "Tout les champs doivent être renseignés";

    @Override
    public String getMessage(){
        return this.message;
    }

}
