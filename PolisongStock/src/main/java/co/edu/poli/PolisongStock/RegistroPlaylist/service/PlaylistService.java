package co.edu.poli.PolisongStock.RegistroPlaylist.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.poli.PolisongStock.RegistroPlaylist.modelo.Playlist;
import co.edu.poli.PolisongStock.RegistroPlaylist.repository.PlaylistRepository;


@Service
public class PlaylistService {
	@Autowired
	private PlaylistRepository playlistRepository;
	
	@Transactional(transactionManager = "PlaylistTransactionManager")
	public Playlist createPlaylist(Playlist playlist) {
		return playlistRepository.save(playlist); // This triggers JPA to insert into DB
	}
	
	  @Transactional(transactionManager = "PlaylistTransactionManager", readOnly = true)
	  public List<Playlist> getAllPlaylist() {
	    List<Playlist> list = playlistRepository.findAll();
	    // inicializar colecciones si las vas a serializar
	    list.forEach(pl -> pl.getCanciones().size());
	    return list;
	  }

	@Transactional(transactionManager = "PlaylistTransactionManager")
	public boolean deletePlaylist(Long id) {
	    if (playlistRepository.existsById(id)) {
	        playlistRepository.deleteById(id);
	        return true;
	    }
	    return false;  // Returns false if ID not found
	}
	  @Transactional(transactionManager = "PlaylistTransactionManager", readOnly = true)
	  public Optional<Playlist> getPlaylistById(Long id) {
	    Optional<Playlist> p = playlistRepository.findById(id);
	    if (p.isPresent()) {
	      // fuerza inicialización dentro de la transacción
	      p.get().getCanciones().size();
	    }
	    return p;
	  }

}
