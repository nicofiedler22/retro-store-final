package cl.duoc.autenticador.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import cl.duoc.autenticador.model.Rol;

public interface RolRepository extends JpaRepository<Rol, Long> {

}