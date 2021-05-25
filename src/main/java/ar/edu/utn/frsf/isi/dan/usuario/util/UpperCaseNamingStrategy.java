package ar.edu.utn.frsf.isi.dan.usuario.util;

import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;
import org.hibernate.boot.model.naming.Identifier;
import org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy;

public class UpperCaseNamingStrategy extends SpringPhysicalNamingStrategy
{
	@Override
	protected Identifier getIdentifier(String name, boolean quoted, JdbcEnvironment jdbcEnvironment)
	{
		return new Identifier(name.toUpperCase(), true);
	}

}
