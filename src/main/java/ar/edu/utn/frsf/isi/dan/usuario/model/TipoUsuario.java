package ar.edu.utn.frsf.isi.dan.usuario.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.annotation.Immutable;

/**
 * @author Leandro Heraldo Tricarique
 *
 */
@Entity
@Immutable
@Table(name = "TIPO_USUARIO", schema = "MS_USUARIO")
public class TipoUsuario
{
	@Id
	//	@SequenceGenerator(name = "tipoUsuarioGenerator", sequenceName = "SECUENCIA_TIPO_USUARIO", schema = "MS_USUARIO", allocationSize = 1)
	//	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tipoUsuarioGenerator")
	@Column(name = "ID")
	private Long id;
	@Column(name = "TIPO")
	private String tipo;

	public Long getId()
	{
		return id;
	}

	public String getTipo()
	{
		return tipo;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public void setTipo(String tipo)
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
		TipoUsuario other = (TipoUsuario) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString()
	{
		return "TipoUsuario [id=" + id + ", tipo=" + tipo + "]";
	}
}
