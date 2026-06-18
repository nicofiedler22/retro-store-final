package cl.duoc.autenticador.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import cl.duoc.autenticador.model.Autenticador;
import java.util.Optional;

public interface AutenticadorRepository extends JpaRepository<Autenticador, Long> {

    Optional<Autenticador> findByCorreo(String correo);
}