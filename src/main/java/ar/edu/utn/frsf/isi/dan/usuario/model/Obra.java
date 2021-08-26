package ar.edu.utn.frsf.isi.dan.usuario.model;

import java.math.BigDecimal;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author Leandro Heraldo Tricarique
 * @author Francisco Tomas Gautero
 *
 */
@Entity
@Table(name = "OBRA", schema = "MS_USUARIO")
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Obra
{
	@Id()
	@SequenceGenerator(name = "obraGenerator", sequenceName = "SECUENCIA_OBRA", schema = "MS_USUARIO", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "obraGenerator")
	@Column(name = "ID")
	private Long id;
	@Column(name = "DESCRIPCION")
	private String descripcion;
	@Column(name = "LATITUD")
	private BigDecimal latitud;
	@Column(name = "LONGITUD")
	private BigDecimal longitud;
	@Column(name = "DIRECCION")
	private String direccion;
	@Column(name = "SUPERFICIE")
	private BigDecimal superficie;
	@ManyToOne
	@JoinColumn(name = "ID_TIPO_OBRA", foreignKey = @ForeignKey(name = "FK_OBRA_ID_TIPO_OBRA_TO_TIPO_OBRA_ID"))
	private TipoObra tipo;
	@ManyToOne
	@JoinColumn(name = "ID_CLIENTE", foreignKey = @ForeignKey(name = "FK_OBRA_ID_CLIENTE_TO_CLIENTE_ID"))
	@JsonIgnore
	private Cliente cliente;

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public String getDescripcion()
	{
		return descripcion;
	}

	public void setDescripcion(String descripcion)
	{
		this.descripcion = descripcion;
	}

	public BigDecimal getLatitud()
	{
		return latitud;
	}

	public void setLatitud(BigDecimal latitud)
	{
		this.latitud = latitud;
	}

	public BigDecimal getLongitud()
	{
		return longitud;
	}

	public void setLongitud(BigDecimal longitud)
	{
		this.longitud = longitud;
	}

	public String getDireccion()
	{
		return direccion;
	}

	public void setDireccion(String direccion)
	{
		this.direccion = direccion;
	}

	public BigDecimal getSuperficie()
	{
		return superficie;
	}

	public void setSuperficie(BigDecimal superficie)
	{
		this.superficie = superficie;
	}

	public TipoObra getTipo()
	{
		return tipo;
	}

	public void setTipo(TipoObra tipo)
	{
		this.tipo = tipo;
	}

	public Cliente getCliente()
	{
		return cliente;
	}

	public void setCliente(Cliente cliente)
	{
		this.cliente = cliente;
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
		Obra other = (Obra) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString()
	{
		return "Obra [id=" + id + ", descripcion=" + descripcion + ", latitud=" + latitud + ", longitud=" + longitud + ", direccion=" + direccion
			+ ", superficie=" + superficie + ", tipo=" + tipo + ", cliente=" + cliente + "]";
	}

}
