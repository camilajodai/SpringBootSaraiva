package br.com.projeto.domain;

import java.sql.Date;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Precos {
 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idpreco;
	
	@Column(nullable = false)
	private Double precoatual;
	
	@Column(nullable = false)
	private Double precodesconto;
	
	@Column
	@CreationTimestamp
	private Date dataalteracao;

	public Precos() {
	}

	public Precos(Integer idpreco, Double precoatual, Double precodesconto, Date dataalteracao) {
		this.idpreco = idpreco;
		this.precoatual = precoatual;
		this.precodesconto = precodesconto;
		this.dataalteracao = dataalteracao;
	}

	public Integer getIdpreco() {
		return idpreco;
	}

	public void setIdpreco(Integer idpreco) {
		this.idpreco = idpreco;
	}

	public Double getPrecoatual() {
		return precoatual;
	}

	public void setPrecoatual(Double precoatual) {
		this.precoatual = precoatual;
	}

	public Double getPrecodesconto() {
		return precodesconto;
	}

	public void setPrecodesconto(Double precodesconto) {
		this.precodesconto = precodesconto;
	}

	public Date getDataalteracao() {
		return dataalteracao;
	}

	public void setDataalteracao(Date dataalteracao) {
		this.dataalteracao = dataalteracao;
	}
	
	
}
