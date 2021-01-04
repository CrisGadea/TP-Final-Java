package ar.edu.unlam.tallerweb1.persistencia;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.Perfil;
import ar.edu.unlam.tallerweb1.modelo.Suscripcion;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
public class SuscripcionTest extends SpringTest {

    @Test
    @Transactional @Rollback
    public void pruebaConexion(){
        assertThat(session().isConnected()).isTrue();
    }

    @Test
    @Transactional @Rollback
    public void sePuedeCrearUnaSuscripcion(){
        // Preparación
        Suscripcion suscripcion = new Suscripcion();
        LocalDateTime time = LocalDateTime.now();
        // Implementación
        suscripcion.setFecha_ven(time.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)+2);
        session().save(suscripcion);
        // Prueba
        assertThat(suscripcion.getId()).isNotNull();
    }


    @Test
    @Transactional @Rollback
    public void sePuedeEditarUnaSuscripcion(){
        // Preparación
        Suscripcion suscripcion = new Suscripcion();
        LocalDateTime time = LocalDateTime.now();
        // Implementación
        suscripcion.setFecha_ven(time.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)+2);

        session().save(suscripcion);

        suscripcion.setFecha_ven(time.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)+2);

        session().update(suscripcion);

        Suscripcion suscripcionConsultada = session().get(Suscripcion.class, suscripcion.getId());
        // Prueba
        Assert.assertEquals(suscripcion,suscripcionConsultada);
    }

    @Test
    @Transactional @Rollback
    public void pruebaQueUnUsuarioPuedeElegirUnaSuscripcionYSuscribirse(){
        // Preparación
        Suscripcion suscripcion = new Suscripcion();
        Usuario usuario = new Usuario();
        LocalDateTime time = LocalDateTime.now();
        LocalDateTime venc_time_free = LocalDateTime.now().plusDays(99999);

        suscripcion.setFecha_ven(venc_time_free.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        Long idSuscripcion = (Long) session().save(suscripcion);

        Suscripcion suscripcionBuscada = session().get(Suscripcion.class, idSuscripcion);

        usuario.setEmail("cgadea@email.com");
        usuario.setPassword("1234");
        usuario.setTel("44444444");
        usuario.setRol("admin");
        usuario.setCreation_at(time.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        usuario.setUpdated_at(time.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        usuario.setUsername("cgadea");
        session().save(usuario);


    }

}
