package br.com.carwash.dto;

import java.util.Date;

public class UsuarioDTO {

	private Long id;
	private String email;
	private String nome;
	private String senha;
	private String cpf;
	private Date dataNacimento;

	public String getEmail() {
		return email;
	}

	public String getNome() {
		return nome;
	}

	public String getSenha() {
		return senha;
	}

	public String getCpf() {
		return cpf != null ? cpf.replace(".", "").replace("-", ""): cpf;
	}

	public Date getDataNacimento() {
		return dataNacimento;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public void setDataNacimento(Date dataNacimento) {
		this.dataNacimento = dataNacimento;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
