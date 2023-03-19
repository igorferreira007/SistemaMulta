package br.com.igor.sistemamultas.entities;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Veiculo implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String placa;
	private Integer ano;
	private String modelo;
	private String marca;
	
	@ManyToOne
	@JoinColumn(name = "condutor_id", referencedColumnName = "id")
	private Condutor condutor;
	
	@OneToMany(mappedBy = "veiculo")
	private List<Multa> multas = new ArrayList<Multa>();
	
	public Veiculo() {
	}

	public Veiculo(Long id, String placa, Integer ano, String modelo, String marca, Condutor condutor) {
		this.id = id;
		this.placa = placa;
		this.ano = ano;
		this.modelo = modelo;
		this.marca = marca;
		this.condutor = condutor;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public Integer getAno() {
		return ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public Condutor getCondutor() {
		return condutor;
	}

	public void setCondutor(Condutor condutor) {
		this.condutor = condutor;
	}

	public List<Multa> getMultas() {
		return multas;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Veiculo other = (Veiculo) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		DecimalFormat df = new DecimalFormat("0.00");
		String todasMultas = "";
		for (Multa multa : multas) {
			todasMultas += "\nId: " + multa.getId() + "(Valor: R$ " + df.format(multa.getValor()) + ")";
		}
		String dados = "Veículo" + "\nId: " + id + "\nPlaca: " + placa + "\nAno: " + ano + "\nModelo: " + modelo + "\nMarca: " + marca 
				+ "\nCondutor Id: " + condutor.getId() + "(CNH: " + condutor.getNumCnh() + ")" + "\nMultas" + todasMultas + "\n";
		return dados;
	}
}
