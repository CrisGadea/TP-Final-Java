package ar.edu.unlam.tallerweb1.persistencia;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.Mensaje;
import ar.edu.unlam.tallerweb1.modelo.Posteo;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import org.hibernate.Criteria;
import org.hibernate.PropertyValueException;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class MensajeTest extends SpringTest {

    @Test
    @Transactional
    @Rollback
    public void pruebaConexion(){
        assertThat(session().isConnected()).isTrue();
    }

    @Test
    @Transactional
    @Rollback
    public void crearMensaje(){
        //preparacion
        Posteo posteo = new Posteo();
        Usuario user = new Usuario();
        Mensaje mensaje = new Mensaje();

        user.setUsername("CrisGadea");
        user.setEmail("cris@gadea.com");
        user.setPassword("123456");
        user.setTel("44444444");
        user.setRol("admin");

        posteo.setCreador(user);
        posteo.setTitulo("primer posteo");
        posteo.setInformacion("Test de posteo");
        posteo.setFecha_creacion(new Date(2005, 01, 02));

        //ejecucion
        session().save(user);
        session().save(posteo);


        mensaje.setCreador(user);
        mensaje.setMensaje("mi mensaje");
        mensaje.setPosteo(posteo);
        mensaje.setFecha_creacion(new Date(2003, 2, 3));

        session().save(mensaje);

        //prueba
        assertThat(session().get(Mensaje.class, mensaje.getId())).isNotNull();

    }
    @Test
    @Transactional
    @Rollback
    public void testQueDemuestraQueELMensajeEsDelUsuarioQueLoCreo(){
        Posteo posteo = new Posteo();
        Usuario user = new Usuario();
        Mensaje mensaje = new Mensaje();

        user.setUsername("CrisGadea");
        user.setEmail("cris@gadea.com");
        user.setPassword("123456");
        user.setTel("44444444");
        user.setRol("admin");

        posteo.setCreador(user);
        posteo.setTitulo("primer posteo");
        posteo.setInformacion("Test de posteo");
        posteo.setFecha_creacion(new Date(2005, 01, 02));

        //ejecucion
        session().save(user);
        session().save(posteo);


        mensaje.setCreador(user);
        mensaje.setMensaje("mi mensaje");
        mensaje.setPosteo(posteo);
        mensaje.setFecha_creacion(new Date(2003, 2, 3));

        session().save(mensaje);

        //prueba
        assertThat(mensaje.getCreador()).isEqualTo(user);
    }

    @Test
    @Transactional
    @Rollback
    public void testQueDemuestraQueELMensajePerteneceAlPosteoQueSetiamos(){
        Posteo posteo = new Posteo();
        Usuario user = new Usuario();
        Mensaje mensaje = new Mensaje();

        user.setUsername("CrisGadea");
        user.setEmail("cris@gadea.com");
        user.setPassword("123456");
        user.setTel("44444444");
        user.setRol("admin");

        posteo.setCreador(user);
        posteo.setTitulo("primer posteo");
        posteo.setInformacion("Test de posteo");
        posteo.setFecha_creacion(new Date(2005, 01, 02));

        //ejecucion
        session().save(user);
        session().save(posteo);


        mensaje.setCreador(user);
        mensaje.setMensaje("mi mensaje");
        mensaje.setPosteo(posteo);
        mensaje.setFecha_creacion(new Date(2003, 2, 3));

        session().save(mensaje);

        //prueba
        assertThat(mensaje.getPosteo()).isEqualTo(posteo);
    }

    @Test(expected = PropertyValueException.class)
    @Transactional
    @Rollback
    public void testQueDemuestraQueELMensajeNoSeCreaSiNoSeSeteaElCreador(){
        //preparacion
        Posteo posteo = new Posteo();
        Mensaje mensaje = new Mensaje();


        posteo.setTitulo("primer posteo");
        posteo.setInformacion("Test de posteo");
        posteo.setFecha_creacion(new Date(2005, 01, 02));

        //ejecucion
        session().save(posteo);

        mensaje.setMensaje("mi mensaje");
        mensaje.setPosteo(posteo);
        mensaje.setFecha_creacion(new Date(2003, 2, 3));

        //prueba
        session().save(mensaje);
    }

    @Test
    @Transactional
    @Rollback
    public void testQueDemuestraQueListaTodosLosMensajesDeUnPostPorElId(){
        Posteo posteo = new Posteo();
        Usuario user = new Usuario();
        Mensaje mensaje1 = new Mensaje();
        Mensaje mensaje2 = new Mensaje();

        user.setUsername("CrisGadea");
        user.setEmail("cris@gadea.com");
        user.setPassword("123456");
        user.setTel("44444444");
        user.setRol("admin");

        posteo.setCreador(user);
        posteo.setTitulo("primer posteo");
        posteo.setInformacion("Test de posteo");
        posteo.setFecha_creacion(new Date(2005, 01, 02));


        session().save(user);
        session().save(posteo);

        mensaje1.setCreador(user);
        mensaje1.setMensaje("mi mensaje 2");
        mensaje1.setPosteo(posteo);
        mensaje1.setFecha_creacion(new Date(2004, 2, 3));

        mensaje2.setCreador(user);
        mensaje2.setMensaje("mi mensaje");
        mensaje2.setPosteo(posteo);
        mensaje2.setFecha_creacion(new Date(2003, 2, 3));

        session().save(mensaje1);
        session().save(mensaje2);

        Criteria criteria = session().createCriteria(Mensaje.class)
                .add(Restrictions.eq("posteo", posteo));
        List<Mensaje> mensajesDelPost = criteria.list();

        assertThat(mensajesDelPost.size()).isEqualTo(2);
        assertThat(mensajesDelPost.contains(mensaje1)).isTrue();
        assertThat(mensajesDelPost.contains(mensaje2)).isTrue();

    }
}
