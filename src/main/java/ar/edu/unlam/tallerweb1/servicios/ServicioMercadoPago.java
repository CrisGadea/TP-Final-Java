package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Suscripcion;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.*;

public interface ServicioMercadoPago {

    Preference generePreference(Usuario usuario, float precio) throws MPException;
}
