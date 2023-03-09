package br.com.igor.sistemamultas.dao.impl;

import java.util.List;

import br.com.igor.sistemamultas.dao.CondutorDao;
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
	public Condutor pesquisar(Long id) {
		return em.find(Condutor.class, id);
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
