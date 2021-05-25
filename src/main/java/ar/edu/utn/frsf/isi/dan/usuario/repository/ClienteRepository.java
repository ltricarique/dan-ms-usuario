package ar.edu.utn.frsf.isi.dan.usuario.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ar.edu.utn.frsf.isi.dan.usuario.model.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long>
{
	List<Cliente> findByRazonSocialIgnoreCaseContainingAndFechaBajaIsNull(String razonSocial);

	List<Cliente> findByCuitContainingAndFechaBajaIsNull(String cuit);

	List<Cliente> findByFechaBajaIsNull();

	Optional<Cliente> findByIdAndFechaBajaIsNull(Long id);

	@Query("SELECT o.cliente FROM Obra o WHERE o.id = :id AND o.cliente.fechaBaja IS NULL")
	Optional<Cliente> findByObraAndFechaBajaIsNull(Long id);

	@Query("SELECT COUNT(c) > 0 FROM Cliente c WHERE c.id = :id AND fechaBaja IS NULL")
	boolean existsById(Long id);
}
