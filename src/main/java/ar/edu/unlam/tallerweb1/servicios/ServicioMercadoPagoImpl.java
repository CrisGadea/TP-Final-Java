package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Usuario;
import com.mercadopago.MercadoPago;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.Preference;
import com.mercadopago.resources.datastructures.preference.BackUrls;
import com.mercadopago.resources.datastructures.preference.Item;
import com.mercadopago.resources.datastructures.preference.Payer;
import com.mercadopago.resources.datastructures.preference.PaymentMethods;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("servicioMercadoPago")
@Transactional
public class ServicioMercadoPagoImpl implements ServicioMercadoPago{

    @Override
    public Preference generePreference(Usuario usuario, float precio) throws MPException {
        // Agrega credenciales
        MercadoPago.SDK.setAccessToken("TEST-201967482075433-111919-7b20934a0d7c5c882f13238c6b0e1451-226677784");

        // Crea un objeto de preferencia
        Preference preference = new Preference();

        // Crea un item en la preferencia
        Item item = new Item();
        item.setTitle("Suscripción")
                .setQuantity(1)
                .setUnitPrice(precio);
        preference.appendItem(item);

        // Crea el pagador
        Payer payer = new Payer();
        payer.setEmail( usuario.getEmail() );
        preference.setPayer(payer);

        // Urls para las respuestas de MercadoPago
        String urlBase = "http://localhost:8080/proyecto_limpio_spring_war_exploded/";
        BackUrls backUrls = new BackUrls(
                urlBase+"pago-exitoso/",
                urlBase+"pago-pendiente/",
                urlBase+"pago-rechazado/");
        preference.setBackUrls(backUrls);
        preference.setAutoReturn(Preference.AutoReturn.all);

        // Pagos en una cuota
        PaymentMethods paymentMethods = new PaymentMethods();
        paymentMethods.setDefaultInstallments(1);
        preference.setPaymentMethods(paymentMethods);

        preference.save();
        return preference;
    }
}
