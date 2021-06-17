package br.com.carwash.dto;

import java.util.Date;

import br.com.carwash.entity.Agendamento;
import br.com.carwash.entity.Cliente;
import br.com.carwash.entity.Loja;

public class AgendamentoCompletoDTO {

	
	private Cliente cliente;
	private Date dataAgendamento;
	private Date dataLavagem;
	private Long idAgendamento;
	private Loja lavajato;

	public AgendamentoCompletoDTO() {
		
	}

	public AgendamentoCompletoDTO(Agendamento a) {
		cliente = a.getCliente();
		dataAgendamento = a.getDataAgendamento();
		dataLavagem = a.getDataLavagem();
		idAgendamento = a.getId();
		lavajato = a.getLavaJato();
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Date getDataAgendamento() {
		return dataAgendamento;
	}

	public void setDataAgendamento(Date dataAgendamento) {
		this.dataAgendamento = dataAgendamento;
	}

	public Date getDataLavagem() {
		return dataLavagem;
	}

	public void setDataLavagem(Date dataLavagem) {
		this.dataLavagem = dataLavagem;
	}

	public Long getIdAgendamento() {
		return idAgendamento;
	}

	public void setIdAgendamento(Long idAgendamento) {
		this.idAgendamento = idAgendamento;
	}

	public Loja getLavajato() {
		return lavajato;
	}

	public void setLavajato(Loja lavajato) {
		this.lavajato = lavajato;
	}
}
