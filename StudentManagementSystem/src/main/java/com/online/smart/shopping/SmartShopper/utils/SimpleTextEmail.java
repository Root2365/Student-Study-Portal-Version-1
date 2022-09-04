package com.online.smart.shopping.SmartShopper.utils;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.springframework.stereotype.Component;

@Component
public class SimpleTextEmail {
    
    public void sendEmail(String recipientEmail, String content ,String subject , String from, String name) throws EmailException {
        Email email = new SimpleEmail();

        // Configuration
        email.setHostName("smtp.googlemail.com");
        email.setSmtpPort(465);
        email.setAuthenticator(new DefaultAuthenticator("studyportalforstudent@gmail.com",
                "fezddnoxsykjzuvw"));

        // Required for gmail
        email.setSSLOnConnect(true);

        // Sender
        email.setFrom("studyportalforstudent@gmail.com");

        // Email title
        email.setSubject(subject);

        // Email message.
        email.setMsg(content);

        // Receiver
        email.addTo(recipientEmail);
        email.send();
        System.out.println("Sent!!");
    }

}