package cl.duoc.autenticador.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import cl.duoc.autenticador.model.Autenticador;

public interface AutenticadorRepository extends JpaRepository<Autenticador, Long> {

}