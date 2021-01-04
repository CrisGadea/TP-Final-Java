package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.modelo.Pelicula;
import ar.edu.unlam.tallerweb1.modelo.Serie;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioPelicula;
import ar.edu.unlam.tallerweb1.servicios.ServicioSerie;
import ar.edu.unlam.tallerweb1.servicios.ServicioUsuarios;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class controladorContenidoTest {
    /* parametros del constructor del controlador mockeados*/
    ServicioPelicula servicioPeliculaMock= mock(ServicioPelicula.class);
    ServicioSerie servicioSerieMock= mock(ServicioSerie.class);
    ServicioUsuarios servicioUsuariosMock= mock(ServicioUsuarios.class);
    HttpServletRequest request=mock(HttpServletRequest.class);
    HttpSession httpSession= Mockito.mock(HttpSession.class);
    ControladorContenido controladorContenido=new ControladorContenido(servicioPeliculaMock,
            servicioSerieMock,
            servicioUsuariosMock);
    @Test
    public void buscarSerieYPeliculasConFiltros() {

        /* parametros del metodo del controlador mockeados*/

        String titulo="titulo";
        String genero="genero";
        String valor="valor";
        String orden="orden";
        List<Pelicula> peliculas = null;
        List<Pelicula> series = null;

        ControladorContenido controladorContenido=new ControladorContenido(servicioPeliculaMock,
                servicioSerieMock,
                servicioUsuariosMock);

        when(servicioPeliculaMock.filtrarPeliculas(titulo,genero,orden,valor)).thenReturn(peliculas);
        when(servicioPeliculaMock.filtrarPeliculas(titulo,genero,orden,valor)).thenReturn(series);

        ModelAndView mav = controladorContenido.buscar(titulo,genero,valor,orden,request);

        assertThat(mav.getViewName()).isEqualTo("home");
    }

    @Test
    public void testQueCalificaSerie() {
        /* parametros del metodo del controlador mockeados*/
        String estrellas="estrellas";
        Long idSerie=1L;
        Serie serie=new Serie();

        when(servicioSerieMock.consultarSerieById(idSerie)).thenReturn(serie);

        ModelAndView mv= controladorContenido.valorSerie(estrellas,idSerie);

        assertThat(mv.getViewName()).isEqualTo("redirect:/reproducir/serie/"+idSerie);
    }

    @Test
    public void testQueCalificaPelicula() {
        /* parametros del metodo del controlador mockeados*/
        String estrellas="estrellas";
        Long idPeli=1L;
        Pelicula peli=new Pelicula();

        when(servicioPeliculaMock.consultarPeliculaById(idPeli)).thenReturn(peli);

        ModelAndView mv= controladorContenido.valorPelicula(estrellas,idPeli);

        assertThat(mv.getViewName()).isEqualTo("redirect:/reproducir/pelicula/"+idPeli);
    }

    @Test
    public void testQueAgreguePeliculaParaVerMasTarde() {
        String mastarde="masTarde";
        Long id=1L;
        String tarde="tarde";
        String tipo="tipo";
        Long idUsuario=1L;
        Usuario usuario=new Usuario();
        Pelicula pelicula=new Pelicula();


        Mockito.when(httpSession.getAttribute(Mockito.any())).thenReturn(1L);
        Mockito.when(request.getSession()).thenReturn(httpSession);
        when(servicioUsuariosMock.buscarUsuarioPorId(idUsuario)).thenReturn(usuario);
        when(servicioPeliculaMock.consultarPeliculaById(id)).thenReturn(pelicula);

        ModelAndView mv= controladorContenido.verMasTarde(mastarde,id,tarde,tipo,request);

        assertThat(mv.getViewName()).isEqualTo("redirect:/ver-mas-tarde");
    }
}
