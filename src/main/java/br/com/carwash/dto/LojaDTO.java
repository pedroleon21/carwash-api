package br.com.carwash.dto;

import java.util.Date;


import br.com.carwash.entity.Loja;

public class LojaDTO {
	
	private String nomeLoja;
	private Long id;
	private String email;
	private Date dataCadastro;
	private String cnpj;
//	private Location localizacao = Location.getDefaultInstance();

	public LojaDTO() {
		
	}
	
	public LojaDTO(Loja l) {
		cnpj = l.getCnpj();
		dataCadastro = l.getDataCadastro();
		email = l.getEmail();
		id = l.getId();
		nomeLoja = l.getNome();
//		localizacao = l.getLocalizacao();
	}

	public String getNomeLoja() {
		return nomeLoja;
	}

	public void setNomeLoja(String loja) {
		this.nomeLoja = loja;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

//	public Location getLocalizacao() {
//		return localizacao;
//	}
//
//	public void setLocalizacao(Location localizacao) {
//		this.localizacao = localizacao;
//	}

	public Loja toEntity() {
		Loja loja = new Loja();
		if(nomeLoja != null) loja.setNome(nomeLoja);;
		if(id != null) loja.setId(id);
		if(email != null) loja.setEmail(email);;
		if(dataCadastro != null) loja.setDataCadastro(dataCadastro);
		if(cnpj != null) this.setCnpj(cnpj);;
//		if(localizacao != null) loja.setLocalizacao(localizacao);
		return loja;
	}
}
