package Exceptions;

/**
 * Classe d'exception sur les qualifications
 * Created by Nico on 28/11/2016.
 */
public class QualificationException extends Exception {
    /**
     * constructeur de l'exception
     * @param message
     *      les message de l'exception
     */
    public QualificationException(String message) {
        super(message);
    }
}
