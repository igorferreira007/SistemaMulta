package br.com.igor.sistemamultas.dao;

import java.util.List;

import br.com.igor.sistemamultas.entities.Condutor;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;

public class CondutorDao {

	private EntityManager em;
	
	public CondutorDao() {
		this.em = Persistence.createEntityManagerFactory("sistema_multas").createEntityManager();
	}
	
	public void cadastrar(Condutor condutor) {
		em.getTransaction().begin();
		em.persist(condutor);
		em.getTransaction().commit();
	}
	
	public Condutor pesquisar(Long id) {
		return em.find(Condutor.class, id);
	}
	
	public void atualizar(Condutor condutor) {
		em.getTransaction().begin();
		em.merge(condutor);
		em.getTransaction().commit();
	}
	
	public void excluir(Condutor condutor) {
		em.getTransaction().begin();
		em.remove(condutor);
		em.getTransaction().commit();
	}
	
	public List<Condutor> pesquisarTodos() {
		return em.createQuery("SELECT c FROM Condutor c", Condutor.class).getResultList();
	}
	
	public void closeDao() {
		em.close();
	}
}
