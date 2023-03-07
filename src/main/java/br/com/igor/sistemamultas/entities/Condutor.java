package br.com.igor.sistemamultas.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Condutor implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Integer numCnh;
	private String nome;
	private Instant dataEmissao;
	private Integer pontucao;
	
	@OneToMany(mappedBy = "condutor")
	private List<Veiculo> veiculos = new ArrayList<Veiculo>();
	
	public Condutor() {
	}

	public Condutor(Long id, Integer numCnh, String nome, Instant dataEmissao, Integer pontucao) {
		super();
		this.id = id;
		this.numCnh = numCnh;
		this.nome = nome;
		this.dataEmissao = dataEmissao;
		this.pontucao = pontucao;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getNumCnh() {
		return numCnh;
	}

	public void setNumCnh(Integer numCnh) {
		this.numCnh = numCnh;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Instant getDataEmissao() {
		return dataEmissao;
	}

	public void setDataEmissao(Instant dataEmissao) {
		this.dataEmissao = dataEmissao;
	}

	public Integer getPontucao() {
		return pontucao;
	}

	public void setPontucao(Integer pontucao) {
		this.pontucao = pontucao;
	}

	public List<Veiculo> getVeiculos() {
		return veiculos;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((numCnh == null) ? 0 : numCnh.hashCode());
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
		Condutor other = (Condutor) obj;
		if (numCnh == null) {
			if (other.numCnh != null)
				return false;
		} else if (!numCnh.equals(other.numCnh))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Condutor [id=" + id + ", numCnh=" + numCnh + ", nome=" + nome + ", dataEmissao=" + dataEmissao
				+ ", pontucao=" + pontucao + ", veiculos=" + veiculos + "]";
	}
}
