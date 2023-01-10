package br.com.apigerenciamento.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.apigerenciamento.entity.Endereco;
import br.com.apigerenciamento.entity.Pessoa;
import br.com.apigerenciamento.exceptions.ResourceNotFoundException;
import br.com.apigerenciamento.repository.EnderecoRepository;
import br.com.apigerenciamento.repository.PessoaRepository;

@RestController
@RequestMapping("/api/enderecos")
public class EnderecoController {
	private final PessoaRepository pessoaRepository;
	private final EnderecoRepository enderecoRepository;

	public EnderecoController(PessoaRepository pessoaRepository, EnderecoRepository enderecoRepository) {
		this.pessoaRepository = pessoaRepository;
		this.enderecoRepository = enderecoRepository;
	}

	// metodo de criacao do novo endereco
	public Endereco criaEndereco(@RequestBody Endereco endereco) {
		return enderecoRepository.save(endereco);
	}

	// metodo para alterar o endereco
	@PutMapping("/{id}")
	public Endereco alteraEndereco(@PathVariable Long id, @RequestBody Endereco endereco) {
		Endereco velhoEndereco = enderecoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException());
		velhoEndereco.setLogadouro(endereco.getLogadouro());
		velhoEndereco.setCep(endereco.getCep());
		velhoEndereco.setNumero(endereco.getNumero());
		velhoEndereco.setCidade(endereco.getCidade());
		return enderecoRepository.save(velhoEndereco);
	}

	// Método para buscar um unico endereco
	@GetMapping("/{id}")
	public List<Endereco> getEnderecoByIdPessoa(@PathVariable Long id) {
		Pessoa pessoa = pessoaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException());
		return pessoa.getEnderecos();
	}

	// método para deletar um endereco
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletaEndereco(@PathVariable Long id) {
		Endereco endereco = enderecoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException());
		enderecoRepository.delete(endereco);
		return ResponseEntity.ok().build();
	}

	// método para chamar todos os enderecos
	@GetMapping
	public List<Endereco> chamaTodosOsEnderecos() {
		return enderecoRepository.findAll();
	}
}
