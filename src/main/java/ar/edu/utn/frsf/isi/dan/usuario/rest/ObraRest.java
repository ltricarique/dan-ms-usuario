package ar.edu.utn.frsf.isi.dan.usuario.rest;

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
import ar.edu.utn.frsf.isi.dan.usuario.model.Obra;
import ar.edu.utn.frsf.isi.dan.usuario.service.ObraService;
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
public class ObraRest
{
	@Autowired
	private ObraService obraService;

	@PostMapping(value = Api.OBRA_POST_ID_PATH)
	@Operation(summary = "Registra una nueva obra a un cliente.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Obra registrada correctamente"),
		@ApiResponse(responseCode = "400", description = "Solicitud incorrecta"),
		@ApiResponse(responseCode = "401", description = "No autorizado"), @ApiResponse(responseCode = "403", description = "Prohibido"),
		@ApiResponse(responseCode = "404", description = "Recurso no encontrado") })
	public ResponseEntity<?> registrar(@Parameter(description = "Obra a registrar") @RequestBody Obra obra,
		@Parameter(description = "Id cliente") @PathVariable Long idCliente)
	{
		try
		{
			return ResponseEntity.ok(obraService.guardarObra(obra, idCliente));
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

	@PutMapping(value = Api.OBRA_PUT_ID_PATH)
	@Operation(summary = "Actualiza una obra.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Obra actualizada"),
		@ApiResponse(responseCode = "400", description = "Solicitud incorrecta"),
		@ApiResponse(responseCode = "401", description = "No autorizado"), @ApiResponse(responseCode = "403", description = "Prohibido"),
		@ApiResponse(responseCode = "404", description = "Obra inexistente") })
	public ResponseEntity<?> actualizar(@RequestBody Obra obra, @Parameter(description = "Id de la obra a actualizar") @PathVariable Long idObra,
		@Parameter(description = "Id cliente") @PathVariable Long idCliente)
	{
		try
		{
			return ResponseEntity.ok(obraService.actualizarObra(obra, idObra, idCliente));
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

	@DeleteMapping(value = Api.OBRA_DELETE_ID_PATH)
	@Operation(summary = "Elimina una obra.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Obra eliminada"),
		@ApiResponse(responseCode = "400", description = "Solicitud incorrecta"),
		@ApiResponse(responseCode = "401", description = "No autorizado"), @ApiResponse(responseCode = "403", description = "Prohibido"),
		@ApiResponse(responseCode = "404", description = "Obra inexistente") })
	public ResponseEntity<?> eliminar(@Parameter(description = "Id de la obra a eliminar") @PathVariable Long id)
	{
		try
		{
			return ResponseEntity.ok(obraService.eliminarObra(id));
		}
		catch (ArgumentoIlegalException | OperacionNoPermitidaException e)
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

	@GetMapping(path = Api.OBRA_GET_ID_PATH)
	@Operation(summary = "Retorna una obra por id.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Obra recuperada"),
		@ApiResponse(responseCode = "400", description = "Solicitud incorrecta"),
		@ApiResponse(responseCode = "401", description = "No autorizado"), @ApiResponse(responseCode = "403", description = "Prohibido"),
		@ApiResponse(responseCode = "404", description = "Obra inexistente") })
	public ResponseEntity<?> obtenerPorId(@Parameter(description = "Id de la obra a retornar") @PathVariable Long id)
	{
		try
		{
			return ResponseEntity.ok(obraService.obtenerObraPorId(id));
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

	@GetMapping(path = Api.OBRA_GET_CLIENTE_PATH)
	@Operation(summary = "Retorna las obras por cliente y/o tipo de obra.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Obras recuperadas"),
		@ApiResponse(responseCode = "400", description = "Solicitud incorrecta"),
		@ApiResponse(responseCode = "401", description = "No autorizado"), @ApiResponse(responseCode = "403", description = "Prohibido"),
		@ApiResponse(responseCode = "404", description = "Obras inexistentes") })
	public ResponseEntity<?> obtenerPorCliente(@Parameter(description = "Id del cliente") @PathVariable Long id,
		@Parameter(description = "Id tipo de obra") @RequestParam(required = false) Long idTipoObra)
	{
		try
		{
			return ResponseEntity.ok(obraService.obtenerObrasCliente(id, idTipoObra));
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
