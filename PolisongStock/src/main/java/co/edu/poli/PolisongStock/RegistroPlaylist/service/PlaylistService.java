package co.edu.poli.PolisongStock.RegistroPlaylist.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.poli.PolisongStock.RegistroCancion.dto.CancionCreateDto;
import co.edu.poli.PolisongStock.RegistroCancion.dto.CancionDetailDto;
import co.edu.poli.PolisongStock.RegistroCancion.modelo.Cancion;
import co.edu.poli.PolisongStock.RegistroCancion.modelo.Formato;
import co.edu.poli.PolisongStock.RegistroCancion.repository.CancionRepository;
import co.edu.poli.PolisongStock.RegistroCancion.service.CancionService;
import co.edu.poli.PolisongStock.RegistroPlaylist.dto.PlaylistCreateDto;
import co.edu.poli.PolisongStock.RegistroPlaylist.dto.PlaylistWithSongsDto;
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
	
	@Autowired
	private CancionService cancionService;
	
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
	      Optional<Playlist> optionalPlaylist = playlistRepository.findById(id);
	      
	      if (optionalPlaylist.isPresent()) {
	          Playlist playlist = optionalPlaylist.get();
	          List<Long> cancionIds = playlist.getCanciones();

	          // Verificar si todas las canciones tienen formato vinilo
	          boolean todasVinilo = true;
	          for (Long cancionId : cancionIds) {
	              Optional<Cancion> optionalCancion = cancionRepository.findById(cancionId);
	              if (optionalCancion.isEmpty() || optionalCancion.get().getFormato() == null ||
	                  !optionalCancion.get().getFormato().getNombre().equalsIgnoreCase("vinilo")) {
	                  todasVinilo = false;
	                  break;
	              }
	          }

	          // Si todas son vinilo, eliminar canciones
	          if (todasVinilo) {
	              for (Long cancionId : cancionIds) {
	                  cancionRepository.deleteById(cancionId);
	              }
	          }

	          // Eliminar la playlist
	          playlistRepository.deleteById(id);
	          return true;
	      }

	      return false; // No se encontró la playlist
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
	  @Transactional(transactionManager = "cancionTransactionManager")
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
			  formato.setNombre(dto.getFormato().getNombre().toLowerCase());
			  formato.setCantidad(dto.getFormato().getCantidad());
			  
			  newCancion.setFormato(formato);
		  }
		  Cancion savedCancion = cancionRepository.save(newCancion);
		  System.out.println("Created new Cancion ID: " + savedCancion.getIdCancion() + " - " + dto.getNombre());
		  return savedCancion.getIdCancion();
		  
	  }
	  
	    @Transactional(readOnly = true, transactionManager = "PlaylistTransactionManager")  // Read-only for Playlist DB
	    public PlaylistWithSongsDto getPlaylistWithSongsById(Long id) {
	        Optional<Playlist> optionalPlaylist = playlistRepository.findById(id);
	        if (optionalPlaylist.isPresent()) {
	            Playlist playlist = optionalPlaylist.get();
	            List<Long> cancionIds = playlist.getCanciones();  // Get IDs
	            
	            // Fetch Cancion details from Cancion DB (cross-DB call)
	            List<CancionDetailDto> cancionDetails = cancionService.getCancionDetailsByIds(cancionIds);  // NEW: Call CancionService
	            
	            PlaylistWithSongsDto dto = new PlaylistWithSongsDto();
	            dto.setId(playlist.getIdPlaylist());
	            dto.setNombre(playlist.getNombre());
	            dto.setCanciones(cancionDetails);  // Set enriched list
	            return dto;
	        }
	        throw new RuntimeException("Playlist not found with ID: " + id);
	    }
	    
	    @Transactional(readOnly = true,transactionManager = "PlaylistTransactionManager")
	    public List<Optional<Playlist>>getPlaylistsByProveedor(String nombre){
	    	return playlistRepository.findByProveedor(nombre);
	    }

}
