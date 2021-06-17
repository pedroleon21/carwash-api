package br.com.carwash.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Response.Status;

import br.com.carwash.dao.LojaDAO;
import br.com.carwash.dao.ProdutoDAO;
import br.com.carwash.dto.LojaDTO;
import br.com.carwash.dto.ProutoCompletoDTO;
import br.com.carwash.entity.Loja;
import br.com.carwash.entity.Produto;
import br.com.carwash.exception.NotValidDataException;

public class LavaJatoService {

	LojaDAO dao = new LojaDAO(); 
	ProdutoDAO produtoDao = new ProdutoDAO();
	public List<LojaDTO> econtrarLojas(Long id, String nomeLoja, String email, String cnpj) throws Exception {
		List<LojaDTO> lojas = new ArrayList<LojaDTO>();
		List<Loja> lojasEt = dao.getListLojas(id,nomeLoja,email,cnpj);
		for(Loja l : lojasEt) {
			lojas.add(new LojaDTO(l));
		}
		return lojas;
	}

	public LojaDTO encontraLoja(Long idLoja) throws Exception {
		Loja l =null;
		l = dao.find(idLoja);
		if(l == null)
			throw new NotValidDataException(Status.NOT_FOUND,"Elemento inexistente");
		return new LojaDTO(l);
	}

	public void cadastrarLoja(LojaDTO loja) throws Exception {
		if(loja.getCnpj() == null ||  loja.getEmail() == null ||
				loja.getNomeLoja() == null )
			throw new NotValidDataException(Status.NOT_ACCEPTABLE,"faltando parametros");
		try {
			Loja l = loja.toEntity();
			l.setDataCadastro(Date.valueOf(LocalDate.now()));
			dao.save(l);
		}catch(Exception e) {
			throw e;
		}
	}

	public void excluirLoja(Long idLoja) throws Exception {
		dao.delete(idLoja);
	}

	public void editarLoja(LojaDTO lavajato) throws Exception {
		Loja loja= dao.find(lavajato.getId());
		dao.update(loja.toEtity(lavajato));
	}

	public List<ProutoCompletoDTO> listaProdutosPorLoja(Long idLoja) {
		List<Produto> produtos = produtoDao.econtraListaProtudosCliente(idLoja);
		List<ProutoCompletoDTO> listProduto = new ArrayList<ProutoCompletoDTO>();
		for(Produto p :produtos) {
			listProduto.add(new ProutoCompletoDTO(p));
		}
		return listProduto;
	}

}
