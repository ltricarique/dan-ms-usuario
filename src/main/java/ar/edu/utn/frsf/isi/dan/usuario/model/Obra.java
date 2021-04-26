package ar.edu.utn.frsf.isi.dan.usuario.model;

import java.math.BigDecimal;

/**
 * @author Leandro Heraldo Tricarique
 *
 */
public class Obra {
	private Integer id;
	private String descripcion;
	private BigDecimal latitud;
	private BigDecimal longitud;
	private String direccion;
	private Integer superficie;
	private TipoObra tipo;
	private Cliente cliente;
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public BigDecimal getLatitud() {
		return latitud;
	}
	
	public void setLatitud(BigDecimal latitud) {
		this.latitud = latitud;
	}
	
	public BigDecimal getLongitud() {
		return longitud;
	}
	
	public void setLongitud(BigDecimal longitud) {
		this.longitud = longitud;
	}
	
	public String getDireccion() {
		return direccion;
	}
	
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	public Integer getSuperficie() {
		return superficie;
	}
	
	public void setSuperficie(Integer superficie) {
		this.superficie = superficie;
	}
	
	public TipoObra getTipo() {
		return tipo;
	}
	
	public void setTipo(TipoObra tipo) {
		this.tipo = tipo;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Obra)
			return ((Obra) obj).getId().equals(id);
		else
			return false;
	}

	@Override
	public String toString() {
		return "Obra [id=" + id + ", descripcion=" + descripcion + ", latitud=" + latitud + ", longitud=" + longitud
				+ ", direccion=" + direccion + ", superficie=" + superficie + ", tipo=" + tipo + ", cliente=" + cliente
				+ "]";
	}
	
}
