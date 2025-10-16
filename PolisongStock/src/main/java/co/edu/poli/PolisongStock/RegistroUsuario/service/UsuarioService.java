package co.edu.poli.PolisongStock.RegistroUsuario.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import co.edu.poli.PolisongStock.RegistroUsuario.modelo.Persona;
import co.edu.poli.PolisongStock.RegistroUsuario.repository.UsuarioRepository;



@Service
@Primary
public class UsuarioService implements UserDetailsService{
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	private final PasswordEncoder encoder;
	
	public UsuarioService(PasswordEncoder encoder) {
		this.encoder = encoder;
	}


	public List getAllUsuario() {
		return usuarioRepository.findAll();
	}
	
	public Boolean getOrCreate(Persona persona){
		Optional<Persona> optionalPersona = usuarioRepository.findByNombreUsuario(persona.getNombreUsuario());
		if(!optionalPersona.isPresent()) {
			
			persona.setContrasenna(encoder.encode(persona.getContrasenna()));
			usuarioRepository.save(persona);
			return true; // This triggers JPA to insert into DB
		}else {
			return false;
		}
	}
	
	public boolean deleteUsuario(Long id) {
	    if (usuarioRepository.existsById(id)) {
	        usuarioRepository.deleteById(id);
	        return true;
	    }
	    return false;  // Returns false if ID not found
	}

	public Optional<Persona> getUsuarioById(Long id) {
	    return usuarioRepository.findById(id);
	}


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Persona persona = usuarioRepository.findByNombreUsuario(username).get();
		return User.withUsername(persona.getNombreUsuario())
				.roles("basicusuario")
				.password(persona.getContrasenna())
				.build();
	}

//pss
}
