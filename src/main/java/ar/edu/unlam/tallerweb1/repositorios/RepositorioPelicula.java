package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.Pelicula;

import java.util.List;

public interface RepositorioPelicula {
    Pelicula consultarPelicula(Pelicula pelicula);
    void registrarPelicula(Pelicula pelicula);
    Pelicula consultarPeliculaById(Long id);
    List<Pelicula> getListaPeliculas();
    void actualizarPelicula(Pelicula pelicula);
    List<Pelicula> filtrarPeliculas(String titulo, String categoria, String orden,String valor);
    public List<Pelicula> getListeUltimasCincoPeliculas();
}
