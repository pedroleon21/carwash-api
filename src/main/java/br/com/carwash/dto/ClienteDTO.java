package br.com.carwash.dto;

import java.util.Date;

import br.com.carwash.entity.Cliente;

public class ClienteDTO {
	
	private Long id;
	private String nome;
	private String cpf;
	private String email;
	private Date dataNacimento;
	
	public ClienteDTO(Cliente c) {
		nome = c.getNome();
		cpf = c.getCpf();
		id = c.getId();
		email = c.getEmail();
		dataNacimento = c.getDataNacimento();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getDataNacimento() {
		return dataNacimento;
	}

	public void setDataNacimento(Date dataNacimento) {
		this.dataNacimento = dataNacimento;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
