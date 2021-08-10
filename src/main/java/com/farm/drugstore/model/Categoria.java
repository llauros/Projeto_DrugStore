package com.farm.drugstore.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table( name = "tb_categoria")
public class Categoria {
	
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Size( min = 5 , max = 30, message = "Numero de caracteres invalido")
	private String descricao;
	
	@OneToMany( mappedBy = "categoria", cascade = CascadeType.ALL)
	private List<Produto> produto;
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
