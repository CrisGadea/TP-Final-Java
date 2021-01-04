package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Posteo;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioPosteo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("servicioPosteo")
@Transactional
public class ServicioPosteoImpl implements ServicioPosteo{

    private RepositorioPosteo servicioPosteoDAO;

    @Autowired
    public ServicioPosteoImpl(RepositorioPosteo servicioPosteoDAO){
        this.servicioPosteoDAO = servicioPosteoDAO;
    }

    @Override
    public void guardarPosteo(Posteo posteo) {
        servicioPosteoDAO.crearPost(posteo);
    }

    @Override
    public List<Posteo> listarPosteos() {
        return servicioPosteoDAO.listarPosteos();
    }

    @Override
    public Posteo getPosteoById(Long id) { return servicioPosteoDAO.getPosteoById(id); }

    @Override
    public void eliminarPosteo(Long id) {
        servicioPosteoDAO.eliminarPosteo(id);
    }

    @Override
    public void actualizarPosteo(Posteo posteo) {
        servicioPosteoDAO.actualizarPosteo(posteo);
    }

    @Override
    public List<Posteo> listarPosteosPorGenero(String genero) { return servicioPosteoDAO.listarPosteosPorGenero(genero); }


}
