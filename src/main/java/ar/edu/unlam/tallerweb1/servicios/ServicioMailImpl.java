package ar.edu.unlam.tallerweb1.servicios;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Service("servicioMail")
public class ServicioMailImpl implements ServicioMail{

    private final JavaMailSenderImpl mailSender;

    public ServicioMailImpl() {
        this.mailSender = new JavaMailSenderImpl();
        new SimpleMailMessage();
    }

    @Override
    public void enviar(String to, String text) throws MessagingException {
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);
        mailSender.setUsername("magiostv2021@gmail.com");
        mailSender.setPassword("Magios2020");

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");

        MimeMessage message = mailSender.createMimeMessage();

        MimeMessageHelper helper;

        helper = new MimeMessageHelper(message, true);
        helper.setSubject("¡Bienvenid@ a MagiosTV!");
        helper.setTo(to);
        helper.setText(text, true);
        mailSender.send(message);

    }

}
