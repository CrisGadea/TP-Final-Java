package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Pelicula;

import java.util.List;

public interface ServicioPelicula {
    Pelicula consultarPelicula(Pelicula pelicula);
    void registrarPelicula(Pelicula pelicula);
    Pelicula consultarPeliculaById(Long id);
    List<Pelicula> getListePeliculas();
    void actualizarPelicula(Pelicula pelicula);
    List<Pelicula> filtrarPeliculas(String titulo, String categoria, String orden ,String valor);
    public List<Pelicula> getListeUltimasCincoPeliculas();
}
