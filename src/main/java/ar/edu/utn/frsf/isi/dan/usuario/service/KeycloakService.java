package ar.edu.utn.frsf.isi.dan.usuario.service;

import ar.edu.utn.frsf.isi.dan.usuario.model.Cliente;
import ar.edu.utn.frsf.isi.dan.usuario.model.Usuario;

/**
 * @author Leandro Heraldo Tricarique
 * @author Francisco Tomas Gautero
 *
 */
public interface KeycloakService
{
	Usuario guardarUsuario(Cliente cliente);

	Boolean eliminarUsuario(Usuario usuario);

	Boolean bajaUsuario(Usuario usuario);
}
