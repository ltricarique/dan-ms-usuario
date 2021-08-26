package ar.edu.utn.frsf.isi.dan.usuario.service;

import java.util.List;

import ar.edu.utn.frsf.isi.dan.usuario.model.Obra;

/**
 * @author Leandro Heraldo Tricarique
 * @author Francisco Tomas Gautero
 *
 */
public interface ObraService
{
	Obra guardarObra(Obra obra, Long idCliente);

	Obra actualizarObra(Obra obra, Long idObra, Long idCliente);

	List<Obra> obtenerObrasCliente(Long idCliente, Long idTipoObra);

	Obra obtenerObraPorId(Long id);

	Boolean eliminarObra(Long id);
}
