package ar.edu.unlam.tallerweb1.repositorios;
import ar.edu.unlam.tallerweb1.modelo.*;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("repositorioReporte")
public class RepositorioReporteImpl implements RepositorioReporte{

    private final SessionFactory sessionFactory;

    @Autowired
    public RepositorioReporteImpl(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }


    @Override
    public Reporte consultarReporte(Reporte reporte) {
        final Session session = sessionFactory.getCurrentSession();
        return session.get(Reporte.class,reporte.getId());
    }

    @Override
    public void registrarReporte(Reporte reporte) {
        final Session session = sessionFactory.getCurrentSession();
        session.persist(reporte);
    }

    @Override
    public Reporte consultarReportePorId(Long id) {
        final Session session = sessionFactory.getCurrentSession();
        return session.get(Reporte.class,id);
    }

    @Override
    public List<Reporte> obtenerListaReportePorPosteo(Posteo posteo) {
        final Session session=sessionFactory.getCurrentSession();
        Criteria criteria=session.createCriteria(Reporte.class);
        return (List<Reporte>) criteria.list();

    }
}
