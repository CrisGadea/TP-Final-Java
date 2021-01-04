package ar.edu.unlam.tallerweb1.repositorios;
import ar.edu.unlam.tallerweb1.modelo.Capitulo;
import ar.edu.unlam.tallerweb1.modelo.Posteo;
import ar.edu.unlam.tallerweb1.modelo.Reporte;
import ar.edu.unlam.tallerweb1.modelo.Serie;

import java.util.List;


public interface RepositorioReporte {
    Reporte consultarReporte(Reporte reporte);
    void registrarReporte(Reporte reporte);
    Reporte consultarReportePorId(Long id);
    List<Reporte>obtenerListaReportePorPosteo(Posteo posteo);
}
