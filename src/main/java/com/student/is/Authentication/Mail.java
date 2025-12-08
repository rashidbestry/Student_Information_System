package com.student.is.Authentication;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.random.RandomGenerator;

public class Mail {
    public static boolean forgotPasswordMail(String login) {
        int newpassword = RandomGenerator.getDefault().nextInt(3333,9999);
        try {
            final String mailServerUsername = "incomservicetm@gmail.com";
            final String mailServerPassword = "nneq byst lpdz tfhx";
            final String mailServer = "smtp.gmail.com";

            Properties properties = new Properties();
            properties.put("mail.smtp.auth", "true");
            properties.put("mail.smtp.starttls.enable", "true");
            properties.put("mail.smtp.host", mailServer);
            properties.put("mail.smtp.port", "587");
            properties.put("mail.smtp.ssl.trust", mailServer);

            Session session = Session.getDefaultInstance(properties,
                    new jakarta.mail.Authenticator() {
                        @Override
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(mailServerUsername, mailServerPassword);
                        }
                    });
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(mailServerUsername));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(login));
            message.setSubject("AORA University - reset user password");
            message.setText("Your new password is : " + newpassword);
            Transport.send(message);
        } catch(MessagingException e) {
            Authentication.changePassword(login, String.valueOf(newpassword));
            return true;
        }
        Authentication.changePassword(login, String.valueOf(newpassword));
        return true;
    }
}
