package br.com.igor.sistemamultas.dao.impl;

import java.util.List;

import br.com.igor.sistemamultas.dao.MultaDao;
import br.com.igor.sistemamultas.db.DbException;
import br.com.igor.sistemamultas.entities.Multa;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;

public class MultaDaoImpl implements MultaDao {
	
	private EntityManager em;
		
	public MultaDaoImpl() {
		this.em = Persistence.createEntityManagerFactory("sistema_multas").createEntityManager();
	}

	@Override
	public void cadastrar(Multa multa) {
		em.getTransaction().begin();
		em.persist(multa);
		em.getTransaction().commit();
	}

	@Override
	public Multa pesquisar(Long id) {
		Multa multa = em.find(Multa.class, id);
		if (multa == null) {
			throw new DbException("Multa não encontrada!");
		}
		return multa;
	}

	@Override
	public void atualizar(Multa multa) {
		em.getTransaction().begin();
		em.merge(multa);
		em.getTransaction().commit();
	}

	@Override
	public void excluir(Multa multa) {
		em.getTransaction().begin();
		em.remove(multa);
		em.getTransaction().commit();
	}

	@Override
	public List<Multa> pesquisarTodos() {
		return em.createQuery("SELECT m FROM Multa m", Multa.class).getResultList();
	}

}
