package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Suscripcion;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioSuscripcion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.repositorios.RepositorioSuscripcion;

import java.time.LocalDateTime;
import java.util.List;

@Service("servicioSuscripcion")
@Transactional
public class ServicioSuscripcionImpl implements ServicioSuscripcion{

    private final RepositorioSuscripcion servicioSuscripcionDao;

    @Autowired
    public ServicioSuscripcionImpl(RepositorioSuscripcion servicioSuscripcionDao){
        this.servicioSuscripcionDao = servicioSuscripcionDao;
    }

    @Override
    public Suscripcion verSuscripcion(Suscripcion suscripcion) {
        return servicioSuscripcionDao.verSuscripcion(suscripcion);
    }

    @Override
    public Suscripcion verSuscripcionPorId(Long id) {
        return servicioSuscripcionDao.verSuscripcionPorId(id);
    }

    @Override
    public List<Suscripcion> listarSuscripciones() { return servicioSuscripcionDao.listarSuscripciones(); }

    @Override
    public Long crearSuscripcion(Suscripcion suscripcion) {
        return servicioSuscripcionDao.crearSuscripcion(suscripcion);
    }

    @Override
    public void editarSuscripcion(Suscripcion suscripcion) {
        servicioSuscripcionDao.editarSuscripcion(suscripcion);
    }

    @Override
    public void eliminarSuscripcion(Suscripcion suscripcion) {
        servicioSuscripcionDao.eliminarSuscripcion(suscripcion.getId());
    }

    @Override
    public boolean suscripcionVigente(Long idSuscripcion) {
        Suscripcion suscripcionActiva = servicioSuscripcionDao.verSuscripcionPorId(idSuscripcion);
        LocalDateTime vencimiento = LocalDateTime.parse(suscripcionActiva.getFecha_ven());
        LocalDateTime time = LocalDateTime.now();
        if (vencimiento.isAfter(time)){
            return true;
        }
        return false;
    }

    @Override
    public Suscripcion verSuscripcionUsuario(Usuario usuario) {
        return servicioSuscripcionDao.verSuscripcionUsuario(usuario);
    }

}
