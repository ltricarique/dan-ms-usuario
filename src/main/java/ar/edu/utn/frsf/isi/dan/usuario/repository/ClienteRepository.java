package ar.edu.utn.frsf.isi.dan.usuario.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ar.edu.utn.frsf.isi.dan.usuario.model.Cliente;

/**
 * @author Leandro Heraldo Tricarique
 * @author Francisco Tomas Gautero
 *
 */
@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long>
{
	List<Cliente> findByRazonSocialIgnoreCaseContainingAndFechaBajaIsNull(String razonSocial);

	List<Cliente> findByCuitContainingAndFechaBajaIsNull(String cuit);

	List<Cliente> findByFechaBajaIsNullOrderByRazonSocial();

	Optional<Cliente> findByIdAndFechaBajaIsNull(Long id);

	@Query("select o.cliente from Obra o where o.id = :idObra and o.cliente.fechaBaja is null")
	Optional<Cliente> findByObraAndFechaBajaIsNull(Long idObra);

	boolean existsByIdAndFechaBajaIsNull(Long id);

	@Query(value = "select count(*) = 0 from ms_pedido.pedido p, ms_usuario.obra o where p.id_obra = o.id and o.id_cliente = :id", nativeQuery = true)
	boolean canDelete(Long id);
}
