package co.edu.poli.PolisongStock.RegistroCancion.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.poli.PolisongStock.Notificaciones.modelo.Notificacion;
import co.edu.poli.PolisongStock.Notificaciones.service.NotificacionesService;
import co.edu.poli.PolisongStock.RegistroCancion.modelo.Cancion;
import co.edu.poli.PolisongStock.RegistroCancion.service.CancionService;
import co.edu.poli.PolisongStock.RegistroPlaylist.service.PlaylistService;
import co.edu.poli.PolisongStock.security.AppUserDetails;

@RestController
@RequestMapping("/api/canciones")

public class CancionController {

	@Autowired
	private CancionService cancionService;
	
	@Autowired
	private NotificacionesService notificacionService;
	
	@PreAuthorize("hasRole('basicusuario') or hasRole('superusuario')")
	@PostMapping 
	public ResponseEntity<Cancion> create(@RequestBody Cancion cancion,@AuthenticationPrincipal AppUserDetails currentUser){
		cancion.getFormato().setNombre(cancion.getFormato().getNombre().toLowerCase());
		String nombreProveedor = currentUser.getUsername();
		cancion.setProveedor(nombreProveedor);
		Cancion saved = cancionService.createCancion(cancion);
		return ResponseEntity.ok(saved);
	}
	
	@GetMapping
	public ResponseEntity<List<Cancion>> getAll(){
		return ResponseEntity.ok(cancionService.getAllCancion());
	}
	
	@GetMapping("/cancion/{id}")
	public ResponseEntity<Cancion> getById(@PathVariable Long id){
		Optional<Cancion> cancion = cancionService.getCancionById(id);
        return cancion.map(ResponseEntity::ok)
                     .orElse(ResponseEntity.notFound().build());	
	}
	
	@GetMapping("/proveedor/{nombre}")
	public ResponseEntity<List<Optional<Cancion>>>getByProveedor(@PathVariable String nombre){
		return ResponseEntity.ok(cancionService.getCancionesByProveedor(nombre));
	}

	@GetMapping("/formato/{nombre}")
	public ResponseEntity<List<Cancion>> getByFormato(@PathVariable String nombre){
		return ResponseEntity.ok(cancionService.getByFormatoNombre(nombre));
	}
	
	@PreAuthorize("hasRole('basicusuario') or hasRole('superusuario')")
	@PutMapping("/{id}")
	public ResponseEntity<String> update(Long id, String updateCancion){
		return ResponseEntity.ok("cancion actualizada");
	}
	
	@PreAuthorize("hasRole('basicusuario') or hasRole('superusuario')")
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
