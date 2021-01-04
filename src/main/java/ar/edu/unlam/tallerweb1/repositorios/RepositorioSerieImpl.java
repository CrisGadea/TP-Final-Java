package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.Pelicula;
import ar.edu.unlam.tallerweb1.modelo.Serie;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("repositorioSerie")
public class RepositorioSerieImpl implements RepositorioSerie{

    private final SessionFactory sessionFactory;

    @Autowired
    public RepositorioSerieImpl(SessionFactory sessionFactory){

        this.sessionFactory=sessionFactory;
    }


    @Override
    public Serie consultarSerie(Serie serie) {
        final Session session = sessionFactory.getCurrentSession();
        return (Serie) session.createCriteria(Serie.class)
                .add(Restrictions.eq("titulo",serie.getTitulo() ))
                .uniqueResult();
    }

    @Override
    public void registrarSerie(Serie serie) {
        final Session session = sessionFactory.getCurrentSession();
        session.persist(serie);
    }

    //obtiene serie por ID en la base de datos
    @Override
    public Serie consultarSerieById(Long id) {
        final Session session = sessionFactory.getCurrentSession();
        return session.get(Serie.class,id);
    }

    @Override
    public List<Serie> getListaSeries() {
        final Session session = sessionFactory.getCurrentSession();
        return (List<Serie>) session.createCriteria(Serie.class).list();
    }

    @Override
    public void actualizarSerie(Serie serie) {
        final Session session = sessionFactory.getCurrentSession();
        session.update(serie);
    }

    @Override
    public List<Serie> filtrarSeries(String titulo, String categoria, String orden, String valor) {
        final Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Serie.class)
                .add(Restrictions.like("titulo","%"+titulo+"%"));
        if(!categoria.equals("")){
            criteria.add(Restrictions.eq("genero", categoria));
        }
        if( !valor.equals("")){
            criteria.add(Restrictions.eq("valor", valor));
        }
        if(orden.equals("asc")){
            criteria.addOrder(Order.asc("fecha_estreno"));
        }else if(orden.equals("desc")){
            criteria.addOrder(Order.desc("fecha_estreno"));
        }
        if (orden.equals("visualAsc")){
            criteria.addOrder(Order.desc("visualizaciones"));
        }if (orden.equals("visualDesc")){
            criteria.addOrder(Order.asc("visualizaciones"));
        }
        return (List<Serie>) criteria.list();
    }

    @Override
    public List<Serie> getListeUltimasCincoSerie(){
        final Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Serie.class);


        criteria.addOrder(Order.desc("fecha_estreno"));


        return (List<Serie>) criteria.list();
    }
}
