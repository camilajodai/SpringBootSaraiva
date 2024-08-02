package br.com.projeto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.projeto.domain.Precos;

public interface PrecosRepository extends JpaRepository<Precos, Integer>{

}
