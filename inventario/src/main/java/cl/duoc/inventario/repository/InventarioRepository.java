package cl.duoc.inventario.repository;

import cl.duoc.inventario.model.Inventario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository

public interface InventarioRepository extends JpaRepository<Inventario, Long> {
    Optional<Inventario> findByProductoId(Long productoId);
    List<Inventario> findByActivoTrue();
    List<Inventario> findByNombreContainingIgnoreCase(String nombre);

    @Query("SELECT i FROM Inventario i WHERE i.cantidad <= i.stockMinimo AND i.activo = true")
    List<Inventario> findBajoStockMinimo();
}
