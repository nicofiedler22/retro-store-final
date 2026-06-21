package cl.duoc.pagos.repository;

import cl.duoc.pagos.model.EstadoPago;
import cl.duoc.pagos.model.Pago;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;


public interface PagoRepository extends JpaRepository<Pago, Long> {

    Optional<Pago> findByTokenTransaccion(String tokenTransaccion);

    List<Pago> findByUsuarioId(Long usuarioId);

    List<Pago> findByEstado(EstadoPago estado);

}
