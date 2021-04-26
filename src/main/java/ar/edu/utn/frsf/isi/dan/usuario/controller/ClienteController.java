package ar.edu.utn.frsf.isi.dan.usuario.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ar.edu.utn.frsf.isi.dan.usuario.model.Cliente;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * @author Leandro Heraldo Tricarique
 *
 */
@RestController
@RequestMapping(Api.CLIENTE_BASE_PATH)
@Tag(name = "ClienteController", description = "Permite gestionar los clientes de la empresa.")
public class ClienteController {
	private static final List<Cliente> CLIENTES = new ArrayList<>();
//	private static Integer SEQUENCE = 1;

	@GetMapping(path = Api.CLIENTE_GET_CUIT_PATH)
	@Operation(summary = "Retorna un cliente por cuit.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Cliente recuperado"),
			@ApiResponse(responseCode = "401", description = "No autorizado"),
			@ApiResponse(responseCode = "403", description = "Prohibido"),
			@ApiResponse(responseCode = "404", description = "Cliente inexistente") })
	public ResponseEntity<Cliente> obtenerPorCuit(@PathVariable String cuit) {
		Optional<Cliente> cliente = CLIENTES.stream().filter(c -> c.getCuit().equals(cuit)).findFirst();

		return ResponseEntity.of(cliente);
	}

	@GetMapping
	@Operation(summary = "Retorna un cliente por razón social.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Cliente recuperado"),
			@ApiResponse(responseCode = "401", description = "No autorizado"),
			@ApiResponse(responseCode = "403", description = "Prohibido"),
			@ApiResponse(responseCode = "404", description = "Cliente inexistente") })
	public ResponseEntity<Cliente> obtenerPorRazonSocial(@RequestParam(required = false) String razonSocial) {
		Optional<Cliente> cliente = CLIENTES.stream().filter(c -> c.getRazonSocial().equalsIgnoreCase(razonSocial))
				.findFirst();

		return ResponseEntity.of(cliente);
	}

	@PostMapping
	@Operation(summary = "Registra un nuevo cliente.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Cliente registrado correctamente"),
			@ApiResponse(responseCode = "401", description = "No autorizado"),
			@ApiResponse(responseCode = "403", description = "Prohibido"), })
	public ResponseEntity<Cliente> crear(@RequestBody Cliente cliente) {
//		cliente.setId(SEQUENCE++);
		CLIENTES.add(cliente);

		return ResponseEntity.ok(cliente);
	}

	@PutMapping(path = Api.CLIENTE_PUT_ID_PATH)
	@Operation(summary = "Actualiza un cliente.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Cliente actualizado"),
			@ApiResponse(responseCode = "401", description = "No autorizado"),
			@ApiResponse(responseCode = "403", description = "Prohibido"),
			@ApiResponse(responseCode = "404", description = "Cliente inexistente") })
	public ResponseEntity<Cliente> actualizar(@RequestBody Cliente cliente, @PathVariable Integer id) {
		int index = CLIENTES.indexOf(cliente);

		if (index >= 0) {
			CLIENTES.set(index, cliente);
			return ResponseEntity.ok(cliente);
		}
		else
			return ResponseEntity.notFound().build();
	}

	@GetMapping(path = Api.CLIENTE_GET_ALL_PATH)
	@Operation(summary = "Retorna todos los clientes registrados.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Clientes registrados"),
			@ApiResponse(responseCode = "401", description = "No autorizado"),
			@ApiResponse(responseCode = "403", description = "Prohibido"),
			@ApiResponse(responseCode = "404", description = "No existen clientes registrados") })
	public ResponseEntity<List<Cliente>> listar() {
		if (!CLIENTES.isEmpty())
			return ResponseEntity.ok(CLIENTES);
		else
			return ResponseEntity.notFound().build();
	}
}
