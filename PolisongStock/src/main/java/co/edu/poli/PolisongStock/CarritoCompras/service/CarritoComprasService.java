package co.edu.poli.PolisongStock.CarritoCompras.service;

import co.edu.poli.PolisongStock.CarritoCompras.modelo.CarritoCompras;
import co.edu.poli.PolisongStock.CarritoCompras.modelo.ItemCarrito;
import co.edu.poli.PolisongStock.CarritoCompras.repository.CarritoComprasRepository;
import co.edu.poli.PolisongStock.CarritoCompras.repository.ItemCarritoRepository;
import co.edu.poli.PolisongStock.RegistroCancion.modelo.Cancion;
import co.edu.poli.PolisongStock.RegistroCancion.service.CancionService;
import co.edu.poli.PolisongStock.RegistroPlaylist.modelo.Playlist;
import co.edu.poli.PolisongStock.RegistroPlaylist.service.PlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

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
    
    @Transactional(transactionManager = "CarritoComprasTransactionManager")
    public CarritoCompras addToCart(Long userId, Long itemId, String itemType, int quantity) {
        CarritoCompras cart = getOrCreateCart(userId);  // Uses findByUserId
        Optional<ItemCarrito> existingItem = itemCarritoRepository.findByCartIdAndItemIdAndItemType(cart.getIdCart(), itemId, itemType);
        
        if (existingItem.isPresent()) {
            ItemCarrito item = existingItem.get();
            item.setCantidad(item.getCantidad() + quantity);
            itemCarritoRepository.save(item);
        } else {
            ItemCarrito newItem = new ItemCarrito();
            newItem.setCarrito(cart);
            newItem.setItemId(itemId);
            newItem.setTipoItem(itemType);
            newItem.setCantidad(quantity);
            if ("SONG".equals(itemType)) {
                Optional<Cancion> cancion = cancionService.getCancionById(itemId);  // Assuming you have this
                newItem.setPrecio(cancion.map(Cancion::getPrecio).orElse(0.0));
            } else if ("PLAYLIST".equals(itemType)) {
                Optional<Playlist> playlist = playlistService.getPlaylistById(itemId);
                newItem.setPrecio(playlist.map(p -> calculatePlaylistPrice(p)).orElse(0.0));
            }
            itemCarritoRepository.save(newItem);
        }
        return cart;
    }

    @Transactional(readOnly = true, transactionManager = "CarritoComprasTransactionManager")
    public Optional<CarritoCompras> getCartByUserId(Long userId) {
        return carritoComprasRepository.findByUserId(userId);  // Uses the new method
    }

    @Transactional(transactionManager = "CarritoComprasTransactionManager")
    public CarritoCompras checkoutCart(Long userId) {
        CarritoCompras cart = carritoComprasRepository.findByUserId(userId).orElseThrow();
        // Process checkout logic...
        cart.getItems().clear();
        carritoComprasRepository.save(cart);
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
        List<Cancion> canciones = cancionService.getCancionesByIdsRaw(songIds);  // Uses the new method
        return canciones.stream().mapToDouble(Cancion::getPrecio).sum();
    }
}