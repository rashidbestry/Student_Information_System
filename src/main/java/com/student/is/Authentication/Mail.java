package com.student.is.Authentication;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.Random;
import java.util.stream.Collectors;

public class Mail {
    public static void forgotPasswordMail(String login) throws MessagingException {
        String newpassword = new Random().ints(10, 33, 122).mapToObj(i -> String.valueOf((char)i)).collect(Collectors.joining());
        final String mailServerUsername = "incomservicetm@gmail.com";
        final String mailServerPassword = "nneq byst lpdz tfhx";
        final String mailServer = "smtp.gmail.com";

        Properties properties = new Properties();
        properties.put("mail.smtp.auth","true");
        properties.put("mail.smtp.starttls.enable","true");
        properties.put("mail.smtp.host",mailServer);
        properties.put("mail.smtp.port","587");
        properties.put("mail.smtp.ssl.trust",mailServer);

        Session session = Session.getDefaultInstance(properties,
                new jakarta.mail.Authenticator(){
                     @Override
                     protected PasswordAuthentication getPasswordAuthentication(){
                            return new PasswordAuthentication(mailServerUsername,mailServerPassword);
            }
        });
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(mailServerUsername));
        message.setRecipient(Message.RecipientType.TO,new InternetAddress(login));
        message.setSubject("AORA University - reset user password");
        message.setText("Your new password is : "+newpassword);
        Transport.send(message);
        Authentication.changePassword(login,newpassword);
        System.out.println("The forgot password email was send");
    }
}
