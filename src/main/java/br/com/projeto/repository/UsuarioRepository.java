package br.com.projeto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.projeto.domain.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{

}
