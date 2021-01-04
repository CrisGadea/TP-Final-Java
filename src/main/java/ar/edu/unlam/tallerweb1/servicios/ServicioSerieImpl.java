package ar.edu.unlam.tallerweb1.servicios;


import ar.edu.unlam.tallerweb1.modelo.Pelicula;
import ar.edu.unlam.tallerweb1.modelo.Serie;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioSerie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("servicioSerie")
@Transactional
public class ServicioSerieImpl implements ServicioSerie{

    private RepositorioSerie servicioSerieDao;

    @Autowired
    public ServicioSerieImpl(RepositorioSerie servicioSerieDao){
        this.servicioSerieDao=servicioSerieDao;
    }


    @Override
    public Serie consultarSerie(Serie serie) {
        return servicioSerieDao.consultarSerie(serie);
    }

    @Override
    public void registrarSerie(Serie serie) {
        servicioSerieDao.registrarSerie(serie);
    }

    @Override
    public Serie consultarSerieById(Long id) {
        return servicioSerieDao.consultarSerieById(id);
    }

    @Override
    public List<Serie> getListaSeries() {
        return servicioSerieDao.getListaSeries();
    }

    @Override
    public void actualizarSerie(Serie serie){ servicioSerieDao.actualizarSerie( serie ); }

    @Override
    public List<Serie> filtrarSeries(String titulo, String categoria, String orden, String valor) {
        return servicioSerieDao.filtrarSeries(titulo,categoria,orden,valor);
    }

    @Override
    public List<Serie> getListeUltimasCincoSerie(){

        /*List<Serie> ultimasCincoSeries=new ArrayList<Serie>();

        for (int i=0; i<=4;i++){
            Serie serieBuscada=servicioSerieDao.getListaSeries().get(i);
            ultimasCincoSeries.add(serieBuscada);
        }
        return ultimasCincoSeries;*/
        return servicioSerieDao.getListeUltimasCincoSerie();
    }
}
