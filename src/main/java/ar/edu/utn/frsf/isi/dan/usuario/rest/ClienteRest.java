package ar.edu.utn.frsf.isi.dan.usuario.rest;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;

import ar.edu.utn.frsf.isi.dan.usuario.exception.ArgumentoIlegalException;
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
 * @author Francisco Tomas Gautero
 *
 */
@RestController
//@RolesAllowed(Role.EMPLEADO)
@RequestMapping(Api.CLIENTE_BASE_PATH)
@CrossOrigin
@Tag(name = "ClienteRest", description = "Permite gestionar los clientes de la empresa.")
public class ClienteRest
{
	@Autowired
	private ClienteService clienteService;

	@Autowired
	private EurekaClient eurekaCliente;

	@RolesAllowed(Role.EMPLEADO)
	@GetMapping(path = Api.CLIENTE_GET_CUIT_PATH)
	@Operation(summary = "Retorna un cliente por cuit.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Cliente recuperado"),
		@ApiResponse(responseCode = "400", description = "Solicitud incorrecta"),
		@ApiResponse(responseCode = "401", description = "No autorizado"), @ApiResponse(responseCode = "403", description = "Prohibido"),
		@ApiResponse(responseCode = "404", description = "Cliente inexistente") })
	public ResponseEntity<?> obtenerPorCuit(@Parameter(description = "Cuit del cliente a retornar") @PathVariable String cuit)
	{
		try
		{
			return ResponseEntity.ok(clienteService.obtenerClientePorCuit(cuit));
		}
		catch (ArgumentoIlegalException e)
		{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		catch (RecursoNoEncontradoException e)
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
		catch (Exception e)
		{
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	@RolesAllowed(Role.EMPLEADO)
	@GetMapping
	@Operation(summary = "Retorna los clientes por razón social.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Clientes recuperados"),
		@ApiResponse(responseCode = "400", description = "Solicitud incorrecta"),
		@ApiResponse(responseCode = "401", description = "No autorizado"), @ApiResponse(responseCode = "403", description = "Prohibido"),
		@ApiResponse(responseCode = "404", description = "Cliente inexistente") })
	public ResponseEntity<?> obtenerPorRazonSocial(
		@Parameter(description = "Razón social del cliente a retornar") @RequestParam(required = false) String razonSocial)
	{
		try
		{
			return ResponseEntity.ok(clienteService.obtenerClientePorRazonSocial(razonSocial));
		}
		catch (ArgumentoIlegalException e)
		{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		catch (RecursoNoEncontradoException e)
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
		catch (Exception e)
		{
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	@RolesAllowed(Role.EMPLEADO)
	@GetMapping(path = Api.CLIENTE_GET_OBRA_PATH)
	@Operation(summary = "Retorna los clientes por su obra.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Clientes recuperados"),
		@ApiResponse(responseCode = "400", description = "Solicitud incorrecta"),
		@ApiResponse(responseCode = "401", description = "No autorizado"), @ApiResponse(responseCode = "403", description = "Prohibido"),
		@ApiResponse(responseCode = "404", description = "Cliente inexistente") })
	public ResponseEntity<?> obtenerPorObra(@Parameter(description = "Id obra del cliente") @PathVariable() Long id)
	{
		try
		{
			return ResponseEntity.ok(clienteService.obtenerClientePorObra(id));
		}
		catch (ArgumentoIlegalException e)
		{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		catch (RecursoNoEncontradoException e)
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
		catch (Exception e)
		{
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	@RolesAllowed(value = { Role.EMPLEADO, Role.CLIENTE })
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
		catch (ArgumentoIlegalException e)
		{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		catch (RecursoNoEncontradoException e)
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
		catch (Exception e)
		{
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	@RolesAllowed(value = { Role.EMPLEADO, Role.CLIENTE })
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
		catch (ArgumentoIlegalException e)
		{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		catch (RecursoNoEncontradoException e)
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
		catch (Exception e)
		{
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	@RolesAllowed(Role.EMPLEADO)
	@DeleteMapping(value = Api.CLIENTE_DELETE_ID_PATH)
	@Operation(summary = "Elimina un cliente.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Cliente eliminado"),
		@ApiResponse(responseCode = "400", description = "Solicitud incorrecta"),
		@ApiResponse(responseCode = "401", description = "No autorizado"), @ApiResponse(responseCode = "403", description = "Prohibido"),
		@ApiResponse(responseCode = "404", description = "Cliente inexistente") })
	public ResponseEntity<?> eliminar(@Parameter(description = "Id del cliente a eliminar") @PathVariable Long id)
	{
		try
		{
			return ResponseEntity.ok(clienteService.bajaCliente(id));
		}
		catch (ArgumentoIlegalException e)
		{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		catch (RecursoNoEncontradoException e)
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
		catch (Exception e)
		{
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	@RolesAllowed(Role.EMPLEADO)
	@GetMapping(path = Api.CLIENTE_GET_ALL_PATH)
	@Operation(summary = "Retorna todos los clientes registrados.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Clientes registrados"),
		@ApiResponse(responseCode = "400", description = "Solicitud incorrecta"),
		@ApiResponse(responseCode = "401", description = "No autorizado"), @ApiResponse(responseCode = "403", description = "Prohibido"),
		@ApiResponse(responseCode = "404", description = "No existen clientes registrados") })
	public ResponseEntity<?> listar()
	{
		try
		{
			return ResponseEntity.ok(clienteService.listarClientes());
		}
		catch (ArgumentoIlegalException e)
		{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		catch (RecursoNoEncontradoException e)
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
		catch (Exception e)
		{
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	@GetMapping(path = "/instancia")
	@Operation(summary = "Retorna información de la instancia.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Información de la instancia"),
		@ApiResponse(responseCode = "400", description = "Solicitud incorrecta"),
		@ApiResponse(responseCode = "401", description = "No autorizado"), @ApiResponse(responseCode = "403", description = "Prohibido"),
		@ApiResponse(responseCode = "404", description = "Recurso no encontrado") })
	public ResponseEntity<?> instancia()
	{
		try
		{
			InstanceInfo service = eurekaCliente.getApplication("dan-ms-usuario").getInstances().get(0);

			String hostName = service.getHostName();
			int port = service.getPort();

			return ResponseEntity.ok("dan-ms-usuario -> [" + hostName + ":" + port + "]");
		}
		catch (ArgumentoIlegalException e)
		{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		catch (RecursoNoEncontradoException e)
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
		catch (Exception e)
		{
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}
}
