package co.edu.poli.PolisongStock.RegistroUsuario.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.poli.PolisongStock.RegistroUsuario.modelo.Persona;
import co.edu.poli.PolisongStock.RegistroUsuario.repository.UsuarioRepository;
import co.edu.poli.PolisongStock.RegistroUsuario.service.UsuarioService;

@RestController
@RequestMapping("/api/usuarios")
@EnableMethodSecurity
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;
	
		

	@PreAuthorize("permitAll")
	@PostMapping ("/crearusuarios")
	public ResponseEntity<String> create(@RequestBody Persona persona){
		boolean r = usuarioService.getOrCreate(persona);
		if(r) {
			return ResponseEntity.ok("Usuario creado exitosamente"); // This triggers JPA to insert into DB
		}else {
			return ResponseEntity.badRequest().body("Usuario ya existe en el sistema");
		}
	
	}

	@PreAuthorize("hasRole('superusuario')")
	@GetMapping
	public ResponseEntity<List<Persona>> getAll(){
		return ResponseEntity.ok(usuarioService.getAllUsuario());
	}
	
	@PreAuthorize("hasRole('superusuario')")
	@GetMapping("/{id}")
	public ResponseEntity<Optional<Persona>> getById(Long id){
		return ResponseEntity.ok(usuarioService.getUsuarioById(id));
		
	}
	@PreAuthorize("hasRole('superusuario')")
	@PutMapping("/{id}")
	public ResponseEntity<String> update(Long id, String updateUsuario){
		return ResponseEntity.ok("usuario actualizado");
	}
	@PreAuthorize("hasRole('superusuario')")
	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete (Long id){
		if(usuarioService.deleteUsuario(id)) {
			return ResponseEntity.ok("usuario eliminado");
		}else {
			return ResponseEntity.badRequest().body("el usuario no existe o no se puede encontrar");
		}
	}
}
