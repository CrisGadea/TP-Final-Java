package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.modelo.Contenido;
import ar.edu.unlam.tallerweb1.modelo.Pelicula;
import ar.edu.unlam.tallerweb1.modelo.Serie;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioPelicula;
import ar.edu.unlam.tallerweb1.servicios.ServicioSerie;
import ar.edu.unlam.tallerweb1.servicios.ServicioUsuarios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Set;

@Controller
public class ControladorContenido {
    private ServicioPelicula servicioPelicula;
    private ServicioSerie servicioSerie;
    private ServicioUsuarios servicioUsuarios;

    @Autowired
    public ControladorContenido( ServicioPelicula servicioPelicula, ServicioSerie servicioSerie,
    ServicioUsuarios servicioUsuarios){

        this.servicioPelicula = servicioPelicula;
        this.servicioSerie = servicioSerie;
        this.servicioUsuarios = servicioUsuarios;
    }

    @RequestMapping(path = "/buscar", method = RequestMethod.POST)
    public ModelAndView buscar(
            @ModelAttribute("titulo") String titulo,
            @ModelAttribute("genero") String genero,
            @ModelAttribute("valor") String valor,
            @ModelAttribute("order") String orden,
            HttpServletRequest request){
        ModelMap model = new ModelMap();
        List<Serie> ultimasCincoSeries=servicioSerie.getListeUltimasCincoSerie();
        List<Pelicula> peliculas = servicioPelicula.filtrarPeliculas(titulo, genero, orden,valor);
        List<Serie> series = servicioSerie.filtrarSeries(titulo, genero, orden,valor);
        model.put("peliculas",peliculas);
        model.put("series", series);
        model.put("ultimasCincoSeries",ultimasCincoSeries);
        return new ModelAndView("home", model);
    }

    @RequestMapping(path = "ver-mas-tarde", method = RequestMethod.GET)
    public ModelAndView verMasTarde(HttpServletRequest request){
        //Si no hay usuario registrado, redirigir al login
        if( request.getSession().getAttribute("USUARIO_ID") == null ){
            return new ModelAndView("redirect:/login");
        }

        ModelMap modelo = new ModelMap();
        Long idUsuario = (Long) request.getSession().getAttribute("USUARIO_ID");
        Usuario usuario = servicioUsuarios.buscarUsuarioPorId(idUsuario);
        Set<Pelicula> peliculasMasTarde = usuario.getPeliculasMasTarde();
        Set<Serie> seriesMasTarde = usuario.getSeriesMasTarde();
        modelo.put("peliculasTarde", peliculasMasTarde);
        modelo.put("seriesTarde",seriesMasTarde);
        return new ModelAndView("tarde", modelo);
    }
    @RequestMapping(path = "ver-mas-tarde", method = RequestMethod.POST)
    public ModelAndView verMasTarde(@ModelAttribute("vermastarde") String mastarde,
                                    @ModelAttribute ("idObtenido") Long id,
                                    @ModelAttribute ("vermastarde") String tarde,
                                    @ModelAttribute ("tipoObtenido") String tipo,
                                    HttpServletRequest request){

        ModelMap model = new ModelMap();
        Long idUsuario = (Long) request.getSession().getAttribute("USUARIO_ID");
        Usuario usuario = servicioUsuarios.buscarUsuarioPorId(idUsuario);
        if(tipo.equals("pelicula")){
            if (tarde.equals("on")) {
                Pelicula pelicula = servicioPelicula.consultarPeliculaById(id);
                Set<Pelicula> peliculas = usuario.getPeliculasMasTarde();
                if (!peliculas.contains(pelicula)) {
                    peliculas.add(pelicula);
                    usuario.setPeliculasMasTarde(peliculas);
                    servicioUsuarios.actualizarUsuario(usuario);
                }
            }else {
                Pelicula peliculaBorrar = servicioPelicula.consultarPeliculaById(id);
                Set<Pelicula> peliculas = usuario.getPeliculasMasTarde();
                peliculas.remove(peliculaBorrar);
                usuario.setPeliculasMasTarde(peliculas);
                servicioUsuarios.actualizarUsuario(usuario);
            }
        }
        if(tipo.equals("serie") ){
            if (tarde.equals("on")) {
                Serie serie = servicioSerie.consultarSerieById(id);
                Set<Serie> series = usuario.getSeriesMasTarde();
                if (!series.contains(serie)) {
                    series.add(serie);
                    usuario.setSeriesMasTarde(series);
                    servicioUsuarios.actualizarUsuario(usuario);
                }
            }else {
                Serie serieBorrar = servicioSerie.consultarSerieById(id);
                Set<Serie> series = usuario.getSeriesMasTarde();
                series.remove(serieBorrar);
                usuario.setSeriesMasTarde(series);
                servicioUsuarios.actualizarUsuario(usuario);
            }
        }
        return new ModelAndView("redirect:/ver-mas-tarde", model);
    }

//                    fin ver mas tarde
@RequestMapping(path = "/calificar-pelicula", method = RequestMethod.POST)
public ModelAndView valorPelicula(@ModelAttribute("estrellas") String estrellas,
                                @ModelAttribute ("idPeli") Long id){

    Pelicula peli = servicioPelicula.consultarPeliculaById(id);
    peli.setValor(estrellas);
    servicioPelicula.actualizarPelicula(peli);
    return new ModelAndView("redirect:/reproducir/pelicula/"+id);
}


    @RequestMapping(path = "/calificar-serie", method = RequestMethod.POST)
    public ModelAndView valorSerie(@ModelAttribute("estrellas") String estrellas,
                                    @ModelAttribute ("idPeli") Long id){

        Serie serie = servicioSerie.consultarSerieById(id);
        serie.setValor(estrellas);
        servicioSerie.actualizarSerie(serie);
        return new ModelAndView("redirect:/reproducir/serie/"+id);
    }

    @RequestMapping(path = "/peliculas-vistas", method = RequestMethod.GET)
    public ModelAndView verPeliculasVistas(HttpServletRequest request){
        //Si no hay usuario registrado, redirigir al login
        if( request.getSession().getAttribute("USUARIO_ID") == null ){
            return new ModelAndView("redirect:/login");
        }

        ModelMap model = new ModelMap();

        Long idUsuario = (Long) request.getSession().getAttribute("USUARIO_ID");
        Set<Pelicula> peliculasVistas = servicioUsuarios.ObtenerPeliculasVistas(idUsuario);
        Set<Serie> seriesVistas = servicioUsuarios.ObtenerSeriesVistas(idUsuario);

        model.put("peliculas", peliculasVistas);
        model.put("series", seriesVistas);

        return new ModelAndView("peliculas-vistas", model);
    }

}

