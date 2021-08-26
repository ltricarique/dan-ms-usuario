package ar.edu.utn.frsf.isi.dan.usuario.service;

import java.util.List;

import ar.edu.utn.frsf.isi.dan.usuario.model.Cliente;

/**
 * @author Leandro Heraldo Tricarique
 * @author Francisco Tomas Gautero
 *
 */
public interface ClienteService
{
	Cliente guardarCliente(Cliente cliente);

	Cliente actualizarCliente(Cliente cliente, Long id);

	Boolean bajaCliente(Long id);

	List<Cliente> listarClientes();

	Cliente obtenerClientePorId(Long id);

	List<Cliente> obtenerClientePorRazonSocial(String razonSocial);

	List<Cliente> obtenerClientePorCuit(String cuit);

	Cliente obtenerClientePorObra(Long id);

	Boolean existeCliente(Long id);

}
