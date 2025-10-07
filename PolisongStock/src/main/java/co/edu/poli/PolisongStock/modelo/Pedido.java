package co.edu.poli.PolisongStock.modelo;

import org.hibernate.annotations.ValueGenerationType;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity

@Table(name="Pedido")

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pedido {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long idPedido;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="envioId")
	private Envio envio;
	
	@OneToMany(cascade= CascadeType.ALL)
	@JoinColumn(name="pedidoId")
	@Column(name="detallePedido")
	private DetallePedido detallePedido;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="experienciaId")
	private Experiencia experiencia;
	
	@Column(name="comprobante")
	private String comprobante;
	
	@Column(name="fecha")
	private String fecha;
	
	
}
