package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.Suscripcion;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

import java.util.List;

public interface RepositorioSuscripcion {
    Suscripcion verSuscripcion(Suscripcion suscripcion);

    Suscripcion verSuscripcionPorId(Long id);

    List<Suscripcion> listarSuscripciones();

    Long crearSuscripcion(Suscripcion suscripcion);

    void editarSuscripcion(Suscripcion suscripcion);

    void eliminarSuscripcion(Long id);

    Suscripcion verSuscripcionUsuario(Usuario usuario);
}
