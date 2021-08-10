package com.farm.drugstore.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table( name = "tb_produto")
public class Produto {
	
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	private String nome;
	
	@Digits(integer = 5, fraction = 2)
	private Double preco;
	
	@ManyToOne
	private Categoria categoria;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}
}
