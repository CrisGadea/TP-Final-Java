package ar.edu.unlam.tallerweb1.repositorios;
import ar.edu.unlam.tallerweb1.modelo.Serie;

import java.util.List;

public interface RepositorioSerie {
    Serie consultarSerie(Serie serie);
    void registrarSerie(Serie serie);
    Serie consultarSerieById(Long id);
    List<Serie> getListaSeries();
    void actualizarSerie(Serie serie);
    List<Serie> filtrarSeries(String titulo, String categoria, String orden, String valor);
    public List<Serie> getListeUltimasCincoSerie();
}
