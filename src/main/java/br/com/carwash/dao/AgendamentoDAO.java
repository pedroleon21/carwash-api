package br.com.carwash.dao;

import java.util.List;
import java.util.Objects;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.carwash.entity.Agendamento;
import br.com.carwash.utils.HibernateUtil;

public class AgendamentoDAO extends SuperDAO<Agendamento> {

	public AgendamentoDAO() {
		super(Agendamento.class);
	}

	@SuppressWarnings("unchecked")
	public List<Agendamento> pegarListaDeAgendamentos(Long agendamento, Long cliente, Long inicio,
			Long fim) throws Exception {
		EntityManager em = HibernateUtil.getEntityManager();
		
		try {	
			String hql = " SELECT a FROM Agendamento a "
					+ " JOIN FETCH a.cliente c "
					+ " WHERE 1=1  ";
			if(Objects.nonNull(agendamento))
				hql = hql + " AND a.id = :id ";
			if(Objects.nonNull(cliente))
				hql = hql + " AND a.cliente.id = :id ";
			if(Objects.nonNull(fim) && Objects.isNull(inicio))
				hql = hql + " AND a.dataAgendamento < :fim ";
			if(Objects.nonNull(inicio) && Objects.isNull(fim))
				hql = hql + " AND a.dataAgendamento < :inicio ";
			if(Objects.nonNull(inicio) && Objects.nonNull(fim))
				hql = hql + " AND a.dataAgendamento BETWEEN :inico AND :fim ";
			
			Query query = em.createQuery(hql,Agendamento.class);
			
			if(Objects.nonNull(agendamento))
				query.setParameter("id", agendamento);
			if(Objects.nonNull(cliente))
				query.setParameter("cliente", cliente);
			if(Objects.nonNull(fim))
				query.setParameter("fim", fim);
			if(Objects.nonNull(inicio))
				query.setParameter("inicio",inicio);
			
			return query.getResultList();
		}catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public List<Agendamento> buscaAgendamentosPorCliente(long id) {
		EntityManager em = HibernateUtil.getEntityManager();
		try {
			String sql = " SELECT a FROM Agendamento a "
					+ " JOIN FETCH a.cliente c "
					+ " JOIN FETCH a.lavaJato lf "
					+ " WHERE c.id = :id AND c.excluido = :excluido ";

			return em.createQuery(sql,Agendamento.class)
					.setParameter("id",id)
					.setParameter("excluido",(Boolean)false)
					.getResultList();
		}catch(Exception e) {
			em.getTransaction().rollback();
			em.close();
			e.printStackTrace();
			throw e;
		}
	}
}
