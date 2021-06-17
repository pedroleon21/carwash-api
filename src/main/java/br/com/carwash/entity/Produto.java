package br.com.carwash.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import br.com.carwash.dto.ProdutoCadastroDTO;

@Entity
public class Produto implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_PRODUTO")
	private Long id;
	
	@ManyToOne(fetch = FetchType.EAGER, targetEntity = Loja.class)
	@JoinColumn(name = "FK_LOJA", referencedColumnName = "ID_LOJA", nullable = false)
	private Loja loja;
	
	@Column(name = "PRECO_PRODUTO")
	private BigDecimal preco;
	
	@Column(name = "NOME_PRODUTO")
	private String nome;
	
	public Produto() {
		
	}

	public Produto(ProdutoCadastroDTO dto, Loja loja) {
		id=dto.getId();
		this.loja = loja;
		preco = dto.getPreco();
		nome = dto.getNome();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Loja getLoja() {
		return loja;
	}

	public void setLoja(Loja loja) {
		this.loja = loja;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}