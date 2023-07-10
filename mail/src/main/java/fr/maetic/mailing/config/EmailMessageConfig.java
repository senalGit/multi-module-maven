package fr.maetic.mailing.config;

public class EmailMessageConfig {
    public static String getEmailMessage(String name, String host, String texte) {
        return String.format("Bonjour %s\n\n%s", name, texte);
    }

    public static String getEmailMessageUserVerification(String name, String host, String token) {
        return String.format("Bonjour %s, \n\n Votre compte a bien été créé. Cliquez sur ce lien ci-dessous pour vérifier votre compte.\n\n %s\n\nL'équipe support", name, getVerificationUrl(host, token));
    }

    private static String getVerificationUrl(String host, String token) {
        String url = String.format("%s/api/users?token=%s", host, token);
        return url;
    }
}
