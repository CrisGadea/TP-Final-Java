package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.modelo.Capitulo;
import ar.edu.unlam.tallerweb1.servicios.*;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ControladorCapituloTest {

    public HttpServletRequest mockRequest(){
        HttpServletRequest request=Mockito.mock(HttpServletRequest.class);
        HttpSession httpSession= Mockito.mock(HttpSession.class);
        Mockito.when(httpSession.getAttribute(Mockito.any())).thenReturn(1L);
        Mockito.when(request.getSession()).thenReturn(httpSession);
        return request;
    }
    @Test
    public void testIrACrearCapitulo(){
        //given:
        ServicioCapituloImpl servicioCapitulo= Mockito.mock(ServicioCapituloImpl.class);
        ServicioArchivoImpl servicioArchivo=Mockito.mock(ServicioArchivoImpl.class);
        ServicioSerieImpl servicioSerie=Mockito.mock(ServicioSerieImpl.class);
        ServicioMailImpl servicioMail=Mockito.mock(ServicioMailImpl.class);
        ServicioUsuariosImpl servicioUsuario=Mockito.mock(ServicioUsuariosImpl.class);
        ControladorCapitulo controladorCapitulo=new ControladorCapitulo(servicioCapitulo,servicioArchivo,servicioSerie,
                servicioMail, servicioUsuario);
        HttpServletRequest request=mockRequest();
        //when
        ModelAndView resp=controladorCapitulo.irACrearCapitulo(1L,request);

        //then
        Assert.assertEquals("crear-capitulo",resp.getViewName());
        Assert.assertEquals(2,resp.getModel().size());
    }
    @Test
    public void testVerActualizarCapitulo(){
        //given:
        ServicioCapituloImpl servicioCapitulo= Mockito.mock(ServicioCapituloImpl.class);
        ServicioArchivoImpl servicioArchivo=Mockito.mock(ServicioArchivoImpl.class);
        ServicioSerieImpl servicioSerie=Mockito.mock(ServicioSerieImpl.class);
        ServicioMailImpl servicioMail=Mockito.mock(ServicioMailImpl.class);
        ServicioUsuariosImpl servicioUsuario=Mockito.mock(ServicioUsuariosImpl.class);
        ControladorCapitulo controladorCapitulo=new ControladorCapitulo(servicioCapitulo,servicioArchivo,servicioSerie,
                servicioMail, servicioUsuario);
        HttpServletRequest request=mockRequest();
        Mockito.when(servicioCapitulo.consultarCapituloById(Mockito.any())).thenReturn(new Capitulo());

        //when
        ModelAndView resp=controladorCapitulo.verActualizarCapitulo(1L,request);

        //then
        Assert.assertEquals("actualizar-capitulo",resp.getViewName());
        Assert.assertEquals(1,resp.getModel().size());
    }
    @Test
    public void testValidarElCapitulo(){
        //given:
        ServicioCapituloImpl servicioCapitulo= Mockito.mock(ServicioCapituloImpl.class);
        ServicioArchivoImpl servicioArchivo=Mockito.mock(ServicioArchivoImpl.class);
        ServicioSerieImpl servicioSerie=Mockito.mock(ServicioSerieImpl.class);
        ServicioMailImpl servicioMail=Mockito.mock(ServicioMailImpl.class);
        ServicioUsuariosImpl servicioUsuario=Mockito.mock(ServicioUsuariosImpl.class);
        ControladorCapitulo controladorCapitulo=new ControladorCapitulo(servicioCapitulo,servicioArchivo,servicioSerie,
                servicioMail, servicioUsuario);
        HttpServletRequest request=mockRequest();
        Mockito.when(servicioCapitulo.consultarCapituloById(Mockito.any())).thenReturn(new Capitulo());

        //when
        ModelAndView resp=controladorCapitulo.validarElCapitulo(1L,request);

        //then
        Assert.assertEquals("reproducir-capitulo",resp.getViewName());
        Assert.assertEquals(1,resp.getModel().size());
    }
}

