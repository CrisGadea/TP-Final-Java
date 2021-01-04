package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Mensaje;
import ar.edu.unlam.tallerweb1.modelo.Posteo;

import java.util.List;

public interface ServicioMensaje {
    Mensaje getMensajeById(Long id);
    List<Mensaje> getListaMensajesByPosteo(Posteo posteo);
    void guardarMensaje(Mensaje mensaje);
    void actualizarMensaje(Mensaje mensaje);
    void eliminarMensaje(Long id);
}
