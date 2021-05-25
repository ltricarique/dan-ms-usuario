package ar.edu.utn.frsf.isi.dan.usuario.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import ar.edu.utn.frsf.isi.dan.usuario.exception.ArgumentoIlegalException;
import ar.edu.utn.frsf.isi.dan.usuario.exception.OperacionNoPermitidaException;
import ar.edu.utn.frsf.isi.dan.usuario.exception.RecursoNoEncontradoException;
import ar.edu.utn.frsf.isi.dan.usuario.model.Cliente;
import ar.edu.utn.frsf.isi.dan.usuario.service.ClienteService;
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
@RequestMapping(Api.CLIENTE_BASE_PATH)
@Tag(name = "ClienteRest", description = "Permite gestionar los clientes de la empresa.")
public class ClienteRest
{
	@Autowired
	private ClienteService clienteService;

	@GetMapping(path = Api.CLIENTE_GET_CUIT_PATH)
	@Operation(summary = "Retorna un cliente por cuit.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Cliente recuperado"),
		@ApiResponse(responseCode = "401", description = "No autorizado"), @ApiResponse(responseCode = "403", description = "Prohibido"),
		@ApiResponse(responseCode = "404", description = "Cliente inexistente") })
	public ResponseEntity<?> obtenerPorCuit(@Parameter(description = "Cuit del cliente a retornar") @PathVariable String cuit)
	{
		try
		{
			return ResponseEntity.ok(clienteService.obtenerClientePorCuit(cuit));
		}
		catch (RecursoNoEncontradoException | ArgumentoIlegalException e)
		{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		catch (Exception e)
		{
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	@GetMapping
	@Operation(summary = "Retorna los clientes por razón social.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Clientes recuperados"),
		@ApiResponse(responseCode = "401", description = "No autorizado"), @ApiResponse(responseCode = "403", description = "Prohibido"),
		@ApiResponse(responseCode = "404", description = "Cliente inexistente") })
	public ResponseEntity<?> obtenerPorRazonSocial(
		@Parameter(description = "Razón social del cliente a retornar") @RequestParam(required = false) String razonSocial)
	{
		try
		{
			return ResponseEntity.ok(clienteService.obtenerClientePorRazonSocial(razonSocial));
		}
		catch (RecursoNoEncontradoException | ArgumentoIlegalException e)
		{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		catch (Exception e)
		{
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	@GetMapping(path = Api.CLIENTE_GET_OBRA_PATH)
	@Operation(summary = "Retorna los clientes por su obra.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Clientes recuperados"),
		@ApiResponse(responseCode = "401", description = "No autorizado"), @ApiResponse(responseCode = "403", description = "Prohibido"),
		@ApiResponse(responseCode = "404", description = "Cliente inexistente") })
	public ResponseEntity<?> obtenerPorObra(@Parameter(description = "Id obra del cliente") @PathVariable() Long id)
	{
		try
		{
			return ResponseEntity.ok(clienteService.obtenerClientePorObra(id));
		}
		catch (RecursoNoEncontradoException | ArgumentoIlegalException e)
		{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		catch (Exception e)
		{
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	@PostMapping
	@Operation(summary = "Registra un nuevo cliente.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Cliente registrado correctamente"),
		@ApiResponse(responseCode = "400", description = "Solicitud incorrecta"),
		@ApiResponse(responseCode = "401", description = "No autorizado"), @ApiResponse(responseCode = "403", description = "Prohibido"),
		@ApiResponse(responseCode = "404", description = "Recurso no encontrado") })
	public ResponseEntity<?> registrar(@RequestBody @Valid Cliente cliente)
	{
		try
		{
			return ResponseEntity.ok(clienteService.guardarCliente(cliente));
		}
		catch (RecursoNoEncontradoException | ArgumentoIlegalException | OperacionNoPermitidaException e)
		{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		catch (Exception e)
		{
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	@PutMapping(path = Api.CLIENTE_PUT_ID_PATH)
	@Operation(summary = "Actualiza un cliente.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Cliente actualizado"),
		@ApiResponse(responseCode = "400", description = "Solicitud incorrecta"),
		@ApiResponse(responseCode = "401", description = "No autorizado"), @ApiResponse(responseCode = "403", description = "Prohibido"),
		@ApiResponse(responseCode = "404", description = "Cliente inexistente") })
	public ResponseEntity<?> actualizar(@Parameter(description = "Cliente a actualizar") @RequestBody Cliente cliente,
		@Parameter(description = "Id del cliente a actualizar") @PathVariable Long id)
	{
		try
		{
			return ResponseEntity.ok(clienteService.actualizarCliente(cliente, id));
		}
		catch (RecursoNoEncontradoException | ArgumentoIlegalException e)
		{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		catch (Exception e)
		{
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	@DeleteMapping(value = Api.CLIENTE_DELETE_ID_PATH)
	@Operation(summary = "Elimina un cliente.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Cliente eliminado"),
		@ApiResponse(responseCode = "401", description = "No autorizado"), @ApiResponse(responseCode = "403", description = "Prohibido"),
		@ApiResponse(responseCode = "404", description = "Cliente inexistente") })
	public ResponseEntity<?> eliminar(@Parameter(description = "Id del cliente a eliminar") @PathVariable Long id)
	{
		try
		{
			return ResponseEntity.ok(clienteService.bajaCliente(id));
		}
		catch (RecursoNoEncontradoException | ArgumentoIlegalException e)
		{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		catch (Exception e)
		{
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	@GetMapping(path = Api.CLIENTE_GET_ALL_PATH)
	@Operation(summary = "Retorna todos los clientes registrados.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Clientes registrados"),
		@ApiResponse(responseCode = "401", description = "No autorizado"), @ApiResponse(responseCode = "403", description = "Prohibido"),
		@ApiResponse(responseCode = "404", description = "No existen clientes registrados") })
	public ResponseEntity<List<Cliente>> listar()
	{
		return ResponseEntity.ok(clienteService.listarClientes());
	}
}
