package br.com.apigerenciamento.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.apigerenciamento.entity.Pessoa;
import br.com.apigerenciamento.exceptions.ResourceNotFoundException;
import br.com.apigerenciamento.repository.EnderecoRepository;
import br.com.apigerenciamento.repository.PessoaRepository;

@RestController
@RequestMapping("/api/pessoas")
public class PessoaController {

	private final PessoaRepository pessoaRepository;
	private final EnderecoRepository enderecoRepository;

	public PessoaController(PessoaRepository pessoaRepository, EnderecoRepository enderecoRepository) {
		this.pessoaRepository = pessoaRepository;
		this.enderecoRepository = enderecoRepository;

	}

	// metodo de criacao da nova pessoa
	@PostMapping
	public Pessoa criaPessoa(@RequestBody Pessoa pessoa) {
		return pessoaRepository.save(pessoa);
	}

	// metodo para alterar uma pessoa
	@PutMapping("/{id}")
	public Pessoa alteraPessoa(@PathVariable Long id, @RequestBody Pessoa pessoa) {
		Pessoa pessoaExistente = pessoaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException());
		pessoaExistente.setNome(pessoa.getNome());
		pessoaExistente.setDataNascimento(pessoa.getDataNascimento());
		return pessoaRepository.save(pessoaExistente);
	}

	// metodo para buscar uma unica pessoa
	@GetMapping("/{id}")
	public Pessoa buscaPessoa(@PathVariable Long id) {
		return pessoaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException());
	}

	// método para deletar uma usuario
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletaPessoa(@PathVariable Long id) {
		Pessoa pessoa = pessoaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException());
		pessoaRepository.delete(pessoa);
		return ResponseEntity.ok().build();
	}

	// método para chamar todas as pessoas
	@GetMapping
	public List<Pessoa> chamaTodasAsPessoas() {
		return pessoaRepository.findAll();
	}
}
