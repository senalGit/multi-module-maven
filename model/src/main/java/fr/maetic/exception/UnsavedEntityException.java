package fr.maetic.exception;


public class UnsavedEntityException extends RuntimeException {

    public UnsavedEntityException() {
        super("Une erreur inattendue s'est produite lors de la tentative de sauvegarde de l'enregistrement");
    }
    public UnsavedEntityException(Object entity) {
        super("Une erreur inattendue s'est produite lors de la tentative de sauvegarde de l'enregistrement " + entity.toString());
    }
    public UnsavedEntityException(String message) {
        super(message);
    }

}