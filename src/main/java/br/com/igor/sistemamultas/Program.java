package br.com.igor.sistemamultas;

import java.time.Instant;
import java.util.List;

import br.com.igor.sistemamultas.dao.imp.CondutorDaoImp;
import br.com.igor.sistemamultas.dao.imp.VeiculoDaoImp;
import br.com.igor.sistemamultas.entities.Condutor;
import br.com.igor.sistemamultas.entities.Veiculo;

public class Program {

	public static void main(String[] args) {

		CondutorDaoImp condutorDao= new CondutorDaoImp();
		VeiculoDaoImp veiculoDao = new VeiculoDaoImp();
		
//		Condutor condutor = new Condutor(null, 123, "Igor", Instant.parse("2019-06-20T19:53:07Z"), 0);
//		condutorDao.cadastrar(condutor);
//		Veiculo veiculo = new Veiculo(null, "AAA1234", 2019, "Onix", "Chevrolet", condutor);
//		veiculoDao.cadastrar(veiculo);
		
//		Condutor condutor = new Condutor(null, 456, "Maria", Instant.parse("2017-06-20T19:53:07Z"), 0);
//		condutorDao.cadastrar(condutor);
		
//		Condutor condutorPequisado = condutorDao.pesquisar(2L);
//		System.out.println(condutorPequisado);
//		Veiculo veiculo = new Veiculo(null, "CCC1234", 2020, "Gol", "VW", condutorPequisado);
//		veiculoDao.cadastrar(veiculo);
		
//		Condutor condutor = new Condutor(null, 789, "Joao", Instant.parse("2015-06-20T19:53:07Z"), 0);
//		condutorDao.cadastrar(condutor);
//		Veiculo veiculo = new Veiculo(null, "DDD1234", 2017, "Cobalt", "Chevrolet", condutor);
//		veiculoDao.cadastrar(veiculo);
		
//		Condutor condutorPequisado = condutorDao.pesquisar(1L);
//		System.out.println(condutorPequisado);
		
//		Condutor condutor = new Condutor(null, 321, "Julio", Instant.parse("2010-06-20T19:53:07Z"), 0);
//		condutorDao.cadastrar(condutor);
		
//		Condutor condutor = condutorDao.pesquisar(2L);
//		System.out.println(condutor);
//		condutor.setNome("Mariana");
//		condutorDao.atualizar(condutor);
		
//		Veiculo veiculo = veiculoDao.pesquisar(3L);
//		System.out.println(veiculo);
//		veiculoDao.excluir(veiculo);
		
//		List<Condutor> condutores = condutorDao.pesquisarTodos();
//		condutores.forEach(System.out::println);
		
//		Condutor condutor = new Condutor(null, 456, "Joao", Instant.parse("2015-06-20T19:53:07Z"), 0);
//		condutorDao.cadastrar(condutor);
		
		System.out.println("Pronto!");
	}

}
