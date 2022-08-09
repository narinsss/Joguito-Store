package com.narin.Joguitostore.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.narin.Joguitostore.model.Categoria;
import com.narin.Joguitostore.repository.CategoriaRepository;

@RestController
@RequestMapping("/categoria")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CastegoriaController {
	@Autowired
	private CategoriaRepository repositoryC;
	
	@GetMapping
	public ResponseEntity<List<Categoria>> getAll(){
		return ResponseEntity.ok(repositoryC.findAll()) ;
	}
	@GetMapping("/{id}" )
	public ResponseEntity<Categoria> getById(@PathVariable Long id){
		return repositoryC.findById(id).map(resp -> ResponseEntity.ok(resp)).orElse(ResponseEntity.notFound().build());
	}
	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<Categoria>> getByNome(@PathVariable String nome){
		return ResponseEntity.ok(repositoryC.findAllByNomeContainingIgnoreCase(nome));
	}
	
	@PostMapping
	public ResponseEntity<Categoria> post(@RequestBody Categoria categoria){
		return ResponseEntity.status(HttpStatus.CREATED).body(repositoryC.save(categoria));
	}
	
	@PutMapping
	public ResponseEntity<Categoria> put(@RequestBody Categoria categoria){
		return ResponseEntity.status(HttpStatus.OK).body(repositoryC.save(categoria));
	}
	
	@DeleteMapping("/{id}")
	public void Rdelete(@PathVariable Long id){
		repositoryC.deleteById(id);
	}

	public CategoriaRepository getRepositoryC() {
		return repositoryC;
	}

	public void setRepositoryC(CategoriaRepository repositoryC) {
		this.repositoryC = repositoryC;
	}
}
