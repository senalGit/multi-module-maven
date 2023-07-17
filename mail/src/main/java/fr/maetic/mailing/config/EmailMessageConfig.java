package fr.maetic.mailing.config;

public class EmailMessageConfig {
    public static String getEmailMessage(String texte) {
        return String.format("Bonjour %s\n\n%s", texte);
    }

    public static String getEmailMessageUserVerification(String name,
                                                         String host,
                                                         String token) {
        String url = String
                .format("%s/api/auth?token=%s",
                        host,
                        token);
        return String.format("Bonjour %s, \n\n Votre compte a bien été créé. Cliquez sur ce lien ci-dessous pour vérifier votre compte.\n\n %s\n\nL'équipe support", name, url);
    }

}
