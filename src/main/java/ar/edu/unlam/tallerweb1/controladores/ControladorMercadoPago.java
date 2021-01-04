package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.modelo.Suscripcion;
import ar.edu.unlam.tallerweb1.servicios.ServicioMercadoPago;
import ar.edu.unlam.tallerweb1.servicios.ServicioSuscripcion;
import com.mercadopago.exceptions.MPException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ControladorMercadoPago {

    private ServicioMercadoPago servicioMercadoPago;
    private final ServicioSuscripcion servicioSuscripcion;

    @Autowired
    public ControladorMercadoPago(ServicioMercadoPago servicioMercadoPago, ServicioSuscripcion servicioSuscripcion){
        this.servicioMercadoPago = servicioMercadoPago;
        this.servicioSuscripcion = servicioSuscripcion;
    }

    @RequestMapping(path = "/pago-exitoso", method = RequestMethod.GET)
    public ModelAndView pagoExitoso(@RequestParam("collection_id") String collectionId,
                                    @RequestParam("collection_status") String collectionStatus,
                                    @RequestParam("external_reference") String externalReference,
                                    @RequestParam("payment_type") String paymentType,
                                    @RequestParam("merchant_order_id") String merchantOrderId,
                                    @RequestParam("preference_id") String preferenceId,
                                    @RequestParam("site_id") String siteId,
                                    @RequestParam("processing_mode") String processingMode,
                                    @RequestParam("merchant_account_id") String merchantAccountId) throws MPException{

        return new ModelAndView("redirect:/home");
    }

    @RequestMapping(path = "/pago-pendiente", method = RequestMethod.GET)
    public ModelAndView pagoPendiente(@RequestParam("collection_id") String collectionId,
                                      @RequestParam("collection_status") String collectionStatus,
                                      @RequestParam("external_reference") String externalReference,
                                      @RequestParam("payment_type") String paymentType,
                                      @RequestParam("merchant_order_id") String merchantOrderId,
                                      @RequestParam("preference_id") String preferenceId,
                                      @RequestParam("site_id") String siteId,
                                      @RequestParam("processing_mode") String processingMode,
                                      @RequestParam("merchant_account_id") String merchantAccountId) throws MPException{

        ModelMap modelo = new ModelMap();
        modelo.put("tipoDeMsj","info");
        modelo.put("msj","Compra pendiente de pago...");
        return new ModelAndView("redirect:/home");
    }

    @RequestMapping(path = "/pago-rechazado", method = RequestMethod.GET)
    public ModelAndView pagoRechazado(@RequestParam("collection_id") String collectionId,
                                      @RequestParam("collection_status") String collectionStatus,
                                      @RequestParam("external_reference") String externalReference,
                                      @RequestParam("payment_type") String paymentType,
                                      @RequestParam("merchant_order_id") String merchantOrderId,
                                      @RequestParam("preference_id") String preferenceId,
                                      @RequestParam("site_id") String siteId,
                                      @RequestParam("processing_mode") String processingMode,
                                      @RequestParam("merchant_account_id") String merchantAccountId,
                                      HttpServletRequest request) throws MPException {

        Long idSuscripcion = (Long) request.getSession().getAttribute("SUSCRIPCION");
        Suscripcion suscripcion = servicioSuscripcion.verSuscripcionPorId(idSuscripcion);
        servicioSuscripcion.eliminarSuscripcion(suscripcion);
        request.getSession().removeAttribute("SUSCRIPCION");

        return new ModelAndView("redirect:/home");
    }


}
