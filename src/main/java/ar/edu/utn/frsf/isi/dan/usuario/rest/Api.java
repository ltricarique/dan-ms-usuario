package ar.edu.utn.frsf.isi.dan.usuario.rest;

/**
 * @author Leandro Heraldo Tricarique
 *
 */
interface Api
{
	String BASE_PATH = "/api/v1";

	String CLIENTE_BASE_PATH = BASE_PATH + "/cliente";
	String CLIENTE_GET_CUIT_PATH = "/{cuit}";
	String CLIENTE_GET_ALL_PATH = "/listar";
	String CLIENTE_GET_OBRA_PATH = "/obra/{id}";
	String CLIENTE_PUT_ID_PATH = "/{id}";
	String CLIENTE_DELETE_ID_PATH = "/{id}";

	String EMPLEADO_BASE_PATH = BASE_PATH + "/empleado";
	String EMPLEADO_GET_ID_PATH = "/{id}";
	String EMPLEADO_GET_ALL_PATH = "/listar";
	String EMPLEADO_PUT_ID_PATH = "/{id}";
	String EMPLEADO_DELETE_ID_PATH = "/{id}";

	String OBRA_BASE_PATH = BASE_PATH + "/obra";
	String OBRA_GET_ID_PATH = "/{id}";
	String OBRA_GET_CLIENTE_PATH = "/cliente/{id}";
	String OBRA_GET_ALL_PATH = "/listar";
	String OBRA_POST_ID_PATH = "/{idCliente}";
	String OBRA_PUT_ID_PATH = "/{idObra}/cliente/{idCliente}";
	String OBRA_DELETE_ID_PATH = "/{id}";
}
