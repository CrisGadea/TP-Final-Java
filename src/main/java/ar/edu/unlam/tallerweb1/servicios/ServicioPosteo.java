package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Posteo;

import java.util.List;

public interface ServicioPosteo {
    void guardarPosteo(Posteo posteo);
    List<Posteo> listarPosteos();
    Posteo getPosteoById(Long id);
    void eliminarPosteo(Long id);
    void actualizarPosteo(Posteo posteo);
    List<Posteo> listarPosteosPorGenero(String genero);
}
