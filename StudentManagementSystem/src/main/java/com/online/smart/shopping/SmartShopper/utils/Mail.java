package com.online.smart.shopping.SmartShopper.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;
import javax.mail.Session;
import javax.mail.Transport;

@Component
public class Mail {
    @Autowired
    private JavaMailSender mailSender;
    public void sendEmail(String recipientEmail, String content ,String subject , String from, String name)
            throws MessagingException, UnsupportedEncodingException, javax.mail.MessagingException {
        System.out.println("In email sending");
        /*MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        helper.setFrom(from , name);
        helper.setTo(recipientEmail);
        helper.setSubject(subject);

        helper.setText(content, true);

        mailSender.send(message);*/




        String host = "127.0.0.1";

        // Getting system properties
        Properties properties = System.getProperties();

        // Setting up mail server
        properties.setProperty("mail.smtp.host", host);
        properties.setProperty("mail.smtp.port", "8097");
        // creating session object to get properties
        Session session = Session.getDefaultInstance(properties);

        try
        {
            // MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From Field: adding senders email to from field.
            message.setFrom(new InternetAddress(from));

            // Set To Field: adding recipient's email to from field.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipientEmail));

            // Set Subject: subject of the email
            message.setSubject(subject);

            // set body of the email.
            message.setText(content);

            // Send email.
            Transport.send(message);
            System.out.println("Mail successfully sent");
        }
        catch (MessagingException mex)
        {
            mex.printStackTrace();
        }
    }

}
