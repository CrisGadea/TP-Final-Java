package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.Pelicula;
import ar.edu.unlam.tallerweb1.modelo.Serie;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

import java.util.List;
import java.util.Set;

// Interface que define los metodos del Repositorio de Usuarios.
public interface RepositorioUsuario {
	Usuario consultarUsuario (Usuario usuario);
	void registrarUsuario (Usuario usuario);
	List<Usuario> listarUsuarios();
	void actualizarUsuario(Usuario usuario);
	Usuario consultarUsuarioPorId(Long id);
	void agregarPeliculaAVistas(Long peliculaID, Long usuarioID);
	Set<Pelicula> ObtenerPeliculasVistas(Long usuarioID);
	void agregarSerieAVistas(Long serieID, Long usuarioID);
	Set<Serie> ObtenerSeriesVistas(Long usuarioID);
}
