package co.edu.poli.PolisongStock.RegistroPlaylist.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.poli.PolisongStock.RegistroCancion.dto.CancionCreateDto;
import co.edu.poli.PolisongStock.RegistroCancion.modelo.Cancion;
import co.edu.poli.PolisongStock.RegistroCancion.modelo.Formato;
import co.edu.poli.PolisongStock.RegistroCancion.repository.CancionRepository;
import co.edu.poli.PolisongStock.RegistroPlaylist.dto.PlaylistCreateDto;
import co.edu.poli.PolisongStock.RegistroPlaylist.modelo.Playlist;
import co.edu.poli.PolisongStock.RegistroPlaylist.repository.PlaylistRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;


@Service
public class PlaylistService {
	@Autowired
	private PlaylistRepository playlistRepository;
	
	@Autowired
	private CancionRepository cancionRepository;
	
    @PersistenceContext(unitName = "playlist")
    private EntityManager entityManager;
	
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
	  @Transactional(transactionManager = "PlaylistTransactionManager")
	  public Playlist findOrCreatePlaylist(PlaylistCreateDto dto) {
		  Optional<Playlist> existingPlaylist = playlistRepository.findByNombre(dto.getNombre());
		  Playlist playlist;
		  
		  if(existingPlaylist.isPresent()) {
			  playlist = existingPlaylist.get();
			  System.out.println("Playlist found" + playlist.getNombre());
			  playlist.getCanciones().clear();
		  }else {
			  playlist = new Playlist();
			  playlist.setNombre(dto.getNombre());
			  playlist.setCanciones(new ArrayList<>());
			  System.out.println("Playlist created" + dto.getNombre());
		  }
		  
		  if(dto.getCanciones() != null && !dto.getCanciones().isEmpty()) {
			  for(CancionCreateDto songDto : dto.getCanciones()) {
				  Long cancionId = findOrCreateCancion(songDto);
				  if(cancionId != null) {
					  playlist.getCanciones().add(cancionId);
				  }
			  }
			  entityManager.flush();
		  }
		  
		  Playlist saved = playlistRepository.save(playlist);
		  System.out.println("Saved Playlist ID: " + saved.getIdPlaylist() + " with " + saved.getCanciones().size() + " songs");
		  return saved;
	  }
	  @Transactional(transactionManager = "cancionTransactionManager", readOnly = true)
	  private Long findOrCreateCancion (CancionCreateDto dto) {
		  Optional <Cancion> existingCancion = cancionRepository.findByNombreAndArtista(dto.getNombre(), dto.getArtista());
		  if(existingCancion.isPresent()) {
			  return existingCancion.get().getIdCancion();
		  }
		  
		  Cancion newCancion = new Cancion();
		  newCancion.setNombre(dto.getNombre());
		  newCancion.setArtista(dto.getArtista());
		  newCancion.setAnnoPublicacion(dto.getAnnoPublicacion() != null ? dto.getAnnoPublicacion() : "Unknown");
		  newCancion.setPrecio(dto.getPrecio() != null ? dto.getPrecio() : 0.0);
		  
		  if(dto.getFormato()!= null) {
			  Formato formato = new Formato();
			  formato.setNombre(dto.getFormato().getNombre());
			  formato.setCantidad(dto.getFormato().getCantidad());
			  
			  newCancion.setFormato(formato);
		  }
		  Cancion savedCancion = cancionRepository.save(newCancion);
		  System.out.println("Created new Cancion ID: " + savedCancion.getIdCancion() + " - " + dto.getNombre());
		  return savedCancion.getIdCancion();
		  
	  }

}
