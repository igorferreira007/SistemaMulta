package br.com.igor.sistemamultas.dao.impl;

import java.util.List;

import br.com.igor.sistemamultas.dao.CondutorDao;
import br.com.igor.sistemamultas.db.DbException;
import br.com.igor.sistemamultas.entities.Condutor;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;

public class CondutorDaoImpl implements CondutorDao {

	private EntityManager em;
	
	public CondutorDaoImpl() {
		this.em = Persistence.createEntityManagerFactory("sistema_multas").createEntityManager();
	}
	
	@Override
	public void cadastrar(Condutor condutor) {
		em.getTransaction().begin();
		em.persist(condutor);
		em.getTransaction().commit();
	}
	
	@Override
	public Condutor pesquisar(long id) {
		Condutor condutor = em.find(Condutor.class, id);
		if (condutor == null) {
			throw new DbException("Condutor não encontrado!");
		}
		return condutor;
	}
	
	@Override
	public List<Condutor> pesquisarPorCnh(int numCnh) {
		List<Condutor> condutores = em.createQuery("SELECT c FROM Condutor c WHERE numcnh = :numCnh", Condutor.class).setParameter("numCnh", numCnh).getResultList();
		if (condutores.isEmpty()) {
			throw new DbException("Condutor não encontrado!");
		}
		return condutores;
	}
	
	@Override
	public void atualizar(Condutor condutor) {
		em.getTransaction().begin();
		em.merge(condutor);
		em.getTransaction().commit();
	}
	
	@Override
	public void excluir(Condutor condutor) {
		em.getTransaction().begin();
		em.remove(condutor);
		em.getTransaction().commit();
	}
	
	@Override
	public List<Condutor> pesquisarTodos() {
		return em.createQuery("SELECT c FROM Condutor c", Condutor.class).getResultList();
	}
	
	public void closeDao() {
		em.close();
	}
}
