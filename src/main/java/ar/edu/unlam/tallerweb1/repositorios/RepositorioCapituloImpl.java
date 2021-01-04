package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.Capitulo;
import ar.edu.unlam.tallerweb1.modelo.Serie;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("repositorioCapitulo")
public class RepositorioCapituloImpl implements RepositorioCapitulo{
    private SessionFactory sessionFactory;

    @Autowired
    public RepositorioCapituloImpl(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Capitulo consultarCapitulo(Capitulo capitulo) {
        final Session session = sessionFactory.getCurrentSession();
        Capitulo capitulo1= session.get(Capitulo.class,capitulo.getId());
        return capitulo1;
    }

    @Override
    public void registrarCapitulo(Capitulo capitulo) {
        final Session session = sessionFactory.getCurrentSession();
        session.persist(capitulo);
    }

    @Override
    public Capitulo consultarCapituloById(Long id) {
        final Session session = sessionFactory.getCurrentSession();
        Capitulo capitulo = session.get(Capitulo.class,id);
        return capitulo;
    }

    @Override
    public void eliminarCapitulo(Long id) {
        final Session session = sessionFactory.getCurrentSession();
        Capitulo capitulo= session.load(Capitulo.class,id);
        session.delete(capitulo);
    }

    @Override
    public void actualizarCapitulo(Capitulo capitulo) {
        final Session session=sessionFactory.getCurrentSession();
        session.update(capitulo);
    }

    @Override
    public List<Capitulo> obtenerListaCapitulo() {
        final Session session = sessionFactory.getCurrentSession();
        List<Capitulo> capitulos= session.createCriteria(Capitulo.class).list();
        return capitulos;
    }

    @Override
    public List<Capitulo> obtenerListaCapituloPorSerie(Serie serie) {
        final Session session=sessionFactory.getCurrentSession();

        Criteria criteria=session.createCriteria(Capitulo.class)
                .add(Restrictions.eq("serie",serie));
        criteria.addOrder(Order.asc("titulo"));

        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

        List<Capitulo> capitulos=criteria.list();
        return capitulos;
    }
}
