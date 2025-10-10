package co.edu.poli.PolisongStock.Playlist.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.edu.poli.PolisongStock.RegistroPlaylist.Modelo.Playlist;


@Repository
public interface PlaylistRepository  extends JpaRepository<Playlist, Long>{

}
