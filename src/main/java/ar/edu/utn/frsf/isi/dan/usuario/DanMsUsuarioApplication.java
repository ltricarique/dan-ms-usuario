package ar.edu.utn.frsf.isi.dan.usuario;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author Leandro Heraldo Tricarique
 * @author Francisco Tomas Gautero
 *
 */
@SpringBootApplication
@EnableEurekaClient
public class DanMsUsuarioApplication
{
	public static void main(String[] args)
	{
		SpringApplication.run(DanMsUsuarioApplication.class, args);
	}
}
