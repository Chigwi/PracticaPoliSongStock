package co.edu.poli.PolisongStock.CarritoCompras.service;

import co.edu.poli.PolisongStock.CarritoCompras.modelo.CarritoCompras;
import co.edu.poli.PolisongStock.CarritoCompras.modelo.ItemCarrito;
import co.edu.poli.PolisongStock.CarritoCompras.repository.CarritoComprasRepository;
import co.edu.poli.PolisongStock.CarritoCompras.repository.ItemCarritoRepository;
import co.edu.poli.PolisongStock.Notificaciones.modelo.Notificacion;
import co.edu.poli.PolisongStock.Notificaciones.service.NotificacionesService;
import co.edu.poli.PolisongStock.RegistroCancion.dto.CancionDetailDto;
import co.edu.poli.PolisongStock.RegistroCancion.modelo.Cancion;
import co.edu.poli.PolisongStock.RegistroCancion.service.CancionService;
import co.edu.poli.PolisongStock.RegistroPedidos.modelo.DetallePedido;
import co.edu.poli.PolisongStock.RegistroPedidos.modelo.Envio;
import co.edu.poli.PolisongStock.RegistroPedidos.modelo.Experiencia;
import co.edu.poli.PolisongStock.RegistroPedidos.modelo.Pedido;
import co.edu.poli.PolisongStock.RegistroPedidos.service.PedidoService;
import co.edu.poli.PolisongStock.RegistroPlaylist.modelo.Playlist;
import co.edu.poli.PolisongStock.RegistroPlaylist.service.PlaylistService;
import co.edu.poli.PolisongStock.RegistroUsuario.controller.UsuarioController;
import co.edu.poli.PolisongStock.RegistroUsuario.modelo.Persona;
import co.edu.poli.PolisongStock.RegistroUsuario.repository.UsuarioRepository;
import co.edu.poli.PolisongStock.RegistroUsuario.service.UsuarioService;
import net.sf.jsqlparser.statement.alter.EnableConstraint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class CarritoComprasService {

    @Autowired
    private CarritoComprasRepository carritoComprasRepository;
    
    @Autowired
    private ItemCarritoRepository itemCarritoRepository;
    
    @Autowired
    private CancionService cancionService;
    
    @Autowired
    private PlaylistService playlistService;
    
    @Autowired
    private NotificacionesService notificacionService;
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Autowired
    private PedidoService pedidoService;
    
    @Transactional(transactionManager = "CarritoComprasTransactionManager")
    public CarritoCompras addToCart(Long userId, Long itemId, String itemType, int quantity) {
        CarritoCompras cart = getOrCreateCart(userId);  // Uses findByUserId
        Optional<ItemCarrito> existingItem = itemCarritoRepository.findByCartIdAndItemIdAndTipoItem(cart.getIdCart(), itemId, itemType);
        
        if (existingItem.isPresent()) {
            ItemCarrito item = existingItem.get();
            item.setCantidad(item.getCantidad() + quantity);
            item.setPrecio(item.getPrecio() * item.getCantidad());
            itemCarritoRepository.save(item);
        } else {
            ItemCarrito newItem = new ItemCarrito();
            newItem.setCarrito(cart);
            newItem.setItemId(itemId);
            newItem.setTipoItem(itemType);
            newItem.setCantidad(quantity);
            if ("cancion".equals(itemType)) {
                Optional<Cancion> cancion = cancionService.getCancionById(itemId);
                newItem.setPrecio(cancion.get().getPrecio()*quantity);
            } else if ("playlist".equals(itemType)) {
                Optional<Playlist> playlist = playlistService.getPlaylistById(itemId);
                newItem.setPrecio(playlist.map(p -> calculatePlaylistPrice(p)).orElse(0.0));
            }
            itemCarritoRepository.save(newItem);
        }
        cart.getItems().size();
        return cart;
    }

    @Transactional(readOnly = true, transactionManager = "CarritoComprasTransactionManager")
    public Optional<CarritoCompras> getCartByUserId(Long userId) {
    	Optional<CarritoCompras> r = Optional.ofNullable(getOrCreateCart(userId)); 
    	r.get().getItems().size();
        return r;
    }

    @Transactional(transactionManager = "CarritoComprasTransactionManager")
    public CarritoCompras checkoutCart(Long userId) {
        Persona usuario = usuarioRepository.findById(userId)
            .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado: " + userId));
        CarritoCompras cart = carritoComprasRepository.findByUserId(userId)
            .orElseThrow(() -> new IllegalStateException("No cart for user: " + userId));

        if (usuario.getCorreos() != null) {
            usuario.getCorreos().size();
        }


        if (!pagoRealizado()) {
            return cart; // payment failed - keep cart intact
        }

        // Build DetallePedido and Pedido
        DetallePedido dp = new DetallePedido();
        dp.setCaniones(new ArrayList());
        List <String> nombres = new ArrayList<String>();
        for (ItemCarrito it : cart.getItems()) {
            dp.getCaniones().add(it.getItemId()); // use correct getter for item id
            if(it.getTipoItem().equals("cancion")) {
            	String nombre = cancionService.getCancionById(it.getItemId()).get().getNombre();
                nombres.add("tipoItem: " + it.getTipoItem() + " nombre: " + nombre);
            }else if(it.getTipoItem().equals("playlist")) {
            	String nombre = playlistService.getPlaylistById(it.getIdItem()).get().getNombre();
                nombres.add("tipoItem: " + it.getTipoItem() + " nombre: " + nombre);
            }
        }
        Envio env = new Envio();
        env.setEmpresaEnvios("Envia");
        env.setEsAereo(true);
        env.setEstimacionLlegada("De 1 a 2 semanas");

        Experiencia exp = new Experiencia();

        Pedido p = new Pedido();
        p.setComprador(usuario.getNombreUsuario()); // or getNombre() depending on your Persona
        p.setEnvio(env);
        p.setExperiencia(exp);
        p.setFecha(new Date().toString());
        p.setDetallePedido(dp);

        // persist the pedido (requires PedidoRepository)
        Pedido saved = pedidoService.createPedido(p);

        // Send notification (guard for nulls)
        
        if (usuario.getCorreos() != null && !usuario.getCorreos().isEmpty()) {
        	double precio = getPrecioTotal(cart);
            Notificacion n = new Notificacion();
            n.setToEmail(usuario.getCorreos().get(0).getDireccion());//refractor
            n.setBody(saved.factura(precio));
            n.setSubject("Pedido realizado con Exito");
            notificacionService.sendEmail(n); // use the actual method from your service
        }

        // clear cart items and save cart
        cart.getItems().clear();
        carritoComprasRepository.save(cart);

        return cart;
    }

    @Transactional(transactionManager = "CarritoComprasTransactionManager")
    public CarritoCompras createCart(Long userId) {
    	CarritoCompras cart = getOrCreateCart(userId);
        return carritoComprasRepository.save(cart);
    }
    
    @Transactional(transactionManager = "CarritoComprasTransactionManager")
    public CarritoCompras removeFromCart(Long userId, Long itemId, String itemType, int quantityToRemove) {
        if (quantityToRemove <= 0) {
            throw new IllegalArgumentException("quantityToRemove must be > 0");
        }

        // 1) load cart while transaction/session is open
        CarritoCompras cart = carritoComprasRepository.findByUserId(userId)
            .orElseThrow(() -> new IllegalStateException("No cart for user: " + userId));

        // 2) ensure items collection is initialized so we operate on the live list
        cart.getItems().size();

        // 3) locate the item in the in-memory collection
        ItemCarrito item = cart.getItems().stream()
            .filter(i -> i.getItemId().equals(itemId) && i.getTipoItem().equalsIgnoreCase(itemType))
            .findFirst()
            .orElse(null);

        if (item == null) {
            // nothing to remove, return the fully-initialized cart
            return cart;
        }

        // 4) modify or delete
        if (item.getCantidad() > quantityToRemove) {
            int newQty = item.getCantidad() - quantityToRemove;
            double unitPrice = item.getPrecio() / item.getCantidad(); // current total / current qty
            item.setCantidad(newQty);
            item.setPrecio(unitPrice * newQty);
            itemCarritoRepository.save(item); // persist change
        } else {
            // remove DB row and remove from in-memory collection
            Long pk = item.getIdItem();           // use your actual PK getter name
            itemCarritoRepository.deleteById(pk);
            cart.getItems().removeIf(i -> i.getIdItem().equals(pk));
        }

        // 5) flush persistence context so DB changes are applied before returning
        carritoComprasRepository.flush(); // JpaRepository exposes flush()

        // 6) return the cart instance that was loaded inside the transaction
        // it's fully initialized and reflects the removal because we updated the in-memory list
        return cart;
    }

    

    private CarritoCompras getOrCreateCart(Long userId) {
        return carritoComprasRepository.findByUserId(userId).orElseGet(() -> {
            CarritoCompras newCart = new CarritoCompras();
            newCart.setUserId(userId);
            return carritoComprasRepository.save(newCart);
        });
    }
    
   
    private Double calculatePlaylistPrice(Playlist playlist) {
        List<Long> songIds = playlist.getCanciones();
        List<Cancion> canciones = cancionService.getCancionesByIdsRaw(songIds);
        return canciones.stream().mapToDouble(Cancion::getPrecio).sum();
    }
    
    private CarritoCompras fetchCartWithItems(Long userId, CarritoCompras fallbackCart) {
        // Preferred repository method (see below) - returns cart with items fetched
        Optional<CarritoCompras> full = carritoComprasRepository.findById(userId);
        if (full.isPresent()) return full.get();

        // Fallback: ensure the existing cart's collection is initialized while transaction is open
        fallbackCart.getItems().size();
        return fallbackCart;
    }
    
    private double getPrecioTotal(CarritoCompras cart) {
    	double precioToti = 0.0;
    	for (int i = 0; i < cart.getItems().size(); i++) {
			precioToti = precioToti + cart.getItems().get(i).getPrecio();
		}
    	return precioToti;
    }
    private boolean pagoRealizado() {
    	Random pago = new Random();
    	if(pago.nextInt(100)%2 == 0) {
        	return true;
    	}else {
    		return false;
    	}
    }
    
}