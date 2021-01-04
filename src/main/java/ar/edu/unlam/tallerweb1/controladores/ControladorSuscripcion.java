package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.modelo.Suscripcion;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioMercadoPago;
import ar.edu.unlam.tallerweb1.servicios.ServicioSuscripcion;
import ar.edu.unlam.tallerweb1.servicios.ServicioUsuarios;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.Preference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
public class ControladorSuscripcion {

    private final ServicioSuscripcion servicioSuscripcion;
    private final ServicioMercadoPago servicioMercadoPago;
    private final ServicioUsuarios servicioUsuario;

    @Autowired
    public ControladorSuscripcion(ServicioSuscripcion servicioSuscripcion,ServicioMercadoPago servicioMercadoPago,
                                  ServicioUsuarios servicioUsuario){
        this.servicioSuscripcion = servicioSuscripcion;
        this.servicioMercadoPago = servicioMercadoPago;
        this.servicioUsuario = servicioUsuario;
    }

    @RequestMapping ( path = "/suscribirse/{meses}/{precio}", method = RequestMethod.GET )
    public ModelAndView suscribirse(@PathVariable("meses") Long meses,
                                            @PathVariable("precio") float precio,
                                            HttpServletRequest request) throws MPException {
        Long idUsuario = (Long) request.getSession().getAttribute("USUARIO_ID");
        Usuario usuario = servicioUsuario.buscarUsuarioPorId(idUsuario);
        Preference preference = servicioMercadoPago.generePreference(usuario,precio);
        LocalDateTime vencimiento = LocalDateTime.now().plusMonths(meses);
        if(request.getSession().getAttribute("SUSCRIPCION") == null){
            Suscripcion suscripcion = new Suscripcion();
            suscripcion.setUsuario(usuario);
            suscripcion.setFecha_ven(vencimiento.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
            Long idSuscripcion = servicioSuscripcion.crearSuscripcion(suscripcion);
            request.getSession().setAttribute("SUSCRIPCION",idSuscripcion);


        }else {
            Long idSuscripcion = (Long) request.getSession().getAttribute("SUSCRIPCION");
            Suscripcion suscripcion = servicioSuscripcion.verSuscripcionPorId(idSuscripcion);
            suscripcion.setFecha_ven(vencimiento.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
            servicioSuscripcion.editarSuscripcion(suscripcion);
        }


        return new ModelAndView("redirect:"+preference.getSandboxInitPoint());
    }
}
