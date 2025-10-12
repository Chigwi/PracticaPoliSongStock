package co.edu.poli.PolisongStock.RegistroPlaylist.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.poli.PolisongStock.RegistroPlaylist.modelo.Playlist;
import co.edu.poli.PolisongStock.RegistroPlaylist.respository.PlaylistRepository;


@Service
public class PlaylistService {
	@Autowired
	private PlaylistRepository playlistRepository;
	
	public Playlist createPlaylist(Playlist playlist) {
		return playlistRepository.save(playlist); // This triggers JPA to insert into DB
	}

	public List getAllPlaylist() {
		return playlistRepository.findAll();
	}
	public boolean deletePlaylist(Long id) {
	    if (playlistRepository.existsById(id)) {
	        playlistRepository.deleteById(id);
	        return true;
	    }
	    return false;  // Returns false if ID not found
	}

	public Optional<Playlist> getPlaylistById(Long id) {
	    return playlistRepository.findById(id);
	}

}
