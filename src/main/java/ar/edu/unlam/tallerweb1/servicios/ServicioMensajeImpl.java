package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Mensaje;
import ar.edu.unlam.tallerweb1.modelo.Posteo;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioMensaje;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("servicioMensaje")
@Transactional
public class ServicioMensajeImpl implements ServicioMensaje{

    private RepositorioMensaje servicioRepositorioDAO;

    @Autowired
    public ServicioMensajeImpl(RepositorioMensaje servicioRepositorioDAO){
        this.servicioRepositorioDAO = servicioRepositorioDAO;
    }

    @Override
    public Mensaje getMensajeById(Long id) {
        return servicioRepositorioDAO.getMensajeById(id);
    }

    @Override
    public List<Mensaje> getListaMensajesByPosteo(Posteo posteo) {
        return servicioRepositorioDAO.getListaMensajesByPosteo(posteo);
    }

    @Override
    public void guardarMensaje(Mensaje mensaje) {
        servicioRepositorioDAO.guardarMensaje(mensaje);
    }

    @Override
    public void actualizarMensaje(Mensaje mensaje) {
        servicioRepositorioDAO.actualizarMensaje(mensaje);
    }

    @Override
    public void eliminarMensaje(Long id) {
        servicioRepositorioDAO.eliminarMensaje(id);
    }
}
