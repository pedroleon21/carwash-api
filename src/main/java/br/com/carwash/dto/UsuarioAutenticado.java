package br.com.carwash.dto;

import br.com.carwash.entity.Cliente;

public class UsuarioAutenticado {

	private Cliente cliente;
	
	public UsuarioAutenticado(Cliente cliente) {
		this.cliente = cliente;
	}

	public Long getUserId() {
		return cliente.getId();
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
}
