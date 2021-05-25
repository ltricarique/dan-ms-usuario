package ar.edu.utn.frsf.isi.dan.usuario.service.impl;

import java.time.Instant;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.utn.frsf.isi.dan.usuario.exception.ArgumentoIlegalException;
import ar.edu.utn.frsf.isi.dan.usuario.exception.OperacionNoPermitidaException;
import ar.edu.utn.frsf.isi.dan.usuario.exception.RecursoNoEncontradoException;
import ar.edu.utn.frsf.isi.dan.usuario.model.Cliente;
import ar.edu.utn.frsf.isi.dan.usuario.model.Obra;
import ar.edu.utn.frsf.isi.dan.usuario.repository.ClienteRepository;
import ar.edu.utn.frsf.isi.dan.usuario.service.ClienteService;

@Service
public class ClienteServiceImpl implements ClienteService
{
	@Autowired
	private ClienteRepository clienteRepository;

	@Override
	public Cliente guardarCliente(Cliente cliente)
	{
		if (cliente != null)
		{
			if (cliente.getId() == null)
			{
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

				System.out.println("-- CLIENTE -> guardarCliente()");
				return clienteRepository.save(cliente);
			}
			else
			{
				throw new OperacionNoPermitidaException("Operación no permitida.");
			}
		}
		else
		{
			throw new ArgumentoIlegalException("No existe cliente.");
		}
	}

	@Override
	public Cliente actualizarCliente(Cliente cliente, Long id)
	{
		if (cliente != null)
		{
			if (clienteRepository.existsById(id))
			{
				cliente.setId(id);
				System.out.println("-- CLIENTE -> actualizarCliente()");
				return clienteRepository.save(cliente);
			}
			else
			{
				throw new RecursoNoEncontradoException("No existe cliente.");
			}
		}
		else
		{
			throw new ArgumentoIlegalException("Datos del cliente no suministrado.");
		}
	}

	@Override
	public Cliente bajaCliente(Long id)
	{
		Cliente cliente = clienteRepository.findByIdAndFechaBajaIsNull(id)
			.orElseThrow(() -> new RecursoNoEncontradoException("No existe cliente."));
		cliente.setFechaBaja(Instant.now());
		return clienteRepository.save(cliente);
	}

	@Override
	public List<Cliente> listarClientes()
	{
		return clienteRepository.findByFechaBajaIsNull();
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
			new RecursoNoEncontradoException("No existe cliente.");

		return cliente;
	}

	@Override
	public List<Cliente> obtenerClientePorCuit(String cuit)
	{
		List<Cliente> cliente = clienteRepository.findByCuitContainingAndFechaBajaIsNull(cuit);

		if (cliente == null || cliente.isEmpty())
			new RecursoNoEncontradoException("No existe cliente.");

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
		return clienteRepository.existsById(id);
	}

}
