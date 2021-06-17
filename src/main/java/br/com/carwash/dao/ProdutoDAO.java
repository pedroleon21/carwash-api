package br.com.carwash.dao;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.carwash.entity.Produto;
import br.com.carwash.utils.HibernateUtil;

public class ProdutoDAO extends SuperDAO<Produto> {

	public ProdutoDAO() {
		super(Produto.class);
	}

	public List<Produto> econtraListaProtudosCliente(Long idLoja) {
		EntityManager em = HibernateUtil.getEntityManager();
		try {
			String hql = " SELECT p FROM Produto p "
					+ " JOIN FETCH p.loja "
					+ " WHERE p.loja.id = :idLoja ";
			return em.createQuery(hql,Produto.class)
					.setParameter("idLoja",idLoja)
					.getResultList();
		}catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
}
