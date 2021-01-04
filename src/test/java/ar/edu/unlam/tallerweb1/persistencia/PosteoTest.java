package ar.edu.unlam.tallerweb1.persistencia;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.Mensaje;
import ar.edu.unlam.tallerweb1.modelo.Posteo;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import org.hibernate.PropertyValueException;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

public class PosteoTest extends SpringTest {
    @Test
    @Transactional
    @Rollback
    public void pruebaConexion(){
        assertThat(session().isConnected()).isTrue();
    }

    @Test
    @Transactional
    @Rollback
    public void  crearPosteo(){
        //preparacion
        Posteo posteo = new Posteo();
        Usuario user = new Usuario();

        user.setUsername("CrisGadea");
        user.setEmail("cris@gadea.com");
        user.setPassword("123456");
        user.setTel("44444444");
        user.setRol("admin");
        session().save(user);

        //ejecucion
        posteo.setCreador(user);
        posteo.setTitulo("primer posteo");
        posteo.setInformacion("Test de posteo");
        posteo.setFecha_creacion(new Date(2005, 01, 02));

        session().save(posteo);

        //prueba
        assertThat(session().get(Posteo.class, posteo.getId())).isNotNull();
    }

    @Test(expected = PropertyValueException.class)
    @Transactional
    @Rollback
    public void  testQueDemuestraQueNoSeCreaElPosteoSiNoSeSeteaSuCreador(){
        //preparacion
        Posteo posteo = new Posteo();

        //ejecucion
        posteo.setTitulo("primer posteo");
        posteo.setInformacion("Test de posteo");
        posteo.setFecha_creacion(new Date(2005, 01, 02));

        //prueba
        session().save(posteo);

    }

}
