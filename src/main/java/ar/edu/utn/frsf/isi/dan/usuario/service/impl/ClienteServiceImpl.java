package ar.edu.utn.frsf.isi.dan.usuario.service.impl;

import java.time.Instant;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.utn.frsf.isi.dan.usuario.exception.ArgumentoIlegalException;
import ar.edu.utn.frsf.isi.dan.usuario.exception.RecursoNoEncontradoException;
import ar.edu.utn.frsf.isi.dan.usuario.model.Cliente;
import ar.edu.utn.frsf.isi.dan.usuario.model.Obra;
import ar.edu.utn.frsf.isi.dan.usuario.repository.ClienteRepository;
import ar.edu.utn.frsf.isi.dan.usuario.service.ClienteService;

/**
 * @author Leandro Heraldo Tricarique
 * @author Francisco Tomas Gautero
 *
 */
@Service
public class ClienteServiceImpl implements ClienteService
{
	private static final Logger LOGGER = LoggerFactory.getLogger(ClienteServiceImpl.class);

	@Autowired
	private ClienteRepository clienteRepository;

	@Override
	public Cliente guardarCliente(Cliente cliente)
	{
		if (cliente == null)
			throw new ArgumentoIlegalException("No existe cliente.");

		cliente.setId(null);

		List<Obra> obras = cliente.getObras();

		if (obras != null && obras.size() > 0)
		{
			for (Obra obra : obras)
			{
				if (obra.getTipo() == null)
					throw new ArgumentoIlegalException("Obra sin su tipo de obra.");

				obra.setCliente(cliente);
			}
		}
		else
		{
			throw new ArgumentoIlegalException("No existen obras.");
		}

		if (cliente.getUsuario() != null)
		{
			if (cliente.getUsuario().getNombre() == null)
			{
				cliente.getUsuario().setNombre(cliente.getEmail());
				cliente.getUsuario().setClave("1234");
			}
		}
		else
		{
			throw new ArgumentoIlegalException("No existe usuario.");
		}

		cliente = clienteRepository.save(cliente);
		LOGGER.info("Cliente guardado");

		return cliente;
	}

	@Override
	public Cliente actualizarCliente(Cliente cliente, Long id)
	{
		if (cliente == null)
			throw new ArgumentoIlegalException("Cliente no suministrado.");

		if (!clienteRepository.existsByIdAndFechaBajaIsNull(id))
			throw new RecursoNoEncontradoException("No existe cliente.");

		cliente.setId(id);
		cliente = clienteRepository.save(cliente);
		LOGGER.info("Cliente actualizado");

		return cliente;
	}

	@Override
	public Boolean bajaCliente(Long id)
	{
		if (!clienteRepository.existsByIdAndFechaBajaIsNull(id))
			throw new RecursoNoEncontradoException("No existe cliente.");

		if (clienteRepository.canDelete(id))
		{
			clienteRepository.deleteById(id);
		}
		else
		{
			Cliente cliente = clienteRepository.findByIdAndFechaBajaIsNull(id).get();
			cliente.setFechaBaja(Instant.now());
			cliente = clienteRepository.save(cliente);
		}

		LOGGER.info("Cliente dado de baja");

		return true;
	}

	@Override
	public List<Cliente> listarClientes()
	{
		return clienteRepository.findByFechaBajaIsNullOrderByRazonSocial();
	}

	@Override
	public Cliente obtenerClientePorId(Long id)
	{
		return clienteRepository.findByIdAndFechaBajaIsNull(id).orElseThrow(() -> new RecursoNoEncontradoException("No existe cliente."));
	}

	@Override
	public List<Cliente> obtenerClientePorRazonSocial(String razonSocial)
	{
		List<Cliente> cliente = clienteRepository.findByRazonSocialIgnoreCaseContainingAndFechaBajaIsNull(razonSocial);

		if (cliente == null || cliente.isEmpty())
			throw new RecursoNoEncontradoException("No existe cliente.");

		return cliente;
	}

	@Override
	public List<Cliente> obtenerClientePorCuit(String cuit)
	{
		List<Cliente> cliente = clienteRepository.findByCuitContainingAndFechaBajaIsNull(cuit);

		if (cliente == null || cliente.isEmpty())
			throw new RecursoNoEncontradoException("No existe cliente.");

		return cliente;
	}

	@Override
	public Cliente obtenerClientePorObra(Long id)
	{
		return clienteRepository.findByObraAndFechaBajaIsNull(id).orElseThrow(() -> new RecursoNoEncontradoException("No existe cliente."));
	}

	@Override
	public Boolean existeCliente(Long id)
	{
		return clienteRepository.existsByIdAndFechaBajaIsNull(id);
	}

}
