package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.modelo.Pelicula;
import ar.edu.unlam.tallerweb1.modelo.Serie;
import ar.edu.unlam.tallerweb1.modelo.Suscripcion;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioLogin;
import ar.edu.unlam.tallerweb1.servicios.ServicioPelicula;
import ar.edu.unlam.tallerweb1.servicios.ServicioSerie;
import ar.edu.unlam.tallerweb1.servicios.ServicioSuscripcion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
public class ControladorLogin {
	// machi estuvo aqui
	// La anotacion @Autowired indica a Spring que se debe utilizar el contructor como mecanismo de inyeccion de dependencias,
	// es decir, qeue lo parametros del mismo deben ser un bean de spring y el framewrok automaticamente pasa como parametro
	// el bean correspondiente, en este caso, un objeto de una clase que implemente la interface ServicioLogin,
	// dicha clase debe estar anotada como @Service o @Repository y debe estar en un paquete de los indicados en
	// applicationContext.xml
	private final ServicioLogin servicioLogin;
	private final ServicioPelicula servicioPelicula;
	private final ServicioSerie servicioSerie;
	private final ServicioSuscripcion servicioSuscripcion;

	@Autowired
	public ControladorLogin(ServicioLogin servicioLogin, ServicioPelicula servicioPelicula, ServicioSerie servicioSerie,ServicioSuscripcion servicioSuscripcion){
		this.servicioLogin = servicioLogin;
		this.servicioPelicula = servicioPelicula;
		this.servicioSerie = servicioSerie;
		this.servicioSuscripcion = servicioSuscripcion;
	}

	// Este metodo escucha la URL localhost:8080/NOMBRE_APP/login si la misma es invocada por metodo http GET
	@RequestMapping("/login")
	public ModelAndView irALogin() {
		// se genera una instancia del modelMap
		ModelMap modelo = new ModelMap();
		// Se agrega al modelo un objeto del tipo Usuario con key 'usuario' para que el mismo sea asociado
		// al model attribute del form que esta definido en la vista 'login'
		Usuario usuario = new Usuario();
		modelo.put("usuario", usuario);
		// Se va a la vista login (el nombre completo de la lista se resuelve utilizando el view resolver definido en el archivo spring-servlet.xml)
		// y se envian los datos a la misma  dentro del modelo
		return new ModelAndView("login", modelo);
	}

	// Este metodo escucha la URL validar-login siempre y cuando se invoque con metodo http POST
	// El metodo recibe un objeto Usuario el que tiene los datos ingresados en el form correspondiente y se corresponde con el modelAttribute definido en el
	// tag form:form
	@RequestMapping(path = "/validar-login", method = RequestMethod.POST)
	public ModelAndView validarLogin(@ModelAttribute("usuario") Usuario usuario, HttpServletRequest request) {
		ModelMap model = new ModelMap();

		// invoca el metodo consultarUsuario del servicio y hace un redirect a la URL /home, esto es, en lugar de enviar a una vista
		// hace una llamada a otro action a traves de la URL correspondiente a ésta
		Usuario usuarioBuscado = servicioLogin.consultarUsuario(usuario);
		if (usuarioBuscado != null) {
			if(!usuarioBuscado.getEnabled()){
				model.put("error", "Para loguearse, debe validar su cuenta.");
				return new ModelAndView("login", model);
			}
			request.getSession().setAttribute("ROL", usuarioBuscado.getRol());
			request.getSession().setAttribute("USUARIO_ID", usuarioBuscado.getId());
			if(servicioSuscripcion.verSuscripcionUsuario(usuarioBuscado) != null) {
				request.getSession().setAttribute("SUSCRIPCION",servicioSuscripcion.verSuscripcionUsuario(usuarioBuscado).getId() );
			}
			return new ModelAndView("redirect:/home");
		} else {
			// si el usuario no existe agrega un mensaje de error en el modelo.
			model.put("error", "Usuario o clave incorrecta");
		}
		return new ModelAndView("login", model);
	}

	// Escucha la URL /home por GET, y redirige a una vista.
	@RequestMapping(path = "/home", method = RequestMethod.GET)
	public ModelAndView irAHome(HttpServletRequest request) {
		//Si no hay usuario registrado, redirigir al login
		if( request.getSession().getAttribute("USUARIO_ID") == null ){
			return new ModelAndView("redirect:/login");
		}
		boolean suscripcion = false;
		if(request.getSession().getAttribute("SUSCRIPCION") == null){
			suscripcion = false;
		}else{
			Long id = (Long) request.getSession().getAttribute("SUSCRIPCION");
			suscripcion = servicioSuscripcion.suscripcionVigente(id);
		}
		ModelMap model = new ModelMap();
		List<Pelicula> peliculas = servicioPelicula.getListePeliculas();
		List<Serie> series = servicioSerie.getListaSeries();
		List<Pelicula> ultimasCincoPeli = servicioPelicula.getListeUltimasCincoPeliculas();
		List<Serie> ultimasCincoSeries=servicioSerie.getListeUltimasCincoSerie();

		model.put("suscripcion", suscripcion);
		model.put("peliculas",peliculas);
		model.put("series", series);
		model.put("rol",request.getSession().getAttribute("ROL"));
		model.put("ultimasCincoPeli",ultimasCincoPeli);
		model.put("ultimasCincoSeries",ultimasCincoSeries);
		return new ModelAndView("home", model);
	}

	// Escucha la url /, y redirige a la URL /login, es lo mismo que si se invoca la url /login directamente.
	@RequestMapping(path = "/", method = RequestMethod.GET)
	public ModelAndView inicio() {
		return new ModelAndView("redirect:/login");
	}

	@RequestMapping(path = "/cerrar-sesion", method = RequestMethod.GET )
	public ModelAndView irACerrarSesion(HttpServletRequest request){
		request.getSession().removeAttribute("USUARIO_ID");
		request.getSession().removeAttribute("ROL");
		request.getSession().removeAttribute("SUSCRIPCION");

		return new ModelAndView("redirect:/login");
	}


	@RequestMapping(path = "/carrusel", method = RequestMethod.GET)
	public ModelAndView irACarrusel(HttpServletRequest request) {
		//Si no hay usuario registrado, redirigir al login
		if( request.getSession().getAttribute("USUARIO_ID") == null ){
			return new ModelAndView("redirect:/login");
		}

		ModelMap model = new ModelMap();
		List<Pelicula> peliculas = servicioPelicula.getListePeliculas();
		List<Serie> series = servicioSerie.getListaSeries();
		List<Pelicula> ultimasCincoPeli=servicioPelicula.getListeUltimasCincoPeliculas();

		model.put("peliculas",peliculas);
		model.put("series", series);
		model.put("rol",request.getSession().getAttribute("ROL"));
		model.put("ultimasCincoPeli",ultimasCincoPeli);
		return new ModelAndView("carrousel", model);
	}
}
