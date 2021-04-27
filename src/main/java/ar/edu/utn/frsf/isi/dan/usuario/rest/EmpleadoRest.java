package ar.edu.utn.frsf.isi.dan.usuario.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

import ar.edu.utn.frsf.isi.dan.usuario.model.Empleado;

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
@RequestMapping(Api.EMPLEADO_BASE_PATH)
@Tag(name = "EmpleadoRest", description = "Permite gestionar los empleados de la empresa.")
public class EmpleadoRest {
	private static final List<Empleado> EMPLEADOS = new ArrayList<>();
	private static Integer SEQUENCE = 1;

	@PostMapping
	@Operation(summary = "Registra un nuevo empleado.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Empleado registrado correctamente"),
			@ApiResponse(responseCode = "401", description = "No autorizado"),
			@ApiResponse(responseCode = "403", description = "Prohibido"), })
	public ResponseEntity<Empleado> crear(@RequestBody Empleado empleado) {
		empleado.setId(SEQUENCE++);
		EMPLEADOS.add(empleado);

		return ResponseEntity.ok(empleado);
	}

	@PutMapping(value = Api.EMPLEADO_PUT_ID_PATH)
	@Operation(summary = "Actualiza un empleado.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Empleado actualizado"),
			@ApiResponse(responseCode = "401", description = "No autorizado"),
			@ApiResponse(responseCode = "403", description = "Prohibido"),
			@ApiResponse(responseCode = "404", description = "Empleado inexistente") })
	public ResponseEntity<Empleado> actualizar(@RequestBody Empleado empleado, @Parameter(description = "Id del empleado a actualizar") @PathVariable Integer id) {
		int index = EMPLEADOS.indexOf(empleado);

		if (index >= 0) {
			EMPLEADOS.set(index, empleado);
			return ResponseEntity.ok(empleado);
		} else
			return ResponseEntity.notFound().build();
	}

	@DeleteMapping(value = Api.EMPLEADO_DELETE_ID_PATH)
	@Operation(summary = "Elimina un empleado.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Empleado eliminado"),
			@ApiResponse(responseCode = "401", description = "No autorizado"),
			@ApiResponse(responseCode = "403", description = "Prohibido"),
			@ApiResponse(responseCode = "404", description = "Empleado inexistente") })
	public ResponseEntity<Empleado> eliminar(@Parameter(description = "Id del empleado a eliminar") @PathVariable Integer id) {
		Optional<Empleado> empleado = EMPLEADOS.stream().filter(c -> c.getId().equals(id)).findFirst();
		
		if (empleado.isPresent()) {
			EMPLEADOS.remove(empleado.get());
			return ResponseEntity.of(empleado);
		}
		else
			return ResponseEntity.notFound().build();
	}

	@GetMapping(path = Api.EMPLEADO_GET_ID_PATH)
	@Operation(summary = "Retorna un empleado por su id.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Empleado recuperado"),
			@ApiResponse(responseCode = "401", description = "No autorizado"),
			@ApiResponse(responseCode = "403", description = "Prohibido"),
			@ApiResponse(responseCode = "404", description = "Empleado inexistente") })
	public ResponseEntity<Empleado> obtenerPorId(@Parameter(description = "Id del empleado a retornar") @PathVariable Integer id) {
		Optional<Empleado> empleado = EMPLEADOS.stream().filter(c -> c.getId().equals(id)).findFirst();

		return ResponseEntity.of(empleado);
	}

	@GetMapping
	@Operation(summary = "Retorna un empleado por su nombre.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Empleado recuperado"),
			@ApiResponse(responseCode = "401", description = "No autorizado"),
			@ApiResponse(responseCode = "403", description = "Prohibido"),
			@ApiResponse(responseCode = "404", description = "Empleado inexistente") })
	public ResponseEntity<Empleado> obtenerPorNombre(@Parameter(description = "Nombre del empleado a retornar") @RequestParam(required = false) String nombre) {
		Optional<Empleado> empleado = EMPLEADOS.stream().filter(c -> c.getNombre().equalsIgnoreCase(nombre))
				.findFirst();

		return ResponseEntity.of(empleado);
	}

	@GetMapping(path = Api.EMPLEADO_GET_ALL_PATH)
	@Operation(summary = "Retorna todos los empleados registrados.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Empleados recuperados"),
			@ApiResponse(responseCode = "401", description = "No autorizado"),
			@ApiResponse(responseCode = "403", description = "Prohibido"),
			@ApiResponse(responseCode = "404", description = "No existen empleados registrados") })
	public ResponseEntity<List<Empleado>> listar() {
		if (!EMPLEADOS.isEmpty())
			return ResponseEntity.ok(EMPLEADOS);
		else
			return ResponseEntity.notFound().build();
	}
}
