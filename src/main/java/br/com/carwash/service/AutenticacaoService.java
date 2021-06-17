package br.com.carwash.service;

import javax.ws.rs.core.Response.Status;

import br.com.carwash.aplicacao.token.JwtToken;
import br.com.carwash.dao.ClienteDAO;
import br.com.carwash.dto.LoginDTO;
import br.com.carwash.dto.UsuarioAutenticado;
import br.com.carwash.entity.Cliente;
import br.com.carwash.exception.NotValidDataException;
import br.com.carwash.exception.SenhaIvalidaException;

public class AutenticacaoService {

	private static ClienteDAO  dao = new ClienteDAO();

	public String gerarAutenticacao(LoginDTO login) throws Exception  { 
		Cliente cliente = dao.findFromEmail(login.getEmail(),login.getSenha());
		if(cliente == null) throw new NotValidDataException(Status.BAD_REQUEST,"Usuário invalido");
		if(!login.getSenha().equals(cliente.getSenha()))
			throw new SenhaIvalidaException();
		return JwtToken.getStringTocken(new UsuarioAutenticado(cliente));
	}

	public String renovarToken(Long id) throws Exception {
		Cliente cliente = dao.find(id);
		return JwtToken.getStringTocken(new UsuarioAutenticado(cliente));
	}
	
	
	
}
