package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Pelicula;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioPelicula;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("servicioPelicula")
@Transactional
public class ServicioPeliculaImpl implements ServicioPelicula{

    private RepositorioPelicula servicioPeliculaDao;

    @Autowired
    public ServicioPeliculaImpl(RepositorioPelicula servicioPeliculaDao){
        this.servicioPeliculaDao = servicioPeliculaDao;
    }

    @Override
    public Pelicula consultarPelicula(Pelicula pelicula) {
        return servicioPeliculaDao.consultarPelicula(pelicula);
    }

    @Override
    public void registrarPelicula(Pelicula pelicula) {
        servicioPeliculaDao.registrarPelicula(pelicula);
    }

    @Override
    public Pelicula consultarPeliculaById(Long id) {
        return servicioPeliculaDao.consultarPeliculaById(id);
    }

    @Override
    public List<Pelicula> getListePeliculas() {
        return servicioPeliculaDao.getListaPeliculas();
    }

    @Override
    public List<Pelicula> getListeUltimasCincoPeliculas() {

        List<Pelicula> ultimasCincoPeliculas=new ArrayList<Pelicula>();

        for (int i=0; i<=4;i++){
            Pelicula peliculaBuscada=servicioPeliculaDao.getListaPeliculas().get(i);
            ultimasCincoPeliculas.add(peliculaBuscada);
        }
        return ultimasCincoPeliculas;
    }

    @Override
    public void actualizarPelicula(Pelicula pelicula) { servicioPeliculaDao.actualizarPelicula(pelicula);}

    @Override
    public List<Pelicula> filtrarPeliculas(String titulo, String categoria, String orden ,String valor) {
        return servicioPeliculaDao.filtrarPeliculas(titulo, categoria, orden ,valor); }
}
