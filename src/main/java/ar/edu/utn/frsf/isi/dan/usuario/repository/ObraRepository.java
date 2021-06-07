package ar.edu.utn.frsf.isi.dan.usuario.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ar.edu.utn.frsf.isi.dan.usuario.model.Obra;

@Repository
public interface ObraRepository extends JpaRepository<Obra, Long>
{
	@Query("select o from Obra o where o.cliente.id = :idCliente and o.cliente.fechaBaja is null")
	List<Obra> findByCliente(Long idCliente);

	@Query("select o from Obra o where o.cliente.id = :idCliente and o.cliente.fechaBaja is null and o.tipo.id = :idTipoObra")
	List<Obra> findByClienteAndTipo(Long idCliente, Long idTipoObra);

	@Query("select count(o) > 0 from Obra o where o.id = :idObra and o.cliente.id = :idCliente")
	boolean existsByIdObraAndIdCliente(Long idObra, Long idCliente);

	@Query(value = "select count(*) = 0 from ms_pedido.pedido p, ms_usuario.obra o where p.id_obra = o.id and o.id = :id", nativeQuery = true)
	boolean canDelete(Long id);
}
