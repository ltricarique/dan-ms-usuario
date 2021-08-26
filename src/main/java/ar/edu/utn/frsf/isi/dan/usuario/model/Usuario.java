package ar.edu.utn.frsf.isi.dan.usuario.model;

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

/**
 * @author Leandro Heraldo Tricarique
 * @author Francisco Tomas Gautero
 *
 */
@Entity
@Table(name = "USUARIO", schema = "MS_USUARIO")
public class Usuario
{
	@Id
	@SequenceGenerator(name = "usuarioGenerator", sequenceName = "SECUENCIA_USUARIO", schema = "MS_USUARIO", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usuarioGenerator")
	@Column(name = "ID")
	private Long id;
	@Column(name = "NOMBRE")
	private String nombre;
	@Column(name = "CLAVE")
	private String clave;
	@ManyToOne
	@JoinColumn(name = "ID_TIPO_USUARIO", foreignKey = @ForeignKey(name = "FK_USUARIO_ID_TIPO_USUARIO_TIPO_USUARIO_ID"))
	private TipoUsuario tipo;

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public String getNombre()
	{
		return nombre;
	}

	public void setNombre(String nombre)
	{
		this.nombre = nombre;
	}

	public String getClave()
	{
		return clave;
	}

	public void setClave(String clave)
	{
		this.clave = clave;
	}

	public TipoUsuario getTipo()
	{
		return tipo;
	}

	public void setTipo(TipoUsuario tipo)
	{
		this.tipo = tipo;
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
		Usuario other = (Usuario) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString()
	{
		return "Usuario [id=" + id + ", nombre=" + nombre + ", clave=" + clave + ", tipo=" + tipo + "]";
	}

}
