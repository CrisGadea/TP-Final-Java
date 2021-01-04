package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Perfil;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

import java.util.*;

public interface ServicioPerfil {

    Perfil consultarPerfil(Perfil perfil);

    Perfil consultarPerfilPorId(Long id);

    List<Perfil> listarPerfiles();

    void crearPerfil(Perfil perfil);

    void editarPerfil(Perfil perfil);

    void eliminarPerfil(Perfil perfil);

    List<Perfil> listarPerfilesPorUsuario(Usuario usuario);
}
