package ar.edu.utn.frsf.isi.dan.usuario.service.impl;

import java.util.Arrays;
import java.util.List;

import javax.ws.rs.core.Response;

import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import ar.edu.utn.frsf.isi.dan.usuario.exception.RecursoNoEncontradoException;
import ar.edu.utn.frsf.isi.dan.usuario.model.Cliente;
import ar.edu.utn.frsf.isi.dan.usuario.model.Usuario;
import ar.edu.utn.frsf.isi.dan.usuario.rest.Role;
import ar.edu.utn.frsf.isi.dan.usuario.service.KeycloakService;

/**
 * @author Leandro Heraldo Tricarique
 * @author Francisco Tomas Gautero
 *
 */
@Service
public class KeycloakServiceImpl implements KeycloakService
{
	private static final Logger LOGGER = LoggerFactory.getLogger(KeycloakServiceImpl.class);

	private static final String KEYCLOAK_REALM_MASTER = "master";
	private static final String KEYCLOAK_ADMIN_CLI = "admin-cli";

	private String realm;
	private final Keycloak keycloak;

	@Autowired
	public KeycloakServiceImpl(@Value("${keycloak.auth-server-url}") String serverUrl, @Value("${keycloak.realm}") String realm)
	{
		this.realm = realm;
		String keycloakUsername = "admin";
		String keycloakPassword = "admin";

		this.keycloak = KeycloakBuilder.builder().serverUrl(serverUrl).realm(KEYCLOAK_REALM_MASTER).username(keycloakUsername)
			.password(keycloakPassword).clientId(KEYCLOAK_ADMIN_CLI).resteasyClient(new ResteasyClientBuilder().connectionPoolSize(10).build())
			.build();
	}

	//	@Autowired
	//	public KeycloakServiceImpl(@Value("${keycloak.auth-server-url}") String serverUrl, @Value("${keycloak.realm}") String realm,
	//		@Value("${keycloak.user}") String keycloakUsername, @Value("${keycloak.password}") String keycloakPassword)
	//	{
	//		this.realm = realm;
	//
	//		this.keycloak = KeycloakBuilder.builder().serverUrl(serverUrl).realm(KEYCLOAK_REALM_MASTER).username(keycloakUsername)
	//			.password(keycloakPassword).clientId(KEYCLOAK_ADMIN_CLI).resteasyClient(new ResteasyClientBuilder().connectionPoolSize(10).build())
	//			.build();
	//	}

	@Override
	public Usuario guardarUsuario(Cliente cliente)
	{
		Usuario usuario = null;
		UsersResource usersResource = getUsersResource();
		UserRepresentation userRepresentation = new UserRepresentation();
		userRepresentation.setUsername(cliente.getUsuario().getNombre());
		userRepresentation.setEmail(cliente.getEmail());
		userRepresentation.setFirstName(cliente.getRazonSocial());
		userRepresentation.setEnabled(true);
		//		userRepresentation.setRealmRoles(Arrays.asList(Role.CLIENTE));

		Response response = usersResource.create(userRepresentation);

		HttpStatus status = HttpStatus.valueOf(response.getStatus());

		switch (status)
		{
			case CREATED:
			{
				String path = response.getLocation().getPath();
				String userId = path.substring(path.lastIndexOf("/") + 1);

				CredentialRepresentation passwordCredential = new CredentialRepresentation();
				passwordCredential.setTemporary(false);
				passwordCredential.setType(CredentialRepresentation.PASSWORD);
				passwordCredential.setValue(cliente.getUsuario().getClave());

				usersResource.get(userId).resetPassword(passwordCredential);

				RealmResource realmResource = getRealmResource();
				RoleRepresentation roleRepresentation = realmResource.roles().get(Role.CLIENTE).toRepresentation();
				realmResource.users().get(userId).roles().realmLevel().add(Arrays.asList(roleRepresentation));

				usuario = cliente.getUsuario();

				LOGGER.info("Usuario keycloak guardado");

				break;
			}
			default:
			{
				LOGGER.info("Usuario keycloak no guardado");

				break;
			}
		}

		return usuario;
	}

	private RealmResource getRealmResource()
	{
		return keycloak.realm(realm);
	}

	private UsersResource getUsersResource()
	{
		return getRealmResource().users();
	}

	private UserRepresentation getUser(Usuario usuario)
	{
		UsersResource usersResource = getUsersResource();
		List<UserRepresentation> usuarios = usersResource.search(usuario.getNombre());

		if (usuarios != null && !usuarios.isEmpty())
			return usuarios.get(0);
		else
			return null;
	}

	@Override
	public Boolean eliminarUsuario(Usuario usuario)
	{
		UserRepresentation user = getUser(usuario);

		if (user == null)
			throw new RecursoNoEncontradoException("Usuario no encontrado.");

		UsersResource usersResource = getUsersResource();

		Response response = usersResource.delete(user.getId());
		HttpStatus status = HttpStatus.valueOf(response.getStatus());

		if (status == HttpStatus.OK)
		{
			LOGGER.info("Usuario keycloak eliminado");

			return true;
		}
		else
		{
			LOGGER.info("Usuario keycloak no eliminado");

			return false;
		}
	}

	@Override
	public Boolean bajaUsuario(Usuario usuario)
	{
		UserRepresentation user = getUser(usuario);

		if (user == null)
			throw new RecursoNoEncontradoException("Usuario no encontrado.");

		UsersResource usersResource = getUsersResource();

		try
		{
			UserResource userResource = usersResource.get(user.getId());

			UserRepresentation userRepresentation = userResource.toRepresentation();
			userRepresentation.setEnabled(false);

			userResource.update(userRepresentation);
		}
		catch (Exception e)
		{
			LOGGER.error("Error al inhabilitar usuario keycloak");
		}

		LOGGER.info("Usuario keycloak inhabilitado");

		return true;
	}

}
