package co.edu.poli.PolisongStock.RegistroUsuario.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.poli.PolisongStock.RegistroUsuario.modelo.Persona;
import co.edu.poli.PolisongStock.RegistroUsuario.repository.UsuarioRepository;



@Service
public class UsuarioService {
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public Persona createUsuario(Persona persona) {
		return usuarioRepository.save(persona); // This triggers JPA to insert into DB
	}

	public List getAllUsuario() {
		return usuarioRepository.findAll();
	}
	public boolean deleteUsuario(Long id) {
	    if (usuarioRepository.existsById(id)) {
	        usuarioRepository.deleteById(id);
	        return true;
	    }
	    return false;  // Returns false if ID not found
	}

	public Optional<Persona> getPlaylistById(Long id) {
	    return usuarioRepository.findById(id);
	}


}
