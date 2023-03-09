package br.com.igor.sistemamultas.dao;

import br.com.igor.sistemamultas.dao.impl.CondutorDaoImpl;
import br.com.igor.sistemamultas.dao.impl.MultaDaoImpl;
import br.com.igor.sistemamultas.dao.impl.VeiculoDaoImpl;

public class DaoFactory {

	public static CondutorDao criarCondutorDao() {
		return new CondutorDaoImpl();
	}
	
	public static VeiculoDao criarVeiculoDao() {
		return new VeiculoDaoImpl();
	}
	
	public static MultaDao criarMultaDao() {
		return new MultaDaoImpl();
	}
}
