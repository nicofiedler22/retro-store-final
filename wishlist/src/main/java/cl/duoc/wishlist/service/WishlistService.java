package cl.duoc.wishlist.service;

import cl.duoc.wishlist.dto.WishlistRequestDTO;
import cl.duoc.wishlist.dto.WishlistResponseDTO;
import cl.duoc.wishlist.model.ItemWishlist;
import cl.duoc.wishlist.repository.WishlistRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WishlistService {

    private final WishlistRepository wishlistRepository;

    public List<WishlistResponseDTO> listarPorUsuario(Long usuarioId) {
        return wishlistRepository.findByUsuarioIdOrderByFechaAgregadoDesc(usuarioId).stream()
                .map(this::toResponseDTO)
                .toList();
    }

    public WishlistResponseDTO obtenerPorId(Long id) {
        return toResponseDTO(buscarPorId(id));
    }

    public boolean existeEnWishlist(Long usuarioId, Long productoId) {
        return wishlistRepository.existsByUsuarioIdAndProductoId(usuarioId, productoId);
    }

    public WishlistResponseDTO agregar(WishlistRequestDTO dto) {
        if (wishlistRepository.existsByUsuarioIdAndProductoId(dto.getUsuarioId(), dto.getProductoId())) {
            throw new IllegalArgumentException("El producto ya se encuentra en la wishlist del usuario");
        }

        ItemWishlist item = ItemWishlist.builder()
                .usuarioId(dto.getUsuarioId())
                .productoId(dto.getProductoId())
                .nombreProducto(dto.getNombreProducto())
                .precioReferencia(dto.getPrecioReferencia())
                .build();

        return toResponseDTO(wishlistRepository.save(item));
    }

    public void eliminar(Long id) {
        if (!wishlistRepository.existsById(id)) {
            throw new EntityNotFoundException("No existe un item de wishlist con id " + id);
        }
        wishlistRepository.deleteById(id);
    }

    @Transactional
    public void eliminarPorUsuarioYProducto(Long usuarioId, Long productoId) {
        if (!wishlistRepository.existsByUsuarioIdAndProductoId(usuarioId, productoId)) {
            throw new EntityNotFoundException("El producto no se encuentra en la wishlist del usuario");
        }
        wishlistRepository.deleteByUsuarioIdAndProductoId(usuarioId, productoId);
    }

    private ItemWishlist buscarPorId(Long id) {
        return wishlistRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No existe un item de wishlist con id " + id));
    }

    private WishlistResponseDTO toResponseDTO(ItemWishlist item) {
        return WishlistResponseDTO.builder()
                .id(item.getId())
                .usuarioId(item.getUsuarioId())
                .productoId(item.getProductoId())
                .nombreProducto(item.getNombreProducto())
                .precioReferencia(item.getPrecioReferencia())
                .fechaAgregado(item.getFechaAgregado())
                .build();
    }

}
