package ar.edu.utn.frsf.isi.dan.usuario.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.utn.frsf.isi.dan.usuario.exception.ArgumentoIlegalException;
import ar.edu.utn.frsf.isi.dan.usuario.exception.OperacionNoPermitidaException;
import ar.edu.utn.frsf.isi.dan.usuario.exception.RecursoNoEncontradoException;
import ar.edu.utn.frsf.isi.dan.usuario.model.Cliente;
import ar.edu.utn.frsf.isi.dan.usuario.model.Obra;
import ar.edu.utn.frsf.isi.dan.usuario.repository.ObraRepository;
import ar.edu.utn.frsf.isi.dan.usuario.service.ClienteService;
import ar.edu.utn.frsf.isi.dan.usuario.service.ObraService;

/**
 * @author Leandro Heraldo Tricarique
 * @author Francisco Tomas Gautero
 *
 */
@Service
public class ObraServiceImpl implements ObraService
{
	private static final Logger LOGGER = LoggerFactory.getLogger(ObraServiceImpl.class);

	@Autowired
	private ObraRepository obraRepository;

	@Autowired
	private ClienteService clienteService;

	@Override
	public Obra guardarObra(Obra obra, Long idCliente)
	{
		if (obra == null)
			throw new ArgumentoIlegalException("Obra no suministrada.");

		Cliente cliente = clienteService.obtenerClientePorId(idCliente);

		obra.setCliente(cliente);

		obra = obraRepository.save(obra);
		LOGGER.info("Obra guardada");

		return obra;
	}

	@Override
	public Obra actualizarObra(Obra obra, Long idObra, Long idCliente)
	{
		if (obra == null)
			throw new ArgumentoIlegalException("Obra no suministrada.");

		if (!obraRepository.existsById(idObra))
			throw new RecursoNoEncontradoException("No existe obra.");

		if (!clienteService.existeCliente(idCliente))
			throw new RecursoNoEncontradoException("No existe cliente.");

		if (!obraRepository.existsByIdObraAndIdCliente(idObra, idCliente))
			throw new ArgumentoIlegalException("La obra no pertenece al cliente.");

		Cliente cliente = clienteService.obtenerClientePorId(idCliente);

		obra.setId(idObra);
		obra.setCliente(cliente);

		obra = obraRepository.save(obra);
		LOGGER.info("Obra actualizada");

		return obra;
	}

	@Override
	public List<Obra> obtenerObrasCliente(Long idCliente, Long idTipoObra)
	{
		if (!clienteService.existeCliente(idCliente))
			throw new RecursoNoEncontradoException("No existe cliente.");

		if (idTipoObra == null)
			return obraRepository.findByCliente(idCliente);
		else
			return obraRepository.findByClienteAndTipo(idCliente, idTipoObra);
	}

	@Override
	public Obra obtenerObraPorId(Long id)
	{
		return obraRepository.findById(id).orElseThrow(() -> new RecursoNoEncontradoException("No existe obra."));
	}

	@Override
	public Boolean eliminarObra(Long id)
	{
		if (!obraRepository.existsById(id))
			throw new RecursoNoEncontradoException("No existe obra.");

		if (!obraRepository.canDelete(id))
			throw new OperacionNoPermitidaException("No es posible eliminar la obra.");

		obraRepository.deleteById(id);

		LOGGER.info("Obra eliminada");

		return true;
	}

}
