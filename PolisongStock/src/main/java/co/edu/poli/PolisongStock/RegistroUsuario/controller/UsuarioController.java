package co.edu.poli.PolisongStock.RegistroUsuario.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
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
import co.edu.poli.PolisongStock.RegistroPedidos.modelo.Pedido;
import co.edu.poli.PolisongStock.RegistroPedidos.service.PedidoService;
import co.edu.poli.PolisongStock.RegistroPlaylist.dto.PlaylistWithSongsDto;
import co.edu.poli.PolisongStock.RegistroPlaylist.modelo.Playlist;
import co.edu.poli.PolisongStock.RegistroPlaylist.service.PlaylistService;
import co.edu.poli.PolisongStock.RegistroUsuario.modelo.Persona;
import co.edu.poli.PolisongStock.RegistroUsuario.repository.UsuarioRepository;
import co.edu.poli.PolisongStock.RegistroUsuario.service.UsuarioService;
import co.edu.poli.PolisongStock.security.AppUserDetails;

@RestController
@RequestMapping("/api/usuarios")
@EnableMethodSecurity
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private CancionService cancionService;
	
	@Autowired
	private PlaylistService playlistService;
	
	@Autowired
	private PedidoService pedidoService;
	
		

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
	public ResponseEntity<Optional<Persona>> getById(@PathVariable Long id){
		return ResponseEntity.ok(usuarioService.getUsuarioById(id));
		
	}
	
	@GetMapping("/user/{nombreUsuario}")
	public ResponseEntity<Optional<Persona>> getThisUser(@PathVariable String nombreUsuario){
		return ResponseEntity.ok(usuarioService.getUsuarioByUsername(nombreUsuario));
	}
	
	// Authenticated user sees their own songs (provider = username)
    @GetMapping("/misCanciones")
    public ResponseEntity<List<Optional<Cancion>>> getMisCanciones(@AuthenticationPrincipal AppUserDetails currentUser) {
        String nombreProveedor = currentUser.getUsername();
        return ResponseEntity.ok(cancionService.getCancionesByProveedor(nombreProveedor));
    }

    // Authenticated user sees their own playlists (provider = username)
    @GetMapping("/misPlaylist")
    public ResponseEntity<List<PlaylistWithSongsDto>> getMisPlaylist(@AuthenticationPrincipal AppUserDetails currentUser) {
        String nombreProveedor = currentUser.getUsername();
        return ResponseEntity.ok(playlistService.getPlaylistsByProveedor(nombreProveedor));
    }

    // Authenticated user sees their own orders (buyer = username)
    @GetMapping("/misPedidos")
    public ResponseEntity<List<Optional<Pedido>>> getMisPedidos(@AuthenticationPrincipal AppUserDetails currentUser) {
        String nombreComprador = currentUser.getUsername();
        return ResponseEntity.ok(pedidoService.getPedidosByComprador(nombreComprador));
    }

	@PreAuthorize("hasRole('superusuario')")
	@PutMapping("/{id}")
	public ResponseEntity<String> update(@PathVariable Long id, String updateUsuario){
		return ResponseEntity.ok("usuario actualizado");
	}
	@PreAuthorize("hasRole('superusuario')")
	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete (@PathVariable Long id){
		
		if(usuarioService.deleteUsuario(id)) {
			return ResponseEntity.ok("usuario eliminado");
		}else {
			return ResponseEntity.badRequest().body("el usuario no existe o no se puede encontrar");
		}
	}
	
}
