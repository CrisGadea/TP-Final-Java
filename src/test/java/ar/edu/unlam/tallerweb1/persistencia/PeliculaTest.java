package ar.edu.unlam.tallerweb1.persistencia;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.Pelicula;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

public class PeliculaTest extends SpringTest{

    @Test
    @Transactional @Rollback
    public void pruebaConexion(){
        assertThat(session().isConnected()).isTrue();
    }

    @Test
    @Transactional @Rollback
    public void testQueDemuestreSiSeCreaLaPelicula (){
        Pelicula pelicula = new Pelicula();

        pelicula.setTitulo("Superman");
        pelicula.setDescripcion("Hombre de acero");
        pelicula.setFecha_estreno(new Date(2005, 01, 02));
        pelicula.setGenero("Drama");
        pelicula.setUbicacionArchivo("archivo1.mp4");

        session().save(pelicula);
        assertThat(pelicula.getId()).isNotNull();
    }
    @Test
    @Transactional @Rollback
    public void testQueDemuestraQueSeEliminaLaPelicula(){
        //creamos la pelicula
        Pelicula pelicula = new Pelicula();

        pelicula.setTitulo("Superman");
        pelicula.setDescripcion("Hombre de acero");
        pelicula.setFecha_estreno(new Date(2005, 01, 02));
        pelicula.setGenero("Drama");
        pelicula.setUbicacionArchivo("archivo1.mp4");

        session().save(pelicula);

        session().delete(pelicula);

        Pelicula peliculaConsultada = session().get(Pelicula.class, pelicula.getId());

        assertThat(peliculaConsultada).isNull();
    }

    @Test
    @Transactional @Rollback
    public void testQueDemuestraQueSeActualizaLaPelicula(){
        Pelicula pelicula = new Pelicula();

        pelicula.setTitulo("Superman");
        pelicula.setDescripcion("Hombre de acero");
        pelicula.setFecha_estreno(new Date(2005, 01, 02));
        pelicula.setGenero("Drama");
        pelicula.setUbicacionArchivo("archivo1.mp4");

        session().save(pelicula);

        //actualizamos la pelicula
        pelicula.setTitulo("Superman 2");
        pelicula.setDescripcion("Hombre de acero regresa");
        pelicula.setFecha_estreno(new Date(2002, 01, 02));
        pelicula.setGenero("Drama");
        pelicula.setUbicacionArchivo("archivo2.mp4");

        session().update(pelicula);

        Pelicula peliculaConsultada = session().get(Pelicula.class, pelicula.getId());

        Assert.assertEquals(pelicula, peliculaConsultada);
    }
}
