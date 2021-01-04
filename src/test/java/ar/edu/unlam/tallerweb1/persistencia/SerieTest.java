package ar.edu.unlam.tallerweb1.persistencia;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.Capitulo;
import ar.edu.unlam.tallerweb1.modelo.Pelicula;
import ar.edu.unlam.tallerweb1.modelo.Serie;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioCapitulo;
import ar.edu.unlam.tallerweb1.servicios.ServicioCapitulo;
import ar.edu.unlam.tallerweb1.servicios.ServicioSerie;
import org.hibernate.Session;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


public class SerieTest extends SpringTest {
    Serie serie = new Serie();

    Capitulo c1=new Capitulo();
    Capitulo c2=new Capitulo();
    Capitulo c3=new Capitulo();

    ServicioSerie servicioSerie;
    ServicioCapitulo servicioCapitulo;
    RepositorioCapitulo servicioCapituloDao;

    @Test
    @Transactional @Rollback
    public void pruebaConexion(){
        assertThat(session().isConnected()).isTrue();
    }

    @Test
    @Transactional @Rollback
    public void testQuePruebaQueExisteUnaSerie(){



        serie.setDescripcion("soy una serie de prueba");
        serie.setTitulo("Dark");
        serie.setFecha_estreno( new Date(2019, 12, 20));
        serie.setUbicacionArchivo("ubicacionSerie.mp4");

        session().save(serie);

        assertThat(serie.getId()).isNotNull();
    }

    @Test
    @Transactional @Rollback
    public void testQueDemuestraQueSeEliminaLaSerie(){
        //creamos la pelicula


        serie.setTitulo("Superman");
        serie.setDescripcion("Hombre de acero");
        serie.setFecha_estreno(new Date(2005, 01, 02));
        serie.setGenero("Drama");
        serie.setUbicacionArchivo("archivo1.mp4");

        session().save(serie);
        session().delete(serie);

        Serie serieConsultada = session().get(Serie.class, serie.getId());

        assertThat(serieConsultada).isNull();
    }

    @Test
    @Transactional @Rollback
    public void testQueDemuestraQueSeActualizaLaSerie(){
        Serie serie = new Serie();

        serie.setTitulo("Superman");
        serie.setDescripcion("Hombre de acero");
        serie.setFecha_estreno(new Date(2005, 01, 02));
        serie.setGenero("Drama");
        serie.setUbicacionArchivo("archivo1.mp4");

        session().save(serie);

        //actualizamos la pelicula
        serie.setTitulo("Superman 2");
        serie.setDescripcion("Hombre de acero regresa");
        serie.setFecha_estreno(new Date(2002, 01, 02));
        serie.setGenero("Drama");
        serie.setUbicacionArchivo("archivo2.mp4");

        session().update(serie);

        Serie serieConsultada = session().get(Serie.class, serie.getId());

        Assert.assertEquals(serie, serieConsultada);
    }

    @Test
    @Transactional @Rollback
    public void testQueDemuestraQueSeAgregaLaSerieVista(){
        //preparacion
        Usuario user = new Usuario();
        Serie serie = new Serie();

        user.setUsername("CrisGadea");
        user.setEmail("cris@gadea.com");
        user.setPassword("123456");
        user.setTel("44444444");
        user.setRol("admin");

        serie.setTitulo("Superman");
        serie.setDescripcion("Hombre de acero");
        serie.setFecha_estreno(new Date(2005, 01, 02));
        serie.setGenero("Drama");
        serie.setUbicacionArchivo("archivo1.mp4");

        session().save(serie);
        session().save(user);

        //ejecucion
        user.getSeriesVistas().add(serie);

        //prueba
        assertThat(user.getSeriesVistas().size()).isEqualTo(1);
        assertThat(user.getSeriesVistas().contains(serie));
    }


    @Test
    @Transactional @Rollback
    public void testQueDemuestraQueSeAgreganMuchasSeries(){
        //preparacion
        Usuario user = new Usuario();
        Serie serie1 = new Serie();
        Serie serie2 = new Serie();
        Serie serie3 = new Serie();

        user.setUsername("CrisGadea");
        user.setEmail("cris@gadea.com");
        user.setPassword("123456");
        user.setTel("44444444");
        user.setRol("admin");

        serie1.setTitulo("Superman 1");
        serie1.setDescripcion("Hombre de acero");
        serie1.setFecha_estreno(new Date(2005, 01, 02));
        serie1.setGenero("Drama");
        serie1.setUbicacionArchivo("archivo1.mp4");
        serie2.setTitulo("Superman 2");
        serie2.setDescripcion("Hombre de acero");
        serie2.setFecha_estreno(new Date(2005, 01, 02));
        serie2.setGenero("Drama");
        serie2.setUbicacionArchivo("archivo1.mp4");
        serie3.setTitulo("Superman 3");
        serie3.setDescripcion("Hombre de acero");
        serie3.setFecha_estreno(new Date(2005, 01, 02));
        serie3.setGenero("Drama");
        serie3.setUbicacionArchivo("archivo1.mp4");

        session().save(serie1);
        session().save(serie2);
        session().save(serie3);
        session().save(user);

        //ejecucion
        user.getSeriesVistas().add(serie1);
        user.getSeriesVistas().add(serie2);
        user.getSeriesVistas().add(serie3);

        //prueba
        assertThat(user.getSeriesVistas().size()).isEqualTo(3);
    }


    @Test
    @Transactional @Rollback
    public void testQueDemuestraQueSerieEstaRealcionadoConMasDeUnCapitulo(){
        serie.setTitulo("Superman");
        serie.setDescripcion("Hombre de acero");
        serie.setFecha_estreno(new Date(2005, 01, 02));
        serie.setGenero("Drama");
        serie.setUbicacionArchivo("archivo1.mp4");

        c1.setTitulo("capi 1");
        c1.setDescripcion("descripcion 1");
        c1.setHoras(1);
        c1.setMinutos(55);
        c1.setUbicacionArchivo("archivo1.mp4");

        c2.setTitulo("capi 2");
        c2.setDescripcion("descripcion 2");
        c2.setHoras(2);
        c2.setMinutos(22);
        c2.setUbicacionArchivo("archivo1.mp4");

        c3.setTitulo("capi 3");
        c3.setDescripcion("descripcion 3");
        c3.setHoras(3);
        c3.setMinutos(33);
        c3.setUbicacionArchivo("archivo1.mp4");

        c1.setSerie(serie);
        c2.setSerie(serie);
        c3.setSerie(serie);


        Assert.assertEquals(serie.getTitulo(),c1.getSerie().getTitulo());
        Assert.assertEquals(serie.getGenero(),c1.getSerie().getGenero());

        Assert.assertEquals(serie.getTitulo(),c2.getSerie().getTitulo());
        Assert.assertEquals(serie.getGenero(),c2.getSerie().getGenero());

        Assert.assertEquals(serie.getTitulo(),c3.getSerie().getTitulo());
        Assert.assertEquals(serie.getGenero(),c3.getSerie().getGenero());

    }

}
