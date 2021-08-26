package ar.edu.utn.frsf.isi.dan.usuario.model;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * @author Leandro Heraldo Tricarique
 * @author Francisco Tomas Gautero
 *
 */
@Entity
@Table(name = "CLIENTE", schema = "MS_USUARIO")
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Cliente
{
	@Id
	@SequenceGenerator(name = "clienteGenerator", sequenceName = "SECUENCIA_CLIENTE", schema = "MS_USUARIO", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "clienteGenerator")
	@Column(name = "ID")
	private Long id;
	@NotNull
	@Column(name = "RAZON_SOCIAL")
	private String razonSocial;
	@NotNull
	@Column(name = "CUIT")
	private String cuit;
	@NotNull
	@Column(name = "EMAIL")
	private String email;
	@Column(name = "MAXIMO_CUENTA_CORRIENTE")
	private BigDecimal maximoCuentaCorriente;
	@Column(name = "HABILITADO_ONLINE")
	private Boolean habilitadoOnline;
	@OneToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.REMOVE })
	@JoinColumn(name = "ID_USUARIO", foreignKey = @ForeignKey(name = "FK_CLIENTE_ID_USUARIO_TO_USUARIO_ID"))
	@NotNull
	private Usuario usuario;
	@Column(name = "FECHA_BAJA")
	private Instant fechaBaja;
	@OneToMany(mappedBy = "cliente", cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.REMOVE })
	private List<Obra> obras;

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public String getRazonSocial()
	{
		return razonSocial;
	}

	public void setRazonSocial(String razonSocial)
	{
		this.razonSocial = razonSocial;
	}

	public String getCuit()
	{
		return cuit;
	}

	public void setCuit(String cuit)
	{
		this.cuit = cuit;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public BigDecimal getMaximoCuentaCorriente()
	{
		return maximoCuentaCorriente;
	}

	public void setMaximoCuentaCorriente(BigDecimal maximoCuentaCorriente)
	{
		this.maximoCuentaCorriente = maximoCuentaCorriente;
	}

	public Boolean getHabilitadoOnline()
	{
		return habilitadoOnline;
	}

	public void setHabilitadoOnline(Boolean habilitadoOnline)
	{
		this.habilitadoOnline = habilitadoOnline;
	}

	public Usuario getUsuario()
	{
		return usuario;
	}

	public void setUsuario(Usuario usuario)
	{
		this.usuario = usuario;
	}

	public Instant getFechaBaja()
	{
		return fechaBaja;
	}

	public void setFechaBaja(Instant fechaBaja)
	{
		this.fechaBaja = fechaBaja;
	}

	public List<Obra> getObras()
	{
		return obras;
	}

	public void setObras(List<Obra> obras)
	{
		this.obras = obras;
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString()
	{
		return "Cliente [id=" + id + ", razonSocial=" + razonSocial + ", cuit=" + cuit + ", email=" + email + ", maximoCuentaCorriente="
			+ maximoCuentaCorriente + ", habilitadoOnline=" + habilitadoOnline + ", usuario=" + usuario + ", obras=" + obras + "]";
	}

}
