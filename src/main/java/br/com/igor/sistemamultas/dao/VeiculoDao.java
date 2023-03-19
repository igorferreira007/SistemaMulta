package br.com.igor.sistemamultas.dao;

import java.util.List;

import br.com.igor.sistemamultas.entities.Veiculo;

public interface VeiculoDao {

	void cadastrar(Veiculo veiculo);
	Veiculo pesquisar(Long id);
	List<Veiculo> pesquisarPorPlaca(String placa);
	void atualizar(Veiculo veiculo);
	void excluir(Veiculo veiculo);
	List<Veiculo> pesquisarTodos();
}
