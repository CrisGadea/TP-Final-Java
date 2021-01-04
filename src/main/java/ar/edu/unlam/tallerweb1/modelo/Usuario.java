package ar.edu.unlam.tallerweb1.modelo;

import com.google.protobuf.Internal;

import javax.persistence.*;
import java.util.*;

// Clase que modela el concepto de Usuario, la anotacion @Entity le avisa a hibernate que esta clase es persistible
// el paquete ar.edu.unlam.tallerweb1.modelo esta indicado en el archivo hibernateCOntext.xml para que hibernate
// busque entities en el
@Entity
public class Usuario {

	// La anotacion id indica que este atributo es el utilizado como clave primaria de la entity, se indica que el valor es autogenerado.
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	// para el resto de los atributo no se usan anotaciones entonces se usa el default de hibernate: la columna se llama igual que
	// el atributo, la misma admite nulos, y el tipo de dato se deduce del tipo de dato de java.
	private String email;
	private String password;
	private String rol;
	private String username;
	private String tel;
	private String creation_at;
	private String updated_at;
	private Boolean isEnabled;

	@ManyToMany(fetch = FetchType.EAGER)
	private Set<Pelicula> peliculasMasTarde = new HashSet<>();
	@ManyToMany(fetch = FetchType.EAGER)
	private Set<Serie> seriesMasTarde = new HashSet<>();
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
			name = "historial_pelis_vistas",
			joinColumns = @JoinColumn(name = "usuario_id"),
			inverseJoinColumns = @JoinColumn(name = "pelicula_id"))
	private Set<Pelicula> peliculasVistas = new HashSet<>();

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
			name = "historial_series_vistas",
			joinColumns = @JoinColumn(name = "serie_id"),
			inverseJoinColumns = @JoinColumn(name = "usuario_id"))
	private Set<Serie> seriesVistas = new HashSet<>();



	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getCreation_at() {
		return creation_at;
	}

	public void setCreation_at(String creation_at) {
		this.creation_at = creation_at;
	}

	public String getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(String updated_at) {
		this.updated_at = updated_at;
	}

	public Set<Pelicula> getPeliculasMasTarde() {
		return peliculasMasTarde;
	}

	public void setPeliculasMasTarde(Set<Pelicula> peliculasMasTarde) {
		this.peliculasMasTarde = peliculasMasTarde;
	}

	public Set<Serie> getSeriesMasTarde() {
		return seriesMasTarde;
	}

	public void setSeriesMasTarde(Set<Serie> seiresMasTarde) {
		this.seriesMasTarde = seiresMasTarde;
	}

	public Set<Pelicula> getPeliculasVistas() {		return peliculasVistas;	}

	public void setPeliculasVistas(Set<Pelicula> peliculasVistas) {		this.peliculasVistas = peliculasVistas;	}

	public Set<Serie> getSeriesVistas() {		return seriesVistas;	}

	public void setSeriesVistas(Set<Serie> seriesVistas) {		this.seriesVistas = seriesVistas;	}

	public Boolean getEnabled() {
		return isEnabled;
	}

	public void setEnabled(Boolean enabled) {
		isEnabled = enabled;
	}
}
