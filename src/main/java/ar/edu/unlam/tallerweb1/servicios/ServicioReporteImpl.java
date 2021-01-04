package ar.edu.unlam.tallerweb1.servicios;
import ar.edu.unlam.tallerweb1.modelo.Capitulo;
import ar.edu.unlam.tallerweb1.modelo.Posteo;
import ar.edu.unlam.tallerweb1.modelo.Reporte;
import ar.edu.unlam.tallerweb1.modelo.Serie;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioCapitulo;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioReporte;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("servicioReporte")
@Transactional
public class ServicioReporteImpl implements ServicioReporte{

    private RepositorioReporte servicioReporteDao;

    @Autowired
    public ServicioReporteImpl(RepositorioReporte servicioReporteDao){
        this.servicioReporteDao = servicioReporteDao;
    }


    @Override
    public Reporte consultarReporte(Reporte reporte) {
        return servicioReporteDao.consultarReporte(reporte);
    }

    @Override
    public void registrarReporte(Reporte reporte) {
        servicioReporteDao.registrarReporte(reporte);
    }

    @Override
    public Reporte consultarReportePorId(Long id) {
        return servicioReporteDao.consultarReportePorId(id);
    }

    @Override
    public List<Reporte> obtenerListaReportePorPosteo(Posteo posteo) {
        return servicioReporteDao.obtenerListaReportePorPosteo(posteo);
    }
}
