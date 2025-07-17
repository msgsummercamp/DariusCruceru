package org.example;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.example.exception.EmailSendException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Component
public class SendMail {

    @Value("${spring.mail.username}")
    private String username;

    @Value("${spring.mail.password}")
    private String password;

    @Value("${spring.mail.host}")
    private String host;

    @Value("${spring.mail.port}")
    private String port;

    @Value("${spring.mail.ssl.enable}")
    private String sslEnable;

    @Value("${spring.mail.auth}")
    private String auth;

    private static final Logger logger = LoggerFactory.getLogger(SendMail.class);


    public void send() {


        Properties properties = System.getProperties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);
        properties.put("mail.smtp.ssl.enable", sslEnable);
        properties.put("mail.smtp.auth", auth);

        Session session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(username));
            message.setSubject("Spring Email Test");
            message.setText("This is the body of the message.");
            Transport.send(message);
            System.out.println("Sent successfully.");
        } catch (MessagingException e) {
            logger.error("Eroare la trimiterea emailului către {}", username, e);
            throw new EmailSendException("Trimiterea emailului a eșuat", e);
        }
    }
}
