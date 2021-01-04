package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.modelo.Capitulo;
import ar.edu.unlam.tallerweb1.modelo.Serie;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Controller
public class ControladorSerie {
    private final ServicioSerie servicioSerie;
    private final ServicioArchivo servicioArchivo;
    private final ServicioCapitulo servicioCapitulo;
    private final ServicioUsuarios servicioUsuarios;
    private final ServicioMail servicioMail;


    @Autowired
    public ControladorSerie (ServicioSerie servicioSerie,
                             ServicioArchivo servicioArchivo,
                             ServicioCapitulo servicioCapitulo,
                             ServicioUsuarios servicioUsuarios,
                             ServicioMail servicioMail){
        this.servicioSerie=servicioSerie;
        this.servicioArchivo=servicioArchivo;
        this.servicioCapitulo=servicioCapitulo;
        this.servicioUsuarios = servicioUsuarios;
        this.servicioMail = servicioMail;

    }


    @RequestMapping ( path = "/crear-serie", method = RequestMethod.GET )
    public ModelAndView irACrearSerie( HttpServletRequest request){
        //Si no hay usuario registrado, redirigir al login
        if( request.getSession().getAttribute("USUARIO_ID") == null ){
            return new ModelAndView("redirect:/login");
        }

        ModelMap model = new ModelMap();
        Serie serie = new Serie();
        model.put("serie", serie);
        model.put("rol",request.getSession().getAttribute("ROL"));
        return new ModelAndView("/crear_serie",model);
    }


    @RequestMapping ( path = "/crear_serie", method = RequestMethod.POST )
    public ModelAndView validarSerie(@ModelAttribute("serie") Serie serie,
                                     @RequestParam("portada") MultipartFile file,
                                     @RequestParam("fecha-estreno") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fecha_estreno,
                                     HttpServletRequest request){
        ModelMap model = new ModelMap();

        Serie serieBuscada = servicioSerie.consultarSerie(serie);

        if (serieBuscada != null) {
            // si la serie existe, agrega un mensaje de error en el modelo.
            model.put("error", "El titulo de la serie ya se encuentra en uso");
            return new ModelAndView("/crear_serie",model);
        } else {
            String path = request.getSession().getServletContext().getRealPath("/image/");
            //String path = request.getContextPath()+"/src/main/webapp/images/";
            String contenido = servicioArchivo.guardarImagen(file, path);
            if(contenido != null){
                serie.setUbicacionArchivo(contenido);
                serie.setFecha_estreno(fecha_estreno);
                serie.setVisualizaciones(0);
                servicioSerie.registrarSerie(serie);
                Serie serieRegistrada=servicioSerie.consultarSerie(serie);
                if (serieRegistrada==null){
                    model.put("error", "serie no se pudo registrar");
                    return new ModelAndView("crear_serie", model);
                }
                String texto = "<p> <b>¡" + serie.getTitulo() + " ha llegado a MagiosTV!</b>"
                        + " Disfruta de todas nuestras nuevas series y pelis que tenemos para vos</p>\n"
                        + "<p><b>"+ serie.getTitulo()+"</b> : "+ serie.getDescripcion() +"</p>";
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





    @RequestMapping ( path = "/reproducir/serie/{id}", method = RequestMethod.GET )
    public ModelAndView validarSerie(@PathVariable("id") Long id, HttpServletRequest request){
        //Si no hay usuario registrado, redirigir al login
        if( request.getSession().getAttribute("USUARIO_ID") == null ){
            return new ModelAndView("redirect:/login");
        }


        ModelMap model = new ModelMap();

        Serie serie = servicioSerie.consultarSerieById(id);
        if(serie != null){
            if (serie.getVisualizaciones()==null){
                serie.setVisualizaciones(0);
            }
            List<Capitulo> capitulos = servicioCapitulo.obtenerListaCapituloPorSerie(serie);
            model.put("serie", serie);
            model.put("capitulos", capitulos);
            model.put("rol",request.getSession().getAttribute("ROL"));

            //agregamos la serie al historial de seriess vistas del usuario loguiado
            Long idUsuario = (Long) request.getSession().getAttribute("USUARIO_ID");
            if (!servicioUsuarios.ObtenerSeriesVistas(idUsuario).contains(serie)) {
                serie.setVisualizaciones(serie.getVisualizaciones()+1);
                servicioSerie.actualizarSerie(serie);
            }
            servicioUsuarios.agregarSerieAVistas(id, idUsuario);
        }
        return new ModelAndView("vistaSerie", model);
    }

    @RequestMapping ( path = "/actualizar-serie/{id}", method = RequestMethod.GET )
    public ModelAndView verActualizarSerie(@PathVariable("id") Long id, HttpServletRequest request ){
        //Si no hay usuario registrado, redirigir al login
        if( request.getSession().getAttribute("USUARIO_ID") == null ){
            return new ModelAndView("redirect:/login");
        }

        ModelMap model = new ModelMap();

        Serie serieAActualizar = servicioSerie.consultarSerieById( id );

        if(serieAActualizar == null){
            return new ModelAndView("redirect:/home");
        }

        model.put("serie", serieAActualizar);
        model.put("rol",request.getSession().getAttribute("ROL"));
        return new ModelAndView("actualizar-serie", model);
    }

    @RequestMapping ( path = "/actualizar-serie/{id}", method = RequestMethod.POST )
    public ModelAndView actualizarSerie(@PathVariable("id") Long id,
                                        @ModelAttribute("serie") Serie serie,
                                        @RequestParam("portada") MultipartFile file,
                                        @RequestParam("fecha-estreno") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fecha_estreno,
                                        HttpServletRequest request){
        serie.setFecha_estreno(fecha_estreno);
        serie.setId(id);
        Serie serieConsultada = servicioSerie.consultarSerieById(id);

        if(serieConsultada == null){
            return new ModelAndView("redirect:/home");
        }

        if(file.isEmpty()){
            //Si no recibio ningun archivo, no actualizo la portada
            //por lo tanto va a seguir teniendo la misma ubicacion del archivo anterior
            serie.setUbicacionArchivo( serieConsultada.getUbicacionArchivo() );
        }else{
            String path = request.getSession().getServletContext().getRealPath("/image/");
            String contenido = servicioArchivo.guardarImagen(file, path);

            if(contenido == null){
                //si el contenido es null, no se pudo guardar la imagen correctamente
                //por lo tanto, dejamos la anterior
                serie.setUbicacionArchivo( serieConsultada.getUbicacionArchivo() );
            }else {
                //si se guardo correctamente la imagen, borramos el archivo anterior
                servicioArchivo.eliminarArchivo( path + serieConsultada.getUbicacionArchivo());
                serie.setUbicacionArchivo(contenido);
            }
        }
        servicioSerie.actualizarSerie(serie);
        return new ModelAndView("redirect:/reproducir/serie/" + id);
    }
}
