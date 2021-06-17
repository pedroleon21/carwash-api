package br.com.carwash.dao;

import java.util.List;
import java.util.Objects;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.carwash.entity.Loja;
import br.com.carwash.utils.HibernateUtil;

public class LojaDAO extends SuperDAO<Loja> {

	public LojaDAO() {
		super(Loja.class);
	}

	@SuppressWarnings("unchecked")
	public List<Loja> getListLojas(Long id, String nomeLoja, String email, String cnpj) throws Exception {
		EntityManager em = HibernateUtil.getEntityManager();
		try {
			String hql = " SELECT l FROM Loja l WHERE 1 = 1 ";
			if (Objects.nonNull(id))
				hql = hql + " AND l.id = :id ";
			if (Objects.nonNull(nomeLoja))
				hql = hql + " AND l.nome = :nomeLoja ";
			if (Objects.nonNull(email))
				hql = hql + " AND l.email = :email ";
			if (Objects.nonNull(cnpj))
				hql = hql + " AND l.cnpj = :cnpj ";

			Query query = em.createQuery(hql, Loja.class);

			if (Objects.nonNull(id))
				query.setParameter("id", id);
			if (Objects.nonNull(nomeLoja))
				query.setParameter("nomeLoja", nomeLoja);
			if (Objects.nonNull(email))
				query.setParameter("email", email);
			if (Objects.nonNull(cnpj))
				query.setParameter("cnpj", cnpj);

			return query.getResultList();

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		}
	}

}
