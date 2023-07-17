package fr.maetic.mailing.utils;

public class EmailUtils {
    public static String getEmailMessage(String name, String host, String token) {
        return "Bonjour " + name + ",\n\nVotre compte a bien été créé. Cliquez sur ce lien ci-dessous pour vérififier votre compte. \n\n" +
                getVerificationUrl(host, token) + "\n\nL'equipe support";
    }

    private static String getVerificationUrl(String host, String token) {
        return host + "/api/auth?token=" + token;
    }
}
