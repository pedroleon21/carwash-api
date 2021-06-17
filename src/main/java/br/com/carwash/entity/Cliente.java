package br.com.carwash.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import br.com.carwash.dto.UsuarioDTO;

@Entity
public class Cliente implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_CLIENTE")
	private Long id;

	@Column(name = "CPF", unique = true)
	private String cpf;

	@Column(name = "NOME")
	private String nome;

	@Column(name = "DS_EMAIL" ,unique = true)
	private String email;

	@Column(name = "SENHA")
	private String senha;

	@Column(name = "DATA_NACIMENTO")
	private Date dataNacimento;
	
	@Column(name = "EXCLUIDO")
	private Boolean excluido = false;
	
	
	public Cliente() {
		super();
	}

	public Cliente(UsuarioDTO user) {
		nome = user.getNome();
		senha = user.getSenha();
		email = user.getEmail();
		cpf = user.getCpf();
		dataNacimento = user.getDataNacimento();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf.replace(".", "").replace("-", "");
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Date getDataNacimento() {
		return dataNacimento;
	}

	public void setDataNacimento(Date dataNacimento) {
		this.dataNacimento = dataNacimento;
	}
	
	public boolean isExcluido() {
		return excluido;
	}

	public void setExcluido(boolean excluido) {
		this.excluido = excluido;
	}

	public Boolean getExcluido() {
		return excluido;
	}

	public void setExcluido(Boolean excluido) {
		this.excluido = excluido;
	}

	public Cliente toMerge(UsuarioDTO user) {
		if (Objects.nonNull(user.getNome()))
			this.nome = user.getNome();
		if (Objects.nonNull(user.getEmail()))
			this.email = user.getEmail();
		if (Objects.nonNull(user.getSenha()))
			this.senha = user.getSenha();
		if (Objects.nonNull(user.getDataNacimento()))
			this.dataNacimento = user.getDataNacimento();
		if (Objects.nonNull(user.getCpf()))
			this.cpf = user.getCpf();
		return this;
	}
}
