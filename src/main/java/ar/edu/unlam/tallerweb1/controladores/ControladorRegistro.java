package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioLogin;
import ar.edu.unlam.tallerweb1.servicios.ServicioMail;
import ar.edu.unlam.tallerweb1.servicios.ServicioRegistro;
import ar.edu.unlam.tallerweb1.servicios.ServicioUsuarios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.mail.MessagingException;
import javax.mail.Multipart;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
public class ControladorRegistro {

    private final ServicioRegistro servicioRegistro;

    private final ServicioLogin servicioLogin;

    private final ServicioMail servicioMail;

    private final ServicioUsuarios servicioUsuarios;


    @Autowired
    public ControladorRegistro(ServicioRegistro servicioRegistro, ServicioLogin servicioLogin, ServicioMail servicioMail,
                               ServicioUsuarios servicioUsuarios){
        this.servicioRegistro = servicioRegistro;
        this.servicioLogin = servicioLogin;
        this.servicioMail = servicioMail;
        this.servicioUsuarios = servicioUsuarios;
    }

    @RequestMapping("/registro")
    public ModelAndView registro(){
        ModelMap model = new ModelMap();
        Usuario usuario = new Usuario();
        model.put("usuario", usuario);
        return new ModelAndView("registro",model);
    }

    @RequestMapping(path = "/validar-registro", method = RequestMethod.POST)
    public ModelAndView validarRegistro(@ModelAttribute("usuario") Usuario usuario
                                        ){
        ModelMap model = new ModelMap();

        Usuario usuarioBuscado = servicioLogin.consultarUsuario(usuario);

        if (usuarioBuscado != null) {
            // si el usuario existe, agrega un mensaje de error en el modelo.
            model.put("error", "Username o email ya existe");
            return new ModelAndView("registro",model);
        } else {
            // Completamos datos del Usuario
            usuario.setRol("user");
            LocalDateTime time = LocalDateTime.now();
            usuario.setUpdated_at(time.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
            usuario.setCreation_at(time.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
            usuario.setEnabled(false);
            //Registramos el Usuario
            servicioRegistro.registrarUsuario(usuario);
            Usuario usuarioRegistrado=servicioLogin.consultarUsuario(usuario);
            Long idUsuarioRegistrado = usuarioRegistrado.getId();
            // Creamos Mail de Bienvenida
            String texto = "<p>Bienvenido " + usuario.getUsername()
                    + " a Magio's TV, tu nueva plataforma para ver tus series y peliculas favoritas</p>\n"
                    + "<p>Debe validar su cuenta para continuar.</p>\n\n"
                    +"<a href=\"http://localhost:8080/proyecto_limpio_spring_war_exploded/validar-cuenta/"+idUsuarioRegistrado+"\">Click aqui para validar</a>";

            if (usuarioRegistrado==null){
                model.put("error", "Usuario no se pudo registrar");
                return new ModelAndView("registro", model);
            }

           try {
                servicioMail.enviar(usuario.getEmail(),texto);
            }catch (MessagingException e){
                e.getMessage();
            }

        }
        return new ModelAndView("redirect:/login");
    }

    @RequestMapping("/validar-cuenta/{id}")
    public ModelAndView validarCuenta(@PathVariable Long id)
    {
        Usuario usuarioBuscado = servicioUsuarios.buscarUsuarioPorId(id);
        usuarioBuscado.setEnabled(true);
        servicioUsuarios.actualizarUsuario(usuarioBuscado);
        return new ModelAndView("redirect:/login");
    }

}
