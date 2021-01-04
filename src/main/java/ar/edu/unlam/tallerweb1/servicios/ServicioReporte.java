package ar.edu.unlam.tallerweb1.servicios;
import ar.edu.unlam.tallerweb1.modelo.Capitulo;
import ar.edu.unlam.tallerweb1.modelo.Posteo;
import ar.edu.unlam.tallerweb1.modelo.Reporte;
import ar.edu.unlam.tallerweb1.modelo.Serie;

import java.util.List;

public interface ServicioReporte {
    Reporte consultarReporte(Reporte reporte);
    void registrarReporte(Reporte reporte);
    Reporte consultarReportePorId(Long id);
    List<Reporte>obtenerListaReportePorPosteo(Posteo posteo);
}
