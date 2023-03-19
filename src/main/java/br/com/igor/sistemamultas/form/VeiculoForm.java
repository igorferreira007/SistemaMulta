package br.com.igor.sistemamultas.form;

import java.util.Scanner;

public class VeiculoForm {
	
	Scanner sc = new Scanner(System.in);

	public void cadastrar() {
		
	}

	public void pesquisar() {
		try {
			System.out.println("Pesquisar por:");
			System.out.println("1. ID");
			System.out.println("2. Placa");
			int opcao = sc.nextInt();
			sc.nextLine();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void atualizar() {
		// TODO Auto-generated method stub
		
	}

	public void excluir() {
		// TODO Auto-generated method stub
		
	}

	public void pesquisarTodos() {
		// TODO Auto-generated method stub
		
	}

	
}
