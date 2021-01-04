package ar.edu.unlam.tallerweb1.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;
import java.util.Objects;

@Entity
public class Pelicula{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String descripcion;
    private Date fecha_estreno;
    private String genero;
    private String valor;
    private String ubicacionArchivo;
    private Integer visualizaciones;
    private Date fecha_publicada;
    private String portadaSlider;
    private Boolean isFree;

    public Integer getVisualizaciones() {
        return visualizaciones;
    }

    public void setVisualizaciones(Integer visualizaciones) {
        this.visualizaciones = visualizaciones;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFecha_estreno() {
        return fecha_estreno;
    }

    public void setFecha_estreno(Date fecha_estreno) {
        this.fecha_estreno = fecha_estreno;
    }

    public String getGenero() { return genero; }

    public void setGenero(String genero) { this.genero = genero; }
    
    public String getUbicacionArchivo() {
        return ubicacionArchivo;
    }

    public void setUbicacionArchivo(String ubicacionArchivo) {
        this.ubicacionArchivo = ubicacionArchivo;
    }

    public Date getFecha_publicada() {
        return fecha_publicada;
    }

    public void setFecha_publicada(Date fecha_publicada) {
        this.fecha_publicada = fecha_publicada;
    }

    public String getPortadaSlider() {
        return portadaSlider;
    }

    public void setPortadaSlider(String portadaSlider) {
        this.portadaSlider = portadaSlider;
    }

    public Boolean getFree() {
        return isFree;
    }

    public void setFree(Boolean free) {
        isFree = free;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pelicula pelicula = (Pelicula) o;
        return Objects.equals(id, pelicula.id) &&
                Objects.equals(titulo, pelicula.titulo) &&
                Objects.equals(descripcion, pelicula.descripcion) &&
                Objects.equals(fecha_estreno, pelicula.fecha_estreno) &&
                Objects.equals(genero, pelicula.genero) &&
                Objects.equals(valor, pelicula.valor) &&
                Objects.equals(ubicacionArchivo, pelicula.ubicacionArchivo)&&
                Objects.equals(fecha_publicada, pelicula.fecha_publicada);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, titulo, descripcion, fecha_estreno, genero, valor, ubicacionArchivo,fecha_publicada);
    }
}
