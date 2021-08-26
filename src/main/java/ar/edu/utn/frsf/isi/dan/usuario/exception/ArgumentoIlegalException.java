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
public class ArgumentoIlegalException extends RuntimeException
{

	public ArgumentoIlegalException()
	{
		super();
	}

	public ArgumentoIlegalException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace)
	{
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ArgumentoIlegalException(String message, Throwable cause)
	{
		super(message, cause);
	}

	public ArgumentoIlegalException(String message)
	{
		super(message);
	}

	public ArgumentoIlegalException(Throwable cause)
	{
		super(cause);
	}

}
