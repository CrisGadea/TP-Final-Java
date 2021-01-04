package ar.edu.unlam.tallerweb1.servicios;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.apache.commons.io.FilenameUtils;


import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Arrays;
import java.util.UUID;

@Service("servicioArchivo")
public class ServicioArchivoImpl implements ServicioArchivo{

    @Override
    public String guardarImagen(MultipartFile file, String path) {
        try {

            if(!file.isEmpty() && esImagenValida(file) ){

                String nombreRandom = UUID.randomUUID().toString() + '.' + FilenameUtils.getExtension(file.getOriginalFilename());
                Path pathcompleto = Paths.get( path + nombreRandom );
                Files.write(pathcompleto,file.getBytes());

                return nombreRandom;
            }

            return null;

        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean esImagenValida(MultipartFile file) {
        List<String> tiposDeImagenesValidas = Arrays.asList("image/png", "image/jpeg", "image/jpg", "image/jfif");
        List<String> extensionesValidasDeImagen = Arrays.asList("jpg", "png", "jpeg", "jfif");
        String extensionDeArchivo = FilenameUtils.getExtension( file.getOriginalFilename() ).toString();
        String tipoDeArchivo = file.getContentType().toString();

        if (  tiposDeImagenesValidas.contains(tipoDeArchivo) && extensionesValidasDeImagen.contains(extensionDeArchivo)) {
            return true;
        }
        return false;
    }

    @Override
    public boolean eliminarArchivo(String path){
        File fileABorrar = new File( path );
        return fileABorrar.delete();
    }

    @Override
    public String guardarVideo(MultipartFile file, String path) {
        try {

            if(!file.isEmpty() && esVideoValido(file) ){

                String nombreRandom = UUID.randomUUID().toString() + '.' + FilenameUtils.getExtension(file.getOriginalFilename());
                Path pathcompleto = Paths.get( path + nombreRandom );
                Files.write(pathcompleto,file.getBytes());

                return nombreRandom;
            }

            return null;

        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean esVideoValido(MultipartFile file) {
        List<String> tiposDeVideosValidos = Arrays.asList("video/mp4");
        List<String> extensionesValidasDeVideos = Arrays.asList("mp4");
        String extensionDeArchivo = FilenameUtils.getExtension( file.getOriginalFilename() ).toString();
        String tipoDeArchivo = file.getContentType().toString();

        if (  tiposDeVideosValidos.contains(tipoDeArchivo) && extensionesValidasDeVideos.contains(extensionDeArchivo)) {
            return true;
        }
        return false;
    }
}
