package ar.edu.unlam.tallerweb1.persistencia;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.Pelicula;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

import static org.assertj.core.api.Assertions.*;

public class UsuarioTest extends SpringTest {
    @Test
    @Transactional @Rollback
    public void pruebaConexion(){
        assertThat(session().isConnected()).isTrue();
    }

    @Test
    @Transactional @Rollback
    public void crearUsuario(){
        Usuario user = new Usuario();

        user.setUsername("CrisGadea");
        user.setEmail("cris@gadea.com");
        user.setPassword("123456");
        user.setTel("44444444");
        user.setRol("admin");

        session().save(user);
        assertThat(user.getId()).isNotNull();
    }

    @Test
    @Transactional @Rollback
    public void testQueDemuestraQueSeAgregaLaPeliculaVista(){
        //preparacion
        Usuario user = new Usuario();
        Pelicula pelicula = new Pelicula();

        user.setUsername("CrisGadea");
        user.setEmail("cris@gadea.com");
        user.setPassword("123456");
        user.setTel("44444444");
        user.setRol("admin");


        pelicula.setTitulo("Superman");
        pelicula.setDescripcion("Hombre de acero");
        pelicula.setFecha_estreno(new Date(2005, 01, 02));
        pelicula.setGenero("Drama");
        pelicula.setUbicacionArchivo("archivo1.mp4");

        session().save(pelicula);
        session().save(user);

        //ejecucion
        user.getPeliculasVistas().add(pelicula);

        //prueba
        assertThat(user.getPeliculasVistas().size()).isEqualTo(1);
        assertThat(user.getPeliculasVistas().contains(pelicula));
    }

    @Test
    @Transactional @Rollback
    public void testQueDemuestraQueSeAgreganMuchasPeliculas(){
        //preparacion
        Usuario user = new Usuario();
        Pelicula pelicula1 = new Pelicula();
        Pelicula pelicula2 = new Pelicula();
        Pelicula pelicula3 = new Pelicula();

        user.setUsername("CrisGadea");
        user.setEmail("cris@gadea.com");
        user.setPassword("123456");
        user.setTel("44444444");
        user.setRol("admin");

        pelicula1.setTitulo("Superman 1");
        pelicula1.setDescripcion("Hombre de acero");
        pelicula1.setFecha_estreno(new Date(2005, 01, 02));
        pelicula1.setGenero("Drama");
        pelicula1.setUbicacionArchivo("archivo1.mp4");
        pelicula2.setTitulo("Superman 2");
        pelicula2.setDescripcion("Hombre de acero");
        pelicula2.setFecha_estreno(new Date(2005, 01, 02));
        pelicula2.setGenero("Drama");
        pelicula2.setUbicacionArchivo("archivo1.mp4");
        pelicula3.setTitulo("Superman 3");
        pelicula3.setDescripcion("Hombre de acero");
        pelicula3.setFecha_estreno(new Date(2005, 01, 02));
        pelicula3.setGenero("Drama");
        pelicula3.setUbicacionArchivo("archivo1.mp4");

        session().save(pelicula1);
        session().save(pelicula2);
        session().save(pelicula3);
        session().save(user);

        //ejecucion
        user.getPeliculasVistas().add(pelicula1);
        user.getPeliculasVistas().add(pelicula2);
        user.getPeliculasVistas().add(pelicula3);

        //prueba
        assertThat(user.getPeliculasVistas().size()).isEqualTo(3);
    }
}
