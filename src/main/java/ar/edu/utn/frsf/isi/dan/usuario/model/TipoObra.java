package ar.edu.utn.frsf.isi.dan.usuario.model;

/**
 * @author Leandro Heraldo Tricarique
 *
 */
public class TipoObra {
	private Integer id;
	private String tipo;
	
	public TipoObra(Integer id, String tipo) {
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
	public boolean equals(Object obj) {
		if (obj instanceof TipoObra)
			return ((TipoObra) obj).getId().equals(id);
		else
			return false;
	}

	@Override
	public String toString() {
		return "TipoObra [id=" + id + ", tipo=" + tipo + "]";
	}

}
