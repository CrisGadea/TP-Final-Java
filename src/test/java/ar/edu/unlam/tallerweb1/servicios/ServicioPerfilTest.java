package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.Perfil;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioPerfil;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.any;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

public class ServicioPerfilTest extends SpringTest {
    @Test
    @Transactional
    @Rollback
    public void pruebaConexion(){
        assertThat(session().isConnected()).isTrue();
    }

    public RepositorioPerfil getRepository(){ return Mockito.mock(RepositorioPerfil.class); }

    public Perfil getPerfil(Usuario usuario) {
        Perfil perfil = new Perfil();
        perfil.setNombre("Juan Perez");
        perfil.setUsuario(usuario);
        perfil.setUbicacionArchivo("#");
        return perfil;
    }

    public List<Perfil> getPerfiles(Usuario usuario) {
        Perfil perfil1 = getPerfil(usuario);
        Perfil perfil2 = getPerfil(usuario);
        List<Perfil> perfiles = new ArrayList<>();
        perfiles.add(perfil1);
        perfiles.add(perfil2);
        return perfiles;
    }

    @Test
    @Transactional
    @Rollback
    public void alConsultarUnPerfilDesdeElServicioMeDevuelveElPerfilBuscado(){
        // Preparacion
        Usuario usuario = new Usuario();
        usuario.setEmail("mail@mail.com");
        usuario.setPassword("1234");
        usuario.setUsername("admin");
        Perfil perfil = this.getPerfil(usuario);
        RepositorioPerfil repository = this.getRepository();
        Mockito.when(repository.consultarPerfil(any(Perfil.class))).thenReturn(perfil);
        ServicioPerfilImpl servicioPerfil=new ServicioPerfilImpl(repository);

        // Implementacion
        Perfil res = servicioPerfil.consultarPerfil(perfil);

        // Prueba
        Assert.assertNotNull(res);
        Assert.assertEquals(res,perfil);
    }

    @Test
    @Transactional
    @Rollback
    public void alConsultarUnPerfilPorIdMeDevuelveElPerfilBuscado(){
        // Preparacion
        Usuario usuario = getUsuario();
        Perfil perfil = this.getPerfil(usuario);
        RepositorioPerfil repository = this.getRepository();

        // Implementacion
        Mockito.when(repository.consultarPerfilPorId(any())).thenReturn(perfil);
        Perfil res = repository.consultarPerfilPorId(perfil.getId());

        // Prueba
        Assert.assertNotNull(res);
        Assert.assertEquals(res,perfil);
    }

    @Test
    @Transactional
    @Rollback
    public void sePuedeListarLosPerfiles(){
        // Preparacion
        RepositorioPerfil repository = this.getRepository();
        Usuario usuario = getUsuario();
        List<Perfil> perfiles = getPerfiles(usuario);

        // Implementacion
        Mockito.when(repository.listarPerfiles()).thenReturn(perfiles);
        List<Perfil> res = repository.listarPerfiles();

        // Prueba
        Assert.assertNotNull(res);
        Assert.assertEquals(res,perfiles);
    }

    @Test
    @Transactional
    @Rollback
    public void sePuedeCrearUnPerfilNuevo(){
        // Preparacion
        Usuario usuario = getUsuario();
        Perfil perfil = this.getPerfil(usuario);
        RepositorioPerfil repository = this.getRepository();

        // Implementacion
        repository.crearPerfil(perfil);
        Mockito.when(repository.consultarPerfilPorId(any())).thenReturn(perfil);

        Perfil perfilBuscado = repository.consultarPerfilPorId(perfil.getId());

        // Prueba
        Assert.assertNotNull(perfilBuscado);
        Assert.assertEquals(perfilBuscado,perfil);
    }

    @Test
    @Transactional
    @Rollback
    public void sePuedeEditarUnPerfil(){
        // Preparacion
        Usuario usuario = getUsuario();
        Perfil perfil = this.getPerfil(usuario);
        RepositorioPerfil repository = this.getRepository();

        // Implementacion

        // Primero creamos el perfil
        repository.crearPerfil(perfil);
        Mockito.when(repository.consultarPerfilPorId(any())).thenReturn(perfil);

        Perfil perfilBuscado = repository.consultarPerfilPorId(perfil.getId());

        String nombreAEditar = "Juan Perez";

        // Luego, a ese mismo perfil lo modificamos
        perfilBuscado.setNombre(nombreAEditar);
        repository.editarPerfil(perfilBuscado);

        // Prueba
        Assert.assertNotNull(perfilBuscado);
        Assert.assertEquals(nombreAEditar, perfilBuscado.getNombre());

    }

    @Test
    @Transactional
    @Rollback
    public void sePuedeEliminarUnPerfil(){
        // Preparacion
        Usuario usuario = getUsuario();
        Perfil perfil = this.getPerfil(usuario);
        RepositorioPerfil repository = this.getRepository();
    }

    @Test
    @Transactional
    @Rollback
    public void sePuedeListarTodosLosPerfilesQueTieneUnUsuario(){
        // Preparacion
        Usuario usuario = getUsuario();
        Perfil perfil = this.getPerfil(usuario);
        RepositorioPerfil repository = this.getRepository();
    }

    private Usuario getUsuario() {
        Usuario usuario = new Usuario();
        usuario.setEmail("mail@mail.com");
        usuario.setPassword("1234");
        usuario.setUsername("admin");
        return usuario;
    }


}
