package ar.edu.unlam.tallerweb1.servicios;

import org.springframework.web.multipart.MultipartFile;

public interface ServicioArchivo {

    String guardarImagen(MultipartFile file, String path);
    boolean esImagenValida(MultipartFile file);
    boolean eliminarArchivo(String path);
    String guardarVideo(MultipartFile file, String path);
    boolean esVideoValido(MultipartFile file);
}
