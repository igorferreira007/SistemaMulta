package br.com.igor.sistemamultas;

import java.util.Locale;
import java.util.Scanner;

import br.com.igor.sistemamultas.form.CondutorForm;
import br.com.igor.sistemamultas.form.VeiculoForm;

public class Program {

	public static void main(String[] args) {
		
//		CondutorDao condutorDao = DaoFactory.criarCondutorDao();
//		VeiculoDao veiculoDao = DaoFactory.criarVeiculoDao();
//		Condutor condutor = condutorDao.pesquisar(4L);
//		Veiculo veiculo = new Veiculo(null, "JJJ1234", 2020, "M3", "BMW", condutor);
//		veiculoDao.cadastrar(veiculo);

		Locale.setDefault(new Locale("pt", "BR"));
		Scanner sc = new Scanner(System.in);
		
		int opcao = 0;
		
		do {
			menuPrincipal();
			opcao = sc.nextInt();
			sc.nextLine();
			
			switch (opcao) {
			case 1:
				condutor(sc);
				break;
			case 2:
				veiculo(sc);
				break;
			case 3:
				multa(sc);
				break;
			}
		} while (opcao != 0);
		sc.close();
	}

	private static void multa(Scanner sc) {
		menuSecundario("MULTA");
		int opcao = sc.nextInt();
	}

	private static void veiculo(Scanner sc) {
		VeiculoForm veiculoForm = new VeiculoForm();
		
		int opcao = 0;
		
		do {
			menuSecundario("VEÍCULO");
			opcao = sc.nextInt();
			
			switch (opcao) {
			case 1:
				veiculoForm.cadastrar();
				break;
			case 2:
				veiculoForm.pesquisar();
				break;
			case 3:
				veiculoForm.atualizar();
				break;
			case 4:
				veiculoForm.excluir();
				break;
			case 5:
				veiculoForm.pesquisarTodos();
				break;
			}
		} while (opcao != 0);
	}

	private static void condutor(Scanner sc) {
		CondutorForm condutorForm = new CondutorForm();
		
		int opcao = 0;
		
		do {
			menuSecundario("CONDUTOR");
			opcao = sc.nextInt();
			
			switch (opcao) {
			case 1:
				condutorForm.cadastrar();
				break;
			case 2:
				condutorForm.pesquisar();
				break;
			case 3:
				condutorForm.atualizar();
				break;
			case 4:
				condutorForm.excluir();
				break;
			case 5:
				condutorForm.pesquisarTodos();
				break;
			}
		} while (opcao != 0);
	}

	private static void menuSecundario(String identificador) {
		System.out.println("========================");
		System.out.println(identificador);
		System.out.println("1. Cadastrar");
		System.out.println("2. Pesquisar");
		System.out.println("3. Atualizar");
		System.out.println("4. Excluir");
		System.out.println("5. Listar todos");
		System.out.println("0. Sair");
		System.out.println("========================");
	}

	private static void menuPrincipal() {
		System.out.println("=====MENU PRINCIPAL=====");
		System.out.println("1. Condutor");
		System.out.println("2. Veículo");
		System.out.println("3. Multa");
		System.out.println("0. Sair");
		System.out.println("========================");
	}
}
