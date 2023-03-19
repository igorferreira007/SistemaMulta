package br.com.igor.sistemamultas.form;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import br.com.igor.sistemamultas.dao.CondutorDao;
import br.com.igor.sistemamultas.dao.DaoFactory;
import br.com.igor.sistemamultas.dao.VeiculoDao;
import br.com.igor.sistemamultas.db.DbException;
import br.com.igor.sistemamultas.entities.Condutor;
import br.com.igor.sistemamultas.entities.Veiculo;
import jakarta.persistence.RollbackException;

public class VeiculoForm {
	
	Scanner sc = new Scanner(System.in);
	
	VeiculoDao veiculoDao = DaoFactory.criarVeiculoDao();
	CondutorDao condutorDao = DaoFactory.criarCondutorDao();

	public void cadastrar() {
		try {
			System.out.print("Placa: ");
			String placa = sc.nextLine();
			
			verificarDisponibilidade(placa);
			
			System.out.print("Ano: ");
			int ano = sc.nextInt();
			sc.nextLine();
			System.out.print("Modelo: ");
			String modelo = sc.nextLine();
			System.out.print("Marca: ");
			String marca = sc.nextLine();
			
			Condutor condutor = pesquisarCondutor();
			
			Veiculo veiculo = new Veiculo(null, placa, ano, modelo, marca, condutor);
			veiculoDao.cadastrar(veiculo);
			
			System.out.println("Veículo cadastrado com sucesso!");
		} catch (DbException e) {
			System.out.println("Erro ao cadastrar veículo: " + e.getMessage());
		} catch (InputMismatchException e) {
			System.out.println("Digite apenas números!");
		}
	}

	public void pesquisar() {
		try {
			System.out.println("Pesquisar por:");
			System.out.println("1. ID");
			System.out.println("2. Placa");
			int opcao = sc.nextInt();
			sc.nextLine();
			
			switch (opcao) {
			case 1:
				System.out.println("========================");
				System.out.print("ID: ");
				long id = sc.nextLong();
				sc.nextLine();
				System.out.println();
				Veiculo veiculo = veiculoDao.pesquisar(id);
				System.out.println(veiculo);
				break;
			case 2:
				System.out.println("========================");
				System.out.print("Placa: ");
				String placa = sc.nextLine();
				System.out.println();
				List<Veiculo> veiculos = veiculoDao.pesquisarPorPlaca(placa);
				veiculos.forEach(System.out::println);
				break;
			}
		} catch (DbException e) {
			System.out.println("Erro ao pesquisar Veículo: " + e.getMessage());
		} catch (InputMismatchException e) {
			System.out.println("Digite apenas números!");
		}
	}

	public void atualizar() {
		try {
			Veiculo veiculo = pesquisarPorPlaca();
			
			System.out.println(veiculo);
			System.out.println();
			
			System.out.print("Ano: ");
			veiculo.setAno(sc.nextInt());
			sc.nextLine();
			System.out.print("Modelo: ");
			veiculo.setModelo(sc.nextLine());
			System.out.print("Marca: ");
			veiculo.setMarca(sc.nextLine());
			
			veiculoDao.atualizar(veiculo);
			
			System.out.println("Veículo atualizado com sucesso!");
		} catch (DbException e) {
			System.out.println("Erro ao pesquisar veículo: " + e.getMessage());
		} catch (InputMismatchException e) {
			System.out.println("Digite apenas números!");
		}
	}

	public void excluir() {
		try {
			Veiculo veiculo = pesquisarPorPlaca();
						
			System.out.println(veiculo);
			System.out.print("Deseja confirmar a exclusão? (S/N): ");
			char confirmacao = sc.next().charAt(0);
			
			if (Character.toLowerCase(confirmacao) == 's') {
				veiculoDao.excluir(veiculo);
				System.out.println("Condutor excluído com sucesso!");
			} else if (Character.toLowerCase(confirmacao) == 'n') {
				System.out.println("Exclusão cancelada!");
			} else {
				System.out.println("Digite apenas uma das opções!");
			}
		} catch (DbException e) {
			System.out.println("Erro ao pesquisar veículo: " + e.getMessage());
		} catch (InputMismatchException e) {
			System.out.println("Digite apenas números!");
		} catch (RollbackException e) {
			System.out.println("Esse veículo não pode ser excluído pois existem multas vinculados a ele.\n"
					+ "Para executar a exclusão remova as multas associados ao veículo.");
		}
	}


	public void pesquisarTodos() {
		List<Veiculo> veiculos = veiculoDao.pesquisarTodos();
		veiculos.forEach(System.out::println);
	}

	private void verificarDisponibilidade(String placa) {
		try {
			veiculoDao.pesquisarPorPlaca(placa);
		} catch (Exception e) {
			return;
		}
		throw new DbException("Veículo já cadastrado!");
	}
	
	private Condutor pesquisarCondutor() throws RuntimeException {
		System.out.println("========================");
		System.out.print("CNH do condutor: ");
		int numCnh = sc.nextInt();
		sc.nextLine();
		List<Condutor> condutores = condutorDao.pesquisarPorCnh(numCnh);
		Condutor condutor = condutores.get(0);
		return condutor;
	}

	private Veiculo pesquisarPorPlaca() throws RuntimeException {
		System.out.println("========================");
		System.out.print("Pesquisar por placa: ");
		String placa = sc.nextLine();
		
		List<Veiculo> veiculos = veiculoDao.pesquisarPorPlaca(placa);
		return veiculos.get(0);
	}
}
