package br.com.carwash.rest;

import javax.annotation.security.PermitAll;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;

import br.com.carwash.dto.LoginDTO;
import br.com.carwash.exception.NotValidDataException;
import br.com.carwash.exception.SenhaIvalidaException;
import br.com.carwash.service.AutenticacaoService;

@Path("/auth")
public class RestAuth extends SuperRest{

	static AutenticacaoService service = new AutenticacaoService();
	

	@POST
	@PermitAll
	@Consumes(MediaType.APPLICATION_JSON)
	public Response login(LoginDTO login) {
		try {
			String token = service.gerarAutenticacao(login);
			 ResponseBuilder response = Response.status(Status.CREATED).entity(token);
			 response.header("Autorization", token);
			 return response.build();
		}catch(SenhaIvalidaException e) {
			return Response.status(Status.FORBIDDEN).build();
		}		catch(NotValidDataException e) {
			e.printStackTrace();
			return Response.status(e.getStatusCode()).entity(e.getMessage()).build();
		}		catch(Exception e) {
			return Response
					.status(Status.INTERNAL_SERVER_ERROR)
					.build();
		}
	}
	
	
	@PUT
	@PermitAll
	@Consumes(MediaType.APPLICATION_JSON)
	public Response refresh() {
		try {
			Long id =getTokenId();
			String token = service.renovarToken(id);
			 ResponseBuilder response = Response.status(Status.CREATED).entity(token);
			 response.header("Autorization", token);
			 return response.build();
		}catch(SenhaIvalidaException e) {
			return Response.status(Status.FORBIDDEN).build();
		}		catch(NotValidDataException e) {
			e.printStackTrace();
			return Response.status(e.getStatusCode()).entity(e.getMessage()).build();
		}		catch(Exception e) {
			return Response
					.status(Status.INTERNAL_SERVER_ERROR)
					.build();
		}
	}
}
