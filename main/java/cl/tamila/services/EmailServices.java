package cl.tamila.services;

import java.util.Date;
import java.util.Properties;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.AddressException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

// Vamos a crear unos valores de nuestro mailing de SMTP

@Service
@Primary
public class EmailServices {
    

    public void sendMail(String mail, String asunto) throws AddressException, MessagingException {
        // Objeto properties, hacemos la configuración con esto
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "sandbox.smtp.mailtrap.io");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("768f918d29-d0321b+1@inbox.mailtrap.io", "6f473de3260a5c");
            }
        });
        Message msg = new MimeMessage(session);
        //Aqui enviamos el correo
        msg.setFrom(new InternetAddress("768f918d29-d0321b+1@inbox.mailtrap.io", false));
        //Enviamos el correo a 
        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mail));
        //Seteamos sus valores, asunto, texto, fecha...
        msg.setSubject(asunto);
        msg.setContent("Envio de mail", "text/html");
        msg.setSentDate(new Date(0));
        
        MimeBodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setContent("Envio de mail", "text/html");
        
        //ejecutamos un transport para que se envie
        Transport.send(msg);
        
    }
}
