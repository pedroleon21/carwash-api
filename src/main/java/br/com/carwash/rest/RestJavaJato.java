package br.com.carwash.rest;

import java.util.List;
import java.util.Objects;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import br.com.carwash.dto.LojaDTO;
import br.com.carwash.dto.ProutoCompletoDTO;
import br.com.carwash.exception.NotValidDataException;
import br.com.carwash.service.LavaJatoService;

@Path("lavajato")
public class RestJavaJato extends SuperRest {

	static final LavaJatoService service = new LavaJatoService();

	@Path("/{idLoja}/produto")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getProdutosPorLoja(@PathParam("idLoja") Long idLoja) {
		try {
			isAtorizade();
			List<ProutoCompletoDTO> produtos = service.listaProdutosPorLoja(idLoja);
			if (produtos.size() == 0)
				return Response.status(Status.NO_CONTENT).build();
			return Response.status(Status.FOUND).entity(produtos).build();
		} catch (Exception e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@PUT
	public Response solicitarEdicaoLoja(LojaDTO loja) {
		try {
			isAtorizade();
			service.editarLoja(loja);
		} catch (IllegalArgumentException e) {
			return Response.status(Status.EXPECTATION_FAILED).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
		return Response.status(Status.ACCEPTED).build();
	}

	@DELETE
	@Path("/{idLoja}")
	public Response solicitaExclusao(@PathParam("idLoja") Long idLoja) {
		try {
			isAtorizade();
			service.excluirLoja(idLoja);
		} catch (IllegalArgumentException e) {
			return Response.status(Status.EXPECTATION_FAILED).build();
		} catch (Exception e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
		return Response.status(Status.ACCEPTED).build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response buscarLojas(@QueryParam("cnpj") String cnpj, @QueryParam("nome") String nomeLoja,
			@QueryParam("email") String email, @QueryParam("loja") Long id) {
		List<LojaDTO> lojas = null;
		try {
			isAtorizade();
			lojas = service.econtrarLojas(id, nomeLoja, email, cnpj);
		} catch (Exception e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
		if (lojas.size() == 0)
			return Response.status(Status.NO_CONTENT).build();
		return Response.status(Status.FOUND).entity(lojas).build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{idLoja}")
	public Response buscaCleinte(@PathParam("idLoja") Long idLoja) {
		LojaDTO loja = null;
		try {
			isAtorizade();
			loja = service.encontraLoja(idLoja);
		} catch (NotValidDataException e) {
			return Response.status(e.getStatusCode()).entity(e.getMessage()).build();
		} catch (Exception e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
		return Response.status(Status.FOUND).entity(loja).build();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response criaNovaLoja(LojaDTO loja) {
		if (Objects.isNull(loja))
			return Response.status(Status.BAD_REQUEST).build();
		try {
			service.cadastrarLoja(loja);
		} catch (NotValidDataException e) {
			return Response.status(e.getStatusCode()).build();
		} catch (Exception e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
		return Response.status(Status.CREATED).build();
	}
}
