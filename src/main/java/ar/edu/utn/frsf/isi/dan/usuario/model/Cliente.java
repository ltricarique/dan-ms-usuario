package ar.edu.utn.frsf.isi.dan.usuario.model;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Leandro Heraldo Tricarique
 *
 */
public class Cliente {
	private Integer id;
	private String razonSocial;
	private String cuit;
	private String mail;
	private BigDecimal maxCuentaCorriente;
	private Boolean habilitadoOnline;
	private Usuario usuario;
	private List<Obra> obras;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRazonSocial() {
		return razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	public String getCuit() {
		return cuit;
	}

	public void setCuit(String cuit) {
		this.cuit = cuit;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public BigDecimal getMaxCuentaCorriente() {
		return maxCuentaCorriente;
	}

	public void setMaxCuentaCorriente(BigDecimal maxCuentaCorriente) {
		this.maxCuentaCorriente = maxCuentaCorriente;
	}

	public Boolean getHabilitadoOnline() {
		return habilitadoOnline;
	}

	public void setHabilitadoOnline(Boolean habilitadoOnline) {
		this.habilitadoOnline = habilitadoOnline;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Obra> getObras() {
		return obras;
	}

	public void setObras(List<Obra> obras) {
		this.obras = obras;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Cliente)
			return ((Cliente) obj).getId().equals(id);
		else
			return false;
	}

	@Override
	public String toString() {
		return "Cliente [id=" + id + ", razonSocial=" + razonSocial + ", cuit=" + cuit + ", mail=" + mail
				+ ", maxCuentaCorriente=" + maxCuentaCorriente + ", habilitadoOnline=" + habilitadoOnline + ", usuario="
				+ usuario + ", obras=" + obras + "]";
	}

}
