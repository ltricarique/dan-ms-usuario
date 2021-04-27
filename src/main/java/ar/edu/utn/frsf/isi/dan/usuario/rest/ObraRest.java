package ar.edu.utn.frsf.isi.dan.usuario.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ar.edu.utn.frsf.isi.dan.usuario.model.Obra;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * @author Leandro Heraldo Tricarique
 *
 */
@RestController
@RequestMapping(Api.OBRA_BASE_PATH)
@Tag(name = "ObraRest", description = "Permite gestionar las obras de los clientes de la empresa.")
public class ObraRest {
	private static final List<Obra> OBRAS = new ArrayList<>();
	private static Integer SEQUENCE = 1;

	@PostMapping
	@Operation(summary = "Registra una nueva obra.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Obra registrada correctamente"),
			@ApiResponse(responseCode = "401", description = "No autorizado"),
			@ApiResponse(responseCode = "403", description = "Prohibido"), })
	public ResponseEntity<Obra> crear(@RequestBody Obra obra) {
		obra.setId(SEQUENCE++);
		OBRAS.add(obra);

		return ResponseEntity.ok(obra);
	}

	@PutMapping(value = Api.OBRA_PUT_ID_PATH)
	@Operation(summary = "Actualiza una obra.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Obra actualizada"),
			@ApiResponse(responseCode = "401", description = "No autorizado"),
			@ApiResponse(responseCode = "403", description = "Prohibido"),
			@ApiResponse(responseCode = "404", description = "Obra inexistente") })
	public ResponseEntity<Obra> actualizar(@RequestBody Obra obra, @Parameter(description = "Id de la obra a actualizar") @PathVariable Integer id) {
		int index = OBRAS.indexOf(obra);

		if (index >= 0) {
			OBRAS.set(index, obra);
			return ResponseEntity.ok(obra);
		} else
			return ResponseEntity.notFound().build();
	}

	@DeleteMapping(value = Api.OBRA_DELETE_ID_PATH)
	@Operation(summary = "Elimina una obra.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Obra eliminada"),
			@ApiResponse(responseCode = "401", description = "No autorizado"),
			@ApiResponse(responseCode = "403", description = "Prohibido"),
			@ApiResponse(responseCode = "404", description = "Obra inexistente") })
	public ResponseEntity<Obra> eliminar(@Parameter(description = "Id de la obra a eliminar") @PathVariable Integer id) {
		Optional<Obra> obra = OBRAS.stream().filter(c -> c.getId().equals(id)).findFirst();
		
		if (obra.isPresent()) {
			OBRAS.remove(obra.get());
			return ResponseEntity.of(obra);
		} else
			return ResponseEntity.notFound().build();
	}

	@GetMapping(path = Api.OBRA_GET_ID_PATH)
	@Operation(summary = "Retorna una obra por id.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Obra recuperada"),
			@ApiResponse(responseCode = "401", description = "No autorizado"),
			@ApiResponse(responseCode = "403", description = "Prohibido"),
			@ApiResponse(responseCode = "404", description = "Obra inexistente") })
	public ResponseEntity<Obra> obtenerPorId(@Parameter(description = "Id de la obra a retornar") @PathVariable Integer id) {
		Optional<Obra> obra = OBRAS.stream().filter(c -> c.getId().equals(id)).findFirst();

		return ResponseEntity.of(obra);
	}

	@GetMapping
	@Operation(summary = "Retorna las obras por cliente y/o tipo de obra.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Obras recuperadas"),
			@ApiResponse(responseCode = "401", description = "No autorizado"),
			@ApiResponse(responseCode = "403", description = "Prohibido"),
			@ApiResponse(responseCode = "404", description = "Obras inexistentes") })
	public ResponseEntity<List<Obra>> obtenerPorClienteObra(@Parameter(description = "Razón social del cliente") @RequestParam(required = false) String razonSocialCliente, @Parameter(description = "Tipo de obra") @RequestParam(required = false) String tipoObra) {
		List<Obra> obras = OBRAS.stream().filter(o -> o.getCliente().getRazonSocial().equalsIgnoreCase(razonSocialCliente) | o.getTipo().getTipo().equalsIgnoreCase(tipoObra)).collect(Collectors.toList());

		if (obras != null && obras.size() > 0)
			return ResponseEntity.ok(obras);
		else
			return ResponseEntity.notFound().build();
	}

}
