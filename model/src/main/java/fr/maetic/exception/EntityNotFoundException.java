package fr.maetic.exception;


public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException() {
        super("Cet enregistrement n'existe pas en base de données");
    }

    public EntityNotFoundException(String message) {
        super(message);
    }

}