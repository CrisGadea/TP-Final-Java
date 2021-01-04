package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.Mensaje;
import ar.edu.unlam.tallerweb1.modelo.Posteo;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository("repositorioMensaje")
public class RepositorioMensajeImpl implements RepositorioMensaje{

    private final SessionFactory sessionFactory;

    @Autowired
    public RepositorioMensajeImpl(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Mensaje getMensajeById(Long id) {
        final Session session = sessionFactory.getCurrentSession();
        return session.get(Mensaje.class,id);
    }

    @Override
    public List<Mensaje> getListaMensajesByPosteo(Posteo posteo) {
        final Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Mensaje.class)
                .add(Restrictions.eq("posteo", posteo));
        criteria.addOrder(Order.desc("fecha_creacion"));

        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

        return (List<Mensaje>) criteria.list();
    }

    @Override
    public void guardarMensaje(Mensaje mensaje) {
        final Session session = sessionFactory.getCurrentSession();
        session.persist(mensaje);
    }

    @Override
    public void actualizarMensaje(Mensaje mensaje) {
        final Session session=sessionFactory.getCurrentSession();
        session.update(mensaje);
    }

    @Override
    public void eliminarMensaje(Long id) {
        final Session session=sessionFactory.getCurrentSession();
        Mensaje mensaje= session.get(Mensaje.class,id);
        session.delete(mensaje);
    }
}
