package co.edu.poli.PolisongStock.RegistroPlaylist.controller;

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
@RequestMapping("/api/playlist")

public class PlaylistController {

	//TODO playlistService
	
	@PostMapping 
	public ResponseEntity<String> create(){
		return ResponseEntity.ok("Playlist creada");
	}
	
	@GetMapping
	public ResponseEntity<List<String>> getAll(){
		ArrayList p = new ArrayList<String>();
		String r = "playlist";
		p.add(r);
		return ResponseEntity.ok(p);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<String> getById(Long id){
		return ResponseEntity.ok("playlist");	
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<String> update(Long id, String updatePlaylist){
		return ResponseEntity.ok("playlist actualizada");
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete (Long id){
		return ResponseEntity.ok("playlist eliminada");
	}
}
