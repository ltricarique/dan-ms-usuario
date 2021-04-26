package ar.edu.utn.frsf.isi.dan.usuario.model;

/**
 * @author Leandro Heraldo Tricarique
 *
 */
public class Usuario {
	private Integer id;
	private String nombre;
	private String clave;
	private TipoUsuario tipo;
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getClave() {
		return clave;
	}
	
	public void setClave(String clave) {
		this.clave = clave;
	}
	
	public TipoUsuario getTipo() {
		return tipo;
	}
	
	public void setTipo(TipoUsuario tipo) {
		this.tipo = tipo;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Usuario)
			return ((Usuario) obj).getId().equals(id);
		else
			return false;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nombre=" + nombre + ", clave=" + clave + ", tipo=" + tipo + "]";
	}
	
}
