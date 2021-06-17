package br.com.carwash.rest;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import br.com.carwash.dto.ProdutoCadastroDTO;
import br.com.carwash.exception.NotValidDataException;
import br.com.carwash.service.ProdutoService;

@Path("produto")
public class RestProduto extends SuperRest {

	static ProdutoService service = new ProdutoService();

	@POST
	public Response cadastarProduto(ProdutoCadastroDTO dto) {
		try {
			isAtorizade();
			service.criarProduto(dto);
		} catch (NotValidDataException e) {
			return Response.status(e.getStatusCode()).entity(e.getMessage()).build();
		} catch (Exception e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
		return Response.status(Status.CREATED).build();
	}
}
