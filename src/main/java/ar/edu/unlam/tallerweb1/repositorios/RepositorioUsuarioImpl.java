package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.Pelicula;
import ar.edu.unlam.tallerweb1.modelo.Serie;
import ar.edu.unlam.tallerweb1.modelo.Suscripcion;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;
import java.util.List;
import java.util.Set;

// implelemtacion del repositorio de usuarios, la anotacion @Repository indica a Spring que esta clase es un componente que debe
// ser manejado por el framework, debe indicarse en applicationContext que busque en el paquete ar.edu.unlam.tallerweb1.dao
// para encontrar esta clase.
@Repository("repositorioUsuario")
public class RepositorioUsuarioImpl implements RepositorioUsuario {

	// Como todo repositorio maneja acciones de persistencia, normalmente estara inyectado el session factory de hibernate
	// el mismo esta difinido en el archivo hibernateContext.xml
	private final SessionFactory sessionFactory;

    @Autowired
	public RepositorioUsuarioImpl(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}

	@Override
	public Usuario consultarUsuario(Usuario usuario) {

		// Se obtiene la sesion asociada a la transaccion iniciada en el servicio que invoca a este metodo y se crea un criterio
		// de busqueda de Usuario donde el email y password sean iguales a los del objeto recibido como parametro
		// uniqueResult da error si se encuentran mas de un resultado en la busqueda.
		final Session session = sessionFactory.getCurrentSession();
		return (Usuario) session.createCriteria(Usuario.class)
				.add(Restrictions.eq("email", usuario.getEmail()))
				.add(Restrictions.eq("password", usuario.getPassword()))
				.uniqueResult();
	}

	@Override
	public void registrarUsuario(Usuario usuario) {

		final Session session = sessionFactory.getCurrentSession();
		//session.createCriteria(Usuario.class).uniqueResult();
		// Mapea el objeto en cascada. Si el objeto a registrar (usuario) ya está creado, arrojará una excepción.
		session.persist(usuario);
	}

	@Override
	public List<Usuario> listarUsuarios() {
		final Session session = sessionFactory.getCurrentSession();
		return (List<Usuario>) session.createCriteria(Usuario.class).list();
	}

	@Override
	public Usuario consultarUsuarioPorId(Long id){
		final Session session = sessionFactory.getCurrentSession();

		return session.get(Usuario.class,id);
	}

	@Override
	public void agregarPeliculaAVistas(Long peliculaID, Long usuarioID) {
		final Session session = sessionFactory.getCurrentSession();
		Usuario usuario = session.get(Usuario.class, usuarioID);
		Pelicula pelicula = session.get(Pelicula.class, peliculaID);
		usuario.getPeliculasVistas().add(pelicula);
	}

	@Override
	public Set<Pelicula> ObtenerPeliculasVistas(Long usuarioID){
		final Session session = sessionFactory.getCurrentSession();
		Usuario usuario = session.get(Usuario.class, usuarioID);
		return usuario.getPeliculasVistas();
	}
	@Override
	public void actualizarUsuario( Usuario usuario) {
		final Session session = sessionFactory.getCurrentSession();
		session.update(usuario);

	}
	@Override
	public Set<Serie> ObtenerSeriesVistas(Long usuarioID){
		final Session session = sessionFactory.getCurrentSession();
		Usuario usuario = session.get(Usuario.class, usuarioID);
		return usuario.getSeriesVistas();
	}
	@Override
	public void agregarSerieAVistas(Long serieID, Long usuarioID) {
		final Session session = sessionFactory.getCurrentSession();
		Usuario usuario = session.get(Usuario.class, usuarioID);
		Serie serie = session.get(Serie.class, serieID);
		usuario.getSeriesVistas().add(serie);
	}


}
