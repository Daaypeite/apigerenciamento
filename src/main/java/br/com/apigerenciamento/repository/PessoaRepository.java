package br.com.apigerenciamento.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.apigerenciamento.entity.Pessoa;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long>{

}
