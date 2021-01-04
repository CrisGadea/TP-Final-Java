package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.Perfil;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

import java.util.*;

public interface RepositorioPerfil {

    Perfil consultarPerfil(Perfil perfil);

    Perfil consultarPerfilPorId(Long id);

    void crearPerfil(Perfil perfil);

    void editarPerfil(Perfil perfil);

    void eliminarPerfil(Long id);

    List<Perfil> listarPerfiles();

    List<Perfil> listarPerfilesPorUsuario(Usuario usuario);
}
