package ar.edu.utn.frsf.isi.dan.usuario.model;

/**
 * @author Leandro Heraldo Tricarique
 *
 */
public class Empleado {
	private Integer id;
	private String nombre;
	private String mail;
	private Usuario usuario;
	
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
	
	public String getMail() {
		return mail;
	}
	
	public void setMail(String mail) {
		this.mail = mail;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}
	
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Empleado)
			return ((Empleado) obj).getId().equals(id);
		else
			return false;
	}

	@Override
	public String toString() {
		return "Empleado [id=" + id + ", nombre=" + nombre + ", mail=" + mail + ", usuario=" + usuario + "]";
	}
	
}
