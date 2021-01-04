package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.modelo.Capitulo;
import ar.edu.unlam.tallerweb1.modelo.Serie;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class ControladorCapitulo {
    private final ServicioCapitulo servicioCapitulo;
    private final ServicioArchivo servicioArchivo;
    private final ServicioSerie servicioSerie;
    private final ServicioMail servicioMail;
    private final ServicioUsuarios servicioUsuarios;

    @Autowired
    public ControladorCapitulo(ServicioCapitulo servicioCapitulo, ServicioArchivo servicioArchivo
            , ServicioSerie servicioSerie, ServicioMail servicioMail, ServicioUsuarios servicioUsuarios){
        this.servicioCapitulo = servicioCapitulo;
        this.servicioArchivo = servicioArchivo;
        this.servicioSerie=servicioSerie;
        this.servicioMail = servicioMail;
        this.servicioUsuarios = servicioUsuarios;

    }

    @RequestMapping( path = "/crear-capitulo/{id}", method = RequestMethod.GET )
    public ModelAndView irACrearCapitulo(@PathVariable("id") Long idSerie, HttpServletRequest request){
        //Si no hay usuario registrado, redirigir al login
        if( request.getSession().getAttribute("USUARIO_ID") == null ){
            return new ModelAndView("redirect:/login");
        }
        ModelMap model = new ModelMap();
        Capitulo capitulo = new Capitulo();
        model.put("idSerie",idSerie);
        model.put("capitulo", capitulo);
        return new ModelAndView("crear-capitulo",model);
    }

    @RequestMapping ( path = "/crear-capitulo/{idSerie}", method = RequestMethod.POST )
    public ModelAndView validarCapitulo(@ModelAttribute("capitulo") Capitulo capitulo,
                                        @PathVariable("idSerie")Long idSerie,
                                        @RequestParam("portada") MultipartFile file,
                                        HttpServletRequest request){
        ModelMap model = new ModelMap();

        Serie serie=servicioSerie.consultarSerieById(idSerie);
        capitulo.setSerie(serie);

        //si el usuario no agrego el archivo portada, el capitulo
        //va a tener una imagen por defecto que ya esta guardada
        //en el servidor
        if(file.isEmpty()){
            capitulo.setUbicacionArchivo("portada.jpg");
        }else{
            String path = request.getSession().getServletContext().getRealPath("/image/");
            String contenido = servicioArchivo.guardarImagen(file, path);
            if(contenido == null){
                capitulo.setUbicacionArchivo("portada.jpg");

            }else{
                capitulo.setUbicacionArchivo(contenido);
            }
        }
        servicioCapitulo.registrarCapitulo(capitulo);

        String texto = "<p> <b>¡El capitulo " + capitulo.getTitulo() + " de la serie " + serie.getTitulo() + " ha llegado a MagiosTV!</b>"
                + " Disfruta de todas nuestras nuevas series y pelis que tenemos para vos</p>\n"
                + "<p><b>"+capitulo.getTitulo()+"</b> : "+capitulo.getDescripcion()+"</p>";
        List<Usuario> usuarios = servicioUsuarios.listarUsuarios();
        try {
            for (Usuario usuario:
                    usuarios) {
                servicioMail.enviar(usuario.getEmail(), texto);
            }
        }catch (MessagingException e){
            e.getMessage();
        }

        return new ModelAndView("redirect:/reproducir/serie/"+idSerie, model);
    }

    @RequestMapping ( path = "/actualizar-capitulo/{id}", method = RequestMethod.GET )
    public ModelAndView verActualizarCapitulo(@PathVariable("id") Long id, HttpServletRequest request ){
        //Si no hay usuario registrado, redirigir al login
        if( request.getSession().getAttribute("USUARIO_ID") == null ){
            return new ModelAndView("redirect:/login");
        }
        ModelMap model = new ModelMap();


        Capitulo capituloAActualizar = servicioCapitulo.consultarCapituloById(id);


        if(capituloAActualizar == null){
            return new ModelAndView("redirect:/home");
        }

        model.put("capitulo", capituloAActualizar);

        return new ModelAndView("actualizar-capitulo", model);
    }

    @RequestMapping ( path = "/actualizar-capitulo/{id}", method = RequestMethod.POST )
    public ModelAndView actualizarCapitulo(@PathVariable("id") Long id,
                                           @ModelAttribute("capitulo") Capitulo capitulo,

                                           @RequestParam("portada") MultipartFile file,
                                           HttpServletRequest request){

        capitulo.setId(id);
        Capitulo capituloConsultado = servicioCapitulo.consultarCapituloById(id);

        if(capituloConsultado == null){
            return new ModelAndView("redirect:/home");
        }

        if(file.isEmpty()){
            //Si no recibio ningun archivo, no actualizo la portada
            //por lo tanto va a seguir teniendo la misma ubicacion del archivo anterior
            capitulo.setUbicacionArchivo( capituloConsultado.getUbicacionArchivo() );
        }else{
            String path = request.getSession().getServletContext().getRealPath("/image/");
            String contenido = servicioArchivo.guardarImagen(file, path);

            if(contenido == null){
                //si el contenido es null, no se pudo guardar la imagen correctamente
                //por lo tanto, dejamos la anterior
                capitulo.setUbicacionArchivo( capituloConsultado.getUbicacionArchivo() );
            }else {
                //si se guardo correctamente la imagen, borramos el archivo anterior
                servicioArchivo.eliminarArchivo( path + capituloConsultado.getUbicacionArchivo());
                capitulo.setUbicacionArchivo(contenido);
            }
        }
        capitulo.setSerie(capituloConsultado.getSerie());

        servicioCapitulo.actualizarCapitulo(capitulo);
        return new ModelAndView("redirect:/home");
        //return new ModelAndView("redirect:/reproducir/serie/" + id);
    }


    @RequestMapping ( path = "/reproducirCapitulo/{id}", method = RequestMethod.GET )
    public ModelAndView validarElCapitulo(@PathVariable("id") Long id, HttpServletRequest request){
        //Si no hay usuario registrado, redirigir al login
        if( request.getSession().getAttribute("USUARIO_ID") == null ){
            return new ModelAndView("redirect:/login");
        }

        ModelMap model = new ModelMap();
        Capitulo capitulo = servicioCapitulo.consultarCapituloById(id);
        if(capitulo != null){
            model.put("capitulo", capitulo);
        }
        return new ModelAndView("reproducir-capitulo", model);
    }

    @RequestMapping(path = "/eliminarCapitulo/{id}", method = RequestMethod.GET)
    public ModelAndView eliminarCapitulo(@PathVariable("id") Long id,
                                         HttpServletRequest request){
        //Si no hay usuario registrado, redirigir al login
        if( request.getSession().getAttribute("USUARIO_ID") == null ){
            return new ModelAndView("redirect:/login");
        }

        ModelMap model= new ModelMap();
        String path = request.getSession().getServletContext().getRealPath("/image/");
        //Serie serie=servicioSerie.consultarSerieById(id);
        Capitulo capituloConsultado=servicioCapitulo.consultarCapituloById(id);
        if(capituloConsultado==null){
            model.put("error","error al intentar eliminar Pelicula");
            return new ModelAndView("redirect: /home",model);
        }
        Serie serie = capituloConsultado.getSerie();
        servicioCapitulo.eliminarCapitulo(capituloConsultado.getId());
        servicioArchivo.eliminarArchivo(path + capituloConsultado.getUbicacionArchivo());
        return new ModelAndView("redirect:/reproducir/serie/"+serie.getId());
    }
}
