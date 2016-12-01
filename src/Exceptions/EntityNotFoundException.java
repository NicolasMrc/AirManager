package Exceptions;

/**
 * Created by Nico on 01/12/2016.
 */
public class EntityNotFoundException extends Exception {

    private String message = "L'entité recherchée n'existe pas en base";

    @Override
    public String getMessage(){
        return this.message;
    }
}
