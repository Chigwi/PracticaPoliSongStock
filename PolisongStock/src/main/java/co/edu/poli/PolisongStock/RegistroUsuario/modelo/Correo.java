package co.edu.poli.PolisongStock.RegistroUsuario.modelo;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity

@Table(name="Correo")

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Correo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idCorreo;
	
	@Column(name="direccion")
	private String direccion;
	
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "persona")
    @JsonBackReference
    private Persona persona;


}
