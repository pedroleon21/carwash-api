package br.com.carwash.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import br.com.carwash.dto.LojaDTO;

@Entity
public class Loja implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_LOJA")
	private Long id;

	@Column(name = "CNPJ", unique = true)
	private String cnpj;

	@Column(name = "NOME_LOJA")
	private String nome;

//	@Column(name = "LOCALIZACAO_LOJA")
//	private Location localizacao;

	@Column(name = "DS_EMAIL")
	private String email;

	@Column(name = "DATA_CADASTRO")
	private Date dataCadastro;

	public Loja() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
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

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Loja toEtity(LojaDTO lavajato) {
		if (Objects.nonNull(lavajato.getNomeLoja()))
			this.nome = lavajato.getNomeLoja();
		if (Objects.nonNull(lavajato.getCnpj()))
			this.cnpj = lavajato.getCnpj();
		if (Objects.nonNull(lavajato.getDataCadastro()))
			this.dataCadastro = lavajato.getDataCadastro();
		if (Objects.nonNull(lavajato.getEmail()))
			this.email = lavajato.getEmail();
		return this;
	}
}
