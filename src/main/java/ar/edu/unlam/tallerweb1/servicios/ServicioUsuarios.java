package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Pelicula;
import ar.edu.unlam.tallerweb1.modelo.Serie;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

import java.util.List;
import java.util.Set;


public interface ServicioUsuarios {

    Usuario buscarUsuarioPorId(Long id);
    List<Usuario> listarUsuarios();
    void actualizarUsuario( Usuario usuario );
    void agregarPeliculaAVistas(Long peliculaID, Long usuarioID);
    Set<Pelicula> ObtenerPeliculasVistas(Long usuarioID);
    void agregarSerieAVistas(Long serieID, Long usuarioID);
    Set<Serie> ObtenerSeriesVistas(Long usuarioID);
}
