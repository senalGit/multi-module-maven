package fr.maetic.mailing.service;

public interface EmailService {
    void sendSimpleMailMessage(String name, String to, String message);
    void sendSimpleMailMessageUserVerification(String name, String to, String token);

    void sendMimeMessageWithAttachment(String name, String to, String token);
    void sendMimeMessageWithEmbeddedImages(String name, String to, String token);
    void sendMimeMessageWithEmbeddedFiles(String name, String to, String token);
    void sendHtmlEmail(String name, String to, String token);
    void sendHtmlEmailWithEmbeddedFiles(String name, String to, String token);

}
