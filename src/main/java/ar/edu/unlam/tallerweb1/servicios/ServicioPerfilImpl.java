package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Perfil;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioPelicula;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioPerfil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("servicioPerfil")
@Transactional
public class ServicioPerfilImpl implements ServicioPerfil{

    private RepositorioPerfil servicioPerfilDao;

    @Autowired
    public ServicioPerfilImpl(RepositorioPerfil servicioPerfilDao){
        this.servicioPerfilDao = servicioPerfilDao;
    }

    @Override
    public Perfil consultarPerfil(Perfil perfil) {
        return servicioPerfilDao.consultarPerfil(perfil);
    }

    @Override
    public Perfil consultarPerfilPorId(Long id) {
        return servicioPerfilDao.consultarPerfilPorId(id);
    }

    @Override
    public List<Perfil> listarPerfiles() { return servicioPerfilDao.listarPerfiles(); }

    @Override
    public void crearPerfil(Perfil perfil) {
        servicioPerfilDao.crearPerfil(perfil);
    }

    @Override
    public void editarPerfil(Perfil perfil) {
        servicioPerfilDao.editarPerfil(perfil);
    }

    @Override
    public void eliminarPerfil(Perfil perfil) {
        servicioPerfilDao.eliminarPerfil(perfil.getId());
    }

    @Override
    public List<Perfil> listarPerfilesPorUsuario(Usuario usuario) {
        return servicioPerfilDao.listarPerfilesPorUsuario(usuario);
    }
}
