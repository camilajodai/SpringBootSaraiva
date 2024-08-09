package br.com.projeto.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.projeto.domain.Usuario;
import br.com.projeto.repository.UsuarioRepository;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
	
	@Autowired
	UsuarioRepository ur;
	
	@PostMapping("/cadastrar")
	public ResponseEntity<?> cadastrar(@RequestBody Usuario us) {
		ur.save(us);
		if(us == null) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Não foi possível cadastrar");
		}
		return ResponseEntity.status(HttpStatus.CREATED).body("Usuário Cadastrado");
	}
	
	@GetMapping("/listar")
	public List<Usuario> listar() {
		return ur.findAll();
	}
	
	@GetMapping("/consultar/{id}")
	public ResponseEntity<?> consultar(@PathVariable Integer id) {
		Optional<Usuario> cid = ur.findById(id);
		if(!cid.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado");
		}
		return ResponseEntity.status(HttpStatus.OK).body(cid);
	}
	
	@GetMapping("/consultarusuario/{nomeusuario}")
	public ResponseEntity<?> consultarusuario(@PathVariable String nomeusuario){
		Optional<Usuario> cnu = ur.findByNomeusuario(nomeusuario);
		if(!cnu.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado");
		}
		return ResponseEntity.status(HttpStatus.OK).body(cnu);
	}
	
//	ATUALIZAÇÃO
	@PatchMapping("/alterarfoto/{id}")
	public ResponseEntity<?> alterarfoto(@PathVariable Integer id, @RequestBody Usuario us) {
		Optional<Usuario> user = ur.findById(id);
		if (!user.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("[{msg: 'Não foi possível encontrar o usuário'}]");
		}
		us.setIdusuario(id);
		us.setNomeusuario(user.get().getNomeusuario());
		us.setSenha(user.get().getSenha());
		us.setDataalteracao(user.get().getDataalteracao());
		ur.save(us);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body("[{msg: 'Foto alterada'}]");
	} 
	
	
	@PatchMapping("/alterarsenha/{id}")
	public ResponseEntity<?> alterarsenha(@PathVariable Integer id, @RequestBody Usuario us) {
		Optional<Usuario> user = ur.findById(id);
		if(!user.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("[{msg: 'Não foi possível encontrar o usuário'}]");
		}
		us.setIdusuario(id);
		us.setDataalteracao(user.get().getDataalteracao());
		us.setNomeusuario(user.get().getNomeusuario());
		us.setFoto(user.get().getFoto());
		ur.save(us);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body("[{msg: 'Senha alterada'}]");
	}
	
	
//	deletar usuario
	@DeleteMapping("/apagarusuario/{id}")
	public ResponseEntity<?> apagarusuario(@PathVariable Integer id) {
		Optional<Usuario> user = ur.findById(id);
		if(!user.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("[{msg: 'Não foi possível encontrar o usuário'}]");
		}
		ur.deleteById(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body("[{msg: 'Usuário apagado'}]");
	}
	
//	@PostMapping("/login")
//	public String login(@RequestBody Usuario user) {
//		String msg = "";
//		Usuario u = ur.findByNomeusuario(user.getNomeusuario(), user.getSenha());
//		if(u==null) {
//			msg = "Usuário ou senha inválida";
//		} else {
//			msg = "Autenticado";
//		}
//		return msg;
//	}
	
	@PostMapping("/auth")
	public ResponseEntity<?> auth(@RequestBody Usuario user) {
		Usuario u = ur.findByNomeusuario(user.getNomeusuario(), user.getSenha());
		if(u==null) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Nome de usuário ou senha incorretos");
		}
		return ResponseEntity.status(HttpStatus.OK).body("[{idusuario:'"+u.getIdusuario()+"'"+"nomeusuario:'"+u.getNomeusuario()+"',"+"foto:'"+u.getFoto()+"}]");
	}
	
	
}