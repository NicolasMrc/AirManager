package Exceptions;

/**
 * Lorsque que l'instance d'une entité n'est pas trouvée
 * Created by Nico on 01/12/2016.
 */
public class EntityNotFoundException extends Exception {

    /**
     * le message de l'exception
     */
    private String message = "L'entité recherchée n'existe pas en base";

    @Override
    public String getMessage(){
        return this.message;
    }
}
