package ar.edu.utn.frsf.isi.dan.usuario.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Leandro Heraldo Tricarique
 * @author Francisco Tomas Gautero
 *
 */
@SuppressWarnings("serial")
@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class ErrorInternoException extends RuntimeException
{

	public ErrorInternoException()
	{
		super();
	}

	public ErrorInternoException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace)
	{
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ErrorInternoException(String message, Throwable cause)
	{
		super(message, cause);
	}

	public ErrorInternoException(String message)
	{
		super(message);
	}

	public ErrorInternoException(Throwable cause)
	{
		super(cause);
	}

}
