package br.com.carwash.dto;

import java.util.Date;

import br.com.carwash.entity.Agendamento;

public class AgendamentoDTO {

	private long idCliente;
	private long idLavaJato;
	private Date agendamento;
	public AgendamentoDTO() {
		
	}
	public AgendamentoDTO(Agendamento a) {
	idCliente = a.getCliente().getId();
	idLavaJato = a.getLavaJato().getId();
	agendamento = a.getDataAgendamento();
	}
	public long getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(long idCliente) {
		this.idCliente = idCliente;
	}
	public long getIdLavaJato() {
		return idLavaJato;
	}
	public void setIdLavaJato(long idLavaJato) {
		this.idLavaJato = idLavaJato;
	}
	public Date getAgendamento() {
		return agendamento;
	}
	public void setAgendamento(Date agendamento) {
		this.agendamento = agendamento;
	}
}
