package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.Posteo;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("repositorioPosteo")
public class RepositorioPosteoImpl implements RepositorioPosteo{

    private final SessionFactory sessionFactory;

    @Autowired
    public RepositorioPosteoImpl(SessionFactory sessionFactory){

        this.sessionFactory=sessionFactory;
    }

    @Override
    public void crearPost(Posteo posteo) {
        final Session session = sessionFactory.getCurrentSession();
        session.persist(posteo);
    }

    @Override
    public List<Posteo> listarPosteos() {
        final Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Posteo.class);
        criteria.addOrder(Order.desc("fecha_creacion"));

        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

        return (List<Posteo>) criteria.list();
    }

    @Override
    public Posteo getPosteoById(Long id) {
        final Session session = sessionFactory.getCurrentSession();
        return session.get(Posteo.class, id);
    }

    @Override
    public void eliminarPosteo(Long id) {
        final Session session = sessionFactory.getCurrentSession();
        Posteo posteo = session.get(Posteo.class, id);
        session.remove(posteo);
    }

    @Override
    public void actualizarPosteo(Posteo posteo) {
        final Session session=sessionFactory.getCurrentSession();
        session.update(posteo);
    }

    @Override
    public List<Posteo> listarPosteosPorGenero(String genero) {
        final Session session = sessionFactory.getCurrentSession();

        Criteria criteria = session.createCriteria(Posteo.class);
        criteria.addOrder(Order.desc("genero"));

        return (List<Posteo>) criteria.list();
    }
}
