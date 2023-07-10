package fr.maetic.mailing.service;

import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.File;

import static fr.maetic.mailing.config.EmailMessageConfig.getEmailMessage;
import static fr.maetic.mailing.config.EmailMessageConfig.getEmailMessageUserVerification;

@Service
@Slf4j
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    public static final String OBJET_MAIL = "Création de votre compte";
    public static final String ENCODING = "UTF-8";
    private final JavaMailSender javaMailSender;
    @Value("${spring.mail.verify.host}")
    private String base_url;
    @Value("${spring.mail.username}")
    private String expediteur;

    @Override
    @Async
    public void sendSimpleMailMessage(String name, String to, String texte) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setSubject(OBJET_MAIL);
            message.setFrom(expediteur);
            message.setTo(to);
            message.setText(getEmailMessage(name, base_url, texte));
            javaMailSender.send(message);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void sendSimpleMailMessageUserVerification(String name, String to, String token) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setSubject(OBJET_MAIL);
            message.setFrom(expediteur);
            message.setTo(to);
            message.setText(getEmailMessageUserVerification(name, base_url, token));

            javaMailSender.send(message);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    @Async
    public void sendMimeMessageWithAttachment(String name, String to, String token) {

        try {
            MimeMessage message = getMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, ENCODING);
            messageHelper.setPriority(1);
            messageHelper.setSubject(OBJET_MAIL);
            messageHelper.setFrom(expediteur);
            messageHelper.setTo(to);
            messageHelper.setText(getEmailMessage(name, base_url, token));

            //Ajouter piece jointe
            FileSystemResource linux1 = new FileSystemResource(new File(System.getProperty("user.home") + "/mail_file/linux1.jpg"));
            FileSystemResource linux2 = new FileSystemResource(new File(System.getProperty("user.home") + "/mail_file/linux2.jpg"));
            FileSystemResource lettre = new FileSystemResource(new File(System.getProperty("user.home") + "/mail_file/lettre.docx"));

            messageHelper.addAttachment(linux1.getFilename(), linux1);
            messageHelper.addAttachment(linux2.getFilename(), linux2);
            messageHelper.addAttachment(lettre.getFilename(), lettre);
            javaMailSender.send(message);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    @Async
    public void sendMimeMessageWithEmbeddedImages(String name, String destinataire, String token) {

        try {
            MimeMessage message = getMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, ENCODING);
            messageHelper.setPriority(1);
            messageHelper.setSubject(OBJET_MAIL);
            messageHelper.setFrom(expediteur);
            messageHelper.setTo(destinataire);
            messageHelper.setText(getEmailMessage(name, base_url, token));

            //Ajouter piece jointe
            FileSystemResource linux1 = new FileSystemResource(new File(System.getProperty("user.home") + "/mail_file/linux1.jpg"));
            FileSystemResource linux2 = new FileSystemResource(new File(System.getProperty("user.home") + "/mail_file/linux2.jpg"));
            FileSystemResource lettre = new FileSystemResource(new File(System.getProperty("user.home") + "/mail_file/lettre.docx"));

            messageHelper.addInline(getContentId(linux1.getFilename()), linux1);
            messageHelper.addInline(getContentId(linux2.getFilename()), linux2);
            messageHelper.addInline(getContentId(lettre.getFilename()), lettre);
            javaMailSender.send(message);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    @Async
    public void sendMimeMessageWithEmbeddedFiles(String name, String to, String token) {

    }

    @Override
    @Async
    public void sendHtmlEmail(String name, String to, String token) {

    }

    @Override
    @Async
    public void sendHtmlEmailWithEmbeddedFiles(String name, String to, String token) {

    }

    private MimeMessage getMimeMessage() {
        return javaMailSender.createMimeMessage();
    }

    private String getContentId(String nomFichier) {
        return String.format("<%s>", nomFichier);
    }
}
