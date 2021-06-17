package br.com.carwash.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Agendamento implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_AGENDAMENTO")
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Cliente.class)
	@JoinColumn(name = "FK_CLIENTE", referencedColumnName = "ID_CLIENTE", nullable = false)
	private Cliente cliente;

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Loja.class)
	@JoinColumn(name = "FK_LOJA", referencedColumnName = "ID_LOJA", nullable = false)
	private Loja lavaJato;

	@Column(name = "DTH_LAVAGEM", nullable = false)
	private Date dataLavagem;

	@Column(name = "DTH_AGENDAMENTO", nullable = false)
	private Date dataAgendamento;

	public Agendamento() {

	}

	public Agendamento(Cliente cliente, Loja loja, Long dth) {
		this.cliente = cliente;
		this.lavaJato = loja;
		this.dataLavagem = new Date(dth);
		this.dataAgendamento = new Date();
	}

	public Loja getLavaJato() {
		return lavaJato;
	}

	public void setLavaJato(Loja javaJato) {
		this.lavaJato = javaJato;
	}

	public Date getDataAgendamento() {
		return dataAgendamento;
	}

	public void setDataAgendamento(Date dataAgendamento) {
		this.dataAgendamento = dataAgendamento;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Date getDataLavagem() {
		return dataLavagem;
	}

	public void setDataLavagem(Date dataLavagem) {
		this.dataLavagem = dataLavagem;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
