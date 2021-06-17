package br.com.carwash.dto;

import java.math.BigDecimal;

import br.com.carwash.entity.Produto;

public class ProutoCompletoDTO {

	private Long id;
	private LojaDTO Loja;
	private String nome;
	private BigDecimal preco;

	public ProutoCompletoDTO() {

	}

	public ProutoCompletoDTO(Produto produto) {
		id = produto.getId();
		Loja = new LojaDTO(produto.getLoja());
		nome = produto.getNome();
		preco = produto.getPreco();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LojaDTO getLoja() {
		return Loja;
	}

	public void setLoja(LojaDTO loja) {
		Loja = loja;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}
}
