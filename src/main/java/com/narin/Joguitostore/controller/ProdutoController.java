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
import org.springframework.web.bind.annotation.RestController;

import com.narin.Joguitostore.model.Produto;
import com.narin.Joguitostore.repository.ProdutoRepository;

@RestController
@RequestMapping("/produto")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProdutoController {
	@Autowired
	private ProdutoRepository repositoryP;
	
	@GetMapping
	public ResponseEntity<List<Produto>> getAll(){
		return ResponseEntity.ok(repositoryP.findAll());
	}
	@GetMapping("/{id}")
	public ResponseEntity<Produto> getById(@PathVariable Long id){
		return repositoryP.findById(id).map(resp -> ResponseEntity.ok(resp)).orElse(ResponseEntity.notFound().build());
	}
	@GetMapping("/nomeP/{nomeP}")
	public ResponseEntity<List<Produto>> getByNomeP(@PathVariable String nomeP){
		return ResponseEntity.ok(repositoryP.findAllByNomePContainingIgnoreCase(nomeP));
	}
	
	@PostMapping
	public ResponseEntity<Produto> post(@RequestBody Produto produto){
		return ResponseEntity.status(HttpStatus.CREATED).body(repositoryP.save(produto));
	}
	
	@PutMapping
	public ResponseEntity<Produto> put(@RequestBody Produto produto){
		return ResponseEntity.status(HttpStatus.OK).body(repositoryP.save(produto));
	}
	
	@DeleteMapping
	public void delete(@PathVariable Long id) {
		repositoryP.deleteById(id);
	}
	
	public ProdutoRepository getRepositoryP() {
		return repositoryP;
	}
	public void setRepositoryP(ProdutoRepository repositoryP) {
		this.repositoryP = repositoryP;
	}
}
