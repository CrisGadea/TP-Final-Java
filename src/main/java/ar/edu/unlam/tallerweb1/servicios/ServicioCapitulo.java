package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Capitulo;
import ar.edu.unlam.tallerweb1.modelo.Serie;

import java.util.List;

public interface ServicioCapitulo {
    Capitulo consultarCapitulo(Capitulo capitulo);
    void registrarCapitulo(Capitulo capitulo);
    Capitulo consultarCapituloById(Long id);
    void eliminarCapitulo(Long id);
    void actualizarCapitulo(Capitulo capitulo);
    List<Capitulo> obtenerListaCapitulo();
    List<Capitulo> obtenerListaCapituloPorSerie(Serie serie);
}
