package ar.edu.utn.frsf.isi.dan.usuario.service;

import java.util.List;

import ar.edu.utn.frsf.isi.dan.usuario.model.Obra;

public interface ObraService
{
	Obra guardarObra(Obra obra, Long idCliente);

	Obra actualizarObra(Obra obra, Long id);

	List<Obra> obtenerObrasCliente(Long idCliente, Long idTipoObra);

	Obra obtenerObraPorId(Long id);
}
