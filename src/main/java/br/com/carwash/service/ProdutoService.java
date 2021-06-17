package br.com.carwash.service;

import javax.ws.rs.core.Response.Status;

import br.com.carwash.dao.LojaDAO;
import br.com.carwash.dao.ProdutoDAO;
import br.com.carwash.dto.ProdutoCadastroDTO;
import br.com.carwash.entity.Loja;
import br.com.carwash.entity.Produto;
import br.com.carwash.exception.NotValidDataException;

public class ProdutoService {

	static LojaDAO lojaDao = new LojaDAO();

	static ProdutoDAO dao = new ProdutoDAO();

	public void criarProduto(ProdutoCadastroDTO dto) throws Exception {
		Loja loja = lojaDao.find(dto.getLojaId());
		if (loja == null)
			throw new NotValidDataException(Status.BAD_REQUEST, "Loja não existe");
		dao.save(new Produto(dto, loja));
	}
}
