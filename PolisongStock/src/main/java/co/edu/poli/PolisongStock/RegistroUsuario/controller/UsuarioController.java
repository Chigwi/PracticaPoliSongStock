package co.edu.poli.PolisongStock.RegistroUsuario.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/usuarios")

public class UsuarioController {

	//TODO usuarioService
	
	@PostMapping 
	public ResponseEntity<String> create(){
		return ResponseEntity.ok("Usuario creado");
	
	}
	@GetMapping
	public ResponseEntity<List<String>> getAll(){
		ArrayList p = new ArrayList<String>();
		String r = "usuarios";
		p.add(r);
		return ResponseEntity.ok(p);
	}
	@GetMapping("/{id}")
	public ResponseEntity<String> getById(Long id){
		return ResponseEntity.ok("usuario");
		
	}
	@PutMapping("/{id}")
	public ResponseEntity<String> update(Long id, String updateUsuario){
		return ResponseEntity.ok("usuario actualizado");
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete (Long id){
		return ResponseEntity.ok("usuario eliminado");
	}
}
