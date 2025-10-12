package co.edu.poli.PolisongStock.RegistroCancion.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.poli.PolisongStock.RegistroCancion.modelo.Cancion;
import co.edu.poli.PolisongStock.RegistroCancion.service.CancionService;

@RestController
@RequestMapping("/api/canciones")

public class CancionController {

	@Autowired
	private CancionService cancionService;
	
	@PostMapping 
	public ResponseEntity<Cancion> create(@RequestBody Cancion cancion){
		Cancion saved = cancionService.createCancion(cancion);
		return ResponseEntity.ok(saved);
	}
	
	@GetMapping
	public ResponseEntity<List<Cancion>> getAll(){
		return ResponseEntity.ok(cancionService.getAllCancion());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Cancion> getById(@PathVariable Long id){
		Optional<Cancion> cancion = cancionService.getCancionById(id);
        return cancion.map(ResponseEntity::ok)
                     .orElse(ResponseEntity.notFound().build());	
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<String> update(Long id, String updateCancion){
		return ResponseEntity.ok("cancion actualizada");
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete ( @PathVariable Long id){
		boolean deleted = cancionService.deleteCancion(id);
        if (deleted) {
            return ResponseEntity.noContent().build();  // 204 No Content on success
        } else {
            return ResponseEntity.notFound().build();  // 404 if not found
        }
	}
}
