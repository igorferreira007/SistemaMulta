package br.com.igor.sistemamultas.form;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import br.com.igor.sistemamultas.dao.CondutorDao;
import br.com.igor.sistemamultas.dao.DaoFactory;
import br.com.igor.sistemamultas.db.DbException;
import br.com.igor.sistemamultas.entities.Condutor;
import jakarta.persistence.RollbackException;

public class CondutorForm {
	
	Scanner sc = new Scanner(System.in);
	
	CondutorDao condutorDao = DaoFactory.criarCondutorDao();
	
	DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	public void cadastrar() {
		try {
			System.out.print("CNH: ");
			int numCnh = sc.nextInt();
			sc.nextLine();
			
			verificarDisponibilidade(numCnh);
			
			System.out.print("Nome: ");
			String nome = sc.nextLine();
			System.out.print("Data de emissão(DD/MM/AAAA): ");
			LocalDate dataEmissao = LocalDate.parse(sc.nextLine(), fmt);
			System.out.print("Pontuação: ");
			int pontuacao = sc.nextInt();
			sc.nextLine();
			
			Condutor condutor = new Condutor(null, numCnh, nome, dataEmissao, pontuacao);
			condutorDao.cadastrar(condutor);
		} catch (DbException e) {
			System.out.println("Erro ao cadastrar condutor: " + e.getMessage());
		} catch (InputMismatchException e) {
			System.out.println("Digite apenas números!");
		}
	}
	
	public void pesquisar() {
		try {
			System.out.println("Pesquisar por:");
			System.out.println("1. ID");
			System.out.println("2. CNH");
			int opcao = sc.nextInt();
			sc.nextLine();
			
			switch (opcao) {
			case 1:
				System.out.println("========================");
				System.out.print("ID: ");
				long id = sc.nextLong();
				sc.nextLine();
				System.out.println();
				Condutor condutor = condutorDao.pesquisar(id);
				System.out.println(condutor);
				break;
			case 2:
				System.out.println("========================");
				System.out.print("CNH: ");
				int numCnh = sc.nextInt();
				sc.nextLine();
				System.out.println();
				List<Condutor> condutores = condutorDao.pesquisarPorCnh(numCnh);
				condutores.forEach(System.out::println);
				break;
			}
		} catch (DbException e) {
			System.out.println("Erro ao pesquisar condutor: " + e.getMessage());
		} catch (InputMismatchException e) {
			System.out.println("Digite apenas números!");
		}
	}
	
	public void atualizar() {
		try {
			System.out.println("========================");
			System.out.print("Pesquisar por CNH: ");
			int numCnh = sc.nextInt();
			sc.nextLine();
			
			List<Condutor> condutores = condutorDao.pesquisarPorCnh(numCnh);
			Condutor condutor = condutores.get(0);
			
			System.out.println(condutor);
			System.out.println();
			System.out.print("Nome: ");
			condutor.setNome(sc.nextLine());
			System.out.print("Data de emissão(DD/MM/AAAA): ");
			condutor.setDataEmissao(LocalDate.parse(sc.nextLine(), fmt));
			System.out.print("Pontuação: ");
			condutor.setPontucao(sc.nextInt());
			sc.nextLine();
			condutorDao.atualizar(condutor);
			
		} catch (DbException e) {
			System.out.println("Erro ao pesquisar condutor: " + e.getMessage());
		} catch (InputMismatchException e) {
			System.out.println("Digite apenas números!");
		}
	}
	
	public void excluir() {
		try {
			System.out.println("========================");
			System.out.print("Pesquisar por CNH: ");
			int numCnh = sc.nextInt();
			sc.nextLine();
			
			List<Condutor> condutores = condutorDao.pesquisarPorCnh(numCnh);
			Condutor condutor = condutores.get(0);
			
			System.out.println(condutor);
			System.out.print("Deseja confirmar a exclusão? (S/N): ");
			char confirmacao = sc.next().charAt(0);
			sc.nextLine();
			
			if (Character.toLowerCase(confirmacao) == 's') {
				condutorDao.excluir(condutor);
				System.out.println("Condutor excluído com sucesso!");
			} else if (Character.toLowerCase(confirmacao) == 'n') {
				System.out.println("Exclusão cancelada!");
			} else {
				System.out.println("Digite apenas uma das opções!");
			}
			System.out.println("========================");
		} catch (DbException e) {
			System.out.println("Erro ao pesquisar condutor: " + e.getMessage());
		} catch (InputMismatchException e) {
			System.out.println("Digite apenas números!");
		} catch (RollbackException e) {
			System.out.println("Esse condutor não pode ser excluído pois existem veículos vinculados a ele.\n"
					+ "Para executar a exclusão remova os veículos associados ao condutor.");
		}
	}
	
	public void pesquisarTodos() {
		List<Condutor> condutores = condutorDao.pesquisarTodos();
		
		condutores.forEach(System.out::println);
	}

	private void verificarDisponibilidade(int numCnh) {
		try {
			condutorDao.pesquisarPorCnh(numCnh);
		} catch (DbException e) {
			return;
		}
		throw new DbException("Condutor já cadastrado!");
	}
}
