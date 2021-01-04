package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.Pelicula;
import ar.edu.unlam.tallerweb1.modelo.Serie;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

@Repository("repositorioPelicula")
public class RepositorioPeliculaImpl implements RepositorioPelicula{

    private final SessionFactory sessionFactory;

    @Autowired
    public RepositorioPeliculaImpl(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Pelicula consultarPelicula(Pelicula pelicula) {
        final Session session = sessionFactory.getCurrentSession();
        return (Pelicula) session.createCriteria(Pelicula.class)
                .add(Restrictions.eq("titulo",pelicula.getTitulo() ))
                .uniqueResult();
    }

    @Override
    public void registrarPelicula(Pelicula pelicula) {
        final Session session = sessionFactory.getCurrentSession();
        session.persist(pelicula);
    }

    @Override
    public Pelicula consultarPeliculaById(Long id) {
        final Session session = sessionFactory.getCurrentSession();
        return session.get(Pelicula.class,id);
    }

    @Override
    public List<Pelicula> getListaPeliculas() {
        final Session session = sessionFactory.getCurrentSession();
        return (List<Pelicula>) session.createCriteria(Pelicula.class).list();
    }

    @Override
    public void actualizarPelicula(Pelicula pelicula) {
        final Session session = sessionFactory.getCurrentSession();
        session.update(pelicula);
    }

    @Override
    public List<Pelicula> filtrarPeliculas(String titulo, String categoria, String orden ,String valor) {
        final Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Pelicula.class)
                .add(Restrictions.like("titulo","%"+titulo+"%"));

        if(!categoria.equals("")){
            criteria.add(Restrictions.eq("genero", categoria));
        }
        if( !valor.equals("")){
            criteria.add(Restrictions.eq("valor", valor));
        }
        if(orden.equals("asc")){
            criteria.addOrder(Order.asc("fecha_estreno"));
        }else if(orden == "desc"){
            criteria.addOrder(Order.desc("fecha_estreno"));
        }
        if (orden.equals("visualAsc")){
            criteria.addOrder(Order.desc("visualizaciones"));
        }if (orden.equals("visualDesc")){
            criteria.addOrder(Order.asc("visualizaciones"));
        }
        return (List<Pelicula>) criteria.list();
    }

    @Override
    public List<Pelicula> getListeUltimasCincoPeliculas(){
        final Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Pelicula.class);


        criteria.addOrder(Order.asc("fecha_estreno"));


        return (List<Pelicula>) criteria.list();
    }
}
