package cl.duoc.wishlist.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "wishlist_items",
        uniqueConstraints = @UniqueConstraint(
                name = "uk_usuario_producto",
                columnNames = {"usuario_id", "producto_id"}))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class ItemWishlist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "usuario_id", nullable = false)
    private Long usuarioId;

    @Column(name = "producto_id", nullable = false)
    private Long productoId;

    @Column(name = "nombre_producto", length = 150, nullable = false)
    private String nombreProducto;

    @Column(name = "precio_referencia", precision = 12, scale = 2)
    private BigDecimal precioReferencia;

    @Column(name = "fecha_agregado", nullable = false)
    private LocalDateTime fechaAgregado;

    @PrePersist
    public void alCrear() {
        if (this.fechaAgregado == null) {
            this.fechaAgregado = LocalDateTime.now();
        }
    }

}
