package Exceptions;

/**
 * Created by Nico on 01/12/2016.
 */
public class EmptyFieldException extends Exception {

    private String message = "Tout les champs doivent être rensignés";

    @Override
    public String getMessage(){
        return this.message;
    }

}
