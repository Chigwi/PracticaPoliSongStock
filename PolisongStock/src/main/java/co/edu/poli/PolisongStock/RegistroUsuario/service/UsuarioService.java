package co.edu.poli.PolisongStock.RegistroUsuario.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import co.edu.poli.PolisongStock.CarritoCompras.service.CarritoComprasService;
import co.edu.poli.PolisongStock.RegistroUsuario.modelo.Persona;
import co.edu.poli.PolisongStock.RegistroUsuario.modelo.Rol;
import co.edu.poli.PolisongStock.RegistroUsuario.repository.RolRepository;
import co.edu.poli.PolisongStock.RegistroUsuario.repository.UsuarioRepository;
import co.edu.poli.PolisongStock.security.AppUserDetails;

@Service
@Primary
public class UsuarioService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private RolRepository rolRepository;
    
    @Autowired
    private CarritoComprasService carritoComprasService;

    private final PasswordEncoder encoder;

    public UsuarioService(PasswordEncoder encoder) {
        this.encoder = encoder;
    }

    public List<Persona> getAllUsuario() {
        return usuarioRepository.findAll();
    }

    public Boolean getOrCreate(Persona persona) {
        Optional<Persona> optionalPersona = usuarioRepository.findByNombreUsuario(persona.getNombreUsuario());

        if (!optionalPersona.isPresent()) {
            persona.setContrasenna(encoder.encode(persona.getContrasenna()));

            Optional<Rol> basic = rolRepository.findById(2L);
            if (basic.isPresent()) {
                persona.setRol(basic.get());
                Persona p = usuarioRepository.save(persona);
                carritoComprasService.createCart(p.getIdPersona());
                return true;
            } else {
                throw new RuntimeException("Rol básico no encontrado");
            }
        } else {
            return false;
        }
    }

    public boolean deleteUsuario(Long id) {
        if (usuarioRepository.existsById(id)) {
            usuarioRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Optional<Persona> getUsuarioById(Long id) {
        return usuarioRepository.findById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Persona persona = usuarioRepository.findByNombreUsuario(username)
            .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

        List<GrantedAuthority> authorities = List.of(
            new SimpleGrantedAuthority("ROLE_" + persona.getRol().getNombre())
        );

        return new AppUserDetails(persona.getIdPersona(), persona.getNombreUsuario(), persona.getContrasenna(), authorities);
    }
}