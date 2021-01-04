package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.Mensaje;
import ar.edu.unlam.tallerweb1.modelo.Posteo;

import java.util.List;

public interface RepositorioMensaje {
    Mensaje getMensajeById(Long id);
    List<Mensaje> getListaMensajesByPosteo(Posteo posteo);
    void guardarMensaje(Mensaje mensaje);
    void actualizarMensaje(Mensaje mensaje);
    void eliminarMensaje(Long id);
}
