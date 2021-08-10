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

import com.farm.drugstore.model.Categoria;
import com.farm.drugstore.repository.CategoriaRepository;

@RestController
@RequestMapping("/categoria")
@CrossOrigin("*")
public class CategoriaController {
	
	@Autowired
	private CategoriaRepository repository;
	
	@GetMapping
	public ResponseEntity<List<Categoria>> findAll() {
		return ResponseEntity.ok(repository.findAll());
	}
	
	@GetMapping("/{id_categoria}")
	public ResponseEntity<Categoria> findById(@PathVariable Long id_categoria){
		Optional<Categoria> obj =  repository.findById(id_categoria);
		
		if (obj.isPresent())
			return ResponseEntity.status(HttpStatus.OK).body(obj.get());
		else
			return ResponseEntity.status(200).body(null);
	}
	
	@GetMapping("/descricao/{descricaoCategoria}")
	public ResponseEntity<List<Categoria>> findAllByDescricao (@PathVariable String descricaoCategoria) {
		List<Categoria> obj = repository.findAllByDescricaoContainingIgnoreCase(descricaoCategoria);
		
		if(obj.size() < 0)
			return ResponseEntity.status(200).body(null);
		
		return ResponseEntity.status(200).body(obj);
	}
	
	@PostMapping("/criar")
	public ResponseEntity<Categoria> saveCategoria(@Valid @RequestBody Categoria categoria){	
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(categoria));
	}
	
	@PutMapping("/atualizar")
	public ResponseEntity<Categoria> updateCategoria(@Valid @RequestBody Categoria categoria) {
		return ResponseEntity.status(HttpStatus.OK).body(repository.save(categoria));
	}
	
	@DeleteMapping("/deletar/{id_categoria}")
	public ResponseEntity<String> deleteCategoria(@PathVariable Long id_categoria) {
		
		Optional<Categoria> obj = repository.findById(id_categoria);
		
		if (obj.isPresent()) {
			repository.deleteById(id_categoria);
			return ResponseEntity.status(HttpStatus.OK).body("Categoria Deletada");
		}	
		return ResponseEntity.status(HttpStatus.OK).body("Cetegoria não encontrada");
	}
}
