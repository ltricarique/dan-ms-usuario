package ar.edu.utn.frsf.isi.dan.usuario.controller;

/**
 * @author Leandro Heraldo Tricarique
 *
 */
interface Api {
	public static String BASE_PATH = "/api/v1";
	
	public static String CLIENTE_BASE_PATH = BASE_PATH + "/cliente";
	public static String CLIENTE_GET_CUIT_PATH = "/{cuit}";
	public static String CLIENTE_GET_ALL_PATH = "/listar";
	public static String CLIENTE_PUT_ID_PATH = "/{id}";
	
	public static String EMPLEADO_BASE_PATH = BASE_PATH + "/empleado";
	public static String EMPLEADO_GET_ID_PATH = "/{id}";
	public static String EMPLEADO_GET_ALL_PATH = "/listar";
	public static String EMPLEADO_PUT_ID_PATH = "/{id}";
	public static String EMPLEADO_DELETE_ID_PATH = "/{id}";
	
	public static String OBRA_BASE_PATH = BASE_PATH + "/obra";
	public static String OBRA_GET_ID_PATH = "/{id}";
	public static String OBRA_GET_ALL_PATH = "/listar";
	public static String OBRA_PUT_ID_PATH = "/{id}";
	public static String OBRA_DELETE_ID_PATH = "/{id}";
}
