package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.Suscripcion;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("repositorioSuscripcion")
public class RepositorioSuscripcionImpl implements RepositorioSuscripcion {

    private SessionFactory sessionFactory;

    @Autowired
    public RepositorioSuscripcionImpl(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Suscripcion verSuscripcion(Suscripcion suscripcion) {
        final Session session = sessionFactory.getCurrentSession();
        return (Suscripcion) session.createCriteria(Suscripcion.class)
                .add(Restrictions.eq("nombre",suscripcion.getId() ))
                .uniqueResult();
    }

    @Override
    public Suscripcion verSuscripcionPorId(Long id) {
        final Session session = sessionFactory.getCurrentSession();
        return session.get(Suscripcion.class,id);
    }



    @Override
    public List<Suscripcion> listarSuscripciones() {
        final Session session = sessionFactory.getCurrentSession();
        return (List<Suscripcion>) session.createCriteria(Suscripcion.class).list();
    }

    @Override
    public Long crearSuscripcion(Suscripcion suscripcion) {
        final Session session = sessionFactory.getCurrentSession();
        return (Long) session.save(suscripcion);
    }

    @Override
    public void editarSuscripcion(Suscripcion suscripcion) {
        final Session session = sessionFactory.getCurrentSession();
        session.update(suscripcion);
    }

    @Override
    public void eliminarSuscripcion(Long id) {
        final Session session = sessionFactory.getCurrentSession();
        Suscripcion suscripcion = session.load(Suscripcion.class,id);
        session.delete(suscripcion);
    }

    @Override
    public Suscripcion verSuscripcionUsuario(Usuario usuario) {
        final Session session = sessionFactory.getCurrentSession();
        return (Suscripcion)session.createCriteria(Suscripcion.class)
                .add(Restrictions.eq("usuario", usuario)).uniqueResult();
    }
}
