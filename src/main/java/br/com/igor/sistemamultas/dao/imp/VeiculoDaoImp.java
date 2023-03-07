package br.com.igor.sistemamultas.dao.imp;

import br.com.igor.sistemamultas.entities.Veiculo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;

public class VeiculoDaoImp {

	private EntityManager em;
	
	public VeiculoDaoImp() {
		this.em = Persistence.createEntityManagerFactory("sistema_multas").createEntityManager();
	}
	
	public void cadastrar(Veiculo veiculo) {
		em.getTransaction().begin();
		em.persist(veiculo);
		em.getTransaction().commit();
	}
	
	public Veiculo pesquisar(Long id) {
		return em.find(Veiculo.class, id);
	}
	
	public void excluir(Veiculo veiculo) {
		em.getTransaction().begin();
		em.remove(veiculo);
		em.getTransaction().commit();
	}
}
