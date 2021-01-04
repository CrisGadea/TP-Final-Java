package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.Posteo;

import java.util.List;

public interface RepositorioPosteo {
    void crearPost(Posteo posteo);
    List<Posteo> listarPosteos();
    Posteo getPosteoById(Long id);
    void eliminarPosteo(Long id);
    void actualizarPosteo(Posteo posteo);
    public List<Posteo> listarPosteosPorGenero(String genero);
}
