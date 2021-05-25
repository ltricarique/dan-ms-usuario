package ar.edu.utn.frsf.isi.dan.usuario.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ar.edu.utn.frsf.isi.dan.usuario.model.Obra;

@Repository
public interface ObraRepository extends JpaRepository<Obra, Long>
{
	@Query("SELECT o FROM Obra o WHERE o.cliente.id = :idCliente AND o.cliente.fechaBaja IS NULL")
	List<Obra> findByCliente(Long idCliente);
	//	List<Obra> findByCliente_IdAndCliente_ObrasIsNotNull(Long idCliente);

	@Query("SELECT o FROM Obra o WHERE o.cliente.id = :idCliente AND o.cliente.fechaBaja IS NULL AND o.tipo.id = :idTipoObra")
	List<Obra> findByClienteAndTipo(Long idCliente, Long idTipoObra);

}
