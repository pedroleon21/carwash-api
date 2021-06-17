package br.com.carwash.service;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Response.Status;

import br.com.carwash.dao.AgendamentoDAO;
import br.com.carwash.dao.ClienteDAO;
import br.com.carwash.dao.LojaDAO;
import br.com.carwash.dto.ClienteDTO;
import br.com.carwash.dto.UsuarioDTO;
import br.com.carwash.entity.Agendamento;
import br.com.carwash.entity.Cliente;
import br.com.carwash.entity.Loja;
import br.com.carwash.exception.NotValidDataException;

public class ClienteService {

	ClienteDAO dao = new ClienteDAO();
	AgendamentoDAO agendamentoDao = new AgendamentoDAO();
	LojaDAO lojaDao = new LojaDAO();

	public List<ClienteDTO> encontraClientes(Long idCliente, String nomeCliente, String cpfClienteS) throws Exception {
		List<ClienteDTO> clientes = new ArrayList<ClienteDTO>();
		List<Cliente> entidades = dao.getClientes(idCliente, nomeCliente, cpfClienteS);
		for (Cliente c : entidades) {
			clientes.add(new ClienteDTO(c));
		}
		return clientes;
	}

	public ClienteDTO encontraCliente(long idCliente) throws Exception {
		Cliente c = null;
		c = dao.find(idCliente);
		if(c == null)
			throw new NotValidDataException(Status.NOT_FOUND,"Elemento inexistente");
		return new ClienteDTO(c);
	}

	public void novoCliente(UsuarioDTO user) throws Exception {
		if (user.getEmail() == null || user.getNome() == null || user.getSenha() == null || user.getCpf() == null
				|| user.getDataNacimento() == null)
			throw new NotValidDataException(Status.NOT_ACCEPTABLE, "faltando parametros");

		try {
			Cliente cliente = new Cliente(user);
			dao.save(cliente);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public void excluirCliente(Long idCliente) throws Exception {
		Cliente cliente =  dao.find(idCliente);
		cliente.setExcluido(true);
		dao.update(cliente);
	}

	public void editarCliente(UsuarioDTO user) throws Exception {
		Cliente cliente = dao.find(user.getId());
		dao.update(cliente.toMerge(user));
	}

	public void marcarAgendamento(Long idCliente, Long idLoja, Long dth) throws Exception {
		Cliente cliente = dao.find(idCliente);
		Loja loja = lojaDao.find(idLoja);
		if(cliente == null)
			throw new NotValidDataException(Status.PRECONDITION_FAILED,"Cliente Invalido");
		if(loja == null)
			throw new NotValidDataException(Status.PRECONDITION_FAILED,"Loja Invalida");
		Agendamento agendamento = new Agendamento(cliente,loja,dth);
		agendamentoDao.save(agendamento);
	}

}
