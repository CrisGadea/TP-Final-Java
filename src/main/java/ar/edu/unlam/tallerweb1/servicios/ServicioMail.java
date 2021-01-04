package ar.edu.unlam.tallerweb1.servicios;

import javax.mail.MessagingException;

public interface ServicioMail {
    void enviar(String to, String text) throws MessagingException;
}
