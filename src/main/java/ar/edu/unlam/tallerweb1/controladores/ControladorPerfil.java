package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.modelo.Perfil;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioArchivo;
import ar.edu.unlam.tallerweb1.servicios.ServicioPerfil;
import ar.edu.unlam.tallerweb1.servicios.ServicioUsuarios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;

@Controller
public class ControladorPerfil {

    private final ServicioPerfil servicioPerfil;
    private final ServicioUsuarios servicioUsuarios;
    private final ServicioArchivo servicioArchivo;

    @Autowired
    public ControladorPerfil(ServicioPerfil servicioPerfil,
                             ServicioUsuarios servicioUsuarios,ServicioArchivo servicioArchivo){
        this.servicioPerfil = servicioPerfil;
        this.servicioUsuarios=servicioUsuarios;
        this.servicioArchivo=servicioArchivo;
    }

    @RequestMapping("/perfil")
    public ModelAndView mostrarPerfil(HttpServletRequest request){

        //Si no hay usuario registrado, redirigir al login
        if( request.getSession().getAttribute("USUARIO_ID") == null ){
            return new ModelAndView("redirect:/login");
        }

        ModelMap modelo = new ModelMap();
        Long usuarioId = (Long) request.getSession().getAttribute("USUARIO_ID");

        Usuario usuarioBuscado = servicioUsuarios.buscarUsuarioPorId(usuarioId);
        if (usuarioBuscado != null){
            List<Perfil> perfiles = servicioPerfil.listarPerfilesPorUsuario(usuarioBuscado);
            modelo.put("usuario",usuarioBuscado);
            modelo.put("perfiles",perfiles);
            modelo.put("rol",request.getSession().getAttribute("ROL"));
        }

        return new ModelAndView("perfil", modelo);
    }

    @RequestMapping(path="/crearPerfil/{idUsuario}", method = RequestMethod.POST)
    public ModelAndView crearPerfil(@ModelAttribute("perfil")Perfil perfil,
                                    @PathVariable("idUsuario")Long idUsuario,
                                    @RequestParam("nombre") String nombre,
                                    @RequestParam("fotoPerfil") MultipartFile file,
                                    HttpServletRequest request){

        ModelMap model=new ModelMap();
        Usuario usuario=servicioUsuarios.buscarUsuarioPorId(idUsuario);

        if(file.isEmpty()){
            perfil.setUbicacionArchivo("portada.jpg");
        }else {
            String path= request.getSession().getServletContext().getRealPath("/image/");
            String contenido=servicioArchivo.guardarImagen(file,path);
            perfil.setUbicacionArchivo(Objects.requireNonNullElse(contenido, "portada.jpg"));
        }
        perfil.setUsuario(usuario);
        perfil.setNombre(nombre);
        servicioPerfil.crearPerfil(perfil);

        return new ModelAndView("redirect:/perfil",model);
    }


    @RequestMapping(path="/crearPerfil/{idUsuario}", method = RequestMethod.GET)
    public ModelAndView irACrearPerfil(@PathVariable("idUsuario") Long idUsuario,HttpServletRequest request){
        //Si no hay usuario registrado, redirigir al login
        if( request.getSession().getAttribute("USUARIO_ID") == null ){
            return new ModelAndView("redirect:/login");
        }
        ModelMap model = new ModelMap();
        Perfil perfil=new Perfil();
        model.put("perfil",perfil);
        model.put("idUsuario",idUsuario);
        model.put("rol",request.getSession().getAttribute("ROL"));

        return new ModelAndView("crearPerfil",model);
    }

    @RequestMapping(path="/perfil/eliminar/{id}", method = RequestMethod.GET)
    public ModelAndView eliminarPerfil(@PathVariable("id") Long id){
        Perfil perfil = servicioPerfil.consultarPerfilPorId(id);

        servicioPerfil.eliminarPerfil(perfil);

        return new ModelAndView("redirect:/perfil");
    }
}
