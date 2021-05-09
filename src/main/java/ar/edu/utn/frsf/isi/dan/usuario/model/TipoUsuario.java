package ar.edu.utn.frsf.isi.dan.usuario.model;

import java.util.Objects;

/**
 * @author Leandro Heraldo Tricarique
 *
 */
public class TipoUsuario {
	private Integer id;
	private String tipo;

	public TipoUsuario(Integer id, String tipo) {
		this.id = id;
		this.tipo = tipo;
	}

	public Integer getId() {
		return id;
	}

	public String getTipo() {
		return tipo;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TipoUsuario other = (TipoUsuario) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "TipoUsuario [id=" + id + ", tipo=" + tipo + "]";
	}
}
