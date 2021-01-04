package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.SpringTest;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

public class ControladorPeliculaTest extends SpringTest {

    @Test
    @Transactional
    @Rollback
    public void pruebaConexion(){
        assertThat(session().isConnected()).isTrue();
    }
}
