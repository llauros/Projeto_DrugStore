package com.farm.drugstore.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.farm.drugstore.model.Produto;
import com.farm.drugstore.repository.ProdutoRepository;

@RestController
@RequestMapping("/produto")
@CrossOrigin("*")
public class ProdutoController {
	
	@Autowired
	private ProdutoRepository repository;
	
	@GetMapping
	public ResponseEntity<List<Produto>> findAll() {
		return ResponseEntity.ok(repository.findAll());
	}
	
	@GetMapping("/{id_categoria}")
	public ResponseEntity<Produto> findById(@PathVariable Long id_categoria){
		Optional<Produto> obj =  repository.findById(id_categoria);
		
		if (obj.isPresent())
			return ResponseEntity.status(HttpStatus.OK).body(obj.get());
		else
			return ResponseEntity.status(200).body(null);
	}
	
	@GetMapping("/descricao/{descricaoProduto}")
	public ResponseEntity<List<Produto>> findAllByDescricao (@PathVariable String descricaoProduto) {
		List<Produto> obj = repository.findAllByNomeContainingIgnoreCase(descricaoProduto);
		
		if(obj.size() < 0)
			return ResponseEntity.status(200).body(null);
		
		return ResponseEntity.status(200).body(obj);
	}
	
	@PostMapping("/criar")
	public ResponseEntity<Produto> saveProduto(@Valid @RequestBody Produto categoria){	
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(categoria));
	}
	
	@PutMapping("/atualizar")
	public ResponseEntity<Produto> updateProduto(@Valid @RequestBody Produto categoria) {
		return ResponseEntity.status(HttpStatus.OK).body(repository.save(categoria));
	}
	
	@DeleteMapping("/deletar/{id_categoria}")
	public ResponseEntity<String> deleteProduto(@PathVariable Long id_categoria) {
		
		Optional<Produto> obj = repository.findById(id_categoria);
		
		if (obj.isPresent()) {
			repository.deleteById(id_categoria);
			return ResponseEntity.status(HttpStatus.OK).body("Produto Deletada");
		}	
		return ResponseEntity.status(HttpStatus.OK).body("Cetegoria não encontrada");
	}
}
