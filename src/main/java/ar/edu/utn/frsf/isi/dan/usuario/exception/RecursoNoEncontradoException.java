package ar.edu.utn.frsf.isi.dan.usuario.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Leandro Heraldo Tricarique
 * @author Francisco Tomas Gautero
 *
 */
@SuppressWarnings("serial")
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class RecursoNoEncontradoException extends RuntimeException
{

	public RecursoNoEncontradoException()
	{
		super();
	}

	public RecursoNoEncontradoException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace)
	{
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public RecursoNoEncontradoException(String message, Throwable cause)
	{
		super(message, cause);
	}

	public RecursoNoEncontradoException(String message)
	{
		super(message);
	}

	public RecursoNoEncontradoException(Throwable cause)
	{
		super(cause);
	}

}
