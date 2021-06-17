package br.com.carwash.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.carwash.entity.Cliente;
import br.com.carwash.utils.HibernateUtil;

public class ClienteDAO extends SuperDAO<Cliente> {
	
	public ClienteDAO() {
		super(Cliente.class);
	}

	@SuppressWarnings("unchecked")
	public List<Cliente> getClientes(Long idCliente, String nomeCliente, String cpfClienteS) throws Exception {
		EntityManager em = HibernateUtil.getEntityManager();
		try {
			String hql = " SELECT C FROM Cliente C WHERE C.excluido = :excluido ";
			if(Objects.nonNull(idCliente))
				hql = hql + " AND C.id = :idCliente " ; 
			if(Objects.nonNull(nomeCliente))
				hql = hql + " AND C.nome LIKE %:nomeCliente% " ;
			if(Objects.nonNull(cpfClienteS))
				hql = hql + " AND C.cpf LIKE %:cpfClienteS% " ;
			
			Query query = em.createQuery(hql,Cliente.class)
					.setParameter("excluido", (Boolean)false);
			
			if(Objects.nonNull(idCliente))
				if(idCliente != 0)
				query.setParameter("idCliente", idCliente);
			if(Objects.nonNull(cpfClienteS))
				query.setParameter("cpfClienteS", cpfClienteS);
			if(Objects.nonNull(nomeCliente))
				query.setParameter("nomeCliente", nomeCliente);
				
			return query.getResultList();
		}catch(NoResultException e) {
			return new ArrayList<Cliente>();
		}
		catch(Exception e){
			System.out.println(e.getMessage());
			throw new Exception(e);
		}finally {
			em.close();
		}
	}

	public Cliente findFromEmail(String email,String senha) {
		EntityManager em = HibernateUtil.getEntityManager();
		try {
			String hql = " SELECT c FROM Cliente c WHERE c.email = :email AND c.senha = :senha ";
			return em.createQuery(hql,Cliente.class)
					.setParameter("email",email)
					.setParameter("senha",senha)
					.getSingleResult();
		}catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

}
