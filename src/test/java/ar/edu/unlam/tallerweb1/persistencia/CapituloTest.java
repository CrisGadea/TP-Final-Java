package ar.edu.unlam.tallerweb1.persistencia;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.Capitulo;
import ar.edu.unlam.tallerweb1.modelo.Serie;
import org.hibernate.PropertyValueException;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

public class CapituloTest extends SpringTest {

    @Test
    @Transactional
    @Rollback
    public void pruebaConexion(){
        assertThat(session().isConnected()).isTrue();
    }


    @Test
    @Transactional
    @Rollback
    public void crearCapitulo(){
        //preparacion
        Capitulo capitulo = new Capitulo();
        Serie serie = new Serie();

        serie.setTitulo("Superman");
        serie.setDescripcion("Hombre de acero");
        serie.setFecha_estreno(new Date(2005, 01, 02));
        serie.setGenero("Drama");
        serie.setUbicacionArchivo("archivo1.mp4");

        session().save(serie);


        capitulo.setUbicacionArchivo("imagen.jpg");
        capitulo.setSerie(serie);
        capitulo.setDescripcion("hola");
        capitulo.setHoras(2);
        capitulo.setMinutos(3);
        capitulo.setTitulo("titulo");

        //ejecucion
        session().save(capitulo);

        //prueba
        assertThat(session().get(Capitulo.class, capitulo.getId())).isNotNull();
    }

    @Test(expected = PropertyValueException.class)
    @Transactional
    @Rollback
    public void testQueDemuestraQueNoSeCreeElCapituloSiNoSeSeteaLaSerie(){
        //prueba
        Capitulo capitulo = new Capitulo();

        capitulo.setUbicacionArchivo("imagen.jpg");
        capitulo.setDescripcion("hola");
        capitulo.setHoras(2);
        capitulo.setMinutos(3);
        capitulo.setTitulo("titulo");

        session().save(capitulo);
    }

    @Test
    @Transactional
    @Rollback
    public void actualizarCapitulo(){
        Capitulo capitulo = new Capitulo();
        Serie serie = new Serie();

        serie.setTitulo("Superman");
        serie.setDescripcion("Hombre de acero");
        serie.setFecha_estreno(new Date(2005, 01, 02));
        serie.setGenero("Drama");
        serie.setUbicacionArchivo("archivo1.mp4");

        session().save(serie);

        capitulo.setUbicacionArchivo("imagen.jpg");
        capitulo.setSerie(serie);
        capitulo.setDescripcion("hola");
        capitulo.setHoras(2);
        capitulo.setMinutos(3);
        capitulo.setTitulo("titulo");

        session().save(capitulo);

        capitulo.setUbicacionArchivo("imagen2.jpg");
        capitulo.setSerie(serie);
        capitulo.setDescripcion("actualizado");
        capitulo.setHoras(4);
        capitulo.setMinutos(5);
        capitulo.setTitulo("tituloActualizado");

        session().update(capitulo);

        Capitulo capituloConsultado = session().get(Capitulo.class, capitulo.getId());

        assertThat(capituloConsultado).isEqualTo(capitulo);
    }


    @Test
    @Transactional
    @Rollback
    public void testQueDemuestraQueSeEliminaElCapitulo(){
        Capitulo capitulo = new Capitulo();
        Serie serie = new Serie();

        serie.setTitulo("Superman");
        serie.setDescripcion("Hombre de acero");
        serie.setFecha_estreno(new Date(2005, 01, 02));
        serie.setGenero("Drama");
        serie.setUbicacionArchivo("archivo1.mp4");

        session().save(serie);

        capitulo.setUbicacionArchivo("imagen.jpg");
        capitulo.setSerie(serie);
        capitulo.setDescripcion("hola");
        capitulo.setHoras(2);
        capitulo.setMinutos(3);
        capitulo.setTitulo("titulo");

        session().save(capitulo);

        session().delete(capitulo);

        assertThat(session().get(Capitulo.class, capitulo.getId())).isNull();
    }

    @Test
    @Transactional
    @Rollback
    public void testQueDemuestraQueNoSeBorraLaSerieCuandoSeEliminaUnCapitulo(){
        Capitulo capitulo = new Capitulo();
        Serie serie = new Serie();

        serie.setTitulo("Superman");
        serie.setDescripcion("Hombre de acero");
        serie.setFecha_estreno(new Date(2005, 01, 02));
        serie.setGenero("Drama");
        serie.setUbicacionArchivo("archivo1.mp4");

        session().save(serie);

        capitulo.setUbicacionArchivo("imagen.jpg");
        capitulo.setSerie(serie);
        capitulo.setDescripcion("hola");
        capitulo.setHoras(2);
        capitulo.setMinutos(3);
        capitulo.setTitulo("titulo");

        session().save(capitulo);

        session().delete(capitulo);

        assertThat(session().get(Serie.class, serie.getId())).isNotNull();
    }

}
