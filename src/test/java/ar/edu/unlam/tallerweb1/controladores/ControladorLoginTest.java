package ar.edu.unlam.tallerweb1.controladores;
import ar.edu.unlam.tallerweb1.controladores.ControladorLogin;
import ar.edu.unlam.tallerweb1.servicios.*;
import org.junit.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioLogin;
import org.springframework.web.servlet.ModelAndView;

public class ControladorLoginTest {
    @Test
    public void validarLoginCorrectoTeMandaAHome(){
        Usuario usuario=new Usuario();
        usuario.setEnabled(true);
        ServicioLogin servicioLoginMock= mock(ServicioLogin.class);
        ServicioPelicula servicioPeliculaMock= mock(ServicioPelicula.class);
        ServicioSerie servicioSerieMock=mock(ServicioSerie.class);
        ServicioSuscripcion servicioSuscripcionMock= mock(ServicioSuscripcion.class);

        HttpServletRequest requestMock = mock(HttpServletRequest.class);
        HttpSession sessionMock = mock(HttpSession.class);

        ControladorLogin controladorLogin = new ControladorLogin(servicioLoginMock,servicioPeliculaMock,servicioSerieMock, servicioSuscripcionMock);

        when(requestMock.getSession()).thenReturn(sessionMock);
        when(servicioLoginMock.consultarUsuario(usuario)).thenReturn(usuario);

        ModelAndView mav = controladorLogin.validarLogin(usuario,requestMock);

        assertThat(mav.getViewName()).isEqualTo("redirect:/home");
    }
}
