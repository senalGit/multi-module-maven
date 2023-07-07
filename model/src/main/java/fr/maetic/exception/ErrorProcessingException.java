package fr.maetic.exception;

public class ErrorProcessingException extends RuntimeException {

    public ErrorProcessingException() {
        super("Une erreur inattendue s'est produite lors de la tentative de traitement de votre demande");
    }

    public ErrorProcessingException(String message) {
        super(message);
    }

    public ErrorProcessingException(String message, Throwable root) {
        super(message, root);
    }
}