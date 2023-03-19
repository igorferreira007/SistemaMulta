package br.com.igor.sistemamultas.dao;

import java.util.List;

import br.com.igor.sistemamultas.entities.Condutor;

public interface CondutorDao {

	void cadastrar(Condutor condutor);
	Condutor pesquisar(long id);
	List<Condutor> pesquisarPorCnh(int numCnh);
	void atualizar(Condutor condutor);
	void excluir(Condutor condutor);
	List<Condutor> pesquisarTodos();	
}
