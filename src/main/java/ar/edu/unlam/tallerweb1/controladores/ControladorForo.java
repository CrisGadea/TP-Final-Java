package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.modelo.*;
import ar.edu.unlam.tallerweb1.servicios.ServicioMensaje;
import ar.edu.unlam.tallerweb1.servicios.ServicioPosteo;
import ar.edu.unlam.tallerweb1.servicios.ServicioReporte;
import ar.edu.unlam.tallerweb1.servicios.ServicioUsuarios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Controller
public class ControladorForo {
    private final ServicioPosteo servicioPosteo;
    private final ServicioUsuarios servicioUsuarios;
    private final ServicioMensaje servicioMensaje;
    private final ServicioReporte servicioReporte;

    @Autowired
    public ControladorForo(ServicioPosteo servicioPosteo, ServicioUsuarios servicioUsuarios, ServicioMensaje servicioMensaje,
                           ServicioReporte servicioReporte){
        this.servicioPosteo = servicioPosteo;
        this.servicioUsuarios = servicioUsuarios;
        this.servicioMensaje = servicioMensaje;
        this.servicioReporte=servicioReporte;
    }

    @RequestMapping( path = "/foro", method = RequestMethod.GET )
    public ModelAndView irAForo(HttpServletRequest request){
        //Si no hay usuario registrado, redirigir al login
        if( request.getSession().getAttribute("USUARIO_ID") == null ){
            return new ModelAndView("redirect:/login");
        }

        ModelMap model = new ModelMap();
        List<Posteo> posteos = servicioPosteo.listarPosteos();
        model.put("posteos",posteos);
        model.put("rol",request.getSession().getAttribute("ROL"));
        return new ModelAndView("foro",model);
    }

    @RequestMapping( path = "/crear-posteo", method = RequestMethod.GET )
    public ModelAndView irACreaPosteo(HttpServletRequest request){
        //Si no hay usuario registrado, redirigir al login
        if( request.getSession().getAttribute("USUARIO_ID") == null ){
            return new ModelAndView("redirect:/login");
        }

        ModelMap model = new ModelMap();
        Posteo posteo = new Posteo();
        model.put("posteo",posteo);
        return new ModelAndView("crear-posteo",model);
    }

    @RequestMapping( path = "/crear-posteo", method = RequestMethod.POST )
    public ModelAndView ValidarPosteo(@RequestParam("titulo") String titulo,
                                      @RequestParam("genero") String genero,
                                      @RequestParam("informacion")String informacion,
                                      HttpServletRequest request){
        ModelMap model = new ModelMap();
        Long id = (Long) request.getSession().getAttribute("USUARIO_ID");
        Usuario usuario = servicioUsuarios.buscarUsuarioPorId(id);
        if(usuario != null){
            Posteo posteo=new Posteo();
            Date date = new Date();
            posteo.setFecha_creacion(date);
            posteo.setCreador(usuario);
            posteo.setTitulo(titulo);
            posteo.setGenero(genero);
            posteo.setInformacion(informacion);
            servicioPosteo.guardarPosteo(posteo);
        }
        return new ModelAndView("redirect:/foro", model);
    }

    @RequestMapping(path = "/eliminarPosteo/{id}", method = RequestMethod.GET)
    public ModelAndView eliminarPosteo(@PathVariable("id") Long id,
                                         HttpServletRequest request){
        //Si no hay usuario registrado, redirigir al login
        if( request.getSession().getAttribute("USUARIO_ID") == null ){
            return new ModelAndView("redirect:/login");
        }

        ModelMap model= new ModelMap();

        //Serie serie=servicioSerie.consultarSerieById(id);
        Posteo posteoConsultado=servicioPosteo.getPosteoById(id);
        if(posteoConsultado==null){
            model.put("error","error al intentar eliminar Posteo");
            return new ModelAndView("redirect: /home",model);
        }

        servicioPosteo.eliminarPosteo(posteoConsultado.getId());
        return new ModelAndView("redirect:/foro",model);
    }


    @RequestMapping( path = "/ver-posteo/{id}", method = RequestMethod.GET )
    public ModelAndView VerPosteo(@PathVariable("id") Long id,
                                  HttpServletRequest request){
        //Si no hay usuario registrado, redirigir al login
        if( request.getSession().getAttribute("USUARIO_ID") == null ){
            return new ModelAndView("redirect:/login");
        }

        ModelMap model = new ModelMap();

        Posteo posteo = servicioPosteo.getPosteoById(id);

        List<Mensaje> mensajes = servicioMensaje.getListaMensajesByPosteo(posteo);
        Long idUsuario = (Long) request.getSession().getAttribute("USUARIO_ID");
        model.put("posteo",posteo);
        model.put("mensajes", mensajes);
        model.put("idUsuario", idUsuario);
        model.put("rol",request.getSession().getAttribute("ROL"));
        return new ModelAndView("posteo", model);
    }

    @RequestMapping( path = "/actualizar-posteo/{id}", method = RequestMethod.GET )
    public ModelAndView irAAcatualizarPosteo(@PathVariable("id") Long id,HttpServletRequest request){
        //Si no hay usuario registrado, redirigir al login
        if( request.getSession().getAttribute("USUARIO_ID") == null ){
            return new ModelAndView("redirect:/login");
        }

        ModelMap model = new ModelMap();

       Posteo posteoAActualizar=servicioPosteo.getPosteoById(id);
       if(posteoAActualizar==null){
           return new ModelAndView("redirect:/foro");
       }
        model.put("posteo",posteoAActualizar);
        return new ModelAndView("actualizar-posteo",model);
    }


    @RequestMapping ( path = "/actualizar-posteo/{id}", method = RequestMethod.POST )
    public ModelAndView actualizarposteo(@PathVariable("id") Long id,
                                         @ModelAttribute("posteo") Posteo posteo,
                                         HttpServletRequest request){

        Long idUsuario = (Long) request.getSession().getAttribute("USUARIO_ID");
        Usuario usuario = servicioUsuarios.buscarUsuarioPorId(idUsuario);
        Date now = new Date();

        posteo.setId(id);
        Posteo posteoConsultado = servicioPosteo.getPosteoById(id);

        if(posteoConsultado == null) {
            return new ModelAndView("redirect:/foro");
        }

        posteo.setCreador(usuario);
        posteo.setFecha_creacion(now);
        servicioPosteo.actualizarPosteo(posteo);
        return new ModelAndView("redirect:/ver-posteo/"+id);
        //return new ModelAndView("redirect:/reproducir/serie/" + id);
    }

    @RequestMapping( path = "/crear-reporte/{id}", method = RequestMethod.GET )
    public ModelAndView irAReportarPosteo(@PathVariable("id") Long idPosteo,HttpServletRequest request){
        //Si no hay usuario registrado, redirigir al login
        if( request.getSession().getAttribute("USUARIO_ID") == null ){
            return new ModelAndView("redirect:/login");
        }

        ModelMap model = new ModelMap();
        Reporte reporte=new Reporte();

        Posteo posteoAReportar=servicioPosteo.getPosteoById(idPosteo);
        if(posteoAReportar==null){
            return new ModelAndView("redirect:/foro");
        }
        model.put("idPosteo",idPosteo);
        model.put("reporte",reporte);

        return new ModelAndView("crear-reporte",model);
    }


    @RequestMapping ( path = "/crear-reporte/{idPosteo}", method = RequestMethod.POST )
    public ModelAndView validarReporte(@PathVariable("idPosteo") Long idPosteo,
                                       @RequestParam("titulo") String titulo,
                                       @RequestParam("razon") String razon,
                                       @RequestParam("descripcion") String descripcion){
        ModelMap model = new ModelMap();

        Posteo posteo=servicioPosteo.getPosteoById(idPosteo);
        Date now = new Date();
        Reporte reporte=new Reporte();

        reporte.setTitulo(titulo);
        reporte.setRazon(razon);
        reporte.setDescripcion(descripcion);
        reporte.setPosteo(posteo);
        reporte.setFecha_estreno(now);

        servicioReporte.registrarReporte(reporte);

        return new ModelAndView("redirect:/ver-posteo/"+idPosteo, model);
    }






    @RequestMapping ( path = "/ver-reportes/{id}", method = RequestMethod.GET )
    public ModelAndView validarSerie(@PathVariable("id") Long id, HttpServletRequest request){
        //Si no hay usuario registrado, redirigir al login
        if( request.getSession().getAttribute("USUARIO_ID") == null ){
            return new ModelAndView("redirect:/login");
        }

        ModelMap model = new ModelMap();


        Posteo posteo = servicioPosteo.getPosteoById(id);
        if(posteo != null){
            List<Reporte> reportes = servicioReporte.obtenerListaReportePorPosteo(posteo);
            model.put("posteo", posteo);
            model.put("reportes", reportes);

        }
        return new ModelAndView("verReportes", model);
    }






    @RequestMapping(path = "/crear-mensaje/{id}", method = RequestMethod.POST)
    public ModelAndView CrearMensaje(@PathVariable("id") Long idPosteo,
                                     @RequestParam("comentario") String comentario,
                                     HttpServletRequest request){
        Posteo posteo = servicioPosteo.getPosteoById(idPosteo);
        Long idUsuario = (Long) request.getSession().getAttribute("USUARIO_ID");
        Usuario usuario = servicioUsuarios.buscarUsuarioPorId(idUsuario);
        if(posteo != null && usuario != null){
            Date now = new Date();
            Mensaje mensaje = new Mensaje();
            mensaje.setMensaje(comentario);
            mensaje.setPosteo(posteo);
            mensaje.setCreador(usuario);
            mensaje.setFecha_creacion(now);
            servicioMensaje.guardarMensaje(mensaje);
        }
        return new ModelAndView("redirect:/ver-posteo/"+idPosteo);
    }

    @RequestMapping(path = "/eliminarMensaje/{id}", method = RequestMethod.GET)
    public ModelAndView eliminarMensaje(@PathVariable("id") Long id,
                                       HttpServletRequest request){
        //Si no hay usuario registrado, redirigir al login
        if( request.getSession().getAttribute("USUARIO_ID") == null ){
            return new ModelAndView("redirect:/login");
        }

        ModelMap model= new ModelMap();

        Mensaje mensajeConsultado=servicioMensaje.getMensajeById(id);

        if(mensajeConsultado==null){
            model.put("error","error al intentar eliminar Posteo");
            return new ModelAndView("redirect: /home",model);
        }

        Posteo posteo = mensajeConsultado.getPosteo();
        servicioMensaje.eliminarMensaje(mensajeConsultado.getId());

        return new ModelAndView("redirect:../ver-posteo/" + posteo.getId());
    }

    @RequestMapping( path = "/actualizar-mensaje/{id}", method = RequestMethod.GET )
    public ModelAndView irAAcatualizarMensaje(@PathVariable("id") Long id,HttpServletRequest request){
        //Si no hay usuario registrado, redirigir al login
        if( request.getSession().getAttribute("USUARIO_ID") == null ){
            return new ModelAndView("redirect:/login");
        }

        ModelMap model = new ModelMap();

        Mensaje mensajeAActualizar=servicioMensaje.getMensajeById(id);

        if(mensajeAActualizar==null){
            return new ModelAndView("redirect:/foro");
        }

        Posteo posteo=mensajeAActualizar.getPosteo();

        model.put("posteo",posteo);
        model.put("mensaje",mensajeAActualizar);
        return new ModelAndView("actualizar-mensaje",model);
    }


    @RequestMapping ( path = "/actualizar-mensaje/{id}", method = RequestMethod.POST )
    public ModelAndView actualizarMensaje(@PathVariable("id") Long id,
                                         @ModelAttribute("mensaje") Mensaje mensaje,
                                         HttpServletRequest request){


        Mensaje mensjeConsultado = servicioMensaje.getMensajeById(mensaje.getId());
        Long idUsuario = (Long) request.getSession().getAttribute("USUARIO_ID");

        if(mensjeConsultado == null) {
            return new ModelAndView("redirect:/foro");
        }

        Usuario usuario=servicioUsuarios.buscarUsuarioPorId(idUsuario);

        Date now = new Date();
        mensaje.setId(id);
        //mensaje.setPosteo(posteo);
        mensaje.setCreador(usuario);
        mensaje.setFecha_creacion(now);
        servicioMensaje.actualizarMensaje(mensaje);

        return new ModelAndView("redirect:/ver-posteo/"+mensaje.getPosteo().getId());

    }

    @RequestMapping(path = "/foro/ordenar-por-genero", method = RequestMethod.POST)
    public ModelAndView agruparForoPorGenero(@ModelAttribute("posteo") Posteo posteo,
                                              @ModelAttribute("genero") String genero,
                                              HttpServletRequest request){
        if( request.getSession().getAttribute("USUARIO_ID") == null ){
            return new ModelAndView("redirect:/login");
        }

        ModelMap model = new ModelMap();

        List<Posteo> posteosOrdenados = servicioPosteo.listarPosteosPorGenero(genero);

        model.put("posteosOrdenados",posteosOrdenados);

        return new ModelAndView("redirect:/foro", model);
    }
}
