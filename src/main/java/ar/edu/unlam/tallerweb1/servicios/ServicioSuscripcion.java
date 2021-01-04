package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Perfil;
import ar.edu.unlam.tallerweb1.modelo.Suscripcion;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

import java.util.*;

public interface ServicioSuscripcion {
    Suscripcion verSuscripcion(Suscripcion suscripcion);

    Suscripcion verSuscripcionPorId(Long id);

    List<Suscripcion> listarSuscripciones();

    Long crearSuscripcion(Suscripcion suscripcion);

    void editarSuscripcion(Suscripcion suscripcion);

    void eliminarSuscripcion(Suscripcion suscripcion);

    boolean suscripcionVigente(Long idSuscripcion);

    Suscripcion verSuscripcionUsuario(Usuario usuario);

}
