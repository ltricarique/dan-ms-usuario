package ar.edu.utn.frsf.isi.dan.usuario.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.utn.frsf.isi.dan.usuario.exception.ArgumentoIlegalException;
import ar.edu.utn.frsf.isi.dan.usuario.exception.RecursoNoEncontradoException;
import ar.edu.utn.frsf.isi.dan.usuario.model.Cliente;
import ar.edu.utn.frsf.isi.dan.usuario.model.Obra;
import ar.edu.utn.frsf.isi.dan.usuario.repository.ObraRepository;
import ar.edu.utn.frsf.isi.dan.usuario.service.ClienteService;
import ar.edu.utn.frsf.isi.dan.usuario.service.ObraService;

@Service
public class ObraServiceImpl implements ObraService
{
	@Autowired
	private ObraRepository obraRepository;

	@Autowired
	private ClienteService clienteService;

	@Override
	public Obra guardarObra(Obra obra, Long idCliente)
	{
		if (obra != null)
		{
			Cliente cliente = clienteService.obtenerClientePorId(idCliente);

			obra.setCliente(cliente);;
			System.out.println("-- OBRA -> guardarObra()");
			return obraRepository.save(obra);
		}
		else
		{
			throw new ArgumentoIlegalException("Datos de la obra no suministrado.");
		}
	}

	@Override
	public Obra actualizarObra(Obra obra, Long id)
	{
		if (obra != null)
		{
			if (obraRepository.existsById(id))
			{
				obra.setId(id);
				System.out.println("-- OBRA -> actualizarObra()");
				return obraRepository.save(obra);
			}
			else
			{
				throw new RecursoNoEncontradoException("No existe obra.");
			}
		}
		else
		{
			throw new ArgumentoIlegalException("Datos de la obra no suministrado.");
		}
	}

	@Override
	public List<Obra> obtenerObrasCliente(Long idCliente, Long idTipoObra)
	{
		if (clienteService.existeCliente(idCliente))
		{
			if (idTipoObra == null)
				return obraRepository.findByCliente(idCliente);
			else
				return obraRepository.findByClienteAndTipo(idCliente, idTipoObra);
		}
		else
		{
			throw new RecursoNoEncontradoException("No existe cliente.");
		}
	}

	@Override
	public Obra obtenerObraPorId(Long id)
	{
		return obraRepository.findById(id).orElseThrow(() -> new RecursoNoEncontradoException("No existe obra."));
	}

}
