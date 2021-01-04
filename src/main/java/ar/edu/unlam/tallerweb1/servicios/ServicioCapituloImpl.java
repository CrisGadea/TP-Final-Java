package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Capitulo;
import ar.edu.unlam.tallerweb1.modelo.Serie;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioCapitulo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("servicioCapitulo")
@Transactional
public class ServicioCapituloImpl implements ServicioCapitulo{

    private RepositorioCapitulo servicioCapituloDao;

    @Autowired
    public ServicioCapituloImpl(RepositorioCapitulo servicioCapituloDao){
        this.servicioCapituloDao = servicioCapituloDao;
    }

    @Override
    public Capitulo consultarCapitulo(Capitulo capitulo) {
        return servicioCapituloDao.consultarCapitulo(capitulo);
    }

    @Override
    public void registrarCapitulo(Capitulo capitulo) {
        this.servicioCapituloDao.registrarCapitulo(capitulo);
    }

    @Override
    public Capitulo consultarCapituloById(Long id) {
        return this.servicioCapituloDao.consultarCapituloById(id);
    }

    @Override
    public void eliminarCapitulo(Long id) {
        this.servicioCapituloDao.eliminarCapitulo(id);
    }

    @Override
    public void actualizarCapitulo(Capitulo capitulo) {
        servicioCapituloDao.actualizarCapitulo(capitulo);
    }

    @Override
    public List<Capitulo> obtenerListaCapitulo() {
        return servicioCapituloDao.obtenerListaCapitulo();
    }

    @Override
    public List<Capitulo> obtenerListaCapituloPorSerie(Serie serie) {
        return servicioCapituloDao.obtenerListaCapituloPorSerie(serie);
    }
}
