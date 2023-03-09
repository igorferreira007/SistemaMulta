package br.com.igor.sistemamultas.dao.impl;

import java.util.List;

import br.com.igor.sistemamultas.dao.VeiculoDao;
import br.com.igor.sistemamultas.entities.Veiculo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;

public class VeiculoDaoImpl implements VeiculoDao {

	private EntityManager em;
	
	public VeiculoDaoImpl() {
		this.em = Persistence.createEntityManagerFactory("sistema_multas").createEntityManager();
	}
	
	@Override
	public void cadastrar(Veiculo veiculo) {
		em.getTransaction().begin();
		em.persist(veiculo);
		em.getTransaction().commit();
	}
	
	@Override
	public Veiculo pesquisar(Long id) {
		return em.find(Veiculo.class, id);
	}
	
	@Override
	public void atualizar(Veiculo veiculo) {
		em.getTransaction().begin();
		em.merge(veiculo);
		em.getTransaction().commit();		
	}
	
	@Override
	public void excluir(Veiculo veiculo) {
		em.getTransaction().begin();
		em.remove(veiculo);
		em.getTransaction().commit();
	}	

	@Override
	public List<Veiculo> pesquisarTodos() {
		return em.createQuery("SELECT v FROM Veiculo v", Veiculo.class).getResultList();
	}
}
