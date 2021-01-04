package ar.edu.unlam.tallerweb1.modelo;

import javax.persistence.*;

@Entity
public class Capitulo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String descripcion;
    private Integer horas;
    private Integer minutos;
    private String ubicacionArchivo;

    @ManyToOne(optional = false)
    private Serie serie;

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

    public Integer getHoras() {
        return horas;
    }

    public void setHoras(Integer horas) {
        this.horas = horas;
    }

    public Integer getMinutos() {
        return minutos;
    }

    public void setMinutos(Integer minutos) {
        this.minutos = minutos;
    }

    public String getUbicacionArchivo() {
        return ubicacionArchivo;
    }

    public void setUbicacionArchivo(String ubicacionArchivo) {
        this.ubicacionArchivo = ubicacionArchivo;
    }

    public Serie getSerie() { return serie; }

    public void setSerie(Serie serie) { this.serie = serie; }
}

