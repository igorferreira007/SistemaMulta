package br.com.igor.sistemamultas.dao;

import java.util.List;

import br.com.igor.sistemamultas.entities.Multa;

public interface MultaDao {

	void cadastrar(Multa multa);
	Multa pesquisar(Long id);
	void atualizar(Multa multa);
	void excluir(Multa multa);
	List<Multa> pesquisarTodos();
}
