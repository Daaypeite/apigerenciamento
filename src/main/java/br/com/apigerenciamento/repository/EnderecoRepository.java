package br.com.apigerenciamento.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.apigerenciamento.entity.Endereco;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long>{

}
