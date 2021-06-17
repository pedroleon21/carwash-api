package br.com.carwash.dto;

import java.math.BigDecimal;

public class ProdutoCadastroDTO {

	private Long lojaId;
	private Long id;
	private BigDecimal preco;
	private String nome;

	public Long getLojaId() {
		return lojaId;
	}

	public void setLojaId(Long lojaId) {
		this.lojaId = lojaId;
	}

	public Long getId() {
		return id;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public String getNome() {
		return nome;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
}
