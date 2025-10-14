package co.edu.poli.PolisongStock.RegistroPlaylist.controller;

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
import co.edu.poli.PolisongStock.RegistroPlaylist.dto.PlaylistCreateDto;
import co.edu.poli.PolisongStock.RegistroPlaylist.dto.PlaylistWithSongsDto;
import co.edu.poli.PolisongStock.RegistroPlaylist.modelo.Playlist;
import co.edu.poli.PolisongStock.RegistroPlaylist.service.PlaylistService;

@RestController
@RequestMapping("/api/playlist")

public class PlaylistController {

	@Autowired
	private PlaylistService playlistService;
	
	@PostMapping 
	public ResponseEntity<Playlist> create(@RequestBody PlaylistCreateDto playlist){
		Playlist saved = playlistService.findOrCreatePlaylist(playlist);
		return ResponseEntity.ok(saved);
	}
	
	@GetMapping
	public ResponseEntity<List<Playlist>> getAll(){
		return ResponseEntity.ok(playlistService.getAllPlaylist());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<PlaylistWithSongsDto> getPlaylistWithSongsById(@PathVariable Long id){
		PlaylistWithSongsDto dto = playlistService.getPlaylistWithSongsById(id);
		return ResponseEntity.ok(dto);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<String> update( @PathVariable Long id, @RequestBody String updatePlaylist){
		return ResponseEntity.ok("playlist actualizada");
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete (@PathVariable Long id){
		boolean deleted = playlistService.deletePlaylist(id);
		if (deleted) {
			return ResponseEntity.noContent().build(); // 204 No Content on success
		} else {
			return ResponseEntity.notFound().build(); // 404 if not found
		}
	}
}
