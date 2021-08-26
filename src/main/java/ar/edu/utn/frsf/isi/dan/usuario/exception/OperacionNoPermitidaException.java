package ar.edu.utn.frsf.isi.dan.usuario.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Leandro Heraldo Tricarique
 * @author Francisco Tomas Gautero
 *
 */
@SuppressWarnings("serial")
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class OperacionNoPermitidaException extends RuntimeException
{

	public OperacionNoPermitidaException()
	{
		super();
	}

	public OperacionNoPermitidaException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace)
	{
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public OperacionNoPermitidaException(String message, Throwable cause)
	{
		super(message, cause);
	}

	public OperacionNoPermitidaException(String message)
	{
		super(message);
	}

	public OperacionNoPermitidaException(Throwable cause)
	{
		super(cause);
	}

}
