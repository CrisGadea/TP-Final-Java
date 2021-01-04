package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.modelo.Pelicula;
import ar.edu.unlam.tallerweb1.modelo.Serie;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioArchivo;
import ar.edu.unlam.tallerweb1.servicios.ServicioMail;
import ar.edu.unlam.tallerweb1.servicios.ServicioPelicula;
import ar.edu.unlam.tallerweb1.servicios.ServicioUsuarios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@Controller
public class ControladorPelicula {

    private final ServicioPelicula servicioPelicula;
    private final ServicioArchivo servicioArchivo;
    private final ServicioUsuarios servicioUsuarios;
    private final ServicioMail servicioMail;

    @Autowired
    public ControladorPelicula(ServicioPelicula servicioPelicula, ServicioArchivo servicioArchivo, ServicioUsuarios servicioUsuarios, ServicioMail servicioMail){
        this.servicioPelicula = servicioPelicula;
        this.servicioArchivo = servicioArchivo;
        this.servicioUsuarios = servicioUsuarios;
        this.servicioMail = servicioMail;
    }

    @RequestMapping ( path = "/crear-pelicula", method = RequestMethod.GET )
    public ModelAndView irACrearPelicula(HttpServletRequest request){
        //Si no hay usuario registrado, redirigir al login
        if( request.getSession().getAttribute("USUARIO_ID") == null ){
            return new ModelAndView("redirect:/login");
        }

        ModelMap model = new ModelMap();
        Pelicula pelicula = new Pelicula();
        model.put("pelicula", pelicula);
        model.put("rol",request.getSession().getAttribute("ROL"));
        return new ModelAndView("crear-pelicula",model);
    }

    @RequestMapping ( path = "/crear-pelicula", method = RequestMethod.POST )
    public ModelAndView validarPelicula(@ModelAttribute("pelicula") Pelicula pelicula,
                                        @RequestParam("portada") MultipartFile file,
                                         @RequestParam("fecha-estreno") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fecha_estreno,
                                        HttpServletRequest request){
        ModelMap model = new ModelMap();

        Pelicula peliculaBuscada = servicioPelicula.consultarPelicula(pelicula);

        if (peliculaBuscada != null) {
            // si la pelicula existe, agrega un mensaje de error en el modelo.
            model.put("error", "El titulo de la pelicula ya se encuentra en uso");
            return new ModelAndView("crear-pelicula",model);
        } else {
            String path = request.getSession().getServletContext().getRealPath("/image/");
            //String path = request.getContextPath()+"/src/main/webapp/images/";
            String contenido = servicioArchivo.guardarImagen(file, path);
           if(contenido != null){
                pelicula.setUbicacionArchivo(contenido);
                pelicula.setFecha_estreno(fecha_estreno);
                pelicula.setVisualizaciones(0);
                servicioPelicula.registrarPelicula(pelicula);
               String texto = "<p> <b>¡" + pelicula.getTitulo() + " ha llegado a MagiosTV!</b>"
                       + " Disfruta de todas nuestras nuevas series y pelis que tenemos para vos</p>\n"
                       + "<p><b>"+pelicula.getTitulo()+"</b> : "+pelicula.getDescripcion()+"</p>";

                Pelicula peliculaRegistrada=servicioPelicula.consultarPelicula(pelicula);
                if (peliculaRegistrada==null){
                    model.put("error", "Pelicula no se pudo registrar");
                    return new ModelAndView("crear-pelicula", model);
                }
               List<Usuario> usuarios = servicioUsuarios.listarUsuarios();
               try {
                   for (Usuario usuario:
                           usuarios) {
                       servicioMail.enviar(usuario.getEmail(), texto);
                   }
               }catch (MessagingException e){
                   e.getMessage();
               }
            }
       }
        return new ModelAndView("redirect:/home", model);
    }
    @RequestMapping ( path = "/reproducir/pelicula/{id}", method = RequestMethod.GET )
    public ModelAndView validarPelicula(@PathVariable("id") Long id, HttpServletRequest request){
        //Si no hay usuario registrado, redirigir al login
        if( request.getSession().getAttribute("USUARIO_ID") == null ){
            return new ModelAndView("redirect:/login");
        }

        ModelMap model = new ModelMap();
        Pelicula pelicula = servicioPelicula.consultarPeliculaById(id);
        if(pelicula != null){
            model.put("pelicula", pelicula);
            model.put("rol",request.getSession().getAttribute("ROL"));

            //agregamos la pelicula al historial de peliculas vistas del usuario loguiado
            Long idUsuario = (Long) request.getSession().getAttribute("USUARIO_ID");
            if (!servicioUsuarios.ObtenerPeliculasVistas(idUsuario).contains(pelicula)) {
                pelicula.setVisualizaciones(pelicula.getVisualizaciones()+1);
                servicioPelicula.actualizarPelicula(pelicula);
            }
            servicioUsuarios.agregarPeliculaAVistas(id, idUsuario);
        }
        return new ModelAndView("ver-pelicula", model);
    }

    @RequestMapping ( path = "/actualizar-pelicula/{id}", method = RequestMethod.GET )
    public ModelAndView verActualizarPelicula(@PathVariable("id") Long id ){
        ModelMap model = new ModelMap();

        Pelicula peliculaAActualizar = servicioPelicula.consultarPeliculaById( id );

        if(peliculaAActualizar == null){
            return new ModelAndView("redirect:/home");
        }

        model.put("pelicula", peliculaAActualizar);
        return new ModelAndView("actualizar-pelicula", model);
    }

    @RequestMapping ( path = "/actualizar-pelicula/{id}", method = RequestMethod.POST )
    public ModelAndView actualizarPelicula(@PathVariable("id") Long id,
                                           @ModelAttribute("pelicula") Pelicula pelicula,
                                           @RequestParam("portada") MultipartFile file,
                                           @RequestParam("fecha-estreno") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fecha_estreno,
                                           HttpServletRequest request){
        pelicula.setFecha_estreno(fecha_estreno);
        pelicula.setId(id);
        Pelicula peliculaConsultada = servicioPelicula.consultarPeliculaById( id );

        if(peliculaConsultada == null){
            return new ModelAndView("redirect:/home");
        }

        if(file.isEmpty()){
            //Si no recibio ningun archivo, no actualizo la portada
            //por lo tanto va a seguir teniendo la misma ubicacion del archivo anterior
            pelicula.setUbicacionArchivo( peliculaConsultada.getUbicacionArchivo() );
        }else{
            String path = request.getSession().getServletContext().getRealPath("/image/");
            String contenido = servicioArchivo.guardarImagen(file, path);

            if(contenido == null){
                //si el contenido es null, no se pudo guardar la imagen correctamente
                //por lo tanto, dejamos la anterior
                pelicula.setUbicacionArchivo( peliculaConsultada.getUbicacionArchivo() );
            }else {
                //si se guardo correctamente la imagen, borramos el archivo anterior
                servicioArchivo.eliminarArchivo( path + peliculaConsultada.getUbicacionArchivo());
                pelicula.setUbicacionArchivo(contenido);
            }
        }
        servicioPelicula.actualizarPelicula(pelicula);
        return new ModelAndView("redirect:/reproducir/pelicula/" + id);
    }
}
