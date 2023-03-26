package br.com.igor.sistemamultas.form;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import br.com.igor.sistemamultas.dao.CondutorDao;
import br.com.igor.sistemamultas.dao.DaoFactory;
import br.com.igor.sistemamultas.dao.MultaDao;
import br.com.igor.sistemamultas.dao.VeiculoDao;
import br.com.igor.sistemamultas.db.DbException;
import br.com.igor.sistemamultas.entities.Condutor;
import br.com.igor.sistemamultas.entities.Multa;
import br.com.igor.sistemamultas.entities.Veiculo;

public class MultaForm {
	
	Scanner sc = new Scanner(System.in);
	
	MultaDao multaDao = DaoFactory.criarMultaDao();
	CondutorDao condutorDao = DaoFactory.criarCondutorDao();

	public void cadastrar() {
		try {
			System.out.print("Valor: ");
			double valor = sc.nextDouble();
			System.out.print("Pontuação: ");
			int pontuacao = sc.nextInt();
			sc.nextLine();
			System.out.print("\nPlaca do veículo: ");
			String placa = sc.nextLine();
			
			Veiculo veiculo = pesquisarVeiculo(placa);
			
			Multa multa = new Multa(null, valor, pontuacao, veiculo);
			verificarNegativos(multa);
			multaDao.cadastrar(multa);
			
			Condutor condutor = veiculo.getCondutor();
			condutor.setPontucao(condutor.getPontucao() + pontuacao);
			condutorDao.atualizar(condutor);
			
			System.out.println("Multa cadastrada com sucesso!");
		} catch (DbException e) {
			System.out.println("Erro ao cadastrar multa: " + e.getMessage());
		} catch (InputMismatchException e) {
			sc.nextLine();
			System.out.println("Digite apenas números!");
		}
	}

	public Multa pesquisar() {
		try {
			System.out.print("Pesquisar por Id: ");
			long id = sc.nextLong();
			sc.nextLine();
			
			Multa multa = multaDao.pesquisar(id);
			
			System.out.println(multa);
			return multa;
		} catch (DbException e) {
			System.out.println("Erro ao pesquisar multa: " + e.getMessage());
		} catch (InputMismatchException e) {
			sc.nextLine();
			System.out.println("Digite apenas números!");
		}
		return null;
	}

	public void atualizar() {
		try {
			Multa multa = pesquisar();
			
			if (multa == null) {
				return;
			}
			
			int pontuacaoAntiga = multa.getPontuacao();
			
			System.out.print("Valor: ");
			multa.setValor(sc.nextDouble());
			System.out.print("Pontuação: ");
			multa.setPontuacao(sc.nextInt());
			sc.nextLine();
			System.out.print("\nPlaca do veículo: ");
			String placa = sc.nextLine();
			
			Veiculo veiculo = pesquisarVeiculo(placa);
			
			multa.setVeiculo(veiculo);	
			verificarNegativos(multa);
			multaDao.atualizar(multa);
			System.out.println("Multa atualizada com sucesso!");
			
			Condutor condutor = veiculo.getCondutor();
			condutor.setPontucao((condutor.getPontucao() - pontuacaoAntiga) + multa.getPontuacao());
			condutorDao.atualizar(condutor);			
		} catch (DbException e) {
			System.out.println("Erro ao atualizar multa: " + e.getMessage());
		} catch (InputMismatchException e) {
			sc.nextLine();
			System.out.println("Digite apenas números!");
		}
	}

	public void excluir() {
		Multa multa = pesquisar();
		
		if (multa == null) {
			return;
		}
		
		int pontuacaoMulta = multa.getPontuacao();
		
		System.out.print("Deseja confirmar a exclusão? (S/N): ");
		char confirmacao = sc.next().charAt(0);
		
		if (Character.toLowerCase(confirmacao) == 's') {
			multaDao.excluir(multa);
			System.out.println("Multa excluída com sucesso!");
			
			Condutor condutor = multa.getVeiculo().getCondutor();
			condutor.setPontucao(condutor.getPontucao() - pontuacaoMulta);
			condutorDao.atualizar(condutor);
		} else if (Character.toLowerCase(confirmacao) == 'n') {
			System.out.println("Exclusão cancelada!");
		} else {
			System.out.println("Digite apenas uma das opções!");
		}		
	}

	public void pesquisarTodos() {
		List<Multa> multas = multaDao.pesquisarTodos();
		multas.forEach(System.out::println);
	}

	private Veiculo pesquisarVeiculo(String placa) throws RuntimeException {
		VeiculoDao veiculoDao = DaoFactory.criarVeiculoDao();
		return veiculoDao.pesquisarPorPlaca(placa).get(0);
	}	

	private void verificarNegativos(Multa multa) {
		if (multa.getValor() < 0 || multa.getPontuacao() < 0) {
			throw new DbException("Valor negativo não aceito!");
		}
	}
}
