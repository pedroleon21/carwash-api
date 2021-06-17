package br.com.carwash.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import br.com.carwash.dto.AgendamentoDTO;
import br.com.carwash.service.AgendamentoService;

@Path("agendamento")
public class RestAgendamento extends SuperRest{
	
	AgendamentoService service= new AgendamentoService(); 
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response EcontraoAgendamentos(@QueryParam("agendamento") Long agendamento,
			@QueryParam("inicio") Long inicio, @QueryParam("fim") Long fim,@QueryParam("cliente") Long cliente) {
		try {
			isAtorizade();
			List<AgendamentoDTO> agendamentos = service.buscaListaAgendamentos(agendamento,cliente,inicio,fim);
			if (agendamentos.size() == 0)
				return Response.status(Status.NO_CONTENT).build();
			return Response.status(Status.FOUND).entity(agendamentos).build();
		}catch(Exception e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@GET
	@Path("/{agendamento}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response encontraAgendamento(@PathParam("agendamento") Long agendamento) {
		try {
			isAtorizade();
			AgendamentoDTO agendamentoDto = service
					.buscaAgendamentoPorId(agendamento);
			return Response
					.status(Status.FOUND)
					.entity(agendamentoDto)
					.build();
		}catch(NotFoundException e) {
			return Response.status(Status.NO_CONTENT).build();
		}catch(Exception e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response agendar(AgendamentoDTO agendamento) {
		try {
			isAtorizade();
			service.cadastarAgendamento(agendamento);
		}catch(Exception e) {
			e.printStackTrace();
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
		return Response.status(Status.ACCEPTED).build();
	}
}
