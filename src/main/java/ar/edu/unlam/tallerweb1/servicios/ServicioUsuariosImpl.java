package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Pelicula;
import ar.edu.unlam.tallerweb1.modelo.Serie;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service("servicioUsuarios")
@Transactional
public class ServicioUsuariosImpl implements ServicioUsuarios{
    @Autowired
    private RepositorioUsuario repositorioUsuario;

    @Override
    public Usuario buscarUsuarioPorId(Long id) {
        return repositorioUsuario.consultarUsuarioPorId(id);
    }

    @Override
    public List<Usuario> listarUsuarios() { return repositorioUsuario.listarUsuarios(); }

    @Override
    public void actualizarUsuario(Usuario usuario) {
        repositorioUsuario.actualizarUsuario(usuario);
    }

    @Override
    public void agregarPeliculaAVistas(Long peliculaID, Long usuarioID){
        repositorioUsuario.agregarPeliculaAVistas(peliculaID, usuarioID);
    }
    @Override
    public Set<Pelicula> ObtenerPeliculasVistas(Long usuarioID){ return  repositorioUsuario.ObtenerPeliculasVistas(usuarioID);}

    @Override
    public void agregarSerieAVistas(Long serieID, Long usuarioID){
        repositorioUsuario.agregarSerieAVistas(serieID, usuarioID);
    }
    @Override
    public Set<Serie> ObtenerSeriesVistas(Long usuarioID){ return  repositorioUsuario.ObtenerSeriesVistas(usuarioID);}
}
