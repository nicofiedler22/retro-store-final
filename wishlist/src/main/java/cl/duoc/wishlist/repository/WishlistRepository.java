package cl.duoc.wishlist.repository;

import cl.duoc.wishlist.model.ItemWishlist;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;
import java.util.Optional;


public interface WishlistRepository extends JpaRepository<ItemWishlist, Long> {

    List<ItemWishlist> findByUsuarioIdOrderByFechaAgregadoDesc(Long usuarioId);

    Optional<ItemWishlist> findByUsuarioIdAndProductoId(Long usuarioId, Long productoId);

    boolean existsByUsuarioIdAndProductoId(Long usuarioId, Long productoId);

    void deleteByUsuarioIdAndProductoId(Long usuarioId, Long productoId);

}
