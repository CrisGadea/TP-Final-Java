package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.controladores.ControladorPerfil;
import ar.edu.unlam.tallerweb1.modelo.Perfil;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioArchivo;
import ar.edu.unlam.tallerweb1.servicios.ServicioPerfil;
import ar.edu.unlam.tallerweb1.servicios.ServicioUsuarios;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.any;

public class ControladorPerfilTest extends SpringTest {

    public ServicioUsuarios getServicioUsuarios(){
        return Mockito.mock(ServicioUsuarios.class);
    }

    public ServicioPerfil getServicioPerfil(){
        return Mockito.mock(ServicioPerfil.class);
    }

    public ServicioArchivo getServicioArchivo(){
        return Mockito.mock(ServicioArchivo.class);
    }

    private Perfil getPerfil() {
        return Mockito.mock(Perfil.class);
    }

    private Usuario getUsuario() {
        return Mockito.mock(Usuario.class);
    }

    @Test
    @Transactional @Rollback
    public void sePuedeMostrarUnPerfilDesdeElControlador(){
        // Preparación
        /*
         *  Preparamos los objetos necesarios para realizar las pruebas del controller.
         */

        Usuario usuario = this.getUsuario();
        Perfil perfil = this.getPerfil();
        Perfil perfil2 = this.getPerfil();
        Perfil perfil3 = this.getPerfil();

        List<Perfil> perfiles = new ArrayList<>();
        perfiles.add(perfil);
        perfiles.add(perfil2);
        perfiles.add(perfil3);

        /*
         *  Se realizan los llamados a los metodos para obtener los servicios mocks.
         */
        ServicioArchivo servicioArchivo = this.getServicioArchivo();
        ServicioPerfil servicioPerfil = this.getServicioPerfil();
        ServicioUsuarios servicioUsuarios = this.getServicioUsuarios();

        /*
         *  Se crea una instancia del subject under test, generando los mocks de los servicios
         *   que necesita el controller.
         */
        ControladorPerfil controller = new ControladorPerfil(servicioPerfil,servicioUsuarios,servicioArchivo);

        /*
         *  Mockeamos el HttpSession
         */
        HttpSession httpSession=Mockito.mock(HttpSession.class);

        /*
         *  Mockeamos el HttpServletRequest
         */
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);

        /*
         *  Generamos los llamados correspondientes para que el metodo se ejecute de forma correcta.
         */
        Mockito.when(httpSession.getAttribute("USUARIO_ID")).thenReturn(1L);
        Mockito.when(request.getSession()).thenReturn(httpSession);
        Mockito.when(servicioUsuarios.buscarUsuarioPorId(any())).thenReturn(usuario);
        Mockito.when(servicioPerfil.listarPerfilesPorUsuario(any())).thenReturn(perfiles);

        // Implementación

        /*
         *  Llamamos al metodo para probar, pasandole como parametro el request mockeado.
         */
        ModelAndView modelRes = controller.mostrarPerfil(request);

        // Pruebas

        Assert.assertNotNull(modelRes);
        Assert.assertEquals(modelRes.getViewName(),"perfil");
        Assert.assertTrue(modelRes.getModel().containsKey("usuario"));
        Assert.assertTrue(modelRes.getModel().containsKey("perfiles"));
        assertThat(modelRes.getModel().get("usuario")).isEqualTo(usuario);
        assertThat(modelRes.getModel().get("perfiles")).isEqualTo(perfiles);
    }

    @Test
    @Transactional @Rollback
    public void sePuedeCrearUnPerfilDesdeElControlador(){

        // Preparación

        Usuario usuario = this.getUsuario();
        Perfil perfil = this.getPerfil();

        MultipartFile file = Mockito.mock(MultipartFile.class);

        ServicioArchivo servicioArchivo = this.getServicioArchivo();
        ServicioPerfil servicioPerfil = this.getServicioPerfil();
        ServicioUsuarios servicioUsuarios = this.getServicioUsuarios();

        ControladorPerfil controller = new ControladorPerfil(servicioPerfil,servicioUsuarios,servicioArchivo);

        HttpSession httpSession=Mockito.mock(HttpSession.class);

        ServletContext servletContext = Mockito.mock(ServletContext.class);

        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);

        Mockito.when(httpSession.getAttribute("USUARIO_ID")).thenReturn(1L);
        Mockito.when(request.getSession()).thenReturn(httpSession);
        Mockito.when(httpSession.getServletContext()).thenReturn(servletContext);
        Mockito.when(servletContext.getRealPath(any())).thenReturn("real_path");
        Mockito.when(servicioUsuarios.buscarUsuarioPorId(any())).thenReturn(usuario);
        Mockito.when(servicioArchivo.guardarImagen(any(),any())).thenReturn("Mock");

        // Implementación

        ModelAndView modelRes = controller.crearPerfil(perfil,usuario.getId(), perfil.getNombre(),file, request);

        // Pruebas

        Assert.assertNotNull(modelRes);
        Assert.assertEquals(modelRes.getViewName(),"redirect:/perfil");
    }

    @Test
    @Transactional @Rollback
    public void sePuedeIrACrearUnPerfilDesdeElControlador(){
        // Preparación


        Usuario usuario = this.getUsuario();

        ServicioArchivo servicioArchivo = this.getServicioArchivo();
        ServicioPerfil servicioPerfil = this.getServicioPerfil();
        ServicioUsuarios servicioUsuarios = this.getServicioUsuarios();

        ControladorPerfil controller = new ControladorPerfil(servicioPerfil,servicioUsuarios,servicioArchivo);

        HttpSession httpSession=Mockito.mock(HttpSession.class);
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);

        Mockito.when(httpSession.getAttribute("USUARIO_ID")).thenReturn(1L);
        Mockito.when(request.getSession()).thenReturn(httpSession);
        Mockito.when(servicioUsuarios.buscarUsuarioPorId(any())).thenReturn(usuario);

        // Implementación

        ModelAndView modelRes = controller.irACrearPerfil(usuario.getId(), request);

        // Pruebas

        Assert.assertNotNull(modelRes);
        Assert.assertEquals(modelRes.getViewName(),"crearPerfil");
        Assert.assertTrue(modelRes.getModel().containsKey("idUsuario"));
        Assert.assertTrue(modelRes.getModel().containsKey("perfil"));

    }

    @Test
    @Transactional @Rollback
    public void sePuedeVerUnPerfilDesdeElControlador(){
        // Preparación

        Usuario usuario = this.getUsuario();

        ServicioArchivo servicioArchivo = this.getServicioArchivo();
        ServicioPerfil servicioPerfil = this.getServicioPerfil();
        ServicioUsuarios servicioUsuarios = this.getServicioUsuarios();

        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpSession httpSession=Mockito.mock(HttpSession.class);


        Mockito.when(httpSession.getAttribute("USUARIO_ID")).thenReturn(1L);
        Mockito.when(request.getSession()).thenReturn(httpSession);
        Mockito.when(servicioUsuarios.buscarUsuarioPorId(any())).thenReturn(usuario);

        ControladorPerfil controller = new ControladorPerfil(servicioPerfil,servicioUsuarios,servicioArchivo);

        // Implementación

        ModelAndView modelRes = controller.mostrarPerfil(request);

        // Pruebas

        Assert.assertNotNull(modelRes);
        Assert.assertEquals(modelRes.getViewName(),"perfil");
        Assert.assertTrue(modelRes.getModel().containsKey("perfiles"));

    }

    @Test
    @Transactional @Rollback
    public void sePuedeEliminarUnPerfilDesdeElControlador(){
        // Preparación

        ServicioArchivo servicioArchivo = this.getServicioArchivo();
        ServicioPerfil servicioPerfil = this.getServicioPerfil();
        ServicioUsuarios servicioUsuarios = this.getServicioUsuarios();

        Perfil perfil = this.getPerfil();

        ControladorPerfil controller = new ControladorPerfil(servicioPerfil,servicioUsuarios,servicioArchivo);

        Mockito.when(servicioPerfil.consultarPerfilPorId(any())).thenReturn(perfil);
        // Implementación

        ModelAndView modelRes = controller.eliminarPerfil(perfil.getId());

        // Pruebas

        Assert.assertNotNull(modelRes);
        Assert.assertEquals(modelRes.getViewName(),"redirect:/perfil");
    }

}
