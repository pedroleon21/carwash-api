package br.com.carwash.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.validation.Valid;

import br.com.carwash.utils.HibernateUtil;

public class SuperDAO<T> {

	protected Class<T> persistedClass;


	public SuperDAO() {
	}

	public SuperDAO(Class<T> persistedClass) {
		super();
		this.persistedClass = persistedClass;
	}

	public T save(@Valid T entity) throws Exception {
		EntityManager em = HibernateUtil.getEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(entity);
			em.getTransaction().commit();
			return entity;
		} catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		} finally {
			em.close();
		}
	}

	public T update(@Valid T newEntity) throws Exception {
		EntityManager em = HibernateUtil.getEntityManager();
		T entity = null;
		try {
			em.getTransaction().begin();
			entity = em.merge(newEntity);
			em.getTransaction().commit();
			return entity;
		} catch (Exception e) {
			em.getTransaction().rollback();
			throw new Exception(e);
		} finally {
			em.close();
		}
	}

	public void delete(long id) throws Exception {
		EntityManager em = HibernateUtil.getEntityManager();
		try {
			em.getTransaction().begin();
			T entity = em.find(persistedClass, id);
			T mergedEntity = em.merge(entity);
			em.remove(mergedEntity);
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		} finally {
			em.close();
		}

	}

	public T find(long id) throws Exception {
		EntityManager em = HibernateUtil.getEntityManager();
		try {
			T obj = null;
			obj = em.find(persistedClass, id);
			return obj;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public T getReff(long id) throws Exception {
		EntityManager em = HibernateUtil.getEntityManager();
		try {
			T obj = null;
			obj = em.getReference(persistedClass, id);
			return obj;
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			em.close();
		}
	}

	@SuppressWarnings({ "unchecked", "hiding" })
	public <T> List<T> getAll() throws Exception {
		EntityManager em = HibernateUtil.getEntityManager();
		try {
			Query query = em.createQuery("FROM " + persistedClass.getName(), persistedClass);
			return query.getResultList();
		} catch (Exception e) {
			em.getTransaction().rollback();
			throw new Exception(e);
		} finally {
			em.close();
		}
	}

	@SuppressWarnings({ "unchecked", "hiding" })
	public <T> List<T> getAllPaginated(Integer initialPage, Integer maxValue) throws Exception {
		EntityManager em = HibernateUtil.getEntityManager();
		try {
			List<T> list = new ArrayList<T>();

			StringBuilder sql = new StringBuilder("FROM ");
			sql.append(persistedClass.getName());
			Query query = em.createQuery(sql.toString());
			query.setFirstResult(initialPage);
			query.setMaxResults(maxValue);
			list = query.getResultList();
			return list;
		} catch (Exception e) {
			em.getTransaction().rollback();
			throw new Exception(e);
		} finally {
			em.close();
		}
	}
}
