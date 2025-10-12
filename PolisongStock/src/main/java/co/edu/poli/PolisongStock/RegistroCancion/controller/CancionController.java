package co.edu.poli.PolisongStock.RegistroCancion.controller;

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
@RequestMapping("/api/canciones")

public class CancionController {

	//TODO cancionService
	
	@PostMapping 
	public ResponseEntity<String> create(){
		return ResponseEntity.ok("Cancion creada");
	}
	
	@GetMapping
	public ResponseEntity<List<String>> getAll(){
		ArrayList p = new ArrayList<String>();
		String r = "canciones";
		p.add(r);
		return ResponseEntity.ok(p);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<String> getById(Long id){
		return ResponseEntity.ok("cancion");	
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<String> update(Long id, String updateCancion){
		return ResponseEntity.ok("cancion actualizada");
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete (Long id){
		return ResponseEntity.ok("cancion eliminada");
	}
}
