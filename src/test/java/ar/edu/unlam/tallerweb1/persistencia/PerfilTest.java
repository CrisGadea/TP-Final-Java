package ar.edu.unlam.tallerweb1.persistencia;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.Perfil;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import static org.assertj.core.api.Assertions.assertThat;

public class PerfilTest extends SpringTest {

    @Test
    @Transactional @Rollback
    public void pruebaConexion(){
        assertThat(session().isConnected()).isTrue();
    }

    @Test
    @Transactional @Rollback
    public void testQuePermiteCrearUnPerfilNuevo(){
        // Preparación
        Perfil perfil = new Perfil();
        // Implementación
        perfil.setNombre("Cris");
        session().save(perfil);
        // Prueba
        assertThat(perfil.getId()).isNotNull();
    }
}
