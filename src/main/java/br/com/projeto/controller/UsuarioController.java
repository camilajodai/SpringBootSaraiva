package br.com.projeto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.projeto.domain.Usuario;
import br.com.projeto.repository.UsuarioRepository;

@RestController
public class UsuarioController {
	
	@Autowired
	UsuarioRepository ur;
	
	@PostMapping("/cadastrar")
	public String cadastrar(@RequestBody Usuario us) {
		ur.save(us);
		return "Usuário Cadastrado";
	}
	
	@GetMapping("/listar")
	public List<Usuario> listar() {
		return ur.findAll();
	}
}