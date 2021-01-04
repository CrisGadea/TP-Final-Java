package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.Perfil;
import ar.edu.unlam.tallerweb1.modelo.Serie;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository("repositorioPerfil")
public class RepositorioPerfilImpl implements RepositorioPerfil{

    private final SessionFactory sessionFactory;

    @Autowired
    public RepositorioPerfilImpl(SessionFactory sessionFactory){
        this.sessionFactory=sessionFactory;
    }

    @Override
    public Perfil consultarPerfil(Perfil perfil) {
        final Session session = sessionFactory.getCurrentSession();
        return (Perfil) session.createCriteria(Perfil.class)
                .add(Restrictions.eq("titulo",perfil.getNombre() ))
                .uniqueResult();
    }

    @Override
    public Perfil consultarPerfilPorId(Long id) {
        final Session session = sessionFactory.getCurrentSession();
        return session.get(Perfil.class,id);
    }

    @Override
    public void crearPerfil(Perfil perfil) {
        final Session session = sessionFactory.getCurrentSession();
        session.persist(perfil);
    }

    @Override
    public void editarPerfil(Perfil perfil) {
        final Session session = sessionFactory.getCurrentSession();
        session.update(perfil);

    }

    @Override
    public void eliminarPerfil(Long id) {
        final Session session = sessionFactory.getCurrentSession();
        Perfil perfil= session.load(Perfil.class,id);
        session.delete(perfil);

    }

    @Override
    public List<Perfil> listarPerfiles() {
        final Session session = sessionFactory.getCurrentSession();
        return (List<Perfil>) session.createCriteria(Perfil.class).list();
    }

    @Override
    public List<Perfil> listarPerfilesPorUsuario(Usuario usuario) {
        final Session session = sessionFactory.getCurrentSession();
        Criteria criteria=session.createCriteria(Perfil.class).add(Restrictions.eq("usuario",usuario));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        return (List<Perfil>) criteria.list();
    }
}
