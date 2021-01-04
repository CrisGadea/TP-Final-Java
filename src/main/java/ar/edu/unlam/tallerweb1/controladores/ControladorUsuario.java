package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.modelo.Suscripcion;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioUsuarios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ControladorUsuario {
    private ServicioUsuarios servicioUsuarios;


    @Autowired
    public ControladorUsuario(ServicioUsuarios servicioUsuarios){
        this.servicioUsuarios = servicioUsuarios;
    }


    @RequestMapping( path = "/configurar-cuenta")
    public ModelAndView irAConfigurarUsuario(HttpServletRequest request){
        //Si no hay usuario registrado, redirigir al login
        if( request.getSession().getAttribute("USUARIO_ID") == null ){
            return new ModelAndView("redirect:/login");
        }

        ModelMap model = new ModelMap();
        Long idUsuario = (Long) request.getSession().getAttribute("USUARIO_ID");
        Usuario usuario = servicioUsuarios.buscarUsuarioPorId(idUsuario);
        String username = usuario.getUsername();
        String email=usuario.getEmail();
        String telefono = usuario.getTel();
        String password = usuario.getPassword();
        String id =usuario.getId().toString();
        model.put("idDeUsuario", id);
        model.put("emailDeUsuario", email);
        model.put("nickDeUsuario", username);
        model.put("telefonoDeUsuario", telefono);
        model.put("passwordDeUsuario", password);
        return new ModelAndView("configurar-cuenta",model);

    }
    @RequestMapping( path = "/modificar-cuenta", method = RequestMethod.POST)
    public ModelAndView irAModificarUsuario(@ModelAttribute("id") Long id ,
                                            @ModelAttribute("username")String username,
                                            @ModelAttribute("email")String email,
                                            @ModelAttribute("tel")String tel,
                                            @ModelAttribute("password")String password,
                                            @ModelAttribute("suscripcion")Suscripcion suscripcion,
                                            HttpServletRequest request) {
        //Si no hay usuario registrado, redirigir al login
        if( request.getSession().getAttribute("USUARIO_ID") == null ){
            return new ModelAndView("redirect:/login");
        }

        Long idUsuario = (Long) request.getSession().getAttribute("USUARIO_ID");

        Usuario usuarioViejo = servicioUsuarios.buscarUsuarioPorId(idUsuario);
        usuarioViejo.setUsername(username);
        usuarioViejo.setEmail(email);
        usuarioViejo.setTel(tel);
        usuarioViejo.setPassword(password);
        servicioUsuarios.actualizarUsuario(usuarioViejo);


        return new ModelAndView("configurar-cuenta");
    }




}
